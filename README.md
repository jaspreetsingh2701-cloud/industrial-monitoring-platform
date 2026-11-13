# Industrial Monitoring Platform — Learning Sprint

This repository is both a hands-on full-stack project and a structured five-day learning sprint.

## Goal
Build a working industrial monitoring prototype while learning Java, Spring Boot, microservices, service communication, real-time data delivery, and frontend integration.

**Time budget:** 5 days × 6 hours/day = 30 focused hours.

**Important:** The five-day target is a working prototype and interview-ready fundamentals. It is not intended to represent production-grade mastery of Java or distributed systems.

## Documentation map

| File | Purpose |
|---|---|
| `docs/00-five-day-plan.md` | Day-by-day schedule with six-hour time blocks |
| `docs/01-project-overview.md` | What we are building, features, scope and outcomes |
| `docs/02-architecture.md` | Services, responsibilities, communication and data flow |
| `docs/day-1.md` … `docs/day-5.md` | One-line topic guides and daily deliverables |
| `docs/03-java-vs-javascript.md` | Java concepts mapped to JavaScript equivalents |
| `docs/04-vscode-java-setup.md` | Windows + VS Code + Java setup |
| `PROGRESS.md` | Checklist and learning log |

## Suggested repository structure

```text
industrial-monitoring-platform/
├── apps/
│   └── dashboard/                 # React + TypeScript + Vite
├── services/
│   ├── device-service/            # Java + Spring Boot
│   └── telemetry-service/         # Java + Spring Boot
├── contracts/
│   └── telemetry.proto            # gRPC contract
├── infrastructure/
│   └── docker-compose.yml
├── docs/
│   └── ...                        # These learning/project documents
├── PROGRESS.md
└── README.md
```

## Working agreement

- Follow one day at a time; do not jump ahead just to create more code.
- At the start of each day, review its guide and prerequisites.
- Implement a small working increment, run it, and understand it before moving on.
- Record commands, decisions, errors and fixes in the relevant docs or `PROGRESS.md`.
- Keep secrets, local environment files and build outputs out of Git.
