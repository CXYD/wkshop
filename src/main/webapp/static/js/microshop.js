
/**
 * Created by Titan on 2016/5/30.
 */

var edittarget = new Array(); //当前编辑的对象

/**
 * 初始化加载项
 */
$().ready(function(){
    init();
    showPage("NewModel",null, null)
    $("#rightForm").validate();
    if($("#id").val()!==''){
         inidEditPage();
    }
});



function init(){
    if($("#container").children().length<=0){
        $("#background").delegate(".btn-success","click",function(){showPage("NewModel",null);});
        $("#background").delegate(".btn-danger","click",function(){dialogbox("删除模块","确认要删除此模块么？","否","是",null);});

        //$("#background").mousemove(function(e){
        //    //createpopupeditlayer("background");
        //});
        $("#background").mouseleave(function(e){$("div").remove("#background div");});
    }else{
        $("#background").undelegate();
        $("#background").unbind();
    }
}

/**
 * 创建编辑层，并绑定编辑层中按钮事件
 * @param node 父容器的节点ID
 */
function createpopupeditlayer(node) {
    if($("#editlayer").length>0) return;
    var popup_edit_layer = $('<div id="editlayer" class="element_popup_over text-center"><div class="parent_relative fullwidth fullheight"><div class="vertical-center fullwidth"><button type="button" class="btn btn-success">插入模块</button><button type="button" class="btn btn-warning">编辑</button><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#dialogbox">删除</button></div></div></div>');
    $("#"+node).append(popup_edit_layer);
    $("#"+node).delegate(".btn-success","click",function(e){showPage("NewModel",null, e.delegateTarget);});
    $("#"+node).delegate(".btn-warning","click",function(e){editNodeinfo(e.delegateTarget);});
    $("#"+node).delegate(".btn-danger","click",function(e){dialogbox("删除模块","确认要删除此模块么？","否","是", e.delegateTarget.id);});
}

/**
 * 删除编辑层
 */
function removepopupeditlayer(){
    $("div").remove("#editlayer");
}

/**
 * 按元素ID删除节点元素
 * @param id 要删除的节点ID
 */
function del_containel_node(id){
    $("div").remove("#"+id);
    showPage("NewModel",null);
}

/**
 * 模板管理页面的右侧HTML源码获取
 * @param pageName 要访问的页面
 * @param obj      当前访问的元素
 * @param delegatetarget 当前的委托目标，如果不为null则入栈
 */
function showPage(pageName,obj,delegatetarget){
    delegatetarget = delegatetarget || null;

    if(pageName!="ToShop"){
        htmlobj = $.ajax({url:pageName,async:false});
        $("#base").html(htmlobj.responseText);
        if(pageName=="PicNav"){
            $("#PicNav_Page").delegate(".col-md-1","click",function(e){GetBackGroundColorFromPicNavImg(e);});
        }
    }
    if(obj!=null){
        var origin = obj.dataset.origin;
        edittarget.length = 0;//清空
        switch(origin){
            case "PicNav":
                createPicNav();
                break;
            case "TextNav":
                createTextNav();
                break;
            case "Adv21":
                createAdv("Adv21");
                break;
            case "Adv31":
                createAdv("Adv31");
                break;
            case "TwoColAdv":
                createAdv("TwoColAdv");
                break;
            case "ProductListTwoCol":
                createProductList("TwoCol");
                $("#productlistinfo").append(createProductListPre("TwoCol"));
                break;
            case "ProductListTopBottom":
                createProductList("TopBottom");
                $("#productlistinfo").append(createProductListPre("TopBottom"));
                break;
            case "ProductListLeftRight":
                createProductList("LeftRight");
                $("#productlistinfo").append(createProductListPre("LeftRight"));
                break;
            case "Notice":
                createNotice();
                break;
            case "ToShop":
                createToShop();
                break;
            default:break;
        }

        selectInit();
    }

    if(delegatetarget!=null){
        if(delegatetarget.id!="background")
            edittarget.push(delegatetarget);
    }
    init();
}


