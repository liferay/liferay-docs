# Understanding the Service Builder-generated Code [](id=understanding-the-service-builder-generated-code)

Now let's examine the files Service Builder generated for your Event entity.
Note that the files listed under Local Service and Remote Service below are only
generated for an entity that has both `local-service` and `remote-service`
attributes set to `true`. Service Builder generates services for these entities
in two locations in your project. These locations use the package path that you
specified in your `service.xml` file:

- `docroot/WEB-INF/service/com/nosester/portlet/eventlisting`
- `docroot/WEB-INF/src/com/nosester/portlet/eventlisting`

The `docroot/WEB-INF/service/com/nosester/portlet/eventlisting/` package
contains utility classes and interfaces for the Event Listing project. All the
classes and interfaces in the service folder are packaged in a `.jar` file
called `event-listing-project-portlet-service.jar`, in your
`docroot/WEB-INF/lib` folder. This `.jar` file is generated whenever you run
Service Builder. It's possible to place this `.jar` file on your application
server's global classpath to make your project's services available to other
projects. This allows a portlet in different project, for example, to create,
update, and delete Events and Locations. Of course, you should consider the
security implications of placing your project's service `.jar` file on your
application server's global classpath: do you *really* want to allow other
plugins to access your project's services? 

The `docroot/WEB-INF/src/com/nosester/portlet/eventlisting` package contains the
implementation of the interfaces defined in the
`docroot/WEB-INF/service/com/nosester/portlet/eventlisting` package. It belongs
to the Event Listing project's classpath but is not available outside the Event
Listing project. Service Builder generates classes and interfaces belonging to
the persistence layer, service layer, and model layer in the
`docroot/WEB-INF/service/com/nosester/portlet/eventlisting` and
`docroot/WEB-INF/src/com/nosester/portlet/eventlisting` packages. Let’s look at
the classes and interfaces generated for Events. The ones generated for
Locations are similar. You won't have to customize more than three of these
classes for each entity: `-LocalServiceImpl`, `-ServiceImpl`, and `-ModelImpl`.

-   Persistence
    - `EventPersistence`: Event persistence interface that defines CRUD methods
      for the Event entity such as `create`, `remove`, `countAll`, `find`,
      `findAll`, etc. 
    - `EventPersistenceImpl`: Event persistence implementation class that
      implements `EventPersistence`. 
    - `EventUtil`: Event persistence utility class that wraps
      `EventPersistenceImpl` and provides direct access to the database for CRUD
      operations. This utility should only be used by the service layer; in your
      portlet classes, use `EventLocalServiceUtil` or `EventServiceUtil`
      instead. 

    ![Figure 4.6: Service Builder generates these persistence classes and interfaces. You shouldn't (and won't need to) customize any of these classes or interfaces.](../../images/service-builder-persistence-diagram.uml.png)

-   Local Service (only generated for an entity if an entity's `local-service`
    attribute is set to `true` in `service.xml`)
    - `EventLocalService`: Event local service interface. 
    - `EventLocalServiceImpl` (**LOCAL SERVICE IMPLEMENTATION**): Event local
      service implementation. This is the only class in the local service
      that you should modify manually. You can add custom business logic here.
      For any custom methods added here, Service Builder adds corresponding
      methods to the `EventLocalService` interface the next time you run it.
    - `EventLocalServiceBaseImpl`: Event local service base implementation. This
      is an abstract class. Service Builder injects a number of instances of
      various service and persistence classes into this class. 
      `@abstract`
    - `EventLocalServiceUtil`: Event local service utility class which wraps
      `EventLocalServiceImpl` and serves as the primary local access point to
      the service layer. 
    - `EventLocalServiceWrapper`: Event local service wrapper which implements
      `EventLocalService`. This class is designed to be extended and it allows
      developers to customize the local Event services. Customizing services
      should be done via a hook plugin.  

    ![Figure 4.7: Service Builder generates these service classes and interfaces. Only EventLocalServiceImpl allows custom methods to be added to the service layer.](../../images/service-builder-service-diagram.uml.png)

-   Remote Service (only generated for an entity if an entity's `remote-service`
    attribute is set to `true` in `service.xml`)
    - `EventService`: Event remote service interface. 
    - `EventServiceImpl` (**REMOTE SERVICE IMPLEMENTATION**): Event remote
      service implementation. This is the only class in the remote service
      that you should modify manually. Here, you can write code that adds
      additional security checks and invokes the local services. For any custom
      methods added here, Service Builder adds corresponding methods to the
      `EventService` interface the next time you run it. 
    - `EventServiceBaseImpl`: Event remote service base implementation. This is
      an abstract class.  `@abstract`
    - `EventServiceUtil`: Event remote service utility class which wraps
      `EventServiceImpl` and serves as the primary remote access point to the
      service layer. 
    - `EventServiceWrapper`: Event remote service wrapper which implements
      `EventService`. This class is designed to be extended and it allows
      developers to customize the remote Event services.  Customizing services
      should be done in a hook plugin. `EventServiceImpl` 
    - `EventServiceSoap`: Event SOAP utility which the remote `EventServiceUtil`
      remote service utility can access. `EventServiceUtil` 
    - `EventSoap`: Event SOAP model, similar to `EventModelImpl`. `EventSoap` is
      serializable; it does not implement `Event`. 

-   Model
    - `EventModel`: Event base model interface. This interface and its
      `EventModelImpl` implementation serve only as a container for the default
      property accessors generated by Service Builder. Any helper methods and
      all application logic should be added to `EventImpl`. 
    - `EventModelImpl`: Event base model implementation. 
    - `Event`: Event model interface which extends `EventModel`. 
    - `EventImpl`:  (**MODEL IMPLEMENTATION**)Event model implementation. You
      can use this class to add helper methods and application logic to your
      model. If you don't add any helper methods or application logic, only the
      auto-generated field getters and setters are available. Whenever you
      add custom methods to this class, Service Builder adds corresponding
      methods to the `Event` interface the next time you run it.
    - `EventWrapper`: Event wrapper, wraps `Event`. 

    ![Figure 4.8: Service Builder generates these model classes and interfaces. Only `EventImpl` allows custom methods to be added to the service layer.](../../images/service-builder-model-diagram.uml.png)

Each file that Service Builder generates is assembled from an associated
Freemarker template. You can find Service Builder's Freemarker templates in the
`com.liferay.portal.tools.servicebuilder.dependencies` package of Liferay's
`portal-impl/src` folder. For example, if you want to find out how a
`-ServiceImpl.java` file is generated, just look at the `service_impl.ftl`
template.

Of all the classes generated by Service Builder, only three should be
manually modified: `EventLocalServiceImpl`, `EventServiceImpl` and `EventImpl`.
If you manually modify the other classes, your changes will be overwritten the
next time you run Service Builder. 

Next, let's learn how to call core Liferay services. Calling core Liferay
services in your portlet is just as easy as calling your project's services that
you generated via Service Builder. 

<!-- Consider removing or moving the section below. - Jim -->
