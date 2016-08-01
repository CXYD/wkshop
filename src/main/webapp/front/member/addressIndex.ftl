<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>地址管理</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no" />
<body>
<div class="whole">

<div class="Mall-Specifications">
<#list addressList as item>
  <div class="user-body" id="${item.id}">
    <div class="element-body">

      <div class="row">
        <div class="col-xs-12 size-15 color-25 ia">${item.consigneename} | ${item.phone}</div>
        <div class="col-xs-12 ia size-12 color-999">
        ${item.address}
        </div>
        
      </div>
    </div>    
    <div class="element-end">
      <div class="row">
        <div class="col-xs-7 ia color-999">
            <#if item.defaulttype??&&item.defaulttype=='0'>
              <input type="radio" name="optionsRadiosinline" value="${item.id}">
            <#elseif item.defaulttype??&&item.defaulttype=='1'>
              <input type="radio" checked name="optionsRadiosinline" value="${item.id}">
            </#if>
            设为默认地址</div>
        <div class="col-xs-5 ia color-999">
        	<div class="col-xs-6 ia size-12">
   		    <img src="${basepath}/static/img/front/wscqt6-8-3.jpg" width="16"> <a href="toEditAddress?id=${item.id}">编辑</a></div>
            <div class="col-xs-6 ia size-12">
   		    <img src="${basepath}/static/img/front/wscqt6-8-4.jpg" width="16"> <a href='javascript:del(${item.id});'>删除</a></div>
        </div>        
        </div>
      </div>
    </div>
  </div>
</#list>
  <div class="Mall-Specifications" id="div_add">
  <div class="user-body">
        
    <div class="element-end" >
      <div class="row" name="add">
        <div class="col-xs-1 ia color-666" ><img src="${basepath}/static/img/front/wscqt6-8-25.jpg" width="16"></div>
        <div class="col-xs-5 ia color-666">
        	新增地址
        </div>        
        </div>
      </div>
    </div>
  </div>
</div>
<#include "/front/footer.ftl">
<script>
    $('div[name="add"]').click(function(){

        window.location.href='${basepath}/front/mine/toAddAddress?name=${name}';
    });
    $("input[type='radio']").click(function(){
        var raid= $('input[type="radio"]:checked').val();
        weui.Loading.show();
        $.ajax({
                    type: "POST",
                    async: true,
                    url: "updateDefault",
                    data:{id:raid},
                    success:function (result) {
                        if(result==true){
                        }else{
                            weui.Loading.error("更新失败!");
                        }
                        weui.Loading.hide();
                    }
                }
        );

    })
    function del(obj){
        weui.confirm("确认要删除吗？","提示",function(r){
            if(r==true){
                weui.Loading.show();
                $.ajax({
                    type:"post",
                    async:true,
                    url:"delById",
                    data:{id:obj},
                    success:function(result){
                        if(result=='1'){
                            $('#'+obj).remove();
                            weui.Loading.info("删除成功！");
                        }else{
                            weui.Loading.error("删除失败！");
                            return false;
                        }
                        weui.Loading.hide();
                    }
                });

            }
        });
    }
    if(${addressList?size}==5){
        $('#div_add').hide();
    }
</script>
</body>
</html>