function GetBackGroundColorFromPicNavImg(selectColor){
    //var ColorNode = selectColor.currentTarget;
    var imgs = $(selectColor.currentTarget).parent().parent().find("img");
    imgs[0].style.backgroundColor = selectColor.currentTarget.style.backgroundColor;
    ///static/img/microshop/dpzxsyt-30.png
    $(selectColor.currentTarget).parent().parent().find(".col-md-1").each(
        function(){
            $(this).empty();
        }
    );
    $(selectColor.currentTarget).append('<img src="/wkshop/static/img/microshop/dpzxsyt-30.png" style="margin-left:-3px;">');
    //return ColorNode.style.backgroundColor;
}

/**
 * 插入节点
 * 如果container容器中没有元素则插入，否则在当前选中节点的下方插入
 * @param node
 */
function insertNode(node) {
    if (edittarget.length > 0) {
        var target = edittarget.pop();
        if (target[0].id != "background") {
            $(target[0]).after(node);
        }
    }else{
        $("#container").append(node);
    }
    edittarget.push(node);
}

//function setImgPic(obj)
//{
//    //判断浏览器
//    var Sys = {};
//    var ua = navigator.userAgent.toLowerCase();
//    var s;
//    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
//        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
//            (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
//                (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
//                    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
//    var file_url = "";
//    if (Sys.ie <= "6.0") {
//        //ie5.5,ie6.0
//        file_url = obj.value;
//    } else if (Sys.ie >= "7.0") {
//        //ie7,ie8
//        var file = obj;
//        file.select();
//        file_url = document.selection.createRange().text;
//    } else if (Sys.firefox) {
//        //fx
//        //file_url = document.getElementById("file").files[0].getAsDataURL();//获取的路径为FF识别的加密字符串
//        file_url = readFileFirefox(obj);
//    }
//    alert(file_url);
//    obj.previousElementSibling.src = obj.value;
//}



/**
 * 创建图片导航
 */
function createPicNav(){
    var itemid = createNodeId("PicNav");
    var picnav = $('<div id="'+itemid+'" class="col-xs-12"><div class="col-xs-3 text-center"><a href="#"><img src="https://d.weidian.com/images/decoration/dcr-default-bg504.jpg" class="thumb-md img-rounded bx-s" style="border-radius:50%"><h6>导航1</h6></a></div><div class="col-xs-3  text-center"><a href="#"><img src="https://d.weidian.com/images/decoration/dcr-default-bg504.jpg" class="thumb-md img-rounded bx-s" style="border-radius:50%"><h6>导航2</h6></a></div><div class="col-xs-3  text-center"><a href="#"><img src="https://d.weidian.com/images/decoration/dcr-default-bg504.jpg" class="thumb-md img-rounded bx-s" style="border-radius:50%"><h6>导航3</h6></a></div><div class="col-xs-3  text-center"><a href="#"><img src="https://d.weidian.com/images/decoration/dcr-default-bg504.jpg" class="thumb-md img-rounded bx-s" style="border-radius:50%"><h6>导航4</h6></a></div></div>');
    insertNode(picnav);
    delegateToItem(itemid);
}

/**
 * 创建文本导航
 */
function createTextNav(){
    var itemid = createNodeId("TextNav");
    var textnav = $('<div id="'+itemid+'" class="col-xs-12"><a href="#"><div class="col-sm-11"><p class="m-t-10 m-b-10">导航文本</p></div><div class="col-sm-1"><span class="fa fa-angle-right m-t-10 m-b-10"></span> </div></a></div>');
    insertNode(textnav);
    delegateToItem(itemid);
}

/**
 * 创建广告
 * @param prefix 节点ID前缀
 */
function createAdv(prefix){
    var itemid = createNodeId(prefix);
    var adv = null;
    switch(prefix){
        case "Adv21":
            adv = $('<div id="'+itemid+'" class="row col-xs-12"><a href="#"><img src="https://d.weidian.com/images/decoration/dcr-default-bg501.jpg" class="img-polaroid " ></a></div>');
            break;
        case "Adv31":
            adv = $('<div id="'+itemid+'" class="row col-xs-12"><a href="#"><img src="https://d.weidian.com/images/decoration/dcr-default-bg503.jpg" class="img-polaroid "></a></div>');
            break;
        case "TwoColAdv":
            adv = $('<div id="'+itemid+'" class="row col-xs-12"><div class="col-xs-6" style="overflow: hidden" ><a href="#"><img src="https://d.weidian.com/images/decoration/dcr-default-bg502.jpg" class="img-polaroid" ></a></div><div class="col-xs-6 " style="overflow: hidden" ><a href="#"><img src="https://d.weidian.com/images/decoration/dcr-default-bg502.jpg" class="img-polaroid " ></a></div></div>');
            break;
        default:break;
    }

    insertNode(adv);
    delegateToItem(itemid);
}

