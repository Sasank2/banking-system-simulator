# Banking System — Architecture Upgrade Roadmap

## Phase 1 — Introduce Interfaces

- [ ] Create `BankingOperations` interface
- [ ] Move method signatures (openAccount, deposit, withdraw, transfer) to interface
- [ ] Make `BankingService` implement `BankingOperations`
- [ ] Refactor `ConsoleApp` to depend on interface instead of concrete class

---

## Phase 2 — Improve Customer Identity Management

- [ ] Add `Map<String, Customer>` to manage customers
- [ ] Prevent duplicate `customerId`
- [ ] Reuse existing customer when opening multiple accounts
- [ ] Validate unique customerId logic

---

## Phase 3 — Improve Collections Usage

- [ ] Replace `ArrayList` references with `List`
- [ ] Return `Collections.unmodifiableList()` from Account
- [ ] Prevent external modification of transactions list
- [ ] Add transaction sorting using Comparator
- [ ] Add sorting by timestamp (ascending/descending)

---

## Phase 4 — Introduce Repository Layer

- [ ] Create `AccountRepository` interface
- [ ] Create `InMemoryAccountRepository` implementation
- [ ] Move account Map logic into repository
- [ ] Inject repository into ConsoleApp

---

## Phase 5 — Refactor Transaction Filtering

- [ ] Move filtering logic out of ConsoleApp
- [ ] Create `TransactionService`
- [ ] Let ConsoleApp only handle input/output

---

## Phase 6 — Code Quality Improvements

- [ ] Fix minor spelling and message consistency
- [ ] Improve main menu formatting
- [ ] Improve error message clarity
- [ ] Final system test (all edge cases)

---

## Phase 7 — Optional Advanced Enhancements

- [ ] Extract `AccountNumberGenerator` class
- [ ] Add transaction search by ID
- [ ] Add account closing feature
- [ ] Add file persistence (save/load accounts)