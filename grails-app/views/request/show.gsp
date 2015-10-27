
<%@ page import="bookapp.Request" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'request.label', default: 'Request')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-request" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-request" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list request">
			
				<g:if test="${requestInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="request.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="userTable" action="show" id="${requestInstance?.user?.id}">${requestInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${requestInstance?.book}">
				<li class="fieldcontain">
					<span id="book-label" class="property-label"><g:message code="request.book.label" default="Book" /></span>
					
						<span class="property-value" aria-labelledby="book-label"><g:link controller="book" action="show" id="${requestInstance?.book?.id}">${requestInstance?.book?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${requestInstance?.is_completed}">
				<li class="fieldcontain">
					<span id="is_completed-label" class="property-label"><g:message code="request.is_completed.label" default="Iscompleted" /></span>
					
						<span class="property-value" aria-labelledby="is_completed-label"><g:formatBoolean boolean="${requestInstance?.is_completed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${requestInstance?.requestToken}">
				<li class="fieldcontain">
					<span id="requestToken-label" class="property-label"><g:message code="request.requestToken.label" default="Request Token" /></span>
					
						<span class="property-value" aria-labelledby="requestToken-label"><g:fieldValue bean="${requestInstance}" field="requestToken"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${requestInstance?.sharedDate}">
				<li class="fieldcontain">
					<span id="sharedDate-label" class="property-label"><g:message code="request.sharedDate.label" default="Shared Date" /></span>
					
						<span class="property-value" aria-labelledby="sharedDate-label"><g:fieldValue bean="${requestInstance}" field="sharedDate"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:requestInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${requestInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
