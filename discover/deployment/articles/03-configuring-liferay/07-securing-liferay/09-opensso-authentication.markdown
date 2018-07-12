# OpenAM Single Sign On Authentication [](id=opensso-single-sign-on-authentication)

OpenAM is an open source single sign-on solution that comes from the code base
of Sun's System Access Manager product. @product@ integrates with OpenAM,
allowing you to use OpenAM to integrate @product@ into an infrastructure that
contains a multitude of different authentication schemes against different
repositories of identities.

Note that OpenAM relies on cookie sharing between applications. Thus, in order
for OpenAM to work, all applications that require SSO must be in the same web
domain.

You can set up OpenAM on the same or different server as @product@ . If you
are using the same @product@ server to host your OpenAM, you must deploy
the OpenAM `.war`. The `.war` is available
[here](https://www.forgerock.com/platform/access-management/). Otherwise, follow
the instructions at the
[OpenAM site](https://backstage.forgerock.com/docs/openam/12.0.4/install-guide)
to install OpenAM. Once you have it installed, create the @product@
administrative user in it. Users are mapped back and forth by screen names. By
default, the @product@ administrative user has a screen name of *test*, so if
you were to use that account, in OpenAM, register the user with the ID of *test*
and an email address of *test@liferay.com*. Once you have the user set up, log
in to OpenAM using this user.

In the same browser window, log in to @product@ as the administrative user (using
the email address *test@liferay.com*). Go to the Control Panel and click
*Configuration* &rarr; *Instance Settings* &rarr; *Authentication* &rarr;
*OpenSSO* at the top. Modify the three URL fields (Login URL, Logout URL, and
Service URL) so they point to your OpenAM server (in other words, only modify the host
name portion of the URLs), check the *Enabled* box, and click *Save*.
@product@ then redirects users to OpenAM when they request the `/c/portal/login`
URL *for example, when they click on the *Sign In* link).

@product@'s OpenAM configuration can be applied at either the system scope or at
the instance scope. To configure the OpenAM SSO module at the system scope,
navigate to @product@'s Control Panel, click on *Configuration* &rarr; *System
Settings* &rarr; *Foundation* and find the OpenSSO entry. Click on it and you'll
find these settings to configure. The values configured here provide the default
values for all portal instances. Enter the in the same format as you would when
initializing a Java primitive type with a literal value.

Property Label | Property Key | Description | Type
----- | ----- | ----- | -----
**Enabled** | `enabled` | Check this box to enable OpenAM authentication. Note that OpenAM will work only if LDAP authentication is also enabled and @product@'s authentication type is set to screen name. | `boolean`
**Import from LDAP** | `importFromLDAP` | If this is checked, users authenticated from OpenAM that do not exist in @product@ are imported from LDAP. LDAP must be enabled. | `boolean`
**Login URL** | `loginURL` | The URL to the login page of the OpenAM server | `String`
**Logout URL** | `logoutURL` | The URL to the logout page of the OpenAM server | `String`
**Service URL** | `serviceURL` | The URL by which OpenAM can be accessed to use the authenticated web services. If you are using OpenAM Express 8 or higher, you need to have the server running Java 6. | `String`
**Screen Name Attribute** | `screenNameAttr` | The name of the attribute on the OpenAM representing the user's screen name | `String`
**Email Address Attribute** | `emailAddressAttr` | The name of the attribute on the OpenAM representing the user's email address | `String`
**First Name Attribute** | `firstNameAttr` | The name of the attribute on the OpenAM representing the user's first name | `String`
**Last Name Attribute** | `lastNameAttr` | The name of the attribute on the OpenAM representing the user's last name | `String`

To override these default settings for a particular portal instance, navigate
to @product@'s Control Panel, click on *Configuration* &rarr; *Instance Settings*,
and then click on *Authentication* at the right and then on *OpenSSO* at the
top.

![Figure 1: @product@'s OpenSSO tab lets you configure OpanAM.](../../../images/opensso.png)

## Summary [](id=summary)

OpenAM is useful when all the applications that require SSO are within the
same web domain. Thus, OpenAM it is often used for intranets. OpenAM can be
deployed via a fronting or reverse proxy web server such as Apache,
intercepting all requests to a set of web applications. For this reason,
OpenAM is also useful when implementing SSO for applications that don't
support SSO out of the box.
