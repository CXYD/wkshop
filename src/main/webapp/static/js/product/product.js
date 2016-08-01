var next = false; //是否切换到下一步
var logurl="";
var goodsObj={}; // 货物


/**
 * 初始化form控件事件
 */
function initForm(){
    //新增规格
    $(".btn.new").click(function(){
        location.href="/wkshop/manage/product/norm/toAddNorm";
    })

    $('#head_photo').on('change',function(){
        ajaxFileUploadview('head_photo','photo_pic',"/wkshop/upload");
    });


    // 选择商品类型下一步
    $('#next-edit-1').on("click",function(){
        $("input[name^='_']").attr("style","width:55px");
        if(typeof(goodsObj.type)== "undefined"){
            swal("请选择宽带商品类型");
            return;
        }else{
            next=true;
            $('#pTab a[href="#edit-1"]').tab('show');
        }
    });

    //下一步，发布商品信息
    $('#next-edit-2').on("click",function(){

        if( checkForm($("#productBase").serialize())){
            next=true;
            $('#pTab a[href="#edit-2"]').tab('show');
        }


    });
    //**发布
    $("#finish").on('click',function(){
        console.log("描述=",$(".summernote").code());

        var selStrArr = $("#select_specs").val();
        var selArr = [];
        for(var i in selStrArr){
            selArr.push(specObj[selStrArr[i]].value);
        }
        $("#selectArray").val(selArr);
        var proinfo =$("#productBase").serialize()
        console.log(proinfo);
        if( checkForm(proinfo)){
            var desc = '&description='+$(".summernote").code()
            var info = proinfo.concat(desc)
            saveAndUpate(info);
            next=true;

        }
    })


    //是否显示续费
    $("#chosebtn .btn").click(function(){
        $("#chosebtn .btn").removeClass('btn-warning');
        $(this).addClass('btn-warning');
        goodsObj.typeName= $(this).text();
        goodsObj.type=$(this).val();
        $("select[name='productType']").val(goodsObj.type);
        $("input[name='type']").val(goodsObj.type);
        console.log("商品类型=",goodsObj.type);
        if(goodsObj.type==2){ //续费
            console.log("续费");
            $("#divXf").show();
        }else{
            $("#divXf").hide();
        }
    })


    $('input[name="receipt"]').on("click",function(){
        var val = $(this).val();
        if(val==1){
            $('input[name="receiptPrice"]').attr("disabled",false);
        }else{
            $('input[name="receiptPrice"]').attr("disabled",true);
        }
    })


    //添加规格
    $(".btn.add").on("click",function(){
        console.log($(this).text());
        if($(this).text()=="添加规格"){
            $(".sku-select").show();
            $(this).hide()
        }else{
            console.log($("#select_specs").val());
        }
    })




    $("select[name='newnumId']").on("change",function(e){
        console.log("个数可用"+$(this).val());
        if($(this).val()!="")
         $('input[name="newnums"]').removeAttr("disabled").val(1);
        else{
            $('input[name="newnums"]').attr("disabled",true)
        }
    })


    // 选择规格
    $("#select_specs").change(function(){
        var specidArr=[];
        var list = {};
        var specData = {};
        var selList = $(this).val();
        var specid=0;

        console.log("已选：",selList);

        for( id in selList){
            var currSpecid = specObj[selList[id]].id;
            list[specObj[selList[id]].id]=specObj[selList[id]];
            if(specidArr.indexOf(currSpecid)>-1){
                specData[specObj[selList[id]].id].push(specObj[selList[id]].value);
            }else{
                specidArr.push(currSpecid);
                specData[specObj[selList[id]].id]=[];
                specData[specObj[selList[id]].id].push(specObj[selList[id]].value);
            }

        }
        createProductList(list,specData);
    });

}



/**
 *  初始化选择规格列表
 */
function initSelect(){

    $.ajax({
        url:"/wkshop/manage/product/norm/querySpecJson",
        success:function(d){
            var specJson ={};
            var specOpt = "";
            console.log("查询所有规格=",d);
            for(var i in d){
                var values=d[i].value.replace("[","").replace("]","")
                var valueArr = values.split(",");

                specOpt= specOpt+ "<optgroup label="+(parseInt(i)+1)+d[i].name+">";


                for(var j in valueArr){
                    var subJson={};

                    subJson.id=d[i].id;
                    subJson.name=d[i].name;
                    subJson.value=valueArr[j];
                    specOpt = specOpt+'<option value='+i.concat(j)+'>'+subJson.value+'</option>';

                    specJson[i.concat(j)]=subJson;
                }


                specOpt= specOpt+ "</optgroup>";
            }

            specObj = specJson;
            $("#select_specs").append(specOpt);

            $('#select_specs').multiselect();

        }
    })
}

