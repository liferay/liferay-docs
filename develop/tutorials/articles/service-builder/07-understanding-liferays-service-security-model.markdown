## Service Security Layers

Liferay's remote services are secured by default. They sit behind a layer of
security that allows only local connections. If you want to invoke Liferay
services from a remote client, you must take deliberate steps to enable such
access. Liferay's core web services require user authentication and
verification. This tutorial explains these processes. Lastly, regardless of
whether the remote service is called from the same machine or via a web service,
Liferay's standard security model performs its function. The user invoking a web
service must have the proper permissions (as defined by Liferay's permissions
system) in order for the remote service invocation to complete successfully. 

The first layer of security that a client encounters when calling a remote
service is called *invoker IP filtering*. Imagine that you have have a batch job
that runs on another machine in your network. This job polls a shared folder on
your network and uploads documents to your Liferay site's *Documents and Media*
portlet on a regular basis, using Liferay's web services. To get your batch job
through the IP filter, your portal administrator must grant web service access
to the machine on which the batch job is running. For example, if your batch job
uses Liferay's SOAP web services to upload the documents, your portal
administrator must add the IP address of the machine on which the batch job is
running to the `axis.servlet.hosts.allowed` property. A typical entry might look
like this:

    axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, [SERVER_IP]

If the IP address of the machine on which the batch job is running is listed
as an authorized host for the service, it's allowed to connect to Liferay's web
services, pass in the appropriate user credentials, and upload the documents. 

+$$$

**Note:** The `portal.properties` file resides on the portal host machine and is
controlled by the portal administrator. Portal administrators can configure
security settings for the Axis Servlet, the Liferay Tunnel Servlet, the Spring
Remoting Servlet, the JSON Servlet, the JSON Web Service Servlet, and the WebDAV
Servlet. The `portal.properties` file (online version is available at
[http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html))
describes these properties. 

$$$

Next, if you're invoking the remote service via web services (e.g., JSON WS, old
JSON, Axis, REST, etc.), a two step process of authentication and authentication
verification takes place. Each call to a Liferay portal web services must be
accompanied by a user authentication token. It's up to the web service caller to
produce the token (e.g., through Liferay's utilities or through some third-party
software). Liferay verifies that there is a Liferay user that matches the token.
If the credentials are invalid, the web service invocation is aborted.
Otherwise, processing enters into Liferay's user permission layer. 

Liferay's user permission layer is the last Liferay security layer triggered
when services are invoked remotely. It's used for every object in the portal,
regardless of whether a local service or a remote service is involved. The user
ID associated with a web service invocation must possess the proper permission
to operate on the objects it's trying to access. A remote exception is thrown if
the user ID does not possess the required permissions. A portal administrator
can grant users access to these resources. For example, suppose you created a
Documents and Media Library folder called *Documents* in a site, created a role
called *Document Uploaders*, and granted this role the rights to add documents
to your new folder. If your batch job accesses Liferay's web services to upload
documents into the folder, you must call the web service using a user ID of a
member of this role (or using the user ID of a user with individual rights to
add documents to this folder, such as a portal administrator). If you don't,
Liferay denies you access to the web service. 

With remote services, you can specify the user credentials using HTTP basic
authentication. Since those credentials are passed over the network unencrypted,
we recommend using HTTPS whenever accessing these services on an untrusted
network. Most HTTP clients let you specify the basic authentication credentials
in the URL--this is very handy for testing.

Use the following syntax to call the AXIS web service using credentials. Make
sure to remove the line escape character `\` when entering your URL:

    http://" + screenNameOrUserIdAsString + ":" + password + "@[server.com]:\
    [port]/api/axis/" + serviceName

The `screenNameOrUserIdAsString` should either be the user's screen name or the
user's ID from the Liferay database. The portal's authentication type setting
determines which one to use; we discuss this in more detail below. A user can
find his or her ID by logging in as the user and accessing *My Account* from the
Dockbar. On this interface, the user ID appears below the user's profile picture
and above the birthday field.

Let's pretend that your portal's authentication type is set to be by *user ID*
and that there's a user whose ID is `2` and whose password is `test`. You can
access Liferay's remote Organization service with the following URL: 

    http://2:test@localhost:8080/api/axis/Portal_OrganizationService

As mentioned above, the authentication type specified for your Liferay Portal
instance dictates the authentication type you'll use to access your web service.
The portal administrator can set the portal's authentication type to any of the
following: 

- *email address*
- *screen name*
- *user ID*

+$$$

**Important:** In order for authentication to work for remote service calls, the
portal authentication type must be set either to *screen name* or *user ID*.
Authentication using the *email address* authentication type is not supported
for remote service calls. 

$$$

You can set the authentication type via the Control Panel or via the
`portal-ext.properties` file. To set the portal authentication type via the
Control Panel, navigate to the Control Panel, click on *Portal Settings*, and
then on *Authentication*. Under *How do users authenticate?*, make a selection.
To set the portal authentication type via properties file, add the following
lines to your Liferay instance's `portal-ext.properties` file and uncomment the
line for the appropriate authentication type:

    #company.security.auth.type=emailAddress
    #company.security.auth.type=screenName
    #company.security.auth.type=userId

Your Liferay Portal password policies (see the
[Password Policies](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/roles-and-permissions#password-policies)
documentation) should be reviewed, since they'll be enforced on your
administrative user as well. If the portal is enforcing password policies on its
users (e.g., requiring them to change their passwords on a periodic basis), an
administrative user accessing Liferay's web services in a batch job will have
its password expire too.

To prevent a password from expiring, a portal administrator can add a new
password policy that doesn't enforce password expiration and can add a specific
administrative user to it. Then your batch job can run as many times as you need
it to, without your administrative user's password expiring. 

To summarize, accessing Liferay remotely requires you to pass the following
layers of security checks:

- *IP permission layer*: The IP address must be pre-configured in the server's
  portal properties. 
- *Authentication/verification layer (web services only)*: Liferay verifies that
  the caller's authorization token can be associated with a portal user.  
- *User permission layer*: The user needs permission to access the related
  resources. 

If you'd like to develop client applications that need to invoke Liferay's web
services, make sure that your Liferay instance's web service security settings
have been configured to allow access.
