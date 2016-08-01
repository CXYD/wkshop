var specDataValueObj = {};
var feeArr =[];   //选择的规格key
var selArr = [];  //选择的规格value
var specJson ={}; // 规格值

/**
 * 保存点击事件
 */
$("#finish").on("click",function(e){
    for(var y in feeArr){
        selArr.push(specJson[feeArr[y]].value);
    }
    $("#selectArray").val(selArr);
    var packageid = $("input[name='packageId']").val();
    console.log("packageid=",packageid);

    if($("#packageForm").valid()){

        if($.trim(packageid)==""){
            checkName();

        }else{
            save();
        }


    }


})


/**
 * 添加期限
 */
$("#btnAddMonth").click(function(){
    if($("select[name='select_addMonth']").length<1) {
        $(this).before("<select name='select_addMonth'  style='margin-left: 5px' onclick='initMonthSel(this)' onchange='addMonth()'><option value=''>选择套餐资费</option>");
    }

})


/**
 * 添加资费
 */
$("#btnAddFee").click(function(){
    if($("select[name='input_addFee']").length<1) {
        $(this).before("<select name='input_addFee'  style='margin-left: 5px' onclick='initFeeSel(this)' onchange='addFee()'><option value=''>选择套餐资费</option>");
    }
})


/**
 * 校验名称是否存在
 */
function checkName(){

    $.ajax({
        url:"/wkshop/manage/product/package/checkName",
        data:{name:$("input[name='name']").val()},
        success:function(d){
            if(d=="ok"){
                save();

            }else{
                swal("套餐名称已经存在!");
            }
        }
    })
}


/**
 * 保存和更新
 */
function save(){
    var feeFlag = false;
    var monthFlag = false;
    var data = $("#packageForm").serialize();
    for(var i in feeArr){
        if(feeArr[i].indexOf("c0")>-1){
            feeFlag = true;
        }else if(feeArr[i].indexOf("c1")>-1){
            monthFlag = true;
        }
    }

    if(feeArr.length>1 && feeFlag  && monthFlag){
        $.ajax({
            url:"/wkshop/manage/product/package/save",
            data:data,
            success:function(d){
                console.log("success");
                swal("保存成功");
                $('.wraper').load('/wkshop/manage/product/package/selectList')
            }
        });
    }else{
        swal("合约期限和套餐资费为必选项!");
    }
}

/**
 * 初始化套餐资费下拉选项
 * @param self
 */
function initFeeSel(self){
    $(self).empty();
    $(self).append(specSelectFeeOpt);
}

/**
 * 初始化合约期限下拉选项
 * @param self
 */
function initMonthSel(self){
    $(self).empty();
    $(self).append(specSelectMonthOpt);
}

/**
 * 添加资费
 */
function addFee(){
    var fee = $("select[name='input_addFee'] option:selected").text();
    var feeVal = $("select[name='input_addFee']").val();
    if(feeArr.indexOf(feeVal)>-1){
        swal("不能重复选择");
        $("select[name='input_addFee']").remove();
        $("div[name='btnSetFee']").remove();
        return ;
    }else{
        feeArr.push(feeVal);
    }


    if(feeVal!="") {
        $("#btnAddFee").before(' <label class="" style="border:1px #d7d7d7 solid; margin: 2px 5px 2px 0;">' + fee + '<button type="button" value="'+feeVal+ '"class="close" onclick="delMe(this)"  aria-hidden="true">×</button></label>');
    }
    $("select[name='input_addFee']").remove();
    console.log("已选：",feeArr);
    startCreatePackageTable(feeArr,false);
}

/**
 * 添加合约期
 */
function addMonth(){
    var month = $("select[name='select_addMonth'] option:selected").text();
    var monthVal = $("select[name='select_addMonth']").val();
    if(feeArr.indexOf(monthVal)>-1){
        swal("不能重复选择");
        $("select[name='select_addMonth']").remove();
        return ;
    }else{
        feeArr.push(monthVal);
    }

    if(monthVal!="") {
        $("#btnAddMonth").before(' <label class="" style="border:1px #d7d7d7 solid; margin: 2px 5px 2px 0;">' + month + '<button type="button" value="'+monthVal+'" class="close" onclick="delMe(this)"  aria-hidden="true">×</button></label>');
    }
    $("select[name='select_addMonth']").remove();
    console.log("已选：",feeArr);
    startCreatePackageTable(feeArr,false);
}


/**
 * 准备创建表格
 * @param feeArray 选择的规格
 * @param isDel 是增加，还是删除
 */
function startCreatePackageTable(feeArray, isDel){
    if(feeArray.length>1){
        var col1 = false;
        var col2 = false;

        for(i in feeArray){
            if(feeArray[i].indexOf("c0")==0){
                col1 = true;
            }else if(feeArray[i].indexOf("c1")==0){
                col2 = true;
            }
        }

        if(col1&&col2){
            var specDataList= translateSpec(feeArray);
            createProductList(specDataList.list,specDataList.specData);
        }else{
            if(isDel){

                console.log("删除表内容")
                $("#packageBaseBody").empty();
            }
        }

    }else{
        if(isDel) {
            console.log("删除表内容")
            $("#packageBaseBody").empty();
        }
    }


}


/**
 * 根据选择值转换
 * @param selList 选择的规格
 * @returns {{list: {}, specData: {}}}
 */
