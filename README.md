# GitHubBookmarks

## Tech stack & Open-source libraries
### Android
- Minimum SDK level 22
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Create a UI that automatically responds to lifecycle events.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Build data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store UI related data that isn't destroyed on app rotations.
  - [Room](https://developer.android.com/training/data-storage/room) - Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - Useful to bind data directly through layouts xml file, so no `findViewById()` anymore.
- [Hilt](https://dagger.dev/hilt/) - Dependency injection.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.

## Architecture
GitHubBookmarks is based on the MVVM architecture and the Repository pattern.
<p align = 'center'>
<img width = '600' src = 'https://github.com/hy0417sage/ComputerScience-study-recode/blob/main/0.%20%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C/IMG:MVVM.png'>
</p>