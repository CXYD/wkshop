<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="号码库管理">-->

<script>
    var tables;

  function loadDataTables(status,pici){
      tables = $('#datatable').DataTable({
          "processing": true,
          "serverSide": true,
          "bFilter":false,
          "destroy": true,
          "language":{
              "url":"${staticpath}/datatables/Chinese.json"
          },
          "ajax": {
              url:"${basepath}/manage/data/number/loadData",
              dataSrc:"list",
              data:{status:status,pici:pici}
          },
          columns:[
//                {name:"label_id", "orderable": false, title:'ID', data:"labelId"},
              {name:"batchname", title:"批次名","orderable": false,  data:"batchname"},
              {name:"phonenum", title:"号码", "orderable": false, data:"phonenum"},
              {name:"state", title:"状态","orderable": false,  data:"state",render:function(data,type,row,meta){
                  if(data==0){
                      return "正常";
                  }else if(data==1){
                      return "失效";
                  }else if(data==2){
                      return "占用";
                  }else if(data==3){
                      return "已售";
                  }
              }},
              {name:"numtype", title:"号码类型","orderable": false,  data:"numtype",render:function(data,type,row,meta){
                  if(data==0){
                      return "老号";
                  }else if(data==1){
                      return "新号";
                  }
              }},
              {name:"create_time", title:"创建时间","orderable": false,  data:"createTime",render:function(data,type,row,meta){
                  return data;
              }}
          ]
      });//datatable
  }

    $(function(){

        loadDataTables();
        $("#datatable thead").css("background","#dff0d8");

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
                                            <button type="button" class="btn btn-success" name="btnOpenImpl">导入号码</button>
                                            <#--<button type="button" class="btn btn-default" >修改状态</button>-->
                                            <button type="button" class="btn btn-danger" onclick="btnDelPici()" >删除批次</button>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div id="datatable_filter" class="dataTables_filter pull-right">

                                        <select name="selectStatus" aria-controls="datatable" class="form-control input-sm">
                                            <option value="">状态</option>
                                            <option value="0">可用</option>
                                            <option value="1">失效</option>
                                            <option value="2">占用</option>
                                            <option value="3">已售</option>
                                        </select>

                                        <select name="selectPICI" aria-controls="datatable" class="form-control input-sm">
                                            <option value="">选择批次</option>
                                        </select>
                                        <div class="input-group">
                                                <span class="input-group-btn">
                                                <button type="button" id="btnseach" class="btn btn-effect-ripple btn-primary"><i class="fa fa-search"></i></button>
                                                </span>
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
        <form action="${basepath}/manage/product/lable" id="form" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">导入号码</h4>
                </div>
                <div class="modal-body">

                    <div class="row">
                        <form class="form-horizontal">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-label">批次名称</label>
                                <div class="col-md-8">
                                    <input type="text" name="name" class="form-control"   placeholder="请输入批次名称...">
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-label">号码类别</label>
                                <div class="col-md-8">
                                    <select name="type">
                                        <option value="">选择号码类别</option>
                                        <option value="0">老号码</option>
                                        <option value="1">新号码</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-label">导入号码</label>
                                <div class="col-md-8">
                                   <input id="filePath" name="filePath" style="display:none;" type="file">
                                    <div class="btn btn-default" name="fileLoad" id="fileLoad" onclick="$('#filePath').click()">上传文件</div>
                                </div>
                            </div>
                        </form>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                    <button type="button" onclick="fileupload()" method="insert"   class="btn btn-info">保存</button>
                </div>
            </div>
        </form>
    </div>

</div><!-- /.modal -->



<div id="selectPici" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">

    <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">选择批次</h4>
                </div>
                <div class="modal-body">

                    <div class="row">
                        <form class="form-horizontal">

                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-label">选择删除批次</label>
                                <div class="col-md-8">
                                    <select name="sel_pic">
                                        <option value="">选择批次</option>
                                    </select>
                                </div>
                            </div>


                        </form>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                    <button type="button" onclick="delSelPici()" class="btn btn-info">确定</button>
                </div>
            </div>
    </div>

</div><!-- /.modal -->

<script>

    $(document).ready(function(){
        initPiciSel();
    })

    $("#filePath").change(function(){
        $("#fileLoad").text($("#filePath").val());
    })

    $("button[name='btnOpenImpl']").click(function(){
        $("#filePath").val("");
        $("input[name='name']").val("");
        $("select[name='type']").val("");
        $("#fileLoad").text("上传文件");
        $("#con-close-modal").modal("toggle");
    })

    function fileupload(){
        var fileName = $("#filePath").val();
        var name = $("input[name='name']").val();
        var type = $("select[name='type']").val();

        if(fileName==""){
            swal("上传文件不能为空!");
            return false;
        }

        if(type==""){
            swal("请选择号码类别!");
            return false;
        }

        if(name==""){
            swal("批次名称不能为空!");
            return false;
        }else{
            $.ajax({
                url:"${basepath}/manage/data/number/unique",
                data:{name:name},
                success:function(d){
                    console.log(d);
                    if(d=="error"){
                        swal("批次名称重复！");
                        return;
                    }else{
                        //对文件格式进行校验
                        var d1=/\.[^\.]+$/.exec(fileName);
                        if(d1==".xls"){
                            $.ajaxFileUpload({
                                        url:"${basepath}/manage/data/number/importFile",
                                        secureuri:false,
                                        fileElementId:'filePath',
                                        data:{name:name,type:type},
                                        dataType: 'text/xml',
                                        success: function (data) {
                                            swal("导入成功!");
                                            initPiciSel();
                                            tables.ajax.reload();
                                            $("#con-close-modal").modal("toggle");
                                        },error: function (data, status, e){
                                            swal("导入失败!");
                                        }
                                    }
                            );
                        }else{
                            swal("只能导入.xls的excel文件！");
                        }

                    }
                }

            })
        }






    }

    function btnDelPici(){
        $("#selectPici").modal("toggle");
    }


    function delSelPici(){
        console.log();
        var selpici = $("select[name='sel_pic']").val();
        if(selpici!=""){

            $.ajax({
                url:"${basepath}/manage/data/number/deletePici",
                data:{pici:selpici},
                success:function(d){
                    if(d>0){
                        sweetAlert("删除成功!");
                        $("#selectPici").modal("toggle");
                        initPiciSel();
                        tables.ajax.relaod();
                    }else{
                        sweetAlert("删除失败!");
                    }
                }
            })
        }else {
            sweetAlert("请选择批次!");
        }


    }


    function initPiciSel(){
        $.ajax({
            url:"${basepath}/manage/data/number/selectKeyValue",
            success:function(d){
                console.log(d);
                $("select[name='selectPICI']").empty();
                var opt="<option value=''>全部</option>";
                for(var i in d){
                    opt = "<option value='"+d[i].skey+"'>"+ d[i].svalue+"</option>" +opt;

                }
                $("select[name='selectPICI']").append(opt);
            }
        });
    }

    <#--$("select[name='selectPICI']").click(function(e){-->
        <#--$.ajax({-->
            <#--url:"${basepath}/manage/data/number/selectKeyValue",-->
            <#--success:function(d){-->
                <#--console.log(d);-->
                <#--$("select[name='selectPICI']").empty();-->
                <#--var opt="<option value=''>全部</option>";-->
                <#--for(var i in d){-->
                    <#--opt = "<option value='"+d[i].skey+"'>"+ d[i].svalue+"</option>" +opt;-->

                <#--}-->
                <#--$("select[name='selectPICI']").append(opt);-->
            <#--}-->
        <#--});-->
    <#--})-->

    $("select[name='sel_pic']").click(function(e){
        $.ajax({
            url:"${basepath}/manage/data/number/selectKeyValue",
            data:{khid:"123"},
            success:function(d){
                console.log(d);
                $("select[name='sel_pic']").empty();

                var opt="";
                for(var i in d){
                    opt = "<option value='"+d[i].skey+"'>"+ d[i].svalue+"</option>" +opt;

                }
                $("select[name='sel_pic']").append(opt);
            }
        });
    })

    $("#btnseach").click(function(e){
        var state = $("select[name='selectStatus']").val();
        var  pici = $("select[name='selectPICI']").val();
        loadDataTables(state,pici);

    })

</script>
<script src="${basepath}/static/js/ajaxfileupload.js"></script>
<#--</@page.pageBase>-->