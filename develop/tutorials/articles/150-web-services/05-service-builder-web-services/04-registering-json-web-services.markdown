# Registering JSON Web Services [](id=registering-json-web-services)

Liferay's developers use a tool called *Service Builder* to build services.
When you build services with Service Builder, all remote-enabled services
(i.e., `service.xml` entities with the property `remote-service="true"`) are
exposed as JSON web services. When each `*Service.java` interface is created
for a remote-enabled service, the `@JSONWebService` annotation is added to that
interface at the class level. All the public methods of that interface become
registered and available as JSON web services.

Liferay scans all OSGi bundles registered with the `@Component` annotation or in
a `*BundleActivator` class for remote services. Each class that uses the
`@JSONWebService` annotation is examined and its methods become exposed via the
JSON web services API. 

+$$$

**Note:** Liferay's developers use *Service Builder* to expose their services
via JSON automatically. If you haven't used Service Builder before, please see
the
[Service Builder tutorials](/develop/tutorials/-/knowledge_base/7-1/what-is-service-builder).

$$$

Next, you'll see how to register your application's remote services as JSON web
services. Keep in mind that Liferay uses this same mechanism. This is why
Liferay's remote services are exposed as JSON web services out-of-the-box. 

## Registering an App's JSON Web Services [](id=registering-an-apps-json-web-services)

For example, say your app named `SupraSurf` has some services you want exposed
as remote services. After enabling the `remote-service` attribute on its
`SurfBoard` entity, you rebuild the services. Service Builder regenerates the
`SurfBoardService` interface, adding the `@JSONWebService` annotation to it.
This annotation tells Liferay that the interface's public methods are to be
exposed as JSON web services, making them a part of the app's JSON API. Start 
your Liferay instance, and then deploy your app to Liferay. 

To get some feedback from your Liferay instance on registering your 
application's services, configure the instance to log the application's 
informational messages (i.e., its `INFO ...` messages). See the tutorials on
Liferay's 
[logging system](/discover/portal/-/knowledge_base/7-1/server-administration#log-levels) 
for details. 

To test Liferay's JSON web service registration process, add a simple method to
your app's services. Edit your `*ServiceImpl` class and add this method:

    public String helloWorld(String worldName) {
        return "Hello world: " + worldName;
    }

Rebuild the services and re-deploy your app's modules. You can now invoke this 
service method via JSON. For instructions on doing this, see the JSON invocation 
tutorials listed 
[here](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services). 

This same mechanism registers Liferay's own services. They're enabled by
default, so you don't have to configure them. 

Next, you'll learn how to form a mapped URL for the remote service so you can
predictably access it. 

## Mapping and Naming Conventions [](id=mapping-and-naming-conventions)

You can form the mapped URL of an exposed service by following the naming
convention below:

    http://[server]:[port]/api/jsonws/[context-path].[service-class-name]/[service-method-name]

Look at the last three bracketed items more closely: 

- `context-name` is the app's context name (e.g., `suprasurf`). Its value is
  specified via the `json.web.service.context.path` 
  property in the `@OSGiBeanProperties` annotation. For example, for Liferay web
  content articles, Liferay's `JournalArticleService` class includes this
  annotation (among others): 

        @OSGiBeanProperties(property =  {
            "json.web.service.context.name=journal", "json.web.service.context.path=JournalArticle"}, service = JournalArticleService.class)

- `service-class-name` is generated from the service's class name in lower case,
  minus its `Service` or `ServiceImpl` suffix. For example, specify `surfboard`
  as the `app-context-name` for the `SurfBoardService` class. 
- `service-method-name` is generated from the service's method name by
  converting its camel case to lower case and using dashes (`-`) to separate
  words. 

The following example demonstrates these naming conventions by mapping a service 
method's URL using the naming conventions both on a custom service and on a 
Liferay service.

For the custom service method, the URL looks like

    http://localhost:8080/api/jsonws/suprasurf.surfboard/hello-world

Note the context name part of the URL. For Liferay, it's similar. Here's a 
Liferay service method:

    @JSONWebService
    public interface UserService {
        public com.liferay.portal.model.User getUserById(long userId) {...}

Here's that Liferay service method's URL:

    http://localhost:8080/api/jsonws/user/get-user-by-id

Each service method is bound to one HTTP method type. Any method with a name
starting with `get`, `is`, or `has` is a read-only method and is mapped as
a *GET HTTP* method by default. All other methods are mapped as *POST HTTP*
methods. 

In the list of JSON web services at
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws), when you
select a method, the part of its HTTP method URL that follows
`http://[server]:[port]/api/jsonws` appears at the top of the screen. 

Conveniently, remote service requests can use authentication credentials
associated with the user's current session. 

Next, you'll learn how to prevent a method from being exposed as a service. 

## Ignoring a Method [](id=ignoring-a-method)

To keep a method from being exposed as a service, annotate the method with the
following option:

    @JSONWebService(mode = JSONWebServiceMode.IGNORE)

Methods with this annotation don't become part of the JSON Web Service API. 
Next, you'll learn how to define custom HTTP method and URL names. 

## HTTP Method and URL Names [](id=http-method-and-url-names)

At the method level, you can define custom HTTP method names and URL names. Just
use an annotation like this one:

    @JSONWebService(value = "add-board-wow", method = "PUT")
    public boolean addBoard(

In this example, the application's service method `addBoard` is mapped to URL
method name `add-board-wow`. Its complete URL is now
`http://localhost:8080/api/jsonws/suprasurf.surfboard/add-board-wow`
and can be accessed using the HTTP PUT method.

If the URL method name in a JSON web service annotation starts with a slash
character (`/`), only the method name is used to form the service URL; the class
name is ignored:

    @JSONWebService("/add-something-very-specific")
    public boolean addBoard(

Similarly, you can change the class name part of the URL by setting the value
in a class-level annotation:

    @JSONWebService("sbs")
    public class SurfBoardServiceImpl extends SurfBoardServiceBaseImpl {

This maps all the service's methods to a URL class name `sbs` instead of the
default class name `surfboard`.

Next, you'll learn a different approach to exposing your methods via manual 
registration.

## Manual Registration Mode [](id=manual-registration-mode)

Up to now, it's assumed that you want to expose most of your service methods,
while hiding some specific methods (the *blacklist* approach). Sometimes, 
however, you want the opposite: to explicitly specify only the methods you want
to expose (the *whitelist* approach). This is possible by specifying
a class-level annotation with *manual mode*. Then it's up to you to annotate
only those methods you want to expose: 

    @JSONWebService(mode = JSONWebServiceMode.MANUAL)
    public class SurfBoardServiceImpl extends SurfBoardServiceBaseImpl{
        ...
        @JSONWebService
        public boolean addBoard(

Now only the `addBoard` method and any other method annotated with 
`@JSONWebService` are part of the JSON Web Service API; all other methods are
excluded from the API. 

## Related Topics [](id=related-topics)

[Invoking JSON Web Services](/develop/tutorials/-/knowledge_base/7-1/invoking-json-web-services)

[JSON Web Services Invoker](/develop/tutorials/-/knowledge_base/7-1/json-web-services-invoker)

[JSON Web Services Invocation Examples](/develop/tutorials/-/knowledge_base/7-1/json-web-services-invocation-examples)
