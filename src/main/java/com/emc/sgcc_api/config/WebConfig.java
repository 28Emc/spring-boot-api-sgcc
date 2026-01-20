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
        String[] swaggerExcludePatterns = {
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/v1/api-docs",
                "/v1/api-docs/**",
                "/v3/api-docs/**",
                "/v3/api-docs.yaml",
                "/sgcc/v1/api-docs",
                "/sgcc/v1/api-docs/**"
        };

        // Excluir rutas de documentación de Swagger del interceptor ApiKey
        registry.addInterceptor(apiKeyInterceptor)
                .excludePathPatterns(swaggerExcludePatterns);

        // Excluir rutas de documentación de Swagger del interceptor SystemId
        registry.addInterceptor(systemIdInterceptor)
                .excludePathPatterns(swaggerExcludePatterns);

        // Excluir rutas de documentación de Swagger del interceptor JWT
        registry.addInterceptor(jwtInterceptor)
                .excludePathPatterns(swaggerExcludePatterns);
    }
}
