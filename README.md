# Java Selenium Framework Demo

A JUnit 5 + Selenium 4 demo framework targeting LocalAutomationApp, organized by route with deep coverage per page.

## Industry-Standard Design
- **Page Object Model (POM):** `/pages` encapsulates locators and actions for each route.
- **Core utilities:** `/core` provides driver factory, thread-safe driver manager, waits, and config.
- **Test structure:** `/tests` maps 1:1 to routes for clarity and ownership.
- **Stable selectors:** `data-testid`-based locators for resilient automation.
- **Cross-browser ready:** browser and headless configuration via system properties/env vars.
- **Observability:** screenshots on failure + optional Jira issue creation.

## Prereqs
- Java 17+
- Maven
- LocalAutomationApp running on http://localhost:5173

## LocalAutomationApp Setup
This repo expects LocalAutomationApp at `../../LocalAutomationApp` (see `.env`).

```bash
cd /Users/tomhuang/prog
git clone <LocalAutomationApp repo URL> LocalAutomationApp
cd LocalAutomationApp
docker compose --profile stable up -d --build
```

Confirm the app is up:
- Frontend: `http://localhost:5173`
- API: `http://localhost:3001/health`

## Run Locally
```bash
mvn -q test -Dbase.url=http://localhost:5173 -Dbrowser=chrome -Dheadless=false
```

### Env/System Properties
- `base.url` / `BASE_URL`
- `browser` / `BROWSER` (chrome|firefox|edge)
- `headless` / `HEADLESS` (true|false)
- `remote.url` / `REMOTE_URL` (optional Selenium Grid URL)

## CI (GitHub Actions)
Workflow: `.github/workflows/java-selenium-ci.yml`

Requirements:
- Set repository secrets:
  - `LOCAL_AUTOMATION_APP_REPO` (git URL)
  - `LOCAL_AUTOMATION_APP_REF` (optional branch/tag/commit)

The workflow:
1) Clones LocalAutomationApp
2) Starts it with Docker Compose
3) Runs Selenium tests
4) Tears down containers

## CI (Local Jenkins)
This repo ships a local Jenkins setup via Docker Compose.

Defaults are in `.env` for first-time users. Use `.env.example` as a starting point if you want to customize.

1) Start Jenkins + Selenium:
```bash
docker compose -f docker-compose.jenkins.yml up -d --build
```

To also start LocalAutomationApp from this repo, use the `app` profile:
```bash
docker compose -f docker-compose.jenkins.yml --profile app up -d --build
```

2) Unlock Jenkins:
```bash
docker exec java-selenium-jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

3) Create a Pipeline job:
- Pipeline from SCM, repository path = this repo
- Script path: `Jenkinsfile`
- For local Jenkins in Docker, use repo URL `file:///opt/java-repo` (mounted by `docker-compose.jenkins.yml`).

First run checklist:
- Install suggested plugins (Git, Pipeline, JUnit, Docker).
- Create the Pipeline job.
- Start LocalAutomationApp (host or `--profile app`).
- Run the job with `USE_APP_PROFILE=true` if using the app profile.

Notes:
- Jenkins UI runs on `http://localhost:9081`.
- When using the `app` profile, set `BASE_URL` to `http://local-frontend:5173`.
- If you're running LocalAutomationApp on the host, keep `BASE_URL` as `http://host.docker.internal:5173`.
- In Jenkins, you can toggle `USE_APP_PROFILE` to auto-set `BASE_URL` to `http://local-frontend:5173`.
- Local SCM uses a mounted repo at `file:///opt/java-repo`, with local checkout enabled in `docker-compose.jenkins.yml`.

## Troubleshooting
- `host.docker.internal` not resolving: set `BASE_URL` to your host IP (e.g. `http://192.168.1.10:5173`) or use the `app` profile.
- Wrong LocalAutomationApp path: update `LOCAL_AUTOMATION_APP_DIR` in `.env`.
- Port conflicts: change `JENKINS_HTTP_PORT` or `SELENIUM_PORT` in `.env`.

## Jira Integration (Optional, Local Docker)
This demo includes a lightweight Jira failure hook. To run Jira locally:

1) Start Jira + Postgres:
```bash
docker compose -f docker-compose.jira.yml up -d
```

Useful commands:
```bash
docker compose -f docker-compose.jira.yml logs -f
docker compose -f docker-compose.jira.yml ps
docker compose -f docker-compose.jira.yml stop
docker compose -f docker-compose.jira.yml down
```

2) Complete Jira setup in browser:
- Visit `http://localhost:8080`
- Choose **I'll set it up myself**
- Database: use the built-in database if you just need a local demo (it can take a few minutes)
- Application Title: `Local Jira (CI)`
- Mode: **Private**
- Base URL: `http://localhost:8080`
- License: generate a trial key from MyAtlassian and paste it in
- Create the admin user (example: `admin` / `admin`, `Local Admin`, `admin@local.test`)
- Create a project with key `DEMO` (or your preferred key)
- If the UI hangs, check logs with `docker compose -f docker-compose.jira.yml logs -f jira`

3) Export Jira environment variables before running tests:
```bash
export JIRA_BASE_URL=http://localhost:8080
export JIRA_USER=admin
export JIRA_TOKEN=admin
export JIRA_PROJECT_KEY=DEMO
export JIRA_ISSUE_TYPE=Bug
```

4) Run tests (will file Jira issues on failures):
```bash
mvn -q test -Dbase.url=http://localhost:5173 -Dbrowser=chrome -Dheadless=false
```

Note: The Jira hook is **demo-only**. In production, failures should be deduplicated and sanitized.

## Layout
- `src/test/java/com/demo/core`: driver management, waits, base test, Jira helper
- `src/test/java/com/demo/pages`: page objects with stable locators
- `src/test/java/com/demo/tests`: UI tests by route
- `src/test/resources/fixtures`: test data files
