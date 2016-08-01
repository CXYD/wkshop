
<!DOCTYPE HTML>
<html>
<head>
    <#include "/front/header.ftl">
    <title>老号码校验</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<body>

<div class="register">
    <ul>
        <li class="register-content">
            <input type="tel" min="13000000000"  class="form-control" maxlength="11" id="phone" onkeyup="checkInput()" placeholder="请输入您的手机号码">
        </li>

        <li class="register-content">
            <div class="input-group">
                <input type="tel" class="form-control" maxlength="4" placeholder="请输入您的短信验证码" onkeyup="checkInput()" id="checkcode">
                <span class="input-group-addon size-13 "  id="sendCode" >发送验证码</span>
                <span class="input-group-addon size-13 hide " id="reSendCode">重发验证码(<span id="second">90</span>)</span>
            </div>
        </li>
        <li class="register-content  "><button type="button" disabled class="btn btn-block gray-btn" id="btnCheckPhone" style="height: 40px">提交校验</button></li>
    </ul>

</div>
</body>
<#include "/front/footer.ftl">
<script>

    $('#btnCheckPhone').on('click',function(){

        checkrCode();

    })


    function checkInput(){
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        var dis = $('#phone').val()==''||$('#checkcode').val()==''||!myreg.test($("#phone").val());

        console.log(dis);
        if(!dis){
            $('button').removeClass('gray-btn').addClass('org-btn');
            $('button').attr('disabled',false)
        }else{
            $('button').removeClass('org-btn').addClass('gray-btn');
            $('button').attr('disabled',true)
        }

        if(myreg.test($("#phone").val())){
            $('#sendCode').addClass('btn-success');
            $('#sendCode').bind('click',sendMSN);
        }else{

            $('#sendCode').unbind('click',sendMSN)
        }
    }



    function checkUserPhone(){
        weui.Loading.show();
        $.ajax({
            url:"${basepath}/product/checkNumByID",
            data:{phone:$('#phone').val(),numID:'${oldNumID!""}'},
            type:"POST",
            success:function(d){
                weui.Loading.hide();
                if(d=='ok'){
                    sessionStorage.setItem('oldNum',$('#phone').val())
                    location.href='checkOldNumSucc?phone='+$('#phone').val();
                }else{
                    weui.Loading.error('常抱歉，您的手机号码没有校验通过！');
                }
            }
        })
    }

    function sendMSN() {
        console.log('发送');
        $(this).addClass('hide')
        $('#reSendCode').removeClass('hide')
        weui.Loading.show();
        $.ajax({
            url:"/wkshop/sms/sendCode",
            data:{phone:$('#phone').val()},
            type:"post",
            asysn:false,
            success:function(data){
                weui.Loading.hide();
                if(data.statusCode!='000000'){
                    weui.Loading.error(data.statusMsg);
                }else{
                    weui.Loading.info('验证码发送成功！');
                    geticode(90);
                }
            },
            error:function(){
                weui.Loading.hide();
                weui.Loading.error('验证码发送失败！');
            }
        })


    }

    function checkrCode(){
        $.ajax({
            url:"/wkshop/sms/checkRandCode",
            data:{randCode:$('#checkcode').val()},
            type:"post",
            success:function(data) {

                if (data) {
                    checkUserPhone();
                } else {
                    weui.Loading.error('验证码错误！');
                }
            }
        })

    }

    function geticode(secs){
        $('#second').text(secs);
        if(--secs>0){
            setTimeout("geticode("+secs+")",1000);
        }
        else{
            $("#sendCode").text("点击重发");
            $("#sendCode").removeClass('hide')
            $('#reSendCode').addClass('hide');
        }
    }

</script>
</html>
