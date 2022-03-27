# Android Multimodule Architecture Concepts
Android Multimodule Architecture Concepts is a project to showcase different architectural approaches to developing multi-modular Android apps.

The multi-module architecture proposed consists of 3 different layers:
* **Presentation layer**: contains all the Android UI framework components (i.e. Activities, Fragments, ViewModels...) and the related resources (i.e. images, strings...).
* **Domain layer**: contains the platform-independent business logic and models.
* **Data layer**: contains the repositories, the data source api implementations and the corresponding api-specific models.

These layers are implemented using 3 different modules to increase the decoupling and the separation of concerns. The dependencies between the modules are set in order to make the data layer's classes completeply invisible to the presentation layer's ones and vice versa.
