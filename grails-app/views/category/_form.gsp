<%@ page import="bookapp.Category" %>



<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="category.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${categoryInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'discription', 'error')} ">
	<label for="discription">
		<g:message code="category.discription.label" default="Discription" />
		
	</label>
	<g:textField name="discription" value="${categoryInstance?.discription}"/>

</div>

