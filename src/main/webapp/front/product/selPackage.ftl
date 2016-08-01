
<!DOCTYPE HTML>
<html>
<head>
<#include "/front/header.ftl">
    <title>选择合约套餐</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no" />
<style>
    li{
        line-height: 2em;
    }
</style>
<body>
<div class="">
    <div class="Mall-Specifications">
        <div class="user-body account">
            <div class="element-body loaf">
                <div class="row">
                    <div class="col-xs-8 padding-Ten">
                        合约期限
                    </div>
                </div>
            </div>
            <div class="element-end">
                <div class="row">
                    <div class="col-xs-9 ia">
                        <div class="col-xs-12 ia">
                            <ul class="nav nav-tabs loaf" id="monthSelTab">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <p class="Package-title" >套餐资费</p>

        <div id="Option-contract">
          <span style="float:left; width:25%; background:#FFF;" id="feeSelTab">

          </span>
            <ul>
                <li class="nr">
                    <div class="row">
                        <div class="col-xs-12 Package">
                            <div class="col-xs-4 text-right ia">
                                合约期：
                            </div>
                            <div class="col-xs-8 text-left ia" id="month">

                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-12 Package">
                            <div class="col-xs-4 text-right ia">
                                赠送话费：
                            </div>
                            <div class="col-xs-8 text-left ia" id="zeng">

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 Package">
                            <div class="col-xs-4 text-right ia">
                                返还规则：
                            </div>
                            <div class="col-xs-8 text-left ia" id="returns">

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 Package">
                            <div class="col-xs-4 text-right ia">
                                通话时长：
                            </div>
                            <div class="col-xs-8 text-left ia" id="times">

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 Package">
                            <div class="col-xs-4 text-right ia">
                                短信条数：
                            </div>
                            <div class="col-xs-8 text-left ia" id="sms">

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 Package">
                            <div class="col-xs-4 text-right ia">
                                网络流量：
                            </div>
                            <div class="col-xs-8 text-left ia" id="folw">

                            </div>
                        </div>
                    </div>
                </li>

            </ul>

        </div>

        <div class="user-body account" style="margin-top:10px;">
            <div class="element-body loaf">
                <div class="row">
                    <div class="col-xs-8 padding-Ten">
                        首月资费
                    </div>
                </div>
            </div>
            <div class="element-end">
                <div class="row">
                    <div class="col-xs-12 ia">
                        <div class="col-xs-12 ia">
                            <ul class="nav nav-tabs loaf" id="selFirstFee">
                                <li  ><a style="line-height: 2em;"  data-toggle="tab" data-value="0">付半月</a></li>
                                <li  class="active" ><a style="line-height: 2em;" data-toggle="tab" data-value="1">付全月</a></li>
                                <li><a style="line-height: 2em;"  data-toggle="tab"  data-value="2">按量计费</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="element-body loaf">
                <div class="row">
                    <div class="col-xs-12 padding-Ten" id="firstFeeContent" style="">适合月初开通的用户，按照您选择的套餐正常计费并享受套餐内容。</div>
                </div>
            </div>
            <div class="element-body loaf">
                <div class="row">
                    <div class="col-xs-12 padding-Ten color-red" id="remark">
                        ${note!""}
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#--<form id="selectPackageForm" action="submitSelPackage" method="POST">-->
        <#--<input type="hidden" name="packageID">-->
        <#--<input type="hidden" name="firstFee">-->
        <#--<input type="hidden" name="processID" value="${processInfo.id}">-->
    <#--</form>-->
    <p class="dfaqa"><button type="button" class="btn btn-block  org-btn" id="submitToNext">下一步</button></p>
