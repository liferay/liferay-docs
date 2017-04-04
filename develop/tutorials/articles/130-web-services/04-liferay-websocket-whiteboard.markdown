# Liferay WebSocket Whiteboard [](id=liferay-websocket-whiteboard)

Modern web apps exchange large amounts of data with clients. The WebSockets 
specification lets this exchange occur over a full-duplex connection that 
remains open, therefore enabling real-time communication. This approach is more
efficient than techniques like long polling, which open two connections to 
emulate a full-duplex connection. 
[Click here](https://www.websocket.org/aboutwebsocket.html) 
for more information on WebSockets. 

Since WebSockets are ubiquitous throughout the web and in all modern browsers, 
you need a way to register new WebSocket endpoints in @product@. The *Liferay 
WebSocket Whiteboard* lets you define new WebSocket endpoints as regular OSGi 
services. This tutorial shows you how to do this. Onward! 

## Configuring a Non-Liferay OSGi Container [](id=configuring-a-non-liferay-osgi-container)

There may be instances where you want to use a Liferay OSGi module in a 
non-Liferay OSGi container, and need to define a WebSocket endpoint. To do this, 
you must register a `javax.servlet.ServletContext` service with the property 
`websocket.active` set to `true`: 

    Dictionary<String, Object> servletContextProps = new Hashtable<String, Object>();
    servletContextProps.put("websocket.active", Boolean.TRUE);

    bundleContext.registerService(ServletContext.class, servletContext, servletContextProps);

You must also configure the `javax.websocket-api`'s `ServiceLoader`. You 
can do this by creating your own module as a `javax.websocket-api` fragment. 
Here's an example of a manifest for such a module: 

    Fragment-Host: javax.websocket-api

    Require-Capability:\
        osgi.serviceloader;\

    filter:='(osgi.serviceloader=javax.websocket.server.ServerEndpointConfig$Configurator)';\
            cardinality:=multiple,\
        osgi.extender;\
            filter:='(osgi.extender=osgi.serviceloader.processor)'

Next, you'll learn how to define a new WebSocket server endpoint in a Liferay 
OSGi container. 

## Configuring a Liferay OSGi Container [](id=configuring-a-liferay-osgi-container)

Defining a new WebSocket server endpoint in @product@ is straightforward. Follow 
these steps: 

1. If you're running Liferay Portal 7.0.2 GA3 or Liferay Digital 
   Enterprise 7.0 Fix Pack 7 or earlier, add the following property to your 
   `portal-ext.properties` file. Otherwise, you can skip this and move on to the 
   next step. 

        module.framework.system.packages.extra=\
            com.ibm.crypto.provider,\
            com.ibm.db2.jcc,\
            com.microsoft.sqlserver.jdbc,\
            com.mysql.jdbc,\
            com.p6spy.engine.spy,\
            com.sun.security.auth.module,\
            com.sybase.jdbc4.jdbc,\
            oracle.jdbc,\
            org.postgresql,\
            org.apache.naming.java,\
            org.hsqldb.jdbc,\
            org.mariadb.jdbc,\
            sun.misc,\
            sun.net.util,\
            sun.security.provider,\
            javax.websocket;version="1.1.0",\
            javax.websocket.server;version="1.1.0"

2. Deploy the Liferay WebSocket Whiteboard module 
   (`com.liferay.websocket.whiteboard`) to your @product@ instance. You can 
   download this module from JCenter or Maven Central by clicking the respective 
   link: 

    - [JCenter](https://bintray.com/bintray/jcenter/com.liferay%3Acom.liferay.websocket.whiteboard)
    - [Maven Central](https://search.maven.org/#search%7Cga%7C1%7Cliferay.websocket.whiteboard)

3. In your module's build file, add a dependency to the Liferay WebSocket 
   Whiteboard: 

        com.liferay:com.liferay.websocket.whiteboard:1.0.1

4. In your module, define a WebSocket server endpoint as you normally would. 
   Note, however, that @product@ doesn't currently support the annotation-driven 
   approach; only the interface-driven approach is supported. To create a 
   WebSocket server endpoint, register an OSGi Service for 
   `javax.websocket.Endpoint.class` with the following properties: 

    - `org.osgi.http.websocket.endpoint.path`: the WebSocket's path (required)
    - `org.osgi.http.websocket.endpoint.decoders`: the WebSocket's decoders 
      (optional)
    - `org.osgi.http.websocket.endpoint.encoders`: the WebSocket's encoders 
      (optional)
    - `org.osgi.http.websocket.endpoint.subprotocols`: the WebSocket's 
      subprotocols (optional)

For example, the following steps show you how to define a WebSocket endpoint in 
a portlet. For the purposes of this example, the portlet also contains a client 
that communicates with the endpoint. This example portlet, Echo Portlet, uses 
WebSocket functionality to echo a simple message the client sends to the server. 

![Figure 1: The example Echo portlet sends and receives a simple message via a WebSocket endpoint.](../../images/websocket-echo-portlet.png)

Although the following steps show only code snippets, you can 
[click here](https://github.com/cgoncas/liferay-websocket-echo) 
to see the complete example code. 

Use these steps to define a WebSocket endpoint: 

1. Add the WebSocket dependency to your module's build file. For example, here's 
   the dependency in a `build.gradle` file: 

        javax.websocket:javax.websocket-api:1.1

2. Create the WebSocket endpoint. Note that the `@Component` annotation contains 
   the required property `org.osgi.http.websocket.endpoint.path`, which defines 
   the endpoint `/o/echo`. Also note that `service = Endpoint.class` in the 
   `@Component` annotation registers this class as an `Endpoint` service in 
   @product@'s OSGi framework. Otherwise, there's nothing special about the 
   `EchoWebSocketEndpoint` class's code; it resembles that of any other 
   WebSocket endpoint: 

        @Component(
            immediate = true,
            property = {"org.osgi.http.websocket.endpoint.path=/o/echo"},
            service = Endpoint.class
        )
        public class EchoWebSocketEndpoint extends Endpoint {

            @Override
            public void onOpen(final Session session, EndpointConfig endpointConfig) {
                session.addMessageHandler(
                    new MessageHandler.Whole<String>() {
                    @Override
                    public void onMessage(String text) {
                        try {
                            RemoteEndpoint.Basic remoteEndpoint =
                                session.getBasicRemote();
                            remoteEndpoint.sendText(text);
                        }
                        catch (IOException ioe) {
                            throw new RuntimeException(ioe);
                        }
                    }
                });
            }
        }

3. Write your client code. In this example, the Echo portlet's `view.jsp` 
   defines a WebSocket client. Again, there's nothing special about this code; 
   it resembles that of other WebSocket clients:

        <%@ include file="/init.jsp" %>

        <div id="content">
            <div id="left_col">
                <strong>Websocket URL:</strong>
                <br />
                <input id="urlInputText" type="text" readonly />
                </br>
                </br>

                <button onClick="initWebSocket();">Connect</button>
                <button onClick="stopWebSocket();">Disconnect</button>
                <button onClick="checkSocket();">State</button>
                <br />
                <br />
                <strong>Message:</strong>
                <br />
                <input id="inputText" onkeydown="if(event.keyCode==13)sendMessage();" type="text" />
                 <button onClick="sendMessage();">Send</button>
             </div>

             <div id="right_col">
                 <strong>Log:</strong>
                 <br />
                 <textarea id="debugTextArea" style="width:400px;height:200px;" readonly></textarea>
             </div>
        </div>

        <script type="text/javascript">
            var debugTextArea = document.getElementById("debugTextArea");

            var wsUri = "ws://localhost:8080/o/echo";

            urlInputText.value=wsUri;

            resizeUrlInputText(urlInputText.value);

            function resizeUrlInputText(message) {
                urlInputText.size=message.length;
            }

            function debug(message) {
                debugTextArea.value += message + "\n\n";
                debugTextArea.scrollTop = debugTextArea.scrollHeight;
            }

            function sendMessage() {
                var msg = document.getElementById("inputText").value;
                if ( websocket != null ) {
                    document.getElementById("inputText").value = "";
                    websocket.send(msg);
                    debug("Message sent: " + msg);
                    console.log( "string sent :", '"'+msg+'"' );
                }
                else {
                    debug("Can't sent message, the connection is not open");
                }
            }

            var websocket = null;

            function initWebSocket() {
                try {
                    if (typeof MozWebSocket == 'function')
                        WebSocket = MozWebSocket;
                    if ( websocket && websocket.readyState == 1 )
                        websocket.close();
                    websocket = new WebSocket( wsUri );
                    websocket.onopen = function(evt) {
                        debug("CONNECTED");
                    };
                    websocket.onclose = function(evt) {
                        debug("DISCONNECTED");
                    };
                    websocket.onmessage = function(evt) {
                        console.log( "Message received: ", evt.data );
                        debug( "Message received: " + evt.data );
                    };
                    websocket.onerror = function(evt) {
                        debug('ERROR: ' + evt.data);
                    };
                }
                catch (exception) {
                    debug('ERROR: ' + exception);
                }
            }

            function stopWebSocket() {
                if (websocket) {
                    websocket.close();
                }
            }

            function checkSocket() {
                if (websocket != null) {
                    var stateStr;
                    switch (websocket.readyState) {
                        case 0: {
                            stateStr = "CONNECTING";
                            break;
                        }
                        case 1: {
                            stateStr = "OPEN";
                        break;
                    }
                    case 2: {
                        stateStr = "CLOSING";
                        break;
                    }
                    case 3: {
                        stateStr = "CLOSED";
                        break;
                    }
                    default: {
                        stateStr = "UNKNOW";
                        break;
                    }
                }
                debug("WebSocket state = " + websocket.readyState + " ( " + stateStr + " )");
                } else {
                    debug("WebSocket is null");
                }
            }
        </script>

That's it! Now you know how to create WebSocket endpoints in @product@. 

## Related Topics [](id=related-topics)

[JAX-WS and JAX-RS](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-0/service-builder-web-services)
