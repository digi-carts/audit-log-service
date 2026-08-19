package com.digicart.auditlog.service;

import com.digicart.auditlog.dto.AuditSettingsUpdateRequest;
import com.digicart.auditlog.entity.AuditSettings;
import com.digicart.auditlog.repository.AuditSettingsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditSettingsService {

    private final AuditSettingsRepository auditSettingsRepository;

    public AuditSettingsService(AuditSettingsRepository auditSettingsRepository) {
        this.auditSettingsRepository = auditSettingsRepository;
    }

    public AuditSettings getSettings() {
        return auditSettingsRepository.findAll().stream().findFirst()
            .orElseGet(() -> {
                AuditSettings defaults = new AuditSettings();
                return auditSettingsRepository.save(defaults);
            });
    }

    @Transactional
    public AuditSettings update(AuditSettingsUpdateRequest req) {
        AuditSettings settings = getSettings();
        if (req.retentionDays() != null) settings.setRetentionDays(req.retentionDays());
        return auditSettingsRepository.save(settings);
    }
}
