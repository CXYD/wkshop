<div ui-view="" class="ng-scope">
    <div class="e701 ng-scope">
        <div class="notice"><p class="notice-text">几乎所有内容形式都可以用大图的形式表现，唯一需要注意的是，图片宽度不要小于640像素，否则影响显示的效果。</p></div>
        <div class="e701-edit-wrap">

            <div class="e701-edit-selection">
                <p class="e701-edit-selection-title">列表名称</p>

                <div class="edit-zone edit-zone-pbFix">
                    <p class="list-name">
                        <input type="text" placeholder="请填写列表名称（可不填写）" id="title" maxlength="8" way-data="data.options.title" class="ng-pristine ng-valid">
                    </p>

                </div>
            </div>
            <div class="e701-edit-selection">
                <p class="e701-edit-selection-title">添加商品</p>
                <div class="edit-zone">
                    <div class="ng-scope"> <!-- ngRepeat: item in templates.data.content -->
                        <div class="show-one-item ng-scope">
                            <img src="https://d.weidian.com/images/decoration/dcr-default-bg5.jpg">
                            <p class="show-one-item-name ng-binding">商品标题</p>
                            <p class="show-one-item-price ng-binding">￥0.00</p>
                            <#--<div class="btns-show-one-item">-->
                                <#--<a href="javascript:void(0)" class="btn-show-one-item-edit" ng-click="editItem(this)">编辑</a>-->
                                <#--<a href="javascript:void(0)" class="btn-show-one-item-delete" ng-click="deleteItem($index)">删除</a>-->
                            <#--</div>-->
                        </div>

                    <div class="add-item-one ng-scope">
                        <a href="javascript:void(0)" class="btn-add-item" onclick="addItem(0)">选择商品</a>
                    </div>

                    </div>
            </div>
        </div>
        <div class="btn_area">
            <a data-for-gaq="新建自定义页面-编辑模块-确认保存" href="javascript:void(0)" class="btn" onclick="confirm(this)">确定</a>
            <a href="javascript:void(0)" class="btn white" onclick="cancel()">取消</a>
            <input type="hidden" id="currAddProductId">
            <div class="clr">&nbsp;</div>
        </div>
     </div>
</div>

   <script>
       function addItem(i){
           $('#currAddProductId').val(i);
           $('#productLink-modal-dialog').modal('toggle');
       }
   </script>