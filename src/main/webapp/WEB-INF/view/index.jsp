<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>WebChat | 聊天</title>
    <jsp:include page="include/commonfile.jsp"/>
    <%--<%@ include file="include/commonfile.jsp" %>--%>
    <script src="${ctx}/static/plugins/sockjs/sockjs.js"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>


<div class="am-cf admin-main">


    <table>
        <tr>
            <td style="width: 259px;height: 548px;" valign="top">
                <jsp:include page="include/sidebar.jsp"/>

            </td>
            <td style="padding-left:10px" >
                <%--<div id="main" class="admin-content">--%>
                    <%--<div class="" style="width: 80%;float:left;">--%>
                        <%--<!-- 聊天区 -->--%>
                        <%--<div class="" id="chat-view-main">--%>
                            <%--<div class="am-scrollable-vertical" id="chat-view" style="height: 510px;">--%>
                                <%--<ul class="am-comments-list am-comments-list-flip" id="chat">--%>
                                <%--</ul>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<!-- 输入区 -->--%>
                        <%--<div class="am-form-group am-form">--%>
                <%--<textarea class="" id="message" name="message" rows="5" placeholder="这里输入你想发送的信息..."--%>
                          <%--onkeydown="keyDown(event)"></textarea>--%>
                        <%--</div>--%>
                        <%--<!-- 接收者 -->--%>
                        <%--<div class="" style="float: left">--%>
                            <%--<p class="am-kai">发送给 : <span id="sendto"></span>--%>
                                <%--<button class="am-btn am-btn-xs am-btn-danger" onclick="$('#sendto').text('全体成员')">复位</button>--%>
                            <%--</p>--%>
                        <%--</div>--%>
                        <%--<!-- 按钮区 -->--%>
                        <%--<div class="am-btn-group am-btn-group-xs" style="float:right;">--%>
                            <%--<button class="am-btn am-btn-default" type="button" onclick="getConnection()"><span--%>
                                    <%--class="am-icon-plug"></span> 连接--%>
                            <%--</button>--%>
                            <%--<button class="am-btn am-btn-default" type="button" onclick="closeConnection()"><span--%>
                                    <%--class="am-icon-remove"></span> 断开--%>
                            <%--</button>--%>
                            <%--<button class="am-btn am-btn-default" type="button" onclick="checkConnection()"><span--%>
                                    <%--class="am-icon-bug"></span> 检查--%>
                            <%--</button>--%>
                            <%--<button class="am-btn am-btn-default" type="button" onclick="clearConsole()"><span--%>
                                    <%--class="am-icon-trash-o"></span> 清屏--%>
                            <%--</button>--%>
                            <%--<button class="am-btn am-btn-default" type="button" onclick="sendMessage()"><span--%>
                                    <%--class="am-icon-commenting"></span> 发送--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<!-- 列表区 -->--%>
                    <%--<div class="am-panel am-panel-default" style="float:right;width: 20%;">--%>
                        <%--<div class="am-panel-hd">--%>
                            <%--<h3 class="am-panel-title">在线列表 [<span id="onlinenum"></span>]</h3>--%>
                        <%--</div>--%>
                        <%--<ul class="am-list am-list-static am-list-striped">--%>
                            <%--<li>图灵机器人--%>
                                <%--<button class="am-btn am-btn-xs am-btn-danger" id="tuling" data-am-button>未上线</button>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                        <%--<ul class="am-list am-list-static am-list-striped" id="list">--%>
                        <%--</ul>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <iframe style="width: 1259px;height: 700px" frameborder="0" src="${pageContext.request.contextPath}/chatPage" name="main"></iframe>
            </td>
        </tr>
    </table>

    <!-- content start -->

    <!-- content end -->
</div>
<a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
    <span class="am-icon-btn am-icon-th-list"></span>
</a>
<%--<jsp:include page="include/footer.jsp"/>--%>

<script>
    var ws;
    if(null == ws){
    var wsServer = "ws://" + location.host + "${pageContext.request.contextPath}" + "/chatServer";
    ws = new WebSocket(wsServer); //创建WebSocket对象
</script>
</body>
</html>
