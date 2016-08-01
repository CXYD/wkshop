<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="物流工具">-->
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">

            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_length" id="datatable_length">
                                        <div class="m-b-30">
                                            <button type="button" class="btn btn-success">添加模板</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <table id="datatable" class="table table-striped  dataTable no-footer" role="grid" aria-describedby="datatable_info">
                                        <thead>
                                        <tr role="row">
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >模板名称</th>
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >配送区域</th>
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >运费</th>
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1" aria-label="" >操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>


                                        <tr role="row" class="even">

                                            <td>北京配送</td>
                                            <td class="sorting_1">北京市</td>
                                            <td>2.00</td>
                                            <td>
                                                <button class="btn btn-warning" onclick="return false">
                                                    <i class="icon-edit icon-white"></i>编辑
                                                </button>
                                                <button method="deletes" class="btn btn-danger" onclick="return false">
                                                    <i class="icon-remove-sign icon-white"></i> 删除
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
            </div>
        </div>
    </div>

</div>

<#--</@page.pageBase>-->