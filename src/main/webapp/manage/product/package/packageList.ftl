<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="套餐管理">-->

<script>
    var tables;
    $(function(){
        tables = $('#datatable').DataTable({
            "processing": true,
            "serverSide": true,
            "bFilter":false,
            "language":{
                "url":"${staticpath}/datatables/Chinese.json"
            },
            "ajax": {
                url:"${basepath}/manage/product/package/loadData",
                dataSrc:"list"
            },
            columns:[
//                {name:"label_id", "orderable": false, title:'ID', data:"labelId"},
                {name:"name", title:"套餐名称", "orderable": false, data:"name"},
                {name:"createTime", title:"创建时间","orderable": false,  data:"createTime",render:function(data,type,row,meta){
                    return data;
                }},
                {name:"oper", title:"操作","orderable": false,  data:"status",render:function(data,type,row,meat){

                    if(data=="0"){
                        return '<a name="edit">编辑</a>-<a name="del">删除</a>-<a name="update" state="1">启用</a>';
                    }else{
                        return '<a name="edit">编辑</a>-<a name="del">删除</a>-<a  name="update" state="0">禁用</a>';
                    }
                }}

            ]
        });//datatable

        $("#datatable thead").css("background","#dff0d8");

        $("#datatable").on("click","a[name='del']",function(e){
            console.log("操作...");
//            console.log($(this).parents('tr'));
//            console.log(tables.row($(this).parents('tr')).data().id);
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
                        url: '${basepath}/manage/product/package/delByID',
                        type: 'POST',
                        dataType: 'text',
                        data: {id: rowid},
                    })
                            .done(function(data) {
                                if (data=="1") {
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



        $("#datatable").on("click","a[name='update']",function(e){
            console.log("操作...");
            console.log($(this).parents('tr'));
            var rowid = tables.row($(this).parents('tr')).data().id;

            console.log(rowid)
            $.ajax({
                url: '${basepath}/manage/product/package/updateStatus',
                type: 'POST',
                dataType: 'text',
                data: {id: rowid,status:$(this).attr("state")},
            })
                    .done(function(data) {
                        if (data=="1") {
                            tables.ajax.reload();
                            swal("提示!", "操作成功.", "success");
                        };
                    })
                    .fail(function() {
                        swal("error");
                    });

        });//update



        $("#datatable").on("click","a[name='edit']",function(e){
            console.log("操作...");
            console.log($(this).parents('tr'));
            var rowid = tables.row($(this).parents('tr')).data().id;

            $('.wraper').load("${basepath}/manage/product/package/toEditInfo?id="+rowid);

        });//edit


    });

    function openAdd(){
        console.log("添加");
        $('.wraper').load('${basepath}/manage/product/package/toAddInfo');
    }




</script>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">

            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_length" id="datatable_length">
                                        <div class="m-b-30">
                                            <button type="button" class="btn btn-success" onclick="openAdd()">添加套餐</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <table id="datatable" class="table table-striped table-bordered dataTable hover" role="grid" aria-describedby="datatable_info">

                                    </table>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>




<#--</@page.pageBase>-->