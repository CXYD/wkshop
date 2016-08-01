<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 15/11/5
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head lang="zh">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="#ffffff" name="msapplication-TileColor"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"/>

    <title>上传身份证</title>
    <link rel="stylesheet" href="/wkshop/static/css/front/upload.css"/>
    <link rel="stylesheet" href="/wkshop/static/css/front/weui.css"/>
    <script type="text/javascript" src="/wkshop/static/js/jquery.js"></script>
    <script type="text/javascript" src="/wkshop/static/js/front/ajaxfileupload.min.js"></script>
    <script type="text/javascript" src="/wkshop/static/js/front/LocalResizeIMG.js"></script>
    <script type="text/javascript" src="/wkshop/static/js/front/patch/mobileBUGFix.mini.js"></script>

    <script>
        function setTop(className) {
            try {
                var oSucc = document.getElementsByClassName(className)[0];
                oSucc.style.top = document.documentElement.clientHeight / 2 - oSucc.offsetHeight / 2 + scrollT + 'px';
            } catch (e) {
            }
        }

        window.onload = function () {
            $(".popup-w").css("height", document.body.scrollHeight + 'px')
            /*上传成功弹层,上传失败，loading*/
            var scrollT = 0;
            window.onscroll = function () {
                scrollT = document.documentElement.scrollTop || document.body.scrollTop;
                setTop('upload-succ');
                setTop('loading');
                setTop('upload-fail');
            }

            $(".nu-upload").bind('click', function () {
                if ($(".loading :visible").length > 0) {
                    var cycle = setInterval(function () {
                        if ($(".popup-w :visible").length > 0) {
                            clearInterval(cycle);
                            scrollT = document.documentElement.scrollTop || document.body.scrollTop;
                            setTop('upload-succ');
                            setTop('loading');
                            setTop('upload-fail');
                        }
                    }, 10);
                }
            });
        }

    </script>
</head>
<body>

<script src="/wkshop/static/js/front/jweixin-1.0.0.js"></script>