//保存商品信息
function saveAndUpate(info){
    //异步提交提交表单
    console.log("保存商品信息",info);
    $.blockUI({ message: "处理中，请稍候...",css: {
        border: 'none',
        padding: '15px',
        backgroundColor: '#000',
        '-webkit-border-radius': '10px',
        '-moz-border-radius': '10px',
        opacity: .5,
        color: '#fff'
    } });

    $.ajax({
        url:"/wkshop/manage/product/productManage/save",
        type:"POST",
        dataType:"json",
        traditional:true,
        data:info,
        success:function(data){
            $('#productId').val(data);
            jQuery.unblockUI();
             swal({title:'提示',text:'保存成功',timer:3000,showConfirmButton:false});
            window.setTimeout(function(){
                    $('.wraper').load("/wkshop/manage/product/productManage/toProductList");
            },3000);

        },
        err:function(e){console.err('保存商品失败',info); jQuery.unblockUI();swal('保存失败')}
    });
}

/**
 *  校验表单
 */
function checkForm(formValue){
    var obj = strToObj(formValue);
    console.log("form表单数据=",obj);
    if($.trim(obj.productName)==""){
        swal("商品名不能为空");
        return false;
    }


    if(obj.photo_pic==""){
        swal("请添加商品图片");
        return false;
    }

    return true;
}


// 编辑设置form表单
function initEditInfo(){
    if($.trim($('#productId').val())!=""){
        $.ajax({
            url:"/wkshop/manage/product/productManage/queryDetail",
            data:{productid:$("#productId").val()},
            success:function(d){
                console.log("获取产品详情=",d);
                setForm(d);

                $('.summernote').code(d.description);
                $('#cpcDcrPreview').html(d.description);
                $('#pTab a[href="#edit-1"]').tab('show');
            }
        })
    }
}


/**
 * 初始化商品添加的下来列表
 */
function initClassAndlabel(){
    $('#productClass').empty();
    $.ajax({
        url:"/wkshop/manage/product/class/queryClassJson",
        success:function(d){
            console.log(d);
            var opt="<option value='' selected>未选择</option>";
            for(var i in d){
                opt = "<option value='"+d[i].id+"'>"+ d[i].name+"</option>" +opt;

            }
            $('#productClass').append(opt);
        }
    })

    $('#productLabel').empty();
    $.ajax({
        url:"/wkshop/manage/product/lable/queryLabelJson",
        success:function(d){
            console.log(d);
            var opt="<option value='' selected>未选择</option>";
            for(var i in d){
                opt = "<option value='"+d[i].labelId+"'>"+ d[i].name+"</option>" +opt;

            }
            $('#productLabel').append(opt);
        }
    })

    initProcessSelect();
}

function initProcessSelect(){
    $("select[name='processid']").empty();
    $.ajax({
        url:"/wkshop/manage/product/process/queryProcessJson",
        success:function(d){
            var opt="<option value='' selected>未选择</option>";
            for(var i in d){
                opt = "<option value='"+d[i].id+"'>"+ d[i].name+"</option>" +opt;

            }
            $("select[name='processid']").append(opt);
        }
    })
}

function strToObj(str){
    str = str.replace(/&/g,"','");
    str = str.replace(/=/g,"':'");
    str = "({'"+str +"'})";
    obj = eval(str);
    return obj;
}

/**
 * 编辑产品信息初始化
 */

