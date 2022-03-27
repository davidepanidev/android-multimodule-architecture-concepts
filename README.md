# Layered Architecture Concepts

This architecture concept is composed by 3 modules:
* **app**: contains the _presentation layer_. It contains the Android UI framework components (_Activities_, _Fragments_, _ViewModels_...), the Android _Application_ component and all the framework specific configurations.
* **domain**: represents the _domain layer_. It contains _Use Cases_ (platform-independent business logic) and _Entities_ (platform-independent business models). Ideally this module would be a pure Kotlin library because it has to be platform-independent. However in this concept it has to be an Android module because it depends on the _data_ module which is an Android module itself.
* **data**: represents the _data layer_. It contains _Repositories_ and their corresponding interfaces, the data source _Api_ implementations and the corresponding api-specific models. It also contains the _Hilt_ modules providing the _depenency injection_ dependencies required by the classes in this module. 
