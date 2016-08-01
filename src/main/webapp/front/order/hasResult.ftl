<!DOCTYPE HTML>
<html>
<#include "/front/header.ftl">
<head>
    <title>订单查询</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no" />
<body>
<div class="whole">
<#list odlist as item>
<div class="Mall-content">
    <div class="row">
      <div class="col-xs-10 ia">下单时间：
      <#if item.createTime??>
      ${item.createTime?string("yyyy-MM-dd hh:mm:ss")} </div>
      </#if>
          <#if item.isPaid=='0'>
              <div class="col-xs-2 ia">待付款</div>
          <#elseif item.isPaid=='1'>
              <div class="col-xs-2 ia">完成</div>
          </#if>
    </div>
</div>
<div class="Mall-Specifications">
  <div class="money-body" style="background:#f8f8f8;">
    <div class="col-xs-3 ia"><img src="${basepath}/static/img/front/wscqt6-8-1.jpg" width="100%"></div>
    <div class="col-xs-8 ia color-666">
      <div class="col-xs-12 ia color-333 qwwee">
        <label>智慧沃家套餐</label>
      </div>
      <div class="col-xs-12 ia"> 规格：20M 1G全国流量 1000分钟全国通话 </div>
      <div class="col-xs-12 ia"> 套餐：${item.packageName!''} </div>
      <div class="col-xs-12 ia"> 号码：${item.newmob!''}（普通卡）；${item.oldmob!''}（普通卡） </div>
    </div>
    <div class="col-xs-1 ia" onclick="window.location.href='${basepath}/front/order/detail?orderid=${item.orderNum}'"><img src="${basepath}/static/img/front/wscqt6-8-2.png" width="30%"></div>
  </div> 
  <div class="user-body">
    	<div class="element-end">
      <div class="row">
        <div class="col-xs-7 ia">
        	<div class="col-xs-6 ia">
            <strong>实付金额</strong>
        </div>
        <div class="col-xs-16 ia color-red"> <strong>${item.amount?string('0.00')}</strong> </div>
        </div>
        <#if item.isPaid=='0'>
        <button type="button" class="btn btn-warning pull-right new-btn">立即支付</button>
        </#if>
      </div>
    </div>
  </div>
</div>
    </#list>
</div>
</body>
<#include "/front/footer.ftl">
</html>
