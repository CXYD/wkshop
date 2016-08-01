<!DOCTYPE html>
<html>
<head>
    <#include "/front/header.ftl">
    <title>修改昵称</title>
</head>

<body>

<div class="whole">

    <div class="Mall-Specifications account">
        <div class="element-end">
            <div class="element-body">
                <div class="row">
                    <div class="col-xs-12 ia">
                        <input name="nickName" id="nickName" class="delete-btn dxld-log" required  placeholder="请输入昵称" minlength="4" maxlength="20" style="float:left; border:0;"><a class="clear"></a>
                        <input type="hidden" id="username" name="username" value="${member.username}">
                    </div>
                </div>
            </div>
            <div class="element-end">
                <div class="row">
                    <div class="reminder text-left">4-20个字符，可由中英文、数字、"_"、"-"组成</div>
                </div>
            </div>
        </div>
        <div class="element-end">
            <div class="row">
                <div class="col-xs-12">
                    <p class="register-content "><button id="bt_upnick" type="button" class="btn btn-block syiaq size-13">确定</button></p>
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
    $("#bt_upnick").click(function () {
        var username=$("#username").val();
        var nickName= $.trim($("#nickName").val());
        if(nickName=="" || nickName==null){
            weui.Loading.error("昵称不能为空！");
            return false;
        }
        $.ajax({
            url: "${basepath}/member/upnickname",
            data: {"username":username,"nickName":nickName},
            async: false,
            success: function (msg) {
                if(msg=="昵称修改成功！"){
                    window.location.href="${basepath}/front/mine/accountManage?name="+username;
                }
                weui.Loading.info(msg);
            },
            error: function () {
                weui.Loading.error("修改失败！");
            }
        });
    });
</script>