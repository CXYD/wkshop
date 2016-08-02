<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Cache" content="no-cache">

    <meta content="telephone=no" name="format-detection">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>首页</title>
    <!-- Bootstrap core CSS -->
    <link href="${basepath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basepath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <!-- Custom styles for this template -->
<#--<link href="${basepath}/static/css/style.css" rel="stylesheet">-->
    <link href="${basepath}/static/css/helper.css" rel="stylesheet">

<#--<link href="${basepath}/static/css/tpl.css" rel="stylesheet">-->
    <link href="${basepath}/static/css/index.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/page.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_200/tpl.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_201/tpl.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_202/tpl.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_301/tpl.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_302/tpl.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_303/tpl.css" rel="stylesheet">
<#--<link href="${basepath}/static/css/tpl/module_501/tpl.css" rel="stylesheet">-->
    <link href="${basepath}/static/css/tpl/module_502/tpl.css" rel="stylesheet">
<#--<link href="${basepath}/static/css/tpl/module_503/tpl.css" rel="stylesheet">-->

    <link href="${basepath}/static/css/tpl/module_504/tpl.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_505/tpl.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_701/tpl.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_702/tpl.css" rel="stylesheet">
    <link href="${basepath}/static/css/tpl/module_503/banner.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${basepath}/static/css/front.css"/>



    <script src="${basepath}/static/js/jquery.js"></script>
    <script src="${basepath}/static/js/pace.min.js"></script>
<#--<script src="${staticpath}/js/jquery-1.9.1.min.js"></script>-->
    <script src="${basepath}/static/js/bootstrap.min.js"></script>
    <script src="${basepath}/static/js/template.js"></script>
    <script src="${basepath}/static/js/page/unslider.min.js"></script>
    <script src="${basepath}/static/js/page/jquery.event.move.js"></script>
    <script src="${basepath}/static/js/page/jquery.event.swipe.js"></script>


    <script>!function(){var e="@charset \"utf-8\";html{color:#000;overflow-y:scroll;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}html *{outline:0;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0)}html,body{font-family:Arial, Microsoft YaHei}body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td,hr,button,article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{margin:0;padding:0}input,select,textarea{font-size:100%}table{border-collapse:collapse;border-spacing:0}fieldset,img{border:0}abbr,acronym{border:0;font-variant:normal}del{text-decoration:line-through}address,caption,cite,code,dfn,em,th,var{font-style:normal;font-weight:500}ol,ul{list-style:none}caption,th{text-align:left}h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:500}q:before,q:after{content:''}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}sup{top:-.5em}sub{bottom:-.25em}a:hover{text-decoration:none}ins,a{text-decoration:none;color: #000;}",t=document.createElement("style");if(document.getElementsByTagName("head")[0].appendChild(t),t.styleSheet)t.styleSheet.disabled||(t.styleSheet.cssText=e);else try{t.innerHTML=e}catch(i){t.innerText=e}}(),function(e,t){function i(){var t=o.getBoundingClientRect().width;t/s>540&&(t=540*s);var i=t/10;o.style.fontSize=i+"px",c.rem=e.rem=i}var a,n=e.document,o=n.documentElement,r=n.querySelector('meta[name="viewport"]'),l=n.querySelector('meta[name="flexible"]'),s=0,d=0,c=t.flexible||(t.flexible={});if(r){console.warn("将根据已有的meta标签来设置缩放比例");var m=r.getAttribute("content").match(/initial\-scale=([\d\.]+)/);m&&(d=parseFloat(m[1]),s=parseInt(1/d))}else if(l){var h=l.getAttribute("content");if(h){var p=h.match(/initial\-dpr=([\d\.]+)/),u=h.match(/maximum\-dpr=([\d\.]+)/);p&&(s=parseFloat(p[1]),d=parseFloat((1/s).toFixed(2))),u&&(s=parseFloat(u[1]),d=parseFloat((1/s).toFixed(2)))}}if(!s&&!d){var f=(e.navigator.appVersion.match(/android/gi),e.navigator.appVersion.match(/iphone/gi)),b=e.devicePixelRatio;s=f?b>=3&&(!s||s>=3)?3:b>=2&&(!s||s>=2)?2:1:1,s=1,d=1/s}if(o.setAttribute("data-dpr",s),!r)if(r=n.createElement("meta"),r.setAttribute("name","viewport"),r.setAttribute("content","initial-scale="+d+", maximum-scale="+d+", minimum-scale="+d+", user-scalable=no"),o.querySelector("head"))o.querySelector("head").appendChild(r);else{var g=n.createElement("div");g.appendChild(r),n.write(g.innerHTML)}e.addEventListener("resize",function(){clearTimeout(a),a=setTimeout(i,300)},!1),e.addEventListener("pageshow",function(e){e.persisted&&(clearTimeout(a),a=setTimeout(i,300))},!1),"complete"===n.readyState?n.body.style.fontSize=12*s+"px":n.addEventListener("DOMContentLoaded",function(e){n.body.style.fontSize=12*s+"px"},!1),i(),c.dpr=e.dpr=s,c.refreshRem=i,c.rem2px=function(e){var t=parseFloat(e)*this.rem;return"string"==typeof e&&e.match(/rem$/)&&(t+="px"),t},c.px2rem=function(e){var t=parseFloat(e)/this.rem;return"string"==typeof e&&e.match(/px$/)&&(t+="rem"),t}}(window,window.lib||(window.lib={}));</script>

