# Token-based Single Sign On Authentication [](id=token-based-single-sign-on-authentication)

Token-based SSO authentication was introduced in @product@ 7.0 to standardize
support for Shibboleth, SiteMinder, and any other SSO product which works on the
basis of propagating a token via one of the following mechanisms:

- HTTP request parameter
- HTTP request header
- HTTP cookie
- Session attribute

The authentication token contains either the @product@ user's screen name or
email address, whichever @product@ has been configured to use for the particular
company (portal instance). Recall that @product@ supports three authentication
methods:

- By email address
- By screen name
- By user ID

Note that @product@'s token-based authentication mechanism only supports email
address and screen name. If the portal is configured to use user ID when a
token-based authentication is attempted, the `TokenAutoLogin` class logs this
warning:

    Incompatible setting for: company.security.auth.type

Please note that the above sources are fully trusted. 

Furthermore, you must use a security mechanism external to @product@, such as a
fronting web server like Apache. The chosen fronting solution must prevent
malicious @product@ user impersonation that otherwise might be possible by
sending HTTP requests directly to @product@ from the client's web browser.

Token based authentication is disabled by default. To manage token
based SSO authentication, navigate to @product@'s Control Panel, click on
*System Settings*, &rarr; *Security* &rarr; *SSO*. Token Based SSO appears in
the list at the bottom. Alternately, you can search for *Token* in the Search
field. Here are the configuration options for the Token Based SSO module:

**Authentication cookies:** Set this to the cookie names that must be removed
after logout. (Example: `SMIDENTITY`, `SMSESSION`)

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
from the specified location. (Example: SM_USER)

Remember to click *Save* to activate Token Based SSO.

## Required SiteMinder Configuration [](id=required-siteminder-configuration)

If you use SiteMinder, note that @product@ sometimes uses the tilde character in
its URLs. By default, SiteMinder treats the tilde character (and others) as bad
characters and returns an HTTP 500 error if it processes a URL containing any of
them. To avoid this issue, change this default setting in the SiteMinder
configuration to this one:

	BadUrlChars       //,./,/.,/*,*.,\,%00-%1f,%7f-%ff,%25

The configuration above is the same as the default except the `~` was removed
from the bad URL character list. Restart SiteMinder to make your configuration
update take effect. For more information, please refer to SiteMinder's
[documentation](https://support.ca.com/cadocs/0/CA%20SiteMinder%20r6%200%20SP6-ENU/Bookshelf_Files/HTML/index.htm?toc.htm?258201.html)

# Summary [](id=summary)

@product@'s token-based SSO authentication mechanism is highly flexible
and compatible with any SSO solution which can provide it with a valid @product@
user's screen name or email address. These include Shibboleth and SiteMinder.
