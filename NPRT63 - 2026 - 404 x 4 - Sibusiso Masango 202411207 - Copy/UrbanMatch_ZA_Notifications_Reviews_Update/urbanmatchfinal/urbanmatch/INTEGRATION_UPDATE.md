# UrbanMatch integration update

Changes: notifications are generated server-side for applications, status changes, messages, withdrawals and reviews. Reviews are loaded from MySQL for display. Seeker withdrawal endpoint is wired to the existing button. Notification payload mapping uses actual database column names.

Limitations: CV file uploads and secure persistent login have not been implemented. Existing HTTP Basic credentials remain in memory until the tab reloads. Automated end-to-end tests against your local MySQL database have not been run here. Review eligibility still requires an accepted application. Test notification table column names and allowed application status values against your local schema before deployment.

Test: register employer and seeker; post job; apply; inspect notification bell; accept application; submit review; inspect employer review display; send message; inspect bell; withdraw a separate pending application. Run `./mvnw.cmd clean test` and `./mvnw.cmd spring-boot:run` with DB_PASSWORD set.
