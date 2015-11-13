# Liferay SAML in Pictures

This article provides diagrams of the SAML actions supported by Liferay along
with descriptions of each step. For information about configuring Liferay as a
SAML Identity Provider or Service Provider, please see the [not yet written]()
article.

## Identity Provider Initiated SSO

![Figure x: Identity Provider Initiated SSO](../../images/idp-initiated-sso.png)

1. A IdP initiated SSO is a SSO request sent directly to the IdP by the user's
   browser. For situations where Liferay is configured to serve as the IdP, the
   IdP initiated SSO URL
    - Must specify the path as `/c/portal/saml/sso`.
    - Must include the `entityId` parameter which is the identifier to a
      previously configured Service Provider Connection (SPC).
    - May include a `RelayState` parameter which contains a URL encoded value
      to which the user will be redirected upon successful authentication. This
      URL should point to a location on the desired SPC.
    - For other IdPs (e.g. Siteminder, ADFS, etc.), please consult the vendor
      appropriate documentation for how to construct IdP initiated SSO URLs.

2. If the IdP determines that the user is not authenticated, it prompts the
   user with the appropriate login page. If Liferay Portal is the IdP, the
   portal prompts the user with its login screen.

3. Upon successful authentication, the IdP constructs a SAML Response that
   contains appropriate attribute statements as configured in the designated
   SPC.

4. The IdP sends the response to the Assertion Consumer Service URL as
   configured in the SPC. (Note: the Assertion Consumer Service URL is
   generally imported as part of the SAML Metadata XML provided by the SP.) The
   SAML Response may be sent via HTTP-POST or HTTP-Redirect. This is again
   specified in the SAML Metadata XML. HTTP-POST is the preferred method to
   reduce the risk that the URL is too long for a browser to handle. If using
   HTTP-POST, the request contains two parameters:
    - the `SAMLResponse`
    - the `RelayState`

5. The SP validates and processes the SAML Response. Liferay's SAML solution
   requires SAMLResponse messages to be signed. This signature process ensures
   proper identification for the IdP and prevents potential SAML Response
   spoofing.
    - If one Liferay Portal is serving as the IdP and another is serving as
      the SP, make sure the proper certificates are used. The SAML Metadata
      XML imported into the Liferay SP should contain the proper
      certificate of the Liferay IdP.
    - If Liferay is serving as the IdP and another application as the SP,
      make sure to export the certificate from the Liferay IdP and import
      it into the SP's certificate store.

6. The User is redirected either to the `RelayState` (if specified) or home
   page of the SP (if no `RelayState` is provided).

## Identity Provider Initiated SLO

![Figure x: Identity Provider Initiated SLO](../../images/idp-initiated-slo.png)

1. The user requests to log out from the portal by requesting the URL
   `/c/portal/logout`. When SAML is enabled and the user has also signed in to
   any configured SP, the SAML plugin takes over the logout process and
   displays a screen with all of the signed on services. The single logout
   screen displays the status of each SP logout and shows any SPs that can't be
   logged out of (some SPs might be down or simply don't support SLO).

2. The IdP sends a SAML `LogoutRequest` to the SP using either HTTP-Post,
   HTTP-Redirect, or SOAP binding.

3. The SP delivers the `LogoutResponse` to the IdP using either HTTP-Post,
   HTTP-Redirect, or direct response to SOAP request.

4. The IdP sends a SAML `LogoutRequest` to the SP using either HTTP-Post,
   HTTP-Redirect, or SOAP binding.

5. The SP delivers the `LogoutResponse` to the IdP using either HTTP-Post,
   HTTP-Redirect, or direct response to SOAP request. Once the last SP has
   delivered its `LogoutResponse` or has timed out, the IdP proceeds to log out
   the user from Liferay on the IdP.

## Service Provider Initiated SSO

![Figure x: Service Provider Initiated SSO](../../images/sp-initiated-sso.png)

1. With Liferay configured as a SAML SP, SSO can be initiated either by
   requesting the URL `/c/portal/login` or by requesting a protected resource
   that requires authentication (e.g., a document that is not viewable by the
   Guest role). If the user requests a protected resource, its URL is recorded
   as in the `RelayState` parameter. It's also possible to set the `RelayState`
   parameter by requesting `/c/portal/login` with the parameter `redirect` set
   to a particular URL.

2. Liferay looks up the configured SAML Identity Provider Connection and sends
   a SAML `AuthnRequest` to the IdP's SSO Service URL.

3. If the user doesn't already have an active session on the IdP, then the user
   is asked to log in to the portal. This is normally handled by the Liferay
   Login portlet. This can also happen when the `AuthnRequest` has `forceAuthn`
   set to `true`.

4. Once the `AuthnRequest` is verified and the user is signed in, a SAML
   response is constructed with any of the attribute statements configured on
   the Service Provider Connection. Then the SAML response is sent to the
   Assertion Consumer Service URL configured on the SAML metadata XML of the
   SP. Then the SP verifies the response and the contained assertion. Liferay
   requires the sender to be authenticated. This is done via whole message
   signature from the issuing IdP.

5. The Response can be sent via HTTP-Post binding or HTTP-redirect binding. The
   IdP automatically makes this choice based on the SP metadata. HTTP-Post
   binding is preferred and used when available. HTTP-Redirect binding often
   doesn't work because the signature and included assertions make the URL too
   long for the browser to handle.

6. The user is redirected to the protected resource requested or the last page
   the user accessed before initiating SSO.

![Figure x: Service Provider Initiated SLO](../../images/idp-initiated-slo.png)

1. The SLO is initiated by accessing `/c/portal/logout`.

2. A SAML `LogoutRequest` is sent to the Single Logout Service URL of the IdP
   configured in the SAML metadata for the Identity Provider Connection. This
   is sent either using HTTP-POST or HTTP-Redirect binding. If user has logged
   on to other SPs, then the user is presented with a single logout screen.
   Otherwise, the SAML session is terminated and the process jumps to step 5.
   The single logout screen displays the status of each SP logout and shows any
   SPs that can't be logged out of (they could be down or simply don't support
   SLO).

3. The IdP sends a SAML `LogoutRequest` to the SP using either HTTP-Post,
   HTTP-Redirect, or SOAP binding.

4. The SP delivers the `LogoutResponse` to the IdP using either HTTP-Post,
   HTTP-Redirect, or direct response to SOAP request.

5. Once the last SP has delivered its `LogoutResponse` or has timed out, the
   IdP proceeds to destroy the session on the IdP, logging out the user.

6. Finally, the IdP sends a `LogoutResponse` to the SP that initiated SLO.

7. The SP terminates its SAML session and proceeds to log out of Liferay.

## Related Topics

- [Configuring Liferay as an IdP or SP (not yet written)]()
