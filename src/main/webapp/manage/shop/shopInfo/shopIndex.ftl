<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商城概况">-->
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
                    <div class="" style="position: absolute;z-index: 99" id="shoplink" hidden>
                        <p>
                            <img src="${basepath}/getQrCode?khid=16946474" alt="">
                        </p>
                    </div>
                </div>

            </div> <!-- panel-body -->
        </div> <!-- panel -->
    </div>
    <#--<div class="pull-right">-->
        <#--<span class="label label-info" data-html="true" data-toggle="tooltip" data-placement="top" title="" data-original-title="浏览UV:是指你微商城所有页面的访问人数；<br>浏览PV:是指你微商城所有页面的访问次数之和；<br>到店UV：是指你微商城的商品详情页（即单品页）的访问人数；<br>到店PV：是指你微商城的商品详情页（即单品页）的访问次数。">?</span>-->
    <#--</div>-->
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

        <div class="pannel-body">

            <!-- Tabs-style-1 -->
            <div class="row">

                <div class="col-lg-12">
                    <ul class="nav nav-tabs nav-justified" id="pTab">
                        <li class="active">
                            <a href="#chart-1" data-toggle="tab"  aria-expanded="true">
                                <span class="visible-xs"><i class="fa fa-home"></i></span>
                                <span class="hidden-xs">商品热度</span>
                            </a>
                        </li>
                        <li class="">
                            <a id="chart_2" href="#chart-2" data-toggle="tab" aria-expanded="false">
                                <span class="visible-xs"><i class="fa fa-user"></i></span>
                                <span class="hidden-xs">商品统计</span>
                            </a>
                        </li>
                        <li class="">
                            <a id="chart_3" href="#chart-3" data-toggle="tab" aria-expanded="false">
                                <span class="visible-xs"><i class="fa fa-envelope-o"></i></span>
                                <span class="hidden-xs">访问流量</span>
                            </a>
                        </li>

                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="chart-1">
                            <div class="panel panel-default">
                               <div class="panel-heading">
                                   <h5>商品销量排行</h5>
                               </div>

                                <div class="panel-body">

                                <div class="form-group">
                                        选择月份
                                        <select id="sel_moon1">
                                            <#list moons as moon>
                                            <option value="${moon}">${moon}</option>
                                            </#list>
                                        </select>
                                </div>
                                <#--<div class="panel-body">-->
                                    <table class="table table-bordered">
                                        <tr>
                                            <td>
                                                <div id="chart1" style="width: 600px;height:400px;"></div>
                                            </td>
                                            <td>
                                                <div class="col-md-12">
                                                    <div class="row">
                                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                                            <table class="table table-hover">
                                                                <thead>
                                                                <tr>
                                                                    <th>排名</th>
                                                                    <th>商品名</th>
                                                                    <th>订单占比</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <tr>
                                                                    <td>1</td>
                                                                    <td>牛仔裤</td>
                                                                    <td>30%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>2</td>
                                                                    <td>鞋子</td>
                                                                    <td>20%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>3</td>
                                                                    <td>衬衫</td>
                                                                    <td>18%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>4</td>
                                                                    <td>外套</td>
                                                                    <td>12%</td>
                                                                </tr>


                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>



                               </div> <#--panel body</div>-->
                            </div>
                        </div>
                        <div class="tab-pane" id="chart-2">

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5>商品销量统计</h5>
                                </div>

                                <div class="panel-body">
                                <div class="from-group">
                                        选择月份
                                        <select id="sel_moon2">
                                            <#list moons as moon>
                                                <option value="${moon}">${moon}</option>
                                            </#list>
                                        </select>
                                        选择商品
                                        <select id="sel_good2">
                                            <option value="1">手机</option>
                                            <option value="2">笔记本</option>
                                        </select>
                                    &nbsp;&nbsp;&nbsp;&nbsp
                                    <button id="bt_look" class="btn btn-warning btn-sm">
                                        <i class="icon-edit icon-white"></i> 查看
                                    </button>
                                </div>
                                    <div id="chart2" style="width: 800px;height:400px;"></div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">

                                            <table class="table table-hover">

                                                <caption>
                                                   <pre> 详细数据 </pre>
                                                </caption>

                                                <thead>
                                                <tr>
                                                    <th>日期</th>
                                                    <th>商品订单提交量</th>

                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>201606</td>
                                                    <td>23423</td>

                                                </tr>
                                                <tr>
                                                    <td>201605</td>
                                                    <td>23412</td>
                                                </tr>
                                                <tr>
                                                    <td>201604</td>
                                                    <td>23432</td>
                                                </tr>
                                                <tr>
                                                    <td>201603</td>
                                                    <td>52345</td>
                                                </tr>


                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>  <#--panel end -->

                        </div>

                        <div class="tab-pane " id="chart-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">

                                    <h5>月度流量趋势</h5>
                                </div>
                                <div class="panel-body">
                                    <ul>
                                        选择月份
                                        <select id="sel_moon3">
                                            <#list moons as moon>
                                                <option value="${moon}">${moon}</option>
                                            </#list>
                                        </select>

                                    </ul>
                                </div>
                                    <div id="chart3" style="width: 800px;height:400px;"></div>
                            </div>


                        </div>

                    </div>
                </div> <!-- End row -->


            </div>
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
            var lastMoon=${lastMoon};

            ranks(lastMoon);

            $("#chart_2").click(function () {
                $.ajax({
                    url:"${basepath}/manage/shopInfo/statistics",
                    async:false,
                    success:function (data) {
                        var p1=document.getElementById("sel_good2");
                        document.getElementById("sel_good2").options.length=0;
                        var op1=document.createElement("option");
                        op1.innerHTML="--请选择--";
                        p1.appendChild(op1);
                        for ( var i = 0; i < data[0].length; i++) {
                            var op1=document.createElement("option");
                            op1.innerHTML=data[0][i];
                            p1.appendChild(op1);
                        }

                    },
                    error:function(){
                        swal("失败","加载失败","error");
                    }
                });
            });


            $("[data-toggle='tooltip']").tooltip();

            ZeroClipboard.setMoviePath( "${basepath}/manage/shop/shopInfo/ZeroClipboard.swf" );

            var clip = new ZeroClipboard.Client(); // 新建一个对象
            clip.setHandCursor( true ); // 设置鼠标为手型

            clip.addEventListener('complete',copySucc);
            clip.setText("http://liantong.wokuan.cn"); // 设置要复制的文本。
            // 注册一个 button，参数为 id。点击这个 button 就会复制。
            //这个 button 不一定要求是一个 input 按钮，也可以是其他 DOM 元素。
            clip.glue("copy-botton"); // 和上一句位置不可调换

            $("#openlink").mouseover(function(){
                $("#shoplink").show();
            });
            $("#openlink").mouseout(function(){
                $("#shoplink").hide();
            });

            $("#chart_3").click(function(){
                flowrate(lastMoon);
            });
            $("#sel_moon3").change(function(){
                var moon=$("#sel_moon3 option:selected").text();
                flowrate(moon);
            });
            function flowrate(moon) {
                var myChart3 = echarts.init(document.getElementById('chart3'));
                var chart3 = {
                    title: {
                        text: ''
                    },
                    tooltip: {trigger: 'axis'},
                    legend: {
                        data:['PV','UV']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis: {
                        data:[]
                    },
                    yAxis:  [
                        {
                            type : 'value'
                        }
                    ],
                    series: []
                };

                $.ajax({
                    url:'${basepath}/manage/shopInfo/flowrate',
                    data:{'moon':moon},
                    async:false,
                    success:function(data){
                        // 后台循环
                        var xAxisData = [];
                        xAxisData.push({
                            type : 'category',
                            boundaryGap : false,
                            axisLabel:{
                                interval:0
                            },
                            data:data[0]
                        });
                        var seriesData = [];
                        seriesData.push({
                                    name:"PV",
                                    type:"line",
                                    stack:'总量',
                                    data:data[1]
                                },
                                {
                                    name:"UV",
                                    type:"line",
                                    stack:'总量',
                                    data:data[2]
                                });
                        chart3.xAxis=xAxisData;
                        chart3.series=seriesData;
                        myChart3.setOption(chart3);

                    },
                });
                myChart3.setOption(chart3);
            }

            $("#bt_look").click(function(){
                var moon=$("#sel_moon2 option:selected").text();
                var good=$("#sel_good2 option:selected").text();
                statistics(moon,good);
            });
            function statistics(moon,good) {
                var myChart2 = echarts.init(document.getElementById('chart2'));
                var chart2 = {
                    title: {
                        text: ''
                    },
                    tooltip: {trigger: 'axis'},
                    legend: {
                        data:['商品提交订单量']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis: {
                        data:[]
                    },
                    yAxis:  [
                        {
                            type : 'value'
                        }
                    ],
                    series: []
                };

                $.ajax({
                    url:'${basepath}/manage/shopInfo/look',
                    data:{'moon':moon,'good':good},
                    async:false,
                    success:function(data){
                        //                   后台循环
                        var xAxisData = [];
                        xAxisData.push({
                            type : 'category',
                            boundaryGap : false,
                            axisLabel:{
                                interval:0
                            },
                            data:data[0]
                        });
                        var seriesData = [];
                        seriesData.push({
                            name:"商品提交订单量",
                            type:"line",
                            stack:'总量',
                            data:data[1]
                        });
                        chart2.xAxis=xAxisData;
                        chart2.series=seriesData;
                        myChart2.setOption(chart2);

                    },
                });
                myChart2.setOption(chart2);
            }


            $("#sel_moon1").change(function(){
                var moon=$(this).find("option:selected").text();
                ranks(moon);
            });
            function ranks(moon) {
                var myChart1 = echarts.init(document.getElementById('chart1'));
                var chart1 = {
                    title: {
                        text: ''
                    },
                    tooltip: {},
                    legend: {
                        data:['商品提交订单量']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            saveAsImage : {show: true}
                        }
                    },
                    xAxis: {
                        data:[]
                    },
                    yAxis: {},
                    series: []
                };

                $.ajax({
                    url:'${basepath}/manage/shopInfo/getGoodsByMoon',
                    data:{'moon':moon},
                    async:false,
                    success:function(data){
                        //前台循环
//                    var goods=[];
//                    var order=[];
//                    for(var i = 0; i < data.length;i++){
//                        goods.push(data[i].goods);
//                        order.push(data[i].orderNum);
//                    }
//                    var seriesData = [];
//                    seriesData.push({
//                        name:"商品提交订单量",
//                        type:"bar",
//                        data:order
//                    });
//                    var xAxisData = [];
//                    xAxisData.push({
//                        axisLabel:{
//                            interval:0
//                        },
//                        data:goods
//                    });
//                    chart1.xAxis=xAxisData;
//                    chart1.xAxis.data=goods;
//                    chart1.series=seriesData;

                        //                   后台循环
                        var xAxisData = [];
                        xAxisData.push({
                            axisLabel:{
                                interval:0
                            },
                            data:data[0]
                        });
                        var seriesData = [];
                        seriesData.push({
                            name:"商品提交订单量",
                            type:"bar",
                            data:data[1]
                        });
                        //chart1.xAxis.data=data[0];
                        chart1.xAxis=xAxisData;
                        chart1.series=seriesData;
                        myChart1.setOption(chart1);

                    },
                });
                myChart1.setOption(chart1);
            }

        });
        function copySucc(client, text){
//        alert("成功");
            $.Notification.autoHideNotify('success', 'top center', '提示','复制成功.');
        }




    </script>

<#--</@page.pageBase>-->