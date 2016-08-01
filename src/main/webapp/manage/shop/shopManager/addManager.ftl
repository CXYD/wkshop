<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商城管理员">-->

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
                <form name="manage_form" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-2 control-label"><font size="3" color="red">*</font>管理员账号</label>
                        <div class="col-md-8">
                            <input id="g1" name="username" value="" type="text" class="form-control"  placeholder="请输入管理员手机号码" maxlength="11">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">管理员姓名</label>
                        <div class="col-sm-8">
                            <input id="g4" name="nickname" type="text" class="form-control" >
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label"><font size="3" color="red">*</font>设置密码</label>
                        <div class="col-sm-8">
                            <input id="g2" name="password" type="text" class="form-control" placeholder="英文+数字,至少6位,不区分大小写" minlength="6" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label"><font size="3" color="red">*</font>确认密码</label>
                        <div class="col-sm-8">
                            <input id="g3" name="password2" type="text" class="form-control">
                        </div>
                    </div>

                    <div class="col-sm-offset-3 col-sm-3">
                        <button id="add_manage" type="button" class="btn btn-block btn--md btn-success">添加</button>
                    </div>
                </form>
            </div> <!-- panel-body -->
        </div> <!-- panel -->
    </div> <!-- col -->
</div> <!-- End row -->
<script>
    $("#back_bt").click(function(){
        $('.wraper').load("${basepath}/manage/shopManage/listShopManager");
    });
    var check_name=true;
    var check_pass=true;
    $("#g1").focusout(function () {
        checkName();
    });
    $("#g2").focusout(function () {
        checkPass();
    });
    $("#add_manage").click(function(){
        $("font[name='font_name']").empty();
        $("font[name='font_pass']").empty();
        $("font[name='font']").empty();
        check_name=true;
        check_pass=true;
        checkName();
        checkPass();
        if(!check_name){
            $("#g1").focus();
        }else if(!check_pass){
            $("#g2").focus();
        }else if($.trim($("#g3").val())==""){
            $("#g3").after('<font name="font" size="2" color="red">请再次输入密码</font>');
        }else if($.trim($("#g3").val())!=$.trim($("#g2").val())){
            $("#g3").after('<font name="font" size="2" color="red">两次输入的密码不同</font>');
        }else {
            $.ajax({
                url: "${basepath}/manage/shopManage/addManage",
                data: $("form[name='manage_form']").serialize(),
                async: false,
                success: function (msg) {
                    if(msg=="成功"){
                        swal("", "添加成功", "success");
                        $("button[class='confirm']").click(function () {
                            $('.wraper').load("${basepath}/manage/shopManage/listShopManager");
                        });
                    }
                },
                error: function () {
                    swal("", "添加失败", "error");
                }
            });
        }
    });
    function checkName() {
        var username=$("#g1").val();
        $("font[name='font_name']").empty();
        if($.trim($("#g1").val())==""){
            $("#g1").after('<font name="font_name" size="2" color="red">管理员账号不能为空</font>');
            check_name=false;
        }else if(!(/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(username))){
            $("#g1").after('<font name="font_name" size="2" color="red">请输入正确的手机号码</font>');
            check_name=false;
        }else {
            $.ajax({
                url: "${basepath}/manage/shopManage/getManage",
                data: {"username":username},
                async: false,
                success: function (msg) {
                    check_name=false;
                    if(msg=="账号可以使用"){
                        check_name=true;
                    }
                    $("#g1").after('<font name="font_name" size="2" color="red">'+msg+'</font>');
                },
                error: function () {
                    swal("", "账户添加失败", "error");
                    check_name=false;
                }
            });
        }
    }
    function checkPass() {
        var pass=$("#g2").val();
        $("font[name='font_pass']").empty();
        if($.trim($("#g2").val())==""){
            $("#g2").after('<font name="font_pass" size="2" color="red">密码不能为空</font>');
            check_pass=false;
        }else if(!(/^[a-zA-Z0-9]{5,40}$/.test(pass))){
            $("#g2").after('<font name="font_pass" size="2" color="red">英文+数字,至少6位,不区分大小写</font>');
            check_pass=false;
        }
    }
</script>



<#--</@page.pageBase>-->
