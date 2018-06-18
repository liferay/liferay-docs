# OAuth 2.0 [](id=oauth-2-0)

OAuth 2.0 is an industry-standard authorization protocol. Users can seamlessly
share select credentials from another website to log into yours. You've probably
seen this before: any time you see a "Log In with Facebook," or "Log In with
Google" button, or you authorize a third-party Twitter client, you're seeing
OAuth 2.0 in action. It works by authorizing password-less access to portions of
user-owned resources (such as an email address, a user profile picture, or
something else from your account) and other permissioned resources.

OAuth 2.0's design encrypts all authorization transport through HTTPS, which
prevents data passed between the systems from being intercepted. 

## Flow of OAuth 2.0 [](id=flow-of-oauth-2-0)

OAuth 2.0 takes advantage of web standards wherever possible: transport is
encrypted with HTTPS; tokens are implemented as HTTP headers; data is passed via
web services. 

Here's how OAuth 2.0 works: 

![Figure 1: OAuth 2.0 takes advantage of web standards.](../../../images/oauth-flow.png)

1.  A user accesses a third-party application that supports authorization via
    credentials from a Liferay-based website. In the application (web or
    mobile), the user requests authorization via OAuth, sending the browser or
    app to the Liferay-based website. When using PKCE (explained below), the
    application also generates a code verifier and sends a code challenge that 
    is created by applying a transformation to it. 

2.  The user authenticates and is shown the resources the application wants
    permission to access. When the user gives permission by clicking *Allow*,
    Liferay generates an authorization code that's sent to the application over
    HTTPS. 

3.  The application then requests a more permanent authorization token and
    sends the code with the request (along with the PKCE code verifier). 

4.  If the authorization code matches (and transformed PKCE code verifier matches 
    the previously sent code challenge), Liferay cryptographically generates an 
    authorization token for this user and application combination. It sends the 
    token to the application over HTTPS. Initial authorization is now complete! 

5.  When the application must retrieve data, it sends the token with the request
    to prove it's authorized to have that data.

6.  Provided the token matches what Liferay has for that user and application,
    access is granted to retrieve the data. 

That description throws around a lot of terms. Definitions provided below. 

## OAuth 2.0 Terminology [](id=oauth-2-0-terminology)

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
This list is sent during the initial authorization request (or otherwise
defaults to scopes selected in the application registration) so users can grant
or deny access to their resources. 

**Callback URI:** Also called a Redirection Endpoint URI. After authorization is
complete, the authorization server (i.e., Liferay) sends the client to this
location. 

## Creating an Application [](id=creating-an-application)

When you have an application that can use OAuth 2.0 for authorization, you must
register that application so @product@ can recognize it. Do this by accessing
*Control Panel* &rarr; *Configuration* &rarr; *OAuth2 Administration*: 

1.  Click the *Add* (![add](../../../images/icon-add.png)) button. 

2.  Fill out the form (description below). 

3.  Click *Save* to save the application. 

![Figure 2: Adding an application registers it so users can authorize access to their data.](../../../images/oauth-new-application.png)

**Name:** Give the application a recognizable title. 

**Website URL:** Add a link to the application's website. 

**Callback URIs:** Enter at least one (line-separated) URI where users should
be redirected after they authorize (or refuse to authorize) access to their
accounts. This should link to a handler for whichever Allowed Authorization
Types you support (see below). 

**Client Profile:** Choose a template that filters the authorization types
that are appropriate (secure) for that profile. For example, if your application
is a web application, choose *Web Application*, and these authorization types 
are available and selected automatically: Authorization Code, Client Credentials, 
Refresh Token, and Resource Owner Password Credentials. These are OAuth 2 "flows" 
documented in the 
[OAuth2 RFC 6749 Standards Document](https://tools.ietf.org/html/rfc6749). 
If you want to select authorization types manually, select *Other*. 

**Allowed Authorization Types:** Select the defined OAuth 2 
[protocol flows](https://tools.ietf.org/html/rfc6749#section-1.2) your
application supports. Several common combinations are defined for you in the
various Client Profiles above. 

After you save the form, it reappears with some additional fields: 

**Client ID:** The system generates this for you; it's an identifier for your
application, so that @product@ knows what application is being authorized to
access user data. 

**Client Secret:** Click the *pencil* icon to generate a client secret. The
secret identifies the client is genuine during the authorization process (see
figure 1 above). Not all client profiles require a client secret, because
some are incapable of keeping it secret! This is when the aforementioned PKCE
code challenge and verifier is needed.

**Icon:** Upload an icon that your application's users identify with your
application. This is displayed on the authorization screen. 

**Privacy Policy URL:** Add a link to your application's privacy policy. 

**Token Introspection:** Allow your application to retrieve metadata from the
token by requesting it from @product@. This implements 
[RFC 7662](https://tools.ietf.org/html/rfc7662).

Excellent! Now you know how to add OAuth2 authorization for your application to
@product@! Next, you must define scopes of user data the application can access.