function translateSpec(selList){
    var specidArr=[];
    var list = {};
    var specData = {};
    var specObj = specJson;
    for( id in selList){
        if(id!='remove'){
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


    }

    return {"list":list,"specData":specData}

}

/**
 * 规格删除
 * @param _self
 */
function delMe(_self){
    var feeVal = $(_self).attr("value");
    feeArr.splice($.inArray(feeVal,feeArr),1);
    startCreatePackageTable(feeArr,true);
    $(_self).parent().remove();

}






var specSelectFeeOpt="<option value=''>选择资费</option>";
var specSelectMonthOpt="<option value=''>选择合约期限</option>";
/**
 * 加载规格值
 */
function initSpecJson(){

    $.ajax({
        url:"/wkshop/manage/product/norm/queryPackageSpecJson",
        asyn:false,
        success:function(d){
            for(var i in d){
                var values=d[i].value.replace("[","").replace("]","")
                var valueArr = values.split(",");
                for(var j in valueArr){
                    var subJson={};

                    subJson.id=d[i].id;
                    subJson.name=d[i].name;
                    subJson.value=valueArr[j];
                    var col = "c"+ i.concat(j);
                    specJson[col]=subJson;
                    if(i==1)
                        specSelectFeeOpt = specSelectFeeOpt+'<option value='+col+'>'+subJson.value+'</option>';
                    else
                        specSelectMonthOpt= specSelectMonthOpt+'<option value='+col+'>'+subJson.value+'</option>';
                }
            }

        }
    });

}


/**
 * 笛卡儿积组合
 * @param list  头
 * @param specData 值
 * @returns {*}
 */
function descartes(list,specData) {

    //parent上一级索引;count指针计数
    var point  = {};

    var result = [];
    var pIndex = null;
    var tempCount = 0;
    var temp   = [];

    //根据参数列生成指针对象
    for(var index in list) {
        if(typeof list[index] == 'object' && index!="remove") {
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
            if(index!="remove"){

                tempCount = point[index]['count'];
                temp.push({"id":specData[index].id,"type":specData[index].type,"name":specData[index].name,"value":list[index][tempCount]});
            }
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


/**
 * 获取已经存在的规格
 * @returns {{specData: {}, specValueData: {}}}
 */
function getIsHereSpec() {
    //开始遍历规格
    var specValueData = {};
    var specData      = {};
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
    $('#packageBaseBody tr').find('input[type="text"]').each(function(){
        productJson[this.name.replace(/^_(\w+)\[\d+\]/g,"$1")] = this.value;
        si++;
        if(si==7){
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
                if(index == 'package_no') {
                    //值为空时设置默认货号
                    if(productJson[index] == '') {
                        productJson[index] = defaultProductNo;
                    }
                    if(productJson[index].match(/(?:\-\d*)$/) == null) {
                        //正常货号生成
                        productItem['package_no'] = productJson[index]+'-'+(i+1);
                    } else {
                        //货号已经存在则替换
                        productItem['package_no'] = productJson[index].replace(/(?:\-\d*)$/,'-'+(i+1));
                    }
                } else {
                    productItem[index] = productJson[index];
                }
            }
        }

        productItem['specArray'] = specMaxData[i];
        productList.push(productItem);

    }


    //创建货品数据表格
    var packageRowHtml = template.render('packageRowTemplate',{'templateData':productList});
    $('#packageBaseBody').html(packageRowHtml);

    if($('#packageBaseBody tr').length == 0)
    {
        initPackageTable();
    }
}


/**
 *  编辑设置form表单
 */

function initEditInfo(){
    if($('input[name="packageId"]').val()!=""){

        $.ajax({
            url:"/wkshop/manage/product/package/queryDetail",
            data:{id:$('input[name="packageId"]').val()},
            success:function(d){
                setForm(d)
            }
        })
    }
}


/**
 * 编辑产品初始化
 * @param info 查询返回套餐信息
 */

function setForm(info){

    $("input[name='name']").val(info.name);
    $("textarea[name='note']").val(info.note);
    var specData = eval(info.specArray);
    var productList =[];
    var selArr = info.selectArray.split(",");
    $.ajax({
        url:"/wkshop/manage/product/package/querySpecByPid/"+info.id,
        success:function(d){

            for(var id in selArr ) {
                for (var key in specJson) {
                    if($.trim(selArr[id]) == $.trim(specJson[key].value)){
                        if(specJson[key].name=="合约期限"){
                            $("#btnAddMonth").before(' <label class="" style="border:1px #d7d7d7 solid; margin: 2px 5px 2px 0;">' + specJson[key].value + '<button type="button" value="'+key+'" class="close" onclick="delMe(this)"  aria-hidden="true">×</button></label>');
                        }else if( specJson[key].name=="套餐资费"){
                            $("#btnAddFee").before(' <label class="" style="border:1px #d7d7d7 solid; margin: 2px 5px 2px 0;">' +  specJson[key].value + '<button type="button" value="'+key+ '"class="close" onclick="delMe(this)"  aria-hidden="true">×</button></label>');
                        }
                        feeArr.push(key);
                    }
                }
            }

            //创建货品数据表格
            var packageRowHtml = template.render('packageRowTemplate',{'templateData':d});
            $('#packageBaseBody').html(packageRowHtml);
        }
    })


}
