<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="键值对管理">-->
<script>
	$(function(){
        var table = $('#dataTables-example').DataTable({
            "ajax": {
				url:"loadData",
				dataSrc:"list"
            },
//            serverParams:function(data){
//				$.each($("form").serializeArray(),function(ix,v){
//					data[v.name]= v.value;
//				})
////			$.extend(data, $("form").serialize());
//			},
			columns:[
                {name:"ID", "orderable": false, title:'<input type="checkbox" id="firstCheckbox"/>', data:"id",render:function ( data, type, row, meta ) {
                    // 'sort', 'type' and undefined all just use the integer
                    return '<input type="checkbox" name="ids" value="'+data+'"/>';
                }},
				{name:"kValue", title:"key", data:"kValue"},
				{name:"vValue", title:"value", data:"vValue"},
                {name:"createtime", title:"创建时间", data:"createTime",render:function(data,type,row,meta){
                    return data;
                }},
                {name:"oper", title:"操作", data:"id",render: function (data, type, row, meta) {
					<#if checkPrivilege("/manage//keyvalue/edit")>
                        return '<a href="${basepath}/manage//keyvalue/toEdit?id=' + data + '">编辑</a>&nbsp;<a href="${basepath}/manage//keyvalue/deleteByID?id=' + data + '">删除</a>';
					<#else>
                        return "";
					</#if>
                }}
			]
        });
	});
</script>
<style type="text/css">
.titleCss {
	background-color: #e6e6e6;
	border: solid 1px #e6e6e6;
	position: relative;
	margin: -1px 0 0 0;
	line-height: 32px;
	text-align: left;
}

.aCss {
	overflow: hidden;
	word-break: keep-all;
	white-space: nowrap;
	text-overflow: ellipsis;
	text-align: left;
	font-size: 12px;
}

.liCss {
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
	height: 30px;
	text-align: left;
	margin-left: 10px;
	margin-right: 10px;
}
</style>
<form action="${basepath}/keyvalue/loadData" method="post">
	<table class="table table-bordered table-condensed">
		<tr>
			<td style="text-align: right;">key</td>
			<td style="text-align: left;" >
			<input type="text" class="input-small" id="kValue" name="kValue">
			</td>
			<td style="text-align: right;">value</td>
			<td style="text-align: left;" >
			<input type="text" class="input-small" id="vValue" name="vValue">
			</td>
		</tr>
		<tr>
			<td colspan="4">
            <#if checkPrivilege("/manage/keyvalue/search") >
					<button method="selectList" id="btnSearch" class="btn btn-primary" table-id="dataTables-example" onclick="return selectList(this)">
						<i class="icon-search icon-white"></i> 查询
					</button>
             </#if>
				<#if checkPrivilege("/manage/keyvalue/insert") >
                <a href="${basepath}/manage//keyvalue/toAdd" class="btn btn-success"><i class="icon-plus-sign icon-white"></i> 添加</a>
				</#if>

				<div style="float: right;vertical-align: middle;bottom: 0px;top: 10px;">
                    <#--<#include "/manage/system/pager.ftl"/>-->
				</div>

			</td>
		</tr>
	</table>

    <table class="display stripe row-border cell-border" id="dataTables-example">
    </table>
    
</form>
<#--</@page.pageBase>-->