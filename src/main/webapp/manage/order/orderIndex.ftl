<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="订单概况">-->

<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading"><h4 class="panel-title">本月订单统计</h4></div>

        <div class="panel-body text-center">

            <div class="col-lg-4 col-sm-4">
                <div class="widget-panel widget-style-2 white-bg">
                    <h2 class="m-0 counter"> ${createNumList?size}</h2>
                    <div>本月下单笔数</div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-4">
                <div class="widget-panel widget-style-2 white-bg">
                    <h2 class="m-0 counter">${payNum}</h2>
                    <div>本月付款笔数</div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-4">
                <div class="widget-panel widget-style-2 white-bg">
                    <h2 class="m-0 counter">${totalprice?string('0.00')}</h2>
                    <div>本月订单收入</div>
                </div>
            </div>


        </div>

    </div>

</div> <!-- end row -->

<div class="row">
    <div class="panel panel-default">
            <h4>月度订单趋势</h4>
        <div class="panel-body">
            <div class="row">
                <form class="form-horizontal" role="form">
                    <label class="col-md-2 control-label">选择月份</label>
                    <div class="col-sm-2">
                        <select class="form-control inline">
                            <option value="">201605</option>
                            <option value="1">201606</option>
                        </select>
                    </div>
                </form>
            </div>
            <div id="main" style="height:400px;"></div>
        </div>
    </div>

</div>
<script>

    jQuery(document).ready(function($) {
        /*$('.counter').counterUp({
            delay: 100,
            time: 1200
        });*/

        window.alert=swal;
        var myChart = echarts.init(document.getElementById('main'));
        var option = {
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['下单笔数','付款笔数','付款金额']
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
                    data : ['2016-05-05','2016-05-06','2016-05-07','2016-05-08','2016-05-09','2016-05-11','2016-05-12']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'下单笔数',
                    type:'line',
                    stack: '总量',
                    data:[120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name:'付款笔数',
                    type:'line',
                    stack: '总量',
                    data:[220, 182, 191, 234, 290, 330, 310]
                },
                {
                    name:'付款金额',
                    type:'line',
                    stack: '总量',
                    data:[150, 232, 201, 154, 190, 330, 410]
                },
            ]
        };
       myChart.setOption(option);
      //$("[data-toggle='tooltip']").tooltip();
    });



    </script>

<#--</@page.pageBase>-->