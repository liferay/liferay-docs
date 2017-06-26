# Setting Up SAML [](id=saml)

SAML is an XML-based open standard data format for exchanging authentication and
authorization data between parties known as an identity provider and a service
provider. For more fundamental information on SAML and @product@, refer to the
[SAML introduction](/discover/deployment/-/knowledge_base/7-0/saml). 

An identity provider is a trusted provider that provides single sign-on for
users to access other websites. A service provider is a website that hosts
applications and grants access only to identified users with proper credentials.
SAML is maintained by the 
[OASIS Security Services Technical Committee](https://www.oasis-open.org/ committees/security/).
for more information. Liferay Portal 6.1 EE and later 
versions support SAML 2.0 integration via the 
[Liferay SAML 2.0 
Provider](https://web.liferay.com/marketplace/-/mp/application/15188711)
application. It is provided from Liferay Marketplace and allows @product@ to act
as a SAML 2.0 identity provider or as a service provider.

**Important:** You can set @product@ up as an Identity Provider or as a Service
Provider. Each single @product@ instance can serve as an identity provider or as a
service provider, but **not both**. Both configurations are covered in this
article.

## Setting up @product@ as a SAML Identity Provider [](id=setting-up-liferay-as-a-saml-identity-provider)

To set @product@ up to act as a SAML Identity Provider, follow these steps:

1.  Install the Liferay SAML 2.0 Provider app. To access the SAML Admin
    interface, click on *Control Panel* &rarr; *Configuration* and then on *SAML Admin*.

2.  To begin configuring @product@ to use SAML, select a SAML role for @product@ and
    choose an entity ID.

    ![Figure 1: Select a SAML role for Liferay and enter an entity ID.](../../../../images-dxp/saml-initial-config.png)

    Select the *Identity Provider* SAML role. Enter *liferaysamlidp* if you're
    setting up an example @product@ instance. Alternatively, choose your own
    entity ID.  Then click *Save*. A new Certificate and Private Key section
    appears. 

3.  The Certificate and Private Key section lets you create a keystore for SAML.
    Enter the following information:
    
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

    If you're making a test configuration, use the password *liferay*. When
    you enter all the required information, click *Save*.

    Note that the SAML keystore is created by the SAML plugin's keystore
    manager. This keystore has two storage options: 

    - In the file system
    - In the Documents and Media library
 
    File system storage is used by default: 

        saml.keystore.manager.impl=com.liferay.saml.credential.FileSystemKeyStoreManagerImpl

    The default location is the `[Liferay Home]/data` directory. 
    
    To use the Documents and Media library, use this property: 

        saml.keystore.manager.impl=com.liferay.saml.credential.DLKeystoreManagerImpl

    The great thing about the Docs and Media storage is that you can use any
    number of [back end file stores](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration). 
    These are protected not only by the system in which you're storing the key,
    but also by @product@'s permissions system.
 
4.  After you click *Save*, you can click *Replace Certificate* at any time to
    replace the current certificate with a new one if your old one has expired
    or if you want to change the key's password. 

    ![Figure 2: The General tab of the SAML Admin portlet displays information about the current certificate and private key and allows administrators to download the certificate or replace the certificate.](../../../../images-dxp/saml-keystore-info.png)

    Three more tabs now appear: 

    - *General* 
      This tab enables or disables SAML IdP and manages the required keystore.  
    
    - *Identity Provider*     
      This tab contains other required configurations such as whether to enable 
      SSL. If SSL has been enabled, then SAML requests are not approved unless 
      they are also encrypted.
    
    - *Service Provider Connections*     
      This tab manages any Service Providers connected to this @product@ 
      instance. See below for more information.

5.  After you save your certificate and private key information,
    check the *Enabled* box at the top of the General tab and click *Save*.
    You successfully set @product@ up as a SAML Identity Provider!


### Changing the Identity Provider Settings [](id=changing-the-identity-provider-settings)

To configure @product@'s SAML Identity Provider Settings, navigate to the *Identity
Provider* tab of the SAML Admin Control Panel entry.

The *Identity Provider* tab includes these options:

**Sign Metadata:** When this box is checked, the metadata XML file that's
produced is signed.

**SSL Required:** When this box is checked, any SAML messages that are *not*
sent over SSL are rejected. This does not affect how URLs are generated.

**Authn Request Signature Required:** When this box is checked, each Authn
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

**Service Provider Defaults:** The options in this section set defaults that
are used when adding new service provider connections.

#### Checkpoint [](id=checkpoint)

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

### Adding a SAML Service Provider [](id=adding-a-saml-service-provider)

Of course, setting up @product@ as a SAML Identity Provider is only useful if you
can connect to one or more SAML Service Providers. Navigate to the Service
Provider Connections tab of the SAML Admin Control Panel entry and click 
the *Add Service Provider* button to add a SAML Service Provider.

The New Service Provider page includes these options:

**Name:** The name of the Service Provider with which to connect.

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
`portlet.properties` with the `saml.metadata.refresh.interval` property which
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

Note that the full namespace depends on the attribute name. The namespaces are
useful; for example, when you have an Expando attribute that might otherwise
create an attribute with the same name as some other attribute.

#### Checkpoint [](id=checkpoint-0)

If a @product@ instance is the SAML IdP, it can connect to multiple SPs.
The converse, however, is not true: SPs connect to only one IdP. Verify that
your settings are correct for the first SP before proceeding to add more SPs.

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

## Setting up @product@ as a SAML Service Provider [](id=setting-up-liferay-as-a-saml-service-provider)

Many of these steps are similar to configuring @product@ as a SAML Identity
Provider. As a reminder, a single @product@ installation can be configured as a
SAML Identify Provider *or* as a SAML Service Provider but not as both. If you
already set up one @product@ installation as a SAML Identity Provider, use a
*different* @product@ installation as a SAML Service Provider.

+$$$

**Note:** If you're using a third party IdP with @product@ as the SP, all
messages coming from the IdP must be signed. If they're not, an error message
appears and communication between the IdP and @product@ fails.

$$$

1.  Install the Liferay SAML 2.0 Provider app. To confirm that the app was
    successfully deployed, look for the *SAML Admin* entry in the Configuration
    section of the Control Panel.

2.  To begin configuring @product@ to use SAML, you must select a SAML role for
    @product@ and you need to choose an entity ID. Select the *Service
    Provider* SAML role. Enter *liferaysamlsp* if you're setting up an example
    @product@ installation. Alternatively, choose your own entity ID. Then
    click *Save* and a new section entitled Certificate and Private Key appears.

3.  The Certificate and Private Key is for creating a keystore for SAML. Enter
    the following information:

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

    If you're configuring an example setup, use the password *liferay*. When
    you enter all the required information, click *Save*.

4.  After you clicked *Save*, check that you can view information about your
    certificate or download your certificate. If you can, you successfully
    created a keystore. After you create a keystore, additional options
    appear. There are three tabs:

    - General 
    This tab enables or disables SAML IdP and manages the required keystore.
    
    - Service Provider (*not Identity Provider!*)    
    This tab manages basic and advanced configurations for the SP.
 
    - Identity Provider Connection (*not Service Provider Connections!*) 
    This tab manages connections to the IdP. There can be only one IdP connection.

    Note that these options are different than if you were setting up @product@ as
    an Identity Provider.

5.  Next, you need to configure an Identity Provider connection. Click on the
    *Identity Provider Connection* tab. Enter a name for the Identity Provider,
    enter its entity ID, and enter its metadata URL. If you have already
    followed the previous instructions and configured a separate @product@
    installation as an Identify provider, you'd enter the following information:

    - Name: *Liferay IdP*
    - Entity ID: *liferaysamlidp*
    - Metadata URL: http://localhost:8080/c/portal/saml/metadata (test this URL
      first)

    **Important**: The Liferay SAML 2.0 Provider plugin supports using *either*
    a URL to a SAML IdP metadata file *or* an actual (uploaded) SAML metadata
    XML file. The value entered in the *Metadata URL* field will only be
    persisted to the database when there is one entered metadata URL and there
    is no specified metadata XML file. Otherwise, @product@ keeps the original
    metadata URL in the database. This behavior ensures that once a metadata URL
    has been specified, there will always be a metadata URL saved in the
    database. This way, if a portal administrator forgets the previously entered
    metadata URL or its format, he or she can simply look at the displayed
    metadata URL and either choose to modify the displayed metadata URL or to
    overwrite the previously saved metadata URL by specifying a metadata XML
    file.

    Currently, the SAML Provider plugin does not provide a way to "clear"
    the SAML IdP metadata URL or metadata XML file fields using the Control
    Panel UI. If you really need to clear these fields, it's possible (but not
    recommended) to delete the contents of the SAML IdP metadata URL and
    metadata XML file columns of the `SamlSpIdpConnection` table of @product@'s
    database.

