# High-Level Architecture [](id=high-level-architecture)

Liferay Screens for Android is composed of a Core, a Screenlet layer, a View
layer, Interactors, and Server Connectors. Interactors are technically part of 
the core, but are worth covering separately. They facilitate interaction with 
both local and remote data sources, as well as communication between the 
Screenlet layer and the 
[Liferay Mobile SDK](/develop/tutorials/-/knowledge_base/7-1/mobile-sdk). 

![Figure 1: Here are the high-level components of Liferay Screens for Android. The dashed arrow connectors represent a "uses" relationship, in which a component uses the component its pointing to.](../../../../images/screens-android-architecture-01.png)

Each component is described below.

**Core:** includes all the base classes for developing other Screens components.
It's a micro-framework that lets developers write their own Screenlets, Views,
and Interactors.

**Screenlets:** Java view classes for inserting into any activity or fragment 
view hierarchy. They render a selected layout in the runtime and in Android 
Studio's visual editor and react to UI events, sending any necessary server 
requests. You can set a Screenlet's properties from its layout XML file and 
Java classes. The Screenlets bundled with Liferay Screens are known collectively 
as the 
[Screenlet Library](/develop/reference/-/knowledge_base/7-1/screenlets-in-liferay-screens-for-android). 

**Server Connectors:** a collection of classes that interact with different 
Liferay versions. These classes abstract away the complexity of communicating 
with different Liferay versions. This allows the developer to call API methods 
and the correct Interactor without worrying about the specific Liferay version 
(currently 6.2 or 7). 

**Interactors:** implement specific use cases for communicating with servers.
They can use local and remote data sources. Most Interactors use the
[Liferay Mobile SDK](/develop/tutorials/-/knowledge_base/7-1/mobile-sdk) 
to exchange data with a Liferay instance. If a user action or use case needs to 
execute more than one query on a local or remote store, the sequence is done 
in the corresponding Interactor. If a Screenlet supports more than one user 
action or use case, an Interactor must be created for each. Interactors are 
typically bound to one specific Liferay version, and instantiated by a Server 
Connector. Interactors run in a background thread and can therefore perform 
intensive operations without blocking the UI thread. 

**Views:** a set of layouts and accompanying custom view classes that present 
Screenlets to the user.

Next, the core layer is described in detail. 

## Related Topics [](id=related-topics)

[Core Layer](/develop/tutorials/-/knowledge_base/7-1/core-layer)

[Screenlet Layer](/develop/tutorials/-/knowledge_base/7-1/screenlet-layer)

[View Layer](/develop/tutorials/-/knowledge_base/7-1/view-layer)

[Screenlet Lifecycle](/develop/tutorials/-/knowledge_base/7-1/screenlet-lifecycle)
