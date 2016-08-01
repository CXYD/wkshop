<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="通用设置">-->
<div class="row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h5>购买商品设置</h5>
            </div>

            <div class="panel-body">

                <form id="form_buy" class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-md-3 control-label">销售及成交记录：</label>
                    <div class="col-md-9">
                        <div class="radio-inline col-md-4">
                            <label class="cr-styled" for="radio1">
                                <input type="radio" id="radio1" name="record" <#if ('${buySet.record}'=='ON')> checked </#if>  value="ON"  >
                                <i class="fa"></i>
                                开启
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label class="cr-styled" for="radio2">
                                <input type="radio" id="radio2" name="record"  <#if ('${buySet.record}'=='OFF')> checked </#if> value="OFF">
                                <i class="fa"></i>
                                关闭
                            </label>
                        </div>
                    </div>
                </div>
                <#--<div class="form-group">-->
                    <#--<label class="col-md-3 control-label">商品评价：</label>-->
                    <#--<div class="col-md-9">-->
                        <#--<div class="radio-inline col-md-4">-->
                            <#--<label class="cr-styled" for="radio3">-->
                                <#--<input type="radio" id="radio3" name="feedback" <#if ('${buySet.feedback}'=='ON')> checked </#if> value="option1">-->
                                <#--<i class="fa"></i>-->
                                <#--开启买家评价-->
                            <#--</label>-->
                        <#--</div>-->
                        <#--<div class="radio-inline">-->
                            <#--<label class="cr-styled" for="radio4">-->
                                <#--<input type="radio" id="radio4" name="feedback" <#if ('${buySet.feedback}'=='OFF')> checked </#if> value="option2">-->
                                <#--<i class="fa"></i>-->
                                <#--关闭买家评价-->
                            <#--</label>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                    <div class="form-group">
                        <label class="col-md-3 control-label">更多商品推荐：</label>
                        <div class="col-md-9">
                            <div class="radio-inline col-md-4">
                                <label class="cr-styled" for="radio5">
                                    <input type="radio" id="radio5" name="moreproduct" <#if ('${buySet.moreproduct}'=='ON')> checked </#if> value="ON">
                                    <i class="fa"></i>
                                    开启
                                </label>
                            </div>
                            <div class="radio-inline">
                                <label class="cr-styled" for="radio6">
                                    <input type="radio" id="radio6" name="moreproduct" <#if ('${buySet.moreproduct}'=='OFF')> checked </#if> value="OFF">
                                    <i class="fa"></i>
                                    关闭
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">商品详情页联系商家：</label>
                        <div class="col-md-9">
                            <div class="radio-inline col-md-4">
                                <label class="cr-styled" for="radio7">
                                    <input type="radio" id="radio7" name="detail" <#if ('${buySet.detail}'=='ON')> checked </#if> value="ON">
                                    <i class="fa"></i>
                                    开启
                                </label>
                            </div>
                            <div class="radio-inline">
                                <label class="cr-styled" for="radio8">
                                    <input type="radio" id="radio8" name="detail" <#if ('${buySet.detail}'=='OFF')> checked </#if> value="OFF">
                                    <i class="fa"></i>
                                    关闭
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">购物门槛：</label>
                        <div class="col-md-9">
                            <div class="radio-inline col-md-4">
                                <label class="cr-styled" for="radio9">
                                    <input type="radio" id="radio9" name="loginbuy" <#if ('${buySet.loginbuy}'=='ON')> checked </#if> value="ON">
                                    <i class="fa"></i>
                                    登录后购买
                                </label>
                            </div>
                            <div class="radio-inline">
                                <label class="cr-styled" for="radio10">
                                    <input type="radio" id="radio10" name="loginbuy" <#if ('${buySet.loginbuy}'=='OFF')> checked </#if> value="OFF">
                                    <i class="fa"></i>
                                    无需登录购买
                                </label>
                            </div>
                        </div>
                    </div>
                </form>

            </div> <!-- panel-body -->

        </div> <!-- panel -->

        <div class="panel">

            <div class="panel-heading">
                <h4>宽带续费业务设置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <#--<font name="font" size="2" color="red">注:商城服务区有维挽数据的需要设置到期时间和推荐商品,如无,则隐藏.</font>-->
                </h4>
            </div>

            <div class="panel-body">


                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">续费链接</label>
                        <div class="col-sm-6">
                            <input id="" name="" type="text" class="" >
                            <div id="bt_copyLine" class="btn btn-success" >复制链接</div>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">办理时间：</label>
                        <select>
                            <option value="1">一个月内到期</option>
                            <option value="1">二个月内到期</option>
                            <option value="1">三个月内到期</option>
                            <option value="1">四个月内到期</option>
                        </select>

                    </div>

                    <h5>自定义商品设置：
                        <font name="font" size="2" color="red">当推荐产品不符合需求、维挽数据没有查询到用户信息、没有维挽数据时,前台点击续费,显示自定义商品</font>
                    </h5>

                    <div class="form-group">
                        <label class="col-md-2 control-label">单宽带商品：</label>
                        <select>
                            <option value="1">请选择商品</option>
                            <#list dkd as dkd>
                                <option>${dkd}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">宽带+固话商品：</label>
                        <select>
                            <option value="1">请选择商品</option>
                            <option value="1">1</option>
                            <option value="1">2</option>
                            <option value="1">3</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">宽带+4G号：</label>
                        <select>
                            <option value="1">请选择商品</option>
                            <option value="1">1</option>
                            <option value="1">2</option>
                            <option value="1">3</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">宽带+固话+4G：</label>
                        <select>
                            <option value="1">请选择商品</option>
                            <option value="1">1</option>
                            <option value="1">2</option>
                            <option value="1">3</option>
                        </select>
                    </div>

                </form>

            </div> <!-- panel body-->
        </div>


        <div class="panel">
            <div class="panel-heading">
                <h5>推荐商品设置</h5>
            </div>

            <div class="panel-body">

                <p>推荐续费商品：为了更精准地向用户推荐续费商品，请您根据以下用户特征进行配置，前台会优先向符合条件的用户推荐该商品，如推荐商品为空，则会向该用户推荐默认商品。</p>
                <br>

                    <div class="form-group">
                        <form id="form_params" class="form-horizontal" role="form">
                            <label class="col-sm-1">套餐名称</label>
                            <div class="col-sm-2">
                                <input name="T_0206" type="text" class="form-control" >
                            </div>

                            <label class="col-sm-1">套餐资费</label>
                            <div class="col-sm-2">
                                <input name="zifei" type="text" class="form-control" >
                            </div>

                            <label class="col-sm-1">推荐商品</label>
                            <div class="col-sm-2">
                                <input name="tuijian" type="text" class="form-control">
                            </div>
                            <input id="pageNo" name="PageNo" type="hidden" value="">
                            <input id="sort" name="sort" type="hidden" value="">
                        </form>
                            <button id="bt_so" type="button" class="btn btn-success m-l-10">检索</button>
                    </div>



                        <p>排序：<button name="sort_param" id="userNum DESC" class="btn btn-success btn-sm m-b-5">用户数</button>
                            <button name="sort_param" id="T_0206" class="btn btn-success btn-sm m-b-5">套餐名称</button>
                            <button name="sort_param" id="ID" class="btn btn-success btn-sm m-b-5">套餐资费</button>
                            <button name="sort_param" id="T_0202" class="btn btn-success btn-sm m-b-5">促销</button>
                            </p>
                <form id="form_tj" role="form" class="form-horizontal">

                    <table class="table table-bordered" style="text-align: center">
                    <thead style="text-align: center">
                    　<tr role="row" style="background-color: #dff0d8;text-align: center">
                        <th colspan="5" style="text-align: center">用户特征</th>
                        <th rowspan="2" style="text-align: center">推荐<br>商品</th>
                        <th rowspan="2" style="text-align: center">关联<br>商品</th>
                        <th rowspan="2" style="text-align: center">操作<br></th>
                    </tr>
                    <tr role="row" style="background-color: #dff0d8">
                        <th style="text-align: center">序号</th>
                        <th style="text-align: center">套餐名称</th>
                        <th style="text-align: center">套餐资费</th>
                        <th style="text-align: center">促销</th>
                        <th style="text-align: center">用户数</th>
                    </tr>
                    </thead>
                    <tbody id="tbody">

                    </tbody>
                    </table>
                </form>
                <div id="xianye"></div>

                <div class="pull-right">
                    <button class="-align-right btn btn-success" id="previous">上一页</button>
                    <span id="span"></span>
                    <button class="-align-right btn btn-success" id="next">下一页</button>
                </div>


            </div> <!--panel body -->

        <div class="panel">
            <div class="panel-body">
                <div class="col-sm-offset-4 col-sm-3">
                    <button id="bt_save" type="button" class="btn btn-block btn--md btn-success">保存</button>
                </div>
            </div>

        </div>


    </div>


        <#--<label class="col-sm-12">注：</label>-->
        <#--<label class="col-sm-12">1.用户特征数据根据维挽数据中的套餐名称ID、套餐资费ID、促销字段排重得出，并需计算出每条记录的用户数，</label>-->
        <#--<label class="col-sm-12">2.需根据维挽数据每月月初计算数据，并与之前数据对比，如有新数据时，默认置顶标红，提醒用户设置，如有删除数据则删除，前台不予显示。</label>-->
        <#--<label class="col-sm-12">3.维挽数据以省为单位提供，当判断商城服务范围为市时，则仅显示该市的数据。</label>-->


    </div> <!-- col -->
