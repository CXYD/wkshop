/**
 * 初始化预览页面
 */
function initPrviewPage(){
    $.ajax({
        url:'../queryTpl',
        data:{id:$("#pageid").val()},
        success:function(page){
            console.log("查询返回",page);
            setPageInfo($.parseJSON( page.pagecontent));
            $('.banner').unslider();
        }
    })

}

/**
 * 预览页面赋值
 * @param page
 */
function setPageInfo(page){
    console.log('预览页面信息'+page);
    console.log(page.temp_info);
    for(var i in page.temp_info){
        console.log(page.temp_info[i].type);
        console.log(page.temp_info[i].data);
        console.log(JSON.stringify(page.temp_info[i].data));
        var html = template('module_'+page.temp_info[i].type,page.temp_info[i].data);
        $("#content").append(html);
    }

}


/**
 * 表单校验
 * @param $obj 表单对象
 * @param errormsg 出错信息
 * @returns {Boolean} 校验结果
 */
function validate(obj){
    var value = $(obj).val();
    console.log("校验值：",value);
    if($.trim(value) == ''){
        console.log('值为空');
        //$obj.parent().addClass("has-error");
        $(obj).parent().find('p.dcr-error-msg').removeClass('ng-hide');
        $(obj).parent().parent().find('p.dcr-error-msg').removeClass('ng-hide');
        $(obj).addClass('error');
        $(obj).addClass('err_box');
        $(obj).parent().parent().addClass('err_box');
        return false;
    }else{
        if($(obj).parent().find("p.dcr-error-msg").length > 0){
            $(obj).parent().find("p.dcr-error-msg").addClass('ng-hide');
        }
        return true;
    }
}

/**
 * 右侧确定
 * */
function confirm(_self){


    var editTpl = way.get('temp_info');

    console.log("获取输入值：",editTpl);
    // editTpl == undefined ,说明是标题
    var flag = true;
    if(editTpl!=undefined && editTpl.type!='701'&& editTpl.type!='702' && editTpl.type!='703'){
        $(_self).parent().parent().find('input[type="text"]').each(function(){
            console.log($(this).val());
            flag = validFlag(this,flag);
        })

    }


    if(flag){
        console.log('提交保存');
        saveModuleValueToLeft(editTpl);
        var currIndex = $("#currIndex").val();
        var tmpTpl = $.extend(true,{},editTpl);

        if (currIndex != undefined && currIndex != '') {
            tpls.temp_info.splice(currIndex+1,1);
            tpls.temp_info.splice(currIndex + 1, 0,tmpTpl );
        } else {
            tpls.temp_info.pop();
            tpls.temp_info.push(tmpTpl);
        }

        way.set('temp_info',"");
        console.log("所有模块数据=", tpls);


        rightLoadModule('noAdd');
        leftLoadModule('noAdd');

    }
}


function validFlag(obj,flag){
    if(validate(obj)){
        if(!flag)
            flag = true;
    }else{
        flag = false;
    }
    return flag;
}
/**
 * 保存右侧模块值到左侧预览器
 * @param editTpl
 */
function saveModuleValueToLeft(editTpl){
    if(editTpl !=undefined && editTpl!='' && editTpl.type != undefined){
        switch(editTpl.type){
            case '200':
                save200(editTpl);
                break;
            case '201':
                save201(editTpl);
                break;
            case '301':
                save301(editTpl);
                break;
            case '501':
                save501(editTpl);
                break;
            case '502':
                save502(editTpl);
                break;
            case '503':
                save503(editTpl);
                break;
            case '504':
                save504(editTpl);
                break;
            case '701':
            case '702':
            case '703':
                save7XX(editTpl);
                break;

        }
    }else{
        saveEditTitle();
    }
}

function saveEditTitle(){
    way.set('title',$('.e_1-title').find('.e_1-value').val());
    tpls.title = $('.e_1-title').find('.e_1-value').val();
    tpls.desc = $('.e_1-describe').find('.e_1-value').val();
    tpls.skin.options.backgroundColor = $('#pickcolor').val();
    tpls.image = $('#e_1_img').attr('src');

}

function save200(editTpl){
    console.log("保存200，",editTpl);
    $('.dcr-item-selector').find('.module_200-shop').find('marquee').text(editTpl.data.title);
}

function save201(editTpl){

}
/**
 * 文字导航
 * @param editTpl
 */
function save301(editTpl){
    console.log('同步到左侧预览');
    $('.dcr-item-selector').find(".module_301-name").text(editTpl.data.content[0].name);
    $('.dcr-item-selector').find("a").attr('href',editTpl.data.content[0].link.url);

}

