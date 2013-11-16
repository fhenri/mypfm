
<%@ page import="org.mypfm.core.Account" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="list"><g:message code="default.returnlist.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-bank" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			    <div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list account">
			
				<g:if test="${accountInstance?.label}">
                    <li class="fieldcontain">
                        <span id="bankName-label" class="property-label"><g:message code="account.Label.label" default="Account Label" /></span>

                            <span class="property-value" aria-labelledby="bankName-label"><g:fieldValue bean="${accountInstance}" field="label"/></span>

                    </li>
				</g:if>
			
				<g:if test="${accountInstance?.bank}">
                    <li class="fieldcontain">
                        <span id="iBanCode-label" class="property-label"><g:message code="account.Bank.label" default="Account bank" /></span>

                            <span class="property-value" aria-labelledby="iBanCode-label"><g:fieldValue bean="${accountInstance}" field="bank"/></span>

                    </li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${accountInstance?.id}" />
					<g:link class="edit" action="edit" id="${accountInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
