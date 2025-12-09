package com.emc.sgcc_api.interceptor;

import com.emc.sgcc_api.config.RequestContext;
import com.emc.sgcc_api.exception.MissingHeaderException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${security.apiKey}")
    private String currentApiKey;

    private final RequestContext requestContext;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        String apiKey = req.getHeader("x-api-key");

        if (apiKey == null || apiKey.isBlank()) {
            throw new MissingHeaderException("x-api-key", null);
        }

        if (!apiKey.equals(currentApiKey)) {
            throw new MissingHeaderException("x-api-key", "Invalid API Key");
        }

        requestContext.setApiKey(apiKey);
        requestContext.setIp(req.getRemoteAddr());
        requestContext.setUserAgent(req.getHeader("User-Agent"));

        return true;
    }
}