/**
 * 大图广告
 * @param editTpl
 */
function save501(editTpl){
    $('.ad_ctrl_main').find('.upload_ctrl').each(function(i) {
        var imgid = $(this).find('img').attr('id')
        var currimg = $(this).find("#upimg" + i).attr('src');
        console.log('图片url：', currimg);
        way.set("temp_info.data.content[0].image", currimg)
    });
    console.log("501 保存值：",editTpl);
    $('.dcr-item-selector').find(".module_501").each(function(i){
        $(this).find('img').attr('src',editTpl.data.content[i].image);

    })
}

function save502(editTpl){
    $('.ad_ctrl_main').find('.item_box').each(function(i) {
        var imgid = $(this).find('img').attr('id')
        var currimg = $(this).find("#upimg" + i).attr('src');
        console.log('图片url：', currimg);
        way.set("temp_info.data.content[" + i + "].image", currimg)
    });
    console.log("502 保存值：",editTpl);
    $('.dcr-item-selector').find(".module_502-pic").each(function(i){

        $(this).css('background','url('+editTpl.data.content[i].image+')');

    })
}


/**
 *  轮播图
 */

function save503(editTpl){
    $('.ad_ctrl_main').find('.item_box').each(function(i) {
        var imgid = $(this).find('img').attr('id')
        var currimg = $(this).find("#upimg" + i).attr('src');
        console.log('图片url：', currimg);
        way.set("temp_info.data.content[" + i + "].image", currimg)
    });

    $(".dcr-dragContent").find('.module_503-box').children().remove();
    var m = 0
    while(m<4){
        for (var key in editTpl.data.content[m]) {
            if (key == 'image') {
                var sliderBox = '<li class="module_503-img"> <a> <img src="'+editTpl.data.content[m].image+'"> </a> </li>';

                $(".dcr-dragContent").find('.module_503-box').append(sliderBox);
            }
        }
        m++;
    }

}

/**
 * 图片导航
 * @param editTpl
 */
function save504(editTpl){

    $('.ad_ctrl_main').find('.item_box').each(function(i) {
        var imgid = $(this).find('img').attr('id')
        var currimg = $(this).find("#upimg" + i).attr('src');
        console.log('图片url：', currimg);
        way.set("temp_info.data.content[" + i + "].image", currimg)
    });
    console.log("504 保存值：",editTpl);
    $('.dcr-item-selector').find(".show-box").each(function(i){

        $(this).find('p').text(editTpl.data.content[i].title);
        $(this).find('img').attr('src',editTpl.data.content[i].image);

    })
}


/**
 * 商品列表
 * @param editTpl
 */
function save7XX(editTpl){
    console.log('保存7xx-',editTpl);
    $('.dcr-item-selector').find('.module_701').find('a.item').each(function(i){

            $(this).find('img').attr('src',editTpl.data.content[i].url);
            $(this).find('.module_701-item-title').text(editTpl.data.content[i].name);
            $(this).find('.module_701-item-price').text('￥'+editTpl.data.content[i].price+'.00');

    })

    if(editTpl.data.options.title!=undefined){

        $('.dcr-item-selector').find('div.model_701-title').text(editTpl.data.options.title).css('display','block');
    }

}



/**
 * 删除
 * @param _self
 */
function removeSelf(_self){

    console.log("删除自身...");
    var yes = swal({
        title: "提示",
        text: "确定要删除？",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnConfirm: true
    }, function (flag) {
        if (flag) {
            $(_self).remove();
            rightLoadModule('noAdd');
        }
    });

}

/**
 * 下移
 */
function toNext(_self){
    console.log('下移');
    $(_self).before($(_self).next());
}

/**
 * 上移
 * @param _self
 */
function toPre(_self){
    console.log('下移');
    $(_self).after($(_self).prev());
}

/**
 * 插入
 * @param _self
 */
function insertEl(_self,currIndex){

    //console.log('插入当前id=',$(_self).attr(id));
    var currModuleid = $(_self).parent().attr('id');
    if(currModuleid == 'cpcDcrPreview'){
        currModuleid = $(_self).attr('id');
    }
    console.log('插入当前id=',currModuleid);
    $("#currModule").val(currModuleid);
    $("#currIndex").val(currIndex);
    $("#ui-modal-dialog").modal('toggle');
}

/**
 * 取消
 */
function cancel(){
    var currEditIndex = $('#currEditIndex').val();
    $('.dcr-item-selector').removeClass('dcr-item-selector');
    rightLoadModule('noAdd');
}

