<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="区域管理">-->

<body>
<style>
    .td_right{text-align: right;}
</style>

<form action="${basepath}/manage/area" id="form" method="post">
   <input type="hidden" name="id" value="${e.id!""}">
	<table class="table table-bordered">
		<tr>
			<td colspan="2" style="background-color: #dff0d8;text-align: center;">
				<strong>区域编辑</strong>
			</td>
		</tr>
		<tr>
			<th class="td_right">所属上级</th>
			<td style="text-align: left;">
				<input type="text" name="pname" value="${pname!"-"}" disabled="">
			</td>
		</tr>
		<tr>
			<th class="td_right">区域名称</th>
			<td style="text-align: left;">
                <input type="text" name="areaName" value="${e.areaName!""}" data-rule="名称:required;length[1~20]">
			</td>
		</tr>
		<tr >
			<td colspan="2" style="text-align: center;">
				<#if e.id??>
					<button method="update" class="btn btn-success">
						<i class="icon-ok icon-white"></i> 修改
					</button>
				<#else >
					<button method="insert" class="btn btn-success">
						<i class="icon-ok icon-white"></i> 新增
					</button>
				</#if>
			</td>
		</tr>
	</table>
</form>
<#--</@page.pageBase>-->