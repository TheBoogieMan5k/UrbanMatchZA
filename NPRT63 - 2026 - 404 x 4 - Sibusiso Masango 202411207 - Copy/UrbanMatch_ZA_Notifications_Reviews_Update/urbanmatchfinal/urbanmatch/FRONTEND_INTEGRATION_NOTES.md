# UrbanMatch frontend API integration

This build replaces browser localStorage demo data with REST-backed fetch requests and retains the original HTML styling. Refreshing the page logs the user out because the existing backend uses HTTP Basic authentication and credentials are kept **only in memory** (not localStorage). Registration requires a password of at least 8 characters. Public job listings come from MySQL, not seeded demo records.

## Implemented bridge paths
- Register, login (HTTP Basic), logout; browse public jobs and public directory
- Employer job create/edit/close/reopen/delete
- Seeker application submit; employer application status updates
- Saved jobs; profile name/phone/location; employer company profile
- Conversations and messages; notifications read; reports, reviews, admin moderation

## Known limitations and follow-up work
- The existing API does not provide a CV upload endpoint. The frontend blocks attachments rather than pretending to upload them.
- Job form fields such as positions and deadline, and seeker bio/skills/photo are not stored by the current backend schema. They need migrations and new endpoints.
- Browser-created notifications are disabled: add notifications in Spring services, inside transactions.
- The frontend does not have a withdrawal endpoint. Withdrawal is disabled.
- HTTP Basic does not provide persistent login; use a secure session or token flow later.
- A user directory endpoint exposes only active non-admin names, IDs, roles, locations. Add pagination/privacy controls before production.
- This code has been statically syntax-checked, but must be functionally tested with your own MySQL schema and Spring Boot instance.
- Security: HTTP Basic credentials are retained in JS memory only. Use HTTPS in any deployment, not plain HTTP over a network.
