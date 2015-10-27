
<%@ page import="bookapp.Request" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'request.label', default: 'Request')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-request" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-request" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="request.user.label" default="User" /></th>
					
						<th><g:message code="request.book.label" default="Book" /></th>
					
						<g:sortableColumn property="is_completed" title="${message(code: 'request.is_completed.label', default: 'Iscompleted')}" />
					
						<g:sortableColumn property="requestToken" title="${message(code: 'request.requestToken.label', default: 'Request Token')}" />
					
						<g:sortableColumn property="sharedDate" title="${message(code: 'request.sharedDate.label', default: 'Shared Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${requestInstanceList}" status="i" var="requestInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${requestInstance.id}">${fieldValue(bean: requestInstance, field: "user")}</g:link></td>
					
						<td>${fieldValue(bean: requestInstance, field: "book")}</td>
					
						<td><g:formatBoolean boolean="${requestInstance.is_completed}" /></td>
					
						<td>${fieldValue(bean: requestInstance, field: "requestToken")}</td>
					
						<td>${fieldValue(bean: requestInstance, field: "sharedDate")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${requestInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
