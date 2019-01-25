<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tags:pageTemplate titulo="Lista de Usuários">


	<div class="container">
	
		<a href="${s:mvcUrl('UC#form').build()}">Novo Usuário</a>
	
		<h1>Lista de Usuários</h1>
		
		<p> ${sucesso}</p>

		<table class="table table-bordered">
			<tr>

				<th>Nome</th>
				<th>Email</th>
				<th>Roles</th>
				<th>Editar</th>

			</tr>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td>${usuario.nome}</td>
					<td>${usuario.email}</td>
					<td><c:forEach items="${usuario.roles}" var="role">
					${role.nome}
					</c:forEach></td>
					<td><a href="${s:mvcUrl('UC#alteraPermissaoForm').arg(0, usuario.nome).build()}"><button type="button" class="btn btn-primary">+</button></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>


</tags:pageTemplate>