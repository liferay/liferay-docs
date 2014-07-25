### Asynchronous "Send and Forget" [](id=asynchronous-send-and-forget-liferay-portal-6-2-dev-guide-06-en)

In the *send and forget* model, the sender sends messages and continues
processing. We'll apply this behavior to Jungle Gym's company-wide new product
notification.

Procurement isn't expecting response messages from individual employees, so
there's no need for the company-wide listener to package up responses. We do,
however, want everyone to get product news at the *same time*, so instead of
dispatching news to employees *serially* we'll dispatch *in parallel*.

![Figure 7.10: Asynchronous messaging with *parallel* dispatching](../../images/msg-bus-async-parallel-msg.png)

We'll specify a parallel destination type in our `messaging-spring.xml`:

*Destination bean*:

    <bean
        id="destination.employee.news"
        class="com.liferay.portal.kernel.messaging.ParallelDestination"
    >
            <property name="name" value="jungle/employee/news" />
    </bean>

*Listener bean*:

    <bean
        id="messageListener.employee_listener"
        class="com.liferay.training.parts.messaging.impl.EmployeeMessagingImpl"
    />

*Configuration bean listener map entry*: 

    <entry key="jungle/employee/news">
        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
            <ref bean="messageListener.employee_listener" />
        </list>
    </entry>

*Configuration bean destination list reference*: 

    <ref bean="destination.employee.news"/>

Congratulations! You implemented inter-departmental communications for the
procurement process at Jungle Gyms R-Us.

Along the way you used Message Bus to implement the following:

- Sender, listener, and destination components. 
- Synchronous and Asynchronous messaging schemes. 
- *Serial* and *parallel* message dispatching. 
- Java and JSON message types. 
