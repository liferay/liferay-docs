# JAX-RS [](id=jax-rs)

JAX-RS web services work in Liferay modules the same way they work outside of
Liferay. The only difference is that you must register the class in the OSGi
framework. Liferay's development tools make this easy by providing a template. 

In Liferay Developer Studio, create a new module using the *rest* template: 

1.  Click *File* &rarr; *New* &rarr; *Liferay Module Project*. 

2.  Give the project a name and select the *rest* template. 

3.  Select *Next* and enter a class name and a package name for your service. 

4.  Click *Finish*. 

Alternatively, use 
[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-blade-cli)
to create the project. 

+$$$

**Note:** The initial release of @product-ver@'s development tools created
a project with an invalid configuration. If your class contains an
`@ApplicationPath` annotation, you must update the following files: 

**Your Java Class:** 

1.  Add the following import: 

        import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

2.  Remove the `@ApplicationPath` annotation. 

3.  Modify the `@Component` annotation so it looks like this: 

        @Component( 
		property = { 
			JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/greetings", 
			JaxrsWhiteboardConstants.JAX_RS_NAME + "=Greetings.Rest"
		}, 
		service = Application.class)

**build.gradle:**

Add the following dependency: 

	compileOnly group: "org.osgi", name: "org.osgi.service.jaxrs", version: "1.0.0"

**Files to delete:**

Delete everything under `src/main/resources/configuration`. 

$$$

The class that's generated contains a working JAX-RS web service. You can deploy
it and use it immediately. 

While it's beyond the scope of this article to cover 
[JAX-RS Whiteboard](https://blog.osgi.org/2018/03/osgi-r7-highlights-jax-rs-whiteboard.html)
in its entirety, essentially it's JAX-RS unchanged except for configuration
properties in the `@Component` annotation. These properties declare two things: 

1.  The endpoint for the service

2.  The service name as it appears in the OAuth 2.0 configuration

The generated class contains this configuration: 

    @Component( 
            property = { 
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/greetings", 
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=Greetings.Rest"
            }, 
            service = Application.class)

This configuration registers the service at this endpoint: 

    https://[server-name]:[port]/o/greetings

If you're testing this locally on Tomcat, the URL is

    https://localhost:8080/o/greetings

As you might guess, you don't have access to the service by just calling the URL
above. You must authenticate first, which you'll learn how to do next. 

## Using OAuth 2.0 to Invoke a JAX-RS Web Service [](id=using-oauth-2-0-to-invoke-a-jax-rs-web-service)

Your JAX-RS web service requires authorization by default. To enable this, you
must create an 
[OAuth 2.0 application](/discover/deployment/-/knowledge_base/7-1/oauth-2-0#creating-an-application)
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

For simplicity, the examples below use [Curl](https://curl.haxx.se) to
authenticate. You need the two pieces of information generated for your
application: the Client ID and the Client Secret. For example, say those fields
contain these values: 

**Client ID:** `id-12e14a84-e558-35a7-cf9a-c64aafc7f` 

**Client Secret:** `secret-93f14320-dc39-d67f-9dec-97717b814f`

First, you must request an OAuth token. If you're testing locally, you'd make
a request like this: 

    curl http://localhost:8080/o/oauth2/token -d 'grant_type=client_credentials&client_id=id-12e14a84-e558-35a7-cf9a-c64aafc7f&client_secret=secret-93f14320-dc39-d67f-9dec-97717b814f'

The response is JSON: 

    {"access_token":"a7f12bef7f2e578cf64bce4085db8f17b6a3c2963f865a65b374e89784bbca5","token_type":"Bearer","expires_in":600,"scope":"GET POST PUT"}

It contains a token, generated for this client. It expires in 600 seconds, and
it grants GET, POST, and PUT for this web service. 

When you want to call the service, you must supply the token in the HTTP header,
like this: 

    curl --header "Authorization: Bearer a7f12bef7f2e578cf64bce4085db8f17b6a3c2963f865a65b374e89784bbca5" http://localhost:8080/o/greetings/morning

With authorization, your web service can be called and responds to the request: 

    Good morning!

Of course, this is only one of the authorization flows for OAuth 2.0. If you're
creating a web-based client whose back-end is a JAX-RS web service hosted on
@product@, you'd want one of the other flows. See the 
[OAuth 2.0 documentation](/discover/deployment/-/knowledge_base/7-1/oauth-2-0)
for further information. Additionally, OAuth 2.0 assumes the use of HTTPS for
its security: the above URLs are only for local testing purposes. You certainly
would not want to pass OAuth tokens between clients and servers in the clear.
Make sure that in production your server uses HTTPS. 

Great! Now you know how to create, deploy, and invoke JAX-RS web services on
@product@'s platform! 

## Related Topics [](id=related-topics)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services)
