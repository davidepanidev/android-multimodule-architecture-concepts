# Clean Architecture Compose Concept

[![Kotlin](https://img.shields.io/badge/kotlin-1.7.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![License](https://img.shields.io/github/license/davide-pani/kotlin-extensions?color=orange)]()

This concept consists of structuring the App in 4 separate modules:
* **app**: Android module that contains the Android _Application_ component and all the framework specific configurations. It has visibility over all the other modules and defines the golbal dependency injection configurations.
* **presentation**: Android module that represents the _presentation layer_. It contains the Android UI framework components (_Activities_, _Composables_, _ViewModels_...) and the related resources (e.g. images, strings...).
* **domain**: Kotlin module that represents the _domain layer_. It contains _Use Cases_ (platform-independent business logic), the _Entities_ (platform-independent business models) and the _Repository interfaces_.
* **data**: Android module that represents the _data layer_. It contains _Repositories_ implementation, the data source _Api_ implementations and the corresponding api-specific models. 


The dependencies between the modules and the visibility among them is shown by the picture below (the _app_ module is not shown as it has global visibility over the all project):

<img src="pictures/clean architecture - modules dependencies.png" height="300">


## Data Flow

The data flow is shown by the picture below:

<img src="pictures/clean architecture - data flow.png">

