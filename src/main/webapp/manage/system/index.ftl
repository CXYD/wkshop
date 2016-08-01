
<#include "/manage/tpl/header.ftl">
<style>
        .vertical-center{
        position: absolute;
        top: 40%;
        left: 60%;
        transform: translate(-50%, -50%);
    }
</style>
<body>

<!-- Aside Start-->
<aside class="left-panel">

    <!-- brand -->
    <div class="logo">
        <a href="index.html" class="logo-expanded">
            <i class="ion-social-buffer"></i>
            <span class="nav-label">商城后台管理系统</span>
        </a>
    </div>
    <!-- / brand -->

    <!-- Navbar Start -->
    <nav class="navigation">
        <ul class="list-unstyled">
            <#--<li><a href="index.html"><i class="ion-home"></i> <span class="nav-label">首页</span></a></li>-->

        <#list userMenus as menu>
            <li class="has-submenu"><a id="item_${menu.pid!"0"}_${menu.id!""}" data-href="${menu.url}" class="menu-item"> <i class="fa fa-folder fa-fw"></i> ${menu.name!""}<#if menu.children?? && menu.children?size gt 0><span class="fa arrow"></span></#if></a>
            <#if menu.children?? && menu.children?size gt 0>
                <ul class="list-unstyled">
                <#list menu.children as menu>
                    <li><a id="item_${menu.pid!"0"}_${menu.id!""}" data-href="${menu.url}"  class="menu-item "><i class="fa fa-files-o fa-fw"></i> ${menu.name!""}</a></li>
                </#list>
                </ul>
            </#if>
            </li>
        </#list>
        </ul>
    </nav>

</aside>
<!-- Aside Ends-->


<!--Main Content Start -->
<section class="content">

    <!-- Header -->
    <header class="top-head container-fluid">
        <button type="button" class="navbar-toggle pull-left">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <!-- Left navbar -->
        <nav class=" navbar-default" role="navigation">

            <!-- Right navbar -->
            <ul class="nav navbar-nav navbar-right top-menu top-right-menu">

                <!-- /messages -->
                <!-- Notification -->
                <li class="dropdown">

                    <!-- /Notification -->

                    <!-- user login dropdown start-->
                <li class="dropdown text-center">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <img alt="" src="${basepath}/static/img/avatar-2.jpg" class="img-circle profile-img thumb-sm">
                        <span class="username">  ${currentUser().nickname!currentUser().username}  </span> <span
                            class="caret"></span>
                    </a>
                    <ul class="dropdown-menu pro-menu fadeInUp animated" tabindex="5003"
                        style="overflow: hidden; outline: none;">
                        <#--<li name='changePwd'>
                            <a href="javascript:void(0)"><i class="fa fa-gear fa-fw"></i> 修改密码</a>
                        </li>-->
                       <#-- <li class="divider"></li>-->
                        <li><a href="${basepath}/manage/user/logout"><i class="fa fa-sign-out"></i> 注销</a></li>
                    </ul>
                </li>
                <!-- user login dropdown end -->
                </li></ul>
            <!-- End right navbar -->
        </nav>

    </header>
    <!-- Header Ends -->
     <#if accountflag=='1'>
     <div id="div_create">
     <div  class="vertical-center"  >您还没有创建商城</div>
         <lable   id="createid" style="margin-top: 40px"  class="vertical-center btn  btn-success size-13">立即创建</lable>
    </div>
     </#if>
    <!-- Page Content Start -->
    <!-- ================== -->

    <div class="wraper container-fluid">


    </div>
    <!-- Page Content Ends -->
    <!-- ================== -->

    <!-- Footer Start -->
    <footer class="footer">
        2016 © 北京沃宽科技有限公司.
    </footer>
    <!-- Footer Ends -->



</section>
<!-- Main Content Ends -->



<#include "/manage/tpl/footjs.ftl">
<script>

    $(function(){
//        webIndex.index.initHomePage();
        webIndex.index.menu.initMenuEvent();
        $('li[name=set]').on('click',function(){
            webIndex.index.set();
        });
        $('li[name=changePwd]').on('click',function(){
            webIndex.index.changePwd();
        })
    });

   var webIndex = {
      index : {
          initHomePage : function(){
              $('.container-fluid').load();
          },
          menu:{
              initMenuEvent: function(){
                  $('.navigation').on('click',function(){
//                      console.log(event.target);
//                      console.log($(event.target).attr('data-href'))
                      $('.wraper').load("${basepath}/"+$(event.target).attr('data-href'));
                        $(this).find('li.has-submenu').find('li.active').removeClass('active');
                      $(event.target).parent().addClass('active');
                  })
              }
          },
          set:function(){
            $('.wraper').load('${basepath}/manage/user/show?account=${currentUser().username}')
          },
            changePwd:function(){
                $('.wraper').load('${basepath}/manage/user/toChangePwd')
            }

      }
   }
    $("#createid").click(function(){
        $('.wraper').load('${basepath}/manage/shopManage/toCreateShop?username=${username}');
        $('#div_create').hide();
    })
</script>

</body>
</html>
