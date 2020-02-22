<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品评价 - 鲜果益客</title>
<meta name="Keywords" content="java,淘淘java" />
<meta name="description" content="在淘淘中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。" />
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.common.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.commentImg.css" media="all" />
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<!-- header end -->

<div id="container">
<div class="w">
	
<div id="main">
    <div class="g-0">
        <div id="content" class="c-3-5">
            
            <div class="mod-main mod-comm extra-main" id="evalu01">
                        <div class="mt">
                            <h3>商品评价</h3>
                            <div class="extra-l ftx03 ml10">
                                <span id="tip-num">(
                                    <span class="num-comment">
                                        1
                                    </span>个待评价
)</span>
                            </div>
                        </div>
                         <div class="mc">
                            <div class="tb-void tb-line">
                                <table class="tb-void tb-line">
                                    <colgroup>
                                        <col width="490">
                                        <col width="130">
                                        <col width="">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>商品信息</th>
                                            <th>购买时间</th>
                                            <th>评价状态</th>
                                        </tr>
                                    </thead>
                                </table>
                                <table class="tb-void tb-line">
                                    <tbody>
                                    <c:forEach items="${orderItemList}" var="orderItems">
                                    <tr>
                                        <td>
                                            <ul class="pro-info" oid="3122336930" pid="975788">
                                                <li class="fore1">
                                                    <div class="p-info clearfix">
                                                        <div class="p-img fl">
                                                            <a target="_blank" href="http://localhost8082/item/${orderItems.itemId}.html">
                                                                <img width="50" height="50" title="${orderItems.title}" data-img="1" src="${orderItems.picPath}" class="err-product">
                                                            </a>
                                                        </div>
                                                        <div class="p-name fl">
                                                            <a target="_blank" href="http://localhost8082/item/${orderItems.itemId}.html">${orderItems.title}</a>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="fore2">
                                                    <% request.setAttribute("currentTimeStamp", Calendar.getInstance().getTime());%>

                                                    <span class="ftx03"><fmt:formatDate value="${currentTimeStamp}" pattern="YYYY-MM-dd"/></span>
                                                </li>



                                                <li class="fore3 forem">
                                                        <a href="#" onclick="submitForm(${orderItems.itemId})" voucherstatus="0" class="pj" alt="975788" title="发表评价" catefirst="670" catesecond="716" catethird="720">发表评价<b class="icon-show"></b></a>
                                                </li>
                                            </ul>
                                            <div class="clr"></div>
                                                                                        <div class="comment-box prompt01" oid="3122336930" pid="975788" style="display: block;">
                                                <div class="box-t"></div>
                                                <div class="form" tagflag="true" isconspros="0" pid="975788">


                                                <form id="${orderItems.itemId}" method="post">
                                                    <div class="item item01 xindeEl">
                                                        <span class="label"><em>*</em>心得：</span>
                                                        <div class="cont">
                                                            <textarea name="comment" cols="" rows="" class="area area01">商品是否给力？快分享你的购买心得吧~</textarea>
<%--                                                            <textarea name="" cols="" rows="" class="area area01" >商品是否给力？快分享你的购买心得吧~</textarea>--%>
                                                            <div class="clr"></div>
                                                            <div class="msg-text ftx-03">10-500字</div>
                                                        </div>
                                                        <div class="clr"></div>
                                                    </div>
                                                </form>
