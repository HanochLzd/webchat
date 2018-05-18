<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="include/commonfile.jsp"/>
<!-- 列表区 -->
<div class="am-panel am-panel-default" style="">
    <div class="am-panel-hd">
        <h3 class="am-panel-title">在线列表 [<span id="onlinenum"></span>]</h3>
    </div>
    <ul class="am-list am-list-static am-list-striped">
        <li>图灵机器人
            <button class="am-btn am-btn-xs am-btn-danger" id="tuling" data-am-button>未上线</button>
        </li>
    </ul>
    <ul class="am-list am-list-static am-list-striped" id="list">
    </ul>
</div>