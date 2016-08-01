<!DOCTYPE HTML>
<html>
<head>
    <#include "/front/header.ftl">
    <title>发票信息</title>
</head>
<body>
<div class="whole">
    <div class="Mall-Specifications">
        <div class="user-body account">
            <div class="element-body loaf">
                <div class="row">
                    <div class="col-xs-3 padding-Ten">
                        发票信息
                    </div>
                </div>
            </div>
            <div class="element-end">
                <div class="row">
                    <div class="col-xs-9 ia">
                        <div class="col-xs-12 ia">
                            <ul class="nav nav-tabs loaf">
                                <li data-value="0"  class="active"><a data-toggle="tab">不要发票</a></li>
                                <li data-value="1"><a  data-toggle="tab">纸质发票</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>


        <div class="user-body" hidden id="head">
            <div class="element-body fill-top">
                <div class="row">
                    <div class="col-xs-3 ia">发票抬头：</div>
                    <div class="col-xs-8 ia">
                        <div class="col-xs-12 ia">
                            <input type="text" class="dxld-log" onkeyup="check()" maxlength="50" placeholder="请输入发票抬头">
                        </div>
                    </div>
                </div>
            </div>


            <div class="element-end" hidden id="fee">
                <div class="row">
                    <div class="col-xs-3 ia">快递费用：</div>
                    <div class="col-xs-9 ia">
                        <div class="col-xs-12 ia" id="receipt_price">
                            ¥ 0
                        </div>
                    </div>
                </div>

            </div>

        </div>

        <p class="register"><button type="button" class="btn btn-block btn-warning" >保存</button></p>
    </div>

</div>
<#include "/front/footer.ftl">
<script>
    $('li').on('click',function(){
        var sel = $(this).attr('data-value')
        if(sel=='1'){
            console.log('show');
            $('#head').removeAttr('hidden')
            $('#fee').removeAttr('hidden')
            $('button').removeClass('btn-warning');
            $('button').attr('disabled',true)
            var receipt_price = $.cookie('receipt_price');
            if(receipt_price!=undefined)
            $('#receipt_price').text('￥'+receipt_price+'.00')

        }else{
            $('#head').attr('hidden',true)
            $('#fee').attr('hidden',true)
            $('button').addClass('btn-warning');
            $('button').removeAttr('disabled')
        }
    })

    function check(){
        $('button').addClass('btn-warning');
        $('button').removeAttr('disabled')
    }

    $('button').on('click',function(){
        var isticket = $('li.active').attr('data-value')
        if(isticket=='1'){
            console.log($('input').val());
        }
        $.cookie('needticket',isticket);
        $.cookie('tickethead',$('input').val());
        location.href='${basepath}/product/goodsSubmit';
    })

</script>
</body>
</html>
