package com.digicart.auditlog.cucumber;

import com.digicart.auditlog.service.AuditLogService;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

public class AuditStepDefinitions {
    @Autowired
    AuditLogService auditLogService;

    @Before
    public void stubs() {
        when(auditLogService.findAll(isNull(), isNull(), isNull(), isNull(), anyInt(), anyInt()))
                .thenReturn(Map.of("logs", List.of(), "total", 0L, "page", 1, "limit", 50));
    }
}
