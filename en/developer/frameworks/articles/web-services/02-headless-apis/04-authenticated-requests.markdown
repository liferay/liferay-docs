---
header-id: making-authenticated-rest-api-requests
---

# Making Authenticated Requests

[TOC levels=1-4]

To make an authenticated request, you must authenticate as a specific User. 

There are three authentication mechanisms available when invoking web APIs: 

**Basic Authentication:** Sends the user credentials as an encoded user name 
and password pair. This is the simplest authentication protocol (available since 
HTTP/1.0). 

**OAuth 2.0:** In @product-ver@, you can use OAuth 2.0 for authentication. See 
the 
[OAuth 2.0 documentation](/docs/7-2/deploy/-/knowledge_base/d/oauth-2-0) 
for more information. 

**Cookie/Session authentication:** From inside the portal you can make direct
requests to the APIs by sending the session token.

First, you'll learn how send requests with basic authentication. 

## Basic Authentication

Basic authentication requires that you send an HTTP `Authorization` header 
containing the encoded user name and password. You must first get that encoded 
value. To do so, you can use `openssl` or a `Base64` encoder. Either way, you 
must encode the `user:password` string. Here's an example of the `openssl` 
command for encoding the `user:password` string for a user `test@example.com` 
with the password `Liferay`: 

```bash
openssl base64 <<< test@example.com:Liferay
```

This returns the encoded value: 

    dGVzdEBleGFtcGxlLmNvbTpMaWZlcmF5Cg==

If you don't have `openssl` installed, try the `base64` command: 

```bash
base64 <<< test@example.com:Liferay
```

| **Warning:** Encoding a string as shown here does not encrypt the resulting 
| string. An encoded string can easily be decoded by executing 
| `base64 <<< the-encoded-string`, which returns the original string. 
| 
| Anyone listening to your request could therefore decode the `Authorization` 
| header and reveal your user name and password. To prevent this, ensure that 
| all communication is made through HTTPS, which encrypts the entire message 
| (including headers). 

Use the encoded value for the HTTP Authorization header when sending the 
request: 

```bash
curl -H "Authorization: Basic dGVzdEBleGFtcGxlLmNvbTpMaWZlcmF5Cg==" http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/
```

The response contains data instead of the 403 error that an unauthenticated 
request receives. For more information on the response's structure, see 
[Working with Collections of Data](/docs/7-2/frameworks/-/knowledge_base/f/working-with-collections-of-data). 

```json
{
  "items": [
    {
      "alternativeHeadline": "The power of OpenAPI & Liferay",
      "articleBody": "<p>We are happy to announce...</p>",
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T07:04:47Z",
      "dateModified": "2019-04-22T07:04:51Z",
      "datePublished": "2019-04-22T07:02:00Z",
      "encodingFormat": "text/html",
      "friendlyUrlPath": "new-headless-apis",
      "headline": "New Headless APIs",
      "id": 59301,
      "numberOfComments": 0,
      "siteId": 20124
    },
    {
      "alternativeHeadline": "How to work with OAuth",
      "articleBody": "<p>To configure OAuth...</p>",
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T09:35:09Z",
      "dateModified": "2019-04-22T09:35:09Z",
      "datePublished": "2019-04-22T09:34:00Z",
      "encodingFormat": "text/html",
      "friendlyUrlPath": "authenticated-requests",
      "headline": "Authenticated requests",
      "id": 59309,
      "numberOfComments": 0,
      "siteId": 20124
    }
  ],
  "lastPage": 1,
  "page": 1,
  "pageSize": 20,
  "totalCount": 2
}
```

## OAuth 2.0 Authorization

