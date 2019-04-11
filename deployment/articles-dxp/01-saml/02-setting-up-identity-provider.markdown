---
header-id: setting-up-liferay-as-a-saml-identity-provider
---

# Setting up @product@ as a SAML Identity Provider

An identity provider is a trusted provider that provides single sign-on for
users to access other websites. A service provider is a website that hosts
applications and grants access only to identified users with proper credentials.
Liferay Portal 6.1 EE and later versions support SAML 2.0 integration via the 
[Liferay SAML 2.0 Provider](https://web.liferay.com/marketplace/-/mp/application/15188711)
application. It is provided from Liferay Marketplace and allows @product@ to act
as a SAML 2.0 identity provider or as a service provider.
**Important:** You can set @product@ up as an Identity Provider or as a Service Provider. Each single @product@ instance can serve as an identity provider or as a
service provider, but **not both**. Both configurations are covered in this
article.

## Storing Your Keystore

Your first step is to determine where to store your keystore. You have two
options:

- In the file system
- In the Documents and Media library
 
The file system keystore manager is used by default and the default location is
the `[Liferay Home]/data` directory. To use Documents and Media library storage
for your keystore instead of file system storage, go to *Control Panel* &rarr;
*System Settings* &rarr; *Security* &rarr; *SSO* &rarr; *SAML KeyStoreManager
Implementation Configuration*. Select from the two options: *Filesystem
Keystore Manager* or *Document Library Keystore Manager*.

If you use Document Library storage, you can use any number of 
[back end file stores](/docs/7-2/deploy/-/knowledge_base/deploy/document-repository-configuration).
These are protected not only by the system where the key is stored, but
also by @product@'s permissions system.

## Configuring @product@ as a SAML Identity Provider

1.  To access the SAML Admin interface, click on *Control Panel* &rarr;
    *Configuration* and then on *SAML Admin*.

2.  To begin configuring @product@ to use SAML, select a SAML role for @product@ and
    choose an entity ID.

    [Figure 1: Select a SAML role for Liferay and enter an entity ID.](../../../images-dxp/saml-initial-config.png)

    Select the *Identity Provider* SAML role. Enter your own entity ID. Then
    click *Save*. A new Certificate and Private Key section appears. 

3.  The Certificate and Private Key section lets you create a keystore for SAML.
    Click on *Create Certificate* and enter the following information:
 
    - Your common name (your first and last name)
    - The name of your organization
    - The name of your organizational unit
    - The name of your city or locality
    - The name of your state or province
    - The name of your country
    - The length in days that your keystore will remain valid (how long before
      the keystore expires)
    - The key algorithm (RSA is the default)
    - The key length in bits (2048 is the default)
    - The key password

    Click *Save*.

    When you create the certificate and private key, you also create a keystore
    if one doesn't already exist. As described above, this keystore has two
    storage options: file system storage (the default) and Documents and Media
    storage. By default, the certificate uses the SHA256 algorithm for
    encryption and is fingerprinted and self-signed via MD5 and SHA1. 
 
4.  After you click *Save*, you can click *Replace Certificate* at any time to
    replace the current certificate with a new one if your old one has expired
    or if you want to change the key's password. 

    [Figure 2: The General tab of the SAML Admin portlet displays information about the current certificate and private key and allows administrators to download the certificate or replace the certificate.](../../../images-dxp/saml-keystore-info.png) 

    Three more tabs now appear: 

    **General:** For enabling or disabling a SAML IdP and managing the required
    keystore.

    **Identity Provider:** Contains IdP options, such as whether to enable SSL.
    If SSL has been enabled, then SAML requests are not approved unless they
    are also encrypted.

    **Service Provider Connections:** This tab manages any Service Providers
    connected to this @product@ instance.
 
    See below for more information on the Identity Provider and Service
    Provider Connections tabs.

5.  After you save your certificate and private key information,
    check the *Enabled* box at the top of the General tab and click *Save*.
    You successfully set @product@ up as a SAML Identity Provider!

## Changing the Identity Provider Settings

To configure @product@'s SAML Identity Provider Settings, navigate to the *Identity
Provider* tab of the SAML Admin Control Panel entry.

The *Identity Provider* tab includes these options:

**Sign Metadata?:** Check this box to ensure the metadata XML file that's
produced is signed.

**SSL Required:** Check this box to reject any SAML messages that are *not*
sent over SSL. This affects URLs in the generated metadata. 

**Require Authn Request Signature?:** When this box is checked, each Authn
Request must be signed by the sending Service Provider. In most cases, this
should be enabled. 

**Session Maximum Age:** Specify the maximum duration of the SAML SSO session
in seconds. If this property is not set or is set to `0`, the SSO session has an
unlimited duration. The SSO session maximum duration can be longer than the
portal session maximum duration. If the portal session expires before the SSO
session expires, the user is logged back in to @product@ automatically. SSO
session expiration does not trigger a single logout from all service providers.
You can use the session maximum age, for example, to force users to sign in
again after a certain period of time.

**Session Timeout:** Specify the maximum idle time of the SAML SSO session.
Even if the session maximum age is unlimited, the SSO session expires whenever
the user's idle time reaches the limit set by the session timeout property.

## Checkpoint

Before adding a Service Provider (SP), verify you've completed these tasks:

1. A SAML keystore has been generated. It can be stored in one of two locations:
   the `data` folder or in the Documents and Media library.

2. On the *Identity Provider* tab, the following settings have been set:

    a. **Sign Metadata** has been checked. 
 
    b. **SSL Required** - checked if SSL is active elsewhere. SSL is disabled by 
       default. 

    c. **Authn Request Signature Required:** has been checked. 
 
    d. **Session Maximum Age:** has been set. If set to `0`, then the SSO has an 
       unlimited duration. 

    e. **Session Timeout:** Specify the maximum idle time of the SAML SSO session. 

3. Once the *Enabled* checkbox has been checked, the IdP is live, and you can
   generate the required metadata. This URL is the default location of
   @product@'s metadata XML file:

        [host]:[port]/c/portal/saml/metadata 

If this URL does not display correctly, then the SAML instance has not been
enabled. Use the URL or click *Save* in the browser to generate an actual `XML` file.

Your Identity Provider is now set up. Next, you must register a Service
Provider. 