/**
 * 编辑
 * @param _self
 */
function editMe(_self){
    console.log("来源",$(event.target).attr('class'));
    var elClass = $(event.target).attr('class');
    // 获取当前元素在同级中的位置
    console.log('当前坐标',$(_self).index());
    var currIndex = $(_self).index();
    var tmp = {};

    if(elClass == 'btn-toNext'){
        toNext(_self);
        tmp = tpls.temp_info[currIndex];
        var nextModule = tpls.temp_info[currIndex+1];
        if (undefined != nextModule){
            tpls.temp_info[currIndex] = nextModule;
            tpls.temp_info[currIndex+1] = tmp;
        }
        console.log('移动后=',tpls);
    }
    if(elClass == 'btn-toPre'){
        toPre(_self);
        if(currIndex != 0){
            tmp = tpls.temp_info[currIndex];
            tpls.temp_info[currIndex] = tpls.temp_info[currIndex-1];
            tpls.temp_info[currIndex-1] = tmp;
            console.log('移动后=',tpls);
        }
    }

    if(elClass == 'btn-add'){
        insertEl(_self,currIndex);
    }

    if(elClass == 'btn-del'){
        removeSelf(_self);
        tpls.temp_info.splice(currIndex,1);
        console.log('删除后=',tpls);
    }
    // 编辑
    if(elClass == 'dcr-drag-mask'){
        console.log("编辑");
        removeItemSel();
        $(_self).addClass('dcr-item-selector');
        var className = $(_self).find('[class^="module_"]').attr('class');
        console.log(className);
        if(className.indexOf('503')>-1){
            rightLoadModule(503,currIndex);
        }else if(className.indexOf('702')>-1){
            rightLoadModule(702,currIndex);
        }else{

            rightLoadModule(className.substr(className.indexOf('_')+1),currIndex);
        }


    }


}

function setRightValue(moduleInfo){
    console.log('右侧值。。',moduleInfo);
    if(moduleInfo != undefined){

        switch(moduleInfo.type){
            case '200':
                edit200(moduleInfo);
                break;
            case '201':
                edit201(moduleInfo);
                break;
            case '301':
                edit301(moduleInfo);
                break;
            case '501':
                edit501(moduleInfo);
                break;
            case '502':
            case '503':
                edit502(moduleInfo);
                //edit503(moduleInfo);
                break;
            case '504':
                edit504(moduleInfo);
                break;
            case '702':
            case '701':
            case '703':
                edit7XX(moduleInfo);
                break;
        }
    }else{
      //  editTitle();
    }
}

function edit200(editTpl){
    $('.dcr-views').find('.e_1-edit  textarea').val(editTpl.data.title);
}

function edit201(editTpl){

}

function edit301(editTpl){
    way.set('temp_info',editTpl);
    $('.dcr-views').find('.enter_text input').val(editTpl.data.content[0].name);
        for (var key in editTpl.data.content[0].link) {
            if (key == 'name') {
                $(".ad_ctrl_main").find('#btnSelectLink0').text(editTpl.data.content[0].link.name);
            }
        }
}

/**
 * 大图
 * @param editTpl
 */
function edit501(editTpl){

    way.set('temp_info',editTpl);
    $('.ad_ctrl_main').find('.item_box').each(function(i){

        var currHref = editTpl.data.content[i].image
        var img = '<div class="img ng-scope"> <img data-imag="upimg'+i+'" id="upimg'+i+'" width="100%" class="ng-isolate-scope" src="'+currHref+'"> </div>';
        if(currHref!=undefined){
            $(this).find('.upload_ctrl').children().remove();
            $(this).find('.upload_ctrl').append(img);
        }

        for (var key in editTpl.data.content[i].link){
            if (key == 'name'){
                $(this).find('.btn-group').find('button').text(editTpl.data.content[i].link.name);
            }
        }

    })

}

/**
 * 两列图
 * @param editTpl
 */
function edit502(editTpl){

    way.set('temp_info',editTpl);

    $('.ad_ctrl_main').find('.item_box').each(function(i){

        var currHref = editTpl.data.content[i].image
        var img = '<div class="img ng-scope"> <img data-imag="upimg'+i+'" id="upimg'+i+'" width="100%" class="ng-isolate-scope" src="'+currHref+'"> </div>';
        if(currHref!=undefined){
            $(this).find('.upload_ctrl').children().remove();
            $(this).find('.upload_ctrl').append(img);
        }
        for (var key in editTpl.data.content[i].link){
            if (key == 'name'){
                $(this).find('.btn-group').find('button').text(editTpl.data.content[i].link.name);
            }
        }

    })
}

