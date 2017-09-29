<%@ page import="java.time.LocalDate" %>
<%@ page import="com.sda.jspexample.random.utils.RandomNumberGenerator" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.stream.Stream" %><%--
  Created by IntelliJ IDEA.
  User: wim
  Date: 29.09.2017
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <h1>Hello world <%= LocalDate.now()%> </h1>
    <a href="helloWorld.jsp">Link do innej strony - de facto tej samej</a>
    <p>Zawartość żądania:</p>
    <p>
        Host: <%= request.getHeader("Host")%> <br/>
        Host z malych liter wolany <%= request.getHeader("host")%> <br/>
        Typ metody: <%= request.getMethod()%><br/>
        Parametry użytkownika: <%= request.getQueryString() %>
        <br/>
        Dzisiejsze numery w totka to: <%= RandomNumberGenerator.drawLottoNumbers()%>
        <br/>
        Dzisiejsze typy dla EuroJackot:
        <%
            Random random = new Random();
            StringBuilder piecZ45 = new StringBuilder();
            StringBuilder dwaZ10 = new StringBuilder();
            for (int i=0;i<5;i++){
                if (i!=0){
                    piecZ45.append(", ");
                    dwaZ10.append(", ");
                }
                if (i<2){
                    dwaZ10.append(random.nextInt(10)+1);
                }
                piecZ45.append(random.nextInt(45)+1);
            }
            out.println(piecZ45.toString());
            out.println("<br/> Oraz dwie z 10: ");
            out.println(dwaZ10.toString());

            String userParams = request.getQueryString();
            if (userParams == null){
                response.sendRedirect("http://www.google.pl");
            }else{
                String[] params = userParams.split("&");
                String idParam = null;
                for (String param : params){
                    String[] keyValuePair = param.split("=");
                    if (keyValuePair.length == 2 && keyValuePair[0].contains("id")){
                        idParam = keyValuePair[1];
                        break;
                    }
                }
                if(idParam == null){
                    response.sendError(404);
                }else{
                    out.println("Wartość parametru id: "+idParam);
                }
            }

        %>


    </p>
</body>
</html>
