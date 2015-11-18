<%@ page import="bookapp.PickupLocation" %>



<div class="fieldcontain ${hasErrors(bean: pickupLocationInstance, field: 'addressOne', 'error')} ">
	<label for="addressOne">
		<g:message code="pickupLocation.addressOne.label" default="Address One" />
		
	</label>
	<g:textField name="addressOne" value="${pickupLocationInstance?.addressOne}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pickupLocationInstance, field: 'addressTwo', 'error')} ">
	<label for="addressTwo">
		<g:message code="pickupLocation.addressTwo.label" default="Address Two" />
		
	</label>
	<g:textField name="addressTwo" value="${pickupLocationInstance?.addressTwo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pickupLocationInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="pickupLocation.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${pickupLocationInstance?.city}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pickupLocationInstance, field: 'latitude', 'error')} ">
	<label for="latitude">
		<g:message code="pickupLocation.latitude.label" default="Latitude" />
		
	</label>
	<g:textField name="latitude" value="${pickupLocationInstance?.latitude}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pickupLocationInstance, field: 'longitude', 'error')} ">
	<label for="longitude">
		<g:message code="pickupLocation.longitude.label" default="Longitude" />
		
	</label>
	<g:textField name="longitude" value="${pickupLocationInstance?.longitude}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pickupLocationInstance, field: 'mobileNumber', 'error')} ">
	<label for="mobileNumber">
		<g:message code="pickupLocation.mobileNumber.label" default="Mobile Number" />
		
	</label>
	<g:textField name="mobileNumber" value="${pickupLocationInstance?.mobileNumber}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pickupLocationInstance, field: 'book', 'error')} ">
	<label for="book">
		<g:message code="pickupLocation.book.label" default="Book" />
		
	</label>
	<g:select id="book" name="book.id" from="${bookapp.Book.list()}" optionKey="id" value="${pickupLocationInstance?.book?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pickupLocationInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="pickupLocation.user.label" default="User" />
		
	</label>
	<g:select id="user" name="user.id" from="${bookapp.UserTable.list()}" optionKey="id" value="${pickupLocationInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

