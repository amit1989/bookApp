
<%@ page import="bookapp.UserTable" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userTable.label', default: 'UserTable')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-userTable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-userTable" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list userTable">
			
				<g:if test="${userTableInstance?.userName}">
				<li class="fieldcontain">
					<span id="userName-label" class="property-label"><g:message code="userTable.userName.label" default="User Name" /></span>
					
						<span class="property-value" aria-labelledby="userName-label"><g:fieldValue bean="${userTableInstance}" field="userName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userTableInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="userTable.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${userTableInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userTableInstance?.userToken}">
				<li class="fieldcontain">
					<span id="userToken-label" class="property-label"><g:message code="userTable.userToken.label" default="User Token" /></span>
					
						<span class="property-value" aria-labelledby="userToken-label"><g:fieldValue bean="${userTableInstance}" field="userToken"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userTableInstance?.gcm}">
				<li class="fieldcontain">
					<span id="gcm-label" class="property-label"><g:message code="userTable.gcm.label" default="Gcm" /></span>
					
						<span class="property-value" aria-labelledby="gcm-label"><g:fieldValue bean="${userTableInstance}" field="gcm"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userTableInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="userTable.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${userTableInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:userTableInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${userTableInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
