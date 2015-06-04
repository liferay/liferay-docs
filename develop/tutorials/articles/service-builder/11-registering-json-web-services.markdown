## Registering JSON Web Services [](id=registering-json-web-services)

Liferay's developers use a tool called *Service Builder* to build services. When
you build services with Service Builder, all remote-enabled services (i.e.,
`service.xml` entities with the property `remote-service="true"`) are exposed as
JSON web services. When each `*Service.java` interface is created for a
remote-enabled service, the `@JSONWebService` annotation is added on the class
level of that interface. All of the public methods of that interface become
registered and available as JSON web services. 

The `*Service.java` interface source file should never be modified by the user.
If you need more control over its methods (e.g., if you need to hide some
methods while exposing others), you can configure the `*ServiceImpl` class. When
the service implementation class (`*ServiceImpl`) is annotated with the
`@JSONWebService` annotation, the service interface is ignored and the service
implementation class is used for configuration in its place. In other words,
`@JSONWebService` annotations in the service implementation override any JSON
web service configuration in the service interface.

That's it! When you start Liferay Portal, it scans service classes for
annotations (more about scanning later). Each class that uses the
`@JSONWebService` annotation is examined and its methods become exposed via the
JSON web services API. As explained previously, the `*ServiceImpl` configuration
overrides the `*Service` interface configuration during registration.

Liferay Portal, however, does not scan all available classes for the
annotations. Instead, it only scans services. More precisely, it scans all
classes, including plugin classes, registered in the portal's application
context. All classes that are available to the `BeanLocator` are scanned.
Practically, this means that the portal scans all classes registered in its
Spring context and the Spring context of its plugins. If you use Service Builder
to build plugin services, the services are automatically registered to the
Spring context and are made available to the `BeanLocator`. Moreover, this means
that you can register *any* object in the Spring context of your plugin and the
portal scans it for remote services! We are not forcing you to use Service
Builder. We recommend using it because it easily does so many things with
regards to your remote services. 

+$$$

**Note:** Liferay's developers use *Service Builder* to expose their services
via JSON automatically. If you haven't used Service Builder before, please see
the
[Defining an Object-Relational Map with Service Builder](develop/tutorials/-/knowledge_base/6-2/defining-an-object-relational-map-with-service-builder)
tutorial.

$$$

OK, now let's see how you can register your plugin's remote services as JSON web
services. Keep in mind that Liferay's developers use this same mechanism. This
is why Liferay Portal's remote service are exposed as JSON web services
out-of-the-box. 

### Registering Plugin JSON Web Services [](id=registering-plugin-json-web-services)

Let's say you have a portlet named `SupraSurf` that has some services. And you
decide to expose them as remote services. After enabling the `remote-service`
attribute on its `SurfBoard` entity, you rebuild the services. Service Builder
regenerates the `SurfBoardService` interface, adding the `@JSONWebService`
annotation to it. This annotation tells the portal that the interface's public
methods are to be exposed as JSON web services, making them a part of the
plugin's JSON API. 

By default, scanning of the portlet's services is disabled. To enable scanning,
you need to add an appropriate filter definition in the portlet's `web.xml`
file. Fortunately, Liferay provides a way to automatically add the filter. Just
click the *Build WSDD* button in Liferay IDE while editing the `service.xml`
file in *Overview* mode, or just invoke the `build-wsdd` Ant target. On building
the WSDD, Liferay's Plugins SDK modifies the portlet's `web.xml` and enables the
JSON web services for the plugin. Under the hood, the Plugins SDK registers the
`SecureFilter` and the `JSONWebServiceServlet` for the plugin. You only need to
enable JSON web services for your plugin once.

If your portal server isn't running, start it up. Then deploy your portlet
plugin to Liferay. 