@product-ver@ supports authorization via OAuth 2.0, which is a token-based 
authorization mechanism. For more details, see 
[@product@'s OAuth 2.0 documentation](/docs/7-2/deploy/-/knowledge_base/d/oauth-2-0). 
The following sections show you how to use OAuth 2.0 to authenticate web API 
requests. 

### Obtaining the OAuth 2.0 Token

Before using OAuth 2.0 to invoke a web API, you must register your application 
(your web API's consumer) as an authorized OAuth client. To do this, follow the 
instructions in the 
[Creating an Application](/docs/7-2/deploy/-/knowledge_base/d/oauth-2-0#creating-an-application) 
section of the OAuth 2.0 documentation. When creating the application, fill in
the form as follows: 

**Application Name:** Your application's name. 

**Client Profile:** Headless Server. 

**Allowed Authorization Types:** Check *Client Credentials*. 

After clicking *Save* to finish creating the application, write down the Client 
ID and Client Secret values that appear at the top of the form. 

Next, you must get an OAuth 2.0 access token. To do this, see the tutorial 
[Authorizing Account Access with OAuth 2](/docs/7-2/deploy/-/knowledge_base/d/authorizing-account-access-with-oauth2). 

### Invoking the Service with an OAuth 2.0 Token

Once you have a valid OAuth 2.0 token, include it in the request's 
`Authorization` header, specifying that the authentication type is a 
[bearer token](https://tools.ietf.org/html/rfc6750). 
For example: 

```bash
curl -H "Authorization: Bearer d5571ff781dc555415c478872f0755c773fa159" http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/
```

The response contains the resources that the authenticated user has 
permission to access, just like the response from Basic authentication.

## Using Cookie Authentication or Making Requests from the UI

You can call the REST APIs using the existing session from outside @product@
by passing the session identifier (the cookie reference) and the Liferay Auth
Token (a Cross-Site Request Forgery---CSRF---token).

To do a request from outside @product@ you must provide the `Cookie` identifier
in the header. In CURL, pass the `-H` parameter: 

     -H 'Cookie: JSESSIONID=27D7C95648D7CDBE3347601FC4543F5D'

You must also provide the CSRF token by passing it in the `p_auth` query
parameter, or by adding the URL to the whitelist of CSRF allowed URLs or disabling CSRF
checks altogether with the `auth.verifier.auth.verifier.PortalSessionAuthVerifier.check.csrf.token` property (application level).

Here's a sample CURL request with the cookie and CSRF token:

```bash
curl -H 'Cookie: JSESSIONID=27D7C95648D7CDBE3347601FC4543F5D' http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/?p_auth=O4dCU1Mj
```
 
To do a cookie request from inside @product@, from JavaScript code or a Java
method, the session identifier is not needed and you must only provide
the CRSF token or add the API to the whitelist of CSRF allowed URLs.
 
## Making Unauthenticated Requests

Unauthenticated requests are disabled by default in @product@'s headless REST
APIs. You can, however, enable them manually by defining an exception in the
Service Access Policy to allow unauthenticated requests.

1. Go to Control Panel &rarr; Configuration &rarr; Service Access Policy.

2. Add a new Service Access Policy.

3. Enable both *Enabled* and *Default* options.

4. Use `com.liferay.headless.delivery.internal.resource.v1_0.OpenAPIResourceImpl`
   for the Service Class and `getOpenAPI` for the Method Name (or the method/class
   you want to expose).

5.  Test the APIs by making a request to an OpenAPI profile URL: 

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/openapi.yaml"
```

You should get the OpenAPI profile for the API you sent the request to. 

## Cross-Origin Resource Sharing (CORS)

Modern web browsers block access to content from domains other than the one
currently being visited. For example, browsers block fetch/ajax requests from
a local JavaScript application (being executed in localhost:4000) that tries to
access a Tomcat server (running in localhost:8080). 

Cross Origin Resource Sharing allows the configuration of safe resource sharing
between sites. A web application using APIs can only request endpoints that have
the same origin/domain unless some special CORS headers are set that explicitly
allow querying from different domains. 

For development purposes, it's common to enable CORS headers to allow
scripts to call APIs served by a different server. 

![Figure 1: Configure Cross-Origin Resource Sharing in Liferay](../../../images/cors.png)

Follow these instructions to configure [Cross-Origin Resource Sharing (CORS)](/docs/7-2/deploy/-/knowledge_base/d/configuring-cors) 
in @product@.

## Related Topics

[Get Started: Invoke a Service](/docs/7-2/frameworks/-/knowledge_base/f/get-started-invoke-a-service)

[Working with Collections of Data](/docs/7-2/frameworks/-/knowledge_base/f/working-with-collections-of-data)
