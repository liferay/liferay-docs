# JSON Web Services

JSON web services let you access portal service methods by exposing them as a
JSON HTTP API. Service methods are made easily accessible using HTTP requests,
both from JavaScript within the portal and from any JSON-speaking client. 

We'll cover the following topics as we explore JSON Web Service functionality:

- Registration
- Configuration
- Invocation 
- Results

Let's start by discussing how to register JSON web services. 

## Registering JSON Web Services

Liferay's developers use a tool called *Service Builder* to build services. When
you build services with Service Builder, all remote-enabled services (i.e.,
`service.xml` entities with the property `remote-service="true"`) are exposed as
JSON web services. When each `*Service.java` interface is created for a
remote-enabled service, the `@JSONWebService` annotation is added on the class
level of that interface. All of the public methods of that interface become
registered and available as JSON web services. 

The `*Service.java` interface source file should never be modified by the user.
If you need, however, more control over its methods (e.g., hiding some methods
and exposing others), you can configure the `*ServiceImpl` class. When the
service implementation class (`*ServiceImpl`) is annotated with the
`@JSONWebService` annotation, the service interface is ignored and the service
implementation class is used for configuration in its place. In other words,
`@JSONWebService` annotations in the service implementation override any JSON
Web Service configuration in service interface.

That's it! When you start Liferay Portal, it scans service classes for
annotations (more about scanning later). Each class that uses the
`@JSONWebService` annotation is examined and its methods become exposed as JSON
API. As explained previously, the `*ServiceImpl` configuration overrides the
`*Service` interface configuration during registration.

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
[Defining an Object-Relational Map with Service Builder](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/defining-an-object-relational-map-with-service-builder)
tutorial.

$$$

OK, now let's see how you can create a plugin with some remote services. Keep in
mind that Liferay developers use the very same mechanism so that Liferay
Portal's services come enabled out-of-the-box. 

### Registering Plugin JSON Web Services

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

Let's deploy the `SupraSurf` portlet plugin on our portal server. If your server
isn't running, start it up. Then deploy your plugin onto it. 

To get some feedback from the portal on registering your plugin's services,
configure the portal to log the plugin's informational messages (i.e., its `INFO
...` messages). See the section on Liferay's logging system in
[Using Liferay Portal](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/liferays-logging-system-liferay-portal-6-2-user-guide-18-en).

Let's add a simple method to the plugin's services. Edit the
`SurfBoardServiceImpl` class and add the following method:

    public String helloWorld(String worldName) {
        return "Hello world: " + worldName;
    }

Rebuild the services and deploy the plugin. Notice that the portal prints a
message like the one below informing us that an action was configured for the
portlet. This indicates that the service method is now registered as a JSON Web
Web Service action!

	INFO  [JSONWebServiceActionsManagerImpl:117] Configured 1 actions for\
	    /suprasurf-portlet

This same mechanism registers Liferay Portal's own service actions. They are
conveniently enabled by default, so you don't have to configure them. 

Next, let's learn how to form a mapped URL for the remote service so we can
access it. 

### Mapping and Naming Conventions

You can form the mapped URL of an exposed service by following the naming
convention below: 

    http://[server]:[port]/api/jsonws/[plugin-context-name.][service-class-name]/[service-method-name]

Let's look at the last three bracketed items more closely: 

- `plugin-context-name` is the plugin's context name (e.g., `suprasurf-portlet`
  in our example). For the portal's services, this part is not needed. 
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

Here's is that portal service method's URL: 

        http://localhost:8080/api/jsonws/user-service/get-user-by-id

Each service method is bound to one HTTP method type. Any method with a name
starting with `get`, `is`, or `has` is assumed to be a read-only method and is
mapped as a *GET HTTP* method by default. All other methods are mapped as *POST
HTTP* methods. 

As you may have noticed, plugin services are accessed via the portal context.
Conveniently, requests sent this way can leverage the user's authentication in
his current portal session.

Next, we'll learn to how to *list* JSON web services available from our portal. 

### Listing Available JSON Web Services

To see which service methods are registered and available for use, open your
browser to the following address: 

    http://localhost:8080/api/jsonws

The API page lists the portal's registered and exposed service methods. To get
each method's details, click on the method name. You'll see the full signature
of the method, all of its arguments, and a list of exceptions that can be
thrown. For additional information about remote service methods, you can look up
the method in Liferay Portal's
[Javadocs](http://docs.liferay.com/portal/6.2/javadocs). Using a simple form
from within your browser, you can even invoke the service method for testing
purposes.

The same API page lists remote services of plugins, too. When multiple plugins
with remote services enabled are deployed, the API page shows a select box with
all available plugin context paths (including the portal's path). The select box
facilitates switching between the plugins' list of remote services and the
portal's list of remote services.

If you've been paying attention, you already know how to control registration by
using the `@JSONWebService` annotation in your `*ServiceImpl` class. This
overrides any configuration defined in the interface. What you might not know is
that you can control the visibility of methods using annotations at the method
level. 

Let's find out how to ignore a specific method. 

### Ignoring a Method

To keep a method from being exposed as a service, annotate the method with the
following option:

    @JSONWebService(mode = JSONWebServiceMode.IGNORE)

Methods with this annotation don't become part of the JSON Web Service API. 

Let's learn to define custom HTTP method names and URL names. 

### HTTP Method Name and URL

At the method level, you can define custom HTTP method names and URL names. Just
use an annotation like this one: 

    @JSONWebService(value = "add-board-wow", method = "PUT")
    public boolean addBoard(

In this example, the plugin's service method `addBoard` is mapped to URL
method name `add-board-wow`. Its complete URL is now
`http://localhost:8080/api/jsonws/suprasurf-portlet.surfboard/add-board-wow` and
can be accessed using the HTTP PUT method. 

