<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		Cadastrar Proposta
	</caption>
	<tr>
		<td><label for="valor"> Valor
		</label></td>
		<td><input type="number" id="valor" name="valor" min=0
			required value="" /></td>
	</tr>
	<tr>
		<td><label for="pagamento"> Pagamento
		</label></td>
		<td><input type="text" id="pagamento" name="pagamento" required/></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="Enviar" /></td>
	</tr>
</table>
