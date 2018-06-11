# Authorizing Account Access with OAuth2

Once you have an application registered, you can start authorizing users. To do
that, you must construct the URL to the authorization server (@product@). This
asks users to authorize the requested permissions to their resources, defined as
you saw in the previous tutorial as scopes. 

## Authorization Code Flow

The most common OAuth flow is the Authorization Code flow, used for web
applications. The URL for this requires the following request parameters: 

- `response_type` 
- `client_id` 
- `client_secret` (optional)

To construct a URL for this authorization, therefore, follow this pattern: 

    https://[hostname]/o/oauth2/authorize?response_type=code&client_id=[client ID]

The client ID comes from registering the application. It's automatically
generated (though you can change it if you edit the application). 

This same flow could include the client secret if you created one. This requires
that the client know the secret as well as the ID to request an access token. In
this example, the URL would also have to include the `client_secret` request
parameter: 

    https://[hostname]/o/oauth2/authorize?response_type=code&client_id=[client ID]&client_secret=[client secret]

Whether to use a client secret or not is up to you. You may wish to review the
[OAuth 2.0 Documentation](https://www.oauth.com/oauth2-servers/client-registration/client-id-secret/)
when you make your decision. 

## Authorization with PKCE Flow

This flow is the same as above with the addition of the Proof Key for Code
Exchange (PKCE). It requires another request parameter: `code_verifier`. This
flow is for clients such as smartphone applications that may not have sole
access to the URL (and thus the request parameters) returned by the
authorization server. It protects against a malicious application on the same
system authorizing itself by reading the response code. To do this, the client
application sends a *code verifier* with the authorization request: a string it
has generated and which it only knows. After authorization, the code verifier is
sent with the authorization code, validating the client. 

To support this flow, you must have defined PKCE as an Allowed Authorization
Type when you created the application. This is part of the Native Application
client profile. To request an authorization code using PKCE, use a URL
containing the `code_verifier` request parameter: 


    https://[hostname]/o/oauth2/authorize?response_type=code&client_id=[client ID]&client_secret=[client secret]&code_verifier=[PKCE code]

## Client Credentials and Resource Owner Flows

There are two other, less used flows. If you have a scenario where two servers
exchange agreed upon, non user-centric data, you can bypass the Allow/Deny
screen for users and authorize the client. This is called the Client Credentials
flow, and you'd use this URL pattern: 

    https://[hostname]/o/oauth2/authorize?response_type=code&grant_type=client_credentials&client_id=[client ID]&client_secret=[client secret]

A final flow, where users trust the application with their passwords is rare,
but possible. This is called the Resource Owner Password flow, and its URL
pattern looks like this: 

    https://[hostname]/o/oauth2/authorize?response_type=code&grant_type=password&client_id=[client ID]&client_secret=[client secret]&username=[user@emailaddress.com]&password=

Users are prompted for their passwords, and upon successful log in, receive an
authorization code. 

## Token Use

All flows above result in an authorization code that's sent by the authorization
server (@product@) to the client application. This token is sent in the response
for the client application to store and send along with any future request for
data. 

For example, if the authorization code `946856e2b5ddf0928f6fc55f657bab73` were
sent to the client application, when the client requests data, this code must be
sent in each request header. Using a command line HTTP client such as Curl, you
could send a request like this: 

    curl -H 'Authorization: Bearer 946856e2b5ddf0928f6fc55f657bab73' 'https://[hostname]/o/api/sample2'

OAuth 2.0 provides a convenient way for client applications to be granted access
to particular services (scopes) by users without sharing credential information. 

## Revoking Access

Once access is granted, users or administrators are free to revoke access
whenever they wish. If this happens to a client, the token becomes invalid and
the client would have to ask the user for authorization again. This puts users
in control of what has access to their data, and they can exercise this control
at any time. 

![Figure x: Users have complete control over what applications have access to their data in their account profiles.](../../images/oauth-user-apps.png)

In their account area, users can click *OAuth2 Connected Applications* and see
a list of any applications they've allowed to access their accounts. From here,
they can revoke access by clicking the *Remove Access* item in the Action menu
or the *Remove Access* button in the detail screen for the application. 

Administrators can view the authorizations in the Authorizations tab of any app
in *Control Panel* &rarr; *Configuration* &rarr; *OAuth2 Administration*. 

![Figure x: All authorizations for an app appear in the Authorizations tab for the app.](../../images/oauth-revoke-access.png)

Clicking the *Revoke* button on any authorization listed here revokes that
application's access to that user's account. 

## Summary

OAuth 2.0 provides a complete and secure authorization flow for users, without
their having to share any credential information. Once applications are created
in the system, secure tokens provide access to particular scopes of information,
and this access can be revoked at any time, making OAuth 2.0 a convenient method
for users and developers alike to access the information they need. 
