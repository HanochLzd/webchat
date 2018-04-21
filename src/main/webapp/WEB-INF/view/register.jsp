<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <title>WebChat | 注册</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="<%=path%>/static/source/css/layui.css" rel='stylesheet' type='text/css'/>
    <link href="<%=path%>/static/source/css/global.css" rel='stylesheet' type='text/css'/>
    <script src="<%=path%>/static/plugins/jquery/jquery-2.1.4.min.js"></script>
    <script src="<%=path%>/static/plugins/layer/layer.js"></script>
    <script src="<%=path%>/static/plugins/layer/layui.js" charset="utf-8"></script>
</head>
<body>


<%--
1.账号：自行设置
2.密码：自行设置
3.头像：注册后设置
3.个性签名：可以设置，之后补充
4.昵称：自行
5.性别
6.年龄
--%>
<h1 onclick="home(id);">WebChat</h1>
<div class="layui-container fly-marginTop">

    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li><a href="${pageContext.request.contextPath}/user/login/">登入</a></li>
                <li class="layui-this">注册</li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post" action="${pageContext.request.contextPath}/register">
                            <div class="layui-form-item">
                                <label for="L_id" class="layui-form-label">账号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_id" name="userId" required lay-verify="userid"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_username" class="layui-form-label">昵称</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_username" name="userNickName" required
                                           lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">性别</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="userSex" value="1" title="男" checked="">
                                    <input type="radio" name="userSex" value="2" title="女">
                                    <%--<input type="radio" name="sex" value="禁" title="禁用" disabled="">--%>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="password" required lay-verify="pass"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_repass" class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_repass" name="repassword" required lay-verify="pass"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <%--
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">人类验证</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_vercode" name="vercode" required lay-verify="required"
                                           placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">
                                    <span style="color: #c00;">{{d.vercode}}</span>
                                </div>
                            </div>
                            --%>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="*" lay-submit>立即注册</button>
                                <span style="color: #c00;" id="span"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
    $(function () {
        layui.use('form', function () {
            var form = layui.form;
            form.render();
            //自定义验证规则
            form.verify({
                userid: function (value) {
                    if (value.length < 2) {
                        return 'ID至少得3个字符啊';
                    }
                }
                , pass: [/(.+){6,12}$/, '密码必须6到12位']

            });
        });
        if ("${error}") {
            layer.msg('${error}', {
                offset: 0
            });
        }

        if ("${message}") {
            layer.open({
                title: '${message}',
                content: 'ID:${user.userId}',
                move: false,
                yes: function (index) {
                    location.href = '${pageContext.request.contextPath}/user/login';
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        }
    });
</script>
</body>
</html>