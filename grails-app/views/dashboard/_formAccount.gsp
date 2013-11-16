<%@ page import="org.mypfm.core.Account" %>

<ul class="messagesList">

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'accountId', 'error')} required">
    <label for="accountId">
        <g:message code="account.accountId.label" default="Account Id" />
        <span class="required-indicator">*</span>
        ${accountInstance?.accountId}"
    </label>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'bank', 'error')} ">
    <label for="bank">
        <g:message code="account.bank.label" default="Bank" />
        <g:select id="bank" name="bank.id" from="${org.mypfm.core.Bank.list()}" optionKey="id" value="${accountInstance?.bank?.id}" class="many-to-one" noSelection="['null': '']"/>
    </label>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'type', 'error')} ">
    <label for="type">
        <g:message code="account.type.label" default="Type" />

    </label>
    <g:textField name="type" value="${accountInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'label', 'error')} ">
    <label for="label">
        <g:message code="account.label.label" default="Label" />

    </label>
    <g:textField name="label" value="${accountInstance?.label}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'opendate', 'error')} ">
    <label for="opendate">
        <g:message code="account.opendate.label" default="Opendate" />

    </label>
    <g:datePicker name="opendate" precision="day"  value="${accountInstance?.opendate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'currency', 'error')} ">
    <label for="currency">
        <g:message code="account.currency.label" default="Currency" />

    </label>
    <g:currencySelect name="currency" value="${accountInstance?.currency}"  noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'balanceAmount', 'error')} ">
    <label for="balanceAmount">
        <g:message code="account.balanceAmount.label" default="Balance Amount" />

    </label>

</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'balanceDate', 'error')} ">
    <label for="balanceDate">
        <g:message code="account.balanceDate.label" default="Balance Date" />

    </label>
    <g:datePicker name="balanceDate" precision="day"  value="${accountInstance?.balanceDate}" default="none" noSelection="['': '']" />
</div>

</ul>