6.  Finally, after you save your certificate and private key information and
    configure an Identity Provider connection, check the *Enabled* box at the top
    of the General tab and click *Save*. @product@ is now a SAML Service Provider!

Note that the SAML Service Provider session is tied to the normal session on
the application server. Session expiration on the application server terminates
the session on the Service Provider but does not initiate single logout. 

#### Checkpoint [](id=checkpoint-1)

1. A SAML keystore has been generated.

2. Verify the connection to the IdP. 

    a.  *Name*: generic name for the IdP. 
    
    b.  *Entity ID*: the same name of the IdP. If the IdP is another @product@
        instance, then it is the same name as the above example. 

    c. *Metadata URL*: The IdP's metadata as a URL or as an XML file.

3. On the *General* tab, the *Enabled* checkbox has been checked.

4. Once *Enabled* checkbox has been checked, the service provider's metadata
   becomes available:

        [host]:[port]/c/portal/saml/metadata

### Changing the SAML Service Provider Settings [](id=changing-the-saml-service-provider-settings)

If you'd like to configure @product@'s SAML Service Provider Settings, navigate to
the Service Provider tab of the SAML Admin portlet.

The Service Provider tab includes these options:

**Assertion Signature Required:** When this box is checked, SAML assertions
must be individually signed in addition to the entire SAML message.