/**
 * 创建商品推荐列表
 * @param prefix 节点ID前缀
 */
function createProductList(prefix){
    var itemid = createNodeId(prefix);
    var productlist = null;

    switch(prefix){
        case "TopBottom":/** 大图 **/
        productlist = $(' <div id="'+itemid+'" class="row col-xl-12 module_701"><div class="single-line style_1"> <a class="item" href="http://weidian.com/i/1886296077"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466650375975-6976235.jpg?w=480&amp;h=0"> <p class="module_701-item-sold">销量0</p> </div> <p class="module_701-item-title">sdfsf</p><p class="module_701-item-price">¥ 2.00</p> </a> </div> </div>');
            break;
        case "LeftRight":/** 列表 **/
        productlist = $(' <div id="'+itemid+'" class="row col-xl-12 module_701"> <div class="three-line"> <div class="item-wrap"> <a class="item" href="http://weidian.com/i/1829265829"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466650395101-480766.jpg?w=400&amp;h=400&amp;cp=1"> <p class="module_701-item-sold">销量0</p> </div> <p class="module_701-item-title">顶顶顶顶顶顶顶顶顶顶</p> <p class="module_701-item-price">¥ 131.00</p> </a> </div> <div class="item-wrap"> <a class="item" href="http://weidian.com/i/1886295522"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466591061648-6181072.png?w=400&amp;h=400&amp;cp=1"> <p class="module_701-item-sold">销量0</p> </div> <p class="module_701-item-title">dsfds</p> <p class="module_701-item-price">¥ 32.00</p> </a> </div> </div> </div>');
            break;
        case "TwoCol": /** 两列 **/
        productlist = $('<div id="'+itemid+'" class="row col-xl-12 module_701"  > <ul class="double-line style_2"> <li class="item-wrap"> <a class="item" href="#"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466650375975-6976235.jpg?w=400&amp;h=400&amp;cp=1"> </div> <p class="module_701-item-title">商品1</p> <p class="module_701-item-price">¥ 2.00</p> </a> </li> <li class="item-wrap"> <a class="item" href="#"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466650395101-480766.jpg?w=400&amp;h=400&amp;cp=1">  </div> <p class="module_701-item-title">商品2</p> <p class="module_701-item-price">¥ 131.00</p> </a> </li> </ul> <div style="clear:both;"></div> </div>');
          break;
        default:break;
    }
    insertNode(productlist);
    delegateToItem(itemid);
}

/**
 * 创建公告
 */
function createNotice(){
    var itemid = createNodeId("Notice");
    var notice = $('<div id="'+itemid+'" class="col-sm-12"><div class="col-sm-3 m-t-10 m-b-10"><div class="Notice-img"><img src="/wkshop/static/img/microshop/dpzxsyt-29.png"></div></div><div class="col-sm-9 m-t-10 m-b-10"><div class="Notice-left"><marquee scrollamount="3">智慧宽带</marquee></div></div></div>');
    insertNode(notice);
    delegateToItem(itemid);
}

/**
 * 创建进入商城
 */
function createToShop(){
    var itemid = createNodeId("ToShop");
    var toshop = $('<div id="'+itemid+'" class="col-sm-12"><div class="col-sm-6 text-left m-t-10 m-b-10"><img src="/wkshop/static/img/microshop/dpzxsyt-28.png" width="16"><small class="m-l-5">智慧宽带</small></div><div class="col-sm-6 text-right m-t-10 m-b-10"><small class="m-r-5">进入商城</small><span class="fa fa-angle-right"></span></div></div>');
    insertNode(toshop);
    delegateToItem(itemid);
}

