<style>
    .dcr-error-msg {
        color: red;
        font-size: 13px;
        margin-left: 70px;
        height: 32px;
        line-height: 32px;
        margin-top: -10px;
    }

</style>
<div ui-view="" class="ng-scope">
    <div class="e_1 ng-scope">
        <div class="e_1-module-describe">通过对标题、摘要进行设置，你可以控制页面在朋友圈分享时的效果。另外请合理使用页面底色，好的颜色搭配可以使你的流量翻倍。</div>
        <div class="e_1-edit">
            <div class="e_1-title">
                <div class="e_1-edit-box"><span class="e_1-name">页面标题</span>
                    <input
                        class="e_1-value ng-pristine ng-valid" type="text" value="" maxlength="20">
                </div>
                <p class="dcr-error-msg ng-hide" >title不能为空</p>
                <p class="err-msg ng-hide" >title长度在20个字以内</p></div>
            <div class="e_1-describe">
                <div class="e_1-edit-box">
                    <span class="e_1-name">页面描述</span>
                    <input class="e_1-value ng-pristine ng-valid" type="text" value="" maxlength="40">
                </div>
                <p class="dcr-error-msg ng-hide" >页面描述不能为空</p>
                <p class="err-msg ng-hide" >页面描述长度在40个字以内</p>

                <#--<div class="e_1_imgUpload e_1_showUploadBtn">-->
                    <#--<div class="upload_plus ng-isolate-scope" style="height: 100%;width: 60px;float:left;" imgfile="templates.image">-->
                        <#--<form action="#" method="post" enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">-->
                            <#--<input type="hidden" name="callback" class="form_callback">-->
                            <#--<input type="hidden" name="param" class="form_param">-->
                            <#--<input type="hidden" name="fr" class="fr">-->
                            <#--<input type="hidden" name="redirect_url" class="redirect_url">-->
                            <#--<input type="file" name="imgs[]" class="input-file" accept="image/*" required="">-->

                            <#--<div class="drc_updateImg"><i class="drc_ican_updateImg">更换图片</i></div>-->
                        <#--</form>-->
                        <#--<div class="upload_ctrl" ng-transclude="">-->
                            <#--<a class="e_1_imgUpload_btn ng-scope">-->
                                <#--<img dcr-img-reload="" id="e_1_img" re-paint="paintModule" class="ng-isolate-scope"-->
                                     <#--src="http://wd.geilicdn.com/vshop-shop-logo-default.jpg?w=250&amp;h=250&amp;cp=1">-->
                            <#--</a>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<span class="e_1_imgUpload_desc">分享图片</span></div>-->
                <#--<p class="err-msg ng-hide" >分享图片不能为空</p>-->
                <#--<p class="e_1-share">-->
                    <#--<em>用户通过微信分享页面给好友时，会自动显示页面分享描述</em>-->
                <#--<a class="e_1-share-btn" onclick="share()">分享示例</a></p>-->
            </div>
            <div id="e_1-share_bg"></div>
            <div id="e_1-share_show">
                <div class="e_1-share-header">
                    <p class="e_1-share-header-name">分享示例：</p>
                    <a id="e_1-share-close" type="button" value="Close" onclick="close()"></a>
                </div>
                <div class="e_1-share-box">
                    <p class="e_1-share-title">自定义页面标题</p>
                    <div class="e_1-share-details">
                        <img class="e_1-share-image" src="">
                        <p class="e_1-share-describe">页面描述页面描述页面描述页面描述页面描述页面描述页面描述页面描述页面描述</p>
                    </div>
                </div>
            </div>
            <div class="e_1-color">
                <div class="e_1-edit-box e_1-bgcolor">
                    <span class="e_1-name">背景颜色</span>
                    <div class="e_1-color-value">
                        <input type="hidden"  class="form-control input-sm" id="pickcolor"  value="#eeeeee">
                    </div>
                    <a class="e_1-color-reset" onclick="reset()">重置</a>

                    <div style="clear: both;"></div>

                </div>
                <p class="err-msg ng-hide" n>背景颜色色值错误</p>
            </div>
        </div>
        <div class="btn_area">
            <a href="javascript:void(0)" class="btn" onclick="confirm(this)">确定</a>
            <a href="javascript:void(0)" class="btn white" onclick="cancel()">取消</a>

            <div class="clr">&nbsp;</div>
        </div>
    </div>
</div>
<script>
    $(document).ready( function() {
        $('#pickcolor').minicolors({
            control: $(this).attr('data-control') || 'hue',
            defaultValue: $(this).attr('data-defaultValue') || '',
            inline: $(this).attr('data-inline') === 'true',
            letterCase: $(this).attr('data-letterCase') || 'lowercase',
            opacity: $(this).attr('data-opacity'),
            position: $(this).attr('data-position') || 'bottom left',
            change: function (hex, opacity) {
                if (!hex) return;
                if (opacity) hex += ', ' + opacity;
                try {
//                    console.log(hex);
                    $('#dcr-phone').css('background', hex)
                } catch (e) {
                }
            },
            theme: 'bootstrap'
        });

    })
</script>