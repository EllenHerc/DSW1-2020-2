<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compra/Venda Imoveis</title>
    </head>
    <body>
        <hr>
        <h1><b>COMPRA E VENDA DE IMOVEIS</b></h1>
        <hr>
        <a href="imoveis">Imoveis</a><br/>
        <c:choose>
            <c:when test="${cliente != null}">
		<a href="cliente">Minhas Propostas</a><br/>
            </c:when>
	    <c:otherwise>
		<a href="login">Login</a><br/>
            </c:otherwise>
	</c:choose>
    </body>
</html>