


<script type="text/template" id="module_1">
    <div class="module_1" style="{{styleText}}">{{moduleName}}</div>
</script>
<script id="module_200" type="text/template">
    <div class="module_200">
        <a class="module_200-shop" href="#">
            <span class="module_200-shop-item module_200-headimg" >
                <img src="/wkshop/static/img/microshop/dpzxsyt-29.png">
            </span>
            <span class="module_200-shop-item module_200-shopname">
                <marquee behavior="scroll"  direction="left" align="middle">{{title}}</marquee>
            </span>

        </a>
    </div>
</script>
<script id="module_201" type="text/template"><div class="module_201">
<form class="search-box dcr-search-form" action="http://weidian.com/search_result.html">
    <input name="userid" class="dcr-search-name" value="{{userID}}" type="hidden">
    <input type="submit" class="search-btn" value=""/>
    <input name="tb_search" class="search-key" type="search" placeholder="商品搜索：请输入商品关键字" >
</form>
</div>
</script>
<script type="text/template" id="module_202"><div class="module_202">
<a class="module_202-shop" href="{{shopUrl}}">
    <span class="module_202-shop-item module_202-headimg"><img src="${basepath}/static/img/microshop/shop-logo-default-2.jpg"></span>
    <span class="module_202-shop-item module_202-shopname">{{shopName}}</span>
    <span class="module_202-shop-item module_202-btn">进入店铺</span>
</a>
</div>
</script>
<script id="module_301" type="text/template">
    <div class="module_301">
        {{each content}}
        <div class="module_301-word-nav">
            <a {{if $value.link.url}}href="{{$value.link.url}}"{{/if}}>
            <div class="module_301-name">{{$value.name}}</div>
            </a>
        </div>
        {{/each}}
    </div></script><script id="module_302" type="text/template"><div class="module_302">
<div class="module_302-title">{{title}}</div>
<div class="module_302-foot">
    <a>
        <span>{{date}}</span>
        <span>{{author}}</span>
    </a>
    <a {{if link.url}}href="{{link.url}}"{{/if}} class="module_302-link-title">{{link_title}}</a>
</div>
</div></script><script id="module_501" type="text/template"><div class="module_501">
{{if show_gap == true}}
<div class="show-gap">
    {{each content}}
    <a class="no-gap" {{if $value.link.url}}href="{{$value.link.url}}"{{/if}} >
    <img src="{{$value.image}}" />
    </a>
    {{/each}}
</div>
{{/if}}
{{if show_gap == false}}
{{each content}}
<a {{if $value.link.url}}href="{{$value.link.url}}"{{/if}} class="no-gap">
<img src="{{$value.image}}"  />
</a>
{{/each}}
{{/if}}
</div></script><script id="module_502" type="text/template"><div class="module_502">
<ul class="{{if show_gap == true}}module_502_gap{{else}}module_502_no_gap{{/if}}">
    {{each content }}
    <li>
        <a {{if $value.link.url}}href="{{$value.link.url}}"{{/if}}>
        <div class="module_502-pic" style="background:url('{{$value.image}}') no-repeat center center;background-size:cover">
            <div style="margin-top: 75%;"></div>
        </div>
        </a>
    </li>
    {{/each}}
</ul>
</div></script>
<script id="module_503" type="text/template">
    <div class="banner">
        <ul>
            {{each content}}
            {{if $value.image}}
            <li>
                <a {{if $value.link}}href="{{$value.link.url}}"{{/if}}>
                <img  src="{{$value.image}}"/>
                </a>
            </li>
            {{/if}}
            {{/each}}
        </ul>
        <ol class="dots">
            {{each content}}
            {{if $value.image}}
            {{if $index==0}}
            <li class="dot active">1</li>
            {{else}}
            <li class="dot">1</li>
            {{/if}}
            {{/if}}
            {{/each}}
        </ol>

    </div>
</script>
<script id="module_504" type="text/template">
    <div class="module_504">

        {{each content}}
        {{$value.link}}
        <div class="show-box">
            <a  class="show-name" {{if $value.link}} href="{{$value.link.url}}" {{/if}}>
            <img src={{$value.image}} class="image"/>
            <p>{{$value.title}}</p>
            </a>
        </div>
        {{/each}}


    </div>
</script>
<script type="text/template" id="module_505"><div class="module_505">
<div class="module_505-map">
    <a href="http://apis.map.qq.com/tools/poimarker?type=0&marker=coord:{{lat}},{{lng}};title:{{notice}}&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp" alt="地图">
        <img src="http://apis.map.qq.com/ws/staticmap/v2/?key=KUMBZ-WL4WV-TZJPH-UOMEM-D452E-4GFWB&size=750*320&center={{lat}},{{lng}}&markers=color:blue|label:A|{{lat}},{{lng}}&zoom=13"></img>
    </a>
    {{if notice}}<div class="module_505-notice">{{notice}}</div>{{/if}}
</div>
</div></script><script id="module_701" type="text/template"><div class="module_701">
{{if options.title}}
<div class="model_701-title {{if options.title_pos=='center'}}model_701-title-center{{/if}}" >{{options.title}}</div>
{{/if}}

