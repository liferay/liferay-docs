# Accessing Services Remotely 

You've created your portlet and built some terrific services. You're happy to
brag to your colleagues about the awesome things your portlet does. And you've
started to prick their interest; they want to call your portlet's services. You
wonder whether this will be difficult and you start asking yourself questions.
How do publish my services? How do my clients find my services? How can
consumers call my services efficiently? No worries. We'll answer all of these
questions on accessing remote services. 

Here are the topics we'll cover in this chapter:

- Finding Services 
- Invoking the API Remotely 
- Service Security Layers 
- SOAP Web Services 
- JSON Web Services 

## Finding Services 

You can find Liferay's services by searching for them in the Javadocs:
[http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/).
Below we'll show you how to search for portal services and portlet services.

Let's start by finding a portal service. 

### Finding Portal Services

Liferay's Javadocs are easy to browse and well-organized. Here's how you find
the *Organization* services: 

1. In your browser, open up the Javadocs:
   [http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/) 

2. Under *Portal Services*, click the link for the `com.liferay.portal.service`
   package, since the services for the Organization entity belong to the
   *Portal* scope. 

3. Find and click on the `-ServiceUtil` class (in this case,
   `OrganizationLocalServiceUtil`) in the *Class Summary* table or the
   *Classes* list at the bottom of the page. 

That was easy! What if you want to find portlet services? 

### Finding Portlet Services

Searching for one of Liferay's built-in portlet services is also easy. Instead
of clicking the link for the service package of the *portal*, click the link for
the service package of the *portlet*. The portlet service packages use the
naming convention `com.liferay.portlet.[portlet-name].service`, where
`[portlet-name]` is replaced with the actual name of the portlet. 

Here's how you find services for a user's blogs statistics:

1. In your browser, open the Javadocs:
   [http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/)

2. Under *Portlet Services*, click the link for the
   `com.liferay.portlet.blogs.service` package in the *Packages* frame, since the
   services are a part of the *blogs portlet*. 

3. Find and click on the `-ServiceUtil` class (in this case
   `BlogsStatsUserLocalServiceUtil`) in the *Class Summary* table or the
   *Classes* list. 

Now you're ready to invoke Liferay services.

