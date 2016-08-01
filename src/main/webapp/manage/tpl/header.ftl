<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<#assign non_responsive2>y</#assign>
<#assign responsive>${Session["responsive"]!""}</#assign>
<#if responsive == "y">
    <#assign non_responsive2>n</#assign>
<#elseif systemSetting().isResponsive??>
    <#assign non_responsive2>n</#assign>
<#else >
    <#assign non_responsive2>y</#assign>
</#if>
    <script>
        var basepath = "${basepath}";
        var staticpath = "${staticpath}";
        var non_responsive2 = "${non_responsive2}";
        <#if currentUser()??>
        var login = true;
        var currentUser = "${currentUser().username}";
        <#else >
        var login = false;
        var currentUser = "";
            <#if checkLogin>
            top.location = "${basepath}/manage/user/logout";
            </#if>
        </#if>
    </script>
<#if non_responsive2 != "y">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</#if>
    <meta name="description" content="${systemSetting().description}"/>
    <meta name="keywords" content="${systemSetting().keywords}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Cache" content="no-cache">
    <title>电商系统</title>
    <link rel="shortcut icon" type="image/x-icon" href="${systemSetting().shortcutIcon}">
    <!-- Bootstrap core CSS -->
    <link href="${basepath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basepath}/static/css/bootstrap-reset.css" rel="stylesheet">

    <link href="${basepath}/static/assets/toggles/toggles.css" rel="stylesheet" />
<#--<link href="${basepath}/static/assets/tagsinput/jquery.tagsinput.css" rel="stylesheet" />-->
    <!--Animation css-->
    <link href="${basepath}/static/css/animate.css" rel="stylesheet">

    <!--Icon-fonts css-->
    <link href="${basepath}/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="${basepath}/static/assets/ionicon/css/ionicons.min.css" rel="stylesheet" />

    <!--Morris Chart CSS -->

    <link rel="stylesheet" href="${basepath}/static/assets/morris/morris.css">

    <!--Form Wizard-->
<#--<link rel="stylesheet" type="text/css" href="${ctxStatic}/assets/form-wizard/jquery.steps.css" />-->



    <!-- sweet alerts -->
    <link href="${basepath}/static/assets/sweet-alert/sweet-alert.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${basepath}/static/css/style.css" rel="stylesheet">
    <link href="${basepath}/static/css/helper.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="${basepath}/static/js/html5shiv.js"></script>
    <script src="${basepath}/static/js/respond.min.js"></script>
    <![endif]-->

    <!-- js placed at the end of the document so the pages load faster -->

<#--<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.7/css/jquery.dataTables.css">-->

    <!-- jQuery -->
<#--<script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>-->

    <!-- DataTables -->
<#--<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>-->


    <script src="${basepath}/static/js/jquery.js"></script>
<#--<script src="${staticpath}/js/jquery-1.9.1.min.js"></script>-->
    <script src="${basepath}/static/js/bootstrap.min.js"></script>
    <script src="${basepath}/static/js/modernizr.min.js"></script>
    <script src="${basepath}/static/js/pace.min.js"></script>
    <script src="${basepath}/static/js/wow.min.js"></script>
    <script src="${basepath}/static/js/jquery.scrollTo.min.js"></script>
    <script src="${basepath}/static/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="${basepath}/static/assets/chat/moment-2.2.1.js"></script>

    <!-- Counter-up -->
    <script src="${basepath}/static/js/waypoints.min.js" type="text/javascript"></script>
    <script src="${basepath}/static/js/jquery.counterup.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${basepath}/static/assets/register/js/comm.js"></script>

<#--<script src="${basepath}/static/assets/c3-chart/d3.v3.min.js"></script>-->
<#--<script src="${basepath}/static/assets/c3-chart/c3.js"></script>-->
<link href="${basepath}/static/assets/summernote/summernote.css" rel="stylesheet" />
<script src="${basepath}/static/assets/summernote/summernote.min.js"></script>
    <script src="${basepath}/static/assets/summernote/lang/zh.js"></script>

</head>