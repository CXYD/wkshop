<!DOCTYPE html>
<html>
<head>
    <#include "/front/header.ftl">
    <title>修改性别</title>
</head>

<body>

<div class="whole">

    <div class="Mall-Specifications">
        <div class="user-body">
            <div class="element-body">
                <div class="row">
                    <div class="col-xs-3 ia">男</div>
                    <div class="col-xs-9 ia">
                        <div class="col-xs-11 padding-Ten text-right"><input type="radio" name="gender" <#if ('${member.gender}'=='男')> checked </#if> value="男" ></div>
                    </div>
                </div>
            </div>
            <div class="element-body">
                <div class="row">
                    <div class="col-xs-3 ia">女</div>
                    <div class="col-xs-9 ia">
                        <div class="col-xs-11 padding-Ten text-right"><input type="radio" name="gender" <#if ('${member.gender}'=='女')> checked </#if> value="女"></div>
                    </div>
                </div>
            </div>
            <div class="element-end">
                <div class="row">
                    <div class="col-xs-3 ia">保密</div>
                    <div class="col-xs-9 ia">
                        <div class="col-xs-11 padding-Ten text-right">
                            <input type="radio" name="gender" <#if ('${member.gender}'=='保密')> checked </#if> value="保密"></div>
                    </div>
                </div>
            </div>

            <div class="element-end">
                <div class="row">
                    <div class="col-xs-12">
                        <p class="register-content"><button id="bt_upgender" type="button" class="btn btn-block  syiaq size-13">确定</button></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>

</body>
<#include "/front/footer.ftl">
</html>

<script type="text/javascript">
    $("#bt_upgender").click(function () {
        var username='${member.username}';
        var gender=$("input:checked").val();
        $.ajax({
            url: "${basepath}/member/upgender",
            data: {"username":username,"gender":gender},
            async: false,
            success: function (msg) {
                weui.Loading.info(msg);
                if(msg=="性别修改成功！"){
                    window.location.href="${basepath}/front/mine/accountManage?name="+username;
                }
            },
            error: function () {
                weui.Loading.error("修改失败！");
            }
        });
    });
</script>