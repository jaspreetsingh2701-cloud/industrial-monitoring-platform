# Day 3 — Microservices and Communication

**Outcome:** Understand service boundaries and demonstrate backend-to-backend communication.

| Block | Topics — one-line guide |
|---|---|
| Hour 1 | Monolith vs microservices, bounded responsibilities, independent deployment and data ownership. |
| Hour 2 | REST service-to-service calls, JSON contracts, timeouts, error handling and idempotency. |
| Hour 3 | Protocol Buffers, `.proto` files, gRPC methods, request/response messages and stubs. |
| Hour 4 | SSE, event streams, one-way server push and when to choose it over WebSocket. |
| Hour 5 | Build Telemetry Service and connect a backend service through the chosen contract. |
| Hour 6 | Test the service flow, inspect logs, debug failures and explain protocol trade-offs. |

## Deliverable
Two independently runnable backend services, with a working service-to-service communication example.

## Completion checklist
- [ ] Explain why the project has two services.
- [ ] Explain service-owned data.
- [ ] Compare REST and gRPC.
- [ ] Describe SSE and its one-way nature.
- [ ] Demonstrate a successful backend-to-backend call.
