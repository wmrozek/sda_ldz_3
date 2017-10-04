package com.sda.jspexample.controller;

import com.sda.jspexample.book.repository.BookRepository;
import com.sda.jspexample.library.books.Book;
import com.sda.jspexample.menu.MenuElement;
import com.sda.jspexample.model.ModelAndViewWithMenu;
import com.sda.jspexample.random.utils.BookGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookListController {

    @RequestMapping(value="/")
    public ModelAndView startPage(){
            ModelAndView model = new ModelAndViewWithMenu();
        model.setViewName("index.jsp");
        return model;
    }

    @RequestMapping(value="/books")
    public ModelAndView getBooks() {
        ModelAndView model = new ModelAndViewWithMenu();
        List<Book> list = BookRepository.getBooks();
        model.addObject("list", list);
        model.setViewName("bookList.jsp");
        return model;
    }
    @RequestMapping(value="/books/add")
    public ModelAndView addBookPage(){
        ModelAndView model = new ModelAndViewWithMenu();
        model.setViewName("addBook.jsp");
        return model;
    }

    @RequestMapping(value="/books/reservation")
    public ModelAndView bookReservationPage(
            @RequestParam(name = "bookId", required = true) String id,
            @RequestParam(name="validationError", required = false) Boolean validationFailed){
        ModelAndView model = new ModelAndViewWithMenu();
        if (validationFailed != null && validationFailed){
            model.addObject("errorMessage", true);
        }
        model.addObject("book", BookRepository.getBookById(id));
        model.setViewName("reservation.jsp");
        return model;
    }
}
