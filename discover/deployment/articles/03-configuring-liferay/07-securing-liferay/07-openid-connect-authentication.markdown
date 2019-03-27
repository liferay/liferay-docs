# Authenticating with OpenID Connect [](id=authenticating-with-openid-connect)

OpenID Connect is a lightweight authentication layer built on top of the 
[OAuth 2.0](https://oauth.net/2/) 
authorization protocol. It compliments having local accounts by enabling users
to authenticate using accounts they have on other systems. Users who avoid
signing up for new accounts can then use an account they already have to sign
into your website. By using OpenID Connect, you *delegate* user authentication
to other providers, making it easy for users with existing accounts to
authenticate to your system. 

+$$$

**Note:** You can add multiple providers to your installation, but @product@
can't yet be an OpenID Connect provider. 

$$$

Because OpenID Connect is built on OAuth 2.0, its token flow is similar. OAuth
2.0 is only an authorization protocol, so it sends an *access token* that grants
access to particular APIs. OpenID Connect adds to this an *identity token* that
passes user information like name and email, provided the user has authenticated
and granted permission. 

## Creating a Client in OpenID Connect Provider [](id=creating-a-client-in-openid-connect-provider)

To use OpenID Connect, you must first register it as a client in your provider.
This is an OAuth 2.0 client. The process varies by provider: 

1.  Navigate to the provider's website and create a client. 

2.  During the creation process, you must supply an *authorized redirect URL*
    that can process the tokens sent from the provider. @product@'s URL is

        https://[server.domain]/c/portal/login/openidconnect

3.  The provider will send several pieces of information. Some of these, like
    the Discovery Endpoint, Authorization Endpoint, or Issuer URL are the same
    regardless of the client. The two pieces of information unique to your
    request are the `client_id` and the `client_secret`. 

Collect the information from the provider. You'll need it create the provider
next. 

## Configuring an OpenID Connect Provider Connection [](id=configuring-an-openid-connect-provider-connection)

Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; *Foundation* and select ***OpenID Connect Provider*** (*System Scope*) and follow these steps: 

1.  Add the provider by clicking the *Add* button. 

2.  Use the information you received from the provider to fill out the form: 

**Provider Name:** This name appears in the Sign-In Portlet when users use
OpenID Connect to log in. 

**OpenID Client ID:** Provide the OAuth 2.0 Client ID you received from your
provider. 

**OpenID Connect Client Secret:** Provide the OAuth 2.0 Client Secret you
received from your provider. 

**Scopes:** Leave the default, which requests the user name and the email. Your
provider may offer other scopes of user information. 

**Discovery Endpoint:** Other URLs may be obtained from this URL, and they vary
by provider. 

**Authorization Endpoint:** This URL points to the provider's URL for
authorizing the user (i.e., signing the user in). 

**Issuer URL:** The provider's URL that points to information about the provider
who is issuing the user information. 

**JWKS URI:** A URL that points to the provider's JSON Web Key Set that contains
the public keys that can verify the provider's tokens. 

**Subject Types:** A Subject Identifier is a unique and never reassigned
identifier the provider uses to establish who the user is, and is consumed by
the client (i.e., @product@). There are two types: public (provides the same
value to all clients) and private (provides a different value to each client). 

**Token Endpoint:** The provider's URL where tokens can be requested. 

**User Information Endpoint:** The OAuth 2.0 protected URL from which user
information can be obtained. 

Once you've filled out the form, click *Save*, and you're ready to enable OpenID
Connect authentication. 

+$$$

System Settings configuration file: 

    com.liferay.portal.security.sso.openid.connect.internal.configuration.OpenIdConnectProviderConfiguration-[name].config

where `[name]` is a descriptive, but unique name for example `provider1`.

$$$

## Enabling OpenID Connect Authentication [](id=enabling-openid-connect-authentication)

1. Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*
   &rarr; *Foundation* and select ***OpenID Connect***. 

2. Click the *Enabled* check box, and then click *Save*. 

**Note:** You can also enable OpenID Connect authentication for the given
virtual instance through the *Control Panel* &rarr; *Configuration* &rarr;
*Instance Settings* &rarr; *OpenID Connect* tab.

+$$$

System Settings configuration file:

    com.liferay.portal.security.sso.openid.connect.configuration.OpenIdConnectConfiguration.config

$$$
    
Now users can sign in with OpenID Connect. 

## Signing In With OpenID Connect [](id=signing-in-with-openid-connect)

There's a new link in the Sign-In Portlet for signing in with OpenID Connect: 

1.  From the Sign-In Portlet, click the OpenID Connect link at the bottom. 

2.  Choose a provider and click *Sign In*. 

3.  This takes you to your provider's sign in page. Enter your credentials and
    log in. 

4.  Upon successful authentication, you're redirected back to @product@ in an
    authenticated state. 

OpenID is a standards-based, secure way to authenticate users from other
systems. 
