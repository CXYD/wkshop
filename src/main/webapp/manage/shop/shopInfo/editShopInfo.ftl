<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="商城信息">-->


<div class="row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-heading"><h3 class="panel-title">商城信息</h3></div>
            <div class="panel-body">
                <form id="pic_form" class="form-horizontal" role="form">
                        <input type="hidden" id="khid" name="khid" value="${shopInfo.khid}">
                    <div class="form-group">
                        <label class="col-md-2 control-label">商城logo</label>
                        <div class="col-md-1">
                            <img id="head_photo_src"  class="thumb-lg img-rounded bx-s" style="width: 50px;height: 50px;" src="${shopInfo.logurl}" alt="">
                        </div>
                        <div class="col-md-2 m-t-20">
                        <#--<a href="#">修改</a>-->
                            <input type="button" value="修改" onclick="javascript:$('#head_photo').click()">
                            <input type="file" name="head_photo" onchange="valChange()" id="head_photo" accept="image/*">
                            <input type="hidden" name="photo_pic" id="photo_pic" value="">
                        </div>
                    </div>
                </form>

                <form name="shop_form" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label class="col-md-2 control-label">商城名称</label>
                        <div class="col-md-8">
                        <input type="hidden" id="khid" name="khid" value="${shopInfo.khid}">
                            <input name="shopname" value="${shopInfo.shopname}" maxlength="30" type="text" class="form-control"  placeholder="请输入商城名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">创建日期</label>
                        <div class="col-md-8">
                            <input value="${(shopInfo.createtime?string('yyyy-MM-dd HH:mm:ss'))!}" type="text" readonly class="form-control" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">商城简介</label>
                        <div class="col-md-8">
                            <textarea name="shopdesc" class="form-control" rows="5" maxlength="200"   placeholder="请输入商城简介信息，可用于微信分享">${shopInfo.shopdesc}</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系人</label>
                        <div class="col-sm-8">
                            <input name="updateaccount" value="${shopInfo.updateaccount}" type="text" readonly class="form-control" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系人手机号</label>
                        <div class="col-sm-8">
                            <input id="linkmantel" name="linkmantel" value="${shopInfo.linkmantel}" type="text" maxlength="11" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-inline">

                            <label class="col-sm-2 control-label">客服电话</label>
                            <div class="col-sm-8">
                                <input id="quhao" name="quhao" value="${shopInfo.quhao}" type="text" class="form-control pull-left col-sm-3" maxlength="4">
                                <label class="form-control-static col-sm-1">
                                    ——
                                </label>
                                <input id="servicetel" name="servicetel" value="${shopInfo.servicetel}" type="text" class="form-control col-sm-4 " maxlength="8">

                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">联系人地址</label>
                        <div class="col-md-8">
                            <textarea name="address" class="form-control" rows="5"  maxlength="200" placeholder="200字以内" maxlength="200">${shopInfo.address}</textarea>
                        </div>
                    </div>

                </form>

                <div class="col-md-2 col-md-offset-5">
                    <button id="updateInfo" class="btn btn-success">保存修改</button>
                </div>
            </div> <!-- panel-body -->
        </div> <!-- panel -->
    </div> <!-- col -->
</div> <!-- End row -->


<div id="con-close-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">上传图片</h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <form class="form-horizontal">
                        <label class="col-md-12 control-label">图片大小不可以大于1M</label>
                        <img src="${basepath}/static/img/add.png" width="100%" alt="">
                    </form>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-info">保存</button>
            </div>
        </div>
    </div>
</div><!-- /.modal -->

<script>

    function Picker(){
        alert("日期；；")
    }
    $("#updateInfo").click(function () {
        var linkmantel=$("#linkmantel").val();
        var quhao=$("#quhao").val();
        var servicetel=$("#servicetel").val();
        $("font[name='font']").empty();
        if(!(/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(linkmantel))) {
            $("#linkmantel").after('<font name="font" size="2" color="red">请输入正确的手机号码</font>');
        }else if(!(/^\d{3}$|^\d{4}$/.test(quhao))) {
            $("#quhao").after('<font name="font" size="2" color="red">请输入正确的区号号码</font>');
        }else if(!(/^\d{7}$|^\d{8}$/.test(servicetel))) {
            $("#servicetel").after('<font name="font" size="2" color="red">请输入正确的固话号码</font>');
        }else {
        $.ajax({
            url:"${basepath}/manage/shopInfo/updateInfo",
            data:$("form[name='shop_form']").serialize(),
            async:false,
            success:function () {
                swal("成功","添加成功","success");
            },
            error:function(){
                swal("失败","添加失败","error");
            }
        });
        }
    });

    $("#head_photo").hide();

    function valChange() {
        ajaxFileUploadview('head_photo','photo_pic',"${basepath}/upload");
    }
    $(".btn.btn-info").click(function(){
        swal("成功","完成","success");
        return;
    })

    function show_head(head_file){
        //插入数据库
        $("#head_photo_src").attr('src',head_file);
        var str=head_file.split("/");
        var last=str[str.length-1];
        var mm=last.split("\\");
        var name=mm[mm.length-1];
        var logurl="../../static/upload/"+name;
        var khid=$("#khid").val();
        $.ajax({
            url:"${basepath}/manage/shopInfo/uppic",
            data:{"logurl":logurl,"khid":khid},
            async:false,
            success:function () {

            },
            error:function(){
                swal("失败","图片添加失败","error");
            }
        });

    }

    //文件上传带预览
    function ajaxFileUploadview(imgid,hiddenid,url){
        console.log("url:"+url);
        $.ajaxFileUpload({
            url:url,
            secureuri:false,
            fileElementId:imgid, //文件上传空间的id属性
            dataType: 'text',
            data:{name:'logan', id:'id'},
            success: function (data, status)
            {
                if(typeof(data) != 'undefined')
                {
                    $.Notification.autoHideNotify('success','top center','提示','图片上传成功!');
                    show_head(data);
                }
            },
            error: function (data, status, e)
            {
                $.Notification.autoHideNotify('error','top center','提示',e);
            }
        })
        return false;
    }

</script>

<#--</@page.pageBase>-->
<link href="${basepath}/static/assets/notifications/notification.css" rel="stylesheet" />
<script src="${basepath}/static/assets/notifications/notify.min.js"></script>
<script src="${basepath}/static/assets/notifications/notify-metro.js"></script>
<script src="${basepath}/static/assets/notifications/notifications.js"></script>
<script src="${basepath}/static/js/ajaxfileupload.js"></script>

