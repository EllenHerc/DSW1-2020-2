<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">   
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Página da Imobiliária</title>
    </head>
    <body>
        <%
            String contextPath = request.getContextPath();
        %>
        <div align="center">

            <h1>Bem vindo ${requestScope.imobiliaria.nome}</h1>
            <h4>
                <a href="<%=contextPath%>/imobiliaria/proposta/">
                    Analisar Propostas
                </a> - 
                 <a href="<%=contextPath%>/imobiliaria/imovel/cadastrarImovel/">
                     Cadastrar Imovel
                </a>
            </h4>
            <br/>
            <div class="card-columns">
                <c:forEach var="imovel" items="${requestScope.listaImoveis}">
                <div class="card">
                  <img class="card-img-top" src="${imovel.fotos[0].url}" alt="Card image cap">
                  <div class="card-body">
                    <h5 class="card-title">${imovel.descricao}</h5>
                        <table border="0" class="card-text">
                            <tr>
                                <td>Valor: </td><td>${imovel.valor}</td>
                            </tr>
                            <tr>
                                <td>Endereço: </td><td><br/> ${imovel.logradouro}, ${imovel.numero} - ${imovel.bairro}</td>
                            </tr>
                            <tr>
                                <td></td><td>${imovel.cidade.nome} - ${imovel.cidade.uf}, ${imovel.cep}</td>
                            </tr>
                        </table>
                  </div>
                  <div class="card-footer">
                    <small class="text-muted">${imovel.imobiliaria.nome}</small>
                  </div>
                </div>
                </c:forEach>
            </div>
            <br/>
        </div>
            <a href="<%=contextPath%>/index.jsp">Sair</a>
    </body>
</html>