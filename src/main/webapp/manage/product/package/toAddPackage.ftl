<style>
    .sku-group {
        background-color: #fff;
        padding: 10px 10px 10px 10px;
        border: 1px solid #e5e5e5;
    }
    .tiny {
        height: 22px;
        line-height: 18px;
        padding-left: 5px;
        width: 85px;
        border: 1px #d7d7d7 solid;
        margin: 2px 5px 2px 0;
    }

</style>
<div class="panel panel-default">
    <div class="panel-heading"><h4>编辑套餐</h4></div>

    <div class="panel-body">
        <form class="form-horizontal" id="packageForm">
            <div class="form-group">
                <label class="control-label col-sm-2">*套餐名称</label>
                <div class="col-sm-8">
                    <input class="form-control col-sm-8" name="name" required type="text">
                    <input type="hidden" name="packageId" value="${packageId}">
                    <input type="hidden" id="selectArray" name="selectArray" value="">
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-sm-2">*合约期限</label>
                <div class="col-sm-10">
                    <#--<label class="tiny">12个月<button type="button" class="close" onclick="delMe()" aria-hidden="true">×</button></label>-->
                    <div class="btn btn-default btn-xs" id="btnAddMonth"><i class="fa fa-plus"></i>添加合约期限</div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">*套餐资费</label>
                <div class="col-sm-10">
                    <#--<label class="tiny">106元/月<button type="button" class="close" onclick="delMe()" aria-hidden="true">×</button></label>-->
                    <div class="btn btn-default btn-xs" id="btnAddFee"><i class="fa fa-plus"></i>添加套餐资费</div>
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-sm-2">*资费说明</label>
                <div class="col-sm-10">
                    <table class="table table-bordered" style="text-align: center">
                        <thead style="text-align: center">
                        　<tr role="row" style="background-color: #dff0d8;text-align: center">
                            <th rowspan="2" style="text-align: center">合约期限</th>
                            <th rowspan="2" style="text-align: center">套餐资费</th>
                            <th colspan="6" style="text-align: center">资费说明</th>
                        </tr>
                        <tr role="row" style="background-color: #dff0d8">
                            <th style="text-align: center">合约期</th>
                            <th style="text-align: center">赠送话费</th>
                            <th style="text-align: center">返还话费规则</th>
                            <th style="text-align: center">通话时长(分钟)</th>
                            <th style="text-align: center">短信条数</th>
                            <th style="text-align: center">网络流量</th>
                        </tr>
                        </thead>
                        <tbody id="packageBaseBody">

                        </tbody>

                        <!--商品内容模板-->
                        <script id="packageRowTemplate" type="text/html">

                            <%var i=0;%>
                            <%for(var item in templateData){%>
                            <%item = templateData[item]%>
                            <tr class='td_c'>
                                <td hidden><input class="small" name="_spec_no[<%=i%>]" pattern="required" type="text" value="<%=item['package_no']%>" /></td>

                                <%var isProduct = false;%>
                                <%var specArrayList = parseJSON(item['specArray'])%>
                                <%for(var result in specArrayList){%>
                                <%result = specArrayList[result]%>
                                <input type='hidden' name="_spec_array[<%=i%>][]" value='{"id":"<%=result.id%>","type":"<%=result.type%>","value":"<%=result.value%>","name":"<%=result.name%>"}' />
                                <%isProduct = true;%>
                                <td>
                                    <%=result['value']%>
                                </td>
                                <%}%>
                                <td><input class="tiny" name="_month[<%=i%>]" type="text" required value="<%=item['month']%>" /></td>
                                <td><input class="tiny" name="_zeng[<%=i%>]" type="text"   value="<%=item['zeng']%>" /></td>
                                <td><input class="tiny" name="_returns[<%=i%>]" type="text"   value="<%=item['returns']%>" /></td>
                                <td><input class="tiny" name="_times[<%=i%>]" type="text"   value="<%=item['times']%>" /></td>
                                <td><input class="tiny" name="_sms[<%=i%>]" type="text"   value="<%=item['sms']%>" /></td>
                                <td><input class="tiny" name="_folw[<%=i%>]" type="text"  value="<%=item['folw']%>" /></td>

                                <#--<%if(isProduct == true){%>-->
                                <#--<td><a href="javascript:void(0)" onclick="delPackage(this);"><span class="ion-close-circled" alt="删除" /></a></td>-->
                                <#--<%}%>-->
                            </tr>
                            <%i++;%>
                            <%}%>

                        </script>

                    </table>
                </div>
            </div>


        <#--<div class="form-group">-->
                <#--<label class="control-label col-sm-2">导入查套餐</label>-->
                <#--<div class="col-sm-8">-->
                    <#--<input type="file" class="from-control">-->
                <#--</div>-->
            <#--</div>-->


            <div class="form-group">
                <label class="control-label col-sm-2" >备注信息</label>
                <div class="col-sm-8">
                    <textarea class="form-control" name="note"  rows="5"></textarea>
                </div>
            </div>



            <div class="col-sm-3 col-sm-offset-4 m-t-15">
                <button type="button" id="finish" class="btn btn-block btn--md btn-success">保存</button>
            </div>

        </form>



    </div>
</div>

<script>
    var defaultProductNo = 'SD'+${systime};
</script>

<script src="${basepath}/static/js/product/package.js">
<script src="${basepath}/static/js/jquery.validate.min.js"></script>
<script src="${basepath}/static/js//messages_zh.js"></script>
<script>
    $.validator.setDefaults({
        submitHandler: function() {
//            alert("提交事件!");
        }
    });
    $().ready(function() {
        $("#packageForm").validate();
        initSpecJson();
        initEditInfo();
    });


<#--</script>-->