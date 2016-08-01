<div ui-view="" class="ng-scope">
    <div class="testModule ng-scope">
        <style type="text/css">
            .ad_ctrl_main {
                text-align: center;
            }

            .item_box {
                width: 240px;
                display: inline-block;
                padding: 15px;
                margin: 10px;
            }

            .upload_plus .default {
                height: 120px;
            }

            .upload_plus .img {
                height: 120px;
            }

            .enter_text input {
                display: block;
                border: 1px solid #ededed;
                margin-top: 10px;
                padding: 0 4%;
                font-size: 12px;
                line-height: 30px;
                text-align: left;
                color: #111;
                width: 92%;
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

            input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
                color: #999;
                　　
            }

            　　input:-moz-placeholder, textarea:-moz-placeholder {
                color: #999;
                　　
            }

            　　input::-moz-placeholder, textarea::-moz-placeholder {
                color: #999;
                　　
            }

            　　input:-ms-input-placeholder, textarea:-ms-input-placeholder {
                color: #999;
                　　
            }

            .upload_plus {
                height: 120px;
            }
        </style>
        <div class="notice"><p class="notice-text">必须上传4张图片，比例为1：1，一般用来展示商品分类或者内容分类。</p></div>
        <div class="ad_ctrl_main" >
            <div class="ctrl_area"> <!-- ngRepeat: item in templates.data.content track by $index -->
                <div class="item_box ng-scope" >
                    <div class="upload_plus ng-isolate-scope" >
                        <form action="/wkshop/imgUpload" method="post" enctype="multipart/form-data"  target="imgIframe" class="ng-pristine ng-valid">
                            <input type="file"  id="img0" class="input-file"name="imgFile" accept="image/*" required="" >
                            <div class="drc_updateImg"><i class="drc_ican_updateImg" style="display: none">更换图片</i></div>
                        </form>
                        <div class="upload_ctrl">
                            <div  class="default ng-scope"><span>建议尺寸280 x 280</span></div>
                        </div>
                    </div>
                    <div class="enter_text">
                        <input type="text" placeholder="请填写导航名称（5个字以内）" way-data="data.content[0].title" maxlength="5" class="ng-pristine ng-valid"></div>
                    <div class="btn-group" dropdown="">
                        <button type="button" class="btn btn-default dropdown-toggle" style="line-height:18px;width: 192px;;" data-toggle="dropdown" way-data="data.content[0].link.name" id="btnSelectLink0" aria-expanded="true">请选择连接 <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">商品</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">商品分类</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">其他连接</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(0)">自定义页面</a></li>
                        </ul>
                    </div>
                </div>
                <!-- end ngRepeat: item in templates.data.content track by $index -->
                <div class="item_box ng-scope">
                    <div class="upload_plus ng-isolate-scope" >
                        <form action="/wkshop/imgUpload" method="post"
                              enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">
                            <input type="file" id="img1" name="imgFile" class="input-file" accept="image/*" required="">

                            <div class="drc_updateImg"><i class="drc_ican_updateImg" style="display: none">更换图片</i></div>
                        </form>
                        <div class="upload_ctrl">
                            <div  class="default ng-scope"><span>建议尺寸280 x 280</span></div>
                          </div>
                    </div>
                    <div class="enter_text">
                        <input type="text" placeholder="请填写导航名称（5个字以内）" maxlength="5" way-data="data.content[1].title"   class="ng-pristine ng-valid"></div>
                    <div class="btn-group" dropdown="">
                        <button type="button" class="btn btn-default dropdown-toggle" style="line-height:18px;width: 192px;;" data-toggle="dropdown" id="btnSelectLink1"   way-data="data.content[1].link.name" aria-expanded="true">请选择连接 <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:void(0)" onclick="popupLink(1)">商品</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(1)">商品分类</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(1)">其他连接</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(1)">自定义页面</a></li>
                        </ul>
                    </div>
                </div>
                <!-- end ngRepeat: item in templates.data.content track by $index -->
                <div class="item_box ng-scope" >
                    <div class="upload_plus ng-isolate-scope" >
                        <form action="/wkshop/imgUpload" method="post"
                              enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">
                            <input type="file"  id="img2" name="imgFile" class="input-file" accept="image/*" required="" >

                            <div class="drc_updateImg"><i class="drc_ican_updateImg" style="display: none">更换图片</i></div>
                        </form>
                        <div class="upload_ctrl" >
                            <div  class="default ng-scope"><span>建议尺寸280 x 280</span></div>
                       </div>
                    </div>
                    <div class="enter_text">
                        <input type="text" placeholder="请填写导航名称（5个字以内）" maxlength="5"  way-data="data.content[2].title"  class="ng-pristine ng-valid"></div>
                    <div class="btn-group" dropdown="">
                        <button type="button" class="btn btn-default dropdown-toggle" style="line-height:18px;width: 192px;" data-toggle="dropdown" aria-expanded="true"  id="btnSelectLink2"  >请选择连接 <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:void(0)" onclick="popupLink(2)">商品</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(2)">商品分类</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(2)">其他连接</a></li>
                            <li><a href="javascript:void(0)" onclick="popupLink(2)">自定义页面</a></li>
                        </ul>
                    </div>
                </div>
                <!-- end ngRepeat: item in templates.data.content track by $index -->
                <div class="item_box ng-scope">
                    <div class="upload_plus ng-isolate-scope" >
                        <form action="/wkshop/imgUpload" method="post"
                              enctype="multipart/form-data" target="imgIframe" class="ng-pristine ng-valid">
                            <input type="file"  id="img3" name="imgFile" class="input-file" accept="image/*" required="">

                            <div class="drc_updateImg"><i class="drc_ican_updateImg" style="display: none">更换图片</i></div>
                        </form>
                        <div class="upload_ctrl" >
                            <div  class="default ng-scope" ><span>建议尺寸280 x 280</span></div>
                        </div>
                    </div>
                    <div class="enter_text">
                        <input type="text" placeholder="请填写导航名称（5个字以内）" maxlength="5" way-data="data.content[3].title" class="ng-pristine ng-valid">
                    </div>
                    <div class="btn-group" dropdown="">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="line-height:18px;width: 192px;;" aria-expanded="true"  id="btnSelectLink3"  >请选择连接 <span class="caret"></span></button>
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