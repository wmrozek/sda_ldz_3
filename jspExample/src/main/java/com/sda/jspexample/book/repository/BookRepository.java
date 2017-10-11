package com.sda.jspexample.book.repository;

import com.sda.jspexample.MySqlDbConnection;
import com.sda.jspexample.library.books.Author;
import com.sda.jspexample.library.books.Book;
import com.sda.jspexample.library.books.BookGenre;
import com.sda.jspexample.random.utils.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookRepository {

    @Autowired
    private MySqlDbConnection mySqlDbConnection;

    public void addBook(Book book){
        Connection connection = mySqlDbConnection.getConnection();
        String sqlCheckAuthor = "SELECT id FROM authors WHERE name = ? AND surname = ?";



        try {
            //Sprawdzamy czy autor jest już w DB
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheckAuthor);
            checkStmt.setString(1, book.getAuthor().getName());
            checkStmt.setString(2, book.getAuthor().getSurname());
            ResultSet authorRs = checkStmt.executeQuery();
            int authorId = 0;
            while(authorRs.next()){
                authorId = authorRs.getInt("id");
            }
            if (authorId == 0) {
                //Wstawiamy autora do bazy danych
                String sqlInsert = "INSERT INTO authors(name, surname) VALUES (?,?)";
                PreparedStatement stmt = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, book.getAuthor().getName());
                stmt.setString(2, book.getAuthor().getSurname());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();

                while (rs.next()) {
                    authorId = rs.getInt(1);
                }
            }

            String bookSql = "INSERT INTO books(title, isbn, genre, id_author) VALUES (?,?,?,?)";
            PreparedStatement bookStmt = connection.prepareStatement(bookSql);
            bookStmt.setString(1, book.getTitle());
            bookStmt.setString(2, book.getIsbn());
            bookStmt.setString(3, book.getGenre().toString());
            bookStmt.setInt(4, authorId);
            bookStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooks(){
        Connection connection = mySqlDbConnection.getConnection();
        String SQL = "SELECT b.title AS title, b.genre AS genre, b.isbn AS isbn, " +
                "a.name AS author_name, a.surname AS author_surname " +
                "FROM books b LEFT JOIN authors a ON b.id_author = a.id;";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while(rs.next()){
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(new Author());
                book.getAuthor().setName(rs.getString("author_name"));
                book.getAuthor().setSurname(rs.getString("author_surname"));
                book.setGenre(BookGenre.valueOf(rs.getString("genre")));
                books.add(book);
            }
            rs.close();
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }





    private static List<Book> list = new ArrayList<>();

//    public static void addBook(Book book){
//        book.setId(RandomNumberGenerator.getNextSequence());
//        list.add(book);
//    }

//    public static List<Book> getBooks(){
//        return list;
//    }

    public static Book getBookById(String id){
        if (id == null){
            return null;
        }
        Book result = list.stream()
                .filter(book -> Integer.toString(book.getId()).equals(id)).findAny().orElse(null);
        return result;

    }
}