function edit503(editTpl){

}

/**
 * 图片导航
 * @param editTpl
 */
function edit504(editTpl){
    console.log('编辑信息；',editTpl);
    way.set('temp_info',editTpl);
    $('.ad_ctrl_main').find('.item_box').each(function(i){

        var currHref = editTpl.data.content[i].image
        var img = '<div class="img ng-scope"> <img data-imag="upimg'+i+'" id="upimg'+i+'" width="100%" class="ng-isolate-scope" src="'+currHref+'"> </div>';
        if(currHref!=undefined){
            $(this).find('.upload_ctrl').children().remove();
            $(this).find('.upload_ctrl').append(img);
        }

        $(this).find('.enter_text').find('input').val(editTpl.data.content[i].title);
        //$(this).find("#upimg" + i).attr('src',editTpl.data.content[i].image);
        for (var key in editTpl.data.content[i].link){
            if (key == 'name'){
                $(this).find('.btn-group').find('button').text(editTpl.data.content[i].link.name);
            }
        }

    })

}

function edit702(editTpl){

}

function edit7XX(editTpl){
    way.set('temp_info',editTpl);
    $('#title').val(editTpl.data.options.title);
    for(var key in editTpl.data.content){
        $('.e701-edit-selection').find('.show-one-item').each(function(i){
            $(this).find('img').attr('src',editTpl.data.content[i].url);
            $(this).find('.show-one-item-name').text(editTpl.data.content[i].name);

           if(editTpl.data.content[i].price==undefined){
                $(this).find('.show-one-item-price').text("￥0.00");
            }else{
                $(this).find('.show-one-item-price').text("￥"+editTpl.data.content[i].price+".00");
            }


        })
    }
}


function editTitle(){
    console.log('编辑标题',tpls);
    removeItemSel();
    $(".dcr-phone-head").addClass('dcr-item-selector');
    $('.e_1-title').find('.e_1-value').val(tpls.title);
    $('.e_1-describe').find('.e_1-value').val(tpls.desc);
    $('#e_1_img').attr('src',tpls.skin.image);
    $('#pickcolor').val(tpls.skin.options.backgroundColor);
}

function returnSelectLink(){
    console.log("值："+$("#subSelect").val());
    var opt="<option selected value='"+$("#subSelect").val()+"'>" +$("#subSelect").find("option:selected").text()+" </option>";
    console.log($("#parentSelect").val());
    var text = $("#subSelect").find("option:selected").text();
    var val = $("#subSelect").val()
    way.set('temp_info.data.content[0].link',{url:val,id:text})
    $("#"+$("#parentSelect").val()).append(opt);
    $("#subSelect-modal").modal("toggle");

}

/**
 * 加载右侧模板页面
 * @param type
 */
function rightLoadModule(type,currIndex){
    console.log('加载右侧模块type={},currIndx={}',type,currIndex);
    if(type == undefined){

        $('#ui-view').load("loadRightModule",{type:""},function(){
            editTitle();

        });

    }else{
        $('#ui-view').load("loadRightModule",{type:type},function(){
            if(currIndex != undefined){
                if(tpls.temp_info[0].type==undefined){
                    setRightValue(tpls.temp_info[currIndex+1]);
                }else
                setRightValue(tpls.temp_info[currIndex]);
            }

        });
    }



}


/**
 * 加载左侧页面
 * @param type
 */
function leftLoadModule(currModuleID,type,temp_info){
    console.log("当前模块位置="+currModuleID);
    removeItemSel();
    if(type != 'noAdd'){
        //var currModuleID =  $("#currModule").val();

        var mid = new Date().getTime();
        var mdiv = '<div data-mid="'+mid+'" id="m'+mid+'" onclick="editMe(this)" class="click-item dcr-dragItem ng-scope dcr-item-selector"></div>';

        if(currModuleID !=undefined && currModuleID!=''){
            console.log("模块插入");
            $("#"+currModuleID).after(mdiv);
        }else{
            $("#cpcDcrPreview").append(mdiv);
        }
        console.log("加载模块type=",type);
        $("#m"+mid).load("loadLeftModule",{type:type},function(){
            //console.log("加载完成:=",$("#m"+mid).children());
            //console.log("保存模块值到左侧：",temp_info);
            //$("#currModule").val('m'+mid)
            //saveModuleValueToLeft(temp_info);

            loadLeftValue(mid,type,temp_info);
        });
    }

}


/**
 * 从后台加载值到左侧
 * @param id
 * @param type
 * @param edit_info
 */
