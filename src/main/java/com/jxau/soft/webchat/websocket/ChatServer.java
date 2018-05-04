package com.jxau.soft.webchat.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Hanoch
 * TODO:重新设计，加入好友系统，1.能够接收好友请求 2.展示分组、好友在线离线状态 3.接收好友消息 4.创建群聊并接收群聊消息
 */
@ServerEndpoint(value = "/chatServer", configurator = HttpSessionConfigurator.class)
public class ChatServer {
    /**
     * 记录当前在线连接数
     */
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 用户名
     */
    private String userid;

    /**
     * 在线列表,记录用户名称
     */
    private static List<String> list = new ArrayList<>();

    /**
     * 用户名和 websocket的session绑定的路由表
     */
    private static Map<String, Session> routetab = new HashMap<>();

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @param config EndpointConfig
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        //把当前连接存入webSocket中
        webSocketSet.add(this);
        addOnLineCount();
        //request的session
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.userid = (String) httpSession.getAttribute("userid");
        //在线列表,记录用户名称
        list.add(userid);
        routetab.put(userid, session);
        String message = getMessage("[" + userid + "]加入聊天室,当前在线人数为" + getOnlineCount() + "位", "notice", list);
        //广播推送消息
        broadcast(message);
    }

    /**
     * 连接关闭调用
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        //从在线列表移除这个用户
        list.remove(userid);
        routetab.remove(userid);
        String message = getMessage("[" + userid + "]离开了聊天室,当前在线人数为" + getOnlineCount() + "位", "notice", list);
        //广播
        broadcast(message);
    }

    /**
     * 接收客户端传来的消息message，判断是否有接收人而选择广播还是指定发送
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMassage(String message) {
        JSONObject chat = JSON.parseObject(message);
        JSONObject jsonMessage = JSON.parseObject(chat.get("message").toString());
        //如果to为空，广播发送；否则指定发送
        if (jsonMessage.get("to") == null || "".equals(jsonMessage.get("to"))) {
            broadcast(message);
        } else {
            String[] userList = jsonMessage.get("to").toString().split(",");
            //发送给自己
            singleSend(message, routetab.get(jsonMessage.get("from").toString()));
            //分别发送给指定用户
            for (String user : userList) {
                if (!user.equals(jsonMessage.get("from"))) {
                    singleSend(message, routetab.get(user));
                }
            }
        }
    }

    /**
     * 发生错误时调用
     *
     * @param error 错误
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    /**
     * 广播消息
     *
     * @param message 广播消息
     */
    private void broadcast(String message) {
        for (ChatServer chat : webSocketSet) {
            try {
                chat.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定发送
     *
     * @param message 发送的信息
     * @param session 与某个客户端的连接会话
     */
    private void singleSend(String message, Session session) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            onError(e);
        }
    }

    /**
     * 组装消息String->Json->String
     *
     * @param message 交互信息
     * @param type    信息类型
     * @param list    在线列表
     * @return String
     */
    private String getMessage(String message, String type, List list) {
        JSONObject member = new JSONObject();
        member.put("message", message);
        member.put("type", type);
        member.put("list", list);
        return member.toString();
    }

    private int getOnlineCount() {
        return onlineCount;
    }

    private void addOnLineCount() {
        ChatServer.onlineCount++;
    }

    private void subOnlineCount() {
        ChatServer.onlineCount--;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
