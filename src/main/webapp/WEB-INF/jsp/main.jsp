<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>主界面</title>
</head>
<body>
<ul>
    <s:hasRole name="管理员">
        <li><a href="user/userList">用户管理</a></li>
    </s:hasRole>
    <s:hasPermission name="书本查询">
        <li>用户新增</li>
    </s:hasPermission>
    <li>用户修改</li>
    <li>用户删除</li>

</ul>

</body>
</html>