<script>

    var canClosed = 0;

    try {
        wx.config({
            debug: false,
            appId: 'wx4db86df6e1598d8b',
            timestamp: new Number(1446519067),
            nonceStr: 'B92DF1DD-D2FF-18E4-1104-76ABDEF14EDB',
            signature: '1446519067',
            jsApiList: [
                'closeWindow'
            ]
        });
    } catch (e) {
    }
    wx.ready(function () {
        canClosed = 1;
    });

    $(function () {
        var isReady = 1;
        $("#file_1,#file_2,#file_3").change(function (e) {
            var domId = this.id;
            var tail = domId.substring(domId.length - 1);
            try {
                jQuery.preView(e, "preview_" + tail, function (data) {
                    $("#up_" + tail).hide();
                    $("#preview_" + tail).hide();
                    $("#watermark_" + tail).hide();
                    $("#upover_" + tail).show();

                });
            } catch (e) {
                $("#up_" + tail).hide();
                $("#upover_" + tail).show();
                isReady += tail;
            }
        });


        $("#preview_1,#preview_2,#preview_3").bind("load", function () {
            var isNew = $(this).attr("isNew");
            if (isNew && isNew == "1") {
                var domId = this.id;
                var tail = domId.substring(domId.length - 1);
                jQuery.preViewCompress(this, 512000, 600, function () {
                    $("#preview_" + tail).show();
                    $("#watermark_" + tail).show();
                    isReady += tail;
                });
            }
        });


        $(".ia-tip").click(function () {
            var domId = this.id;
            var tail = domId.substring(domId.length - 1);
            $("#up_" + tail).show();
            $("#upover_" + tail).hide();
            $("#preview_" + tail).attr("src", "");
            isReady -= tail;
            $("#file_" + tail).replaceWith($("#file_" + tail).clone(true));
        });

        function closeMe() {
            // 微信关闭自己
            if (canClosed == 1) {
                //wx.closeWindow();
                history.back();
            } else if (canClosed == 2) {
                //调用APP方法关闭页面
                alert("您的客户端不支持关闭当前窗口操作,请点击应用上的关闭或返回按钮");
            } else {
                alert("您的客户端不支持关闭当前窗口操作,请点击应用上的关闭或返回按钮");
            }
        }

        function reTry() {
            location.reload();
        }

        function succOrfail(isSucc, errorCode, errorMsg) {
            var msg = "";
            if (errorCode == 1) {
                msg = "图片大小不可超过2M";
            } else if (errorCode == 2) {
                msg = "图片格式错误，照片支持jpg/jpeg格式";
            } else if (errorCode == 3 || errorCode == 4) {
                msg = errorMsg;
            }
            if (isSucc) {
                $("div[name='infos']").hide();
                $("#upok").show();
                setTimeout(function () {
                    $("body>div").hide();
                    $("#upsucc").show();
                }, 3000);
            } else {
                $("div[name='infos']").hide();
                $("#upfailtext").html(msg);
                $("#upfail").show();
                setTimeout(function () {
                    var colosTag = $("#upfail").attr("close");
                    if (colosTag && colosTag != "") {
                        closeMe();
                    } else {
                        $("div[name='infos']").hide();
                        isUploading = 0;
                        $("#uppre").show();
                    }
                }, 3000);
            }
        }

        function uploading() {
            $("div[name='infos']").hide();
            $("#uping").show();
        }

        $(".nu-cancel,#upsucc-close").click(closeMe);
        var isUploading = 0;

        var userID_IMG = {};

        $(".nu-upload").click(function () {
            // 校验
            if (isReady == 1 || isReady == 3 || !file_1.value || file_1.value == "") {
                alert("点击添加身份证正面照片");
                return false;
            }

            if (isReady == 1 || isReady == 2 || !file_2.value || file_2.value == "") {
                alert("点击添加身份证反面照片");
                return false;
            }

            if (isReady == 1 || isReady == 4 || !file_3.value || file_3.value == "") {
                alert("点击添加手持身份证正面照片");
                return false;
            }


            if (isUploading == 1) {
                return false;
            } else {
                isUploading = 1;
                uploading();
            }

            //异步上传图片
            jQuery.ajaxFileUpload({
                url: "/wkshop/uploadIDA",
                secureuri: false,
                uploadType: 'img',
                fileElementId: 'preview_1',
                compress: true,
                dataType: 'json',
                success: function (data) {

                    if (data.errorCode) {
                        succOrfail(false, data.errorCode, data.errorMsg);
                    } else if ((data.fail && data.fail == 1) || !data.img1) {
                        succOrfail(false);
                    } else if (data.img1) {
                        userID_IMG.img1=data.img1_url;
                        /**setTimeout(function(){**/
                        jQuery.ajaxFileUpload({
                            url: "/wkshop/uploadIDB",
                            secureuri: false,
                            uploadType: 'img',
                            fileElementId: 'preview_2',
                            compress: true,
                            dataType: 'json',
                            success: function (data) {

                                    if (data.errorCode) {
                                        succOrfail(false, data.errorCode, data.errorMsg);
                                   } else if (data.img2) {
                                        userID_IMG.img2=data.img2_url;
                                        console.log("正反面上传成功。。。");
                                        jQuery.ajaxFileUpload({
                                            url: "/wkshop/uploadIDC",
                                            secureuri: false,
                                            uploadType: 'img',
                                            fileElementId: 'preview_3',
                                            compress: true,
                                            dataType: 'json',
                                            success: function (data) {
                                                if (data.errorCode) {
                                                    succOrfail(false, data.errorCode, data.errorMsg);
                                                } else if (data.img3) {
                                                    succOrfail(true, "", "")
                                                    userID_IMG.img3=data.img3_url;
                                                    sessionStorage.setItem("userID_IMG",JSON.stringify(userID_IMG));
                                                    sessionStorage.setItem("uploadState",1);
                                                }
                                            },
                                            error: function (data) {
                                                succOrfail(false)
                                            }
                                        });

                                }
                            },
                            error: function (data) {
                                succOrfail(false)
                            }
                        });

                            /**}, 2000);**/
                    }
                },
                error: function (data) {
                    succOrfail(false);
                }
            });
        });



//        $(".nu-upload").click(function () {
//
//            //异步上传图片
//            var userID_IMG = {
//                img1: $('#file_1').attr('data-imgurl'),
//                img2: $('#file_2').attr('data-imgurl'),
//                img3:  $('#file_3').attr('data-imgurl')
//            };
//
//            // 校验
//            if (userID_IMG.img1==undefined || userID_IMG.img1 =="") {
//                weui.Loading.error("点击添加身份证正面照片");
//                return false;
//            }
//
//            if (userID_IMG.img2 == undefined || userID_IMG.img2== "") {
//                weui.Loading.error("点击添加身份证反面照片");
//                return false;
//            }
//
//            if (userID_IMG.img3 == undefined || userID_IMG.img3 == "") {
//                weui.Loading.error("点击添加手持身份证正面照片");
//                return false;
//            }
//
//
//
//            console.log(userID_IMG);
//
//            sessionStorage.setItem("userID_IMG",JSON.stringify(userID_IMG));
//
//            if(userID_IMG.img1=='error' || userID_IMG.img2=='error' || userID_IMG.img3=='error'){
//                sessionStorage.setItem("uploadState",0);
//                weui.Loading.error('请确保上传的图片小于2M');
//            }else{
//                sessionStorage.setItem("uploadState",1);
//                weui.Loading.info("上传成功!");
//                closeMe();
//            }
//
//        });



//        $('#file_1').localResizeIMG({
//            quality:1,
//            success:function(result){
//               // result.base64/result.clearBase64
//                var imgData={
//                    base64_string:result.clearBase64
//                };
//                $.ajax({
//                    type: "POST",
//                    url: "/wkshop/uploadBASE64IMG",
//                    data: imgData,
//                    dataType:"json",
//                    success: function(data){
//                        if (0 == data.status) {
//                            return false;
//                        }else if(1== data.status){
//                            //alert(data.content);
//                            $('#file_1').attr('data-imgurl',data.content);
//                        }else if(2==data.status){
//                            succOrfail(false,1);
//                        }
//                    },error:function(){
//                        $('#file_1').attr('data-imgurl','error');
//                    }
//                });
//            }
//        })
//
//        $('#file_2').localResizeIMG({
//            quality:1,
//            success:function(result){
//                // result.base64/result.clearBase64
//                var imgData={
//                    base64_string:result.clearBase64
//                };
//                $.ajax({
//                    type: "POST",
//                    url: "/wkshop/uploadBASE64IMG",
//                    data: imgData,
//                    dataType:"json",
//                    success: function(data){
//
//                        if (0 == data.status) {
//                            return false;
//                        }else if(1== data.status){
//                            //alert(data.content);
//                            $('#file_2').attr('data-imgurl',data.content);
//                        }else if(2==data.status){
//                            succOrfail(false,1);
//                        }
//                    },error:function(){
//                        $('#file_2').attr('data-imgurl','error');
//                    }
//                });
//            }
//        })
//
//
//        $('#file_3').localResizeIMG({
//            quality:1,
//            success:function(result){
//                // result.base64/result.clearBase64
//                var imgData={
//                    base64_string:result.clearBase64
//                };
//
//                $.ajax({
//                    type: "POST",
//                    url: "/wkshop/uploadBASE64IMG",
//                    data: imgData,
//                    dataType:"json",
//                    success: function(data){
//
//                        if (0 == data.status) {
//                            return false;
//                        }else if(1== data.status){
//                            //alert(data.content);
//                            $('#file_3').attr('data-imgurl',data.content);
//
//                        }else if(2==data.status){
//                            succOrfail(false,1);
//                        }
//                    },error:function(){
//                        $('#file_3').attr('data-imgurl','error');
//                    }
//                });
//            }
//        })




    });


