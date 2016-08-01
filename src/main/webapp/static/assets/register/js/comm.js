/**
 * Created by 14-5-22.
 */

/**
 * 正则判断邮箱地址
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsEmail(val)
{
    var pattern = /^[0-9a-zA-Z](\w)+@(\w)+(\.)(com|cn|net|edu|con(\.)cn)$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则判断邮箱地址
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsAdsl(val)
{
    var pattern = /^\d{9,13}$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则判断手机号
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsTelephone(val)
{
    var pattern = /^(?:13\d|15\d|18[123456789])-?\d{5}(\d{3}|\*{3})$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则固定电话号
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsPhone(val)
{
    var pattern = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}


/**
 * 正则判断是否含有特殊字符
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsCharmap(val)
{
    var pattern = /[\':;*?~`!@#$%^&+={}\[\]\<\>\(\),\.]/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则判断是否为身份证号
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsIdCard(val)
{
    var pattern = /^(\d{15}$)|(^\d{17}([0-9]|X|x))$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则判断密码
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsPassword(val)
{
    var pattern = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}


/**
 * 打开div by id
 * @param val
 */
function openDiv(val) {
    $("#"+val).OpenDiv();
}

/**
 * 关闭div by id
 * @param val
 */
function closeDiv(val) {
    $("#"+val).CloseDiv();
}

/**
 * 正则判断邮箱地址
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsEmail(val)
{
    var pattern = /^[0-9a-zA-Z](\w)+@(\w)+(\.)(com|cn|net|edu|con(\.)cn)$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则宽带帐号
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsAdsl(val)
{
    var pattern = /^[0-9a-zA-z]{10,12}$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则判断手机号
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsTelephone(val)
{
    var pattern = /^(?:13\d|15\d|18[123456789])-?\d{5}(\d{3}|\*{3})$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则固定电话号
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsPhone(val)
{
    var pattern = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}


/**
 * 正则判断是否含有特殊字符
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsCharmap(val)
{
    var pattern = /[\':;*?~`!@#$%^&+={}\[\]\<\>\(\),\.]/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则判断是否为身份证号
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsIdCard(val)
{
    var pattern = /^(\d{15}$)|(^\d{17}([0-9]|X|x))$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 正则判断密码
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsPassword(val)
{
    var pattern = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,20}$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 去除左右空格
 * @param val
 * @returns {string}
 * @constructor
 */
function trim(val){
    return val.replace(/[ ]/g,"");
}

/**
 * 同步ajax get传值
 * @param url
 * @returns {*}
 */
function ajaxGet(url) {
    var flag;
    var xmlHttp;
    //创建XMLHttpRequest对象
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XmlHttp");
    } else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    //接收页面返回的数据
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                flag = xmlHttp.responseText;
            }
        }
    }
    xmlHttp.open("GET", url, false);
    xmlHttp.send(null);
    return flag;
}


/**
 * 同步ajax post传值
 * @param url
 * @returns {*}
 */
function ajaxPost(url,data) {

    var flag;
    var xmlHttp;
    //创建XMLHttpRequest对象
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XmlHttp");
    } else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    //接收页面返回的数据
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                flag = xmlHttp.responseText;
            }
        }
    }
    xmlHttp.open("POST", url, false);
    xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");//post
    xmlHttp.send(data);
    return flag;
}

/**
 * 初始化单选框值
 * @param rName
 * @param rValue
 */
function InitRadio(rName,rValue){
    var rObj = document.getElementsByName(rName);
    for(var i = 0;i < rObj.length;i++){
        if(rObj[i].value == rValue){
            rObj[i].checked =  'checked';
        }
    }
}

/**
 * 为选中的复选框赋值
 * @param rName
 * @param rValue
 */
function SetCheckVal(rName,rValue){
    var rObj = document.getElementsByName(rName);
    for(var i = 0;i < rObj.length;i++){
        if(rObj[i].checked ){
            rObj[i].value =  rValue;
        }
    }
}

