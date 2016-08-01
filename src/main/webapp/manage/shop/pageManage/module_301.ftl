<div ui-view="" class="ng-scope">
    <div class="testModule ng-scope">
        <style type="text/css">
    .ad_ctrl_main {
        text-align: center;
    }
    .item_box {
        width: 400px;
        display: inline-block;
        padding:25px 15px 15px;
        margin: 10px;
        text-align: left;
    }
    .enter_text input {
        display: block;
        border: 1px solid #ededed;
        margin-top: 10px;
        padding: 0 4%;
        font-size: 13px;
        line-height: 34px;
        text-align: left;
        color: #111;
        width: 100%;
    }
    .notice {
        border-bottom: 1px solid #e7e7eb;
        padding: 35px 0 34px;
    }
    .notice .notice-text {
        font-size: 14px;
        line-height: 24px;
        width: 600px;
        margin: auto;
    }

    .dcr-error-msg {
        color: red;
        font-size: 13px;
        height: 24px;
        line-height: 32px;
    }
    input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
        color: #999;
        　　}
    　　input:-moz-placeholder, textarea:-moz-placeholder {
        color:#999;
        　　}
    　　input::-moz-placeholder, textarea::-moz-placeholder {
        color:#666;
        　　}
    　　input:-ms-input-placeholder, textarea:-ms-input-placeholder {
        color:#666;
        　　}
</style>
    <div class="notice">
        <p class="notice-text">文本导航可以作为目录使用，比如文章合集，分类目录 等，便于买家找到其他的店铺内容。</p>
    </div>
    <div class="ad_ctrl_main">
        <div class="ctrl_area">
            <div class="item_box ng-scope">
                <div class="enter_text">
                    <input type="text" maxlength="20" placeholder="请填写导航名称（20个字以内）" name="navName" class="ng-pristine ng-valid" way-data="data.content[0].name">
                    <p class="dcr-error-msg ng-hide" >导航名称不能为空</p>
                </div>
                <div class="btn-group" dropdown="">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="line-height:18px;    width: 100%" aria-expanded="true"  id="btnSelectLink0"  >请选择连接 <span class="caret"></span></button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:void(0)" onclick="popupLink(0)">商品</a></li>
                        <li><a href="javascript:void(0)" onclick="popupLink(0)">商品分类</a></li>
                        <li><a href="javascript:void(0)" onclick="popupLink(0)">其他连接</a></li>
                        <li><a href="javascript:void(0)" onclick="popupLink(0)">自定义页面</a></li>
                    </ul>
                </div>
                <div class="btn-group" dropdown="">


            </div> </div>
            <#--<a href="javascript:void(0)" onclick="addItem()" class="btn_add_ad ng-scope">新增广告</a>-->
            </div>
        <div class="btn_area">
            <a data-for-gaq="新建自定义页面-编辑模块-确认保存" href="javascript:void(0)" class="btn" onclick="confirm(this)">确定</a>
            <a href="javascript:void(0)" class="btn white" onclick="cancel()">取消</a>

            <div class="clr">&nbsp;</div>
        </div>
    </div>
</div>
</div>

<script>


</script>