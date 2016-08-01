<div ui-view="" class="ng-scope">
    <div class="testModule ng-scope">
        <style type="text/css">
            .item_box {
                width: 340px;
            }

            .upload_plus .default {
                height: 150px;
            }

            .notice {
                border-bottom: 1px solid #dcdcdc;
                padding: 40px 40px 39px 40px;
            }

            .notice .notice-text {
                font-size: 16px;
                line-height: 24px;
                color: #999;
            }

            .btn_add_ad {
                width: 118px;
                height: 36px;
                background: #fff;
                border: 1px solid #dcdcdc;
                border-radius: 2px;
                color: #666666;
                font-size: 14px;
                line-height: 36px;
                margin-top: 25px;
            }

            .upload_plus {
                height: 150px;
            }

        </style>
        <div class="notice"><p class="notice-text">几乎所有内容形式都可以用大图的形式表现，唯一需要注意的是，图片宽度不要小于640像素，否则影响显示的效果。 </p></div>
        <div class="ad_ctrl_main">
            <div class="ctrl_area"> <!-- ngRepeat: item in templates.data.content track by $index -->
                <div class="item_box ng-scope">
                    <div class="upload_plus ng-isolate-scope" imgfile="item.image">
                        <form action="/wkshop/imgUpload" method="post"
                              enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">
                            <input
                                type="hidden" name="callback" class="form_callback">
                            <input type="hidden" name="param" class="form_param">
                            <input type="hidden" name="fr" class="fr">
                            <input type="hidden" name="redirect_url" class="redirect_url">
                            <input type="file" name="imgFile" id="img0" class="input-file" accept="image/*" required="">

                            <div class="drc_updateImg"><i class="drc_ican_updateImg">更换图片</i></div>
                        </form>
                        <div class="upload_ctrl" >
                            <div  class="default ng-scope"><span>建议尺寸640 x 高度不限</span></div>
                        </div>
                    </div>
                    <div class="btn-group" dropdown="">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="line-height:18px;width: 290px;;" aria-expanded="true"  id="btnSelectLink3"  >请选择连接 <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">商品</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">商品分类</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">其他连接</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">自定义页面</a></li>
                        </ul>
                    </div>
                </div>
                <!-- end ngRepeat: item in templates.data.content track by $index -->
                <!-- ngIf: templates.data.content.length<5 -->
                <#--<a href="javascript:void(0)" onclick="addItem()" class="btn_add_ad ng-scope">新增广告图片</a><!-- end ngIf: templates.data.content.length<5 &ndash;&gt;-->
                <#--<div class="muli_check_group" ischeck="templates.data.show_gap">-->
                    <#--<a href="javascript:void(0)" class="icon_checkbox checkbox_off">-->
                    <#--&nbsp;</a>-->
                    <#--<span ng-transclude="">-->
                    <#--<span class="ng-scope"> 显示图片间的间隙 </span></span>-->
                <#--</div>-->
            </div>
            <div class="btn_area">
                <a for-gaq="新建自定义页面-编辑模块-确认保存" href="javascript:void(0)" class="btn" onclick="confirm(this)">确定</a>
                <a href="javascript:void(0)" class="btn white" onclick="cancel()">取消</a>
                <input type="hidden" id="currUploadImgId">
                <div class="clr">&nbsp;</div>
            </div>
        </div>
    </div>
</div>
<script>


    $('input[type="file"]').change(function(){
        console.log("上传文件改变");
        $("#currUploadImgId").val($(this).attr('id'));
        console.log('当前上传图片id：',$(this).attr('id'));
        $(this).parent().submit();
        $(this).val('');
    });


</script>