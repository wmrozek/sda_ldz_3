package com.sda.jspexample.random.utils;

import com.sda.jspexample.library.books.Author;
import com.sda.jspexample.library.books.Book;
import com.sda.jspexample.library.books.BookGenre;

import java.util.Random;

public class BookGenerator {
    private static final String[] COUNTRY_CODES = new String[]{"PL", "DE", "US", "FR", "GB"};


    public static Book createBook(String title, String authorName, String authorSurname){
        Random random = new Random();
        Author author = new Author(authorName, authorSurname,
                COUNTRY_CODES[random.nextInt(COUNTRY_CODES.length)]);

        BookGenre[] values = BookGenre.values();
        Book book = new Book(title, generateIsbnNumber(random),
                random.nextInt(1500), author,
                values[random.nextInt(values.length)]);
        return book;
    }

    private static String generateIsbnNumber(Random random){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<10;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
