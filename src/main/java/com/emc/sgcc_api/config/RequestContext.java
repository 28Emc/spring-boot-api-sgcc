package com.emc.sgcc_api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Getter
@Setter
public class RequestContext {
    private Long userId;
    private Long systemId;
    private String apiKey;
    private String ip;
    private String userAgent;
}
