<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="收支管理">-->
<style>
    .table th, .table td {
        text-align: center;
        white-space:nowrap;
    }
</style>
<script>
    //制保留2位小数，如：2，会在2后面补上00.即2.00
    function toDecimal2(x) {
        var f = parseFloat(x);
        /*if (isNaN(f)) {
            return false;
        }*/
        var f = Math.round(x*100)/100;
        var s = f.toString();
        var rs = s.indexOf('.');
        if (rs < 0) {
            rs = s.length;
            s += '.';
        }
        while (s.length <= rs + 2) {
            s += '0';
        }
        return s;
    }
    $(function(){
        cc();
    });
  var orderNum="";
  var balSelect="";
  var paySelect="";
  var ftime="";
  var eTime="";
    function cc(str){
        orderNum=$('#orderNum').val();
        balSelect=$('#balSelect').val();
        paySelect=$('#paySelect').val();
        ftime =$('#finishtime').val();
        eTime =$('#endTime').val();
        if(ftime!="" &&　eTime==""){
            swal('结束时间不能为空！');
            return false;
        　　}
        if(ftime!="" && ftime==eTime){
            swal('开始时间和结束时间不能相同！');
            return false;
        　　}
        if(eTime!="" && ftime==""){
            swal('完成时间不能为空！');
            return false;
          }
        if(str!=undefined){
            balSelect=str;
        }
        if(ftime.substring(0,4)<eTime.substring(0,4)){
            swal("不允许跨年查询！");
            return false;
        }

        $('#balpaydataTable').DataTable({
                "bSort": false, //是否支持排序功能
                "bFilter": false, //搜索栏
                "processing": true,
                "serverSide": true,
                "destroy":true,
                "language":{
                    "url":"${staticpath}/datatables/Chinese.json"
                },
                "ajax": {
                    type:"POST",
                    url:"${basepath}/manage/order/banlancePay/loadData",
                    data:{
                        orderNum:orderNum,
                        settlestatus:balSelect,
                        paytype:paySelect,
                        finishtime:ftime,
                        endTime:eTime,
                    },
                    dataSrc:"list"
                },
                columns:[
                    {name:"o.finishtime",title:"完成时间", data:"finishtime"},
                    {name:"o.order_num",title:"订单号", data:"orderNum"},
                    {name:"o.create_time", title:"下单时间", data:"createTime"},
                    {name:"p.product_name","orderable": false, title:"商品名称", data:"productName"},
                    {name:"o.paytype", title:"支付方式","orderable": false,  data:"paytype",render:function(data,type,row,meat){
                        switch(data)
                        {case '21':return '连连支付';break;case '22':return '支付宝支付';break;case '23':return '货到付款';break;default:return '';
                        }
                    }},
                    {name:"o.amount","orderable": false, title:"总金额", data:"amount",render:function(data,type,row,meat){
                        return toDecimal2(data);
                    }},
                    {name:"commision","orderable": false, title:"佣金", data:"commision",render:function(data,type,row,meat){
                        return toDecimal2(data);
                    }},
                    {name:"balamount","orderable": false, title:"结算金额", data:"balamount",render:function(data,type,row,meat){
                        return toDecimal2(data);
                    }},
                    {name:"o.settlestatus","orderable": false, title:"结算状态", data:"settlestatus"},
                ]
            });
        $("#balpaydataTable thead").css("background","#dff0d8");


    }

</script>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default" >

            <div class="panel-body">

                <div class="row">
                    <div class="col-md-12" >

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <label class="control-label col-sm-1" >订单号</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" maxlength="32" id="orderNum" name="orderNum" value="">
                                    </div>
                                    <label class="control-label col-sm-2" >完成时间</label>
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" style="width: 150px" id="finishtime" name="finishtime">
                                    </div>
                                    <label class="control-label col-sm-2" >结束时间</label>
                                    <div class="col-sm-3">
                                        <input type="date" class="form-control" style="width: 150px"id="endTime" name="endTime" >
                                    </div>
                                </div>

                            </div>
                            <div class="form-group ">
                                <div class="col-sm-12 ">
                                    <label class="control-label col-sm-1" >支付方式</label>
                                        <div class="col-sm-2">
                                            <select class="form-control input-sm" id="paySelect">
                                                <option selected="" value="">全部</option>
                                                <#list KVlist as item>
                                                    <#if item.kValue??&&item.catalog=="pay">
                                                        <option value="${item.kValue}">${item.vValue}</option>
                                                    </#if>
                                                </#list>
                                            </select>
                                        </div>
                                    <label class="control-label col-sm-2" >结算状态</label>
                                        <div class="col-sm-2">
                                            <select class="form-control input-sm" id="balSelect">
                                                <option selected="" value="">全部</option>
                                                <#list KVlist as item>
                                                    <#if item.kValue??&&item.catalog=="paybal">
                                                        <option value="${item.kValue}">${item.vValue}</option>
                                                    </#if>
                                                </#list>
                                            </select>
                                        </div>
                                    <label class="control-label col-sm-1"></label>
                                    <div class="col-sm-1">
                                        <lable class="btn btn-primary col-sm-offset-1" onclick="cc();">查询</lable>
                                    </div>

                                    <div>
                                        <button type="button" class="btn btn-success" id="export">导出excel</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div> <!-- col -->

                </div>
            </div>
        </div>
    </div>

</div>
<div class="row" style="font-size: 50%;margin-bottom: 10px">

    <div class="col-lg-5"  id="bal">
        <ul class="nav nav-tabs">
            <li class="active" >
                <a  onclick="cc()" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs"><i class="fa fa-home"></i></span>
                    <span  class="hidden-xs">全部</span>
                </a>
            </li>
            <li class="" >
                <a name="settlestatus" state="0" data-toggle="tab" aria-expanded="true">
                    <span class="visible-xs"><i class="fa fa-user"></i></span>
                    <span class="hidden-xs">待结算</span>
                </a>
            </li>
            <li class="" >
                <a name="settlestatus" state="1" data-toggle="tab" aria-expanded="false">
                    <span class="visible-xs"><i class="fa fa-envelope-o"></i></span>
                    <span class="hidden-xs">已结算</span>
                </a>
            </li>
        </ul>
    </div>
<#--<div class="row" style="margin-top: 5px;">
    <label>排序方式：</label><button type="button" class="btn btn-primary" >完成时间</button>&nbsp;
    <button type="button" class="btn btn-primary">下单时间</button>&nbsp;
    <button type="button" class="btn btn-success">导出EXCEL</button>
</div>-->
</div> <!-- End row -->
<div class="row" >
    <div>
        <table class="table table-striped table-bordered dataTable table-hover"  id="balpaydataTable">
        </table>
    </div>
</div>
<script>
    $("#bal").on("click","a[name='settlestatus']",function(e){
        var state = $(this).attr("state");
        cc(state);

    });//update
    $("#export").click(function(){
            var yes = swal({
            title: "提示",
            text: "确定要导出吗？",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false
        }, function (flag) {
            if (flag) {
                var currenttime = getNowFormatDate();
                var searchwhere = "orderNum="+orderNum+"&settlestatus="+balSelect+"&paytype="+paySelect+"&finishtime="+ftime+"&endTime="+eTime+"&filename=balpay"+currenttime;
                var url="${basepath}/manage/order/banlancePay/balPayExport?"+searchwhere;
                window.open(url);
                swal("提示!", "操作成功.", "success");
            }
        });

    })
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
    }
</script>
<#--</@page.pageBase>-->