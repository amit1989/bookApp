<%@ page import="bookapp.Request" %>



<div class="fieldcontain ${hasErrors(bean: requestInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="request.user.label" default="User" />
		
	</label>
	<g:select id="user" name="user.id" from="${bookapp.UserTable.list()}" optionKey="id" value="${requestInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: requestInstance, field: 'book', 'error')} ">
	<label for="book">
		<g:message code="request.book.label" default="Book" />
		
	</label>
	<g:select id="book" name="book.id" from="${bookapp.Book.list()}" optionKey="id" value="${requestInstance?.book?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: requestInstance, field: 'is_completed', 'error')} ">
	<label for="is_completed">
		<g:message code="request.is_completed.label" default="Iscompleted" />
		
	</label>
	<g:checkBox name="is_completed" value="${requestInstance?.is_completed}" />

</div>

<div class="fieldcontain ${hasErrors(bean: requestInstance, field: 'requestToken', 'error')} ">
	<label for="requestToken">
		<g:message code="request.requestToken.label" default="Request Token" />
		
	</label>
	<g:textField name="requestToken" value="${requestInstance?.requestToken}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: requestInstance, field: 'sharedDate', 'error')} ">
	<label for="sharedDate">
		<g:message code="request.sharedDate.label" default="Shared Date" />
		
	</label>
	<g:textField name="sharedDate" value="${requestInstance?.sharedDate}"/>

</div>

