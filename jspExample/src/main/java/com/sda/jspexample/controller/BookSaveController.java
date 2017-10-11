package com.sda.jspexample.controller;

import com.sda.jspexample.book.repository.BookRepository;
import com.sda.jspexample.library.books.Book;
import com.sda.jspexample.model.User;
import com.sda.jspexample.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BookSaveController {

    @Autowired
    private BookRepository repository;

    @RequestMapping(value="/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("bookForm") Book book,
                          HttpServletRequest request,
                          HttpServletResponse response){
        System.out.println(book.getTitle());
        System.out.println(book.getPageCount());
        repository.addBook(book);
        Cookie cookieTitle = new Cookie("bookTitle", Integer.toString(book.getId()));
        cookieTitle.setMaxAge(30);
        Cookie cookiePageCount = new Cookie("bookPageCount",
                Integer.toString(book.getPageCount()));
        response.addCookie(cookieTitle);
        response.addCookie(cookiePageCount);
        return "redirect:/";
    }
    @RequestMapping(value="/rentBookByLoggedUser", method = RequestMethod.POST)
    public String rentBookByLoogedUser(@ModelAttribute("bookId") String bookId,
                                       @ModelAttribute("memberId") String userId){
        User user = UserRepository.getUserById(userId);
        Book book = BookRepository.getBookById(bookId);
        //Dodanie książki do listy wypożyczonych
        return "redirect:/";

    }

    @RequestMapping(value="/rentBook", method = RequestMethod.POST)
    public String rentBook(@ModelAttribute("userForm") User user,
                           @ModelAttribute("bookId") String bookId,
                           HttpServletResponse response

    ){
        boolean valid = doValidation(user);
        if (!valid){
            return "redirect:/books/reservation?bookId="+bookId+"&validationError=true";
        }
        //Dodawanie nowego użytkownika i wypożyczenie książki
        UserRepository.addUser(user);
        Cookie cookie = new Cookie("userId", Integer.toString(user.getId()));
        response.addCookie(cookie);
        return "redirect:/books";
    }


    private boolean doValidation(User user){
        return true;
        }

}
