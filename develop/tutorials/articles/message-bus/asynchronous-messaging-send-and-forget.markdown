# Asynchronous "Send and Forget" Messaging 

Asynchronous messaging occurs when the sender sends a message and then continues 
processing. In the *send and forget* model of asynchronous messaging, the 
sender's messages don't contain any response information. Message listeners 
therefore can't reply to them. Think of it this way--if you got a letter in the 
mail without any kind of return address or other information telling you who 
sent it, who could you send a reply to? This tutorial shows you how to implement 
messaging in this fashion between one sending and two receiving portlets. You 
can find the code for this example plugin project [here on Github](https://github.com/ngaskill/liferay-docs/tree/message-bus-tutorials/develop/tutorials/code/msg-bus/async-send-forget/insults-portlet).

Even though there are many cases where you want to include some sort of response 
information in your messages, there are times where leaving out a response 
location is preferable. Such is the case with a prolific yet distinguished 
writer of insults. An insult writer *definitely* doesn't want a reply from 
anyone on the receiving end of their insults. This insult writer has three 
portlets--one for writing the insults, one for logging them, and one for 
displaying them to the insulted. The Message Bus is used to send an insult to 
the logging and insulted portlets as soon as the insult writer writes it. It 
also makes sense for each message to be dispatched in parallel instead of in 
series. This way, both messages go out at the same time. Without further ado, 
it's time to load up the Message Bus with insults!

![Figure 1: Asynchronous messaging with *parallel* dispatching](../../images/msg-bus-async-parallel-msg.png)

## Deciding on Destination Keys 

As with other types of messaging in the Message Bus, the first thing you need to 
do is decide where you're going to send the message. You do this by using 
destination keys. Destination keys serve as the specific locations where 
messages are sent. You can think of them as the mailing addresses of the Message 
Bus system. The destination keys need to be included with the message and 
registered as destinations in `WEB-INF/src/META-INF/messaging-spring.xml`. In 
this example there's just one destination key--`insults/users`. Both of the 
receiving portlets are configured to listen on this destination key and no 
response is required. 

Now that you know what your destination keys are, you can use them when writing 
the code that sends the messages. 

## Implementing the Message Sender

Writing the message sender is a fairly straightforward task. Simply place your 
message sending code in the method of your application that you want it to be 
called with. There's also not much code involved. In this example, the message 
sender is placed inside the method of the insult writer portlet that is 
responsible for adding a new insult to the database. Therefore a message is sent 
out each time a new insult is written, which is precisely what the insult writer 
wants to happen. You can find this code in `InsultWriterPortlet.java` [here on on Github](https://github.com/ngaskill/liferay-docs/blob/message-bus-tutorials/develop/tutorials/code/msg-bus/async-send-forget/insults-portlet/docroot/WEB-INF/src/com/insults/portlet/insults/InsultWriterPortlet.java).

A sender for an asynchronous send and forget message takes the following steps:

1. Creates a `JSONObject` to serve as the message:

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

2. Uses the `put` method to stuff the message with key/value pairs. In this 
   example, some key/value pairs of an insult are added:
   
        jsonObject.put("insult", insult);
        jsonObject.put("rating", rating);

3. Sends the message to the destination:

        MessageBusUtil.sendMessage("insults/users", jsonObject.toString());
        
You also need to be sure that you add the following imports:

        import com.liferay.portal.kernel.messaging.MessageBusUtil;
        import com.liferay.portal.kernel.json.JSONFactoryUtil;
        import com.liferay.portal.kernel.json.JSONObject;

Now that you've implemented your sender, you can implement any listeners that 
you need.

## Implementing Message Listeners

You need to have one or more message listeners implemented to receive messages 
from your sender. Each listener is a class that implements Liferay's 
`MessageListener` interface. In this example there are two listeners, one for 
each receiving portlet. You can find the example listeners [here on Github](https://github.com/ngaskill/liferay-docs/tree/message-bus-tutorials/develop/tutorials/code/msg-bus/async-send-forget/insults-portlet/docroot/WEB-INF/src/com/insults/portlet/insults/messaging/impl).

Asynchronous listeners for send and forget messages take the following steps: 

1. Implements the `receive(Message message)` method of the
   `com.liferay.portal.kernel.messaging.MessageListener` interface.

2. Gets the message payload and cast it to a `String`.

        String payload = (String)message.getPayload();

3. Creates a `JSONObject` from the payload string.

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

4. Gets values from the `JSONObject` using its getter methods. This example gets 
   the values that were added by the sender.

        String insult = (String) jsonObject.getString("insult");
        String rating = (String) jsonObject.getString("rating");

Make sure that you add the following imports to your listener classes:

        import com.liferay.portal.kernel.messaging.Message;
        import com.liferay.portal.kernel.messaging.MessageListener;
        import com.liferay.portal.kernel.json.JSONObject;
        import com.liferay.portal.kernel.json.JSONFactoryUtil;
        
Any other listeners you need can be implemented using the same steps. Next, 
you'll configure your listeners and destinations for use with the Message Bus. 

## Configuring Message Bus 

Now that you've implemented your message senders and listeners, you need to 
configure them in your plugin's `WEB-INF/src/META-INF/messaging-spring.xml` 
file. Create this file if it doesn't yet exist. 

---

 ![Warning](../../images/tip.png) **Warning:** You should only do this *after* 
implementing any senders and listeners you have. Tools like Liferay IDE and 
Liferay Developer Studio try to deploy plugins automatically as you save 
changes. If your sender or listener classes don't exist and you declare them in 
`messaging-spring.xml`, your plugin will break. 

---

For example, here's the configuration file for the insult writer, insult log, 
and insulted portlets: 

    <?xml version="1.0"?>

    <beans
	    default-destroy-method="destroy"
	    default-init-method="afterPropertiesSet"
	    xmlns="http://www.springframework.org/schema/beans"
	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
    >

	<!-- Listeners -->
	
	<bean id="messageListener.insultlog_listener" class="com.insults.portlet.insults.messaging.impl.InsultLogMessagingImpl" />
	<bean id="messageListener.insulted_listener" class="com.insults.portlet.insults.messaging.impl.InsultedMessagingImpl" />
	
	<!-- Destinations -->

    <bean id="insults.users" class="com.liferay.portal.kernel.messaging.ParallelDestination">
		<property name="name" value="insults/users" />
	</bean>
	
    <!-- Configurator -->

	<bean id="messagingConfigurator" class="com.liferay.portal.kernel.messaging.config.PluginMessagingConfigurator">
		<property name="messageListeners">
			<map key-type="java.lang.String" value-type="java.util.List">
				<entry key="insults/users">
					<list value-type="com.liferay.portal.kernel.messaging.MessageListener">
						<ref bean="messageListener.insultlog_listener" /> 
						<ref bean="messageListener.insulted_listener" />
					</list>
				</entry>
			</map>
		</property>
        <property name="destinations">
            <list>
                <ref bean="insults.users"/>
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
as you've configured it to.	In the case of the insult writer, the insult log 
and insulted portlets now show each insult. 

![Figure 2: Message Bus carries the insult to the receiving portlets.](../../images/msg-bus-insults.png)

Great! Now you know how to use Message Bus to send asynchronous send and forget 
messages. 

## Related Topics

[Service Builder and Services](/develop/tutorials/-/knowledge_base/service-builder-lp-6-2-develop-tutorial)
