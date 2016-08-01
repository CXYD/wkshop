function resetBtn(form){
    $("#"+form)[0].reset();
}

/**
 * 表单校验
 * @param $obj 表单对象
 * @param errormsg 出错信息
 * @returns {Boolean} 校验结果
 */
function validate($obj,errormsg){
    var errorLabel = "<label name=\"errorLabel\" class=\"control-label\" for=\"inputError\"><i class=\"fa fa-times-circle-o\"></i>"+errormsg+"</label>";
    var value = $obj.val();
    if($.trim(value) == ''){
        $obj.parent().append(errorLabel);
        $obj.parent().addClass("has-error");
        return false;
    }else{
        if($obj.parent().find("[name='errorLabel']").length > 0){
            $obj.parent().find("[name='errorLabel']").remove();
        }
        return true;
    }
}


function destroyValidator(form){
    $("#"+form).bootstrapValidator("destroy");

}



function loadFormData(formId,jsonStr){
    var obj = eval("("+jsonStr+")");
    var key,value,tagName,type,arr;
    for(x in obj){
        key = x;
        value = obj[x];
        $("[name='"+key+"']").each(function(){
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if(tagName=='INPUT'){
                if(type=='radio'){
                    $(this).prop('checked',$(this).val()==value);
                }else if(type=='checkbox'){
                    arr = String(value).split(',');
                    for(var i =0;i<arr.length;i++){
                        $(this).prop('checked',false);
                        if($(this).val()== arr[i]){
                            $(this).prop('checked',true);
                            break;
                        }
                    }
                }else{
                    $(this).val(value);
                }
            }else if(tagName=='SELECT'){
                var dataArray = String(value).split(',');
                if(typeof $(tagName).attr("multiple")=="undefined"){
                    $(this).multiselect('select', dataArray[0]);
                    $(this).multiselect("refresh");
                }else{
                    $(this).multiselect('deselectAll',false);
                    $(this).multiselect('select', dataArray);
                    $(this).multiselect("refresh");
                }
            }else if(tagName=='TEXTAREA'){
                $(this).val(value);
            }
        });
    }
}


