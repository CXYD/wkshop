<!DOCTYPE html>
<html>
<head lang="en">
<#include "/front/header.ftl">
    <meta charset="UTF-8">
    <title>发现</title>
</head>
<body>

<div class="wraper" name="content">
    发现页面
</div>

<div class="quick-nav" name="footer">
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/homePage?khid=${khid}"><img src="${basepath}/static/img/front/wscqt6-8-46.png" width="25"></a> </div>
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/classPage?khid=${khid}"><img src="${basepath}/static/img/front/wscqt6-8-47.png" width="25"></a> </div>
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/findPage?khid=${khid}"><img src="${basepath}/static/img/front/wscqt6-8-53.png" width="25"></a> </div>
    <div class="col-xs-3 text-center"><a href="${basepath}/webapp/myPage?khid=${khid}"><img  src="${basepath}/static/img/front/wscqt6-8-49.png" width="25"></a> </div>
</div>

</body>

</html>
<#include "/front/footer.ftl">