<%--
  Created by IntelliJ IDEA.
  User: xdd
  Date: 2021/4/11
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <title>管理员新增用户</title>
    <script src="../static/js/jquery-3.6.0.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <script src="../static/js/adminADDTransactionANdStudent.js"></script>
</head>
<body>
欢迎${userName}管理员!
<a href="adminIndex">返回上一层</a>

<%--后台返回的提示信息--%>
<input id="msg" type="hidden" value="${msg}" />

<%--新增教师按钮--%>
<div class="layui-input-inline">
    <button type="button" onclick="selectRole()" class="layui-btn layui-btn-primary layui-btn-radius">新增教师</button>
</div>

<%--新增学生按钮--%>
<div class="layui-input-inline">
    <button type="button" onclick="studentSelectRole()" class="layui-btn layui-btn-primary layui-btn-radius">新增学生</button>
</div>

<%--新增老师临时数据--%>
<c:if test="${not empty(addTransaction)}">
<table border="1" cellspacing="1" cellpadding="1" align="center">

    <caption><h2>新增教师临时列表</h2></caption>
    <tr>
        <th>账号</th>
        <th>姓名</th>
        <th>联系方式</th>
        <th>职务</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${addTransaction}" var="addTransaction">
        <tr>
            <td>${addTransaction.teacherNumber }</td>
            <td>${addTransaction.teacherName }</td>
            <td>${addTransaction.phoneNumber}</td>
            <td>${addTransaction.post}</td>
            <td><a href="deleteTransaction/${addTransaction.teacherNumber }">删除</a></td>
        </tr>
    </c:forEach>



    <tfoot>
    <tr>
        <td colspan="5">临时列表，数据库并未存储，需要点击保存才会存入数据库,ps:保存包括临时学生表和教师表同时保存</td>
    </tr>
    </tfoot>
</table>
    <%--    教师临时列表分页--%>
    <table border="1" cellspacing="1" cellpadding="1" align="center">
        <tr>
            <td><a href="TransactionId?id=${TransactionNumber-TransactionNumber}">首页</a></td>
            <c:forEach begin="1" end="${TransactionNumber}" varStatus="i">
                <td><a href="TransactionId?id=${i.index}">${i.index}</a></td>
            </c:forEach>
            <td><a href="TransactionId?id=${TransactionNumber}">末页</a></td>
        </tr>
    </table>
</c:if>

    <%--新增学生临时数据--%>
<c:if test="${not empty(addStudent)}">
        <table border="1" cellspacing="1" cellpadding="1" align="center">

            <caption><h2>新增学生临时列表</h2></caption>
            <tr>
                <th>账号</th>
                <th>姓名</th>
                <th>班级</th>
                <th>住宿情况</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${addStudent}" var="addStudent">
                <tr>
                    <td>${addStudent.studentNumber }</td>
                    <td>${addStudent.studentName }</td>
                    <td>${addStudent.classId}</td>
                    <td>${addStudent.dormitoryNumberOrDayReading}</td>
                    <td><a href="deleteStudent/${addStudent.studentNumber }">删除</a></td>
                </tr>
            </c:forEach>

            <tfoot>
            <tr>
                <td colspan="5">临时列表，数据库并未存储，需要点击保存才会存入数据库,ps:保存包括临时学生表和教师表同时保存</td>
            </tr>
            </tfoot>

    </table>
<%--    学生临时列表分页--%>
    <table border="1" cellspacing="1" cellpadding="1" align="center">
        <tr>
            <td><a href="StudentId?id=${StudentNumber-StudentNumber}">首页</a></td>
            <c:forEach begin="1" end="${StudentNumber}" varStatus="i">
                <td><a href="StudentId?id=${i.index}">${i.index}</a></td>
            </c:forEach>
            <td><a href="StudentId?id=${StudentNumber}">末页</a></td>
        </tr>
    </table>

</c:if>

<a href="preservation">保存</a>




<%--布局无视下面结构--%>

<!-- 新增教师弹出内容 -->
<div class="layui-row" id="newTransaction" style="display:none;">

    <div class="layui-col-md11">

        <form class="layui-form" lay-filter="formTestFilter2121" method="post" action="addTransaction" >

            <div class="layui-form-item">
                <label class="layui-form-label">账号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="teacherNumber" class="layui-input"/>
                </div>

                <label class="layui-form-label">姓名：</label>
                <div class="layui-input-inline">
                    <input type="text" name="teacherName" class="layui-input"/>
                </div>

            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">手机：</label>
                <div class="layui-input-inline">
                    <input type="text" name="phoneNumber" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">职务：</label>
                <div class="layui-input-inline">
                    <input type="text" name="post" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">

                <div class="layui-input-block">
                    <input type="submit" value="保存"/>
                </div>

            </div>
        </form>
    </div>
</div>

<!-- 新增学生弹出内容 -->
<div class="layui-row" id="newStudent" style="display:none;">

    <div class="layui-col-md11">

        <form class="layui-form" lay-filter="formTestFilter2121" method="post" action="addStudent">

            <div class="layui-form-item">
                <label class="layui-form-label">学号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="studentNumber" class="layui-input">
                </div>

                <label class="layui-form-label">姓名：</label>
                <div class="layui-input-inline">
                    <input type="text" name="studentName" class="layui-input">
                </div>

            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">班级：</label>
                <div class="layui-input-inline">
                    <input type="text" name="classId" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">住宿or走读：</label>
                <div class="layui-input-inline">
                    <input type="text" name="dormitoryNumberOrDayReading" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">

                <div class="layui-input-block">
                    <input type="submit" value="保存"/>
                </div>

            </div>
        </form>
    </div>
</div>

</body>
</html>