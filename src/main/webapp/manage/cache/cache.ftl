<#import "/manage/tpl/pageBase.ftl" as page/>
<@page.pageBase currentMenu="缓存管理">
<script>
    $(function () {
        var path = "${basepath}/manage/cache";

        function responseProcess(data) {
            if (data.code = "00") {
                alert("缓存加载成功!");
            } else {
                alert("加载缓存失败\n错误码:" + data.code + "\n错误信息:" + (data.msg || "失败"));
            }
        }

        //后台缓存
        $("#btnLoadManageCache").click(function () {
            $.post(path + "/loadManageCache", responseProcess, "json");
        });
        //前台缓存
        $("#btnLoadFrontCache").click(function () {
            $.post(path + "/loadFrontCache", responseProcess, "json");

        });
        //前台缓存明细
        $(".btn-front-cache").click(function () {
            var catalog = $(this).attr("catalog");
            $.post(path + "/loadFrontCacheDetail", {catalog: catalog}, responseProcess, "json");
        });
    });
</script>
<form class="row">
    <fieldset>
        <legend><h4>全局缓存</h4></legend>
        <div class="row">
            <span class="col-md-12">
            <a id="btnLoadManageCache"
               class="btn btn-primary"> <span class="icon-refresh icon-white"></span>
                加载后台缓存
            </a>
            <a id="btnLoadFrontCache" href="javascript:void(0)"
               class="btn btn-primary"> <i class="icon-refresh icon-white"></i>
                加载前台缓存
            </a>
            </span>
        </div>
    </fieldset>
</form>
<form class="row" style="margin-top: 40px">
    <fieldset>
        <legend><h4>前台缓存</h4></legend>
        <div class="row">
        <span class="col-md-11">
            <a id="btnFrontCacheActivity" catalog="activity"
               class="btn btn-info btn-front-cache"><i class="icon-refresh icon-white"></i>
                加载活动+活动商品列表
            </a>
            <a id="btnFrontCacheIndexImg" catalog="indexImg"
               class="btn btn-info btn-front-cache"><i class="icon-refresh icon-white"></i>
                门户滚动图片
            </a>
            <a id="btnFrontCacheAdvert" catalog="advert"
               class="btn btn-info btn-front-cache"><i class="icon-refresh icon-white"></i>
                广告列表
            </a>
            <a id="frontCacheNotifyTemplate" catalog="notifyTemplate"
               class="btn btn-info btn-front-cache"><i class="icon-refresh icon-white"></i>
                加载邮件模板列表
            </a>
            <a id="btnFrontCacheProductStock" catalog="productStock"
               class="btn btn-info btn-front-cache"><i class="icon-refresh icon-white"></i>
                加载商品内存库存
            </a>
            <a id="btnFrontCacheHotquery" catalog="hotquery"
               class="btn btn-info btn-front-cache"><i class="icon-refresh icon-white"></i>
                热门查询关键字
            </a>
            </span>
        </div>
    </fieldset>
</form>
</@page.pageBase>