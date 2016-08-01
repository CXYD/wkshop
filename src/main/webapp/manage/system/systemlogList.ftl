<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="日志管理">-->
<script>
$(function(){
    var table = $('#dataTable').DataTable({
        "processing": true,
        "serverSide": true,
        "bFilter":false,
        "language":{
            "url":"${staticpath}/datatables/Chinese.json"
        },
        "ajax": {
            url:"${basepath}/manage/systemlog/loadData",
            dataSrc:"list"
        },
        columns:[
            {name:"title", title:"标题", data:"title"},
            {name:"account", title:"账号", data:"account"},
            {name:"logTime", title:"登陆时间", data:"logTime"},
            {name:"loginIP", title:"登陆IP", data:"loginIp"},
                {name:"loginArea", title:"登陆位置", data:"loginArea"},
            {name:"diffAreaLogin", title:"是否异地登录", data:"diffAreaLogin",render:function(data,type,row,meta){
				return data=="y"?"是":"否";
            }}
        ]
    });
});
</script>
	<form action="${basepath}/manage/systemlog" method="post" theme="simple">
				<table class="table table-bordered">
					<tr>
						<td style="text-align: right;">是否异登陆</td>
						<td style="text-align: left;">
							<#assign y_n = {'':"全部",'y':'是','n':'否'}>
                            <select id="diffAreaLogin" name="diffAreaLogin">
							<#list y_n?keys as key>
                                <option value="${key}">${y_n[key]}</option>
							</#list>
                            </select>
						</td>
						<td>登陆账号</td>
						<td><input type="text" class="input-medium search-query" name="account"/></td>
					</tr>
				</table>

				<table class="table table-bordered">
					<tr>
						<td colspan="16">
<!-- 								<i class="icon-search icon-white"></i> 查询 -->

							<button method="selectList" class="btn btn-primary" table-id="dataTable" onclick="return selectList(this)">
								<i class="icon-search icon-white"></i> 查询
							</button>
						</td>
					</tr>
				</table>

        <table class="display stripe row-border cell-border" id="dataTable">
        </table>
	</form>
<#--</@page.pageBase>-->