</div>
<script type="text/javascript">

    var packageInfo = {};
    $(document).ready(function(){
        var processinfo = $.cookie('process_info');
        console.log(decodeURI(processinfo));

       loadSelTab();
        loadTabInfo();
    })


    function loadSelTab(){
        var feeTabArr=[]
        var monthTabArr=[]
        <#list specInfos as specInfo>
            <#if specInfo.specArray?? && specInfo?size gt 0>
                var feeValue =  $.parseJSON('${specInfo.specArray}')[1].value
                if(feeTabArr.indexOf(feeValue)==-1){
                    feeTabArr.push(feeValue);
                }

                var monthValue = $.parseJSON('${specInfo.specArray}')[0].value
                if(monthTabArr.indexOf(monthValue)==-1){
                    monthTabArr.push(monthValue);
                }
            </#if>
        </#list>

        console.log(feeTabArr)
        $('#feeSelTab').empty();
        for(var inx in feeTabArr){
            if(inx==0){
                $("#feeSelTab").append('<p class="qh">'+feeTabArr[0]+'</p>')
            }else
                $("#feeSelTab").append('<p>'+feeTabArr[inx]+'</p>')
        }

        $('#monthSelTab').empty();
        for(var i in monthTabArr){
            if(i==0){
                $('#monthSelTab').append('<li class="active"><a style="line-height: 2em;" data-toggle="tab">'+monthTabArr[0]+'</a></li>');

            }else{
                $('#monthSelTab').append('<li class=""><a style="line-height: 2em;"  data-toggle="tab">'+monthTabArr[i]+'</a></li>');
            }
        }
    }


    function loadTabInfo(selMonth){
        var selFee = $("#feeSelTab").find('.qh').text();
        if(selMonth==undefined)
         selMonth = $('#monthSelTab').find('.active').text();

        <#list specInfos as specInfo>
            <#if specInfo.specArray?? && specInfo?size gt 0>
                var tmpSpec =  $.parseJSON('${specInfo.specArray}');
                if(tmpSpec[0].value==selMonth && tmpSpec[1].value==selFee){
                    console.log("${specInfo}");
                    $('#month').text('${specInfo.month}');
                    $('#zeng').text('${specInfo.zeng}');
                    $('#returns').text('${specInfo.returns}');
                    $('#times').text('${specInfo.times}');
                    $('#sms').text('${specInfo.sms}');
                    $('#folw').text('${specInfo.folw}');
                    <#--$('input[name=packageID]').val(${specInfo.id});-->
                    packageInfo.packageID = '${specInfo.id}'
                    packageInfo.packageName='${packageName}'
                }
            </#if>
        </#list>
    }

    $('#monthSelTab').on('click',function(e){
//        console.log("----"+$(e.target).html());
        loadTabInfo($(e.target).html());
    })
    $('#feeSelTab').on('click',function(){
        $(this).find('.qh').removeClass('qh');
        $(event.target).addClass('qh');
        loadTabInfo();
    })

    var feeContent=['适合月中开通的用户，按照您选择的套餐收取一半的费用，享受一半的套餐内容，从次月起恢复正常。'
                    ,'适合月初开通的用户，按照您选择的套餐正常计费并享受套餐内容。'
                    ,'适合月底开通的用户，开通当月按照标准资费依据使用量进行计费，从次月起回复正常。']
    $('#selFirstFee').on('click',function(){
        var selVal = $(event.target).attr('data-value');
        packageInfo.firstFee = selVal;
        packageInfo.feeName = $(event.target).text()
        $('#firstFeeContent').text(feeContent[selVal])
    })

    $("#submitToNext").on('click',function(){
        if(packageInfo.firstFee==undefined){
            var v = $('#selFirstFee').find(".active a").attr('data-value')
            packageInfo.firstFee = v;
        }

        sessionStorage.setItem("packageInfo",JSON.stringify(packageInfo))
//        $.cookie('packageInfo',JSON.stringify(packageInfo),{expries:1,path:'/wkshop'});
        location.href = 'submitSelPackage';
    })

</script>

</body>
<#include "/front/footer.ftl">
</html>
