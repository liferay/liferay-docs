# Invoking Liferay Services [](id=invoking-liferay-services)

Liferay has many web services ready to use out of the box. These services include retrieving data and information about various assets, creating new assets, and even editing existing assets.

To see a comprehensive list of the available web services, start up a bundle and navigate to http://localhost:8080/api/jsonws. This list will include any custom web services that have been deployed to the bundle.

These services are useful for creating single page applications, and can even be used to create custom frontends both inside and outside of Liferay Portal.

## Invoking Web Services via JavaScript

In @product_ver@ there is a global JavaScript object named `Liferay` that has many useful utilities. One method is `Liferay.Service` which is used for invoking JSON web services.

The `Liferay.Service` method takes four possible arguments.

**Required**

1. service {string|object}: Either the service name, or an object with the keys as the service to call, and the value as the service configuration object.

**Optional**

2. data {object|node|string}: The data to send to the service. If the object passed is the ID of a form or a form element, the form fields will be serialized and used as the data.
3. successCallback {function}: A function to execute when the server returns a response. It receives a JSON object as it's first parameter.
4. exceptionCallback {function}: A function to execute when the response from the server contains a service exception. It receives a the exception message as it's first parameter.

One of the major benefits of using the `Liferay.Service` method vs. a standard ajax request is that it handles the authentication.

```javascript
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
```

In this example we are retrieving information about a user by passing in the `companyId` and `emailAddress` of the user in question. Response data resembles the following.

```JSON
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
```

## Batching Requests [](id=batching-requests)

Another format for invoking the `Liferay.Service` method is by passing an object with the keys as the service to call, and the value as the service configuration object.

```javascript
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
```

With this format you can actually invoke multiple services with the same request by passing in an array of service objects.

```javascript
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
		// obj is now an array of response obejcts
		// obj[0] == /user/get-user-by-email-address data
		// obj[1] == /role/get-user-roles data

		console.log(obj);
	}
);
```

## Nesting Requests [](id=nesting-requests)

With nested service calls, you can bind information from related objects together in a JSON object. You can call other services within the same HTTP request and nest returned objects in a convenient way.

You can use variables to reference objects returned from service calls. Variable names must start with a dollar sign, $.

In this example we will retrieve user data with `/user/get-user-by-id`, and use the contactId returned from that service to then invoke `/contact/get-contact` in the same request.

You must flag parameters that take values from existing variables. To flag a parameter, insert the `@` prefix before the parameter name.

```javascript
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
```

Here is what the response data would look like for that request.

```JSON
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
```

## Filtering Results [](id=filtering-results)

If you don't want all the properties returned by a service, you can define a whitelist of properties: only the specific properties you request in the object are returned from your web service call. Here’s how you whitelist the properties you need:

```javascript
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
```

To specify whitelist properties, you simply place the properties in square brackets (e.g., [whiteList]) immediately followinging the name of your variable.

Here is what the filtered response looks like.

```JSON
{
	"firstName": "Test",
	"emailAddress": "test@liferay.com"
}
```

## Inner Parameters [](id=inner-parameters)

When you pass in an object parameter, you’ll often need to populate its inner parameters (i.e., fields). Consider a default parameter `serviceContext` of type `ServiceContext`. To make an appropriate call to JSON web services you might need to set `serviceContext` fields such as`scopeGroupId`.

```javascript
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
```
