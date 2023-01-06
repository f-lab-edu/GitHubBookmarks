# GitHubBookmarks

## Tech stack & Open-source libraries
### Android
- Minimum SDK level 22
- Kotlin based, Coroutines for asynchronous.
    - JetPack
    - Lifecycle - Create a UI that automatically responds to lifecycle events.
    - LiveData - Build data objects that notify views when the underlying database changes.
    - ViewModel - Store UI related data that isn't destroyed on app rotations.
    - Room - Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
    - DataBinding - Useful to bind data directly through layouts xml file, so no findViewById() anymore.
- Hilt - Dependency injection.
- Retrofit2 & OkHttp3 - Construct the REST APIs.