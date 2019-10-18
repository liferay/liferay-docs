---
header-id: jax-rs
---

# JAX-RS

[TOC levels=1-4]

JAX-RS web services work in Liferay modules the same way they work outside of
Liferay. The only difference is that you must register the class in the OSGi
framework. Liferay makes this easy by providing a template. 

[Create a project](/docs/7-2/reference/-/knowledge_base/r/creating-a-project)
and choose the *rest* template. 

The class that's generated contains a working JAX-RS web service. You can deploy
it and use it immediately. 

While it's beyond the scope of this article to cover 
[JAX-RS Whiteboard](https://blog.osgi.org/2018/03/osgi-r7-highlights-jax-rs-whiteboard.html)
in its entirety, essentially it's JAX-RS unchanged except for configuration
properties in the `@Component` annotation. These properties declare three things: 

1.  The endpoint for the service

2.  The service name as it appears in the OAuth 2.0 configuration

3.  (Optional) Properties you may want to set for further configuration.

The generated class contains this configuration: 

```java
@Component( 
        property = { 
            JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/greetings", 
            JaxrsWhiteboardConstants.JAX_RS_NAME + "=Greetings.Rest"
        }, 
        service = Application.class)
```

This configuration registers the service at this endpoint: 

    https://[server-name]:[port]/o/greetings

If you're testing this locally on Tomcat, the URL is

    https://localhost:8080/o/greetings

As you might guess, you don't have access to the service by just calling the URL
above. You must authenticate first, which you'll learn how to do next. 

## Authenticating to JAX-RS Web Services

Authentication during development can be done through Basic Authentication or
portal sessions, but you don't want to leave that enabled for production. For
production, you want OAuth 2.0. Here's how to configure JAX-RS authentication. 

### During Development: Basic Auth

When you deploy a JAX-RS application, an 
[Auth Verifier](/docs/7-2/deploy/-/knowledge_base/d/authentication-verifiers)
filter is registered for it. You can set its properties in your `@Component`
annotation by prefixing the properties with `auth.verifier`. For example, to
disable guest access to the service, configure it like this: 

```java
@Component( 
        property = { 
            JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/greetings", 
            JaxrsWhiteboardConstants.JAX_RS_NAME + "=Greetings.Rest",
            "auth.verifier.guest.allowed=false"
        }, 
        service = Application.class)
```

Basic Auth is great during development, but credentials passed on the URL appear
in server logs, so when you're done developing, you should disable Basic Auth
and use OAuth2 instead. To disable Basic Auth, create and deploy a configuration
file called
`com.liferay.portal.security.auth.verifier.internal.tracker.AuthVerifierFilterTracker.config` 
that contains this property: 

```properties
default.registration.property=["filter.init.auth.verifier.OAuth2RESTAuthVerifier.urls.includes=*","filter.init.auth.verifier.PortalSessionAuthVerifier.urls.includes=*"]
```
This disables Basic Auth for all JAX-RS applications, but keeps Portal Session
and OAuth2 enabled. 

### Using OAuth 2.0 to Invoke a JAX-RS Web Service

Your JAX-RS web service requires authorization by default. To enable this, you
must create an 
[OAuth 2.0 application](/docs/7-2/deploy/-/knowledge_base/d/oauth-2-0#creating-an-application)
to provide a way to grant access to your service: 

1.  Go to the *Control Panel* &rarr; *Configuration* &rarr; *OAuth2
    Administration* and click the ![add](../../../images/icon-add.png) button to
    add an application. 

2.  Give your application a descriptive name. 

3.  Choose the Client Profile appropriate for this service. These are templates
    that auto-select the appropriate authorization types or "flows" from the
    OAuth 2 standard. For this example choose the *Headless Server* profile,
    which auto-selects the *Client Credentials* authorization type. 

4.  Click *Save*. 

The form now reappears with two additional generated fields: Client ID and
Client Secret. You'll use these to authenticate to your web service. 

To make your service accessible, 

1.  Click the *Scopes* tab. 

2.  You'll see an entry for your deployed `Greetings.Rest` service. Expand it by
    clicking the arrow. 

3.  Check the box labeled *read data on your behalf*. 

4.  Click *Save*. 

![Figure 1: Enable the scope to grant access to the service.](../../../images/jax-rs-oauth2-scope.png)

For simplicity, the examples below use [Curl](https://curl.haxx.se) to
authenticate. You need the two pieces of information generated for your
application: the Client ID and the Client Secret. For example, say those fields
contain these values: 

**Client ID:** `id-12e14a84-e558-35a7-cf9a-c64aafc7f` 

**Client Secret:** `secret-93f14320-dc39-d67f-9dec-97717b814f`

First, you must request an OAuth token. If you're testing locally, you'd make
a request like this: 

```bash
curl http://localhost:8080/o/oauth2/token -d 'grant_type=client_credentials&client_id=id-12e14a84-e558-35a7-cf9a-c64aafc7f&client_secret=secret-93f14320-dc39-d67f-9dec-97717b814f'
```

The response is JSON: 

```json
{"access_token":"a7f12bef7f2e578cf64bce4085db8f17b6a3c2963f865a65b374e89784bbca5","token_type":"Bearer","expires_in":600,"scope":"GET POST PUT"}
```

It contains a token, generated for this client. It expires in 600 seconds, and
it grants GET, POST, and PUT for this web service. 

When you want to call the service, you must supply the token in the HTTP header,
like this: 

```bash
curl --header "Authorization: Bearer a7f12bef7f2e578cf64bce4085db8f17b6a3c2963f865a65b374e89784bbca5" http://localhost:8080/o/greetings/morning
```

With authorization, your web service can be called and responds to the request: 

    Good morning!

Of course, this is only one of the authorization flows for OAuth 2.0. If you're
creating a web-based client whose back-end is a JAX-RS web service hosted on
@product@, you'd want one of the other flows. See the 
[OAuth 2.0 documentation](/docs/7-2/deploy/-/knowledge_base/d/oauth-2-0)
for further information. Additionally, OAuth 2.0 assumes the use of HTTPS for
its security: the above URLs are only for local testing purposes. You certainly
would not want to pass OAuth tokens between clients and servers in the clear.
Make sure that in production your server uses HTTPS. 

#### OAuth2 Scopes

Without any special Liferay OAuth2 annotations or properties, a standard OSGi
JAX-RS application is inspected by the Liferay OAuth2 runtime, and scopes are
derived by default based on the HTTP verbs supported by the application.

When developers want more control, they can use the property
`oauth2.scopechecker.type=annotations` and the annotation
`com.liferay.oauth2.provider.scope.RequiresScope` exported from the `Liferay
OAuth2 Provider Scope API` bundle to annotate endpoint resource methods or whole
classes like this:

```java
@RequiresScope("scopeName")
```

Once deployed, this becomes a scope in the 
[OAuth 2.0 configuration](/docs/7-2/deploy/-/knowledge_base/d/oauth2-scopes). 
You can disable scope checking (not recommended) by setting the scope checker to
a non-existent type: 

```properties
oauth2.scope.checker.type=none
```
#### Requiring OAuth2

You can specify OAuth2 authorization as required for your JAX-RS application by
using this property: 

```properties
osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.OAuth2)
```

### JAX-RS and Service Access Policies

When authenticating via Basic Auth, the 
[Service Access Policy](/docs/7-2/deploy/-/knowledge_base/d/service-access-policies)
`SYSTEM_USER_PASSWORD` is enforced. When authenticating via OAuth 2.0, the
`AUTHORIZED_OAUTH2_SAP` policy is enforced. Configure them appropriately for
your environment, as by default, they allow invoking all remote services. To
disable Service Access Policy enforcement for JAX-RS endpoints (not recommended),
set this property: 

```properties
liferay.access.control.disable=true
```

With this configured, guests can call these endpoints without administrators
having to define a default Service Access Policy.

## Public JAX-RS Services

To create a public endpoint for development purposes, all you must do is set two
properties: 

```java
@Component(
    property={
        "auth.verifier.guest.allowed=true",
        "liferay.access.control.disable=true"
    },
    service = Application.class
)
```

Don't keep this configuration for production. For public services, it's best to
leave the security in place and whitelist the particular endpoints you're making
public. See [Service Access Policies](/docs/7-2/deploy/-/knowledge_base/d/service-access-policies)
for further information. 

## Using JAX-RS with CORS

If you foresee that JavaScript in a browser might access your JAX-RS web service
from a different domain, you might want to use the CORS annotation. You can use
the `@CORS` annotation to define 
[CORS policies](/docs/7-2/deploy/-/knowledge_base/d/configuring-cors) on your deployed
JAX-RS applications. Note that the annotations 
[can be overridden by an administrator](/docs/7-2/deploy/-/knowledge_base/d/configuring-cors). 
It only takes three steps: 

1.  Add the Portal Remote CORS API dependency to your module: 

```groovy
compileOnly project(":apps:portal-remote:portal-remote-cors-api")
```

2.  Activate the CORS annotation feature in your application properties: 

```java
@Component(
    property = {
        "osgi.jaxrs.application.base=/my-application",
        "osgi.jaxrs.name=My.Application.Name",
        "liferay.cors.annotation=true"
    },
    service = Application.class
    )
    public class MyApplication extends Application {
    ...
    }
```

3.  Use the `@CORS` annotation throughout your application globally or by
    method. 

Globally: 

```java
@Component(
	property = {
		"osgi.jaxrs.application.base=/my-application",
		"osgi.jaxrs.name=My.Application.Name",
		"liferay.cors.annotation=true"
	},
	service = Application.class
)
@CORS(allowMethods="GET")
public class MyApplication extends Application {
...
}
```

By method: 

```java
@CORS
	@GET
	@Path("/users")
	public List<User> getUserList() throws Exception {
		return _users;
	}
```

You can use the annotation to provide a configuration for any of the CORS
headers. Here are some examples: 

|      Header      |    Annotation Example    | 
|------------------|--------------------------|
|Access-Control-Allow-Credentials|`@CORS(allowCredentials = false)`|
|Access-Control-Allow-Headers|`@CORS(allowHeaders = "X-PINGOTHER")`|
|Access-Control-Allow-Methods|`@CORS(allowMethods = "OPTIONS,POST")`|
|Access-Control-Allow-Origin|`@CORS(allowOrigin = "http://www.liferay.com")`|

If for some reason you want to disable the `@CORS` annotations in your
application, you can do it globally by disabling it in your `@Component`
annotation: 

```java
@Component(
	property = {
		"osgi.jaxrs.application.base=/no-cors-application",
		"osgi.jaxrs.name=NoCors.Application.Name",
		"liferay.cors.annotation=false"
	},
	service = Application.class
)
```

Great! Now you know how to create, deploy, and invoke JAX-RS web services on
@product@'s platform! 

## Related Topics

[REST Builder](/docs/7-2/appdev/-/knowledge_base/a/rest-builder)