To get some feedback from the portal on registering your plugin's services,
configure the portal to log the plugin's informational messages (i.e., its `INFO
...` messages). See the section on Liferay's logging system in
[Using Liferay Portal](discover/deployment/-/knowledge_base/6-2/liferays-logging-system) for details.

To test Liferay's JSON web service registration process, add a simple method to
your plugin's services. Edit your `*ServiceImpl` class and add the following
method:

    public String helloWorld(String worldName) {
        return "Hello world: " + worldName;
    }

Rebuild the services and re-deploy your plugin. Notice that the portal prints a
message like the one below informing us that an action was configured for the
portlet. This indicates that the service method is now registered as a JSON Web
Web Service action!

    INFO  [JSONWebServiceActionsManagerImpl:117] Configured 1 actions for\
        /suprasurf-portlet

This same mechanism registers Liferay Portal's own service actions. They are
conveniently enabled by default, so you don't have to configure them. 

Next, let's learn how to form a mapped URL for the remote service so we can
access it. 

### Mapping and Naming Conventions [](id=mapping-and-naming-conventions)

You can form the mapped URL of an exposed service by following the naming
convention below: 

    http://[server]:[port]/api/jsonws/[plugin-context-name].[service-class-name]/[service-method-name]

Let's look at the last three bracketed items more closely: 

- `plugin-context-name` is the plugin's context name (e.g., `suprasurf-portlet`
  in our example). For the portal's services, the `plugin-context-name` and the
  subsequent period should be omitted. 
- `service-class-name` is generated from the service's class name in lower case,
  minus its `Service` or `ServiceImpl` suffix. For example, specify `surfboard`
  as the `plugin-context-name` for the `SurfBoardService` class. 
- `service-method-name` is generated from the service's method name by
  converting its camel case to lower case and using dashes (`-`) to separate
  words. 

We'll demonstrate these naming conventions by mapping a service method's URL
using the naming conventions both on a created plugin service and on a portal
service.

For our created service method, the URL looks like:

    http://localhost:8080/api/jsonws/suprasurf-portlet.surfboard/hello-world

Note the context name part of the URL. For the portal, it's similar. Here's a
portal service method we want to access: 

    @JSONWebService
    public interface UserService {
        public com.liferay.portal.model.User getUserById(long userId) {...}

Here is that portal service method's URL: 

    http://localhost:8080/api/jsonws/user-service/get-user-by-id

Each service method is bound to one HTTP method type. Any method with a name
starting with `get`, `is`, or `has` is assumed to be a read-only method and is
mapped as a *GET HTTP* method by default. All other methods are mapped as *POST
HTTP* methods. 

As you may have noticed, plugin services are accessed via the portal context.
Conveniently, requests sent this way can leverage the authentication credentials
associated with the user's current portal session.

Next, we'll learn to how to *list* JSON web services available from our portal. 

### Listing Available JSON Web Services [](id=listing-available-json-web-services)

To see which service methods are registered and available for use, open your
browser to the following address: 

    http://localhost:8080/api/jsonws

The API page lists the portal's registered and exposed service methods. To get
each method's details, click on the method name. You'll see the full signature
of the method, all of its arguments, and a list of exceptions that can be
thrown. For additional information about remote service methods, you can look up
the method in Liferay Portal's
[Javadocs](http://docs.liferay.com/portal/6.2/javadocs). Using a simple form
from within your browser, you can even invoke service methods. When developing
portlet services, this can be quite handy for testing.

The same API page lists remote services of plugins, too. When multiple plugins
with remote services enabled have been deployed, click on the Context Path
selector on the API page. This selector lists all of the available plugin
context paths (including the portal's path). Select a plugin's context path or
the portal's context path to list all of the remote services available within
the context path.

As discussed in the Registering JSON Web Services section, you can control
registration by using the `@JSONWebService` annotation in your `*ServiceImpl`
class. This overrides any configuration defined in the interface. You can also
control the visibility of methods using annotations at the method level. 

Let's find out how to ignore a specific method. 

### Ignoring a Method [](id=ignoring-a-method)

To keep a method from being exposed as a service, annotate the method with the
following option:

    @JSONWebService(mode = JSONWebServiceMode.IGNORE)

Methods with this annotation don't become part of the JSON Web Service API. 

Let's learn how to define custom HTTP method names and URL names. 

### HTTP Method Name and URL [](id=http-method-name-and-url)

At the method level, you can define custom HTTP method names and URL names. Just
use an annotation like this one: 

    @JSONWebService(value = "add-board-wow", method = "PUT")
    public boolean addBoard(

In this example, the plugin's service method `addBoard` is mapped to URL
method name `add-board-wow`. Its complete URL is now
`http://localhost:8080/api/jsonws/suprasurf-portlet.surfboard/add-board-wow` and
can be accessed using the HTTP PUT method. 

If the URL method name in a JSON web service annotation starts with a slash
character (`/`), only the method name is used to form the service URL; the class
name is ignored:

    @JSONWebService("/add-something-very-specific")
    public boolean addBoard(

Similarly, you can change the class name part of the URL, by setting the value
in a class-level annotation:

    @JSONWebService("sbs")
    public class SurfBoardServiceImpl extends SurfBoardServiceBaseImpl {

This maps all of the service's methods to a URL class name `sbs` instead
of the default class name `surfboard`. 

Next, we'll show you a different approach to exposing your methods as we discuss
manual registration. 

### Manual Registration Mode [](id=manual-registration-mode)

Up to now, it is assumed that you want to expose most of your service methods,
while hiding some specific methods (the *blacklist* approach).
Sometimes, however, you want the opposite: to explicitly specify only those
methods that are to be exposed (the *whitelist* approach). This is possible,
too, by specifying *manual mode* on the class-level annotation. Then it is up to
you to annotate only those methods that you want exposed.

    @JSONWebService(mode = JSONWebServiceMode.MANUAL)
    public class SurfBoardServiceImpl extends SurfBoardServiceBaseImpl{
        ...
        @JSONWebService
        public boolean addBoard(

Now only the `addBoard` method and any other method annotated with
`@JSONWebService` will be part of the JSON Web Service API; all other methods
of this service will be excluded from the API.

## Related Topics

[Invoking JSON Web Services](/develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services)
