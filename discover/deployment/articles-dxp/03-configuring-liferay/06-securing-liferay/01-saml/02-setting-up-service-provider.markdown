# Setting up @product@ as a SAML Service Provider [](id=setting-up-liferay-as-a-saml-service-provider)

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

    When you enter all the required information, click *Save*.

4.  After you clicked *Save*, check that you can view information about your
    certificate or download your certificate. If you can, you successfully
    created a keystore. After you create a keystore, additional options
    appear. There are three tabs:

	- *General*:
    This tab enables or disables SAML IdP and manages the required keystore.
    
    - *Service Provider*: This tab manages basic and advanced configurations for
      the SP.
 
    - *Identity Provider Connection*: This tab manages connections to the IdP.
      There can be only one IdP connection.

    Note that these options are different than if you were setting up @product@ as
    an Identity Provider.

5.  Next, you need to configure an Identity Provider connection. Click on the
    *Identity Provider Connection* tab. Enter a name for the Identity Provider,
    enter its entity ID, and enter its metadata URL. If you have already
    followed the previous instructions and configured a separate @product@
    installation as an Identify provider, you'd enter the following information:

    - Name: *Liferay IdP*
    - Entity ID: *liferaysamlidp*
	- Clock Skew
	- Force Authn
    - Metadata URL: http://localhost:8080/c/portal/saml/metadata (test this URL
      first)
	- Name Identifier Format
	- Attribute Mapping
	- Keep Alive URL

    **Important**: The Liferay SAML 2.0 Provider app supports using *either*
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

    Currently, the SAML Provider app does not provide a way to "clear"
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

## Checkpoint [](id=checkpoint-1)

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

## Changing the SAML Service Provider Settings [](id=changing-the-saml-service-provider-settings)

If you'd like to configure @product@'s SAML Service Provider Settings, navigate to
the Service Provider tab of the SAML Admin portlet.

The Service Provider tab includes these options:

**Require Assertion Signature?:** When this box is checked, SAML assertions
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
update interval in System Settings by modifying the
`saml.metadata.refresh.interval` property which specifies a number of seconds.
If fetching the metadata XML file by URL fails, you can't enable the Identity
Provider connection. If the metadata is inaccessible via URL, you can upload the
XML file manually. In this case, the metadata XML file is not updated
automatically. 

**Name Identifier Format:** Choose the Name Identifier Format used in the SAML
Response. This should be set according to what the Service Provider expects to
receive. For Liferay Service Providers, any selection other than email address
indicates that the Name Identifier refers to screen name. The formats don't have
any special meaning to Liferay Identity Providers. The NameID value is defined
by the Name Identifier attribute.

**Attribute Mapping:** The attribute mapping is done from the attribute name or
friendly name in the SAML Response to the @product@ attribute name. For example,
if you want to map a response attribute named `mail` to the @product@ attribute
`emailAddress`, you'd enter the following mapping:

    mail=emailAddress

Available @product@ attributes are: `emailAddress`, `screenName`, `firstName`,
`lastName`, `modifiedDate`, and `uuid`.

**Keep Alive URL:** If users are logged into several @product@ IdP instances via
a @product@ SP, their sessions can be kept alive as long as they keep a browser
window open to one of them. Configure this only if the IdP is @product@. The URL
is `https://[host name]/c/portal/saml/keep_alive`. On the @product@ IdP,
configure this URL the same way, but point back to this SP. 

Save your changes when you are finished configuring the @product@ instance as a
service provider. There is no need to restart the server and the changes will be
applied immediately.

The previous two sections explained how to use the SAML 2.0 Provider
app's Control Panel interface to configure @product@ as an Identity
Provider or as a Service Provider. Such configurations should only be made
through the SAML Control Panel interface and not via properties. Some features
of the Liferay SAML 2.0 Provider app are not available as properties.


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

If you're running a multi-node cluster behind a load balancer, follow these
steps to enable all the nodes as SPs:

1.  Configure each node of your [@product@
    cluster](/discover/deployment/-/knowledge_base/7-0/liferay-clustering) as a SAML
    service provider using the instructions of the previous section.

2.  Copy the keystore file (`[Liferay Home]/data/keystore.jks`, by default) from
    the first @product@ node to the remaining @product@ nodes. This file is the
    Java keystore that's created by the SAML Provider app. The keystore
    contains the valid or self-signed certificate managed by the SAML Provider
    app.

    Note: The keystore file and its default location can vary according to the
    keystore manager defined by the `saml.keystore.manager.impl` property.
    Here's the relevant section of the Liferay SAML 2.0 Provider app's
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
