<%@ page import="bookapp.Tags" %>



<div class="fieldcontain ${hasErrors(bean: tagsInstance, field: 'book', 'error')} ">
	<label for="book">
		<g:message code="tags.book.label" default="Book" />
		
	</label>
	<g:select id="book" name="book.id" from="${bookapp.Book.list()}" optionKey="id" value="${tagsInstance?.book?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tagsInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="tags.location.label" default="Location" />
		
	</label>
	<g:select id="location" name="location.id" from="${bookapp.PickupLocation.list()}" optionKey="id" value="${tagsInstance?.location?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tagsInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="tags.tags.label" default="Tags" />
		
	</label>
	<g:textField name="tags" value="${tagsInstance?.tags}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tagsInstance, field: 'detail', 'error')} ">
	<label for="detail">
		<g:message code="tags.detail.label" default="Detail" />
		
	</label>
	<g:textField name="detail" value="${tagsInstance?.detail}"/>

</div>

