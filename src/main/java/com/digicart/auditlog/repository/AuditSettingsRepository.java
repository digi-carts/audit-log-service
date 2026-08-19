package com.digicart.auditlog.repository;

import com.digicart.auditlog.entity.AuditSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditSettingsRepository extends JpaRepository<AuditSettings, Integer> {
}
