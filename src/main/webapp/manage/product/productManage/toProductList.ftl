<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商品管理">-->

<script>
    var tables=null;
    $(function() {
         loadDataTable();
          $("#datatable thead").css("background","#dff0d8");
    });

    function loadDataTable(status,type,name){


//        if (tables == undefined || tables == "undefined" || tables == null) {

//        }else{
//            tables.destroy();
//        }
        var typeArr =["","宽带新装","宽带续费","手机","上网卡","手机号","其他"]

        tables = $('#datatable').DataTable({
            "processing": true,
            "serverSide": true,
            "bFilter": false,
            "destroy": true,
            "language": {
                "decimal":",",
                "thousands":".",
                "url": "${staticpath}/datatables/Chinese.json"
            },
            "ajax": {
                url: "${basepath}/manage/product/productManage/loadData",
                dataSrc: "list",
                data:{status:status,type:type,name:name}
            },
            "aoColumnDefs": [
                {
                    sDefaultContent: '',
                    aTargets: ['_all']
                }
            ],
            columns: [
                {
                    name: "ID",
                    orderable: false,
                    title: '<input type="checkbox" id="firstCheckbox"/>',
                    data: "id",
                    render: function (data, type, row, meta) {
                        // 'sort', 'type' and undefined all just use the integer
                        return '<input type="checkbox" name="ids" value="' + data + '"/>';
                    }
                },
                {name: "product_name", title: "商品名称", "orderable": false, data: "productName"},
                {name: "sell_price", title: "价格","orderable": false,  data: "sellPrice",render:function(data,type,row,meat){
                    return formatCurrency(data);
                }},
                {name: "sale", title: "销量","orderable": false,  data: "sale"},
                {name: "brand_id", title: "标签","orderable": false,  data: "labelName"},
                {name: "create_time", title: "创建时间","orderable": false,  data: "createTime"},
                {name: "type", title: "商品类型","orderable": false,  data: "type",render:function(data,type,row,meat){
                    return typeArr[data];
                }},
                {name: "product_status", title: "状态","orderable": false,  data: "productStatus", render:function(data,type,row,meat){
                    if(data==1){
                        return "已上架";
                    }else{
                        return "已下架";
                    }
                }},
                {
                    name: "oper",
                    title: "操作",
                    "orderable": false,
                    data: "status",
                    render: function (data, type, row, meat) {
                        var id = row.id;
                        return ' <div class="" style="position: absolute;z-index: 99;" id="shoplink" hidden > <p> <img src="" alt=""> </p></div>'
                        +'<a name="edit">编辑</a>-<a name="linkurl" id="openlink" data-id='+id+'>链接</a>' ;
                    }
                }

            ]
        }); //datatable
//        $('tr:last[role="row"]').hide();
        console.log($('tr[role="row"]').html());
//        tables.draw();
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
                                            <button type="button" class="btn btn-success" id="add">添加商品</button>
                                            <button type="button" class="btn btn-default" id="change">改分类</button>
                                            <button type="button" class="btn btn-default" method="deleteS" onclick="return operIDs(this,'确定删除选择的记录?');">删除</button>
                                            <button type="button" class="btn btn-default" id="updateUp" method="updateUp" onclick="return operIDs(this,'确定上架选择的记录?');">上架</button>
                                            <button type="button" class="btn btn-default" id="updateDown" method="updateDown" onclick="return operIDs(this,'确定下架选择的记录?');">下架</button>
                                            <#--<button type="button" class="btn btn-default" id="export">导出商品</button>-->
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div id="datatable_filter" class="dataTables_filter pull-right">

                                        <select name="selectStatus" aria-controls="datatable" class="form-control input-sm">
                                            <option value="">商品状态</option>
                                            <option value="0">已下架</option>
                                            <option value="1">已上架</option>
                                            <option value="3">已售罄</option>
                                        </select>

                                            <select name="selectType" aria-controls="datatable" class="form-control input-sm">
                                                <option value="">选择分类</option>
                                                <option value="1">宽带新装</option>
                                                <option value="2">宽带续费</option>
                                                <option value="3">手机</option>
                                                <option value="4">上网卡</option>
                                                <option value="5">手机号</option>
                                                <option value="6">其他</option>
                                            </select>
                                        <div class="input-group">
                                            <input type="text"  name="inputName" class="form-control" placeholder="输入商品名称搜索">
                                                <span class="input-group-btn">
                                                <button type="button" name="seachName" class="btn btn-effect-ripple btn-primary"><i class="fa fa-search"></i></button>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                            <div class="col-sm-12">
                                <table id="datatable" class="table table-striped  no-footer" role="grid" aria-describedby="datatable_info">

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
        <form action="${basepath}/manage/product/productManage" id="form" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">修改分类</h4>
                </div>
                <div class="modal-body">

                    <div class="row">
                        <form class="form-horizontal">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-label">选择分类</label>
                                <div class="col-md-8">
                                    <select class="form-control input-sm" id="selectProductClass">
                                        <option value="">选择分类</option>
                                    </select>
                                </div>
                            </div>


                        </form>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                    <button type="button" method="change" id="btn_changeClass" class="btn btn-info">保存</button>
                </div>
            </div>
        </form>
    </div>

</div><!-- /.modal -->

<script>


    $(document).ready(function(){
        loadSelectClass();
    })

    $("#datatable").on("click","a[name='edit']",function(e){
        var rowid = tables.row($(this).parents('tr')).data().id;
        $('.wraper').load("${basepath}/manage/product/productManage/toEditInfo?productid="+rowid);
    });

    $("#add").click(function(){
        $('.wraper').load("${basepath}/manage/product/productManage/toAddProduct");
    })

    $("#delSel").click(function(){
        swal({
            title: "确定删除?",
            text: "你将要删除所选商品!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText:"取消",
            closeOnConfirm: false
        }, function(data){
            swal(data);
        });
    })


    $("#change").on("click",function(e){
        if ($("input:checked").size() == 0) {
            swal("请先选择要更改分类的记录！");
            return false;
        }

        $("#con-close-modal").modal('toggle');
    })



    $("#btn_changeClass").on("click",function(){

        var options=$("#selectProductClass option:selected");
        classid = options.val();

        if(classid==""){
            swal('请选择分类');
            return;
        }else {
            var id_array=new Array();
            $('input[name="ids"]:checked').each(function(){
                id_array.push($(this).val());//向数组中添加元素
            });
            $.ajax({
                url:"${basepath}/manage/product/productManage/change",
                type:"POST",
                dataType:"text",
                data:{ids:id_array,productClassId:classid},
                success:function(result){
                    console.log(result);
                    $("#con-close-modal").modal('toggle');
                    swal("更改分类成功");
//                    tables.ajax.reload();
                },error:function(){
                    swal("失败");
                }});


        }
    })


    function loadSelectClass(){
        $.ajax({
            url:"${basepath}/manage/product/class/queryClassJson",
            type:"POST",
            success:function(result){
                var options;
                console.log(result);

                for(i=0;i<result.length;i++){
                    var obj = result[i];
                    options += "<option value='"+obj.id+"'>"+obj.name+"</option>";
                }
                $("#selectProductClass").html(options);
            }});
    }


    //创建遮罩效果
    function createMark(){
        $.blockUI({ message: "系统处理中，请等待...",css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff'
        }});
    }



    //批量删除选择的记录
    function operIDs(obj,tip) {
        console.log("批量操作...");
        if ($("input:checked").size() == 0) {
            swal("请先选择要操作的内容！");
            return false;
        }



        console.log($(obj).attr("method"));

        var yes  = swal({
            title: "提示",
            text: tip,
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText:"取消",
            closeOnConfirm: false
        }, function(flag){
            if(flag){
                var id_array=new Array();
                $('input[name="ids"]:checked').each(function(){
                    id_array.push($(this).val());//向数组中添加元素
                });
                var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串
                //提交后台删除
                $.ajax({
                    url:"${basepath}/manage/product/productManage/"+$(obj).attr("method"),
                    type:"POST",
//                    dataType:"json",
                    data:{ids:id_array},
                    success:function(result){
                    console.log(result);
                        if(result=="success"){
                            swal("提示!", "操作成功.", "success");
                            tables.ajax.reload();
                        }else{
                            swal("提示!", "操作失败.", "fail");
                        }
                }});


            }
        });


        return false;
    }


    $("select[name='selectStatus']").on("change",function(d){

        console.log($(this).val());
        var name =  $("input[name='inputName']").val();
        var status = $(this).val();
        var type = $("select[name='selectType']").val();
        loadDataTable(status,type,name);

    })

    $("select[name='selectType']").on("change",function(d){
        console.log($(this).val());
        var name =  $("input[name='inputName']").val();
        var status =  $("select[name='selectStatus']").val();
        var type = $(this).val();
        loadDataTable(status,type,name);

    })

    $("button[name='seachName']").click(function(){
       var name =  $("input[name='inputName']").val();
        var status =  $("select[name='selectStatus']").val();
        var type = $("select[name='selectType']").val();
        loadDataTable(status,type,name);
    });


    $(".btn.btn-warning.btn-sm").click(function(e){
        swal($(this).attr("id"));
    });



    $("#datatable").on("mouseover", "a[name='linkurl']", function (e) {
//            $("#shoplink").show();
//        console.log($(this).offset().top);
//        console.log($(this).offset().left);
        var link = '${basepath}/getQrCode?type=1&id='+$(this).attr('data-id')
        $("#shoplink").find('img').attr('src',link)
        $("#shoplink").css("left", $(this).offset().left-550);
        $("#shoplink").css("top",$(this).offset().top-225);
        $("#shoplink").show();

    });// linkurl


    $("#datatable").on("mouseout", "a[name='linkurl']", function (e) {
        $("#shoplink").hide();
    });// linkurl


</script>
<#--</@page.pageBase>-->