# Implementing Synchronous Messaging 

Synchronous messaging occurs when the sender blocks, waiting for a response from 
the recipient. During this block, the sender cannot process *any* additional 
information. This means that the thread the message is sent from is effectively 
locked until it receives a response. You should therefore use synchronous 
messaging only in cases where it is absolutely necessary that a response is 
received before moving on. You can think of this as a sort of high priority read 
receipt for a message. This tutorial uses such a case to illustrate the steps 
required to set up and use synchronous messaging between two portlets in a 
plugin project. 

![Figure 1: Synchronous messaging.](../../images/msg-bus-sync-msg.png)

Imagine the following scenario. A rock concert requires many, many things to be 
set up before the show can go on. The amplifiers, sound system, lighting, and 
any other stage effects have to be properly set up for the show to be 
successful. Naturally, the tour manager has chosen Liferay Portal for managing 
all these tasks. The manager has a custom Tasks portlet for submitting setup 
tasks, which then need to go to the roadies' Setup portlet on a separate page. 
The manager also needs confirmation, before moving on with other things, that 
the roadies' Setup portlet has received each request. Synchronous messaging to 
the rescue! 

<!-- 
Note: Links to sample code referenced throughout will need to be changed once it 
gets merged into master.
-->

<!-- 
QA: Contact Nick to get the starting portlet project.
-->

## Deciding on Destination Keys 

You first need to figure out what your destination keys will be. Destination 
keys serve as the specific locations where messages are sent. You can think of 
them as the mailing addresses of the Message Bus system. The destination keys 
need to be included with the message and registered as destinations in 
`WEB-INF/src/META-INF/messaging-spring.xml`. In this example, the destination 
keys are chosen to reflect the package names of the two portlets. 

The following table shows the destination keys, senders, and receivers for the 
Tasks and Setup portlets described above: 

 Destination Key | Sender | Receivers |
-------------------- | ----- | ----- |
 `tour/roadie/setup` | Tasks | Setup |
 `tour/manager/task` | Setup | Tasks |

The receiver sends its response messages to a destination on which the sender 
listens. This way, a full-bodied response message is sent back to the sender 
along with the response object. Now that you know what your destination keys 
are, you can use them when writing the code that sends and receives the 
messages. You'll start with the message sender first. 

## Implementing the Message Sender 

Now it's time to write the message sender code. So where should you put this 
code? Great question! Simply place it in the method of your application that you 
want it to be called with. For example, the message sender code for the tour 
manager's Tasks portlet is in the method of [`TasksPortlet.java`](https://github.com/ngaskill/liferay-docs/blob/message-bus-tutorials/develop/tutorials/code/msg-bus/synchronous/tasks-portlet/docroot/WEB-INF/src/com/tour/portlet/tasks/TasksPortlet.java) 
that adds a new task. This is because a synchronous message needs to be sent 
each time the tour manager adds a new task to the portlet. 

The sender takes the following steps: 

1. Creates the message using Liferay's `Message` class. 

        Message message = new Message();

2. Stuffs the message with key/value pairs using the `put` method. In this 
   example, key/value pairs of a Task entity are added. 
   
        message.put("name", name);
        message.put("description", description);
        message.put("status", status);

3. Sets a response ID and response destination for listeners to use in replying
   back. 
   
        message.setResponseId("1111");
        message.setResponseDestinationName("tour/manager/task");

4. Sends the message to the destination with a timeout value of 10,000
   milliseconds. This is how long the sender blocks for while waiting for a 
   response. If no response is received, then a `MessageBusException` is thrown.

    try {
        String roadieResponse = (String) MessageBusUtil.sendSynchronousMessage("tour/roadie/setup", message, 10000);
    } catch (MessageBusException e) {
	e.printStackTrace();
	}

Also be sure to add the following imports to your message sender file: 

        import com.liferay.portal.kernel.messaging.Message;
        import com.liferay.portal.kernel.messaging.MessageBusException;
        import com.liferay.portal.kernel.messaging.MessageBusUtil;

Now that you've got your message sender implemented, it's time to head to the 
next stop on the Message Bus--the message listener! 

## Implementing the Message Listener 

