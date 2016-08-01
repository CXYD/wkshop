$(function(){
    //initUserMenu();
})

function initUserMenu(){
    $.ajax({url: '/menus', success: function(menu){
        setMenu($('.list-unstyled'),menu);
    }});
}

function setMenu(menuid,menus){

    console.log("setMenu");

    var menuhtm = [];
    $.each(menus,function(n,menudata) {
        menuhtm.push("<li id=\"menu");
        menuhtm.push(menudata.menuId);
        menuhtm.push("\" class=\"treeview\">");
        // menuhtm.push("<li class=\"treeview\">");
        menuhtm.push("<a href=\"#\">");
        menuhtm.push("<i class=\"fa "+getMenuIcon(menudata.menuType)+"\"></i> <span>");
        menuhtm.push(menudata.menuName);
        if(menudata.subMenus.length>0){
            menuhtm.push("</span><i class=\"fa fa-angle-left pull-right\"></i></a>")
            menuhtm.push(setSecondLevelMenu(menudata.subMenus));
        }else{
            menuhtm.push("</a>")
        }
        menuhtm.push("</li>");
    })
    menuid.append('' + menuhtm.join('') + '');
}

function setSecondLevelMenu(menus){
    var menuhtm = [];
    menuhtm.push("<ul class=\"treeview-menu\" style=\"display: none;\">");
    $.each(menus,function(n,menudata) {
        menuhtm.push("<li><a href=\"javascript:loadContext('");
        menuhtm.push(menudata.menuUrl);
        menuhtm.push("');\"><i class=\"fa "+getMenuIcon(menudata.menuType)+"\"></i>"+menudata.menuName);
        // menuhtm.push("<li><a href=\"#\"><i class=\"fa "+getMenuIcon(menudata.menuType)+"\"></i>"+menudata.menuName);
        if(menudata.subMenus.length>0){
            menuhtm.push("<i class=\"fa fa-angle-left pull-right\"></i></a>")
            menuhtm.push(setThirdLevelMenu(menudata.subMenus));
        }else{
            menuhtm.push("</a>")
        }
        menuhtm.push("</li>");
    })
    menuhtm.push("</ul>");
    return '' + menuhtm.join('') + '';
}

function setThirdLevelMenu(menus){
    var menuhtm = [];
    menuhtm.push("<ul class=\"treeview-menu\">");
    $.each(menus,function(n,menudata) {
        menuhtm.push("<li><a href=\"javascript:loadContext('");
        menuhtm.push(menudata.menuUrl);
        menuhtm.push("');\"><i class=\"fa "+getMenuIcon(menudata.menuType)+"\"></i>"+menudata.menuName+"</a>");
    })
    menuhtm.push("</ul>");
    return '' + menuhtm.join('') + '';
}

function getMenuIcon(menutype){
    var iconClass;
    switch(menutype){
        case "1" : iconClass="fa-cloud"; break;//Zone
        case "2" : iconClass="fa-cubes"; break;//clust group
        case "3" : iconClass="fa-cogs"; break;//clust node
        case "4" : iconClass="fa-codepen"; break; //host group
        case "5" : iconClass="fa-circle"; break;//host node
        case "6" : iconClass="fa-th"; break; // project group
        case "7" : iconClass="fa-file"; break;//project node
        case "8" : iconClass="fa-circle-o"; break;//node
    }
    return iconClass;
}


