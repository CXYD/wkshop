var zkPath = "";
var zkId = "";
function loadContext(url,copy){

    if(intervalId)
        clearInterval(intervalId);
    if(copy && mmgrid){
        var rows =  mmgrid.selectedRows();
        var length = rows.length;
        if(length > 0){
            data = rows[0];
        }else{
            alert("请选择复制的条目!");
            return;
        }
    }

    zkPath=getParam(url,"zkpath");
    zkId=getParam(url,"zkid");
    $(".wraper.container-fluid").load(url,function(response,status,xhr){
        $(".content").resize(function(){});

    });
}
