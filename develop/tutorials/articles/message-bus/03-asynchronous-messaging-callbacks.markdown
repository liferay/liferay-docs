# Asynchronous Messaging with Callbacks [](id=asynchronous-messaging-with-callbacks)

Asynchronous messaging consists of sending a message and then continuing with
processing. The sender doesn't block and wait for an immediate response. This 
allows the sender to continue with other tasks. Even so, it's often necessary 
to allow the listener to respond to the sender. This can be achieved using a
call-back. The sender implements a call-back by stuffing the message with a
destination key that lets the listener know where to send its response. You can
think of this as a return address of sorts. This tutorial illustrates
asynchronous messaging with call-backs by showing you how to implement it
between one sending and two listening portlets in a plugin project. You can find
the code for this example plugin project here:
[Tasks Portlet](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/code/msg-bus/async-callback/tasks-portlet).

Imagine the following scenario. A rock concert requires many things to be done
before the show can go on. The amplifiers, sound system, lighting, and any other
stage effects have to be set up properly for the show to be successful.
Naturally, the tour manager has chosen Liferay Portal for managing all these
tasks. The manager has a Tasks portlet for submitting items that need to be set
up. The tasks then need to go to the roadies' Setup portlet and the inventory
manager's Inventory portlet. The manager also wants a response from these
portlets. However, the manager is very busy. It wouldn't be practical to put
everything else on hold while waiting for responses from each setup task.
Asynchronous messaging with call-backs is an ideal solution. In this example,
the messages sent by the Tasks portlet to the Setup and Inventory portlets are
sent in serial instead of in parallel. Now it's time to hop on the Message Bus! 

![Figure 1: Asynchronous messaging with *serial* dispatching](../../images/msg-bus-async-serial-msg.png)

## Deciding on Destination Keys [](id=deciding-on-destination-keys)

You first need to figure out what your destination keys will be. Destination
keys are the locations where messages are sent. You can think of them as the
mailing addresses of the Message Bus system. The destination keys need to be
included with the message and registered as destinations in
`WEB-INF/src/META-INF/messaging-spring.xml`. In this example, the destination 
keys are chosen to reflect the package names of the two portlets. 

The following table shows the destination keys, senders, and listeners for the 
Tasks, Setup, and Inventory portlets described above: 

 Destination Key | Sender | Listeners |
-------------------- | ----- | ----- |
 `tour/roadie/setup` | Tasks | Setup, Inventory |
 `tour/manager/task` | Setup, Inventory | Tasks |

Now that you know what your destination keys are, you can use them when writing 
the code for sending and receiving messages. You'll start with the message
sender in the Tasks portlet first. 

## Implementing the Message Sender [](id=implementing-the-message-sender)

To get the wheels on the Message Bus rolling, you need to start with the initial 
message sender. In this example, the initial sender is inside the method of the
Tasks portlet that is responsible for adding new setup tasks. This is because
the messages need to be sent each time the tour manager adds a new setup task.
You can find this code here:
[`TasksPortlet.java`](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/code/msg-bus/async-callback/tasks-portlet/docroot/WEB-INF/src/com/tour/portlet/tasks/TasksPortlet.java). 

A sender for an asynchronous message with a call-back does the following things:

1. Creates a `JSONObject` to serve as the message:

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

2. Uses the `put` method to stuff the message with key/value pairs. In this 
   example, some key/value pairs of a Task entity are added:

        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("status", status);

3. Adds the call-back destination key:

        jsonObject.put("responseDestinationName", "tour/manager/task");

4. Sends the message to the destination:

        MessageBusUtil.sendMessage("tour/roadie/setup", jsonObject.toString());

You also need to be sure that you add the following import:

	import com.liferay.portal.kernel.messaging.MessageBusUtil;

Now that you've implemented your initial sender, you can implement your 
listeners.

## Implementing the Message Listeners [](id=implementing-the-message-listeners)

You need to have one or more message listeners implemented to receive messages 
from your sender. Each listener is a class that implements Liferay's 
`MessageListener` interface. In this example there are three listeners, one for 
each portlet. You can find the example listeners here:
[Listeners](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/code/msg-bus/async-callback/tasks-portlet/docroot/WEB-INF/src/com/tour/portlet/tasks/messaging/impl).

Asynchronous listeners with call-backs do the following things: 

1. Implements the `receive(Message message)` method of the
   `com.liferay.portal.kernel.messaging.MessageListener` interface.

2. Gets the message payload and cast it to a `String`.

        String payload = (String)message.getPayload();

3. Creates a `JSONObject` from the payload string.

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);
 
