<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="messages">
    <head>
       <fmt:message key="sendCounter"/> 
    </head>

    <body>
        <%
            String contextPath = request.getContextPath().replace("/", "");
        %>
        <div align="center">
            <h1>
                <fmt:message key="sendCounter"/> 
            </h1>
            <h4>
                <fmt:message key="property"/>: ${imovel.descricao}<br>
                <fmt:message key="value"/>: ${proposta.valor}<br>
                <fmt:message key="paymentConditions"/>: ${proposta.pagamento}<br>
                <fmt:message key="clientName"/>: ${proposta.cliente.nome}<br>
                <fmt:message key="phone"/>: ${proposta.cliente.telefone}<br>
                Email: ${proposta.cliente.user.email}<br>
                CPF: ${proposta.cliente.cpf}                
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
            <form action="enviaContraProposta" method="post">
                <%@include file="campos.jsp"%>
            </form>
            <a href="lista"><fmt:message key="cancel"/></a>
        </div>
    </body>
</fmt:bundle>
</html>