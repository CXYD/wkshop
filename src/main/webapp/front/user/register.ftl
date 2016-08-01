<!DOCTYPE html>
<html>
<head>
    <#include "/front/header.ftl">
    <title>注册</title>
</head>

<body>

<div class="register">
    <form>
    <ul>
        <li class="register-content">
            <input id="username" name="username" maxlength="11" type="tel" class="form-control" placeholder="请输入注册手机号码">
        </li>

        <li class="register-content">
            <div id="div_num" class="input-group col-sm-12" style="width: 100%;" >
                <input id="khid" type="hidden" value="">
                <input id="code" type="tel" style="width: 65%" maxlength="4" class="form-control" placeholder="请输入您短信收到的验证码">
                <input class="btn form-control" style="width: 35%;" id="btncode" type="button"  onclick="sendSMS(this)" value="获取验证码" ></input>
            </div>
        </li>
        <li class="register-content">
            <input id="password" name="password" type="text" maxlength="18" class="form-control" placeholder="请输入密码" minlength="6">
        </li>
        <li class="register-content "><button id="register" type="button" class="btn btn-block fillet size-13">立即注册</button></li>
    </ul>
    </form>
</div>

</body>
<#include "/front/footer.ftl">
</html>
<script type="text/javascript">

    $("#register").click(function () {
        var username = $.trim($("#username").val());
        var checkcode = $.trim($("#code").val());
        var password = $.trim($("#password").val());
        if (username == "") {
            $("#username").focus();
            weui.Loading.error("手机号码不能为空!");
            return false;
        }
        if(!IsTelephone(username)){
            $("#username").focus();
            weui.Loading.error("手机号码错误!");
            return false;
        }
        if (checkcode == "") {
            $("#checkcode").focus();
            weui.Loading.error("短信验证码不能为空!");
            return false;
        }
        if (password == "") {
            $("#password").focus();
            weui.Loading.error("密码不能为空!");
            return false;
        }
        if(password.length<6){
            $("#password").focus();
            weui.Loading.error("密码长度必须在6到18位之间！");
            return false;
        }
        if(!IsPassword(password)){
            $("#password").focus();
            weui.Loading.error("密码只能由数字和字母组成!");
            return false;
        }
        var url="${basepath}/manage/user/checkUserNum";
        var data={num:checkcode};
        $.post(url,data,function(result){
            if(result!="验证码正确"){
                weui.Loading.info(result);
            }else{
                var formurl="${basepath}/register/addRegUser";
                $.ajax({
                    type:  "post",
                    async:true,
                    url:formurl,
                    data:$('form').serialize(),
                    success:function(data){
                        if(data==true){
                            weui.Loading.info("注册成功！");
                            window.location.href="${basepath}/front/user/login?khid=${khid}";
                        }else{
                            weui.Loading.error("注册失败！");
                        }
                    }
                })
            }
        })

    })
    function sendSMS(obj){
        var username = $("#username").val();
        if(username=="" || username==null){
            weui.Loading.error("手机号码不能为空!");
            return false;
        }
        if(!IsTelephone(username)){
            weui.Loading.error("手机号码格式错误!");
            return false;
        }
        var url="${basepath}/register/checkUserByName?username="+username;
        $.post(url,null,function(result){
            if(result==true){
                weui.Loading.error("手机号已经注册了!");
                return false;
            }else{
                var url="${basepath}/manage/user/sendUserNum";
                var data={phone:username,tpl:"43699",code1:"46963",code2:"",code3:""};
                weui.Loading.show();
                $.post(url,data,function(result){
                    //var json = eval("(" + jsonData + ")");
                    if(result.statusCode=="000000"){
                        time(obj);
                        weui.Loading.info("短信发送成功!");
                    }else if(result.statusCode="160040"){
                        weui.Loading.error("验证码超出当天发送上限!");
                    }else{
                        weui.Loading.error("短信发送失败!");
                    }
                    weui.Loading.hide();
                });
            }
        });
    }
    $("input[name='username']").keyup(function(){
        $("#btncode").addClass("syiaq");
    });
    $("input[name='password']").keyup(function(){
        var username = $.trim($("#username").val());
        var checkcode = $.trim($("#code").val());
        var password = $.trim($("#password").val());
        if(username!="" || checkcode!="" || password!=""){
            $('#register').removeClass("fillet").addClass("syiaq");
        }
    });
</script>