function loadLeftValue(id,type,edit_info){
    console.log("初始化左侧模块值",edit_info,type);
    if(edit_info !=undefined && edit_info!='' && edit_info.type != undefined){
        switch(type){
            case '200':
                initE200(id,edit_info);
                break;
            case '201':
                initE201(id,edit_info);
                break;
            case '301':
                initE301(id,edit_info);
                break;
            case '501':
                initE501(id,edit_info);
                break;
            case '502':
                initE502(id,edit_info);
                break;
            case '503':
                initE503(id,edit_info);
                break;
            case '504':
                initE504(id,edit_info);
                break;
            case '701':
            case '702':
            case '703':
                initE7XX(id,edit_info);
                break;

        }
    }

}

function initE200(id,info){
    $('#m'+id).find('.module_200-shopname').find('marquee').text(info.data.title);
}

function initE201(id,info){

}

function initE301(id,info){
    $('#m'+id).find('.module_301-name').text(info.data.content[0].name);
}

function initE501(id,info){
    $('#m'+id).find('.module_501').find('img').attr('src',info.data.content[0].image);
}

function initE502(id,info){
    console.log('502模块值：',info);
    $('#m'+id).find('.module_502').find('.module_502-pic:eq(0)').css('background','url('+info.data.content[0].image+')');
    $('#m'+id).find('.module_502').find('.module_502-pic:eq(1)').css('background','url('+info.data.content[1].image+')');
}

function initE503(id,info){
    console.log('503模块值：',info);
    $('#m'+id).find('.module_503').find('img').attr("src",info.data.content[0].image);
}

function initE504(id,info){

    $('#m'+id).find('.show-box').each(function(i) {
        $(this).find('img').attr('src',info.data.content[i].image);
        $(this).find('p').text(info.data.content[i].title);

    });

}

function initE7XX(id,info){

    console.log('商品模块：',info);

    $('#m'+id).find('.module_701').find('.module_701-item-image').each(function(i){
        $(this).attr('src',info.data.content[i].url);
    })

    $('#m'+id).find('.module_701-item-title').each(function(i){
        $(this).text(info.data.content[i].name);
    })

    $('#m'+id).find('.module_701-item-price').each(function(i){
        $(this).text('￥'+info.data.content[i].price+".00");
    })

    $('#m'+id).find('div.model_701-title').text(info.data.options.title).css('display','block');

}



function removeItemSel(){
    $("#dcr-phone div.dcr-item-selector").each(function(index,el){
        $(el).removeClass('dcr-item-selector');
    })

}

/** 打开选选择连接**/
function popupLink(id){
    $('#currPopuplink').val(id);
    $("#link-modal-dialog").modal("toggle");
}
/** 关闭弹窗 **/
function closePopup(){
    $("#link-modal-dialog").modal("toggle");
}

//切换连接
function selectLink(val){
    console.log("选择tab");
    $('#currLinkTypeTab').val(val);
    $("li[id^='selLinktab']").removeClass('dcr-cur')
    $("#selLinktab" + val).addClass('dcr-cur')
    $("div[id^='modal-curLinkType']").hide();
    $("#modal-curLinkType" + val).show();

}

/**
 * 选择设置选项
 * @param value
 */
function selectItem(value){
    // {type:4, id:-1, url:$('#diyUrl).val(), name:'自定义链接'}

    //console.log('设置选项：',event.currentTarget);
    var popupLinkID =  $('#currPopuplink').val();
    way.set('temp_info.data.content['+popupLinkID+'].link',value)
    $('#btnSelectLink'+popupLinkID).text(value.name);
    console.log("赋值后；",way.get('temp_info'));

}
/**
 * 推广商品选择
 * @param value
 */
function selectProductItem(id,value){
    way.set('temp_info.data.content['+id+']',value);

    console.log('商品选择赋值后；',way.get('temp_info'));
    for(var key in way.get('temp_info.data.content')){
        $('.e701-edit-selection').find('.show-one-item').each(function(i){
            if(i==key){

                $(this).find('img').attr('src',way.get('temp_info.data.content['+key+'].url'));
                $(this).find('.show-one-item-name').text(way.get('temp_info.data.content['+key+'].name'));
                $(this).find('.show-one-item-price').text("￥"+way.get('temp_info.data.content['+key+'].price')+".00");;
            }
        })
    }

}

/**
 * 选取连接类型
 * @param val
 */
