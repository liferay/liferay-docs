# Securing @product@ [](id=securing-product)

Liferay follows the OWASP Top 10 (2013) and CWE/SANS Top 25 lists to ensure
@product@ is as secure as possible. Following these recommendations protects the
product against known kinds of attacks and security vulnerabilities. For
example, @product@'s persistence layer is generated and maintained by the
Service Builder framework which prevents SQL Injection using Hibernate and
parameter based queries.

To prevent Cross Site Scripting (XSS), user-submitted values are escaped on
output. To support integration features, @product@ doesn't encode input. Data is
stored in the original form as submitted by the user. @product@ includes built-in
protection against CSRF attacks, Local File Inclusion, Open Redirects,
Uploading and serving files of dangerous types, Content Sniffing, Clickjacking,
Path Traversal, and many other common attacks.

To stay on top, @product@ also contains fixes for state-of-the-art attacks and
techniques to improve product security. For example, @product@ uses PBKDF2 to
store passwords. @product@ also contains mitigation for Quadratic Blowup XXE
attack, Rosetta Flash vulnerability, Reflected File Download, and other kinds
of attacks.

This section of tutorials shows you how to configure various security and login
features, such as LDAP, single sign-on, Service Access Policies, and more. What
follows is an overview of what's available. 

## Authentication Overview [](id=authentication-overview)

@product@ user authentication can take place using any of a variety of prepared
solutions:

- Form authentication using the Sign In Portlet with extensible adapters for
  checking and storing credentials (portal database, LDAP)
- Single-Sign-On (SSO) solutions - NTLM, CAS, SiteMinder, OpenSSO, OpenID,
  Facebook
- [SAML plugin ](https://www.liferay.com/marketplace/-/mp/application/15188711)
- JAAS integration with application server

Note: Although Liferay's SSO solutions are incompatible with WebDAV, they can
be used with Liferay Sync. See the
[Publishing Files](/discover/portal/-/knowledge_base/7-1/publishing-files) 
article for more information on WebDAV and Liferay Sync.

You can authenticate and authorize apps remotely using the `AuthVerifier` layer:

- Password based HTTP Basic + Digest authentication
- Token based OAuth plugin
- Portal session based solution for JavaScript applications

Both user authentication and remote application authentication are
[extensible](/develop/tutorials/-/knowledge_base/7-1/authentication-pipelines).
Developers can create custom Login portlets and plugins, extend the default
Login portlet `auth.pipeline`, create `AutoLogin` extensions for SSO, or create
custom `AuthVerifier` implementations.

## Authorization and Permission Checking [](id=authorization-and-permission-checking)

There are several adjustable authorization layers in place to prevent
unauthorized or unsecured access to data:

- Remote IP and HTTPS transport check to limit access to @product@'s Java
  servlets
- Extensible Access Control Policies layer to perform any portal service
  related authorization check
- Extensible role-based permission framework for almost any portal entity or
  data (stored in the portal database or elsewhere)
- Portlet Container security checks to control portlet access
- Remote IP check for portal remote API authentication methods
- Service Access Policies to control access to portal remote API

## Additional Security Features [](id=additional-security-features)

Users can be assigned to sites, teams, user groups, or organizations. Custom
roles can be created, permissions can be assigned to those roles, and those
roles can be applied to users. Roles are scoped to apply only with a specific
context like a site, an organization, or globally. See the 
[Roles and Permissions (not yet written)]() documentation for more information.

+$$$

Note: @product@ relies on the application server for sanitizing CRLF in
HTTP headers. You should, therefore, make sure this is configured properly in
your application server, or you may experience false positives in reports from
automatic security verification software such as Veracode. There is one
exception to this for Resin, which does not have support for this feature. In
this case, @product@ sanitizes HTTP headers. 

$$$

There are additional security plugins available from 
[Liferay Marketplace](https://www.liferay.com/marketplace). 
For example, you can find an Audit plugin for tracking user actions or an
AntiSamy plugin for clearing HTML from XSS vectors.

There are many ways to fine-tune or disable various security features. Here
are a few examples of these kinds of configuration actions:

- Disable the Sign In portlet's *Create Account* link
- Configure @product@'s HTTPS web server address
- Configure the list of allowed servers to which users can be redirected
- Configure the list of portlets that can be accessed from any page
- Configure the file types allowed to be uploaded and downloaded

## Secure Configuration and Run Recommendations [](id=secure-configuration-and-run-recommendations)

@product@ is built using the "secure by default" concept in mind. It's not
recommended to disable built-in protections or to allow all values in security
white-lists. Such acts may lead to security misconfiguration and an insecure
deployment.

For more information about securing a @product@ installation, please see 
[our security statement](https://www.liferay.com/security), 
[the community security team](https://dev.liferay.com/web/community-security-team), 
and the resources listed on those pages.

Also, customers are advised to deploy security patches as described on the
[customer portal](https://www.liferay.com/group/customer/products/portal/security-vulnerability).

For community and CE deployments, the stay secure by always using the latest
community version, which contains all previous security patches. Until a new
version is released, the Community Security Team issues patches for the latest
CE version via the
[community security team](https://dev.liferay.com/web/community-security-team)
page.
