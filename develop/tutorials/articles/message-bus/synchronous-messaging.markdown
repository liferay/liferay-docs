### Synchronous Messaging [](id=synchronous-messaging-liferay-portal-6-2-dev-guide-06-en)

In our example, equipment purchases can't proceed without approval from Finance
and Legal departments. Since special offers from the manufacturers often only
last for a couple hours, Procurement makes it their top priority to get approval
as soon as possible. Implementing their exchange using *synchronous* messaging
makes the most sense. 

![Figure 7.8: Synchronous messaging](../../images/msg-bus-sync-msg.png)

The following table describes how we'll set things up: 

 Destination Key | Type  | Sender | Receivers                                 |
---------------- | ----- | ------ | ----------------------------------------- |
 `jungle/finance/purchase`          | synchronous | Procurement | Finance |
 `jungle/finance/purchase/response` | synchronous | Finance | Procurement |
 `jungle/legal/purchase`            | synchronous | Procurement | Legal   |
 `jungle/legal/purchase/response`   | synchronous | Legal | Procurement   |

We've set it up so Finance sends its response messages to a destination on which
Procurement will listen. That way a full-bodied response message is sent back to
Procurement in addition to the response object returned from sending the
message. 

*The Procurement Department sends a purchase approval request:* 

<!--Where does this code go? messaging-spring.xml? If so is the code here
simply added to the file? And the code in subsequent descriptions too?-->

<!-- That's a very good question. This would generally be part of a portlet
application. This doesn't look like an actual, working demo, and I think we need
to talk to Jim about it. -Rich --> 

    Message message = new Message();
    message.put("department", "Procurement");
    message.put("partName", part.getName(Locale.US));

    message.setResponseId("1111");
    message.setResponseDestinationName("jungle/finance/purchase/response");

    try {
        String financeResponse = (String) MessageBusUtil.sendSynchronousMessage(
            "jungle/finance/purchase", message, 10000);

        System.out.println(
            "Procurement received Finance sync response to purchase approval for " +
            part.getName(Locale.US) + ": " + financeResponse);

        message.setResponseId("2222");
        message.setResponseDestinationName("jungle/legal/purchase/response");

        String legalResponse = (String) MessageBusUtil.sendSynchronousMessage(
            "jungle/legal/purchase", message, 10000);

        System.out.println(
            "Procurement received Legal sync response to purchase approval for " +
            part.getName(Locale.US) + ": " + legalResponse);

        if (financeResponse.contains("yes") && legalResponse.contains("yes")) {
            sendPurchaseNotification(part, userId);
        }
    }
    catch (MessageBusException e) {
        e.printStackTrace();
    }

This sender takes the following steps: 

1. Creates the message using Liferay's `Message` class.

2. Stuffs the message with key/value pairs.

3. Sets a response ID and response destination for listeners to use in replying
   back.

4. Sends the message to the destination with a timeout value of 10,000
   milliseconds.

5. Blocks waiting for the response.

*Finance Department listens for purchase approval requests and replies back:* 

    public class FinanceMessagingImpl implements MessageListener {

        public void receive(Message message) {
            try {
                doReceive(message);
            }
            catch (Exception e) {
                _log.error("Unable to process message " + message, e);
            }
        }

        protected void doReceive(Message message)
            throws Exception {

            String department = (String) message.get("department");
            String partName = (String) message.get("partName");

            System.out.println("Finance received purchase request for " +
                partName + " from " + department);

            Message responseMessage = MessageBusUtil.createResponseMessage(
                message);

            responseMessage.put("department", "Finance");
            responseMessage.put("partName", partName);
            responseMessage.setPayload("yes");

            MessageBusUtil.sendMessage(
                responseMessage.getDestinationName(), responseMessage);
        }

        private static Log _log =
            LogFactoryUtil.getLog(FinanceMessagingImpl.class);
    }

This listener executes the following steps: 

1. Implements the `receive(Message message)` method of the
   `com.liferay.portal.kernel.messaging.MessageListener` interface. 

2. Extracts values from the `Message` parameter by getting values associated
   with known keys. 

3. Creates a `Message` based on the message received via the
   `MessageBusUtil.createResponseMessage(message)` method, which accesses the
   response destination name from the `message` variable and sets the destination
   of the response message. 

4. Sets the response message's payload. 

5. Sends the response `Message` to the response destination.

You can implement the listener for the Legal Department similarly. Next we'll
account for Legal Department-related classes in our configuration.

*Message Bus Configuration for the purchase approval request process:*

For Message Bus to direct messages from destinations to listeners, we must
register the listeners by configuring the appropriate mappings in our plugin's
`WEB-INF/src/META-INF/messaging-spring.xml` file (create this file if it's not
already in your plugin). Here is the configuration: 

