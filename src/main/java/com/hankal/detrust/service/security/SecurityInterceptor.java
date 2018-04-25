package com.hankal.detrust.service.security;

import com.hankal.detrust.exception.GlobalException;
import com.hankal.detrust.result.CodeMsg;
import com.hankal.detrust.validator.TokenValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /**
         * 对来自后台的请求统一进行日志处理
         */
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info(String.format(
                "-> 请求参数, URL: %s, METHOD: %s, URI: %s, PARAMS: %s, content-type: ",
                url, method, uri, queryString, request.getHeader("content-type")));

        if (request.getParameterMap().size() > 0) {
            if (request.getParameterMap().containsKey("loginToken") ||
                    request.getParameterMap().containsKey("userId")) {

                String loginToken = request.getParameter("loginToken");
                String userId = request.getParameter("userId");

                if (TokenValidator.isToken(loginToken)) {
                    if (!TokenValidator.getUserId(loginToken).equals(userId)) {
                        throw new GlobalException(CodeMsg.USER_ID_SUPPORT_ERROR);
                    }
                }
            }
        }

        return true;
    }
}
