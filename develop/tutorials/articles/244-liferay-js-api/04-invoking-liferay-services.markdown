# Invoking Liferay Services [](id=invoking-liferay-services)

@product@ provides many web services out-of-the-box to you. These services 
include retrieving data and information about various assets, creating new 
assets, and even editing existing assets.

To see a comprehensive list of the available web services, start up a bundle and 
navigate to `http://localhost:8080/api/jsonws`. This list includes any custom 
web services that have been deployed to the bundle. These services are useful 
for creating single page applications, and can even be used to create custom 
front-ends, both inside and outside of @product@.

This tutorial explains how to invoke these web services using JavaScript.

Go ahead and get started.

## Invoking Web Services via JavaScript [](id=invoking-web-services-via-javascript)

@product-ver@ contains a global JavaScript object called `Liferay` that has 
many useful utilities. One method is `Liferay.Service`, which is used for 
invoking JSON web services.

The `Liferay.Service` method takes four possible arguments:

**service {string|object}:** Either the service name, or an object with the keys 
as the service to call, and the value as the service configuration object.
(Required)

**data {object|node|string}:** The data to send to the service. If the object 
passed is the ID of a form or a form element, the form fields will be serialized 
and used as the data.

**successCallback {function}:** A function to execute when the server returns a 
response. It receives a JSON object as it's first parameter.

**exceptionCallback {function}:** A function to execute when the response from 
the server contains a service exception. It receives an exception message as 
it's first parameter.

One of the major benefits of using the `Liferay.Service` method versus using a
standard AJAX request is that it handles the authentication for you.

Below is an example configuration of the `Liferay.Service` method:

    Liferay.Service(
            '/user/get-user-by-email-address',
            {
                    companyId: Liferay.ThemeDisplay.getCompanyId(),
                    emailAddress: 'test@liferay.com'
            },
            function(obj) {
                    console.log(obj);
            }
    );

The example above retrieves information about a user by passing in the 
`companyId` and `emailAddress` of the user in question. The response data
resembles the following JSON object:

    {
            "agreedToTermsOfUse": true,
            "comments": "",
            "companyId": "20116",
            "contactId": "20157",
            "createDate": 1471990639779,
            "defaultUser": false,
            "emailAddress": "test@liferay.com",
            "emailAddressVerified": true,
            "facebookId": "0",
            "failedLoginAttempts": 0,
            "firstName": "Test",
            "googleUserId": "",
            "graceLoginCount": 0,
            "greeting": "Welcome Test Test!",
            "jobTitle": "",
            "languageId": "en_US",
            "lastFailedLoginDate": null,
            "lastLoginDate": 1471996720765,
            "lastLoginIP": "127.0.0.1",
            "lastName": "Test",
            "ldapServerId": "-1",
            "lockout": false,
            "lockoutDate": null,
            "loginDate": 1472077523149,
            "loginIP": "127.0.0.1",
            "middleName": "",
            "modifiedDate": 1472077523149,
            "mvccVersion": "7",
            "openId": "",
            "portraitId": "0",
            "reminderQueryAnswer": "test",
            "reminderQueryQuestion": "what-is-your-father's-middle-name",
            "screenName": "test",
            "status": 0,
            "timeZoneId": "UTC",
            "userId": "20156",
            "uuid": "c641a7c9-5acb-aa68-b3ea-5575e1845d2f"
    }
 
Now that you know how to send an individual request, you're ready to run batch
requests.

## Batching Requests [](id=batching-requests)

Another format for invoking the `Liferay.Service` method is by passing an object 
with the keys as the service to call and the value as the service configuration 
object.

Below is an example configuration for a batch request:

    Liferay.Service(
            {
                    '/user/get-user-by-email-address': {
                            companyId: Liferay.ThemeDisplay.getCompanyId(),
                            emailAddress: 'test@liferay.com'
                    }
            },
            function(obj) {
                    console.log(obj);
            }
    );

You can use this format to invoke multiple services with the same request by 
passing in an array of service objects. Here's an example:

    Liferay.Service(
            [
                    {
                            '/user/get-user-by-email-address': {
                                    companyId: Liferay.ThemeDisplay.getCompanyId(),
                                    emailAddress: 'test@liferay.com'
                            }
                    },
                    {
                            '/role/get-user-roles': {
                                    userId: Liferay.ThemeDisplay.getUserId()
                            }
                    }
            ],
            function(obj) {
                    // obj is now an array of response objects
                    // obj[0] == /user/get-user-by-email-address data
                    // obj[1] == /role/get-user-roles data
    
                    console.log(obj);
            }
    );
 