/**
 * 鼠标移入移出委托，用于创建及删除元素节点的编辑层
 * @param itemid 元素节点id
 */
function delegateToItem(itemid) {
    $("#container").delegate("#" + itemid, "mousemove", function () {createpopupeditlayer(itemid);});
    $("#container").delegate("#" + itemid, "mouseleave", function () {removepopupeditlayer();});
}

/**
 * 生成节点ID
 * @param prefix 节点ID前缀
 * @returns {string} 节点ID
 */
function createNodeId(prefix) {
    return "_"+prefix + "_" + randomString(32);;
}

/**
 * 生成随机字符串
 * @param len 生成长度
 * @returns {string} 随机字符串
 */
function randomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

/**
 * 弹出消息框
 * @param title 消息框标题
 * @param content 消息内容
 * @param btntext1 按钮1文本
 * @param btntext2 按钮2文本
 * @param deltargetid 要删除的节点ID
 */
function dialogbox(title,content,btntext1,btntext2,deltargetid){
    $('div').remove('#dialogbox');
    var dialog = '<div class="modal fade" id="dialogbox" tabindex="-1" role="dialog"'+
        ' aria-labelledby="titlelabel" aria-hidden="true">'+
        '<div class="modal-dialog">'+
        '<div class="modal-content">'+
        '<div class="modal-header">'+
        '<button type="button" class="close"'+
        'data-dismiss="modal" aria-hidden="true">&times;'+
        '</button><h4 class="modal-title" id="titlelabel">'+
        title +'</h4></div>'+
        '<div class="modal-body">'+content+'</div>'+
        '<div class="modal-footer">'+
        '<button type="button" class="btn btn-default" data-dismiss="modal">'+btntext1+'</button>'+
        '<button type="button" class="btn btn-primary" data-dismiss="modal">'+btntext2+'</button>'+
        '</div></div></div>';
    $(dialog).appendTo('body');
    if(deltargetid!=null && deltargetid!='')
        $("#dialogbox").delegate(".btn-primary","click",function(){del_containel_node(deltargetid);});
}

/**
 * 公告滚动
 */
function scrollNotice(){
    var scrollPic_02 = new ScrollPic();
    scrollPic_02.scrollContId   = "scrollbox"; //内容容器ID
    scrollPic_02.arrLeftId      = "arrLeft";//左箭头ID
    scrollPic_02.arrRightId     = "arrRight"; //右箭头ID

    scrollPic_02.frameWidth     = 330;//显示框宽度
    scrollPic_02.pageWidth      = 330; //翻页宽度

    scrollPic_02.speed          = 50; //移动速度(单位毫秒，越小越快)
    scrollPic_02.space          = 100; //每次移动像素(单位px，越大越快)
    scrollPic_02.autoPlay       = false; //自动播放
    scrollPic_02.autoPlayTime   = 3; //自动播放间隔时间(秒)
    scrollPic_02.initialize(); //初始化
}

function setValues(id,selector,pagename,el_type){
    var elements = $("#"+id+" "+selector);
    $("#"+pagename+" "+el_type).each(
        function(i){
            switch(el_type){
                case "input":
                case "textarea":
                    var val = $(this).val();
                    $(elements[i]).html(val);
                    break;
                case "select":
                    var href = $(this).val();
                    $(elements[i]).attr('href',href);
                    break;
                default:break;
            }
        }
    );
}

/**
 * 图片导航设置
 */
function savePicNav(){

    if($("#rightForm").valid()){
        console.log("校验结果");
        var target = edittarget.pop();
        var navtext = $("#"+target[0].id+" h6");
        $("#PicNav_Page input[type=text]").each(
            function(i){
                var val = $(this).val();
                $(navtext[i]).html(val);
            }
        );
        var navhref = $("#"+target[0].id+" a");
        $("#PicNav_Page select").each(
            function(i){
                var href = $(this).val();
                var value=$(this).find("option:selected").text();
                $(navhref[i]).attr('href',href);
                $(navhref[i]).attr("val",value);
            }
        );
        showPage("NewModel",null);
    }else{
        swal("检查必填项不能为空");
    }
}

