<!DOCTYPE html>
<html>
<head>
    <#include "/front/header.ftl">
    <title>登录</title>
</head>

<body>

<div class="whole">
    <div class="register">
        <form method="post">
        <ul>
            <li class="register-content">
                <div class="element-body">
                    <div class="row">
                        <div class="col-xs-3 padding-Ten">账号</div>
                        <div class="col-xs-9 ia">
                            <div class="col-xs-9 ia">
                                <input type="tel" id="username" name="username" class="dxld-log" maxlength="11" placeholder="请输入注册手机号码" >
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="register-content">
                <div class="element-body">
                    <div class="row">
                        <div class="col-xs-3 padding-Ten">密码</div>
                        <div class="col-xs-9 ia">
                            <div class="col-xs-9 ia">
                                <input type="text" id="password"  name="password" class="dxld-log" placeholder="请输入您的密码"  maxlength="18" >
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="register-content "><button type="button" name="login" class="btn btn-block fillet  size-13">立即登录</button></li>
            <li class="register-content">

                <div class="row">
                    <div class="col-xs-12 ia">
                        <div class="col-xs-6 ia">
                            <a href="${basepath}/register/frontRegister?khid=${khid}">立即注册</a></div>
                        <div class="col-xs-6 ia text-right">
                            <a href="${basepath}/front/user/forgetPassword?khid=${khid}">找回密码</a></div>
                    </div>

                </div>
            </li>
        </ul>
        </form>
    </div>
</div>

</body>
<#include "/front/footer.ftl">
<script>
    $("input[name='password']").keyup(function(){
        $('button[name="login"]').removeClass("fillet").addClass("syiaq");
    });
    $('button[name="login"]').click(function(){
        var username =$.trim($('#username').val());
        var password =$.trim($('#password').val());
        if(username=="" || username ==null){
            $("#username").focus();
            weui.Loading.error("帐号不能为空！");
           return false;
        }
        if(!IsTelephone(username)){
            $("#username").focus();
            weui.Loading.error("帐号格式错误!");
            return false;
        }
        if(password=="" || password ==null){
            $("#password").focus();
            weui.Loading.error("密码不能为空！");
            return false;
        }
        if(password.length<6){
            $("#password").focus();
            weui.Loading.error("密码长度必须在6到18位之间！");
            return false;
        }
        var url ="${basepath}/front/user/checkAccount"
        weui.Loading.show();
        $.ajax({
            type:"post",
            async:"true",
             url:url,
             data:$('form').serialize(),
             success:function(data){
            if(data==true){
                var urll="${basepath}/front/user/doLogin?khid=${khid}";
                $('form').attr("action",urll);
                $('form').submit();
            }else{
                weui.Loading.error("用户名或者密码错误！");
            }
                 weui.Loading.hide();
            }
        })
    })
</script>
</html>
