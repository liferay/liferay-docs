# Liferay SAML in Pictures [](id=liferay-saml-in-pictures)

(Coming soon)

<!-- This EE-only article needs to be tested and updated for 7.0. -->

![EE-only](../../images/ee-feature-web.png)

This article provides diagrams of the SAML actions supported by Liferay along
with descriptions of each step. For information about configuring Liferay as a
SAML Identity Provider or Service Provider, please see the [not yet written]()
article.

## Identity Provider Initiated SSO [](id=identity-provider-initiated-sso)

![Figure 1: Identity Provider Initiated SSO](../../images/idp-initiated-sso.png)

1. A IdP initiated SSO request is a SSO request sent directly to the IdP by the
   user's browser. For situations where Liferay is configured to serve as the
   IdP, the IdP initiated SSO URL
    - Must specify the path as `/c/portal/saml/sso`.
    - Must include the `entityId` parameter which is the identifier to a
      previously configured Service Provider Connection (SPC).
    - May include a `RelayState` parameter which contains a URL encoded value
      to which the user will be redirected upon successful authentication. This
      URL should point to a location on the desired SPC.
    For non-Liferay IdPs (e.g., Siteminder, ADFS, etc.), please consult the
    vendor appropriate documentation for how to construct IdP initiated SSO
    URLs.

2. If the IdP determines that the user is not authenticated, it prompts the
   user with the appropriate login page. If Liferay Portal is the IdP, the
   portal prompts the user with its login screen.

3. Upon successful authentication, the IdP constructs a SAML Response that
   contains appropriate attribute statements as configured in the designated
   SPC.

4. The IdP sends the response to the Assertion Consumer Service URL as
   configured in the SPC. (Note: the Assertion Consumer Service URL is
   generally imported as part of the SAML metadata XML provided by the SP.) The
   SAML Response may be sent via HTTP-POST or HTTP-Redirect. This is again
   specified in the SAML metadata XML. HTTP-POST is the preferred method to
   reduce the risk that the URL is too long for a browser to handle. If using
   HTTP-POST, the request contains two parameters:
    - the `SAMLResponse`
    - the `RelayState`

5. The SP validates and processes the SAML Response. Liferay's SAML solution
   requires `SAMLResponse` messages to be signed. This signature process
   ensures proper identification for the IdP and prevents potential SAML
   Response spoofing.
    - If one Liferay Portal is serving as the IdP and another is serving as the
      SP, make sure the proper certificates are used. The SAML metadata XML
      imported into the Liferay SP should contain the proper certificate of the
      Liferay IdP.
    - If Liferay is serving as the IdP and another application as the SP, make
      sure to export the certificate from the Liferay IdP and import it into
      the SP's certificate store.

6. The User is redirected either to the `RelayState` (if specified) or home
   page of the SP (if no `RelayState` is provided).

## Identity Provider Initiated SLO [](id=identity-provider-initiated-slo)

![Figure 2: Identity Provider Initiated SLO](../../images/idp-initiated-slo.png)

1. An IdP initiated SLO request is a SLO request sent directly to the IdP by
   the user's browser. For situations where Liferay is configured to serve as
   the IdP, the IdP initiated SSO URL must specify the URL path as
   `/c/portal/logout`. If the user has signed on to any configured SP, the SAML
   plugin takes over the logout process and displays a screen with all the
   signed on services. The single logout screen displays the status of each SP
   logout and shows any SPs that can't be logged out of (some SPs might be down
   or simply don't support SLO). For non-Liferay IdPs (e.g., Siteminder, ADFS,
   etc.), please consult the vendor appropriate documentation for how to
   construct IdP initiated SLO URLs.

2. The IdP sends a SAML `LogoutRequest` to the SP
    - When Liferay is configured as the IdP, the `LogoutRequest` is sent using
      either HTTP-Post, HTTP-Redirect, or SOAP binding. HTTP-Post binding is
      preferred but in its absence, the first available SLO endpoint with
      supported binding is selected.
    - When Liferay is configured as the SP, supported bindings for
      `LogoutRequest` are HTTP-Post, HTTP-Redirect, or SOAP.
    - For other IdPs or SPs, please consult the vendor appropriate
      documentation.

3. The SP delivers the `LogoutResponse` to the IdP. When Liferay is configured
   as the SP, the `LogoutResponse` is delivered using either HTTP-Post,
   HTTP-Redirect, or direct response to SOAP request. HTTP-Post binding is
   preferred but in its absence, HTTP-Redirect will be used. SOAP is only used
   to respond to the `LogoutRequest` over SOAP binding.

