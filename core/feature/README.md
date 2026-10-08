# Features

This directory is made for library modules that host features.

**IMPORTANT:**

## Each feature has at least 2 modules

We have a specific, and simple way of separating the code into modules.

We **never** take any shortcut, because months of debugging refactoring can save days of planning. (see?)

### TL;DR:

1. Abstract every feature into the "api" module.
2. If the implementation of a feature depends on another feature, depend only on other "api" modules, never the "impl" module.
3. Only app modules can declare "impl" dependencies, and inject the instances of the abstractions.

This is how it looks like in the filesystem:

```
- feature
  - nice-stuff
    - api // clean models & abstractions
    - impl // can depend on the **api** of other features (not their impl)
    - ui // can depend on **api**, but not on impl
    - whatever // can depend on **api**, but not on impl
```

### Why?

For these 2 reasons:

#### Simplicity of the exposed APIs

We avoid exposing unneeded details, keeping internal things… internal.

#### Compilation speed

We organize module dependencies so that the compilation bottlenecks are only with very small amounts of code.
Modules with significant amounts of code to compile must be able to compile in parallel.

_This might sound an ideal, but with the correct abstraction, it is always a possible thing, with only a few exceptions that can't be called app "features", and are therefore not relevant here._

### The API module

Each feature has a module named "api".

#### Content

Here's what it may contain:

1. Models
2. Abstractions of the feature
3. Dependency on other api modules

Here are some details:

##### 1. Models

These can be `data class`, `value class`, `enum class`, `sealed interface`, `sealed class`, and `class`.

They must have only `val` properties. **No `var` allowed.**

They must **never reference mutable types** (`MutableList`).

**Observable** types like `Flow`, `SharedFlow`, or similar are **allowed**. Functional types are also allowed.

Dependencies on database/storage or serialization types **must be avoided**. That includes annotations (e.g. Room and Kotlin serialization annotations like `@Entity` and `@Serializable`).

We avoid using `interface` for models, because it can actually lead to hidden tight coupling when a model is forcibly made to conform to conflicting constraints. An abstraction over a remote API model and a local database model can hide needed details and prove hard to evolve as the remote API and the local database have 2 different evolution timelines.

The models used in API models represent only what the consuming code needs, and conversion to remote API models and to database entities is done in the implementation module that the end app will depend on and inject.

##### 2. Abstractions

These can be `interface` and `abstract class`.

Most of the time, they will contain `abstract` functions and properties (`fun` & `val`).
Functions may be `suspend fun`, or not if the code is not performing I/O (directly or indirectly).

In some cases, the abstractions might have `var` if the use case justifies it.

Short non-abstract functions and properties are allowed for convenience, as long as the amount of code is **not** significant and is not the implementation of the feature, semantically speaking.

They use the models and can also reference other APIs.
Observable types like `Flow` and `SharedFlow` can also be referenced. Functional types are also allowed.

##### 3. Dependencies

Obviously, "api" modules must never declare dependencies on "impl" modules, even for convenience. That would defeat the purpose of compilation parallelization benefits, preventing builds from leveraging multicore CPUs.

Depending on other "api" modules as needed is perfectly fine, however (e.g. to reference their models).

#### Disallowed content

No dependency on an "engine", on a network client, on a Play Services API, even on an Android or Apple API (some rare exceptions might arise, to be discussed with the team).

### Implementation modules

They can depend on "api", but not on other "impl" modules.
They can depend on "engines", network clients, Play Services APIs, Android, Apple or other system APIs, third party libraries that are used to implement the feature…

As stated before, the reason we don't directly depend on our own "impl" modules between each other is to enable parallelized compilation of the implementation modules. As soon as the "api" modules are done, the compilation of an "impl" can start, independent of other "impl" modules.

Note that these modules may have a name that is not "impl".
Here are a few valid examples:
- `lib`
- `impl-aosp`
- `impl-play-services`
- `impl-library-x`

### UI modules

Just like implementation modules, they should never depend on the "impl" modules directly, only "api" ones.

That means the "api" modules need to provide all the details that the UI will need.
