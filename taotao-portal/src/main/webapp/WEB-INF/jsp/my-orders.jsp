<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Cache-Control" content="max-age=300"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的订单 - 淘淘</title>
    <meta name="Keywords" content="java,淘淘java"/>
    <meta name="description" content="在淘淘中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。"/>
    <link rel="stylesheet" type="text/css" href="/css/base.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="/css/myjd.common.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="/css/myjd.order.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="/css/psearch20131008.css" media="all" />
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<!-- header start -->
<jsp:include page="commons/header.jsp"/>
<!-- header end -->

<div id="container">
    <div class="w">

        <div id="main">
            <div class="g-0">
                <div id="content" class="c-3-5">
                    <div class="mod-main mod-comm" id="order01">
                        <div class="m m3" id="safeinfo" style="display:none"></div>
                        <div class="mt">
                            <h3>我的订单
                                <div class="layer-credit" id="creditPayShow" style="display:none">
                                    <a class="close" href="#none" clstag="click|keycount|orderinfo|baitiaoclose">关闭</a>
                                    <div class="cont">
                                        <span></span>
                                        <a class="go" href="#none" target="_blank"></a>
                                    </div>
                                </div>
                            </h3>
                        </div>
                        <div class="mc" id="order01">
                            <dl>
                                <dt>便利提醒：</dt>
                                <dd id="ordercount-waitPay" clstag="click|keycount|orderinfo|waitPay">待付款(0)</dd>
                                <dd id="ordercount-waitReceive" clstag="click|keycount|orderinfo|waitReceive">待确认收货(0)
                                </dd>
                                <dd id="ordercount-waitPick" clstag="click|keycount|orderinfo|waitPick">待自提(0)</dd>
                            </dl>
                        </div>
                    </div>
                    <div class="mod-main mod-comm lefta-box" id="order02">
                        <div class="mt">
                            <ul class="taborder">
                                <li>
                                    <div class="tyies-t">
                                        <strong class="ftx-01">全部订单</strong><b></b>
                                    </div>
                                </li>
                            </ul>
<%--                            <div class="extra-r">--%>
<%--                                <div class="search-01">--%>
<%--                                    <input id="ip_keyword" name="" type="text" class="s-itxt" value="商品名称、商品编号、订单编号"--%>
<%--                                           onfocus="if (this.value==this.defaultValue) this.value=''"--%>
<%--                                           onblur="if (this.value=='') this.value=this.defaultValue"--%>
<%--                                           onkeydown="javascript:if(event.keyCode==13) OrderSearch('ip_keyword');">--%>
<%--                                    <!--input name="" type="button" value="查 询" class="btn-13" onclick="OrderSearch('ip_keyword')" clstag="click|keycount|orderinfo|search"/-->--%>
<%--                                    <a href="javascript:;" class="btn-13" onclick="OrderSearch('ip_keyword')"--%>
<%--                                       clstag="click|keycount|orderinfo|search">查 询</a>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </div>
                        <div class="mc">
                            <table class="tb-void">
                                <thead>
                                <tr>
                                    <th>订单信息</th>
                                    <th>收货人</th>
                                    <th>订单金额</th>
                                    <th>下单时间
<%--                                        <select id="submitDate" name="" class="sele">--%>
<%--                                            <option value="1" selected="">最近三个月</option>--%>
<%--                                            <option value="2">今年内</option>--%>
<%--                                            <option value="2013">2013年</option>--%>
<%--                                            <option value="2012">2012年</option>--%>
<%--                                            <option value="2011">2011年</option>--%>
<%--                                            <option value="3">2011年以前</option>--%>
<%--                                        </select>--%>
                                    </th>
                                    <th>订单状态
<%--                                        <select id="orderState" name="" class="sele">--%>
<%--                                            <option value="4096" selected="">全部状态</option>--%>
<%--                                            <option value="1">等待付款</option>--%>
<%--                                            <option value="32">等待自提</option>--%>
<%--                                            <option value="128">等待收货</option>--%>
<%--                                            <!-- <option value="0">处理中</option> -->--%>
<%--                                            <!--<option value="2048">有效</option> -->--%>
<%--                                            <option value="1024">已完成</option>--%>
<%--                                            <option value="-1">已取消</option>--%>
<%--                                        </select>--%>
                                    </th>
                                    <th>操作</th>
                                </tr>
                                </thead>
<%--                                订单时间和类型选择方法--%>
<%--                                <script type="text/javascript" language="javascript">--%>
<%--                                    $("#submitDate").change(function () {--%>
<%--                                        var sDate = $("#submitDate option[@selected]").val();--%>
<%--                                        window.location = 'list.action?d=' + sDate + '&s=4096&t=';--%>
<%--                                    });--%>
<%--                                    $("#orderState").change(function () {--%>
<%--                                        var oState = $("#orderState option[@selected]").val();--%>
<%--                                        window.location = 'list.action?d=1&s=' + oState + '&t=';--%>
<%--                                    });--%>
<%--                                    $("#submitDate").val(1);--%>
<%--                                    $("#orderState").val(4096);--%>
<%--                                </script>--%>

                                <tbody id="tb-2538292730">
