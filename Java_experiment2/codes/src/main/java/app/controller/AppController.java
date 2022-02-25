package app.controller;

import app.model.Book;
import app.service.AppService;
import app.service.BookService;
import mymvc.annotation.*;
import mymvc.model.MyModelView;
import org.apache.commons.fileupload.FileItem;

import java.io.UnsupportedEncodingException;
import java.util.List;


@MyController
@MyRequestMapping("/app")
public class AppController {


    @MyAutowired
    private AppService appService;
    @MyAutowired
    private BookService bookService;

    @MyRequestMapping(value = "/book", method="POST")
    @ResponseView
    public MyModelView addBook(@MyRequestParam("id") String id, @MyRequestParam("title") String title, @MyRequestParam("author") String author, @MyRequestParam("press") String press) throws UnsupportedEncodingException {
        MyModelView mv = new MyModelView();
//        System.err.println(title + author + press);
        String real_title = new String(title.getBytes("ISO8859_1"), "UTF-8");
        String real_author = new String(author.getBytes("ISO8859_1"), "UTF-8");
        String real_press = new String(press.getBytes("ISO8859_1"), "UTF-8");
        bookService.addBook(Integer.parseInt(id), real_title, real_author, real_press);
        mv.setView("bookinfo");
        mv.addModel("bookList", bookService.getAllBooks());
        return mv;
    }

    @MyRequestMapping(value = "/book", method = "GET")
    @ResponseView
    public MyModelView showBooks(){
        MyModelView modelView = new MyModelView();
        List<Book> bookList = bookService.getAllBooks();
        modelView.setView("bookinfo");
        modelView.addModel("bookList", bookList);
        return modelView;
    }



    @MyRequestMapping(value = "/upload", method = "GET")
    @ResponseView
    public MyModelView upload_page() {
        MyModelView mv = new MyModelView();
        mv.setView("upload");
        return mv;
    }


    @MyRequestMapping(value = "/upload", method = "POST")
    @ResponseView

    public MyModelView upload(@MyRequestParam("file") FileItem source) {

        String path = "C:/Users/dell/Desktop/Exp2_uploadfile_test/"; // 这里暂时是写死的 #TODO

        MyModelView mv = new MyModelView();
        mv.setView("upload_result");
        if (appService.uploadFile(source, path)){
            mv.addModel("info", "上传成功");
        }else{
            mv.addModel("info", "上传失败");
        }
        return mv;
    }
}
