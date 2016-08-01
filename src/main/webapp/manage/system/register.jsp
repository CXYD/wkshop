<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 16/5/11
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="shortcut icon" href="img/favicon_1.ico">

    <title>Velonic - Responsive Admin Dashboard Template</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctxStatic}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic}/css/bootstrap-reset.css" rel="stylesheet">

    <!--Animation css-->
    <link href="${ctxStatic}/css/animate.css" rel="stylesheet">

    <!--Icon-fonts css-->
    <link href="${ctxStatic}/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="${ctxStatic}/assets/ionicon/css/ionicons.min.css" rel="stylesheet" />

    <!--Form Wizard-->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/assets/form-wizard/jquery.steps.css" />



    <!-- Custom styles for this template -->
    <link href="${ctxStatic}/css/style.css" rel="stylesheet">
    <link href="${ctxStatic}/css/helper.css" rel="stylesheet">


    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="${ctxStatic}/js/html5shiv.js"></script>
    <script src="${ctxStatic}/js/respond.min.js"></script>
    <![endif]-->


</head>


<body>

<!-- Aside Start-->

<!-- Aside Ends-->



<!-- Page Content Start -->
<!-- ================== -->

<div class="wraper container-fluid">








    <!-- Wizard with Validation -->
    <div class="row">
        <div class="col-md-8 col-sm-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">注册向导</h3>
                </div>
                <div class="panel-body">
                    <form id="wizard-validation-form" action="#">
                        <div>
                            <h3>设置账号信息</h3>
                            <section>
                                <div class="form-group clearfix">
                                    <label class="col-lg-2 control-label " for="userName">手机号码*</label>
                                    <div class="col-lg-10">
                                        <input class="form-control" id="username" name="username" type="text">
                                    </div>
                                </div>
                                <div class="form-group clearfix">
                                    <label class="col-lg-2 control-label " for="checkcode">短信验证码*</label>
                                    <div class="col-lg-10">
                                        <input class="form-control" id="checkcode" name="checkcode" type="text">
                                    </div>
                                </div>
                                <div class="form-group clearfix">
                                    <label class="col-lg-2 control-label " for="password2">设置密码*</label>
                                    <div class="col-lg-10">
                                        <input id="password2" name="password" type="text" class="required form-control">

                                    </div>
                                </div>

                                <div class="form-group clearfix">
                                    <label class="col-lg-2 control-label " for="confirm2">确认密码 *</label>
                                    <div class="col-lg-10">
                                        <input id="confirm2" name="confirm" type="text" class="required form-control">
                                    </div>
                                </div>
                                <div class="form-group clearfix">
                                    <label class="col-lg-12 control-label ">(*) 必填项</label>
                                </div>
                            </section>
                            <h3>身份验证</h3>
                            <section>

                                <div class="form-group clearfix">
                                    <label class="col-lg-2 control-label" for="name2"> 真实姓名 *</label>
                                    <div class="col-lg-10">
                                        <input id="name2" name="name" type="text" class="required form-control">
                                    </div>
                                </div>
                                <div class="form-group clearfix">
                                    <label class="col-lg-2 control-label " for="surname2"> 单位名称 *</label>
                                    <div class="col-lg-10">
                                        <input id="surname2" name="surname" type="text" class="required form-control">

                                    </div>
                                </div>


                                <div class="form-group clearfix">
                                    <label class="col-lg-2 control-label " for="address2">座机号码 </label>
                                    <div class="col-lg-10">
                                        <input id="address2" name="address" type="text" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group clearfix">
                                    <label class="col-lg-12 control-label ">(*) 必填项</label>
                                </div>

                            </section>
                            <h3>人工审核</h3>
                            <section>
                                <div class="form-group clearfix">
                                    <div class="col-lg-12">
                                        <ul class="list-unstyled w-list">
                                            <li>First Name : Jonathan </li>
                                            <li>Last Name : Smith </li>
                                            <li>Emial: jonathan@smith.com</li>
                                            <li>Address: 123 Your City, Cityname. </li>
                                        </ul>
                                    </div>
                                </div>
                            </section>

                            </section>
                        </div>
                    </form>
                </div>  <!-- End panel-body -->
            </div> <!-- End panel -->

        </div> <!-- end col -->

    </div> <!-- End row -->


</div>
<!-- Page Content Ends -->
<!-- ================== -->

<!-- Footer Start -->

<!-- Footer Ends -->





<!-- js placed at the end of the document so the pages load faster -->
<script src="${ctxStatic}/js/jquery.js"></script>
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<script src="${ctxStatic}/js/pace.min.js"></script>
<script src="${ctxStatic}/js/wow.min.js"></script>
<script src="${ctxStatic}/js/jquery.nicescroll.js" type="text/javascript"></script>


<!--Form Validation-->
<script src="${ctxStatic}/assets/form-wizard/bootstrap-validator.min.js" type="text/javascript"></script>

<!--Form Wizard-->
<script src="${ctxStatic}/assets/form-wizard/jquery.steps.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/assets/jquery.validate/jquery.validate.min.js"></script>

<!--wizard initialization-->
<script src="${ctxStatic}/assets/form-wizard/wizard-init.js" type="text/javascript"></script>


<script src="${ctxStatic}/js/jquery.app.js"></script>


</body>
</html>
