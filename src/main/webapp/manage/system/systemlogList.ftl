<style>
    .table th, .table td {
        text-align: center;
    }
</style>
<script>

function cc(){
    var account=$('#account').val();
    var logTime =$('#logTime').val();
    var eTime =$('#endTime').val();
    if(logTime!="" &&　eTime==""){
        swal('结束时间不能为空！');
        return false;
        　　}
    if(eTime!="" && logTime==""){
        swal('开始时间不能为空！');
        return false;
    }
    if(logTime.substring(0,4)<eTime.substring(0,4)){
        swal("不允许跨年查询！");
        return false;
    }
    tables = $('#dataTable').DataTable({
        "bFilter": false, //搜索栏
        "processing": true,
        "serverSide": true,
        "destroy":true,
        "language":{
            "url":"${staticpath}/datatables/Chinese.json"
        },
        "ajax": {
            url:"${basepath}/manage/systemlog/loadData",
            data:{
                account:account,
                logTime:logTime,
                endTime:eTime,
            },
            dataSrc:"list"
        },
        columns:[
            {name:"title", title:"标题", data:"title"},
            {name:"account", title:"账号", data:"account"},
            {name:"log_time", title:"记录时间", data:"logTime"},
            {name:"login_ip", title:"登陆IP", data:"loginIp"},
            {name:"login_area", title:"登陆位置", data:"loginArea"},
            {name:"content", title:"内容", data:"content"}
        ]
    });
    $("#dataTable thead").css("background","#dff0d8");
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
                                        <input type="date" class="form-control" style="width: 150px" name="logTime" id="logTime">
                                    </div>
                                    <label class="control-label col-sm-1" >结束时间</label>
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" style="width: 150px" id="endTime" name="endTime">
                                    </div>
                                    <label class="control-label col-sm-1" >登陆账号</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" maxlength="32" name="account" id="account">
                                    </div>
                                    <div class="col-sm-1">
                                        <lable class="btn btn-primary" onclick="cc();">查询</lable>
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
        <table class="table table-striped table-bordered dataTable hover"  id="dataTable">
        </table>
    </div>
</div>
