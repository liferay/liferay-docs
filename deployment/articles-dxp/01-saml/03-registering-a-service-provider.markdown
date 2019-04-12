---
header-id: registering-a-saml-service-provider
---

# Registering a SAML Service Provider

Setting up @product@ as a SAML Identity Provider is only useful if you
can connect to one or more SAML Service Providers. Navigate to the Service
Provider Connections tab of the SAML Admin Control Panel entry and click 
the *Add Service Provider* button to add a SAML Service Provider.

The New Service Provider page includes these options:

**Name:** The name of the Service Provider with which to connect. The name can
be anything; it's purely cosmetic.

**Entity ID:** The Service Provider's entity ID. This value must match the
entity ID declared in the Service Provider metadata.

**Enabled:** Check this box to activate the Service Provider connection.

**Assertion Lifetime:** Defines the number of seconds after which the SAML
assertion issued by the Identity Provider should be considered expired.

**Force Encryption:** If the SP does not provide a public key for encrypting the
assertions, abort the single sign-on. 

**Metadata:** Provide a URL to the Service Provider metadata XML file or
manually upload the Service Provider metadata XML file. If you provide a URL,
the XML file is retrieved and periodically polled for updates. The update
interval can be configured in System Settings with the
`saml.metadata.refresh.interval` property which specifies a number of seconds.
If fetching the metadata XML file by URL fails, you can't enable the Service
Provider connection. If the Identity Provider server cannot access the metadata
via URL, you can upload the XML file manually. In this case, the metadata XML
file is not updated automatically. 

**Name Identifier Format:** Choose the Name Identifier Format used in the SAML
Response. This should be set according to what the Service Provider expects to
receive. For Liferay Service Providers, any selection other than email address
indicates that the Name Identifier refers to screen name. The formats don't have
any special meaning to Liferay Identity Providers. The `NameID` value is defined
by the Name Identifier attribute. See the next option.

**Name Identifier Attribute Name:** This specifies which attribute of the
@product@ `User` object to use as the `NameID` value. Possible values include
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
namespaces can be useful. Use them when attribute names from different
namespaces might conflict. For example, `expando:user` vs
`urn:liferay:roles:user`.

**Keep Alive URL:** If users are logged into several @product@ SP instances via
a @product@ IdP, their sessions can be kept alive as long as they keep a
browser window open to one of them. Configure this only if the SP is @product@.
The URL is `https://[SP host name]/c/portal/saml/keep_alive`. 

## Checkpoint

Verify your settings are correct when connecting the @product@-based IdP to its
first SP. SPs connect to only one IdP, so if the first one doesn't work, the
rest won't either. 

1. Provide a general name for the SP.

2. The `Entity ID` name must be identical to the one declared in the Service
   Provider metadata.

3. Check the *Enabled* checkbox.

4. Set a value for the *Assertion Lifetime*.

5. Choose whether encryption should be required (recommended). 

6. Make sure the SP's metadata has been provided either as a URL or an XML file 
   has been uploaded.

7. Make sure *Name Identifier Format* and *Name Identifier Attribute Name* have 
   been set.

8. Make sure *Attributes Namespace Enabled* has been set.

If you don't have a Service Provider to add right now, that's fine. In the next
section, you'll learn how to set @product@ up as a SAML Service Provider. The
same instance can't be both, but after you set up another @product@ instance as
a Service Provider, come back to this one and add the Service Provider according
to the instructions above.
