<!DOCTYPE html>
<html>
<head lang="en">
  <#include "/front/header.ftl">
    <meta charset="UTF-8">
    <title>手机商城</title>
</head>
<body>

</body>

</html>
<#include "/front/footer.ftl">

<script>
    weui.Loading.show();
    weui.Loading.error("支付失败,稍后再试！")
    window.history.go(-1)
</script>