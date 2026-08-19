package com.digicart.auditlog.dto;

import jakarta.validation.constraints.Min;

public record AuditSettingsUpdateRequest(
    @Min(1) Integer retentionDays
) {}
