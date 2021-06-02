<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>


<% String contextPath = request.getContextPath().replace("/", ""); %>
<body>
<fmt:bundle basename="messages">
    <div class="container">
        <div class="row mt-5">
            <div class="col-12">
                <h1><fmt:message key="update"/></h1>
                <form method="post" action="/<%= contextPath%>/edicao">
                    <div class="form-group">
                      <label><fmt:message key="name"/></label>
                      <input type="text" class="form-control" name="nome" value="<c:out value='${usuario.nome}' />">
                    </div>
                    <div class="form-group">
                      <label><fmt:message key="phone"/></label>
                      <input type="text" class="form-control" name="telefone" value="<c:out value='${usuario.telefone}' />">
                    </div>
                    <div class="form-group">
                      <label><fmt:message key="gender"/></label>
                      <input type="text" class="form-control" name="sexo" value="<c:out value='${usuario.sexo}' />">
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="update"/></button>
                </form>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
</fmt:bundle>
</body>
</html>