</head>
<body class="body default" style="font-size: 12px; background-color: rgb(238, 238, 238);">
<input type="hidden" name="homePageId" id="pageid" value="${pageId!''}">


<div id="content" class="content"></div>




<script type="text/template" id="module_1">
    <div class="module_1" style="{{styleText}}">{{moduleName}}</div>
</script>
<script type="text/template" id="module_undefined"></script>
<script id="module_200" type="text/template">
    <div class="module_200">
        <a class="module_200-shop" href="#">
            <span class="module_200-shop-item module_200-headimg" >
                <img style="margin-top: 8px;" src="/wkshop/static/img/microshop/dpzxsyt-29.png">
            </span>
            <span class="module_200-shop-item module_200-shopname">
                <marquee behavior="scroll"  direction="left" align="middle">{{title}}</marquee>
            </span>

        </a>
    </div>
</script>
<script id="module_201" type="text/template"><div class="module_201">
<form class="search-box dcr-search-form" action="http://weidian.com/search_result.html">
    <input name="userid" class="dcr-search-name" value="{{userID}}" type="hidden">
    <input type="submit" class="search-btn" value=""/>
    <input name="tb_search" class="search-key" type="search" placeholder="商品搜索：请输入商品关键字" >
</form>
</div>
</script>
<script type="text/template" id="module_202"><div class="module_202">
<a class="module_202-shop" href="{{shopUrl}}">
    <span class="module_202-shop-item module_202-headimg"><img src="${basepath}/static/img/microshop/shop-logo-default-2.jpg"></span>
    <span class="module_202-shop-item module_202-shopname">{{shopName}}</span>
    <span class="module_202-shop-item module_202-btn">进入店铺</span>
</a>
</div>
</script>
<script id="module_301" type="text/template">
    <div class="module_301">
        {{each content}}
        <div class="module_301-word-nav">
            <a {{if $value.link.url}}href="{{$value.link.url}}"{{/if}}>
            <div class="module_301-name">{{$value.name}}</div>
            </a>
        </div>
        {{/each}}
    </div></script><script id="module_302" type="text/template"><div class="module_302">
<div class="module_302-title">{{title}}</div>
<div class="module_302-foot">
    <a>
        <span>{{date}}</span>
        <span>{{author}}</span>
    </a>
    <a {{if link.url}}href="{{link.url}}"{{/if}} class="module_302-link-title">{{link_title}}</a>
</div>
</div></script><script id="module_501" type="text/template"><div class="module_501">
{{if show_gap == true}}
<div class="show-gap">
    {{each content}}
    <a class="no-gap" {{if $value.link.url}}href="{{$value.link.url}}"{{/if}} >
    <img src="{{$value.image}}" />
    </a>
    {{/each}}
</div>
{{/if}}
{{if show_gap == false}}
{{each content}}
<a {{if $value.link.url}}href="{{$value.link.url}}"{{/if}} class="no-gap">
<img src="{{$value.image}}"  />
</a>
{{/each}}
{{/if}}
</div></script><script id="module_502" type="text/template"><div class="module_502">
<ul class="{{if show_gap == true}}module_502_gap{{else}}module_502_no_gap{{/if}}">
    {{each content }}
    <li>
        <a {{if $value.link}}href="{{$value.link.url}}"{{/if}}>
        <div class="module_502-pic" style="background:url('{{$value.image}}') no-repeat center center;background-size:cover">
            <div style="margin-top: 75%;"></div>
        </div>
        </a>
    </li>
    {{/each}}
</ul>
</div></script>
<script id="module_503" type="text/template">
    <div class="banner">
        <ul>
            {{each content}}
            {{if $value.image}}
            <li>
                <a {{if $value.link}}href="{{$value.link.url}}"{{/if}}>
                <img  src="{{$value.image}}"/>
                </a>
            </li>
            {{/if}}
            {{/each}}
        </ul>
        <ol class="dots">
            {{each content}}
            {{if $value.image}}
            {{if $index==0}}
            <li class="dot active">1</li>
            {{else}}
            <li class="dot">1</li>
            {{/if}}
            {{/if}}
            {{/each}}
        </ol>

    </div>
