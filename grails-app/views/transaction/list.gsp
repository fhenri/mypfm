
<%@ page import="org.mypfm.core.Transaction" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'transaction.label', default: 'View Transactions')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body active="transaction">
<div class="row-fluid">

    <g:if test="${flash.message}">
        <div class="alert">
            <button type="button" class="close" data-dismiss="alert">×</button>
            ${flash.message}
        </div>
    </g:if>

    <g:uploadForm action="upload" class="form-import">
        <div id="importfile-div" class="pull-right">
            <input type="file" id="importfile" class="input-file" name="bankFile">
            <input type="submit" id="importsubmit" class="btn btn-success" value="${message(code: 'transaction.import')}">
        </div>
    </g:uploadForm>

    <br/><p/>
    <g:if test="${transactionTotal == 0}">
        <div class="well">
            <em>Nothing to display</em>
        </div>
    </g:if>

    <g:else>
        <div class="alert alert-info fade in" id="homeTitle">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${transactionTotal} <g:message code="transaction.list.title"/></strong>
        </div>

        <div class="box">
            <div class="box-header">
                <h2><i class="halflings-icon align-justify"></i><span class="break"></span>Transaction List</h2>
                <div class="box-icon">
                    <a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
                </div>
            </div>
            <div class="box-content">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                        <tr>
                            <g:sortableColumn property="account" title="${message(code: 'transaction.account')}" class="id header"/>
                            <g:sortableColumn property="label" title="${message(code: 'transaction.label')}" class="id header"/>
                            <g:sortableColumn property="date" title="${message(code: 'transaction.date')}" class="id header"/>
                            <g:sortableColumn property="amount" title="${message(code: 'transaction.amount')}" class="id header"/>
                            <g:sortableColumn property="category.id" title="${message(code: 'transaction.category')}" class="id header"/>
                            <th>${message(code: 'transaction.comment')}</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${transactionList}" var="transaction">
                            <g:form action="update" method="post">
                                <g:hiddenField name="id" value="${transaction?.id}" />
                                <g:hiddenField name="version" value="${transaction?.version}" />
                                <tr>
                                    <td>
                                        ${transaction.account}
                                    </td>
                                    <td>
                                        ${transaction.label}
                                    </td>
                                    <td>
                                        ${transaction.date.format('dd/MM/yyyy')}
                                    </td>
                                    <td>
                                        ${transaction.amount?.getAmount()}
                                    </td>
                                    <td>
                                        <g:select id="category" name="category.id" from="${org.mypfm.core.Category.list(sort:"id", order:"asc")}" optionKey="id" value="${transaction?.category?.id}" class="many-to-one" noSelection="['null': '']"/>
                                    </td>
                                    <td>
                                        <g:textArea cols="12" rows="2" name="comment" value="${transaction?.comment}"/>
                                    </td>
                                    <td>

                                    <g:submitButton name="update" class="btn btn-mini btn-info"
                                                    value="${message(code: 'default.button.update.label', default: 'Update')}" />
                                    %{--
                                    <a href="#" id="${transaction?.id}" class="btn btn-mini btn-info btn-transactionCategory">
                                        <i class="halflings-icon edit halflings-icon"></i>
                                    </a>

                                        <a href="javascript:{}" onclick="document.getElementByName('updateTransaction_${transaction.id}').submit();" class="btn btn-mini btn-info">
                                            <i class="halflings-icon edit halflings-icon"></i>
                                        </a>
                                        <g:hiddenField name="id" value="${transaction?.id}"/>
                                        <g:hiddenField name="version" value="${transaction?.version}"/>
                                        <g:actionSubmit class="btn btn-mini btn-info" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />

                                        <g:link action="show" id="${transaction.id}" class="btn btn-mini btn-info">
                                            <i class="halflings-icon edit halflings-icon"></i>
                                        </g:link>
                                    --}%
                                    </td>
                                </tr>
                            </g:form>
                        </g:each>
                    </tbody>
                </table>
            </div>
            <!-- keep pager -->
            <div id="pagination" class="pagination pagination-right">
                <g:paginate controller="Transaction" action="list" total="${transactionTotal}"/>
            </div>
        </div><!-- box -->
    </g:else>
</div>
<!--
<div class="modal hide fade" id="transactionCategoryModal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Category</h3>
    </div>
    <g:form method="post" >
        <g:hiddenField name="id" id="transactionCategoryId"/>
        <div class="modal-body">
            <ul class="messagesList">
                <li>
                    <span class="from">Comment</span>
                    <span class="title">
                        <g:textArea cols="12" rows="4" name="comment" value="${transaction?.comment}"/>
                    </span>
                </li>
                <li>
                    <span class="from">Category</span>
                    <span class="title">
                        <g:select id="category" name="category.id" from="${org.mypfm.core.Category.list()}" optionKey="id" value="${transaction?.category?.id}" class="many-to-one" noSelection="['null': '']"/>
                    </span>
                </li>
            </ul>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal">Close</a>
            <g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </div>
    </g:form>
</div>
-->
</body>
</html>
