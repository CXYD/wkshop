<!DOCTYPE html>
<html data-dpr="1" style="font-size: 37.5px;"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <meta content="telephone=no" name="format-detection">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Cache" content="no-cache">

    <link href="${basepath}/static/css/front/item.min.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basepath}/static/css/front/weui.css" />
    <script type="text/javascript" src="${basepath}/static/js/front/weui.js"></script>

    <title>商品信息</title>
    <#include "/static/css/tpl/goodsTplCSS.ftl"/>
</head>
<body class="ignore_weixin_login" style="font-size: 12px; overflow: visible;">
<div class="loading hide" id="item_wrap_loading" style="display: block;">&nbsp;</div>
<a href="#yhplace" class="hide" id="couponMiaodian"></a>
<div id="noItem" class="hide">
    <header id="common_hd_none" class="c_txt rel">
        <#--<a id="hd_back_none" class="abs" href="/?userid=771308584">返回</a>-->
        <h1 class="hd_tle bold">&nbsp;</h1>
        <a id="hd_enterShop_none" class="hide abs"><span id="hd_enterShop_none_img" class="abs"><img class="block" src="" width="32" height="32"></span>进入店铺</a>
    </header>
    <div id="noItemShow" class="c_txt">

        你访问的商品已下架。
    </div>
