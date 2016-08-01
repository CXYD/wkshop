<#import "/manage/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="配送方式">
<script>
    $(function(){
        var table = $('#expressListDataTable').DataTable({
            "ajax": {
                url:"loadData",
                dataSrc:"list"
            },
            columns:[
                {name:"ID", "orderable": false, title:'<input type="checkbox" id="firstCheckbox"/>', data:"id",render:function ( data, type, row, meta ) {
                    return '<input type="checkbox" name="ids" value="'+data+'"/>';
                }},
                {name:"code", title:"快递编码", data:"code"},
                {name:"name", title:"名称", data:"name"},
                {name:"fee", title:"费用", data:"fee"},
                {name:"ordinal", title:"顺序", data:"ordinal",render:function(data,type,row,meta){
                    return data;
                }},
                {name:"oper", title:"操作", data:"id",render: function (data, type, row, meta) {

					<#if checkPrivilege("/manage/express/edit")>
                        return '<a href="${basepath}/manage/express/toEdit?id=' + data + '">编辑</a>';
					<#else>
                        return "";
					</#if>
                }}
            ]
        });

        $("#result_table tr").mouseover(function(){
            $(this).addClass("over");}).mouseout(function(){
            $(this).removeClass("over");});

        //全选、反选
        $("#firstCheckbox").click(function() {
            $('input[type=checkbox]').attr("checked",$(this).attr("checked")?true:false);
        });
    });

    function deleteSelect(){
        if($("input:checked").size()==0){
            return false;
        }
        return confirm("确定删除选择的记录?");
    }
</script>

	<form action="${basepath}/manage/express" method="post">
		<table class="table table-bordered">
			<tr>
				<td style="text-align: right;">快递编码</td>
				<td style="text-align: left;">
					<input type="text" class="input-medium search-query" name="code"/>
				</td>
				<td style="text-align: right;">名称</td>
				<td>
					<input type="text" class="input-medium search-query" name="name"/>
				</td>
			</tr>
		</table>
		<table class="table table-bordered">
			<tr>
				<td colspan="8">
					<button method="selectList" class="btn btn-primary" table-id="expressListDataTable" onclick="return selectList(this)">
						<i class="icon-search icon-white"></i> 查询
					</button>
					<a href="${basepath}/manage/express/toAdd" class="btn btn-success">
						<i class="icon-plus-sign icon-white"></i> 添加
					</a>
					<button method="deletes" class="btn btn-danger" onclick="return submitIDs(this,'确定删除选择的记录?');">
						<i class="icon-remove-sign icon-white"></i> 删除
					</button>
							
					<div style="float: right;vertical-align: middle;bottom: 0px;top: 10px;">
						<#--<#include "/manage/system/pager.ftl">-->
					</div>
				</td>
			</tr>
		</table>
		<table class="display stripe row-border cell-border" id="expressListDataTable">
    	</table>
	</form>

</@page.pageBase>
