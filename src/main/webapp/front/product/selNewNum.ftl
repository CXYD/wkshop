
<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>选择新号码</title>
    <link rel="stylesheet" href="${basepath}/static/css/front/add_card.css">
</head>
<body>
<#--添加新号-->
<div id="add-additional-card" class="page" data-role="page" style="display: block;">

    <div class="content adt-card-c">
        <div class="adt-card-w">

            <div class="card-page main-card" id="mainCard">
                <h4>
                    <span class="num"></span>
                    <span class="edit-package" id="editMainCard" style="display: block;">选择手机号码</span>
                </h4>
                <div class="adt-card-list">
                    <span style="width: 100%">所选号码需另行支付：￥0.00</span>
                    <ul>
                        <li></li>
                    </ul>
                </div>

            </div>


            <!--增加副卡-->

            <div class="card-page add-card-w" id="addSubCard">
            <div class="add-card-info">
                <img class="quest-icon" src="http://res.mall.10010.cn/mall/mobile/images/quest-icon.jpg?resVer=20160714175042">
                <span class="fr last-num">还可添加<i>${nums}</i>个</span>
                <span class="fl">添加手机号</span>
            </div>
            <div class="btns-box">
                <a href="javascript:void(0);" class="org-btn w-full fl" id="number-select">选新号码</a>
            </div>
            <#--<div class="add-card-notice-w">-->
                <#--<ul class="add-card-notice" style="display: none;">-->
                    <#--<li class="tt">副卡规则说明：</li>-->
                    <#--<li>1、每张主卡可办理1-4张副卡，副卡须与主卡属于同一归属地、同一客户名称及身份证件号码；</li>-->
                    <#--<li>2、副卡开通后立即生效，首月资费方式与主卡相同。</li>-->
                    <#--<li>2、开通副卡立即生效，首月免月租，按使用量计费，执行主卡套餐套餐外资费；从次月开始共享主卡套餐内的流量、语音及流量叠加包。</li>-->
                <#--</ul>-->
            <#--</div>-->
        </div>

        </div>



        <div class="next-node-fixed">
            <a href="javascript:void(0);" class="org-btn" id="toBusinessDetail">下一步</a>
        </div>
    </div>
</div>

<#--选择新号-->
<div id="number-select_div" class="page" data-role="page" style="display: none;overflow:hidden;">
    <div class="user-body">
        <div class="element-body fill-top color-666">
            <div class="row">
                <div class="col-xs-12 col-xs-offset-4"><h4>选择号码</h4></div>
            </div>
        </div>
        <div class="num-table">
            <table id="phoneNumTable">
                <thead>
                <tr>
                    <th width="50%">手机号码</th>
                    <th width="50%">卡费</th>
                </tr>
                </thead>
                <tbody>


                </tbody>
            </table>
            <a href="javascript:refreshPhonenumData()" class="btn-red">换一批</a>
        </div>

        <#--<div class="col-xs-12 fill-top">您所选的号码<span class="color-f60" id="selNum"></span></div>-->
        <#--<div class="col-xs-12 fill-top">另行支付：<span class="color-f60" id="numFee"></span></div>-->

        <div class="col-xs-12 padding-Ten register">
            <p class="dfaqa"><button type="button" class="btn btn-block org-btn" style="line-height: 0px">确定</button></p>
        </div>
    </div>
</div>

