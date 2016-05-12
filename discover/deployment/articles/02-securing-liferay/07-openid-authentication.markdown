# OpenID Single Sign On Authentication [](id=openid-single-sign-on-authentication)

OpenID is a single sign-on standard which is implemented by multiple vendors.
The idea is that multiple vendors implement the standard. Users can register
for an ID with any vendor they trust. The credential issued by that vendor can
be used by all the web sites that support OpenID. Some high profile
OpenID vendors are [AOL](http://openid.aol.com/screenname),
[LiveDoor](http://profile.livedoor.com/username), and
[LiveJournal](http://username.livejournal.com/). Please see the
[OpenID site](http://www.openid.net/) for a more complete list.

A main benefit of OpenID for the user is that the user no longer has to
register for a new account on every site which requires an account. Users can
register on *one* site (the OpenID provider's site) and then use those
credentials to authenticate to many web sites which support OpenID. Many web
site owners often struggle to build communities because end users are reluctant
to register for so many different accounts. Supporting OpenID makes it easier
for site owners to build their communities because the barriers to
participating (i.e., the effort it takes to register for and keep track of many
accounts) are removed. All of the account information is kept with the OpenID
provider, making it much easier to manage this information and keep it up to
date.

@product@ can act as an OpenID consumer, allowing users to automatically
register and sign in with their OpenID accounts. Internally, the product uses
[OpenID4Java](https://github.com/jbufu/openid4java) to implement the feature.

OpenID is enabled by default in Liferay but can be disabled or enabled at
either the system scope or portal instance scope. To configure the OpenID SSO
module at the system level, navigate to the Control Panel and click on *System*
&rarr; *System Settings*. Then click on the *Platform* category and find
*OpenID Configuration* in the list. There's only a single configuration
setting. Check the *Enabled* box to enable OpenID at the system scope (for all
portal instances), uncheck it to disable it at the system scope.

To configure the OpenID SSO module at the portal instance scope, navigate to
the Control Panel and click on *Configuration* &rarr; *Instance Settings*. Then
click on *Authentication* at the right and then on *OpenID* at the top. As for
the system scope, there's only a single configuration settings for the portal
instance scope. Check the *Enabled* box to enable OpenID for the current portal
instance, uncheck it to disable it for the current portal instance.

<!--
## Related Topics [](id=related-topics)

- Liferay Security Overview
- Signing in to Liferay
- Token-based Single Sign On Authentication
-->
