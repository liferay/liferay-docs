# Authenticating with Kerberos

You can use Kerberos to authenticate Microsoft Windows &trade; accounts with
@product@. This is done completely through configuration, by using a combination
of @product@'s LDAP support and a web server that supports the Kerberos
protocol. 

Note that this configuration is preferred above 
[NTLM](/discover/deployment/-/knowledge_base/7-1/ntlm-single-sign-on-authentication)
because security vulnerabilities persist. 

While it's beyond the scope of this article to explain how to set up Kerberos
and Active Directory on a Windows &trade; server, we can describe the minimum
prerequisites for setting up Liferay authentication: 

1.  A Windows &trade; server with Active Directory and DNS set up so the AD
    server and @product@ can resolve each other on the network. In other words,
    they must be able to ping each other *by name*. 

2.  An administrative user in AD @product@ can use to bind to AD. 

3.  A Kerberos keytab file exported via the `ktpass` command containing the
    cryptographic information the @product@ server needs to bind to AD. 

4.  A web server in front of @product@ that supports Kerberos, such as Apache,
    NGNIX, or IIS. The web server must also support injecting a header to be used 
    as a token in the @product@ configuration (see below). 