Implementing the message listener is a bit more involved than implementing the 
message sender, but not by much. To implement the listener you need to make a 
class that implements Liferay's `MessageListener` interface. You can find the 
listener of the tour manager's Tasks portlet [here on Github](https://github.com/ngaskill/liferay-docs/blob/message-bus-tutorials/develop/tutorials/code/msg-bus/synchronous/tasks-portlet/docroot/WEB-INF/src/com/tour/portlet/tasks/messaging/impl/SetupMessagingImpl.java). 
It's in the package `com.tour.portlet.tasks.messaging.impl`. 

The listener class executes the following steps: 

1. Implements the `receive(Message message)` method of the
   `com.liferay.portal.kernel.messaging.MessageListener` interface. 

2. Extracts values from the `Message` parameter by getting values associated
   with known keys. For example, this example gets the `"name"` key that was 
   created by the sender. 
   
        String name = (String) message.get("name");

3. Creates and sends a response `Message` object based on the message received 
   via the `MessageBusUtil.createResponseMessage(message)` method. This method 
   accesses the response destination name from the `message` variable and sets 
   the destination of the response message. The `setPayload` method sets the 
   response message's payload. In this example, the payload is set to 
   `"RECEIVED"`, which is in turn used by the original sender to display a 
   success message. 
   
        Message responseMessage = MessageBusUtil.createResponseMessage(message);
        ```
        responseMessage.setPayload("RECEIVED");

4. Sends the response `Message` to the response destination. 

        MessageBusUtil.sendMessage(responseMessage.getDestinationName(), responseMessage);

Also be sure to add the following imports to your message listener file: 

    import com.liferay.portal.kernel.messaging.Message;
    import com.liferay.portal.kernel.messaging.MessageBusUtil;
    import com.liferay.portal.kernel.messaging.MessageListener;

Now you have both a sender and a listener implemented for your messages! There's 
just one more thing to take care of before you're done. 

## Configuring Message Bus 

For Message Bus to successfully direct messages from destinations to listeners, 
you must register the listeners by configuring the appropriate mappings in your 
plugin's `WEB-INF/src/META-INF/messaging-spring.xml` file. 

---

 ![Warning](../../images/tip.png) **Warning:** You should only do this *after* 
implementing any senders and listeners you have. Tools like Liferay IDE and 
Liferay Developer Studio try to deploy plugins automatically as you save 
changes. If you declare sender or listener classes in the configuration file 
that don't exist yet, your plugin will break. 

---

Create the `WEB-INF/src/META-INF/messaging-spring.xml` file if it's not already 
in your plugin. For example, here's the configuration for the custom Tasks and 
Setup portlets: 

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

	
        <!-- Destinations -->

        <bean id="tour.roadie.setup" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
		    <property name="name" value="tour/roadie/setup" />
	    </bean>

        <bean id="tour.manager.task" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
		    <property name="name" value="tour/manager/task" />
	    </bean>


        <!-- Configurator -->

	    <bean id="messagingConfigurator" class="com.liferay.portal.kernel.messaging.config.PluginMessagingConfigurator">
		    <property name="messageListeners">
			    <map key-type="java.lang.String" value-type="java.util.List">
				    <entry key="tour/roadie/setup">
					    <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
						    <ref bean="messageListener.setup_listener" />
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

    ```
    <listener>
      <listener-class>com.liferay.portal.kernel.spring.context.PortletContextLoaderListener</listener-class>
    </listener>

    <context-param>
      <param-name>portalContextConfigLocation</param-name>
      <param-value>/WEB-INF/classes/META-INF/messaging-spring.xml</param-value>
    </context-param>
    ```

Save and redeploy your portlet. Your plugin should now send and receive messages 
as you've configured it to. In the case of the tour manager, the Tasks portlet 
now displays the success message when a setup task is added.

![Figure 2: The response to the synchronous message is successful!](../../images/msg-bus-synch-tasks.png)

Likewise, the roadies' Setup portlet now gets any new tasks added by the road 
manager. 

![Figure 3: The task was created in the roadies' Setup portlet.](../../images/msg-bus-synch-setup.png)

Congratulations! You've successfully made your first trip on the Message Bus! 

## Related Topics 

[Service Builder and Services](/develop/tutorials/-/knowledge_base/service-builder-lp-6-2-develop-tutorial)

<!--

[Asynchronous Messaging with Callbacks](http://www.liferay.com/)

[Asynchronous Send and Forget Messaging](http://www.liferay.com/)
-->
