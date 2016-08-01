
<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>确认订单</title>

</head>

<body>
<div class="whole">
    <div class="Mall-Specifications">
        <div class="money-body background-f8">
            <div class="col-xs-3 ia"><img id='imgurl' src="${info.imgurl!""}" width="100%"></div>
            <div class="col-xs-8 ia">
                <div class="col-xs-12 ia color-333">
                    <p class="col-xs-7 padding-none"><strong id="productName">${info.productName}</strong></p>
                    <p class="col-xs-5 ia text-right"><strong id="sellPrice">${info.sellPrice?string("currency")}</strong></p>
                </div>
                <div class="col-xs-12 ia" id="specDiv" hidden> <span id="selSpec" style="font-size: 12px;">规格：无</span></div>
                <div class="col-xs-12 ia" id="packageName" hidden> 套餐： </div>
                <div class="col-xs-12 ia" id="selPhone" hidden> 号码：13222222222（普通卡）；13333333333（普通卡） </div>
                <div class="col-xs-12 ia" id="fristFee" hidden> 首月资费：半月付 </div>
            </div>

        </div>

        <div class="money-body background-f8" id="recommendProduct" hidden>
            <div class="col-xs-3 ia"><img src="${basepath}/static/img/front/wscqt6-8-2.jpg" width="100%"></div>
            <div class="col-xs-8 ia">
                <div class="col-xs-12 ia color-333">
                    <p class="col-xs-7 padding-none"><strong id="recommendName">北京联通IPTV</strong></p>
                    <p class="col-xs-5 ia text-right"><strong id="recommendPrice">¥ 100.00</strong></p>
                </div>
                <div class="col-xs-12 ia">x 1</div>
            </div>

        </div>

        <div class="user-body" id="userInfo" hidden>
            <div class="element-body">
                <div class="row">
                    <div class="col-xs-3  padding-Ten">用户信息</div>
                    <div class="col-xs-9 ia">
                        <div class="col-xs-12 ia" id="userInfo_name">韩寒 </div>
                        <div class="col-xs-12 ia" id="userInfo_tel">韩寒 </div>
                        <div class="col-xs-12 ia" id="userInfo_ID">韩寒 </div>
                        <div class="col-xs-12 ia" id="userInfo_addr">北京市西城区北三环中路华尊大厦A座1603 </div>
                    </div>
                </div>
            </div>
            <div class="element-body" id="kdInfo" hidden>
                <div class="row">
                    <div class="col-xs-3 padding-Ten">宽带信息</div>
                    <div class="col-xs-9 ia">
                        <div class="col-xs-12 ia" id="kdInfo_ad">9000000123456 </div>
                        <div class="col-xs-12 ia" id="kdInfo_name">韩寒 </div>
                    </div>
                </div>
            </div>
            <div class="element-end" id="netInfo" hidden>
                <div class="row">
                    <div class="col-xs-3 padding-Ten">入网资料</div>
                    <div class="col-xs-9 ia">
                        <div class="col-xs-12 ia" id="netInfo_name">韩寒</div>
                        <div class="col-xs-12 ia" id="netInfo_ID">111111111111111111</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="user-body" id="addrInfo">
            <div class="element-body iqaa">
                <div class="row" id="toAddTempAddr_link">
                    <div class="col-xs-3 padding-Ten">
                        <span class="color-red">*</span>收货信息
                    </div>
                    <div class="col-xs-9 ia ">
                        <div class="col-xs-12 ia" id="addrInfo_name"> </div>
                        <div class="col-xs-11 ia" id="addrInfo_addr">  </div>
                    </div>
                </div>
            </div>
            <div class="element-body" >
                <div class="row">
                    <div class="col-xs-5 padding-Ten">
                        <span class="color-red">*</span>选择支付方式</div>

                </div>
            </div>


            <div class="element-body" name="selpaytype">
                <div class="row">
                    <div class="col-xs-8 ia "><img src="http://img01.bqstatic.com//upload/icon/icondao.png" width="30">
                        货到付款
                    </div>
                    <div class="col-xs-4 padding-Ten">
                        <div class="col-xs-12 padding-Ten text-right" style="font-size: 20px;"><i class="ion-checkmark-circled  text-success hide" data-type="23"></i></div>
                    </div>
                </div>
            </div>

            <#--<div class="element-body" name="selpaytype">-->
                <#--<div class="row">-->
                    <#--<div class="col-xs-8 ia"><img src="${basepath}/static/img/front/wscqt6-8-21.jpg" width="30">-->
                        <#--支付宝-->
                    <#--</div>-->
                    <#--<div class="col-xs-4 padding-Ten">-->
                        <#--<div class="col-xs-12 padding-Ten text-right" style="font-size: 20px;"><i class="ion-checkmark-circled text-success" data-type="22"></i></div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->

            <div class="element-body" name="selpaytype">
                <div class="row">
                    <div class="col-xs-8 ia"><img src="${basepath}/static/img/front/wscqt6-8-22.jpg" width="30">
                        连连支付
                    </div>
                    <div class="col-xs-4 padding-Ten">
                        <div class="col-xs-12 padding-Ten text-right" style="font-size: 20px;"><i class="ion-checkmark-circled  text-success" data-type="21"></i></div>
                    </div>
                </div>
            </div>

            <div class="element-end" id='ticketInfo' hidden>
                <div class="row">
                    <div class="col-xs-3 padding-Ten">发票信息</div>
                    <div class="col-xs-9 ia iqaa">
                        <div class="col-xs-12 ia" id="ticketInfo_type">不要发票</div>
                        <div class="col-xs-12 ia" id="ticketInfo_name" hidden></div>
                        <div class="col-xs-12 ia" id="ticketInfo_conent" hidden></div>
                    </div>
                </div>
            </div>
        </div>


        <div class="user-body">
            <div class="money-body">
                <div class="row">
                    <div class="col-xs-9 padding-Ten" >商品金额</div>
                    <div class="col-xs-3 ia">
                        <div class="col-xs-12 ia  padding-Ten" id="paySellPrice"> ${info.sellPrice?string("currency")}</div>
                    </div>
                </div>
                <div class="row" id="emsFeeDiv">
                    <div class="col-xs-9 padding-Ten">运费</div>
                    <div class="col-xs-3 ia">
                        <div class="col-xs-12 ia padding-Ten" id="emsFee" > +${info.emsFee?string("currency")} </div>
                    </div>
                </div>
                <div class="row" id="cardDiv" hidden>
                    <div class="col-xs-9 padding-Ten">号卡金额</div>
                    <div class="col-xs-3 ia">
                        <div class="col-xs-12 ia padding-Ten" id="cardFee" > +￥0.00 </div>
                    </div>
                </div>
                <div class="row" id="billFeeDiv" hidden>
                    <div class="col-xs-9 padding-Ten">发票快递</div>
                    <div class="col-xs-3 ia">
                        <div class="col-xs-12 ia padding-Ten" id="billFee" > +${info.receiptPrice?string("currency")} </div>
                    </div>
                </div>
            </div>
            <div class="element-end">
                <div class="row">
                    <div class="col-xs-9 ia"><strong>总计</strong></div>
                    <div class="col-xs-3 ia">
                        <div class="col-xs-12 ia color-red" > <strong id="totalPrice" style="font-size: 15px;">${info.totalPrice?string("currency")}</strong> </div>
                    </div>
                </div>
            </div>

        </div>
        <form action="${basepath}/product/savePrePayOrder" id="preSubmitOrderForm" method="POST" >
            <input type="hidden" id="payType" name="paytype" value="21">
            <input type="hidden" id="getTicket">
            <input type='hidden' id="ticketHead" name="invoice">
            <input type="hidden" id="productID" name="productid" value="${info.productID!""}">
            <input type="hidden" id="goodsID" name="goodsid" value="${info.goodsID!""}">
            <input type="hidden" id="khid" name="khid">
            <input type="hidden" name="token" value="${token}">
            <input type="hidden" id="orderid" name="orderid">
            <input type="hidden" id="nums" name="nums">
            <input type="hidden" id="quantity" name="quantity">

            <input type="hidden" name="linkman">
            <input type="hidden" name="contractmobile">
            <input type="hidden" name="distributddress">

            <input type="hidden" name="owner">
            <input type="hidden" name="ownermobi">
            <input type="hidden" name="installaddress">
            <input type="hidden" name="idcard">

            <input type="hidden" name="packageid">
            <input type="hidden" name="packagename">
            <input type="hidden" name="firstfee">

            <input type="hidden" name="netusername">
            <input type="hidden" name="netcardid">
            <input type="hidden" name="netimg1">
            <input type="hidden" name="netimg2">
            <input type="hidden" name="netimg3">

            <input type="hidden" name="newmob">
            <input type="hidden" name="oldmob">
        </form>

        <form id="payForm" action="${basepath}/product/toPay" method="POST">
            <input type="hidden" name="orderno">
            <input type="hidden" name="payType">
        </form>

        <p class="dfaqa"><button type="button" name="submitOrder" class="btn btn-block syiaq" style="line-height: 2.43rem" >提交订单</button></p>

    </div>

