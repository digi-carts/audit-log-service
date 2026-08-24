package com.digicart.auditlog.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

/**
 * Request/response DTO: Audit Log Create Request.
 */
public record AuditLogCreateRequest(
    @NotBlank String service,
    String level,
    String method,
    String path,
    String userId,
    String userRole,
    String storeId,
    Integer statusCode,
    Integer duration,
    String message,
    Map<String, Object> meta,
    String ipAddress
) {}
