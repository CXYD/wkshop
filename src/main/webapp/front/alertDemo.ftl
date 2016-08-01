
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <#include "/front/header.ftl">
    <title>RunJS</title>

</head>
<body>
<button onclick="javascript:a1();">alert</button><br>
<button onclick="javascript:a2();">alert 带回调</button><br>
<button onclick="javascript:c1();">confirm</button><br>
<button onclick="javascript:c2();">confirm 带回调</button><br>
<button onclick="javascript:s();">success</button><br>
<button onclick="javascript:i();">info</button><br>
<button onclick="javascript:e();">error</button><br>
<button onclick="javascript:show();">show</button><br>
<button onclick="javascript:openMenu();">弹出菜单</button><br>
<#include "/front/footer.ftl">
</body>
</html>
<style>button{
    border:1px solid #ccc;
    cursor:pointer;
    display:block;
    margin:auto;
    position:relative;
}</style>
<script>function a1(){
    weui.alert("无回调alert!");
}
function a2(){
    weui.alert("带回调alert!","alert",function(){
        alert("点击确认");
    });
}
function c1(){
    weui.confirm("无回调confirm!");
}
function c2(){
    weui.confirm("带回调alert!","confirm",function(r){
        alert((r==true)?"点击确认":"点击取消");
    });
}
function s(){
    weui.Loading.success();
}
function i(){
    weui.Loading.info("info!");
}
function e(){
    weui.Loading.error("error!");
}
function show(){
    weui.Loading.show();
    //weui.Loading.hide()//隐藏此元素
}
function openMenu(){
    var array=[{name:"选项1",value:"1"},{name:"选项2",value:"2"}];
    menus = weui.actionSheet.create(array,function(val,text){
        alert("选择值:"+val+",选择内容:"+text);
    });
    menus.show();
}

//$.cookie('name')

//$.cookie('name','value',{expires:7,path:'/wkshop'})

</script>