/**
 * 按名称获取元素
 * @param Name
 * @returns {NodeList}
 * @constructor
 */
function GetElementsByName(Name){
    return document.getElementsByName(Name);
}

/**
 * 为选中的复选框赋值
 * @param rName
 * @param rValue
 */
function SetValByName(rName,rValue){
    var rObj = document.getElementsByName(rName);
    for(var i = 0;i < rObj.length;i++){
        rObj[i].value =  rValue;
    }
}

/**
 * 获取多选选中的值
 * @param rName
 * @returns {string|value|*|value|value|value}
 * @constructor
 */
function GetCheckVal(rName){
    var methods = document.getElementsByName(rName);
    for(var i=0;i<methods.length;i++){
        if(methods[i].checked){
            return methods[i].value;
        }
    }
}

/**
 * 按名称 获取多选选中的元素
 * @param rName
 * @returns {string|value|*|value|value|value}
 * @constructor
 */
function GetCheckElements(rName){
    var methods = document.getElementsByName(rName);
    var els = [];
    var idx = 0;
    for(var i=0;i<methods.length;i++){
        if(methods[i].checked){
            els[idx] = methods[i];
            idx++;
        }
    }
    return els;
}

/**
 * 按样式名称 获取选中的元素
 * @param cName
 * @returns {string|value|*|value|value|value}
 * @constructor
 */
function GetClassElementsByChk(cName){
    var methods = document.getElementsByClassName(cName);
    var els = [];
    var idx = 0;
    for(var i=0;i<methods.length;i++){
        if(methods[i].checked){
            els[idx] = methods[i];
            idx++;
        }
    }
    return els;
}

/**
 * 按样式名称 获取元素
 * @param cName
 * @returns {string|value|*|value|value|value}
 * @constructor
 */
function GetClassElements(cName){
    var els = document.getElementsByClassName(cName);
    return els;
}


/**
 * 复选框实现单选功能
 * @param rName
 * @param rValue
 */
function CheckboxRadio(rName,rValue){
    var rObj = document.getElementsByName(rName);
    for(var i = 0;i < rObj.length;i++){
        if(rObj[i].value == rValue){
            rObj[i].checked =  true;
        }else{
            rObj[i].checked =  false;
        }
    }
}

/**
 * 复选框全部取消勾选
 * @param rName
 */
function CheckboxFalse(rName){
    var rObj = document.getElementsByName(rName);
    for(var i = 0;i < rObj.length;i++){
        rObj[i].checked =  false;
    }
}

/**
 * 复选框全部勾选
 * @param rName
 */
function CheckboxTrue(rName){
    var rObj = document.getElementsByName(rName);
    for(var i = 0;i < rObj.length;i++){
        rObj[i].checked =  true;
    }
}

/**
 * 按类名隐藏
 * @param rClass
 * @constructor
 */
function HideByClass(rClass){
    var rObj = document.getElementsByClassName(rClass);
    for(var i = 0;i < rObj.length;i++){
        rObj[i].style.display = "none";
    }
}

/**
 * 按类名显示
 * @param rClass
 * @constructor
 */
function ShowByClass(rClass){
    var rObj = document.getElementsByClassName(rClass);
    for(var i = 0;i < rObj.length;i++){
        rObj[i].style.display = "block";
    }
}

/**
 * 隐藏
 * @param rId
 * @constructor
 */
function HideById(rId){
    document.getElementById(rId).style.display = "none";
}

/**
 * 显示
 * @param rId
 * @constructor
 */
function ShowById(rId){
    document.getElementById(rId).style.display = "block";
}

/**
 * GetValById
 * @param rId
 * @returns {string|value|*|value|value|value}
 * @constructor
 */
function GetValById(rId){
    return trim(document.getElementById(rId).value);
}

/**
 * SetValById
 * @param rId
 * @param rVal
 * @constructor
 */
