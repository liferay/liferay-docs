---
header-id: setting-up-liferay-as-a-saml-service-provider
---

# Setting up @product@ as a SAML Service Provider

Many of these steps are similar to configuring @product@ as a SAML Identity
Provider. As a reminder, a single @product@ installation can be configured as a
SAML Identify Provider *or* as a SAML Service Provider but not as both. If your
@product@ installation is already a SAML Identity Provider, use a *different*
@product@ installation as a SAML Service Provider.

| **Note:** If you have a third party IdP with @product@ as the SP, all
| messages coming from the IdP must be signed. If they're not, an error message
| appears and communication between the IdP and @product@ fails.

1.  Install the Liferay SAML 2.0 Provider app. To confirm successful deployment,
    look for the *SAML Admin* entry in the Configuration section of the Control
    Panel.

2.  To begin configuring @product@ to use SAML, you must select a SAML role for
    @product@ and choose an entity ID. Select the *Service Provider* SAML role.
    Choose your own entity ID. Then click *Save* and a new section entitled
    Certificate and Private Key appears.

3.  The Certificate and Private Key section is for creating a keystore for SAML.
    Click *Create Certificate* and enter the following information:

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

    Remember that the keystore has two storage options: file system storage (the
    default) and Documents and Media storage.By default, the certificate uses
    the SHA256 algorithm for encryption and is fingerprinted and self-signed via
    MD5 and SHA1. When you enter all the required information, click *Save*.

4.  After you clicked *Save*, check that you can view information about your
    certificate or download your certificate. If you can, you successfully
    created a keystore. After you create a keystore, additional options
    appear. There are three tabs:

    **General**: Enables or disables SAML SP and manages the required
    keystore.
 
    **Service Provider**: This tab manages basic and advanced configurations for
    the SP.
 
    **Identity Provider Connection**: This tab manages connections to the IdP.
    There can be multiple IdP connections.

5.  You can also generate an encryption certificate. This is a separate key for
    encrypting assertions. If you want assertions encrypted, you must generate
    a key for this. The procedure is exactly the same as generating your
    certificate in step 3 above. 

5.  Next, you must configure an Identity Provider connection. Click on the
    *Identity Provider Connections* tab. Enter a name for the Identity Provider,
    enter its entity ID, and enter its metadata URL. If you have already
    followed the previous instructions and configured a separate @product@
    installation as an Identify provider, you'd enter the following information:

    - Name: *Liferay IdP*
    - Entity ID: [ID of IdP]
	- Clock Skew: Set the tolerance in milliseconds between SP and IdP.
	- Force Authn: Whether the IdP should force reauthentication regardless of
      context.
    - Metadata URL: http://localhost:8080/c/portal/saml/metadata (test this URL
      first)
	- Name Identifier Format: See settings. 
	- Attribute Mapping: See settings. 
	- Keep Alive URL: See settings. 

    **Important**: The Liferay SAML 3.0 Provider app supports using *either*
    a URL to a SAML IdP metadata file *or* an actual (uploaded) SAML metadata
    XML file. The value entered in the *Metadata URL* field is persisted to the
    database only when there a metadata URL and there is no specified metadata
    XML file. Otherwise, @product@ keeps the original metadata URL in the
    database. This behavior ensures that once a metadata URL has been specified,
    there is always a metadata URL saved in the database. This way, if a portal
    administrator forgets the previously entered metadata URL or its format, he
    or she can look at the displayed metadata URL and choose to modify the
    displayed metadata URL or overwrite the previously saved metadata URL by
    specifying a metadata XML file.

6.  Finally, after you save your certificate and private key information and
    configure an Identity Provider connection, check the *Enabled* box at the top
    of the General tab and click *Save*. @product@ is now a SAML Service Provider!

Note that the SAML Service Provider session is tied to the normal session on
the application server. Session expiration on the application server terminates
the session on the Service Provider but does not initiate single logout. 

You can add multiple IdP connections. To add another Identity Provider, click
*Add Identity Provider* again and enter the details for the other provider. When
users log in, they are asked to choose an identity provider, so be sure to name
the providers so users can recognize them. 

## Checkpoint

1. A SAML keystore has been generated.

2. Verify the connection to the IdP. 

    a.  *Name*: generic name for the IdP. 
 
    b.  *Entity ID*: the same name of the IdP. If the IdP is another @product@
        instance, then it is the same name as the above example. 

    c. *Metadata URL*: The IdP's metadata as a URL or as an XML file.

    d. If the IdP is another @product instance, ensure its corresponding
    Service Provider Connection for this SP is enabled.

3. On the *General* tab, the *Enabled* checkbox has been checked.

4. Once *Enabled* checkbox has been checked, the service provider's metadata
   becomes available:

        [host]:[port]/c/portal/saml/metadata

## Setting Up @product@ as a SAML Service Provider in a Clustered Environment

You can use the Liferay SAML 2.0 Provider app as an SSO solution for a
clustered @product@ environment. If your multi-node cluster is behind a load
balancer, you must enable all the nodes as SPs, and they must share the
same keystore manager. 

If using the Filesystem Keystore Manager (the default):

1.  Configure each node of your 
    [@product@ cluster](/docs/7-2/deployment/-/knowledge_base/deployment/liferay-clustering) 
    as a SAML service provider as above. 

2.  Copy the keystore file (`[Liferay Home]/data/keystore.jks`, by default) from
    the first node to the remaining nodes. This file is the Java keystore that's
    created by the SAML Provider app. The keystore contains the valid or
    self-signed certificate managed by the SAML Provider app.

3.  Verify that the service provider metadata has been generated to be used
    either as a URL or an XML file. The metadata is the same for all nodes
    because of the same database back-end. The IdP's request goes through the
    load balancer.

4.  At this point, all  nodes have the same SAML SP configuration and each of
    them can respond to web requests and handle the SAML protocol. To test your
    SSO solution, sign into @product@ via your load balancer, navigate to a few
    pages of a few different sites, and then log out.

If using the Document Library Keystore Manager, skip step 2 because the keystore
file is stored in the database shared by all the nodes.

Now you know how to configure @product@ either as a SAML identity provider
or a service provider. You also know how to configure SAML in a
clustered environment. 
