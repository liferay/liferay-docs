# OpenSSO Single Sign On Authentication [](id=opensso-single-sign-on-authentication)

OpenSSO is an open source single sign-on solution that comes from the code base
of Sun's System Access Manager product. @product@ integrates with OpenSSO,
allowing you to use OpenSSO to integrate @product@ into an infrastructure that
contains a multitude of different authentication schemes against different
repositories of identities.

Note that OpenSSO relies on cookie sharing between applications. Thus, in order
for OpenSSO to work, all applications that require SSO must be in the same web
domain.

You can set up OpenSSO on the same server as Liferay or a different box. Follow
the instructions at the [OpenSSO site](http://opensso.dev.java.net/) to install
OpenSSO. Once you have it installed, create the Liferay administrative user in
it. Users are mapped back and forth by screen names. By default, the Liferay
administrative user has a screen name of *test*, so in OpenSSO, you would
register the user with the ID of *test* and an email address of
*test@liferay.com*. Once you have the user set up, log in to Open SSO using
this user.

In the same browser window, log in to Liferay as the same user, using the email
address *test@liferay.com*. Go to the Control Panel and click on
*Configuration* &rarr; *Instance Settings* and then click on *Authentication* at
the right and then on *OpenSSO* at the top. Modify the three URL fields (Login
URL, Logout URL, and Service URL) so they point to your OpenSSO server (i.e.,
only modify the host name portion of the URLs). Then check the *Enabled* box
and click *Save*. @product@ then redirects users to OpenSSO when they request the
`/c/portal/login` URL, e.g., when they click on the *Sign In* link.

Liferay's OpenSSO configuration can be applied at either the system scope or at
the portal instance scope. To configure the OpenSSO SSO module at the system
scope, navigate to Liferay's Control Panel, click on *System* &rarr; *System
Settings*, click on *Platform* and find the OpenSSO Configuration. Click on it
and you'll find these settings to configure. The values configured here provide
the default values for all portal instances. Enter the in the same format as
you would when initialising a Java primitive type with a literal value.

Property Label | Property Key | Description | Type
:----: | :----: | :----: | :----:
**Enabled** | `enabled` | Check this box to enable OpenSSO authentication. Note that OpenSSO will work only if LDAP authentication is also enabled and Liferay's authentication type is set to screen name. | `boolean`
**Import from LDAP** | `importFromLDAP` | If this is checked, users authenticated from OpenSSO that do not exist in Liferay are imported from LDAP. LDAP must be enabled. | `boolean`
**Login URL** | `loginURL` | The URL to the login page of the OpenSSO server | `String`
**Logout URL** | `logoutURL` | The URL to the logout page of the OpenSSO server | `String`
**Service URL** | `serviceURL` | The URL by which OpenSSO can be accessed to use the authenticated web services. If you are using OpenSSO Express 8 or higher, you need to have the server running Java 6. | `String`
**Screen Name Attribute** | `screenNameAttr` | The name of the attribute on the OpenSSO representing the user's screen name | `String`
**Email Address Attribute** | `emailAddressAttr` | The name of the attribute on the OpenSSO representing the user's email address | `String`
**First Name Attribute** | `firstNameAttr` | The name of the attribute on the OpenSSO representing the user's first name | `String`
**Last Name Attribute** | `lastNameAttr` | The name of the attribute on the OpenSSO representing the user's last name | `String`

To override these default settings for a particular portal instance, navigate
to Liferay's Control Panel, click on *Configuration* &rarr; *Instance Settings*,
and then click on *Authentication* at the right and then on *OpenSSO* at the
top.

## Summary [](id=summary)

OpenSSO is useful when all the applications that require SSO are within the
same web domain. Thus, OpenSSO it is often used for intranets. OpenSSO can be
deployed via a fronting or reverse proxy web server such as Apache,
intercepting all requests to a set of web applications. For this reason,
OpenSSO is also useful when implementing SSO for applications that don't
support SSO out of the box.
