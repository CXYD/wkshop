<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="规格管理">-->

<script>
    var tables;
    $(function() {
        tables = $('#datatable').DataTable({
            "processing": true,
            "serverSide": true,
            "bFilter": false,
            "language": {
                "url": "${staticpath}/datatables/Chinese.json"
            },
            "ajax": {
                url: "${basepath}/manage/product/norm/loadData",
                dataSrc: "list"
            },
            columns: [
//                {name: "ID", "orderable": false, title: 'ID', data: "id"},
                {name: "name", title: "规格名称","orderable": false,  data: "name"},
                {name: "value", title: "规格值","orderable": false,  data: "value"},
                {
                    name: "create_time", title: "创建时间","orderable": false,  data: "createTime", render: function (data, type, row, meta) {
                    return data;
                }
                },
                {
                    name: "oper",
                    title: "操作",
                    "orderable": false,
                    data: "status",
                    render: function (data, type, row, meat) {

                        return '<a name="del" >删除</a>-<a name="edit">编辑</a>';
                    }
                }

            ]
        });//datatable

        $("#datatable thead").css("background", "#dff0d8");
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
                                            <button type="button" class="btn btn-success" id="btn_addSpec">添加规格项</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <table id="datatable" class="table table-striped no-footer" role="grid" aria-describedby="datatable_info">

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
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">添加规格</h4>
            </div>
            <div class="modal-body" >

                <div class="row">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-2 control-label">规格名称</label>
                            <div class="col-md-8 icon-add">
                                <input type="hidden" name="id" value="">
                                <input type="text" name="name" class="form-control" data-rule="规格:required;remote[${basepath}/manage/product/norm/unique]"  placeholder="请输入规格名称...">
                            </div>
                        </div>

                        <div class="form-group" >
                            <label class="col-md-2 control-label">规格值</label>
                            <div class="col-md-8" style="height: 300px;overflow:auto;overflow-y:scroll;">
                                <div class="btn btn-success" id="btn_addSpecValue"> 添加</div>
                                <table class="table table-striped table-bordered" id="specValueTable">
                                    <thead>
                                    <tr>
                                        <th>规格值</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>

                        </div>

                    </form>

                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-info" id="btn_saveSpec">保存</button>
            </div>
        </div>
    </div>
</div><!-- /.modal -->

<script>


    $("#datatable").on("click","a[name='del']",function(e){
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
                    url: '${basepath}/manage/product/norm/delByID',
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



    $("#datatable").on("click","a[name='edit']",function(e){
        console.log("操作...");
        console.log($(this).parents('tr'));
        console.log(tables.row($(this).parents('tr')).data().id);
        var rowid = tables.row($(this).parents('tr')).data().id;
        var name = tables.row($(this).parents('tr')).data().name;
        var value = tables.row($(this).parents('tr')).data().value;
        var values = value.replace("[","").replace("]","").split(",");

        console.log(rowid)
        $("#specValueTable").empty();
        $("input[name='id']").val(rowid);
        $("input[name='name']").val(name);
        console.log(value);
        console.log(values);
        for (x in values) {
            var row = ("<tr><td><input data-rule='规格值:required' placeholder='请输入规格值'  value='"+values[x]+"' type='text'></td><td><a href='#' onclick='deleteRow(this)'>删除</a></td></tr>");
            $("#specValueTable").append(row);
        }

        $("#con-close-modal").modal("toggle");
    });//update



    $(".btn.btn-warning").click(function(e){
        swal($(this).attr("id"));
    });


    $("#btn_addSpec").on("click",function(){
        $("#specValueTable tbody").empty();
        $("input[name='name']").val("");
        $("input[name='id']").val("");
        $("#con-close-modal").modal("toggle");
    })

    /**
     * 添加规格
     */
    $("#btn_addSpecValue").on("click",function(){
        console.log("添加一行");
       var specValueTable =  $("#specValueTable");
        var row = ("<tr><td><input data-rule='规格值:required' placeholder='请输入规格值'  type='text'></td><td><a href='#' onclick='deleteRow(this)'>删除</a></td></tr>");
        var arr = new Array();
        var isUnique=1;
        $('#specValueTable input').each(function () {
            var currVal = $(this).val();
            if(""== $.trim(currVal)){
                isUnique=0;
                swal("规格值不能为空!");
                return;
            }
            arr.push(currVal);
            if (arr.length != $.unique(arr).length) {
                swal("不能有重复的规格值");
                isUnique=0;
                return false;
            }
        });
        if(isUnique==1)
        specValueTable.append(row);
        $("#specValueTable input:last").focus();

    })


    /**
     * 保存规格值
     */
    $("#btn_saveSpec").on("click",function(){
        var arr= new Array();
        var specObj = {};
        $('#specValueTable input').each(function(i){
            var currVal = $.trim($(this).val());

            if(""== currVal){
                swal("规格值不能为空!");
                return;
            }
            arr.push(currVal);
            specObj[i]=currVal;
            if( arr.length!=$.unique( arr ).length ){
                swal("不能有重复的规格值");
                return false;
            }
        });

        var specname = $("input[name='name']").val();
        var id = $("input[name='id']").val();
        if($.trim(specname)==""){
            swal("提示","规格名不能为空","warning");
            return ;
        }
        var specArr = []
        for(var i in specObj){
            console.log(specObj[i]);
            specArr.push(specObj[i]);
        }

        console.log("规格名：",specname);
        console.log("规格值：",specArr);
        if(specArr.length>0){
            $.ajax({
                url:"${basepath}/manage/product/norm/save",
                data:{id:id,name:specname,value:specArr},
                success:function(d){
                    console.log("添加规格返回值",d);
                    if(d=="success"){
                        if(id==""){
                            swal("添加成功!");
                        }else{
                            swal("修改成功!");
                        }
                        $("#con-close-modal").modal("toggle");
                        tables.ajax.reload();
                    }else{
                        swal("添加失败!");
                    }
                }
            })
        }else{
            swal('至少添加一个规格值。');
        }




    })




    function deleteRow(rows){
        $(rows).parent("td").parent("tr").remove();
    }
    //  查询出将要删除的行所在的位置index

    function getElementOrder(field){

        var i = 0;

        var order = 0;

        var elements = document.getElementsByName(field.name);

        for(i=0;i<elements.length;i++){

            order++;

            if(elements[i]==field){

                break;

            }

        }

        return order;

    }

    function showShopLink(obj){
        $("#"+$(obj).attr("openid")).show();
    }

    function closeShopLink(obj){
        $("#"+$(obj).attr("openid")).hide();
    }


</script>
<#--</@page.pageBase>-->