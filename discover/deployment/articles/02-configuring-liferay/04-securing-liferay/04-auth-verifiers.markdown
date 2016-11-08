# Authentication Verifiers [](id=authentication-verifiers)

Liferay includes a centralized and extensible authentication layer called the
authentication verification layer. This layer is mainly used for authenticating
remote invocations of Liferay's API.

The main responsibilities of the authentication verification layer are to

1. Verify provided credentials using registered `AuthVerifier` instances
2. Create portal authorization contexts based on verification results

If no available `AuthVerifier` is able to verify request credentials, an
authorization context supporting non-authenticated access is created for a
guest user. This allows each Liferay API to expose only a single API endpoint.
In contrast, legacy (prior to 6.2) versions of Liferay exposed two API
endpoints for each API: the `/api/endpoint` URI was for non-authenticated
access and the URI `/api/secure/endpoint` was for authenticated access.

@product@ offers built-in `AuthVerifier` implementations for the most common
situations. These include situations where remote clients use HTTP Basic or
HTTP Digest authentication, send credentials in request parameters, send
authenticated `JSESSIONID`s, or use shared secrets to establish trust. Other
`AuthVerifier` implementations can be deployed as modules containing
implementations of the `AuthVerifier` interface that are registered as services
in @product@'s OSGi runtime.

Note: The authentication verification layer's focus is on verifying
authentication, not on providing credentials. The authentication verification
layer is NOT responsible for issuing tokens, credentials, or displaying Sign In
portlets. Instead, the layer verifies existing credentials and authenticated
sessions and is therefore a complement to authentication endpoints. However, to
ensure backwards compatibility, the default portal implementations support
requests providing username and password credentials. Thus, the authentication
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

When a request is processed by all matching `AuthVerifier`s, Liferay creates an
authorization context for the resolved user.

This encompasses setting the `HttpServletRequest` `remoteUser` to return the
resolved user ID setting Liferay `ThreadLocal`s to the resolved user.

The resolved user can be the user returned by one of the `AuthVerifier`
instances or a guest user if no instance was able to verify the provided
credentials.

For more detailed technical information, please see the [AuthVerifiers (not yet
written)]() tutorial.

## Related Topics [](id=related-topics)

(Coming Soon)
