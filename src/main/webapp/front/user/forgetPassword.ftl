<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>找回密码</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<body>
<form method="post">
<div class="register">
	<ul>
        <li class="register-content">
        	<input type="text" class="form-control" id="username" name="username" maxlength="11"  placeholder="请输入注册手机号码">
      </li>
        
        <li class="register-content">
        <div class="input-group col-sm-12">
         <input type="text" class="form-control" style="width: 65%"  id="code" name="code" maxlength="4" placeholder="请输入您的短信验证码">
            <input class="btn form-control" id="btncode" style="width: 35%;" type="button"  onclick="sendSMS(this)" value="获取验证码" ></input></td>
        </div>
        </li>
        <li class="register-content">
        	<input type="text" class="form-control" id="password" name="password" maxlength="18" placeholder="请输入新密码">
      </li>
        <li class="register-content">
        	<input type="text" class="form-control" id="confirmPassword" name="confirmPassword" maxlength="18" placeholder="确认密码">
      </li>
        <li class="register-content"><button name="submit"  type="button" class="btn btn-block size-13">提交</button></li>
    </ul>
    
</div>
</form>
</body>
<#include "/front/footer.ftl">
<script>
    $('button[name="submit"]').click(function(){
        var username = $.trim($("#username").val());
        var checkcode = $.trim($("#code").val());
        var password = $.trim($("#password").val());
        var confirmPassword = $.trim($("#confirmpassword").val());
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
        if ($.trim($("#confirmPassword").val()) == "") {
            $("#confirmPassword").focus();
            weui.Loading.error("确认新密码不能为空!");
            return false;
        }
        if ($.trim($("#password").val()) != $.trim($("#confirmPassword").val())) {
            weui.Loading.error("两次输入的密码不一致!");
            return false;
        }
        var url="${basepath}/manage/user/checkUserNum?num="+checkcode;
        weui.Loading.show();
        $.post(url,null,function(result){
            if(result!="验证码正确"){
                weui.Loading.info(result);
                return false;
            }else{
                //验证码正确且没有失效
                $.ajax({
                    url: '${basepath}/member/updateMemberPwd',
                    type: 'POST',
                    dataType: 'text',
                    data: $('form').serialize(),
                    success:function(data){
                        //alert(data)
                        var flag = eval("("+data+")")
                        if(flag==true){
                            weui.Loading.info("操作成功！");
                            window.location.href="${basepath}/front/user/login/?khid=${khid}";
                        }else{
                            weui.Loading.error("操作失败！");

                        }

                    }
                })
            }
        });
        weui.Loading.show();
    })

    function sendSMS(obj){
        var phone = $.trim($("#username").val());
        if(phone==""){
            $("#username").focus();
            weui.Loading.error('手机号码不能为空!');
            return false;
        }
        if(!IsTelephone(phone)){
            $("#username").focus();
            weui.Loading.error('手机号码错误!');
            return false;
        }
        var url="${basepath}/manage/user/sendUserNum";
        var data={phone:phone,tpl:"43699",code1:"46963",code2:"",code3:""};
        $.post(url,data,function(result){
            if(result.statusCode=="000000"){
                time(obj);
                weui.Loading.info("短信发送成功!");
            }else if(result.statusCode=="160040"){
                weui.Loading.error("验证码超出当天发送上限!");
            }else{
                weui.Loading.error("短信发送失败!");
            }
        });
    }
    $("input[name='username']").keyup(function(){
        $("#btncode").addClass("syiaq");
    });

    $("input[name='confirmPassword']").keyup(function(){
        var phone = $.trim($("#username").val());
        var checkcode = $.trim($("#code").val());
        var password = $.trim($("#password").val());
        var confirmPassword = $.trim($("#confirmpassword").val());
        if(phone!="" || checkcode!="" || password!="" || confirmPassword!=""){
            $('button[name="submit"]').addClass("syiaq");
        }
    });
</script>
</html>
