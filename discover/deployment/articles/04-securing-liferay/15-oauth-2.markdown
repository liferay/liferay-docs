# OAuth 2.0 

OAuth 2.0 is an industry-standard authorization protocol. Users can seamlessly
share select credentials from another website to log into yours. You've probably
seen this before: any time you see a "Log In with Facebook," or "Log In with
Google" button, or you authorize a third-party Twitter client, you're seeing
OAuth 2.0 in action. It works by authorizing password-less access to portions of
user-owned resources (such as an email address, a user profile picture, or
something else from your account). 

OAuth 2.0's design encrypts all authorization transport through HTTPS, which
prevents data passed between the systems from being intercepted. 

## Flow of OAuth 2.0

OAuth 2.0 takes advantage of web standards wherever possible: transport is
encrypted with HTTPS; tokens are implemented as HTTP headers; data is passed via
web services. 

Here's how OAuth 2.0 works: 

![Figure x: OAuth 2.0 takes advantage of web standards.](../../images/oauth-flow.png)

1.  A user accesses a third-party application that supports authorization via
    credentials from a Liferay-based website. In the application (web or
    mobile), the user requests authorization via OAuth, sending the browser or
    app to the Liferay-based website. When using PKCE (explained below), the
    application also sends a code verifier. 

2.  The user authenticates and is shown the resources the application wants
    permission to access. When the user gives permission by clicking *Allow*,
    Liferay generates an authorization code that's sent to the application over
    HTTPS. 

3.  The application then requests a more permanent authorization token and
    sends the code with the request (along with the PKCE code verifier
    previously sent in the first request). 

4.  If the authorization code and the code verifier match, Liferay
    cryptographically generates an authorization token for this user and
    application combination. It sends the token to the application over HTTPS.
    Initial authorization is now complete! 

5.  When the application must retrieve data it's authorized to retrieve from
    Liferay, it sends the token with the request. 

6.  Provided the token matches what Liferay has for that user and application,
    access is granted to retrieve the data. 

That description throws around a lot of terms. Definitions provided below. 

## OAuth 2.0 Terminology

**Authentication:** Providing credentials so a system can verify who you are by
matching those credentials with what it has stored. OAuth is not an
authentication protocol. 

**Authorization:** Granting access to resources stored on another system. OAuth
is an authorization protocol. 

**Application:** Any client (web, mobile, etc.) that must be authorized to have
access to resources. Applications must be registered by administrators before
users can authorize access to their resources. 

**Client:** Almost synonymous with *application*, except that applications can
have variants, such as web and mobile. These variants are clients. 

**Client ID:** An identifier given to a client so it can be recognized. 

**Client Secret:** A previously agreed-upon text string that identifies a client
as a legitimate client. 

**Access Token:** A cryptographically generated text string that identifies
a user/client combination for access to that User's resources. 

**Response Type:** OAuth 2.0 supports several response types. Pictured and
described above is the most common: the authorization code. Other response types
are *password* (logging in with a user name and password), and *client
credentials* (headless predefined application access). 

**Scope:** A list of items that define what the application wants to access.
This list is sent during the initial authorization request so the User can grant
or deny access to his/her resources. 

**Callback URI:** Also called a Redirection Endpoint URI, this is a location the
authorization server (i.e., Liferay) sends the client to after authorization is
complete. 

## Creating an Application


