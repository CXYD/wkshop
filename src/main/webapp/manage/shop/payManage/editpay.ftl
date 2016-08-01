<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="支付管理">-->

<div class="row">


    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-body">

                <button type="button" class="btn btn-warning w-lg m-b-5">设置银行账户</button>
                <br><br>
                <div class="panel" style="background: #f5f5f5">
                    <div class="form-group" >
                        <label class="col-sm-2 control-label"> 连连支付</label>
                        <div class="col-sm-2 control-label pull-right">
                            <div class="toggle toggle-success" id="pay1"  style="height: 25px; width: 60px;">
                                <div class="toggle-slide active">
                                    <div class="toggle-inner" style="width: 80px; margin-left: 0px;">
                                    <div class="toggle-on active" style="height: 20px; width: 40px; text-align: center; text-indent: -10px; line-height: 20px;">OFF</div>
                                    <div class="toggle-blob" style="height: 20px; width: 20px; margin-left: -10px;"></div>
                                        <div class="toggle-off" style="height: 20px; width: 40px; margin-left: -10px; text-align: center; text-indent: 10px; line-height: 20px;">ON</div>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <label class="col-md-12 control-label">启用连连支付（代收），买家就可使用银行卡付款购买您的商品。货款将先进入宽带微商城账号，订单完成后即时转账到您的银行账户。</label>
                <br><br>
                <label class="col-md-12 control-label">支持银行：</label>
                <img alt="" src="${basepath}/static/img/pay/banks.png" >
                <br><br><br>
                <div class="panel" style="background: #f5f5f5">
                    <div class="form-group" >
                        <label class="col-sm-2 control-label"> 支付宝支付</label>
                        <div class="col-sm-2 control-label pull-right">
                            <div class="toggle toggle-success" id="pay2"  style="height: 25px; width: 60px;">
                                <div class="toggle-slide active">
                                    <div class="toggle-inner" style="width: 80px; margin-left: 0px;">
                                        <div class="toggle-off " style="height: 20px; width: 40px; text-align: center; text-indent: -10px; line-height: 20px;">关</div>
                                        <div class="toggle-blob" style="height: 20px; width: 20px; margin-left: -10px;"></div>
                                        <div class="toggle-on active" style="height: 20px; width: 40px; margin-left: -10px; text-align: center; text-indent: 10px; line-height: 20px;">On</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <label class="col-md-12 control-label">启用支付宝（代收）后，买家可使用支付宝进行付款。货款将先进入宽带微商城账号，订单完成后即时转账到您的银行账户。</label>
                <br><br>
                <ul> 由于支付宝无法在微信、QQ的购物环境中使用，目前启用支付宝将不在前述的两个APP内提供支付入口。买家可在支付宝服务窗、微博、Safari、Chrome等其他APP支付时，使用支付宝付款。</ul>
                <br><br>
                <div class="panel" style="background: #f5f5f5">
                    <div class="form-group" >
                        <label class="col-sm-2 control-label"> 货到付款</label>
                        <div class="col-sm-2 control-label pull-right">
                            <div class="toggle toggle-success" id="pay3" style="height: 25px; width: 60px;">
                                <div class="toggle-slide active">
                                    <div class="toggle-inner" style="width: 80px; margin-left: 0px;">
                                        <div class="toggle-on" style="height: 20px; width: 40px; text-align: center; text-indent: -10px; line-height: 20px;">ON</div>
                                        <div class="toggle-blob" style="height: 20px; width: 20px; margin-left: -10px;"></div>
                                        <div class="toggle-off active" style="height: 20px; width: 40px; margin-left: -10px; text-align: center; text-indent: 10px; line-height: 20px;">OFF</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <label class="col-md-12 control-label">启用后买家可选择货到付款下单，您需自行通过合作快递安排配送。买家开箱验货无误后，快递公司向买家收款并与您结算费用。</label>
                <br><br>
                <div class="col-sm-offset-4 col-sm-3">
                    <button id="bt_set" type="button" class="btn btn-block btn--md btn-success">保存</button>
                </div>
            </div> <!-- panel-body -->
        </div> <!-- panel -->
    </div> <!-- col -->

</div>

<script>

    jQuery(document).ready(function() {


        // Tags Input
        jQuery('#tags').tagsInput({width: 'auto'});

        jQuery('.toggle').toggles({on: false});

        if("${paySet.llpay}"=="1"){
            $("#pay1").toggles({on: true});
        }
        if("${paySet.alipay}"=="1"){
            $("#pay2").toggles({on: true});
        }
        if("${paySet.delaypay}"=="1"){
            $("#pay3").toggles({on: true});
        }
        $("#bt_set").click(function () {
            var p1=$("#pay1").find(".toggle-on").hasClass("active");
            var p2=$("#pay2").find(".toggle-on").hasClass("active");
            var p3=$("#pay3").find(".toggle-on").hasClass("active");
            var llpay=(p1==true?1:0);
            var alipay=(p2==true?1:0);
            var delaypay=(p3==true?1:0);
            if(llpay==0&alipay==0&delaypay==0){
                swal("请选择支付方式");
            }else {
            $.ajax({
                url:"${basepath}/manage/payManage/addSet",
                data:{"llpay":llpay,"alipay":alipay,"delaypay":delaypay},
                async:false,
                success:function () {
                    swal("成功","添加成功","success");
                },
                error:function(){
                    swal("失败","添加失败","error");
                }
            });
            }
        });
    });
    $(".btn.btn-warning.w-lg.m-b-5").click(function(){
        $('.wraper').load("${basepath}/manage/payManage/accountAdd");
    });

</script>
<#--</@page.pageBase>-->
