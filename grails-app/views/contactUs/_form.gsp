<%@ page import="bookapp.ContactUs" %>



<div class="fieldcontain ${hasErrors(bean: contactUsInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="contactUs.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${contactUsInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: contactUsInstance, field: 'contactNumber', 'error')} ">
	<label for="contactNumber">
		<g:message code="contactUs.contactNumber.label" default="Contact Number" />
		
	</label>
	<g:textField name="contactNumber" value="${contactUsInstance?.contactNumber}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: contactUsInstance, field: 'details', 'error')} ">
	<label for="details">
		<g:message code="contactUs.details.label" default="Details" />
		
	</label>
	<g:textField name="details" value="${contactUsInstance?.details}"/>

</div>

