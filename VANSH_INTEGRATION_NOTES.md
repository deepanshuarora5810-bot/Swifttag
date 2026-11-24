# Vansh integration notes

This branch-ready source combines the existing SwiftTag repository with selected reusable classes from FASTagTollSystem. Existing DAO, service, model, and UI files were preserved.

## Added packages/classes
- `collections`: FastagRegistry, VehicleRegistry, TransactionSorter
- `threading`: SharedWallet, TollLaneWorker
- `exception`: InsufficientBalanceException, BlacklistedVehicleException
- `util`: InputValidator, ReceiptGenerator, DBConnection

The original standalone UI and its Main class were not copied because they form a separate console flow and one original call (`VehicleRegistry.addVehicle(String, String)`) does not match the registry API. The team application's existing UI and service architecture remain in place.

## Important design notes
- `FastagRegistry` and `SharedWallet` are in-memory demonstration stores. They are separate from the repository's DAO-backed `Wallet` and `Fastag` data. Do not use both stores as production sources of truth without a deliberate migration.
- `TransactionSorter` uses `String[]` records, while the main application uses `TollTransaction`; it is retained as a standalone Java Collections demonstration and is not wired into the DAO/service flow.
- `DBConnection` now provides the API required by the existing DAOs. Configure `DB_URL`, `DB_USER`, and `DB_PASSWORD` (or `-Ddb.url`, `-Ddb.user`, `-Ddb.password`) and add the correct JDBC driver before running database-backed features. No database connection is assumed or tested by compilation.
- The original FASTag standalone UI/tests were not copied into the team's `ui` package to avoid replacing or conflicting with the team's menus.

## Compile
From the repository root (JDK 17+):

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src/main/java -name "*.java")
```

On Windows CMD, use a source list or compile through your IDE.
