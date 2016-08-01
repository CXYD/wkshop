<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="管理订单">-->
<style>
    .table th, .table td {
        text-align: center;
        /*style="white-space:nowrap"*/
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
    $(function(){
        cc();
    });
    var orderNum="";
    var mobile="";
    var lman="";
    var ordSelect="";
    var createTime="";
    var endTime="";
    var paySelect="";
    function cc(str){
           orderNum=$('#orderNum').val();
           mobile=$('#contractmobile').val();
           lman=$('#linkman').val();
           ordSelect=$('#odSelect').val();
           createTime=$('#createTime').val();
           endTime=$('#endTime').val();
        　if(createTime!="" &&　endTime==""){
            swal('结束时间不能为空！');

            return false;
        　　}
         if(endTime!="" && createTime==""){
             swal('开始时间不能为空！');
             return false;
         }
           if(str!=undefined){
            ordSelect=str;
        }
        if(createTime.substring(0,4)<endTime.substring(0,4)){
            swal("不允许跨年查询！");
            return false;
        }
          paySelect=$('#paySelect').val();
          tables = $('#orderdataTable').DataTable({
          "bSort": false, //是否支持排序功能
          "scrollX": "120%",   //表格的宽度
          "scrollXInner": "130%",   //表格的内容宽度\
            "bFilter": false, //搜索栏
            "processing": true,
            "serverSide": true,
            "destroy":true,
            "language":{
                "url":"${staticpath}/datatables/Chinese.json"
            },
            "ajax": {
                type:"POST",
                url:"${basepath}/manage/order/orderManage/loadData",
                data:{
                    orderNum:orderNum,
                    contractmobile:mobile,
                    linkman:lman,
                    orderstatus:ordSelect,
                    paytype:paySelect,
                    createTime:createTime,
                    endTime:endTime,
                },
                dataSrc:"list"
            },
            columns:[
                /* {name:"ID", "orderable": false,title:'<input type="checkbox" id="firstCheckbox"/>', data:"id",render:function ( data, type, row, meta ) {
                     // 'sort', 'type' and undefined all just use the integer
                     return '<input type="checkbox" name="ids" value="'+data+'"/>';
                 }},*/
                {name:"order_num","orderable": true, title:"订单号", data:"orderNum"},
                {name:"create_time","orderable": true, title:"下单时间", data:"createTime"},
                {name:"productName","orderable": false, title:"商品名称", data:"productName"},
                {name:"price","orderable": false, title:"单价", data:"price",render:function(data,type,row,meat){
                    return toDecimal2(data)
                }},
                {name:"quantity","orderorderableable": false, title:"数量", data:"quantity"},
                {name:"linkman","": false, title:"联系人", data:"linkman"},
                {name:"contractmobile","orderable": false, title:"联系手机", data:"contractmobile"},
                {name:"paytype", title:"支付方式","orderable": false,  data:"paytype"},
                {name:"amount","orderable": false, title:"实付金额", data:"amount",render:function(data,type,row,meat){
                    return toDecimal2(data)
                }},
                {name:"orderstatus", title:"状态","orderable": false,  data:"orderstatus"},
                {name:"update_account","orderable": false, title:"操作员", data:"updateAccount"},
                /*{name:"o.update_time","orderable":true,title:"操作时间",data:"updateTime"},*/
                {name:"oper", title:"操作","orderable": false,  data:"orderstatus",render:function(data,type,row,meat){
                        switch(data)
                        {
                            case '待办理':
                            return '<a name="updateStatus" state="05">办理成功</a><br><a name="updataStatusFail" state="08">办理失败<span></span></a>';break;
                            case '待付款':
                            return '<a name="updataStatusFail" state="02">取消订单</a>';break;
                            case '已取消':
                                return '';break;
                            default:return '';

                        }

                }},
                {name:"oper", title:"详情","orderable": false,  data:"orderNum",render:function(data,type,row,meat){


                    return "<a onclick='openDetail("+data+")'>详情</a>";
                }}
            ]
        });
        $("#orderdataTable thead").css("background","#dff0d8");
        $("#orderdataTable").on("click","a[name='del']",function(e){
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
        $("#orderdataTable").on("click","a[name='updateStatus']",function(e){
            var state = $(this).attr("state");
            console.log($(this).parents('tr'));
            console.log(tables.row($(this).parents('tr')).data().id);
            var rowid = tables.row($(this).parents('tr')).data().id;
            console.log(rowid)
            $.ajax({
                url: '${basepath}/manage/order/orderManage/updateOrderStatus',
                type: 'POST',
                dataType: 'text',
                data: {id: rowid,status:$(this).attr("state")},
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

        });//update
        $("#orderdataTable").on("click","a[name='updataStatusFail']",function(e){
            var rowid = tables.row($(this).parents('tr')).data().id;
            var state = $(this).attr("state");
            $("#desc").val("");
            if(state=="02"){
                $(".modal-title").html("取消订单的理由");
            }else{
                $(".modal-title").html("办理失败的理由");
            }
            $("#nodesc").css('display','none');
            $("input[name='hiddenid']").val(rowid);
            $("input[name='odstate']").val(state);
            $("#myModal").modal('toggle');

        });

    }
    function openDetail(data){
        $('.wraper').load('${basepath}/manage/order/orderManage/toOrderDetail?orderNum='+data)
    }

</script>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-body">

                <form id="orderform"  class="form-horizontal" >

                    <div class="form-group">
                        <div class="col-sm-12">

                            <label class="control-label col-sm-1" >订单号</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" maxlength="32" id="orderNum" name="orderNum" value="">
                            </div>

                            <label class="control-label col-sm-2" >订单状态</label>
                            <div class="col-sm-2">
                                <select class="form-control input-sm" id="odSelect">
                                    <option selected="" value="">全部</option>
                                    <#list KVlist as item>
                                        <#if item.kValue??&&item.catalog=="odstatus">
                                            <option value="${item.kValue}">${item.vValue}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </div>
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
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="control-label col-sm-1">联系手机</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="contractmobile" id="contractmobile" >
                            </div>
                            <label class="control-label col-sm-2" >联系人姓名</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" maxlength="32" name="linkman" id="linkman">
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">

                            <label class="control-label col-sm-1" >开始时间</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" style="width: 150px" name="createTime" id="createTime">
                            </div>
                            <label class="control-label col-sm-2" >结束时间</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" style="width: 150px" id="endTime" name="endTime">
                            </div>
                            <label class="control-label col-sm-1" ></label>
                            <div class="col-sm-1">
                                <lable class="btn btn-primary" onclick="cc();">查询</lable>
                            </div>
                        </div>

                    </div>
                </form>
            </div> <!-- panel-body -->
        </div> <!-- panel -->
    </div> <!-- col -->

</div>
<div class="row">
                <div class="row" style="font-size: 50%">

                    <div class="col-lg-7 col-md-7" id="oder">
                        <ul class="nav nav-tabs ">
                            <li class="active">
                                <a  onclick="cc();" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-home"></i></span>
                                    <span class="hidden-xs ">全部</span>
                                </a>
                            </li>
                            <li class="">
                                <a name="oderstatus" state="03" data-toggle="tab" aria-expanded="true">
                                    <span class="visible-xs"><i class="fa fa-user"></i></span>
                                    <span class="hidden-xs" >待付款</span>
                                </a>
                            </li>
                            <li class="">
                                <a name="oderstatus" state="04" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-envelope-o"></i></span>
                                    <span class="hidden-xs" >待办理</span>
                                </a>
                            </li>
                            <li class="">
                                <a name="oderstatus" state="05" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-cog"></i></span>
                                    <span class="hidden-xs">办理成功</span>
                                </a>
                            </li>
                            <li class="">
                                <a name="oderstatus" state="06" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-cog"></i></span>
                                    <span class="hidden-xs">待退款</span>
                                </a>
                            </li>
                            <li class="">
                                <a name="oderstatus" state="07" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-cog"></i></span>
                                    <span class="hidden-xs">退款结束</span>
                                </a>
                            </li>
                            <li class="">
                                <a name="oderstatus" state="02" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-cog"></i></span>
                                    <span class="hidden-xs">已取消</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="row">
                        <#--<label>排序方式：</label><button type="button" class="btn btn-primary">下单时间</button>&nbsp;-->
                        <#--<button type="button" class="btn btn-primary">订单操作时间</button>&nbsp;-->
                        <button type="button" id="export" class="btn btn-success">导出EXCEL</button>
                    </div>
                </div> <!-- End row -->


</div>
<br>
<div class="row">
    <div>
        <table class="table table-striped table-bordered dataTable table-hover"  id="orderdataTable">
        </table>
    </div>
</div>
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <input type="hidden" name="hiddenid">
    <input type="hidden" name="odstate">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-2 control-label">备注</label>
                            <div class="col-md-8">
                                <textarea  id="desc" maxlength="300"  class="form-control" placeholder="请输入理由..."></textarea>
                            </div>
                            <label  class="col-md-4 control-label" id="nodesc"  style="margin-left:20px; display: none;color: red;">理由不能为空！</label>
                        </div>
                    </form>
                </div>


            </div>
            <div class="modal-footer" >
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" name="btnsave" class="btn btn-info">保存</button>
            </div>
        </div>
    </div>
</div><!-- /.modal -->
<script>
    $(".modal-footer").on("click","button[name='btnsave']",function(e){

        var hiddid = $("input[name='hiddenid']").val();
        var odstate = $("input[name='odstate']").val();
        var descValue = $("#desc").val();
        if(descValue==''){
            $("#nodesc").css('display','block');
            return false;
        }
        $.ajax({
            url: '${basepath}/manage/order/orderManage/addRemark',
            type: 'POST',
            dataType: 'text',
            data: {id: hiddid,remark:descValue,orderstatus:odstate},
        })
                .done(function(data) {
                    if (data=="success") {
                        tables.ajax.reload();
                        $("#myModal").modal('toggle');
                        if(odstate=="02"){
                            swal("提示!", "订单已取消.", "success");
                        }else if(odstate="08"){
                            swal("提示!", "办理失败成功.", "success");
                        }
                    };
                })
                .fail(function() {
                    swal("error");
                });
    });
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
                    var searchwhere = "orderNum="+orderNum+"&contractmobile="+mobile+"&linkman="+lman+"&orderstatus="+ordSelect+"&createTime="+createTime+"&endTime="+endTime+"&paytype="+paySelect+"&filename=order"+currenttime;
                    var url="${basepath}/manage/order/orderManage/orderDataExport?"+searchwhere;
                    window.open(url);
                    swal("提示!", "操作成功.", "success");
                }
            });
    })

    $("#oder").on("click","a[name='oderstatus']",function(e){
        var state = $(this).attr("state");
        cc(state);

    });
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