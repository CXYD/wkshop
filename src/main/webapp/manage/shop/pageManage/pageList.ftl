<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="页面管理">-->

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
                url:"${basepath}/manage/pageManage/loadData",
                dataSrc:"list"
            },
            columns:[
//                {name:"label_id", "orderable": false, title:'ID', data:"labelId"},
                {name:"title", title:"页面标题", data:"title"},
                {name:"dsp", title:"页面描述", data:"dsp"},
                {name:"create_time", title:"创建时间", data:"createTime",render:function(data,type,row,meta){
                    return data;
                }},
                {name:"oper", title:"操作","orderable": false,  data:"status",render:function(data,type,row,meat){
                    var baseLink = '<a name="edit" state="1">编辑</a>-<a name="linkurl"  data-id='+row.id+'>链接地址</a>';
                    var delLink = '<a name="del">删除</a>-'

                    if(row.isindex==1){
                        return ' <div class="" style="position: absolute;z-index: 99;" id="shoplink" hidden > <p> <img src="" alt=""> </p></div>'+baseLink+'-店铺首页'
                    }else{
                        return ' <div class="" style="position: absolute;z-index: 99;" id="shoplink" hidden > <p> <img src="" alt=""> </p></div>'+delLink+baseLink+'-<a name="setHome">设为首页</a>'
                    }

                }}

            ]
        });//datatable

        $("#datatable thead").css("background","#dff0d8");

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
                closeOnConfirm: true
            }, function (flag) {
                if (flag) {

                    console.log(rowid)
                    $.ajax({
                        url: '/wkshop/manage/pageManage/delByID',
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
            var rowid = tables.row($(this).parents('tr')).data().id;

            window.open("/wkshop/manage/pageManage/toEditPage?id="+rowid);

        });//edit

        $("#datatable").on('click',"a[name=setHome]",function(){
            console.log('设为首页');
            var rowid = tables.row($(this).parents('tr')).data().id;
            $.ajax({
                url:"/wkshop/manage/pageManage/setHomePage",
                type:"POST",
                data:{id:rowid,isIndex:1},
                success:function(data){
                    if (data=="success") {
                        tables.ajax.reload();
                        swal("提示!", "操作成功.", "success");
                    };
                }
            })
        })


    });



    $("#datatable").on("mouseover", "a[name='linkurl']", function (e) {
//            $("#shoplink").show();
//        console.log($(this).offset().top);
//        console.log($(this).offset().left);
        var link = '${basepath}/getQrCode?type=3&id='+$(this).attr('data-id')
        $("#shoplink").find('img').attr('src',link)
        $("#shoplink").css("left", $(this).offset().left-550);
        $("#shoplink").css("top",$(this).offset().top-225);
        $("#shoplink").show();

    });// linkurl


    $("#datatable").on("mouseout", "a[name='linkurl']", function (e) {
        $("#shoplink").hide();
    });// linkurl


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
                                            <button type="button" class="btn btn-success" onclick="toAddPage()">新建页面</button>
                                        </div>
                                    </div>
                                </div>

                            <div class="row">
                                <div class="col-md-12">
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

    <script>
        function toAddPage(){
            window.open("/wkshop/manage/pageManage/toAddPageNew")
        }

    </script>



<#--</@page.pageBase>-->