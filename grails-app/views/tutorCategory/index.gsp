
<%@ page import="bookapp.TutorCategory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tutorCategory.label', default: 'TutorCategory')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tutorCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tutorCategory" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'tutorCategory.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="details" title="${message(code: 'tutorCategory.details.label', default: 'Details')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tutorCategoryInstanceList}" status="i" var="tutorCategoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tutorCategoryInstance.id}">${fieldValue(bean: tutorCategoryInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: tutorCategoryInstance, field: "details")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tutorCategoryInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
