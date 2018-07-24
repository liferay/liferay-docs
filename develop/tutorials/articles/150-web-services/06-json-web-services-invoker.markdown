# JSON Web Services Invoker [](id=json-web-services-invoker)

With JSON web services, you send a request to a service method with parameters,
and you receive the result as a JSON object. As straightforward as this seems,
it can be improved. In this tutorial, you'll learn how. 

Say you're working with two related objects: a `User` and its corresponding
`Contact`. Normally you first call the user service to get the user object, and
then you use that object's contact ID to call the contact service. This sends
two HTTP requests to get two separate JSON objects. There's no contact
information in the user object (i.e. no `user.contact`). This approach is
suboptimal with respect to performance (sending two HTTP calls) and usability
(manually managing the relationship between two objects). It'd be nicer if you
had a tool to address these inefficiencies. Fortunately, the JSON Web Service
Invoker does just that! 

Liferay's JSON Web Service Invoker helps optimize your JSON Web Services use. 

## Simple Invoker Calls [](id=simple-invoker-calls)

The Invoker is accessible from the following fixed address:

    http://[address]:[port]/api/jsonws/invoke

It only accepts a `cmd` request parameter---this is the Invoker's command. If
the command request parameter is missing, the request body is used as the
command. So you can specify the command by using the request parameter `cmd` or
the request body. 

The Invoker command is a plain JSON map that describes how JSON web services are
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
command is a JSON string, null values can be specified by explicitly using the
`null` keyword or by placing a dash before the parameter name and leaving the
value empty (e.g. `"-param1": ''`).

The example Invoker calls functions exactly the same way as the following
standard JSON Web Service call: 

    /user/get-user-by-id?userId=123&-param1

If you're running Liferay locally on port `8080`, here's how you invoke a JSON
web service: 

1.  Collect your credentials. Here's an example: 

    - Email: `test@liferay.com` 
    - User ID: `20127`
    - Authorization Token: `htXjvt5d`

2.  Invoke the service: 

        http://localhost:8080/api/jsonws/invoke?cmd={%22/user/get-user-by-id%22:{%22userId%22:20172}}&p_auth=htXjvt5d

This URL uses the following JSON map. Note that it's supplied in the URL by 
using the `cmd` URL parameter:

    {
        "/user/get-user-by-id": {
            "userId": 20172
        }
    }

