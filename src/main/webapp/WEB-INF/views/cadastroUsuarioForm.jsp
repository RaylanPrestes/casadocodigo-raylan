<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<tags:pageTemplate titulo="Cadastro de Usuário">
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
	
	<div class="container">
		<h1>Cadastro de Usuário</h1>
		
		<form:form action="${s:mvcUrl('UC#cadastra').build() }" method="post" commandName="usuario" enctype="form-data">
			<div class="form-group">
				<label>Nome</label>
				<form:input path="nome" cssClass="form-control" />
				<form:errors path="nome" />
			</div>
			<div class="form-group">
		        <label>Email</label>
				<form:input path="email" cssClass="form-control" />
		        <form:errors path="email" />
			</div>
			<div class="form-group">
				<label>Senha</label>
				<form:password path="senha" cssClass="form-control" />
		        <form:errors path="senha" />
			</div>
			<div class="form-group">
				<label>Confirme sua Senha</label>
				<form:password path="senhaConfirma" cssClass="form-control"/>
		        <form:errors path="senhaConfirma" />
			</div>
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	</div> 

</tags:pageTemplate>