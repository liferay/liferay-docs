---
header-id: creating-a-destination
---

# Creating a Destination

[TOC levels=1-4]

[Message Bus destinations](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus#messaging-destinations) 
are based on destination configurations and registered as OSGi services. Message 
Bus detects the destination services and manages their associated destinations. 

Here are the steps for creating a destination. The example configurator class
that follows demonstrates these steps.

1.  Create an `activate(BundleContext)` method in your component. Then create a 
    [`BundleContext`](https://osgi.org/javadoc/r4v43/core/org/osgi/framework/BundleContext.html) 
    instance variable and set it to the `activate` method's `BundleContext`: 

    ```java
    @Activate
    protected void activate(BundleContext bundleContext) {

        _bundleContext = bundleContext;

    }

    private final BundleContext _bundleContext;
    ```

    You'll create and register your destination inside this `activate` method. 
    This ensures that the destination is available upon service activation. Once 
    the destination is registered, Message Bus detects its service and manages
    the destination. 

2.  Create a destination configuration by using one of 
    [`DestinationConfiguration`'s](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html) 
    static `create*` methods or its constructor. Set any attributes that apply 
    to the destinations you'll create with the destination configuration. 

    For example, this code uses the `DestinationConfiguration` constructor to 
    create a destination configuration for parallel destinations. It then sets 
    the destination configuration's maximum queue size and 
    [`RejectedExecutionHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/concurrent/RejectedExecutionHandler.html): 

    ```java
    @Activate
    protected void activate(BundleContext bundleContext) {
        ...

        // Create a DestinationConfiguration for parallel destinations.

        DestinationConfiguration destinationConfiguration =
            new DestinationConfiguration(
                DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
                    "myDestinationName");

        // Set the DestinationConfiguration's max queue size and
        // rejected execution handler.

        destinationConfiguration.setMaximumQueueSize(_MAXIMUM_QUEUE_SIZE);

        RejectedExecutionHandler rejectedExecutionHandler =
            new CallerRunsPolicy() {

                @Override
                public void rejectedExecution(
                    Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

                    if (_log.isWarnEnabled()) {
                        _log.warn(
                            "The current thread will handle the request " +
                                "because the graph walker's task queue is at " +
                                    "its maximum capacity");
                    }

                    super.rejectedExecution(runnable, threadPoolExecutor);
                }

        };

        destinationConfiguration.setRejectedExecutionHandler(
            rejectedExecutionHandler);

    }
    ```

3.  Create the destination by invoking the 
    [`DestinationFactory`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationFactory.html)
    method `createDestination(DestinationConfiguration)`, passing in the 
    destination configuration from the previous step. 

    For example, this code does so via a `DestinationFactory` reference: 

    ```java
    @Activate
    protected void activate(BundleContext bundleContext) {
        ...

        Destination destination = _destinationFactory.createDestination(
            destinationConfiguration);

    }
    ...

    @Reference
    private DestinationFactory _destinationFactory;
    ```

4.  Register the destination as an OSGi service by invoking the `BundleContext` 
    method `registerService` with these parameters: 

    -   The destination class `Destination.class`.
    -   Your `Destination` object.
    -   A `Dictionary` of properties defining the destination, including the 
        `destination.name`. 

    ```java
    @Activate
    protected void activate(BundleContext bundleContext) {
        ...

        Dictionary<String, Object> properties = new HashMapDictionary<>();

        properties.put("destination.name", destination.getName());

        ServiceRegistration<Destination> serviceRegistration =
            _bundleContext.registerService(
                Destination.class, destination, properties);
    }
    ```

5.  Manage the destination object and service registration resources using a
    collection such as a `Map<String, ServiceRegistration<Destination>>`.
    Keeping references to these resources is helpful for when you're ready to
    unregister and destroy them. 

    ```java
    @Activate
    protected void activate(BundleContext bundleContext) {
        ...

        _serviceRegistrations.put(destination.getName(), 
            serviceRegistration);

    }
    ...

    private final Map<String, ServiceRegistration<Destination>>
        _serviceRegistrations = new HashMap<>();
    ```

6.  Add a `deactivate` method that unregisters and destroys any destinations for 
    this component. This ensures there aren't any active destinations for this 
    component when the service deactivates: 

    ```java
    @Deactivate
    protected void deactivate() {

        // Unregister and destroy destinations

        for (ServiceRegistration<Destination> serviceRegistration : 
            _serviceRegistrations.values()) {

            Destination destination = _bundleContext.getService(
                serviceRegistration.getReference());

            serviceRegistration.unregister();

            destination.destroy();

        }

        _serviceRegistrations.clear();

    }
    ```

Here's the full messaging configurator component class that contains the code in 
the above steps: 

```java
@Component (
    immediate = true,
    service = MyMessagingConfigurator.class
)
public class MyMessagingConfigurator {

    @Activate
    protected void activate(BundleContext bundleContext) {

        _bundleContext = bundleContext;

        // Create a DestinationConfiguration for parallel destinations.

        DestinationConfiguration destinationConfiguration =
            new DestinationConfiguration(
                DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
                    "myDestinationName");

        // Set the DestinationConfiguration's max queue size and
        // rejected execution handler.

        destinationConfiguration.setMaximumQueueSize(_MAXIMUM_QUEUE_SIZE);

        RejectedExecutionHandler rejectedExecutionHandler =
            new CallerRunsPolicy() {

                @Override
                public void rejectedExecution(
                    Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

                    if (_log.isWarnEnabled()) {
                        _log.warn(
                            "The current thread will handle the request " +
                                "because the graph walker's task queue is at " +
                                    "its maximum capacity");
                    }

                    super.rejectedExecution(runnable, threadPoolExecutor);
                }

            };

        destinationConfiguration.setRejectedExecutionHandler(
            rejectedExecutionHandler);

        // Create the destination

        Destination destination = _destinationFactory.createDestination(
            destinationConfiguration);

        // Add the destination to the OSGi service registry

        Dictionary<String, Object> properties = new HashMapDictionary<>();

        properties.put("destination.name", destination.getName());

        ServiceRegistration<Destination> serviceRegistration =
            _bundleContext.registerService(
                Destination.class, destination, properties);

        // Track references to the destination service registrations 

        _serviceRegistrations.put(destination.getName(),    
            serviceRegistration);
    }

    @Deactivate
    protected void deactivate() {

        // Unregister and destroy destinations this component unregistered

        for (ServiceRegistration<Destination> serviceRegistration : 
            _serviceRegistrations.values()) {

            Destination destination = _bundleContext.getService(
                serviceRegistration.getReference());

            serviceRegistration.unregister();

            destination.destroy();

        }

        _serviceRegistrations.clear();

    }

    private final BundleContext _bundleContext;

    @Reference
    private DestinationFactory _destinationFactory;

    private final Map<String, ServiceRegistration<Destination>>
        _serviceRegistrations = new HashMap<>();
}
```

## Related Topics

[Message Bus Destinations](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus#messaging-destinations)
