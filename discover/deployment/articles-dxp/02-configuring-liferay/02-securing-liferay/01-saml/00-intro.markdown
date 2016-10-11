# SAML

The @product@ SAML adapter lets you configure Single Sign On (SSO) and Single
Log Off (SLO) for Liferay, using a Liferay instance as either the Service Provider
(SP) or the Identity Provider (IdP). This article provides the conceptual
framework for Liferay's SSO solution.

-  Identity Provider initiated SSO and SLO
-  Identity Provider initiated SLO
-  Service Provider initiated SSO
-  Service Provider initiated SLO

+$$$

**Note:** A single Liferay instance can be *either* the SP or the IdP in your
SSO setup, but cannot be both. You can however, use separate instances for both
purposes (for example, one instance is the SP and one is the IdP).

$$$

Once you're through here, see the next article on [Setting Up
SAML](/discover/portal/-/knowledge_base/7-0/setting-up-saml) for instructions on
using the [SAML
adapter](https://web.liferay.com/marketplace/-/mp/application/15188711). In that
article you learn to make the conceptual magic from this article come to life! Yeah, that
was a lame attempt to get you escited about Liferay's SSO solution--just keep
reading, starting with identity provider initiated SSO.

## Identity Provider Initiated SSO [](id=identity-provider-initiated-sso)

Sometimes a user enters the SSO cycle by sending a request directly from the
browser to the IdP.

![Figure 1: Identity Provider Initiated SSO](../../../../images-dxp/saml-idp-initiated-sso.png)

### The SSO Request

What happens when a SSO request is sent directly to the IdP from the user's
browser? If Liferay is the IdP, the IdP initiated SSO URL

- Must specify the path as `/c/portal/saml/sso`.
- Must include the `entityId` parameter which is the identifier to a
  previously configured Service Provider Connection (SPC).
- May include a `RelayState` parameter which contains a URL encoded value
  to which the user will be redirected upon successful authentication. This
  URL should point to a location on the desired SPC.

For non-Liferay IdPs (Siteminder, ADFS, etc.), consult the vendor's
documentation on constructing IdP initiated SSO URLs.

If the IdP determines that the user isn't authenticated, it prompts the user
with the appropriate login screen. 

### The SSO Response

Upon successful authentication the IdP constructs a SAML Response. It includes
attribute statements configured in the designated Service Provider Connection
(SPC; see the [next
article](/discover/portal/-/knowledge_base/7-0/setting-up-saml) on setting up
the SPC in Liferay's SAML adapter).

The IdP sends the response to the Assertion Consumer Service URL as configured
in the SPC. (Note: the Assertion Consumer Service URL is generally imported as
part of the SAML metadata XML provided by the SP.) The SAML Response may be sent
via HTTP-POST or HTTP-Redirect. This is again specified in the SAML metadata
XML. HTTP-POST is the preferred method to reduce the risk that the URL is too
long for a browser to handle. If using HTTP-POST, the request contains two
parameters:

    SAMLResponse

and

    RelayState

The SP validates and processes the SAML Response. Liferay's SAML solution
requires `SAMLResponse` messages to be signed. This signature process ensures
proper identification for the IdP and prevents potential SAML Response spoofing.

- If one Liferay instance is serving as the IdP and another is serving as the
  SP, make sure the proper certificates are used. The SAML metadata XML imported
  into the Liferay SP should contain the proper certificate of the Liferay IdP.
- If Liferay is serving as the IdP and another application is the SP,
  export the certificate from the Liferay IdP and import it into the SP's
  certificate store.

If a `RelayState` is included in the SAML Response, the user is redirected to
it. Otherwise the home page of the SP is served.

## Identity Provider Initiated SLO [](id=identity-provider-initiated-slo)

![Figure 2: Identity Provider Initiated SLO](../../../../images-dxp/saml-idp-initiated-slo.png)

### The SLO Request

An IdP initiated SLO request is a SLO request sent directly to the IdP by the
user's browser. When Liferay serves as the IdP, the IdP initiated SSO URL
must specify the URL path as 

`/c/portal/logout`

If the user is signed on to any configured SP, the SAML plugin takes over the
logout process, displaying all the signed on services. The single logout screen
displays the status of each SP logout and whether any SPs can't be logged out of
(for example, if the SP is down or doesn't support SLO). For non-Liferay IdPs
(Siteminder, ADFS, etc.) consult the vendor's documentation on constructing IdP
initiated SLO URLs.

The IdP sends a SAML `LogoutRequest` to the SP

- When Liferay is configured as the IdP, the `LogoutRequest` is sent using
  either HTTP-Post, HTTP-Redirect, or SOAP binding. HTTP-Post binding is
  preferred but in its absence, the first available SLO endpoint with supported
  binding is selected.
- When Liferay is configured as the SP, supported bindings for
  `LogoutRequest` are HTTP-Post, HTTP-Redirect, or SOAP.
- For other IdPs or SPs, please consult the vendor's
  documentation.

### The SLO Response

The SP delivers the `LogoutResponse` to the IdP. When Liferay is configured as
the SP, the `LogoutResponse` is delivered using either HTTP-Post, HTTP-Redirect,
or direct response to SOAP request. HTTP-Post binding is preferred but in its
absence, HTTP-Redirect will be used. SOAP is only used to respond to the
`LogoutRequest` over SOAP binding.

The IdP sends a SAML `LogoutRequest` to the second SP using either HTTP-Post,
HTTP-Redirect, or SOAP binding.

The second SP then delivers the `LogoutResponse` to the IdP using either
HTTP-Post, HTTP-Redirect, or direct response to SOAP request. This process is
repeated for all of the SPs the user is logged in to. When Liferay is configured
as the IdP, Liferay logs the user out after the last SP has delivered its
`LogoutResponse` or has timed out.

## Service Provider Initiated SSO [](id=service-provider-initiated-sso)

![Figure 3: Service Provider Initiated SSO](../../../../images-dxp/saml-sp-initiated-sso.png)

### The SSO Request

When the user's browser requests a protected resource or sign on URL on the SP,
the SP initiated SSO process is triggered. With Liferay is configured as a SAML
SP, SSO can be initiated either by requesting the URL `/c/portal/login` or by
requesting a protected resource that requires authentication (e.g., a document
that is not viewable by the Guest role). If the user requests a protected
resource, its URL is recorded as in the `RelayState` parameter. If the user
requested `/c/portal/login`, the `RelayState` can be set by providing the
`redirect` parameter. Otherwise, if `auth.forward.by.last.path` is enabled, the
last accessed path is set as the `RelayState`. For non-Liferay SPs, consult the
vendor documentation on how to initiate initiate SSO.

The SP looks up the IdP's Single Sign On service URL<!--Need to understsnd what
the IdP SSO URL is--> and sends an `AuthnRequest`. When Liferay is configured as
the SP, Liferay looks up the configured SAML Identity Provider Connection and
sends a SAML `AuthnRequest` to the IdP's Single Sign On service URL defined in
the SAML metadata XML document. Liferay supports sending and receiving the
`AuthnRequest` using HTTP-Post or HTTP-Redirect binding. HTTP-Post binding is
preferred.

If the user doesn't have an active session or if `ForceAuthn` was requested by
the SP, the user must authenticate by providing their credentials.  When Liferay
is configured as the IdP, authentication occurs in Login portlet. Liferay
decodes and verifies the `AuthnRequest` before requesting the user to
authenticate.

### The SSO Response

After sign on a SAML Response is constructed, sent to the Assertion Consumer
Service URL of the SP, and verified.

When Liferay is configured as the IdP, any attributes configured on the
Service Provider Connection are included in the response as attribute
statements. The Assertion Consumer Service URL is looked up from the SAML
metadata XML of the SP. The response can be sent via HTTP-Post binding or
HTTP-redirect binding. The IdP automatically makes this choice based on the
SP metadata. HTTP-Post binding is preferred and used when available.
HTTP-Redirect binding often doesn't work because the signature and included
assertions make the URL too long for browsers.

When Liferay is configured as the SP, any response and assertion signatures
are verified. Liferay requires the sender to be authenticated. This is done
via whole message signature from the issuing IdP. Any responses that are
missing that signature are considered unauthenticated and the response is
rejected. The Response can be received via HTTP-Post binding or
HTTP-redirect binding. HTTP-Post binding is preferred for the reasons
mentioned in the previous section. For non-Liferay SP or IdP vendors,
consult their documentation.

The user is redirected to the requested resource or to the URL contained in the
`RelayState` parameter (e.g., the last page the user accessed before initiating
SSO).

## Service Provider Initiated SLO

![Figure 4: Service Provider Initiated SLO](../../../../images-dxp/saml-sp-initiated-slo.png)

### The SLO Request

A SP initiated SLO comes directly from the user's browser to the SP. When Liferay is
configured as the SP, the SLO is initiated by requesting the logout URL

    /c/portal/logout

For other SPs, consult the vendor's documentation on initiating SLO.

A SAML `LogoutRequest` is sent to the Single Log Out service URL of the IdP.

When Liferay serves as the SP the `LogoutRequest` is sent to the IdP
configured by the Identity Provider Connection and the SLO service URL defined
in the SAML metadata. This is sent either using HTTP-POST or HTTP-Redirect
binding.

When Liferay is configured as the IdP, if the user has logged on to other SPs,
the user is presented with a single logout screen. Otherwise, the SAML session
is terminated the process jumps to step 5. The single logout screen displays the
status of each SP logout and shows any SPs that can't be logged out of (some SPs
might be down or don't support SLO).

### The SLO Response

The IdP sends a SAML `LogoutResponse` to the SP. When Liferay is configured as
the IdP, the `LogoutResponse` is sent using either HTTP-Post, HTTP-Redirect, or
SOAP binding.

The SP delivers the `LogoutResponse` to the IdP. When Liferay is configured as
the SP, the `LogoutResponse` is sent using either HTTP-Post, HTTP-Redirect or
direct response to SOAP request.

The IdP destroys its SSO session. When Liferay is the IdP, once the last SP has
delivered its `LogoutResponse` or has timed out, the IdP destroys the Liferay
session, logging out the user.

Finally, the IdP sends a `LogoutResponse` to the SP that initiated SLO.

The SP terminates its SAML session and proceeds to log out.

## Related Topics [](id=related-topics)

- [Configuring Liferay as an IdP or SP (not yet written)]()
