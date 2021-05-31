<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		Cadastro de Imovel
	</caption>
        <tr>
		<td><label for="descricao"> Descrição
		</label></td>
		<td><input type="text" id="descricao" name="descricao"
			required /></td>
	</tr>
        <tr>
		<td><label for="valor"> Valor
		</label></td>
		<td><input type="number" id="valor" name="valor" min=0
			required value="" /></td>
	</tr>
        <tr>
		<td><label for="cep"> Cep
		</label></td>
		<td><input type="text" maxlength="9" minlength="9" id="cep" name="cep" OnKeyPress="formatar('#####-###', this)"
			required /></td>
	</tr>
        <tr>
		<td><label for="logradouro"> Logradouro
		</label></td>
		<td><input type="text" id="logradouro" name="logradouro"
			required /></td>
	</tr>
        <tr>
		<td><label for="numero"> Número
		</label></td>
		<td><input type="number" id="numero" name="numero"
			required /></td>
	</tr>
        <tr>
		<td><label for="Bairro"> Bairro
		</label></td>
		<td><input type="text" id="bairro" name="bairro"
			required /></td>
	</tr>
        <tr>
		<td><label for="cidade"> Cidade
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
			value="Enviar" /></td>
	</tr>
</table>
