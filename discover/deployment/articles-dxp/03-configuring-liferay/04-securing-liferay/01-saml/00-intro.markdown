# SAML [](id=saml-0)

The @product@'s SAML (Security Assertion Markup Language) adapter lets you
execute Single Sign On (SSO) and Single Log Off (SLO) in your deployment. Each
@product@ instance serves as either the Service Provider (SP) or the Identity
Provider (IdP). This article provides the conceptual framework for @product@'s
SSO solution.

- Single Sign On
    - Identity Provider initiated SSO
    - Service Provider initiated SSO

- Single Log Off
    - Identity Provider initiated SLO
    - Service Provider initiated SLO

+$$$

**Note:** A single @product@ instance is *either* the SP or the IdP in your SSO
setup; it can't be both. You can, however, use separate instances for both
purposes (for example, one instance is the SP and another is the IdP).

$$$

Below is background on how SAML works. To jump right to its configuration, see
the next article on [Setting Up SAML](/discover/deployment/-/knowledge_base/7-0/setting-up-saml)
for instructions on using the [SAML adapter](https://web.liferay.com/marketplace/-/mp/application/15188711). 
Use the instructions to make the conceptual magic from this article come to
life! 


## Important SAML URLs [](id=important-saml-urls)

For reference, here are a few important SAML URLs.

This URL is the default location of @product@'s metadata XML file:

    [host]:[port]/c/portal/saml/metadata

Note that when configuring SAML for @product@, no importing of SAML certificates
is required. @product@ reads certificates from the SAML metadata XML file. If you
want a third-party application like Salesforce to read a Liferay SAML
certificate, you can export the @product@ certificate from the keystore. The
default keystore file is `[Liferay Home]/data/keystore.jks`. The exported
certificate can be imported by a third-party application like Salesforce.


## Single Sign On [](id=single-sign-on)

Both the IdP and the SP can initiate the Single Sign On process, and the SSO flow
is different depending on each one. Consider IdP initiated SSO first.

### Identity Provider Initiated SSO [](id=identity-provider-initiated-sso)

Sometimes a user enters the SSO cycle by sending a request directly from the
browser to the IdP.

![Figure 1: Identity Provider Initiated SSO](../../../../images-dxp/saml-idp-initiated-sso.png)

#### The SSO Request to the IdP [](id=the-sso-request-to-the-idp)

If @product@ is the IdP, the IdP initiated SSO URL

- Must specify the path as `/c/portal/saml/sso`. 
- Must include the `entityId` parameter which is the identifier to a
  previously configured Service Provider Connection (SPC). 
- May include a `RelayState` parameter which contains a URL
  encoded value to which the user will be redirected upon successful
  authentication. This URL should point to a location on the desired SPC
  (according to the 
  [SAML 2.0 standards section 3.4.3](https://docs.oasis-open.org/security/saml/v2.0/saml-bindings-2.0-os.pdf), 
  this value *must not* exceed 80 bytes in length). It is useful to specify a
  landing page after SSO has been executed.

For non-@product@ IdPs (Siteminder, ADFS, etc.), consult the vendor's
documentation on constructing IdP initiated SSO URLs.

If the IdP determines that the user isn't authenticated, it prompts the user
with the appropriate login screen. 

#### The SSO Response from the IdP [](id=the-sso-response-from-the-idp)

Upon successful authentication the IdP constructs a SAML Response. It includes
attribute statements configured in the designated Service Provider Connection
(SPC; see the [next article](/discover/portal/-/knowledge_base/7-0/setting-up-saml) on setting up
the SPC in @product@'s SAML adapter).

The IdP sends the response to the Assertion Consumer Service URL using HTTP-POST
or HTTP-Redirect. HTTP-POST is preferred because it reduces the risk that
the URL is too long for a browser to handle. Using HTTP-POST, the request
contains two parameters:

    SAMLResponse

and

    RelayState

+$$$

**Note:** The method for sending the SAML response (for example, HTTP-Post) and
the Assertion Consumer Service URL are generally imported as part of the SAML
metadata XML provided by the SP. In @product@, you import the SP's metadata in the
SAML Adapter's Service Provider Connections tab.

$$$

#### The SP Processes the SSO Response [](id=the-sp-processes-the-sso-response)

The SP validates and processes the SAML Response. @product@'s SAML solution
requires `SAMLResponse` messages to be signed. This signature process ensures
proper identification for the IdP and prevents potential SAML Response spoofing.

- If one @product@ instance is the IdP and another is the
  SP, make sure the SAML metadata XML file imported into the @product@ SP contains
  the IdP's certificate. 
- If @product@ is the IdP and another application is the SP,
  export the certificate from the @product@ IdP and import it into the SP's
  certificate store.

If a `RelayState` is included in the SAML Response, the user is redirected to
it. Otherwise the home page of the SP is served.

### Service Provider Initiated SSO [](id=service-provider-initiated-sso)

![Figure 2: Service Provider Initiated SSO](../../../../images-dxp/saml-sp-initiated-sso.png)

#### The SSO Request to the SP [](id=the-sso-request-to-the-sp)

When the user's browser requests a protected resource or sign on URL on the SP,
it triggers the SP initiated SSO process. When @product@ is the SAML SP, SSO is
initiated either by requesting `/c/portal/login` URL or a protected resource
that requires authentication (for example, a document that is not viewable by
the Guest role). If the user requests a protected resource, its URL is recorded
in the `RelayState` parameter. If the user requested `/c/portal/login`, the
`RelayState` can be set by providing the `redirect` parameter. Otherwise, if the
[portal property](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html)
`auth.forward.by.last.path` is set to true, the last accessed path is set as the
`RelayState`. For non-@product@ SPs, consult the vendor documentation on initiating
SSO.

#### The AuthnRequest to the IdP [](id=the-authnrequest-to-the-idp)

The SP looks up the IdP's Single Sign On service URL and sends an
`AuthnRequest`. When @product@ is the SP it looks up the configured SAML Identity
Provider Connection and sends a SAML `AuthnRequest` to the IdP's Single Sign On
service URL as defined in the SAML metadata XML document. @product@ supports
sending and receiving the `AuthnRequest` using HTTP-Post or HTTP-Redirect
binding. HTTP-Post is preferred.

If the user doesn't have an active session or if `ForceAuthn` was requested by
the SP, the user must authenticate by providing his or her credentials. When
@product@ is the IdP, authentication occurs in the Login Portlet. @product@ decodes
and verifies the `AuthnRequest` before requesting the user to authenticate.

#### The SSO Response from the IdP [](id=the-sso-response-from-the-idp-0)

After authentication a SAML Response is constructed, sent to the Assertion
Consumer Service URL of the SP, and verified.

When @product@ is configured as the IdP, any attributes configured on the Service
Provider Connection are included in the response as attribute statements. The
Assertion Consumer Service URL is looked up from the SAML metadata XML of the
SP. The response is sent using HTTP-Post or HTTP-redirect binding. The IdP
automatically makes this choice based on the SP metadata. HTTP-Post binding is
preferred and used when available. HTTP-Redirect binding is fragile because the
signature and included assertions often make the URL too long for browsers.

When @product@ is configured as the SP, any response and assertion signatures are
verified. @product@ requires the sender to be authenticated. This is done via
whole message signature from the issuing IdP. Any responses missing the
signature are considered unauthenticated and the response is rejected. The
Response can be received via HTTP-Post binding or HTTP-redirect binding.
HTTP-Post binding is preferred for the reasons mentioned in the previous
section. For non-@product@ SP or IdP vendors, consult their documentation.

The user is redirected to the requested resource or to the URL contained in the
`RelayState` parameter (for example, the last page the user accessed before
initiating SSO).

## Single Log Off [](id=single-log-off)

The Single Log Off request is sent from the user's browser to either the IdP or
to a SP, and the SLO flow differs in each case. First consider IdP initiated
SLO.

### Identity Provider Initiated SLO [](id=identity-provider-initiated-slo)

![Figure 3: Identity Provider Initiated SLO](../../../../images-dxp/saml-idp-initiated-slo.png)

#### The SLO Request to the IdP [](id=the-slo-request-to-the-idp)

An IdP initiated SLO request is a SLO request sent directly to the IdP by the
user's browser. When @product@ serves as the IdP, the IdP initiated SSO URL
must specify the URL path as 

`/c/portal/logout`

If the user is signed on to any configured SP, the SAML plugin takes over the
logout process, displaying all the signed on services. The single logout screen
displays the authentication status of each SP and whether any SPs can't be
logged out of (for example, if the SP is down or doesn't support SLO). For
non-@product@ IdPs (Siteminder, ADFS, etc.) consult the vendor's documentation on
constructing IdP initiated SLO URLs.

The IdP sends a SAML `LogoutRequest` to the SP.

- When @product@ is configured as the IdP, the `LogoutRequest` is sent using
  either HTTP-Post, HTTP-Redirect, or SOAP binding. HTTP-Post binding is
  preferred but in its absence, the first available SLO endpoint with supported
  binding is selected.
- When @product@ is configured as the SP, supported bindings for `LogoutRequest`
  are HTTP-Post, HTTP-Redirect, or SOAP.
- For other IdPs or SPs, please consult the vendor's documentation.

#### The SLO Response from the SP [](id=the-slo-response-from-the-sp)

The SP delivers a `LogoutResponse` to the IdP. When @product@ is configured as
the SP, the `LogoutResponse` is delivered using either HTTP-Post, HTTP-Redirect,
or direct response to SOAP request. HTTP-Post binding is preferred but in its
absence, HTTP-Redirect is used. SOAP is only used to respond to the
`LogoutRequest` over SOAP binding.

The IdP sends a SAML `LogoutRequest` to the second SP using either HTTP-Post,
HTTP-Redirect, or SOAP binding.

The second SP then delivers the `LogoutResponse` to the IdP using HTTP-Post,
HTTP-Redirect, or direct response to SOAP request. The process is repeated for
all SPs the user is logged into. When @product@ is the IdP, @product@ logs the user
out after the last SP has delivered its `LogoutResponse` or has timed out.

### Service Provider Initiated SLO [](id=service-provider-initiated-slo)

![Figure 4: Service Provider Initiated SLO](../../../../images-dxp/saml-sp-initiated-slo.png)

#### The SLO Request to the SP [](id=the-slo-request-to-the-sp)

In SP initiated SLO, user's browser requests logout directly to the SP.  When
@product@ is configured as the SP, the SLO is initiated by requesting the logout
URL

    /c/portal/logout

For other SPs, consult the vendor's documentation on initiating SLO.

A SAML `LogoutRequest` is sent to the Single Log Out service URL of the IdP.

-  If @product@ serves as the SP, the `LogoutRequest` is sent to the IdP 
configured by the IdP Connection tab of the SAML provider (see the [next
article](/discover/portal/-/knowledge_base/7-0/setting-up-saml) to set up the
IdP Connection) and the SLO service URL defined in the SAML metadata. The
request is sent using HTTP-POST or HTTP-Redirect binding.

-  When @product@ is the IdP, if the user has logged on to other SPs the user is
presented with a single logout screen with the status of each SP logout,
flagging any that can't be looged out of (some SPs might not support SLO or are
currently down). If there are no other SPs to log out of, the SAML session
terminates and the IdP destroys its session.

#### The SLO Response from the SP [](id=the-slo-response-from-the-sp-0)

If the user is logged in to additional SPs (beyond just the initiating SP), the
IdP sends the SAML `LogoutRequest` to each one. When @product@ is the IdP, the
`LogoutResponse` is sent using either HTTP-Post, HTTP-Redirect, or SOAP binding.

Each SP delivers its `LogoutResponse` to the IdP. When @product@ is the SP, the
`LogoutResponse` is sent using either HTTP-Post, HTTP-Redirect or direct
response to SOAP request.

After all additional SPs deliver their `LogoutResponse`s to the IdP, the IdP
destroys its SSO session. When @product@ is the IdP, once the last SP has
delivered its `LogoutResponse` or has timed out, the IdP destroys the @product@
session, logging out the user.

Finally, the IdP sends a `LogoutResponse` to the SP that initiated SLO. The
initiating SP terminates its SAML session and logs the user out.

## Related Topics [](id=related-topics)

- [Setting Up SAML](/discover/deployment/-/knowledge_base/7-0/setting-up-saml)
- [Token-Based SSO Authentication](https://dev.liferay.com/discover/deployment/-/knowledge_base/7-0/token-based-single-sign-on-authentication)
