# Communicating Between JSF Portlets Using IPC [](id=communicating-between-jsf-portlets-using-ipc)

Liferay Faces Bridge supports Portlet 2.0 Inter Portlet Communication (IPC),
using the JSR 329 approach for supporting Portlet 2.0 Events and Portlet 2.0
Public Render Parameters. 

You can visit the [Liferay Faces
Demos](http://www.liferay.com/community/liferay-projects/liferay-faces/demos)
home page to see portlets that demonstrate the IPC techniques described in this
tutorial. At that location, you'll also find portlets that implement Ajax Push
for IPC, using ICEfaces+ICEPush and PrimeFaces+PrimePush. 

For the first tutorial, you'll learn how to use Portlet 2.0 Public Render
Parameters.

## Using Portlet 2.0 Public Render Parameters [](id=using-portlet-2-0-public-render-parameters)

The Public Render Parameters technique provides a way for portlets to share data
by setting public/shared parameter names in a URL controlled by the portal.
While the benefit of this approach is that it is relatively easy to implement,
the drawback is that only small amounts of data can be shared. Typically the
kind of data that is shared is simply the value of a database primary key. As
required by the Portlet 2.0 standard, Public Render Parameters must be declared
in the `WEB-INF/portlet.xml` descriptor. 

This example excerpt from a `WEB-INF/portlet.xml` descriptor demonstrates
setting a public render parameter for a customer ID, shared between a Customers
portlet and a Bookings portlet: 

    <portlet>
        <portlet-name>customersPortlet</portlet-name>
        <supported-public-render-parameter>selectedCustomerId</supported-public-render-parameter>
    </portlet>
    <portlet>
        <portlet-name>bookingsPortlet</portlet-name>
        <supported-public-render-parameter>selectedCustomerId</supported-public-render-parameter>
    </portlet>
    <public-render-parameter>
        <identifier>selectedCustomerId</identifier>
        <qname xmlns:x="http://liferay.com/pub-render-params">x:selectedCustomerId</qname>
    </public-render-parameter>

Fortunately, the JSR 329 standard defines a mechanism for you to use Portlet 2.0
Public Render Parameters for IPC in a way that is more natural to JSF
development. Section 5.3.2 of this standard requires the bridge to inject the
public render parameters into the Model concern of the MVC design pattern (as in
JSF model managed-beans) after the `RESTORE_VIEW` phase completes. This is
accomplished by evaluating the EL expressions found in the
`<model-el>...</model-el>` section of the `WEB-INF/faces-config.xml` descriptor.
The
[`faces-config.xml`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/demos/bridge/jsf2-ipc-pub-render-params-portlet/src/main/webapp/WEB-INF/faces-config.xml)
descriptor excerpt below demonstrates using this mechanism for the example
Customers and Bookings portlets:

    <faces-config>
        <application>
            <application-extension>
                <bridge:public-parameter-mappings>
                    <bridge:public-parameter-mapping>
                        <parameter>customersPortlet:selectedCustomerId</parameter>
                        <model-el>#{customersModelBean.selectedCustomerId}</model-el>
                    </bridge:public-parameter-mapping>
                    <bridge:public-parameter-mapping>
                        <parameter>bookingsPortlet:selectedCustomerId</parameter>
                        <model-el>#{bookingsModelBean.selectedCustomerId}</model-el>
                    </bridge:public-parameter-mapping>
                </bridge:public-parameter-mappings>
            </application-extension>
        </application>
    </faces-config>

Section 5.3.2 of the JSR 329 standard also requires that if a
`bridgePublicRenderParameterHandler` has been registered in the
`WEB-INF/portlet.xml` descriptor, then the handler must be invoked so that it
can perform any processing that might be necessary. Optionally, you can
implement and register a `bridgePublicRenderParameterHandler` for processing
public render parameters. 

For example, a `BridgePublicRenderParameterHandler` for processing public render
params for the Bookings portlet's currently selected customer could be
stubbed out like the following class code: 

    package com.liferay.faces.example.handler;

    import javax.faces.context.FacesContext;

    import com.liferay.faces.bridge.BridgePublicRenderParameterHandler;

    public class CustomerSelectedHandler
    implements BridgePublicRenderParameterHandler {

        public void processUpdates(FacesContext facesContext) {
            // Here is where you would perform any necessary processing of public
            // render parameters
        }

    }

For the `BridgePublicRenderParameterHandler` to be invoked, it must be
registered in an `<init-param>` element within the portlet's `<portlet>` element
in the `WEB-INF/portlet.xml` descriptor: 

    <init-param>
        <name>javax.portlet.faces.bridgePublicRenderParameterHandler</name>
        <value>com.liferay.faces.example.handler.CustomerSelectedHandler</value>
    </init-param>

+$$$

**Note:** For a complete example demonstrating public render parameters and a
`bridgePublicRenderParameterHandler`, see the [JSF2 IPC Public Render Parameters
Portlet](https://github.com/liferay/liferay-faces/tree/3.1.3-ga4/demos/bridge/jsf2-ipc-pub-render-params-portlet)
demo on GitHub.
 
$$$

Now that you've explored Public Render Parameters for JSF in IPC, you'll learn
about Events in IPC. 

## Handling Portlet 2.0 Events [](id=handling-portlet-2-0-events)

In Portlet 2.0, you can leverage a server-side events technique that uses an
event-listener design to share data between portlets. When using this form of
IPC, the portlet container acts as a broker and distributes events and payload
(data) to portlets. One requirement of this approach is that the payload must
implement the `java.io.Serializable` interface since it might be sent to a
portlet in another `.war` file running in a different classloader. In addition,
the Portlet 2.0 standard requires the events to be declared in the
`WEB-INF/portlet.xml` descriptors of the involved portlets. 

The following example `WEB-INF/portlet.xml` descriptor snippets define an IPC
event for when a Customer is edited in the example Bookings portlet. The
`bookingsPortlet` portlet is registered as the event's publisher (or sender).
The `customersPortlet` portlet, on the other hand, is registered as a processor
(or listener) for that event type. Consequently, when a Customer is edited in
the `bookingsPortlet` portlet, that portlet publishes the event and the
`customersPortlet` portlet is notified for processing the event. 

Here's a snippet from the Customers portlet's
[`portlet.xml`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/demos/bridge/jsf2-ipc-events-customers-portlet/src/main/webapp/WEB-INF/portlet.xml)
descriptor: 

    <portlet>
        <portlet-name>customers</portlet-name>

        ...

        <supported-processing-event>
            <qname xmlns:x="http://liferay.com/events">x:ipc.customerEdited</qname>
        </supported-processing-event>
    </portlet>


    ...

    <event-definition>
        <qname xmlns:x="http://liferay.com/events">x:ipc.customerEdited</qname>
        <value-type>com.liferay.faces.demos.dto.Customer</value-type>
    </event-definition>

The snippet from the Bookings portlet's
[`portlet.xml`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/demos/bridge/jsf2-ipc-events-bookings-portlet/src/main/webapp/WEB-INF/portlet.xml)
is similar, except it is specified as a publisher: 

    <portlet>
        <portlet-name>bookings</portlet-name>

        ...

        <supported-publishing-event>
            <qname xmlns:x="http://liferay.com/events">x:ipc.customerEdited</qname>
        </supported-publishing-event>
    </portlet>

    ...

    <event-definition>
        <qname xmlns:x="http://liferay.com/events">x:ipc.customerEdited</qname>
        <value-type>com.liferay.faces.demos.dto.Customer</value-type>
    </event-definition>

Optionally, you can implement a `BridgeEventHandler` for an event type and
register the handler in the `WEB-INF/portlet.xml` descriptor. If a
`BridgeEventHandler` has been registered in the `WEB-INF/portlet.xml`
descriptor, Section 5.2.5 of the JSR 329 standard requires that the handler must
be invoked so that it can perform any event processing that might be necessary. 

When the customer's details (such as first name/last name) are edited in the
Bookings portlet, the event named `ipc.customerEdited` is sent back to the
Customers portlet and is processed by the following
[`CustomerEditedEventHandler`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/demos/bridge/jsf2-ipc-events-customers-portlet/src/main/java/com/liferay/faces/demos/event/CustomerEditedEventHandler.java)
class: 

    ...

    import javax.faces.context.FacesContext;
    import javax.portlet.Event;
    import javax.portlet.faces.BridgeEventHandler;
    import javax.portlet.faces.event.EventNavigationResult;

    ...

    public class CustomerEditedEventHandler implements BridgeEventHandler {

            ...

            public EventNavigationResult handleEvent(FacesContext facesContext, Event event) {
                    EventNavigationResult eventNavigationResult = null;
                    String eventQName = event.getQName().toString();
    
                    if (eventQName.equals("{http://liferay.com/events}ipc.customerEdited")) {
                        ...
                    }
    
                    return eventNavigationResult;
            }

            ...
    }

And here's the descriptor for registering the `CustomerEditedEventHandler` class
as a bridge event handler for the Customers portlet. The following
`<init-param>` belongs in the Customers portlet's `<portlet>` element, in the
[`WEB-INF/portlet.xml`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/demos/bridge/jsf2-ipc-events-customers-portlet/src/main/webapp/WEB-INF/portlet.xml)
descriptor. 

    <init-param>
        <name>javax.portlet.faces.bridgeEventHandler</name>
        <value>com.liferay.faces.example.event.CustomerEditedEventHandler</value>
    </init-param>

+$$$

**Note:** For a complete example demonstrating JSF 2 IPC events, see the [JSF2
IPC Events -
Customers](https://github.com/liferay/liferay-faces/tree/3.2.4-ga5/demos/bridge/jsf2-ipc-events-customers-portlet)
and [JSF2 IPC Events -
Bookings](https://github.com/liferay/liferay-faces/tree/3.2.4-ga5/demos/bridge/jsf2-ipc-events-bookings-portlet)
demo portlets on GitHub. 

$$$

You've explored some common basic JSF portlet development topics dealing with
IPC. These techniques should help launch you into development of your own JSF
portlet development using IPC! 

**Related Topics**

[Contexts and Dependency Injection for JSF Portlets](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/contexts-and-dependency-injection-for-jsf-portlets)

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Portal](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-portal)

[Understanding Liferay Faces Alloy](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-alloy)
