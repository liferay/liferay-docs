# Invoking JSON Web Services [](id=invoking-json-web-services)

Liferay's JSON web service API can be invoked in languages other than Java, such
as Beanshell, Groovy, JavaScript, Python, and Ruby. It can also be invoked via
URL or [cURL](http://curl.haxx.se/). Additionally, Liferay provides a handy JSON
web services page that allows you to browse and invoke service methods.

If you're running Liferay locally on port 8080, you can find the JSON web
services page at [localhost:8080/api/jsonws](localhost:8080/api/jsonws). You can
use this page to generate example code for invoking web services. Use the
Context Path selector to switch contexts and you'll see different available
service methods. The portal's context path is `/`, the Calendar portlet's
context path is `/calendar-portlet`, the Marketplace portlet's context path is
`/marketplace-portlet`, etc. To see the generated code for a particular service,
click on the name of the service, enter the required parameters and click
*Invoke*. The JSON result of your service invocation appears. There are multiple
ways to invoke Liferay's JSON web services: click on *JavaScript Example*, *curl
Example*, or *URL example* to see different ways of invoking the web service.

![Figure 1: When you invoke a service from Liferay's JSON web services page, you can view the result of your service invocation as well as example code for invoking the service via JavaScript, curl, or URL.](../../images/jsonws-simple-example.png)

This tutorial explains general techniques for working with JSON web services and
includes details about invoking via URL. For examples of invoking Liferay's JSON
web services via JavaScript, URL, and cURL, please see the
[JSON Web Services Invocation Examples](/develop/tutorials/-/knowledge_base/6-2/json-web-services-invocation-examples)
tutorial.

How you invoke a JSON web service depends on how you pass in its parameters. In
this tutorial, you'll learn how to include parameters in web service
invocations. First, you need to understand how your invocation is matched to a
method, especially in the case of overloaded service methods. 

The general rule is that you provide the service method name and *all*
parameters for the service method--even if you only provide `null`. 

It's important to provide all parameters, but it doesn't matter *how* you do it
(e.g., as part of the URL line, as request parameters, etc.). The order of the
parameters doesn't matter either. 

+$$$

**Note:** An authentication related token (`p_auth`) must accompany each Liferay
web service invocation. For details, please see the
[Service Security Layers](/develop/tutorials/-/knowledge_base/6-2/service-security-layers)
tutorial. Also, please see the note in the following section to learn how to
find the `p_auth` token value that corresponds to your Liferay Portal session.

$$$

Exceptions abound in life, and there's an exception to the rule that *all*
parameters are required. When using numeric *hints* to match methods, not all of
the parameters are required. Let's look at using hints next. 

## Using Hints When Invoking a Service via URL [](id=using-hints)

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

Here's a real Liferay Portal example:

    http://localhost:8080/api/jsonws/bookmarksfolder/add-folder.4/parent-folder-id/0/name/News?p_auth=[value]

In this example, the hint number is `4` because there are four parameters:
`parentFolderId`, `name`, `description`, and `p_auth`. Since the `description`
parameter is omitted, its value is assumed to be `null`. If you try to invoke
this web service with another hint number such as `3` or `5`, you'll get an
exception since there is no `bookmarks/add-folder` method that takes those
numbers of parameters. `p_auth` is an authentication parameter that's associated
with your Liferay session. See below for more information.

**Important:** When invoking a Liferay web service by entering a URL into your
browser, you must be logged into Liferay with an account that has permission to
invoke the web service. You must also supply an authentication token as a URL
parameter. This authentication token is associated with your browser session and
is called `p_auth`. Using this authentication token helps prevent CSRF attacks.

Here are two easy ways to find the `p_auth` token:

1. Go to Liferay's JSON web services page and click on any service method. The
   value of the `p_auth` token appears under the Execute heading.

2. If you're working from a JavaScript context and have access to the `Liferay`
   object, invoking `Liferay.authToken` provides the value of the `p_auth`
   parameter.

Thus, if the value of your `p_auth` parameter happens to be `n35K1pb2`, for
example, you'd invoke the URL examples above like this:

    http://localhost:8080/api/jsonws/bookmarksfolder/add-folder.4/parent-folder-id/0/name/News?p_auth=n35K1pb2

The remainder of this tutorial omits the `p_auth` parameter from the example
URLs for invoking web services. Remember that you need to include it if you want
to invoke services from your browser!

Next, find out how to pass parameters as part of the URL path.

## Passing Parameters as Part of a URL Path [](id=passing-parameters-as-part-of-a-url-path)

You can pass parameters as part of the URL path. After the service URL, just
specify method parameters in name-value pairs. Parameter names must be formed
from method argument names by converting them from camelCase to names using all
lower case and separated-by-dash. Here's an example of calling one of the
portal's services. This example returns all top-level bookmark folders from the
specified site:

    http://localhost:8080/api/jsonws/bookmarksfolder/get-folders/group-id/20181/parent-folder-id/0

You can pass parameters in any order; it's not necessary to follow the order in
which the arguments are specified in the method signatures. 

When a method name is overloaded, the *best match* will be used. The method that
contains the least number of undefined arguments and is chosen and invoked for
you. 

You can also pass parameters in a URL query, and we'll show you how next. 

## Passing Parameters as a URL Query [](id=passing-parameters-as-a-url-query)

You can pass in parameters as request parameters. Parameter names are specified
as is (e.g. camelCase) and are set equal to their argument values, like this: 

    http://localhost:8080/api/jsonws/bookmarksfolder/add-folder?parentFolderId=0&name=News&description=news

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
conversion process. 

Conversion for common types (e.g., `long`, `String`, `boolean`) is
straightforward. Dates can be given in milliseconds. Locales can be passed as
locale names (e.g. `en` and `en_US`). To pass in an array of numbers, send a
`String` of comma-separated numbers (e.g. the `String` `4,8,15,16,23,42` can be
converted to `long[]` type). You get the picture!

In addition to the common types, arguments can be of type `List` or `Map`. To
pass a `List` argument, send a JSON array. To pass a `Map` argument, send a JSON
object. These types of conversions are performed in two steps:

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

## Sending NULL Values [](id=sending-null-values)

To pass a `null` value for an argument, prefix the parameter name with a dash.
Here's an example: 

    http://localhost:8080/api/jsonws/bookmarksfolder/add-folder/parent-folder-id/0/name/News/-description

Here's the equivalent example using URL query parameters instead of URL path
parameters:

    http://localhost:8080/api/jsonws/bookmarksfolder/add-folder?parentFolderId=0&name=News&-description

The `description` parameter is interpreted as `null`. Although we have this
parameter last in the URL above, it doesn't have to be last.

Null parameters don't have specified values. When a null parameter is passed as
a request parameter, its value is ignored and `null` is used instead: 

    <input type="hidden" name="-description" value=""/>

When using JSON-RPC (see the JSON-RPC section below), you can send null values
explicitly, even without a prefix. Here's an example: 

    "description":null

Now let's learn about encoding parameters. 

## Encoding Parameters [](id=encoding-parameters)

There's a difference between URL encoding and query (i.e., request parameters)
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

For example, suppose you want to pass the value
"&#1057;&#1091;&#1087;&#1077;&#1088;" ("Super" in Cyrillic) to a JSON web
service method. This name first has to be converted to UTF-8 (resulting in array
of 10 bytes) and then encoded for URLs or request parameters. The resulting
value is the string `%D0%A1%D1%83%D0%BF%D0%B5%D1%80` that can be passed to our
service method. When received, this value is first going to be translated to an
array of 10 bytes (URL decoded) and then converted to a UTF-8 string of the 5
original characters.

Did you know you can send files as arguments? Find out how next. 

## Sending Files as Arguments [](id=sending-files-as-arguments)

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

Now we'll show you how to invoke JSON web services using JSON-RPC. 

## JSON-RPC [](id=json-rpc)

You can invoke JSON Web Service using [JSON-RPC](http://json-rpc.org). Most of
the JSON-RPC 2.0 specification is supported in Liferay JSON web services. One
important limitation is that parameters must be passed in as *named* parameters.
Positional parameters are not supported, as there are too many overloaded
methods for convenient use of positional parameters. 

Here's an example of invoking a JSON web service using JSON-RPC: 

    POST http://localhost:8080/api/jsonws/dlapp
    {
        "method":"get-folders",
        "params":{"repositoryId":10172, "parentFolderId":0},
        "id":123,
        "jsonrpc":"2.0"
    }

Let's talk about parameters that are made available to secure JSON web
services by default. 

## Default Parameters [](id=default-parameters)

When accessing *secure* JSON web services (i.e., services for which the user
must be authenticated), some parameters are made available to the web services
by default. Note that as of Liferay 6.2, all of Liferay's web services are
secured by default. Unless you want to change the available parameters' values
to something other than their defaults, you don't have to specify them
explicitly. 

Here are the available default parameters:

- `userId`: The primary key of the authenticated user
- `user`: The full user object
- `companyId`: The primary key of the user's company
- `serviceContext`: The empty service context object 

Let's find out about object parameters next. 

## Object Parameters [](id=object-parameters)

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

In JSON-RPC, here's what it looks like:

    "+foo" : "com.liferay.impl.FooBean"

All the examples above specify a concrete implementation for the `foo` service
method parameter. 

Once you pass in an object parameter, you might want to populate the object.
Find out how next. 

## Inner Parameters [](id=inner-parameters)

When you pass in an object parameter, you'll often need to populate its inner
parameters (i.e., fields). Consider a default parameter `serviceContext` of type
`ServiceContext` (see the
[Understanding ServiceContext](/develop/tutorials/-/knowledge_base/6-2/servicecontext)
tutorial to find out more about this type). To make an appropriate call to
JSONWS, you might need to set the `serviceContext` parameter's
`addGroupPermissions` and `scopeGroupId` fields. 

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

**Tip:** Use inner parameters with object parameters to set inner contents of
created object parameter instances! 

$$$

Next, let's see what values are returned when a JSON web service is invoked. 

## Returned Values [](id=returned-values)

No matter how a JSON web service is invoked, it returns a JSON string that
represents the service method result. Returned objects are *loosely* serialized
to a JSON string and returned to the caller. 

Let's look at some values returned from service calls. We'll create a
`UserGroup` as we did in our SOAP web service client examples. To make it easy,
we'll use the test form provided with the JSON web service in our browser. 

1.  Sign in to your portal as an administrator and then point your browser to
    the JSON web service method that adds a `BookmarksFolder`: 

        http://127.0.0.1:8080/api/jsonws?signature=%2Fbookmarksfolder%2Fadd-folder-4-parentFolderId-name-description-serviceContext

    Alternatively, navigate to it by starting at
    `http://127.0.0.1:8080/api/jsonws` and then scrolling down to the section
    for *BookmarksFolder*. Click *add-folder*. 

2.  In the *parentFolderId* field, enter `0`. Top-level bookmarks folders have a
    `parentFolderId` value of `0`. Set the name to an arbitrary value like
    *News*. Set the description to something like *Created via JSON WS*.

3.  Click *Invoke* and you'll get a result similar to the following: 

        {
          "companyId": 20154,
          "createDate": 1433285384961,
          "description": "Created via JSON WS",
          "folderId": 21898,
          "groupId": 20181,
          "modifiedDate": 1433285384961,
          "name": "News",
          "parentFolderId": 0,
          "resourceBlockId": 304,
          "status": 0,
          "statusByUserId": 0,
          "statusByUserName": "",
          "statusDate": null,
          "treePath": "/21898/",
          "userId": 20198,
          "userName": "Test Test",
          "uuid": "91d76380-8a2c-4965-a7fb-e0c8e1afea4d"
        }

The returned `String` represents the `UserGroup` object you just created,
serialized into a JSON string. To find out more about JSON strings, go to
[json.org](http://www.json.org/). 

## Common JSON Web Service Errors [](id=common-json-web-service-errors)

While working with JSON web services, you may encounter errors. Let's discuss
the following common errors:

-   *Authenticated access required*

    If you see this error, it means you don't have permission to invoke the
    remote service. Double-check that you're signed in as a user with the
    appropriate permissions. If necessary, sign in as an administrator to invoke
    the remote service.

-   *Missing value for parameter* 
    
    If you see this error, you didn't pass a parameter value along with the
    parameter name in your URL path. The parameter value must follow the
    parameter name, like in this example: 

        /api/jsonws/user/get-user-by-id/userId

    The path above specifies a parameter named `userId`, but doesn't specify the
    parameter's value. You can resolve this error by providing the parameter
    value after the parameter name: 

        /api/jsonws/user/get-user-by-id/userId/173

-   *No JSON web service action associated* 

    This is error means no service method could be matched with the provided
    data (method name and argument names). This can be due to various reasons:
    arguments may be misspelled, the method name may be formatted incorrectly,
    etc. Since JSON web services reflect the underlying Java API, any changes in
    the respective Java API will automatically be propagated to the JSON web
    services. For example, if a new argument is added to a method or an existing
    argument is removed from a method, the parameter data must match that of the
    new method signature.

-   *Unmatched argument type* 

    This error appears when you try to instantiate a method argument using an
    incompatible argument type.

-   *Judgment Day*

    We hope you never see this error. It means that Skynet has initiated a
    nuclear war and most of humanity will be wiped out; survivors will need to
    battle *Terminator* cyborgs. If you see this error and survive *Judgment
    Day*, we recommend joining the resistance--they'll likely need good
    developers to support the cause, especially those familiar with time travel. 

    Had you going there, didn't we? 

## Related Topics [](id=related-topics)

[Registering JSON Web Services](/develop/tutorials/-/knowledge_base/6-2/registering-json-web-services)

[JSON Web Services Invoker](/develop/tutorials/-/knowledge_base/6-2/json-web-services-invoker)

[JSON Web Services Invocation Examples](/develop/tutorials/-/knowledge_base/6-2/json-web-services-invocation-examples)