function setForm(info){

    $("select[name='productType']").val(info.type);
    $("input[name='type']").val(info.type);
    if(info.type=="2"){
        $("#divXf").show();
    }
    $("select[name='procontent']").val(info.procontent);
    $("input[name='productName']").val(info.productName);
    $("textarea[name='shortDescription']").val(info.shortDescription);
    $("#productClass").val(info.categoryId);
    $("#productLabel").val(info.brandId);
    $("input[name='storeNums']").val(info.storeNums);
    $("img[name='productImg']").attr('src',info.img);
    $("input[name='img']").val(info.img);
    $("input[name='prestr']").val(info.prestr);
    if(info.toBalance=="0")
        $("input[name='toBalance'][value='0']").attr("checked",'checked');
    else {
        $("input[name='toBalance'][value='1']").attr("checked", 'checked');
    }
    if(info.receipt=="0")
        $("input[name='receipt'][value='0']").attr("checked",'checked');
    else{
        $("input[name='receipt'][value='1']").attr("checked",'checked');
        $('input[name="receiptPrice"]').attr("disabled",false);
        $("input[name='receiptPrice']").val(info.receiptPrice);
    }
    $("input[name='freight']").val(info.freight);
    $("select[name='processid']").val(info.processid);


    var specData = eval(info.specArray);
    var productList =[];
    var selArr = info.selectArray.split(",");
    console.log("返回的规则",info);
    $.ajax({
        url:"/wkshop/manage/product/productManage/queryGoodsByPid/"+info.id,
        success:function(d){
            console.log("货品：",eval(d));

            var selSetVal = [];
            for(var id in selArr ) {
                for (var key in specObj) {
                    if(selArr[id] ==specObj[key].value){
                        selSetVal.push(key);
                        console.log(key)
                    }
                }
            }

            $("#select_specs").multiselect('select',selSetVal);
            //创建规格标题
            var goodsHeadHtml = template.render('goodsHeadTemplate',{'templateData':specData});
            $('#goodsBaseHead').html(goodsHeadHtml);
            //创建货品数据表格
            var goodsRowHtml = template.render('goodsRowTemplate',{'templateData':d});
            $('#goodsBaseBody').html(goodsRowHtml);


        }
    })
}


/**
 *  库存量计算
 * @param value
 */
function countValue(value){
    console.log("计算总和");
    var sum = 0;
    $("input[name^='_storeNums']").each(function () {
        if ($(this).val() == "") {
        }else{
            sum += parseInt($(this).val());
        }
    })

    $("input[name='storeNums']").val(sum);
}


/**
 * 显示上传的照片
 */
function show_head(head_file){

    //插入数据库
    $("img[name='productImg']").attr('src',head_file);
    $("#photo_pic").val(head_file);

}

/**
 * 文件上传带预览
 * @param imgid
 * @param hiddenid
 * @param url
 * @returns {boolean}
 */
function ajaxFileUploadview(imgid,hiddenid,url){


    $.ajaxFileUpload({
        url:url,
        secureuri:false,
        fileElementId:imgid, //文件上传空间的id属性
        dataType: 'text',
        data:{name:'logan', id:'id'},
        success: function (data, status)
        {
            if(typeof(data) != 'undefined')
            {
                show_head(data);
                $.Notification.autoHideNotify('success','top center','提示','添加成功');
            }
        },
        error: function (data, status, e)
        {

            $.Notification.autoHideNotify('error','top center','提示',e);
        }
    })

    return false;
}

//  ----------------    流程相关开始 --------------------/

//添加流程，打开窗口
$("a[name='a_addProcess']").on("click",function(e){
    $("#processForm")[0].reset();
    $("#con-close-modal").modal('toggle');
    $("input[name='name']").focus();
    init_process_select();
})


// 新增流程保存按钮
$(".btn.btn-info").click(function(){
    console.log($("#processForm").serialize() );
    var name =  $("input[name='name']").val();

    if($.trim(name)==""){
        swal("错误","流程名称不能为空","error");
    }else{

        saveProcess();
    }

})
//流程名
$("input[name='name']").blur(function(e){
    uniqueProcessName();
})


function uniqueProcessName(){
    $.ajax({
        url:"/wkshop/manage/product/process/unique",
        data:{name:$(this).val()},
        type:"POST",
        success:function(d){
            console.log("流程名是否可以用=",d);
        }
    })
}
/**
 * 保存流程
 */
function saveProcess(){


    $.ajax({
        url:"/wkshop/manage/product/process/unique",
        data:{name:$('#processName').val()},
        type:'post',
        success:function(d){
            if(d=='ok'){
                $.ajax({
                    url:"/wkshop/manage/product/process/save",
                    data:$("#processForm").serialize(),
                    success:function(d){
                        console.log(d)
                        swal("成功","添加完成","success");
                        $("#con-close-modal").modal('toggle');
                        initProcessSelect();
                    }
                })

            }else{
                return false;
            }
        }
    })



}



