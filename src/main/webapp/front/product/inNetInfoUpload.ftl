<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>入网资料上传</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<body>


<div class="whole size-13">




    <div class="newly-added">


        <ul>


            <div class="row col-xs-12">

                <form class="form-horizontal">
                    <fieldset>
                        <div class="control-group">
                            <!-- Text input-->
                            <label class="control-label col-xs-4" >机主姓名</label>
                            <div class="controls col-xs-8">
                                <input type="text" placeholder="" maxlength="50" value="" id="name" class=" fill-top">
                            </div>
                        </div>

                        <div class="control-group">

                            <!-- Text input-->
                            <label class="control-label col-xs-4" for="input01">身份证号</label>
                            <div class="controls col-xs-8">
                                <input type="text" placeholder="" id="useid" maxlength="18" value="" class=" fill-top">
                            </div>
                        </div>

                        <div class="control-group">

                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="input01">上传照片</label>
                            <div class="controls col-xs-9">
                            <div class="col-xs-4 ia">

                                <button type="button" class="btn btn-default pull-right size-13"  id="btnToUPload">
                                    <#--<img src="${basepath}/static/img/front/wscqt6-8-29.jpg" width="20">-->
                                    &nbsp;立即上传</button>
                            </div>
                            <div class="col-xs-5 ia">
                                <button type="button" class="btn btn-default pull-right size-13" id="btnLaterUpload">稍后上传</button>
                            </div>
                            </div>
                        </div>

                    </fieldset>
                </form>


            </div>

            <#--<li class="register-content">-->
                <#--<div class="user-end">-->
                    <#--<div class="row">-->
                        <#--<div class="col-xs-3 ia"><span class="color-red">*</span>机主姓名：</div>-->
                        <#--<div class="col-xs-8 ia">-->
                            <#--<div class="col-xs-12 ia">-->
                                <#--<input type="text" id="username" class="whole" style="border: 0px" maxlength="50" placeholder="请输入机主姓名">-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</li>-->
            <#--<li class="register-content">-->
                <#--<div class="user-end">-->
                    <#--<div class="row">-->
                        <#--<div class="col-xs-3 ia"><span class="color-red">*</span>身份证号：</div>-->
                        <#--<div class="col-xs-8 ia">-->
                            <#--<div class="col-xs-12 ia">-->
                                <#--<input type="text" id="userid" class="whole" style="border: 0px"   maxlength="18" placeholder="请输入身份证号">-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</li>-->
            <#--<li class="register-content">-->
                <#--<div class="user-end">-->
                    <#--<div class="row">-->
                        <#--<div class="col-xs-3 ia"><span class="color-red">*</span>证件照片：</div>-->
                        <#--<div class="col-xs-8 ia">-->
                            <#--<div class="col-xs-6 ia">-->

                                <#--<button type="button" class="btn btn-default pull-right size-13"  id="btnToUPload"><img src="${basepath}/static/img/front/wscqt6-8-29.jpg" width="20">&nbsp;立即上传</button>-->
                            <#--</div>-->
                            <#--<div class="col-xs-6 ia">-->
                                <#--<button type="button" class="btn btn-default pull-right size-13" id="btnLaterUpload">稍后上传</button>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</li>-->
            <p class="register-content register"><button type="button" id="toNext"  class="btn btn-block org-btn" style="background-color: #f60;color: white">下一步</button></p>
        </ul>
        <div class="data-explain">
            根据国家工信部<a href="#">《电话用户真实身份信息登记规定》（工业和信息化部令25号）</a>要求，用户在我司网上营业厅办理电话开户过户等入网手续需进行实名制登记。中国联通将保证此<span class="color-f60">身份证照片</span>仅用于本次入网使用。</div>

        <div class="data-explain">
            在您签收时，请入网人持本人<span class="color-f60">身份证件原件</span>签收，严禁代收，请您提前准备好<span class="color-f60">复印件</span>，并可在<span class="color-f60">复印件</span>上标注"仅限中国联通入网使用"字样及日期，中国联通将保证此<span class="color-f60">复印件</span>仅用于本次入网使用。</div>
        </ul>
    </div>
</div>
</div>
</body>
<#include "/front/footer.ftl">

<script>
    var inNetInfo={};
        $(document).ready(function(){
            var state = sessionStorage.getItem("uploadState");
            if(state!=undefined && state==1){
                $('#btnToUPload').text('上传成功');
            }else{
                $('#btnToUPload').text('立即上传');
            }
            var info = sessionStorage.getItem("inNetInfo");
            if(info!=undefined){
                if(info.username!=undefined){
                    $('#username').val(info.username);
                }
                if(info.userid!=undefined){
                    $('#userid').val(info.userid);
                }
            }
        })


    //稍后上传
    $('#btnLaterUpload').on('click',function(){
        if($('#username').val()!='' && $('#userid').val()!=""){
            inNetInfo.username=$('#username').val();
            inNetInfo.userid = $('#userid').val()
            sessionStorage.setItem('inNetInfo',JSON.stringify(inNetInfo))
//            $.cookie('inNetInfo',JSON.stringify(inNetInfo));
            window.location.href='goodsPreSubmitSave';
        }else{
            weui.Loading.info("请填写用户信息！")

        }
    })
    //立即上传
    $('#btnToUPload').on('click',function(){
        inNetInfo.username=$('#username').val();
        inNetInfo.userid = $('#userid').val()
        sessionStorage.setItem('inNetInfo',JSON.stringify(inNetInfo))
        location.href='toUplaoduserIDImg';
//        window.open('toUplaoduserIDImg');
    })

    $('#toNext').on('click',function(){
        if($('#userid').val().length<18){
            weui.Loading.info("身份证位数不够！")
            return ;
        }

        var imgInfo = sessionStorage.getItem('userID_IMG');
        if($('#username').val()!='' && $('#userid').val()!=""&& imgInfo!=undefined && imgInfo!='null' ){
            inNetInfo.username=$('#username').val();
            inNetInfo.userid = $('#userid').val()
            sessionStorage.setItem('inNetInfo',JSON.stringify(inNetInfo))
//            $.cookie('inNetInfo',JSON.stringify(inNetInfo));
//            $.cookie('userID_IMG',sessionStorage.getItem('userID_IMG'),{expires:1,path:'/wkshop'})
                window.location.href='goodsPreSubmitSave';
        }else{
            weui.Loading.info("确认用户信息填写完整！")
        }
    })

    function checkInputInfo(){
        var imgInfo = {};
        try {
            imgInfo = $.parseJSON(sessionStorage.getItem('userID_IMG'));
        }catch(e){
            console.err(e);
        }


    }

</script>
</html>
