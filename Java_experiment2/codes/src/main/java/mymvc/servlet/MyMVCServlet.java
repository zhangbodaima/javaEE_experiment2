package mymvc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import mymvc.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import mymvc.model.MyModelView;
import org.apache.commons.fileupload.FileItem;
import mymvc.model.HandlerMapping;
import mymvc.model.MVCMapping;
import mymvc.utils.Uploadhandler;
import mymvc.utils.UrlAndMethod;

// Servelet 分发器
public class MyMVCServlet extends HttpServlet {

    Map<UrlAndMethod, MVCMapping> handlerMapping;

    @Override
    public void init(ServletConfig config) {
        try {
            handlerMapping = new HandlerMapping(config).getAllMappings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp, "POST");
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp, String requestMethod) throws IOException {
        String url = req.getRequestURI().replace(req.getContextPath(), "").replace("/mymvc","");
        UrlAndMethod urlAndMethod = new UrlAndMethod(url, requestMethod);

        if(!handlerMapping.containsKey(urlAndMethod)){
            resp.getWriter().write("404 Not Found!");
        }

        List<FileItem> fileItems = null;

        try{
            fileItems = Uploadhandler.getAllFiles(req);
        } catch (Exception e) {
            e.printStackTrace();
        }


        MVCMapping mapping = handlerMapping.get(urlAndMethod);

        Method method = mapping.getMethod();
        ResponseType type = mapping.getResponseType();

        Class<?>[] paramTypes = method.getParameterTypes();
        Map<String, String[]> paramMap = req.getParameterMap();



        Object[] paramValues = new Object[paramTypes.length];

        switch (requestMethod) {
            case "GET":
                int j = 0;
                for(String[] param: paramMap.values()){
                    if (param.length > 1){
                        paramValues[j++] = Arrays.toString(param);
                    }else{
                        paramValues[j++] = param[0];
                    }
                }
                break;
            case "POST":
                boolean fileType = false;
                int fileIndex = 0;
                for(int i = 0; i < paramTypes.length; i++) {
                    String requestParam = paramTypes[i].getSimpleName();
                    if (requestParam.equals("FileItem")) {
                        paramValues[fileIndex] = fileItems.get(fileIndex);
                        fileType = true;
                        fileIndex++;
                    }
                }
                if (!fileType){
                    int k = 0;
                    for(String[] param: paramMap.values()){
                        if (param.length > 1){
                            paramValues[k++] = Arrays.toString(param);
                        }else{
                            paramValues[k++] = param[0];
                        }
                    }
                }
                break;
        }

        try {
            Object res = method.invoke(mapping.getCla(), paramValues);
            switch (type) {
                case Text:
                    // Json 格式
                    ObjectMapper objectMapper = new ObjectMapper();
                    String json = objectMapper.writeValueAsString(res);
                    resp.setContentType("text/html;charset=utf-8");
                    //把方法的执行结果以流的方式返回给前台
                    resp.getWriter().write(json);
                    break;
                case View:
                    MyModelView modelView = (MyModelView) res;

                    for(Map.Entry<String, Object> entry : modelView.getModelMap().entrySet()){
                        req.setAttribute(entry.getKey(), entry.getValue());
                    }
                    // 转发
                    req.getRequestDispatcher("/WEB-INF/" + modelView.getView() +".jsp").forward(req, resp);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
