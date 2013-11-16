
<%@ page import="org.mypfm.core.Transaction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'transaction.label', default: 'Account')}" />
		<title><g:message code="transaction.transactionLabel" args="[entityName]" /></title>
	</head>
	<body active="transaction">
		<div id="show-account" class="content scaffold-show" role="main">

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

            <g:form method="post" >
                <g:hiddenField name="id" value="${transactionUpdate.id}"/>
                <div class="pull-right">
                    <ul class="messagesList">
                        <li>
                            <span class="from">Label</span>
                            <span class="title">
                                ${transactionUpdate?.label}
                            </span>
                        </li>
                        <li>
                            <span class="from">Date</span>
                            <span class="title">
                                ${transactionUpdate?.date}
                            </span>
                        </li>
                        <li>
                            <span class="from">Amount</span>
                            <span class="title">
                                ${transactionUpdate?.amount}
                            </span>
                        </li>
                        <li>
                            <span class="from">Comment</span>
                            <span class="title">
                                <g:textArea cols="22" rows="3" name="comment" value="${transactionUpdate?.comment}"/>
                            </span>
                        </li>
                        <li>
                            <span class="from">Category</span>
                            <span class="title">
                                <g:select name="category" from="${org.mypfm.core.Category.list()}" multiple="multiple" optionKey="id" size="15"
                                          value="${transactionInstance?.category*.id}" class="many-to-many" />
                            </span>
                        </li>
                    </ul>
                    <div id="action-button" class="pull-right">
                        <g:link action="list" class="btn">
                            Return to List
                        </g:link>
                        <g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    </div>
                </div>
            </g:form>
        </div>
	</body>
</html>
