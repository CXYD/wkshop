<!DOCTYPE html>
<html>
<head lang="en">
<#include "/front/header.ftl">
    <meta charset="UTF-8">
    <title>我的</title>
</head>
<body>

<div class="wraper" name="content">

    <div class="whole">
        <a onclick="noLogin()"><img src="${basepath}/static/img/front/wscqt6-8-5.jpg" width="100%"></a>
        <div class="Mall-Specifications">
            <div class="user-body">
                <div class="element-body iqaa">
                    <div class="row" onclick="preSearch();">
                        <div class="col-xs-1 ia" >
                            <img src="${basepath}/static/img/front/wscqt6-8-6.jpg" width="20">
                        </div>
                        <div class="col-xs-10 ia inner">我的订单</div>
                    </div>
                </div>
                <div class="element-end">
                    <div class="row">
                        <div class="col-xs-4 color-666 text-center">
                            <img src="${basepath}/static/img/front/wscqt6-8-7.jpg" width="40" onclick="preSearch()"></div>
                        <div class="col-xs-4 ia color-666 text-center" >
                            <img src="${basepath}/static/img/front/wscqt6-8-8.jpg" width="40" onclick="preSearch()"></div>
                        <div class="col-xs-4 color-666 text-center">
                            <img src="${basepath}/static/img/front/wscqt6-8-9.jpg" width="40" onclick="preSearch()">
                        </div>
                    </div>
                </div>
                <div class="element-end">
                    <div class="row">
                        <div class="col-xs-4 color-666 text-center">
                            <span onclick="preSearch()">待付款</span>
                        </div>
                        <div class="col-xs-4 color-666 text-center">
                            <span onclick="preSearch()">待办理</span>
                        </div>
                        <div class="col-xs-4 color-666 text-center">
                            <span onclick="preSearch()">已退款</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="Mall-Specifications">
            <div class="user-body">
                <div class="element-end iqaa">
                    <div class="row" onclick="noLogin();">
                        <div class="col-xs-1 ia"><img src="${basepath}/static/img/front/wscqt6-8-10.jpg" width="20" "></div>
                        <div class="col-xs-10 ia inner" name="address">地址管理</div>
                    </div>
                </div>


            </div>
        </div>
</div>

<div class="quick-nav" name="footer">
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/homePage?khid=${khid}"><img src="${basepath}/static/img/front/wscqt6-8-46.png" width="25"></a> </div>
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/classPage?khid=${khid}"><img  src="${basepath}/static/img/front/wscqt6-8-47.png" width="25"></a> </div>
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/findPage?khid=${khid}"><img src="${basepath}/static/img/front/wscqt6-8-48.png" width="25"></a> </div>
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/myPage?khid=${khid}"><img src="${basepath}/static/img/front/wscqt6-8-51.png" width="25"></a> </div>
</div>

</body>

</html>
<#include "/front/footer.ftl">
<script>
    var nologinurl='${basepath}/front/user/login?khid=${khid}';
    function noLogin(){
        window.location.href=nologinurl;
    }
    function preSearch(){
        weui.confirmLogin("您还没有登录，您可以通过以下方法查看订单：","提示",function(r){
            if(r==true){
                window.location.href=nologinurl;
            }else{
                window.location.href="${basepath}/front/order/toSearchOrder";
            }
        });
    }
</script>