</div> <!-- End row -->

<div id="tagter_edit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">

            <div class="modal-content">
                <div class="modal-header">
                    <button name="bt_shuaxin" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">编辑推荐商品</h4>
                </div>
                <div class="modal-body">

                    <div class="row">
                        <form id="from_save_tj" class="form-horizontal">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-label">推荐商品</label>
                                <div class="col-md-8">
                                    <input id="id_ID" name="ID" type="hidden" value="">
                                    <input type="text" id="id_tj" name="tuijian" value="" class="form-control"  data-rule="推荐商品:required"  placeholder="请输入推荐商品...">
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-label">关联商品</label>
                                <div class="col-md-8">
                                    <input type="text" id="id_gl" name="guanlian" value="" class="form-control" placeholder="请输入关联商品号...">
                                </div>
                            </div>
                        </form>

                    </div>

                    <div class="modal-footer">
                        <button name="bt_shuaxin" type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                        <button id="bt_save_tj" type="submit" method="insert" class="btn btn-info">确定</button>
                    </div>
                </div>

            </div>

    </div>

</div><!-- /.modal -->

<script type="text/javascript" src="${basepath}/static/js/main/ZeroClipboard.js"></script>
<script src="${basepath}/static/assets/notifications/notify.min.js"></script>
<script src="${basepath}/static/assets/notifications/notify-metro.js"></script>
<script src="${basepath}/static/assets/notifications/notifications.js"></script>

