# Setting up @product@ as a SAML Identity Provider [](id=setting-up-liferay-as-a-saml-identity-provider)

An identity provider is a trusted provider that provides single sign-on for
users to access other websites. A service provider is a website that hosts
applications and grants access only to identified users with proper credentials.
SAML is maintained by the 
[OASIS Security Services Technical Committee](https://www.oasis-open.org/ committees/security/).
Liferay Portal 6.1 EE and later versions support SAML 2.0 integration via the 
[Liferay SAML 2.0 Provider](https://web.liferay.com/marketplace/-/mp/application/15188711)
application. It is provided from Liferay Marketplace and allows @product@ to act
as a SAML 2.0 identity provider or as a service provider.
**Important:** You can set @product@ up as an Identity Provider or as a Service Provider. Each single @product@ instance can serve as an identity provider or as a
service provider, but **not both**. Both configurations are covered in this
article.

To set @product@ up to act as a SAML Identity Provider, follow the steps below.
Before proceeding, note that in step 3 below, you generate a keystore for SAML.
This keystore has two storage options:

    - In the file system
    - In the Documents and Media library
 
The file system keystore manager is used by default and the default location is
the `[Liferay Home]/data` directory. To use Documents and Media library storage
for your keystore instead of file system storage, use the document library
keystore manager.

To select a keystore manager, to to *Control Panel* &rarr; *System Settings*
&rarr; *SAML KeyStoreManager Implementation Configuration*. There, the options
are *Filesystem Keystore Manager* and *Document Library Keystore Manager*.

The great thing about using Document Library storage is that you can use any number
of [back end file stores](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration).
These are protected not only by the system in which you're storing the key, but
also by @product@'s permissions system.

Here are the steps for setting up @product@ to act as a SAML Identity Provider:

1.  Install the Liferay SAML 2.0 Provider app. To access the SAML Admin
    interface, click on *Control Panel* &rarr; *Configuration* and then on *SAML Admin*.

2.  To begin configuring @product@ to use SAML, select a SAML role for @product@ and
    choose an entity ID.

    ![Figure 1: Select a SAML role for Liferay and enter an entity ID.](../../../../images-dxp/saml-initial-config.png)

    Select the *Identity Provider* SAML role. Enter *liferaysamlidp* if you're
    setting up an example @product@ instance. Alternatively, choose your own
	entity ID. Then click *Save*. A new Certificate and Private Key section
	appears. 

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

    When you enter all the required information, click *Save*.

    When you create the certificate and private key, you also create a keystore
    if one doesn't already exist. As described above, this keystore has two
    storage options: file system storage (the default) and Documents and Media
    storage. 
 
4.  After you click *Save*, you can click *Replace Certificate* at any time to
    replace the current certificate with a new one if your old one has expired
    or if you want to change the key's password. 

    ![Figure 2: The General tab of the SAML Admin portlet displays information about the current certificate and private key and allows administrators to download the certificate or replace the certificate.](../../../../images-dxp/saml-keystore-info.png)

    Three more tabs now appear: 

	- *General*:
	  This tab lets you enable or disable a SAML IdP and lets you manage the
required keystore.
    
    - *Identity Provider*:
      This tab contains other required configurations such as whether to enable 
      SSL. If SSL has been enabled, then SAML requests are not approved unless 
      they are also encrypted.
    
    - *Service Provider Connections*:
      This tab manages any Service Providers connected to this @product@ 
      instance.
	  
	  See below for more information on the Identity Provider and Service
	  Provider Connections tabs.

5.  After you save your certificate and private key information,
    check the *Enabled* box at the top of the General tab and click *Save*.
    You successfully set @product@ up as a SAML Identity Provider!

## Changing the Identity Provider Settings [](id=changing-the-identity-provider-settings)

To configure @product@'s SAML Identity Provider Settings, navigate to the *Identity
Provider* tab of the SAML Admin Control Panel entry.

The *Identity Provider* tab includes these options:

**Sign Metadata?:** When this box is checked, the metadata XML file that's
produced is signed.

**SSL Required:** When this box is checked, any SAML messages that are *not*
sent over SSL are rejected. This affects URLs in the generated metadata. 

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

## Checkpoint [](id=checkpoint)

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

3. Once the *Enabled* checkbox has been checked, the IdP is now live, and you can
   generate the required metadata. This URL is the default location of
   @product@'s metadata XML file:

        [host]:[port]/c/portal/saml/metadata 

If this URL does not display correctly, then the SAML instance has not been
enabled. Use the URL or click *Save* in the browser to generate an actual `XML` file.

## Adding a SAML Service Provider [](id=adding-a-saml-service-provider)

Of course, setting up @product@ as a SAML Identity Provider is only useful if you
can connect to one or more SAML Service Providers. Navigate to the Service
Provider Connections tab of the SAML Admin Control Panel entry and click 
the *Add Service Provider* button to add a SAML Service Provider.

The New Service Provider page includes these options:

**Name:** The name of the Service Provider with which to connect. The name can
be anything; it's purely cosmetic.

**Entity ID:** The Service Provider's entity ID. This value must match the
entity ID declared in the Service Provider metadata.

**Enabled:** When this box is checked, the Service Provider connection is
active.

**Assertion Lifetime:** Defines the number of seconds after which the SAML
assertion issued by the Identity Provider should be considered expired.

**Metadata:** You can either provide a URL to the Service Provider metadata XML
file or you can manually upload the Service Provider metadata XML file. If you
provide a URL, the XML file is automatically retrieved and periodically
polled for updates. The update interval can be configured in
System Settings with the `saml.metadata.refresh.interval` property which
specifies a number of seconds. If fetching the metadata XML file by URL fails,
you can't enable the Service Provider connection. If the Identity Provider
server cannot access the metadata via URL, you can upload the XML file manually.
In this case, the metadata XML file is not updated automatically. 

**Name Identifier Format:** Choose the Name Identifier Format used in the SAML
Response. This should be set according to what the Service Provider expects to
receive. For Liferay Service Providers, any selection other than email address
indicates that the Name Identifier refers to screen name. The formats don't have
any special meaning to Liferay Identity Providers. The NameID value is defined
by the Name Identifier attribute. See the next option.

**Name Identifier Attribute Name:** This specifies which attribute of the
@product@ `User` object to use as the NameID value. Possible values include
`emailAddress`, `screenName` and `uuid`. Additionally, you can prefix the name
with `static:` or `expando:`. If you use the prefix `static`, the value is
whatever comes after `static:`. If you use the prefix `expando`, the value is
whatever custom field is specified after `expando:`. For example, `expando:SSN`
would look up the `User` custom field with the name `SSN`.

**Attributes Enabled:** Include and resolve assertion attributes.

**Attributes Namespace Enabled:** When this box is checked, the attribute names
are namespaced like this:

    urn:liferay:user:expando:
    urn:liferay:user:
    urn:liferay:groups:
    urn:liferay:organizationRole:
    urn:liferay:organization:
    urn:liferay:roles:
    urn:liferay:siteRole:
    urn:liferay:userGroupRole:
    urn:liferay:userGroups:

**Attributes:** Enter a list of attributes to include in the assertion, one per
line. Each line is an expression that gets parsed. Examples: 

    organizations
    organizationRoles
    roles
    siteRoles
    userGroups
    static:[attributeName]=[attributeValue]
    expando:[userCustomFieldName] 

Note that the full namespace depends on the attribute name. Attribute
namespaces can be very useful. Use them when attribute names from different
namespaces might conflict. For example, `expando:user` vs
`urn:liferay:roles:user`.

**Keep Alive URL:** If users are logged into several @product@ SP instances via
a @product@ IdP, their sessions can be kept alive as long as they keep a
browser window open to one of them. Configure this only if the SP is @product@.
The URL is `https://[SP host name]/c/portal/saml/keep_alive`. 

### Checkpoint [](id=checkpoint-0)

Verify your settings are correct when connecting the @product@-based IdP to its
first SP. SPs connect to only one IdP, so if the first one doesn't work, the
rest won't either. 

1. Provide a general name for the SP.

2. The `Entity ID` name must be identical to the one declared in the Service
   Provider metadata.

3. Check the *Enabled* checkbox.

4. Set a value for the *Assertion Lifetime*.

5. Make sure the SP's metadata has been provided either as a URL or an XML file 
   has been uploaded.

6. Make sure *Name Identifier Format* and *Name Identifier Attribute Name* have 
   been set.

7. Make sure *Attributes Namespace Enabled* has been set.

If you don't have a Service Provider to add right now, that's fine. In the next
section, you'll learn how to set @product@ up as a SAML Service Provider. After
you set up another @product@ instance as a Service Provider, come back
to this @product@ installation and add the Service Provider: *Control Panel*
&rarr; *SAML Admin* &rarr; *Service Provider Connections* &rarr; *Add Service
Provider*.
