<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="messages">
    <head>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script>
        function mensagem(id) {
            var name=confirm("Proposta não aceita! \r\n\r\nEnviar Contra-proposta ao cliente?");
            var url_string = window.location.href;
            var url = url_string.split("lista");
            url = url[0].split("#");           
            
            if (name)
            {                
                window.location.href = url[0]+"enviarContraProposta?idproposta="+id;
            }
            else
            {
                window.location.href = url[0]+"naoAceitar?idproposta="+id;
            }
        }
        </script>
    </head>
    <body>
        <%
            String contextPath = request.getContextPath();
        %>
        <div align="center">

            <h1>Propostas</h1>            
            <br/>
        </div>
        <div align="center">
                <table class="table table-striped">
                    <caption><fmt:message key="offer2"/></caption>
                    <tr>
                        <th><fmt:message key="date"/></th>
                        <th><fmt:message key="paymentConditions"/></th>                        
                        <th><fmt:message key="value"/></th>                        
                        <th><fmt:message key="client"/></th>    
                        <th>CPF</th>
                        <th><fmt:message key="property"/></th>
                        <th>Status</th>
                        <th colspan="2"><fmt:message key="actions"/></th>
                    </tr>
                    <c:forEach var="proposta" items="${requestScope.listaPropostas}">
                        <tr>
                            <td>${proposta.dataemissao}</td>
                            <td>${proposta.pagamento}</td>
                            <td>${proposta.valor}</td>
                            <td>${proposta.cliente.nome}</td>
                            <td>${proposta.cliente.cpf}</td>
                            <td><a href="<%=contextPath%>/proposta/imovel?id=${proposta.imovel.id}" target="_blank">${proposta.imovel.descricao}</a></td>
                            <td>${proposta.status}</td>
                            <c:choose>
                                <c:when test="${proposta.status == 'ABERTO'}">
                                    <td><a href="<%=contextPath%>/imobiliaria/proposta/aceitar?idproposta=${proposta.id}" class="btn btn-success">Aceitar</a></td>
                                    <td><a href="#" onclick="mensagem(${proposta.id})" class="btn btn-danger">Recusar</a></td>
                                </c:when>                                
                            </c:choose>   
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <br/>
            <a href="<%=contextPath%>/imobiliaria/">Voltar</a>
    </body>
</fmt:bundle>
</html>