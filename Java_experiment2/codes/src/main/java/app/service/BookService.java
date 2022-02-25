package app.service;

import app.model.Book;
import mymvc.annotation.MyService;

import java.util.ArrayList;
import java.util.List;

@MyService
public class BookService {

    private List<Book> allBookList = new ArrayList<Book>(){{
        add(new Book(1, "JavaEE", "张三", "电信出版社"));
        add(new Book(2, "ComputerNetwork", "李四", "重庆大学出版社"));
        add(new Book(3, "OperatingSystem", "王五", "清华大学出版社"));
    }};

    public List<Book> getAllBooks(){
        return allBookList;
    }
    public Book getBookById(int id){
        for (Book book : allBookList){
            if (book.getId() == id){
                return book;
            }
        }
        return null;
    }
    public void addBook(int id, String title, String author, String press){
        allBookList.add(new Book(id, title, author, press));
    }
}
