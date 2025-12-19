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
public class SystemIdInterceptor implements HandlerInterceptor {

    @Value("${security.systemId}")
    private String currentSystemId;

    private final RequestContext requestContext;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        if (req.getRequestURI().endsWith("/health-check")) {
            return true;
        }

        String systemId = req.getHeader("x-system-id");

        if (systemId == null) {
            throw new MissingHeaderException("x-system-id", null);
        }

        if (!systemId.equals(currentSystemId) || !systemId.matches("\\d+")) {
            throw new MissingHeaderException("x-system-id", "Invalid System ID");
        }

        requestContext.setSystemId(Long.parseLong(systemId));

        return true;
    }
}
