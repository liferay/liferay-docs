# Invoking JSON Web Services [](id=invoking-json-web-services)

You can invoke Liferay's JSON web service API in languages other than Java, such
as Beanshell, Groovy, JavaScript, Python, and Ruby. You can also invoke it via 
URL or 
[cURL](http://curl.haxx.se/). 
Additionally, Liferay provides a handy JSON web services page that allows you to 
browse and invoke service methods. 

If you're running Liferay locally on port 8080, you can find the JSON web 
services page at 
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws). 
You can use this page to generate example code for invoking web services. When 
you invoke a service on this page as described in the tutorial 
[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-0/invoking-remote-services),
the JSON result of your service invocation appears. Click on the *JavaScript
Example*, *curl Example*, or *URL Example* tabs to see different ways of 
invoking the web service. 

![Figure 1: When you invoke a service from Liferay's JSON web services page, you can view the result of your service invocation as well as example code for invoking the service via JavaScript, curl, or URL.](../../../images/jsonws-simple-example.png)

This tutorial explains general techniques for working with JSON web services
and includes details about invoking them via URL. For examples of invoking 
Liferay's JSON web services via JavaScript, URL, and cURL, see the
[JSON Web Services Invocation Examples](/develop/tutorials/-/knowledge_base/7-0/json-web-services-invocation-examples) 
tutorial. 

There are multiple ways to invoke a JSON web service since there are different
ways to supply parameters. In this tutorial, you'll learn how to include
parameters in web service invocations. First, you must understand how your 
invocation is matched to a method, especially in the case of overloaded service
methods.

The general rule is that you provide the service method's name and *all* the 
service method's parameters--even if you only provide `null` values. It's 
important to provide all parameters, but it doesn't matter *how* you do it
(e.g., as part of the URL line, as request parameters, etc.). The order of the
parameters doesn't matter either. 

+$$$

**Note:** An authentication related token (`p_auth`) must accompany each
Liferay web service invocation. For details, see the 
[Service Security Layers](/develop/tutorials/-/knowledge_base/7-0/service-security-layers) 
tutorial. Also, see the note in the following section to learn how to find the 
`p_auth` token value that corresponds to your Liferay session. 

$$$

Exceptions abound in life, and there's an exception to the rule that *all* 
parameters are required. When using numeric *hints* to match methods, not all of 
the parameters are required. You'll learn to use hints next. 

## Using Hints When Invoking a Service via URL [](id=using-hints-when-invoking-a-service-via-url)

Adding numeric hints lets you specify how many method arguments a service has.
If you don't specify an argument for a parameter, it's automatically passed in 
as `null`. Syntactically, you can add hints as numbers separated by a dot in the 
method name. Here's an example: 

    /foo/get-bar.2/param1/123/-param2

Here, the `.2` is a numeric hint specifying that only service methods with two
arguments are matched; others will be ignored for matching. 

There's an important distinction to make between matching with hints and 
matching without hints. When a hint is specified, you don't have to specify all
of the parameters. Any missing arguments are treated as `null`. The previous
example may be called like this:

    /foo/get-bar.2/param1/123

In this example, `param2` will automatically be set to `null`. 

Here's a real Liferay example:

    http://localhost:8080/api/jsonws/bookmarks.bookmarksfolder/add-folder.4/parent-folder-id/0/name/News?p_auth=[value]

In this example, the hint number is `4` because there are four parameters:
`parentFolderId`, `name`, `description`, and `p_auth`. Since the `description`
parameter is omitted, its value is assumed to be `null`. If you try to invoke
this web service with another hint number such as `3` or `5`, you'll get an
exception since there is no `bookmarks/add-folder` method that takes that number 
of parameters. The authentication parameter `p_auth` is associated with your 
Liferay session. See below for more information. 

+$$$

**Important:** When invoking a Liferay web service by entering a URL into your
browser, you must be logged into Liferay with an account that has permission to
invoke the web service. You must also supply an authentication token as a URL
parameter. This authentication token is associated with your browser session and
is called `p_auth`. Using this authentication token helps prevent CSRF attacks. 

$$$

Here are two easy ways to find the `p_auth` token: 

1. Go to Liferay's JSON web services page and click on any service method. The 
   value of the `p_auth` token appears under the Execute heading. 

2. If you're working from a JavaScript context and have access to the `Liferay` 
   object, invoking `Liferay.authToken` provides the value of the `p_auth` 
   parameter. 

For example, if your `p_auth` parameter's value is `n35K1pb2`, you could invoke 
the preceding URL examples like this: 

    http://localhost:8080/api/jsonws/bookmarks.bookmarksfolder/add-folder.4/parent-folder-id/0/name/News?p_auth=n35K1pb2

For simplicity, the remainder of this tutorial omits the `p_auth` parameter from 
the example URLs for invoking web services. Remember that you must include it if 
you want to invoke services from your browser! 

Next, you'll learn how to pass parameters as part of the URL path. 

## Passing Parameters as Part of a URL Path [](id=passing-parameters-as-part-of-a-url-path)

To pass method parameters as part of the URL path, specify them in name-value 
pairs after the service URL. Parameter names must be formed from method argument 
names by converting them from camel case to names that use all lower case, 
dash-separated words. For example, this returns all top-level bookmark folders 
from the specified site: 

    http://localhost:8080/api/jsonws/bookmarks.bookmarksfolder/get-folders/group-id/20181/parent-folder-id/0

You can pass parameters in any order; it's not necessary to follow the order in
which the arguments are specified in the method signatures. 

When a method name is overloaded, the *best match* will be used. The method that
contains the least number of undefined arguments is chosen and invoked for you. 

You can also pass parameters in a URL query. The next section shows you how to 
do this. 

## Passing Parameters as a URL Query [](id=passing-parameters-as-a-url-query)

To pass in parameters as request parameters, specify them as-is (camel case) and 
set them equal to their argument value. For example: 

    http://localhost:8080/api/jsonws/bookmarks.bookmarksfolder/add-folder?parentFolderId=0&name=News&description=news

As with passing parameters as part of a URL path, the parameter order is not 
important and the best match rule applies for overloaded methods. 

Now you know a few different ways to pass parameters. It's also possible to pass
URL parameters in a mixed way. For example, some can be part of the URL path 
while others can be specified as request parameters. 

Parameter values are sent as strings using the HTTP protocol. Before a matching
Java service method is invoked, each parameter value is converted from a 
`String` to its target Java type. Liferay uses a third party open source library
to convert each object to its appropriate common type. Although it's possible to 
add or change the conversion for certain types, this tutorial only covers the 
standard conversion process. 

Conversion for common types (e.g., `long`, `String`, `boolean`) is 
straightforward. Dates can be given in milliseconds. Locales can be passed as
locale names (e.g. `en` and `en_US`). To pass in an array of numbers, send a
string of comma-separated numbers (e.g. the string `4,8,15,16,23,42` can be
converted to `long[]` type). You get the picture! 

In addition to the common types, arguments can be of type `List` or `Map`. To
pass a `List` argument, send a JSON array. To pass a `Map` argument, send a JSON
object. These types of conversions are performed in two steps: 

- *Step 1--JSON deserialization*: JSON arrays are converted into `List<String>`, 
  and JSON objects are converted to `Map<String, String>`. For security reasons,
  it's forbidden to instantiate any type within JSON deserialization. 
- *Step 2--Generification*: Each `String` element of the `List` and `Map` is
  converted to its target type (the argument's generic Java type specified in
  the method signature). This step is only executed if the Java argument type
  uses generics. 

As an example, consider the conversion of a `String` array `[en,fr]` as JSON web 
service parameters for a `List<Locale>` Java method argument type: 

- *Step 1--JSON deserialization*: The JSON array is deserialized to a
  `List<String>` containing `String`s `en` and `fr`.

- *Step 2--Generification*: Each `String` is converted to the `Locale` (the
  generic type), resulting in the `List<Locale>` Java argument type. 

Next, you'll learn how to specify an argument as `null`. 

## Sending Null Values [](id=sending-null-values)

To pass a `null` value for an argument, prefix the parameter name with a dash. 
Here's an example: 

    http://localhost:8080/api/jsonws/bookmarks.bookmarksfolder/add-folder/parent-folder-id/0/name/News/-description

Here's the equivalent example using URL query parameters instead of URL path
parameters:

    http://localhost:8080/api/jsonws/bookmarks.bookmarksfolder/add-folder?parentFolderId=0&name=News&-description

The `description` parameter is interpreted as `null`. Note that this parameter 
doesn't have to be last in the URL. 

Null parameters don't have specified values. When a null parameter is passed as
a request parameter, its value is ignored and `null` is used instead:

    <input type="hidden" name="-description" value=""/>

When using JSON-RPC (see the JSON-RPC section below), you can send null values
explicitly, even without a prefix. Here's an example:

    "description":null

Next, you'll learn about encoding parameters. 

## Encoding Parameters [](id=encoding-parameters)

There's a difference between URL encoding and query (i.e., request parameters)
encoding. The difference lies in how the space character is encoded. When the
space character is part of the URL path, it's encoded as `%20`; when it's part
of the query it's encoded as a plus sign (`+`).

All these encoding rules apply to ASCII and international (non-ASCII)
characters. Since Liferay works in UTF-8 mode, parameter values must be encoded 
as UTF-8 values. Liferay doesn't decode request URLs and request parameter 
values to UTF-8 itself; it relies on the web server layer. When
accessing services through JSON-RPC, encoding parameters to UTF-8 isn't
enough--you need to send the encoding type in a Content-Type header (e.g.
`Content-Type : "text/plain; charset=utf-8"`).

For example, suppose you want to pass the value
"&#1057;&#1091;&#1087;&#1077;&#1088;" ("Super" in Cyrillic) to a JSON web
service method. This name first has to be converted to UTF-8 (resulting in an 
array of 10 bytes) and then encoded for URLs or request parameters. The 
resulting value is the string `%D0%A1%D1%83%D0%BF%D0%B5%D1%80` that can be 
passed to your service method. When received, this value is first translated to 
an array of 10 bytes (URL decoded), and then converted to a UTF-8 string of the 
5 original characters.

Next, you'll learn how to send files as arguments.

## Sending Files as Arguments [](id=sending-files-as-arguments)

Files can be uploaded using multi-part forms and requests. Here's an example:

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

This is a common upload form that invokes the `DLAppService` class's 
`addFileEntry` method. 

Now you'll learn how to invoke JSON web services using JSON-RPC.

## JSON-RPC [](id=json-rpc)

You can invoke JSON Web Service using 
[JSON-RPC](http://json-rpc.org). Most of the JSON-RPC 2.0 specification is 
supported in Liferay JSON web services. One important limitation is that 
parameters must be passed in as *named* parameters. Positional parameters aren't 
supported, as there are too many overloaded methods for convenient use of 
positional parameters. 

Here's an example of invoking a JSON web service using JSON-RPC:

    POST http://localhost:8080/api/jsonws/dlapp
    {
        "method":"get-folders",
        "params":{"repositoryId":10172, "parentFolderId":0},
        "id":123,
        "jsonrpc":"2.0"
    }

Next, you'll learn about parameters that are made available to secure JSON web
services by default. 

## Default Parameters [](id=default-parameters)

When accessing secure JSON web services (i.e., services for which the user must 
be authenticated), some parameters are made available to the web services
by default. All of Liferay's web services are secured by default. Unless you 
want to change the available parameters' values to something other than their 
defaults, you don't have to specify them explicitly.

Here are the available default parameters:

- `userId`: The primary key of the authenticated user
- `user`: The full user object
- `companyId`: The primary key of the user's company
- `serviceContext`: The empty service context object 

Next, you'll learn about object parameters. 

## Object Parameters [](id=object-parameters)

Most services accept simple parameters like numbers and strings. However,
sometimes you might need to provide an object (a non-simple type) as a service
parameter.

To create an instance of an object parameter, prefix the parameter with a plus 
sign, `+` and don't assign it any other parameter value. This is similar to 
specifying a null parameter by prefixing the parameter with a dash symbol, `-`. 

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
parameter name, followed by specifying the concrete implementation class. Here's 
an example: 

    /jsonws/foo/get-bar/zap-id/10172/start/0/end/1/+foo:com.liferay.impl.FooBean

Here's another way of doing it:

    <input type="hidden" name="+foo:com.liferay.impl.FooBean" value=""/>

The examples above specify that a `com.liferay.impl.FooBean` object, presumed to
implement the class of the parameter named `foo`, is created. 

You can also set a concrete implementation as a value. Here's an example:

    <input type="hidden" name="+foo" value="com.liferay.impl.FooBean"/>

In JSON-RPC, here's what it looks like:

    "+foo" : "com.liferay.impl.FooBean"

All the preceding examples specify a concrete implementation for the `foo` 
service method parameter. 

Once you pass in an object parameter, you might want to populate the object. 
Find out how next. 

## Inner Parameters [](id=inner-parameters)

<!-- Link to ServiceContext tutorial once it exists -->
When you pass in an object parameter, you'll often need to populate its inner
parameters (i.e., fields). Consider a default parameter `serviceContext` of type
`ServiceContext`. To make an appropriate call to JSONWS, you might need to set 
the `serviceContext` parameter's `addGroupPermissions` and `scopeGroupId` 
fields. 

You can pass inner parameters by using dot notation to specify them. Just append 
the name of the parameter with a dot (i.e., a period, `.`), followed by the 
inner parameter's name. For the `ServiceContext` inner parameters mentioned 
previously, you'll specify `serviceContext.addGroupPermissions` and 
`serviceContext.scopeGroupId`. These are recognized as inner parameters and 
their values are injected into existing parameters before the API service method 
is executed. 

Inner parameters aren't counted as regular parameters for matching methods and 
are ignored during matching. 

+$$$

**Tip:** Use inner parameters with object parameters to set inner contents of
created object parameter instances!

$$$

Next, let's see what values are returned when a JSON web service is invoked.

## Returned Values [](id=returned-values)

No matter how a JSON web service is invoked, it returns a JSON string that
represents the service method result. Returned objects are *loosely* serialized
to a JSON string and returned to the caller.

Now you'll look at some values returned from service calls. You'll create a 
`UserGroup` as in the SOAP web service client examples. To make it easy, you'll 
use the test form provided with the JSON web service in our browser. 

1.  Sign in to a local Liferay instance as an administrator and then point your 
    browser to the JSON web service method that adds a `BookmarksFolder`:

        http://localhost:8080/api/jsonws?contextName=bookmarks&signature=%2Fbookmarks.bookmarksfolder%2Fadd-folder-4-parentFolderId-name-description-serviceContext

    Alternatively, navigate to it by starting at
    `http://localhost:8080/api/jsonws` and then scrolling down to the section
    for *BookmarksFolder*. Then click *add-folder*.

2.  In the `parentFolderId` field, enter `0`. Top-level bookmarks folders have a
    `parentFolderId` value of `0`. Set the name to an arbitrary value like
    *News*. Set the description to something like *Created via JSON WS*.

3.  Click *Invoke* and you'll get a result similar to the following:
        
        {
          "companyId": "20202",
          "createDate": 1459969296960,
          "description": "Created via JSON WS",
          "folderId": "31001",
          "groupId": "20233",
          "lastPublishDate": null,
          "modifiedDate": 1459969297005,
          "name": "News",
          "parentFolderId": "0",
          "resourceBlockId": "1",
          "status": 0,
          "statusByUserId": "0",
          "statusByUserName": "",
          "statusDate": null,
          "treePath": "/31001/",
          "userId": "20250",
          "userName": "Joe Bloggs",
          "uuid": "0682170c-f9d7-f295-aa67-26ceea37a6e5"
        }

The returned `String` represents the `BookmarksFolder` object you just created,
serialized into a JSON string. To find out more about JSON strings, go to
[json.org](http://www.json.org/). 

<!-- Consider adding a new paragraph here demonstrating how to use Liferay's 
JSONFactoryUtil class. This class provides methods that allow Liferay developers 
to serialize objects (convert a Liferay object such as a user to JSON string 
format), deserialize JSON strings, and convert between JSON and XML. It also 
allows developers to create new JSONObjects and add manipulate them. -->

## Common JSON Web Service Errors [](id=common-json-web-service-errors)

While working with JSON web services, you may encounter errors. Some common 
errors are listed here: 

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
    data (method name and argument names). This can be due to various reasons. 
    For example, arguments may be misspelled, the method name may be formatted 
    incorrectly, and so on. Since JSON web services reflect the underlying Java 
    API, any changes in the respective Java API are automatically propagated to 
    the JSON web services. For example, if a new argument is added to a method 
    or an existing argument is removed from a method, the parameter data must 
    match that of the new method signature. 

-   *Unmatched argument type*

    This error appears when you try to instantiate a method argument using an
    incompatible argument type. 

## Related Topics [](id=related-topics)

[JSON Web Services Invoker](/develop/tutorials/-/knowledge_base/7-0/json-web-services-invoker)

[JSON Web Services Invocation Examples](/develop/tutorials/-/knowledge_base/7-0/json-web-services-invocation-examples)

[Service Security Layers](/develop/tutorials/-/knowledge_base/7-0/service-security-layers)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-0/invoking-remote-services)
