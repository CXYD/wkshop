
<link rel="stylesheet" type="text/css" href="${staticpath}/bootstrap-multiselect-0.9.13/dist/css/bootstrap-multiselect.css" />
<script src="${staticpath}/bootstrap-multiselect-0.9.13/dist/js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="${basepath}/static/css/tpl/main.css">
<#--<link rel="stylesheet" href="${basepath}/resource/kindeditor-4.1.11/themes/default/default.css" />-->
<#--<script charset="utf-8" src="${basepath}/resource/kindeditor-4.1.11/kindeditor-all-min.js"></script>-->
<#--<script src="${staticpath}/ueditor/ueditor.config.js"></script>-->
<#--<script src="${staticpath}/ueditor/ueditor.all.min.js"></script>-->
<#--<script src="${staticpath}/ueditor/lang/zh-cn/zh-cn.js"></script>-->
<script>


</script>
<style>
    .sku-group {
        background-color: #fff;
        padding: 10px 10px 10px 10px;
        border: 1px solid #e5e5e5;
    }
    input.tiny {
        height: 18px;
        line-height: 18px;
        padding-left: 5px;
        width: 55px;
        border: 1px #d7d7d7 solid;
        margin: 2px 5px 2px 0;
    }

</style>

<div class="panel panel-default">

    <div class="panel-body" style="overflow-y: scroll;overflow-x: hidden ;height: 600px">

        <!-- Tabs-style-1 -->
        <div class="row">

            <div class="col-lg-12">
                <ul class="nav nav-tabs nav-justified" id="pTab">
                    <li class="active">
                        <a href="#home-1" data-toggle="tab"  aria-expanded="true">
                            <span class="visible-xs"><i class="fa fa-home"></i></span>
                            <span class="hidden-xs">选择商品类型</span>
                        </a>
                    </li>
                    <li class="">
                        <a href="#edit-1" data-toggle="tab" aria-expanded="false">
                            <span class="visible-xs"><i class="fa fa-user"></i></span>
                            <span class="hidden-xs">编辑商品基本信息</span>
                        </a>
                    </li>
                    <li class="">
                        <a href="#edit-2" data-toggle="tab" aria-expanded="false">
                            <span class="visible-xs"><i class="fa fa-envelope-o"></i></span>
                            <span class="hidden-xs">编辑商品详情</span>
                        </a>
                    </li>

                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="home-1">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="col-sm-offset-1 col-sm-10" id="chosebtn">
                                    <button type="button" value="1" class="btn btn-block btn-lg btn-default">宽带新装</button>
                                    <button type="button" value="2" class="btn btn-block btn-lg btn-default">宽带续费</button>
                                    <button type="button" value="3" class="btn btn-block btn-lg btn-default">手机</button>
                                    <button type="button" value="4" class="btn btn-block btn-lg btn-default">上网卡</button>
                                    <button type="button" value="5" class="btn btn-block btn-lg btn-default">手机号</button>
                                    <button type="button" value="6" class="btn btn-block btn-lg btn-default">其他</button>
                                </div>

                                <div class="col-sm-6 col-sm-offset-3 m-t-15">
                                    <button type="button" id="next-edit-1" class="btn btn-block btn--md btn-success">下一步</button>
                                </div>

                            </div>


                        </div>
                    </div>
                    <div class="tab-pane" id="edit-1">

                        <div class="panel panel-default"  >
                            <div class="panel-heading">
                                <h4>基本信息</h4>
                            </div>
                            <div class="panel-body" >

                                <form class="form-horizontal" role="form" id="productBase">
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">商品类型</label>
                                        <div class="col-sm-8">
                                            <input type="hidden" name="type">
                                            <select  name="productType" class="form-control"  disabled>
                                                <option value="1">宽带新装</option>
                                                <option value="2">宽带续费</option>
                                                <option value="3">手机</option>
                                                <option value="4">上网卡</option>
                                                <option value="5">手机号</option>
                                                <option value="6">其他</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group" id="divXf" style="display: none">
                                        <label class="col-md-2 control-label">*续费商品内容</label>
                                        <div class="col-sm-8">
                                            <select class="form-control" name="procontent">
                                                <option value="">选择商品内容</option>
                                                <option value="1">单宽带</option>
                                                <option value="2">宽带+固话</option>
                                                <option value="3">宽带+固话+IPTV</option>
                                                <option value="4">宽带+固话+手机号</option>
                                                <option value="5">宽带+固话+手机+IPTV</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">*商品名称</label>
                                        <div class="col-sm-8">
                                            <input type="hidden" name="id" id="productId" value="${productid}">
                                            <input type="hidden" name="selectArray" id="selectArray" value="">
                                            <input type="text" class="form-control" name="productName" data-rule="商品名称:required;" placeholder="请输入商品名称">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">商品宣传语</label>
                                        <div class="col-md-8">
                                            <textarea class="form-control" rows="5" name="shortDescription" data-rule="商品简介:required;" placeholder="请输入商品宣传语"></textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">商品分类</label>
                                        <div class="col-md-8">
                                            <select id="productClass" name="categoryId" class="form-control">
                                                <option  value="">选择分类</option>
                                            </select>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-md-2 control-label">商品标签</label>
                                        <div class="col-md-8">
                                            <select id="productLabel" name="brandId" class="form-control">
                                                <option value="">商品标签</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label class="control-label col-md-2">添加规格</label>
                                        <div class="col-md-10">
                                            <select multiple="multiple" class="multi-select"  id="select_specs" name="select_specs[]">

                                        </select>
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">商品库存</label>
                                        <div class="col-md-8">
                                            <div style="overflow: auto;">
                                                <table class="table table-small-font table-bordered table-striped">
                                                    <thead id="goodsBaseHead"></thead>
                                                    <!--商品标题模板-->
                                                    <script id="goodsHeadTemplate" type='text/html'>
                                                        <tr>
                                                            <th hidden>商品货号</th>
                                                            <%var isProduct = false;%>
                                                            <%for(var item in templateData){%>
                                                            <%isProduct = true;%>
                                                            <th><%=templateData[item]['name']%></th>
                                                            <%}%>
                                                            <th>库存</th>
                                                            <th>原价(元)</th>
                                                            <th>价格(元)</th>
                                                            <th>月资费(元)</th>
                                                            <th>佣金(元)</th>
                                                            <#--<%if(isProduct == true){%>-->
                                                            <#--<th>操作</th>-->
                                                            <#--<%}%>-->
                                                        </tr>
                                                    </script>

                                                    <tbody id="goodsBaseBody">

                                                    </tbody>

                                                    <!--商品内容模板-->
                                                    <script id="goodsRowTemplate" type="text/html">

                                                        <%var i=0;%>
                                                        <%for(var item in templateData){%>
                                                        <%item = templateData[item]%>
                                                        <tr class='td_c'>
                                                            <td hidden><input class="small" name="_goods_no[<%=i%>]" pattern="required" type="text" value="<%=item['goodsNo'] ? item['goodsNo'] : item['productId']%>" /></td>

                                                            <%var isProduct = false;%>
                                                            <%var specArrayList = parseJSON(item['specArray'])%>
                                                            <%for(var result in specArrayList){%>
                                                            <%result = specArrayList[result]%>
                                                            <input type='hidden' name="_spec_array[<%=i%>][]" value='{"id":"<%=result.id%>","type":"<%=result.type%>","value":"<%=result.value%>","name":"<%=result.name%>"}' />
                                                            <%isProduct = true;%>
                                                            <td>

                                                            <#--<%if(result['type'] == 1){%>-->
                                                                <%=result['value']%>
                                                            <#--<%}else{%>-->
                                                            <#--<img class="img_border" width="30px" height="30px" src="/<%=result['value']%>">-->
                                                            <#--<%}%>-->
                                                            </td>
                                                            <%}%>


                                                            <td><input class="tiny" onchange="countValue(this.value)" name="_storeNums[<%=i%>]" maxlength="9" type="number" min="0" pattern="int"   value="<%=item['storeNums']?item['storeNums']:0%>" "/></td>
                                                            <td><input class="tiny" name="_oldPrice[<%=i%>]" type="number" min="0" pattern="float" maxlength="9" value="<%=item['oldPrice']%>" /></td>
                                                            <td><input class="tiny" name="_sellPrice[<%=i%>]" type="number" min="0" pattern="float" maxlength="9" data-rule="价格:required;" value="<%=item['sellPrice']%>" /></td>
                                                            <td>
                                                                <input class="tiny" name="_monthPrice[<%=i%>]" type="number" min="0" pattern="float" maxlength="9" value="<%=item['monthPrice']%>" />
                                                            <#--<button class="btn" type="button" onclick="memberPrice(this);"><span class="add <%if(item['groupPrice']){%>orange<%}%>">会员组价格</span></button>-->
                                                            </td>
                                                            <td><input class="tiny" name="_commission[<%=i%>]" type="number" min="0" maxlength="9" pattern="float" empty value="<%=item['commission']%>" /></td>
                                                            <#--<%if(isProduct == true){%>-->
                                                            <#--<td><a href="javascript:void(0)" onclick="delProduct(this);"><span class="ion-close-circled" alt="删除" /></a></td>-->
                                                            <#--<%}%>-->
                                                        </tr>
                                                        <%i++;%>
                                                        <%}%>

                                                    </script>

                                                </table>
                                                <table class="table table-small-font table-bordered table-striped">

                                                    <tr><th>批量设置 <a name="setStore"> 库存</a><a name="setOldPrice"> 原价</a><a name="setSellPrice"> 价格</a> <a name="setMonth">月资费 </a><a name="commission"> 佣金</a></th></tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">总库存</label>
                                        <div class="col-sm-8">
                                            <input type="text" name="storeNums" readonly class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">价格前缀</label>
                                        <div class="col-md-8">
                                            <input type="text" name="prestr">
                                        </div>
                                    </div>



                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">商品图片</label>
                                        <div class="col-md-8">
                                            <img name="productImg" class="thumb-lg" src="" alt="200*200">
                                            <a onclick="javascript:$('#head_photo').click()">添加图片</a>
                                            <input type="file" name="head_photo" id="head_photo" style="display: none" accept="image/*">
                                            <input type="hidden" name="img" id="photo_pic" value="">
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">佣金结算</label>
                                        <div class="col-md-8">
                                            <div class="radio-inline">
                                                <label class="cr-styled">
                                                    <input type="radio"  name="toBalance" value="1" checked>
                                                    <i class="fa"></i>
                                                    是
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label class="cr-styled">
                                                    <input type="radio"  name="toBalance" value="0">
                                                    <i class="fa"></i>
                                                    否
                                                </label>
                                            </div>

                                        </div>

                                    </div>




                                    <div class="form-group">
                                        <label class="col-md-2 control-label">运费设置</label>
                                        <div class="col-md-8">
                                            <div class="radio">
                                                <label class="cr-styled col-md-3" for="example-radio1">
                                                    <input type="radio" id="freight-radio" checked >
                                                    <i class="fa"></i>
                                                    统一运费
                                                </label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-rmb"></i></span>
                                                    <input type="number" min="0" max="99999" name="freight"  class="form-control" placeholder="..">
                                                    <span class="input-group-addon">.00</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">发票设置</label>
                                        <div class="col-md-8">
                                            <div class="radio-inline">
                                                <label class="cr-styled">
                                                    <input type="radio"  name="receipt" value="1">
                                                    <i class="fa"></i>
                                                    开发票，快递费:
                                                </label>
                                                <input name="receiptPrice" type="number" min="0" max="99999" placeholder="￥0.00" disabled>
                                            </div>
                                        <#--<div class="col-md-4">-->
                                        <#--<label class="col-md-1 control-label">运费</label>-->
                                        <#--<input name="receiptPrice">-->
                                        <#--</div>-->
                                            <div class="radio-inline">
                                                <label class="cr-styled">
                                                    <input type="radio"  name="receipt" value="0" checked>
                                                    <i class="fa"></i>
                                                    不开发票
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">流程选择</label>
                                        <div class="col-md-8">
                                            <select name="processid" class="form-control">
                                                <option value="">选择流程</option>
                                            </select>
                                        </div>
                                        <a name="a_addProcess">添加流程</a>
                                    </div>
                                    <div class="col-sm-6 col-sm-offset-3 m-t-15">
                                        <button type="button" id="next-edit-2" class="btn btn-block btn--md btn-success">下一步</button>
                                    </div>
                                </form>

                            </div>
                        </div>  <#--panel end -->


                    </div>
                    <div class="tab-pane " id="edit-2" >
                        <div class="row">
                            <div class="col-sm-12" >
                                <div class="panel panel-default">
                                    <div class="panel-body" >
                                        <div class="row" >
                                            <div class="col-sm-6">
                                                <div class="dcr-shower" data-dpr="1" onselectstart="return false;" unselectable="on">
                                                    <div class="dcr-phone" id="dcr-phone" dcr-view-init="" style="background: rgb(238, 238, 238);margin:10px auto;">
                                                        <div class="dcr-phone-head">
                                                            <img src="${basepath}/static/img/microshop/phone_head.jpg" alt="">
                                                            <input type="hidden" id="customPageId" value="">
                                                            <p class="dcr-phone-title" id="dcrPageTitle" way-data="title">商品详情</p>
                                                        <#--<a class="dcr-title-btn" data-for-gaq="新建自定义页面-设置-设置" onclick="editTitlePage()">设置</a>-->

                                                            <div class="dcr-title-edit"></div>
                                                        </div>
                                                    <#--预览内容-->
                                                        <div class="dcr-phone-body" style="width: 320px;display:block;word-break: break-all;word-wrap: break-word;overflow-x: hidden;: hide" id="cpcDcrPreview">
                                                        </div>



                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="summernote"></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6 col-sm-offset-3 m-t-15">
                                <button type="button" id="finish" class="btn btn-block btn--md btn-success">发布商品</button>
                            </div>
                        </div>
                        </div>

                    </div>
                    <br>
                </div>
            </div> <!-- End row -->
        </div>

    </div>

    <div id="con-close-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">流程设置</h4>
                </div>
                <div class="modal-body">
                    <form id="processForm">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label  class="control-label col-md-12">流程名称</label>
                                    <div class="col-md-8">
                                        <input type="text" name="name" id="processName" data-rule="流程:required;remote[${basepath}/manage/product/process/unique]"  class="form-control" >
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label  class="control-label col-md-12">选择新号码</label>
                                    <div class="col-md-8">
                                        <select name="newnumId" class="form-control"></select>
                                    </div>
                                    <label  class="control-label col-md-4"><a  href= "javascript: goToPage('data/number')">新建号码库</a></label>
                                </div>
                            </div>
                            <div class="col-md-12 m-t-5">
                                <div class="form-group">
                                    <label class="control-label col-md-3">可选号码数量</label>
                                    <div class="col-md-5">
                                        <input type="number" name="newnums" disabled="disabled" min="1" class="form-control" >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label  class="control-label col-md-12">老用户校验</label>
                                    <div class="col-md-8">
                                        <select name="oldnumId" class="form-control"></select>
                                    </div>
                                    <label  class="control-label col-md-4"><a href= "javascript: goToPage('data/number')">新建号码库</a></label>
                                </div>
                            </div>
                        </div>

                        <div class="row  m-t-5">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label  class="control-label col-md-12">选择套餐</label>
                                    <div class="col-md-8">
                                        <select name="packageid" class="form-control"></select>
                                    </div>
                                    <label  class="control-label col-md-4"><a  href= "javascript: goToPage('product/package')" >新建合约套餐</a></label>
                                </div>
                            </div>
                        </div>

                        <div class="row m-t-10">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label  class="control-label col-md-3">身份证信息</label>
                                    <div class="col-md-8">

                                        <div class="checkbox-inline">
                                            <label class="cr-styled">
                                                <input type="checkbox" name="upidcard">
                                                <i class="fa"></i>
                                                上传身份证信息
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row  m-t-5">
                            <div class="col-md-12">
                                <label class="control-label col-md-12">用户填写信息</label>
                            </div>
                            <div class="col-md-3">

                                <div class="checkbox-inline">
                                    <label class="cr-styled">
                                        <input type="checkbox" name="username">
                                        <i class="fa"></i>
                                        机主姓名
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-3">

                                <div class="checkbox-inline">
                                    <label class="cr-styled" >
                                        <input type="checkbox" name="usertel">
                                        <i class="fa"></i>
                                        联系电话
                                    </label>
                                </div>
                            </div>

                            <div class="col-md-3">

                                <div class="checkbox-inline">
                                    <label class="cr-styled">
                                        <input type="checkbox" name="userid">
                                        <i class="fa"></i>
                                        身份证号
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-3">

                                <div class="checkbox-inline">
                                    <label class="cr-styled">
                                        <input type="checkbox" name="useraddr">
                                        <i class="fa"></i>
                                        详细地址
                                    </label>
                                </div>
                            </div>

                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-info">保存</button>
                </div>
            </div>
        </div>
    </div><!-- /.modal -->


    <script src="${basepath}/static/js/product/product.js"></script>
