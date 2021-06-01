<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="messages">
    <head>
        <script>
            function formatar(mascara, documento){
              var i = documento.value.length;
              var saida = mascara.substring(0,1);
              var texto = mascara.substring(i)

              if (texto.substring(0,1) != saida){
                        documento.value += texto.substring(0,1);
              }

            }
        </script>
        <title>Cadastro de Imovel</title>
    </head>

    <body>
        <%
            String contextPath = request.getContextPath();
        %>
        <div align="center">
            <h1>
                <fmt:message key="properties"/> <fmt:message key="register"/>
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
        <div align="center">
            <form action="<%=contextPath%>/imobiliaria/imovel/insercaoImovel/" method="post">
                <%@include file="camposImovel.jsp"%>
            </form>
            <a href="lista"><fmt:message key="back"/></a>
        </div>
    </body>
</fmt:bundle>
</html>