# Android Multimodule Architecture Concepts

_Android Multimodule Architecture Concepts_ is a project to showcase different architectural approaches to developing multi-modular Android apps.

The multi-module architecture proposed consists of 3 different layers:
* **Presentation layer**: contains all the Android UI framework components (i.e. Activities, Fragments, ViewModels...) and the related resources (i.e. images, strings...).
* **Domain layer**: contains the platform-independent business logic and models.
* **Data layer**: contains the repositories, the data sources api implementations and the corresponding api-specific models.

These layers are implemented using 3 separate modules to increase decoupling and separation of concerns. The dependencies between the modules are set in order to make the _data layer_ completely invisible to the _presentation layer_ and vice versa.

## Architecture concepts 

This project hosts each concept of this architecture in a separate branch (the _main_ branch only contains an empty template for all the concepts). 

The proposed concepts are:
* [**layered-architecture-concept**](https://github.com/davide-pani/android-multimodule-architecture-concepts/tree/layered-architecture-concept): the dependency between the modules is _presentation_ -> _domain_ -> _data_. The 3 modules are Android modules.
* [**clean-architecture-concept**](https://github.com/davide-pani/android-multimodule-architecture-concepts/tree/clean-architecture-concept): the dependency between the modules is _presentation_ -> _domain_ <- _data_. The _presentation_ and _data_ modules are Android modules whereas the _domain_ module is a pure Kotlin library being the innermost platform-independent module.
