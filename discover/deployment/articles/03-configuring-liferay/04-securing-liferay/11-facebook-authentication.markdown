# Facebook Connect Single Sign On Authentication [](id=facebook-connect-single-sign-on-authentication)

Facebook Connect SSO authentication is an integration with Facebook's Graph API.
It retrieves the user's Facebook profile information and matches it to existing
@product@ users (either by Facebook ID or by email address). Once the user's
primary Facebook email address is found, the Facebook Connect SSO authentication
module searches for the same email address in @product@'s `User_` table. If a
match is found, the user is signed on, provided that the required permissions
have been granted on the Facebook side.

If a match isn't made, the user is prompted in @product@ to add a user from
Facebook. When new @product@ users are added this way, they are created by
retrieving the following four fields from Facebook:

- Email address
- First name
- Last name
- Gender

In order to integrate @product@ with Facebook, you must first create an
"application" on Facebook's website here:
[https://developers.facebook.com](https://developers.facebook.com). This is
necessary because Facebook Connect requires @product@ to authenticate using the
OAuth 2.0 protocol. Facebook provides you the necessary application ID and
secret used in OAuth messages sent between @product@ and Facebook. One benefit of
this is that a Facebook user can revoke @product@'s access at any time.

## Managing Facebook Connect SSO Authentication [](id=managing-facebook-connect-sso-authentication)

Apply Facebook Connect SSO configuration at the system scope or instance scope.
To configure the Facebook Connect SSO module at the System level, navigate to
@product@'s *Control Panel* &rarr; Configuration &rarr; System Settings*, and find
the *Facebook Connect* module under the *Foundation* heading. The values
configured here provide the default values for all portal instances.

To override these defaults for a particular portal instance, navigate to
@product@'s *Control Panel*, click on *Instance Settings*, and find *Facebook* 
within the Authentication section.

**Enabled**: Check this box to enable Facebook Connect SSO authentication.

**Require Verified Account**: Check this box to allow logins by Facebook users
who have gone through the Facebook email verification process to prove that
they can access the inbox associated with the email address they provided when
registering for a Facebook account.

**Application ID**: This can only be set at the portal instance level. Enter
the ID of your registered Facebook application.

**Application Secret**: This can only be set at the portal instance level.
Enter the secret of your registered Facebook application.

**Graph URL**: This is the base URL of the Facebook graph API. Only change this
if Facebook changes their graph API. If Facebook's graph API remains unchanged,
use the default graph URL.

**OAuth Authorization URL**: This is Facebook's OAuth authorization URL. You
will only need to change this if Facebook changes their OAuth authorization
endpoint. This URL will be decorated with dynamic data and linked to from the
Liferay Sign In portlet.

**OAuth Token URL**: This is Facebook's OAuth access token URL. @product@ uses
this URL to exchange a request token for an access token.

**OAuth Redirect URL**: This is the URL that the user will be directed to once
an OAuth request token has been generated. The URL points to a @product@ service
which exchanges the request token for the access token which is required in
order for @product@ to make successful calls to the Facebook Graph API. You
should only need to change this URL if requests to your @product@ instance need
to go via a fronting webserver such as Apache that does URL rewriting.

## Related Topics [](id=related-topics)

(Coming Soon)
