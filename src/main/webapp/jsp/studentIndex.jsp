<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 14360
  Date: 2021/4/21
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生登录</title>
    <script src="../static/js/jquery-3.6.0.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <script src="../static/js/studentIndex.js"></script>
</head>
<body>
<%--后台返回的提示信息--%>
<input id="msg" type="hidden" value="${msg}" />

欢迎${userName}同学!
<a href="personalInformation">个人信息</a>
<%--新增教师按钮--%>
<div class="layui-input-inline">
    <button type="button" onclick="selectRole()" class="layui-btn layui-btn-primary layui-btn-radius">请假</button>
</div>
<a href="leave">请假记录</a>
<a href="">证书上传</a>
<a href="">报修</a>
<div>

<%-- 个人信息显示 --%>
<c:if test="${not empty(personalInformation)}">
        <table border="1" cellspacing="1" cellpadding="1" align="center">
            <form action="personalInformationModify" method="post">
            <c:forEach items="${personalInformation}" var="personalInformation">
            <tr>
                <td>账号：</td>
                <td><input type="text" name="studentNumber" disabled="disabled" value="${personalInformation.studentNumber}"></td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input name="studentName" disabled="disabled" value="${personalInformation.studentName}"></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td><input type="text" id="studentGender" name="studentGender" disabled="disabled" value="${personalInformation.studentGender}"/></td>
            </tr>
            <tr>
                <td>出生日期：</td>
                <td><input id="dateOfbirth" type="date" name="dateOfbirth" disabled="disabled" value="<fmt:formatDate value="${personalInformation.dateOfbirth}" pattern="yyyy-MM-dd"/>"/></td>
            </tr>
            <tr>
                <td>班级：</td>
                <td><input disabled="disabled" name="classId" value="${personalInformation.classId}"/></td>
            </tr>
            <tr>
                <td>住宿号：</td>
                <td><input disabled="disabled" name="dormitoryNumberOrDayReading" value="${personalInformation.dormitoryNumberOrDayReading}"/></td>
            </tr>
            <tr>
                <td>手机号：</td>
                <td><input type="tel" required="required" maxlength="11" pattern="^(0|86|17951)?1[0-9]{10}" id="studentPhoneNumber" name="studentPhoneNumber" oninvalid="validatelt(this,'请输入11位手机号码')" disabled="disabled" value="${personalInformation.studentPhoneNumber}"></td>
            </tr>
            <tr>
                <td colspan="2"><input id="submit" type="submit" style="display: none" value="保存"/></td>
            </tr>
            </c:forEach>
            </form>
        </table>
    <button id="edit">编辑</button>
    <button id="cancel" style="display: none">取消</button>

</c:if>


<%--布局无视下面结构--%>

    <!-- 请假弹出内容 -->
    <div class="layui-row" id="newLeave" style="display:none;">

        <div class="layui-col-md11">

            <form class="layui-form" lay-filter="formTestFilter2121" method="post" action="leaveAdd" enctype="multipart/form-data" >

<%--                <div class="layui-form-item">--%>
<%--                    <label class="layui-form-label">开始时间：</label>--%>
<%--                    <div class="layui-input-inline">--%>
<%--                        <input type="datetime-local" name="startTime" class="layui-input"/>--%>
<%--                    </div>--%>

<%--                    <label class="layui-form-label">结束时间</label>--%>
<%--                    <div class="layui-input-inline">--%>
<%--                        <input type="datetime-local" name="endTime" class="layui-input"/>--%>
<%--                    </div>--%>

<%--                </div>--%>

<%--                <div class="layui-form-item">--%>
<%--                    <label class="layui-form-label">请假原因</label>--%>
<%--                    <div class="layui-input-inline">--%>
<%--                        <input type="text" name="reason" class="layui-input"/>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <div class="layui-form-item">
                    <label class="layui-form-label">请假凭证</label>
                    <div class="layui-input-inline">
                        <input type="file" name="file" class="layui-input"/>
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

</div>
</body>
</html>
