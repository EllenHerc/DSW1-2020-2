<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">   
    </head>
    <body>
    <fmt:bundle basename="messages">
        <% String contextPath = request.getContextPath().replace("/", ""); %>        
        <div align="center">

            <h1><fmt:message key="welcome"/> ADM</h1>
            <br/>
            <div align="center">
                <table border="1">
                    <caption><fmt:message key="users"/></caption>
                    <tr>
                        <th><fmt:message key="name"/></th>
                        <th>Email</th>                        
                        <th><fmt:message key="phone"/></th>
                        <th><fmt:message key="phone"/></th>
                        <th><fmt:message key="gender"/></th>
                    </tr>
                    <c:forEach var="cliente" items="${requestScope.clientes}">
                        <tr>
                            <td>${cliente.nome}</td>
                            <td>${cliente.user.email}</td>
                            <td>${cliente.telefone}</td>
                            <td>${cliente.sexo}</td>
                            <td><form method="post" action="/<%= contextPath%>/admin?cpf=${cliente.cpf}"><button type="submit" class="btn btn-primary"><fmt:message key="remove"/></button></form></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <br/>
            <a href="<%=contextPath%>/index.jsp"><fmt:message key="logout"/></a>
    </fmt:bundle>
    </body>
</html>