**Clock Skew:** Clock skew is a tolerance in milliseconds used by the Service
Provider for verifying expiration of messages and assertions. This can be used
to mitigate time differences between the clocks of the Identity Provider and
the Service Provider. This usually only matters when assertions have been made
to expire very quickly.

**LDAP Import Enabled:** When this box is checked, user information is imported
from the configured LDAP connection based on the resolved NameID. LDAP
connections can be configured from Portal Settings.

**Sign Authn Requests:** When this box is checked, the AuthnRequest is signed
even if the Identity Provider metadata indicates that it's not required.

**Sign Metadata:** When this box is checked, the metadata XML file is signed.

**SSL Required:** When this box is checked, any SAML messages that are not sent
over HTTPS are rejected. This does not affect how URLs are generated.

The Identity Provider Connection page includes these options:

**Name:** The name of the Identity Provider with which to connect.

**Entity ID:** The Identity Provider's entity ID. This value must match the
entity ID declared in the Identity Provider metadata.

**Clock Skew:** Clock skew is a tolerance in milliseconds used by the Service
Provider for verifying expiration of messages and assertions. This can be used
to mitigate time differences between the clocks of the Identity Provider and
the Service Provider. This usually only matters when assertions have been made
to expire very quickly.

**Force Authn:** When this box is checked, the Service Provider asks the
Identity Provider to re-authenticate the user before verifying the user.

**Metadata:** You can either provide a URL to the Identity Provider metadata
XML file or you can manually upload it. If you provide a URL, the XML file is
automatically retrieved and periodically polled for updates. You can change the
update interval in `portlet.properties` by modifying the
`saml.metadata.refresh.interval` property which specifies a number of seconds.
If fetching the metadata XML file by URL fails, you can't enable the Identity
Provider connection. If the metadata is inaccessible via URL, you can upload the
XML file manually. In this case, the metadata XML file is not 
updated automatically. 

