package com.digicart.auditlog.service;

import com.digicart.auditlog.dto.AuditSettingsUpdateRequest;
import com.digicart.auditlog.entity.AuditSettings;
import com.digicart.auditlog.repository.AuditSettingsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application service implementing audit settings use cases for <em>audit-log-service</em>.
 */
@Service
public class AuditSettingsService {

    private final AuditSettingsRepository auditSettingsRepository;

    /**
     * Creates a new {@code AuditSettingsService}.
     *
     * @param auditSettingsRepository audit settings repository collaborator
     */
    public AuditSettingsService(AuditSettingsRepository auditSettingsRepository) {
        this.auditSettingsRepository = auditSettingsRepository;
    }

    /**
     * Returns settings.
     * @return the audit settings
     */
    public AuditSettings getSettings() {
        return auditSettingsRepository.findAll().stream().findFirst()
            .orElseGet(() -> {
                AuditSettings defaults = new AuditSettings();
                return auditSettingsRepository.save(defaults);
            });
    }

    /**
     * Updates an existing record.
     *
     * @param req request payload
     * @return the audit settings
     */
    @Transactional
    public AuditSettings update(AuditSettingsUpdateRequest req) {
        AuditSettings settings = getSettings();
        if (req.retentionDays() != null) settings.setRetentionDays(req.retentionDays());
        return auditSettingsRepository.save(settings);
    }
}
