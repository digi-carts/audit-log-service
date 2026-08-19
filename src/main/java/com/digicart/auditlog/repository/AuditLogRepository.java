package com.digicart.auditlog.repository;

import com.digicart.auditlog.entity.AuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {
    List<AuditLog> findByStoreId(String storeId, Pageable pageable);
    List<AuditLog> findByUserId(String userId, Pageable pageable);
    List<AuditLog> findByService(String service, Pageable pageable);
    List<AuditLog> findByLevel(String level, Pageable pageable);
    List<AuditLog> findByCreatedAtBefore(Instant cutoff);
    long countByStoreId(String storeId);
    long countByService(String service);
}
