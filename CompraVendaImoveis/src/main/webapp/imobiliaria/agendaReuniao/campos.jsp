<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
        <caption>
		Agendamento de reunião
	</caption>
        <tr>
		<td><label for="idimovel"> ID proposta:
		</label></td>
		<td><input type="text" id="idproposta" name="idproposta" value="${proposta.id}" readonly style="background-color:gray; color:white" />
                </td>
	</tr>
	<tr>
		<td><label for="data"> Data
		</label></td>
		<td><input type="date" id="data" name="data"
			required value="" /></td>
	</tr>
	<tr>
		<td><label for="hora"> Hora
		</label></td>
		<td><input type="time" id="hora" name="hora"
			required value="" /></td>
	</tr>
        <tr>
		<td><label for="link"> Link da videoconferência
		</label></td>
		<td><input type="text" id="link" name="link"
			required value="" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="Enviar" /></td>
	</tr>
</table>
