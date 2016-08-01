<script>
    // 加载piwiki 组件，便于上传onerrorlog

    //监控 onerror事件


    (function(win) {
        var doc = win.document;
        var docEl = doc.documentElement;
        var metaEl = doc.querySelector('meta[name="viewport"]');
        var flexibleEl = doc.querySelector('meta[name="flexible"]');
        var dpr = 0;
        var scale = 0;
        var tid;
        var flexible = win.flexible || (win.flexible={});

        if (metaEl) {
            var match = metaEl.getAttribute('content').match(/initial\-scale=([\d\.]+)/);
            if (match) {
                scale = parseFloat(match[1]);
                dpr = parseInt(1 / scale);
            }
        } else if (flexibleEl) {
            var content = flexibleEl.getAttribute('content');
            if (content) {
                var initialDpr = content.match(/initial\-dpr=([\d\.]+)/);
                var maximumDpr = content.match(/maximum\-dpr=([\d\.]+)/);
                if (initialDpr) {
                    dpr = parseFloat(initialDpr[1]);
                    scale = parseFloat((1 / dpr).toFixed(2));
                }
                if (maximumDpr) {
                    dpr = parseFloat(maximumDpr[1]);
                    scale = parseFloat((1 / dpr).toFixed(2));
                }
            }
        }

        if (!dpr && !scale) {
            var isAndroid = win.navigator.appVersion.match(/android/gi);
            var isIPhone = win.navigator.appVersion.match(/iphone/gi);
            var devicePixelRatio = win.devicePixelRatio;
            if (isIPhone) {
                // iOS下，对于2和3的屏，用2倍的方案，其余的用1倍方案
                if (devicePixelRatio >= 3 && (!dpr || dpr >= 3)) {
                    dpr = 3;
                } else if (devicePixelRatio >= 2 && (!dpr || dpr >= 2)){
                    dpr = 2;
                } else {
                    dpr = 1;
                }
            } else {
                // 其他设备下，仍旧使用1倍的方案
                dpr = 1;
            }
            scale = 1 / dpr;
        }

        docEl.setAttribute('data-dpr', dpr);
        if (!metaEl) {
            metaEl = doc.createElement('meta');
            metaEl.setAttribute('name', 'viewport');
            metaEl.setAttribute('content', 'initial-scale=' + scale + ', maximum-scale=' + scale + ', minimum-scale=' + scale + ', user-scalable=no');
            if (docEl.firstElementChild) {
                docEl.firstElementChild.appendChild(metaEl);
            } else {
                var wrap = doc.createElement('div');
                wrap.appendChild(metaEl);
                doc.write(wrap.innerHTML);
            }
        }

        function refreshRem(){
            var width = docEl.getBoundingClientRect().width;
            if (width / dpr > 540) {
                width = 540 * dpr;
            }
            var rem = width / 10;
            docEl.style.fontSize = rem + 'px';
            flexible.rem = win.rem = rem;
        }

        win.addEventListener('resize', function() {
            clearTimeout(tid);
            tid = setTimeout(refreshRem, 300);
        }, false);
        win.addEventListener('pageshow', function(e) {
            if (e.persisted) {
                clearTimeout(tid);
                tid = setTimeout(refreshRem, 300);
            }
        }, false);

        if (doc.readyState === 'complete') {
            doc.body.style.fontSize = 12 * dpr + 'px';
        } else {
            doc.addEventListener('DOMContentLoaded', function(e) {
                doc.body.style.fontSize = 12 * dpr + 'px';
            }, false);
        }


        refreshRem();

        flexible.dpr = win.dpr = dpr;
        flexible.refreshRem = refreshRem;
        flexible.rem2px = function(d,fixRem) {
            var rem = fixRem || this.rem;
            var val = parseFloat(d) * rem;
            if (typeof d === 'string' && d.match(/rem$/)) {
                val += 'px';
            }
            return val;
        }
        flexible.px2rem = function(d,fixRem) {
            var rem = fixRem || this.rem;
            var val = parseFloat(d) / rem;
            if (typeof d === 'string' && d.match(/px$/)) {
                val += 'rem';
            }
            return val;
        }

    })(window,{});
    function loadMainImg(img) {
        /*
         * 屏幕宽度小于等于480， w = 480
         屏幕宽度大于480 & 小于等于640， w= 640
         屏幕宽度大于640 & 小于等于800， w= 800
         屏幕宽度大于800， w= 1080
         * */

        var deviceWidth = document.body.clientWidth;
        if (deviceWidth <= 240) {
            deviceWidth = 480
        } else if (deviceWidth > 240 && deviceWidth <= 320) {
            deviceWidth = 640
        } else if (deviceWidth > 320 && deviceWidth <= 540) {
            deviceWidth = 800
        } else {
            deviceWidth = 1080
        }

        url = img.getAttribute('data-src').replace('?w=750', '?w=' + deviceWidth);

        img.src = url;
    }