</script>

<div id="uppre" class="common-wrapper">
    <div class="steps nopd">
        <div class="sm-tip"> 您提供的身份证信息中国联通将予以加密保护，保证此照片仅用于本次入网。为避免证件照片被非法利用，上传后将自动添加水印。
        </div>
    </div>
    <div class="m idcard">
        <div class="mc">
            <!-- 添加class，idcard-add-no ,为不可添加状态 -->
            <div id="up_1" class="idcard-add ">
                <a href="javascript:;" class="ia-add1">
                    <input id="file_1" capture="camera" accept="image/*" name="file_1" type="file"
                           style="font-size: 118px;cursor: pointer;position: absolute;right: 0;top: 0; width: 100%; height: 100%;z-index:999;filter:alpha(opacity=0);opacity:0;">点击添加身份证正面照片
                </a>
            </div>
            <div id="upover_1" class="idcard-add" style="display:none;">
                <a href="javascript:;" class="ia-succ">&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <img id="preview_1" src="/wkshop/static/img/front/upload/pic1.png" class="idcard-img">

                <div id="watermark_1" class="watermark"></div>
                <i id="change_1" class="ia-tip">更换</i></div>
        </div>
        <div class="mc">
            <!-- 添加class，idcard-add-no ,为不可添加状态 -->
            <div id="up_2" class="idcard-add "><a href="javascript:;" class="ia-add2">
                <input id="file_2" capture="camera" accept="image/*" name="file_2" type="file"
                       style="font-size: 118px;cursor: pointer;position: absolute;right: 0;top: 0; width: 100%; height: 100%;z-index:999;filter:alpha(opacity=0);opacity:0;">点击添加身份证反面照片
            </a>
            </div>
            <div id="upover_2" class="idcard-add" style="display:none;">
                <a href="javascript:;" class="ia-succ">&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <img id="preview_2" src="/wkshop/static/img/front/upload/pic2.png" class="idcard-img">

                <div id="watermark_2" class="watermark"></div>
                <i id="change_2" class="ia-tip">更换</i></div>
        </div>
        <div class="mc">
            <!-- 添加class，idcard-add-no ,为不可添加状态 -->
            <div id="up_3" class="idcard-add "><a href="javascript:;" class="ia-add3">
                <input id="file_3" capture="camera" accept="image/*" name="file_3" type="file"
                       style="font-size: 118px;cursor: pointer;position: absolute;right: 0;top: 0; width: 100%; height: 100%;z-index:999;filter:alpha(opacity=0);opacity:0;">点击添加本人手持身份证正面照片
            </a>
            </div>
            <div id="upover_3" class="idcard-add" style="display:none;">
                <a href="javascript:;" class="ia-succ">&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <img id="preview_3" src="/wkshop/static/img/front/upload/pic2.png" class="idcard-img">

                <div id="watermark_3" class="watermark"></div>
                <i id="change_3" class="ia-tip">更换</i></div>
        </div>
    </div>
    <div class="name-upload">
        <!-- class添加nu-cancel-no，为不能点击状态 -->
        <a href="javascript:;" class="nu-cancel">取消</a>
        <!-- class添加nu-upload-no，为不能点击状态 -->
        <a href="javascript:;" class="nu-upload">上传</a></div>
</div>
<!-- 上传成功和失败的弹层 -->

<div id="upok" name="infos" class="popup-w" style="display:none;">
    <div class="upload-succ">
        <div class="upload-icon"></div>
        <p>上传成功</p></div>
</div>
<div id="upfail" name="infos" class="popup-w" style="display:none;">
    <div class="upload-fail">
        <div class="upload-icon"></div>
        <p>上传失败</p><strong id="upfailtext"></strong></div>
</div>
<div id="upsucc" name="infos" class="common-wrapper" style="display:none;">
    <div class="succ-show"><span></span><strong>上传成功</strong><br/>
        <a id="upsuccclose" style="background-color:#ff7448;border: 1px solid #ff7448;color: #fff"
           href="goodsSubmit">确定</a>
    </div>
</div>
<div id="uping" name="infos" class="loading" style="display:none;">
    <div class="loading-in"></div>
</div>
</body>
<script type="text/javascript" src="/wkshop/static/js/front/weui.js"></script>
</html>