//初始化添加流程下来列表
function init_process_select(){
    //旧号下拉列表

        $.ajax({
            url:"/wkshop/manage/data/number/selectKeyValue",
            data:{khid:"123"},
            success:function(d){
                console.log(d);
                $("select[name='oldnumId']").empty();
                var opt="<option value='' selected>未选择</option>";
                for(var i in d){
                    opt = "<option value='"+d[i].skey+"'>"+ d[i].svalue+"</option>" +opt;

                }
                $("select[name='oldnumId']").append(opt);
            }
        });


//新号下拉列表

        $.ajax({
            url:"/wkshop/manage/data/number/selectKeyValue",
            data:{khid:"123"},
            success:function(d){
                $("select[name='newnumId']").empty();
                var opt="<option value='' selected>未选择</option>";
                for(var i in d){
                    opt = "<option value='"+d[i].skey+"'>"+ d[i].svalue+"</option>" +opt;

                }
                $("select[name='newnumId']").append(opt);
            }
        });

    $.ajax({
        url:"/wkshop/manage/product/package/selectKeyValue",
        success:function(d){
            $("select[name='packageid']").empty();
            var opt="<option value='' selected>未选择</option>";
            for(var i in d){
                opt = "<option value='"+d[i].id+"'>"+ d[i].name+"</option>" +opt;

            }
            $("select[name='packageid']").append(opt);
        }
    })

}

//  ----------------    流程相关结束--------------------/



/**
 * 初始化货品表格
 */

function initProductTable() {

    var goodsHeadHtml = template.render('goodsHeadTemplate',{'templateData':[]});
    $('#goodsBaseHead').html(goodsHeadHtml);

    //默认产生一条商品空挡
    var goodsRowHtml = template.render('goodsRowTemplate',{'templateData':[[]]});
    $('#goodsBaseBody').html(goodsRowHtml);

}


/**
 * @brief 根据规格数据生成货品序列
 * @param object specData规格数据对象
 * @param object specValueData 规格值对象集合
 */
function createProductList(specData,specValueData) {
    //生成货品的笛卡尔积
    var specMaxData = descartes(specValueData,specData);

    //从表单中获取默认商品数据
    var productJson = {};
    var pJsonArr = [];
    var si = 0;
    $('#goodsBaseBody tr').find('input[type="number"]').each(function(){
        productJson[this.name.replace(/^_(\w+)\[\d+\]/g,"$1")] = this.value;
        si++;
        if(si==5){
            var tArr = $.extend(true, {}, productJson);
            pJsonArr.push(tArr);
            si=0;
        }


    });
    //生成最终的货品数据
    var productList = [];
    var itemArr = [];
    for(var i = 0;i < specMaxData.length;i++) {
        var productItem = {};
        if(i<=pJsonArr.length){
            productJson = pJsonArr[i];
            for(var index in productJson) {
                //自动组建货品号
                if(index == 'goods_no') {
                    //值为空时设置默认货号
                    if(productJson[index] == '') {
                        productJson[index] = defaultProductNo;
                    }

                    if(productJson[index].match(/(?:\-\d*)$/) == null) {
                        //正常货号生成
                        productItem['goods_no'] = productJson[index]+'-'+(i+1);
                    } else {
                        //货号已经存在则替换
                        productItem['goods_no'] = productJson[index].replace(/(?:\-\d*)$/,'-'+(i+1));
                    }
                } else {
                    productItem[index] = productJson[index];
                }
            }
        }

        productItem['specArray'] = specMaxData[i];

        productList.push(productItem);

    }

    //创建规格标题
    var goodsHeadHtml = template.render('goodsHeadTemplate',{'templateData':specData});
    $('#goodsBaseHead').html(goodsHeadHtml);

    //创建货品数据表格
    var goodsRowHtml = template.render('goodsRowTemplate',{'templateData':productList});
    $('#goodsBaseBody').html(goodsRowHtml);

    if($('#goodsBaseBody tr').length == 0)
    {
        initProductTable();
    }
}





/**
 * 获取已经存在的规格
 * @returns {{specData: {}, specValueData: {}}}
 */

function getIsHereSpec() {
    //开始遍历规格
    var specValueData = {};
    var specData      = {};

    console.log($('input:hidden[name^="_spec_array"]'));
    //规格已经存在的数据
    if($('input:hidden[name^="_spec_array"]').length > 0) {
        $('input:hidden[name^="_spec_array"]').each(function() {
            var json = $.parseJSON(this.value);

            if(!specValueData[json.id]) {
                specData[json.id]      = json;
                specValueData[json.id] = [];
            }

            if(jQuery.inArray(json['value'],specValueData[json.id]) == -1) {
                specValueData[json.id].push(json['value']);
            }
        });
    }
    return {"specData":specData,"specValueData":specValueData};
}


