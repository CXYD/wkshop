<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商品分类">-->

<script>
    $(function() {
        var tables = $('#datatable').DataTable({
            "processing": true,
            "serverSide": true,
            "bFilter":false,
            "language":{
                "url":"${staticpath}/datatables/Chinese.json"
            },
            "ajax": {
                url: "${basepath}/manage/product/class/loadData",
                dataSrc: "list"
            },
            "aoColumnDefs": [
                {
                    sDefaultContent: '',
                    aTargets: ['_all']
                }
            ],
            columns: [
//                {name: "ID", "orderable": false, title: 'ID', data: "id"},
                {name: "name", title: "分类名称", "orderable": false, data: "name"},
                {name: "nums", title: "商品数","orderable": false,  data: "productNums"},
//                {name: "order1", title: "序号","orderable": false,  data: "order1"},
                {
                    name: "createtime", "orderable": false, title: "创建时间", data: "createtime", render: function (data, type, row, meta) {
                    return data;
                }
                },
                {
                    name: "oper",
                    title: "操作",
                    "orderable": false,
                    data: "status",
                    render: function (data, type, row, meat) {
                        return '<a name="show" state="1">查看</a>-<a name="del">删除</a>-<a name="linkurl" >链接</a>' +
                               ' <div class="" style="position: absolute;z-index: 99;" id="shoplink" hidden > <p> <img src="${basepath}/getQrCode?khid=16946474" alt=""> </p></div>';
                    }
                }

            ]
        }); //datatable
        $("#datatable thead").css("background","#dff0d8");

        $("#datatable").on("click","a[name='show']",function(e){
            var id = tables.row($(this).parents('tr')).data().id;
            var name = tables.row($(this).parents('tr')).data().name;
            $('.wraper').load("${basepath}/manage/product/productManage/detailList?id="+id+"&name="+name);
        })

        $("#datatable").on("click", "a[name='del']", function (e) {
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
                        url: '${basepath}/manage/product/class/delByID',
                        type: 'POST',
                        dataType: 'text',
                        data: {id: rowid},
                    })
                    .done(function (data) {
                        if (data == "success") {
                            tables.ajax.reload();
                            swal("提示!", "操作成功.", "success");
                        }
                        ;
                    })
                    .fail(function () {
                        swal("error");
                    });

                }
            });

        }); //del

        $("#datatable").on("mouseover", "a[name='linkurl']", function (e) {
            console.log("滑过...");
//            $("#shoplink").show();
            console.log($(this).offset().top);
            console.log($(this).offset().left);
            $("#shoplink").css("left", $(this).offset().left-top-225);
            $("#shoplink").css("top",$(this).offset().top-225);
            $("#shoplink").show();

        });// linkurl


        $("#datatable").on("mouseout", "a[name='linkurl']", function (e) {
            console.log("滑出...");
            $("#shoplink").hide();
        });// linkurl

        $("#form").on("valid.form", function(e, form){
            console.log(this.isValid);
            console.log("submit...");

            $.ajax({
                url: '${basepath}/manage/product/class/insert',
                type: 'POST',
                dataType: 'text',
                data: {name:$('input[name="name"]').val(),order1:$('input[name="order1"]').val() },
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
                                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#con-close-modal">添加分类</button>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <table id="datatable" class="table table-striped table-bordered dataTable hover" role="grid" aria-describedby="datatable_info">

                                    </table>

                                </div>
                                <#--<div class="col-md-12 col-md-offset-10">-->
                                    <#--<div class="" style="position: absolute;z-index: 99;" id="shoplink" hidden > <p> <img src="${basepath}/getQrCode?khid=16946474" alt=""> </p></div>-->
                                <#--</div>-->
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
        <form action="${basepath}/manage/product/class" id="form" method="post">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">添加分类</h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <form class="form-horizontal">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label">分类名称</label>
                            <div class="col-md-8">
                                <input type="text" name="name" class="form-control"  data-rule="类别:required;remote[${basepath}/manage/product/class/unique]"  placeholder="请输入分类名称...">
                            </div>
                        </div>
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label">序号</label>
                            <div class="col-md-8">
                                <input type="text" name="order1" class="form-control" placeholder="请输入序号...">
                            </div>
                        </div>


                    </form>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="submit" method="insert" class="btn btn-info">保存</button>
            </div>
        </div>
    </form>
    </div>

</div><!-- /.modal -->

<#--</@page.pageBase>-->