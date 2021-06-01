<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="messages">
    <head>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script language="javascript">
        $(document).ready(function() {
            var max_fields = 10;
            var wrapper = $(".inputs");
            var add_button = $("#adicionarcampo");

            var x = 1;
            $(add_button).click(function(e) {
              e.preventDefault();

              if (x < max_fields) { 
                $(wrapper).append('<div><input type="text" id="foto' + (x) + '" name="foto' + (x) + '" class="form-control" placeholder="Url" /><a href="#" class="remove_field"><fmt:message key="remove"/></a></div>');
                x++;
              }

            });


            $(wrapper).on("click", ".remove_field", function(e) {
              e.preventDefault();
              $(this).parent('div').remove();
              x--;
            });
        });
        </script>
        <title><fmt:message key="properties"/> <fmt:message key="register"/></title>
    </head>

    <body>
        <%
            String contextPath = request.getContextPath();
        %>
        <div align="center">
            <h1>
                <fmt:message key="pictures"/> 
            </h1>
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
        <div class="container-fluid">
            <!-- Informando Quantidade -->
            <form target="_self" action="<%=contextPath%>/imobiliaria/imovel/insercaoFoto" method="post" id="formQuantidade"> 
                <label for="idimovel"> ID imovel:</label>
                <input class="form-control" type="text" id="idimovel" name="idimovel" value="${idimovel}" readonly style="background-color:gray; color:white" />
                <br/><label for="Url">Informe a url da foto</label>
              <div class="inputs">
                <input type="text" name="foto0" id="foto0"  class="form-control" placeholder="Url">
                <br/>
              </div>
              <a href="javascript:void(0)" id="adicionarcampo" class="btn btn-sm btn-info">Adicionar Foto</a>
              <br/><br/><div align="center"><input class="btn btn-sm btn-success" type="submit"
                                              value="<fmt:message key="send"/>" /></div>
            </form>
            </div>
        <div align="center">

            <a href="lista"><fmt:message key="back"/></a>
        </div>
    </body>
</fmt:bundle>
</html>