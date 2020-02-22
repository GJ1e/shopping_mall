<%--
  Created by IntelliJ IDEA.
  User: 郜杰
  Date: 2020/2/10
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link type="text/css" rel="stylesheet" href="/css/base.css" />
    <link rel="stylesheet" type="text/css" href="/css/purchase.base.2012.css" />
    <link rel="stylesheet" type="text/css" href="/css/purchase.sop.css" />
    <title>购物车添加成功</title>
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
    <script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery.cookie.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/taotao.js" charset="utf-8"></script>
</head> <body id="mainframe">
<!--shortcut start-->
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->
<div class="w" id="headers">
    <div id="logo"><a href="http://www.jd.com/"><img alt="淘淘商城" src="/images/taotao-logo.gif"></a></div>
    <ul class="step" id="step1">
        <li class="fore1">1.我的购物车<b></b></li>
        <li class="fore2">2.填写核对订单信息<b></b></li>
        <li class="fore3">3.成功提交订单</li>
    </ul>
    <div class="clr"></div>
</div>
<div class="w" id="safeinfo"></div><!--父订单的ID-->
<div class="w main">
    <div class="m m3 msop">
        <div class="mt" id="success_tittle"><s class="icon-succ02"></s><h3 class="ftx-02">此商品已经成功添加到购物车！</h3>
        </div>
        <div class="mc" id="success_detail">
            <ul class="list-order">
                <li class="li-st">
                    <div class="fore1"><a href="/cart/cart.html"><h3>查看购物车</h3></a></div>

                    <div class="fore2"><a href="/"><h3>继续购物</h3></a></div>

                </li>
            </ul>

        </div>
    </div>
</div>
<div class="w">
    <!-- links start -->
    <jsp:include page="commons/footer.jsp"></jsp:include>
    <!-- links end -->
</div>
<!-- footer end -->
</body>
</html>