/**
 * 文本导航设置
 */
function saveTextNav(){
    var target = edittarget.pop();

    setValues(target[0].id,"p","TextNav_Page","input");
    setValues(target[0].id,"a","TextNav_Page","select");

    showPage("NewModel",null);
}

/**
 * 广告设置
 */
function saveAdv(){

    var target = edittarget.pop();

    setValues(target[0].id,"a","Adv_Page","select");

    showPage("NewModel",null);
}

/**
 * 公告设置
 */
function saveNotice(){
    var target = edittarget.pop();

    setValues(target[0].id,"marquee","Notice_Page","textarea");

    showPage("NewModel",null);
}

/**
 * 编辑节点信息
 * @param id
 */
function editNodeinfo(editnode){
    edittarget.push($(editnode));
    var id = editnode.id;
    var flag = editnode.id.split('_')[1];

    if(flag=="Adv21")
        showPage("Adv/21",null);
    else if(flag=="Adv31")
        showPage("Adv/31",null);
    else if(flag=="TwoColAdv")
        showPage("TwoColAdv",null);
    else if(flag=="TwoCol" || flag=="TopBottom" || flag=="LeftRight")
        showPage("ProductList",null);
    else
        showPage(flag,null);

    selectInit();
    switch (flag){
        case "PicNav":
            editPicNavinfo(id,flag);
            break;
        case "TextNav":
            editTextNavinfo(id,flag);
            break;
        case "Adv21":
            editAdvinfo(id,"Adv");
            break;
        case "Adv31":
            editAdvinfo(id,"Adv");
            break;
        case "TwoColAdv":
            editAdvinfo(id,"Adv");
            break;
        case "TwoCol":
        case "TopBottom":
        case "LeftRight":
            editProductListinfo(id,"ProductList",flag);
            break;
        case "Notice":
            break;
    }
}

/**
 * 编辑节点信息
 * @param id 节点ID
 * @param selector 要选择的元素标签
 * @param pagename 页面ID
 * @param el_type  要赋值的元素标签
 * @param el_type_selector 要赋值的元素标签类型
 */
function editValues(id,selector,pagename,el_type,el_type_selector){
    el_type_selector = el_type_selector || "";
    var elements = $("#"+id+" "+selector);
    $("#"+pagename+"_Page "+el_type+el_type_selector).each(
        function(i){
            switch(el_type){
                case "input":
                case "textarea":
                    this.value =elements[i].innerHTML;
                    break;
                case "select":
                    $(this).append("<option selected value='"+elements[i].href+"'>"+$(elements).attr("val")+"</option>");
                    break;

                default:break;
            }
        }
    );
}

/**
 * 编辑图片导航
 * @param id
 * @param pagename
 */
function editPicNavinfo(id,pagename){
    editValues(id,"h6",pagename,"input","[type=text]");
    editValues(id,"a",pagename,"select");
}

/**
 * 编辑文本导航
 * @param id
 * @param pagename
 */
function editTextNavinfo(id,pagename){
    editValues(id,"p",pagename,"input");
    editValues(id,"a",pagename,"select");
}

/**
 * 编辑广告
 * @param id
 * @param pagename
 */
function editAdvinfo(id,pagename){
    editValues(id,"a",pagename,"select");
}

/**
 * 编辑商品列表
 * @param id
 * @param pagename
 */
function editProductListinfo(id,pagename,flag){
    editValues(id,"label",pagename,"input","[type=text]");
    editValues(id,"a",pagename,"select");
    $("#productlistinfo").append(createProductListPre(flag));
}

/**
 * 动态创建商品列表底部预览
 * @param type 列表类型
 * @returns {*} 返回创建的节点，不在列表内的不创建，返回null值
 */