</script>
<script id="module_504" type="text/template">
    <div class="module_504">

        {{each content}}
        {{$value.link}}
        <div class="show-box">
            <a  class="show-name" {{if $value.link}} href="{{$value.link.url}}" {{/if}}>
            <img src={{$value.image}} class="image"/>
            <p>{{$value.title}}</p>
            </a>
        </div>
        {{/each}}


    </div>
</script>
<script type="text/template" id="module_505"><div class="module_505">
<div class="module_505-map">
    <a href="http://apis.map.qq.com/tools/poimarker?type=0&marker=coord:{{lat}},{{lng}};title:{{notice}}&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp" alt="地图">
        <img src="http://apis.map.qq.com/ws/staticmap/v2/?key=KUMBZ-WL4WV-TZJPH-UOMEM-D452E-4GFWB&size=750*320&center={{lat}},{{lng}}&markers=color:blue|label:A|{{lat}},{{lng}}&zoom=13"></img>
    </a>
    {{if notice}}<div class="module_505-notice">{{notice}}</div>{{/if}}
</div>
</div>
</script>
<script id="module_701" type="text/template"><div class="module_701">
{{if options.title}}
<div class="model_701-title {{if options.title_pos=='center'}}model_701-title-center{{/if}}" >{{options.title}}</div>
{{/if}}

{{if options.model == 1}}

<div class="single-line style_{{options.style}}">
    {{each content}}
    <a class="item" href="{{$value.h5url}}">
        <div class="module_701-item-image">
            <img src="{{$value.url}}">
            {{if options.show_item_sale}}
            <p class="module_701-item-sold">销量{{$value.sold}}</p>
            {{/if}}
        </div>
        {{if options.show_item_name && options.show_item_price}}
        <p class="module_701-item-title">{{$value.name}}</p>
        <p class="module_701-item-price">¥ {{$value.price}}</p>

        {{else if options.show_item_name && !options.show_item_price}}
        <p class="module_701-item-title module_701-item-only-one">{{$value.itemName}}</p>

        {{else if !options.show_item_name && options.show_item_price}}
        <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
        {{/if}}


    </a>
    {{/each}}
</div>

{{else if options.model == 2}}

<ul class="double-line style_{{options.style}}">
    {{each content}}
    <li class="item-wrap">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale && options.style != 3}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name && options.show_item_price}}
            <p class="module_701-item-title">{{$value.itemName}}</p>
            <p class="module_701-item-price">¥ {{$value.price}}</p>

            {{else if options.show_item_name && !options.show_item_price}}
            <p class="module_701-item-title module_701-item-only-one">{{$value.itemName}}</p>

            {{else if !options.show_item_name && options.show_item_price}}
            <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </li>
    {{/each}}
</ul>
<div style="clear:both;"></div>
{{else}}
<div class="three-line">
    {{each content}}
    <div class="item-wrap">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name}}
            <p class="module_701-item-title">{{$value.itemName}}</p>
            {{/if}}
            {{if options.show_item_price}}
            <p class="module_701-item-price">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </div>
    {{/each}}
</div>

{{/if}}
</div></script>
<script id="module_702" type="text/template"><div class="module_702">
{{if options.title}}
<div class="model_701-title {{if options.title_pos=='center'}}model_701-title-center{{/if}}" >{{options.title}}</div>
{{/if}}

{{if options.model == 1}}

<div class="single-line style_{{options.style}}">
    {{each content}}
    <a class="item" href="{{$value.h5url}}">
        <div class="module_701-item-image">
            <img src="{{$value.src_img}}">
            {{if options.show_item_sale}}
            <p class="module_701-item-sold">销量{{$value.sold}}</p>
            {{/if}}
        </div>
        {{if options.show_item_name && options.show_item_price}}
        <p class="module_701-item-title">{{$value.itemName}}</p>
        <p class="module_701-item-price">¥ {{$value.price}}</p>

        {{else if options.show_item_name && !options.show_item_price}}
        <p class="module_701-item-title module_701-item-only-one">{{$value.itemName}}</p>

        {{else if !options.show_item_name && options.show_item_price}}
        <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
        {{/if}}


    </a>
    {{/each}}
</div>

{{else if options.model == 2}}

<ul class="double-line style_{{options.style}}">
    {{each content}}
    <li class="item-wrap">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale && options.style != 3}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name && options.show_item_price}}
            <p class="module_701-item-title">{{$value.name}}</p>
            <p class="module_701-item-price">¥ {{$value.price}}</p>

            {{else if options.show_item_name && !options.show_item_price}}
            <p class="module_701-item-title module_701-item-only-one">{{$value.itemName}}</p>

            {{else if !options.show_item_name && options.show_item_price}}
            <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </li>
    {{/each}}
