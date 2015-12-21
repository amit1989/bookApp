<%@ page import="bookapp.BookImage" %>

<div class="fieldcontain ${hasErrors(bean: bookImageInstance, field: 'isbnNumber', 'error')} ">
	<label for="isbnNumber">
		<g:message code="bookImage.isbnNumber.label" default="Isbn Number" />
	</label>
	<g:textField name="isbnNumber" value="${bookImageInstance?.isbnNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookImageInstance, field: 'photo', 'error')}">
	<label for="photo">
		<g:message code="bookImage.photo.label" default="Photo" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="photo" name="photo" />
</div>

