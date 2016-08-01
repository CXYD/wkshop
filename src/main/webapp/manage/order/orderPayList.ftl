<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="收支管理">-->
<style>
    .table th, .table td {
        text-align: center;
    }
</style>
<script>

    $(function(){
        cc();

    });
    function cc(){
        var orderNum=$('#orderNum').val();
        var mobile=$('#contractmobile').val();
        var lman=$('#linkman').val();
        table = $('#orderdataTable').DataTable({
            "bSort": true, //是否支持排序功能
            "bFilter": false, //搜索栏
            "processing": true,
            "serverSide": true,
            "destroy":true,
            "language":{
                "url":"${staticpath}/datatables/Chinese.json"
            },
            "ajax": {
                type:"POST",
                url:"${basepath}/manage/order/orderPay/loadData",
                data:{
                    orderNum:orderNum,
                    contractmobile:mobile,
                    linkman:lman,
                },
                dataSrc:"list"
            },
            columns:[
                {name:"create_time","orderable": true, title:"完成时间", data:"createTime"},
                {name:"linkman","orderable": false, title:"订单号", data:"linkman"},
                {name:"contractmobile","orderable": false, title:"下单时间", data:"contractmobile"},
                {name:"paytype","orderable": false, title:"商品名称", data:"paytype"},
                {name:"amount","orderable": false, title:"支付方式", data:"amount"},
                {name:"orderstatus","orderable": false, title:"实付金额", data:"orderstatus"},
                {name:"orderstatus","orderable": false, title:"佣金", data:"orderstatus"},
                {name:"orderstatus","orderable": false, title:"结算金额", data:"orderstatus"},
                {name:"update_account","orderable": false, title:"结算状态", data:"updateAccount"},
            ]
        });
    }


</script>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">

            <div class="panel-body" >

                <div class="row"  >
                    <div class="col-md-12" >

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <label class="control-label col-sm-1" >订单号</label>
                                    <div class="col-sm-2">
                                        <input type="email" class="form-control"  placeholder="">
                                    </div>
                                    <label class="control-label col-sm-2" >完成时间</label>
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" id="exampleInputPassword2" placeholder="">
                                    </div>
                                    <label class="form-control-static col-sm-1 m-l-15">
                                        ——
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" >
                                    </div>
                                </div>

                            </div>
                            <div class="form-group ">
                                <div class="col-sm-12 ">
                                    <label class="control-label col-sm-1" >支付方式</label>
                                    <div class="col-sm-2">
                                        <select class="inline">
                                            <option value="">全部</option>
                                            <option value="1">连连支付</option>
                                            <option value="2">支付宝支付</option>
                                            <option value="3">货到付款</option>
                                        </select>
                                    </div>
                                    <label class="control-label col-sm-2" >结算状态</label>
                                    <div class="col-sm-2">
                                        <select class="inline">
                                            <option selected="" value=" 全部"> 全部</option>
                                            <option value=" 待付款">待结算</option>
                                            <option value=" 审核中">已结算</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-offset-1">
                                        <button class="btn btn-primary ">检索</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div> <!-- col -->

                </div>

                <div class="row">

                    <div class="col-lg-5" style="font-size: 50%">
                        <ul class="nav nav-tabs">
                            <li class="">
                                <a href="#home" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-home"></i></span>
                                    <span class="hidden-xs">全部</span>
                                </a>
                            </li>
                            <li class="active">
                                <a href="#profile" data-toggle="tab" aria-expanded="true">
                                    <span class="visible-xs"><i class="fa fa-user"></i></span>
                                    <span class="hidden-xs">待结算</span>
                                </a>
                            </li>
                            <li class="">
                                <a href="#messages" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-envelope-o"></i></span>
                                    <span class="hidden-xs">已结算</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="row" style="margin-top: 5px;">
                        <label>排序方式：</label><button type="button" class="btn btn-primary" >完成时间</button>&nbsp;
                        <button type="button" class="btn btn-primary">下单时间</button>&nbsp;
                        <button type="button" class="btn btn-success">导出EXCEL</button>
                    </div>
                </div> <!-- End row -->

                <div class="row" >
                    <div>
                        <table class="table table-striped table-bordered dataTable table-hover"  id="orderdataTable">
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<#--</@page.pageBase>-->