function createProductListPre(type){
    var node = null;
    switch (type){
        case "TopBottom":/** 大图 **/
            node = $(' <div class="module_701"><div class="single-line style_1"> <a class="item" href="http://weidian.com/i/1886296077"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466650375975-6976235.jpg?w=480&amp;h=0"> <p class="module_701-item-sold">销量0</p> </div> <p class="module_701-item-title">sdfsf</p><p class="module_701-item-price">¥ 2.00</p> </a> </div> </div>');
            return node;
        case "LeftRight":/** 列表 **/
            node = $(' <div class="module_701"> <div class="three-line"> <div class="item-wrap"> <a class="item" href="http://weidian.com/i/1829265829"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466650395101-480766.jpg?w=400&amp;h=400&amp;cp=1"> <p class="module_701-item-sold">销量0</p> </div> <p class="module_701-item-title">顶顶顶顶顶顶顶顶顶顶</p> <p class="module_701-item-price">¥ 131.00</p> </a> </div> <div class="item-wrap"> <a class="item" href="http://weidian.com/i/1886295522"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466591061648-6181072.png?w=400&amp;h=400&amp;cp=1"> <p class="module_701-item-sold">销量0</p> </div> <p class="module_701-item-title">dsfds</p> <p class="module_701-item-price">¥ 32.00</p> </a> </div> </div> </div>');
            return node;
        case "TwoCol": /** 两列 **/
            node = $('<div class="module_701"  > <ul class="double-line style_2"> <li class="item-wrap"> <a class="item" href="#"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466650375975-6976235.jpg?w=400&amp;h=400&amp;cp=1"> <#--<p class="module_701-item-sold">销量0</p>--> </div> <p class="module_701-item-title">商品1</p> <p class="module_701-item-price">¥ 2.00</p> </a> </li> <li class="item-wrap"> <a class="item" href="#"> <div class="module_701-item-image"> <img src="http://wd.geilicdn.com/vshop771308584-1466650395101-480766.jpg?w=400&amp;h=400&amp;cp=1"> <#--<p class="module_701-item-sold">销量0</p>--> </div> <p class="module_701-item-title">商品2</p> <p class="module_701-item-price">¥ 131.00</p> </a> </li> </ul> <div style="clear:both;"></div> </div>');
            return node;
        default:return null;
    }
}


/**
 * 初始化下拉选项
 */
function selectInit(){

    var opt = "<option value='' selected>请选择链接</option><option value='1'>商品</option><option value='2'>商品分类</option><option value='3'>自定义页面</option><option value='4'>其他链接</option>";
    $("select[id!='subSelect']").empty();
    $("select[id!='subSelect']").append(opt);
    $("select[id!='subSelect']").on("change",function(e){
        $("#subSelect-modal input[id='parentSelect']").val($(this).attr("id"));
        $("#subSelect-modal").modal("toggle");
    })
}


function closeModal(_self){
    $("#"+$(_self).attr("id")).modal("toggle");
}

function returnSelectLink(){
    console.log("值："+$("#subSelect").val());
    var opt="<option selected value='"+$("#subSelect").val()+"'>" +$("#subSelect").find("option:selected").text()+" </option>";
    console.log($("#parentSelect").val());
    $("#"+$("#parentSelect").val()).append(opt);
    $("#subSelect-modal").modal("toggle");

}


function savePage(){
    console.log($("#container").html());
    //if($("#rightForm").valid()) {
        $.ajax({
            url: "savePage",
            type: "post",
            data: {
                pagecontent: $("#container").html(),
                title: $("#title").val(),
                dsp: $("#dsp").val(),
                id: $("#id").val(),
                pageid: $("#pageid").val()
            },
            success: function (d) {
                if (d != '') {
                    swal("保存成功!");
                    $("#btnPreView").removeAttr("disabled");
                    $("#btnPreView").addClass("btn-info");

                }
            }
        })
    //}else{
    //    swal("必填项不能为空");
    //}
}

function previewPage(){
    console.log("预览");
    $("#pageContent").val($("#container").html());
    $("#previewForm").submit();
}


function keyP(v){
    $("#pre_title").html(v.value);
}


function inidEditPage(){
    $.ajax({
        url:"queryPage",
        data:{id:$("#id").val()},
        type:"post",
        success:function(d){
            $("#container").append(d.pagecontent);
            $("#container div[id^='_']").each(function(){
                console.log("id=",$(this).attr("id"));
                delegateToItem($(this).attr("id"));
                $("#btnPreView").removeAttr("disabled");
            })
        }
    })
}