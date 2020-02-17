<%--
  Created by IntelliJ IDEA.
  User: 郜杰
  Date: 2020/2/17
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="orderList" title="订单列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/order/list',method:'get',pageSize:10,toolbar:orderListToolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'orderId',width:70">订单号</th>
        <th data-options="field:'userId',width:70">用户ID</th>
        <th data-options="field:'paymentType',width:70,align:'center',formatter:TAOTAO.formatOrderPaymentType">支付类型</th>
        <th data-options="field:'payment',width:70,align:'right'">实付金额</th>
        <th data-options="field:'status',width:70,align:'center',formatter:TAOTAO.formatOrderStatus">订单状态</th>

        <th data-options="field:'buyerMessage',width:170">买家留言</th>
        <th data-options="field:'shippingName',width:70">物流名称</th>
        <th data-options="field:'shippingCode',width:120">物流单号</th>
        <th data-options="field:'consignTime',width:150,align:'center',formatter:TAOTAO.formatDateTime">发货时间</th>
        <th data-options="field:'createTime',width:150,align:'center',formatter:TAOTAO.formatDateTime">订单创建</th>
        <th data-options="field:'endTime',width:150,align:'center',formatter:TAOTAO.formatDateTime">交易完成</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">

    function getSelectionsIds() {
        var orderList = $("#orderList");
        var sels = orderList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

    var orderListToolbar = [{
        text: '发货',
        iconCls: 'icon-edit',
        handler: function () {
            var ids = getSelectionsIds();
            if(ids.length == 0){
                $.messager.alert('提示','必须选择一个订单才能编辑!');
                return ;
            }
            if(ids.indexOf(',') > 0){
                $.messager.alert('提示','只能选择一个订单!');
                return ;
            }

        }
    },
        {
            text:'删除',
            iconCls:'icon-cancel',
            handler:function(){
                var ids = getSelectionsIds();
                if(ids.length == 0){
                    $.messager.alert('提示','未选中商品!');
                    return ;
                }
                $.messager.confirm('确认','确定删除ID为 '+ids+' 的商品吗？',function(r){
                    if (r){
                        var params = {"ids":ids};
                        $.post("/order/delete",params, function(data){
                            if(data.status == 200){
                                $.messager.alert('提示','删除商品成功!',undefined,function(){
                                    $("#itemList").datagrid("reload");
                                });
                            }
                        });
                    }
                });
            }

        },

    ]

</script>