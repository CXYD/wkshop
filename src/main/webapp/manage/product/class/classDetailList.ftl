<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商品分类">-->


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
                data:{labelid:'${id}'},
                url:"${basepath}/manage/product/productManage/loadClassDetailData",
                dataSrc:"list"
            },
            columns:[
//                {name:"ID", "orderable": false, title:'ID', data:"id"},
                {name:"product_name", title:"商品名称", data:"productName"},
                {name:"sell_price", title:"价格", data:"sellPrice"},
                {name:"sale", title:"销量", data:"sale"},
                {name:"store_nums", title:"库存", data:"storeNums"},
                {name:"brand_id", title:"标签", data:"labelName"},
                {name:"create_time", title:"创建时间", data:"createTime",render:function(data,type,row,meta){
                    return data;
                }},
                {name:"type", title:"商品类型", data:"type"},
                {name:"oper", title:"操作","orderable": false,  data:"status",render:function(data,type,row,meat){

                        return '<a name="cancel">取消分类</a>';
                }}

            ]
        });//datatable

        $("#datatable thead").css("background","#dff0d8");





        $("#datatable").on("click","a[name='cancel']",function(e){
            console.log("操作...");
            console.log($(this).parents('tr'));
            console.log(tables.row($(this).parents('tr')).data().id);
            var rowid = tables.row($(this).parents('tr')).data().id;

            console.log(rowid)
            $.ajax({
                url: 'updateStatus',
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


    });

function goback(){
    $('.wraper').load("${basepath}/manage/product/class/selectList");
}


</script>

<row>
    <div class="panel panel-default">
        <div class="panel-heading">
            <button class="btn btn-success" onclick="goback()">返回</button>
            <input type="hidden" name="id" value="${id}">
            <h5>${name}分类下的商品</h5>
        </div>
        <div class="panel-body">
            <table id="datatable" class="table table-striped table-bordered dataTable hover" role="grid" aria-describedby="datatable_info">

            </table>
        </div>
    </div>
</row>

<#--</@page.pageBase>-->