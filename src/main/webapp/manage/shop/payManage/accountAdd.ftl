<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="支付管理">-->

<div class="row">
    <div class="col-sm-6">
        <div class="m-b-30">
            <button id="back_bt" type="button" class="btn btn-success">返回</button>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <form name="add" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-2 control-label"><font size="3" color="red">*</font>企业开户名称：</label>
                        <div class="col-md-8">
                            <input id="p1" value="${payAccount.payname}" name="payname" type="text" class="form-control"  placeholder="请填写您的开户企业名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label"><font size="3" color="red">*</font>企业开户银行：</label>
                        <div class="col-sm-8">
                            <input id="p2" value="${payAccount.paybank}" name="paybank" type="text" class="form-control" placeholder="请填写您的开户银行名称">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label"><font size="3" color="red">*</font>企业银行账号：</label>
                        <div class="col-sm-8">
                            <input id="p3" value="${payAccount.payaccount}" name="payaccount" type="text" class="form-control" placeholder="请填写您的银行账号">
                        </div>
                    </div>

                    <div class="col-sm-offset-3 col-sm-3">
                        <button id="addAccount" type="button" class="btn btn-block btn--md btn-success">添加</button>
                    </div>
                </form>
            </div> <!-- panel-body -->
            <#--<label class="col-md-12 control-label">注：</label>-->
            <#--<label class="col-md-12 control-label">1.必须是企业对公账户，</label>-->
            <#--<label class="col-md-12 control-label">2.提交账户信息后需人工审核通过才能进行转账。</label>-->

        </div> <!-- panel -->
    </div> <!-- col -->
</div> <!-- End row -->
<script>

    $("#addAccount").click(function(){
        $("font[name='font']").empty();
        var num=$.trim($("#p3").val());
        if($.trim($("#p1").val())==""){
            $("#p1").after('<font name="font" size="2" color="red">企业开户名称不能为空</font>');
        }else if($.trim($("#p2").val())==""){
            $("#p2").after('<font name="font" size="2" color="red">企业开户银行不能为空</font>');
        }else if(num==""){
            $("#p3").after('<font name="font" size="2" color="red">企业银行账号不能为空</font>');
        }else if(!(/^(\d{16}|\d{19})$/.test(num))){
            $("#p3").after('<font name="font" size="2" color="red">请输入正确的银行卡号</font>');
        }else {
        $.ajax({
            url:"${basepath}/manage/payManage/addAccount",
            data:$("form[name='add']").serialize(),
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

    $("#back_bt").click(function(){
        $('.wraper').load("${basepath}//manage/payManage/edit");
    });
</script>



<#--</@page.pageBase>-->
