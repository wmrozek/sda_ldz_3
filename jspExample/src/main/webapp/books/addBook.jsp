
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
    <title>Dodawanie nowej pozycji do biblioteki</title>
</head>
<body>
<%@ include file="../header.jsp"%>
<div style="float:left; width:30%;"><%@ include file="../menu.jsp" %></div>
<div style="float:right; width:70%;">
    <p>Strona główna biblioteki - wyberz opcje menu z lewej strony.</p>
    <p>
    <%
        Cookie[] cookies = request.getCookies();
        if (cookies.length == 0){
            out.println("Brak ostatnio dodanych książek");
        }else {
            out.println("Książki ostatnio dodane: ");
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("JSESSIONID")){
                    continue;
                }
                out.println(cookie.getName()+" - "+cookie.getValue());
            }
        }
    %>
    </p>
    <form name="bookForm" action="/addBook" method="post">
        <label>Nazwa ksiązki</label><input type="text" name="title" />
        <label>Liczba stron</label><input type="text" name="pageCount" />
        <input type="submit" value="Zapisz" />

    </form>

</div>
<div style="clear:both"></div>

</body>
</html>
