package com.digicart.auditlog.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

/**
 * JPA entity mapped in this service schema (Audit Log).
 */
@Entity
@Table(name = "audit_log", schema = "audit_log_svc")
@EntityListeners(AuditingEntityListener.class)
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String service;

    @Column(nullable = false)
    private String level = "info";

    private String method;

    private String path;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "status_code")
    private Integer statusCode;

    private Integer duration;

    private String message;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String meta;

    @Column(name = "ip_address")
    private String ipAddress;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    /**
     * Returns id.
     * @return the uuid
     */
    public UUID getId() { return id; }
    /**
     * Returns service.
     * @return the string
     */
    public String getService() { return service; }
    /**
     * Sets service.
     *
     * @param service service
     */
    public void setService(String service) { this.service = service; }
    /**
     * Returns level.
     * @return the string
     */
    public String getLevel() { return level; }
    /**
     * Sets level.
     *
     * @param level level
     */
    public void setLevel(String level) { this.level = level; }
    /**
     * Returns method.
     * @return the string
     */
    public String getMethod() { return method; }
    /**
     * Sets method.
     *
     * @param method method
     */
    public void setMethod(String method) { this.method = method; }
    /**
     * Returns path.
     * @return the string
     */
    public String getPath() { return path; }
    /**
     * Sets path.
     *
     * @param path path
     */
    public void setPath(String path) { this.path = path; }
    /**
     * Returns user id.
     * @return the string
     */
    public String getUserId() { return userId; }
    /**
     * Sets user id.
     *
     * @param userId caller user id from the gateway ({@code X-User-Id})
     */
    public void setUserId(String userId) { this.userId = userId; }
    /**
     * Returns user role.
     * @return the string
     */
    public String getUserRole() { return userRole; }
    /**
     * Sets user role.
     *
     * @param userRole caller role from the gateway ({@code X-User-Role})
     */
    public void setUserRole(String userRole) { this.userRole = userRole; }
    /**
     * Returns store id.
     * @return the string
     */
    public String getStoreId() { return storeId; }
    /**
     * Sets store id.
     *
     * @param storeId store (tenant) identifier
     */
    public void setStoreId(String storeId) { this.storeId = storeId; }
    /**
     * Returns status code.
     * @return the integer
     */
    public Integer getStatusCode() { return statusCode; }
    /**
     * Sets status code.
     *
     * @param statusCode status code
     */
    public void setStatusCode(Integer statusCode) { this.statusCode = statusCode; }
    /**
     * Returns duration.
     * @return the integer
     */
    public Integer getDuration() { return duration; }
    /**
     * Sets duration.
     *
     * @param duration duration
     */
    public void setDuration(Integer duration) { this.duration = duration; }
    /**
     * Returns message.
     * @return the string
     */
    public String getMessage() { return message; }
    /**
     * Sets message.
     *
     * @param message message
     */
    public void setMessage(String message) { this.message = message; }
    /**
     * Returns meta.
     * @return the string
     */
    public String getMeta() { return meta; }
    /**
     * Sets meta.
     *
     * @param meta meta
     */
    public void setMeta(String meta) { this.meta = meta; }
    /**
     * Returns ip address.
     * @return the string
     */
    public String getIpAddress() { return ipAddress; }
    /**
     * Sets ip address.
     *
     * @param ipAddress ip address
     */
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    /**
     * Returns created at.
     * @return the instant
     */
    public Instant getCreatedAt() { return createdAt; }
}