</div>
<#if goodsInfo??>
<div id="item_show_wrap">

    <div class="swiper-container hide"></div>
   <#--<div id="item_wrap_loading" class="loading">&nbsp;</div> -->

    <div id="item_info_for_show_wrap" style="display: none;">
        <section id="item_info" class="rel ">
            <article id="item_show" class="loading c_txt rel">
                <div data-key="0" class="item-img" id="itemImg" data-share-src="Array?w=750&h=0&cp=1"
                     style="background-image: url('${goodsInfo.img}');background-size: cover; background-repeat: no-repeat;">
                </div>
                <div id="itemSlider" class="swiper-container big animated hide">
                    <ul id="itemSliderBox" class="swiper-wrapper">
                        <li data-key="0"class="swiper-slide">
                            <img src="${goodsInfo.img}" class="swiper-img center">
                        </li>                            </ul>
                    <span class="slider-close-btn abs">关闭</span>
                    <div class="slider-counter">
                    </div>
                    <div class="swiper-pagination"></div>
                </div>
                <em id="sold" class="abs hide"></em>
            </article>

            <h2 id="item_name">${goodsInfo.productName}</h2>

            <div id="item_price_wrap" class="rel">
                <p style="color: #d73940;margin-bottom: 8px">
                ${goodsInfo.shortDescription!}
                </p>
                <p class=""style="margin-bottom: 10px;">
                    <span id="seckill_price" class="" style="font-size:13px;color: black">${goodsInfo.prestr!}</span>
                    <span id="item_price" class="i_pri" style="font-size: 14px">${goodsInfo.sellPrice?string("currency")}</span>
                <#--<span>&nbsp;<s>1000</s></span>-->
                </p>

            <#if goodsInfo.type=='1' || goodsInfo.type=='2'>
                <p class="" style="margin-bottom: 10px;">
                    <span id="seckill_price" class="" style="font-size:12px;color: black">月资费:</span>
                    <span id="item_price" class="i_pri" style="font-size: 13px;" data-type="${goodsInfo.type}" data-original-price="131.00">${goodsInfo.monthPrice?string("currency")}</span>
                </p>
            </#if>

                <div >
                <#if goodsInfo.freight==0>
                    <span ><span style="color: #999999">运费:</span>免运费</span>
                <#else>
                    <span ><span style="color: #999999">运费:</span>${goodsInfo.freight}</span>
                </#if>

                <#--<span style="margin-left: 10%">销量:100</span>-->
                 <#if goodsInfo.type!="1" && goodsInfo.type!="2">
                    <span style="margin-left: 10%"><span style="color: #999999">库存:</span>${goodsInfo.storeNums}</span>
                </#if>
                </div>

            <#--<p id="free_delivery" class="promotion-pannel ">-->
            <#--<span class="promotion-left-icon">包邮</span>-->
            <#--<em id="free_delivery_em" class="promotion-right-content">偏远地区除外&nbsp;></em>-->
            <#--</p>-->
                <p id="regionalRestrictions" class="promotion-pannel hide">
                    <span class="promotion-left-icon">限地区</span>
                    <em id="regionalRestrictionsContent" class="promotion-right-content">查看详细可购买地区&nbsp;></em>
                </p>

                <p id="express_money_show"></p>
                <!-- <em id="sold" class="abs">&nbsp;</em> -->
            </div>


        <!-- coupon feature -->
            <div class="item-block hide" style="margin: 0 .4rem" id="salesPromotion" >
                <p style="font-size: 12px;margin-top: 0.2rem;"><span style="color: #999999">促销</span><em>&nbsp;&nbsp; 办理宽带即可享受5折优惠</em></p>
                <div id="item_seller" class="">
                    <a id="seller_wrap" href="#" class="rel for_gaq" style="padding-left: 3.3333333333333rem" >
                        <span style="padding-top: 0.5rem;position: absolute;left: 0.6rem"><input type="checkbox"></span>
                        <div id="seller_thumb_wrap" class="abs" style="left:1.3rem">
                            <img width="100%" id="" src="${basepath}/static/img/front/iptv.png" class="abs"/>
                        </div>
                        <p id="seller_name" class="over_hidden ellipsis" style="font-size: 14px;" >IPTV促销产品</p>
                        <div id="wd_shop_icon">
                            <div class="">&nbsp;<span style="color: red">￥100</span> <s>200</s></div>
                        </div>

                    </a>

                </div>

            </div>


            <div id="control_title" class="item-block hide">
                <div id="show_controls" class="rel">
                    <em id="selected_sku_des_tle" class="abs ellipsis" style="font-size: 12px;"><span style="font-size: 12px;color: #999999">选择</span>&nbsp;请选择规格</em>
                    <p id="selected_sku_des">&nbsp;</p>
                    <em id="show_controls_arrow" class="abs">&nbsp;</em>
                </div>
            </div>
        <#--<section class="guarantee_wrap">-->
        <#--<section id="guarantee" class="guarantee"><span class="guarantee-new-icon">微店担保交易</span></section>-->
        <#--</section>-->
            <div id="item_other_info" class="rel hide">
                <div class="userimgList hide" id="userimgList">
                </div>
            </div>

            <div class="itemrank hide" id='itemrank'></div>
            <p id="yhplace" class="hide"></p>
        </section>



        <section id="item_recommend_wrap" name="moreGoods" class="item-block hide">
            <div id="recommend_wrap_loading" class="loading" style="display: none;">&nbsp;</div>
        </section>

        <section id="item_recommend_wrap" name="recommendGoods" class="item-block hide">
            <div id="recommend_wrap_loading" class="loading" style="display: none;">&nbsp;</div>

        </section>




        <section id="askinfo" class="askinfo hide"></section>
        <#--<section id="item_info_for_common" class="hide item-block">-->
            <#--<div id="comment">-->
                <#--<h2 class="block-title" id="count"></h2>-->
                <#--<ul id="commentList" class="hide">-->
                <#--</ul>-->
                <#--<a id="moreContent" style="display:none" class="for_gaq" data-for-gaq="更多评价" href="/itemComment.html">更多评价</a>-->
            <#--</div>-->
        <#--</section>-->

        <section id="item_detail" class="item-block">
            <div id="detail_wrap">
                <h3 class="block-title" style="font-size: 14px">商品详情</h3>
                <p class="img_des" id="itemDetailName">${goodsInfo.description!""}</p>
                <div id="video_wrap" class="hide">&nbsp;</div>
                <div id="detail_loading" class="loading hide">&nbsp;</div>
            </div>
            <div id="item_recommend_wrap_diy" class="hide"></div>
        </section>

    </div>

        <footer id="item_fix_btn" class="fix hide wrap"><!--底部固定控制栏 之 按钮-->
            <div id="control_btn" class="margin_auto">
                <div id="control_btn_inner" class="rel">
                    <div id="control_btn_inner_left" class="abs">
                        <div class="contact for_gaq" data-for-gaq="客服">
                            <span class="footer-action-icon icon-contact"></span>
                            <span class="hide"></span>客服
                        </div>
                        <div class="goshop for_gaq" id="goshop" data-for-gaq="进店逛">
                            <span class="footer-action-icon icon-goshop"></span>进店
                        </div>

                    </div>

                    <div id="control_btn_inner_right" style="right: 0px" class="abs">
                        <a id="add_cart" class="c_txt abs for_gaq" style="width: 100%;height: 50px;top:-8px;right: 0px; border-radius: 0;line-height: 50px;" data-for-gaq=""><#if goodsInfo.type=='1' || goodsInfo.type=='2'>立即办理<#else>立即购买</#if></a>
                    <#--<a id="buy_now" class="btnok c_txt abs send_gaq">立即购买</a>-->
                        <#--<a id="sellerOut" class="c_txt abs for_gaq" data-for-gaq="已下架">已下架</a>-->
                    </div>
                </div>
            </div>
        </footer>

    <footer id="item_select_sku_count" class="fix hide wrap J-backdrop" style="display: none; opacity: 1; z-index: 99;"><!--底部固定控制栏 之 规格信息-->
        <section id="item_control" class="wrap abs">
            <div id="item_control_inner" class="wrap margin_auto slide-in-down">
                <div id="control_item_info" class="rel">
                    <img id="control_item_info_img" width="60" height="60" class="abs" src="${goodsInfo.img}">
                    <span id="control_seckill_price" class="hide"></span>
                    <span id="" style="float: left;position: absolute;left: 2.666rem;top: 0.2rem;" class="i_pri">${goodsInfo.prestr!} <span id="control_sell_price" data-goodsid="">${goodsInfo.sellPrice?string("currency")}</span></span>
                    <#if  goodsInfo.type=='1'|| goodsInfo.type=='2' >
                    <span id="" style="float: left;position: absolute;left: 2.666rem;top: 0.8rem;" class="i_pri">月资费
                        <span id="control_month_price">${goodsInfo.monthPrice?string("currency")}</span></span>
                    </#if>
                    <div id="control_item_selected_sku" class="hide">已选型号：</div>
                    <div id="control_item_info_close" class="abs close-icon">&nbsp;</div>
                </div>

                <div id="control_wrap">
                    <div id="control_info" style="overflow: auto; max-height: 300px;">


                    </div>
                    <#if goodsInfo.type!="1" && goodsInfo.type!="2">
                        <div id="control_count" class="">
                            <p class="control_tle gray_txt">数量</p>
                            <div id="control_num" class="left c_txt bold rel">
                                <em id="control_num_sub" class="abs disabled">－</em>
                                <input type="number" name="item_num" id="item_num" min="1" class="c_txt block" value="1">
                                <em id="control_num_add" class="abs">＋</em>
                            </div>
                            <div id="control_stock" class="left" data-stock="${goodsInfo.storeNums}">库存${goodsInfo.storeNums}件</div>
                            <div class="clear"></div>
                        </div>
                    </#if>

                </div>
                <a id="control_bottom_submit" class="btnok c_txt" style="background: #ed7c18;border-radius:0px;">确定</a>
            </div>
        </section>
    </footer>
    <p id="wd_footer_logo" class="hide"></p>

