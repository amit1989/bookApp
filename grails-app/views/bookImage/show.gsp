
<%@ page import="bookapp.BookImage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bookImage.label', default: 'BookImage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-bookImage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-bookImage" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list bookImage">
			
				<g:if test="${bookImageInstance?.isbnNumber}">
				<li class="fieldcontain">
					<span id="isbnNumber-label" class="property-label"><g:message code="bookImage.isbnNumber.label" default="Isbn Number" /></span>
					
						<span class="property-value" aria-labelledby="isbnNumber-label"><g:fieldValue bean="${bookImageInstance}" field="isbnNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookImageInstance?.isImageFound}">
				<li class="fieldcontain">
					<span id="photo-label" class="property-label"><g:message code="bookImage.photo.label" default="Photo" /></span>
						<span class="property-value" aria-labelledby="photo-label">
							<img src="${createLinkTo(dir:'images/books/',file:bookImageInstance.isbnNumber + ".jpg")}"
								 width="150px" height="150px" alt="image" title="image"/>
						</span>
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:bookImageInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${bookImageInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
