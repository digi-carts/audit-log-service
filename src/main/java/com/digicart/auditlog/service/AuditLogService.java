package com.digicart.auditlog.service;

import com.digicart.auditlog.dto.AuditLogCreateRequest;
import com.digicart.auditlog.entity.AuditLog;
import com.digicart.auditlog.repository.AuditLogRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * Application service implementing audit log use cases for <em>audit-log-service</em>.
 */
@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public Map<String, Object> findAll(String service, String level, String storeId,
                                        String userId, int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by("createdAt").descending());
        List<AuditLog> logs;

        if (storeId != null && !storeId.isBlank()) {
            logs = auditLogRepository.findByStoreId(storeId, pageRequest);
        } else if (userId != null && !userId.isBlank()) {
            logs = auditLogRepository.findByUserId(userId, pageRequest);
        } else if (service != null && !service.isBlank()) {
            logs = auditLogRepository.findByService(service, pageRequest);
        } else if (level != null && !level.isBlank()) {
            logs = auditLogRepository.findByLevel(level, pageRequest);
        } else {
            logs = auditLogRepository.findAll(pageRequest).getContent();
        }

        long total = auditLogRepository.count();
        return Map.of("logs", logs, "total", total, "page", page, "limit", limit);
    }

    public Optional<AuditLog> findById(UUID id) {
        return auditLogRepository.findById(id);
    }

    @Transactional
    public AuditLog create(AuditLogCreateRequest req) {
        AuditLog log = new AuditLog();
        log.setService(req.service());
        log.setLevel(req.level() != null ? req.level() : "info");
        log.setMethod(req.method());
        log.setPath(req.path());
        log.setUserId(req.userId());
        log.setUserRole(req.userRole());
        log.setStoreId(req.storeId());
        log.setStatusCode(req.statusCode());
        log.setDuration(req.duration());
        log.setMessage(req.message());
        log.setMeta(req.meta());
        log.setIpAddress(req.ipAddress());
        return auditLogRepository.save(log);
    }

    @Transactional
    public void delete(UUID id) {
        AuditLog log = auditLogRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("AuditLog not found"));
        auditLogRepository.delete(log);
    }

    @Transactional
    public int purgeOlderThan(int retentionDays) {
        Instant cutoff = Instant.now().minus(retentionDays, ChronoUnit.DAYS);
        List<AuditLog> old = auditLogRepository.findByCreatedAtBefore(cutoff);
        auditLogRepository.deleteAll(old);
        return old.size();
    }
}
