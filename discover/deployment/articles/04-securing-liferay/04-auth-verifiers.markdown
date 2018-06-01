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
&rarr; *API Authentication*. Configuration for Auth Verifiers that ship with 
the product include

- Basic Auth Header 
- Digest Authentication 
- HTTP Tunnel Extender 
- Image Request
- Portal Sessions 
- Request Parameter
- Tunnel Auth 

Only following Auth Verifiers are enabled by default and can be used to access
remote API out-of-the-box:

- Basic Auth Header
- Portal Sessions 

## Basic Auth Header [](id=basic-auth-header)

This Auth Verifier allows the remote client to authenticate using 
[HTTP Basic Authentication](https://en.wikipedia.org/wiki/Basic_access_authentication). 
Configure it by providing URL paths that should be authenticated this way. 
When Force Basic Authentication field is checked then HTTP Basic Authentication is 
required.

The default URLs are `/api/*,/xmlrpc*` for web services. The mapping excludes 
`/api/liferay*` to prevent accessing `TunnelServlet`. For more information please 
see  Tunnel Authentication Verifiers.

## Digest Auth Header [](id=digest-auth-header)

This Auth Verifier allows the remote client to authenticate using 
[HTTP Digest Authentication](https://en.wikipedia.org/wiki/Digest_access_authentication). 
Configure it by providing URL paths that should be authenticated this way. 
When Force Digest Authentication field is checked then HTTP Basic Authentication is 
required.

This Auth Verifier is not enabled by default.

## HTTP Tunnel Extender [](id=http-tunnel-extender)

As Liferay embraced modularity, this extender was written to enable modules to
be part of `TunnelServlet`. It maps `TunnelServlet` and
`TunnelingServletAuthVerifier` to the module servlet context. Modules with
`Http-Tunnel` in the manifest can make use of the Tunnel Servlet, and can expose
the API via `/o/_module_/api/liferay/do`. 

Configure it by setting client IP addresses allowed to tunnel. For more
information, please see 
[the properties documentation](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#HTTP%20Tunneling)
as well as 
[remote staging](/discover/portal/-/knowledge_base/7-1/enabling-remote-live-staging).

Note that this is not a recommended way to export remote APIs; it's far
better to expose remote services using JAX-RS or Liferay JSON Web Service 
technologies.

## Image Request Authentication Verifier [](id=image-request-authentication-verifier)

When connected to LibreOffice/OpenOffice, the Office process must download
images from @product@ to render docs with images. To do this, a 
[JWT Token](https://jwt.io) is created to access the images securely. 

Configure this by setting the Hosts Allowed, URLs included, and URLs excluded if
necessary. 

This Auth Verifier is not enabled by default.

## Portal Sessions Auth Verifiers [](id=portal-sessions-auth-verifiers)

Enables JavaScript in a browser to access Liferay JSON Web Services using an
existing portal session.

In the default configuration, the URLs included field shields access to the
legacy JSON remote services layer:
`/api/json*,/api/jsonws*,/c/portal/json_service*`.

## Request Parameter Auth Verifiers [](id=request-parameter-auth-verifiers)

For backwards compatibility with `RequestParameterAutoLogin` you can
authenticate and access portal endpoints with credentials inside HTTP request
parameters `parameterAutoLoginLogin` and `parameterAutoLoginPassword`.

This Auth Verifier is not enabled by default.

## Tunnel Authentication Verifiers [](id=tunnel-authentication-verifiers)

`TunnelServlet` is a legacy remote API endpoint mapped at `/api/liferay/do` to
provide access to the portal remote services. The Tunnel Auth Verifier allows
trusted remote clients authenticated access using any user ID provided, on
behalf of the user.

An example of a trusted remote client is the Staging remote publishing feature.

Trusted remote clients authenticate using a shared secret stored in the portal
property `tunneling.servlet.shared.secret`. The default value is empty and
forbids all access. 

Even though the default configuration is enabled by default, access is
limited to localhost only. Configure it by setting client IP addresses allowed 
to tunnel. For more information, please see 
[the properties documentation](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#HTTP%20Tunneling)
as well as 
[remote staging](/discover/portal/-/knowledge_base/7-1/enabling-remote-live-staging).

## Related Topics [](id=related-topics)

[Service Access Policies](/discover/deployment/-/knowledge_base/7-1/)
