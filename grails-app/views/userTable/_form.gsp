<%@ page import="bookapp.UserTable" %>



<div class="fieldcontain ${hasErrors(bean: userTableInstance, field: 'userName', 'error')} required">
	<label for="userName">
		<g:message code="userTable.userName.label" default="User Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userName" required="" value="${userTableInstance?.userName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userTableInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="userTable.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="email" required="" value="${userTableInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userTableInstance, field: 'userToken', 'error')} required">
	<label for="userToken">
		<g:message code="userTable.userToken.label" default="User Token" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userToken" required="" value="${userTableInstance?.userToken}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userTableInstance, field: 'gcm', 'error')} ">
	<label for="gcm">
		<g:message code="userTable.gcm.label" default="Gcm" />
		
	</label>
	<g:textField name="gcm" value="${userTableInstance?.gcm}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userTableInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="userTable.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${userTableInstance?.status}"/>

</div>

