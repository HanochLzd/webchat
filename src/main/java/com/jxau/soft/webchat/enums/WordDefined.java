package com.jxau.soft.webchat.enums;

/**
 * @author Hanoch
 */

public enum WordDefined {

    /**
     * 提示信息字段枚举
     */
    LOGIN_USERID_ERROR("用户名不存在!"),
    LOGIN_PASSWORD_ERROR("密码错误!"),
    LOGIN_USERID_DISABLED("账号已被禁用!"),
    LOGIN_NO_LOGED("未登录!"),
    LOGIN_SUCCESS("登录成功!"),
    LOGOUT_SUCCESS("注销成功!"),

    REG_USERID_REGISTERED("该用户名已被注册"),
    REG_SUCCESS("注册成功"),

    LOG_TYPE_LOGIN("登陆"),
    LOG_TYPE_LOGOUT("注销"),
    LOG_TYPE_ADD("新增"),
    LOG_TYPE_UPDATE("更新"),
    LOG_TYPE_DELETE("删除"),
    LOG_TYPE_COMPLETE("完成"),
    LOG_TYPE_IMPORT("导入"),
    LOG_TYPE_EXPORT("导出"),
    LOG_TYPE_DEPLOY("部署"),
    LOG_TYPE_START("启动"),

    LOG_DETAIL_USER_LOGIN("用户登陆"),
    LOG_DETAIL_USER_LOGOUT("用户退出"),
    LOG_DETAIL_UPDATE_PROFILE("更新用户资料"),
    LOG_DETAIL_UPDATE_PROFILEHEAD("更新用户头像"),
    LOG_DETAIL_SYSCONFIG("系统设置"),
    LOG_DETAIL_UPDATE_PASSWORD("更新密码");

    private String wordDefined;

    public String getWordDefined() {
        return wordDefined;
    }

    WordDefined(String wordDefined) {
        this.wordDefined = wordDefined;
    }
}
