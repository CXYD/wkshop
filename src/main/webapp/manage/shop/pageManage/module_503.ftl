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

            .ad_ctrl_main {
                height: 100%;
                margin: 40px auto;
            }

            .upload_plus {
                height: 150px;
            }
        </style>
        <div class="notice"><p class="notice-text">图片最多4个，最少一个。请保证所有图片的尺寸比例相同，否则会影响展示效果。可以用来展示店铺当前的主推内容。</p></div>
        <div class="ad_ctrl_main">
            <div class="ctrl_area">
                <div class="item_box ng-scope">
                    <div class="upload_plus ng-isolate-scope" >
                        <form action="/wkshop/imgUpload" method="post"
                              enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">
                            <input type="file" name="imgFile" id="img0"  class="input-file" accept="image/*" required="">

                            <div class="drc_updateImg"><i class="drc_ican_updateImg">更换图片</i></div>
                        </form>
                        <div class="upload_ctrl" ng-transclude="">
                            <div class="default ng-scope"><span>建议尺寸640 x 高度不限，且多图尺寸需相同</span></div>
                           </div>
                    </div>
                    <div class="btn-group" dropdown="">
                        <button type="button" class="btn btn-default dropdown-toggle" style="line-height:18px;width: 290px;;" data-toggle="dropdown" way-data="data.content[0].link.name" id="btnSelectLink0" aria-expanded="true">请选择连接 <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">商品</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">商品分类</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">其他连接</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">自定义页面</a></li>
                        </ul>
                    </div>
                </div>

                <div class="item_box ng-scope">

                    <div class="upload_plus ng-isolate-scope" >
                        <form action="/wkshop/imgUpload" method="post"
                              enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">
                            <input type="file" name="imgFile" id="img1"  class="input-file" accept="image/*" required="">

                            <div class="drc_updateImg"><i class="drc_ican_updateImg">更换图片</i></div>
                        </form>
                        <div class="upload_ctrl" ng-transclude="">
                            <div class="default ng-scope"><span>建议尺寸640 x 高度不限，且多图尺寸需相同</span></div>
                        </div>
                    </div>
                    <div class="btn-group" dropdown="">
                        <button type="button" class="btn btn-default dropdown-toggle" style="line-height:18px;width: 290px;;" data-toggle="dropdown" way-data="data.content[1].link.name" id="btnSelectLink0" aria-expanded="true">请选择连接 <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:void(0)" onclick="popupLink(1)">商品</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(1)">商品分类</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(1)">其他连接</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(1)">自定义页面</a></li>
                        </ul>
                    </div>
                </div>

                <div class="item_box ng-scope">

                    <div class="upload_plus ng-isolate-scope" >
                        <form action="/wkshop/imgUpload" method="post"
                              enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">
                            <input type="file" name="imgFile" id="img2"  class="input-file" accept="image/*" required="">

                            <div class="drc_updateImg"><i class="drc_ican_updateImg">更换图片</i></div>
                        </form>
                        <div class="upload_ctrl" ng-transclude="">
                            <div class="default ng-scope"><span>建议尺寸640 x 高度不限，且多图尺寸需相同</span></div>
                        </div>
                    </div>
                    <div class="btn-group" dropdown="">
                        <button type="button" class="btn btn-default dropdown-toggle" style="line-height:18px;width: 290px;;" data-toggle="dropdown" way-data="data.content[2].link.name" id="btnSelectLink0" aria-expanded="true">请选择连接 <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:void(0)" onclick="popupLink(2)">商品</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(2)">商品分类</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(2)">其他连接</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(2)">自定义页面</a></li>
                        </ul>
                    </div>
                </div>

                <div class="item_box ng-scope">

                    <div class="upload_plus ng-isolate-scope" >
                        <form action="/wkshop/imgUpload" method="post"
                              enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">
                            <input type="file" name="imgFile" id="img3"  class="input-file" accept="image/*" required="">

                            <div class="drc_updateImg"><i class="drc_ican_updateImg">更换图片</i></div>
                        </form>
                        <div class="upload_ctrl" ng-transclude="">
                            <div class="default ng-scope"><span>建议尺寸640 x 高度不限，且多图尺寸需相同</span></div>
                        </div>
                    </div>
                    <div class="btn-group" dropdown="">
                        <button type="button" class="btn btn-default dropdown-toggle" style="line-height:18px;width: 290px;;" data-toggle="dropdown" way-data="data.content[3].link.name" id="btnSelectLink0" aria-expanded="true">请选择连接 <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:void(0)" onclick="popupLink(3)">商品</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(3)">商品分类</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(3)">其他连接</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(3)">自定义页面</a></li>
                        </ul>
                    </div>
                </div>

            </div>
            <div class="btn_area">
                <a data-for-gaq="新建自定义页面-编辑模块-确认保存" href="javascript:void(0)" class="btn" onclick="confirm(this)">确定</a>
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