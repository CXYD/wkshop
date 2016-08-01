<!DOCTYPE HTML>
<html>
<head>
    <link href="${basepath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basepath}/static/assets/sweet-alert/sweet-alert.min.css" />

    <title>忘记密码</title>
</head>
<form name="form" action="" method="post">
    <table class="table table-bordered" >
        <tr>
            <td colspan="2" style="background-color: #dff0d8;text-align: center;">
                <strong>忘记密码</strong>
            </td>
        </tr>
        <tr style="display:none;">
            <th>id</th>
            <td><input type="hidden" value="" name="id"></td>
        </tr>

        <tr >
            <th class="col-sm-5" style="text-align: right;">手机号码：</th>
            <td>
                <div class="input-group col-sm-12"><input class="form-control" style="width: 22%" type="text"    maxlength="11" id="username" name="username" placeholder="请填写注册手机号码">
                    <input class="form-control btn-sm" style="width: 12%" type="button"  onclick="sendSMS(this)" value="获取验证码" ></input></div></td>
        </tr>
        <tr>
            <th class="col-sm-5" style="text-align: right;">短信验证码：</th>
            <td><input class="col-sm-4" id="checkcode" maxlength="4" name="checkcode"  placeholder="请输入短信验证码"/></td>
        </tr>
        <tr>
            <th class="col-sm-5" style="text-align: right;">设置密码：</th>
            <td style="text-align: left;"><input class="col-sm-4" type="password"  maxlength="20" id="password" name="password" placeholder="英文+数字，至少6位，不区分大小写"/></td>
        </tr>
        <tr>
            <th class="col-sm-5" style="text-align: right;">确认新密码：</th>
            <td style="text-align: left;"><input class="col-sm-4" type="password" maxlength="20"  id="confirmPassword"  name="confirmPassword"  placeholder="再次输入密码"/></td>
        </tr>
        <tr>
            <td  colspan="2" style="text-align: center;">
                <div  onclick="updateChangePwd()" class="btn btn-success">
                    <i class="icon-ok icon-white"></i> 提交
                </div>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript" src="${basepath}/static/assets/register/js/comm.js"></script>
<script src="${basepath}/static/js/jquery.js"></script>
<script type="text/javascript" src="${basepath}/static/assets/sweet-alert/sweet-alert.min.js"></script>

<script>
    $(function() {
        $("#username").focus();
    });
    function updateChangePwd(){
        var  username = $.trim($("#username").val());
        var  password = $.trim($("#password").val());
        var checkcode = $.trim($("#checkcode").val())
        if (username == "") {
            $("#username").focus();
            swal("手机号码不能为空!");
            return false;
        }
        if(!IsTelephone(username)){
            $("#username").focus();
            swal("手机号码错误!");
            return false;
        }
        if (checkcode == "") {
            $("#checkcode").focus();
            swal("短信验证码不能为空!");
            return false;
        }
        if (password == "") {
            $("#password").focus();
            swal("密码不能为空!");
            return false;
        }
        if(password.length<6){
            $("#password").focus();
            swal("密码至少大于等于6位");
            return false;
        }
        if(!IsPassword(password)){
            $("#password").focus();
            swal("密码只能由数字和字母组成!");
            return false;
        }
        if ($.trim($("#confirmPassword").val()) == "") {
            $("#confirmPassword").focus();
            swal("确认新密码不能为空!");
            return false;
        }
        if ($.trim($("#password").val()) != $.trim($("#confirmPassword").val())) {
            swal("两次输入的密码不一致!");
            return false;
        }
        var url="${basepath}/manage/user/checkUserNum?num="+checkcode;
        $.post(url,null,function(result){
            if(result!="验证码正确"){
                swal(result);
                return false;
            }else{
                //验证码正确且没有失效
                $.ajax({
                    url: '${basepath}/manage/user/updateChangePwd',
                    type: 'POST',
                    dataType: 'text',
                    data: $('form').serialize(),
                    success:function(data){
                        //alert(data)
                        var flag = eval("("+data+")")
                        if(flag==true){
                            $('form')[0].reset();
                            swal("提示!", "操作成功.", "success");
                        }else{
                            swal("提示!", "操作失败.", "error");

                        }

                    }
                })
            }
        });

    }
    function sendSMS(obj){
        var phone = $("#username").val();
        if(phone==""){
            $("#username").focus();
            swal('手机号码不能为空!');
            return false;
        }
        if(!IsTelephone(phone)){
            $("#username").focus();
            swal('手机号码错误!');
            return false;
        }
        var url="${basepath}/manage/user/sendUserNum";
        var data={phone:phone,tpl:"43699",code1:"46963",code2:"",code3:""};
        $.post(url,data,function(result){
            if(result.statusCode=="000000"){
                time(obj);
                swal("短信发送成功!");
            }else if(result.statusCode=="160040"){
                swal("验证码超出当天发送上限!");
            }else{
                swal("短信发送失败!");
            }
        });
    }
</script>
