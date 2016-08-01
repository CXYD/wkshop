
<style>
    .dcr-error-msg {
        color: red;
        font-size: 13px;
        margin-left: 70px;
        height: 32px;
        line-height: 32px;
        margin-top: -10px;
    }

    .e_2-value{
        border: 1px solid #dcdcdc;
        width: 502px;
        float: left;
        font-size: 14px;
        padding: 0 12px;
    }

</style>
<div ui-view="" class="ng-scope">
    <div class="e_1 ng-scope">
        <div class="e_1-module-describe">公告：请填写公告内容，如果过长，将会在手机上滚动显示。</div>
        <div class="e_1-edit">
            <div class="e_1-title">
                <div class="e_1-edit-box"><span class="e_1-name">编辑公告</span>
                    <textarea rows="10" way-data="data.title"
                            class="e_2-value ng-pristine ng-valid" type="text" value="" style="height: 138px" maxlength="300"></textarea>
                </div>
                <p class="dcr-error-msg ng-hide" >title不能为空</p>
                <p class="err-msg ng-hide" >title长度在20个字以内</p></div>
           </div>
        </div>
        <div class="btn_area">
            <a href="javascript:void(0)" class="btn" onclick="confirm(this)">确定</a>
            <a href="javascript:void(0)" class="btn white" onclick="cancel()">取消</a>

            <div class="clr">&nbsp;</div>
        </div>
    </div>
</div>