/**
 * 笛卡儿积组合
 *
 * @param list
 * @param specData
 * @returns {*}
 */
function descartes(list,specData) {

    console.log("表头=",list);
    console.log("数据：=",specData);


    //parent上一级索引;count指针计数
    var point  = {};

    var result = [];
    var pIndex = null;
    var tempCount = 0;
    var temp   = [];

    //根据参数列生成指针对象
    for(var index in list) {
        if(typeof list[index] == 'object') {
            point[index] = {'parent':pIndex,'count':0}
            pIndex = index;
        }
    }

    //单维度数据结构直接返回
    if(pIndex == null) {
        return list;
    }

    //动态生成笛卡尔积
    while(true) {
        for(var index in list) {
            tempCount = point[index]['count'];
            temp.push({"id":specData[index].id,"type":specData[index].type,"name":specData[index].name,"value":list[index][tempCount]});
        }

        //压入结果数组
        result.push(temp);
        temp = [];

        //检查指针最大值问题
        while(true) {
            if(point[index]['count']+1 >= list[index].length) {
                point[index]['count'] = 0;
                pIndex = point[index]['parent'];
                if(pIndex == null) {
                    return result;
                }

                //赋值parent进行再次检查
                index = pIndex;
            }
            else {
                point[index]['count']++;
                break;
            }
        }
    }
}


<!--批量设置-->


$("a[name=setOldPrice]").on('click',function(){
    if($("input[name='input_oldPrice']").length < 1) {
        $(this).after("<input name='input_oldPrice' class='tiny' type='number' min='0'><div class='btn btn-default btn-xs m-l-5' name='btnSetOldPrice' onclick='delOldPriceBtn()'>确定</div>");
    }
})

function delOldPriceBtn(){
    var value = $("input[name=input_oldPrice]").val();
    $("input[name^='_oldPrice']").each(function () {
        $(this).val(value );
    })

    $("input[name='input_oldPrice']").remove();
    $("div[name='btnSetOldPrice']").remove();
}

$("a[name=setSellPrice]").on('click',function(){
    if($("input[name='input_sellPrice']").length < 1) {
        $(this).after("<input name='input_sellPrice' class='tiny' type='number' min='0'><div class='btn btn-default btn-xs m-l-5' name='btnSetSellPrice' onclick='delSellPriceBtn()'>确定</div>");
    }
})

function delSellPriceBtn(){
    var value = $("input[name=input_sellPrice]").val();
    $("input[name^='_sellPrice']").each(function () {
        $(this).val(value );
    })

    $("input[name='input_sellPrice']").remove();
    $("div[name='btnSetSellPrice']").remove();
}

$("a[name='setMonth']").on("click",function(e){
    if($("input[name='input_month']").length < 1) {
        $(this).after("<input name='input_month' class='tiny' type='number' min='0'><div class='btn btn-default btn-xs m-l-5' name='btnSetMonth' onclick='delMonthBtn()'>确定</div>");
    }
})
function delMonthBtn(){
    var value = $("input[name='input_month']").val();
    $("input[name^='_monthPrice']").each(function () {
        $(this).val(value );
    })

    $("input[name='input_month']").remove();
    $("div[name='btnSetMonth']").remove();
}

$("a[name='setStore']").on("click",function(e){
    if($("input[name='input_store']").length<1) {
        $(this).after("<input name='input_store' class='tiny' type='number' min='0'><div class='btn btn-default btn-xs m-l-5' name='btnSetStore' onclick='delStoreBtn()'>确定</div>");
    }
})
function delStoreBtn(){
    var sum = 0;
    $("input[name^='_storeNums']").each(function () {
        $(this).val( $("input[name='input_store']").val());
        sum += parseInt($(this).val());
    })
    $("input[name='storeNums']").val(sum);
    $("input[name='input_store']").remove();
    $("div[name='btnSetStore']").remove();
}

$("a[name='commission']").on("click",function(e){
    if($("input[name='input_commission']").length<1) {
        $(this).after("<input name='input_commission' class='tiny' type='number' min='0'><div class='btn btn-default btn-xs m-l-5' name='btnSetCommission' onclick='delCommissionBtn()'>确定</div>");
    }
})
function delCommissionBtn(){
    $("input[name^='_commission']").each(function () {
        $(this).val( $("input[name='input_commission']").val());
    })

    $("input[name='input_commission']").remove();
    $("div[name='btnSetCommission']").remove();
}




<!--批量设置结束-->