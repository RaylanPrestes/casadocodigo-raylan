<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tags:pageTemplate titulo="Lista de Pedidos">

	<div class="container">
		<h1>Lista de Pedidos</h1>

		<table class="table table-bordered">
			<tr>
				<th>ID</th>
				<th>Valor</th>
				<th>Data do Pedido</th>
				<th>Titulos</th>
			</tr>
			<c:forEach items="${pedidos}" var="pedido">
				<tr>
					<td>${pedido.id}</td>
					<td>${pedido.valor}</td>
					<td><fmt:formatDate value="${pedido.data.time }" pattern="dd/MM/yyyy" /></td>
					<td><c:forEach items="${pedido.produtos}" var="produto">
					${produto.titulo},
					</c:forEach></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</tags:pageTemplate>