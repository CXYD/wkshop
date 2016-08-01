<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <#include "/manage/shop/pageManage/tplHeader.ftl">
    <title>页面设计</title>
</head>

<body class="ng-scope">
<div class="dcr-header">
    <div class="dcr-crumbs">
        <a href="selectList" class="crumbs-back crumbs-elements"> </a>
        <a href="selectList" class="crumbs-main crumbs-elements">页面管理</a>
        <span class="crumbs-ltn crumbs-elements"> </span>
        <span class="crumbs-current crumbs-elements">新建自定义页面</span>
    </div>
</div>

<div class="dcr-con">
    <div class="dcr-shower" data-dpr="1" onselectstart="return false;" unselectable="on">
        <div class="dcr-phone" id="dcr-phone" dcr-view-init="" style="background: rgb(238, 238, 238);">
            <div class="dcr-phone-head dcr-item-selector">
                <img src="${basepath}/static/img/microshop/phone_head.jpg" alt="">
                <input type="hidden" id="customPageId" value="">
                <p class="dcr-phone-title" id="dcrPageTitle" way-data="title">页面标题</p>
                <a class="dcr-title-btn" data-for-gaq="新建自定义页面-设置-设置" onclick="editTitlePage()">设置</a>

                <div class="dcr-title-edit"></div>
            </div>
        <#--预览内容-->
            <div class="dcr-phone-body content" id="cpcDcrPreview">

            </div>

            <div data-for-gaq="新建自定义页面-添加模块-添加" onclick="addModule()" class="dcr-addModule">
                <p>+</p>
                <span>选择想要添加的模块</span>
            </div>

        </div>
    </div>
    <div class="dcr-modules">
        <!-- uiView:  -->
        <div class="dcr-views ng-scope" id="ui-view" way-scope="temp_info" way-persistent="true">
            <!-- uiView:  -->

        <#--uiView end-->
        </div>
    </div>
</div>

<div class="dcr-footer">
    <div class="dcr-footer-btn">
        <input type="hidden" id="currModule">
        <input type="hidden" id="currIndex">
        <input type="hidden" id="currEditIndex" value="title">
        <input type="hidden" id="pageid">
        <a class="" id="prviewLink" target="_blank">预览页面</a>
        <a class="dcr-click-able" onclick="savePage()">保存页面</a>
    </div>
</div>