function SetValById(rId,rVal){
    document.getElementById(rId).value = rVal;
}

/**
 * js  innerHtml
 * @param rId  id
 * @param rVal 值
 * @returns {*}
 * @constructor
 */
function InnerHTMLById(rId,rVal){
    return document.getElementById(rId).innerHTML = rVal;
}

/**
 * 表单提交
 * @param rId
 * @constructor
 */
function SubFormById(rId){
    document.getElementById(rId).submit();
}

/**
 * 返回
 */
function GoBack(){
    window.history.go(-1);
}

/**
 * 检验姓名：姓名是2-15字的汉字
 */
function IsCardName(s)
{
    var patrn = /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
    if(!patrn.exec(s))
    {
        return false;
    }
    return true;
}


//region 客户端API调用函数
function ApiSystemError(msg){
    this.name = 'ApiError';
    this.message = msg;
}

function callAndroid(name, args){
    if(!args) args = '[]';
    var ret = window.prompt('wokuan_' + name, args );
    if(ret && typeof ret == 'string'){
        return ret;
    }else{
        throw new ApiSystemError('Api returned nothing!');
    }
}

/**
 * 设置标题
 * @param newtitle 标题名称
 * @constructor
 */
function SetTitle( newtitle ) {
    callAndroid('SetTitle',newtitle );
}

/**
 * 关闭窗口
 * @constructor
 */
function CloseWindow() {
    callAndroid('CloseWindow');
}

/**
 * 弹窗提示
 * @param title       弹窗提示头文字
 * @param content     弹窗提示内容，换行使用\r\n
 * @param buttonname1 第一个按钮名称
 * @param buttonname2 第二个按钮名称
 * @returns {*}
 * @constructor
 */
function MessageBox( title ,content , buttonname1 , buttonname2 ) {
    var ret = callAndroid('PopupMessageBox', title + '_' + content + '_' + buttonname1 + '_' + buttonname2 );
    return ret;
}

/**
 * 获取网络连接状态
 * @returns {*}
 * @constructor
 */
function GetOnline() {
    var ret = callAndroid('GetOnline');
    return ret;
}

function GetVersion() {
    var ret = callAndroid('GetVersion');
    return ret;
}

function DoUpdate() {
    var ret = callAndroid('DoUpdate');
    // alert( 'ret=' + ret );
    return ret;
}

function DoFeedBack() {
    var ret = callAndroid('DoFeedBack');
    return ret;
}

function ShowAboutUS() {
    var ret = callAndroid('ShowAboutUS');
    return ret;
}

function DoLogout() {
    var ret = callAndroid('DoLogout');
    return ret;
}

function GetUUID() {
    var ret = callAndroid('GetUUID');
    return ret;
}

function SetStatusIcon() {
    var ret = callAndroid('SetStatusIcon');
    return ret;
}

function PopSpeed() {
    var ret = callAndroid('PopSpeed');
    return ret;
}

function PopShare() {
    var ret = callAndroid('PopShare');
    return ret;
}

function PopupBrowser(url) {
    callAndroid('PopupBrowser',url);
}


function PopupWeb(title,url) {
    callAndroid('PopupWeb',title+'_'+url);
}

function PopSpeedBag( bodType ) {
    callAndroid('PopSpeedBag',bodType);
}

//endregion



function json_decode(str_json) {
    var json = this.window.JSON;
    if (typeof json === 'object' && typeof json.parse === 'function') {
        try {
            return json.parse(str_json);
        } catch (err) {
            if (!(err instanceof SyntaxError)) {
                throw new Error('Unexpected error type in json_decode()');
            }
            this.php_js = this.php_js || {};
            this.php_js.last_error_json = 4; // usable by json_last_error()
            return null;
        }
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
    var j;
    var text = str_json;

    cx.lastIndex = 0;
    if (cx.test(text)) {
        text = text.replace(cx, function(a) {
            return '\\u' + ('0000' + a.charCodeAt(0)
                    .toString(16))
                    .slice(-4);
        });
    }
    if ((/^[\],:{}\s]*$/)
            .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
                .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
        j = eval('(' + text + ')');
        return j;
    }

    this.php_js = this.php_js || {};
    this.php_js.last_error_json = 4; // usable by json_last_error()
    return null;
}