**Attribute Mapping:** The attribute mapping is done from the attribute name or
friendly name in the SAML Response to the @product@ attribute name. For example,
if you want to map a response attribute named `mail` to the @product@ attribute
`emailAddress`, you'd enter the following mapping:

    mail=emailAddress

Available @product@ attributes are: `emailAddress`, `screenName`, `firstName`,
`lastName`, `modifiedDate`, and `uuid`.

Save your changes when you are finished configuring the @product@ instance as a
service provider. There is no need to restart the server and the changes will be
applied immediately.

The previous two sections explained how to use the SAML 2.0 Provider
EE plugin's Control Panel interface to configure @product@ as an Identity
Provider or as a Service Provider. Such configurations should only be made
through the SAML Control Panel interface and not via properties. Some features
of the Liferay SAML 2.0 Provider plugin are not available as properties.


+$$$

**Limitation:** The Liferay SAML app can only be used with a single virtual
host. Technically, this means that in the SAML metadata for @product@, only one
binding can be added in this form:

    <md:EntityDescriptor>
    ...
    <md:SPSSODescriptor>
    ...
    <md:AssertionConsumerService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="https://portal.domain.com/c/portal/saml/acs" index="1" isDefault="true" />
    ...
    </md:SPSSODescriptor>
    </md:EntityDescriptor>

$$$


## Setting Up @product@ as a SAML Service Provider in a Clustered Environment [](id=setting-up-liferay-as-a-saml-service-provider-in-a-clustered-environment)

If you want to use the Liferay SAML 2.0 Provider app as an SSO solution for a
clustered @product@ environment, follow the steps in this section. Before
proceeding, make sure that the following assumptions apply to your scenario.

Suppose that your clustered @product@ environment consists of multiple @product@
nodes that sit behind a load balancer. Your @product@ nodes could be @product@
Tomcat bundles, for example. Your load balancer could be software like Apache
web server or hardware like F5 BIG-IP. Suppose further that you want want the
nodes of your @product@ cluster to serve as SAML Service Providers. And suppose
that you have a third-party participating as the SAML Identity Provider. (For
testing purposes, you could create a separate @product@ installation to serve as
the SAML IdP.)

If your situation fits the scenario described above, follow these steps:

1.  Configure each node of your [@product@
    cluster](/discover/deployment/-/knowledge_base/7-0/liferay-clustering) as a SAML
    service provider using the instructions of the previous section.

2.  Copy the keystore file (`[Liferay Home]/data/keystore.jks`, by default) from
    the first @product@ node to the remaining @product@ nodes. This file is the
    Java keystore that's created by the SAML Provider plugin. The keystore
    contains the valid or self-signed certificate managed by the SAML Provider
    plugin.

    Note: The keystore file and its default location can vary according to the
    keystore manager defined by the `saml.keystore.manager.impl` property.
    Here's the relevant section of the Liferay SAML 2.0 Provider plugin's
    `portlet.properties` file:

        #
        # Set the name of a class that implements
        # com.liferay.saml.credential.KeyStoreManager. This class is used to load and
        # save the keystore.
        #
        #saml.keystore.manager.impl=com.liferay.saml.credential.DLKeyStoreManagerImpl
        saml.keystore.manager.impl=com.liferay.saml.credential.FileSystemKeyStoreManagerImpl

3.  Verify that the service provider metadata has been generated to be used
    either as a URL or an XML file. The metadata is the same for all nodes
    because of the same database back-end. The IdP's request goes through the
    load balancer.

4.  At this point, all the @product@ nodes have the same SAML SP configuration
    and each of them can respond to web requests and handle the SAML protocol.
    To test your SSO solution, sign into @product@ via your load balancer,
    navigate to a few pages of a few different sites, and then log out.

Now you know how to configure @product@ either as a SAML identity provider
or a service provider. You also know how to configure SAML in a
clustered environment.

