<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>订单查询</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<body>
<form >
<div class="register">
	<ul>
        <li class="register-content">
        	<input type="text" class="form-control" maxlength="11" id="contractmobile"  name="contractmobile" placeholder="请输入收货人的手机号码">
      </li>
        <li style="margin-top:1em; width:100%; float:left;">
        <div class="input-group col-sm-12">
         <input type="text"  style="width: 65%" class="form-control" maxlength="4" id="code" name="code" placeholder="请输入您的短信验证码">
         <input id="spsms"   style="width: 35%"  type="button" class="btn form-control" onclick="sendSMS(this)" value="发送验证码"></input>
        </div>
        </li>
        <li class="register-content" style="background-color: #eeeeee"><lable   id="search"  class="btn btn-block  size-13">立即查询</lable></li>
    </ul>
</div>
</form>
</body>
<#include "/front/footer.ftl">
</html>
<script>
    $("lable").click(function(){
        var phone = $("#contractmobile").val();
        var code = $("#code").val();
        if(phone==""){
            weui.Loading.error('收货人手机号码不能为空！');
            return false;
        }
        if(code==""){
            weui.Loading.error('短信验证码不能为空！');
            return false;
        }
        if(!IsTelephone(phone)){
            weui.Loading.error('提示','手机号码错误！');
            return false;
        }

        var url="${basepath}/manage/user/checkUserNum?num="+code;
        $.post(url,null,function(result){
            if(result!="验证码正确"){
                weui.Loading.error(result);
                return false;
            }else{
                $("#search").addClass("syiaq");
                window.location.href="searchOrder?consignephone="+phone;
            }
        });
    })

    function sendSMS(obj){
        var phone = $("#contractmobile").val();
        if(phone==""){
            weui.Loading.error('收货人手机号码不能为空！');
            return false;
        }
        if(!IsTelephone(phone)){
            weui.Loading.error('手机号码错误！');
            return false;
        }
        var url="${basepath}/manage/user/sendUserNum";
        var data={phone:phone,tpl:"43699",code1:"46963",code2:"",code3:""};
        $.post(url,data,function(result){
            //alert(result.statusCode);
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
    $("input[name='contractmobile']").keyup(function(){
        $("#spsms").addClass("syiaq");
    });
    $("input[name='code']").keyup(function(){
        $("#search").addClass("syiaq");
    });
</script>
