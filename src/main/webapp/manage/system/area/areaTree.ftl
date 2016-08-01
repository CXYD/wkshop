<#--<#import "/manage/tpl/pageBase.ftl" as page>-->
<#--<@page.pageBase currentMenu="区域管理">-->

<script type="text/javascript">

    function changeCitys(pid) {
        changeData(pid, '#city', 'changeAreas');
        $('#area').html('');
    }
    function changeAreas(pid) {
        changeData(pid, '#area', 'void');
    }
    function changeData(pid, div, func) 
    {
        $.post('loadAreasByPid?pid='+pid, function(json)
        {
            json = eval('(' + json + ')');

            var htmlCity = '';
            if (json.length != 0) {
                for(var i=0,l=json.length;i<l;i++){
                    var item = json[i];
                    htmlCity += '<tr><td align="center">';
                    htmlCity += '<a href="javascript:'+func+'('+item.id+');">'+item.name+'</a>&nbsp;&nbsp;';
                    htmlCity += '&nbsp;&nbsp;<a class="glyphicon glyphicon-edit" href="${basepath}/manage/area/toEdit?id='+item.id+'"></a>';
                    htmlCity += '&nbsp;&nbsp;<a class="glyphicon glyphicon-remove-circle" href="javascript:del('+item.id+')"></a>';
                    htmlCity += '</td></tr>';
                }
            }
            htmlCity += '<tr><td align="center"><a style="color:#ff4500" href="toAdd?pid='+pid+'">新增</a></td></td>';

            $(div).html(htmlCity);
        });
    }

    function del(id) 
    {
        $('#_form').attr("action", '${basepath}/manage/area/deletes?ids='+id);
        $('#_form').submit();
    }

</script>

<form id="_form" method="post" action="${basepath}/manage/area/deletes">
    <div>
        <span class="col-lg-4" style="text-align:center">省份</span>
        <span class="col-lg-4" style="text-align:center">城市</span>
        <span class="col-lg-4" style="text-align:center">区域</span>
    </div>

    <table id="province" class="table-bordered table-condensed col-lg-4">
        <#list provinces as item>
            <tr>
                <td align="center">
                    <a href="javascript:changeCitys(${item.id});">${item.name}</a>
                    &nbsp;&nbsp;<a class="glyphicon glyphicon-edit" href="${basepath}/manage/area/toEdit?id=${item.id}"></a>
                    &nbsp;&nbsp;
                    <a class="glyphicon glyphicon-remove-circle" href="javascript:del(${item.id});"></a>
                </td>
            </tr>
        </#list>
        <tr><td align="center"><a style="color:#ff4500" href="toAdd?pid=0">新增</a></td></td></tr>
    </table>

    <table id="city" class="table-bordered table-condensed col-lg-4">
    </table>

    <table id="area" class="table-bordered table-condensed col-lg-4">
    </table>
</form>

<#--</@page.pageBase>-->