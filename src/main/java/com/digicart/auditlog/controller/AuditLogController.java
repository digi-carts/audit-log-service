package com.digicart.auditlog.controller;

import com.digicart.auditlog.dto.AuditLogCreateRequest;
import com.digicart.auditlog.entity.AuditLog;
import com.digicart.auditlog.service.AuditLogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(
        @RequestParam(required = false) String service,
        @RequestParam(required = false) String level,
        @RequestParam(required = false) String storeId,
        @RequestParam(required = false) String userId,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "50") int limit,
        @RequestHeader(value = "X-User-Id", required = false) String xUserId,
        @RequestHeader(value = "X-User-Role", required = false) String xUserRole
    ) {
        return ResponseEntity.ok(auditLogService.findAll(service, level, storeId, userId, page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
        @PathVariable UUID id,
        @RequestHeader(value = "X-User-Id", required = false) String xUserId,
        @RequestHeader(value = "X-User-Role", required = false) String xUserRole
    ) {
        return auditLogService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(
        @Valid @RequestBody AuditLogCreateRequest req,
        @RequestHeader(value = "X-User-Id", required = false) String xUserId,
        @RequestHeader(value = "X-User-Role", required = false) String xUserRole
    ) {
        AuditLog created = auditLogService.create(req);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
        @PathVariable UUID id,
        @RequestHeader(value = "X-User-Id", required = false) String xUserId,
        @RequestHeader(value = "X-User-Role", required = false) String xUserRole
    ) {
        auditLogService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/purge")
    public ResponseEntity<?> purge(
        @RequestHeader(value = "X-User-Role", required = false) String xUserRole
    ) {
        // Only admins/superadmins should trigger purge
        int deleted = auditLogService.purgeOlderThan(30);
        return ResponseEntity.ok(Map.of("deleted", deleted));
    }
}
