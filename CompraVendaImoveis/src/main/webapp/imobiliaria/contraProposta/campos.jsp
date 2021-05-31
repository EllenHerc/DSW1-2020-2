<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		Fechamento de Proposta
	</caption>
        <tr>
		<td><label for="idimovel"> ID proposta:
		</label></td>
		<td><input type="text" id="idproposta" name="idproposta" value="${proposta.id}" readonly style="background-color:gray; color:white" />
                </td>
	</tr>
	<tr>
		<td><label for="valor"> Valor da Contra-Proposta
		</label></td>
		<td><input type="number" id="valor" name="valor" min=0
			required value="" /></td>
	</tr>
	<tr>
		<td><label for="pagamento"> Condições de Pagamento da Contra-Proposta
		</label></td>
		<td><textarea rows="4" cols="50" id="pagamento" name="pagamento" required>Descreva as condições de pagamento...</textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="Enviar" /></td>
	</tr>
</table>
