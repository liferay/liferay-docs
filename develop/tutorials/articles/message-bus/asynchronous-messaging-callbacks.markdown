### Asynchronous Messaging with Callbacks [](id=asynchronous-messaging-with-callbacks-liferay-portal-6-2-dev-guide-06-en)

Asynchronous messaging consists of sending a message and then continuing with
processing without blocking waiting for an immediate response. This allows the
sender to continue with other tasks. It's often necessary, however, that the
listener can respond to the sender. This can be done using a call-back.

Jungle Gyms R-Us's Procurement Department must notify the Sales and Warehouse
departments of incoming equipment while simultaneously soliciting their
feedback. To assure all three departments are up to speed, any responses from
the Sales or Warehouse departments are posted to a shared destination. 

The following table describes how we'll set things up: 

| Destination Key            | Type  | Sender | Receivers                     |
---------------------------- | ----- | ------ | ----------------------------- |
  `jungle/purchase`          | async serial | Procurement |  Sales, Warehouse |
  `jungle/purchase/response` | synchronous  | Sales, Warehouse | Procurement  |

The following image shows asynchronous messaging, with serial dispatching of
messages: 

![Figure 7.9: Asynchronous messaging with *serial* dispatching](../../images/msg-bus-async-serial-msg.png)

Let's package the message as a `JSONObject` and send it to the destination: 

    JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

    jsonObject.put("department", "Procurement");
    jsonObject.put("partName", part.getName(Locale.US));
    jsonObject.put("responseDestinationName", "jungle/purchase/response");

    MessageBusUtil.sendMessage("jungle/purchase", jsonObject.toString());

Here's how the Warehouse Department listens for and handles messages: 

    public void receive(Message message) {

        try {
            doReceive(message);
        }
        catch (Exception e)
        {
            _log.error("Unable to process message " + message, e);
        }
    }

    protected void doReceive(Message message)
        throws Exception {

        String payload = (String)message.getPayload();

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

        String department = jsonObject.getString("department");
        String partName = jsonObject.getString("partName");
        String responseDestinationName = jsonObject.getString(
            "responseDestinationName");

        System.out.println("Warehouse received purchase notification for " +
            partName + " from " + department);

        jsonObject = JSONFactoryUtil.createJSONObject();

        jsonObject.put("department", "Warehouse");
        jsonObject.put("partName", partName);
        jsonObject.put("comment", "Ugh! We're running out of space!!");

        MessageBusUtil.sendMessage(
            responseDestinationName, jsonObject.toString());
    }

Here's how this listener deserializes the `JSONObject` from the message: 

1. Gets the message payload and casts it to a `String`. 
2. Creates a `JSONObject` from the payload string. 
3. Gets values from the `JSONObject` using its getter methods. 

The class also demonstrates how the Warehouse Department packages a response
message and sends it back to the Procurement Department, using these steps: 

1. Create a `JSONObject`.  
2. Stuff it with name-value pairs. 
3. Send the response message to the original message's response destination. 

The Sales department listener can be implemented the same way, substituting
*Sales* as the department value; the comment would likely be different, too. 

You just used the `JSONObject` message type to send an asynchronous response
message using a call-back. 

Remember, we want the Procurement, Sales, and Warehouse departments to be
aware of any message regarding the new playground equipment purchasing process.
Let's leverage our destination keys and department names in handling shared
responses. 

Here's how the Warehouse might handle messages it receives:

    public void receive(Message message) {

        try {
            if (message.getDestinationName().equals("jungle/purchase"))
            {
                doReceive(message);
            }
            else if (
                message.getDestinationName().equals("jungle/purchase/response"))
            {
                doReceiveResponse(message);
            }
        }
        catch (Exception e)
        {
            _log.error("Unable to process message " + message, e);
        }
    }

    protected void doReceiveResponse(Message message)
        throws JSONException {

        String payload = (String)message.getPayload();

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

        String department = jsonObject.getString("department");

        if (!department.equals("Warehouse")) {
            System.out.println(
                "Warehouse is in the loop on response from " + department);
        }
    }

Let's look at `receive(Message)` for a minute. We've set it up to handle
messages differently depending on their destinations: messages to
`jungle/purchase` are handled as Procurement's purchase notifications, while
messages to `jungle/purchase/response` are treated as departmental responses to
Procurement's purchase notifications.  The `doReceiveResponse(Message)` method
performs an important task, checking that the response comes from a department
other than itself, and printing an error if it doesn't. 

Here are the configuration elements we added to the `messaging-spring.xml` from
the previous section:

*Listener beans*: 

    <bean
        id="messageListener.warehouse_listener"
        class="com.liferay.training.parts.messaging.impl.WarehouseMessagingImpl"
    />
    <bean
        id="messageListener.sales_listener"
        class="com.liferay.training.parts.messaging.impl.SalesMessagingImpl"
    />

*Destination beans*: The purchase notifications will be sent to a *serial*
  destination and the responses will be sent to a *synchronous* destination. 

    <bean
        id="destination.purchase"
        class="com.liferay.portal.kernel.messaging.SerialDestination"
    >
        <property name="name" value="jungle/purchase" />
    </bean>

    <bean
        id="destination.purchase.response"
        class="com.liferay.portal.kernel.messaging.SynchronousDestination"
    >
        <property name="name" value="jungle/purchase/response" />
    </bean>

*Configuration bean listener map entry*: Warehouse and Sales are registered
  to listen for the notifications from Procurement. All three departments are
registered to listen for inter-departmental responses.

    <entry key="jungle/purchase">
        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
            <ref bean="messageListener.warehouse_listener" />
            <ref bean="messageListener.sales_listener" />
        </list>
    </entry>
    <entry key="jungle/purchase/response">
        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
            <ref bean="messageListener.procurement_listener" />
            <ref bean="messageListener.warehouse_listener" />
            <ref bean="messageListener.sales_listener" />
        </list>
    </entry>

*Configuration bean destination list references*:

    <ref bean="destination.purchase"/>
    <ref bean="destination.purchase.response"/>

Don't forget to send news of these new products to *all* Jungle Gyms R-Us
employees.

Next, let's explore the asynchronous *send and forget* model.
