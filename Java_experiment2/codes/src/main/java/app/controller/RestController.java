package app.controller;

import java.util.List;
import app.model.Book;
import app.model.RestModel;
import app.service.BookService;
import mymvc.annotation.*;


@MyController
@MyRequestMapping("/rest")
public class RestController {

    @MyAutowired
    private BookService bookService;

    @MyRequestMapping(value = "/book", method = "GET")
    @ResponseBody
    public RestModel getBookMsg(@MyRequestParam("id") String sid){
        int id = Integer.parseInt(sid);
        Book book = bookService.getBookById(id);
        return new RestModel(200, "ok", book);
    }

    @MyRequestMapping(value = "/book/all", method = "GET")
    @ResponseBody
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
}