<script>

    $(function () {

        WeiWan();

        $("#previous").click(function () {
            var num=$("#pageNo").val()-1;
            $("#pageNo").val(num);
            WeiWan();
        });
        $("#next").click(function () {
            var num=parseInt($("#pageNo").val())+1;
            $("#pageNo").val(num);
            WeiWan();
        });
        $("#bt_so").click(function () {
            WeiWan();
        });
        $("button[name='sort_param']").click(function () {
            $("button[name='sort_param']").attr("class","btn btn-success btn-sm m-b-5");
            var sort=$(this).attr("id");
            $("#sort").val(sort);
            $(this).attr("class","btn btn-danger btn-sm m-b-5");
            WeiWan();
        });
        $("button[name='bt_shuaxin']").click(function () {
            WeiWan();
        });

        $("#bt_save").click(function(){
            var flag = true;
            $.ajax({
                url:"${basepath}/manage/generalSet/buySet",
                data:$("#form_buy").serialize(),
                async:false,
                success:function () {
                    swal("成功","保存成功","success");
                },
                error:function(request){
                    swal("","商品设置添加失败","error");
                    flag=false;
                }
            });

        });

        $("#bt_save_tj").click(function () {
            $.ajax({
                url:"${basepath}/manage/generalSet/upTuiJian",
                data:$("#from_save_tj").serialize(),
                async:false,
                success:function (msg) {
                    swal("","保存成功","success");
                },
                error:function(request){
                    swal("","商品推荐添加失败","error");
                }
            });

        });

        ZeroClipboard.setMoviePath( "${basepath}/manage/shop/shopInfo/ZeroClipboard.swf" );

        var clip = new ZeroClipboard.Client(); // 新建一个对象

        clip.setHandCursor( true ); // 设置鼠标为手型

        clip.addEventListener('complete',copySuc);

        clip.setText("124123123"); // 设置要复制的文本。

        // 注册一个 button，参数为 id。点击这个 button 就会复制。
        //这个 button 不一定要求是一个 input 按钮，也可以是其他 DOM 元素。
        clip.glue("bt_copyLine"); // 和上一句位置不可调换


    });


    function copySuc(){
        $.Notification.autoHideNotify('success', 'top center', '提示','复制成功.');
    }

    function WeiWan() {
        $.ajax({
            url:'${basepath}/manage/generalSet/getWeiWan',
            data:$("#form_params").serialize(),
            async:false,
            success:function(data){
                $("#tbody").empty();
                $("#xianye").empty();
                $("#span").empty();
                console.log(data);
                if(data.length!=0){
                    for ( var i = 0; i < data.list.length; i++) {
                        var id=data.list[i].id;
                        var name=data.list[i].t_0206;
                        var userNum=data.list[i].userNum;
                        var tuijian=data.list[i].tuijian;
                        var guanlian=data.list[i].guanlian;
                        $("#tbody").append('<tr role="row"> <td>'+id+'</td> <td>'+name+'</td> <td>300元</td> <td>促销</td> <td>'+userNum+'</td> <td>'+tuijian+'</td> <td>'+guanlian+'</td> <td><button name="bt_edit" type="button" class="btn btn-success btn-sm m-b-3" data-toggle="modal" data-target="#tagter_edit" onclick="tjEdit(this)">  编辑 </button></td> </tr>');

                    }
                }
                var currentPage=data.currentPage;
                $("#pageNo").val(currentPage);
                var startRecord=data.startRecord;
                var endRecord=data.endRecord;
                var count=data.count;
                var pageCount=data.pageCount;

                $("#xianye").append('<div class="panel-heading"> <h5>当前第'+currentPage+'页,显示第 '+(startRecord+1)+' 至 '+(endRecord+1)+' 项结果,共 '+count+' 条数据</h5> </div>');
                var firstPageNum=data.firstPageNum;
                var lastPageNum=data.lastPageNum;
                if(firstPageNum>1){
                    $("#span").append('<button class="-align-right btn btn-default" name="yema" onclick="changepage(this)">1</button>');
                }
                if(firstPageNum>2){
                    $("#span").append('<button class="-align-right btn btn-default" name="dian">...</button>');
                }
                for(var i=firstPageNum;i<=lastPageNum;i++){
                    $("#span").append('<button class="-align-right btn btn-default" name="yema" onclick="changepage(this)">'+i+'</button>');
                }
                if(lastPageNum<pageCount-1){
                    $("#span").append('<button class="-align-right btn btn-default" name="dian">...</button>');
                }
                if(lastPageNum<pageCount){
                    $("#span").append('<button class="-align-right btn btn-default" name="yema" onclick="changepage(this)">'+pageCount+'</button>');
                }
                $("button[name='yema']").each(function () {
                    if($(this).text()==currentPage){
                        $(this).attr("class","-align-right btn btn-danger")
                    }
                });

            },
        });

    }
    function changepage(t) {
        var currentPage=t.innerHTML;
        $("#pageNo").val(currentPage);
        WeiWan();
    }
    function tjEdit(t) {
        var ID=t.parentNode.parentNode.childNodes[1].innerHTML;
        var tuijian=t.parentNode.parentNode.childNodes[11].innerHTML;
        var guanlian=t.parentNode.parentNode.childNodes[13].innerHTML;
        $("#id_ID").val(ID);
        $("#id_tj").val(tuijian);
        $("#id_gl").val(guanlian);
    }


</script>

<#--</@page.pageBase>-->