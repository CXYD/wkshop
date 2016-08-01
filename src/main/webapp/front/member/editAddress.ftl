<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>编辑地址</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<body>
<div class="whole">
    <div class="newly-added">
        <form method="post">
            <ul>
                <input type="hidden" name="id" id="id" value="${userAddress.id}"/>
                <li class="register-content">
                    <div class="user-body">
                        <div class="row">
                            <div class="col-xs-3 ia"><span class="color-red">*</span>收货人：</div>
                            <div class="col-xs-9 ia">
                                <div class="col-xs-12 ia">
                                    <input type="text" id="consigneename" name="consigneename"  class="dxld-log" maxlength="50" placeholder="请输入姓名" value="${userAddress.consigneename}">
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="register-content">
                    <div class="user-body">
                        <div class="row">
                            <div class="col-xs-3 ia"><span class="color-red">*</span>手机号码：</div>
                            <div class="col-xs-9 ia">
                                <div class="col-xs-12 ia">
                                    <input type="text" id="phone"  name="phone" class="dxld-log" maxlength="11" placeholder="请输入您的手机号码" value="${userAddress.phone}">
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="register-content">
                    <div class="user-body">
                        <div class="row">
                            <div class="col-xs-3 ia"><span class="color-red">*</span>详细地址：</div>
                            <div class="col-xs-9 ia">
                                <div class="col-xs-12 ia">
                                    <textarea id="address" name="address" placeholder="请输入您的详细地址"  class="yhtdsia" maxlength="200" onpropertychange="this.style.height = this.scrollHeight + 'px';" oninput="this.style.height = this.scrollHeight + 'px';">${userAddress.address}</textarea>

                                </div>
                            </div>
                        </div>
                    </div>
                    <p class="register-content "><button  type="button"  class="btn btn-block  size-13">保存</button></p>
                </li>
            </ul>
        </form>
    </div>
</div>
</body>
<#include "/front/footer.ftl">
<script>
    $("button").click(function(){
        var consigneename=  $.trim($('#consigneename').val());
        var phone =  $.trim($('#phone').val());
        var address = $.trim($('#address').val());
        if(consigneename=="" || consigneename==null){
            $('#consigneename').focus();
            weui.Loading.error("收货人姓名不能为空！");
            return false;
        }
        if(phone=="" || phone==null){
            $('#phone').focus();
            weui.Loading.error("手机号码不能为空！");
            return false;
        }
        if(!IsTelephone(phone)){
            $('#phone').focus();
            weui.Loading.error("手机号码格式错误！");
            return false;
        }
        if(address=="" || address==null){
            $('#address').focus();
            weui.Loading.error("详细地址不能为空！");
            return false;
        }
        var url='${basepath}/front/mine/editAddress';
        $.ajax({
            type:"post",
            async:true,
            url:url,
            data:$('form').serialize(),
            success:function(result){
                if(result==true){
                    window.location.href="toAddress?name="+${name};
                }else{
                    weui.Loading.error("修改失败！");
                    return false;
                }
            }
        })
    })
    $('textarea[name="address"]').keyup(function(){
        $("button").addClass("syiaq");
    })

</script>
</html>
