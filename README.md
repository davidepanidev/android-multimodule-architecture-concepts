# Layered Architecture Concept

This concept consists of structuring the App in 4 separate modules:
* **app**: Android module that contains the Android _Application_ component and all the framework specific configurations. It has visibility over all the other modules and defines the golbal dependency injection configurations.
* **presentation**: Android module that represents the _presentation layer_. It contains the Android UI framework components (_Activities_, _Fragments_, _ViewModels_...) and the related resources (e.g. images, strings...).
* **domain**: Android module that represents the _domain layer_. It contains _Use Cases_ (platform-independent business logic) and _Entities_ (platform-independent business models). Ideally this module would be a pure Kotlin library because it has to be platform-independent. However in this concept it has to be an Android module because it depends on the _data_ module which is an Android module itself.
* **data**: Android module that represents the _data layer_. It contains _Repositories_ and their corresponding interfaces, the data source _Api_ implementations and the corresponding api-specific models. 


The dependencies between the modules and the visibility among them is shown by the picture below (the _app_ module is not shown as it has global visibility over the all project):

<img src="pictures/layered architecture - modules dependencies.png" height="350">


## Data Flow

The data flow is shown by the picture below:

<img src="pictures/layered architecture - data flow.png">