<%--                                                <button onclick="submitForm(${orderItems.itemId})">提交</button>--%>

                                                </div>
                                                 <script type="text/javascript">
                                                   function submitForm(itemId) {
                                                       var itemId = itemId;
                                                       $.ajax({
                                                            url: "/order/add/comment/" + itemId + ".html",
                                                            data: $("#"+itemId).serialize(),
                                                            type: "POST",
                                                            success: function () {

                                                                alert("商品评价完成");

                                                        },
                                                            error: function () {
                                                                alert("商品评价失败");
                                                        }
                                                    })
                                                   }
                                                 </script>
                                            </div>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
            
        </div>
    </div>
    <div id="left" class="g-3-5 c-0">
        <!--js 加载异步加载的左侧菜单 -->
    <div id="menu"><h3>我的交易</h3><dl class="fore1"><dt><a target="_blank" clstag="homepage|keycount|home2013|hdd" id="_MYJD_ordercenter" href="http://order.jd.com/center/list.action" class="curr">我的订单</a></dt></dl><dl class="fore2"><dt><a target="_blank" clstag="homepage|keycount|home2013|hyushou" id="_MYJD_yushou" href="http://yushou.jd.com/member/qualificationList.action">我的预售</a></dt></dl><dl class="fore3"><dt><a target="_blank" clstag="homepage|keycount|home2013|hbdsh" id="_MYJD_locallife" href="http://life.jd.com/localOrder/iniOrder.do">我的本地生活</a></dt></dl><dl class="fore4"><dt><a target="_blank" clstag="homepage|keycount|home2013|hdqs" id="_MYJD_ding" href="http://ding.jd.com/plan/showPlans.action">我的定期送</a></dt></dl><dl class="fore5"><dt><a target="_blank" clstag="homepage|keycount|home2013|htg" id="_MYJD_tuan" href="http://tuan.jd.com/order/index.php">我的团购</a></dt></dl><dl class="fore6"><dt><a target="_blank" clstag="homepage|keycount|home2013|hjg" id="_MYJD_protection" href="http://jiabao.jd.com/protecting">价格保护</a></dt></dl><dl class="fore7"><dt class="hc"><b></b><a target="_blank" id="_MYJD_gz" href="#none">我的关注</a></dt><dd class="fore1"><div class="item" id="_MYJD_product"><a target="_blank" clstag="homepage|keycount|home2013|hgz" href="http://t.jd.com/home/follow">关注的商品</a></div></dd><dd class="fore2"><div class="item" id="_MYJD_vender"><a target="_blank" clstag="homepage|keycount|home2013|hdp" href="http://t.jd.com/vender/followVenderList.action">关注的店铺</a></div></dd><dd class="fore3"><div class="item" id="_MYJD_activity"><a target="_blank" clstag="homepage|keycount|home2013|hhd" href="http://t.jd.com/activity/followActivityList.action">关注的活动</a></div></dd><dd class="fore4 last "><div class="item" id="_MYJD_history"><a target="_blank" clstag="homepage|keycount|home2013|hll" href="http://my.jd.com/history/list.html">浏览历史&nbsp;<img width="24" height="11" src="/images/myjd-new-ico.png"></a></div></dd></dl><dl class="fore8"><dt class="hc"><b></b><a target="_blank" id="_MYJD_zc" href="#none">我的资产</a></dt><dd class="fore1"><div class="item" id="_MYJD_cashbox"><a target="_blank" clstag="homepage|keycount|home2013|hjk" href="http://jinku.pay.jd.com/xjk/income.action">我的小金库</a></div></dd><dd class="fore2"><div class="item" id="_MYJD_credit"><a clstag="homepage|keycount|home2013|hbt" tag="213" href="http://baitiao.jd.com/creditUser/record">淘淘白条</a>&nbsp;</div></dd><dd class="fore3"><div class="item" id="_MYJD_tx"><a target="_blank" clstag="homepage|keycount|home2013|htx" href="http://mobile.jd.com/yyswt/myjd.do">淘淘通信</a></div></dd><dd class="fore4"><div class="item" id="_MYJD_balance"><a target="_blank" clstag="homepage|keycount|home2013|hye" href="http://mymoney.jd.com/finance/recently.action">余额</a></div></dd><dd class="fore5"><div class="item" id="_MYJD_ticket"><a target="_blank" clstag="homepage|keycount|home2013|hyh" href="http://quan.jd.com/user_quan.action">优惠券</a></div></dd><dd class="fore6"><div class="item" id="_MYJD_card"><a target="_blank" clstag="homepage|keycount|home2013|he" href="http://giftcard.jd.com/giftcard/index.action">淘淘卡/E卡</a></div></dd><dd class="fore7 last"><div class="item" id="_MYJD_bean"><a target="_blank" clstag="homepage|keycount|home2013|hjd" href="http://bean.jd.com/myJingBean/list">京豆</a></div></dd></dl><dl class="fore9 last "><dt class="hc"><b></b><a target="_blank" id="_MYJD_fw" href="#none">客户服务</a></dt><dd class="fore1"><div class="item" id="_MYJD_repair"><a target="_blank" clstag="homepage|keycount|home2013|hfx" href="http://myjd.jd.com/repair/orderlist.action">返修退换货</a></div></dd><dd class="fore2"><div class="item" id="_MYJD_refundment"><a target="_blank" clstag="homepage|keycount|home2013|hqx" href="http://rps.fm.jd.com/rest/refund/refundList">取消订单记录</a></div></dd><dd class="fore3 last "><div class="item" id="_MYJD_complaint"><a target="_blank" clstag="homepage|keycount|home2013|htx" href="http://myjd.jd.com/opinion/orderList.action">我的投诉</a></div></dd></dl></div></div>
    <span class="clr"></span>
</div>
</div>
</div>
<script type="text/javascript">
    function submitForm(itemId) {
        var itemId = itemId;
        $.ajax({
            url: "/order/add/comment/" + itemId + ".html",
            data: $("#"+itemId).serialize(),
            type: "POST",
            success: function (data) {
                if (data.data()==null){

                    alert("商品评价失败");
                }

            },
            error: function () {
                alert("商品评价成功");
            }
        })
    }
</script>

<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
</body>

</html>