function selectTypeLink(e){
    console.log('选择连接类型=',  $('#currLinkTypeTab').val());
    console.log('选择行：', $(e.target).attr('data-val'));
    if($(e.target).attr('data-val') !=undefined){
        selectItem({type:$('#currLinkTypeTab').val(),id: $(e.target).attr('data-val'),name: $(e.target).attr('data-name')});
        closePopup();
    }


}


/**
 * 选取产品类型
 */
function selectProductTypeLink(e){
    console.log('当前上传商品：',$('#currAddProductId').val());
    console.log('选择连接类型=',  $('#currLinkTypeTab').val());
    console.log('选择行：', $(e.target).attr('data-val'));

    var currAddProductID =  $('#currAddProductId').val();

    if($(e.target).attr('data-val') !=undefined){
       // selectProductItem({options:{type:$('#currLinkTypeTab').val(),title: $(e.target).attr('data-name')},content:[{itemID: $(e.target).attr('data-val')}]});
        if(currAddProductID == 1){
            selectProductItem(1,{type:$('#currLinkTypeTab').val(),itemID: $(e.target).attr('data-val'),name: $(e.target).attr('data-name'),price:$(e.target).attr('data-price'),url:$(e.target).attr('data-imgurl')});
        }else{

            selectProductItem(0,{type:$('#currLinkTypeTab').val(),itemID: $(e.target).attr('data-val'),name: $(e.target).attr('data-name'),price:$(e.target).attr('data-price'),url:$(e.target).attr('data-imgurl')});
        }
        $('#productLink-modal-dialog').modal('toggle');
    }

}


/**
 * 初始化分类表
 */
function initClassTable(){
  var  tables = $('#classTable').DataTable({
        "processing": true,
        "serverSide": true,
        "bFilter": false,
      "bLengthChange": false, //改变每页显示数据数量
      "bInfo": false,//页脚信息
        "language": {
            "url": "/wkshop/resource/datatables/Chinese.json"
        },
        "ajax": {
            url: "/wkshop/manage/product/class/loadData",
            dataSrc: "list"
        },
        columns: [
//                {name: "ID", "orderable": false, title: 'ID', data: "id"},
            {name: "name", title: "规格名称","orderable": false, data: "name"},
            {
                name: "createtime", title: "创建时间", "orderable": false,data: "createtime", render: function (data, type, row, meta) {
                return data;
            }
            },
            {
                name: "oper",
                title: "操作",
                "orderable": false,
                data: "status",
                render: function (data, type, row, meat) {

                    return '<div class="btn btn-sm btn-info" data-val="'+row.id+'" data-name="'+row.name+'" name="selectClass" >选取</div>';
                }
            }

        ]
    });//datatable
}
/**
 * 初始化商品表
 */
function initProductTable(){
    var  tables = $('#productTable').DataTable({
        "processing": true,
        "serverSide": true,
        "bFilter": false,
        "bLengthChange": false, //改变每页显示数据数量
        "bInfo": false,//页脚信息
        "language": {
            "url": "/wkshop/resource/datatables/Chinese.json"
        },
        "ajax": {
            url: "/wkshop/manage/product/productManage/loadData",
            dataSrc: "list"
        },
        columns: [
//                {name: "ID", "orderable": false, title: 'ID', data: "id"},
            {name: "product_name", title: "商品名称","orderable": false, data: "productName"},
            {name:"create_time", title:"创建时间", data:"createTime","orderable": false,render:function(data,type,row,meta){
                return data;
            }},
            {
                name: "oper",
                title: "操作",
                "orderable": false,
                data: "status",
                render: function (data, type, row, meat) {

                    return '<div class="btn btn-sm btn-info" data-val="'+row.id+'"  data-name="'+row.productName+'"  name="selectProduct" >选取</div>';
                }
            }

        ]
    });//datatable
}

/**
 * 自定义页面
 */
function initCustomPage(){
    tables = $('#customPageTable').DataTable({
        "processing": true,
        "serverSide": true,
        "bFilter": false,
        "bLengthChange": false, //改变每页显示数据数量
        "bInfo": false,//页脚信息
        "language": {
            "url": "/wkshop/resource/datatables/Chinese.json"
        },
        "ajax": {
            url:"loadData",
            dataSrc:"list"
        },
        columns:[
//                {name:"label_id", "orderable": false, title:'ID', data:"labelId"},
            {name:"title", title:"页面标题", data:"title"},
            {name:"create_time", title:"创建时间", data:"createTime","orderable": false,render:function(data,type,row,meta){
                return data;
            }},
            {name:"oper", title:"操作","orderable": false,  data:"status", "orderable": false,render: function (data, type, row, meat) {

                return '<div class="btn btn-sm btn-info" data-val="'+row.id+'" data-name="'+row.title+'"  name="selectCustom" >选取</div>';
            }}

        ]
    });//datatable
}