</script>


<style type="text/css">.ui-alert-box .ui-alert-btn,.ui-dialog-box .ui-dialog-btn{display:inline-block;padding:4px 12px;margin-bottom:0;font-size:16px;line-height:20px;color:#333;text-align:center;text-shadow:0 1px 1px rgba(255,255,255,0.75);vertical-align:middle;cursor:pointer;background-color:#f5f5f5;background-image:-moz-linear-gradient(top,#fff,#e6e6e6);background-image:-webkit-gradient(linear,0 0,0 100%,from(#fff),to(#e6e6e6));background-image:-webkit-linear-gradient(top,#fff,#e6e6e6);background-image:-o-linear-gradient(top,#fff,#e6e6e6);background-image:linear-gradient(to bottom,#fff,#e6e6e6);background-repeat:repeat-x;border:1px solid #ccc;border-color:#e6e6e6 #e6e6e6 #bfbfbf;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);border-bottom-color:#b3b3b3;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;-webkit-box-shadow:0 0 2px #000;box-shadow:0 0 2px #000}.ui-alert-box .ui-alert-btn:hover,.ui-dialog-box .ui-dialog-btn:hover,.ui-confirm-box .ui-confirm-btn:hover{cursor:pointer}.ui-alert-mask,.ui-dialog-mask{width:100%;height:100%;overflow:hidden;position:fixed;left:0;top:0;bottom:0;right:0;background:rgba(0,0,0,0.5);z-index:10001}.ui-confirm-mask{display:-webkit-box;display:-webkit-flex;display:-moz-flex;display:-ms-flexbox;display:flex;-webkit-box-align:center;-ms-flex-align:center;-webkit-align-items:center;-moz-align-items:center;align-items:center;-webkit-box-pack:center;-ms-flex-pack:center;-webkit-justify-content:center;-moz-justify-content:center;justify-content:center;width:100%;height:100%;overflow:hidden;position:fixed;left:0;top:0;bottom:0;right:0;background:rgba(0,0,0,.7);z-index:2}.ui-confirm-mask{z-index:1001}.ui-alert-header,.ui-dialog-header,.ui-confirm-header{padding:9px 15px;position:relative}.ui-alert-header h3,.ui-dialog-header h3,.ui-confirm-header h3{margin:0;text-align:center;font-size:20px;color:#3f3f3f}.ui-alert-close,.ui-dialog-close,.ui-confirm-close{position:absolute;top:5px;right:10px;font-size:30px;width:35px;font-weight:bold;line-height:30px;color:#000;text-shadow:0 1px 0 #fff;opacity:.2;cursor:pointer;background:transparent;border:0;-webkit-appearance:none}.ui-alert-close:hover,.ui-dialog-close:hover,.ui-confirm-close:hover{cursor:hand}.ui-alert-box,.ui-dialog-box{background:#fff;position:fixed;top:50%;margin-top:-80px;width:240px;left:50%;margin-left:-120px;-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;z-index:10;-webkit-transition:all .3s linear;transition:all .3s linear;-webkit-animation:bounceIn .3s both;animation:bounceIn .3s both}.ui-confirm-box{position:relative;background:#fff;border-radius:.078125rem;z-index:1;width:60%;display:block;-webkit-transition:all .3s linear;transition:all .3s linear;-webkit-animation:all .3s both;animation:all .3s both}.ui-alert-body,.ui-dialog-body,.ui-confirm-body{position:relative;padding:10px 20px 20px;overflow:hidden;box-sizing:border-box;max-height:260px;overflow-y:auto}.ui-alert-body p,.ui-dialog-body p,.ui-confirm-body p{font-size:14px;text-align:center;color:#3f3f3f}.ui-alert-footer,.ui-dialog-footer{padding:14px 15px 15px;margin-bottom:0;text-align:center;background-color:#fff;-webkit-border-radius:0 0 10px 10px;border-radius:0 0 10px 10px;-webkit-box-shadow:inset 0 1px 0 #fff;box-shadow:inset 0 1px 0 #fff}.ui-alert-footer .ui-alert-btn,.ui-dialog-footer .ui-dialog-btn{font-size:16px;line-height:26px;-webkit-box-shadow:0 0 2px #000;box-shadow:0 0 2px #000}.ui-confirm-footer{padding:0;margin-bottom:0;text-align:center;background-color:#fff;-webkit-border-radius:0 0 10px 10px;border-radius:0 0 10px 10px;overflow:hidden;box-shadow:inset 0 1px 0 #fff;display:-webkit-box}.ui-confirm-footer .ui-confirm-btn{background:#fff;color:#0079ff;border-top:#e3e3e3 solid 1px;border-bottom:0;height:46px;line-height:46px;font-size:16px;box-flex:1;-webkit-box-flex:1;display:block;-webkit-border-radius:0;border-radius:0;padding:0 12px}.ui-confirm-footer .ui-confirm-btn:first-child{border-right:#e3e3e3 solid 1px;border-left:none}.ui-confirm-footer .ui-confirm-btn:last-child{border-right:0;border-left:none}@-webkit-keyframes bounceIn{from{-webkit-transform:scale(0.8);opacity:0}50%{-webkit-transform:scale(1.05);opacity:1}100%{-webkit-transform:scale(1);opacity:1}}</style><style type="text/css">.ui-toast-mask{width:100%;height:100%;position:fixed;left:0;top:0;bottom:0;right:0;background:rgba(0,0,0,0.6);z-index:10001}.ui-toast-box{background:#fff;position:fixed;top:50%;width:240px;padding:15px 30px;box-sizing:border-box;text-align:center;margin-top:-20px;left:50%;margin-left:-120px;-webkit-border-radius:4px;border-radius:4px;z-index:10;-webkit-transition:all .2s linear;transition:all .2s linear;-webkit-animation:fadeIn .3s ease both;animation:fadeIn .3s ease both;box-sizing:border-box;font-size:16px;box-shadow:0 0 10px rgba(0,0,0,0.2)}.ui-toast-box p{color:#333;font-size:16px}.ui-toast-mask-hide .ui-toast-box{-webkit-animation:fadeOut .3s ease both;animation:fadeOut .3s ease both;-webkit-transition:all .3s;transition:all .3s}@-webkit-keyframes fadeIn{from{opacity:0;-webkit-transform:scale(.3)}100%{opacity:.9}}@-webkit-keyframes fadeOut{from{opacity:.9}100%{opacity:0;-webkit-transform:scale(.3)}}</style><style type="text/css">.hide{display:none}.overflow-hidden{overflow:hidden;position:fixed}.slidedown-contaienr{position:relative;will-change:all}.slidedown-inner{position:fixed;bottom:0;left:0;right:0;background-color:white;z-index:199;overflow:hidden;width:100%;max-width:640px;margin:0 auto;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-touch-callout:none;-webkit-user-select:none;user-select:none;-webkit-text-size-adjust:none;text-size-adjust:none;overflow-y:auto}.slidedown-body{overflow-x:hidden;overflow-y:auto}.slidedown-body.loading{background-image:url("//s.geilicdn.com/shop/20167/images/common/loading.50c5e3e7.gif");background-position:center;background-size:32px 32px;background-repeat:no-repeat}.slidedown-footer{height:54px;border-top:1px solid #dbdddd}.slidedown-close-btn{font-size:15px;line-height:54px;color:#d1665e;display:block;text-align:center}.slidedown-backdrop{position:fixed;top:0;left:0;background-color:rgba(0,0,0,.7);width:100%;height:3000px;overflow:hidden;z-index:198;will-change:all;-webkit-transition:all .5s ease;transition:all .5s ease}.slidedown-inner .slidedown-title{padding:0 20px;text-align:center;color:#6a6b6a;background-color:#dbdddd;line-height:55px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis}.slide-in-down{-webkit-backface-visibility:hidden;animation:slideInDown .5s ease;-webkit-animation:slideInDown .5s ease;-moz-animation:slideInDown .5s ease}.slide-out-down{-webkit-backface-visibility:hidden;animation:slideOutDown .5s ease;-webkit-animation:slideOutDown .5s ease;-moz-animation:slideOutDown .5s ease}@keyframes slideInDown{0%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:visible}100%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}}@-webkit-keyframes slideInDown{0%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:visible}100%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}}@-moz-keyframes slideInDown{0%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:visible}100%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}}@keyframes slideOutDown{0%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}100%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:hidden}}@-webkit-keyframes slideOutDown{0%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}100%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:hidden}}@-moz-keyframes slideOutDown{0%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}100%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:hidden}}</style><style type="text/css">#item_select_sku_count{height:100%;background-color:rgba(0,0,0,.8);top:0;left:0;right:0;z-index:100}#control_seckill_price{vertical-align:top}#control_seckill_price,#seckill_price{color:#d93229;margin-right:6px;font-size:20px;display:block;float:left}#control_item_info_price.del,#item_price.del{text-decoration:line-through;font-size:14px;color:#919191}#sellerOut{top:0;width:100%;height:40px;line-height:40px;border-radius:4px;font-size:15px;color:#5d5d5d;font-weight:bold;background:#e8e8e8}#sku_submit{display:none}#item_control{bottom:0;width:100%;max-width:640px;margin:0 auto;left:0;right:0}#item_control_inner{max-width:640px}#control_item_selected_sku{color:#919191;font-size:14px}#control_item_info,#control_bottom_btn,#sku_submit{background-color:#f1f1f1}#control_item_info{height:50px;line-height:50px;padding:15px 15px 15px 85px}#control_item_info_img{top:15px;left:15px}#control_item_info_price{padding:0;font-size:20px;color:#d93229}#control_item_info_close{width:25px;height:25px;top:10px;right:15px}#control_item_info_close:before,#control_item_info_close:after{content:"";width:1px;height:15px;background-color:#5d5d5d;position:absolute;top:5px;left:12px}#control_item_info_close:before{-webkit-transform:rotate(45deg);-moz-transform:rotate(45deg);-ms-transform:rotate(45deg);-o-transform:rotate(45deg);transform:rotate(45deg)}#control_item_info_close:after{-webkit-transform:rotate(-45deg);-moz-transform:rotate(-45deg);-ms-transform:rotate(-45deg);-o-transform:rotate(-45deg);transform:rotate(-45deg)}#control_wrap{padding:10px 15px 15px;background-color:#fff}.sku_li{margin-bottom:8px;margin-right:8px;display:inline-block}.sku_li:last-child{margin-bottom:0}.sku_a{line-height:18px;border:1px solid #939393;background-color:#fafafa;padding:6px 14px;font-size:16px;display:inline-block;word-break:break-all}.current_sku{border-color:#c60a1e;color:#c60a1e}.control_tle{padding-bottom:6px}#item_fix_btn{height:50px;left:0;bottom:0;z-index:102}#control_btn,#control_bottom_btn{width:100%;max-width:640px;padding:7px 0;background-color:#f7f7f7;height:36px;line-height:36px;border-top:1px solid #ccc}.max-one-line{display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;text-overflow:ellipsis;color:transparent}.max-two-line{display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden;text-overflow:ellipsis;color:transparent}#control_info .loading{width:100%;height:200px}#item_num{width:48px;height:34px;line-height:34px;font-size:16px;color:#323232;border-top:1px solid #c4c4c4;border-bottom:1px solid #c4c4c4}#control_count{padding-top:10px;margin-top:10px}#control_sub,#control_num,#control_add,#control_stock{height:34px;line-height:34px}#control_sub,#control_add{width:30px;border-top:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;background:#fff;background:-webkit-gradient(linear,center top,center bottom,from(#fff),to(#eeefee));font-size:24px;cursor:pointer}#control_sub{border-left:1px solid #e3e3e3}#control_add{border-right:1px solid #e3e3e3}#control_num{width:50px;padding:0 30px}#control_stock{margin-left:10px;font-size:12px;color:#999}#control_num_sub,#control_num_add{width:30px;height:34px;line-height:34px;top:0;color:#d93229;font-family:serif;font-size:20px;border:1px solid #c4c4c4;background-color:#fff}#control_num_sub.disabled{color:#ccc}#control_num_sub{left:0}#control_num_add{right:0}#submit_cps_url{margin-top:10px}.sku_a.disabled{color:#ccc;border-color:#ccc}#control_bottom_submit.disabled{color:#fff;background-color:#ccc}</style><style type="text/css">.hide{display:none}.overflow-hidden{overflow:hidden;position:fixed}.slidedown-contaienr{position:relative;will-change:all}.slidedown-inner{position:fixed;bottom:0;left:0;right:0;background-color:white;z-index:199;overflow:hidden;width:100%;max-width:640px;margin:0 auto;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-touch-callout:none;-webkit-user-select:none;user-select:none;-webkit-text-size-adjust:none;text-size-adjust:none;overflow-y:auto}.slidedown-body{overflow-x:hidden;overflow-y:auto}.slidedown-body.loading{background-image:url("//s.geilicdn.com/shop/20167/images/common/loading.50c5e3e7.gif");background-position:center;background-size:32px 32px;background-repeat:no-repeat}.slidedown-footer{height:54px;border-top:1px solid #dbdddd}.slidedown-close-btn{font-size:15px;line-height:54px;color:#d1665e;display:block;text-align:center}.slidedown-backdrop{position:fixed;top:0;left:0;background-color:rgba(0,0,0,.7);width:100%;height:3000px;overflow:hidden;z-index:198;will-change:all;-webkit-transition:all .5s ease;transition:all .5s ease}.slidedown-inner .slidedown-title{padding:0 20px;text-align:center;color:#6a6b6a;background-color:#dbdddd;line-height:55px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis}.slide-in-down{-webkit-backface-visibility:hidden;animation:slideInDown .5s ease;-webkit-animation:slideInDown .5s ease;-moz-animation:slideInDown .5s ease}.slide-out-down{-webkit-backface-visibility:hidden;animation:slideOutDown .5s ease;-webkit-animation:slideOutDown .5s ease;-moz-animation:slideOutDown .5s ease}@keyframes slideInDown{0%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:visible}100%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}}@-webkit-keyframes slideInDown{0%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:visible}100%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}}@-moz-keyframes slideInDown{0%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:visible}100%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}}@keyframes slideOutDown{0%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}100%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:hidden}}@-webkit-keyframes slideOutDown{0%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}100%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:hidden}}@-moz-keyframes slideOutDown{0%{-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}100%{-webkit-transform:translate3d(0,100%,0);transform:translate3d(0,100%,0);visibility:hidden}}</style>

<style>
    .ellipsis {
        display: block;
        white-space: nowrap;
        text-overflow: ellipsis;
        -o-text-overflow: ellipsis;
        -ms-text-overflow: ellipsis;
        width: 280px;
        overflow: hidden;
    }
</style>