<div ui-view="" class="ng-scope">
    <div class="testModule ng-scope">
        <style type="text/css">
            .item_row {
                position: relative;
                border: 1px solid #ededed;
                display: inline-block;
                margin: auto;
                width: 508px;
                margin-bottom: 20px;
                margin-left: 75px;
            }

            .item_box {
                margin-top: 30px;
                width: 238px;
                display: inline-block;
                border: 0;
                padding: 0px;
                margin-left: 10px;
            }

            .upload_plus .default {
                height: 120px;
            }

            .upload_plus .img {
                height: 120px;
            }

            .notice {
                border-bottom: 1px solid #e7e7eb;
                padding: 40px;
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
                height: 120px;
            }
        </style>
        <div class="notice"><p class="notice-text">双列图片限定尺寸为4:3，可尝试展示店铺的商品分类，支持多组排列。</p></div>
        <div class="ad_ctrl_main">
            <div class="ctrl_area">
                <div class="item_row ng-scope" >
                    <div class="item_box ng-scope" >
                        <div class="upload_plus ng-isolate-scope" imgfile="item.image">
                            <form action="/wkshop/imgUpload" method="post"
                                  enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">
                                <input type="file" name="imgFile" id="img0" class="input-file" accept="image/*" required="">

                                <div class="drc_updateImg"><i class="drc_ican_updateImg">更换图片</i></div>
                            </form>
                            <div class="upload_ctrl" ng-transclude="">
                                <div class="default ng-scope" >
                                    <span>建议图片尺寸4:3</span></div>
                                </div>
                        </div>
                        <div class="btn-group" dropdown="">
                            <button type="button" class="btn btn-default dropdown-toggle" style="line-height:18px;width: 240px;;" data-toggle="dropdown" way-data="data.content[0].link.name" id="btnSelectLink0" aria-expanded="true">请选择连接 <span class="caret"></span></button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="javascript:void(0)" onclick="popupLink(0)">商品</a></li>
                                <li><a href="javascript:void(0)" onclick="popupLink(0)">商品分类</a></li>
                                <li><a href="javascript:void(0)" onclick="popupLink(0)">其他连接</a></li>
                                <li><a href="javascript:void(0)" onclick="popupLink(0)">自定义页面</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- end ngRepeat: item in template.row track by $index -->
                    <div class="item_box ng-scope" >
                        <div class="upload_plus ng-isolate-scope" imgfile="item.image">
                            <form action="/wkshop/imgUpload" method="post"
                                  enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid"><input
                                    type="hidden" name="callback" class="form_callback">
                                <input type="file" name="imgFile" id="img1" class="input-file" accept="image/*" required="">

                                <div class="drc_updateImg"><i class="drc_ican_updateImg">更换图片</i></div>
                            </form>
                            <div class="upload_ctrl" ng-transclude="">

                                <div class="default ng-scope" >
                                    <span>建议图片尺寸4:3</span></div>
                               </div>
                        </div>
                        <div class="btn-group" dropdown="">
                            <button type="button" class="btn btn-default dropdown-toggle" style="line-height:18px;width: 240px;;" data-toggle="dropdown" way-data="data.content[1].link.name" id="btnSelectLink1" aria-expanded="true">请选择连接 <span class="caret"></span></button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="javascript:void(0)" onclick="popupLink(1)">商品</a></li>
                                <li><a href="javascript:void(0)" onclick="popupLink(1)">商品分类</a></li>
                                <li><a href="javascript:void(0)" onclick="popupLink(1)">其他连接</a></li>
                                <li><a href="javascript:void(0)" onclick="popupLink(1)">自定义页面</a></li>
                            </ul>
                        </div>
                    </div>

            </div>
            <div class="btn_area">
                <a href="javascript:void(0)" class="btn" onclick="confirm(this)">确定</a>
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