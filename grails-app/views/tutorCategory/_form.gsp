<%@ page import="bookapp.TutorCategory" %>



<div class="fieldcontain ${hasErrors(bean: tutorCategoryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="tutorCategory.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${tutorCategoryInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tutorCategoryInstance, field: 'details', 'error')} ">
	<label for="details">
		<g:message code="tutorCategory.details.label" default="Details" />
		
	</label>
	<g:textField name="details" value="${tutorCategoryInstance?.details}"/>

</div>

