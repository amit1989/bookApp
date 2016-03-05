
<%@ page import="bookapp.Tutor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tutor.label', default: 'Tutor')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tutor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tutor" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tutor">
			
				<g:if test="${tutorInstance?.averageRating}">
				<li class="fieldcontain">
					<span id="averageRating-label" class="property-label"><g:message code="tutor.averageRating.label" default="Average Rating" /></span>
					
						<span class="property-value" aria-labelledby="averageRating-label"><g:fieldValue bean="${tutorInstance}" field="averageRating"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tutorInstance?.cources}">
				<li class="fieldcontain">
					<span id="cources-label" class="property-label"><g:message code="tutor.cources.label" default="Cources" /></span>
					
						<span class="property-value" aria-labelledby="cources-label"><g:fieldValue bean="${tutorInstance}" field="cources"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tutorInstance?.details}">
				<li class="fieldcontain">
					<span id="details-label" class="property-label"><g:message code="tutor.details.label" default="Details" /></span>
					
						<span class="property-value" aria-labelledby="details-label"><g:fieldValue bean="${tutorInstance}" field="details"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tutorInstance?.imageUrl}">
				<li class="fieldcontain">
					<span id="imageUrl-label" class="property-label"><g:message code="tutor.imageUrl.label" default="Image Url" /></span>
					
						<span class="property-value" aria-labelledby="imageUrl-label"><g:fieldValue bean="${tutorInstance}" field="imageUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tutorInstance?.instituteName}">
				<li class="fieldcontain">
					<span id="instituteName-label" class="property-label"><g:message code="tutor.instituteName.label" default="Institute Name" /></span>
					
						<span class="property-value" aria-labelledby="instituteName-label"><g:fieldValue bean="${tutorInstance}" field="instituteName"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:tutorInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tutorInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
