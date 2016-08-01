<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商品统计">-->
<!-- Plugins css -->
<link href="${basepath}/static/assets/notifications/notification.css" rel="stylesheet" />
<link href="${basepath}/static/css/myupload.css"/>
<#--<%-->
<#--SystemSetting ssInfo = SystemManager.systemSetting;-->
<#--if(ssInfo==null){-->
<#--ssInfo = new SystemSetting();-->
<#--}-->
<#--%>-->


<div class="row">

    <div class="col-sm-12">
        <div class="panel">
            <div class="panel-body p-t-10">
                <div class="media-main">
                    <a class="pull-left" href="#">
                        <img class="thumb-lg img-rounded bx-s"  src="${ctxStatic}/img/avatar-2.jpg" alt="">
                    </a>


                    <div class="info">
                        <h4>admin的店铺</h4>
                    </div>

                    <div class="pull-in">
                        <h4>liantong.wokuan.cn</h4>
                    </div>

                </div>

                <div class="pull-right">
                    <button class="-align-right btn btn-success" id="copy-botton">复制链接</button>
                    <button class="-align-right btn btn-default" id="openlink">手机访问</button>
                    <div class="" style="position: absolute;z-index: 99" id="shoplink">
                    <p>
                        <img src="${basepath}/getQrCode?khid=16946474" alt="">
                    </p>
                </div>
                </div>

            </div> <!-- panel-body -->
        </div> <!-- panel -->
    </div>

</div>

<div class="row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <h5 class="panel-title">
                <div>流量趋势
                    <small>
                        <a href="#">
                            详情>>
                        </a>
                    </small>
                    <div class="pull-right">
                        <span class="label label-info" data-html="true" data-toggle="tooltip" data-placement="top" title="" data-original-title="浏览UV:是指你微商城所有页面的访问人数；<br>浏览PV:是指你微商城所有页面的访问次数之和；<br>到店UV：是指你微商城的商品详情页（即单品页）的访问人数；<br>到店PV：是指你微商城的商品详情页（即单品页）的访问次数。">?</span>
                    </div>
                </div>
            </h5>
        </div>
    </div>

</div>


<div class="row text-center">
    <div class="col-lg-2 col-sm-2">
        <div class="widget-panel widget-style-2 white-bg">
            <h2 class="m-0 counter">50</h2>
            <div>昨天PV</div>
        </div>
    </div>
    <div class="col-lg-2 col-sm-2">
        <div class="widget-panel widget-style-2 white-bg">
            <h2 class="m-0 counter">8956</h2>
            <div>昨日UV</div>
        </div>
    </div>
    <div class="col-lg-2 col-sm-2">
        <div class="widget-panel widget-style-2 white-bg">
            <h2 class="m-0 counter">1268</h2>
            <div>昨日到店PV</div>
        </div>
    </div>
    <div class="col-lg-2 col-sm-2">
        <div class="widget-panel widget-style-2 white-bg">
            <h2 class="m-0 counter">145</h2>
            <div>昨日到店UV</div>
        </div>
    </div>
    <div class="col-lg-2 col-sm-2">
        <div class="widget-panel widget-style-2 white-bg">
            <h2 class="m-0 counter">145</h2>
            <div>微页面</div>
        </div>
    </div>
    <div class="col-lg-2 col-sm-2">
        <div class="widget-panel widget-style-2 white-bg">
            <h2 class="m-0 counter">145</h2>
            <div>商品</div>
        </div>
    </div>
</div> <!-- end row -->

<div class="row">
    <div class="panel">

        <div id="main" style="height:400px;"></div>
    </div>

</div>


<div id="con-close-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">上传图片</h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <form class="form-horizontal">
                            <label class="col-md-12 control-label">图片大小不可以大于1M</label>
                            <img src="${basepath}/static/img/add.png" alt="">

                    </form>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-info">保存</button>
            </div>
        </div>
    </div>
</div><!-- /.modal -->

<script type="text/javascript" src="${basepath}/static/js/main/ZeroClipboard.js"></script>
<script src="${basepath}/static/assets/notifications/notify.min.js"></script>
<script src="${basepath}/static/assets/notifications/notify-metro.js"></script>
<script src="${basepath}/static/assets/notifications/notifications.js"></script>

<#--<script src="${basepath}/static/js/jquery.form.js"></script>-->
<#--<script src="${basepath}/static/js/myUpload.js"></script>-->

<script>



    jQuery(document).ready(function($) {
        $('.counter').counterUp({
            delay: 100,
            time: 1200
        });

        window.alert=swal;
        var myChart = echarts.init(document.getElementById('main'));
        myChart.setOption(option);
        $("[data-toggle='tooltip']").tooltip();

        ZeroClipboard.setMoviePath( "${basepath}/manage/shop/shopInfo/ZeroClipboard.swf" );

        var clip = new ZeroClipboard.Client(); // 新建一个对象
        clip.setHandCursor( true ); // 设置鼠标为手型

        clip.addEventListener('complete', copySucc);
        clip.setText("http://liantong.wokuan.cn"); // 设置要复制的文本。
        // 注册一个 button，参数为 id。点击这个 button 就会复制。
        //这个 button 不一定要求是一个 input 按钮，也可以是其他 DOM 元素。
        clip.glue("copy-botton"); // 和上一句位置不可调换

        $("#shoplink").hide();
        $("#openlink").mouseover(function(){
            $("#shoplink").show();
        });
        $("#openlink").mouseout(function(){
            $("#shoplink").hide();
        });

    });
    function copySucc(client, text){
//        alert("成功");
        $.Notification.autoHideNotify('success', 'top center', '提示','复制成功.');
    }


    var option = {
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['浏览PV','浏览UV','到店PV','到店UV']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'浏览PV',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'浏览UV',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'到店PV',
                type:'line',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'到店UV',
                type:'line',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
            }
        ]
    };

</script>

<#--</@page.pageBase>-->