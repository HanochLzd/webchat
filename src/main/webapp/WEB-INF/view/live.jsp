<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" href="${ctx}/static/plugins/amaze/css/amazeui.min.css">


<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/video/js/jquery.colorpicker.js"></script>
<script src="https://cdn.bootcss.com/webrtc-adapter/5.0.4/adapter.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/plugins/video/js/live_socket.js"></script>--%>
<script type="text/javascript" src="${ctx}/static/plugins/video/js/web_RTC.js?v=1.1"></script>
<%--<script type="text/javascript" src="${ctx}/static/plugins/video/js/send_Barrage.js"></script>--%>
<%--<script type="text/javascript" src="${ctx}/static/plugins/video/js/ui.js"></script>--%>
<script type="text/javascript" src="${ctx}/static/plugins/video/js/format_date.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/video/js/full_screen.js"></script>

<div class="center_content">
    <div class="dm">
        <div class="d_mask">
            <video id="live_video" class="barrage_video" autoplay width="650px" height="420px" preload="auto"
                   loop controls poster="video/img/zhibo.jpg">
            </video>


            <video id="view_video" autoplay width="350px" height="220px" preload="auto"
                   loop controls></video>
            <%--隐藏的观众视频流--%>
        </div>
        <div class="d_show"></div>
    </div>
    <div id="sendnav">
        <button id="openbtn" type="button" class="am-btn am-btn-xs am-btn-primary am-round">
            <span class="am-icon-video-camera"></span> Start
        </button>
        <%--<button class="fontbutton_config btn" style="border-radius:0;" id="sendfont_speed" type="button"><i--%>
        <%--class="glyphicon glyphicon-plane"--%>
        <%--style="color: #f92231;font-size: 13px;top: 3px;left: -2px"></i>字体速度--%>
        <%--</button>--%>
        <%--<button class="fontbutton_config btn" style="border-radius:0;" id="sendfont_size" type="button"><i--%>
        <%--class="glyphicon glyphicon-text-width"--%>
        <%--style="color: #4cb0f9;font-size: 13px;top: 3px;left: -2px"></i>字体大小--%>
        <%--</button>--%>
        <%--<button class="fontbutton_config btn" style="border-radius:0;" id="sendcolor" type="button"--%>
        <%--value="#ffffff">--%>
        <%--<img src="video/img/colorpicker.png" width="14" height="14">--%>
        <%--字体颜色--%>
        <%--</button>--%>
        <%--<input id="barr_text" type="text" class="form-control" maxlength="50"/>--%>
        <%--<button class="fontbutton_config btn" style="border-radius:0;" id="sendbtn" type="button">发送--%>
        <%--</button>--%>
        <%--<div id="send_cope">--%>
        <%--<div id="send_cope_content"><a href="#"--%>
        <%--onclick=" $('#myModal').modal('show');$('#xian').css({left:'2px'});login_model_show();">登录</a>--%>
        <%--或者<a href="#"--%>
        <%--onclick="$('#myModal').modal('show'); $('#xian').animate({left:'51px'});resign_model_show()">注册</a>后就能发弹幕啦(●'◡'●)~--%>
        <%--</div>--%>
        <%--</div>--%>
    </div>
</div>

<script>

    var pc;//rtc连接对象
    //PeerConnection兼容版本
    var PeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
    //getUserMedia兼容版本
    var UserMedia = (navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
    var localVideoUrl;//本地视频源
    var remoteVideoUrl;//远程视频源
    var localStream;//本地视频流
    var remoteStream;//远程视频流

    var isprovider = false;//真正的视频主播页面

    <%--var roomId = "${liveRoom.roomId}";--%>
    <%--var liverId = "${liveRoom.liverId}";//主播ID--%>
    <%--var liverName = "${liveRoom.liverName}";//主播name--%>
    //var websocket_rtc = null;

    var session_id;
    var pc_opened_array = [];

    var username_my = "${sessionScope.user.userName}";//我的用户名
    var userPicPath = "${sessionScope.user.userPicPath}";//用户头像信息
    var userid_my = "${sessionScope.user.userId}";//获取我的userid

    if (userid_my != null && userid_my.length > 0) {
        islogined = true;
    } else {
        islogined = false;
    }

    // login_update();//更新用户登录区界面

    // websocket_functions();//socket启动


    $(function () {


        $("#sendbtn").click(function () {//弹幕发送按钮
            live_send_Barrage(1);
        });

        $("#openbtn").click(function () {//启动直播
            // if (!islived) {
            //     isprovider = true;//标记为视频提供者
            //     getUserMedia();
            // } else {
            //     close_live();
            // }
            getUserMedia();
        });


        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function (event) {
            close_live();
        };


        // $(".left_cope").width((($("#video_father").width() - 1174) / 2))
        // $(".right_cope").width((($("#video_father").width() - 1174) / 2))
        // window.onresize = function () {
        //     $(".left_cope").width((($("#video_father").width() - 1174) / 2))
        //     $(".right_cope").width((($("#video_father").width() - 1174) / 2))
        // }

    });
</script>