function get_select_radio_val(radioname){
    var chkObjs = document.getElementsByName(radioname);
    for(var i=0;i<chkObjs.length;i++){
        if(chkObjs[i].checked){
            return chkObjs[i].value;
        }
    }
    return null;
}


//region 动态增加断网提示，暂时没用到
/**
 * 判断是否为元素集合
 * @param obj
 * @returns {boolean}

 function isCollection (obj) {
    return obj.propertyIsEnumerable('length') && typeof obj === 'object' && typeof obj.length === 'number';
}; */

/************************************
 * 添加事件绑定
 * @param objarr : 要绑定事件的元素
 * @param type : 事件名称。不加 "on". 如 : "click" 而不是 "onclick".
 * @param fn : 事件处理函数
 ***********************************
 function bind(objarr, type, fn) {
    if(isCollection(objarr)){
        for (var obj in objarr)
        {
            if (objarr[obj].attachEvent) {
                objarr[obj]['e' + type + fn] = fn;
                objarr[obj][type + fn] = function () {
                    objarr[obj]['e' + type + fn](window.event);
                }
                objarr[obj].attachEvent('on' + type, objarr[obj][type + fn]);
            } else
                objarr[obj].addEventListener(type, fn, false);
        }
    }
}*/

/*
 function onClick(event) {
 try {
 var ret = GetOnline();
 if(ret==0){
 alert('请先连接网络后再试！');
 return false;
 }
 }
 catch (e) {
 }
 }

 bind(document.getElementsByTagName("a"),"click",onClick);*/
//endregion

function jPost(url,data){

    var ret;
    $.post(url, data, function (text, status) {

        ret = text;
        alert("text:"+text);
    });

    return ret;
}


function jAjax(url,data,async){

    var ret;
    $.ajax({
        type: "GET",
        url: url,
        data: data,
        dataType: "json",
        async:async,
        success: function(data){
            ret = data;
        }});

    return ret;
}
/**
 * 根据标签的name值获取标签数组元素
 * @param tag 标签
 * @param name 标签的name值
 */
function getElementsByName(tag, name) {
    var returns = document.getElementsByName(name);
    if (returns.length > 0)
        return returns;
    returns = new Array();
    var e = document.getElementsByTagName(tag);
    for (var i = 0; i < e.length; i++) {
        if (e[i].getAttribute("name") == name) {
            returns[returns.length] = e[i];
        }
    }
    return returns;
}
/**
 * 正则判断联通号段手机号
 * @param val
 * @returns {boolean}
 * @constructor
 */
function IsUnicomMobile(val)
{
    var pattern = /^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\d{8}$/;
    if (pattern.test(val)) {
        return true;
    }else {
        return false;
    }
}
var wait=120;
var canSend = true;

function time(o) {

    if (wait == 0) {
    	canSend =true;
        o.removeAttribute("disabled");

        o.value="获取验证码";

        wait = 120;

    } else {

        o.setAttribute("disabled", true);

        o.value="重新发送(" + wait + ")";

        wait--;

        setTimeout(function() {

                time(o);

            },

        1000);

    }

}


/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
        num = num.substring(0,num.length-(4*i+3))+','+
            num.substring(num.length-(4*i+3));
    return ('￥'+((sign)?'':'-') + num + '.' + cents);
}

/**
 * 将数值四舍五入(保留1位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.4'
 * @type String
 */
function formatCurrencyTenThou(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*10+0.50000000001);
    cents = num%10;
    num = Math.floor(num/10).toString();
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
        num = num.substring(0,num.length-(4*i+3))+','+
            num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}