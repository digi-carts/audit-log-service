package com.digicart.auditlog.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * JPA entity mapped in this service schema (Audit Settings).
 */
@Entity
@Table(name = "audit_settings", schema = "audit_log_svc")
@EntityListeners(AuditingEntityListener.class)
public class AuditSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "retention_days", nullable = false)
    private Integer retentionDays = 30;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    /**
     * Returns id.
     * @return the integer
     */
    public Integer getId() { return id; }
    /**
     * Returns retention days.
     * @return the integer
     */
    public Integer getRetentionDays() { return retentionDays; }
    /**
     * Sets retention days.
     *
     * @param retentionDays retention days
     */
    public void setRetentionDays(Integer retentionDays) { this.retentionDays = retentionDays; }
    /**
     * Returns updated at.
     * @return the instant
     */
    public Instant getUpdatedAt() { return updatedAt; }
}
