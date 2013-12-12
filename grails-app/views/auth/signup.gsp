<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="title" value="${message(code: 'register', default: 'Register')}" />
    <title>${title}</title>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">

        <div class="row-fluid">
            <div class="login-box">
                <h2>Sign Up</h2>

                <g:if test="${flash.message}">
                    <div class="alert" role="status">${flash.message}</div>
                </g:if>

                <g:hasErrors bean="${user}">
                    <div class="alert alert-error">
                        <g:renderErrors bean="${user}" as="list"/>
                    </div>
                </g:hasErrors>


                <g:form action="register" class="form-horizontal">
                    <input type="hidden" name="targetUri" value="${targetUri}" />

                    <div class="input-prepend" title="Username">
                        <span class="add-on"><i class="halflings-icon user"></i></span>
                        <input class="input-large span10" name="username" id="username" type="text" placeholder="type username" value="${username}"/>
                    </div>
                    <div class="clearfix"></div>

                    <div class="input-prepend" title="Password">
                        <span class="add-on"><i class="halflings-icon lock"></i></span>
                        <input class="input-large span10" name="password" id="password" type="password" placeholder="type password"/>
                    </div>
                    <div class="clearfix"></div>

                    <div class="input-prepend" title="Confirm Password">
                        <span class="add-on"><i class="halflings-icon lock"></i></span>
                        <input class="input-large span10" name="password2" id="password2" type="password" placeholder="confirm password"/>
                    </div>
                    <div class="clearfix"></div>

                    <div class="button-login">
                        <g:submitButton name="register" type="submit" class="btn btn-primary" value="${message(code: 'default.button.signUp.label', default: 'Sign up')}" />
                    </div>
                    <div class="clearfix"></div>
                </g:form>
            </div><!--/span-->
        </div><!--/row-->

    </div><!--/fluid-row-->

</div><!--/.fluid-container-->

</body>
</html>