</ul>
<div style="clear:both;"></div>
{{else}}
<div class="three-line">
    {{each content}}
    <div class="item-wrap">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name}}
            <p class="module_701-item-title">{{$value.itemName}}</p>
            {{/if}}
            {{if options.show_item_price}}
            <p class="module_701-item-price">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </div>
    {{/each}}
</div>

{{/if}}

<#--{{if options.show_more}}-->
<#--<p class="show-more"><a href="{{options.show_more_url}}" class="btn-show-more" data-id="{{options.category_id}}" data-current="{{options.category_length}}" data-userid="{{options.userid}}">更多商品</a></p>-->
<#--{{/if}}-->
</div>
</script>
<script id="module_703" type="text/template"><div class="module_701">
{{if options.title}}
<div class="model_701-title {{if options.title_pos=='center'}}model_701-title-center{{/if}}" >{{options.title}}</div>
{{/if}}

{{if options.model == 1}}

<div class="single-line style_{{options.style}}">
    {{each content}}
    <a class="item" href="{{$value.h5url}}">
        <div class="module_701-item-image">
            <img src="{{$value.src_img}}">
            {{if options.show_item_sale}}
            <p class="module_701-item-sold">销量{{$value.sold}}</p>
            {{/if}}
        </div>
        {{if options.show_item_name && options.show_item_price}}
        <p class="module_701-item-title">{{$value.itemName}}</p>
        <p class="module_701-item-price">¥ {{$value.price}}</p>

        {{else if options.show_item_name && !options.show_item_price}}
        <p class="module_701-item-title module_701-item-only-one">{{$value.name}}</p>

        {{else if !options.show_item_name && options.show_item_price}}
        <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
        {{/if}}


    </a>
    {{/each}}
</div>

{{else if options.model == 2}}

<ul class="double-line style_{{options.style}}">
    {{each content}}
    <li class="item-wrap">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale && options.style != 3}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name && options.show_item_price}}
            <p class="module_701-item-title">{{$value.itemName}}</p>
            <p class="module_701-item-price">¥ {{$value.price}}</p>

            {{else if options.show_item_name && !options.show_item_price}}
            <p class="module_701-item-title module_701-item-only-one">{{$value.itemName}}</p>

            {{else if !options.show_item_name && options.show_item_price}}
            <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </li>
    {{/each}}
</ul>
<div style="clear:both;"></div>
{{else}}
<div class="three-line">
    {{each content}}
    <div class="">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name}}
            <p class="module_701-item-title">{{$value.name}}</p>
            {{/if}}
            {{if options.show_item_price}}
            <p class="module_701-item-price">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </div>
    {{/each}}
</div>

{{/if}}
</div></script>
<script type="text/template" id="module_97"><div class="module_97">
<div class="module_97-line" style="height:{{height}};"></div>
</div>
</script>
<script type="text/template" id="module_98">
    <div class="module_98">
<div class="module_98-line"></div>
</div></script>
<script id="module_99" type="text/template">
    <div class="module_99" id="module_99_{{id}}" style="background-color:{{backgroundColor}};">
{{if content}}
<div>{{#content}}</div>
{{/if}}
</div></script>


<script>
    <#--$(document).ready('${basepath}/manage/pageManage/tpl');-->

    $(function(){

        initHomePage();


//        $("div[name='footer']").on('click',function(){
//            var page =  $(event.target).attr('data-name')
//            if(page !=undefined){
//                if(page=='home'){
//                    $("div#content").empty();
//                    initHomePage();
//                }else{
//                    $("div#content").load(page);
//                }
//            }
//
//        })
    });


    function initHomePage(){
        $.ajax({
            url:'${basepath}/manage/pageManage/queryTpl',
            data:{id:$("#pageid").val()},
            success:function(page){
                console.log("查询返回",page);
                setPageInfo($.parseJSON( page.pagecontent));
                $('.banner').unslider();
            }
        })
    }

</script>
<script src="${basepath}/static/js/page/tpl.js"></script>


<div class="quick-nav" name="footer">
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/homePage?khid=${khid}"><img <#--data-name="home"--> src="${basepath}/static/img/front/wscqt6-8-50.png" width="25"></a> </div>
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/classPage?khid=${khid}"><img  <#--data-name="classPage"--> src="${basepath}/static/img/front/wscqt6-8-47.png" width="25"></a> </div>
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/findPage?khid=${khid}"><img <#--data-name="findPage"-->
            src="${basepath}/static/img/front/wscqt6-8-48.png" width="25"></a> </div>
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/myPage?khid=${khid}"><img <#--data-name="myPage"--> src="${basepath}/static/img/front/wscqt6-8-49.png" width="25"></a> </div>
</div>

</body>
</html>