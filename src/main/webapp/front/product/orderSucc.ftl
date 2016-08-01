
<!DOCTYPE HTML>
<html>
<head>
    <#include "/front/header.ftl">
    <title>订单提交成功</title>
</head>

<body>

<div class="register">
    <div class="money-body loaf">
        <div class="col-xs-3 ia"><img src="${basepath}/static/img/front/wscqt6-8-20.jpg" width="60"></div>
        <div class="col-xs-8 ia" style="padding-top:1.5em;">
            <div class="col-xs-12 ia color-333 qwwee">
                <label>订单提交成功！</label>
            </div>
        </div>
    </div>
    <div class="Submit-success">
        <div class="col-xs-12 ia size-13">订单金额：<strong class="color-red">${amount?string("currency")}</strong></div>
        <div class="col-xs-12 ia size-13">订  单 号：${orderid}</div>
        <div class="col-xs-12 ia size-13">支付方式：${payType!""}</div>
    </div>
    <p class="dfaqa fill-top">
        <button type="button" class="btn btn-default pull-right" data-href="home">去首页</button>
        <button type="button" class="btn btn-default pull-right right-1em" data-href="detail">查看详情</button>
    </p>
</div>

</body>
<#include "/front/footer.ftl">
<script>
    $('button').on('click',function(){
        var link = $(this).attr('data-href');
        if('home'==link){
            location.href='${basepath}/webapp/homePage?khid=${khid!""}';
        }else{
            location.href='${basepath}/front/order/detail?orderid=${orderid}';
        }
    })
</script>
</html>