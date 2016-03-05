
<%@ page import="bookapp.Tutor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tutor.label', default: 'Tutor')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tutor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tutor" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="averageRating" title="${message(code: 'tutor.averageRating.label', default: 'Average Rating')}" />
					
						<g:sortableColumn property="cources" title="${message(code: 'tutor.cources.label', default: 'Cources')}" />
					
						<g:sortableColumn property="details" title="${message(code: 'tutor.details.label', default: 'Details')}" />
					
						<g:sortableColumn property="imageUrl" title="${message(code: 'tutor.imageUrl.label', default: 'Image Url')}" />
					
						<g:sortableColumn property="instituteName" title="${message(code: 'tutor.instituteName.label', default: 'Institute Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tutorInstanceList}" status="i" var="tutorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tutorInstance.id}">${fieldValue(bean: tutorInstance, field: "averageRating")}</g:link></td>
					
						<td>${fieldValue(bean: tutorInstance, field: "cources")}</td>
					
						<td>${fieldValue(bean: tutorInstance, field: "details")}</td>
					
						<td>${fieldValue(bean: tutorInstance, field: "imageUrl")}</td>
					
						<td>${fieldValue(bean: tutorInstance, field: "instituteName")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tutorInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
