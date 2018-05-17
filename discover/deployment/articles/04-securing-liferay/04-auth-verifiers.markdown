# Authentication Verifiers [](id=authentication-verifiers)

The Authentication Verification Layer is a centralized and extensible way to
authenticate remote invocations of @product@'s API.

The main responsibilities of the authentication verification layer are to

1. Verify provided credentials using registered `AuthVerifier` instances

2. Create portal authorization contexts based on verification results

If no available `AuthVerifier` can verify request credentials, an authorization
context supporting non-authenticated access is created for a guest user. This
allows each API to expose only a single API endpoint. In contrast, legacy (prior
to 6.2) versions of @product@ exposed two API endpoints for each API: the
`/api/endpoint` URI was for non-authenticated access and the URI
`/api/secure/endpoint` was for authenticated access.

There are built-in `AuthVerifier` implementations for the most common
situations, such as when remote clients use HTTP Basic or HTTP Digest
authentication, send credentials in request parameters, send authenticated
`JSESSIONID`s, or use shared secrets to establish trust. Other `AuthVerifier`
implementations can be deployed as modules containing implementations of the
`AuthVerifier` interface that are registered as services in the OSGi runtime.

Note: The authentication verification layer's focus is on verifying
authentication, not on providing credentials. The authentication verification
layer is NOT responsible for issuing tokens, credentials, or displaying Sign In
portlets. Instead, the layer verifies existing credentials and authenticated
sessions and is therefore a complement to authentication endpoints. However, to
ensure backwards compatibility, the default implementations support requests
providing user name and password credentials. Thus, the authentication
verification layer stands on the border between authentication and
authorization.

## Authentication Verification Process Overview [](id=authentication-verification-process-overview)

This layer and surrounding processes are provided by the `AuthVerifierFilter`
class that implements the `javax.servlet.Filter` interface.

**Step 1: Verify Request Credentials**

The layer uses the chain of responsibility design pattern to support both
built-in and third party `AuthVerifier` implementations. Each `AuthVerifier`
can provide configurations where it specifies mapped URLs and other properties.

Each incoming request is matched against all registered `AuthVerifier`s to
select the final list of `AuthVerifier`s that is used to process the request.
It's the responsibility of each `AuthVerifier` to verify the incoming request
credentials.

**Step 2: Create an Authorization Context**

When a request is processed by all matching `AuthVerifier`s, @product@ creates an
authorization context for the resolved user.

This encompasses setting the `HttpServletRequest` `remoteUser` to return the
resolved user ID setting `ThreadLocal`s to the resolved user.

The resolved user can be the user returned by one of the `AuthVerifier`
instances or a guest user if no instance was able to verify the provided
credentials. 
`AuthVerifier`s are created by developers, and are processed automatically as
long as they're registered in the OSGi runtime. Each Auth Verifier gets its own
configuration in *Control Panel* &rarr; *System Settings* &rarr; *Security*
&rarr; *API Authentication*. Auth Verifiers that ship with the product include

- Basic Auth Header 
- Digest Authentication 
- HTTP Tunnel Extender 
- Image Request
- Portal Sessions 
- Request Parameter
- Tunnel Auth 

Not all of these require configuration; the ones that do are described below. 

## Basic Auth Header

This Auth Verifier allows the remote client to authenticate using 
[HTTP Basic Authentication](https://en.wikipedia.org/wiki/Basic_access_authentication). 
Configure it by providing URL paths that should be authenticated this way. The
default URLs are `/api/*,/xmlrpc*` for web services. 

## HTTP Tunnel Extender

As Liferay embraced modularity, this extender was written to enable modules to
be part of `TunnelServlet`. It maps `TunnelServlet` and
`TunnelingServletAuthVerifier` to the module servlet context. Modules with
`Http-Tunnel` in the manifest can make use of the Tunnel Servlet, and can access
the API via `/o/_module_/api/liferay/do`. 

Configure it by setting host IP addresses allowed to tunnel. For more
information, please see 
[the properties documentation](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#HTTP%20Tunneling)
as well as 
[remote staging](/discover/portal/-/knowledge_base/7-1/enabling-remote-live-staging).

Note that this is not a recommended way to make use of remote APIs; it's far
better to use remote services like JSON. 

## Image Request Authentication Verifier

When connected to LibreOffice/OpenOffice, the Office process must download
images from @product@ to render docs with images. To do this, a 
[JWT Token](https://jwt.io) is created to access the images securely. 

Configure this by setting the Hosts Allowed, URLs included, and URLs excluded if
necessary. 

## Portal Sessions Auth Verifiers

Enter URL includes here to allow endpoints to verify authentication using an
existing portal session. 


## Related Topics [](id=related-topics)

[Service Access Policies](/discover/deployment/-/knowledge_base/7-1/
