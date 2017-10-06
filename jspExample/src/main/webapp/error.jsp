
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
    <title>Biblioteka</title>
</head>
<body>
<%@ include file="header.jsp"%>
<div style="float:left; width:30%;"><%@ include file="menu.jsp" %></div>
<div style="float:right; width:70%;">
    <p>Wystąpił błąd - wróć na stronę główną...</p>
    <form action="/login" method="post">
        <label>Nazwa użytkownika</label><input type="text" name="login"/>
        <label>Hasło</label><input type="text" name="password"/>
        <input type="submit" value="Zaloguj">
    </form>

    <p>Wywołania strony: <%= ++requestCount%></p>

    <p>A to zrobilem w branchu - HAHA!</p>
</div>
<div style="clear:both"></div>

</body>
</html>
