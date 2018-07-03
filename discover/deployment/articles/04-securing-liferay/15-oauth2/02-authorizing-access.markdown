# Authorizing Account Access with OAuth2 [](id=authorizing-account-access-with-oauth2)

Once you have an application registered, you can start authorizing users. To do
that, you must construct the URL to the authorization server (@product@). The
authorization server asks users to authorize the requested permissions to their
resources, defined as you saw in the previous tutorial as scopes. 

## Authorization Code Flow [](id=authorization-code-flow)

The most common OAuth flow is the Authorization Code flow, used for web
applications. The URL for this requires the following request parameters: 

- `response_type` 
- `client_id` 

To construct a URL for this authorization, therefore, follow this pattern: 

    https://[hostname]/o/oauth2/authorize?response_type=code&client_id=[client ID]

The client ID comes from registering the application. It's automatically
generated (though you can change it if you edit the application). 

IMPORTANT: Sometimes the phrase "web application" is used loosely, implying
applications where the above URL is requested from the web browser directly. If
this happens, you'd leak the client secret, compromising the security of the
grant flow and the application. In such cases, select the "User Agent
Application" client profile instead when registering your application. This
makes a secure alternative available to your application: PKCE Extended
Authorization Code flow (see below).

Once the user has authorized the requested permissions to their resources, the
authorization server returns an authorization code to your application at its
registered callback URI (a.k.a. redirect URI) as a query string parameter. 

	[your callback URI]?code=[authorization server generated code]

Your application must then exchange this authorization code for an access token
by sending a POST request following this pattern:

	http://localhost:8080/o/oauth2/token

With the following parameters in the body (encoded as 
`application/x-www-form-urlencoded`):

	client_id=[client ID]
	client_secret=[client secret]
	grant_type=authorization_code
	code=[authorization server generated code]
	redirect_uri=[registered callback URI]

In the body of HTTP response to this request, you will receive JSON like this:

	{
		"access_token": "[authorization server generated access token]",
		"token_type": "Bearer",
		"expires_in": 600,
		"scope": "[the scopes that were authorized by the user]",
		"refresh_token": "[authorization server generated refresh token]"
	}

From this you should extract and persist the access token. If you intend to use 
the token for an indefinite amount of time (beyond 600 seconds from the above 
example) you also need the refresh token. This can be used in conjunction with 
the Refresh Token Flow to obtain a new access token, with the same permissions,
without further user authorization.  The authorization server only issues
Refresh Tokens if your application registration is registered for this flow.

## PKCE Extended Authorization Code Flow [](id=pkce-extended-authorization-code-flow)

This flow is the same as above with the addition of the Proof Key for Code
Exchange (PKCE). It requires another request parameter: `code_challenge`. This
flow is for clients like smartphone applications that may not have sole access
to the URL (and thus the request parameters) redirected to by the authorization
server after the user authorization. It protects against a malicious application
on the same system authorizing itself by reading the response code. To do this,
the client application sends a *code challenge* with the authorization request:
a string it has generated and which it only knows. To generate this string it
must first create another secret string known as the *Code Verifier*, and then
apply a transformation to it. After authorization, the code verifier is sent
with the authorization code, validating the client. 

For more detail on how to do this, please refer to the 
[PKCE specification](https://tools.ietf.org/html/rfc7636).

To support this flow, you must have defined PKCE as an Allowed Authorization
Type when you created the application. This is part of the Native Application 
and User Agent Application client profiles. To request an authorization code 
using PKCE, use a URL containing the `code_challenge` request parameter: 

    https://[hostname]/o/oauth2/authorize?response_type=code&client_id=[client ID]&code_challenge=[PKCE code challenge]

The rest of the process is identical to Authorization Code flow, except that
when making the final request to get the access token, must also provide the
following parameter:

	code_verifier=[Code Verifier that was transformed and sent as code_challenge previously]

## Client Credentials and Resource Owner Flows [](id=client-credentials-and-resource-owner-flows)

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

## Token Use [](id=token-use)

All flows above result in an access token that's sent by the authorization
server (@product@) to the client application. This token is sent in the response
for the client application to store and send along with any future request for
data. 

For example, say the authorization code `946856e2b5ddf0928f6fc55f657bab73` was
sent to the client application. When the client requests data, this code must be
sent in each request header. Using a command line HTTP client such as Curl, you
could send a request like this: 

    curl -H 'Authorization: Bearer 946856e2b5ddf0928f6fc55f657bab73' 'https://[hostname]/o/api/sample2'

OAuth 2.0 provides a convenient way for client applications to be granted access
to particular services (scopes) by users without sharing credential information. 

## Revoking Access [](id=revoking-access)

Once access is granted, users or administrators are free to revoke access
whenever they wish. If this happens to a client, the token becomes invalid and
the client must ask the user for authorization again. This puts users in control
of what has access to their data, and they can exercise this control at any
time. 

![Figure 1: Users have complete control over what applications have access to their data in their account profiles.](../../../images/oauth-user-apps.png)

In their account area, users can click *OAuth2 Connected Applications* and see
a list of applications they've allowed to access their accounts. From here,
they can revoke access by clicking the *Remove Access* item in the Action menu
or the *Remove Access* button in the detail screen for the application. 

Administrators can view the authorizations in the Authorizations tab of any app
in *Control Panel* &rarr; *Configuration* &rarr; *OAuth2 Administration*. 

![Figure 2: All authorizations for an app appear in the Authorizations tab for the app.](../../../images/oauth-revoke-access.png)

Clicking the *Revoke* button on any listed authorization revokes that
application's access to that user's account. 

## Summary [](id=summary)

OAuth 2.0 provides a complete and secure authorization flow for users, without
their having to share any credential information. Once applications are created
in the system, secure tokens provide access to particular scopes of information,
and this access can be revoked at any time, making OAuth 2.0 a convenient method
for users and developers alike to access the information they need. 