4. The IdP sends a SAML `LogoutRequest` to the SP using either HTTP-Post,
   HTTP-Redirect, or SOAP binding.

5. The second SP delivers the `LogoutResponse` to the IdP using either
   HTTP-Post, HTTP-Redirect, or direct response to SOAP request. When Liferay
   is configured as the IdP, Liferay logs the user out after the last SP has
   delivered its `LogoutResponse` or has timed out.

## Service Provider Initiated SSO [](id=service-provider-initiated-sso)

![Figure 3: Service Provider Initiated SSO](../../images/sp-initiated-sso.png)

1. When the users' browser requests a protected resource or sign on URL on the
   SP, the SP initiated SSO process is triggered. With Liferay is configured as
   a SAML SP, SSO can be initiated either by requesting the URL
   `/c/portal/login` or by requesting a protected resource that requires
   authentication (e.g., a document that is not viewable by the Guest role). If
   the user requests a protected resource, its URL is recorded as in the
   `RelayState` parameter. If the user requested `/c/portal/login`, the
   `RelayState` can be set by providing the `redirect` parameter. Otherwise, if
   `auth.forward.by.last.path` is enabled, the last accessed path is set as the
   `RelayState`. For non-Liferay SPs, consult the vendor documentation on how
   to initiate initiate SSO.

2. The SP looks up the IdP's Single Sign On service URL and sends an
   `AuthnRequest`. When Liferay is configured as the SP, Liferay looks up the
   configured SAML Identity Provider connection and sends a SAML `AuthnRequest`
   to the IdP's Single Sign On service URL defined in the SAML metadata XML
   document. Liferay supports sending and receiving the `AuthnRequest` using
   HTTP-Post or HTTP-Redirect binding. HTTP-Post binding is preferred.

3. If the user doesn't have an active session or if `ForceAuthn` was requested
   by the SP, the user is asked to authenticate by providing their credentials.
   When Liferay is configured as the IdP, Liferay hands over the authentication
   to the Liferay Login portlet. Liferay decodes and verifies the
   `AuthnRequest` before requesting the user to authenticate.

4. Once the user is signed on, a SAML Response is constructed, sent to the
   Assertion Consumer Service URL of the SP, and verified.

    When Liferay is configured as the IdP, any attributes configured on the
    Service Provider connection are included in the response as attribute
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

6. The user is redirected to the requested resource or to the URL contained in
   the `RelayState` parameter (e.g., the last page the user accessed before
   initiating SSO).

![Figure 4: Service Provider Initiated SLO](../../images/idp-initiated-slo.png)

1. A SP initiated SLO is sent directly by the user's browser. When Liferay is
   configured as the SP, the SLO is initiated by requesting the logout URL
   `/c/portal/logout`. For other SPs, consult the vendor's documentation on how
   to initiate SLO.

2. A SAML `LogoutRequest` is sent to the Single Log Out service URL of the IdP.

    When Liferay is configured as the SP, the `LogoutRequest` is sent to the
    IdP configured by the Identity Provider Connection and the SLO service URL
    defined in the SAML metadata. This is sent either using HTTP-POST or
    HTTP-Redirect binding.

    When Liferay is configured as the IdP, if the user has logged on to other
    SPs, the user is presented with a single logout screen. Otherwise, the SAML
    session is terminated the process jumps to step 5. The single logout screen
    displays the status of each SP logout and shows any SPs that can't be
    logged out of (some SPs might be down or simply don't support SLO).

3. The IdP sends a SAML `LogoutRequest` to the SP. When Liferay is configured
   as the IdP, the `LogoutRequest` is sent using either HTTP-Post,
   HTTP-Redirect, or SOAP binding.

4. The SP delivers the `LogoutResponse` to the IdP. When Liferay is configured
   as the SP, the `LogoutResponse` is sent using either HTTP-Post,
   HTTP-Redirect or direct response to SOAP request.

5. The IdP destroys its SSO session. When Liferay is the IdP, once the last SP
   has delivered its `LogoutResponse` or has timed out, the IdP destroys the
   Liferay session, logging out the user.

6. Finally, the IdP sends a `LogoutResponse` to the SP that initiated SLO.

7. The SP terminates its SAML session and proceeds to log out.

## Related Topics [](id=related-topics)

- [Configuring Liferay as an IdP or SP (not yet written)]()