If the URL name starts with a slash character (`/`), only the method name is
used to form the service URL; the class name is ignored.

    @JSONWebService("/add-something-very-specific")
    public boolean addBoard(

Similarly, you can change the class name part of the URL, by setting the value
in class-level annotation:

    @JSONWebService("sbs")
    public class SurfBoardServiceImpl extends SurfBoardServiceBaseImpl {

This maps all of the service's methods to a URL class name `sbs` instead
of the default class name `surfboard`. 

Next, we'll show you a different approach to exposing your methods as we discuss
manual registration. 

### Manual Registration Mode

Up to now, it is assumed that you want to expose most of your service methods,
while hiding some specific methods (the *blacklist* approach).
Sometimes, however, you want the opposite: to explicitly specify only those
methods that are to be exposed (the *whitelist* approach). This is possible,
too, by specifying *manual mode* on the class-level annotation. Then it is up to
you annotate only those methods that you want exposed.

    @JSONWebService(mode = JSONWebServiceMode.MANUAL)
    public class SurfBoardServiceImpl extends SurfBoardServiceBaseImpl{
        ...
        @JSONWebService
        public boolean addBoard(

Now only the `addBoard` method and any other method annotated with
`@JSONWebService` will be part of the JSON Web Service API; all other methods
of this service will be excluded from the API.

Next, let's look at portal configuration options that apply to JSON Web
Services. 

## Portal Configuration of JSON Web Services

JSON web services are enabled on Liferay Portal by default. If you need to
disable them, specify this portal property setting: 

    json.web.service.enabled=false

Now let's look at strict HTTP methods. 

### Strict HTTP Methods

All JSON web services are mapped to either GET or POST HTTP methods. If a
service method name starts with `get`, `is` or `has`, the service is assumed to
be read-only and is bound to the GET method; otherwise it's bound to POST. 

By default, Liferay Portal doesn't check HTTP methods when invoking a service
call; it works in *non-strict http method* mode, where services may be invoked
using any HTTP method. If you need the strict mode, you can set it with this
portal property: 

    jsonws.web.service.strict.http.method=true

When using strict mode, you must use the correct HTTP methods in calling service
methods. 

When strict HTTP mode is enabled, you still might have need to disable HTTP
methods. We'll show you how next. 

### Disabling HTTP Methods

When strict HTTP method mode is enabled, you can filter web service access based
on HTTP methods used by the services. For example, you can set the portal JSON
web services to work in read-only mode by disabling HTTP methods other than GET.
For example: 

    jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

Now all requests that use HTTP methods from the list above are ignored.

Next, we'll show you how to restrict public access to exposed JSON APIs. 

### Controlling Public Access

Each service method knows if it can be executed by unauthenticated users and
if a user has adequate permission for the chosen action. Most of the portal's
read-only methods are open to public access.

If you're concerned about security, you can further restrict public access to
exposed JSON APIs by explicitly stating which methods are *public* (i.e.,
accessible to unauthenticated users). Use the following property to specify your
public methods: 

    jsonws.web.service.public.methods=*

The property supports wildcards, so if you specify `get*,has*,is*` on the right
hand side of the `=` symbol, all read-only JSON methods will be publicly
accessible. All other JSON methods will be secured. To disable access to *all*
exposed methods, you can leave the right side of the `=` symbol empty; to enable
access to all exposed methods, specify `*`. 

Read on to find out how to invoke JSON web services. 

## Invoking JSON Web Services

How you invoke a JSON web service depends on how you pass in its parameters.
We'll discuss how to pass in parameters below, but first you need to understand
how your invocation is matched to a method, especially when a service method is
overloaded. 

The general rule is that you provide the method name and *all* parameters for
that service method--even if you only provide `null`. 

It's important to provide all parameters, but it doesn't matter *how* you do it
(e.g., as part of the URL line, as request parameters, etc.). The order of the
parameters doesn't matter either. 

+$$$

**Note:** An authentication related token must accompany each Liferay web
service invocation. For details, please see the
[Understanding Liferay's Service Security Model](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/understanding-liferays-service-security-model)
tutorial. 

$$$

Exceptions abound in life, and there's an exception to the rule that *all*
parameters are required--when using numeric *hints* to match methods. Let's look
at using hints next. 

### Using Hints

Adding numeric hints lets you specify how many method arguments a service has.
If you don't specify an argument for a parameter, it's automatically
passed in as `null`. Syntactically, you can add hints as numbers separated by a
dot in the method name. Here's an example: 

    /foo/get-bar.2/param1/123/-param2

Here, the `.2` is a numeric hint specifying that only service methods with two
arguments will be matched; others will be ignored for matching. 

There's an important distinction to make between matching using hints and
matching without hints. When a hint is specified, you don't have to specify all
of the parameters. Any missing arguments are treated as `null`. The previous
example may be called like this:

    /foo/get-bar.2/param1/123

In this example, `param2` will automatically be set to `null`. 

Find out how to pass parameters as part of the URL path next. 

### Passing Parameters as Part of a URL Path

You can pass parameters as part of the URL path. After the service URL, just
specify method parameters in name-value pairs. Parameter names must be formed
from method argument names by converting them from camelCase to names using all
lower case and separated-by-dash. Here's an example of calling one of the
portal's services: 

    http://localhost:8080/api/jsonws/dlapp/get-file-entries/repository-id/\
    10172/folder-id/0

Note, we've inserted line escape character `\` in order to fit the example URL
on this page. 

You can pass parameters in any order; it's not necessary to follow the order in
which the arguments specified in the method signatures. 

When a method name is overloaded, the *best match* will be used. It chooses the
method that contains the least number of undefined arguments and invokes it for
you. 

You can also pass parameters in a URL query, and we'll show you how next. 

### Passing Parameters as a URL Query

You can pass in parameters as request parameters. Parameter names are specified
as is (e.g. camelCase) and are set equal to their argument values, like this: 

    http://localhost:8080/api/jsonws/dlapp/get-file-entries?repositoryId=\
    10172&folderId=0

Note, we've inserted line escape character `\` in order to fit the example URL
on this page.

As with passing parameters as part of a URL path, the parameter order is not
important, and the *best match* rule applies for overloaded methods. 

Now you know a few different ways to pass parameters. It's also possible to pass
URL parameters in a mixed way. Some can be part of the URL path and some can be
specified as request parameters.

Parameter values are sent as strings using the HTTP protocol. Before a matching
Java service method is invoked, each parameter value is converted from a
`String` to its target Java type. Liferay uses a third party open source library
to convert each object to its appropriate common type. It's possible to add or
change the conversion for certain types but we'll just cover the standard
conversions process. 

Conversion for common types (e.g., `long`, `String`, `boolean`) is
straightforward. Dates can be given in milliseconds; locales can be passed as
locale names (e.g. `en` and `en_US`). To pass in an array of numbers, send a
`String` of comma-separated numbers (e.g. `String` `4,8,15,16,23,42` can be
converted to `long[]` type). You get the picture!

In addition to the common types, arguments can be of type `List` or `Map`. To
pass a `List` argument, send a JSON array. To pass a `Map` argument, send a JSON
object. The conversion of these is done in two steps, ingeniously referred to
below as *Step 1* and *Step 2*:

- *Step 1--JSON deserialization*: JSON arrays are converted into `List<String>`
  and JSON objects are converted to `Map<String, String>`. For security reasons,
  it is forbidden to instantiate any type within JSON deserialization. 
- *Step 2--Generification*: Each `String` element of the `List` and `Map` is
  converted to its target type (the argument's generic Java type specified in
  the method signature). This step is only executed if the Java argument type
  uses generics. 

As an example, let's consider the conversion of `String` array `[en,fr]` as JSON
web service parameters for a `List<Locale>` Java method argument type: 

- *Step 1--JSON deserialization*: The JSON array is deserialized to a
  `List<String>` containing `String`s `en` and `fr`. 

- *Step 2--Generification*: Each `String` is converted to the `Locale` (the
  generic type), resulting in the `List<Locale>` Java argument type. 

Now let's see how to specify an argument as `null`. 

### Sending NULL Values

To pass a `null` value for an argument, prefix the parameter name with a dash.
Here's an example: 

    .../dlsync/get-d-l-sync-update/company-id/10151/repository-id/10195/-last-access-date

The `last-access-date` parameter is interpreted as `null`. Although we have it
last in the URL above, it's not necessary.

Null parameters don't have specified values. When a null parameter is passed as
a request parameter, its value is ignored and `null` is used instead: 

    <input type="hidden" name="-last-access-date" value=""/>

When using JSON RPC (see below), you can send null values explicitly, even
without a prefix. Here's an example: 

    "last-access-date":null

Now let's learn about encoding parameters. 

### Encoding Parameters

There's a difference between URL encoding and query (i.e. request parameters)
encoding. The difference lies in how the space character is encoded. When the
space character is part of the URL path, it's encoded as `%20`; when it's part
of the query it's encoded as a plus sign (`+`).

All these encoding rules apply to ASCII and international (non-ASCII)
characters. Since Liferay Portal works in UTF-8 mode, parameter values must be
encoded as UTF-8 values. Liferay Portal doesn't decode request URLs and request
parameter values to UTF-8 itself; it relies on the web server layer. When
accessing services through JSON-RPC, encoding parameters to UTF-8 isn't
enough--you need to send the encoding type in a Content-Type header (e.g.
`Content-Type : "text/plain; charset=utf-8"`). 

For example, let's pass the value "&#1057;&#1091;&#1087;&#1077;&#1088;" ("Super"
in Cyrillic) to some JSON Web Service method. This name first has to be
converted to UTF-8 (resulting in array of 10 bytes) and then encoded for URLs or
request parameters. The resulting value is the string
`%D0%A1%D1%83%D0%BF%D0%B5%D1%80` that can be passed to our service method. When
received, this value is first going to be translated to an array of 10 bytes
(URL decoded) and then converted to a UTF-8 string of the 5 original characters.

Did you know you can send files as arguments? Find out how next. 

### Sending Files as Arguments

Files can be uploaded using multipart forms and requests. Here's an example: 

    <form
     action="http://localhost:8080/api/jsonws/dlapp/add-file-entry"
     method="POST"
     enctype="multipart/form-data">
        <input type="hidden" name="repositoryId" value="10172"/>
        <input type="hidden" name="folderId" value="0"/>
        <input type="hidden" name="title" value="test.jpg"/>
        <input type="hidden" name="description" value="File upload example"/>
        <input type="hidden" name="changeLog" value="v1"/>
        <input type="file" name="file"/>
        <input type="submit" value="addFileEntry(file)"/>
    </form>

This is a common upload form that invokes the `addFileEntry` method of the
`DLAppService` class. 

Now we'll show you how to invoke JSON web services using JSON RPC. 

### JSON RPC

You can invoke JSON Web Service using [JSON RPC](http://json-rpc.org). Most of
the JSON RPC 2.0 specification is supported in Liferay JSON web services. One
important limitation is that parameters must be passed in as *named* parameters.
Positional parameters are not supported, as there are too many overloaded
methods for convenient use of positional parameters. 

Here's an example of invoking a JSON web service using JSON RPC: 

    POST http://localhost:8080/api/jsonws/dlapp
    {
        "method":"get-folders",
        "params":{"repositoryId":10172, "parentFolderId":0},
        "id":123,
        "jsonrpc":"2.0"
    }

Let's talk about parameters that are made available to secure JSON web
services by default. 

### Default Parameters

When accessing *secure* JSON web services (i.e., the user has to be
authenticated), some parameters are made available to the web services by
default. Unless you want to change their values to something other than their
defaults, you don't have to specify them explicitly. 

Here are the default parameters:

- `userId`: The id of authenticated user
- `user`: The full User object
- `companyId`: The users company
- `serviceContext`: The empty service context object 

Let's find out about object parameters next. 

### Object Parameters

Most services accept simple parameters like numbers and strings. However,
sometimes you might need to provide an object (a non-simple type) as a service
parameter. 

To create an instance of an object parameter, prefix the parameter with a plus
sign, `+` and don't assign it any other parameter value. This is similar to
when we specified a null parameter by prefixing the parameter with a dash
symbol, `-`.

Here's an example:

    /jsonws/foo/get-bar/zap-id/10172/start/0/end/1/+foo

To create an instance of an object parameter as a request parameter, make sure
you encode the `+` symbol: 

    /jsonws/foo/get-bar?zapId=10172&start=0&end=1&%2Bfoo

Here's an alternative syntax: 

    <input type="hidden" name="+foo" value=""/>

If a parameter is an abstract class or an interface, it can't be instantiated as
such. Instead, a concrete implementation class must be specified to create the
argument value. You can do this by specifying the `+` prefix before the
parameter name followed by specifying the concrete implementation class. Check
out this example: 

    /jsonws/foo/get-bar/zap-id/10172/start/0/end/1/+foo:com.liferay.impl.FooBean

Here's another way of doing it: 

    <input type="hidden" name="+foo:com.liferay.impl.FooBean" value=""/>

The examples above specify that a `com.liferay.impl.FooBean` object, presumed to
implement the class of the parameter named `foo`, is to be created. 

You can also set a concrete implementation as a value. Here's an example: 

    <input type="hidden" name="+foo" value="com.liferay.impl.FooBean"/>

In JSON RPC, here's what it looks like:

    "+foo" : "com.liferay.impl.FooBean"

All the examples above specify a concrete implementation for the `foo` service
method parameter. 

Once you pass in an object parameter, you might want to populate the object.
Find out how next. 

### Inner Parameters

When you pass in an object parameter, you'll often need to populate its inner
parameters (i.e., fields). Consider a default parameter `serviceContext` of type
`ServiceContext` (see the
[ServiceContext](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/servicecontext-liferay-portal-6-2-dev-guide-06-en)
section of the next chapter to find out more about this type). To make an
appropriate call to JSONWS, you might need to set the `serviceContext`
parameter's fields `addGroupPermissions` and `scopeGroupId`. 

You can pass inner parameters by specifying them using dot notation. Just append
the name of the parameter with a dot (i.e., a period, `.`), followed by the name
of the inner parameter. For the `ServiceContext` inner parameters we mentioned
above, you'd specify `serviceContext.addGroupPermissions` and
`serviceContext.scopeGroupId`. They're recognized as inner parameters and their
values are injected into existing parameters before the API service method is
executed. 

Inner parameters aren't counted as regular parameters for matching methods
and are ignored during matching. 

+$$$

**Tip:** Use inner parameters with object parameters to set inner content of
created parameter instances! 

$$$

Next, let's see what values are returned when a JSON seb service is invoked. 

## Returned Values

No matter how a JSON web service is invoked, it returns a JSON string that
represents the service method result. Returned objects are *loosely* serialized
to a JSON string and returned to the caller. 

Let's look at some values returned from service calls. We'll create a
`UserGroup` as we did in our SOAP web service client examples. To make it easy,
we'll use the test form provided with the JSON web service in our browser. 

1.  Sign in to your portal as an administrator and then point your browser to
    the JSON web service method that adds a `UserGroup`: 

        http://127.0.0.1:8080/api/jsonws?signature=/usergroup/add-user-group-2-\
        name-description

    Note, we've inserted line escape character `\` in order to fit the example
    URL on this page.

    Alternatively, navigate to it by starting at
    `http://127.0.0.1:8080/api/jsonws` then scrolling down to the section for
    *UserGroup*; click *add-user-group*. 

2.  In the *name* field, enter *UserGroup3* and set the description to an
    arbitrary value like *Created via JSON WS*.

3.  Click *Invoke* and you'll get a result similar to the following: 

        {
          "addedByLDAPImport": false,
          "companyId": 10154,
          "createDate": 1382460167254,
          "description": "Created via JSON WS",
          "modifiedDate": 1382460167254,
          "name": "UserGroup3",
          "parentUserGroupId": 0,
          "userGroupId": 13901,
          "userId": 10198,
          "userName": "Test Test",
          "uuid": "1b18c73d-482a-4772-b6f4-a9253bbcbf92"
        }

The returned `String` represents the `UserGroup` object you just created,
serialized into a JSON string. To find out more about JSON strings, go to
[json.org](http://www.json.org/). 

Let's check out some common JSON WebService errors. 

## Common JSON Web Service Errors

While working with JSON web services, you may encounter errors. Let's look at
the most common errors in the following subsections. 

<!--I would have put the subsections in an unordered list, but I'll leave them
for now. Which is preferred? Are they long enough to warrant subsections? --> 

<!-- No, they're not long enough. In fact, that's been a problem with this whole
chapter. I removed some sections already. One way to fix some of the "missing
transition" problems above would be to combine some of the sections. -Rich -->

-   Authenticated access required

    If you see this error, it means you don't have permission to invoke the
    remote service. Double-check that you're signed in as a user with the
    appropriate permissions. If necessary, sign in as an administrator to invoke
    the remote service.

-   Missing value for parameter 
    
    If you see this error, you didn't pass a parameter value along with the
    parameter name in your URL path. The parameter value must follow the
    parameter name, like in this example: 

        /api/jsonws/user/get-user-by-id/userId

    The path above specifies a parameter named `userId`, but doesn't specify
    the parameter's value. You can resolve this error by providing the
    parameter value after the parameter name: 

        /api/jsonws/user/get-user-by-id/userId/173

-   No JSON web service action associated 

    This is error means no service method could be matched with the provided
    data (method name and argument names). This can be due to various reasons:
    arguments may be misspelled, the method name may be formatted incorrectly,
    etc. Since JSON web services reflect the underlying Java API, any changes in
    the respective Java API will automatically be propagated to the JSON web
    services. For example, if a new argument is added to a method or an existing
    argument is removed from a method, the parameter data must match that of the
    new method signature.

-   Unmatched argument type 

    This error appears when you try to instantiate a method argument using an
    incompatible argument type.

-   Judgment Day

    We hope you never see this error. It means that Skynet has initiated a
    nuclear war and most of humanity will be wiped out; survivors will need to
    battle *Terminator* cyborgs. If you see this error and survive *Judgment
    Day*, we recommend joining the resistance--they'll likely need good
    developers to support the cause, especially those familiar with time travel. 

    Had you going there, didn't we? 

Next, we'll show you how to optimize your use of JSON web services by using the
*JSON Web Services Invoker*. 

## JSON Web Services Invoker

Using JSON web services is easy: you send a request that defines a service
method and parameters, and you receive the result as JSON object. Below we'll
show you why that's not optimal, and introduce a tool that lets you use JSON
web services more efficiently and pragmatically. 

Consider this example: You're working with two related objects: a `User` and its
corresponding `Contact`. With simple JSON Web Service calls, you first call some
user service to get the user object and then you call the contact service using
the contact ID from the user object. You end up sending two HTTP requests to get
two JSON objects that aren't even bound together; there's no contact information
in the user object (i.e. no `user.contact`). This approach is suboptimal for
performance (sending two HTTP calls) and usability (manually managing the
relationship between two objects). It'd be nicer if you had a tool to address
these inefficiencies. Fortunately, the *JSON Web Service Invoker* does just
that! 

Liferay's JSON Web Service Invoker helps you optimize your use of JSON Web
Services. In the following sections, we'll show you how. 

### A Simple Invoker Call

The Invoker is accessible from the following fixed address:

    http://[address]:[port]/api/jsonws/invoke

It only accepts a `cmd` request parameter--this is the Invoker's command. If the
command request parameter is missing, the request body is used as the command.
So you can specify the command by either using the request parameter `cmd` or
the request body. 

The Invoker command is a plain JSON map describing how JSON web services are
called and how the results are managed. Here's an example of how to call a
simple service using the Invoker: 

    {
        "/user/get-user-by-id": {
            "userId": 123,
            "param1": null
        }
    }

The service call is defined as a JSON map. The key specifies the service URL
(i.e. the service method to be invoked) and the key's value specifies a map of
service parameter names (i.e. `userId` and `param1`) and their values. In the
example above, the retrieved user is returned as a JSON object. Since the
command is a JSON string, null values can be specified either by explicitly
using the `null` keyword or by placing a dash before the parameter name and 
leaving the value empty (e.g. `"-param1": ''`).

The example Invoker calls functions exactly the same as the following standard
JSON Web Service call: 

    /user/get-user-by-id?userId=123&-param1

Of course, JSON Web Service invoker handles calls to plugin methods as well:

    {
        "/suprasurf-portlet.surfboard/hello-world": {
            "worldName": "Mavericks"
        }
    }

The code above calls our plugin's remote service. 

You can use variables to reference objects returned from service calls. Variable
names must start with a dollar sign, `$`. In our previous example, the service
call returned a user object you can assign to a variable: 

    {
        "$user = /user/get-user-by-id": {
            "userId": 123,
        }
    }

The `$user` variable holds the returned user object. You can reference the
user's contact ID using the syntax `$user.contactId`. 

Next, see how you can use nested service calls to join information from two
related objects. 

### Nesting Service Calls

With nested service calls, you can magically bind information from related
objects together in a JSON object. You can call other services within the same
HTTP request and nest returned objects in a convenient way. Here's the magic of
a nested service call in action: 

    {
        "$user = /user/get-user-by-id": {
            "userId": 123,
            "$contact = /contact/get-contact-by-id": {
                "@contactId" : "$user.contactId"
            }
        }
    }

This command defines two service calls: the contact object returned from the
second service call is nested in (i.e. injected into) the user object, as a
property named `contact`. Now we can bind the user and his or her contact
information together! 

Let's see what the Invoker did in the background when we used a single HTTP
request to make the above nested service call: 

- First, the Invoker called the Java service mapped to `/user/get-user-by-id`,
  passing in a value for the `userId` parameter. 
- Next, the resulting user object was assigned to the variable `$user`. 
- The nested service calls were invoked. 
- The Invoker called the Java service mapped to `/contact/get-contact-by-id` by 
  using the `contactId` parameter, with the `$user.contactId` value from the
  object `$user`. 
- The resulting contact object was assigned to the variable `$contact`. 
- Lastly, the Invoker injected the contact object referenced by `$contact` into
  the user object's property named `contact`. 

+$$$

**Note:** You must *flag* parameters that take values from existing variables.
To flag a parameter, insert the `@` prefix before the parameter name. 

$$$

Next, let's talk about filtering object properties so only those you need are
returned when you invoke a service. 

### Filtering Results

Many of Liferay Portal's model objects are rich with properties. If you only
need a handful of an object's properties for your business logic, making a web
service invocation that returns all of an object's properties is a waste of
network bandwidth. With the JSON Web Service Invoker, you can define a
*white-list* of properties: only the specific properties you request in the
object will be returned from your web service call. Here's how you white-list
the properties you need: 

    {
        "$user[firstName,emailAddress] = /user/get-user-by-id": {
            "userId": 123,
            "$contact = /contact/get-contact-by-id": {
                "@contactId" : "$user.contactId"
            }
        }
    }

In this example, the returned user object has only the `firstName` and
`emailAddress` properties (it still has the `contact` property, too). To specify
white-list properties, you simply place the properties in square brackets (e.g.,
`[whiteList]`) immediately following the name of your variable. 

Let's talk about batching calls next. 

### Batching Calls

When we nested service calls earlier, the intent was to invoke multiple services
with a single HTTP request. Using a single request for multiple service calls is
helpful for gathering related information from the service call results, but it
can also be advantageous to use a single request to invoke multiple unrelated
service calls. The Invoker lets you batch service calls together to improve
performance. It's simple: just pass in a JSON array of commands using the
following format: 

    [
        {/* first command */},
        {/* second command */}
    ]

The result is a JSON array populated with results from each command. The
commands are collectively invoked in a single HTTP request, one after another. 

Now that you've learned the ins and outs of invoking JSON web services in
Liferay, you may want to consider taking a streamlined approach to accessing Web
Content articles and Dynamic Data List records. Liferay's Skinny JSON Provider
app gives you access to them and returns them in an easy-to-use fashion. For
more information, please refer to the
[Invoking Services Using Skinny JSON Provider](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/invoking-services-using-skinny-json-provider) 
tutorial.
