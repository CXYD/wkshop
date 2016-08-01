var ShopPage = function(){
    this.id = null;
    this.khid = null;
    this.title = null;
    this.skin = Object();
    this.desc = null;
    this.tplInfos = [];
    this.flag = null;
}

ShopPage.prototype.setId = function(id){
    this.id = id;
}
ShopPage.prototype.getId = function(){
    return this.id ;
}

ShopPage.prototype.setKhid = function(khid){
    this.khid = khid;
}
ShopPage.prototype.getKhid = function(){
    return this.khid;
}

ShopPage.prototype.setTitle = function(title){
    this.title = title;
}
ShopPage.prototype.getTitle = function(title){
    return this.title;
}


//ShopPage.prototype.set = function(key,value){
//    this.skin[key] = value;
//}

ShopPage.prototype.setSkin = function(data){
    if(data != null && typeof(data) == "object"){
        for(var key in data){
            this.skin[key] = data[key];
        }
    }
}

ShopPage.prototype.getSkin = function(){
    return this.skin;
}

ShopPage.prototype.setDesc = function(desc){
    this.desc = desc;
}

ShopPage.prototype.getDesc = function(){
    return this.desc;
}


ShopPage.prototype.setTplInfos = function(tpls){
    if(tpls instanceof Array){
        this.tplInfos = tpls;
    }
}

ShopPage.prototype.getTplInfos = function(){
    return this.tplInfos;
}

ShopPage.prototype.addTplInfo = function(tpl){
    if(tpl != null){
        this.tplInfos.push(tpl);
    }
}


ShopPage.prototype.removeLastTplInfo = function(){
    this.tplInfos.pop();
}


ShopPage.prototype.insertTplInfo = function(index,tpl){
    if(tpl != null){
        this.tplInfos.splice(index,0,tpl);
    }
}

ShopPage.prototype.delTplInfo = function(index){
    if(index != -1){
        this.tplInfos.splice(index,1);
    }
}

ShopPage.prototype.delTplObj = function(tpl){
    if(tpl != null){
        var i = this.tplInfos.indexOf(tpl);
        if( i != -1){
            thi.tplInfos.splice(i,1);
        }
    }
}


ShopPage.prototype.setFlag = function(flag){
    this.flag = flag;
}

ShopPage.prototype.getFlag = function(){
    return this.flag;
}


ShopPage.prototype.setParam = function(key,value){
    this[key] = value;
}
ShopPage.prototype.removeParam = function(key){
    if(key != null)
    delete this.skin[key];
}


