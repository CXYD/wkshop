<!DOCTYPE html>
<html>
<head>
    <#include "/front/header.ftl">
    <title>账户管理</title>
        <link rel="stylesheet" href="${basepath}/static/assets/dateui/pikaday-package.css">
        <link rel="stylesheet" href="${basepath}/static/assets/dateui/demo-style.css">
</head>
<body>

<div class="whole">
    <div class="Mall-Specifications account">
        <div class="user-body">
            <div class="element-body">
                <div  class="row">
                    <div class="col-xs-3 ia">昵称</div>
                    <div id="div_nickname" class="col-xs-9 ia iqaa" >
                        <div class="col-xs-11 padding-Ten text-right" >&nbsp;${(member.nickName)!''}</div>
                    </div>
                </div>
            </div>
            <div class="element-body">
                <div class="row">
                    <div class="col-xs-3 ia">性别</div>
                    <div id="div_gender" class="col-xs-9 ia iqaa">
                        <div class="col-xs-11 padding-Ten text-right">&nbsp;${(member.gender)!''}</div>
                    </div>
                </div>
            </div>
            <div class="element-body">
                <div class="row">
                    <div class="col-xs-3 ia">出生日期</div>
                    <div class="col-xs-9 ia iqaa">
                          <#if (member.birthDate)??>
                        <input type="date"  name="user_age" id="user_age"  class="col-xs-11 padding-Ten text-right"  value="${member.birthDate?string("yyyy-MM-dd")}"/>
                          </#if>
                    </div>
                </div>
            </div>
            <div class="element-end">
                <div class="row" >
                    <div class="col-xs-3 ia">修改密码</div>
                    <div id="div_updatepwd" class="col-xs-9 ia iqaa">
                        <div class="col-xs-11  padding-Ten text-right">&nbsp;</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="element-end">
            <div class="row">
                <div class="col-xs-12 padding-Ten">
                    <p class="dfaqa"><button name="exitLogin" type="button" class="btn btn-block syiaq radius-btn size-13">退出登录</button></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<#include "/front/footer.ftl">
<script src="${basepath}/static/assets/dateui/pikaday-responsive-modernizr.js"></script>
<script src="${basepath}/static/assets/dateui/moment.min.js"></script>
<script src="${basepath}/static/assets/dateui/pikaday.min.js"></script>
<script src="${basepath}/static/assets/dateui/pikaday-responsive.js"></script>
</html>
<script type="text/javascript">
    var username="${member.username}";
    $("#div_nickname").click(function () {
        window.location.href='${basepath}/member/nickname?username='+username;
    });
    var gender="${member.gender}";
    $("#div_gender").click(function () {
        window.location.href='${basepath}/member/gender?username='+username+'&gender='+gender;
    });
    $("#div_updatepwd").click(function () {
        window.location.href='${basepath}/member/toUpdatePwd?username='+username
    });
    $('button[name="exitLogin"]').click(function(){
        weui.confirm("确定退出登录？","提示",function(r){
            if(r==true){
                window.location.href='${basepath}/front/user/logout?khid='+$.cookie('kh');
            }
        });
    })
    if('${member.birthDate!''}'!=""){
        var $date1 = $("#user_age");
        var instance1 = pikadayResponsive($date1);
        $date1.on("change", function() {
            console.info($(this).val());
            var url="${basepath}/member/updateBirthDate";
            var data= {username:username,birthDate:$(this).val()};
            $.post(url,data,function(result){
                if(result==true){
                }
            })
        });
    }

</script>