<div id="ui-modal-dialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="custom-width-modalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width:800px;height:600px;overflow: hidden">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="custom-width-modalLabel">添加模块</h4>

            </div>
            <div class="modal-body">

                <div class="dcr-modal-content dcr-select-module">
                    <div class="dcr-moduleTab">
                        <ul>
                            <li class="dcr-cur" id="seltab1" onclick="selectClassify('1')">基础</li>
                            <li class="" id="seltab2" onclick="selectClassify('2')">图文</li>
                            <li class="" id="seltab3" onclick="selectClassify('3')">推广</li>
                        </ul>
                    </div>
                    <div class="dcr-moduleList dcr-modal-curType1" id="modal-curType1" onclick="selectModule(event)"
                         style="width:768px;height:450px; display: block;overflow:scroll;">
                        <ul> <!-- ngRepeat: module in moduleData -->
                            <li class="ng-scope" data-module-type="200">
                                <p class="dcr-module-img"><img
                                        src="${basepath}/static/img/microshop/dpzxsyt-9.jpg" data-module-type="200"></p>

                                <p><span ng-bind="module.name" class="ng-binding">公告</span></p></li>
                            <!-- end ngRepeat: module in moduleData -->
                            <li class="ng-scope" data-module-type="201">
                                <p class="dcr-module-img"><img
                                        src="${basepath}/static/img/microshop/dpzxsyt-10.jpg" data-module-type="202">
                                </p>

                                <p><span ng-bind="module.name" class="ng-binding">进入商城</span></p></li>
                            <!-- end ngRepeat: module in moduleData -->
                        <#--<li class="ng-scope"  data-module-type="98">-->
                        <#--<p class="dcr-module-img"><img-->
                        <#--src="https://d.weidian.com/images/decoration/modules/module_98.png"></p>-->

                        <#--<p><span ng-bind="module.name" class="ng-binding">辅助线</span></p></li>-->
                        <#--<!-- end ngRepeat: module in moduleData &ndash;&gt;-->
                        <#--<li class="ng-scope"  data-module-type="97">-->
                        <#--<p class="dcr-module-img"><img-->
                        <#--src="https://d.weidian.com/images/decoration/modules/module_97.png"></p>-->

                        <#--<p><span ng-bind="module.name" class="ng-binding">辅助空白</span></p></li>-->
                        <!-- end ngRepeat: module in moduleData -->
                    </div>

                    <div class="dcr-moduleList dcr-modal-curType2" id="modal-curType2" onclick="selectModule(event)"
                         style="width:768px;height:450px; display: none;overflow:scroll;">
                        <ul> <!-- ngRepeat: module in moduleData -->
                            <li class="ng-scope">
                                <p class="dcr-module-img"><img
                                        src="${basepath}/static/img/microshop/module_201.png" data-module-type="301">
                                </p>

                                <p><span ng-bind="module.name" class="ng-binding">文字导航</span></p></li>
                            <!-- end ngRepeat: module in moduleData -->
                            <li class="ng-scope">
                                <p class="dcr-module-img"><img
                                        src="${basepath}/static/img/microshop/module_504.png" data-module-type="504"></p>

                                <p><span ng-bind="module.name" class="ng-binding">图片导航</span></p></li>

                            <li class="ng-scope">
                                <p class="dcr-module-img">
                                    <img
                                            src="${basepath}/static/img/microshop/module_203.png"
                                            data-module-type="501"></p>

                                <p><span ng-bind="module.name" class="ng-binding">大图广告</span></p></li>

                            <!-- end ngRepeat: module in moduleData -->
                            <li class="ng-scope">
                                <p class="dcr-module-img">
                                    <img
                                            src="${basepath}/static/img/microshop/module_205.png"
                                            data-module-type="503"></p>


                                <p><span ng-bind="module.name" class="ng-binding">轮播广告</span></p>
                            </li>
                            <!-- end ngRepeat: module in moduleData -->
                            <li class="ng-scope">
                                <p class="dcr-module-img">
                                    <img
                                            src="${basepath}/static/img/microshop/module_204.png"
                                            data-module-type="502"></p>

                                <p><span ng-bind="module.name" class="ng-binding">双列大图</span></p></li>
                            <!-- end ngRepeat: module in moduleData --> </ul>
                    </div>

                    <div class="dcr-moduleList dcr-modal-curType3" id="modal-curType3" onclick="selectModule(event)"
                         style="width:768px;height:450px; display: none;overflow:scroll;">
                        <ul> <!-- ngRepeat: module in moduleData -->
                            <li class="ng-scope">
                                <p class="dcr-module-img">
                                    <img src="${basepath}/static/img/microshop/dpzxsyt-7.jpg" data-module-type="701">
                                </p>

                                <p><span ng-bind="module.name" class="ng-binding">商品模块 - 大图</span></p>
                            </li>

                            <li class="ng-scope">
                                <p class="dcr-module-img">
                                    <img src="${basepath}/static/img/microshop/module_302.png" data-module-type="702">
                                </p>

                                <p><span ng-bind="module.name" class="ng-binding">商品模块 - 两列</span></p>
                            </li>

                            <li class="ng-scope">
                                <p class="dcr-module-img">
                                    <img src="${basepath}/static/img/microshop/module_303.png" data-module-type="703">
                                </p>

                                <p><span ng-bind="module.name" class="ng-binding">商品模块 - 列表</span></p>
                            </li>

                            <!-- end ngRepeat: module in moduleData --> </ul>
                    </div>

                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<div id="subSelect-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">设置链接</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <label class="control-label col-md-2">选择链接</label>

                        <div class="col-md-10">
                            <select id="subSelect">
                                <option value="">未选择</option>
                                <option value="www.wokuan.cn">测试链接</option>
                            </select>
                            <input id="parentSelect" type="hidden" value="">
                        </div>
                    </div>

                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal" onclick="closeModal(this)">取消</button>
                <button type="button" class="btn btn-info" onclick="returnSelectLink()">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- /.modal -->

<#--是否删除弹窗-->
<div class="cpcLayer" style="display: none">
    <div class="cpcLayer_con cpcConfirm_con" style="display: block;"><p>是否删除？</p><em
            class="cpcLayer_shadow cpcLayer_shadowA">&nbsp;</em><em class="cpcLayer_shadow cpcLayer_shadowB">&nbsp;</em>

        <div class="cpcLayer_btnW">
            <div class="cpcLayer_btnA ">取消</div>
            <em class="cpcLayer_shadow cpcLayer_shadowC">&nbsp;</em><em class="cpcLayer_shadow cpcLayer_shadowD">
            &nbsp;</em>

            <div class="cpcLayer_btnB ">确定</div>
        </div>
    </div>
</div>
<#--弹窗end-->


