<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
    <link href="${ctxStatic}/assets/register/styles/demo_style.css" rel="stylesheet" type="text/css">
    <link href="${ctxStatic}/assets/register/styles/smart_wizard.css" rel="stylesheet" type="text/css">
    <link href="${ctxStatic}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic}/assets/sweet-alert/sweet-alert.min.css" rel="stylesheet">
</head>

<div class="demoHead">
  <div>
    <div align="center">
      <h1>注册</h1>
    </div>
    <div style="clear:both;"></div>
  </div>
</div>
<form id="signupForm">
<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td>
<!-- Smart Wizard -->
  		<div id="wizard" class="swMain">
  			<ul style="margin-left: 100px">
  				<li><a href="#step-1" id="previousid">
                <label class="stepNumber"></label>
                <span class="stepDesc" style="margin-top: 10px">
                   1.设置账号信息<br />
                </span>
            </a></li>
  				<li><a href="#step-2" id="nextid">
                <label class="stepNumber"></label>
                <span class="stepDesc"    style="margin-top: 10px">
                   2.身份验证<br />
                   <small></small>
                </span>
            </a></li>
  				<li><a href="#step-3" id="finishid">
                <label class="stepNumber"></label>
                <span class="stepDesc" style="margin-top: 10px">
                   3.人工审核<br />
                   <small></small>
                </span>
             </a></li>
  			</ul>
  			<div id="step-1">
            <p>
                <div class="form-group clearfix" style="margin-top: 20px" >
                    <label class="col-lg-2 control-label required" for="username">手机号码*</label>
                    <div class="col-lg-5">
                        <input class="form-control"  id="username" name="username" maxlength="11" type="text">
                    </div>
                    <div class="col-lg-3">
                        <input  class="required form-control btn-warning" type="button" id="btn"  onclick="sendSMS(this);" value="获取验证码"/>
                    </div>
                </div>

                <div class="form-group clearfix">
                    <label class="col-lg-2 control-label " for="checkcode">短信验证码*</label>
                    <div class="col-lg-8">
                        <input class="required form-control" id="checkcode" maxlength="4" name="checkcode" type="text">
                    </div>
                </div>
                <div class="form-group clearfix">
                    <label class="col-lg-2 control-label " for="password">设置密码*</label>
                    <div class="col-lg-8">
                        <input id="password" name="password" type="text" maxlength="20" class="form-control">

                    </div>
                </div>

                <div class="form-group clearfix">
                    <label class="col-lg-2 control-label " for="confirm2">确认密码 *</label>
                    <div class="col-lg-8">
                        <input id="confirm2" name="confirm2" type="text" maxlength="20" class="form-control">
                    </div>
                </div>
                <div class="required form-group clearfix">
                    <label class="col-lg-12 control-label ">(*) 必填项</label>
                </div>
            </p>
        </div>
  			<div id="step-2">
            <p>
                <div class="form-group clearfix" style="margin-top: 20px">
                    <label class="col-lg-2 control-label" for="realname"> 真实姓名 *</label>
                    <div class="col-lg-8">
                        <input id="realname" name="realname" type="text" maxlength="64" class="required form-control">
                    </div>
                </div>
                <div class="form-group clearfix">
                    <label class="col-lg-2 control-label " for="unitname"> 单位名称 *</label>
                    <div class="col-lg-8">
                        <input id="unitname" name="unitname" type="text"  maxlength="64" class="required form-control">

                    </div>
                </div>

                <div class="form-group clearfix">
                    <label class="col-lg-2 control-label " for="telephone">座机号码 </label>
                    <div class="col-lg-8">
                        <input id="telephone" name="telephone" maxlength="12" type="text" class="form-control">
                    </div>
                </div>

                <div class="form-group clearfix">
                    <label class="col-lg-12 control-label ">(*) 必填项</label>
                </div>
            </p>
        </div>
  			<div id="step-3">
            <p>
                <div class="form-group clearfix" style="margin-top: 20px">
                    <div class="col-lg-12">
                        <ul class="list-unstyled w-list">
                            <li>验证信息提交成功！我们会尽快审核并回复。</li>
                            <li>验证通过后，您的注册手机会收到短信通知，请您及时查看。届时使用注册账号登录即可创建属于您自己的商城。</li>
                        </ul>
                    </div>
                </div>
            </p>
        </div>
  		</div>
<!-- End SmartWizard Content -->
</td></tr>
</table>
</form>
<script src="${ctxStatic}/js/jquery.js"></script>
<script type="text/javascript" src="${ctxStatic}/assets/register/js/jquery.smartWizard.js"></script>
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<script src="${ctxStatic}/assets/sweet-alert/sweet-alert.min.js"></script>
<script src="${ctxStatic}/assets/sweet-alert/sweet-alert.init.js"></script>
<script type="text/javascript" src="${ctxStatic}/assets/jquery.validate/jquery.validate.min.js"></script>
<script src="${ctxStatic}/assets/form-wizard/bootstrap-validator.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/assets/register/js/comm.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        // Smart Wizard
        $('#wizard').smartWizard();

        function onFinishCallback(){
            $('#wizard').smartWizard('showMessage','Finish Clicked');
            alert('Finish Clicked');
        }
    });
    function sendSMS(obj){
        var username = $("#username").val();
        if(username=="" || username==null){
            swal("手机号码不能为空!");
            return false;
        }
        if(!IsTelephone(username)){
            swal("手机号码错误!");
            return false;
        }
        var url="checkUser?username="+username;
        $.post(url,null,function(result){
            if(result==true){
                swal("手机号已经注册了!");
                return false;
            }else{
                var url="sendUserNum";
                var data={phone:username,tpl:"43699",code1:"46963",code2:"",code3:""};
                $.post(url,data,function(result){
                    //var json = eval("(" + jsonData + ")");
                    if(result=="000000"){
                        time(obj);
                        swal("短信发送成功!");
                    }else if(result="160040"){
                        swal("验证码超出当天发送上限!");
                    }else{
                        swal("短信发送失败!");
                    }
                });
            }
        });

    }
</script>
</html>
