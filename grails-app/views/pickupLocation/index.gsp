
<%@ page import="bookapp.PickupLocation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pickupLocation.label', default: 'PickupLocation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-pickupLocation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-pickupLocation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="addressOne" title="${message(code: 'pickupLocation.addressOne.label', default: 'Address One')}" />
					
						<g:sortableColumn property="addressTwo" title="${message(code: 'pickupLocation.addressTwo.label', default: 'Address Two')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'pickupLocation.city.label', default: 'City')}" />
					
						<g:sortableColumn property="latitude" title="${message(code: 'pickupLocation.latitude.label', default: 'Latitude')}" />
					
						<g:sortableColumn property="longitude" title="${message(code: 'pickupLocation.longitude.label', default: 'Longitude')}" />
					
						<g:sortableColumn property="mobileNumber" title="${message(code: 'pickupLocation.mobileNumber.label', default: 'Mobile Number')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pickupLocationInstanceList}" status="i" var="pickupLocationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pickupLocationInstance.id}">${fieldValue(bean: pickupLocationInstance, field: "addressOne")}</g:link></td>
					
						<td>${fieldValue(bean: pickupLocationInstance, field: "addressTwo")}</td>
					
						<td>${fieldValue(bean: pickupLocationInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: pickupLocationInstance, field: "latitude")}</td>
					
						<td>${fieldValue(bean: pickupLocationInstance, field: "longitude")}</td>
					
						<td>${fieldValue(bean: pickupLocationInstance, field: "mobileNumber")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pickupLocationInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
