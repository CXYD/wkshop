<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="页面管理">-->
<link rel="stylesheet" href="${basepath}/static/css/microshop.css"  type="text/css">
<script type="text/javascript" src="${basepath}/static/js/microshop.js"></script>
<script type="text/javascript" src="${basepath}/static/js/scrollNotice.js"></script>

<link href="${basepath}/static/css/tpl.css" rel="stylesheet">
<#--<link href="${basepath}/static/css/index.css" rel="stylesheet">-->
<style>
    /* 预览标题*/
    .dcr-phone-title {
        position: absolute;
        left: 0;
        right: 0;
        bottom: 0;
        height: 30px;
        line-height: 33px;
        text-align: center;
        color: #FFFFFF;
    }
    .module_701 {
        overflow: hidden;
        padding-top: 8px;
    }

    .dcr-shower {
        -webkit-user-select: none;
        -moz-user-select: none;
        -o-user-select: none;
        user-select: none;
        overflow-x: hidden;
        overflow-y: auto;
        box-sizing: border-box;
        padding-bottom: 85px;
        /*width: 500px;*/
        height: 100%;
        float: left;
        border-right: 1px solid #dcdcdc;
        background: #f8f8f8;
    }

    .dcr-phone {
        width: 320px;
        border: 1px solid #dcdcdc;
        min-height: 568px;
        /*background: #eeeeee;*/
        margin: 40px auto 0;
        position: relative;
        padding-bottom: 170px;
    }


</style>

<script>!function(){var e="@charset \"utf-8\";html{color:#000;overflow-y:scroll;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}html *{outline:0;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0)}html,body{font-family:Arial, Microsoft YaHei}body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td,hr,button,article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{margin:0;padding:0}input,select,textarea{font-size:100%}table{border-collapse:collapse;border-spacing:0}fieldset,img{border:0}abbr,acronym{border:0;font-variant:normal}del{text-decoration:line-through}address,caption,cite,code,dfn,em,th,var{font-style:normal;font-weight:500}ol,ul{list-style:none}caption,th{text-align:left}h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:500}q:before,q:after{content:''}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}sup{top:-.5em}sub{bottom:-.25em}a:hover{text-decoration:none}ins,a{text-decoration:none;color: #000;}",t=document.createElement("style");if(document.getElementsByTagName("head")[0].appendChild(t),t.styleSheet)t.styleSheet.disabled||(t.styleSheet.cssText=e);else try{t.innerHTML=e}catch(i){t.innerText=e}}(),function(e,t){function i(){var t=o.getBoundingClientRect().width;t/s>540&&(t=540*s);var i=t/10;o.style.fontSize=i+"px",c.rem=e.rem=i}var a,n=e.document,o=n.documentElement,r=n.querySelector('meta[name="viewport"]'),l=n.querySelector('meta[name="flexible"]'),s=0,d=0,c=t.flexible||(t.flexible={});if(r){console.warn("将根据已有的meta标签来设置缩放比例");var m=r.getAttribute("content").match(/initial\-scale=([\d\.]+)/);m&&(d=parseFloat(m[1]),s=parseInt(1/d))}else if(l){var h=l.getAttribute("content");if(h){var p=h.match(/initial\-dpr=([\d\.]+)/),u=h.match(/maximum\-dpr=([\d\.]+)/);p&&(s=parseFloat(p[1]),d=parseFloat((1/s).toFixed(2))),u&&(s=parseFloat(u[1]),d=parseFloat((1/s).toFixed(2)))}}if(!s&&!d){var f=(e.navigator.appVersion.match(/android/gi),e.navigator.appVersion.match(/iphone/gi)),b=e.devicePixelRatio;s=f?b>=3&&(!s||s>=3)?3:b>=2&&(!s||s>=2)?2:1:1,s=1,d=1/s}if(o.setAttribute("data-dpr",s),!r)if(r=n.createElement("meta"),r.setAttribute("name","viewport"),r.setAttribute("content","initial-scale="+d+", maximum-scale="+d+", minimum-scale="+d+", user-scalable=no"),o.querySelector("head"))o.querySelector("head").appendChild(r);else{var g=n.createElement("div");g.appendChild(r),n.write(g.innerHTML)}e.addEventListener("resize",function(){clearTimeout(a),a=setTimeout(i,300)},!1),e.addEventListener("pageshow",function(e){e.persisted&&(clearTimeout(a),a=setTimeout(i,300))},!1),"complete"===n.readyState?n.body.style.fontSize=12*s+"px":n.addEventListener("DOMContentLoaded",function(e){n.body.style.fontSize=12*s+"px"},!1),i(),c.dpr=e.dpr=s,c.refreshRem=i,c.rem2px=function(e){var t=parseFloat(e)*this.rem;return"string"==typeof e&&e.match(/rem$/)&&(t+="px"),t},c.px2rem=function(e){var t=parseFloat(e)/this.rem;return"string"==typeof e&&e.match(/px$/)&&(t+="rem"),t}}(window,window.lib||(window.lib={}));</script>
<div class="row">
    <div class="col-xs-6 col-md-4 dcr-shower">
        <div class="panel panel-default" style="float: left;">
            <div class="dcr-phone">

                <div id="background" class="parent_relative" >
                    <img src="${basepath}/static/img/microshop/phone_head.jpg" width="100%" style="display: block">
                    <p class="dcr-phone-title" id="pre_title">页面标题</p>
                    <#--<a class="dcr-title-btn" onclick="">设置</a>-->
                </div>

                <div id="container"  style="overflow:hidden">

                </div>

            </div>



            <#--<div class="xdycmb" id="kk" style="display:none;">-->
                <#--<div class="eq11a"></div>-->
            <#--</div>-->
        </div>
    </div> <#--左侧-->

    <div class="col-xs-6 col-md-8">

        <div class="panel panel-default" >

            <div class=" row">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-md-2 control-label">页面标题</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" maxlength="30" id="title" name="title" data-rule="标题:required;" value="页面标题" onkeyup="keyP(this)" placeholder="请输入标题名称...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">页面描述</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" maxlength="500" id="dsp" name="dsp" data-rule="描述:required;"  placeholder="请输入页面描述...">
                        </div>
                    </div>
                </form>
            </div>
            <form id="rightForm">
                <div id="base">

                </div>
            </form>
        </div>
    </div>

</div>






</@page.pageBase>

<div class="footer" style="background: RGBA(0,0,0,.8);position: fixed;z-index: 1099; height:60px;">
    <form action="previewPage" id="previewForm" target="_blank" method="post">
        <input type="hidden" id="id" name="id" value="${id!''}">
        <input type="hidden" id="pageid" name="pageid" value="${pageid!''}">
        <input name="pageContent"  id="pageContent" value="" type="hidden">
    </form>
    <div class="col-md-6 pull-right">
        <button type="button" class="btn  m-b-15" id="btnPreView" disabled="disabled"  onclick="previewPage()">预览页面</button>
        <button type="button" class="btn btn-info m-b-15"  onclick="savePage()">保存页面</button>
    </div>
</div>
<#--<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>-->
<#--<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>-->



<script>
    $.validator.setDefaults({
        submitHandler: function() {
//            alert("提交事件!");
        }
    });




</script>
