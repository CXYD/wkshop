
<!DOCTYPE HTML>
<html>
<head>
    <#include "/front/header.ftl">
<title>订单详情</title>
</head>
<body>
<div class="whole">
<div class="Mall-content">
    <div class="row">
      <div class="col-xs-10 ia">订单号：${order.orderNum}</div>
      <div class="col-xs-2 ia" name="orderstate">待付款</div>
    </div>
</div>
<div class="Mall-Specifications">
  <div class="money-body" style=" background:#f8f8f8;">
    <div class="col-xs-3 ia"><img src="image/wscqt6-8-1.jpg" width="100%"></div>
    <div class="col-xs-8 ia color-666">
      <div class="col-xs-12 ia color-333 qwwee">
        <label name="productName">智慧沃家套餐</label>
      </div>
      <div class="col-xs-12 ia"> 规格：20M 1G全国流量 1000分钟全国通话 </div>
      <div class="col-xs-12 ia"> 套餐：联通4G全国套餐96元/月12个月 </div>
      <div class="col-xs-12 ia"> 号码：13222222222（普通卡）；13333333333（普通卡） </div>
      <div class="col-xs-12 ia"> 首月资费：半月付 </div>
    </div>
    <div class="col-xs-1 ia" ><img src="image/wscqt6-8-2.png" width="30%"></div>
  </div>
  
  <div class="user-body">
    <div class="element-body">
      <div class="row">
        <div class="col-xs-3 padding-Ten">用户信息</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia">韩寒</div>
          <div class="col-xs-12 ia">13222222222 </div>
          <div class="col-xs-12 ia"> 北京市西城区北三环中路华尊大厦A座1603 </div>
        </div>
      </div>
    </div>
    <div class="element-body">
      <div class="row">
        <div class="col-xs-3 padding-Ten">宽带信息</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia">9000000123456</div>
          <div class="col-xs-12 ia">韩寒</div>
        </div>
      </div>
    </div>
    <div class="element-end">
      <div class="row">
        <div class="col-xs-3 padding-Ten">入网资料</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia">韩寒</div>
          <div class="col-xs-12 ia">111111111111111111</div>
        </div>
      </div>
    </div>
  </div>
  
  <div class="user-body">
    <div class="element-body">
      <div class="row">
        <div class="col-xs-3 padding-Ten">收货信息</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia"> 韩寒   13222222222 </div>
          <div class="col-xs-12 ia"> 北京市西城区北三环中路华尊大厦A座1603 </div>
        </div>
      </div>
    </div>
    <div class="element-body">
      <div class="row">
        <div class="col-xs-3 padding-Ten">支付方式</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia"> 支付宝支付 </div>
        </div>
      </div>
    </div>
    <div class="element-end">
      <div class="row">
        <div class="col-xs-3 padding-Ten">发票信息</div>
        <div class="col-xs-9 ia">
           <div class="col-xs-12 ia"> 纸质发票</div>
          <div class="col-xs-12 ia">发票抬头：涵涵</div>
          <div class="col-xs-12 ia">发票内容：智慧沃家套餐；北京联通IPTV</div>
        </div>
      </div>
    </div>
  </div>
  <div class="user-body">
    <div class="money-body">
      <div class="row">
        <div class="col-xs-9 padding-Ten">商品金额</div>
        <div class="col-xs-3 ia">
          <div class="col-xs-12 ia  color-666 padding-Ten"> ¥480.00 </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-9 padding-Ten">运费</div>
        <div class="col-xs-3 ia">
          <div class="col-xs-12 ia padding-Ten"> +10.00 </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-9 padding-Ten">号卡金额</div>
        <div class="col-xs-3 ia">
          <div class="col-xs-12 ia padding-Ten"> +40.00 </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-9 padding-Ten">发票快递</div>
        <div class="col-xs-3 ia">
          <div class="col-xs-12 ia padding-Ten"> +8.00 </div>
        </div>
      </div>
    </div>
    <div class="money-body">
      <div class="row">
        <div class="col-xs-9 ia"><strong>实付金额</strong></div>
        <div class="col-xs-3 ia">
          <div class="col-xs-12 ia color-red"> <strong>¥538.00</strong> </div>
        </div>
      </div>
    </div>
    <div class="Submit-body">
      <div class="row">
        <div class="reminder"> 下单时间：2016-05-26 09:50:22 </div>
      </div>
      <div class="row ">
        <button type="button" class="btn btn-warning pull-right new-btn">立即支付</button>
        <button type="button" class="btn btn-default pull-right right-1em">取消订单</button>
        
      </div>
      
    </div>
  </div>
</div>
</div>
<#include "/front/footer.ftl">

<script>

    $(document).ready(function(){

        initOrderInfo();
        initOrderItemInfo();
    })

    var stateArr = {'02':'已取消','03':'代付款','04':'待办理','05':'办理成功','06':'待付款','07':'退款结束'}

    function initOrderInfo(){
        <#--console.log('商品信息：',"${order}");-->
      if ("${order.id?if_exists}"!=''){
          $('div[name=orderstate]').text(stateArr['${order.orderstatus}']);
      }

    }
    function initOrderItemInfo(){

        <#--console.log('商品详情：',"${orderItem}");-->

        if ("${orderItem.id?if_exists}"!=''){
            console.log('${orderItem.productName!""}');
          $('label[name=productName]').text('${orderItem.productName!""}');
        }else{
            console.err('no item');
        }

    }
</script>
</body>
</html>
