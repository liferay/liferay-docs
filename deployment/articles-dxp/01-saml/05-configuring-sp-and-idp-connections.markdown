# Changing the Settings for Service Provider and Identity Provider Connections

To change the SAML Service Provider Settings, navigate to the Service Provider
tab. 

The Service Provider tab includes these options:

**Require Assertion Signature?:** Check this box to require SAML assertions
to be individually signed in addition to the entire SAML message.

| **Note:** Individual assertions need not be signed as long as the SAML response
| itself is signed. The SP and IdP should always communicate over `https` to have
| encryption at the transport level.
| 
| If you believe man-in-the-middle attacks are possible, the SAML response can be
| signed. The only reason to sign the assertions is if the SAML response is not
| signed. In this case, assertions should not only be signed but also encrypted.

**Clock Skew:** Clock skew is a tolerance in milliseconds used by the Service
Provider for mitigating time differences between the clocks of the Identity
Provider and the Service Provider. This usually only matters when assertions
have been made to expire very quickly.

**LDAP Import Enabled:** Check this box to import user information from the
configured LDAP connection based on the resolved `NameID`. LDAP connections can be
configured from Instance Settings.

**Sign Authn Requests:** Check this box to sign the `AuthnRequest` even if the
Identity Provider metadata indicates that it's not required.

**Sign Metadata:** Check this box to sign the metadata XML file.

**SSL Required:** Check this box to reject SAML messages that are not sent
over HTTPS. This does not affect how URLs are generated.

## Changing the SAML Identity Provider Connection Settings

If you'd like to configure @product@'s SAML Identity Provider Settings, navigate to
the Identity Provider Connection tab of the SAML Admin portlet.

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

**Keep Alive URL:** If users are logged into several @product@ SP instances via
a @product@ IdP, their sessions can be kept alive as long as they keep a browser
window open to one of them. Configure this only if the IdP is @product@. The URL
is `https://[IdP host name]/c/portal/saml/keep_alive`. On the @product@ IdP,
configure this URL the same way, but point back to this SP. 

Save your changes when you are finished configuring the @product@ instance as a
service provider. There is no need to restart the server and the changes will be
applied immediately.

The previous two sections explained how to use the SAML 2.0 Provider
app's Control Panel interface to configure @product@ as an Identity
Provider or as a Service Provider. Such configurations should only be made
through the SAML Control Panel interface and not via properties. Some features
of the Liferay SAML 2.0 Provider app are not available as properties.


| **Limitation:** The Liferay SAML app can only be used with a single virtual
| host. Technically, this means that in the SAML metadata for @product@, only one
| binding can be added in this form:
| 
|     <md:EntityDescriptor>
|     ...
|     <md:SPSSODescriptor>
|     ...
|     <md:AssertionConsumerService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="https://portal.domain.com/c/portal/saml/acs" index="1" isDefault="true" />
|     ...
|     </md:SPSSODescriptor>
|     </md:EntityDescriptor>

