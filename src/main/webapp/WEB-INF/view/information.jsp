<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>WebChat | 个人信息</title>
    <jsp:include page="include/commonfile.jsp"/>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="am-cf admin-main">
    <jsp:include page="include/sidebar.jsp"/>

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人信息</strong> /
                <small>info</small>
            </div>
        </div>
        <div class="am-tabs am-margin" data-am-tabs>
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab1">个人信息</a></li>
            </ul>
            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                    <div class="am-g">
                        <c:set value="${user}" var="user"/>
                        <div class="am-u-md-3"><b>昵称:</b></div>
                        <div class="am-u-md-3">
                            ${user.userNickName}
                        </div>
                        <div class="am-u-md-6" style="float: right">
                            <img class="am-circle" src="${user.userProfilehead}" width="140" height="140"
                                 alt="${user.userNickName}"/>
                        </div>

                        <div class="am-u-md-3"><b>性别:</b></div>
                        <div class="am-u-md-3">
                            <c:if test="${user.userSex == null || empty user.userSex}">未知</c:if>
                            <c:if test="${user.userSex != null && not empty user.userSex}">
                                <c:if test="${user.userSex == 1}">男</c:if>
                                <c:if test="${user.userSex == 2}">女</c:if>
                                <c:if test="${user.userSex == -1}">保密</c:if>
                            </c:if>
                        </div>
                        <div class="am-u-md-3"><b>年龄:</b></div>
                        <div class="am-u-md-3">
                            <c:if test="${user.userAge == null || user.userAge == ''}">未知</c:if>
                            <c:if test="${user.userAge != null && user.userAge != ''}">${user.userAge}</c:if>
                        </div>
                        <div class="am-u-md-3"><b>简介:</b></div>
                        <div class="am-u-md-3">
                            <c:if test="${user.userProfile == null || user.userProfile == ''}">
                                这个人很懒,什么都没有留下!
                            </c:if>
                            <c:if test="${user.userProfile != null && user.userProfile != ''}">
                                ${user.userProfile}
                            </c:if>
                        </div>
                        <div class="am-u-md-3"><b>注册时间</b></div>
                        <div class="am-u-md-3">${user.userCreateTime}</div>
                        <div class="am-u-md-3"><b>最后登录</b></div>
                        <div class="am-u-md-3">${user.userLastTime}</div>
                    </div>
                </div>
            </div>
        </div>
        <!-- content end -->
    </div>
    <a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
        <span class="am-icon-btn am-icon-th-list"></span>
    </a>
    <jsp:include page="include/footer.jsp"/>
</body>
</html>
