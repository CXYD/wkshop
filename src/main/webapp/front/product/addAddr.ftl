<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl"/>
    <title>新增收货地址</title>
</head>

<body>
<div class="whole">
    <div class="newly-added">
        <ul>
            <li class="register-content">
                <div class="user-body">
                    <div class="row">
                        <div class="col-xs-3 ia"><span class="color-red">*</span>收货人：</div>
                        <div class="col-xs-9 ia">
                            <div class="col-xs-12 ia">
                                <input type="text" id="cname" class="dxld-log" maxlength="50" onkeyup="checkInput()" placeholder="请输入姓名">
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
                                <input type="text" class="dxld-log" id='cphone' maxlength="11" onkeyup="checkInput()"  placeholder="请输入您的手机号码">
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
                                <textarea name="textarea" id="caddr" placeholder="请输入您的详细地址" onkeyup="checkInput()"  class="yhtdsia" maxlength="200" onpropertychange="this.style.height = this.scrollHeight + 'px';" oninput="this.style.height = this.scrollHeight + 'px';"></textarea>

                            </div>
                        </div>
                    </div>
                </div>
                <p class="register-content "><button type="button" disabled onclick="checkAddForm()" class="btn btn-block" style="height:40px;">保存</button></p>
            </li>



        </ul>
    </div>
</div>


<#include "/front/footer.ftl"/>

<script>
    //点击保存后，放到cookie中，再调转回预提交页面
    function checkAddForm(){
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if($('#cname').val()==""){
            weui.Loading.error('收货人姓名不能为空')
        }else if($('#cphone').val()=="" || $('#cphone').val().length<11||!myreg.test($("#cphone").val())){
            weui.Loading.error('收货人电话格式不正确')
        }else if($('#caddr').val()==''){
            weui.Loading.error('收货人地址不能为空')
        }else{
            var receiveInfo ={
                name: $('#cname').val(),
                phone:$('#cphone').val(),
                addr:$('#caddr').val()
            }
            sessionStorage.setItem('receiveInfo',JSON.stringify(receiveInfo))
//            $.cookie('receiveInfo',JSON.stringify(receiveInfo),{expires:1,path:"/wkshop"})
            window.history.back();
        }
    }



    function checkInput(){
        var dis = $('#cname').val()==''||$('#cphone').val()==''||$('#caddr').val()=='';
        if(!dis){
            $('button').addClass('org-btn');
            $('button').attr('disabled',false)
        }else{
            $('button').removeClass('org-btn');
            $('button').attr('disabled',true)
        }
    }

</script>
</body>
</html>
