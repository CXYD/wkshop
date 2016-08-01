<#--<div class="testModule ng-scope">-->
    <#--<style type="text/css">-->
        <#--.item_box {-->
            <#--width: 340px;-->
        <#--}-->

        <#--.upload_plus .default {-->
            <#--height: 150px;-->
        <#--}-->

        <#--.notice {-->
            <#--border-bottom: 1px solid #dcdcdc;-->
            <#--padding: 40px 40px 39px 40px;-->
        <#--}-->

        <#--.notice .notice-text {-->
            <#--font-size: 16px;-->
            <#--line-height: 24px;-->
            <#--color: #999;-->
        <#--}-->

        <#--.btn_add_ad {-->
            <#--width: 118px;-->
            <#--height: 36px;-->
            <#--background: #fff;-->
            <#--border: 1px solid #dcdcdc;-->
            <#--border-radius: 2px;-->
            <#--color: #666666;-->
            <#--font-size: 14px;-->
            <#--line-height: 36px;-->
            <#--margin-top: 25px;-->
        <#--}-->

        <#--.upload_plus {-->
            <#--height: 150px;-->
        <#--}-->

    <#--</style>-->
    <#--<div class="notice">-->
        <#--<p class="notice-text">几乎所有内容形式都可以用大图的形式表现，唯一需要注意的是，图片宽度不要小于640像素，否则影响显示的效果。 </p></div>-->
    <#--<div class="ad_ctrl_main">-->
        <#--<div class="ctrl_area"> <!-- ngRepeat: item in templates.data.content track by $index &ndash;&gt;-->
            <#--<div class="item_box ng-scope item_box_upImg"-->
                 <#--ng-class="{'item_box_upImg':item.image &amp;&amp; item.image!='','err_box':templates._flag &amp;&amp; !item.image}"-->
                 <#--ng-repeat="item in templates.data.content track by $index">-->
                <#--<!-- ngIf: templates.data.content.length> 1 &ndash;&gt;-->
                <#--<div class="upload_plus ng-isolate-scope" imgfile="item.image">-->
                    <#--<form action="/wkshop/imgUpload" method="post"-->
                          <#--enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">-->
                        <#--<input type="file" name="imgFile" class="input-file" accept="image/*" required="">-->

                        <#--<div class="drc_updateImg"><i class="drc_ican_updateImg">更换图片</i></div>-->
                    <#--</form>-->
                    <#--<div class="upload_ctrl" ng-transclude=""> <!-- ngIf: item.image && item.image!='' &ndash;&gt;-->
                        <#--<div ng-if="item.image &amp;&amp; item.image!=''" class="ng-scope">-->
                            <#--<img-->
                                <#--class="img ng-isolate-scope" dcr-img-reload=""-->
                                <#--width="100%"-->
                                <#--src="http://wd.geilicdn.com/vshop771308584-1466812861162-810358.png?w=320&amp;amp;h=60">-->
                        <#--</div>-->
                        <#--<!-- end ngIf: item.image && item.image!='' &ndash;&gt; <!-- ngIf: !item.image || item.image=='' &ndash;&gt;-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="btn-group" dropdown="">-->
                    <#--<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="line-height:18px;width: 290px;;" aria-expanded="true"  id="btnSelectLink3"  >请选择连接 <span class="caret"></span></button>-->
                    <#--<ul class="dropdown-menu" role="menu">-->
                        <#--<li><a href="javascript:void(0)" onclick="popupLink(3)">商品</a></li>-->
                        <#--<li><a href="javascript:void(0)" onclick="popupLink(3)">商品分类</a></li>-->
                        <#--<li><a href="javascript:void(0)" onclick="popupLink(3)">其他连接</a></li>-->
                        <#--<li><a href="javascript:void(0)" onclick="popupLink(3)">自定义页面</a></li>-->
                    <#--</ul>-->
                <#--</div>-->
            <#--</div>-->

        <#--&lt;#&ndash;</div>&ndash;&gt;-->
        <#--<div class="btn_area"><a for-gaq="编辑自定义页面-编辑模块-确认保存" href="javascript:void(0)" class="btn" onclick="confirm(this)">确定</a>-->
            <#--<a href="javascript:void(0)" class="btn white" onclick="cancel()">取消</a>-->

            <#--<div class="clr">&nbsp;</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->
    <div ui-view="" class="ng-scope">
        <div class="dcr-main-guide">
            创建好一个自定义的页面后，你就可以通过添加文字、图片模块生而成一篇带图的文章（纯文也可以)，然后将链接转发到朋友圈等各个平台上，你也可以把在其中插入商品，为你的商品带来流量。每一个自定义页面都可以作为链接，放到店铺首页中，作为跳转页面。
        </div>
    </div>