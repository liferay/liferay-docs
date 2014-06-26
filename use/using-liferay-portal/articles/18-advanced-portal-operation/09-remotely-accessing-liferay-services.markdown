# Remotely Accessing Liferay Services [](id=remotely-accessing-liferay-services-lp-6-2-use-useportal)

Liferay includes a utility called the *Service Builder* which is used to
generate all of the low level code for accessing resources from the portal
database. This utility is further explained in the [*Liferay Developer Guide*](http://www.liferay.com/documentation/liferay-portal/6.2/development) and
in [*Liferay in Action*](http://manning.com/sezov), but it is mentioned here
because of its feature which generates interfaces not only for Java code, but
also for web services and JavaScript. This means that the method calls for
storing and retrieving portal objects are all the same, and are generated in the
same step.

![Figure 18.26: Liferay SOA's first layer of security is its properties files.](../../images/liferay-soa-first-layer.png)

Because the actual method calls for retrieving data are the same regardless of
how one gets access to those methods (i.e., locally or through web services),
Liferay provides a consistent interface for accessing portal data that few other
products can match. The actual interfaces for the various services are covered
in the [*Liferay Developer Guide*](http://www.liferay.com/documentation/liferay-portal/6.2/development) and
in [*Liferay in Action*](http://manning.com/sezov). Before these services can be
used, administrators need to enable users to access these services remotely.

In the default `portal.properties` file, there is a section called **Main
Servlet**. This section defines the security settings for all of the remote
services provided by Liferay. Copy this section and paste it into your custom
`portal-ext.properties` file. Then you can edit the default values to configure
the security settings for the Axis Servlet, the Liferay Tunnel Servlet, the
Spring Remoting Servlet, the JSON Tunnel Servlet, and the WebDAV servlet.

By default, a user connecting from the same machine Liferay is running on can
access remote services so long as that user has the permission to use those
services in Liferay's permissions system. Of course, you are not really "remote"
unless you are accessing services from a different machine. Liferay has two
layers of security when it comes to accessing its services remotely. Without
explicit rights to both layers, a remote exception will be thrown and access to
those services will not be granted.

The first layer of security that a user needs to get through in order to call a
method from the service layer is servlet security. The *Main Servlet* section of
the `portal-ext.properties` file is used to enable or disable access to
Liferay's remote services. In this section of the properties file, there are
properties for each of Liferay's remote services.

You can set each service individually with the security settings that you
require. For example, you may have a batch job which runs on another machine in
your network. This job looks in a particular shared folder on your network and
uploads documents to your site's Documents and Media portlet on a regular basis,
using Liferay's web services. To enable this batch job to get through the first
layer of security, you would modify the `portal-ext.properties` file and put the
IP address of the machine on which the batch job is running in the list for that
particular service. For example, if the batch job uses the Axis web services to
upload the documents, you would enter the IP address of the machine on which the
batch job is running to the `axis.servlet.hosts.allowed` property.  A typical
entry might look like this:

    axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, SERVER_IP

If the machine on which the batch job is running has the IP address
`192.168.100.100`, this configuration will allow that machine to connect to
Liferay's web services and pass in user credentials to be used to upload the
documents.

![Figure 18.27: Liferay SOA's second layer of security is its permissions system.](../../images/liferay-soa-second-layer.png)

The second layer of security is Liferay's security model that it uses for every
object in the portal. The user account that accesses the services remotely must
have the proper permissions to operate on the objects it attempts to access.
Otherwise, a remote exception will be thrown. Portal administrators need to use
Liferay's permissions system to grant access to these resources to the
administrative user account that attempts to operate on them remotely. For
example, say that a Documents and Media folder called *Documents* has been set
up in a site. A role has been created called *Document Uploaders* which has the
rights to add documents to this folder. Your batch job will be accessing
Liferay's web services in order to upload documents into this folder. In order
for this to work, you have to call the web service using a user account to which
the *Document Uploaders* role has been assigned (or that has individual rights
to add documents to the folder). Otherwise, you will be prevented from using
the web service.

To call the web service using credentials, you would use the following URL
syntax:

    http://" + userIdAsString + ":" + password +
    "@<server.com\>:<port\>/tunnel-web/secure/axis/" + serviceName

The user ID is the user's ID from Liferay's database. This can be obtained by
logging in as the user and navigating to the *My Account* portlet in the Control
Panel. The user ID appears on the Details page of the My Account portlet in the
Control Panel.

For example, to get Organization data using a user that has the ID of
*2* with a password of *test*, you would use the following URL:

    http://2:test@localhost:8080/tunnel-web/secure/axis/Portal_OrganizationService

It is important to note here how *Password Policies* (covered in this guide's
chapter on [User
Management](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/management-liferay-portal-6-2-user-guide-16-en)
can be used in combination with this feature. If you are enforcing password
policies on your users (requiring passwords to take a certain form, requiring
users to change their passwords on a periodic basis, etc.), any administrative
user account which accesses Liferay's web services in a batch job will have its
password expire too.

To prevent this from happening, you can add a new password policy which does not
enforce the password expiration and add your administrative user ID to it. Then
your batch job can run as many times as you need it to and the administrative
user account's password will never expire.

In summary, accessing Liferay remotely requires the successful passing
of two security checks:

1.  The IP address must be pre-configured in the server's
    `portal-ext.properties` file.  
2.  The user ID being used must have permission to access the resources it
    attempts to access.

## Accessing Liferay's JSON Web Services

To see which Liferay service methods are registered and available for use via
JSON web services, open your browser to the following address:

    http://localhost:8080/api/jsonws

The page lists the portal's registered and exposed service methods. Get each
method's details by clicking the method name. You can view the full signature of
each method, all its arguments, the exceptions that can be thrown, and its
Javadoc! Using a simple form from within your browser, you can even invoke the
service method for testing purposes.

To list registered services on a plugin (e.g. a custom portlet), don't forget to
use its context path in your URL:

    http://localhost:8080/[plugin-context]/api/jsonws

This lists the JSON Web Service API for the plugin. 

## Accessing Liferay's WSDL

After configuring the security settings properly, your first step in obtaining
access to Liferay's remote SOAP web services is to access the WSDL. If you are
on a browser on the same machine Liferay is running on, you can do this by
accessing the following URL:

    http://localhost:<port number\>/tunnel-web/axis

If, for example, you are running on Tomcat on port 8080, you would specify this
URL:

    http://localhost:8080/tunnel-web/axis

If you are accessing a web service that was created as part of a portlet plugin,
the URL is similar, but uses the context of your application rather than the
tunnel-web servlet. You can get a list of your Service Builder-generated WSDL
documents by using the URL pattern below:

    http://localhost:8080/your-portlet/axis

If you are on a different machine from the Liferay server, you will need to pass
in your user credentials on the URL to access the WSDL:

    http://<user ID\>:<password\>@<server name\>:<port number\>/tunnel-web/axis

In any case, once you successfully browse to this URL, you will see the list of
web services. You can access the WSDL for each service by clicking on the *WSDL*
link next to the name of the service. There are many services; one for each of
the services available from the Liferay API.

Once you click on one of the *WSDL* links, the Web Service Definition Language
document will be displayed. This document can be used to generate client code in
any language that supports it. You can either save the document to your local
machine and then generate the client code that way, or use your tool to trigger
Liferay to generate the document dynamically by using one of the URLs above. For
further information about developing applications that take advantage of
Liferay's remote services, please see the [*Liferay Developer Guide*](http://www.liferay.com/documentation/liferay-portal/6.2/development) or
[*Liferay in Action*](http://manning.com/sezov).
