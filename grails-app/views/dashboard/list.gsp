
<%@ page import="org.mypfm.core.Bank" %>
<%@ page import="org.mypfm.core.Account" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bank.label', default: 'Bank')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body active="bank">
        <div class="row-fluid">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
        </div>
        <div class="row-fluid sortable">

            <div class="box span6">
                <div class="box-header">
                    <h2>
                        <g:link class="create" action="createBank">
                            <i class="halflings-icon plus"></i>
                        </g:link>
                        <span class="break"></span>
                        <g:message code="default.list.label" args="[entityName]" />
                    </h2>
                    <div class="box-icon">
                        <a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
                        <a href="#" class="btn-close"><i class="halflings-icon remove"></i></a>
                    </div>
                </div>
                <div class="box-content">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <g:sortableColumn property="bankName" title="${message(code: 'bank.bankName.label')}" />
                                <g:sortableColumn property="iBanCode" title="${message(code: 'bank.iBanCode.label')}" />
                                <g:sortableColumn property="webPage" title="${message(code: 'bank.webPage.label')}" />
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${bankInstanceList}" status="i" var="bankInstance">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                    <td><g:link action="showBank" id="${bankInstance.id}">
                                        ${fieldValue(bean: bankInstance, field: "bankName")}
                                    </g:link></td>
                                    <td>${bankInstance.iBanCodeForDisplay}</td>
                                    <td>${bankInstance.webPage}</td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
                <!--
    			<div class="pagination">
    				<g:paginate total="${bankInstanceTotal}" />
    			</div>
    			-->
    		</div>

            <div class="box span6">
                <div class="box-header">
                    <h2><i class="halflings-icon align-justify"></i><span class="break"></span>
                        Account List
                    </h2>
                    <div class="box-icon">
                        <a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
                        <a href="#" class="btn-close"><i class="halflings-icon remove"></i></a>
                    </div>
                </div>
                <div class="box-content">
                    <table class="table table-condensed">
                        <thead>
                        <tr>
                            <g:sortableColumn property="accountId" title="${message(code: 'account.Id.label')}" />
                            <g:sortableColumn property="accountLabel" title="${message(code: 'account.Label.label')}" />
                            <g:sortableColumn property="accountType" title="${message(code: 'account.Type.label')}" />
                            <g:sortableColumn property="accountAmount" title="${message(code: 'account.Amount.label')}" />
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${accountInstanceList}" status="i" var="accountInstance">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td><g:link action="editAccount" id="${accountInstance.id}">
                                    ${fieldValue(bean: accountInstance, field: "accountIdForDisplay")}
                                </g:link></td>
                                <td>${accountInstance.label}</td>
                                <td>${accountInstance.type}</td>
                                <td style="text-align: right;">${accountInstance?.balanceAmount?.getAmount()}</td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>
                <!--
                <div class="pagination">
                    <g:paginate total="${accountInstanceTotal}" />
                </div>
                -->
            </div>


        </div>
	</body>
</html>
