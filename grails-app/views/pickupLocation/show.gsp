
<%@ page import="bookapp.PickupLocation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pickupLocation.label', default: 'PickupLocation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pickupLocation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pickupLocation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pickupLocation">
			
				<g:if test="${pickupLocationInstance?.addressOne}">
				<li class="fieldcontain">
					<span id="addressOne-label" class="property-label"><g:message code="pickupLocation.addressOne.label" default="Address One" /></span>
					
						<span class="property-value" aria-labelledby="addressOne-label"><g:fieldValue bean="${pickupLocationInstance}" field="addressOne"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pickupLocationInstance?.addressTwo}">
				<li class="fieldcontain">
					<span id="addressTwo-label" class="property-label"><g:message code="pickupLocation.addressTwo.label" default="Address Two" /></span>
					
						<span class="property-value" aria-labelledby="addressTwo-label"><g:fieldValue bean="${pickupLocationInstance}" field="addressTwo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pickupLocationInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="pickupLocation.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${pickupLocationInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pickupLocationInstance?.latitude}">
				<li class="fieldcontain">
					<span id="latitude-label" class="property-label"><g:message code="pickupLocation.latitude.label" default="Latitude" /></span>
					
						<span class="property-value" aria-labelledby="latitude-label"><g:fieldValue bean="${pickupLocationInstance}" field="latitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pickupLocationInstance?.longitude}">
				<li class="fieldcontain">
					<span id="longitude-label" class="property-label"><g:message code="pickupLocation.longitude.label" default="Longitude" /></span>
					
						<span class="property-value" aria-labelledby="longitude-label"><g:fieldValue bean="${pickupLocationInstance}" field="longitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pickupLocationInstance?.mobileNumber}">
				<li class="fieldcontain">
					<span id="mobileNumber-label" class="property-label"><g:message code="pickupLocation.mobileNumber.label" default="Mobile Number" /></span>
					
						<span class="property-value" aria-labelledby="mobileNumber-label"><g:fieldValue bean="${pickupLocationInstance}" field="mobileNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pickupLocationInstance?.book}">
				<li class="fieldcontain">
					<span id="book-label" class="property-label"><g:message code="pickupLocation.book.label" default="Book" /></span>
					
						<span class="property-value" aria-labelledby="book-label"><g:link controller="book" action="show" id="${pickupLocationInstance?.book?.id}">${pickupLocationInstance?.book?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pickupLocationInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="pickupLocation.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="userTable" action="show" id="${pickupLocationInstance?.user?.id}">${pickupLocationInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:pickupLocationInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${pickupLocationInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
