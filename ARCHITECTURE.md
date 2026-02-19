# Android App Architecture Rules

## Purpose

This document defines the **mandatory architecture rules** for this Android application. **Any change to the codebase MUST comply with these rules**.

Before making any modification, you MUST:

1. Read this document completely
2. Validate that the change respects all rules
3. Reject or refactor any solution that violates them

This is a **short‑lived project** that must be implemented **quickly**, but **never at the cost of bad practices**.

---

## Project Scope

* Platform: **Android only**
* Language: **Kotlin**
* Project state: **New application**
* Timeline: **Few hours of development**
* Goal: **Fast MVP with good practices**

Over‑engineering is explicitly forbidden.

---

## Architectural Style

### Mandatory Architecture

* **MVVM + Clean Architecture**

### Project Structure (Single Module)

> The project MUST NOT be modularized.

Recommended package structure:

```
com.example.app
│
├── presentation
│   ├── ui
│   ├── viewmodel
│   └── state
│
├── domain
│   ├── model
│   ├── usecase
│   └── repository
│
├── data
│   ├── remote
│   ├── repository
│   └── mapper
│
└── core
    ├── network
    ├── error
    └── util
```

---

## Layer Responsibilities

### Presentation Layer

* Uses **Jetpack Compose ONLY** (no XML)
* Contains:

    * Composables
    * ViewModels
    * UI state models

Rules:

* NO business logic
* NO Retrofit / network models
* NO data layer dependencies
* Observes state via `StateFlow`
* Displays loading, success, and error states

---

### Domain Layer

* Pure Kotlin (NO Android dependencies)
* Contains:

    * UseCases
    * Domain models
    * Repository interfaces

Rules:

* Each UseCase does **one thing only**
* No framework or implementation details
* No Retrofit / DTOs / Compose / Android imports

---

### Data Layer

* Implements repository interfaces from domain
* Responsible for:

    * API calls
    * Mapping DTOs → domain models

Rules:

* Retrofit is mandatory for networking
* No UI or Compose dependencies
* Errors MUST be mapped to domain‑level error models

---

## Networking Rules

### Retrofit Usage

* Retrofit MUST be used for all internet calls

### Internet Availability Check

Before **any** network request:

1. Verify internet connectivity
2. If no internet → return a controlled error

### Error Handling (Mandatory)

Every network call MUST:

* Handle:

    * No internet
    * Timeout
    * HTTP errors
    * Unknown exceptions
* Return a predictable result to the domain layer

Recommended pattern:

* `sealed class Result<Success, Error>`

---

## UI Feedback Rules

The UI MUST always be able to represent:

* Loading state
* Success state
* Error state

Errors MUST be:

* Explicit
* User‑representable
* Not raw exceptions

---

## ViewModel Rules

* ViewModels coordinate UseCases
* They DO NOT contain business logic
* They expose immutable UI state
* State is exposed using `StateFlow`

---

## Dependency Management

* Keep dependency injection simple
* Avoid complex abstractions
* Avoid premature patterns
* No reflection‑heavy or advanced DI setups unless strictly necessary

---

## Over‑Engineering Policy

Explicitly forbidden:

* Extra abstraction layers without real use
* Multiple architectural patterns for the same purpose
* Unnecessary generics
* Complex frameworks that slow down development

Guiding principle:

> "As simple as possible, but no simpler"

---

## Enforcement Rules for AI

When modifying the project:

* You MUST follow this document
* You MUST refuse changes that violate it
* You MUST explain why a rule is being violated
* You MUST prefer clarity over cleverness

Failure to comply with this document is considered an incorrect solution.