<!--If the messaging-spring file potentially hasn't been created yet where has
the reader been putting the code snippets from above? Should this statement go
before the first code snippet?--> 

<!-- The above snippets go in the service layer. If you follow the message bus
section in Liferay in Action, you'll see that the Java stuff goes in the service
layer's -Impl class. It's generally a best practice to write the Java classes
and then write the configuration file, because tools like Liferay IDE and
Liferay Developer Studio try to deploy the plugin right away, and if you declare
classes in the configuration file that don't exist yet, the whole plugin blows
up. -Rich -->

    <?xml version="1.0"?>

    <beans
        default-destroy-method="destroy"
        default-init-method="afterPropertiesSet"
        xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
    >

        <!-- Listeners -->

        <bean
            id="messageListener.finance_listener"
            class="com.liferay.training.parts.messaging.impl.FinanceMessagingImpl"
        /> 
        <bean
            id="messageListener.legal_listener"
            class="com.liferay.training.parts.messaging.impl.LegalMessagingImpl"
        />
        <bean
            id="messageListener.procurement_listener"
            class="com.liferay.training.parts.messaging.impl.ProcurementMessagingImpl"
        />

        <!-- Destinations -->

        <bean
            id="destination.finance.purchase"
            class="com.liferay.portal.kernel.messaging.SynchronousDestination"
        >
            <property name="name" value="jungle/finance/purchase" />
        </bean>

        <bean
            id="destination.finance.purchase.response"
            class="com.liferay.portal.kernel.messaging.SynchronousDestination"
        >
            <property name="name" value="jungle/finance/purchase/response" />
        </bean>

        <bean
            id="destination.legal.purchase"
            class="com.liferay.portal.kernel.messaging.SynchronousDestination"
        >
            <property name="name" value="jungle/legal/purchase" />
        </bean>

        <bean
            id="destination.legal.purchase.response"
            class="com.liferay.portal.kernel.messaging.SynchronousDestination"
        >
            <property name="name" value="jungle/legal/purchase/response" />
        </bean>

        <!-- Configurator -->

        <bean
            id="messagingConfigurator"
            class=
                "com.liferay.portal.kernel.messaging.config.PluginMessagingConfigurator"
        >
            <property name="messageListeners">
                <map key-type="java.lang.String" value-type="java.util.List">
                    <entry key="jungle/finance/purchase">
                        <list
                            value-type=
                                "com.liferay.portal.kernel.messaging.MessageListener"
                        >
                            <ref bean="messageListener.finance_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/finance/purchase/response">
                        <list
                            value-type=
                                "com.liferay.portal.kernel.messaging.MessageListener"
                        >
                            <ref bean="messageListener.procurement_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/legal/purchase">
                        <list
                            value-type=
                                "com.liferay.portal.kernel.messaging.MessageListener"
                        >
                            <ref bean="messageListener.legal_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/legal/purchase/response">
                        <list
                            value-type=
                                "com.liferay.portal.kernel.messaging.MessageListener"
                        >
                            <ref bean="messageListener.procurement_listener" />
                        </list>
                    </entry>
                </map>
            </property>
            <property name="destinations">
                <list>
                    <ref bean="destination.finance.purchase"/>
                    <ref bean="destination.finance.purchase.response"/>
                    <ref bean="destination.legal.purchase"/>
                    <ref bean="destination.legal.purchase.response"/>
                </list>
            </property>
        </bean>
    </beans>

The configuration above specifies the following beans: 

- *Listener beans*: Specify classes to handle messages.
- *Destination beans*: Specify the class *type* and *key* names of the
   destinations.
- *Configurator bean*: Maps listeners to their destinations.

When Finance sends its purchase approval request message for a new three-story
spiral slide, the console reports Finance's receipt of the message,
Procurement's receipt of the callback response from Finance, and Procurement's
receipt of the synchronous response returned from sending the message. Here's
what the console message looks like: 

    Finance received purchase request for three-story spiral slide from Procurement
    Procurement received Finance callback response to purchase approval for three-
    story spiral slide: yes
    Procurement received Finance sync response to purchase approval for three-story 
    spiral slide: yes
    Legal received purchase request for three-story spiral slide from Procurement
    Procurement received Legal callback response to purchase approval for three-
    story spiral slide: yes
    Procurement received Legal sync response to purchase approval for three-story 
    spiral slide: yes

Do you know what all those *yes* messages mean? Success! Jungle Gyms R-Us has
the cash to purchase this cool new slide, and the Legal Department has no gripes
about the slide's safety ratings! 

Next let's have Procurement notify the Sales and Warehouse departments and
solicit their feedback.
