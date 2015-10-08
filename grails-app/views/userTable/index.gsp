
<%@ page import="bookapp.UserTable" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userTable.label', default: 'UserTable')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-userTable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-userTable" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="userName" title="${message(code: 'userTable.userName.label', default: 'User Name')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'userTable.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="userToken" title="${message(code: 'userTable.userToken.label', default: 'User Token')}" />
					
						<g:sortableColumn property="gcm" title="${message(code: 'userTable.gcm.label', default: 'Gcm')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'userTable.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userTableInstanceList}" status="i" var="userTableInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userTableInstance.id}">${fieldValue(bean: userTableInstance, field: "userName")}</g:link></td>
					
						<td>${fieldValue(bean: userTableInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: userTableInstance, field: "userToken")}</td>
					
						<td>${fieldValue(bean: userTableInstance, field: "gcm")}</td>
					
						<td>${fieldValue(bean: userTableInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userTableInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
