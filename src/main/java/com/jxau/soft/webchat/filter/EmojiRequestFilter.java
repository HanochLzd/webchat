package com.jxau.soft.webchat.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤emoji表情
 * 将emoji表情转换成特定字符，以便存入数据库
 * (使用失败，且造成网页打开缓慢和一些未知问题)
 * @author Hanoch
 */
public class EmojiRequestFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new EmojiHttpServletRequestWrapper((HttpServletRequest) servletRequest),
                servletResponse);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
