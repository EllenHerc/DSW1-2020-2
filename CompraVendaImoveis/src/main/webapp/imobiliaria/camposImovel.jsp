<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table border="1">
	<caption>
		<fmt:message key="properties"/> <fmt:message key="register"/>
	</caption>
        <tr>
		<td><label for="descricao"> <fmt:message key="description"/> 
		</label></td>
		<td><input type="text" id="descricao" name="descricao"
			required /></td>
	</tr>
        <tr>
		<td><label for="valor"> <fmt:message key="value"/>
		</label></td>
		<td><input type="number" id="valor" name="valor" min=0
			required value="" /></td>
	</tr>
        <tr>
		<td><label for="cep"> <fmt:message key="postalCode"/> 
		</label></td>
		<td><input type="text" maxlength="9" minlength="9" id="cep" name="cep" OnKeyPress="formatar('#####-###', this)"
			required /></td>
	</tr>
        <tr>
		<td><label for="logradouro"> <fmt:message key="PublicPlace"/>  
		</label></td>
		<td><input type="text" id="logradouro" name="logradouro"
			required /></td>
	</tr>
        <tr>
		<td><label for="numero"> <fmt:message key="number"/> 
		</label></td>
		<td><input type="number" id="numero" name="numero"
			required /></td>
	</tr>
        <tr>
		<td><label for="Bairro"> <fmt:message key="district"/> 
		</label></td>
		<td><input type="text" id="bairro" name="bairro"
			required /></td>
	</tr>
        <tr>
		<td><label for="cidade"> <fmt:message key="city"/> 
		</label></td>
		<td><select name="cidade" id="cidade">
				<c:forEach items="${cidades}" var="cidade">
					<option value="${cidade.key}"
						${hotel.cidade.nome==cidade.value ? 'selected' : '' }>
						${cidade.value}</option>
				</c:forEach>
		</select></td>
	</tr>	
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="send"/>" /></td>
	</tr>
</table>
