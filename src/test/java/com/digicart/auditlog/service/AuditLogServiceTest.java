package com.digicart.auditlog.service;

import com.digicart.auditlog.dto.AuditLogCreateRequest;
import com.digicart.auditlog.entity.AuditLog;
import com.digicart.auditlog.repository.AuditLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditLogServiceTest {

    @Mock
    private AuditLogRepository auditLogRepository;

    @InjectMocks
    private AuditLogService auditLogService;

    @Test
    void createDefaultsLevelInfo() {
        AuditLogCreateRequest req = new AuditLogCreateRequest("order-service", null, "GET", "/orders", null, null, null, 200, 12, "ok", null, "127.0.0.1");
        when(auditLogRepository.save(any(AuditLog.class))).thenAnswer(i -> i.getArgument(0));
        AuditLog log = auditLogService.create(req);
        assertThat(log.getLevel()).isEqualTo("info");
        assertThat(log.getService()).isEqualTo("order-service");
    }

    @Test
    void findAllByStoreId() {
        when(auditLogRepository.findByStoreId(any(), any(PageRequest.class))).thenReturn(List.of(new AuditLog()));
        when(auditLogRepository.count()).thenReturn(1L);
        Map<String, Object> page = auditLogService.findAll(null, null, "s1", null, 1, 50);
        assertThat(page.get("total")).isEqualTo(1L);
        assertThat((List<?>) page.get("logs")).hasSize(1);
    }

    @Test
    void purgeDeletesOlder() {
        AuditLog old = new AuditLog();
        when(auditLogRepository.findByCreatedAtBefore(any(Instant.class))).thenReturn(List.of(old));
        assertThat(auditLogService.purgeOlderThan(30)).isEqualTo(1);
    }
}
