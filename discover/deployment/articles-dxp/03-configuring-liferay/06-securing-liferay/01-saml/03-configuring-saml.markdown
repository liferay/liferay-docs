# Configuring SAML 

As noted in the previous tutorials, anything related to configuring SP
connections must be done through the SAML Admin UI where configurations are
saved to Liferay's database. SP connections can no longer be made via properties
files as they were in the past. 

This is an instance-scoped configuration which can be managed via OSGi
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

The SAML Admin UI remains the place for creating the company-scoped
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
Configuration* in *Control Panel* &rarr; *System Settings*. The options for
this configuration are explained above in the Setting up @product@ as a SAML
Identity Provider section.
