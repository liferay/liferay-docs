# Liferay APIs [](id=liferay-ap-1)

An *Application Programing Interface* (API) is a protocol that, when invoked,
performs an action or set of actions. You can invoke an API from your own code
directly through a Java invocation, or through web services. This chapter
provides an overview of several essential Liferay APIs available to you for use
in developing your Liferay Portal. 

Liferay comes with a host of APIs that provide powerful portal and portlet
services. The following table shows you the entities for which services are
available:

 Portal Services      | Portlet Services              |
--------------------- | ----------------------------- |
 *User*               | *Documents and Media Library* |
 *Organization*       | *Blogs*                       |
 *UserGroup*          | *Wikis*                       |
 *Company*            | *Assets*                      |
 *Role*               | *Social Activity*             |
 *Permission*         |                               |
 *ResourcePermission* |                               |
 *Layout*             |                               |
---

The ability to call Liferay APIs with a Java method or web service invocation
gives you the flexibility to implement client code in Java or any language
(even a scripting language such as PHP) that supports web service invocations.
The APIs can be called from within portlet or non-portlet code. You can make
calls to the APIs *locally* from within the portal or *remotely* outside of the
portal's *Java virtual machine* (*JVM*).

This chapter covers the following topics: 

- Finding Services
- Invoking the API Locally
- Invoking the API Remotely
- Service Security Layers
- SOAP Web Services
- JSON Web Services
- Service Context
- Using Message Bus
- Device Detection
- Liferay's Deprecation Policy

You can find Liferay's services by searching for them in the Javadocs:
[http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/).
Below we'll show you how to search for portal services and portlet services.

Let's start by finding a portal service. 

### Finding Portal Services [](id=liferay-portal-6-1-dev-guide-10-en-finding-portal-services)

Liferay's Javadocs are easy to browse and well-organized. Here's how you find
the *Organization* services: 

1. In your browser, open up the Javadocs:
   [http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/) 

2. Under *Portal Services*, click the link for the `com.liferay.portal.service`
   package, since the services for the Organization entity belong to the
   *Portal* scope. 

3. Find and click on the `-ServiceUtil` class (in this case
   `OrganizationLocalServiceUtil`) in the class summary table or the list of
   classes.

It's just that easy!

Similarly, if you want to search for one of Liferay's built-in portlet services,
no problem. But, when looking up the package, instead of clicking on the link
for the service package of the *portal*, click on the link for the service
package of the *portlet*. The portlet service packages use the naming convention
`com.liferay.portlet.[portlet-name].service`, where `[portlet-name]` is replaced

Now you're ready to invoke Liferay services.

## Invoking the API Locally [](id=invoking-the-api-locally)

Each service provides a local interface to clients running in the same JVM as
Liferay Portal. There are two ways to invoke a service API's methods: 

- Spring injection: If your application uses Spring and has access to the portal
context. 

- `-ServiceUtil` classes: These classes mask the complexity of service
implementations. This is a good option if you're not familiar with Spring. 

Let's invoke a service using its `-ServiceUtil` class. The following JSP code
snippet demonstrates how to get a list of the most recent bloggers from an
organization. 

    <%@ page import="com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil" %>
    <%@ page import="com.liferay.portlet.blogs.util.comparator.StatsUserLastPostDateComparator" %>
    ...
    <%@
    List statsUsers = BlogsStatsUserLocalServiceUtil.getOrganizationStatsUsers(
        organizationId, 0, max, new StatsUserLastPostDateComparator());
    %>

This JSP code invokes the static method `getOrganizationStatsUsers()` from the
`-LocalServiceUtil` class `BlogsStatsUserLocalServiceUtil`. 

---

![note](../../images/tip-pen-paper.png) **Note:** Permission checks are not
performed when you invoke services locally (i.e., from the same JVM that's
Liferay Portal runs on). To ensure permission checks are performed, use the
remote variant of the API, even from a local context. 

---

<!-- I don't believe the above tip is true anymore. If the -ServiceUtil class
uses the filterFindBy implementation, permission checks are performed. -Rich --> 

Next, find out how you can invoke Liferay's service APIs remotely. 

## Invoking the API Remotely [](id=invoking-the-api-remotely)

