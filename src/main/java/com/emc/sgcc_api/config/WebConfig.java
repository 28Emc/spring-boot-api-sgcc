package com.emc.sgcc_api.config;

import com.emc.sgcc_api.interceptor.ApiKeyInterceptor;
import com.emc.sgcc_api.interceptor.JwtInterceptor;
import com.emc.sgcc_api.interceptor.SystemIdInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final ApiKeyInterceptor apiKeyInterceptor;
    private final SystemIdInterceptor systemIdInterceptor;
    private final JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyInterceptor);
        registry.addInterceptor(systemIdInterceptor);
        registry.addInterceptor(jwtInterceptor);
    }
}
