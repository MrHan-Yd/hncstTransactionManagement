<%--
  Created by IntelliJ IDEA.
  User: hncstXDD
  Date: 2021/4/11
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <title>学生事务管理系统登录</title>
    <script src="../static/js/jquery-3.6.0.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <script src="../static/js/LoginAjax.js"></script>
</head>
<body>
     <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
     <h1>学生事务管理系统登录</h1>
     <form action="logon">
         <label>帐号：<input type="text" name="userName"></label><br/>
         <label>密码：<input type="password" name="password"></label><br/>
         <label>验证码：<input type="text" name="checkCode"/><img src="checkCode" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'"></label><br/>
         <label>管理员<input type="radio" value="admin" name="choice"></label>
         <label>教师<input type="radio" value="transaction" name="choice"/></label>
         <label>学生<input type="radio" value="student" name="choice" checked/></label><br/>
         <label><input id="submit" value="登录" type="button"/></label>
     </form>
</body>
</html>
