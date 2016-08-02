<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="结算记录">-->
<style>
    .table th, .table td {
        text-align: center;
    }
</style>
<script>
    //制保留2位小数，如：2，会在2后面补上00.即2.00
    function toDecimal2(x) {
        var f = parseFloat(x);
       /* if (isNaN(f)) {
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
    var balSelect="";
    var baldate="";
    var eTime="";
    function cc(){
        balSelect=$('#balSelect').val();
        baldate =$('#baldate').val();
        eTime =$('#endTime').val();
        if(baldate!="" &&　eTime==""){
            swal('结束时间不能为空！');
            return false;
        　　}
        if(eTime!="" && baldate==""){
            swal('开始时间不能为空！');
            return false;
        }
        if(baldate!="" && baldate==eTime){
            swal('开始时间和结束时间不能相同！');
            return false;
        　　}
        if(baldate.substring(0,4)<eTime.substring(0,4)){
            swal("不允许跨年查询！");
            return false;
        }
        tables = $('#balancedataTable').DataTable({
            "bFilter": false, //搜索栏
           /* "bSort": false, //是否支持排序功能*/
            "processing": true,
            "serverSide": true,
            "destroy":true,
            "language":{
                "url":"${staticpath}/datatables/Chinese.json"
            },
            "ajax": {
                url:"${basepath}/manage/order/balance/loadData",
                data:{
                    balstatus:balSelect,
                    baldate:baldate,
                    endTime:eTime,
                },
                dataSrc:"list"
            },
            columns:[
                {name:"baldate", title:"时间", "orderable": true,data:"baldate",render:function(data,type,row,meat){
                    return data.substring(0,10);
                }},
                {name:"incomeamount","orderable": true, title:"收入金额", data:"incomeamount",render:function(data,type,row,meat){
                    return toDecimal2(data);
                }},
                {name:"payamount","orderable": true, title:"支出金额", data:"payamount",render:function(data,type,row,meat){
                    return toDecimal2(data);
                }},
                {name:"balamount","orderable": true, title:"结算金额", data:"balamount",render:function(data,type,row,meat){
                    return toDecimal2(data);
                }},
                {name:"balstatus", title:"结算状态","orderable": false,  data:"balstatus"},
                {name:"oper", title:"操作","orderable": false,  data:"balstatus",render:function(data,type,row,meat){
                    if(data=='待结算'){
                        return '<a name="update" state="1">申请结算</a>';
                    }else if(data=='已申请'){
                        return '<a name="update" state="2">已申请</a>';
                    }else{
                        return '';
                    }
                }}
            ]
        });
        $("#balancedataTable thead").css("background","#dff0d8");
        $("#balancedataTable").on("click","a[name='del']",function(e){
            console.log("操作...");
            console.log($(this).parents('tr'));
            console.log(tables.row($(this).parents('tr')).data().id);
            var rowid = tables.row($(this).parents('tr')).data().id;
            var yes = swal({
                title: "提示",
                text: "确定要删除？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false
            }, function (flag) {
                if (flag) {

                    console.log(rowid)
                    $.ajax({
                        url: 'delByID',
                        type: 'POST',
                        dataType: 'text',
                        data: {id: rowid},
                    })
                            .done(function(data) {
                                if (data=="success") {
                                    tables.ajax.reload();
                                    swal("提示!", "操作成功.", "success");
                                };
                            })
                            .fail(function() {
                                swal("error");
                            });

                }
            });

        });//del



        $("#balancedataTable").on("click","a[name='update']",function(e){
            console.log("操作...");
            console.log($(this).parents('tr'));
            console.log(tables.row($(this).parents('tr')).data().id);
            var rowid = tables.row($(this).parents('tr')).data().id;
            var state = $(this).attr("state");
            if(state==1){
                var yes = swal({
                    title: "提示",
                    text: "确定要申请结算吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                }, function (flag) {
                    if (flag) {
                        $.ajax({
                            url: '${basepath}/manage/order/balance/updateById',
                            type: 'POST',
                            dataType: 'text',
                            data: {id: rowid,status:state},
                        })
                                .done(function(data) {
                                    if (data=="success") {
                                        tables.ajax.reload();
                                        swal("提示!", "申请成功.", "success");
                                    };
                                })
                                .fail(function() {
                                    swal("error");
                                });
                    }
                });
            }else if(state==2){
                swal("提示!", "已经申请了.", "success");
            }

        });//update
    }
    $(function(){
        cc();
    });

</script>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">

            <div class="panel-body">
                <div class="row"  >
                    <div class="col-md-12" >

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <label class="control-label col-sm-1" >开始时间</label>
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" id="baldate" name="baldate" style="width: 150px">
                                    </div>
                                    <label class="control-label col-sm-1" >结束时间</label>
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" style="width: 150px" id="endTime" name="endTime">
                                    </div>
                                    <label class="control-label col-sm-1" >结算状态</label>
                                    <div class="col-sm-2">
                                        <select class="form-control input-sm" id="balSelect">
                                            <option selected="" value="">全部</option>
                                            <#list KVlist as item>
                                                <#if item.kValue??&&item.catalog=="bal">
                                                    <option value="${item.kValue}">${item.vValue}</option>
                                                </#if>
                                            </#list>
                                        </select>
                                    </div>
                                    <div class="col-sm-1">
                                        <lable class="btn btn-primary" onclick="cc();">查询</lable>
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
<div class="row">
    <div class="col-sm-12">
        <table class="table table-striped table-bordered dataTable hover"  id="balancedataTable">
        </table>
    </div>
</div>
<script>
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
                var searchwhere = "balstatus="+balSelect+"&baldate="+baldate+"&endTime="+eTime+"&filename=bal"+currenttime;
                var url="${basepath}/manage/order/balance/balExport?"+searchwhere;
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