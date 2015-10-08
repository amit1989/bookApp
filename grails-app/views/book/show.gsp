
<%@ page import="bookapp.Book" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-book" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-book" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list book">
			
				<g:if test="${bookInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="book.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${bookInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.isbn}">
				<li class="fieldcontain">
					<span id="isbn-label" class="property-label"><g:message code="book.isbn.label" default="Isbn" /></span>
					
						<span class="property-value" aria-labelledby="isbn-label"><g:fieldValue bean="${bookInstance}" field="isbn"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.discreption}">
				<li class="fieldcontain">
					<span id="discreption-label" class="property-label"><g:message code="book.discreption.label" default="Discreption" /></span>
					
						<span class="property-value" aria-labelledby="discreption-label"><g:fieldValue bean="${bookInstance}" field="discreption"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.category}">
				<li class="fieldcontain">
					<span id="category-label" class="property-label"><g:message code="book.category.label" default="Category" /></span>
					
						<span class="property-value" aria-labelledby="category-label"><g:link controller="category" action="show" id="${bookInstance?.category?.id}">${bookInstance?.category?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="book.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:fieldValue bean="${bookInstance}" field="startDate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="book.endDate.label" default="End Date" /></span>
					
						<span class="property-value" aria-labelledby="endDate-label"><g:fieldValue bean="${bookInstance}" field="endDate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.originalCost}">
				<li class="fieldcontain">
					<span id="originalCost-label" class="property-label"><g:message code="book.originalCost.label" default="Original Cost" /></span>
					
						<span class="property-value" aria-labelledby="originalCost-label"><g:fieldValue bean="${bookInstance}" field="originalCost"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.bookEdition}">
				<li class="fieldcontain">
					<span id="bookEdition-label" class="property-label"><g:message code="book.bookEdition.label" default="Book Edition" /></span>
					
						<span class="property-value" aria-labelledby="bookEdition-label"><g:fieldValue bean="${bookInstance}" field="bookEdition"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.yearOfBook}">
				<li class="fieldcontain">
					<span id="yearOfBook-label" class="property-label"><g:message code="book.yearOfBook.label" default="Year Of Book" /></span>
					
						<span class="property-value" aria-labelledby="yearOfBook-label"><g:fieldValue bean="${bookInstance}" field="yearOfBook"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.onCondition}">
				<li class="fieldcontain">
					<span id="onCondition-label" class="property-label"><g:message code="book.onCondition.label" default="On Condition" /></span>
					
						<span class="property-value" aria-labelledby="onCondition-label"><g:fieldValue bean="${bookInstance}" field="onCondition"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="book.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${bookInstance?.user?.id}">${bookInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.isShared}">
				<li class="fieldcontain">
					<span id="isShared-label" class="property-label"><g:message code="book.isShared.label" default="Is Shared" /></span>
					
						<span class="property-value" aria-labelledby="isShared-label"><g:formatBoolean boolean="${bookInstance?.isShared}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.isDonated}">
				<li class="fieldcontain">
					<span id="isDonated-label" class="property-label"><g:message code="book.isDonated.label" default="Is Donated" /></span>
					
						<span class="property-value" aria-labelledby="isDonated-label"><g:formatBoolean boolean="${bookInstance?.isDonated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.isCompleted}">
				<li class="fieldcontain">
					<span id="isCompleted-label" class="property-label"><g:message code="book.isCompleted.label" default="Is Completed" /></span>
					
						<span class="property-value" aria-labelledby="isCompleted-label"><g:formatBoolean boolean="${bookInstance?.isCompleted}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.discount}">
				<li class="fieldcontain">
					<span id="discount-label" class="property-label"><g:message code="book.discount.label" default="Discount" /></span>
					
						<span class="property-value" aria-labelledby="discount-label"><g:fieldValue bean="${bookInstance}" field="discount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.isOnSell}">
				<li class="fieldcontain">
					<span id="isOnSell-label" class="property-label"><g:message code="book.isOnSell.label" default="Is On Sell" /></span>
					
						<span class="property-value" aria-labelledby="isOnSell-label"><g:formatBoolean boolean="${bookInstance?.isOnSell}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:bookInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${bookInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
