<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compra/Venda Imoveis</title>
    </head>
    <body>
    <fmt:bundle basename="messages">
        <hr>
        <h1><b><fmt:message key="title"/></b></h1>
        <hr>
        <a href="imoveis"><fmt:message key="properties"/></a><br/>
        <c:choose>
            <c:when test="${cliente != null}">
		<a href="cliente"><fmt:message key="myOffers"/></a><br/>
            </c:when>
	    <c:otherwise>
		<a href="login">Login</a><br/>
                <a href="cadastro"><fmt:message key="register"/></a><br/>
            </c:otherwise>
	</c:choose>
    </fmt:bundle>
    </body>
</html>