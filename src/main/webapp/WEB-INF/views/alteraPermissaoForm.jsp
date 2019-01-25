<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url value="/" var="contextPath" />

<tags:pageTemplate titulo="Altera Permissão">
	
	<div class="container">
	<h1>Cadastro de permissoes para ${usuario.nome}</h1>
	
	<a>Permissões:</a> 
	<form:form action="${s:mvcUrl('UC#setPermissao').arg(0, usuario.nome).build()}" method="post" commandName="usuario" enctype="form-data">
	
	<form:checkboxes items="${roles}" path="roles"/> 
							
	<button type="submit" class="btn btn-primary">Atualizar</button>
	</form:form>
	</div>
</tags:pageTemplate>