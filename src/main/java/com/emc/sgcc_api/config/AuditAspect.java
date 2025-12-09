package com.emc.sgcc_api.config;

import com.emc.sgcc_api.entity.AuditAction;
import com.emc.sgcc_api.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final AuditLogService auditService;
    private final RequestContext requestContext;

    @Around("@annotation(auditable)")
    public Object audit(ProceedingJoinPoint pjp, Auditable auditable) throws Throwable {
        Object before = null;
        Object after;
        String entityName = auditable.entity();
        AuditAction action = auditable.action();
        Object entityId = null;

        if (action == AuditAction.UPDATE || action == AuditAction.DELETE) {
            before = pjp.getArgs()[0];
            entityId = extractEntityId(before);
        }

        Object result = pjp.proceed();

        if (action == AuditAction.CREATE || action == AuditAction.UPDATE) {
            after = result;
            if (entityId == null) {
                entityId = extractEntityId(after);
            }
        } else {
            after = null;
        }

        auditService.saveLog(entityName, entityId != null ? String.valueOf(entityId) : null, action, before, after,
                requestContext);

        return result;
    }

    private Object extractEntityId(Object obj) {
        if (obj == null) return null;

        try {
            Method getId = null;

            try {
                getId = obj.getClass().getMethod("getId");
            } catch (NoSuchMethodException ignored) {
            }

            if (getId == null) {
                try {
                    getId = obj.getClass().getMethod("id");
                } catch (NoSuchMethodException ignored) {
                }
            }

            if (getId == null) {
                for (Method m : obj.getClass().getMethods()) {
                    if (m.getName().startsWith("get") && m.getName().endsWith("Id")) {
                        getId = m;
                        break;
                    }
                }
            }

            if (getId != null) {
                return getId.invoke(obj);
            }

        } catch (Exception ignored) {
        }

        return null;
    }
}
