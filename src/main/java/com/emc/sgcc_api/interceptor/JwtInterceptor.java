package com.emc.sgcc_api.interceptor;

import com.emc.sgcc_api.config.RequestContext;
import com.emc.sgcc_api.exception.MissingHeaderException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final RequestContext requestContext;

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {

        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new MissingHeaderException("Authorization (Bearer token)", null);
        }

        String token = header.substring(7);

        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Long userId = claims.get("sub", Integer.class).longValue();
            Long systemId = claims.get("systemId", Integer.class).longValue();

            requestContext.setUserId(userId);

            // si no llena systemId por header, lo llena desde el JWT
            if (requestContext.getSystemId() == null && systemId != null) {
                requestContext.setSystemId(systemId);
            }

        } catch (Exception ignored) {
            throw new MissingHeaderException("Authorization (Bearer token)", "Invalid or expired JWT token");
        }

        return true;
    }
}