<c:forEach items="${orders.data}" var="order">
                                <tr class="tr-th">
                                    <td colspan="6">
                <span class="tcol1">
                    订单编号:
                    <a name="orderIdLinks" id="idUrl2538292730" target="_blank"
                       href="无" clstag="click|keycount|orderinfo|order_num">${order.orderId}</a>

		    	</span>

                                        <span class="tcol2">
                        鲜果益客
                    </span>
                                        <span class="tcol3">
                        <a class="btn-im" onclick="getPamsForChat()" href="#none" title="联系客服"></a>
                    </span>
                                    </td>
                                </tr>
                                <tr id="track2538292730" oty="0,1,70" class="tr-td">
                                    <td>
                                        <div class="img-list">
                                            <c:forEach items="${order.orderItemList}" var="orderItems">
                                            <a href="http://localhost:8082/item/${orderItems.itemId}.html" class="img-box"
                                               clstag="click|keycount|orderinfo|order_product" target="_blank">
                                                <img title="${orderItems.title}" width="50" height="50"
                                                     src="${orderItems.picPath}"
                                                     class="err-product">
                                            </a>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
<%--                                        <div class="u-name">${order.orderShippingList.receiverName}</div>--%>
                                        <c:forEach items="${order.orderShippingList}" var="orderShippings">
                                        <div class="u-name">${orderShippings.receiverName}</div>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        ￥${order.payment}<br>
                                        <c:choose>
                                            <c:when test="${order.paymentType==1}">在线支付</c:when>
                                            <c:when test="${order.paymentType==2}">货到付款</c:when>
                                            <c:otherwise>未知</c:otherwise>
                                        </c:choose>
                                        <br>
                                    </td>
                                    <jsp:useBean id="dateValue" class="java.util.Date"/>
                                    <jsp:setProperty name="dateValue" property="time" value="${order.creatTime}"/>
<%--                                    <% request.setAttribute("currentTimeStamp", Calendar.getInstance().getTime());--%>
<%--                                    %>--%>
                                    <td>
                                        <span class="ftx-03"><fmt:formatDate value="${dateValue}" pattern="YYYY-MM-dd"/> <br>
                                            <fmt:formatDate value="${dateValue}" pattern="HH:mm:ss"/></span>
                                        <input type="hidden" id="datasubmit-2538292730" value="2014-10-20 22:30:49">
                                    </td>

                                    <td><span class="ftx-03">
                                        <c:choose>
<%--                                            状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭'--%>
                                            <c:when test="${order.status == 1}">未付款</c:when>
                                            <c:when test="${order.status == 2}">已付款</c:when>
                                            <c:when test="${order.status == 3}">未发货</c:when>
                                            <c:when test="${order.status == 4}">已发货</c:when>
                                            <c:when test="${order.status == 5}">交易成功</c:when>
                                            <c:when test="${order.status == 6}">交易关闭</c:when>
                                            <c:otherwise>未知</c:otherwise>
                                        </c:choose>
                                        </span></td>

                                    <td id="operate2538292730" class="order-doi" width="100">


                                        <span class="pop-recycle-a">
                                            <a
                                            href="/order/deleteOrder/${order.orderId}.html"clstag="click|keycount|orderinfo|order_del"
                                            onclick="" >删除
                                            </a>
                                        </span>
                                        <span>
                                            <a href="https://www.kuaidi100.com/chaxun?com=${order.shippingName}&nu=${order.shippingCode}"clstag="click|keycount|orderinfo|order_del"
                                               onclick="" >查看物流
                                            </a>
                                        </span>
                                        <span id="doi2538292730"><br>
                                            <a
                                            href="/order/query/orderItem/${order.orderId}.html"
                                            target="_blank" clstag="click|keycount|orderinfo|order_comment">评价晒单
                                            </a><br>
                                        </span>
                                        <a
                                            href="/order/receive/orderitem/${order.orderId}.html" onclick=""
                                            clstag="click|keycount|orderinfo|order_repair">确认收货
                                        </a>
                                        <a class="btn-again" clstag="click|keycount|orderlist|buy"
                                           href="http://localhost:8082/"
                                           target="_blank">还要买
                                        </a>

                                    </td>
                                </tr>