{{if options.model == 1}}

<div class="single-line style_{{options.style}}">
    {{each content}}
    <a class="item" href="{{$value.h5url}}">
        <div class="module_701-item-image">
            <img src="{{$value.src_img}}">
            {{if options.show_item_sale}}
            <p class="module_701-item-sold">销量{{$value.sold}}</p>
            {{/if}}
        </div>
        {{if options.show_item_name && options.show_item_price}}
        <p class="module_701-item-title">{{$value.itemName}}</p>
        <p class="module_701-item-price">¥ {{$value.price}}</p>

        {{else if options.show_item_name && !options.show_item_price}}
        <p class="module_701-item-title module_701-item-only-one">{{$value.itemName}}</p>

        {{else if !options.show_item_name && options.show_item_price}}
        <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
        {{/if}}


    </a>
    {{/each}}
</div>

{{else if options.model == 2}}

<ul class="double-line style_{{options.style}}">
    {{each content}}
    <li class="item-wrap">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale && options.style != 3}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name && options.show_item_price}}
            <p class="module_701-item-title">{{$value.itemName}}</p>
            <p class="module_701-item-price">¥ {{$value.price}}</p>

            {{else if options.show_item_name && !options.show_item_price}}
            <p class="module_701-item-title module_701-item-only-one">{{$value.itemName}}</p>

            {{else if !options.show_item_name && options.show_item_price}}
            <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </li>
    {{/each}}
</ul>
<div style="clear:both;"></div>
{{else}}
<div class="three-line">
    {{each content}}
    <div class="item-wrap">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name}}
            <p class="module_701-item-title">{{$value.itemName}}</p>
            {{/if}}
            {{if options.show_item_price}}
            <p class="module_701-item-price">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </div>
    {{/each}}
</div>

{{/if}}
</div></script><script id="module_702" type="text/template"><div class="module_702">
{{if options.title}}
<div class="model_701-title {{if options.title_pos=='center'}}model_701-title-center{{/if}}" >{{options.title}}</div>
{{/if}}

{{if options.model == 1}}

<div class="single-line style_{{options.style}}">
    {{each content}}
    <a class="item" href="{{$value.h5url}}">
        <div class="module_701-item-image">
            <img src="{{$value.src_img}}">
            {{if options.show_item_sale}}
            <p class="module_701-item-sold">销量{{$value.sold}}</p>
            {{/if}}
        </div>
        {{if options.show_item_name && options.show_item_price}}
        <p class="module_701-item-title">{{$value.itemName}}</p>
        <p class="module_701-item-price">¥ {{$value.price}}</p>

        {{else if options.show_item_name && !options.show_item_price}}
        <p class="module_701-item-title module_701-item-only-one">{{$value.itemName}}</p>

        {{else if !options.show_item_name && options.show_item_price}}
        <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
        {{/if}}


    </a>
    {{/each}}
</div>

{{else if options.model == 2}}

<ul class="double-line style_{{options.style}}">
    {{each content}}
    <li class="item-wrap">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale && options.style != 3}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name && options.show_item_price}}
            <p class="module_701-item-title">{{$value.itemName}}</p>
            <p class="module_701-item-price">¥ {{$value.price}}</p>

            {{else if options.show_item_name && !options.show_item_price}}
            <p class="module_701-item-title module_701-item-only-one">{{$value.itemName}}</p>

            {{else if !options.show_item_name && options.show_item_price}}
            <p class="module_701-item-price module_701-item-only-one">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </li>
    {{/each}}
</ul>
<div style="clear:both;"></div>
{{else}}
<div class="three-line">
    {{each content}}
    <div class="item-wrap">
        <a class="item" href="{{$value.h5url}}">
            <div class="module_701-item-image">
                <img src="{{$value.src_img}}">
                {{if options.show_item_sale}}
                <p class="module_701-item-sold">销量{{$value.sold}}</p>
                {{/if}}
            </div>
            {{if options.show_item_name}}
            <p class="module_701-item-title">{{$value.itemName}}</p>
            {{/if}}
            {{if options.show_item_price}}
            <p class="module_701-item-price">¥ {{$value.price}}</p>
            {{/if}}
        </a>
    </div>
    {{/each}}
</div>

{{/if}}

{{if options.show_more}}
<p class="show-more"><a href="{{options.show_more_url}}" class="btn-show-more" data-id="{{options.category_id}}" data-current="{{options.category_length}}" data-userid="{{options.userid}}">更多商品</a></p>
{{/if}}
</div></script><script type="text/template" id="module_97"><div class="module_97">
<div class="module_97-line" style="height:{{height}};"></div>
</div></script><script type="text/template" id="module_98"><div class="module_98">
<div class="module_98-line"></div>
</div></script><script id="module_99" type="text/template"><div class="module_99" id="module_99_{{id}}" style="background-color:{{backgroundColor}};">
{{if content}}
<div>{{#content}}</div>
{{/if}}
</div></script>