/**
 * 初始化商品表
 */
function initProductSelectTable(){
    var  tables = $('#productSelectTable').DataTable({
        "processing": true,
        "serverSide": true,
        "bFilter": false,
        "bLengthChange": false, //改变每页显示数据数量
        "bInfo": false,//页脚信息
        "language": {
            "url": "/wkshop/resource/datatables/Chinese.json"
        },
        "ajax": {
            url: "/wkshop/manage/product/productManage/loadData",
            dataSrc: "list"
        },
        columns: [
//                {name: "ID", "orderable": false, title: 'ID', data: "id"},
            {name: "product_name", title: "商品名称","orderable": false, data: "productName"},
            {name: "sell_price", title: "价格", data: "sellPrice"},
            {name:"create_time", title:"创建时间", data:"createTime","orderable": false,render:function(data,type,row,meta){
                return data;
            }},
            {
                name: "oper",
                title: "操作",
                "orderable": false,
                data: "status",
                render: function (data, type, row, meat) {

                    return '<div class="btn btn-sm btn-info" data-val="'+row.id+'" data-price="'+row.sellPrice+'" data-imgurl="'+row.img+'" data-name="'+row.productName+'"  name=selectProductLink >选取</div>';
                }
            }

        ]
    });//datatable
}



function uploadSucced(msg){
    //console.log("返回文件地址："+msg);
    var currHref = location.href;
    currHref = currHref.substr(0,currHref.indexOf('wkshop')+6).concat(msg);
    var currImgID =  $("#currUploadImgId").val();
    var i = currImgID.substr(3);
    console.log(currHref);
    var img = '<div class="img ng-scope"> <img data-imag="upimg'+i+'" id="upimg'+i+'" width="100%" class="ng-isolate-scope" src="'+currHref+'"> </div>';

    console.log('当前上传图片id',currImgID);
    $('#'+currImgID).parent().next().children().remove();
    $('#'+currImgID).parent().next().append(img);

}
function uploadFailed(msg){
    console.log("上传错误")
    alert('上传出错');
}

function keyP(val) {
    $("#dcrPageTitle").html(val.value);
}

function editTitlePage() {
    way.set('temp_info', "")
    rightLoadModule();
    $("#currEditIndex").val('title');
}

function addModule() {

    console.log("弹层");
    $("#ui-modal-dialog").modal('toggle');
}

/**
 * 选择模块分类
 */
function selectClassify(val) {
    console.log("选择tab");

    $("li[id^='seltab']").removeClass('dcr-cur')
    $("#seltab" + val).addClass('dcr-cur')
    $("div[id^='modal-curType']").hide();
    $("#modal-curType" + val).show();

}


/**
 *  选择模块
 */
function selectModule(evn) {
    console.log("选择模块");
    var type = $(evn.target).attr('data-module-type');
    var currModule = $("#currModule").val();
    var currIndex = $("#currIndex").val();
    var currEditIndex = $("#currIndex").val();
    console.log(type);

    way.set('temp_info', tplContent[type]);
    if (currIndex != undefined && currIndex != '') {
        tpls.temp_info.splice(currIndex + 1, 0, way.get('temp_info'));
    } else {

        tpls.temp_info.push(way.get('temp_info'));
    }
    console.log("1-开始选择添加模块-模块数据=", tpls);

    rightLoadModule(type);
    console.log('tpls=',JSON.stringify(tpls));
    leftLoadModule(currModule, type);
    console.log('tpls=',JSON.stringify(tpls));
    if (currEditIndex == 'title') {
        $("#currEditIndex").val('');
    } else {

        $("#currEditIndex").val(currEditIndex);
    }
    $("#currModule").val('');
    $("#currIndex").val('');
    $("#ui-modal-dialog").modal('toggle');


}

/**颜色选择器重置
 */
function reset() {
    $('#pickcolor').val('#eeeeee');
    $('#pickcolor').minicolors('value', ['#eeeeee']);
}

function share() {
    $('#e_1-share_show').show()
}

function close() {
    console.log('关闭');
    $('#e_1-share_show').attr("display", "none")
}


/**
 * 保存页面
 */