<div id="link-modal-dialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="custom-width-modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width:800px;height:600px;overflow: hidden">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="custom-width-modalLabel">选择链接</h4>
                <input type="hidden" id="currLinkTypeTab" value="1">
                <input type="hidden" id="currPopuplink">
            </div>
            <div class="modal-body">

                <div class="dcr-modal-content dcr-select-module">
                    <div class="dcr-moduleTab">
                        <ul>
                            <li class="dcr-cur" id="selLinktab1" onclick="selectLink('1')">商品</li>
                            <li class="" id="selLinktab2" onclick="selectLink('2')">商品分类</li>
                            <li class="" id="selLinktab3" onclick="selectLink('3')">自定义页面</li>
                            <li class="" id="selLinktab4" onclick="selectLink('4')">其他链接</li>
                        </ul>
                    </div>
                    <div class="dcr-moduleList dcr-modal-curType1" id="modal-curLinkType1"
                         onclick="selectTypeLink(event)"
                         style="width:768px;height:450px; display: block;overflow:scroll;">
                        <table id="productTable" class="table table-striped table-bordered dataTable hover" role="grid"
                               aria-describedby="datatable_info">

                        </table>
                    </div>

                    <div class="dcr-moduleList dcr-modal-curType2" id="modal-curLinkType2"
                         onclick="selectTypeLink(event)"
                         style="width:768px;height:450px; display: none;overflow:scroll;">
                        <table id="classTable" class="table table-striped table-bordered dataTable hover" role="grid"
                               aria-describedby="datatable_info">

                        </table>
                    </div>

                    <div class="dcr-moduleList dcr-modal-curType3" id="modal-curLinkType3"
                         onclick="selectTypeLink(event)"
                         style="width:768px;height:450px; display: none;overflow:scroll;">
                        <table id="customPageTable" class="table table-striped table-bordered dataTable hover" role="grid"
                               aria-describedby="datatable_info">

                        </table>
                    </div>

                    <div class="dcr-moduleList dcr-modal-curType4" id="modal-curLinkType4"
                         onclick="selectTypeLink(event)"
                         style="width:768px;height:450px; display: none;overflow:scroll;">
                        <div class="pop_diy_input ng-scope">
                            <div class="diy_link_main">
                                <input type="text" name="diyUrl" id="diyUrl" value="" way-data="diyUrl" placeholder="请输入定义链接地址" class="ng-pristine ng-valid">

                                <div class="err ng-binding" ng-bind="isUrl.msg"></div>
                            </div>
                            <div class="btn_diyArea">
                                <a href="javascript:void(0)" class="btn btn_white" onclick="closePopup()">取消</a>
                                <a href="javascript:void(0)" class="btn" onclick="selectItem({type:4,id:-1, url:$('#diyUrl).val(), name:'自定义链接'})">确定</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<div id="productLink-modal-dialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="custom-width-modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width:800px;height:600px;overflow: hidden">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="custom-width-modalLabel">添加商品</h4>
                <input type="hidden" id="currLinkTypeTab" value="1">
                <input type="hidden" id="currPopuplink">
            </div>
            <div class="modal-body">

                <div class="dcr-modal-content dcr-select-module">
                    <div class="dcr-moduleTab">
                        <ul>
                            <li class="dcr-cur" id="selLinktab1" onclick="selectLink('1')">商品</li>
                        </ul>
                    </div>
                    <div class="dcr-moduleList dcr-modal-curType1" id="modal-curLinkType1"
                         onclick="selectProductTypeLink(event)" style="width:768px;height:450px; display: block;overflow:scroll;">
                        <table id="productSelectTable" class="table table-striped table-bordered dataTable hover" role="grid" aria-describedby="datatable_info">

                        </table>
                    </div>


                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<iframe src="about:blank" style="display:none"  name="imgIframe"></iframe>

<#include "/manage/shop/pageManage/tplFooter.ftl">

<script>
    var page = {};
    var editPageId = '${id!""}';
    /**
     * 页面初始化加载
     */
    $(document).ready(function () {
        way.set('temp_info', "")
        rightLoadModule();

        initClassTable();
        initProductTable();
        initCustomPage();
        initProductSelectTable();

        if(editPageId!="")
        initEditPage();


        way.watchAll(function (selector, value) {
////            console.log("Something changed.", {
////                selector: selector,
////                value: value
////            });
        });

    });

    function initEditPage(){

        console.log("编辑内容id：",editPageId);
        $("#customPageId").val(editPageId);
        $("#prviewLink").attr('href', 'pview/' + editPageId);
        $("#prviewLink").addClass('dcr-click-able');
        $.ajax({
            url:'/wkshop/manage/pageManage/queryTpl',
            data:{id:editPageId},
            success:function(page){
                console.log("查询返回",page);
                tpls=JSON.parse(page.pagecontent);
                console.log(tpls);
                way.set('title', tpls.title)
//                way.set('temp_info',tpls.temp_info);
                initLeftModule(tpls.temp_info);
            }
        })

    }

    function initLeftModule(tpl_info){


        var j = 0;
        for(var i=0; i<tpl_info.length;i++){
            console.log('模块id=',tpl_info[i].type);
           if(tpl_info[i].type!=undefined){
               j = i+1;
               temp_info = tpl_info[i];
               leftLoadModule('',tpl_info[i].type,temp_info);
               break;
           }
        }
//        console.log("j=",j);
        for(j; j<tpl_info.length;j++){
            console.log("加载模块：=",tpl_info[j].type);
            if(tpl_info[j].type!=undefined){
                temp_info = tpl_info[j];
                leftLoadModule($("#currModule").val(),tpl_info[j].type,temp_info);

            }

        }
        rightLoadModule();
    }







</script>


</body>
</html>


