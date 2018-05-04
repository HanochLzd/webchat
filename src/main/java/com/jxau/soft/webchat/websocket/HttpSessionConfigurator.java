package com.jxau.soft.webchat.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author Hanoch
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    /**
     * 不是很懂webSocket配置
     *
     * 我正在尝试在握手过程中发送自定义标题。我可以用ServerEndpointConfig.Configurator拦截握手，
     * 并覆盖modifyHandshake
     *
     * @param sec ServerEndpointConfig
     * @param request HandshakeRequest
     * @param response HandshakeResponse
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
