#  NTLM Single Sign On Authentication [](id=ntlm-single-sign-on-authentication)

NTLM (NT LAN Manager) is a suite of Microsoft protocols that provide
authentication, integrity, and confidentiality for users. Though Microsoft has
adopted Kerberos in modern versions of Windows server, NTLM is still used when
authenticating to a workgroup. @product@ now supports NTLM v2
authentication. NTLM v2 is more secure and has a stronger authentication process
than NTLMv1.

Note that in order to use NTLM SSO, @product@'s portal instance authentication
type must be set to screen name.

+$$$

**Note:** To USE NTLM with @product@, you need to configure your browser.
Consult your browser vendor's documentation for the details.

$$$

NTLM configuration can be applied either at the system scope or at the scope of
a portal instance. To configure the NTLM SSO module at the system scope,
navigate to the Control Panel, click on *Configuration* &rarr; *System Settings, click
on the *Foundation* category, and find the NTLM module. The values configured
there provide the default values for all portal instances. Enter values in the
same format as you would when initializing a Java primitive type with a literal
value.

Property Label | Property Key | Description | Type
---- | ---- | ---- | ----
**Enabled** | `enabled` | Check this box to enable NTLN SSO authentication. Note that NTLM will only work if @product@'s authentication type is set to screen name. | `boolean`
**Domain Controller** | `domainController` | Enter the IP address of your domain controller. This is the server that contains the user accounts you want to use with @product@. | `String`
**Domain Controller Name** | `domainControllerName` | Specify the domain controller NetBIOS name. | `String`
**Domain** | `domain` | Enter the domain / workgroup name | `String`
**Service Account** | `serviceAccount` | You need to create a service account for NTLM. This account will be a computer account, not a user account. | `String`
**Service Password** | `serviceAccount` | Enter the password for the service account. | `String`
**Negotiate Flags** | `negotiateFlags` | Only available at system level. Set according to the client's requested capabilities and the server's ServerCapabilities. See the following link: [http://msdn.microsoft.com/en-us/library/cc717152%28v=PROT.10%29.aspx](http://msdn.microsoft.com/en-us/library/cc717152%28v=PROT.10%29.aspx) | `String`

To override system defaults for a particular portal instance, navigate to the
Control Panel, click on *Configuration* &rarr; *Instance Settings*, click on
*Authentication* and then on *NTLM*.

## Summary [](id=summary)

NTLM authentication is often highly desirable in intranet scenarios where the
IT department has control over what software is running on client devices and
thus can ensure NTLM compatibility. In an Active Directory based network /
domain, it is hard to beat the user experience that NTLM authentication can
provide.

Please note that in order to use NTLM SSO, your @product@ instance authentication
type must be set to screen name. If this is not acceptable for your @product@
implementation, then another SSO solution (such as CAS) can be used as a broker
between your portal and the NTLM authentication process.
