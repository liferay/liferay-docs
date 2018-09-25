# Core Layer of Liferay Screens for iOS [](id=core-layer-of-liferay-screens-for-ios)

The Core is the micro-framework that lets developers write Screenlets in a
structured and isolated way. All Screenlets share a common structure based on
the Core classes, but each Screenlet can have a unique purpose and communication
API. 

![Figure 1: Here's the core layer of Liferay Screens for iOS.](../../../../images/screens-ios-architecture-02.png)

From right to left, these are the main components:

-   [**BaseScreenletView**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift):
    The base class for all Screenlet View classes. Its child classes belong to 
    the Theme layer. View classes use standard XIB files to render a UI and then 
    update it when the data changes. The `BaseScreenletView` class contains 
    template methods that child classes may overwrite. When developing your own 
    Theme from a parent Theme, you can read the Screenlet's properties or call 
    its methods from this class. Any user action in the UI is received in this 
    class, and then redirected to the Screenlet class. 

-   [**BaseScreenlet**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenlet.swift):
    The base class for all Screenlet classes. Screenlet classes receive UI 
    events through the `ScreenletView` class, then instantiate Interactors to 
    process and respond to that UI event. When the Interactor's result is 
    received, the `ScreenletView` (the UI) is updated accordingly. The 
    `BaseScreenlet` class contains a set of 
    [template methods](http://www.oodesign.com/template-method-pattern.html) 
    that child classes may overwrite. 

-   [**Interactor**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/Interactor.swift):
    The base class for all Interactors that a Screenlet supports. The Interactor 
    class implements a specific use case supported by the Screenlet. If the 
    Screenlet supports several use cases, it needs different Interactors. If the 
    Interactor needs to retrieve remote data, it uses a Server Connector to do 
    so. When the Server Connector returns the operation's result, the Interactor 
    returns that result to the Screenlet. The Screenlet then changes the 
    `ScreenletView` (the UI) status. 

-   [**ServerConnector**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerConnector.swift):
    The base class for all Liferay Connectors that a Screenlet supports. 
    Connectors retrieve data asynchronously from local or remote data sources. 
    The Interactor classes instantiate and start these Connector classes. 

-   [**SessionContext**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Context/SessionContext.swift): 
    An object (typically a singleton) that holds the logged in user's session. 
    Apps can use an implicit login, invisible to the user, or a login that 
    relies on explicit user input to create the session. User logins can be 
    implemented with 
    [Login Screenlet](/develop/reference/-/knowledge_base/7-1/loginscreenlet-for-ios). 
    For more information, see the 
    [tutorial on accessing the session in iOS](/develop/tutorials/-/knowledge_base/7-1/accessing-the-liferay-session-in-ios). 

-   [**LiferayServerContext**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Context/LiferayServerContext.swift):
    A singleton object that holds server configuration parameters. It's loaded 
    from the `liferay-server-context.plist` file. Most Screenlets use these 
    parameters as default values. 
