<#import "/manage/tpl/htmlBase.ftl" as html>
<@html.htmlBase checkLogin=false jsFiles=["system/login.js"]>
<body>

<div class="wrapper-page animated fadeInDown">
    <div class="panel panel-color panel-primary">
        <div class="panel-heading">
            <h3 class="text-center m-t-10">登陆<strong>沃宽商城后台</strong> </h3>
        </div>

        <form class="form-horizontal m-t-40" id="formLogin" action="${basepath}/manage/user/login" method="post">
            <fieldset>
                <div class="form-group">
                    <#if errorMsg??>
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">&times;</button>
                        ${errorMsg}
                        </div>
                    </#if>
                </div>

            <div class="form-group ">
                <div class="col-xs-12">
                    <input class="form-control" value="${last_login_username!""}" placeholder="账号" name="username"
                           class="len form-control" id="username" autofocus/>
                </div>
            </div>
            <div class="form-group ">

                <div class="col-xs-12">
                    <input class="form-control" type="password" name="password" placeholder="密码" class="len form-control"
                           label="密码"/>
                </div>
            </div>

            <div class="form-group ">
                <div class="col-xs-12">
                    <label class="cr-styled">
                        <input type="checkbox" checked>
                        <i class="fa"></i>
                        记住我
                    </label>
                </div>
            </div>

            <div class="form-group text-right">
                <div class="col-xs-12">
                    <button class="btn btn-purple w-md" type="submit" id="btnLogin">登陆</button>
                </div>
            </div>
            <div class="form-group m-t-30">
                <div class="col-sm-7">
                    <a href="recoverpw.html"><i class="fa fa-lock m-r-5"></i>忘记密码?</a>
                </div>
                <div class="col-sm-5 text-right">
                    <a href="toRegister">创建账号</a>
                </div>
            </div>
            </fieldset>
        </form>

    </div>
</div>
</body>
</@html.htmlBase>