</div>
<#include "/front/footer.ftl">
<script>
    $('#toAddTempAddr_link').on('click',function(){
        location.href='${basepath}/product/toAddTempAddr';
    })
    $('#ticketInfo').on('click',function(){
        location.href='${basepath}/product/toAddTicketInfo';
    })

    var orderInfo = {};
    orderInfo.paytype = 21;
    orderInfo.productid="${info.productID!""}"

    $(document).ready(function(){

        // 如果有订单ID，跳转到订单详情页面

        loadSpec();
        loadPackageInfo();
        loadNewPhoneNum();
        loadInNetInfo();
        // 收货人地址信息
        loadAddrInfo()
        loadNewKDUserInfo();
        console.log("加载一次");
    })

//  入网用户信息
   function loadInNetInfo(){
       var netInfo = $.parseJSON(sessionStorage.getItem('inNetInfo'));
       if(netInfo!=null){

           $('#netInfo_name').text(netInfo.username);
           $('#netInfo_ID').text(netInfo.userid);
           $('#netInfo').show();

           $('input[name=netusername]').val(netInfo.username);
           $('input[name=netcardid]').val(netInfo.userid);

           var img =  $.parseJSON(sessionStorage.getItem('userID_IMG'));
           if(img!=null){
               $('input[name=netimg1]').val(img.img1);
               $('input[name=netimg2]').val(img.img2);
               $('input[name=netimg3]').val(img.img3);
           }

       }

    }

    // 新号
    function loadNewPhoneNum(){
        var selNumInfoArr = $.parseJSON(sessionStorage.getItem('selNumInfo'));
        var newNum =[]
        if(selNumInfoArr!=null){
            var selNumInfo = selNumInfoArr.num;
            for(var i in selNumInfo){
                var num = selNumInfo[i].phone+'('+selNumInfo[i].cardtype+')';
                newNum.push(num);
            }

            $('#selPhone').text('号码：'+newNum).show();
            $('#cardDiv').show();

            $('input[name=newmob]').val(JSON.stringify(selNumInfoArr));
        }

        var oldNum = sessionStorage.getItem('oldNum')
        if(oldNum!=null && oldNum!=undefined){
            $('input[name=oldmob]').val(oldNum);
        }

    }

    //加载套餐包信息
    function loadPackageInfo(){
        var packageInfo = $.parseJSON(sessionStorage.getItem('packageInfo'));
        if(packageInfo!=null){
            $('#packageName').text('套餐:'+packageInfo.packageName).show();
            if(packageInfo.firstFee=='0'){
                $('#fristFee').text('首月资费:半月付').show();
            }else if(packageInfo.firstFee=='1'){
                $('#fristFee').text('首月资费:全月付').show();
            }else{
                $('#fristFee').text('首月资费:按量计费').show();
            }


            $('input[name=packageid]').val(packageInfo.packageID);
            $('input[name=packagename]').val(packageInfo.packageName);
            $('input[name=firstfee]').val(packageInfo.firstFee);
        }

    }

    //装机信息
    function loadNewKDUserInfo(){
        var kdInfo = $.parseJSON(sessionStorage.getItem('newKDuserInfo'));
        console.log('kdinfo=',kdInfo);
        if(kdInfo!=null){
            console.log(kdInfo['name']!=undefined);
            $('#userInfo_name').text(kdInfo.name);
            $('#userInfo_ID').text(kdInfo.ID);
            $('#userInfo_tel').text(kdInfo.tel);
            $('#userInfo_addr').text(kdInfo.addr);

            $('input[name=owner]').val(kdInfo.name);
            $('input[name=ownermobi]').val(kdInfo.tel);
            $('input[name=installaddress]').val(kdInfo.addr);
            $('input[name=idcard]').val(kdInfo.ID);
            $('#userInfo').show();
        }
    }

    //规格信息
    function loadSpec(){

        $('#quantity').val(sessionStorage.getItem("quantity")==undefined?1:sessionStorage.getItem("quantity"));
        var selSpec = '${info.selSpec!""}';

        if(selSpec!=""){
            var specArr = $.parseJSON(selSpec);
            var specName = []
            for(var i in specArr){
                specName.push(specArr[i].name)
            }
            $('#specDiv').show();
            $('#selSpec').text('规格:'+specName);
        }
    }


    //收货人地址信息
    function loadAddrInfo(){

        var receiveInfo = $.parseJSON(sessionStorage.getItem('receiveInfo'));
        if(receiveInfo!=null){
            var name = receiveInfo.name;
            var phone = receiveInfo.phone
            var addr = receiveInfo.addr
            if (name!=undefined && phone!=undefined){
                $('#addrInfo_name').text(name+"  "+phone);
                $('#addrInfo_addr').text(addr);

                $('input[name=linkman]').val(name);
                $('input[name=contractmobile]').val(phone);
                $('input[name=distributddress]').val(addr);
            }
        }

        console.log('收货人：'+name);
    }

    //发票信息
    function loadTicketInfo(){
        var needTicket = $.cookie('needticket')
        var tickethead = $.cookie('tickethead');
        if(needTicket=='1'){
            $('#ticketInfo_type').text('纸质发票');
            $('#ticketInfo_name').text('发票抬头:'+tickethead);
            $('#ticketInfo_name').removeAttr('hidden');
        }
    }


    $('div[name="selpaytype"]').on('click',function(){
        $('div[name="selpaytype"]').find('i.ion-checkmark-circled').addClass('hide')
        $(this).find('i.ion-checkmark-circled').each(function(){
            $('#payType').val($(this).attr('data-type'));
            $(this).removeClass('hide')
        })



    })

    $('button[name=submitOrder]').on('click',function(){

        var needticket = $.cookie('needticket');
        var tickethead = $.cookie('tickethead')
        var paytype=$('#payType').val();
        $('input[name=payType]').val(paytype);

        $('#getTicket').val(needticket);
        $('#ticketHead').val(tickethead);

        var cname= $.cookie('cname')
        weui.Loading.show();
            console.log('提交');

            $.ajax({
                url:$('#preSubmitOrderForm').attr('action'),
                    data:$('#preSubmitOrderForm').serialize(),
                    type:'POST',
                    success:function(data){
                        weui.Loading.hide();
                        if(data.code=='0000'){
                            $('#orderid').val(data.orderid);
                            $('input[name=orderno]').val(data.orderid);
                            clearLocalStorage();
                            $('#payForm').submit();
                        }else{
                            console.log('data=',data,data.length);
                            weui.Loading.hide();
                            weui.Loading.error(data.msg);
                        }

                    },error:function(){
                    weui.Loading.hide();
                    weui.Loading.error("提交失败，稍后再试！");
                }
            })
    })

    function clearLocalStorage(){
        // 用户填写宽带新装信息

        // 上传信息

        //其他信息
        sessionStorage.clear();
//        localStorage.clear();
    }
//
//    var checkSubmitFlg = false;
//    function checkSubmit() {
//        if (checkSubmitFlg == true) {
//            return false;
//        }
//        checkSubmitFlg = true;
//        return true;
//    }
//    document.ondblclick = function docondblclick() {
//        window.event.returnValue = false;
//    }
//    document.onclick = function doc {
//        if (checkSubmitFlg) {
//            window.event.returnValue = false;
//        }
//    }

</script>
</body>
</html>
