<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sda.jspexample.library.books.Book" %>
<%@ page import="com.sda.jspexample.random.utils.BookGenerator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--  Created by IntelliJ IDEA.
  User: wim
  Date: 02.10.2017
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- Komentarz JSP - NIE WIDOCZNY W WYGENEROWANEJ STRONIE --%>
<!-- Komentarz HTML - Widoczny na stronie -->
<%! int requestCount = 0; %>
<div><p>Tytuł strony</p></div>
<div style="float:left; width:30%;">
    <%@ include file="menu.jsp" %>
</div>
<div style="float:right; width:70%;">
    <p>Content strony</p>


    <%--
        <%
            List<Book> list = new ArrayList();
            list.add(BookGenerator.createBook("Java Podstawy", "Cay", "Horstmann"));
            list.add(BookGenerator.createBook("Inferno", "Dan", "Brown"));
            list.add(BookGenerator.createBook("Pan Tadeusz", "Adam", "Mickiewicz"));
            out.println("<table>");
            for(Book book : list){
                out.println("<tr>");
                    out.println(String.format("<td>%s</td><td>%s</td><td>%d</td>",
                            book.getTitle(), book.getAuthor().toString(),
                            book.getPageCount()));
                out.println("</tr>");
            }
        out.println("</table>");
        %>
    --%>
    <table>
        <tr>
            <th>Tytuł</th>
            <th>Autor</th>
            <th>Liczba stron</th>
            <th>Opcje</th>
        </tr>
        <c:forEach items="${list}" var="book">

            <tr>
                <td>${book.getTitle()}</td>
                <td>${book.getAuthor().toString()}</td>
                <td>${book.getPageCount()}</td>
                <td><a href="/books/reservation?bookId=${book.getId()}">Zarezerwuj</a></td>
            </tr>

        </c:forEach>
    </table>


</div>
<div style="clear:both"></div>

</body>
</html>
