<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>
        <g:layoutTitle default=""/> - Personal Finance Manager
    </title>
    <meta name="description" content="Personal Finance Manager Tool.">
    <meta name="author" content="Frédéric Henri">
    <meta name="keyword" content="Personal, Finance, Manager, PFM, Money">

    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- end: Mobile Specific -->

    <!-- start: Favicon -->
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <!-- end: Favicon -->


    <!-- start: CSS -->
    <link id="bootstrap-style" rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-responsive.min.css')}" type="text/css">
    <link id="base-style" rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css" >
    <link id="base-style-responsive" rel="stylesheet" href="${resource(dir: 'css', file: 'style-responsive.css')}" type="text/css" >
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>
    <!--
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    -->
    <!-- end: CSS -->

    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="${createLink(uri: '/')}"><span>Personal Finance Manager</span></a>

            <sec:ifLoggedIn>
                <div class="nav-no-collapse header-nav">
                    <ul class="nav pull-right">

                        <!-- start: User Dropdown -->
                        <li class="dropdown">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="halflings-icon white user"></i> <sec:username />
                                <span class="caret"></span>
                            </a>
                        <ul class="dropdown-menu">
                            <sec:ifAllGranted roles="ROLE_ADMIN">
                                <li><g:link uri="/user/list"><i class="halflings-icon white wrench"></i> Manage User</g:link></li>
                            </sec:ifAllGranted>
                                <li><g:link uri="/auth/updatePassword"><i class="halflings-icon white wrench"></i>Change Password</g:link></li>
                                <li><g:link uri="/logout"><i class="halflings-icon white off"></i>Logout</g:link></li>
                            </ul>
                        </li>
                        <!-- end: User Dropdown -->
                    </ul>
                </div>
            </sec:ifLoggedIn>
        </div>
    </div>
</div>

<section id="main">
    <sec:ifLoggedIn>
        <div class="container-fluid">
            <div class="row-fluid">

                <!-- start: Main Menu -->
                <div id="sidebar-left" class="span1">
                    <div class="nav-collapse sidebar-nav">
                        <ul class="nav nav-tabs nav-stacked main-menu">
                            <g:if test="${pageProperty(name: 'body.active') == 'dashboard'}">
                                <li class="active"><g:link uri="/dashboard/list"><i class="fa-icon-dashboard"></i><span class="hidden-tablet"> Dashboard</span></g:link></li>
                            </g:if>
                            <g:else>
                                <li><g:link uri="/dashboard/list"><i class="fa-icon-dashboard"></i><span class="hidden-tablet"> Dashboard</span></g:link></li>
                            </g:else>
                            <g:if test="${pageProperty(name: 'body.active') == 'transaction'}">
                                <li class="active"><g:link uri="/transaction/list"><i class="fa-icon-tasks"></i><span class="hidden-tablet"> Transaction</span></g:link></li>
                            </g:if>
                            <g:else>
                                <li><g:link uri="/transaction/list"><i class="fa-icon-tasks"></i><span class="hidden-tablet"> Transaction</span></g:link></li>
                            </g:else>
                        </ul>
                    </div>
                </div>
                <!-- end: Main Menu -->

                <noscript>
                    <div class="alert alert-block span11">
                        <h4 class="alert-heading">Warning!</h4>
                        <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
                    </div>
                </noscript>

                <!-- start: Content -->
                <div id="content" class="span11">
                    <g:layoutBody/>
                </div>
            </div>
        </div>
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        <g:layoutBody/>
    </sec:ifNotLoggedIn>

</section>

<r:layoutResources disposition="footer"/>

<!-- start: JavaScript-->
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-1.9.1.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap.js')}"></script>

<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-migrate-1.0.0.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-ui-1.10.0.custom.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.ui.touch-punch.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'modernizr.js')}"></script>

<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.cookie.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'fullcalendar.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.dataTables.min.js')}"></script>

<script type="text/javascript" src="${resource(dir: 'js', file: 'excanvas.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.flot.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.flot.pie.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.flot.stack.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.flot.resize.min.js')}"></script>

<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.chosen.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uniform.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.cleditor.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.noty.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.elfinder.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.raty.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.iphone.toggle.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.uploadify-3.1.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.gritter.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.imagesloaded.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.masonry.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.knob.modified.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.sparkline.min.js')}"></script>

<script type="text/javascript" src="${resource(dir: 'js', file: 'custom.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'counter.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'retina.js')}"></script>
<!-- end: JavaScript-->

</body>
</html>
