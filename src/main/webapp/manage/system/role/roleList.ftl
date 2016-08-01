<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="角色管理">-->
<script>
    $(function(){
        var table = $('#roleListDataTable').DataTable({
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
                    return '<input type="checkbox" name="ids" value="'+data+'"/>';
                }},
                {name:"roleName", title:"角色名称", data:"roleName"},
                {name:"roleDesc", title:"角色描述", data:"roleDesc"},
                {name:"roleDbPrivilege", title:"数据库权限", data:"roleDbPrivilege"},
                {name:"status", title:"状态", data:"status",render:function(data,type,row,meta){
                    if(data == "y"){
                        return '<img src="${basepath}/resource/images/action_check.gif">';
                    } else {
                        return '<img src="${basepath}/resource/images/action_delete.gif">';
                    }
                }},
                {name:"oper", title:"操作", data:"id",render: function (data, type, row, meta) {

					<#if checkPrivilege("/manage/role/edit")>
                        return '<a href="${basepath}/manage/role/toEdit?id=' + data + '">编辑</a>';
					<#else>
                        return "";
					</#if>
					<#if checkPrivilege("/manage/role/delete")>
                        return '<a href="javascript:;">删除</a>';
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
        $("#checkAll").click(function() {
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

<form action="${basepath}/manage/role"  method="post">
	<table class="table table-bordered">
		<tr>
			<td>
				<#if checkPrivilege("role/selectList")>
					<a href="${basepath}/manage/role/selectList" class="btn btn-primary">
						<i class="icon-search icon-white"></i> 查询
					</a>
				</#if>
				<#if checkPrivilege("role/insert")>
					<a href="${basepath}/manage/role/toAdd" class="btn btn-success">
						<i class="icon-plus-sign icon-white"></i> 添加
					</a>
				</#if>
			</td>
		</tr>
	</table>

	<table class="display stripe row-border cell-border" id="roleListDataTable">
	</table>
</form>
<#--</@page.pageBase>-->