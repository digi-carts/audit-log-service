package com.digicart.auditlog.dto;

import jakarta.validation.constraints.Min;

/**
 * Request/response DTO: Audit Settings Update Request.
 */
public record AuditSettingsUpdateRequest(
    @Min(1) Integer retentionDays
) {}
