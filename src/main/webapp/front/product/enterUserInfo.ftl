
<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>用户填写信息</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<body>
<div class="whole">
    <div class="newly-added">
        <ul>
            <li class="register-content hide">
                <div class="user-body">
                    <div class="row">
                        <div class="col-xs-3 ia"><span class="color-red">*</span>机主姓名：</div>
                        <div class="col-xs-9 ia">
                            <div class="col-xs-12 ia">
                                <input type="text" class="dxld-log" maxlength="50" onkeyup="checkInput()" value="" id="username" placeholder="请输入机主姓名">
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="register-content hide">
                <div class="user-body">
                    <div class="row">
                        <div class="col-xs-3 ia"><span class="color-red">*</span>联系电话：</div>
                        <div class="col-xs-9 ia">
                            <div class="col-xs-12 ia">
                                <input type="text" class="dxld-log" maxlength="11" onkeyup="checkInput()" value="" id="usertel" placeholder="请输入您的手机号码">
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="register-content">
                <div class="user-body">
                    <div class="row">
                        <div class="col-xs-3 ia"><span class="color-red">*</span>身份证号：</div>
                        <div class="col-xs-9 ia">
                            <div class="col-xs-12 ia">
                                <input type="text" class="dxld-log" maxlength="18" onkeyup="checkInput()" value="" id="userid" placeholder="请输入您的18位身份证号">
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
                                <textarea name="textarea" id="useraddr"   placeholder="请输入您的详细地址" onkeyup="checkInput()" class="yhtdsia" maxlength="200" onpropertychange="this.style.height = this.scrollHeight + 'px';" oninput="this.style.height = this.scrollHeight + 'px';"></textarea>
                            </div>
                        </div>
                    </div>
                </div>

            </li>
            <p class="register-content "><button type="button" disabled class="btn btn-block gray-btn" style="height: 40px" id="btnEnterUserInfo">下一步</button></p>
        </ul>
    </div>
</div>
</body>
<#include "/front/footer.ftl">

<script>
    function checkInput(){
        var dis = true;
        $('li').not(".hide").find('input').each(function(){
            dis =$(this).val()=="";
        })

        $('li').not(".hide").find('textarea').each(function(){
            dis =$(this).val()=="";
        })


        if(!dis){
            $('button').removeClass('gray-btn').addClass('org-btn');
            $('button').attr('disabled',false);
        }else{
            $('button').removeClass('org-btn').addClass('gray-btn');
            $('button').attr('disabled',true);
        }
    }

    $('button').on('click',function(){
        if($('#userid').val().length!=18){
            weui.Loading.error('身份证位数不正确!')
            return ;
        }else{
            var newUserInfo = {};
            newUserInfo.name=$('#username').val();
            newUserInfo.tel=$('#usertel').val();
            newUserInfo.ID = $('#userid').val()
            newUserInfo.addr=$('#useraddr').val();

            //localStorage.setItem('newKDuserInfo',JSON.stringify(newUserInfo));
            sessionStorage.setItem('newKDuserInfo',JSON.stringify(newUserInfo));

            location.href='enterUserInfo';
        }

    })

</script>
</html>
