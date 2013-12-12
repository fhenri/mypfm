<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="title" value="${message(code: 'login', default: 'Login')}" />
    <title>${title}</title>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">

        <div class="row-fluid">
            <div class="login-box">
                <h2>Login to your account</h2>

                <g:if test="${flash.message}">
                    <div class="alert" role="status">${flash.message}</div>
                </g:if>

                <form action='${postUrl}' method='POST' id='loginForm' autocomplete='off' class="form-horizontal">
                    <input type="hidden" name="targetUri" value="${targetUri}" />

                    <div class="input-prepend" title="Username">
                        <span class="add-on"><i class="halflings-icon user"></i></span>
                        <input class="input-large span10" name="j_username" id="username" type="text" placeholder="type username" value="${username}"/>
                    </div>
                    <div class="clearfix"></div>

                    <div class="input-prepend" title="Password">
                        <span class="add-on"><i class="halflings-icon lock"></i></span>
                        <input class="input-large span10" name="j_password" id="password" type="password" placeholder="type password"/>
                    </div>
                    <div class="clearfix"></div>

                    <label class="remember" for="remember">
                        <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                        <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
                    </label>

                    <div class="button-login">
                        <g:submitButton name="signIn" type="submit" class="btn btn-primary" value='${message(code: "springSecurity.login.button")}' />
                    </div>
                    <div class="clearfix"></div>
                </form>
            <!--
                <hr>
                <h3>Forgot Password?</h3>
                <p>
                    No problem, <g:link action="lostPassword">click here</g:link> to get a new password.
                </p>
                -->
            </div><!--/span-->
        </div><!--/row-->

    </div><!--/fluid-row-->

</div><!--/.fluid-container-->
<script type='text/javascript'>
    <!--
    (function() {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>

</body>

</html>