4. Gets values from the `JSONObject` using its getter methods. This example gets 
   the values that were added by the sender. Also note that the destination key 
   from the sender is retrieved for use in the call-back.

    	String name = (String) jsonObject.getString("name");
        String description = (String) jsonObject.getString("description");
        String status = (String) jsonObject.getString("status");
        String responseDestinationName = jsonObject.getString("responseDestinationName");

5. Creates a `JSONObject` to use as the response message.

        jsonObject = JSONFactoryUtil.createJSONObject();

6. Stuffs the response message with key/value pairs.

        jsonObject.put("roadieResponse", "Yes");
 
7. Sends the message back to the sender.

        MessageBusUtil.sendMessage(responseDestinationName, jsonObject.toString());

Make sure that you add the following imports to your listener classes:

	import com.liferay.portal.kernel.messaging.Message;
	import com.liferay.portal.kernel.messaging.MessageBusUtil;
	import com.liferay.portal.kernel.messaging.MessageListener;

Any other listeners you need can be implemented using the same steps. Next, 
you'll configure your listeners and destinations for use with the Message Bus. 

## Configuring the Message Bus [](id=configuring-the-message-bus)

Now that you've implemented your message senders and listeners, you need to 
configure them in your plugin's `WEB-INF/src/META-INF/messaging-spring.xml` 
file. Create this file if it doesn't yet exist. 

+$$$

**Warning:** You should only do this *after* implementing any senders and
listeners you have. Tools like Liferay IDE and Liferay Developer Studio
automatically deploy plugins as you save changes. If you declare sender or
listener classes in the configuration file that don't yet exist, exceptions will
be thrown when your application is deployed. 

$$$

For example, here's the configuration file for the Tasks, Setup, and Inventory
portlets: 

    <?xml version="1.0"?>

    <beans
		default-destroy-method="destroy"
		default-init-method="afterPropertiesSet"
		xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
	>

        <!-- Listeners -->

        <bean id="messageListener.setup_listener" class="com.tour.portlet.tasks.messaging.impl.SetupMessagingImpl" />
        <bean id="messageListener.inventory_listener" class="com.tour.portlet.tasks.messaging.impl.InventoryMessagingImpl" />
        <bean id="messageListener.tasks_listener" class="com.tour.portlet.tasks.messaging.impl.TasksMessagingImpl" />

	
        <!-- Destinations -->

        <bean id="tour.roadie.setup" class="com.liferay.portal.kernel.messaging.SerialDestination">
            <property name="name" value="tour/roadie/setup" />
        </bean>

        <bean id="tour.manager.task" class="com.liferay.portal.kernel.messaging.SerialDestination">
            <property name="name" value="tour/manager/task" />
        </bean>

        <!-- Configurator -->

        <bean id="messagingConfigurator" class="com.liferay.portal.kernel.messaging.config.PluginMessagingConfigurator">
            <property name="messageListeners">
                <map key-type="java.lang.String" value-type="java.util.List">
                    <entry key="tour/roadie/setup">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                                <ref bean="messageListener.setup_listener" />
                                <ref bean="messageListener.inventory_listener" />
                        </list>
                    </entry>
                    <entry key="tour/manager/task">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                                <ref bean="messageListener.tasks_listener" />
                        </list>
                    </entry>
                </map>
            </property>
            <property name="destinations">
                <list>
                    <ref bean="tour.roadie.setup"/>
                    <ref bean="tour.manager.task"/>
                </list>
            </property>
        </bean>
    </beans>

This configuration specifies the following beans: 

- *Listener beans*: Specify the listener classes to handle messages.
- *Destination beans*: Specify the class *type* and *key* names of the
   destinations.
- *Configurator bean*: Maps listeners to their destinations.

Now you just need to register this `messaging-spring.xml` file in your 
`docroot/WEB-INF/web.xml` file. To do so, place the following code just above 
the closing `</web-app>` tag in the `web.xml` file: 

    <listener>
      <listener-class>com.liferay.portal.kernel.spring.context.PortletContextLoaderListener</listener-class>
    </listener>

    <context-param>
      <param-name>portalContextConfigLocation</param-name>
      <param-value>/WEB-INF/classes/META-INF/messaging-spring.xml</param-value>
    </context-param>

Save and redeploy your portlet. Your plugin should now send and receive messages
the way you've configured it. In the case of the tour manager, the Tasks portlet
now shows replies from the Setup and Inventory portlets. 

![Figure 2: Responses from the Setup and Inventory portlets show in the Tasks portlet.](../../images/msg-bus-async-callb-tasks.png)

Great! Now you know how to use Message Bus to send asynchronous messages with 
call-backs. 

## Related Topics [](id=related-topics)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)

[Developing with the Plugins SDK](/tutorials/-/knowledge_base/6-2/plugins-sdk)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/6-2/liferay-ide)

[Developing with Maven](/tutorials/-/knowledge_base/6-2/maven)
