<%@ page import="bookapp.Tutor" %>



<div class="fieldcontain ${hasErrors(bean: tutorInstance, field: 'averageRating', 'error')} ">
	<label for="averageRating">
		<g:message code="tutor.averageRating.label" default="Average Rating" />
		
	</label>
	<g:textField name="averageRating" value="${tutorInstance?.averageRating}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tutorInstance, field: 'cources', 'error')} ">
	<label for="cources">
		<g:message code="tutor.cources.label" default="Cources" />
		
	</label>
	<g:textField name="cources" value="${tutorInstance?.cources}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tutorInstance, field: 'details', 'error')} ">
	<label for="details">
		<g:message code="tutor.details.label" default="Details" />
		
	</label>
	<g:textField name="details" value="${tutorInstance?.details}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tutorInstance, field: 'imageUrl', 'error')} ">
	<label for="imageUrl">
		<g:message code="tutor.imageUrl.label" default="Image Url" />
		
	</label>
	<g:textField name="imageUrl" value="${tutorInstance?.imageUrl}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tutorInstance, field: 'instituteName', 'error')} ">
	<label for="instituteName">
		<g:message code="tutor.instituteName.label" default="Institute Name" />
		
	</label>
	<g:textField name="instituteName" value="${tutorInstance?.instituteName}"/>

</div>

