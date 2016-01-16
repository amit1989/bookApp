<%@ page import="bookapp.AppApiVersion" %>



<div class="fieldcontain ${hasErrors(bean: appApiVersionInstance, field: 'versionName', 'error')} ">
    <label for="versionName">
        <g:message code="appApiVersion.versionName.label" default="Version Name"/>

    </label>
    <g:textField name="versionName" value="${appApiVersionInstance?.versionName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: appApiVersionInstance, field: 'uploadedDate', 'error')} ">
    <label for="uploadedDate">
        <g:message code="appApiVersion.uploadedDate.label" default="Uploaded Date"/>

    </label>
    <g:textField name="uploadedDate" value="${appApiVersionInstance?.uploadedDate}"/>

</div>

