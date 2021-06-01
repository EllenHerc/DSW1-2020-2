<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

    <body>
    <fmt:bundle basename="messages">
        <%
            String contextPath = request.getContextPath().replace("/", "");
        %>
        <div align="center">
            <h1>
                <fmt:message key="offer"/>
            </h1>
            <h4>
                <fmt:message key="property"/>: ${imovel.descricao}
            </h4>
        </div>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <div align="center">
            <form action="insercao" method="post">
                <%@include file="campos.jsp"%>
            </form>
            <a href="lista"><fmt:message key="back"/></a>
        </div>
    </fmt:bundle>
    </body>

</html>