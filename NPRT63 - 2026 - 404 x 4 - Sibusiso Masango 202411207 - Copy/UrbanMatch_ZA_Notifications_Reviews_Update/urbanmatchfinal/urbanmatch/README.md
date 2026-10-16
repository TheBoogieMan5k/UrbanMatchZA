# UrbanMatch ZA — development project

## Setup

1. Install JDK 21+ and MySQL 8.
2. Create `urbanmatch_db` with the **existing 10-table schema** from your local MySQL Workbench. This archive does not include a database dump.
3. Set `DB_PASSWORD` in PowerShell (`$env:DB_PASSWORD = ...`) and run `./mvnw.cmd clean test` followed by `./mvnw.cmd spring-boot:run`.
4. Open http://localhost:8080/

## Backend API

Authentication uses **stateless HTTP Basic** with database-backed BCrypt hashes. For protected API requests send `Authorization: Basic <base64(email:password)>` over HTTPS in any deployed environment. `GET /api/auth/login` verifies credentials; it does not issue a session/token. Register via `POST /api/auth/register`. No public admin registration.

- Jobs: `GET /api/jobs/open`, `GET /api/jobs/{id}`, `GET /api/jobs/mine`, `POST /api/jobs`, `PUT /api/jobs/{id}`, `PATCH /api/jobs/{id}/status?value=open|closed`, `DELETE /api/jobs/{id}`.
- Applications: `POST /api/applications`, `GET /api/applications/mine`, `GET /api/applications/job/{jobId}`, `PATCH /api/applications/{id}/status?value=...`.
- Profile: `GET/PATCH /api/profile`; employer profile `GET/PUT /api/employer/profile`.
- Saved jobs: `GET /api/saved-jobs`, `POST/DELETE /api/saved-jobs/{jobId}`.
- Conversations: `GET/POST /api/conversations`, `GET/POST /api/conversations/{id}/messages`, `PATCH /api/conversations/{id}/read`.
- Notifications: `GET /api/notifications`, `PATCH /api/notifications/{id}/read`.
- Reviews: `GET /api/reviews/{userId}`, `POST /api/reviews`.
- Reports: `POST /api/reports`; admin: `GET /api/admin/reports`, `PATCH /api/admin/reports/{id}?status=...`, `GET /api/admin/users`, `PATCH /api/admin/users/{id}/status?status=...`, `GET /api/admin/jobs`, `PATCH /api/admin/jobs/{id}/status?status=...`.

## Important limitations

**This is NOT a fully finished integrated application.** The original 197KB frontend `templates/index.html` has been preserved, including its localStorage-backed demonstration workflows; it is **not yet wired to these REST endpoints**. The Java backend has not been compiled or integration-tested against your local database in this environment. Some table field assumptions require verification against your actual SQL schema. CV binary upload, deployment, end-to-end security and usability tests, and a verified GitHub submission remain outstanding.

**Security:** HTTP Basic credentials must only be sent over HTTPS outside localhost. Passwords must not be saved in localStorage. Do not expose `/api/users/count` publicly. CSRF is disabled only for this stateless HTTP Basic API; do not add cookie-based authentication without restoring CSRF protections.
