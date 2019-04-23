---
header-id: configuring-saml
---

# Configuring SAML

There are some ways of configuring the SAML plugin outside the UI. This is done
via OSGi configuration files and by uploading metadata XML to configure how
connections are negotiated. 

## OSGi Configuration Properties

As noted in the previous tutorials, anything related to configuring SP
connections must be done through the SAML Admin UI where configurations are
saved to Liferay's database. SP connections can no longer be made via properties
files as they were in versions prior to 3.1.0. 

| **Note:** Don't use OSGi `.config` files or @product@'s System Settings Control
| Panel application to configure SAML providers (IdP or SP). The System Settings
| UI is auto-generated, and is for advanced administrators. It does not perform the
| enhanced validation on the fields that the SAML Admin UI performs, so it could
| allow administrators to create invalid configurations.

This is a portal instance scoped configuration which can be managed via OSGi
Configuration Admin. The affected properties are those in the
`SAMLProviderConfiguration` metatype:

    - `saml.keystore.credential.password`
    - `saml.sp.assertion.signature.required`
    - `saml.idp.authn.request.signature.required`
    - `saml.sp.clock.skew`
    - `saml.default.assertion.lifetime`
    - `saml.sp.default.idp.entity.id`
	- `saml.enabled`
	- `saml.entity.id`
	- `saml.sp.ldap.import.enabled`
	- `saml.role`
	- `saml.idp.session.maximum.age`
    - `saml.idp.session.timeout`
    - `saml.sp.sign.authn.request`
    - `saml.sign.metadata`
    - `saml.ssl.required`
    - `saml.idp.metadata.name.id.attribute`

The SAML Admin UI remains the place for creating the portal instance scoped
configuration instances.

Note that there is also a system wide configuration, represented by the
`SamlConfiguration` metatype. 

If you used Liferay 6.2, please note that the following system wide properties
were removed:

    `saml.metadata.paths` (served no purpose after removal of SP connection defaults)
    `saml.runtime.metadata.max.refresh.delay`
    `saml.runtime.metadata.min.refresh.delay`

The latter two properties were replaced with the single property
`saml.runtime.metadata.refresh.interval`.

Note also the introduction of the *SAML KeyStoreManager Implementation
Configuration* in *Control Panel* &rarr; *System Settings* &rarr; Security
&rarr; SSO. The options for this configuration are explained above in the
Setting up @product@ as a SAML Identity Provider section.

In the latest version of the plugin, the SHA256 algorithm is the default
encryption algorithm used in the configuration and to generate keys. The default
configuration tries SHA256, then SHA384, then SHA512 before falling back to
SHA1. Because SHA1 is potentially vulnerable, you can blacklist it using this
property: 

```properties
blacklisted.algorithms=["blacklisted_algorithm_url", "another_blacklisted_algorithm_url"]
```

To blacklist SHA1, therefore, you'd have this configuration: 

```properties
blacklisted.algorithms=["http://www.w3.org/2000/09/xmldsig#sha1"]
```

There's a lot more granularity in how connections are negotiated if you
configure the metadata XML. 

## Configuring Negotiation Via metadata.xml

If the default negotiation configuration doesn't work for you, you can craft
your own configuration and upload it. Before doing this, visit your host's
metadata URL and save a copy of the configuration in case you need it later: 

    http://[hostname]/c/portal/saml/metadata

For example, if you're stuck connecting to
a legacy IdP that only supports SHA1, you can upload a configuration that
disables the other algorithms: 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<md:EntityDescriptor xmlns:md="urn:oasis:names:tc:SAML:2.0:metadata" entityID="samlidp">
  <md:IDPSSODescriptor WantAuthnRequestsSigned="true" protocolSupportEnumeration="urn:oasis:names:tc:SAML:2.0:protocol">
    <md:Extensions>
      <alg:SigningMethod xmlns:alg="urn:oasis:names:tc:SAML:metadata:algsupport" Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
    </md:Extensions>
    <md:KeyDescriptor use="signing">
      <ds:KeyInfo xmlns:ds="http://www.w3.org/2000/09/xmldsig#">
        <ds:X509Data>
          <ds:X509Certificate>... omitted ...</ds:X509Certificate>
        </ds:X509Data>
      </ds:KeyInfo>
    </md:KeyDescriptor>
    <md:SingleLogoutService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="http://localhost:8080/c/portal/saml/slo"/>
    <md:SingleLogoutService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="http://localhost:8080/c/portal/saml/slo"/>
    <md:SingleSignOnService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="http://localhost:8080/c/portal/saml/sso"/>
    <md:SingleSignOnService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="http://localhost:8080/c/portal/saml/sso"/>
  </md:IDPSSODescriptor>
</md:EntityDescriptor>
```

Notice that in the configuration above, the `<md:Extensions>` block has only one
signing algorithm: SHA1. 

| **Note:** Since the default configuration falls back to SHA1, you shouldn't
| need to do this unless your legacy system can't negotiate via the fallback
| mechanism. Also note that if you blacklisted SHA1, this won't work. Due to
| [vulnerabilities in SHA1](https://en.wikipedia.org/wiki/SHA-1), it's best to
| avoid using it altogether if possible. 

If you've changed your metadata configuration, you can go back to the default
configuration if you saved it before making the change. If you didn't, you can
provide a URL instead of an uploaded XML file to one of your peers' metadata
configurations. 
