<%@ page import="bookapp.AppApiVersion" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'appApiVersion.label', default: 'AppApiVersion')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-appApiVersion" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                    default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-appApiVersion" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list appApiVersion">

        <g:if test="${appApiVersionInstance?.versionName}">
            <li class="fieldcontain">
                <span id="versionName-label" class="property-label"><g:message code="appApiVersion.versionName.label"
                                                                               default="Version Name"/></span>

                <span class="property-value" aria-labelledby="versionName-label"><g:fieldValue
                        bean="${appApiVersionInstance}" field="versionName"/></span>

            </li>
        </g:if>

        <g:if test="${appApiVersionInstance?.uploadedDate}">
            <li class="fieldcontain">
                <span id="uploadedDate-label" class="property-label"><g:message code="appApiVersion.uploadedDate.label"
                                                                                default="Uploaded Date"/></span>

                <span class="property-value" aria-labelledby="uploadedDate-label"><g:fieldValue
                        bean="${appApiVersionInstance}" field="uploadedDate"/></span>

            </li>
        </g:if>

    </ol>
    <g:form url="[resource: appApiVersionInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${appApiVersionInstance}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
