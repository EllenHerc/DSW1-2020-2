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
        <script>
            $(function () {                
                $("#cidade").autocomplete({
                    source: "buscaPorNome",
                    minLength: 1,
                    select: function (event, ui) {
                        var url_string = window.location.href;
                        var url = url_string.split('?', 1);
                        $(window.document.location).attr('href', url[0]+"?cidade="+ui.item.value);
                    }
                    
                });
            });
        </script>
        <title>Imoveis</title>
    </head>
    <body>
        <%
            String contextPath = request.getContextPath();
        %>
        <div align="center">
            
            <div class="ui-widget">
                <label for="cidade">Buscar por Cidade</label>
                <input id="cidade" name="cidade" placeholder="Digite o nome da cidade">
            </div>
        </div>
        <br/>
	<div align="center">
            
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
                        <a href="<%=contextPath%>/listaImoveis?id=${imovel.id}" class="btn btn-secondary">Visualizar Fotos</a>
                        <c:choose>
                            <c:when test="${requestScope.cliente != null}">
                                <a href="<%=contextPath%>/cliente/proposta/cadastroProposta?idimovel=${imovel.id}" class="btn btn-primary">Realizar Proposta</a>
                            </c:when>                                
                        </c:choose>   
                  </div>
                  <div class="card-footer">
                    <small class="text-muted">${imovel.imobiliaria.nome}</small>
                  </div>
                </div>
                </c:forEach>
            </div>
		
	</div>
        <br/>
        <c:choose>
            <c:when test="${requestScope.cliente != null}">
		<a href="voltar">Voltar</a><br/>
            </c:when>
	    <c:otherwise>
		<a href="<%=contextPath%>/index.jsp">Voltar</a>
            </c:otherwise>
	</c:choose>
        
</body>
</html>