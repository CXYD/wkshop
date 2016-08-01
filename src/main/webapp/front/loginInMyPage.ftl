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
            <div class="Sign-user" onclick="accountManage();">
                <p class="Sign-user"><img src="${basepath}/static/img/front/wscqt6-8-11.jpg" width="100%"></p>
                <p class="Sign-name">
                <#if nickname??>
                ${(nickname)!''}
                <#else>
                ${(username)!''}
                </#if>
               欢迎来到${shopname}！</p>
            </div>
        <div class="Mall-Specifications">
            <div class="user-body">
                <div class="element-body iqaa">
                    <div class="row" onclick="serachOrder('');">
                        <div class="col-xs-1 ia">
                            <img src="${basepath}/static/img/front/wscqt6-8-6.jpg" width="20">
                        </div>
                        <div class="col-xs-10 ia inner">我的订单</div>
                    </div>
                </div>
                <div class="element-end">
                    <div class="row">
                        <div class="col-xs-4 color-666 text-center" onclick="serachOrder('03');">
                            <img src="${basepath}/static/img/front/wscqt6-8-7.jpg" width="40"></div>
                        <div class="col-xs-4 ia color-666 text-center" onclick="serachOrder('04');">
                            <img src="${basepath}/static/img/front/wscqt6-8-8.jpg" width="40"></div>
                        <div class="col-xs-4 color-666 text-center" onclick="serachOrder('07');">
                            <img src="${basepath}/static/img/front/wscqt6-8-9.jpg" width="40">
                        </div>
                    </div>
                </div>
                <div class="element-end">
                    <div class="row">
                        <div class="col-xs-4 color-666 text-center" onclick="serachOrder('03');">
                            待付款<span style="color: red">(${noPaySum!''})</span>
                        </div>
                        <div class="col-xs-4 color-666 text-center" onclick="serachOrder('04');">
                            待办理<span style="color: red">(${nohandledSum!''})</span>
                        </div>
                        <div class="col-xs-4 color-666 text-center" onclick="serachOrder('07');">
                            已退款<span style="color: red">(${refundedSum!''})</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="Mall-Specifications">
            <div class="user-body">
                <div class="element-end iqaa">
                    <div class="row" name="address">
                        <div class="col-xs-1 ia"><img src="${basepath}/static/img/front/wscqt6-8-10.jpg" width="20"></div>
                        <div class="col-xs-10 ia inner">地址管理</div>
                    </div>
                </div>


            </div>
        </div>
    </div>

    <div class="quick-nav" name="footer">
        <div class="col-xs-3 text-center"><a href="${basepath}/webapp/homePage?khid=${khid}"><img data-name="home" src="${basepath}/static/img/front/wscqt6-8-46.png" width="25"></a> </div>
        <div class="col-xs-3 text-center"><a href="${basepath}/webapp/classPage?khid=${khid}"><img  data-name="classPage" src="${basepath}/static/img/front/wscqt6-8-47.png" width="25"></a> </div>
        <div class="col-xs-3 text-center"><a href="${basepath}/webapp/findPage?khid=${khid}"><img data-name="findPage" src="${basepath}/static/img/front/wscqt6-8-48.png" width="25"></a> </div>
        <div class="col-xs-3 text-center"><a href="${basepath}/webapp/myPage?khid=${khid}"><img  data-name="myPage"  src="${basepath}/static/img/front/wscqt6-8-49.png" width="25"></a> </div>
    </div>

</body>

</html>
<#include "/front/footer.ftl">
<script>
    $("div[name='address']").click(function(){

        window.location.href='${basepath}/front/mine/toAddress?name=${username}&khid=${khid}';
    });
    function serachOrder(status){
        window.location.href='${basepath}/front/order/searchOrder?consignephone=${username}&status='+status;
    }
    function accountManage() {
        $.cookie('kh','${khid}',{expires:100,path:'/'})
        window.location.href='${basepath}/front/mine/accountManage?name=${username}';
    }

</script>