</body>
<#include "/front/footer.ftl">
<script>

    var newNumsInfo = {};
    newNumsInfo.fee='${numFee!""}'
    newNumsInfo.nums= ${nums};
    newNumsInfo.selNum=[]
    var currNum;
    var numTotal=-1;

    $('#number-select').on('click',function(){
        if(newNumsInfo.nums>0){
            if(numTotal<=10 && numTotal>=0){

            }else{
                loadPhoneNum();
            }

            $('#add-additional-card').css('display','none');
            $('#number-select_div').css('display','block');
        }else{
            $('#number-select').removeClass('org-btn').addClass('gray-btn').attr('disabled',true);
        }


    })

    //添加选择的号码
    $('button').on('click',function(){

        if(currNum==undefined||currNum==""){
            weui.Loading.info("没有选择号码")
        }else{
            if(!checkRepeat(currNum)){
            var phoneCard = '<div class="card-page adt-card" isgroupnum="0" type="num" groupid=""' +
                    ' info-id="'+currNum+'"citycode="110" provincecode="undefined" nicetag="0" numprefee="0"> ' +
                    '<h4>'+currNum+'</h4> <i class="close"></i> <div class="tabs-box" style="display: block;"> <ul class="tabslist"> ' +
                    '<li data-tabs-sel="0" class="tab-on" data-value="A000065V000001"> <label>普卡</label> </li> ' +
                    '<li data-tabs-sel="1" class="" data-value="A000065V000002"> <label>微卡</label> </li> ' +
                    '<li data-tabs-sel="2" class="tab-last " data-value="A000065V000003"> <label>Nano卡</label> </li> ' +
                    '</ul> </div> <p class="card-type-info">卡类型介绍&gt;&gt;</p> </div>'
            newNumsInfo.nums--;

            $('span.last-num').find('i').text(newNumsInfo.nums);
            $('.add-card-w').before(phoneCard);
            $('div.adt-card-list').find('span').text('另需付费：￥'+$("#add-additional-card .adt-card").length+'.00');
            $('#number-select_div').css('display','none');
            $('#add-additional-card').css('display','block');
            if(newNumsInfo.nums<1){
                $('#number-select').removeClass('org-btn').addClass('gray-btn').attr('disabled',true);
            }
        }

        }

    })

    function refreshPhonenumData(){

        if(numTotal>=0 && numTotal<=10){
            weui.Loading.info("已没有更多号码！")
        }else {
            loadPhoneNum();
        }

    }


    function loadPhoneNum(){
        weui.Loading.show();
        $.ajax({
            url:'refreshPhonenum',
            data:{numid:'${newnumId}'},
            type:'post',
            success:function(d){
                console.log(d);
                numTotal = d.length;
                $('#phoneNumTable tbody').empty();
                weui.Loading.hide();
                for(var i in d){
                    var row = ' <tr> <td> ' +
                            '<b class="chkbox-ckd" group="pn"><input type="radio" class="radio" name="pn" value="'+ d[i].phonenum+'" data-fee="'+ d[i].fee+'" phonenummd5="ba457a356929052bcbd0e6baa1cc33d5"></b> ' +
                            '<span>'+ d[i].phonenum+'</span> </td> <td>￥0.0</td> </tr>';
                    $('#phoneNumTable tbody').append(row);
                }

                if(d.length<1){
                    $('#phoneNumTable tbody').append("<tr> <td rowspan='2'>无可选号码<td></tr>");
                }

                $('#phoneNumTable tr').bind('click',function(){
                    $('b').css('background-position','');
                    $(this).find('b').css('background-position',' 0 0');
                    currNum=$(this).find('input').attr('value');
                });


            }
        })

    }

    // 校验是否有重复号码
    function checkRepeat(number) {
        var result = false;

        $.each($("#add-additional-card .adt-card"), function(i, val) { // 防止重复选号
            if ($(this).attr('info-id') == number) {
                weui.Loading.error('已选择此号码');
                result = true;
                return false;
            }
        });
        return result;
    }

    // 计算号码还能添加的个数
    function computerLeftSubCardCount() {
//        goodsInfo.addCardCount = $("#add-additional-card .adt-card").length;
//        $("#addSubCard .last-num i").text(goodsInfo.checkResult.leftNum - goodsInfo.addCardCount);
//        $("#leftCountTips p i").eq(2).text(
//                (goodsInfo.checkResult.leftNum - goodsInfo.addCardCount) + "张");
    }

    function setHearInfo(){
        console.log('总计');
        $('.adt-card-w').find('.adt-card').each(function(){

            console.log($(this).attr('info.id'));
        })

    }

    $(".adt-card-w")
        // 新增号码取消
            .delegate(".adt-card .close", "click", function() {
                $(this).closest(".card-page").remove();
                newNumsInfo.nums ++;
                $('span.last-num').find('i').text(newNumsInfo.nums);
                $('div.adt-card-list').find('span').text('所选号码需另行支付：'+$("#add-additional-card .adt-card").length);
                $("#number-select").removeClass("gray-btn").addClass("org-btn");
            })
        // 卡类型切换
            .delegate(".adt-card .tabslist li", "click", function() {
                $(this).addClass("tab-on").siblings().removeClass("tab-on");
            })
        // 查看卡类型介绍
            .delegate(".card-type-info", "click", function() {
                $("#card-layer").show().vCenter();
                $(".mask").show().height($(document).height());
            });

    // 下一步
    $('#toBusinessDetail').on('click',function(){
        var numsInfoArr =[]
        $("#add-additional-card .adt-card").each(function(){
            console.log($(this).attr('info-id'));
           // console.log($(this).find('li.tab-on').text());
            var phone=$(this).attr('info-id');
            var cardtype=$(this).find('li.tab-on').text()
            var selCard={phone:phone,cardtype:cardtype};
            numsInfoArr.push(selCard);
        });
    console.log('所选号码：',numsInfoArr);
        if(numsInfoArr.length<1){
            weui.Loading.error("请先选择号码")
        }else{

            if(!checkIsOccupy(numsInfoArr)){
                var phoneNumInfo = {};
                phoneNumInfo.pici='${newnumId}';
                phoneNumInfo.num=numsInfoArr;

                sessionStorage.setItem("selNumInfo",JSON.stringify(phoneNumInfo));
//            $.cookie('selNumInfo',JSON.stringify(numsInfoArr));
                location.href='submitSelNewNum';
            }


        }

    })
    // 校验号码是否被占用
    function checkIsOccupy(numArr){
        weui.Loading.show();
        var checkNum=[]
        for(var i in numArr){
            checkNum.push(numArr[i].phone);
        }
        $.ajax({
            url:'checkIsOccupy',
            type:"post",
            asysn:false,
            data:{numArr:checkNum,numid:'${newnumId}'},
            success:function(d){
                weui.Loading.hide();
                if(d!='no'){
                    weui.Loading.error(d+"被占用!")
                    return false;
                }
                return true;
            }
        })

        true;


    }


</script>

</html>