In the URL, the double quotes are URL-encoded. To find your user ID, check the
User Menu under *My Account* &rarr; *Account Settings*. To find your `p_auth`
authentication token, navigate to 
[Liferay's JSON web services API page](http://localhost:8080/api/jsonws) 
and click on any method in the list. The value of your `p_auth` token appears
under the Execute heading along with any other parameters of the selected API
method. 

Use JSON syntax to supply values for objects and arrays as parameters. To supply
a value for an object, use curly brackets: `{` and `}`. To supply a value for an
array, use square brackets: `[` and `]`. 

If you want to pass an array as a parameter using the same credential token as
above, here's an example how, using two vocabularies with vocabulary IDs of
`20783` and `20784`:

    http://localhost:8080/api/jsonws/invoke?cmd={%22/assetvocabulary/get-vocabularies%22:{%22vocabularyIds%22:[20783,20784]}}&p_auth=htXjvt5d

This URL uses the following JSON map:

    {
        "/assetvocabulary/get-vocabularies": {
            "vocabularyIds": [20783,20784]
        }
    }

As before, the double quotes in the URL are URL-encoded. Also, the
`vocabularyIds` parameter is an array, so its value is supplied as a JSON array.

Finally, here's one more Liferay JSON web service invoker example that
demonstrates how to pass an object containing an array as a parameter:

    http://localhost:8080/api/jsonws/invoke?cmd={%22/user/add-user%22:{%22companyId%22:20127,%22autoPassword%22:false,%22password1%22:%22test%22,%22password2%22:%22test%22,%22autoScreenName%22:false,%22screenName%22:%22joe.bloggs%22,%22emailAddress%22:%22joe.bloggs@liferay.com%22,%22facebookId%22:0,%22openId%22:%22%22,%22locale%22:%22en_US%22,%22firstName%22:%22Joe%22,%22middleName%22:%22T%22,%22lastName%22:%22Bloggs%22,%22prefixId%22:0,%22suffixId%22:0,%22male%22:true,%22birthdayMonth%22:1,%22birthdayDay%22:1,%22birthdayYear%22:1970,%22jobTitle%22:%22Tester%22,%22groupIds%22:null,%22organizationIds%22:null,%22roleIds%22:null,%22userGroupIds%22:null,%22sendEmail%22:false,%22serviceContext%22:{%22assetTagNames%22:[%22test%22]}}}&p_auth=htXjvt5d

This URL uses the following JSON map:

    {
        "/user/add-user": {
            "companyId": 20127,
            "autoPassword": false,
            "password1": "test",
            "password2": "test",
            "autoScreenName": false,
            "screenName": "joe.bloggs",
            "emailAddress": "joe.bloggs@liferay.com",
            "facebookId": 0,
            "openId": "",
            "locale": "en_US",
            "firstName": "Joe",
            "middleName": "T",
            "lastName": "Bloggs",
            "prefixId": 0,
            "suffixId": 0,
            "male": true,
            "birthdayMonth": 1,
            "birthdayDay": 1,
            "birthdayYear": 1970,
            "jobTitle": "Tester",
            "groupIds": null,
            "organizationIds": null,
            "roleIds": null,
            "userGroupIds": null,
            "sendEmail": false,
            "serviceContext": {"assetTagNames":["test"]}
        }
    }

The `serviceContext` is the object containing an array in this example. It
contains the array `assetTagNames`.

Of course, the JSON Web Service Invoker handles calls to plugin methods as well:

    {
        "/suprasurf/hello-world": {
            "worldName": "Mavericks"
        }
    }

The code above calls the (fictitious) SupraSurf application's remote service. 

You can use variables to reference objects returned from service calls. Variable 
names must start with a dollar sign, `$`. In the previous example, the service
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

## Nesting Service Calls [](id=nesting-service-calls)

With nested service calls, you can bind information from related objects 
together in a JSON object. You can call other services within the same HTTP 
request and nest returned objects in a convenient way. Here's a nested service 
call in action: 

    {
        "$user = /user/get-user-by-id": {
            "userId": 123,
            "$contact = /contact/get-contact-by-id": {
                "@contactId": "$user.contactId"
            }
        }
    }

This command defines two service calls: the contact object returned from the
second service call is nested in (i.e. injected into) the user object, as a
property named `contact`. Now you can bind the user and his or her contact 
information together! 

Now you'll see what the Invoker does in the background when using a single HTTP
request to make the preceding nested service call: 

- First, the Invoker calls the Java service mapped to `/user/get-user-by-id`,
  passing in a value for the `userId` parameter. 
- Next, the resulting user object is assigned to the variable `$user`. 
- The nested service calls are invoked. 
- The Invoker calls the Java service mapped to `/contact/get-contact-by-id` by 
  using the `contactId` parameter, with the `$user.contactId` value from the
  object `$user`. 
- The resulting contact object is assigned to the variable `$contact`. 
- Lastly, the Invoker injects the contact object referenced by `$contact` into
  the user object's property named `contact`. 

+$$$

**Note:** You must flag parameters that take values from existing variables. To 
flag a parameter, insert the `@` prefix before the parameter name. 

$$$

Next, you'll learn about filtering object properties so that only the properties
you need are returned when you invoke a service. 

## Filtering Results [](id=filtering-results)

Many of Liferay's model objects are rich with properties. If you only need a 
handful of an object's properties for your business logic, making a web service 
invocation that returns all of an object's properties is a waste of network 
bandwidth. With the JSON Web Service Invoker, you can define a whitelist of 
properties: only the specific properties you request in the object are returned 
from your web service call. Here's how you whitelist the properties you need: 

    {
        "$user[firstName,emailAddress] = /user/get-user-by-id": {
            "userId": 123,
            "$contact = /contact/get-contact-by-id": {
                "@contactId": "$user.contactId"
            }
        }
    }

In this example, the returned user object has only the `firstName` and
`emailAddress` properties (it still has the `contact` property, too). To specify 
whitelist properties, place the properties in square brackets (e.g.,
`[whiteList]`) immediately following the name of your variable. 

Next, you'll learn about making calls in batch. 

## Making Batch Calls [](id=making-batch-calls)

When nesting service calls, you invoke multiple services with a single HTTP
request. This is helpful for gathering related information from the service call
results, but it you can also use a single request to invoke multiple unrelated
service calls by batching service calls together to improve performance. Do this
by passing in a JSON array of commands: 

    [
        {/* first command */},
        {/* second command */}
    ]

The result is a JSON array populated with results from each command. The 
commands are collectively invoked in a single HTTP request, one after another. 

Great! Now you know how to use Liferay's JSON Web Service Invoker to simplify 
your JSON calls to Liferay. 

## Related Topics [](id=related-topics)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-1/invoking-remote-services)

[Invoking JSON Web Services](/develop/tutorials/-/knowledge_base/7-1/invoking-json-web-services)

[JSON Web Services Invocation Examples](/develop/tutorials/-/knowledge_base/7-1/json-web-services-invocation-examples)
