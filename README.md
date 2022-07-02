# Android Multimodule Architecture Concepts

_Android Multimodule Architecture Concepts_ is a project to showcase different architectural approaches to developing multi-modular Android apps.

The architecture proposed consists of 3 different layers:
* **Presentation layer**: contains all the Android UI framework components (e.g. Activities, Fragments, ViewModels...) and the related resources (e.g. images, strings...).
* **Domain layer**: contains the platform-independent business logic and models.
* **Data layer**: contains the repositories, the data sources api implementations and the corresponding api-specific models.

These layers are implemented using 3 separate modules to increase decoupling and separation of concerns. The dependencies between the modules are set so that the _data layer_ is completely invisible to the _presentation layer_ and vice versa.

## Architecture concepts 

The project hosts each architecture concept in a separate branch (this _main_ branch only contains an empty template). 

The proposed concepts are:
* [**Layered Architecture Concept**](https://github.com/davide-pani/android-multimodule-architecture-concepts/tree/layered-architecture-concept): the dependency between the modules is <code>_presentation_ -> _domain_ -> _data_</code>. The 3 modules are Android modules. The visibility among the modules is shown by the picture below:
<img src="https://github.com/davide-pani/android-multimodule-architecture-concepts/blob/layered-architecture-concept/pictures/layered%20architecture%20-%20modules%20dependencies.png" height="300">

* [**Clean Architecture Concept**](https://github.com/davide-pani/android-multimodule-architecture-concepts/tree/clean-architecture-concept): the dependency between the modules is <code>_presentation_ -> _domain_ <- _data_</code>. The _presentation_ and _data_ modules are Android modules whereas the _domain_ module is a Kotlin library being the innermost platform-independent module. The visibility among the modules is shown by the picture below:
<img src="https://github.com/davide-pani/android-multimodule-architecture-concepts/blob/clean-architecture-concept/pictures/clean%20architecture%20-%20modules%20dependencies.png" height="300">

* [**Clean Architecture Compose Concept**](https://github.com/davide-pani/android-multimodule-architecture-concepts/tree/clean-architecture-compose-concept): fork of the [**Clean Architecture Concept**](https://github.com/davide-pani/android-multimodule-architecture-concepts/tree/clean-architecture-concept) with the UI built with Jetpack Compose.
  
  
## Screenshots
  
The App retrieves data from the [CoinGecko API](https://www.coingecko.com/en/api) and shows the current most capitalized cryptocurrency (which of course is Bitcoin 😄).
  
<img src="screenshots/home.png" height="500">
