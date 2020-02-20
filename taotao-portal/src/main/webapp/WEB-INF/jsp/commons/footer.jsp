<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w" clstag="homepage|keycount|home2013|37a">
	<div id="service-2013">
		<dl class="fore1">
			<dt><b></b><strong>购物指南</strong></dt>
			<dd>
				<div><a href="http://help.jd.com/help/question-56.html" target="_blank" rel="nofollow">购物流程</a></div>
				<div><a href="http://help.jd.com/help/question-57.html" target="_blank" rel="nofollow">会员介绍</a></div>
				<div><a href="http://help.jd.com/help/question-61.html" target="_blank" rel="nofollow">常见问题</a></div>
				<div><a href="http://help.jd.com/index.html" target="_blank" rel="nofollow">联系客服</a></div>
			</dd>
		</dl>
		<dl class="fore2">		
			<dt><b></b><strong>配送方式</strong></dt>
			<dd>
				<div><a href="http://help.jd.com/help/question-64.html" target="_blank" rel="nofollow">上门自提</a></div>
				<div><a href="http://help.jd.com/help/question-360.html" target="_blank" rel="nofollow">211限时达</a></div>
				<div><a href="http://help.jd.com/help/distribution-768.html" target="_blank" rel="nofollow">配送服务查询</a></div>
				<div><a href="http://help.jd.com/help/question-892.html#help2215" target="_blank" rel="nofollow">配送费收取标准</a></div>
			</dd>
		</dl>
		<dl class="fore3">
			<dt><b></b><strong>支付方式</strong></dt>
			<dd>
				<div><a href="http://help.jd.com/help/question-67.html" target="_blank" rel="nofollow">货到付款</a></div>
				<div><a href="http://help.jd.com/help/question-68.html" target="_blank" rel="nofollow">在线支付</a></div>
			</dd>
		</dl>
		<dl class="fore4">		
			<dt><b></b><strong>售后服务</strong></dt>
			<dd>
				<div><a href="http://myjd.jd.com/afs/help/afshelp.action" target="_blank" rel="nofollow">售后政策</a></div>
				<div><a href="http://help.jd.com/help/question-99.html" target="_blank" rel="nofollow">价格保护</a></div>
				<div><a href="http://help.jd.com/help/question-100.html" target="_blank" rel="nofollow">退款说明</a></div>
				<div><a href="http://help.jd.com/help/question-881.html" target="_blank" rel="nofollow">取消订单</a></div>
			</dd>
		</dl>
		<span class="clr"></span>
	</div>
</div>
<%--<div class="w" clstag="homepage|keycount|home2013|38a">--%>
<%--	<jsp:include page="footer-links.jsp"></jsp:include>--%>
<%--</div>--%>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/jquery-extend.js"></script>
<script type="text/javascript" src="/js/lib-v1.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/taotao.js" charset="utf-8"></script>
<script type="text/javascript"> (function(){
var A="<strong>热门搜索：</strong><a href='http://localhost:8082/search.html?q=苹果' target='_blank' style='color:#ff0000' clstag='homepage|keycount|home2013|03b1'>苹果</a><a href='http://localhost:8082/search.html?q=橙子' target='_blank'>橙子</a><a href='http://localhost:8082/search.html?q=火龙果' target='_blank'>火龙果</a><a href='http://localhost:8082/search.html?q=橙汁' target='_blank'>橙汁</a>";
var B=["苹果","橘子","橙子","火龙果","橙汁"];
B=pageConfig.FN_GetRandomData(B);
$("#hotwords").html(A);
var _searchValue = "${query}";
if(_searchValue.length == 0){
	_searchValue = B;
}
$("#key").val(_searchValue).bind("focus",function(){if (this.value==B){this.value="";this.style.color="#333"}}).bind("blur",function(){if (!this.value){this.value=B;this.style.color="#999"}});
})();
</script>