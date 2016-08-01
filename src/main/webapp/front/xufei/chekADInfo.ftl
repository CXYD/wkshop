<!DOCTYPE html>
<html>
<head lang="en">
<#include "/front/header.ftl">
    <meta charset="UTF-8">
    <title>手机商城</title>
</head>
<body>

<div class="whole">
    <div id="identity-content">
      <span style="float:left; width:100%; background:#FFF;">
            <p  class="qh" name="checkTEL">固话号码验证</p>
            <p name="checkAD">宽带账号验证</p>
      </span>
        <ul>

            <li style="display: block">

                <div class="col-xs-12 ia height text-left color-666">请输入您要续费的宽带信息进行身份验证</div>

                <div class="user-end fill-top">
                    <div class="row">
                        <div class="col-xs-3 ia text-left">固话号码</div>
                        <div class="col-xs-8 ia">
                            <div class="col-xs-12 ia">
                                <input type="text" class="whole" maxlength="8" placeholder="请输入您的固话号码，无需区号">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="user-end fill-top">
                    <div class="row">
                        <div class="col-xs-3 ia text-left">机主姓名</div>
                        <div class="col-xs-8 ia">
                            <div class="col-xs-12 ia">
                                <input type="text" class="whole" maxlength="50" placeholder="请输入机主姓名">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="user-end fill-top">
                    <div class="row">
                        <div class="col-xs-3 ia text-left height">验证码</div>
                        <div class="col-xs-8 ia">
                            <div class="input-group">
                                <input type="text" class="form-control" maxlength="6" placeholder="请输入验证码">
                                <span class="input-group-addon size-13">AFGJES</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12">
                    <div class="register-content "><button type="button" class="btn btn-block fillet size-13">身份验证</button></div>
                </div>
            </li>


            <li>

                <div class="col-xs-12 ia height text-left color-666">请输入您要续费的宽带信息进行身份验证</div>

                <div class="user-end fill-top">
                    <div class="row">
                        <div class="col-xs-3 ia text-left">宽带账号</div>
                        <div class="col-xs-8 ia">
                            <div class="col-xs-12 ia">
                                <input type="text" class="whole" maxlength="15" placeholder="请输入您的宽带账号">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="user-end fill-top">
                    <div class="row">
                        <div class="col-xs-3 ia text-left">机主姓名</div>
                        <div class="col-xs-8 ia">
                            <div class="col-xs-12 ia">
                                <input type="text" class="whole" maxlength="50" placeholder="请输入机主姓名">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="user-end fill-top">
                    <div class="row">
                        <div class="col-xs-3 ia text-left height">验证码</div>
                        <div class="col-xs-8 ia">
                            <div class="input-group">
                                <input type="text" class="form-control" maxlength="6" placeholder="请输入验证码">
                                <span class="input-group-addon size-13">AFAGES</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12">
                    <div class="register-content "><button type="button" class="btn btn-block fillet size-13">身份验证</button></div>
                </div>
            </li>
        </ul>

    </div>
</div>
<script type="text/javascript">

    $('p').on('click',function(){
        $('p').removeClass('qh');

        if($(this).attr('name')=='checkAD'){
            $('#identity-content>li:eq(1)').css('display','none');
            $('#identity-content>li:eq(0)').css('display','block');
        }else{
            $('#identity-content>li:eq(0').css('display','none');
            $('#identity-content>li:eq(1)').css('display','block');
        }
    })

</script>

</body>

</html>
<#include "/front/footer.ftl">