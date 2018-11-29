# Screenlet Layer [](id=screenlet-layer)

The Screenlet layer contains the Screenlets available in Liferay Screens for
Android. The following diagram uses Screenlet classes prefixed with
*MyScreenlet* to show the Screenlet layer's relationship with the core, View,
and Interactor components. 

![Figure 1: This diagram illustrates the Android Screenlet layer's relationship to other Screens components.](../../../../images/screens-android-architecture-03.png)

Screenlets are comprised of several Java classes and an XML descriptor file:

**MyScreenletViewModel:** an interface that defines the attributes shown in the
UI. It typically accounts for all the input and output values presented to the
user. For instance,
[`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/view/LoginViewModel.java)
includes attributes like the user name and password. The Screenlet can read the
attribute values, invoke Interactor operations, and change these values based
on operation results.

**MyScreenlet:** a class that represents the Screenlet component the app
developer interacts with. It includes the following things:

- Attribute fields for configuring the Screenlet's behavior. They are read in
  the Screenlet's `createScreenletView` method and their default values can 
  optionally be set there too.
- A reference to the Screenlet's View, specified by the `liferay:layoutId` 
  attribute's value. Note: a View must implement the Screenlet's `ViewModel`
  interface. 
- Any number of methods for invoking Interactor operations. You can optionally
  make them public for app developers to call. They can also handle UI events
  received in the view class through a regular listener (such as Android's
  `OnClickListener`) or events forwarded to the Screenlet via the
  `performUserAction` method.
- An optional (but recommended) listener object for the Screenlet to call on a 
  particular event. 

**MyScreenletInteractor:** implements an end-to-end use case that communicates
with a server or consumes a Liferay service. It might perform several
intermediate steps. For example, it might send a request to a server, compute a
local value based on the response, and then send this value to a different
server. On completing an interaction, the Interactor must notify its listeners,
one of which is typically the Screenlet class instance. The number of
Interactors a Screenlet requires depends on the number of server use cases it
supports. For example, the
[Login Screenlet](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/LoginScreenlet.java)
class only supports one use case (log in the user), so it has only one
Interactor. The
[DDL Forms Screenlet](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/ddl/form/DDLFormScreenlet.java)
class, however, supports several use cases (load the form, load a record, submit
the form, etc.), so it uses a different Interactor class for each use case.

**MyScreenletConnector62** and **MyScreenletConnector70**: the classes that 
create the Interactors required to communicate with a specific Liferay version. 
The `ServiceProvider` creates a singleton `ServiceVersionFactory` that returns 
the right Connector. 

**MyScreenletDefaultView:** a class that renders the Screenlet's UI with the
default layout. The class in Figure 3, for example, belongs to the Default View
set. The View object and the layout file communicate using standard mechanisms,
like a `findViewById` method or a listener object. User actions are received by
a specified listener (for example, `OnClickListener`) and then passed to the
Screenlet object via the `performUserAction` method. 

**myscreenlet_default.xml:** an XML file that specifies how to render the
Screenlet's View. Here's a skeleton of a Screenlet's layout XML file: 

    <?xml version="1.0" encoding="utf-8"?>
    <com.your.package.MyScreenletView 
        xmlns:android="http://schemas.android.com/apk/res/android">

        <!-- Put your regular components here: EditText, Button, etc. -->

    </com.your.package.MyScreenletView>

Refer to the tutorial
[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-1/creating-android-screenlets)
for more Screenlet details. Next, the View layer's details are described. 

## Related Topics [](id=related-topics)

[High-Level Architecture](/develop/tutorials/-/knowledge_base/7-1/high-level-architecture)

[Core Layer](/develop/tutorials/-/knowledge_base/7-1/core-layer)

[View Layer](/develop/tutorials/-/knowledge_base/7-1/view-layer)

[Screenlet Lifecycle](/develop/tutorials/-/knowledge_base/7-1/screenlet-lifecycle)
