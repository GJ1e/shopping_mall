<%--
  Created by IntelliJ IDEA.
  User: 郜杰
  Date: 2020/2/17
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <table class="easyui-datagrid" id="userList" title="会员列表"
           data-options="singleSelect:false,collapsible:true,pagination:true,url:'/user/list',method:'get',pageSize:10">
        <thead>
        <tr>
            <th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'id',width:100">用户ID</th>
            <th data-options="field:'username',width:100">用户昵称</th>
            <th data-options="field:'phone',width:100">手机号</th>
            <th data-options="field:'email',width:100">邮箱</th>
            <th data-options="field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">注册日期</th>
        </tr>
        </thead>
    </table>
<script>

</script>