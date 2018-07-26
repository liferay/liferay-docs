# Making Authenticated Requests [](id=making-authenticated-requests)

When you make an unauthenticated request, you do so as a guest user and receive 
only public information in the response. To make an authenticated request, you 
must authenticate as a specific portal user. 

There are two authentication mechanisms available when invoking web APIs: 

-   **Basic Authentication:** Sends the user credentials as an encoded username 
    and password pair. This is the simplest authentication protocol (available 
    since HTTP/1.0). 
-   **OAuth 2.0:** In @product-ver@, you can use OAuth 2.0 for authentication. 
    See the 
    [deployment guide](/discover/deployment/-/knowledge_base/7-1/oauth-2-0) 
    for more information on OAuth 2.0 in @product@. 

First, you'll learn how send requests with basic authentication. 

## Basic Authentication [](id=basic-authentication)

Basic authentication requires that you send an HTTP `Authorization` header 
containing the encoded username and password. You must first get that encoded 
value. To do so, you can use the `openssl` command in a console, or a `Base64` 
encoder. Either way, you must encode the `user:password` string. For example, 
here's an example of the `openssl` command for encoding the `user:password` 
string for a user `test@liferay.com` with the password `Liferay`: 

    openssl base64 <<< test@liferay.com:Liferay

This returns the encoded value: 

    dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5Cg==

If you don't have the `openssl` command in your console, try the `base64` 
command: 

    base64 <<< test@liferay.com:Liferay

+$$$

**Warning:** Encoding a string as shown here does not encrypt the resulting 
string. Such an encoded string can easily be decoded by executing 
`base64 <<< the-encoded-string`, which returns the original string. 

Anyone listening to your request could therefore decode the `Authorization` 
header and reveal your user name and password. To prevent this, ensure that all 
communication is made through HTTPS, which encrypts the entire message 
(including headers).

$$$

Then use the encoded value for the HTTP Authorization header when sending the 
request: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/organization

The response contains significantly more data than the response from an 
unauthenticated request to the same URL. This is because the authenticated user 
has permission to access the resources in the response. For more information on 
the response's structure, see the tutorials 
[API Vocabulary](liferay.com) 
and 
[Operating with Collections of Data](liferay.com). 

    {
        "totalItems": 2,
        "numberOfItems": 2,
        "view": {
            "@id": "http://localhost:8080/o/api/p/organization?page=1&per_page=30",
            "first": "http://localhost:8080/o/api/p/organization?page=1&per_page=30",
            "last": "http://localhost:8080/o/api/p/organization?page=1&per_page=30",
            "@type": ["PartialCollectionView"],
        },
        "@id": "http://localhost:8080/o/api/p/organization",
        "member": [
            {
                "name": "Engineering",
                "@type": ["Organization"],
                "@id": "http://localhost:8080/o/api/p/organization/37260",
                "members": "http://localhost:8080/o/api/p/organization/37260/person",
                "@context": [
                    {"members": {"@type": "@id"}},
                    {"suborganizations": {"@type": "@id"}}
                ],
                "suborganizations": "http://localhost:8080/o/api/p/organization/37260/organization",
                "location": {
                    "addressCountry": "Spain",
                    "addressRegion": "Madrid",
                    "@type": ["PostalAddress"],
                }
            },
            {
                "name": "Global",
                "@type": ["Organization"],
                "@id": "http://localhost:8080/o/api/p/organization/37277",
                "members": "http://localhost:8080/o/api/p/organization/37277/person",
                "@context": [
                    {"members": {"@type": "@id"}},
                    {"suborganizations": {"@type": "@id"}}
                ],
                "suborganizations": "http://localhost:8080/o/api/p/organization/37277/organization",
                "location": {
                    "@type": ["PostalAddress"],
                }
            }
        ],
        "@context": [
            {"@vocab": "http://schema.org/"},
            "https://www.w3.org/ns/hydra/core#"
        ],
        "@type": ["Collection"],
    }

## OAuth 2.0 Authentication [](id=oauth-2-0-authentication)

@product-ver@ supports authorization via OAuth 2.0, which is a token-based 
authentication mechanism. For more details, see 
[@product@'s OAuth 2.0 documentation](/discover/deployment/-/knowledge_base/7-1/oauth-2-0). 
The following sections show you how to use OAuth 2.0 to authenticate web API 
requests. 

### Obtaining the OAuth 2.0 Token [](id=obtaining-the-oauth-2-0-token)

Before using OAuth 2.0 to invoke a web API, you must register your application 
(your web API's consumer) as an authorized OAuth client. To do this, follow the 
instructions in the 
[Creating an Application](/discover/deployment/-/knowledge_base/7-1/oauth-2-0#creating-an-application) 
section of the OAuth 2.0 documentation. When creating the application in the 
portal, fill in the form as follows: 

-   **Application Name:** Your application's name. 
-   **Client Profile:** Headless Server.
-   **Allowed Authorization Types:** Check *Client Credentials*.

After clicking *Save* to finish creating the application, write down the Client 
ID and Client Secret values that appear at the top of the form. 

Next, you must get an OAuth 2.0 access token. To do this, see the tutorial 
[Authorization Account Access with OAuth 2](/discover/deployment/-/knowledge_base/7-1/authorizing-account-access-with-oauth2). 

### Invoking the Service with an OAuth 2.0 Token [](id=invoking-the-service-with-an-oauth-2-0-token)

Once you have a valid OAuth 2.0 token, include it in the request's 
`Authorization` header, specifying that the authentication type is a 
[bearer token](https://tools.ietf.org/html/rfc6750). 
For example: 

    curl -H "Authorization: Bearer d5571ff781dc555415c478872f0755c773fa159" http://localhost:8080/o/api/p/organization

The response contains the resources that the authenticated user has permission 
to access, just like the response from Basic authentication. 
