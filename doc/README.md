# audit-log-service

Central audit trail and retention settings. Port **3012**, schema **`audit_log_svc`**.

Platform design: [System design](https://github.com/digi-carts/doc/blob/main/architecture/system-design.md)

## Domain

`AuditLog` stores `service`, `level` (default `info`), HTTP `method`/`path`, `user_id`/`user_role`/`store_id`, `status_code`, `duration`, `message`, JSON `meta`, and `ip_address`.

`AuditSettings` is a singleton-style config. `POST /audit-logs/purge` deletes logs older than 30 days (intended for admin/superadmin).

Gateway prefix: `/api/audit/**`. Controllers: `/audit-logs`, `/audit-settings`.

Other services do not currently emit logs automatically; clients or a future gateway filter must `POST` entries.

## Tech stack

Java 21, Spring Boot 3.3.0, Web, JPA, Validation, Liquibase, PostgreSQL.

## HTTP API

### Logs — `/audit-logs`

| Method | Path | Notes |
|--------|------|--------|
| GET | `/audit-logs` | Query: `service`, `level`, `storeId`, `userId`, `page` (1), `limit` (50) |
| GET | `/audit-logs/{id}` | 404 if missing |
| POST | `/audit-logs` | `AuditLogCreateRequest` → 201 |
| DELETE | `/audit-logs/{id}` | 204 |
| POST | `/audit-logs/purge` | Returns `{ deleted }` |

### Settings — `/audit-settings`

| Method | Path |
|--------|------|
| GET | `/audit-settings` |
| PUT | `/audit-settings` |

### Health

`GET /health`

## Configuration

| Variable | Required | Default |
|----------|----------|---------|
| `DATABASE_URL` | yes | schema `audit_log_svc` |
| `PORT` | no | `3012` |

## Local run

```bash
export DATABASE_URL="jdbc:postgresql://localhost:5432/digicarts?currentSchema=audit_log_svc"
mvn spring-boot:run
```

## CI/CD

`digi-cart-audit-log-service-dev` / `digi-cart-audit-log-service`.

## Related

- [api-gateway](https://github.com/digi-carts/api-gateway/blob/stage/doc/README.md)
- [platform-ui](https://github.com/digi-carts/platform-ui/blob/stage/doc/README.md)