</c:forEach>
                                </tbody>



                            </table>
                        </div>
                        <div class="mt10">
                            <div class="pagin fr" id="pagin-btm">

                            </div>
                            <div class="clr"></div>
                        </div>
                    </div>


                </div>
            </div>
            <div id="left" class="g-3-5 c-0">
                <!--js 加载异步加载的左侧菜单 -->
                <div id="menu"><h3>我的交易</h3>
                    <dl class="fore1">
                        <dt><a target="_blank" clstag="homepage|keycount|home2013|hdd" id="_MYJD_ordercenter"
                               href="http://order.jd.com/center/list.action" class="curr">我的订单</a></dt>
                    </dl>
                    <dl class="fore2">
                        <dt><a target="_blank" clstag="homepage|keycount|home2013|hyushou" id="_MYJD_yushou"
                               href="http://yushou.jd.com/member/qualificationList.action">我的预售</a></dt>
                    </dl>
                    <dl class="fore3">
                        <dt><a target="_blank" clstag="homepage|keycount|home2013|hbdsh" id="_MYJD_locallife"
                               href="http://life.jd.com/localOrder/iniOrder.do">我的本地生活</a></dt>
                    </dl>
                    <dl class="fore4">
                        <dt><a target="_blank" clstag="homepage|keycount|home2013|hdqs" id="_MYJD_ding"
                               href="http://ding.jd.com/plan/showPlans.action">我的定期送</a></dt>
                    </dl>
                    <dl class="fore5">
                        <dt><a target="_blank" clstag="homepage|keycount|home2013|htg" id="_MYJD_tuan"
                               href="http://tuan.jd.com/order/index.php">我的团购</a></dt>
                    </dl>
                    <dl class="fore6">
                        <dt><a target="_blank" clstag="homepage|keycount|home2013|hjg" id="_MYJD_protection"
                               href="http://jiabao.jd.com/protecting">价格保护</a></dt>
                    </dl>
                    <dl class="fore7">
                        <dt class="hc"><b></b><a target="_blank" id="_MYJD_gz" href="#none">我的关注</a></dt>
                        <dd class="fore1">
                            <div class="item" id="_MYJD_product"><a target="_blank"
                                                                    clstag="homepage|keycount|home2013|hgz"
                                                                    href="http://t.jd.com/home/follow">关注的商品</a></div>
                        </dd>
                        <dd class="fore2">
                            <div class="item" id="_MYJD_vender"><a target="_blank"
                                                                   clstag="homepage|keycount|home2013|hdp"
                                                                   href="http://t.jd.com/vender/followVenderList.action">关注的店铺</a>
                            </div>
                        </dd>
                        <dd class="fore3">
                            <div class="item" id="_MYJD_activity"><a target="_blank"
                                                                     clstag="homepage|keycount|home2013|hhd"
                                                                     href="http://t.jd.com/activity/followActivityList.action">关注的活动</a>
                            </div>
                        </dd>
                        <dd class="fore4 last ">
                            <div class="item" id="_MYJD_history"><a target="_blank"
                                                                    clstag="homepage|keycount|home2013|hll"
                                                                    href="http://my.jd.com/history/list.html">浏览历史&nbsp;<img
                                    width="24" height="11" src="/images/myjd-new-ico.png"></a></div>
                        </dd>
                    </dl>
                    <dl class="fore8">
                        <dt class="hc"><b></b><a target="_blank" id="_MYJD_zc" href="#none">我的资产</a></dt>
                        <dd class="fore1">
                            <div class="item" id="_MYJD_cashbox"><a target="_blank"
                                                                    clstag="homepage|keycount|home2013|hjk"
                                                                    href="http://jinku.pay.jd.com/xjk/income.action">我的小金库</a>
                            </div>
                        </dd>
                        <dd class="fore2">
                            <div class="item" id="_MYJD_credit"><a clstag="homepage|keycount|home2013|hbt" tag="213"
                                                                   href="http://baitiao.jd.com/creditUser/record">淘淘白条</a>&nbsp;
                            </div>
                        </dd>
                        <dd class="fore3">
                            <div class="item" id="_MYJD_tx"><a target="_blank" clstag="homepage|keycount|home2013|htx"
                                                               href="http://mobile.jd.com/yyswt/myjd.do">淘淘通信</a></div>
                        </dd>
                        <dd class="fore4">
                            <div class="item" id="_MYJD_balance"><a target="_blank"
                                                                    clstag="homepage|keycount|home2013|hye"
                                                                    href="http://mymoney.jd.com/finance/recently.action">余额</a>
                            </div>
                        </dd>
                        <dd class="fore5">
                            <div class="item" id="_MYJD_ticket"><a target="_blank"
                                                                   clstag="homepage|keycount|home2013|hyh"
                                                                   href="http://quan.jd.com/user_quan.action">优惠券</a>
                            </div>
                        </dd>
                        <dd class="fore6">
                            <div class="item" id="_MYJD_card"><a target="_blank" clstag="homepage|keycount|home2013|he"
                                                                 href="http://giftcard.jd.com/giftcard/index.action">淘淘卡/E卡</a>
                            </div>
                        </dd>
                        <dd class="fore7 last">
                            <div class="item" id="_MYJD_bean"><a target="_blank" clstag="homepage|keycount|home2013|hjd"
                                                                 href="http://bean.jd.com/myJingBean/list">京豆</a></div>
                        </dd>
                    </dl>
                </div>
            </div>
            <span class="clr"></span>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/search_main.js"></script>
<script type="text/javascript">
    SEARCH.query = "${query}";
    SEARCH.bottom_page_html(${page},${totalPages},'');
    SEARCH.page = function(b, a) {
        b = parseInt(b, 10);
        if (b < 1) {
            b = 1
        }
        window.location = "/order/myOrder.html?page=" + b;
    };
</script>
<script type="text/javascript">

</script>
<!-- footer start -->
<jsp:include page="commons/footer.jsp"/>
<!-- footer end -->
</body>
</html>