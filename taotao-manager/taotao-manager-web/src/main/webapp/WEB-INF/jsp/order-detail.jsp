<%--
  Created by IntelliJ IDEA.
  User: 郜杰
  Date: 2020/2/18
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-panel" title="Nested Panel" data-options="width:'100%',minHeight:500,noheader:true,border:false" style="padding:10px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',split:false" style="width:250px;padding:5px">
           <input type="text" name="orderId" id="orderId"/>
            <button id="orderSearch" onclick="search()">搜索</button>
        </div>
        <div data-options="region:'center'" style="padding:5px">
            <table class="easyui-datagrid" id="orderDetail" data-options="singleSelect:false,collapsible:true,pagination:true,method:'get',pageSize:20,url:'/order/query/detail',queryParams:{orderId:0}">
                <thead>
                <tr>
                    <th data-options="field:'id',width:30">ID</th>
                    <th data-options="field:'orderId',width:70">订单号</th>
                    <th data-options="field:'title',width:150">商品名称</th>
                    <th data-options="field:'picPath',width:50,align:'center',formatter:TAOTAO.formatUrl">商品图片</th>
                    <th data-options="field:'num',width:50">数量</th>
                    <th data-options="field:'price',width:60,align:'center',formatter:TAOTAO.formatPrice">商品单价</th>
                    <th data-options="field:'totalFee',width:60,align:'center',formatter:TAOTAO.formatPrice">商品总价</th>
                    <th data-options="field:'itemId',width:60,align:'center'">商品ID</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script>
    function search() {
        var orderId = $("#orderId").val();
        var datagrid = $("#orderDetail");
        if (orderId != null){
            datagrid.datagrid('reload',{
                orderId : orderId
            });
        }
    };
</script>