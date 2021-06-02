<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table border="1">
	<caption>
		<fmt:message key="offer"/>
	</caption>
        <tr>
		<td><label for="idimovel"> ID Imovel:
		</label></td>
		<td><input type="text" id="idimovel" name="idimovel" value="${imovel.id}" readonly style="background-color:gray; color:white" />
                </td>
	</tr>
	<tr>
		<td><label for="valor"> <fmt:message key="value"/>
		</label></td>
		<td><input type="number" id="valor" name="valor" min=0
			required value="" /></td>
	</tr>
	<tr>
		<td><label for="pagamento"> <fmt:message key="payment"/>
		</label></td>
		<td><textarea rows="4" cols="50" id="pagamento" name="pagamento" required><fmt:message key="paymentConditions"/></textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="Enviar" /></td>
	</tr>
</table>
