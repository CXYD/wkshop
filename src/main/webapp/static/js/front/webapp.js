$("div[name='footer']").on('click',function(){
    var page =  $(event.target).attr('data-name')
    if(page !=undefined)
    $("div[name=content]").load("webapp/"+page);


})


var selSpecArr=[];
//选择规则
$('#control_info').on('click',function(){
    if($(event.target).attr('class').indexOf('sku_a')>-1){
        $(event.target).parent().parent().find('.current_sku').each(function(){
            var preVal = $(this).text();
            var idx = selSpecArr.indexOf(preVal);
            if(idx>-1)
                selSpecArr.splice(selSpecArr.indexOf($(this).text()),1);
            $(this).removeClass('current_sku');
        })
        $(event.target).addClass('current_sku');
        selSpecArr.push($(event.target).text());

    }

    if(specCount == selSpecArr.length) {
        console.log(selSpecArr[specCount-1]);

        $('#selected_sku_des_tle').text('已选:' + selSpecArr);
        var goodsInfo = checkAndGetPrice(selSpecArr);
        if(currentGoodsType==0 && goodsInfo.storeNums==0){
            weui.Loading.error("该商品暂时缺货!")
            $('.control_sku').find('li.sku_li').each(function(){
               if($.trim($(this).text())== $.trim(selSpecArr[specCount-1])){
                   $(this).find('a').removeClass('current_sku');
                   selSpecArr.pop();
               }
            })

        }
        setSelPageInfo(goodsInfo);
    }
    //console.log('已选规格=',selSpecArr);

})

function checkAndGetPrice(selSpecArr){

    for(i in goodDetail){
        var goodSpec = $.parseJSON(goodDetail[i].specArray)
        var inCount = 0;
        for(j in goodSpec){
            if(selSpecArr.indexOf(goodSpec[j].value)>-1){
                inCount++;
            }
        }

        if(inCount == specCount){
            return goodDetail[i];
        }
    }
}

//立即办理/立即购买
$('#add_cart').on('click',function(){
    console.log('规则个数：',specCount);

    if($('#item_select_sku_count').css('display')=='block' ){
        weui.Loading.show()
        submitOrder();
    }else{
        if(specCount==undefined || specCount==0){
            current_goodsID = goodDetail[0].id;
        }

        $('#item_fix_btn').css('display','none');
        $('#item_select_sku_count').css('display','block');
    }

})
//确定,提交到下一步
$('a#control_bottom_submit').on('click',function(){
    if($('#item_num').val()!=undefined)
    $('#nums').val($('#item_num').val())
    submitOrder();
})

/**
 * 提交订单
 */
function submitOrder(){
    weui.Loading.show();
    if(specCount==undefined || specCount == selSpecArr.length){
        var goodsInfo = checkAndGetPrice(selSpecArr);
        console.log("找到商品：",goodsInfo);
        if(goodsInfo!=undefined){
            $('#selected_sku_des_tle').text('已选:'+selSpecArr);
            $('#goodsID').val(goodsInfo.id);
        }
        $.cookie('goodsID',$('#goodsID').val(), { expires: 1 , path: '/wkshop'});
        $.cookie('productID',$('#productID').val(), { expires: 1 , path: '/wkshop'});
        sessionStorage.setItem("quantity",$('#item_num').val()==undefined?1:$('#item_num').val())

        $("#selectOrderForm").submit();
    }else{
        weui.Loading.hide();
        weui.Loading.error('选择规则');
    }
}

//减号
$('#control_num_sub').on('click',function(){
    val = $('#item_num').val();
    if(val==1){
        weui.Loading.error('至少一件');
        $(this).addClass('disabled')
    }else{
        val--;
        $('#item_num').val(val)
    }
})
var current_goodsID=0;
var val = 1;
//加号
$('#control_num_add').on('click',function(){
    var stock = $('#control_stock').attr('data-stock');
    if(specCount!=undefined && specCount!=0)
        current_goodsID = $('#control_sell_price').attr('data-goodsid');
    console.log("当前货物：",current_goodsID);
    if(current_goodsID!=undefined&&current_goodsID!=""){
        // 去后台查询库存
        weui.Loading.show();
       checkStock();
    }else{
        weui.Loading.error('清选择规格！');
    }


})

$('#item_num').on('blur',function(){
    console.log($(this).val());
})

//校验库存
function checkStock(){
    $.ajax({
        url:'/wkshop/product/queryGoodsStock',
        data:{id: current_goodsID},
        success:function(d){
            weui.Loading.hide()
            stock = d;
            if(parseInt(val)>=parseInt(stock)){
                weui.Loading.error('超出库存上限');
            }else{
                val++;
                $('#item_num').val(val)
                $('#control_num_sub').removeClass('disabled')
            }
        },error:function(){
            weui.Loading.hide();
            weui.Loading.error('系统繁忙稍后再试');
        }
    })
}
//选择规格后的页面改变
function setSelPageInfo(goodsInfo){
    console.log('符合的商品；',goodsInfo);
    $('#control_stock').attr('data-stock',goodsInfo.storeNums);
    $('#control_stock').text('库存'+goodsInfo.storeNums+'件');
    $('#control_sell_price').text("￥"+changeTowDecimal(goodsInfo.sellPrice));
    $('#control_month_price').text("￥"+changeTowDecimal(goodsInfo.monthPrice));
    $('#control_sell_price').attr('data-goodsid',goodsInfo.id)
}

function changeTowDecimal(x){

    var s_x = x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0)
    {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2)
    {
        s_x += '0';
    }
    return s_x;
}