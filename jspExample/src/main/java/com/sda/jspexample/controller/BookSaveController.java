package com.sda.jspexample.controller;

import com.sda.jspexample.book.repository.BookRepository;
import com.sda.jspexample.library.books.Book;
import com.sda.jspexample.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookSaveController {

    @RequestMapping(value="/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("bookForm") Book book){
        System.out.println(book.getTitle());
        System.out.println(book.getPageCount());
        BookRepository.addBook(book);
        return "redirect:/";
    }

    @RequestMapping(value="/rentBook", method = RequestMethod.POST)
    public String rentBook(@ModelAttribute("userForm") User user
                           ){
        boolean valid = doValidation(user);
        if (!valid){
            return "redirect:/books/reservation?bookId="+bookId+"&validationError=true";
        }
        //Dodawanie nowego użytkownika i wypożyczenie książki
        return "redirect:/books";
    }


    private boolean doValidation(User user){
        return true;
        }

}
