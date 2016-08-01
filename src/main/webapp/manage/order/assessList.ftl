<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="评价管理">-->

                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-body">

                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <div class="col-sm-12">

                                            <label class="control-label col-sm-1" >评价时间</label>
                                            <div class="col-sm-2">
                                                <input type="date" class="form-control" id="exampleInputPassword2" placeholder="">
                                            </div>
                                            <label class="form-control-static col-sm-1 m-l-15">
                                                ——
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="date" class="form-control" >
                                            </div>

                                            <label class="control-label col-sm-1" >评价类型</label>
                                            <div class="col-sm-2">
                                                <select class="inline">
                                                    <option>全部</option>
                                                    <option>好评</option>
                                                    <option>中评</option>
                                                    <option>差评</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-offset-1">
                                                <button class="btn btn-primary">检索</button>
                                            </div>
                                        </div>

                                    </div>

                                </form>
                            </div> <!-- panel-body -->
                        </div> <!-- panel -->
                    </div> <!-- col -->

                </div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                            <div class="row">
                                <div class="col-sm-12">
                                    <table id="datatable" class="table table-bordered table-condensed table-hover" role="grid" aria-describedby="datatable_info">
                                        <thead>
                                        <tr role="row" style="background-color: #dff0d8">
                                            <th width="20"><input type="checkbox" id="firstCheckbox" /></th>
                                            <th  nowrap="nowrap" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >商品名称</th>
                                            <th  nowrap="nowrap" tabindex="1" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >订单号</th>
                                            <th  nowrap="nowrap" tabindex="2" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >评价时间</th>
                                            <th  nowrap="nowrap" tabindex="3" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >用户账号</th>
                                            <th  nowrap="nowrap" tabindex="4" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >评价类型</th>
                                            <th  nowrap="nowrap"  tabindex="5" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >评价内容</th>
                                            <th  nowrap="nowrap" tabindex="6" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>


                                        <tr role="row" class="odd">
                                            <td><input type="checkbox" name="ids"
                                                       value="" /></td>
                                            <td nowrap="nowrap">20M沃家庭A套餐包一年（郊区）</td>
                                            <td>111111111</td>
                                            <td nowrap="nowrap">2016-05-10 14:20:20</td>
                                            <td>admin</td>
                                            <td>方式</td>
                                            <td nowrap="nowrap">是的撒打算飞洒发撒发生法萨芬是发的说法是飞洒</td>
                                            <td>
                                                <button method="deletes" class="btn btn-danger" onclick="return false">
                                                     删除
                                                </button>
                                            </td>
                                        </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="datatable_info" role="status" aria-live="polite">共3条，每页10条</div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="dataTables_paginate paging_simple_numbers pull-right" id="datatable_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="datatable" tabindex="0" id="datatable_previous">
                                                <a href="#">上页</a>
                                            </li>
                                            <li class="paginate_button active" aria-controls="datatable" tabindex="0">
                                                <a href="#">1</a>
                                            </li>
                                            <li class="paginate_button " aria-controls="datatable" tabindex="0">
                                                <a href="#">2</a>
                                            </li>
                                            <li class="paginate_button " aria-controls="datatable" tabindex="0">
                                                <a href="#">3</a>
                                            </li>
                                            <li class="paginate_button " aria-controls="datatable" tabindex="0">
                                                <a href="#">4</a>
                                            </li>
                                            <li class="paginate_button " aria-controls="datatable" tabindex="0">
                                                <a href="#">5</a>
                                            </li>
                                            <li class="paginate_button " aria-controls="datatable" tabindex="0">
                                                <a href="#">6</a>
                                            </li>
                                            <li class="paginate_button next" aria-controls="datatable" tabindex="0" id="datatable_next">
                                                <a href="#">下页</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
<script>
    // 操作
    $(".btn.btn-danger").click(
            function(e){
                console.log("操作...");

                var yes = swal({
                    title: "提示",
                    text: "确定要删除？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                }, function (flag) {
                    if (flag) {
                        //提交后台删除
//                createMark();
//           var _form = $("form");
//            _form.attr("action", $(obj).attr("method"));
//            _form.submit();
                        swal("提示!", "操作成功.", "success");
                    }
                });
            });
</script>
<#--</@page.pageBase>-->