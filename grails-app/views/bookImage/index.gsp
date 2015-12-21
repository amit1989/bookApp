
<%@ page import="bookapp.BookImage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bookImage.label', default: 'BookImage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-bookImage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-bookImage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="isbnNumber" title="${message(code: 'bookImage.isbnNumber.label', default: 'Isbn Number')}" />
						<g:sortableColumn property="photo" title="${message(code: 'bookImage.photo.label', default: 'Photo')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${bookImageInstanceList}" status="i" var="bookImageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${bookImageInstance.id}">${fieldValue(bean: bookImageInstance, field: "isbnNumber")}</g:link></td>
						<g:if test="${bookImageInstance.isImageFound}">
							<td><img src="${createLinkTo(dir:'images/books/',file:bookImageInstance.isbnNumber + ".jpg")}"
									 width="50px" height="50px" alt="image" title="image"/></td>
						</g:if>
						<g:else>
							<td></td>
						</g:else>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${bookImageInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
