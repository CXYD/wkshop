<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商城信息">-->
<div class="row">
    <div class="panel panel-default">
        <div class="panel-body">

            <div class="form-horizontal">
                <form name="from_add" class="form-horizontal" role="form">
                <div class="col-sm-10">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">
                            商城名称
                        </label>
                        <div class="col-sm-8">
                            <input id="shopname" name="shopname" type="text" class="form-control" placeholder="请填写商城名称">
                        </div>
                    </div>
                </div>

                <div class="col-sm-10">
                    <div class="form-group">
                        <label class="control-label col-sm-2">所在区域</label>
                        <div class="col-sm-4">
                            <select name="servicesheng" id="input_province" class="form-control">
                                <option value="">== 请选择 ==</option>
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <select name="servicecity" id="input_city" class="form-control">
                                <option value="">== 请选择 ==</option></select>
                        </div>

                    </div>
                </div>
                </form>


                <div class="col-sm-offset-3 col-sm-3">
                    <button id="bt_save" type="button" class="btn btn-block btn--md btn-success">保存</button>
                </div>

            </div> <!--form  -->

        </div> <!--panel body -->

    </div>


</div>

<script src="${ctxStatic}/js/main/city-pdata.js"></script>
<script src="${ctxStatic}/js/main/city-picker.js"></script>
<script>

    $("#bt_save").click(function(){
        $("font[name='font']").empty();
        if($.trim($("#shopname").val())==""){
            $("#shopname").after('<font name="font" size="2" color="red">商城名称不能为空</font>');
        }else {
            $.ajax({
                url:"${basepath}/manage/shopInfo/addShop",
                data:$("form[name='from_add']").serialize(),
                async:false,
                success:function () {
                    swal("成功","保存成功","success");
                },
                error:function(){
                    swal("失败","保存失败","error");
                }
            });
        }

    });
</script>
<#--</@page.pageBase>-->