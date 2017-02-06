# Token-based Single Sign On Authentication [](id=token-based-single-sign-on-authentication)

Token-based SSO authentication was introduced in @product-ver@ to standardize
support for Shibboleth, SiteMinder, and any other SSO product which works on
the basis of propagating a token via one of the following mechanisms:

- HTTP request parameter
- HTTP request header
- HTTP cookie
- Session attribute

The authentication token contains either the @product@ user's screen name
or email address, whichever @product@ has been configured to use for the
particular company (portal instance). Recall that @product@ supports three authentication methods:

- By email address
- By screen name
- By user ID

Note that @product@'s token-based authentication mechanism only supports email
address and screen name. If the portal is configured to use user ID when a
token-based authentication is attempted, the `TokenAutoLogin` class logs this
warning:

    Incompatible setting for: company.security.auth.type

Please note that the above sources are fully trusted. Consequently, a security
mechanism external to @product@ must be used. For example, you could use a
fronting web server like Apache. The chosen fronting solution must prevent
malicious @product@ user impersonation that otherwise might be possible by
sending HTTP requests directly to @product@ from the client's web browser.

Token based authentication is disabled by default. To manage token based SSO
authentication, navigate to @product@'s Control Panel, click on *System
Settings*, then find and click on the *TokenConfiguration* module. Here are the
configuration options for the Token SSO module:

**Authentication cookies:** Set this to the cookie names that must be removed
after logout.

**Enabled:** Check this box to enable token-based SSO authentication.

**Import from LDAP:** Check this box to automatically import users from LDAP if
they do not exist in the portal.

**Logout redirect URL:** When user logs out of @product@, the user is
redirected to this URL.

**Token location:** Set this to the location of the user token. As mentioned
earlier, the options are:

- HTTP request parameter
- HTTP request header
- HTTP cookie
- Session attribute

**User token name:** Set equal to the name of the token. This will be retrieved
from the specified location.

@product@'s token-based SSO authentication mechanism is highly flexible
and compatible with any SSO solution which can provide it with a valid @product@
user's screen name or email address. These include Shibboleth and SiteMinder.
