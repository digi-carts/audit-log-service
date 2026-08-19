package com.digicart.auditlog.repository;

import com.digicart.auditlog.entity.AuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for audit log  persistence.
 */
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {
    /**
     * Finds by store id.
     *
     * @param storeId store (tenant) identifier
     * @param pageable pageable
     * @return matching records
     */
    List<AuditLog> findByStoreId(String storeId, Pageable pageable);
    /**
     * Finds by user id.
     *
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param pageable pageable
     * @return matching records
     */
    List<AuditLog> findByUserId(String userId, Pageable pageable);
    /**
     * Finds by service.
     *
     * @param service service
     * @param pageable pageable
     * @return matching records
     */
    List<AuditLog> findByService(String service, Pageable pageable);
    /**
     * Finds by level.
     *
     * @param level level
     * @param pageable pageable
     * @return matching records
     */
    List<AuditLog> findByLevel(String level, Pageable pageable);
    /**
     * Finds by created at before.
     *
     * @param cutoff cutoff
     * @return matching records
     */
    List<AuditLog> findByCreatedAtBefore(Instant cutoff);
    /**
     * Count by store id.
     *
     * @param storeId store (tenant) identifier
     * @return the long
     */
    long countByStoreId(String storeId);
    /**
     * Count by service.
     *
     * @param service service
     * @return the long
     */
    long countByService(String service);
}
