package com.emc.sgcc_api.service;

import com.emc.sgcc_api.config.RequestContext;
import com.emc.sgcc_api.entity.AuditAction;
import com.emc.sgcc_api.entity.AuditLogEntity;
import com.emc.sgcc_api.repository.AuditLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogService {
    private final AuditLogRepository repository;
    private final ObjectMapper mapper;

    public void saveLog(
            String entityName,
            String entityId,
            AuditAction action,
            Object beforeObj,
            Object afterObj,
            RequestContext ctx
    ) {
        try {
            AuditLogEntity log = new AuditLogEntity();
            log.setEntity(entityName);
            log.setEntityId(entityId);
            log.setAction(action.name());
            log.setBeforeData(beforeObj != null ? mapper.valueToTree(beforeObj) : null);
            log.setAfterData(afterObj != null ? mapper.valueToTree(afterObj) : null);
            log.setUserId(ctx.getUserId());
            log.setSystemId(ctx.getSystemId());
            log.setIpAddress(ctx.getIp());
            log.setUserAgent(ctx.getUserAgent());
            repository.save(log);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
