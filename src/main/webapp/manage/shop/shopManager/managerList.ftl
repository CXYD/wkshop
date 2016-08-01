<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商城管理员">-->

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
                url: "${basepath}/manage/shopManage/loadData",
                dataSrc: "list"
            },

            "aoColumnDefs": [
                {
                    sDefaultContent: '',
                    aTargets: ['_all']
                }
            ],
            columns: [
                {name: "id", "orderable": false, title: 'ID', data: "id"},
                {name: "username", title: "管理员账户", data: "username"},
                {name: "nickname", title: "姓名", data: "nickname"},
                {name: "create_time", title: "创建时间", data: "createTime"},
                {
                    name: "oper",
                    title: "操作",
                    "orderable": false,
                    data: "status",
                    render: function (data, type, row, meat) {
                        return '<a name="show" state="1">编辑</a>-<a name="del">删除</a>';
                    }
                }

            ]
        }); //datatable
        $("#datatable thead").css("background","#dff0d8");

        $("#datatable").on("click", "a[name='show']", function (e) {
            var username=tables.row($(this).parents('tr')).data().username;
            //console.log(tables.row($(this).parents('tr')).data().id);
            //alert(username);
            $('.wraper').load("${basepath}/manage/shopManage/editShopManager?username="+username);
        });
        $("#datatable").on("click", "a[name='del']", function (e) {
            console.log("操作...");
            console.log();
            var username=tables.row($(this).parents('tr')).data().username;;
            var yes = swal({
                title: "确定要删除？",
                text: "",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false
            }, function (flag) {
                if (flag) {
                    $.ajax({
                        url: "${basepath}/manage/shopManage/delManage",
                        data: {username: username},
                        async: false,
                        success: function (msg) {
                            tables.ajax.reload();
                            swal("", "删除成功", "success");
                        },
                        error: function () {
                            swal("", "删除失败", "error");
                        }
                    });
                }
            });

        }); //del
        $(".btn.btn-success").click(function(){
            $('.wraper').load("${basepath}/manage/shopManage/addShopManager");
        });

    });

</script>

<div class="panel">

        <div class="row">
            <div class="col-sm-6">
                <div class="m-b-30">
                    <button type="button" class="btn btn-success">新增管理员</button>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <table id="datatable" class="table table-striped">

                </table>
            </div>
        </div>

</div>

<#--</@page.pageBase>-->