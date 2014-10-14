# Service Security Layers [](id=service-security-layers)

Liferay's remote services sit behind a layer of security that by default allows
only local connections. Access to the remote APIs must be enabled as a separate
step in order to call them from a remote machine. Behind this layer of security
is Liferay's standard security model: even if on the same machine, a user must
have the proper permissions in Liferay's permissions system to access remote
services. 

The first layer of security a client encounters when calling a remote service
is called *invoker IP filtering*. Imagine you have have a batch job that runs
on another machine in your network. This job polls a shared folder on your
network and uploads documents to your site's *Documents and Media* portlet on a
regular basis, using Liferay's web services. To get your batch job through the
IP filter, the portal administrator has to allow the machine on which the batch
job is running access to Liferay's remote service. For example, if your batch
job uses the SOAP web services to upload the documents, the portal administrator
must add the IP address of the machine on which the batch job is running to the
`axis.servlet.hosts.allowed` property.  A typical entry might look like this:

    axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, SERVER_IP

If the IP address of the machine on which the batch job is running is listed
as an authorized host for the service, it's allowed to connect to Liferay's web
services, pass in the appropriate user credentials, and upload the documents. 

![Figure 11.1:  Liferay SOA's first layer of security](../../images/soa-security-layer-1.png)

+$$$

**Note:** The `portal.properties` file resides on the portal host machine and is
controlled by the portal administrator. Portal administrators can configure
security settings for the Axis Servlet, the Liferay Tunnel Servlet, the Spring
Remoting Servlet, the JSON Servlet, the JSON Web Service Servlet, and the WebDAV
Servlet. The
[Properties Reference](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/configuring-liferay-s-properti-1)
chapter of *Using Liferay Portal* describes these properties. 

$$$

Liferay's security model is the second layer of security that's triggered
when services are remotely invoked, and it's used for every object in the
portal, whether accessing it locally or remotely. The user ID accessing the
services remotely must have the proper permission to operate on the objects it's
trying to access. A remote exception is thrown if the user ID isn't permitted.
The Portal Administrator grants users access to these resources. For example,
imagine you created a Documents and Media Library folder called `Documents` in
a site and created a role called *Document Uploaders* that has the rights to
add documents to your new folder. If your batch job accesses Liferay's web
services to upload documents into the folder, you have to call the web service
using a user ID of a member of this group (or the user ID of a user with
individual rights to add documents to this folder). If you don't, Liferay denies
you access to the Web Service. 

![Figure 11.2: Liferay SOA's second layer of security](../../images/soa-security-layer-2.png)

With remote services, you can specify the user credentials using HTTP Basic
authentication. Since those credentials are specified unencrypted; it's
recommended to use HTTPS whenever accessing these services in an untrusted
network. Most HTTP clients let you specify the Basic authentication credentials
in the URL--this is very handy for testing.

You'd use the following syntax to call the AXIS web service using credentials:

    http://" + userIdAsString + ":" + password + "@[server.com]:[port]/api/secure/axis/" + serviceName

The user ID is the user's ID from the Liferay database. This may be obtained by
logging in as the user and navigating to the *My Account* page of the control
panel. On this page, the user ID appears below the user's profile picture and
above the birthday field.

Let's pretend there's a user whose ID is `2` and whose password is `test`. You
can get Organization data with the following URL: 

    http://2:test@localhost:8080/api/secure/axis/Portal_OrganizationService

+$$$

**Note:** Older Liferay versions let you access services with
`http://localhost:8080/tunnel-web/axis`. This path has changed in Liferay 6.1;
if you enter it, you'll be redirected to the new one. 

$$$

The authentication type specified for your Liferay Portal instance dictates the
authentication type you'll use to access your web service. The portal
administrator can set the portal's authentication type to any of the following: 

- *screen name*
- *user ID*
- *email address*

Your Liferay Portal Password Policies (see chapter
[User Management](/discover/portal/-/knowledge_base/6-1/user-management)
of *Using Liferay Portal*) should be reviewed, since they'll be enforced on your
administrative ID as well. If the portal is enforcing password policies on its
users (e.g., requiring them to change their passwords on a periodic basis), an
administrative ID accessing Liferay's web services in a batch job will have its
password expire too.

To prevent a password from expiring, the portal administrator can add a new
password policy that doesn't enforce password expiration and add your
administrative user ID to it. Then your batch job can run as many times as you
need it to, without your administrative ID's password expiring. 

To summarize, accessing Liferay remotely requires you to pass two layers of
security checks:

- *First layer*: The IP address must be pre-configured in the server's portal
  properties. 
- *Second layer*: The user needs permission to access the related resources. 

Next let's talk about Liferay's SOAP web services. 
