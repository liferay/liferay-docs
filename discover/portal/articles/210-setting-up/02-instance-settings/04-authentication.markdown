# Authentication [](id=authentication)

Configure authentication at Control Panel &rarr; Configuration &rarr; Instance
Settings. In the Configuration tab, scroll down to the Authentication section
and expand it.

Authentication has several tabs: General, OpenSSO, CAS, NTLM, LDAP, OpenID,
Facebook, Google, and OpenId Connect. These configure how Users authenticate.

![Figure 1: Configure general authentication behavior and settings for external authentication systems.](../../../images/instance-settings-auth.png)

The General tab's settings affect only the platform's built-in authentication
functionality and don't have anything to do with the integration options on the
other tabs. There are several general authentication settings:

-   Authenticate by email address (default), screen name, or User ID (a
    numerical ID auto-generated in the database---not recommended).

-   Enable/Disable automatic log in. If enabled, a User can check
    a box which will cause the Site to "remember" the login information by
    placing a cookie on the browser. If disabled, Users must always log in
    manually.

-   Enable/Disable forgotten password functionality.

-   Enable/Disable request password reset links.

-   Enable/Disable account creation by strangers. If running an Internet
    site, leave this enabled so visitors can create accounts on your Site.

-   Enable/Disable account creation by those using an email address in the
    domain of the company running the Site (which is set on the General page of
    Instance Settings). This is handy if you're using Liferay to host both
    internal and external web sites. Make sure all internal IDs are created by
    administrators but external Users can register for IDs themselves.

-   Enable/Disable email address verification. If enabled,
    Users receive a verification email with a link back to the virtual instance,
    verifying that the email address they entered is valid.

By default, all settings except for the last are enabled. User authentication by
email address is an important default for the following reasons:

1.  An email address is unique to the User who owns it.

2.  People remember their email addresses. A Users who hasn't logged in for a
    while could forget their screen name.

3.  If email address isn't used to authenticate, a User might change her email
    address then forget to update the email address in her profile. If this
    occurs, no notifications sent by the virtual instance will reach the User.
    Keeping the email address at the forefront of a User's mind when she logs in
    helps ensure the User keeps it current. 

Learn to configure a third party authentication service or set up Single Sign On
(SSO) in the
[security documentation](/discover/deployment/-/knowledge_base/7-1/securing-product).
