# SAML [](id=saml)

(Coming soon)

<!-- This EE-only article needs to be tested and updated for 7.0. -->

![EE-only](../../images/ee-feature-web.png)

SAML is an XML-based open standard data format for exchanging authentication and
authorization data between parties known as an identity provider and a service
provider. An identity provider is a trusted provider that enables users to use
single sign-on to access other websites. A service provider is a website that
hosts applications and grants access only to identified users with proper
credentials. SAML is maintained by the OASIS Security Services Technical
Committee. See
[https://www.oasis-open.org/committees/security/](https://www.oasis-open.org/committees/security/)
for more information. Liferay Portal 6.1 EE and later versions support SAML 2.0
integration via the SAML 2.0 Provider EE plugin. This plugin is provided as an
app from Liferay Marketplace that allows Liferay to act as a SAML 2.0 Identity
provider or as a service provider. First, we'll look at how to set Liferay up as
an Identity Provider and then we'll look at how to set it up as a Service
Provider. The SAML 2.0 Provider EE plugin supports any Identity provider that
supports the SAML 2.0 standard.

## Setting up Liferay as a SAML Identity Provider [](id=setting-up-liferay-as-a-saml-identity-provider)

To set Liferay up to act as a SAML Identity Provider, follow these steps:

1. Install the SAML 2.0 Provider EE app, either via the Control Panel's
   Marketplace interface or manually. To confirm that the plugin was
   successfully deployed, look for the *SAML Admin* entry in the Control Panel.

    ![Figure 1: The SAML Admin entry appears as a sub-section of the Configuration section of the Control Panel.](../../images/saml-admin.png)

    To access the SAML Admin interface, click on *Admin* &rarr; *Control Panel*
    and then on *SAML Admin*.

2. To begin configuring Liferay to use SAML, you need to select a SAML role
   for Liferay and you need to choose an entity ID.

    ![Figure 2: Select a SAML role for Liferay and enter an entity ID.](../../images/saml-initial-config.png)

    The SAML role can be set to Identity Provider or Service Provider.
    Select the *Identity Provider* SAML role. A single Liferay instance can
    serve as an identity provider or as a service provider, but not both.
    Enter *liferaysamlidp* if you're setting up an example Liferay instance.
    Alternatively, choose your own entity ID. Then click *Save* and look for a
    new section to appear, entitled Certificate and Private Key.

3. The Certificate and Private Key section allows you to enter information to
   create a keystore for SAML. Enter the following information:

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
    you've entered all the required information, click *Save*.

    Note that the SAML keystore is created by the SAML plugin's keystore
    manager. By default, the following property is set in the SAML plugin:

        saml.keystore.manager.impl=com.liferay.saml.credential.FileSystemKeyStoreManagerImpl

    This portlet property means that the keystore will be created in the
    filesystem. The default location is the `[Liferay Home]/data` directory.
    However, this portlet property can be changed:

        saml.keystore.manager.impl=com.liferay.saml.credential.DLKeystoreManagerImpl

    With this setting, the keystore will be created in Liferay's document
    library.  Liferay's document library could be stored on the file system, in
    a database, in the cloud, etc. By default, documents are stored on the file
    system, as per the following portal property:

        dl.store.impl=com.liferay.portlet.documentlibrary.store.FileSystemStore

    See the
    [http://docs.liferay.com](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Document%20Library%20Portlet)
    section on Liferay's `dl.store.impl` portal property for details.

4. After you've clicked *Save*, information about the keystore you created is
   displayed. You can click *Replace Certificate* at any time to replace the
   current certificate with an updated one. You can do this, for example, if
   your keystore has expired or is about to expire. You can also do this if
   you'd like to change any of the information saved in your keystore, including
   the password.

    ![Figure 3: The General tab of the SAML Admin portlet displays information about the current certificate and private key and allows administrators to download the certificate or replace the certificate.](../../images/saml-keystore-info.png)

    Also, notice that additional options appear in the SAML Admin Control Panel
    portlet. There are three tabs:

    - General
    - Identity Provider
    - Service Provider Connections

    Leave the SAML role and Entity role as you configured them in step 3. In the
    Certificate and Private Key section, enter the same information that you
    entered when you generated your keystore, then click *Save*. After saving
    your certificate and private key information, you can view information about
    your certificate or download your certificate.

![Figure 17.2: After saving your certificate and private key information, you can view information about your certificate or download your certificate.](../../images/saml-admin-certificate-saved.png)

5. Finally, after you've saved your certificate and private key information,
   check the *Enabled* box at the top of the General tab and click *Save*.
   Great! You've successfully set Liferay up as a SAML Identity Provider!

To configure Liferay's SAML Identity Provider Settings, navigate to the Identity
Provider tab of the SAML Admin Control Panel entry.

The Identity Provider tab includes these options:

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
session expires, the user is automatically logged back in to the portal.  SSO
session expiration does not trigger a single logout from all service providers.
You can use the session maximum age, for example, to force users to sign in
again after a certain period of time.

**Session Timeout:** Specify the maximum idle time of the SAML SSO session.
Even if the session maximum age is unlimited, the SSO session expires whenever
the user's idle time reaches the limit set by the session timeout property.

**Service Provider Defaults:** The options in this section set defaults that
are used when adding new service provider connections.

Of course, setting up Liferay as a SAML Identity Provider is only useful if you
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
you won't be able to enable the Service Provider connection. If the Identity
Provider server cannot access the metadata via URL, you can upload the XML file
manually. In this case, the metadata XML file is not automatically updated.

**Name Identifier Format:** This selector lets you choose the Name Identifier
Format used in the SAML Response. This should be set according to what the
Service Provider expects to receive. For Liferay Service Providers, any
selection other than email address indicates that the Name Identifier refers to
screen name. The formats don't have any special meaning to Liferay Identity
Providers. The NameID value is defined by the Name Identifier attribute. (See
the next option.)

**Name Identifier Attribute Name:** This specifies which attribute of the
Liferay `User` object to use as the NameID value. Possible values include
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
useful, for example, when you have an Expando attribute that might otherwise
create an attribute with the same name as some other attribute.

If you don't have a Service Provider to add right now, that's fine. In the next
section, you'll learn how to set Liferay up as a SAML Service Provider. After
you've set up another Liferay instance as a Service Provider, you can come back
to this Liferay instance and add the Service Provider: *Control Panel* &rarr;
*SAML Admin* &rarr; *Service Provider Connections* &rarr; *Add Service
Provider*.

## Setting up Liferay as a SAML Service Provider [](id=setting-up-liferay-as-a-saml-service-provider)

Many of these steps are similar to the ones for setting Liferay up to act
as a SAML Identity Provider. A single Liferay instance can be configured as a
SAML Identify Provider *or* as a SAML Service Provider but not as both. If
you've already set up one Liferay instance as a SAML Identity Provider, use a
*different* Liferay instance as a SAML Service Provider

1. Install the SAML 2.0 Provider EE app, either via the Control Panel's
   Marketplace interface or manually. To confirm that the plugin was
   successfully deployed, look for the *SAML Admin* entry in the Configuration
   section of the Control Panel.

2. To begin configuring the Liferay to use SAML, you must select a SAML role
   for Liferay and you need to choose an entity ID. The SAML role can be set to
   Identity Provider or Service Provider. Select the *Service Provider*
   SAML role. Enter *liferaysamlsp* if you're setting up an example Liferay
   instance. Alternatively, choose your own entity ID. Then click *Save* and a
   new section entitled Certificate and Private Key appears.

3. The Certificate and Private Key section allows you to enter information that
   will be used to create a keystore for SAML. Enter the following information:

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
    you've entered all the required information, click *Save*.

4. After you've clicked *Save*, check that you can view information about your
   certificate or download your certificate. If you can, you've successfully
   created a keystore. After you've created a keystore, additional options
   appear in the SAML Admin Control Panel portlet. There are three tabs:

    - General
    - Service Provider (*not Identity Provider!*)
    - Identity Provider Connection (*not Service Provider Connections!*)

    Note that these options are different than if you were setting up Liferay as
    an Identity Provider.

5. Next, you need to configure an Identity Provider connection. Click on the
   *Identity Provider Connection* tab. Enter a name for the Identity Provider,
   enter its entity ID, and enter its metadata URL. If you have already
   followed the previous instructions and configured a separate Liferay instance
   as an Identify provider, you'd enter the following information:

    - Name: *Liferay IdP*
    - Entity ID: *liferaysamlidp*
    - Metadata URL: http://localhost:8080/c/portal/saml/metadata (test this URL
      first)

    **Important**: The SAML 2.0 Provider EE plugin supports using *either* a URL
    to a SAML IdP metadata file *or* an actual (uploaded) SAML metadata XML
    file. The value entered in the *Metadata URL* field will only be persisted
    to the database when there is one entered metadata URL and there is no
    specified metadata XML file. Otherwise, the portal keeps the original
    metadata URL in the database. This behavior ensures that once a metadata URL
    has been specified, there will always be a metadata URL saved in the
    database. This way, if a portal administrator forgets the previously entered
    metadata URL or its format, he or she can simply look at the displayed
    metadata URL and either choose to modify the displayed metadata URL or to
    overwrite the previously saved metadata URL by specifying a metadata XML
    file.

    Currently, the SAML 2.0 Provider EE plugin does not provide a way to "clear"
    the SAML IdP metadata URL or metadata XML file fields using the Control
    Panel UI. If you really need to clear these fields, it's possible (but not
    recommended) to delete the contents of the SAML IdP metadata URL and
    metadata XML file columns of the `SamlSpIdpConnection` table of Liferay's
    database. (A way to "clear" the SAML IdP metadata URL or metadata XML file
    fields using the Control Panel UI has been requested as a feature. You can
    track the issue progress here:
    [https://issues.liferay.com/browse/LPS-59199](https://issues.liferay.com/browse/LPS-59199)).

6. Finally, after you've saved your certificate and private key information and
   configured an Identity Provider connection, check the *Enabled* box at the
   top of the General tab and click *Save*. Great! You've successfully set
   Liferay up as a SAML Service Provider!

Note that the SAML Service Provider session is tied to the normal session on
the application server. Session expiration on the application server terminates
the session on the Service Provider but does not initiate single logout. If
you'd like to configure Liferay's SAML Service Provider Settings, navigate to
the Service Provider tab of the SAML Admin Control Panel portlet.

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
connections can be configured from Instance Settings.

**Sign Authn Requests:** When this box is checked, the AuthnRequest is signed
even if the Identity Provider metadata indicates that it's not required.

**Sign Metadata:** When this box is checked, the metadata XML file is signed.

**SSL Required:** When this box is checked, any SAML messages that are not sent
over HTTPS are rejected. This does not affect how URLs are generated.

The Identity Provider page includes these options:

**Name:** The name of the Identity Provider with which to connect.

**Entity ID:** The entity ID of the Identity Provider. This value must match
the entity ID declared in the Identity Provider metadata.

**Clock Skew:** Clock skew is a tolerance in milliseconds used by the Service
Provider for verifying expiration of messages and assertions. This can be used
to mitigate time differences between the clocks of the Identity Provider and
the Service Provider. This usually only matters when assertions have been made
to expire very quickly.

**Force Authn:** When this box is checked, the Service Provider asks the
Identity Provider to re-authenticate the user before verifying the user.

**Metadata:** You can either provide a URL to the Identity Provider metadata
XML file or you can manually upload the Identity Provider metadata XML file. If
you provide a URL, the XML file is automatically retrieved and periodically
polled for updates. The update interval can be configured in
`portlet.properties` with the `saml.metadata.refresh.interval` property which
specifies a number of seconds. If fetching the metadata XML file by URL fails,
you won't be able to enable the Identity Provider connection. If the metadata
is inaccessible via URL, you can upload the XML file manually. In this case,
the metadata XML file is not automatically updated.

**Attribute Mapping:** The attribute mapping is done from the attribute name or
friendly name in the SAML Response to the Liferay attribute name. For example,
if you want to map a response attribute named `mail` to the Liferay attribute
`emailAddress`, you'd enter the following mapping:

    mail=emailAddress

Available Liferay attributes are: `emailAddress`, `screenName`, `firstName`,
`lastName`, `modifiedDate`, and `uuid`.

+$$$

**Note:** The previous two sections explained how to use the SAML 2.0 Provider
EE plugin's Control Panel interface to configure Liferay as an Identity
Provider or as a Service Provider. Such configurations should only be made
through the SAML Control Panel interface and not via properties. Some features
of the SAML 2.0 Provider EE plugin are not available as properties.

$$$

Suppose that you have two Liferay instances running on ports 8080 and 9080 of
your host. Suppose further that you've configured the Liferay running on port
8080 as a SAML Identity Provider and the Liferay running on port 9080 as a SAML
Service Provider, following the instructions above. If your Identity Provider
and Service Provider have been correctly configured, navigating to
[http://localhost:8080/c/portal/saml/sso?entityId=liferaysamlsp](http://localhost:8080/c/portal/saml/sso?entityId=liferaysamlsp)
initiates the SAML Identity Provider based login process. To initiate the SAML
Service Provider based login process, just navigate to the Liferay running on
port 9080 and click *Sign In*, navigate to
[http://localhost:9080/c/portal/login](http://localhost:9080/c/portal/login), or
try to access a protected resource URL such as a Control Panel URL.

+$$$

**Limitation:** The Liferay SAML plugin can only be used with a single virtual
host. Technically, this means that in the SAML metadata for Liferay, only one
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

## Important SAML URLs [](id=important-saml-urls)

For reference, here are a few important SAML URLs.

This URL is the default location of Liferay's metadata XML file:

    [host]:[port]/c/portal/saml/metadata

Note that when configuring SAML for Liferay, no importing of SAML certificates
is required. Liferay reads certificates from the SAML metadata XML file. If you
want a third-party application like Salesforce to read a Liferay SAML
certificate, you can export the Liferay certificate from the keystore. The
default keystore file is `[Liferay Home]/data/keystore.jks`. The exported
certificate can be imported by a third-party application like Salesforce.

With the URL below, you can trigger an Identity Provider initiated SSO. The
`entityId` parameter is the entity ID of the Service Provider you want to log
in to. The `RelayState` parameter is optional. It specifies a landing page on
the Service Provider.

    [host]:[port]/c/portal/saml/sso?entityId=[SP entity id]&RelayState=[landing page on SP]

## Setting Up Liferay as a SAML Service Provider in a Clustered Environment [](id=setting-up-liferay-as-a-saml-service-provider-in-a-clustered-environment)

If you want to use Liferay's SAML 2.0 Provider EE plugin as a SSO solution for a
clustered Liferay environment, follow the steps in this section. Before
proceeding, make sure that the following assumptions apply to your scenario.

Suppose that your clustered Liferay environment consists of multiple Liferay
nodes that sit behind a load balancer. Your Liferay nodes could be Liferay
Tomcat bundles, for example. Your load balancer could be software like Apache
web server or hardware like F5 BIG-IP. Suppose further that you want want the
nodes of your Liferay cluster to serve as SAML Service Providers. And suppose
that you have a third-party participating as the SAML Identity Provider. (For
testing purposes, you could create a separate Liferay instance to serve as the
SAML IdP.)

If your situation fits the scenario described above, follow these steps:

1. Configure one node of your Liferay cluster as a SAML SP using the
   instructions of the previous section.

2. Ensure that this Liferay node is using the fully qualified name of the load
   balancer (e.g., `FQN.LB.HOST`) as the value of the `web.server.host` property
   in the node's `portal-ext.properties` file.

        #
        # Set the hostname that will be used when the portlet generates URLs.
        # Leaving this blank will mean the host is derived from the servlet
        # container.
        #
        web.server.host=FQN.LB.HOST


3. Repeat steps 1 and 2 for each other Liferay node.

4. Copy the keystore file (`[Liferay Home]/data/keystore.jks`, by default) from
   the first Liferay node to each other Liferay node. This file is the Java
   keystore that's created by the SAML 2.0 Provider EE plugin. The keystore
   contains the valid or self-signed certificate managed by the SAML 2.0 EE
   Provider plugin.

    Note: The keystore file and its default location can vary according to the
    keystore manager defined by the `saml.keystore.manager.impl` property.
    Here's the relevant section of the SAML 2.0 Provider EE plugin's
    `portlet.properties` file:

        #
        # Set the name of a class that implements
        # com.liferay.saml.credential.KeyStoreManager. This class is used to load and
        # save the keystore.
        #
        #saml.keystore.manager.impl=com.liferay.saml.credential.DLKeyStoreManagerImpl
        saml.keystore.manager.impl=com.liferay.saml.credential.FileSystemKeyStoreManagerImpl

5. At this point, all of the Liferay nodes have the same SAML SP configuration
   and each of them can respond to web requests and handle the SAML protocol. To
   test your SSO solution, sign into Liferay via your load balancer, navigate to
   a few pages of a few different sites, and then log out.

Great! Now you know how to configure Liferay either as a SAML identity provider
or a service provider. You also know how to configure the Liferay SAML in a
clustered environment.

-->
