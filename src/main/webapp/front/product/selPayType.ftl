<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl"/>
    <title>选择支付方式</title>
</head>

<body>
<div class="whole">

    <div class="Mall-Specifications ">
        <div class="user-body">
            <#--<div class="element-body">-->
                <#--<div class="row">-->
                    <#--<div class="col-xs-3 ia size-15">商品名称//多个商品这样是没法列的，参考京东</div>-->
                    <#--<div class="col-xs-9 padding-Ten">-->
                        <#--<div class="col-xs-12 padding-Ten text-right color-999">智慧沃家共享套餐</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
            <div class="element-end">
                <div class="row">
                    <div class="col-xs-3 ia size-15">支付金额</div>
                    <div class="col-xs-9 ia padding-Ten">
                        <div class="col-xs-12 color-f60 text-right">${total?string("currency")}</div>
                    </div>
                </div>
            </div>


        </div>

        <div class="user-body">
            <#--<div class="element-body">-->
                <#--<div class="row">-->
                    <#--<div class="col-xs-8 ia  size-15"><img src="${basepath}/static/img/front/wscqt6-8-21.jpg" width="30">-->
                        <#--支付宝-->
                    <#--</div>-->
                    <#--<div class="col-xs-4 padding-Ten">-->
                        <#--<div class="col-xs-12 padding-Ten text-right" style="font-size: 20px;"><i class="ion-checkmark-circled " data-type="22"></i></div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
            <div class="element-end">
                <div class="row">
                    <div class="col-xs-8 ia  size-15"><img src="${basepath}/static/img/front/wscqt6-8-22.jpg" width="30">
                        连连支付
                    </div>
                    <div class="col-xs-4 padding-Ten">
                        <div class="col-xs-12 padding-Ten text-right" style="font-size: 20px;"><i class="ion-checkmark-circled  text-success" data-type="21"></i></div>
                    </div>
                </div>
            </div>

        </div>
        <p class="dfaqa"><button type="button" class="btn btn-block syiaq">确认支付</button></p>
    </div>
</div>
<form name="toPayForm" action="${basepath}/product/toPay">
    <input type="hidden" name="payType" value="21">
    <input type="hidden" name="orderno" value="${orderno!""}">
</form>
</body>
<#include "/front/footer.ftl"/>

<script>

    $('button').on('click',function(){
        $('form[name=toPayForm]').submit();
    })
</script>
</html>