*Remote* clients run outside of the portal JVM or on a remote machine, but they
can still access Liferay's service APIs. The main benefit of remotely accessing
service APIs is that security checks are performed. Unless you want to avoid
permission checking, develop your client (even if it's local) so it triggers the
front-end security layer.

<!-- We should not be making this argument. The developer calling the service
actually has no idea if the implementation of the service checks permissions or
not (unless, of course, he/she goes and looks at the source code). There could
be a permission check in the service, and we automatically generate one if the
service uses the filterFindBy method from Service Builder. 

There are benefits and drawbacks to using the remote services. Benefits:
services can be called from outside the portal; services can be invoked through
JavaScript; remote services can reply with JSON objects. Drawbacks: remote
services are harder to call because there's no context; there's more overhead
(i.e., memory, processing) in invoking a remote service; remote server
permission has to be configured ahead of time to prevent just anyone invoking a
remote service. -Rich -->

Liferay's API follows a Service Oriented Architecture
[(SOA)](http://en.wikipedia.org/wiki/Service-oriented_architecture). The API
supports Java invocation and a variety of protocols including SOAP and JSON over
HTTP. A limited set of *RESTful* web services, based on the AtomPub protocol,
are also supported--see the [Portal Atom Collections](http://www.liferay.com/community/wiki/-/wiki/Main/Portal+Atom+Collections)
wiki by Igor Spasi&#263; for more details. You can also use the API through
Remote Procedure Calls
([RPC](http://en.wikipedia.org/wiki/Remote_procedure_call)). You have many good
options for leveraging Liferay's API. 

Let's step back now and discuss the security layers of Liferay's *service
oriented* architecture and how you can configure them. 

## Service Security Layers [](id=service-security-layers)

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

---

![note](../../images/tip-pen-paper.png) **Note:** The `portal.properties` file
resides on the portal host machine and is controlled by the portal
administrator. Portal administrators can configure security settings for the
Axis Servlet, the Liferay Tunnel Servlet, the Spring Remoting Servlet, the JSON
Servlet, the JSON Web Service Servlet, and the WebDAV Servlet. The [Properties
Reference](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/configuring-liferay-s-properti-1)
chapter of *Using Liferay Portal* describes these properties. 

---

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

---

![note](../../images/tip-pen-paper.png) **Note:** Older Liferay versions let you
access services with `http://localhost:8080/tunnel-web/axis`. This path has
changed in Liferay 6.1; if you enter it, you'll be redirected to the new one. 

---

The authentication type specified for your Liferay Portal instance dictates the
authentication type you'll use to access your web service. The portal
administrator can set the portal's authentication type to any of the following: 

- *screen name*
- *user ID*
- *email address*

Your Liferay Portal Password Policies (see chapter
[Management](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/administering-liferay-port-1)
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

## SOAP Web Services [](id=soap-web-services)

You can access Liferay's services via *Simple Object Access Protocol* (*SOAP*)
over HTTP. The *packaging* protocol is SOAP and the *transport* protocol is
HTTP.

As an example, let's look at the SOAP web service classes for Liferay's
`Company`, `User`, and `UserGroup` portal services to execute the following:

1. List each UserGroup to which user *test* belongs. 

2. Add a new UserGroup named *MyGroup*. 

3. Add user *test* to the UserGroup. 

We'll use these SOAP related classes: 

    import com.liferay.portal.model.CompanySoap;
    import com.liferay.portal.model.UserGroupSoap;
    import com.liferay.portal.service.http.CompanyServiceSoap;
    import com.liferay.portal.service.http.CompanyServiceSoapServiceLocator;
    import com.liferay.portal.service.http.UserGroupServiceSoap;
    import com.liferay.portal.service.http.UserGroupServiceSoapServiceLocator;
    import com.liferay.portal.service.http.UserServiceSoap;
    import com.liferay.portal.service.http.UserServiceSoapServiceLocator;

Can you see the naming convention for SOAP related classes? The classes above
all have suffixes `-ServiceSoapServiceLocator`, `-ServiceSoap`, and `-Soap`. The
`-ServiceSoapServiceLocator` class *finds* the `-ServiceSoap` by means of the
service's URL you provide. The `-ServiceSoap` class is the interface to the
services specified in the *Web Services Definition Language* (*WSDL*) file for
each service. The `-Soap` classes are the serializable implementations of the
models. Let's look at how to determine the URLs for these services. 

You can see a list of the services deployed on your portal by opening your
browser to a URL following one of these formats: 

- For your secure services (i.e., serevices requiring authentication) use
  `http://[host]:[port]/api/secure/axis`. 

- For your sevices that don't require authentication, use
  `http://[host]:[port]/api/axis`. 

Here's the list of *secure* web services for `UserGroup`: 

- `Portal_UserGroupService` (wsdl)
    - `addGroupUserGroups`
    - `addTeamUserGroups`
    - `addUserGroup`
    - `deleteUserGroup`
    - `getUserGroup`
    - `getUserUserGroups`
    - `unsetGroupUserGroups`
    - `unsetTeamUserGroups`
    - `updateUserGroup`

---

![note](../../images/tip-pen-paper.png) **Note:** Liferay's developers use a tool
called *Service Builder* to expose their services via SOAP automatically. If
you're interested in using Service Builder, check out the *Service Builder*
chapter in this guide. 

---

Each web service is listed with its name, operations, and a link to its WSDL
file. The WSDL file is written in XML and provides a model for describing and
locating the web service. 

Here's the WSDL Excerpt for the `addUserGroup` operation of `UserGroup`: 

    <wsdl:operation name="addUserGroup" parametterOrder="name description
    publicLayoutSetPropertyId privateLayoutSetPropertyId">
        <wsdl:input message="impl:addUserGroupRequest" name="addUserGroupRequest"/>
        <wsdl:outputMessage="impl:addUserGroupResponse" name="assUserGroupResponse"/>
    </wsdl:operation>

<!--Jim, I removed the screenshot of this XML and just typed it out. Is it okay?-->

To use the service, you pass in the WSDL URL along with your login credentials
to the SOAP service locator for your service. We'll show you an example in the
next section. 

Next, let's invoke the web service! 

### SOAP Java Client [](id=lp-6-1-dgen08-soap-java-client-0)

A Java web service client can easily be set up using Eclipse IDE. Here's how: 

In Eclipse, add a new Web Service Client to your project for each service you
plan to consume in your client code. For our purposes, the client we're building
needs a Web Service Client for the portal's `Company`, `User`, and `UserGroup`
services. 

To add your Web Service Clients in Eclipse IDE, click *New* &rarr; *Other...*, then
expand the *Web Services* category. Click *Web Service Client*.

For each client you create, you're prompted to enter the service definition
(WSDL) for the desired service. Here's an example WSDL: 

    http://localhost:8080/api/axis/Portal_UserService?wsdl

![Figure 11.3: Service Definition](../../images/api-web-svc-wsdl.png)

<!-- Again, too many screenshots. -Rich -->
<!--Jim I removed three screenshots that were too close otgether to respond to
Rich's comments and added some detail to the text. -->

With the WSDL specified, Eclipse automatically adds the auxiliary files and
libraries required to consume that web service.  Nifty!

Here's the code that locates and invokes operations to add a new UserGroup named
`MyUserGroup` and assign to it a `User` with screen name *test*: 

    import java.net.URL;

    import com.liferay.portal.model.CompanySoap; import
    com.liferay.portal.model.UserGroupSoap; import
    com.liferay.portal.service.http.CompanyServiceSoap; import
    com.liferay.portal.service.http.CompanyServiceSoapServiceLocator; import
    com.liferay.portal.service.http.UserGroupServiceSoap; import
    com.liferay.portal.service.http.UserGroupServiceSoapServiceLocator; import
    com.liferay.portal.service.http.UserServiceSoap; import
    com.liferay.portal.service.http.UserServiceSoapServiceLocator;

    public class LiferaySoapClient {
        public static void main(String[] args) {

            try {
                String remoteUser = "test";
                String password = "test";
                String virtualHost = "localhost";

                String groupName = "MyUserGroup";

                String serviceCompanyName = "Portal_CompanyService";
                String serviceUserName = "Portal_UserService";
                String serviceUserGroupName = "Portal_UserGroupService";

                long userId = 0;

                // Locate the Company
                CompanyServiceSoapServiceLocator locatorCompany =
                    new CompanyServiceSoapServiceLocator();

                CompanyServiceSoap soapCompany =
                    locatorCompany.getPortal_CompanyService(
                        _getURL(remoteUser, password, serviceCompanyName,
                                true));

                CompanySoap companySoap =
                    soapCompany.getCompanyByVirtualHost(virtualHost);

                // Locate the User service
                UserServiceSoapServiceLocator locatorUser =
                    new UserServiceSoapServiceLocator();
                UserServiceSoap userSoap = locatorUser.getPortal_UserService(
                    _getURL(remoteUser, password, serviceUserName, true));

                // Get the ID of the remote user
                userId = userSoap.getUserIdByScreenName(
                    companySoap.getCompanyId(), remoteUser);
                System.out.println("userId for user named " + remoteUser +
                        " is " + userId);

                // Locate the UserGroup service
                UserGroupServiceSoapServiceLocator locator =
                    new UserGroupServiceSoapServiceLocator();
                UserGroupServiceSoap usergroupsoap =
                    locator.getPortal_UserGroupService(
                        _getURL(remoteUser, password, serviceUserGroupName,
                                true));

                // Get the user's user groups
                UserGroupSoap[] usergroups = usergroupsoap.getUserUserGroups(
                        userId);

                System.out.println("User groups for userId " + userId + " ...");
                for (int i = 0; i < usergroups.length; i++) {
                    System.out.println("\t" + usergroups[i].getName());
                }

                // Adds the user group if it does not already exist
                String groupDesc = "My new user group";
                UserGroupSoap newUserGroup = null;

                boolean userGroupAlreadyExists = false;
                try {
                    newUserGroup = usergroupsoap.getUserGroup(groupName);
                    if (newUserGroup != null) {
                        System.out.println("User with userId " + userId +
                                " is already a member of UserGroup " +
                                        newUserGroup.getName());
                        userGroupAlreadyExists = true;
                    }
                } catch (Exception excep) {
                    // print cause, but continue
                    System.out.println(excep.getLocalizedMessage());
                }

                if (!userGroupAlreadyExists) {
                    newUserGroup = usergroupsoap.addUserGroup(
                            groupName, groupDesc);
                    System.out.println("Added user group named " + groupName);

                    long users[] = {userId};
                    userSoap.addUserGroupUsers(newUserGroup.getUserGroupId(),
                            users);
                }

                // Get the user's user groups
                usergroups = usergroupsoap.getUserUserGroups(userId);

                System.out.println("User groups for userId " + userId + " ...");
                for (int i = 0; i < usergroups.length; i++) {
                    System.out.println("\t" + usergroups[i].getName());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static URL _getURL(String remoteUser, String password,
            String serviceName, boolean authenicate)
        throws Exception {
            //Unauthenticated url
            String url = "http://localhost:8080/api/axis/" + serviceName;

            //Authenticated url
            if (authenicate) {
                url = "http://" + remoteUser + ":" + password +
                    "@localhost:8080/api/secure/axis/" + serviceName;
            }
            return new URL(url);
        }
    }

Running this client should produce output like the following example: 

    userId for user named test is 10196
    User groups for user 10196 ...
    java.rmi.RemoteException: No UserGroup exists with the key {companyId=10154, name=MyUserGroup}
    Added user group named
    Added user to user group named MyUserGroup
    User groups for user 10196 ...
        MyUserGroup

The output tells us the user had no groups, but was added to UserGroup
`MyUserGroup`. 

You might be thinking, "But an error was thrown! We did something wrong!" Yes,
an error was thrown (`java.rmi.RemoteException:`), but we're sitting here as
cool as an iced cream sandwich all the same. The exception was thrown simply
because the `UserGroup` check was invoked before the `UserGroup` was created.
Because the very next line of the output says `Added user group named...`, we're
okay. Don't worry, be happy! 

Here are a few things to note about the URL: 

- It's a *secure* (authenticated) URL for the service. Authentication is done
using HTTP Basic Authentication, which isn't appropriate for a production
environment, since the password is unencrypted. It's simply used for convenience
in this example. 

- The screen name and password are passed in as credentials. 
- The name of the service (e.g. `Portal_UserGroupService`) is specified at the
  end of the URL. Remember that the service name can be found in the web
  service listing.

The operations `getCompanyByVirtualHost()`, `getUserIdByScreenName()`,
`getUserUserGroups()`, `addUserGroup()` and `addUserGroupUsers()` are specified
for the `-ServiceSOAP` classes `CompanyServiceSoap`, `UserServiceSoap` and
`UserGroupServiceSoap` in the WSDL files. Information on parameter types,
parameter order, request type, response type, and return type are conveniently
specified in the WSDL for each Liferay web service. It's all there for you!

Next let's implement a web service client implemented in PHP. 

### SOAP PHP Client [](id=lp-6-1-dgen08-soap-php-client-0)

You can write your client in any language that supports web services invocation.
Let's invoke the same operations we did when we created our Java client, this
time using PHP and the PHP SOAP Client: 

    <?php
        $groupName = "MyGroup2";
        $userName = "test";
        $clientOptions = array(
        'login' => $userName,
        'password' => 'test');

        // Add user group

        $userGroupClient = new SoapClient(
            "http://localhost:8080/api/secure/axis/Portal_UserGroupService?wsdl",
            $clientOptions);
        $group = $userGroupClient->addUserGroup($groupName, "This is my group",
            0, 0);
        print "group id for " . $groupName . " is " . $group->userGroupId . "\n";

        // add user to user group

        $companyClient = new SoapClient(
            "http://localhost:8080/api/secure/axis/Portal_CompanyService?wsdl",
            $clientOptions);
        $company = $companyClient->getCompanyByVirtualHost("localhost");
        $userClient = new SoapClient(
            "http://localhost:8080/api/secure/axis/Portal_UserService?wsdl",
            $clientOptions);
        $userId = $userClient->getUserIdByScreenName($company->companyId,
            $userName);
        print "user id for " . $userName . " is " . $userId . "\n";

        $users = array($userId);
        $userClient->addUserGroupUsers($group->userGroupId, $users);

        // get and print user groups to which user belongs

        $userGroups = $userGroupClient->getUserUserGroups($userId);
        print "user groups for user " . $userId . " ...\n";
        foreach($userGroups as $k=>$v)
            print ($v->name) . " " . $v->userGroupId . "\n";
    ?>

It's worth repeating that you can use any language that supports use of SOAP web
services to create your web services client. Try it out on Liferay's SOAP web
services!

Next we'll explore Liferay's JSON Web Services. 

## JSON Web Services [](id=json-web-services)

JSON Web Services let you access portal service methods by exposing them as a
JSON HTTP API. Service methods are made easily accessible using HTTP requests,
both from JavaScript within the portal and from any JSON-speaking client. 

We'll cover the following topics as we explore JSON Web Service functionality:

- Registration
- Configuration
- Invocation 
- Results

Let's talk about registering JSON Web Services next. 

### Registering JSON Web Services [](id=lp-6-1-dgen08-registering-json-web-services-0)

Liferay's developers use a tool called *Service Builder* to build services. When
you build services with Service Builder, all remote-enabled services (i.e.,
`service.xml` entities with the property `remote-service="true"`) are exposed as
JSON Web Services. When each `-Service.java` interface is created for a
remote-enabled service, the `@JSONWebService` annotation is added on the class
level of that interface. All of the public methods of that interface become
registered and available as JSON Web Services. 

The `-Service.java` interface source file should never be modified by the user.
If you need, however, more control over its methods (e.g., hiding some methods
and exposing others), you can configure the `-ServiceImpl` class.  When the
service implementation class (`-ServiceImpl`) is annotated with the
`@JSONWebService` annotation, the service interface is ignored and the service
implementation class is used for configuration in its place. In other words,
`@JSONWebService` annotations in the service implementation override any JSON
Web Service configuration in service interface.

That's it!  When you start Liferay Portal, it scans classes on the classpath
for annotations. The scanning process is optimized so only portal and service
JARs are scanned, along with class raw bytecode content. Each class that uses
the `@JSONWebService` annotation is loaded and further examined, and its
methods become exposed as JSON API. As explained previously, the `-ServiceImpl`
configuration overrides the `-Service` interface configuration during
registration. 

As an example, let's register the `DLAppService`: 

    @JSONWebService
    public interface DLAppService {
    ...

The `@JSONWebService` annotation is found on portal startup. You'll see the
following lines in the console output when the debug log level is set:

    10:55:06,595 DEBUG [JSONWebServiceConfigurator:121] Configure JSON web service actions
    10:55:06,938 DEBUG [JSONWebServiceConfigurator:136] Configuring 820 actions in ... ms

Scanning and registration is complete and all service methods (those of
`DLAppService` and of other services) are registered as JSON Web Services. 

<!--What other services? -->

<!-- I think this whole section is confusing. It sounds like we're talking about
custom services written for a custom application, but then we provide an example
using the DLAppService, which is an internal Liferay service. This makes it
sound like we're recommending people modify Liferay's source code to add the
annotation, which is obviously not what we want. 

So we need to answer these two questions in this section: 

How do I register/enable the JSON interface for internal Liferay services, or
are they already available out of the box? 

How do I register/enable my custom services' JSON interface to be used by
developers? 

Our audience is most emphatically not core developers, so if the above
instruction is for developers adding services to Liferay's core, it's out of
scope for our documentation. 

-Rich
-->

#### Registering Plugin JSON Web Services [](id=lp-6-1-dgen09-registering-plugin-json-web-services-0)

Custom portlets can be registered and scanned for JSON web services, too.
Services that use the `@JSONWebService` annotation become part of the JSON API.
Scanning of portlet services isn't enabled by default; the following servlet
definition must be added in your portlet's `web.xml`: 

        <web-app>
            ...
            <filter>
                <filter-name>Secure JSON Web Service Servlet Filter</filter-name>
                <filter-class>com.liferay.portal.kernel.servlet.PortalClassLoaderFilter</filter-class>
                <init-param>
                    <param-name>filter-class</param-name>
                    <param-value>com.liferay.portal.servlet.filters.secure.SecureFilter</param-value>
                </init-param>
                <init-param>
                    <param-name>basic_auth</param-name>
                    <param-value>true</param-value>
                </init-param>
                <init-param>
                    <param-name>portal_property_prefix</param-name>
                    <param-value>jsonws.servlet.</param-value>
                </init-param>
            </filter>
            <filter-mapping>
                <filter-name>Secure JSON Web Service Servlet Filter</filter-name>
                <url-pattern>/api/jsonws/*</url-pattern>
            </filter-mapping>

            <servlet>
                <servlet-name>JSON Web Service Servlet</servlet-name>
                <servlet-class>com.liferay.portal.kernel.servlet.PortalClassLoaderServlet</servlet-class>
                <init-param>
                    <param-name>servlet-class</param-name>
                    <param-value>com.liferay.portal.jsonwebservice.JSONWebServiceServlet</param-value>
                </init-param>
                <load-on-startup>0</load-on-startup>
            </servlet>
            <servlet-mapping>
                <servlet-name>JSON Web Service Servlet</servlet-name>
                <url-pattern>/api/jsonws/*</url-pattern>
            </servlet-mapping>
            ...
        </web-app>

Now the servlet can scan and register your portlet's JSON Web Services. 

<!--  Really? This is most decidedly unfriendly. Is this still the case? There's
also a missing transition here. -Rich
-->
<!-- Jim I added the transition, but when I looked on the web for information
on registering JSON web services from custom plugins I only found the dev guide
and the wiki post (both of which obviously agree with what we have here). When
I pinged Rich about it he mentioned that this seems like a hack and if this is
still really true (having to add filters, filter mappings, and servlet
declaration in web.xml), then we should explain why it's necessary. --> 

Let's see how to form a mapped URL of the service next. 

#### Mapping and naming conventions [](id=lp-6-1-dgen08-mapping-and-naming-conventions-0)

You can form a mapped URL of an exposed service using the following naming
convention: 

    http://[server]:[port]/api/jsonws/[service-class-name]/[service-method-name]

<!-- We seem to have skipped something here. What's a mapped URL of an exposed
service? -Rich -->

<!--Jim I can't answer this. My intuition since it follows registration of JSON
web services from custom plugins is simply that this is the URL that leads you
to the service? -->

Let's look at the last two bracketed items more closely: 

- `service-class-name` is generated from the service's class name by removing
  the `Service` or `ServiceImpl` suffix and making it lower case. 
- `service-method-name` is generated from the service's method name by
  converting its camel case to lower case and using dashes (`-`) to separate
  words. 

Let's demonstrate by mapping a service method's URL using the above naming
conventions: 

-   First, here's the service method we want to map: 

        @JSONWebService
        public interface UserService {
            public com.liferay.portal.model.User getUserById(long userId) {...}

-   Here's what the service method's URL looks like: 

        http://localhost:8080/api/jsonws/user-service/get-user-by-id

Each service method is bound to one HTTP method type. Any method name starting
with `get`, `is` or `has` are assumed to be read-only methods and are mapped as
*GET HTTP* methods by default. All other methods are mapped as *POST HTTP*
methods. 

There are two ways to access a plugin's JSON Web Services. We'll call them,
ingeniously, *Option 1* and *Option 2*. 

-   *Option 1*: Access the plugin service via the plugin context (e.g. your
    custom portlet's context):

        http://[server]:[port]/[plugin-context]/api/jsonws/[service-class-name]/[service-method-name]

    This calls the plugin's service in a separate web application that is not
    aware of the user's current session in the portal. As a result, accessing
    the service in this manner requires additional authentication. You might use
    this for batch services or other requests that don't require context. 

-   *Option 2*: Accessing the plugin service via the portal context:

        http://[server]:[port]/[portal-context]/api/jsonws/[plugin-context].[service-class-name]/[service-method-name]

    Conveniently, requests sent this way can leverage the user's authentication
    in his current portal session. Liferay's JavaScript API for services calls
    plugin services using this method.

NExt we'll learn to available JSON Web Services. 

#### Listing available JSON Web Services [](id=lp-6-1-dgen08-listing-available-json-web-services-0)

To see which service methods are registered and available for use, open your
browser to the following address: 

    http://localhost:8080/api/jsonws

The page lists the portal's registered and exposed service methods. Get each
method's details by clicking the method name. You'll see the full signature of
the method, all its arguments, list exceptions that can be thrown, and read its
Javadoc! Using a simple form from within your browser, you can even invoke the
service method for testing purposes. 

To list registered services on a plugin (e.g. a custom portlet), don't forget to
use its context path in your URL: 

    http://localhost:8080/[plugin-context]/api/jsonws

This lists the JSON Web Service API for the portlet. 


If you've been paying attention, you already know how to control registration by
using the `@JSONWebService` annotation in your `-ServiceImpl` class. This
overrides any configuration defined in the interface. What you might not know is
that you can control the visibility of methods using annotations at the method
level. 

Let's find out how to ignore a specific method. 

#### Ignoring a method [](id=lp-6-1-dgen08-ignoring-a-method-0)

To keep a method from being exposed as a service, annotate the method with the
following option:

    @JSONWebService(mode = JSONWebServiceMode.IGNORE)

Methods with this annotation don't become part of the JSON Web Service API. 

Let's learn to define custom HTTP method names and URL names. 

#### HTTP method name and URL [](id=lp-6-1-dgen08-http-method-name-and-url-0)

At the method level, you can define custom HTTP method names and URL names. Just
use an annotation like this one: 

    @JSONWebService(value = "add-file-wow", method = "PUT")
    public FileEntry addFileEntry(

In this example, the `DLApp` service method `addFileEntry` is mapped to URL
method name `add-file-wow`. The complete URL is actually
`http://localhost:8080/api/jsonws/dlapp/add-file-wow` and can be accessed using
the PUT HTTP method.

If the URL name starts with a slash character (`/`), only the method name is
used to form the service URL; the class name is ignored.

    @JSONWebService("/add-something-very-specific")
    public FileEntry addFileEntry(

Similarly, you can change the class name part of the URL, by setting the value
in class-level annotation:

    @JSONWebService("dla")
    public class DLAppServiceImpl extends DLAppServiceBaseImpl {

This maps all the service methods of the class to URL class name `dla` instead
of the `dlapp` default.

<!-- None of the examples above should use DLApp, as that's an internal Liferay
service, and we don't want developers changing these annotations. Instead, these
examples should be changed to use the service that Jesse is developing for the
Service Builder chapter. We're also missing a transition here. -Rich -->

Next we'll show you a different approach to exposing your methods as we discuss
manual registration. 

#### Manual registration mode [](id=lp-6-1-dgen08-manual-registration-mode-0)

Up to now, it is assumed that you want to expose most of your service methods,
while hiding some specific methods (the *blacklist* approach).
Sometimes,however, you want the opposite: to explicitly specify only those
methods that are to be exposed (the *whitelist* approach). This is possible,
too, by specifying *manual mode* on the class-level annotation. Then it is up
to you annotate only those methods that you want exposed.

    @JSONWebService(mode = JSONWebServiceMode.MANUAL)
    public class DLAppServiceImpl extends DLAppServiceBaseImpl {
        ...
        @JSONWebService
        public FileEntry addFileEntry(

Now only the `addFileEntry` method and any other method annotated with
`@JSONWebService` will be part of the JSON Web Service API; all other methods
of this service will be excluded from the API.

<!-- Russ, while I love this pun, unfortunately for the survival of the
statements about how easy it is to annotate, annotations are a standard Java
thing that developers should know about before reading this. For that reason,
it's neither a novel nor a new concept, and so I'm removing the statements about
how easy it is. Maybe you can fit the pun in somewhere else? -->

Next let's look at portal configuration options that apply to JSON Web Services. 

### Portal Configuration of JSON Web Services [](id=lp-6-1-dgen08-portal-configuration-of-json-web-services-0)

JSON Web Services are enabled on Liferay Portal by default. If you need to
disable them, specify this portal property setting: 

    json.web.service.enabled=false

Now let's look at strict HTTP methods. 

#### Strict HTTP methods [](id=lp-6-1-dgen08-strict-http-methods-0)

All JSON Web Services are mapped to either GET or POST HTTP methods. If a
service method name starts with `get`, `is` or `has`, the service is assumed to
be read-only and is bound to the GET method; otherwise it's bound to POST. 

By default, Liferay Portal doesn't check HTTP methods when invoking a service
call; it works in *non-strict http method* mode, where services may be invoked
using any HTTP method. If you need the strict mode, you can set it with this
portal property: 

    jsonws.web.service.strict.http.method=true

When using strict mode, you must use the correct HTTP methods in calling service
methods. 

When strict HTTP mode is enabled, you still might have need to disable HTTP
methods. We'll show you how next. 

<!--This needed a transition, and I got tired of saying, "next let's...". Check
for acuracy. -Russ-->

#### Disabling HTTP methods [](id=lp-6-1-dgen08-disabling-http-methods-0)

When strict HTTP method mode is enabled, you can filter web service access based
on HTTP methods used by the services. For example, you can set the portal JSON
Web Services to work in read-only mode by disabling HTTP methods other than GET.
For example: 

    jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

Now all requests that use HTTP methods from the list above are ignored.

Next we'll show you how to restrict public access to exposed JSON APIs. 

#### Controlling public access [](id=lp-6-1-dgen08-controlling-public-access-0)

Each service method knows if it can be executed by unauthenticated users and
if a user has adequate permission for the chosen action. Most of the portal's
read-only methods are open to public access.

If you're concerned about security, you can further restrict public access to
exposed JSON APIs by explicitly stating which methods are *public* (i.e.,
accessible to unauthenticated users). Use the following property to specify your
public methods: 

    jsonws.web.service.public.methods=*

The property supports wildcards, so if you specify `get*,has*,is*` on the right
hand side of the `=` symbol, all read-only JSON methods will be publicly
accessible. All other JSON methods will be secured. To disable access to *all*
exposed methods, you can leave the right side of the `=` symbol empty; to enable
access to all exposed methods, specify `*`. 

Next find out how to invoke JSON Web Services. 

### Invoking JSON Web Services [](id=lp-6-1-dgen08-invoking-json-web-services-0)

How you invoke a JSON web service depends on how you pass in its parameters.
We'll discuss how to pass in parameters below, but first let's make sure you
understand how your invocation is matched to a method, especially when a service
method is overloaded. 

The general rule is that you provide the method name and *all* parameters for
that service method--even if you only provide `null`. 

It's important to provide all parameters, but it doesn't matter *how* you do it
(e.g., as part of the URL line, as request parameters, etc.). The order of the
parameters doesn't matter either. 

Exceptions abound in life, and there's an exception to the rule that *all*
parameters are required--when using numeric *hints* to match methods. Let's look
at using hints next. 

#### Using hints [](id=lp-6-1-dgen08-using-hints-0)

Adding numeric hints lets you specify how many method arguments a service has.
If you don't specify an argument for a parameter, it's automatically
passed in as `null`. Syntactically, you can add hints as numbers separated by a
dot in the method name. Here's an example: 

    /foo/get-bar.2/param1/123/-param2

Here, the `.2` is a numeric hint specifying that only service methods with two
arguments will be matched; others will be ignored for matching. 

There's an important distinction to make between matching using hints and
matching without hints. When a hint is specified, you don't have to specify all
of the parameters. Any missing arguments are treated as `null`. The previous
example may be called like this:

    /foo/get-bar.2/param1/123

In this example, `param2` will automatically be set to `null`. 

Find out how to pass parameters as part of the URL path next. 

#### Passing parameters as part of URL path [](id=lp-6-1-dgen08-passing-parameters-as-part-of-url-path-0)

You can pass parameters as part of the URL path. After the service URL, just
specify method parameters in name-value pairs. Parameter names must be formed
from method argument names by converting them from camelCase to names using all
lower case and separated-by-dash. Here's an example: 

    http://localhost:8080/api/secure/jsonws/dlapp/get-file-entries/repository-id/10172/folder-id/0

You can pass parameters in any order; it's not necessary to follow the order in
which the arguments specified in the method signatures. 

When a method name is overloaded, the *best match* will be used. It chooses the
method that contains the least number of undefined arguments and invokes it for
you. 

You can also pass parameters in a URL query, and we'll show you how next. 

#### Passing parameters as URL query [](id=lp-6-1-dgen08-passing-parameters-as-url-query-0)

You can pass in parameters as request parameters. Parameter names are specified
as is (e.g. camelCase) and are set equal to their argument values, like this: 

    http://localhost:8080/api/secure/jsonws/dlapp/get-file-entries?repositoryId=10172&folderId=0

As with passing parameters as part of a URL path, the parameter order is not
important, and the *best match* rule applies for overloaded methods. 

Now that you know a few different ways to pass parameters, next let's mix them
all up. 

Parameters can be passed in a mixed way; some can be part of the URL path and
some can be specified as request parameters. 

Parameter values are sent as strings using the HTTP protocol. Before a matching
Java service method is invoked, each parameter value is converted from a `String`
to its target Java type. Liferay uses a third party open source library to
convert each object to its appropriate common type. It's possible to add or
change the conversion for certain types; but we'll just cover the standard
conversions process. 

Conversion for common types (e.g., `long`, `String`, `boolean`) is
straightforward. Dates can be given in milliseconds; locales can be passed as
locale names (e.g. `en` and `en_US`). To pass in an array of numbers, send a
`String` of comma-separated numbers (e.g. `String` `4,8,15,16,23,42` can be
converted to `long[]` type). You get the picture!

In addition to the common types, arguments can be of type `List` or `Map`. To
pass a `List` argument, send a JSON array. To pass a `Map` argument, send a JSON
object. The conversion of these is done in two steps, ingeniously referred to
below as *Step 1* and *Step 2*:

- *Step 1--JSON deserialization*: JSON arrays are converted into
  `List<String>` and JSON objects are converted to `Map<String, String>`. For
  security reasons, it is forbidden to instantiate any type within JSON
  deserialization.  
- *Step 2--Generification*: Each `String` element of the `List` and `Map` is
  converted to its target type (the argument's generic Java type specified in
  the method signature). This step is only executed if the Java argument type
  uses generics. 

As an example, let's consider the conversion of `String` array `[en,fr]` as JSON
web service parameters for a `List<Locale>` Java method argument type: 

- *Step 1--JSON deserialization*: The JSON array is deserialized to a
  `List<String>` containing `String`s `en` and `fr`. 
- *Step 2--Generification*: Each `String` is converted to the `Locale` (the
  generic type), resulting in the `List<Locale>` Java argument type. 

Now let's see how to specify an argument as `null`. 

#### Sending NULL values [](id=lp-6-1-dgen08-sending-null-values-0)

To pass a `null` value for an argument, prefix the parameter name with a dash.
Here's an example: 

    .../dlsync/get-d-l-sync-update/company-id/10151/repository-id/10195/-last-access-date

The `last-access-date` parameter is interpreted as `null`. Although we have it
last in the URL above, it's not necessary.

Null parameters don't have specified values. When a null parameter is passed as
a request parameter, its value is ignored and `null` is used instead: 

    <input type="hidden" name="-last-access-date" value=""/>

When using JSON RPC (see below), you can send null values explicitly, even
without a prefix. Here's an example: 

    "last-access-date":null

Now let's learn about encoding parameters. 

#### Encoding Parameters [](id=lp-6-1-dgen08-parameters-encoding-0)

There's a difference between URL encoding and query (i.e. request parameters)
encoding. The difference lies in how the space character is encoded. When the
space character is part of the URL path, it's encoded as `%20`; when it's part
of the query it's encoded as a plus sign (`+`).

All these encoding rules apply as well to international (non-ascii) characters.
Since Liferay Portal works in UTF-8 mode, parameter values must be encoded as
UTF-8 values. Liferay Portal doesn't decode request URLs and request parameter
values to UTF-8 itself; it relies on the web server layer. When accessing
services through JSON-RPC, encoding parameters to UTF-8 isn't enough--you need
to send the encoding type in a Content-Type header (e.g.  `Content-Type :
"text/plain; charset=utf-8"`). 

For example, let's pass the value "&#1057;&#1091;&#1087;&#1077;&#1088;" ("Super"
in Cyrillic) to some JSON Web Service method. This name first has to be
converted to UTF-8 (resulting in array of 10 bytes) and then encoded for URLs or
request parameters. The resulting value is the string
`%D0%A1%D1%83%D0%BF%D0%B5%D1%80` that can be passed to our service method. When
received, this value is first going to be translated to an array of 10 bytes
(URL decoded) and then converted to a UTF-8 string of the 5 original characters.

Did you know you can send files as arguments? Find out how next. 

#### Sending files as arguments [](id=lp-6-1-dgen08-sending-files-as-arguments-0)

Files can be uploaded using multipart forms and requests. Here's an example: 

    <form action="http://localhost:8080/api/secure/jsonws/dlapp/add-file-entry" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="repositoryId" value="10172"/>
        <input type="hidden" name="folderId" value="0"/>
        <input type="hidden" name="title" value="test.jpg"/>
        <input type="hidden" name="description" value="File upload example"/>
        <input type="hidden" name="changeLog" value="v1"/>
        <input type="file" name="file"/>
        <input type="submit" value="addFileEntry(file)"/>
    </form>

This is common upload form that invokes the `addFileEntry` method of the
`DLAppService` class. 

Now we'll show you how to invoke JSON Web Services using JSON RPC. 

#### JSON RPC [](id=lp-6-1-dgen08-json-rpc-0)

You can invoke JSON Web Service using [JSON RPC](http://json-rpc.org). Most of
the JSON RPC 2.0 specification is supported in Liferay JSON Web Services. One
important limitation is that parameters must be passed in as *named*
parameters.  Positional parameters are not supported, as there are too many
overloaded methods for convenient use of positional parameters. 

Here's an example of invoking a JSON web service using JSON RPC: 

    POST http://localhost:8080/api/secure/jsonws/dlapp
    {
        "method":"get-folders",
        "params":{"repositoryId":10172, "parentFolderId":0},
        "id":123,
        "jsonrpc":"2.0"
    }

Let's talk about paramteters that are made available to secure JSON web
services by default. 

#### Default parameters [](id=lp-6-1-dgen08-default-parameters-0)

When accessing *secure* JSON web services (i.e., the user has to be
authenticated), some parameters are made available to the web services by
default. Unless you want to change their values to something other than their
defaults, you don't have to specify them explicitly. 

Here are the default parameters:  

- `userId`: The id of authenticated user
- `user`: The full User object
- `companyId`: The users company
- `serviceContext`: The empty service context object 

Let's find out about object parameters next. 

#### Object parameters [](id=lp-6-1-dgen08-object-parameters-0)

Most services accept simple parameters like numbers and strings. However,
sometimes you might need to provide an object (a non-simple type) as a service
parameter. 

To create an instance of an object parameter, prefix the parameter with a plus
sign, `+` and don't assign it any other parameter value. This is similar to
when we specified a null parameter by prefixing the parameter with a dash
symbol, `-`.

Here's an example:

    /jsonws/foo/get-bar/zap-id/10172/start/0/end/1/+foo

To create an instance of an object parameter as a request parameter, make sure
you encode the `+` symbol: 

    /jsonws/foo/get-bar?zapId=10172&start=0&end=1&%2Bfoo

Here's an alternative syntax: 

    <input type="hidden" name="+foo" value=""/>

If a parameter is an abstract class or an interface, it can't be instantiated as
such. Instead, a concrete implementation class must be specified to create the
argument value. You can do this by specifying the `+` prefix before the
parameter name followed by specifying the concrete implementation class. Check
out this example: 

    /jsonws/foo/get-bar/zap-id/10172/start/0/end/1/+foo:com.liferay.impl.FooBean

Here's another way of doing it: 

    <input type="hidden" name="+foo:com.liferay.impl.FooBean" value=""/>

The examples above specify that a `com.liferay.impl.FooBean` object, presumed to
implement the class of the parameter named `foo`, is to be created. 

You can also set a concrete implementation as a value. Here's an example: 

    <input type="hidden" name="+foo" value="com.liferay.impl.FooBean"/>

In JSON RPC, here's what it looks like:

    "+foo" : "com.liferay.impl.FooBean"

All the examples above specify a concrete implementation for the `foo` service
method parameter. 

Once you pass in an object parameter, you might want to populate the object.
Find out how next. 

#### Inner Parameters [](id=lp-6-1-dgen08-inner-parameters-0)

When you pass in an object paramter, you'll often need to populate its inner
parameters (i.e., fields). Consider a default parameter `serviceContext` of type
`ServiceContext` (see the *Service Context* section in this chapter to find out
more about this type). To make an appropriate call to JSONWS, you might need to
set the `serviceContext` parameter's fields `addGroupPermissions` and
`scopeGroupId`. 

You can pass inner parameters by specifying them using dot notation. Just
apppend the name of the parameter with a dot (i.e., a period, `.`), followed by
the name of the inner parameter. For the `ServiceContext` inner parameters we
mentioned above, you'd specify `serviceContext.addGroupPermissions` and
`serviceContext.scopeGroupId`. They're recognized as inner parameters and their
values are injected into existing parameters before the API service method is
executed. 

Inner parameters aren't counted as regular parameters for matching methods
and are ignored during matching. 

---

![tip](../../images/tip-pen-paper.png) **Tip:** Use inner parameters with object
parameters to set inner content of created parameter instances! 

---

Next let's see what values are returned when a JSON seb service is invoked. 

### Returned values [](id=lp-6-1-dgen08-returned-values-0)

No matter how a JSON web service is invoked, it returns a JSON string that
represents the service method result. Returned objects are *loosely* serialized
to a JSON string and returned to the caller. 

Let's look at some values returned from service calls. We'll create a
`UserGroup` as we did in our SOAP web service client examples. To make it easy,
we'll use the test form provided with the JSON web service in our browser. 

1.  Open your browser to the JSON web service method that adds a `UserGroup`:

        http://127.0.0.1:8080/api/jsonws?signature=/usergroup/add-user-group-2-name-description

    Alternatively, navigate to it by starting at
    `http://127.0.0.1:8080/api/jsonws` then scrolling down to the section for
    *UserGroup*; click *add-user-group*. 

2.  In the *name* field enter *UserGroup3* and set the description to an
    arbitrary value like *Created using JSON WS*.

3.  Click *Invoke* and you'll get a result similar to the following: 

        {"addedByLDAPImport":false,"companyId":10154,"description":"Created
        using JSON WS","name":"MyUserGroup33","parentUserGroupId":0,"userGroupId":13162}

The returned `String` represents the `UserGroup` object you just created,
serialized into a JSON string. To find out more about JSON strings, go to
[json.org](http://www.json.org/).

To learn about serializing Java objects, maps and lists, check out the article
[JSON
Serialization](http://www.liferay.com/community/wiki/-/wiki/Main/JSON+Serialization)
by Igor Spasi&#263;. 

Let's check out some common JSON WebService errors. 

### Common JSON WebService errors [](id=lp-6-1-dgen09-common-json-webservice-errors-0)

While working with JSON Web Services, you may encounter errors. Let's look at
the most common errors in the following subsections. 

<!--I would have put the subsections in an unordered list, but I'll leave them
for now. Which is preferred?  Are they long enough to warrant subsections? --> 

<!-- No, they're not long enough. In fact, that's been a problem with this whole
chapter. I removed some sections already. One way to fix some of the "missing
transition" problems above would be to combine some of the sections. -Rich -->

<!--I just added the transitions for now. -Russ -->

-   Missing value for parameter 


    If you see this error, you didn't pass a parameter value along with the
    parameter name in your URL path. The parameter value must follow the
    parameter name, like in this example: 

        /api/jsonws/user/get-user-by-id/userId

    The path above specifies a parameter named `userId`, but doesn't specify
    the parameter's value. You can resolve this error by providing the
    parameter value after the parameter name: 

        /api/jsonws/user/get-user-by-id/userId/173

-   No JSON web service action associated 

    This is error means no service method could be matched with the provided
    data (method name and argument names). This can be due to various reasons:
    arguments may be misspelled, the method name may be formatted incorrectly,
    etc. Since JSON web services reflect the underlying Java API, any changes in
    the respective Java API will automatically be propagated to the JSON web
    services. For example, if a new argument is added to a method or an existing
    argument is removed from a method, the parameter data must match that of the
    new method signature.

-   Unmatched argument type 

    This error appears when you try to instantiate a method argument using an
    incompatible argument type.

-   Judgment Day

    We hope you never see this error. It means that Skynet has initiated a
    nuclear war and most of humanity will be wiped out; survivors will need to
    battle *Terminator* cyborgs. If you see this error and survive *Judgment
    Day*, we recommend joining the resistance--they'll likely need good
    developers to support the cause, especially those familiar with time travel. 

    Had you going there, didn't we? 

<!-- Too much?-->

<!-- I actually took all the other ones out except for this one. This turns it
from a chapter-long thing into a random, unexpected insertion, which may be
funnier (YMMV), and doesn't make Liferay out to be a part of an evil,
computer-generated, apocalypse-causing, malevolent force. --> 

<!--Wait, we aren't?-->

Next we'll show you how to optimize your use of JSON Web Services by using the
*JSON Web Services Invoker*. 

### JSON Web Services Invoker [](id=lp-6-1-dgen09-json-web-services-invoker-0)

Using JSON Web Services is easy: you send a request that defines a service
method and parameters, and you receive the result as JSON object. Below we'll
show you why that's not optimal, and introduce a tool that lets you use JSON
Web Services more efficiently and pragmatically. 

Consider this example: You're working with two related objects: a `User` and its
corresponding `Contact`. With simple JSON Web Service calls, you first call some
user service to get the user object and then you call the contact service using
the contact ID from the user object. You end up sending two HTTP requests to get
two JSON objects that aren't even bound together; there's no contact information
in the user object (i.e. no `user.contact`). This approach is suboptimal for
performance (sending two HTTP calls) and usability (manually managing the
relationship between two objects). It'd be nicer if you had a tool to address
these inefficiencies. Fortunately, the *JSON Web Service Invoker* does just
that! 

Liferay's JSON Web Service Invoker helps you optimize your use of JSON Web
Services. In the following sections, we'll show you how. 

#### A simple Invoker call [](id=lp-6-1-dgen09-a-simple-invoker-call-0)

The Invoker is accessible from the following fixed address:

    http://[address]:[port]/api/jsonws/invoke

It only accepts a `cmd` request parameter--this is the Invoker's command. If the
command request parameter is missing, the request body is used as the command.
So you can specify the command by either using the request parameter `cmd` or
the request body. 

The Invoker command is a plain JSON map describing how JSON Web Services are
called and how the results are managed. Here's an example of how to call a
simple service using the Invoker: 

    {
        "/user/get-user-by-id": {
            "userId": 123,
            "param1": null
        }
    }

The service call is defined as a JSON map. The key specifies the service URL
(i.e. the service method to be invoked) and the key's value specifies a map of
service parameter names (i.e. `userId` and `param1`) and their values. In the
example above, the retrieved user is returned as a JSON object. Since the
command is a JSON string, null values can be specified either by explicitly
using the `null` keyword or by placing a dash before the parameter name and 
leaving the value empty (e.g. `"-param1": ''`). 

The example Invoker call functions exactly the same as the following standard
JSON Web Service call: 

    /user/get-user-by-id?userId=123&-param1

You can use variables to reference objects returned from service calls. Variable
names must start with a dollar sign, `$`. In our previous example, the service
call returned a user object you can assign to a variable: 

    {
        "$user = /user/get-user-by-id": {
            "userId": 123,
        }
    }

The `$user` variable holds the returned user object. You can reference the
user's contact ID using the syntax `$user.contactId`. 

<!-- Missing transition. --> 
NExt see how you can use nested service calls to join information from two
related objects. 

#### Nesting service calls [](id=lp-6-1-dgen09-nested-service-calls-0)

With nested service calls, you can magically bind information from related
objects together in a JSON object. You can call other services within the same
HTTP request and nest returned objects in a convenient way. Here's the magic of
a nested service call in action: 

    {
        "$user = /user/get-user-by-id": {
            "userId": 123,
            "$contact = /contact/get-contact-by-id": {
                "@contactId" : "$user.contactId"
            }
        }
    }

This command defines two service calls: the contact object returned from the
second service call is nested in (i.e. injected into) the user object, as a
property named `contact`. Now we can bind the user and his or her contact
information together! 

Let's see what the Invoker did in the background when we used a single HTTP
request to make the above nested service call: 

- First, the Invoker called the Java service mapped to `/user/get-user-by-id`,
  passing in a value for the `userId` parameter. 
- Next, the resulting user object was assigned to the variable `$user`. 
- The nested service calls were invoked. 
- The Invoker called the Java service mapped to `/contact/get-contact-by-id`
  by using the `contactId` parameter, with the `$user.contactId` value from
  the object `$user`. 
- The resulting contact object was assigned to the variable `$contact`. 
- Lastly, the Invoker injected the contact object referenced by `$contact`
  into the user object's property named `contact`. 

---

![note](../../images/tip-pen-paper.png) **Note:** You must *flag* parameters that
take values from existing variables. To flag a parameter, insert the `@` prefix
before the parameter name. 

---

Next let's talk about filtering object properties so only those you
need are returned when you invoke a service. 

#### Filtering results [](id=lp-6-1-dgen09-filtering-results-0)

Many of Liferay Portal's model objects are rich with properties. If you only
need a handful of an object's properties for your business logic, making a web
service invocation that returns all of an object's properties is a waste of
network bandwidth. With the JSON Web Service Invoker, you can define a
*white-list* of properties: only the specific properties you request in the
object will be returned from your web service call. Here's how you white-list
the properties you need: 

    {
        "$user[firstName,emailAddress] = /user/get-user-by-id": {
            "userId": 123,
            "$contact = /contact/get-contact-by-id": {
                "@contactId" : "$user.contactId"
            }
        }
    }

In this example, the returned user object has only the `firstName` and
`emailAddress` properties (it still has the `contact` property, too). To specify
white-list properties, you simply place the properties in square brackets (e.g.,
`[whiteList]`) immediately following the name of your variable. 

Let's talk about batching calls next. 

#### Batching calls [](id=lp-6-1-dgen09-batching-calls-0)

When we nested service calls earlier, the intent was to invoke multiple services
with a single HTTP request. Using a single request for multiple service calls is
helpful for gathering related information from the service call results, but it
can also be advantageous to use a single request to invoke multiple unrelated
service calls. The Invoker lets you batch service calls together to improve
performance. It's simple: just pass in a JSON array of commands using the
following format: 

    [
        {/* first command */},
        {/* second command */}
    ]

The result is a JSON array populated with results from each command. The
commands are collectively invoked in a single HTTP request, one after another. 

By learning to leverage JSON Web Services in Liferay, you've added some powerful
tools to your toolbox. Good job! 

Next let's consider the `ServiceContext` class that's used by many Liferay
services.

## Service Context [](id=service-conte-1)

The `ServiceContext` class is a parameter class used for passing contextual
information for a service. Using a parameter class lets you consolidate many
different methods with different sets of optional parameters into a single,
easier-to-use method. The class also aggregates information necessary for
transversal features, including permissioning, tagging, categorization, and
more. 

<!-- What's a transversal feature? I don't understand that term, and I'm
thinking we shouldn't just use it like this without explaining what it is. -Rich
-->

In this section we'll look at the Service Context fields, learn how to create
and populate a Service Context, and learn to access Service Context data.

First we'll look at the fields of the `ServiceContext` class.

### Service Context Fields [](id=lp-6-1-dgen08-service-context-fields-0)

The `ServiceContext` class has many fields. The best field descriptions are
found in the Javadoc: 

[http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/portal/service/ServiceContext.html](http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/portal/service/ServiceContext.html).

Here we'll give you a helpful categorical listing of the fields: 

- Actions:
    - `_command`
    - `_workflowAction`
- Attributes:
    - `_attributes`
    - `_expandoBridgeAttributes`
- Classification: 
    - `_assetCategoryIds`
    - `_assetTagNames`
- IDs and Scope:
    - `_companyId`
    - `_portletPreferencesIds`
    - `_plid`
    - `_scopeGroupId`
    - `_userId`
    - `_uuid`
- Language:
    - `_languageId`
- Miscellaneous:
    - `_headers`
    - `_signedIn`
- Permissions:
    - `_addGroupPermissions`
    - `_addGuestPermissions`
    - `_deriveDefaultPermissions`
    - `_groupPermissions`
    - `_guestPermissions`
- Resources:
    - `_assetEntryVisible`
    - `_assetLinkEntryIds`
    - `_createDate`
    - `_indexingEnabled`
    - `_modifiedDate`
- URLs, paths and addresses:
    - `_currentURL`
    - `_layoutFullURL`
    - `_layoutURL`
    - `_pathMain`
    - `_portalURL`
    - `_remoteAddr`
    - `_remoteHost`
    - `_userDisplayURL`

Are you wondering how the `ServiceContext` fields get populated? Good! We'll
show you that next.

### Creating and Populating a Service Context  [](id=lp-6-1-dgen08-creating-and-populating-a-service-context--0)

Although all the `ServiceContext` class fields are optional, services that
store any type of content need the scope group ID specified, at least. Here's a
simple example of creating a `ServiceContext` instance and passing it as a
parameter to a service API using Java: 

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setScopeGroupId(myGroupId);
        ...
        BlogsEntryServiceUtil.addEntry(...., serviceContext);

If you invoke the service from a servlet, a Struts action or any other front
end class which has access to the `PortletRequest`, use one of the
`ServiceContextFactory.getInstance(...)` methods. These methods create the
`ServiceContext` object and automatically fill it with all necessary values.
The above example looks different if you invoke the service from a servlet: 

        ServiceContext serviceContext =
                ServiceContextFactory.getInstance(BlogsEntry.class.getName(),
                portletRequest);
        BlogsEntryServiceUtil.addEntry(..., serviceContext);

You can see an example of populating a `ServiceContext` with information from a
request object in the code of the `ServiceContextFactory.getInstance(...)`
methods. The methods demonstrate how to set parameters like *scope group ID*,
*company ID*, *language ID*, and more; they also demonstrate how to access and
populate more complex context parameters like *tags*, *categories*, *asset
links*, *headers*, and the *attributes* parameter. By calling
`ServiceContextFactory.getInstance(String className, PortletRequest
portletRequest)`, you can assure your expando bridge attributes are set on the
`ServiceContext`. 

Next let's see an example of accessing information from a `ServiceContext`. 

### Accessing Service Context data [](id=lp-6-1-dgen08-accessing-service-context-data-0)

We'll use code snippets from `BlogsEntryLocalServiceImpl.addEntry(...,
ServiceContext)` to show you how to access information from a `ServiceContext`
and comment on how the context information is being used. 

As we mentioned, your service needs a scope group ID from your `ServiceContext`.
The same holds true for the blogs entry service because the scope group ID
provides the scope of the blogs entry (the entity being persisted). For the
blogs entry, the scope group ID is used in the following way: 

- It's used as the groupId for the `BlogsEntry` entity.
- It's used to generate a unique URL for the blog entry.
- It's used to set the scope for comments on the blog entry.

Here are the corresponding code snippets:

        long groupId = serviceContext.getScopeGroupId();
        ...
        entry.setGroupId(groupId);
        ...
        entry.setUrlTitle(getUniqueUrlTitle(entryId, groupId, title));
        ...

        // Message boards

        if (PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED) {
            mbMessageLocalService.addDiscussionMessage(
                userId, entry.getUserName(), groupId,
                BlogsEntry.class.getName(), entryId,
                WorkflowConstants.ACTION_PUBLISH);
        }

Can `ServiceContext` be used to access the UUID of the blog entry? Absolutely!
Can you use `ServiceContext` to set the time the blog entry was added? Sure you
can. See here: 

        entry.setUuid(serviceContext.getUuid());
        ...
        entry.setCreateDate(serviceContext.getCreateDate(now));

Can `ServiceContext` be used in setting permissions on resources? You bet! When
adding a blog entry, you can add new permissions or apply existing permissions
for the entry, like this: 

        // Resources

        if (serviceContext.isAddGroupPermissions() ||
            serviceContext.isAddGuestPermissions()) {

            addEntryResources(
                entry, serviceContext.isAddGroupPermissions(),
                serviceContext.isAddGuestPermissions());
        }
        else {
            addEntryResources(
                entry, serviceContext.getGroupPermissions(),
                serviceContext.getGuestPermissions());
        }

`ServiceContext` helps apply categories, tag names, and the link entry IDs to
asset entries too. 

        // Asset

        updateAsset(
            userId, entry, serviceContext.getAssetCategoryIds(),
            serviceContext.getAssetTagNames(),
            serviceContext.getAssetLinkEntryIds());

Does `ServiceContext` also play a role in starting a workflow instance for the
blogs entry? Must you Ask? 

        // Workflow

        if ((trackbacks != null) && (trackbacks.length > 0)) {
            serviceContext.setAttribute("trackbacks", trackbacks);
        }
        else {
            serviceContext.setAttribute("trackbacks", null);
        }

        WorkflowHandlerRegistryUtil.startWorkflowInstance(
            user.getCompanyId(), groupId, userId, BlogsEntry.class.getName(),
            entry.getEntryId(), entry, serviceContext);

The snippet above also demonstrates the `trackbacks` attribute, a standard
attribute for the blogs entry service. There may be cases where you need to pass
in custom attributes to your blogs entry service. Use Expando attributes to
carry custom attributes along in your `ServiceContext`. Expando attributes are
set on the added blogs entry like this: 

        entry.setExpandoBridgeAttributes(serviceContext);

You can see that the `ServiceContext` can be used to transfer lots of useful
information for your services. 

Let's look at Message Bus next. 

## Using Message Bus [](id=lp-6-1-dgen09-using-message-bus-0)

The *Message Bus* is a service level API used to exchange messages within
Liferay. The Message Bus is a mechanism for sending message payloads to
different components in Liferay, providing loose coupling between message
producers and consumers to prevent class loading issues. It's located in
the global class loader, making it accessible to every deployed web application.
Remote messaging isn't supported, but messages are sent across a cluster
when ClusterLink is enabled. 

Message Bus has several common uses, including sending search index write
events, sending subscription emails, handling messages at scheduler endpoints,
and running asynchronous processes. 

You can leverage Message Bus to send messages between and within your plugins. 

As we show you Message Bus we'll talk about things like *synchronous* and
*asynchronous* messaging , *serial* vs. *in-parallel* message dispatching, and
Java and JSON style messages formats. 

Before we get into those topics, let's first try to understand Message Bus
System's architecture. 

<!--Is a subtopic list okay? -->

<!-- No; I changed it to a paragraph-based list. -Rich -->

<!--It looks like Rich changed the "common uses" list instead of the
"subtopics" list, so I left his change and also changed the subtopics list into
a paragraph. Russ--> 

### The Message Bus System [](id=lp-6-1-dgen09-the-message-bus-system-0)

The Message Bus system contains the following components: 

- **Message Bus**: Manages transfer of messages from message *senders* to message
  *listeners*. 
- **Destinations**: Addresses or endpoints to which *listeners* register to
  receive messages. 
- **Listeners**: Consume messages received at destinations. They receive all
  messages sent to their registered destinations.
- **Senders**: Invoke the Message Bus to send messages to destinations. 

Your services can send messages to one or more destinations, and can listen
to one or more destinations. The figure below depicts this. An individual
service can be both a message sender and a message listener. For example, in the
figure below both *Plugin 2 - Service 3* and *Plugin 5 - Service 7* send and
listen for messages. 

![Figure 11.4: Example, Message Bus system](../../images/msg-bus-system.png)

The Message Bus supports *synchronous* and *asynchronous* messaging: 

- **Synchronous messaging**: After it sends a message, the sender blocks waiting
  for a response from a recipient. 
- **Asynchronous messaging**: After it sends a message, the sender is free to
  continue processing. The sender can be configured to receive a call-back or
  can simply send and forget. We'll show you how to implement both synchronous
  and asynchronous messaging in this section. 
    - **Call-back**: The sender can include a call-back destination key as the
      response destination for the message. The recipient (listener) can then
      send a response message back to the sender via this response destination. 
    - **Send-and-Forget**: The sender includes no call-back information in the
      message sent and continues with processing.

Configuration of Message Bus is done using the following files:

- `WEB-INF/src/META-INF/messaging-spring.xml`: Specifies your destinations,
  listeners, and their mappings to each other. 
- `WEB-INF/web.xml`: Holds a listing of deployment descriptors for your plugin.
  Make sure you add `messaging-spring.xml` to your list of Spring configurations
  in this file.

---

![note](../../images/tip-pen-paper.png) **Note:** The internal file
`META-INF/messaging-core-spring.xml` of `portal-impl.jar` specifies the default
Message Bus class, default asynchronous message sender class, and default
synchronous message sender class for Liferay. 

---

You can control your *Message Types* by using either the `Message` or
`JSONObject` class. Liferay core services are typically serialized and
deserialized in JSON. In our examples we'll demonstrate both types of message
classes. 

So far we've introduced the Message Bus System, including message types,
destinations, senders, listeners, and approaches to sending messages. Next we'll
show you how easy it is to create your destinations, register listeners, and
send your messages. To demonstrate, we'll implement a business use case.

### Example Use Case--Procurement process [](id=lp-6-1-dgen09-example-use-case---procurement-process-0)

Our use case will consider Jungle Gyms R-Us and its distribution of playground
equipment, buying the equipment from manufacturers and selling the equipment to
retailers. We'll focus on the company's process for procuring new jungle gym
equipment. Let's lay out this process now.

Jungle Gyms R-Us employs the following departments in their procurement process: 

- *Procurement Department*: Scouts out the latest equipment deals of
  manufacturers.
- *Finance Department*: Determines whether the equipment can be purchased based
  on budget.
- *Legal Department*: Determines whether the equipment's safety ratings are
  acceptable.
- *Warehouse Department*: Recieves the equipment, stores it, and prepares it for
  shipping.
- *Sales Department*: Builds relationships with prospective customers to sell
  them products.

The departments currently use email to exchange comments about new equipment
purchases, but someone always seems to be left out of the loop. One time, Sales
was gung-ho about getting their hands on the latest and greatest spring rider
animals from Boingo-Boingo Industries, but they didn't consider the failing
safety reviews discovered by the Legal department, because the Legal department
forgot to copy the Sales department in their email to Procurement.  Tempers flew,
feelings were hurt, and everybody avoided hanging out in the company breakroom
for the next couple of weeks. 

<!-- Now that I see that this example is not a functional or even a potential
real-world example, I think we need to scrap the whole thing and come up with
another example, one that is integrated into an actual application. We could
conceivablly salvage it by making it into a purchase request application:
Departments have to enter purchase requests, and the message bus automatically
emails certain departments when a purchase request is entered. -Rich -->

Jungle Gyms R-Us could use Liferay's [Workflow with
Kaleo](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/workflow-with-kal-4)
to resolve the communication breakdown, but we'll resolve the Jungle Gym's
communication woes using Message Bus, to show you how it works. 
Here are the inter-department message exchanges we'll accommodate:

 Message | Sender | Listener | Response | Response Listeners |
-------- | ------ | -------- | -------- | ------------------ |
 Request permission to proceed with purchase | Procurement | Finance | required | Procurement |
 Request permission to proceed with purchase | Procurement | Legal | required | Procurement |
 Notify and solicit feedback on new purchase | Procurement | Warehouse | optional | Procurement, Sales |
 Notify and solicit feedback on new purchase | Procurement | Sales | optional | Procurement, Warehouse |
 Broadcast equipment news | Procurement | Employees | none | none |

Let's implement Procurement's request to Finance first. 

### Synchronous messaging [](id=lp-6-1-dgen09-synchronous-messaging-0)

In our example, equipment purchases can't proceed without approval from Finance
and Legal departments. Since special offers from the manufacturers often only
last for a couple hours, Procurement makes it their top priority to get approval
as soon as possible. Implementing their exchange using *synchronous* messaging
makes the most sense. 

![Figure 11.5: Synchronous messaging](../../images/msg-bus-sync-msg.png)

The following table describes how we'll set things up: 

 Destination Key | Type  | Sender | Receivers                                 |
---------------- | ----- | ------ | ----------------------------------------- |
 `jungle/finance/purchase`          | synchronous | Procurement | Finance |
 `jungle/finance/purchase/response` | synchronous | Finance | Procurement |
 `jungle/legal/purchase`            | synchronous | Procurement | Legal   |
 `jungle/legal/purchase/response`   | synchronous | Legal | Procurement   |

We've set it up so Finance sends its response messages to a destination on which
Procurement will listen. That way a full-bodied response message is sent back to
Procurement in addition to the response object returned from sending the
message. 

*The Procurement Department sends a purchase approval request:* 

<!--Where does this code go? messaging-spring.xml? If so is the code here
simply added to the file? And the code in subsequent descriptions too?-->

<!-- That's a very good question. This would generally be part of a portlet
application. This doesn't look like an actual, working demo, and I think we need
to talk to Jim about it. -Rich --> 

    Message message = new Message();
    message.put("department", "Procurement");
    message.put("partName", part.getName(Locale.US));

    message.setResponseId("1111");
    message.setResponseDestinationName("jungle/finance/purchase/response");

    try {
        String financeResponse = (String) MessageBusUtil.sendSynchronousMessage(
            "jungle/finance/purchase", message, 10000);

        System.out.println(
            "Procurement received Finance sync response to purchase approval for " +
            part.getName(Locale.US) + ": " + financeResponse);

        message.setResponseId("2222");
        message.setResponseDestinationName("jungle/legal/purchase/response");

        String legalResponse = (String) MessageBusUtil.sendSynchronousMessage(
            "jungle/legal/purchase", message, 10000);

        System.out.println(
            "Procurement received Legal sync response to purchase approval for " +
            part.getName(Locale.US) + ": " + legalResponse);

        if (financeResponse.contains("yes") && legalResponse.contains("yes")) {
            sendPurchaseNotification(part, userId);
        }
    }
    catch (MessageBusException e) {
        e.printStackTrace();
    }

This sender takes the following steps: 

1. Creates the message using Liferay's `Message` class.

2. Stuffs the message with key/value pairs.

3. Sets a response ID and response destination for listeners to use in replying
   back.

4. Sends the message to the destination with a timeout value of 10,000
   milliseconds.

5. Blocks waiting for the response.

*Finance Department listens for purchase approval requests and replies back:* 

    public class FinanceMessagingImpl implements MessageListener {

        public void receive(Message message) {
            try {
                doReceive(message);
            }
            catch (Exception e) {
                _log.error("Unable to process message " + message, e);
            }
        }

        protected void doReceive(Message message)
            throws Exception {

            String department = (String) message.get("department");
            String partName = (String) message.get("partName");

            System.out.println("Finance received purchase request for " +
                partName + " from " + department);

            Message responseMessage = MessageBusUtil.createResponseMessage(
                message);

            responseMessage.put("department", "Finance");
            responseMessage.put("partName", partName);
            responseMessage.setPayload("yes");

            MessageBusUtil.sendMessage(
                responseMessage.getDestinationName(), responseMessage);
        }

        private static Log _log =
            LogFactoryUtil.getLog(FinanceMessagingImpl.class);
    }

This listener executes the following steps: 

1. Implements the `receive(Message message)` method of the
   `com.liferay.portal.kernel.messaging.MessageListener` interface. 

2. Extracts values from the `Message` parameter by getting values associated
   with known keys. 

3. Creates a `Message` based on the message received via the
   `MessageBusUtil.createResponseMessage(message)` method, which accesses the
   response destination name from the `message` variable and sets the
   destination of the response message. 

4. Sets the response message's payload. 

5. Sends the response `Message` to the response destination.

You can implement the listener for the Legal Department similarly. Next we'll
account for Legal Department-related classes in our configuration.

*Message Bus Configuration for the purchase approval request process:*

For Message Bus to direct messages from destinations to listeners, we must
register the listeners by configuring the appropriate mappings in our plugin's
`WEB-INF/src/META-INF/messaging-spring.xml` file (create this file if it's not
already in your plugin). Here is the configuration: 

<!--If the messaging-spring file potentially hasn't been created yet where has
the reader been putting the code snippets from above? Should this statement go
before the first code snippet?--> 

<!-- The above snippets go in the service layer. If you follow the message bus
section in Liferay in Action, you'll see that the Java stuff goes in the service
layer's -Impl class. It's generally a best practice to write the Java classes
and then write the configuration file, because tools like Liferay IDE and
Liferay Developer Studio try to deploy the plugin right away, and if you declare
classes in the configuration file that don't exist yet, the whole plugin blows
up. -Rich -->

    <?xml version="1.0"?>

    <beans
        default-destroy-method="destroy"
        default-init-method="afterPropertiesSet"
        xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
    >

        <!-- Listeners -->

        <bean id="messageListener.finance_listener" class="com.liferay.training.parts.messaging.impl.FinanceMessagingImpl" />
        <bean id="messageListener.legal_listener" class="com.liferay.training.parts.messaging.impl.LegalMessagingImpl" />
        <bean id="messageListener.procurement_listener" class="com.liferay.training.parts.messaging.impl.ProcurementMessagingImpl" />

        <!-- Destinations -->

        <bean id="destination.finance.purchase" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/finance/purchase" />
        </bean>

        <bean id="destination.finance.purchase.response" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/finance/purchase/response" />
        </bean>

        <bean id="destination.legal.purchase" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/legal/purchase" />
        </bean>

        <bean id="destination.legal.purchase.response" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/legal/purchase/response" />
        </bean>

        <!-- Configurator -->

        <bean id="messagingConfigurator" class="com.liferay.portal.kernel.messaging.config.PluginMessagingConfigurator">
            <property name="messageListeners">
                <map key-type="java.lang.String" value-type="java.util.List">
                    <entry key="jungle/finance/purchase">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                            <ref bean="messageListener.finance_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/finance/purchase/response">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                            <ref bean="messageListener.procurement_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/legal/purchase">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                            <ref bean="messageListener.legal_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/legal/purchase/response">
                        <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                            <ref bean="messageListener.procurement_listener" />
                        </list>
                    </entry>
                </map>
            </property>
            <property name="destinations">
                <list>
                    <ref bean="destination.finance.purchase"/>
                    <ref bean="destination.finance.purchase.response"/>
                    <ref bean="destination.legal.purchase"/>
                    <ref bean="destination.legal.purchase.response"/>
                </list>
            </property>
        </bean>
    </beans>

The configuration above specifies the following beans: 

- *Listener beans*: Specify classes to handle messages.
- *Destination beans*: Specify the class *type* and *key* names of the
  destinations.
- *Configurator bean*: Maps listeners to their destinations.

When Finance sends its purchase approval request message for a new three-story
spiral slide, the console reports Finance's receipt of the message,
Procurement's receipt of the callback response from Finance, and Procurement's
receipt of the synchronous response returned from sending the message. Here's
what the console message looks like: 

    Finance received purchase request for three-story spiral slide from Procurement
    Procurement received Finance callback response to purchase approval for three-
    story spiral slide: yes
    Procurement received Finance sync response to purchase approval for three-story 
    spiral slide: yes
    Legal received purchase request for three-story spiral slide from Procurement
    Procurement received Legal callback response to purchase approval for three-
    story spiral slide: yes
    Procurement received Legal sync response to purchase approval for three-story 
    spiral slide: yes

Do you know what all those *yes* messages mean? Success! Jungle Gyms R-Us has
the cash to purchase this cool new slide, and the Legal Department has no gripes
about the slide's safety ratings! 

Next let's have Procurement notify the Sales and Warehouse departments and
solicit their feedback.

### Asynchronous messaging with callbacks  [](id=lp-6-1-dgen09-asynchronous-messaging-with-callbacks-0)

Asynchronous messaging consists of sending a message and then continuing with
processing without blocking waiting for an immediate response. This allows the
sender to continue with other tasks. It's often necessary, however, that the
listener can respond to the sender. This can be done using a call-back.

Jungle Gyms R-Us's Procurement Department must notify the Sales and Warehouse
departments of incoming equipment while simultaneously soliciting their
feedback. To assure all three departments are up to speed, any responses from
the Sales or Warehouse departments are posted to a shared destination. 

The following table describes how we'll set things up: 

| Destination Key            | Type  | Sender | Receivers                     |
---------------------------- | ----- | ------ | ----------------------------- |
  `jungle/purchase`          | async serial | Procurement |  Sales, Warehouse |
  `jungle/purchase/response` | synchronous  | Sales, Warehouse | Procurement  |

The following image shows asynchronous messaging, with serial dispatching of
messages: 

![Figure 11.6: Asynchronous messaging with *serial* dispatching](../../images/msg-bus-async-serial-msg.png)

Let's package the message as a `JSONObject` and send it to the destination: 

    JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

    jsonObject.put("department", "Procurement");
    jsonObject.put("partName", part.getName(Locale.US));
    jsonObject.put("responseDestinationName", "jungle/purchase/response");

    MessageBusUtil.sendMessage("jungle/purchase", jsonObject.toString());

Here's how the Warehouse Department listens for and handles messages: 

    public void receive(Message message) {

        try {
            doReceive(message);
        }
        catch (Exception e)
        {
            _log.error("Unable to process message " + message, e);
        }
    }

    protected void doReceive(Message message)
        throws Exception {

        String payload = (String)message.getPayload();

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

        String department = jsonObject.getString("department");
        String partName = jsonObject.getString("partName");
        String responseDestinationName = jsonObject.getString(
                        "responseDestinationName");

        System.out.println("Warehouse received purchase notification for " +
            partName + " from " + department);

        jsonObject = JSONFactoryUtil.createJSONObject();

        jsonObject.put("department", "Warehouse");
        jsonObject.put("partName", partName);
        jsonObject.put("comment", "Ugh! We're running out of space!!");

        MessageBusUtil.sendMessage(
            responseDestinationName, jsonObject.toString());
    }

Here's how this listener deserializes the `JSONObject` from the message: 

1. Gets the message payload and casts it to a `String`. 
2. Creates a `JSONObject` from the payload string. 
3. Gets values from the `JSONObject` using its getter methods.

The class also demonstrates how the Warehouse Department packages a response
message and sends it back to the Procurement Department, using these steps: 

1. Create a `JSONObject`.  
2. Stuff it with name-value pairs. 
3. Send the response message to the original message's response destination.

The Sales department listener can be implemented the same way, substituting
*Sales* as the department value; the comment would likely be different, too. 

You just used the `JSONObject` message type to send an asynchronous response
message using a call-back. 

Remember, we want the Procurement, Sales, and Warehouse departments to be
aware of any message regarding the new playground equipment purchasing process.
Let's leverage our destination keys and department names in handling shared
responses. 

Here's how the Warehouse might handle messages it receives:

    public void receive(Message message) {

        try {
            if (message.getDestinationName().equals(
                    "jungle/purchase"))
            {
                doReceive(message);
            }
            else if (message.getDestinationName().equals(
                    "jungle/purchase/response"))
            {
                doReceiveResponse(message);
            }
        }
        catch (Exception e)
        {
            _log.error("Unable to process message " + message, e);
        }
    }

    protected void doReceiveResponse(Message message)
        throws JSONException {

        String payload = (String)message.getPayload();

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

        String department = jsonObject.getString("department");

        if (!department.equals("Warehouse")) {
            System.out.println(
                "Warehouse is in the loop on response from " + department);
        }
    }

Let's look at `receive(Message)` for a minute. We've set it up to handle
messages differently depending on their destinations: messages to
`jungle/purchase` are handled as Procurement's purchase notifications, while
messages to `jungle/purchase/response` are treated as departmental responses to
Procurement's purchase notifications.  The `doReceiveResponse(Message)` method
performs an important task, checking that the response comes from a department
other than itself, and printing an error if it doesn't. 

Here are the configuration elements we added to the `messaging-spring.xml` from
the previous section:

*Listener beans*: 

        <bean id="messageListener.warehouse_listener" class="com.liferay.training.parts.messaging.impl.WarehouseMessagingImpl" />
        <bean id="messageListener.sales_listener" class="com.liferay.training.parts.messaging.impl.SalesMessagingImpl" />

*Destination beans*: The purchase notifications will be sent to a *serial*
  destination and the responses will be sent to a *synchronous* destination. 

        <bean id="destination.purchase" class="com.liferay.portal.kernel.messaging.SerialDestination">
            <property name="name" value="jungle/purchase" />
        </bean>

        <bean id="destination.purchase.response" class="com.liferay.portal.kernel.messaging.SynchronousDestination">
            <property name="name" value="jungle/purchase/response" />
        </bean>

*Configuration bean listener map entry*: Warehouse and Sales are registered
  to listen for the notifications from Procurement. All three departments are
registered to listen for inter-departmental responses.

        <entry key="jungle/purchase">
            <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                <ref bean="messageListener.warehouse_listener" />
                <ref bean="messageListener.sales_listener" />
            </list>
        </entry>
        <entry key="jungle/purchase/response">
            <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                <ref bean="messageListener.procurement_listener" />
                <ref bean="messageListener.warehouse_listener" />
                <ref bean="messageListener.sales_listener" />
            </list>
        </entry>

*Configuration bean destination list references*:

        <ref bean="destination.purchase"/>
        <ref bean="destination.purchase.response"/>

Don't forget to send news of these new products to *all* Jungle Gyms R-Us
employees.

<!-- Missing transition. --> 

### Asynchronous "Send and Forget" [](id=lp-6-1-dgen09-asynchronous-send-and-forget-0)

In the *send and forget* model, the sender sends messages and continues
processing. We'll apply this behavior to Jungle Gym's company-wide new product
notification.

Procurement isn't expecting response messages from individual employees, so
there's no need for the company-wide listener to package up responses. We do,
however, want everyone to get product news at the *same time*, so instead of
dispatching news to employees *serially* we'll dispatch *in parallel*.

![Figure 11.7: Asynchronous messaging with *parallel* dispatching](../../images/msg-bus-async-parallel-msg.png)

We'll specify a parallel destination type in our `messaging-spring.xml`:

*Destination bean*:

        <bean id="destination.employee.news" class="com.liferay.portal.kernel.messaging.ParallelDestination">
                <property name="name" value="jungle/employee/news" />
        </bean>

*Listener bean*:

        <bean id="messageListener.employee_listener" class="com.liferay.training.parts.messaging.impl.EmployeeMessagingImpl" />

*Configuration bean listener map entry*: 

        <entry key="jungle/employee/news">
            <list value-type="com.liferay.portal.kernel.messaging.MessageListener">
                <ref bean="messageListener.employee_listener" />
            </list>
        </entry>

*Configuration bean destination list reference*: 

        <ref bean="destination.employee.news"/>

Congratulations! You implemented inter-departmental communications for the
procurement process at Jungle Gyms R-Us.

Along the way you used Message Bus to implement the following:

- Sender, listener, and destination components. 
- Synchronous and Asynchronous messaging schemes. 
- *Serial* and *parallel* message dispatching. 
- Java and JSON message types.

Next we'll show you the Device Detection API and its capabilities. 

## Device Detection [](id=device-detection)

As you know, Internet traffic has risen exponentially over the past decade and
shows no sign of stopping. With the latest and greatest devices, mobile Internet
access has become the norm and is predicted to pass PC based Internet access
soon. Because of the mobile boom, new obstacles and challenges are presented for
content management. How will content adapt to all devices with different
capabilities? How can your grandma's gnarly tablet and cousin's awesome new
mobile phone request the same information from your portal?

The Device Detection API detects the capabilities of any device making a
request to your portal. It can also determine what mobile device or operating
system was used to make a request, and then follows rules to make Liferay render
pages based on the device. To use this feature, you first need to install the
*Device Recognition Provider* app from Liferay Marketplace. Find more
information on the app by following one of these links, depending on whether you
use Liferay CE or EE: [Device Recognition
CE](http://www.liferay.com/marketplace/-/mp/application/15193341) or [Device
Recognition EE](http://www.liferay.com/marketplace/-/mp/application/15186132). 

The Device Recognition plugin comes bundled inside the Device Recognition
Provider app; it uses a device database called *WURFL* to determine the
capabilities of your device. Visit the WURFL website for more information at
[http://wurfl.sourceforge.net/](http://wurfl.sourceforge.net/).

You can create your own plugin to use your device's database. Let's look at
some simple uses of the Device Detection API and talk about its
capabilities. 

### Using the Device API [](id=using-the-device-api)

Let's look at a couple of code snippets to get you started. You can obtain the
object `Device` from the `themeDisplay` object like this:

    Device device = themeDisplay.getDevice();

You can view the API in the [Device
Javadocs](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/kernel/mobile/device/Device.html).
Using some of the methods from the Javadocs, here's an example that obtains a
device's dimensions:

    Dimensions dimensions = device.getScreenSize();
    float height = dimensions.getHeight();
    float width = dimensions.getWidth();

Now your code can get the `Device` object and the dimensions of a device.
Of course this is just a simple example; you can acquire many other device
attributes that help you take care of the pesky problems that arise when sending
content to different devices. You can refer to the Device Javadocs mentioned
above for assistance.  Let's look at some device capabilities next.

### Device capabilities [](id=device-capabilities)

Most of the capabilities of a device can be detected, depending on the device
detection implementation you're using. The Device Recognition plugin's device
database (WURFL) has a list of capabilities
[here](http://www.scientiamobile.com/wurflCapability/tree). For example, you
can obtain the capability of a brand name with this code:

    String brand = device.getCapability("brand_name");

You can grab many other device capabilities, including `model_name`,
`marketing_name`, and `release_date`. You can also get boolean values like
`is_wireless_device`, `is_tablet`, etc. 

With the Device Detection API, you can detect the capabilities of a device
making request to your portal and render content accordingly; so your grandma's
gnarly tablet and your cousin's awesome new mobile phone can make requests to
your portal and receive identical content. This will make everyone happy! 

You're really getting the hang of Liferay's APIs. Way to go! 

## Liferay's Deprecation Policy [](id=liferay-portal-6-1-api-deprecation-policy)

Methods in Liferay's APIs are deprecated when they're no longer called by
Liferay internally. Method deprecation occurs during major releases of Liferay.
You'll know when there's a major release if you understand Liferay's
release version notation. Liferay version numbers consist of a three digit
number. Knowing what each digit represents is key, so let's consider a case
where you were using Liferay 6.0.2, and Liferay 6.0.3 has just been released:

- Digit 1 (**6**.0.3): The first digit represents the major release version,
  which is a *feature release*. Along with changes to Liferay's architecture,
  internal schema, and the introduction of new functionality, API methods get
  deprecated during feature releases. In our example, this number hasn't
  changed, so any API methods we use in our custom development are intact. 
- Digit 2 (6.**0**.3): The second digit indicates the minor release. Minor
  releases involve the introduction of new features and bug fixes. While
  customization might be affected when installing, API methods don't get
  deprecated. For our example, this didn't change.  feature release. It also did
  not change in our example. 
- Digit 3 (6.0.**3**): The third digit represents the maintenance release, or
  fix pack release. In our case, the third digit changed from *2* to *3*, so we
  discovered a maintenance release. In terms of API method deprecation, you're
  in the clear; in acoordince with our policy, methods you used in your custom
  development haven't been deprecated. 

<!-- I think this whole section is incorrect. It should be synced with this
(focusing on the deprecation policy, of course): https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/editions-of-lifer-4 -->

<!--I updated it based on the user guide. Please review -Russ -->

## Summary [](id=conclusi-4)

We've covered a lot of ground here. You've learned how to use Liferay's remote
APIs, how to enable and disable remote services and access to them, and how to
leverage `ServiceContext` objects in your use of Liferay services. You've
really tested your neural net processor in working with Message Bus, and you've
detected your grandma's tablet with Liferay's device detection API. Well done!
Next we'll take a look at some of the powerful frameworks of Liferay Portal,
learn how they work and how you can leverage them. 
