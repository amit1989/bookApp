<%@ page import="bookapp.UserTable; bookapp.Book" %>



<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="book.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${bookInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'isbn', 'error')} ">
	<label for="isbn">
		<g:message code="book.isbn.label" default="Isbn" />
		
	</label>
	<g:textField name="isbn" value="${bookInstance?.isbn}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'discreption', 'error')} ">
	<label for="discreption">
		<g:message code="book.discreption.label" default="Discreption" />
		
	</label>
	<g:textField name="discreption" value="${bookInstance?.discreption}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'category', 'error')} ">
	<label for="category">
		<g:message code="book.category.label" default="Category" />
		
	</label>
	<g:select id="category" name="category.id" from="${bookapp.Category.list()}" optionKey="id" value="${bookInstance?.category?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'startDate', 'error')} ">
	<label for="startDate">
		<g:message code="book.startDate.label" default="Start Date" />
		
	</label>
	<g:textField name="startDate" value="${bookInstance?.startDate}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'endDate', 'error')} ">
	<label for="endDate">
		<g:message code="book.endDate.label" default="End Date" />
		
	</label>
	<g:textField name="endDate" value="${bookInstance?.endDate}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'originalCost', 'error')} ">
	<label for="originalCost">
		<g:message code="book.originalCost.label" default="Original Cost" />
		
	</label>
	<g:textField name="originalCost" value="${bookInstance?.originalCost}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'bookEdition', 'error')} ">
	<label for="bookEdition">
		<g:message code="book.bookEdition.label" default="Book Edition" />
		
	</label>
	<g:textField name="bookEdition" value="${bookInstance?.bookEdition}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'yearOfBook', 'error')} ">
	<label for="yearOfBook">
		<g:message code="book.yearOfBook.label" default="Year Of Book" />
		
	</label>
	<g:textField name="yearOfBook" value="${bookInstance?.yearOfBook}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'onCondition', 'error')} ">
	<label for="onCondition">
		<g:message code="book.onCondition.label" default="On Condition" />
		
	</label>
	<g:textField name="onCondition" value="${bookInstance?.onCondition}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="book.user.label" default="User" />
		
	</label>
	<g:select id="user" name="user.id" from="${bookapp.UserTable.list()}" optionKey="id" value="${bookInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'isShared', 'error')} ">
	<label for="isShared">
		<g:message code="book.isShared.label" default="Is Shared" />
		
	</label>
	<g:checkBox name="isShared" value="${bookInstance?.isShared}" />

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'isDonated', 'error')} ">
	<label for="isDonated">
		<g:message code="book.isDonated.label" default="Is Donated" />
		
	</label>
	<g:checkBox name="isDonated" value="${bookInstance?.isDonated}" />

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'isCompleted', 'error')} ">
	<label for="isCompleted">
		<g:message code="book.isCompleted.label" default="Is Completed" />
		
	</label>
	<g:checkBox name="isCompleted" value="${bookInstance?.isCompleted}" />

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'discount', 'error')} ">
	<label for="discount">
		<g:message code="book.discount.label" default="Discount" />
		
	</label>
	<g:field name="discount" type="number" value="${bookInstance.discount}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'isOnSell', 'error')} ">
	<label for="isOnSell">
		<g:message code="book.isOnSell.label" default="Is On Sell" />
		
	</label>
	<g:checkBox name="isOnSell" value="${bookInstance?.isOnSell}" />

</div>