Next you can learn how to nest your requests.

## Nesting Requests [](id=nesting-requests)

Nested service calls allow you to bind information from related objects together
in a JSON object. You can call other services in the same HTTP request and
conveniently nest returned objects.

You can use variables to reference objects returned from service calls. Variable 
names must start with a dollar sign (`$`).

The example in this section retrieves user data with `/user/get-user-by-id`, and 
uses the `contactId` returned from that service to then invoke 
`/contact/get-contact` in the same request.

+$$$

**Note:** You must flag parameters that take values from existing variables. To 
flag a parameter, insert the `@` prefix before the parameter name.

$$$

Below is an example configuration that demonstrates the concepts covered in this
section:

    Liferay.Service(
            {
                    "$user = /user/get-user-by-id": {
                            "userId": Liferay.ThemeDisplay.getUserId(),
                            "$contact = /contact/get-contact": {
                                    "@contactId": "$user.contactId"
                            }
                    }
            },
            function(obj) {
                    console.log(obj);
            }
    );

Here is what the response data would look like for the request above:

    {
            "agreedToTermsOfUse": true,
            "comments": "",
            "companyId": "20116",
            "contactId": "20157",
            "createDate": 1471990639779,
            "defaultUser": false,
            "emailAddress": "test@liferay.com",
            "emailAddressVerified": true,
            "facebookId": "0",
            "failedLoginAttempts": 0,
            "firstName": "Test",
            "googleUserId": "",
            "graceLoginCount": 0,
            "greeting": "Welcome Test Test!",
            "jobTitle": "",
            "languageId": "en_US",
            "lastFailedLoginDate": null,
            "lastLoginDate": 1472231639378,
            "lastLoginIP": "127.0.0.1",
            [...]
            "screenName": "test",
            "status": 0,
            "timeZoneId": "UTC",
            "userId": "20156",
            "uuid": "c641a7c9-5acb-aa68-b3ea-5575e1845d2f",
            "contact": {
                    "accountId": "20118",
                    "birthday": 0,
                    [...]
                    "createDate": 1471990639779,
                    "emailAddress": "test@liferay.com",
                    "employeeNumber": "",
                    "employeeStatusId": "",
                    "facebookSn": "",
                    "firstName": "Test",
                    "lastName": "Test",
                    "male": true,
                    "middleName": "",
                    "modifiedDate": 1471990639779,
                    [...]
                    "userName": ""
            }
    }
 
Now that you know how to process requests, you can learn how to filter the
results next.

## Filtering Results [](id=filtering-results)

If you don't want all the properties returned by a service, you can define a 
whitelist of properties. This returns only the specific properties you request
in the object.

Below is an example of whitelisting properties:

    Liferay.Service(
            {
                    '$user[emailAddress,firstName] = /user/get-user-by-id': {
                            userId: Liferay.ThemeDisplay.getUserId()
                    }
            },
            function(obj) {
                    console.log(obj);
            }
    );

To specify whitelist properties, place the properties in square brackets 
(e.g., `[whiteList]`) immediately following the name of your variable. The example
above requests only the `emailAddress` and `firstName` of the user.

Below is the filtered response:

    {
            "firstName": "Test",
            "emailAddress": "test@liferay.com"
    }

Next you can learn how to populate the inner parameters of the request.
 
## Inner Parameters [](id=inner-parameters)

When you pass in an object parameter, you'll often need to populate its inner 
parameters (i.e., fields).

Consider a default parameter `serviceContext` of type `ServiceContext`. To make 
an appropriate call to JSON web services you might need to set `serviceContext` 
fields such as`scopeGroupId`, as shown below:

    Liferay.Service(
            '/example/some-web-service',
            {
                    serviceContext: {
                            scopeGroupId: 123
                    }
            },
            function(obj) {
                    console.log(obj);
            }
    );
 
Now you know how to invoke Liferay services!

## Related Topics [](id=related-topics)

[Liferay JavaScript APIs](/develop/tutorials/-/knowledge_base/7-0/liferay-javascript-apis)
