
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
    <c:if test="${book eq null}">
      <%--  <c:redirect url="../error.jsp"/>
      zmienia się url dodatkowo--%>
        <jsp:forward page="../error.jsp"/>
    </c:if>

    <c:if test="${errorMessage eq true}">
        <h1>Nie udało się wypożyczyć książki - podałeś złe dane. Spróbuj jeszcze raz</h1>
    </c:if>


    <p>Wypożyczasz książkę - ${book.getTitle()}</p>

    <p>Podaj dane osobowe:</p>
    <form name="userForm" action="/rentBook" method="post">
        <input type="hidden" name="bookId" value="${book.getId()}"/>
        <input type="submit" />

    </form>



</div>
<div style="clear:both"></div>

</body>
</html>