function savePage() {

    tpls.id=$("#customPageId").val();
    console.log('保存数据=', tpls);
    if (tpls.temp_info.length < 1) {
        swal('模块个数不能为零');
    } else {

        var skin = {"id": "1", "css_name": "default", "options": {"backgroundColor": "#eeeeee"}};
        tpls[skin] = skin;

        $.ajax({
            url: "savePage",
            type: "post",
            dataType: 'json',
            data: {
                pagecontent: JSON.stringify(tpls)
            },
            success: function (id) {
                if (id != '') {
                    $("#prviewLink").attr('href', 'pview/' + id);
                    $("#prviewLink").addClass('dcr-click-able');
                    $("#customPageId").val(id);
                    swal("保存成功!");

                }
            }
        })
    }
}


/**
 * 初始化下拉选项
 */
function selectInit() {

    var opt = "<option value='' selected>请选择链接</option>" +
        "<option value='1' >商品</option>" +
        "<option value='2' >商品分类</option>" +
        "<option value='3' >自定义页面</option>" +
        "<option value='4' >其他链接</option>";
    $("select[id!='subSelect']").empty();
    $("select[id!='subSelect']").append(opt);
    $("select[id!='subSelect']").on("change", function (e) {
        $("#subSelect-modal input[id='parentSelect']").val($(this).attr("id"));
        $("#subSelect-modal").modal("toggle");
    })
}


var temp_info = {};


/**
 * 同步加载
 * @param url
 * @param params
 * @param callback
 * @returns {*}
 */
jQuery.fn.loadHtml = function( url, params, callback ) {
    //保存上一次的load事件句柄，如果传入的url不是string那么直接调用上一次的load函数!
    if ( typeof url !== "string" && _load ) {
        return _load.apply( this, arguments );
    }
    var selector, response, type,
    //this为调用者
        self = this,
    //获取第一个空格
        off = url.indexOf(" ");
    //如果存在空格，URL="http://localhost:8080/qinl/a.action selector"
    if ( off >= 0 ) {
        //获取selector,表示只用特定的相应数据取代元素的innerHTML属性!
        selector = jQuery.trim( url.slice( off, url.length ) );
        //获取URL部分，URL部分和selector部分用空格隔开!
        url = url.slice( 0, off );
    }
    //如果是函数那么表示没有传入数据!
    // If it's a function
    if ( jQuery.isFunction( params ) ) {
        // We assume that it's the callback
        callback = params;
        params = undefined;
        // Otherwise, build a param string
        //如果params不是函数，如果第二个参数还是对象，那么表示用户自己传入了数据
        //那么我们自己把数据提交方式设置为post！
    } else if ( params && typeof params === "object" ) {
        type = "POST";
    }
    // If we have elements to modify, make the request
    //如果调用对象有DOM元素，那么底层调用ajax方法。我们知道ajax方法默认是get这在ajaxSettings里可以看到!
    //获取到的数据类型是"html"
    if ( self.length > 0 ) {
        jQuery.ajax({
            url: url,
            // if "type" variable is undefined, then "GET" method will be used
            type: type,
            async:false,
            dataType: "html",
            data: params
            //成功调用done方法
        }).done(function( responseText ) {
            //我们知道ajax方法返回的对象是一个jqXHR对象，调用done方法表示向
            //成功回调函数集合中添加一个回调函数！compelte表示往ajax封装的
            //Callbacks中添加一个回调，该回调不管成功还是失败都是会执行的!
            //在done方法里面的回调是：deferred.resolveWith( callbackContext, [ success, statusText, jqXHR ] );
            //success = response.data;
            //所以在done里面的第一个参数就是访问服务器获取到的数据!
            // Save response for use in complete callback
            response = arguments;
            //如果没有selector那么直接把返回的数据放入到调用对象中间
            //如果有selector,那么只是把返回的数据的特定部分放入到调用对象中间，通过find方法查找!
            self.html( selector ?
                // If a selector was specified, locate the right elements in a dummy div
                // Exclude scripts to avoid IE 'Permission Denied' errors
                //parseHTML没有传入的第二个参数，表示不包括script标签!
                jQuery("<div>").append( jQuery.parseHTML( responseText ) ).find( selector ) :

                // Otherwise use the full result
                responseText );
            //不管成功与否都会调用complete方法!
            //调用complete方法的时候如果用户指定了回调函数，那么回调函数会修改为另外一个函数
            //加入到完成回调集合里面。对调用对象每一个DOM都调用回调函数，为该函数传入的回调
            //参数是:completeDeferred.fireWith( callbackContext, [ jqXHR, statusText ] );
            //也就是参数是调用done方法的参数，[ success, statusText, jqXHR ]
        }).complete( callback && function( jqXHR, status ) {
            self.each( callback, response || [ jqXHR.responseText, status, jqXHR ] );
        });
    }
    return this;
};
