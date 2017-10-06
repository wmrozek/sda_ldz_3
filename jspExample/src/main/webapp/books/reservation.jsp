<%@ page import="com.sda.jspexample.model.User" %>
<%@ page import="com.sda.jspexample.users.repository.UserRepository" %>
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

    <form action="/rentBookByLoggedUser" method="post">
        <input type="hidden" name="bookId" value="${book.getId()}">
    <%
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("userId")){
                String userId = cookie.getValue();
                User user = UserRepository.getUserById(userId);
                if (user != null){
                    out.println(String.format("Czy chcesz wypożyczyć " +
                            "książkę jako użytkownik " +
                            "<br/> %s %s <br/> %s", user.getName(),
                            user.getSurname(), user.getPesel()));
                    out.println("<input type=\"hidden\" name=\"memberId\" value=\""+user.getId()+"\"/>");
                    out.println("<input type=\"submit\">Wypożycz</input>");
                }
            }
        }
    %>
    </form>

    <p>Podaj dane osobowe:</p>
    <form name="userForm" action="/rentBook" method="post">
        <input type="hidden" name="bookId" value="${book.getId()}"/>
        <input type="text" name="name"/>
        <input type="text" name="surname"/>
        <input type="text" name="pesel"/>
        <input type="submit" />

    </form>



</div>
<div style="clear:both"></div>

</body>
</html>
