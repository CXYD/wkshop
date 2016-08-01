
<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>校验成功</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<body>

<div class="register">
    <div class="money-body loaf">
        <div class="col-xs-12 text-center  fill-top"><img src="${basepath}/static/img/front/wscqt6-8-31.jpg" width="75"></div>
    </div>
    <div class="col-xs-12 ia size-13 fill-top height2 indent26">尊敬的用户，您的号码<span class="color-f60">${phone!""}</span>已通过初步审核啦，我们将会以短信或人工方式通知您办理结果，请耐心等待。</div>
    <div class="element-end">
        <div class="row">
            <div class="col-xs-12 padding-Ten">
                <p class="dfaqa"><button type="button" class="btn btn-block org-btn">下一步</button></p>
            </div>
        </div>
    </div>

</div>
</body>
<#include "/front/footer.ftl">
<script>
    $('button').on('click',function(){
        location.href='submitCheckOldNum';
    })
</script>
</html>
