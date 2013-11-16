<%@ page import="org.mypfm.core.Bank" %>



<div class="fieldcontain ${hasErrors(bean: bankInstance, field: 'bankName', 'error')} required">
	<label for="bankName">
		<g:message code="bank.bankName.label" default="Bank Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="bankName" required="" value="${bankInstance?.bankName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bankInstance, field: 'iBanCode', 'error')} ">
	<label for="iBanCode">
		<g:message code="bank.iBanCode.label" default="IBAN Code" />
		
	</label>
	<g:textField name="iBanCode" value="${bankInstance?.iBanCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bankInstance, field: 'webPage', 'error')} ">
	<label for="webPage">
		<g:message code="bank.webPage.label" default="Web Page" />
		
	</label>
	<g:textField name="webPage" value="${bankInstance?.webPage}"/>
</div>
