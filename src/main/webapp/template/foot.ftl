<style>
	
	/* IndexBottom */
.IndexBottom{padding-top:20px;padding-bottom:10px;border-top: 2px solid #f40;}
.IndexBottom dl{float: left;display: inline;width: 110px;margin-top: 22px;margin-left: 43px;}
.IndexBottom dt{margin-left: 5px;font-family: "Microsoft YaHei",\5fae\8f6f\96c5\9ed1,\5b8b\4f53;font-size: 16px;}
.IndexBottom dd{margin-top: 4px;}
.IndexBottom dd span{display: block;margin-top: 4px;}
/* .IndexBottom dd span a{background: url(../i/index_sprite.png) no-repeat -170px -336px;padding-left: 18px;+background-position: -170px -338px;_background-position: -170px -336px;} */
/* .IndexBottom dd span a:hover{background: url(../i/index_sprite.png) no-repeat -141px -316px;+background-position: -141px -318px;_background-position: -141px -316px;} */
.IndexBottom .aboutjy{margin-left: 68px;}
.IndexBottom .IndexBottom-help{margin-left: 112px;}
</style>
	<div id="footer" style="margin-top: 20px;">
	
<!-- 	<nav class="navbar navbar-default navbar-fixed-bottom" role="navigation"> -->
	
		<div class="container">
			<div class="row IndexBottom">
				<#if GlobalData["cms.articleCategoryList"]?? && GlobalData["cms.articleCategoryList"]?size gt 0>
				<#list GlobalData["cms.articleCategoryList"] as item>
					<div class="col-xs-2" style="text-align: center;">
						<div class="row" style="margin-bottom: 10px;"><strong>${item.categoryName!""}</strong></div>
						<#if item.articles?? && item.articles?size gt 0>
						    <#list item.articles as item>
                                <div class="row" style="line-height: 20px;">
                                    <a href="${basepath}/cms/article/${item.code}.html" target="_blank">
                                    ${item.title!""}
                                    </a>
                                </div>
							</#list>
						</#if>
					</div>
				</#list>
				</#if>
			</div>
			<hr style="margin: 20px 0px 0px">
			<!-- 友情链接 -->
			<div class="row" >
				<div class="col-xs-12" style="text-align: center;">
					<div style="text-align: center;margin: auto;">
						<#if GlobalData["cms.friendLinkList"]?? && GlobalData["cms.friendLinkList"]?size gt 0>
						    <#list GlobalData["cms.friendLinkList"] as item>
                                <div style="float: left;margin: 5px;">
                                    <a href="http://${item.linkUrl!""}" target="_blank">${item.linkName!""}</a>
                                </div>
						    </#list>
						</#if>
					</div>
				</div>
			</div>
			<hr style="margin: 0px;">
			<div class="row" style="margin-top: 5px;display: inline;">
				<div class="col-xs-3">
				</div>
				<div class="col-xs-1">
					<!-- cnzz站点统计 -->
					${systemSetting().statisticsCode!""}
				</div>
				<div class="col-xs-3">
				</div>
			</div>
		</div>
	</div>

<#include "/template/fixed.ftl"/>
<script>
$(function() {
	$("#myshopMenuPPP").hover(
		function(){
			$("#myshopMenu").show();
		},
		function(){
			$("#myshopMenu").hide();
		}		
	);
	
	//$("img.lazy").lazyload();
	
	$("img.lazy").lazyload({
		//threshold : 200,
		//placeholder : "http://imgt4.bdstatic.com/it/u=2281794157,3480422365&fm=21&gp=0.jpg",
		//event : "click"
		effect : "fadeIn"
		//event:"mouseover"
	});
});
</script>
