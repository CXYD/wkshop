<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="管理订单">-->
<style>
    .dh3{
        background:#dff0d8;
    }
</style>
<div class="btn-group">
    <button type="button" class="btn btn-default"><a href="javascript:history.go(-1)">返回</a></button>
</div>
    <div class="panel panel-default m-t-10 ">
        <div class="panel-heading ">
            <h3 class="panel-title">
                订单信息
            </h3>
        </div>
        <div class="panel-body">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-md-2 control-label">订单号的：</label>
                    <label class="control-label">${odBean.orderNum}</label>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">下单时间：</label>
                    <label class="control-label">${odBean.createTime?string("yyyy-MM-dd hh:mm:ss")}</label>
                </div>
            <div class="form-group">
                <label class="col-md-2 control-label">支付方式：</label>
                <#if odBean.paytype??>
                <label class="control-label">${odBean.paytype}</label>
                </#if>
            </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">订单金额：</label>
                    <label class="control-label">${odBean.amount?string('0.00')}</label>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">订单状态：</label>
                    <#if odBean.orderstatus??>
                        <label class="control-label">${odBean.orderstatus}</label>
                    </#if>
                </div>
            </form>
        </div>
</div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    商品信息
                </h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-2 control-label">商品名称：</label>
                        <label class="control-label">${odBean.productName!''}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">规格：</label>
                        <label class="control-label">2000</label>
                    </div>
                    <div class="form-group">
                        <#if odBean.price??>
                        <label class="col-md-2 control-label">单价：</label>
                        <label class="control-label">${odBean.price?string('0.00')}</label>
                        </#if>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">数量：</label>
                        <label class="control-label">${odBean.quantity!''}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">发票信息：</label>
                        <label class="control-label">${odBean.invoice!''}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label" >新号码：</label>
                        <label class="control-label">${odBean.newmob!''}</label>&nbsp;&nbsp;&nbsp;
                        <label class="control-label">号卡金额：</label>
                        <label class="control-label">${odBean.cardprice?string('0.00')}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">老号码：</label>
                        <label class="control-label">${odBean.oldmob!''}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">选择套餐：</label>
                        <label class="control-label">按时打发打发大声道</label>&nbsp;&nbsp;&nbsp;
                        <label class="control-label">首月资费：</label>
                        <label class="control-label">${odBean.firstfee?string('0.00')}</label>
                    </div>
                </form>
            </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        联系人信息
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-md-2 control-label">联系人姓名：</label>
                            <label class="col-md-1 control-label" style="text-align: left">${odBean.linkman!''}</label>
                            <label class="col-md-4 control-label">机主姓名：</label>
                            <label class="control-label" style="text-align: left">${odBean.owner!''}</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">联系人手机号：</label>
                            <label class="col-md-1 control-label">${odBean.contractmobile!''}</label>
                            <label class="col-md-4 control-label">机主电话：</label>
                            <label class="control-label">${odBean.ownermobi!''}</label>
                        </div>
                        <div class="form-group">
                        <label class="col-md-2 control-label">配送地址：</label>
                        <label class="control-label">${odBean.distributddress!''}</label>
                      </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">装机地址：</label>
                            <label class="control-label">${odBean.installaddress!''}</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">身份证姓名：</label>
                            <label class="control-label">${odBean.owner!''}</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">身份证号码：</label>
                            <label class="control-label">${odBean.idcard!''}</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">身份证照片：</label>
                          <#if odBean.netimg1??>
                            <img src="${basepath}/${odBean.netimg1}"
                                 class="img-thumbnail">
                            </#if>
                          <#if odBean.netimg2??>
                            <img src="${basepath}/${odBean.netimg2}"
                                 class="img-thumbnail">
                          </#if>
                          <#if odBean.netimg3??>
                            <img src="${basepath}/${odBean.netimg3}"
                                 class="img-thumbnail">
                          </#if>
                        </div>
                    </form>
                </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            订单处理
                        </h3>
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-md-2 control-label">订单状态：</label>
                                <#if odBean.orderstatus??>
                                    <label class="control-label">${odBean.orderstatus}</label>
                                </#if>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">备注：</label>
                                <textarea class="col-md-3" disabled="disabled"" >${odBean.remark!''}</textarea>
                            </div>

                            <div class="col-md-6 text-danger col-md-offset-1" >注：如是用户在前台自主取消，则备注统一显示“买家取消订单”</div>
                        </form>
                    </div>
                    </div>
<#--</@page.pageBase>-->