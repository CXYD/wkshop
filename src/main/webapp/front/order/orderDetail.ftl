
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
    <div class="col-xs-3 ia"><img src="${productImgUrl!""}" width="100%"></div>
    <div class="col-xs-8 ia color-666">
      <div class="col-xs-12 ia color-333 qwwee">
        <label name="productName">${orderItem.productName!""}</label>
      </div>
      <div class="col-xs-12 ia" id="specArr" hidden> 规格： </div>
      <div class="col-xs-12 ia" id="packagInfo" hidden> 套餐：${order.packagename!""} </div>
      <div class="col-xs-12 ia" id="newNum" hidden> 号码： </div>
      <div class="col-xs-12 ia" id="firstFee" hidden> 首月资费： </div>
    </div>
    <#--<div class="col-xs-1 ia" ><img src="${basepath}/static/img/front/wscqt6-8-2.png" width="30%"></div>-->
  </div>
  
  <div class="user-body">
    <div class="element-body" id="userinfo" hidden>
      <div class="row">
        <div class="col-xs-3 padding-Ten">用户信息</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia" id="userinfo_name"></div>
          <div class="col-xs-12 ia" id="userinfo_phone"> </div>
          <div class="col-xs-12 ia" id="userinfo_addr">  </div>
        </div>
      </div>
    </div>
    <div class="element-body" id="adinfo" hidden>
      <div class="row">
        <div class="col-xs-3 padding-Ten">宽带信息</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia" id="adinfo_adsl">9000000123456</div>
          <div class="col-xs-12 ia" id="adinfo_name">韩寒</div>
        </div>
      </div>
    </div>
    <div class="element-end" id="netinfo" hidden>
      <div class="row">
        <div class="col-xs-3 padding-Ten">入网资料</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia" id="netinfo_name">韩寒</div>
          <div class="col-xs-12 ia" id="netinfo_id">111111111111111111</div>
        </div>
      </div>
    </div>
  </div>
  
  <div class="user-body">
    <div class="element-body" id="addrinfo">
      <div class="row">
        <div class="col-xs-3 padding-Ten">收货信息</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia" id="addrinfo_name"> 韩寒   13222222222 </div>
          <div class="col-xs-12 ia" id="addrinfo_addr"> 北京市西城区北三环中路华尊大厦A座1603 </div>
        </div>
      </div>
    </div>
    <div class="element-body">
      <div class="row">
        <div class="col-xs-3 padding-Ten">支付方式</div>
        <div class="col-xs-9 ia">
          <div class="col-xs-12 ia" id="payType"> </div>
        </div>
      </div>
    </div>
    <div class="element-end" id='ticketInfo'>
      <div class="row">
        <div class="col-xs-3 padding-Ten">发票信息</div>
        <div class="col-xs-9 ia">
           <div class="col-xs-12 ia" id="ticketInfo_type"> 纸质发票</div>
          <div class="col-xs-12 ia" id="ticketInfo_head" hidden>发票抬头：</div>
          <#--<div class="col-xs-12 ia" id="ticketInfo_content" hidden>发票内容：智慧沃家套餐；北京联通IPTV</div>-->
        </div>
      </div>
    </div>
  </div>
  <div class="user-body">
    <div class="money-body">
      <div class="row">
        <div class="col-xs-9 padding-Ten">商品金额</div>
        <div class="col-xs-3 ia">
          <div class="col-xs-12 ia  color-666 padding-Ten"> ${order.productAmount?string("currency")} </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-9 padding-Ten" id="emsFeeDiv">运费</div>
        <div class="col-xs-3 ia">
          <div class="col-xs-12 ia padding-Ten" id="emsFee"> +${order.shipAmount?string("currency")} </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-9 padding-Ten" id="cardDiv" >号卡金额</div>
        <div class="col-xs-3 ia">
          <div class="col-xs-12 ia padding-Ten" id="cardFee"> +${orderItem.cardprice?string("currency")} </div>
        </div>
      </div>
        <div class="row" id="ticketFee">
            <div class="col-xs-9 padding-Ten" id="billFeeDiv">发票快递</div>
            <div class="col-xs-3 ia">
                <div class="col-xs-12 ia padding-Ten" id="billFee"> +${order.invoiceprice?string("currency")} </div>
            </div>
        </div>

    </div>
    <div class="money-body">
      <div class="row">
        <div class="col-xs-9 ia"><strong>实付金额</strong></div>
        <div class="col-xs-3 ia">
          <div class="col-xs-12 ia color-red" id="payMoney"> <strong>${order.amount?string("currency")}</strong> </div>
        </div>
      </div>
    </div>
    <div class="Submit-body">
      <div class="row">
        <div class="reminder" id="createtimeDiv"> 下单时间:${order.createTime?string("yyyy-MM-dd HH:mm:ss")}</div>
      </div>
      <div class="row " id="footerBtn" hidden>
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
        loadInstallADInfo();
        laodAddrInfo();
        loadNetInfo();
        laodTicketInfo();
    })

    $('button:eq(0)').on('click',function(){
        location.href="${basepath}/product/selPayType/${order.orderNum!""}";
    })

    $('button:eq(1)').on('click',function(){
        weui.Loading.show();
        $.ajax({
            url:"${basepath}/product/cancelOrder/${order.orderNum!""}",
            type:"post",
            success:function(d){
                weui.Loading.hide();
                if(d>0){
                    weui.Loading.info("取消成功！")
                    $('#footerBtn').attr('hidden',true);
                    $('div[name=orderstate]').text('已取消');
                    //回收产品库存，号码等
                    // TODO .......
                }else{
                    weui.Loading.info("取消失败！")
                }
            },error:function(){
                weui.Loading.hide();
                weui.Loading.info("系统繁忙稍后再试！")
            }
        })
    })

    var stateArr = {'02':'已取消','03':'待付款','04':'待办理','05':'办理成功','06':'待付款','07':'退款结束'}
    var payType = {21:"连连支付",22:"支付宝支付", 23:"货到付款"}
    function initOrderInfo(){
        <#--console.log('商品信息：',"${order}");-->
      if ("${order.id?if_exists}"!=''){
          $('div[name=orderstate]').text(stateArr['${order.orderstatus}']);
          if('03'=='${order.orderstatus}'){
              $('#footerBtn').attr('hidden',false);
          }
      }

    }
    function initOrderItemInfo(){
      var feeArr=["半月付","全月付","按流量付"]
        <#--console.log('商品详情：',"${orderItem}");-->

        if ("${orderItem.id?if_exists}"!=''){
            console.log('${orderItem.productName!""}');
          $('label[name=productName]').text('${orderItem.productName!""}');
        }else{
            console.err('no item');
        }

        if ("${orderItem.packagename?if_exists}"!=''){
         $('#packagInfo').attr('hidden',false);
        }

        if('${orderItem.newmob?if_exists}'!=''){
            var newNumArr = JSON.parse('${orderItem.newmob}');
            var numObj = newNumArr.num;
            console.log(numObj);
            var tmpArr = []
            for(var i=0 ;i<numObj.length;i++){
                tmpArr.push(numObj[i].phone+'('+numObj[i].cardtype+")")
            }
            $('#newNum').attr("hidden",false).text("号码："+tmpArr);
        }

        if('${orderItem.firstfee?if_exists}'!=''){
            var i = '${orderItem.firstfee!""}'
            $('#firstFee').attr('hidden',false).text('首月资费：'+feeArr[i]);
        }
        /**
         * 加载规则
         */
        if("${orderItem.goodsid?if_exists}"!='') {

            $.get("/wkshop/product/querySpecByGoodsID/<#if orderItem.goodsid??>${orderItem.goodsid}</#if>", function (data, status) {

                var specArr = JSON.parse(data);
                console.log(specArr);

                var obj = [];
                for (i in specArr) {
                    var tmpSpec = specArr[i];
                    if (tmpSpec != null) {
                        obj = obj.concat(tmpSpec.value);
                    }
                }
                console.log(obj);
                $('#specArr').attr('hidden',false).text('规格：'+obj);;
            })
        }
console.log("支付类型：",${order.paytype});
        $('#payType').text(payType[${order.paytype}]);

    }
    // 新装信息
    function loadInstallADInfo(){
        if ("${order.owner?if_exists}"!=''){
            $('#userinfo').attr("hidden",false);
            $('#userinfo_name').text('${order.owner!""}');
            $('#userinfo_addr').text('${order.installaddress!""}');
            $('#userinfo_phone').text('${order.ownermobi!""}');
        }
    }

    // 加载续费用户信息
    function loadKDXFInfo(){
        <#--if ("${order.owner?if_exists}"!=''){-->
            <#--$('#userinfo').attr("hidden",false);-->
            <#--$('#userinfo_name').text('${order.owner!""}');-->
            <#--$('#userinfo_addr').text('${order.installaddress!""}');-->
            <#--$('#userinfo_phone').text('${order.ownermobi!""}');-->
        <#--}-->
    }

    //加载入网信息
    function loadNetInfo(){

        if ("${order.netusername?if_exists}"!=''){
            $('#netinfo').attr("hidden",false);
            $('#netinfo_name').text('${order.owner!""}');
            $('#netinfo_id').text('${order.installaddress!""}');
        }
    }

    //加载收货信息
    function laodAddrInfo(){
        if ("${order.addrinfo_name?if_exists}"!=''){
            $('#addrinfo').attr("hidden",false);
            $('#addrinfo_name').text('${order.netusername!""}');
            $('#addrinfo_addr').text('${order.netcardid!""}');
        }


    }

    //发票信息
    function laodTicketInfo(){
        if ("${order.invoice?if_exists}"!=''){
          $("#ticketInfo_head").attr("hidden",false).text("发票抬头：${order.invoice!""}");
            $("#ticketFee").attr("hidden",false);

        }else{
            $('#ticketInfo_type').text('不要发票');
        }
    }

</script>
</body>
</html>
