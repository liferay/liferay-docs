# OpenID Single Sign On Authentication [](id=openid-single-sign-on-authentication)

OpenID is a single sign-on standard implemented by multiple vendors.  Users can
register for an ID with the vendor they trust. The credential issued by that
vendor can be used by all the web sites that support OpenID. Some high profile
OpenID vendors are Google, Paypal, Amazon, and Microsoft. Please see the [OpenID
site](http://www.openid.net/) for a more complete list.

With OpenID, users don't have to register for a new account on every site which
requires an account. Users register on *one* site (the OpenID provider's site)
and then use those credentials to authenticate to many web sites which support
OpenID. Web site owners sometimes struggle to build communities because users
are reluctant to register for *another* account.  Supporting OpenID removes that
barrier, making it easier for site owners to build their communities. All the
account information is kept with the OpenID provider, making it much easier to
manage this information and keep it up to date.

@product@ can act as an OpenID consumer, allowing users to automatically
register and sign in with their OpenID accounts. Internally, the product uses
[OpenID4Java](https://github.com/jbufu/openid4java) to implement the feature.

## OpenID at the System Scope [](id=openid-at-the-system-scope)

OpenID is enabled by default in @product@ but can be disabled or enabled at either
the system scope or portal instance scope. To configure the OpenID SSO module at
the system level, navigate to the Control Panel and click on *Configuration*
&rarr; *System Settings*. Then click on the *Foundation* category and find
*OpenID* in the list. There's only a single configuration setting. Check the
*Enabled* box to enable OpenID at the system scope (for all portal instances),
uncheck it to disable it at the system scope.

## OpenID at the Instance Scope [](id=openid-at-the-instance-scope)

To configure the OpenID SSO module at the portal instance scope, navigate to the
Control Panel and click on *Configuration* &rarr; *Instance Settings*, then on
*Authentication* &rarr; *OpenID*. There's only a single configuration setting.
Check the *Enabled* box to enable OpenID for the current portal instance,
or uncheck it to disable it for the current portal instance.

<!--
## Related Topics [](id=related-topics)

- @product@ Security Overview
- Signing in to @product@
- Token-based Single Sign On Authentication
-->