## Invoking the API Remotely 

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
supports Java invocation and a variety of protocols including SOAP, JSON over
HTTP, Burlap, Hessian, and more. A limited set of *RESTful* web services, based
on the AtomPub protocol, are also supported--see the [Portal Atom
Collections](http://www.liferay.com/community/wiki/-/wiki/Main/Portal+Atom+Collections)
wiki by Igor Spasi&#263; for more details. You can also use the API
through Remote Procedure Calls
([RPC](http://en.wikipedia.org/wiki/Remote_procedure_call)). You have many good 
options for leveraging Liferay's API. 

<!-- This Burlap and Hessian statement has been around for a while, yet I've
never actually seen an implementation of it. Has this statement been vetted by
somebody who knows the remote API intimately? -Rich --> 

Let's step back now and discuss the security layers of Liferay's *service
oriented* architecture and how you can configure them. 

## Service Security Layers 

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

![Figure 5.1:  Liferay SOA's first layer of security](../../images/soa-security-layer-1.png)

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

![Figure 5.2: Liferay SOA's second layer of security](../../images/soa-security-layer-2.png)

With remote services, you can specify the user credentials using HTTP Basic
authentication. Since those credentials are specified unencrypted; it's
recommended to use HTTPS whenever accessing these services in an untrusted
network. Most HTTP clients let you specify the Basic authentication credentials
in the URL--this is very handy for testing.

You'd use the following syntax to call the AXIS web service using credentials.
Be sure to remove the line escape character `\` when entering your URL:

    http://" + userIdAsString + ":" + password + "@[server.com]:[port]/api/\
    secure/axis/" + serviceName

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

## SOAP Web Services 

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

![note](../../images/tip-pen-paper.png) **Note:** Liferay's developers use a
tool called *Service Builder* to expose their services via SOAP automatically.
If you're interested in using Service Builder, read Chapter 4. 

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

To use the service, you pass in the WSDL URL along with your login credentials
to the SOAP service locator for your service. We'll show you an example in the
next section. 

Next, let's invoke the web service! 

### SOAP Java Client 

A Java web service client can easily be set up using Eclipse IDE. Here's how: 

In Eclipse, add a new Web Service Client to your project for each service you
plan to consume in your client code. For our purposes, the client we're building
needs a Web Service Client for the portal's `Company`, `User`, and `UserGroup`
services. 

To add your Web Service Clients in Eclipse IDE, click *New* &rarr; *Other...*,
then expand the *Web Services* category. Click *Web Service Client*.

For each client you create, you're prompted to enter the service definition
(WSDL) for the desired service. Here's an example WSDL: 

    http://localhost:8080/api/axis/Portal_UserService?wsdl

![Figure 5.3: Service Definition](../../images/api-web-svc-wsdl.png)

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
    java.rmi.RemoteException: No UserGroup exists with the key {companyId=10154,
    name=MyUserGroup}
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
  end of the URL. Remember that the service name can be found in the web service
  listing. 

The operations `getCompanyByVirtualHost()`, `getUserIdByScreenName()`,
`getUserUserGroups()`, `addUserGroup()` and `addUserGroupUsers()` are specified
for the `-ServiceSOAP` classes `CompanyServiceSoap`, `UserServiceSoap` and
`UserGroupServiceSoap` in the WSDL files. Information on parameter types,
parameter order, request type, response type, and return type are conveniently
specified in the WSDL for each Liferay web service. It's all there for you!

Next let's implement a web service client implemented in PHP. 

### SOAP PHP Client 

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

## JSON Web Services 

JSON Web Services let you access portal service methods by exposing them as a
JSON HTTP API. Service methods are made easily accessible using HTTP requests,
both from JavaScript within the portal and from any JSON-speaking client. 

We'll cover the following topics as we explore JSON Web Service functionality:

- Registration
- Configuration
- Invocation 
- Results

Let's talk about registering JSON Web Services next. 

### Registering JSON Web Services 

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

    DEBUG [JSONWebServiceConfigurator:121] Configure JSON web service actions
    DEBUG [JSONWebServiceConfigurator:136] Configuring 820 actions in ... ms

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

#### Registering Plugin JSON Web Services 

Custom portlets can be registered and scanned for JSON web services, too.
Services that use the `@JSONWebService` annotation become part of the JSON API.
Scanning of portlet services isn't enabled by default; the following servlet
definition must be added in your portlet's `web.xml`: 

    <web-app>
        ...
        <filter>
            <filter-name>
            Secure JSON Web Service Servlet Filter
            </filter-name>
            <filter-class>
            com.liferay.portal.kernel.servlet.PortalClassLoaderFilter
            </filter-class>
            <init-param>
                <param-name>
                filter-class
                </param-name>
                <param-value>
                com.liferay.portal.servlet.filters.secure.SecureFilter
                </param-value>
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
            <servlet-name>
            JSON Web Service Servlet
            </servlet-name>
            <servlet-class>
            com.liferay.portal.kernel.servlet.PortalClassLoaderServlet
            </servlet-class>
            <init-param>
                <param-name>
                servlet-class
                </param-name>
                <param-value>
                com.liferay.portal.jsonwebservice.JSONWebServiceServlet
                </param-value>
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

#### Mapping and Naming Conventions 

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
    custom portlet's context). Be sure to remove the line escape character `\`
    for your URL:

        http://[server]:[port]/[plugin-context]/api/jsonws/[service-class-name]/\
        [service-method-name]

    This calls the plugin's service in a separate web application that is not
    aware of the user's current session in the portal. As a result, accessing
    the service in this manner requires additional authentication. You might use
    this for batch services or other requests that don't require context. 

-   *Option 2*: Accessing the plugin service via the portal context. Be sure to
    remove the line escape character `\` in your URL:

        http://[server]:[port]/[portal-context]/api/jsonws/[plugin-context].\
        [service-class-name]/[service-method-name]

    Conveniently, requests sent this way can leverage the user's authentication
    in his current portal session. Liferay's JavaScript API for services calls
    plugin services using this method.

NExt we'll learn to available JSON Web Services. 

#### Listing Available JSON Web Services 

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

#### Ignoring a Method 

To keep a method from being exposed as a service, annotate the method with the
following option:

    @JSONWebService(mode = JSONWebServiceMode.IGNORE)

Methods with this annotation don't become part of the JSON Web Service API. 

Let's learn to define custom HTTP method names and URL names. 

#### HTTP Method Name and URL 

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

#### Manual Registration Mode 

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

### Portal Configuration of JSON Web Services 

JSON Web Services are enabled on Liferay Portal by default. If you need to
disable them, specify this portal property setting: 

    json.web.service.enabled=false

Now let's look at strict HTTP methods. 

#### Strict HTTP Methods 

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

#### Disabling HTTP Methods 

When strict HTTP method mode is enabled, you can filter web service access based
on HTTP methods used by the services. For example, you can set the portal JSON
Web Services to work in read-only mode by disabling HTTP methods other than GET.
For example: 

    jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

Now all requests that use HTTP methods from the list above are ignored.

Next we'll show you how to restrict public access to exposed JSON APIs. 

#### Controlling Public Access 

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

### Invoking JSON Web Services 

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

#### Using Hints 

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

#### Passing Parameters as Part of a URL Path 

You can pass parameters as part of the URL path. After the service URL, just
specify method parameters in name-value pairs. Parameter names must be formed
from method argument names by converting them from camelCase to names using all
lower case and separated-by-dash. Here's an example: 

    http://localhost:8080/api/secure/jsonws/dlapp/get-file-entries/repository-id/\
    10172/folder-id/0

Note, we've inserted line escape character `\` in order to fit the example URL
on this page. 

You can pass parameters in any order; it's not necessary to follow the order in
which the arguments specified in the method signatures. 

When a method name is overloaded, the *best match* will be used. It chooses the
method that contains the least number of undefined arguments and invokes it for
you. 

You can also pass parameters in a URL query, and we'll show you how next. 

#### Passing Parameters as a URL Query 

You can pass in parameters as request parameters. Parameter names are specified
as is (e.g. camelCase) and are set equal to their argument values, like this: 

    http://localhost:8080/api/secure/jsonws/dlapp/get-file-entries?repositoryId=\
    10172&folderId=0

Note, we've inserted line escape character `\` in order to fit the example URL
on this page.

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

- *Step 1--JSON deserialization*: JSON arrays are converted into `List<String>`
  and JSON objects are converted to `Map<String, String>`. For security reasons,
  it is forbidden to instantiate any type within JSON deserialization. 
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

#### Sending NULL Values 

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

#### Encoding Parameters

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

#### Sending Files as Arguments 

Files can be uploaded using multipart forms and requests. Here's an example: 

    <form
     action="http://localhost:8080/api/secure/jsonws/dlapp/add-file-entry"
     method="POST"
     enctype="multipart/form-data">
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

#### JSON RPC 

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

#### Default Parameters 

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

#### Object Parameters 

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

#### Inner Parameters 

When you pass in an object paramter, you'll often need to populate its inner
parameters (i.e., fields). Consider a default parameter `serviceContext` of type
`ServiceContext` (see the *ServiceContext* section in this chapter to find out
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

### Returned Values 

No matter how a JSON web service is invoked, it returns a JSON string that
represents the service method result. Returned objects are *loosely* serialized
to a JSON string and returned to the caller. 

Let's look at some values returned from service calls. We'll create a
`UserGroup` as we did in our SOAP web service client examples. To make it easy,
we'll use the test form provided with the JSON web service in our browser. 

1.  Open your browser to the JSON web service method that adds a `UserGroup`: 

        http://127.0.0.1:8080/api/jsonws?signature=/usergroup/add-user-group-2-\
        name-description

    Note, we've inserted line escape character `\` in order to fit the example
    URL on this page.

    Alternatively, navigate to it by starting at
    `http://127.0.0.1:8080/api/jsonws` then scrolling down to the section for
    *UserGroup*; click *add-user-group*. 

2.  In the *name* field enter *UserGroup3* and set the description to an
    arbitrary value like *Created using JSON WS*.

3.  Click *Invoke* and you'll get a result similar to the following: 

        {"addedByLDAPImport":false,"companyId":10154,"description":"Created
        using JSON WS","name":"MyUserGroup33","parentUserGroupId":0,
        "userGroupId":13162}

The returned `String` represents the `UserGroup` object you just created,
serialized into a JSON string. To find out more about JSON strings, go to
[json.org](http://www.json.org/).

To learn about serializing Java objects, maps and lists, check out the article
[JSON
Serialization](http://www.liferay.com/community/wiki/-/wiki/Main/JSON+Serialization)
by Igor Spasi&#263;. 

Let's check out some common JSON WebService errors. 

### Common JSON Web Service Errors 

While working with JSON Web Services, you may encounter errors. Let's look at
the most common errors in the following subsections. 

<!--I would have put the subsections in an unordered list, but I'll leave them
for now. Which is preferred?  Are they long enough to warrant subsections? --> 

<!-- No, they're not long enough. In fact, that's been a problem with this whole
chapter. I removed some sections already. One way to fix some of the "missing
transition" problems above would be to combine some of the sections. -Rich -->

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

Next we'll show you how to optimize your use of JSON Web Services by using the
*JSON Web Services Invoker*. 

### JSON Web Services Invoker 

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

#### A Simple Invoker Call 

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

Next see how you can use nested service calls to join information from two
related objects. 

#### Nesting Service Calls 

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
- The Invoker called the Java service mapped to `/contact/get-contact-by-id` by 
  using the `contactId` parameter, with the `$user.contactId` value from the
  object `$user`. 
- The resulting contact object was assigned to the variable `$contact`. 
- Lastly, the Invoker injected the contact object referenced by `$contact` into
  the user object's property named `contact`. 

---

![note](../../images/tip-pen-paper.png) **Note:** You must *flag* parameters
that take values from existing variables. To flag a parameter, insert the `@`
prefix before the parameter name. 

---

Next let's talk about filtering object properties so only those you
need are returned when you invoke a service. 

#### Filtering Results 

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

#### Batching Calls 

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

## Summary

In this chapter, we showed you how easy it is to find and invoke services. We
also explained how Liferay's service security layers are used to protect your
data. Then, we dove into SOAP web services and showed you how to invoke them.
Lastly, we jumped into JSON web services to register them and invoke them in a
myriad of ways. You see, here at Liferay, we aim to give you terrific service! 

Next, we'll take a look at some of the powerful frameworks of Liferay Portal,
learn how they work and how you can leverage them. 