</div>
</#if>
<div id="cps_wrap" class="fix hide"></div><!--for cps float-div-->
<form action="${basepath}/product/goodsSubmit" id="selectOrderForm" method="POST">
    <input type="hidden" id="productID" name="productID">
    <input type="hidden" id="goodsID" name="goodsID">
    <input type="hidden" id="khid" name="khid">
    <input type="hidden" id="nums" name="nums" value="1">
    <input type="hidden" name="token" value="${token!""}">
</form>


<script src="${basepath}/static/js/jquery-1.8.3.min.js"></script>


<script>
    var currentGoodsType = 0;
    $(document).ready(function(){
        //判断goodInfo是否存在
        console.log("${goodsInfo?if_exists}"=='');

        if("${goodsInfo?if_exists}"==''){

        }else{
            if("${goodsInfo.productStatus}"=="0"){
                //已下架
                console.log('已下架');
                $('#item_wrap_loading').css('display','none');
                $('#noItem').removeClass('hide');
            }else{
                //加载
                console.log("加载");
                loadGoodSpecInfo();
                loadBuySet();
                loadRecommendGoods();
                $('#item_wrap_loading').css('display','none');
                $('#item_info_for_show_wrap').css('display','block' );

                $('#item_fix_btn').removeClass('hide');
                $('#item_detail').removeClass('hide')
            }
        }

        if('${goodsInfo.type}'!='1' && '${goodsInfo.type}'!='0'){
            if('${goodsInfo.storeNums}'=='0'){
                console.log('缺货');
                $('#add_cart').unbind();
                $('#add_cart').css('background-color','#999');
                $('#add_cart').text('暂时缺货');
            }
        }else{
            currentGoodsType = '${goodsInfo.type}'
        }

    })

    /**
     * 加载促销产品
     */
    function loadSalesPromotion(){

    }

    /**
     * 加载推荐商品
     */
    function loadRecommendGoods(){
        console.log(${goodsInfo.id});
        $('section[name=recommendGoods]').load('/wkshop/product/loadRecommendGoods',{productID:${goodsInfo.id}});
        $('section[name=recommendGoods]').removeClass('hide');
    }

    /**
     * 加载购买页面设置
     */
    function loadBuySet(){
        $.get('/wkshop/manage/generalSet/queryBuySet',{khid:'${goodsInfo.khid}'},function(data,status){
//            console.log('加载规则：',data);
            if(data==""){
                console.log('无规则');
            }else if(data.moreproduct=='ON'){
                console.log('更多推荐');
             $('section[name=moreGoods]').load("/wkshop/product/loadMoreGoods",{type:'${goodsInfo.type}'});
                $('section[name=moreGoods]').removeClass('hide');
            }
        });
    }


    //打开选择规则
    $("#control_title").on('click',function(){
        $('#control_btn_inner_left').css('display','none');
        $("#item_select_sku_count").css('display','block');

    })
    $('#control_item_info_close').on('click',function(){
        $('#control_btn_inner_left').css('display','block');
        $('#item_fix_btn').css('display','block');
        $("#item_select_sku_count").css('display','none');
    })

    var goodDetail ;
    var specCount;

    /**
     * 加载规则
     */
    function loadGoodSpecInfo(){
        $.get("/wkshop/product/goods/<#if goodsInfo??>${goodsInfo.id}</#if>",function(data,status){
            $('#productID').val('${goodsInfo.id}');
            $('#khid').val('${goodsInfo.khid}');
            goodDetail = data;
            console.log("货物详情：",goodDetail);
            var obj =[];
            for(i in data){
                var tmpSpec = $.parseJSON(data[i].specArray);
                if(tmpSpec!=null){
                    specCount = tmpSpec.length
                    obj = obj.concat(tmpSpec);
                }
            }
            console.log('必选个数：',specCount);
            if(specCount>0){
                $('#control_title').removeClass('hide')
            }else{
                $('#goodsID').val(goodDetail[0].id);
            }

            var tmp =[];
            for(j in obj){
                var o = obj[j];
                if(typeof(tmp[o.id])!='object'){
                    tmp[o.id]={id: o.id,name: o.name,value: [o.value]}
                }else{
                    if(tmp[o.id].value.indexOf(o.value)==-1)
                        tmp[o.id].value.push(o.value);
                }

            }


            var sku = "";
            for(x in tmp){
                sku = sku + '<div class="control_sku"><p class="control_tle gray_txt">'+tmp[x].name+'</p> <ul id="sku_ul">';
                var tmp_arr = tmp[x].value;
                for(y in tmp_arr){

                    sku = sku + '<li class="sku_li"> <a class="sku_a " data-index="'+tmp[x].id+'" data-type="multisku" data-attrid="6639">'+tmp_arr[y]+'</a> </li>'
                }

                sku = sku + ' </ul> </div>'
            }

            $('#control_info').html(sku);

        })


    }





</script>
<script src="${basepath}/static/js/jquery.cookie.js"></script>
<script src="${basepath}/static/js/front/webapp.js?t=<@urlTimestamp/>"></script>
</body>
</html>