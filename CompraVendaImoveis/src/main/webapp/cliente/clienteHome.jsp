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
        <%
            String contextPath = request.getContextPath();
        %>
        <div align="center">

            <h1>Bem vindo ${sessionScope.cliente.nome}</h1>
            <h4>
                <a href="<%=contextPath%>/cliente/redirect/imoveis">
                    <fmt:message key="searchProperty"/>
                </a>
            </h4>
            <br/>
            <div align="center">
                <table border="1">
                    <caption><fmt:message key="myOffers"/></caption>
                    <tr>
                        <th><fmt:message key="emissionDate"/></th>
                        <th><fmt:message key="paymentConditions"/></th>                        
                        <th><fmt:message key="value"/></th>
                        <th>Status</th>
                        <th><fmt:message key="property"/></th>
                    </tr>
                    <c:forEach var="proposta" items="${requestScope.listaPropostas}">
                        <tr>
                            <td>${proposta.dataemissao}</td>
                            <td>${proposta.pagamento}</td>
                            <td>${proposta.valor}</td>
                            <td>${proposta.status}</td>
                            <td><a href="<%=contextPath%>/proposta/imovel?id=${proposta.imovel.id}" target="_blank">${proposta.imovel.descricao}</a></td>
                            
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <br/>
            <a href="<%=contextPath%>/index.jsp"><fmt:message key="logout"/></a>
            <a href="<%=contextPath%>/edicao"><fmt:message key="changeMe"/></a>
            <a href="<%=contextPath%>/remocao"><fmt:message key="removeMe"/></a>
    </fmt:bundle>
    </body>
</html>