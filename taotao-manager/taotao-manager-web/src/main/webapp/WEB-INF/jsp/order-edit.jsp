<%--
  Created by IntelliJ IDEA.
  User: 郜杰
  Date: 2020/2/18
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
    <form id="orderEditForm" class="orderForm" method="post">
        <input type="hidden" name="orderId"/>
        <input type="hidden" name="userId" />
        <input type="hidden" name="paymentType"/>
        <input type="hidden" name="payment"/>
        <input type="hidden" name="status"/>
        <input type="hidden" name="buyerMessage"/>
        <input type="hidden" name="buyerNick">
        <table cellpadding="5">
            <tr>
                <td>物流名称:</td>
                <td><input class="easyui-textbox" type="text" name="shippingName" data-options="required:true" style="width: 280px;"/></td>
            </tr>
            <tr>
                <td>物流单号:</td>
                <td><input class="easyui-textbox" type="number" name="shippingCode" data-options="required:true" style="width: 280px;"/></td>
            </tr>

        </table>

    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
    </div>
</div>
<script type="text/javascript">
    function submitForm(){
        if(!$('#orderEditForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }

        $.post("/order/update",$("#orderEditForm").serialize(), function(data) {
            if (data.status == 200) {
                $.messager.alert('提示', '修改订单成功!', 'info', function () {
                    $("#orderEditWindow").window('close');
                    $("#orderList").datagrid("reload");
                });
            }
        });
    }
</script>