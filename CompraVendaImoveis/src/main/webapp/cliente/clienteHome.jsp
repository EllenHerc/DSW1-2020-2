<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">   
        <title>Pagina do Cliente</title>
    </head>
    <body>
        <%
            String contextPath = request.getContextPath();
        %>
        <div align="center">

            <h1>Bem vindo ${requestScope.cliente.nome}</h1>
            <h4>
                <a href="<%=contextPath%>/imoveis">
                    Buscar por Imovel
                </a>
            </h4>
            <br/>
            <div align="center">
                <table border="1">
                    <caption>Propostas Realizadas</caption>
                    <tr>
                        <th>Data Emissão</th>
                        <th>Condição de Pagamento</th>                        
                        <th>Valor</th>
                        <th>Status</th>
                        <th>Imovel</th>
                        <th>Ações</th>
                    </tr>
                    <c:forEach var="proposta" items="${requestScope.listaPropostas}">
                        <tr>
                            <td>${proposta.dataemissao}</td>
                            <td>${proposta.pagamento}</td>
                            <td>${proposta.valor}</td>
                            <td>${proposta.status}</td>
                            <td><a href="<%=contextPath%>/cliente/proposta/imovel?id=${proposta.imovel.id}" target="_blank">${proposta.imovel.descricao}</a></td>
                            <td><a href="<%=contextPath%>/cliente/proposta/edicao?id=${proposta.id}">
                                Editar
                            </a> &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a href="<%=contextPath%>/cliente/proposta/remocao?id=${proposta.id}"
                               onclick="return confirm('Tem certeza de que deseja excluir esta proposta?');">
                                Remover
                            </a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <br/>
            <a href="<%=contextPath%>/index.jsp">Voltar</a>
    </body>
</html>