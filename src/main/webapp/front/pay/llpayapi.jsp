<%
/* *
 *功能：手机网站支付接口接入页
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.jeeshop.web.util.pay.config.PayConfig"%>
<%@ page import="net.jeeshop.web.util.pay.util.PaySubmit"%>
<%@ page import="org.slf4j.Logger"%>
<%@ page import="org.slf4j.LoggerFactory"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE HTML">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>手机网站支付接口</title>
<link rel="stylesheet" type="text/css" href="/wkshop/static/css/front/weui.css" />
</head>
<script type="text/javascript" src="/wkshop/static/js/jquery.js"></script>
<script type="text/javascript" src="/wkshop/static/js/front/weui.js"></script>
<script type="text/javascript" >weui.Loading.show()</script>
<%

		  final Logger logger = LoggerFactory.getLogger(PaySubmit.class);
		////////////////////////////////////请求参数//////////////////////////////////////

		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = "http://beijing.wokuan.cn/smartWo/notifyUrl";
		//需http://格式的完整路径，不能加?id=123这类自定义参数

		//页面跳转同步通知页面路径
		String return_url = "http://beijing.wokuan.cn/smartWo/returnUrl";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		//合同号
//		String account = new String(request.getParameter("adslaccount").getBytes("ISO-8859-1"),"UTF-8");
//
//		//商户订单号
//		String out_trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//		//商户网站订单系统中唯一订单号，必填
//
//		//订单名称
//		String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");
//		//必填
//
//		//付款金额
//		String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//必填

		//商品展示地址
		//String show_url = new String(request.getParameter("return_url").getBytes("ISO-8859-1"),"UTF-8");
		//必填，需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

		//订单描述
		//String body = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");
		//选填

		//超时时间
		//String it_b_pay = new String(request.getParameter("it_b_pay").getBytes("ISO-8859-1"),"UTF-8");
		//选填


		//////////////////////////////////////////////////////////////////////////////////

		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("service", "cn.wokuan.pay.wap.create.direct.cn.wokuan.pay.by.user");
		sParaTemp.put("user_id",account); // userid
//        sParaTemp.put("seller_id",PayConfig.seller_id);
		sParaTemp.put("_input_charset", PayConfig.input_charset);
//		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("no_order", out_trade_no); //order
		sParaTemp.put("money_order", total_fee);
		sParaTemp.put("name_goods", "");
		sParaTemp.put("pay_type","ll");

//		sParaTemp.put("subject", subject);
//		sParaTemp.put("total_fee", total_fee);
//		sParaTemp.put("show_url", show_url);
//		sParaTemp.put("body", body);
//		sParaTemp.put("it_b_pay", it_b_pay);
//		sParaTemp.put("extern_token", extern_token);

		//建立请求
		String sHtmlText = PaySubmit.buildRequest(sParaTemp,"post","确认");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
