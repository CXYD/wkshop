<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商品标签">-->

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
                url:"${basepath}/manage/product/lable/loadData",
                dataSrc:"list"
            },
            columns:[
//                {name:"label_id", "orderable": false, title:'ID', data:"labelId"},
                {name:"name", title:"标签名称", "orderable": false, data:"name"},
                {name:"createtime", title:"创建时间", "orderable": false, data:"createtime",render:function(data,type,row,meta){
                    return data;
                }},
                {name:"oper", title:"操作","orderable": false,  data:"status",render:function(data,type,row,meat){

                    if(data=="0"){
                       return '<a name="del">删除</a>-<a name="update" state="1">启用</a>';
                    }else{
                        return '<a name="del">删除</a>-<a  name="update" state="0">禁用</a>';
                    }
                }}

            ]
        });//datatable

        $("#datatable thead").css("background","#dff0d8");

        $("#datatable").on("click","a[name='del']",function(e){
            console.log("操作...");
            console.log($(this).parents('tr'));
            console.log(tables.row($(this).parents('tr')).data().labelId);
            var rowid = tables.row($(this).parents('tr')).data().labelId;
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
                        url: '${basepath}/manage/product/lable/delByID',
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



        $("#datatable").on("click","a[name='update']",function(e){
            console.log("操作...");
            console.log($(this).parents('tr'));
            console.log(tables.row($(this).parents('tr')).data().labelId);
            var rowid = tables.row($(this).parents('tr')).data().labelId;

            console.log(rowid)
            $.ajax({
                url: '${basepath}/manage/product/lable/updateStatus',
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


        $("#form").on("valid.form", function(e, form){
            console.log(this.isValid);
            console.log("submit...");

            $.ajax({
                url: '${basepath}/manage/product/lable/insert',
                type: 'POST',
                dataType: 'text',
                data: {name:$('#name').val() },
                success:function(data){
                    tables.ajax.reload();
                    swal("提示!", "操作成功.", "success");
                    $('#con-close-modal').modal('toggle');
                }
            })

        });




    });




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
                                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#con-close-modal">添加标签</button>
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



    <div id="con-close-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">

        <div class="modal-dialog">
            <form action="" id="form" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">添加标签</h4>
                </div>
                <div class="modal-body">

                    <div class="row">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-2 control-label">标签名称</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="name" name="name" data-rule="标签:required;remote[${basepath}/manage/product/lable/unique]"  placeholder="请输入标签名称...">
                                </div>
                            </div>
                        </form>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                    <button type="submit" method="${basepath}/manage/product/lable/insert"   class="btn btn-info">保存</button>
                </div>
            </div>
            </form>
        </div>

    </div><!-- /.modal -->

<#--</@page.pageBase>-->