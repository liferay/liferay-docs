# High Level Architecture

Liferay Screens for iOS is composed of a Core, a Screenlet layer, a View layer,
and Server Connectors. Server Connectors are technically part of the Core, but
are worth describing separately. They facilitate interaction with local and
remote data sources and communication between the Screenlet layer and the
[Liferay Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/mobile-sdk). 

![Figure 1: The high level components of Liferay Screens for iOS.](../../../../images/screens-ios-architecture-01.png)

Each component is described here: 

-   **Core:** A micro-framework that lets developers write their own Screenlets, 
    views, and Server Connector classes. The Core includes all the base classes 
    for developing Screens components. 

-   **Screenlets:** Swift classes to insert in any `UIView`. Screenlets render a 
    selected Theme in the runtime and in Interface Builder. They also react to 
    UI events to start server requests (via Server Connectors), and define a set 
    of `@IBInspectable` properties that can be configured from Interface 
    Builder. The Screenlets bundled with Liferay Screens are known as the 
    [Screenlet library](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios). 

-   **Interactors:** Implementations of specific use cases for communicating 
    with servers or any other data store. They can use local and remote data 
    sources by using Server Connectors or custom classes. If a user action or 
    use case needs to execute more than one query on a local or remote store, 
    the sequence is done in the corresponding Interactor. If a Screenlet 
    supports more than one user action or use case, an Interactor must be 
    created for each. 

-   **Connectors** (or Server Connectors): A collection of classes that can 
    interact with local and remote data sources and Liferay instances. Liferay's 
    own set of Connectors, Liferay Connectors, use the
    [Liferay Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-ios-app). 
    All Server Connectors can run concurrently since they use the 
    [`NSOperation` framework](https://developer.apple.com/library/mac/documentation/General/Conceptual/ConcurrencyProgrammingGuide/OperationObjects/OperationObjects.html#//apple_ref/doc/uid/TP40008091-CH101-SW1). 
    It's straightforward to define priorities and dependencies between 
    Connectors, so you can build your own graph of Connectors (operations) that 
    can the framework can resolve. Connectors are always created using a 
    [factory class](https://en.wikipedia.org/wiki/Abstract_factory_pattern) 
    so they can be injected by the app developer. 

-   **Themes:** A set of XIB files and accompanying `UIView` classes that 
    present Screenlets to the user. 
