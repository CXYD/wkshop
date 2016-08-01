<#import "/manage/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="配送方式">
<style>
	.td_right{text-align: right;}
</style>
<script type="text/javascript">
	$(function() {
		 $("#code").focus();
	});
</script>
</head>

<body>
<#if e.id??>
    <#assign formAction="update">
<#assign insertAction=false />
<#else >
<#assign formAction="insert">
    <#assign insertAction=true />
</#if>

<form action="${basepath}/manage/express" id="form" method="post">
		<table class="table table-bordered">
			<tr>
				<td colspan="2" style="background-color: #dff0d8;text-align: center;">
					<strong>配送方式编辑</strong>
				</td>
			</tr>
			<tr style="display:none;">
				<th>id</th>
				<td><input type="hidden" name="id" value="${e.id!""}"></td>
			</tr>
			<tr>
				<th class="td_right">编码</th>
				<td style="text-align: left;">
					<#if insertAction>
						<input type="text" name="code" value="${e.code!""}" id="code"  data-rule="编码:required;code;remote[unique, id];"/>
					<#else>
						<input type="text" name="code" id="code" value="${e.code!""}" data-rule="编码:required;code;remote[unique, id];"/>
					</#if>
				</td>
			</tr>
			<tr>
				<th class="td_right">名称</th>
				<td style="text-align: left;">
                    <#if insertAction>
                        <input type="text" name="name" id="name"  data-rule="名称:required;name;"/>
                    <#else>
                        <input type="text" name="name" id="name" value="${e.name!""}" data-rule="名称:required;name;"/>
                    </#if>
				</td>
			</tr>
            <tr>
                <th class="td_right">费用</th>
                <td style="text-align: left;">
                	<#if insertAction>
                		<input type="text" name="fee" id="fee" data-rule="费用:required;fee;"/>
                	<#else>	
                		<input type="text" name="fee" id="fee" value="${e.fee}" data-rule="费用:required;fee;"/>
                	</#if>
                </td>
            </tr>
            <tr>
                <th class="td_right">顺序</th>
                <td style="text-align: left;">
                	<#if insertAction>
                		<input type="text" name="ordinal" id="ordinal" data-rule="顺序:required;"/>
                	<#else>
                		<input type="text" name="ordinal" id="ordinal" value="${e.ordinal!""}" data-rule="顺序:required;"/>
                	</#if>
                </td>
            </tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<#if insertAction>
						<button method="insert" class="btn btn-success">
							<i class="icon-ok icon-white"></i> 新增
						</button>
                        <#else >
						<button method="update" class="btn btn-success">
							<i class="icon-ok icon-white"></i> 保存
						</button>
                    </#if>
				</td>
			</tr>
		</table>
</form>
</@page.pageBase>