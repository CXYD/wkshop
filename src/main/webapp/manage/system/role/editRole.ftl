<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="角色管理">-->
<SCRIPT type="text/javascript">
	$(function(){
		var setting = {
			check: {
				enable: true,
				dblClickExpand: false
			},view: {
				fontCss: getFontCss
			},callback: {
				onClick: onClick
			}
		};
		function onClick(e,treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo2");
			zTree.expandNode(treeNode);
		}

		function getFontCss(treeId, treeNode) {
			return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
		}

		loadMenusTree($("#id").val());

		//加载菜单树
		function loadMenusTree(id){
			$.ajax({
				url:"${basepath}/manage/menu/getMenusByPid?pid=0",
				type:"get",
				data:{id:id},
				dataType:"text",
				success:function(data, textStatus){
					var zNodes = eval('('+data+')');
					$.fn.zTree.init($("#treeDemo2"), setting, zNodes);

					$("#role_name").focus();
				},
				error:function(){
					alert("error");
				}
			});
		}
		//全部展开
		$("#expandOrCollapseAllBtn").bind("click", {type:"expandOrCollapse"}, expandNode);
		$("#checkAllTrueOrFalse").bind("click", {type:"checkAllTrueOrFalse"}, expandNode);

        //编辑角色
        $("#saveRoleBtn").click(function(){
            var roleName = $("#roleName").val();
            if(roleName==''){
                alert("角色名称不能为空！");
                return;
            }
            //jQuery.blockUI({ message: "处理中，请稍候...", css: {color:'#fff',border:'3px solid #aaa',backgroundColor:'#CC3300'},overlayCSS: { opacity:'0.0' }});

            $.blockUI({ message: "处理中，请稍候...",css: {
                border: 'none',
                padding: '15px',
                backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                opacity: .5,
                color: '#fff'
            } });

            var ids = "";
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo2");
            var nodes = treeObj.getCheckedNodes(true);
            for(var i=0;i<nodes.length;i++){
                ids+=nodes[i].id+",";
            }
			var roleEditUrl = "${basepath}/manage/role/" + $("#insertOrUpdate").val()
            $.ajax({
                url: roleEditUrl ,
                type : "post",
                data : {
                    privileges : ids,
                    id : $("#id").val(),
                    roleName : roleName,
                    roleDesc : $("#roleDesc").val(),
                    roleDbPrivilege : $("#roleDbPrivilege").val(),
                    status:$("#status").val()
                },
                dataType : "text",
                success : function(data) {
                    window.location = "${basepath}/manage/role/back";
                },
                error : function() {
                    jQuery.unblockUI();
                    alert("修改角色失败！");
                }
            });
            return false;
        });
	});
		
	var expandAllFlg = true;
	var checkAllTrueOrFalseFlg = true;
	function expandNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo2"),
		type = e.data.type,
		nodes = zTree.getSelectedNodes();


		if (type == "expandAll") {
			zTree.expandAll(true);
		} else if (type == "collapseAll") {
			zTree.expandAll(false);
		} else if (type == "expandOrCollapse") {
			zTree.expandAll(expandAllFlg);
			expandAllFlg = !expandAllFlg;
		} else if (type == "checkAllTrueOrFalse") {
			zTree.checkAllNodes(checkAllTrueOrFalseFlg);
			checkAllTrueOrFalseFlg = !checkAllTrueOrFalseFlg;
		} else {
			if (type.indexOf("All")<0 && nodes.length == 0) {
				alert("请先选择一个父节点");
			}
			var callbackFlag = $("#callbackTrigger").attr("checked");
			for (var i=0, l=nodes.length; i<l; i++) {
				zTree.setting.view.fontCss = {};
				if (type == "expand") {
					zTree.expandNode(nodes[i], true, null, null, callbackFlag);
				} else if (type == "collapse") {
					zTree.expandNode(nodes[i], false, null, null, callbackFlag);
				} else if (type == "toggle") {
					zTree.expandNode(nodes[i], null, null, null, callbackFlag);
				} else if (type == "expandSon") {
					zTree.expandNode(nodes[i], true, true, null, callbackFlag);
				} else if (type == "collapseSon") {
					zTree.expandNode(nodes[i], false, true, null, callbackFlag);
				}
			}
		}
	}


</SCRIPT>
<#if e.id??>
	<#assign formAction="update">
	<#assign insertAction=false />
<#else >
	<#assign formAction="insert">
	<#assign insertAction=true />
</#if>
<form action="${basepath}/manage/role" method="post" name="form1" id="form1">
	<table class="table table-bordered" style="width: 500px;margin: auto;">
		<input type="hidden" id="insertOrUpdate" value="${formAction}" />
		<tr>
			<td colspan="2" style="background-color: #dff0d8;text-align: center;">
				<strong>角色编辑</strong>
			</td>
		</tr>
		<tr style="display: none;">
			<th>id</th>
			<td><input type="hidden" name="id" id="id" value="${e.id!""}"/></td>
		</tr>
		<tr>
			<th style="background-color: #dff0d8;text-align: center;">角色名称</th>
			<td style="text-align: left;">
				<#if insertAction>
					<input type="text" name="roleName" id="roleName" value="${e.roleName!""}" data-rule="帐号:required;roleName;length[4~20];remote[unique]" />
				<#else>
					<input type="text" value="${e.roleName!""}" name="roleName" id="roleName" />
				</#if>
			</td>
		</tr>
		<tr>
			<th style="background-color: #dff0d8;text-align: center;">角色描述</th>
			<td style="text-align: left;">
				<input type="text" value="${e.roleDesc!""}" name="roleDesc" id="roleDesc" data-rule="描述:required;roleDesc;length[2~50]" />
			</td>
		</tr>
		<tr>
			<th style="background-color: #dff0d8;text-align: center;">数据库权限</th>
			<td style="text-align: left;">
				<select name="roleDbPrivilege" id="roleDbPrivilege">
					<#assign map_dbPrivilege ={'select':'select','select,insert':'select,insert','select,insert,update':'select,insert,update','select,insert,update,delete':'select,insert,update,delete'}/>
					<#list map_dbPrivilege?keys as item>
						<option value="${item}" <#if e.roleDbPrivilege?? && e.roleDbPrivilege==item>selected="selected" </#if>>${map_dbPrivilege[item]}</option>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<th style="background-color: #dff0d8;text-align: center;">状态</th>
			<td style="text-align: left;" >
				<select name="status" id="status" class="input-small">
					<#assign y_n ={'y':'启用','n':'禁用'}/>
					<#list y_n?keys as item>
						<option value="${item}" <#if e.status?? && e.status==item>selected="selected" </#if>>${y_n[item]}</option>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<th style="background-color: #dff0d8;text-align: center;">角色权限</th>
			<td>
				<div id="optionDiv">
					[<a id="expandOrCollapseAllBtn" href="#" title="展开/折叠全部资源" onclick="return false;">展开/折叠</a>]
					[<a id="checkAllTrueOrFalse" href="#" title="全选/全不选" onclick="return false;">全选/全不选</a>]
				</div>
				<ul id="treeDemo2" class="ztree"></ul>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;" colspan="2">
				<#if insertAction>
                    <button id="saveRoleBtn" class="btn btn-success">
                        <i class="icon-ok icon-white"></i> 新增
                    </button>
				<#else >
                    <button id="saveRoleBtn" class="btn btn-success">
                        <i class="icon-ok icon-white"></i> 保存
                    </button>
				</#if>
			</td>
		</tr>
	</table>
</form>
<#--</@page.pageBase>-->