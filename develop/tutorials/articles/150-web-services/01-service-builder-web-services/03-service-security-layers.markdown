# Service Security Layers [](id=service-security-layers)

Liferay's remote services are secured by default, because they only allow local
connections. Enabling remote access requires peeling away several layers of
security, first by IP address, then by user authentication and verification.
Users invoking web services must have the proper permissions (as defined by
Liferay's permissions system) for the remote service invocation to complete
successfully. This tutorial explains these processes. 

The first layer of security is called *invoker IP filtering*. Imagine that you
have have a batch job that runs on another machine in your network. This job
polls a shared folder on your network and uses Liferay's web services to upload
documents to your Liferay Site's *Documents and Media* app on a regular basis.
To get your batch job through the IP filter, you must grant web service access
to the machine where the batch job runs. For example, if your batch job uses
Liferay's SOAP web services to upload the documents, you must add the IP address
of the machine where the batch job runs to the `axis.servlet.hosts.allowed`
property. A typical entry might look like this:

    axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, [SERVER_IP]

If the IP address of the machine where the batch job runs is listed as an
authorized host for the service, the machine can connect to Liferay's web
services, pass in the appropriate user credentials, and upload the documents. 

+$$$

**Note:** The `portal-ext.properties` file resides on the Liferay server and is
controlled by its administrator. Administrators can configure security settings
for the Axis Servlet, the Liferay Tunnel Servlet, the Spring Remoting Servlet,
the JSON Servlet, the JSON Web Service Servlet, and the WebDAV Servlet. The
[`portal.properties` file](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)
describes these properties. 

$$$

Next, if you invoke the remote service via web services, a two step process of
authentication and authentication verification takes place. Each call to
a Liferay web service must be accompanied by a user authentication token:
`p_auth`. It's up to the web service caller to produce the token (e.g., through
Liferay's utilities or through some third-party software). Liferay verifies that
there is a Liferay user matching the token. If the credentials are invalid, the
web service invocation is aborted. Otherwise, processing enters Liferay's user
permission layer. 

The user permission layer is the last security layer triggered for remote
services. It's used for every object, regardless of whether a local or remote
service is involved. The user ID associated with a web service invocation must
have permission to operate on the objects it's trying to access. A remote
exception is thrown if the user ID doesn't have permission. An instance
administrator can grant users access to these resources. 

For example, suppose you created a Documents and Media Library folder called
*Documents* in a Site, created a Role called *Document Uploaders*, and granted
this Role the rights to add documents to your new folder. If your batch job
accesses Liferay's web services to upload documents into the folder, you must
call the web service using a user ID with this Role (or using the user ID of
a user with individual rights to add documents to this folder, such as an
instance administrator). If you don't, Liferay denies you access to the web
service. 

When invoking remote services from a non-browser client, you can specify the
user credentials using HTTP basic authentication. For security reasons, you must
be logged in and supply a valid `p_auth` authentication token to invoke
a web service via a browser. Since you should never pass credentials over the
network unencrypted, use HTTPS whenever accessing Liferay services. Most HTTP
clients (like [cURL](http://curl.haxx.se/)) let you specify the basic
authentication credentials in the URL: this is very handy for testing.

+$$$

**Important:** To invoke a Liferay web service via your browser, you must be
logged in to Liferay. You must also supply an authentication token (the `p_auth`
parameter). If you navigate to your Liferay instance's JSON web services API 
page (
[localhost:8080/api/jsonws](localhost:8080/api/jsonws), by default) and click
on a remote service method, you'll see the `p_auth` token for your browser
session. This token is supplied automatically when you invoke a Liferay web
service via the JSON web services API page or via JavaScript using
`Liferay.Service(...)`.

$$$

Use the following syntax to call the Axis web service using credentials.

    http://" + emailAddressOrScreenNameOrUserIdAsString + ":" + password + "@[server.com]:\
    [port]/api/axis/" + serviceName

The `emailAddressOrScreenNameOrUserIdAsString` should be the user's email
address, screen name, or user ID. The Liferay instance's authentication type
setting determines which one to use. Authentication by email address is the
default. A user can find his or her user ID by logging in as the user and
accessing *My Account* &rarr; *Account Settings* from the User Menu. On this
interface, the user ID appears below the user's profile picture and above the
birthday field. 

Suppose that you've defined authentication by user ID, and that there's a user
with an ID of `2` and a password of `test`. You can access Liferay's remote
Organization service with the following URL: 

    http://2:test@localhost:8080/api/axis/Portal_OrganizationService

Note that if an email address appears in the URL path, it must be URL-encoded 
(e.g. `test@liferay.com` becomes `test%40liferay.com`). 

Suppose that you've now defined authentication by email address. To call the
same web service for the same user, change the URL to this: 

    http://test%40liferay.com:test@localhost:8080/api/axis/Portal_OrganizationService

As mentioned, the authentication type you've defined dictates the authentication
type you'll use to access your web service. The authentication type can be set
to email address, screen name, or user ID. 

You can set the authentication type via the Control Panel or via the
`portal-ext.properties` file. To set the authentication type via the Control 
Panel, navigate to  *Control Panel* &rarr; *Configuration* &rarr; *Instance 
Settings*, and select the *General* tab under *Authentication*. Choose your 
authentication type in the *How do users authenticate?* menu. To set the 
authentication type via properties file, add the following lines to your Liferay 
instance's `portal-ext.properties` file and uncomment the line for the 
appropriate authentication type: 

    #company.security.auth.type=emailAddress
    #company.security.auth.type=screenName
    #company.security.auth.type=userId

You should also review your password policies, since they're enforced on your
administrative user. If a password policy requires them to change their
passwords on a periodic basis, the password for an administrative user accessing
web services in a batch job expires too. 

To prevent a password from expiring, add a new password policy that doesn't
enforce password expiration, and then add your administrative user to the
policy. Then your batch job can run as many times as you need it to, without the
password expiring. 

To summarize, accessing Liferay remotely requires you to pass the following
layers of security checks:

- *IP permission layer*: The IP address must be pre-configured in the server's
  portal properties. 
- *Authentication/verification layer (web services only)*: Liferay verifies that
  the caller's authorization token can be associated with an instance user.
- *User permission layer*: The user needs permission to access the related
  resources. 

If you want to develop client applications that can invoke Liferay's web
services, make sure that your Liferay instance's web service security settings
have been configured to allow access. 

**Related Topics**

[Configuring JSON Web Services](/develop/tutorials/-/knowledge_base/7-1/portal-configuration-of-json-web-services)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-1/invoking-remote-services)

[Invoking JSON Web Services](/develop/tutorials/-/knowledge_base/7-1/invoking-json-web-services)

[JSON Web Services Invoker](/develop/tutorials/-/knowledge_base/7-1/json-web-services-invoker)

[JSON Web Services Invocation Examples](/develop/tutorials/-/knowledge_base/7-1/json-web-services-invocation-examples)

[SOAP Web Services](/develop/tutorials/-/knowledge_base/7-1/soap-web-services)
