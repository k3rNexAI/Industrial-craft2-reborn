# EU model — initial design

## Core invariants

- Energy is an integer count of EU; negative storage is forbidden.
- Storage is bounded by `0 <= energy <= capacity`.
- Every storage exposes independent receive and extract limits.
- Simulated operations must not mutate state.
- Cable edges impose a transfer limit, loss percentage and voltage tier.
- A future network manager must avoid double-counting a cable and must tick on the logical server only.

## Planned voltage tiers

| Tier | Name | Nominal packet size |
|---|---|---:|
| 1 | Low Voltage (LV) | 32 EU |
| 2 | Medium Voltage (MV) | 128 EU |
| 3 | High Voltage (HV) | 512 EU |
| 4 | Extreme Voltage (EV) | 2048 EU |

These are initial project balance values, not a claim of exact parity with any particular IC2 release.

## Next engineering task

Implement `EnergyNode` capabilities on machine block entities, then a server-side `EnergyNetwork` graph with deterministic traversal, per-edge throughput, voltage checks, and persisted machine buffers. Add tests for capacity bounds, simulation, losses, and loops before connecting the network to block entities.