<script>

    //默认货号（由服务端生产返回）
    var defaultProductNo = 'SD'+${systime};


        $().ready(function() {

            initForm();
            // 初始化表格
            initProductTable();
            // 初始化下来列表
            initSelect();

            // 初始化编辑信息
            initEditInfo();

            initClassAndlabel();



            $('.summernote').summernote({
                lang : 'zh-CN',
                height: 500,                 // set editor height

                minHeight: null,             // set minimum height of editor
                maxHeight: null,             // set maximum height of editor

                focus: true                 // set focus to editable area after initializing summernote
                ,onChange:function(contents,$editable){
                    console.log('onchange:',contents);
                    $('#cpcDcrPreview').html(contents);
                }
               , onImageUpload: function(files,editor,editable) {

                    sendFile(files[0],editor,editable);

                 }
            });




        });

    function sendFile(file,editor,editable){
        console.log(file);
        var formData = new FormData();
        formData.append("file",file);
        console.log(formData);
        $.ajax({
            data:formData,
            type: "POST",
            url:"/wkshop/uploadProductImg",
            cache: false,
            contentType: false, //必须
            processData: false, //必须
            success:function(data){
//                $(".summernote").summernote('insertImage', url, 'image name'); // the insertImage API
                editor.insertImage(editable, data.fileName);
            }
        });
    }


   function goToPage(page){
       $('#con-close-modal').modal('toggle');
       setTimeout( function (){
           $('.wraper').load('/wkshop/manage/'+page+'/selectList')
       },500)
    }

    </script>




<#--</@page.pageBase>-->
    <link href="${basepath}/static/assets/notifications/notification.css" rel="stylesheet" />
    <script src="${basepath}/static/assets/notifications/notify.min.js"></script>
    <script src="${basepath}/static/assets/notifications/notify-metro.js"></script>
    <script src="${basepath}/static/assets/notifications/notifications.js"></script>

