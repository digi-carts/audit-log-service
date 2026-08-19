package com.digicart.auditlog.controller;

import com.digicart.auditlog.dto.AuditSettingsUpdateRequest;
import com.digicart.auditlog.service.AuditSettingsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing audit settings HTTP APIs for <em>audit-log-service</em>.
 */
@RestController
@RequestMapping("/audit-settings")
public class AuditSettingsController {

    private final AuditSettingsService auditSettingsService;

    /**
     * Creates a new {@code AuditSettingsController}.
     *
     * @param auditSettingsService audit settings service collaborator
     */
    public AuditSettingsController(AuditSettingsService auditSettingsService) {
        this.auditSettingsService = auditSettingsService;
    }

    /**
     * Handles GET.
     *
     * @param xUserId x user id
     * @param xUserRole x user role
     * @return HTTP response
     */
    @GetMapping
    public ResponseEntity<?> getSettings(
        @RequestHeader(value = "X-User-Id", required = false) String xUserId,
        @RequestHeader(value = "X-User-Role", required = false) String xUserRole
    ) {
        return ResponseEntity.ok(auditSettingsService.getSettings());
    }

    /**
     * Handles PUT.
     *
     * @param req request payload
     * @param xUserId x user id
     * @param xUserRole x user role
     * @return HTTP response
     */
    @PutMapping
    public ResponseEntity<?> update(
        @Valid @RequestBody AuditSettingsUpdateRequest req,
        @RequestHeader(value = "X-User-Id", required = false) String xUserId,
        @RequestHeader(value = "X-User-Role", required = false) String xUserRole
    ) {
        return ResponseEntity.ok(auditSettingsService.update(req));
    }
}
