<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <body>
        <fmt:bundle basename="messages">
            <form action='/AloMundoMVN/conversao.jsp' method='post'>
                <fmt:message key="min"/><input type='number' name='min' />
                <fmt:message key="max"/><input type='number' name='max' />
                <fmt:message key="inc"/><input type='number' name='step' />
                <input type='submit' />
            </form>
        </fmt:bundle>
    </body>
</html>
