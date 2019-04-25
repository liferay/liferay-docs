# Making Authenticated Requests [](id=making-authenticated-requests)

To make an authenticated request, you must authenticate as a specific user. 

There are two authentication mechanisms available when invoking web APIs: 

**Basic Authentication:** Sends the user credentials as an encoded user name 
and password pair. This is the simplest authentication protocol (available since 
HTTP/1.0). 

**OAuth 2.0:** In @product-ver@, you can use OAuth 2.0 for authentication. See 
the 
[OAuth 2.0 documentation](/discover/deployment/-/knowledge_base/7-2/oauth-2-0) 
for more information. 

First, you'll learn how send requests with basic authentication. 

## Basic Authentication [](id=basic-authentication)

Basic authentication requires that you send an HTTP `Authorization` header 
containing the encoded user name and password. You must first get that encoded 
value. To do so, you can use `openssl` or a `Base64` encoder. Either way, you 
must encode the `user:password` string. Here's an example of the `openssl` 
command for encoding the `user:password` string for a user `test@liferay.com` 
with the password `Liferay`: 

    openssl base64 <<< test@liferay.com:Liferay

This returns the encoded value: 

    dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5Cg==

If you don't have `openssl` installed, try the `base64` command: 

    base64 <<< test@liferay.com:Liferay

| **Warning:** Encoding a string as shown here does not encrypt the resulting 
| string. Such an encoded string can easily be decoded by executing 
| `base64 <<< the-encoded-string`, which returns the original string. 
| 
| Anyone listening to your request could therefore decode the `Authorization` 
| header and reveal your user name and password. To prevent this, ensure that 
| all communication is made through HTTPS, which encrypts the entire message 
| (including headers). 

Use the encoded value for the HTTP Authorization header when sending the 
request: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5Cg==" http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/

The response contains data instead of the 403 error that an unauthenticated 
request receives. For more information on the response's structure, see 
[API Vocabulary](liferay.com) and 
[Working with Collections of Data](liferay.com). 

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

## OAuth 2.0 Authentication [](id=oauth-2-0-authentication)

@product-ver@ supports authorization via OAuth 2.0, which is a token-based 
authentication mechanism. For more details, see 
[@product@'s OAuth 2.0 documentation](/discover/deployment/-/knowledge_base/7-2/oauth-2-0). 
The following sections show you how to use OAuth 2.0 to authenticate web API 
requests. 

### Obtaining the OAuth 2.0 Token [](id=obtaining-the-oauth-2-0-token)

Before using OAuth 2.0 to invoke a web API, you must register your application 
(your web API's consumer) as an authorized OAuth client. To do this, follow the 
instructions in the 
[Creating an Application](/discover/deployment/-/knowledge_base/7-2/oauth-2-0#creating-an-application) 
section of the OAuth 2.0 documentation. When creating the application, fill in
the form as follows: 

**Application Name:** Your application's name. 

**Client Profile:** Headless Server. 

**Allowed Authorization Types:** Check *Client Credentials*. 

After clicking *Save* to finish creating the application, write down the Client 
ID and Client Secret values that appear at the top of the form. 

Next, you must get an OAuth 2.0 access token. To do this, see the tutorial 
[Authorization Account Access with OAuth 2](/discover/deployment/-/knowledge_base/7-2/authorizing-account-access-with-oauth2). 

### Invoking the Service with an OAuth 2.0 Token [](id=invoking-the-service-with-an-oauth-2-0-token)

Once you have a valid OAuth 2.0 token, include it in the request's 
`Authorization` header, specifying that the authentication type is a 
[bearer token](https://tools.ietf.org/html/rfc6750). 
For example: 

    curl -H "Authorization: Bearer d5571ff781dc555415c478872f0755c773fa159" http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/

The response will contain the resources that the authenticated user has 
permission to access, just like the response from Basic authentication. 

# Making Unauthenticated Requests [](id=making-unauthenticated-requests)

Unauthenticated requests are disabled by default in @product@'s headless REST 
APIs. You can, however, enable them manually by following these steps: 

1.  Create the config file 
    `com.liferay.headless.delivery.internal.jaxrs.application.HeadlessDeliveryApplication-default.config` 
    and add this code to it: 

        auth2.scopechecker.type="none"
        auth.verifier.auth.verifier.BasicAuthHeaderAuthVerifier.urls.includes="*"
        auth.verifier.auth.verifier.OAuth2RestAuthVerifier.urls.includes="*"
        auth.verifier.guest.allowed="true"

    Note that the last property (`auth.verifier.guest.allowed`) lets guests 
    access public content via the APIs. To turn this off, set the property to 
    `false`. 

2.  Deploy the config file to `[Liferay Home]/osgi/configs`. Note that 
    [Liferay Home](/discover/deployment/-/knowledge_base/7-2/installing-liferay#liferay-home) 
    is typically the application server's parent folder. 

3.  Test the APIs by making a request to an OpenAPI profile URL: 

        curl "http://localhost:8080/o/headless-delivery/v1.0/openapi.yaml"

You should get the OpenAPI profile for the API you sent the request to. 

## Related Topics [](id=related-topics)
