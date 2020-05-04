---
header-id: authenticated-requests
---

# Making Authenticated Requests

[TOC levels=1-4]

To make an authenticated request, you must authenticate as a specific User. 

There are three authentication mechanisms available when invoking web APIs: 

**Basic Authentication:** Sends the user credentials as an encoded user name and
password pair. This is the simplest authentication protocol (available since
HTTP/1.0), but should be used only for development purposes, as it's insecure. 

**OAuth 2.0:** In @product-ver@, you can use OAuth 2.0 for authorization. See
the [OAuth 2.0 documentation](/docs/7-2/deploy/-/knowledge_base/d/oauth-2-0) for
more information. 

**Cookie/Session authentication:** From inside the portal you can do direct
requests to the APIs by sending the session token.

First, you'll learn how to send requests with basic authentication. 

## Basic Authentication

Basic authentication requires that you send an HTTP `Authorization` header
containing the encoded user name and password. You must first get that encoded
value. To do so, you can use `openssl` or a `Base64` encoder. Either way, you
must encode the `user:password` string. Here's an example of the `openssl`
command for encoding the `user:password` string for a user `test@liferay.com`
with the password `Liferay`: 

```bash
openssl base64 <<< test@liferay.com:Liferay
```

This returns the encoded value: 

    dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5Cg==

If you don't have `openssl` installed, try the `base64` command: 

```bash
base64 <<< test@liferay.com:Liferay
```

| **Warning:** Encoding a string as shown here does not encrypt the resulting 
| string. The encoded string can easily be decoded by executing 
| `base64 <<< the-encoded-string`, which returns the original string. 
| 
| Anyone listening to your request could therefore decode the `Authorization` 
| header and reveal your user name and password. To prevent this, ensure that 
| all communication is made through HTTPS, which encrypts the entire message 
| (including headers). 

Use the encoded value for the HTTP Authorization header when sending the
request: 

```bash
curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5Cg==" http://localhost:8080/o/graphql ...
```

The response contains data instead of the 403 error that an unauthenticated
request receives. For more information on the response's structure, see 
[Working with Collections of Data](/docs/7-2/frameworks/-/knowledge_base/f/working-with-collections-of-data). 

## OAuth 2.0 Authorization

@product-ver@ supports authorization via OAuth 2.0, which is a token-based
authorization mechanism. For more details, see 
[@product@'s OAuth 2.0 documentation](/docs/7-2/deploy/-/knowledge_base/d/oauth-2-0). The following
sections show you how to use OAuth 2.0 to authenticate web API requests. 

### Obtaining the OAuth 2.0 Token

Before using OAuth 2.0 to invoke a web API, you must register your application
(your web API's consumer) as an authorized OAuth client. To do this, follow the
instructions in [Creating an Application](/docs/7-2/deploy/-/knowledge_base/d/oauth-2-0#creating-an-application).
When creating the application, fill in the form as follows: 

**Application Name:** Your application's name. 

**Client Profile:** Headless Server. 

**Allowed Authorization Types:** Check *Client Credentials*. 

After clicking *Save* to finish creating the application, write down the Client
ID and Client Secret values that appear at the top of the form. 

Next, you must get an OAuth 2.0 access token. To do this, see 
[Authorizing Account Access with OAuth 2](/docs/7-2/deploy/-/knowledge_base/d/authorizing-account-access-with-oauth2). 

### Invoking the Service with an OAuth 2.0 Token

Once you have a valid OAuth 2.0 token, include it in the request's
`Authorization` header, specifying that the authentication type is a 
[bearer token](https://tools.ietf.org/html/rfc6750):

```bash
curl -H "Authorization: Bearer d5571ff781dc555415c478872f0755c773fa159" http://localhost:8080/o/graphql
```

The response contains the resources that the authenticated user has permission
to access, just like the response from Basic authentication. The request could
be prevented depending on the scopes defined. If POST a GraphQL query
and there is scope disabling all request except `GET`, you see a 403. 

## Using Cookie Authentication or doing a request from the portal

You can call the GraphQL APIs using the existing session from outside the
@product@ by passing the session identifier (the cookie reference) and the
Liferay Auth Token (a CSRF---Cross-Site Request Forgery---token).

To make an unauthenticated request from outside the @product@ you must
provide the `Cookie` identifier in the header: 

```bash
curl -H 'Cookie: JSESSIONID=27D7C95648D7CDBE3347601FC4543F5D'
```

You must also provide the CSRF token by passing it as a query parameter
called `p_auth` or by adding the URL to the whitelist of CSRF allowed URLs or disabling CSRF
checks altogether with the `auth.verifier.auth.verifier.PortalSessionAuthVerifier.check.csrf.token` property (application level).

Here's a sample cURL request with the cookie and CSRF token:

```bash
curl -H 'Cookie: JSESSIONID=27D7C95648D7CDBE3347601FC4543F5D' http://localhost:8080/o/graphql?p_auth=O4dCU1Mj
```
 
To do an unauthenticated request from inside the @product@, from JavaScript code
or a Java method, you don't need the session identifier. You must only provide
the CRSF token or add the API to the whitelist of CSRF allowed URLs.

## Making Unauthenticated Requests

Unauthenticated requests are disabled by default in @product@'s GraphQL APIs. As
all GraphQL APIs share the same endpoint, you cannot have the same level of
granularity with Service Access Policies as in REST APIs. For that reason, we do
not recommend disabling the security of the GraphQL APIs.

## Related Topics

[Get Started: Invoke a Service](/docs/7-2/frameworks/-/knowledge_base/f/get-started-invoke-a-service)

[Working with Collections of Data](/docs/7-2/frameworks/-/knowledge_base/f/working-with-collections-of-data)
