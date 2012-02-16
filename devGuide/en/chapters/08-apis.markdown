﻿
# Liferay APIs

This chapter provides an overview of several of the essential Liferay *application programming interfaces* (*APIs*) available to developers. An API is a programing interface that can be invoked from your own code, either directly through a Java invocation or through web services, to perform an action or set of actions.

Liferay comes with a host of APIs that expose powerful portal and portlet services. You can access Liferay's built-in *portal* services for such entities as User, Organization, UserGroup, Company, Role, Permission, ResourcePermission and Layout. In addition, you can access Liferay's built-in *portlet* services for such portlets as those for blogs, wikis, assets, social equity, and the Documents & Media Library.

The APIs can be called via Java method or web service invocations giving you flexibility to implement client code in Java or other languages (even a scripting language such as PHP) that support web service invocations.  The APIs can be called from within a portlet or non-portlet code. You can make calls to the APIs *locally* from within the portal or *remotely* outside of the portal's *Java virtual machine* (*JVM*).

This chapter covers the following topics:

-	*Local API:* Using Liferay services locally, from the same JVM as the portal.
	
-	*Remote API:* Using Liferay services remotely, outside of the portal's JVM and from machines other than the portal host.

-	*Service Security:* Leveraging the service security layers provided in Liferay's service oriented architecture (SOA).

-	*SOAP Web Services:* Consuming services via Liferay's SOAP interface.

-	*JSON Web Services:* Consuming services via Liferay's JSON service interface.

-	*Service Context:* Understanding what the service context is, how to it can be used in services, and how to use it in calling services.

First, let's consider Liferay's service oriented architecture and the layers of securtiy involved with the services.

## Local API

Each service built with *Service Builder* provides a local interface to clients running in the same JVM as the portal. Since Liferay's portal and portlet services are built using the Service Builder, their public service methods can be accessed through static methods provided by auto-generated `-LocalServiceUtil` classes.

So, how do you find the services? Just look it up in our Javadocs.

For example, here is how you lookup the Organization services:

1.	In your browser, open up the Javadocs at [http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/).

2.	Since the services for the Organization entity belong to the portal, click on the link for the `com.liferay.portal.service` package in the *Packages* frame.

3.	Find and click on the `-ServiceUtil` class (in this case `OrganizationLocalServiceUtil`) in the class summary table or the list of classes.

It's just that easy!

Similarly, if you want to search for one of Liferay's built-in portlet services, no problem. But, when looking up the package, instead of clicking on the linke for  the `com.liferay.portal.service` package you click on the link for the portlet's service package. The portlet service packages use the naming convention `com.liferay.portlet.[portlet-name].service`, where `[portlet-name]` is replaced with the actual name of the portlet.

For example, to lookup the services for user's blogs statistics you would do the following:

1.	In your browser, open up the Javadocs at [http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/).

2.	Since the services are a part of the blogs portlet, click on the link for the `com.liferay.portlet.blogs.service` package in the *Packages* frame.

3.	Find and click on the `-ServiceUtil` class (in this case `BlogsStatsUserLocalServiceUtil`) in the class summary table or the list of classes.

So, now that you know how to lookup the service classes, let's look at the following JSP code snippet that demonstrates how to get a list of the most recent bloggers from an organization.

    <%@ page import="com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil" %>
    <%@ page import="com.liferay.portlet.blogs.util.comparator.StatsUserLastPostDateComparator" %>
    ...
    <%@
    List statsUsers = BlogsStatsUserLocalServiceUtil.getOrganizationStatsUsers(
        organizationId, 0, max, new StatsUserLastPostDateComparator());
    %>

This JSP code invokes static method `getOrganizationStatsUsers()` on the `-LocalServiceUtil` class `BlogsStatsUserLocalServiceUtil`.

Next, let's consider how to invoke services remotely.

## Remote API

Services are also made available to *remote* clients (clients running outside of the portal JVM). Service Builder generates remote interfaces for your entities if their `remote-service` attribute is set to `true` in your portlet's `service.xml`. Here is an example of how remote services are turned on for Liferay's `Organization` entity in the `service.xml` for the portal:

    <entity name="Organization" local-service="true" remote-service="true">

The generated services can be accessed using [RPC](http://en.wikipedia.org/wiki/Remote_procedure_call), [SOA](http://en.wikipedia.org/wiki/Service-oriented_architecture) or [REST](http://en.wikipedia.org/wiki/Representational_state_transfer). We'll explore how to access and invoke web services in a variety of ways. 

But first, we'll step back for a moment and consider the security layers of Liferay's *service oriented* architecture and how they can be configured.

## Service Security Layers

Liferay's Service Builder utility is used to generate all of the low level code for accessing resources from the portal database. This utility is further explained in *Liferay in Action*, but it is mentioned here because of its feature which generates interfaces not only for Java code, but also for web services and JavaScript. This means that the method calls for storing and retrieving portal objects are all the same, and are generated in the same step.

![Figure 8.1:  Liferay SOA's first layer of security](../../images/soa-security-layer-1.png)

Because the actual method calls for retrieving data are the same regardless of how one gets access to those methods (i.e. locally or through web services), Liferay provides a consistent interface for accessing portal data that few other products can match. The actual interfaces for the various services are covered in *Liferay in Action*, but before they can be used there are steps that need to be taken to enable users to access those services remotely.

In the default `portal.properties` file, there is a section called **Main Servlet**. This section defines the security settings for all of the remote services provided by Liferay. Copy this section and paste it into your custom `portal-ext.properties` file, and you can configure security settings for the Axis Servlet, the Liferay Tunnel Servlet, the Spring Remoting Servlet, the JSON Servlet, the JSON Web Service Servlet, and the WebDAV Servlet.

By default, a user connecting from the same machine Liferay is running on can access remote services so long as that user has the permission to use those services in Liferay's permissions system. Of course, you are not really "remote" unless you are accessing services from a different machine. Liferay has two layers of security when it comes to accessing its services remotely. Without explicit rights to both layers, a remote exception will be thrown and access to those services will not be granted.

The first layer of security that a user needs to get through in order to call a method from the service layer is servlet security. The *Main Servlet* section of the `portal-ext.properties` file is used to enable or disable access to Liferay's remote services. In that section of the properties file, there are properties for each of Liferay's remote services.

You can set each service individually with the security settings that you require. For example, you may have a batch job which runs on another machine in your network. This job looks in a particular shared folder on your network and uploads documents to your community's document library portlet on a regular basis, using Liferay's web services. To enable this batch job to get through the first layer of security, you would modify the `portal-ext.properties` file and put the IP address of the machine on which the batch job is running in the list for that particular service. For example, if the batch job uses the Axis web services to upload the documents, you would enter the IP address of the machine on which the batch job is running to the `axis.servlet.hosts.allowed` property. A typical entry might look like this:

	axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, SERVER_IP

If the machine on which the batch job is running has the IP address `192.168.100.100`, this configuration will allow that machine to connect to Liferay's web services and pass in user credentials to be used to upload the documents.

![Figure 8.2: Liferay SOA's second layer of security](../../images/soa-security-layer-2.png)

The second layer of security is Liferay's security model that it uses for every object in the portal. The user ID that accesses the services remotely must have the proper permission to operate on the objects it will be accessing. Otherwise, a remote exception will be thrown. The Portal Administrator will need to make use of Liferay's usual means of granting access to these resources to the user ID that will be operating on them remotely. For example, say that a Documents and Media Library folder called *Documents* has been set up in a site. A role has been created called *Document Uploaders* which has the rights to add documents to this folder. Your batch job will be accessing Liferay's web services in order to upload documents into this folder. In order for this to work, you will have to call the web service using a user ID that is a member of this group (or that has individual rights to add documents to this folder). Otherwise, you will be prevented from using the Web Service.

To call the AXIS web service using credentials, you would use the following URL syntax:

	http://" + userIdAsString + ":" + password + "@[server.com]:[port]/api/secure/axis/" + serviceName

The user ID is the user's ID from the Liferay database. This may be obtained by logging in as the user and clicking *My Account* from the Dock. In the top left corner of the portlet that appears is the user ID.

For example, to get Organization data using a user that has the ID of *2* with a password of *test*, you would use the following URL:

	http://2:test@localhost:8080/api/secure/axis/Portal_OrganizationService

It is important to note here how *Password Policies* (see chapter *Administering Liferay Portal* of [Using Liferay Portal 6.1](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/managing-users-user-groups-organizations-sites-teams-and-roles)) can be used in combination with this feature. If you are enforcing password policies on your users (requiring them to change their passwords on a periodic basis, etc.), any administrative ID which accesses Liferay's web services in a batch job will have its password expire too.

To prevent this from happening, you can add a new password policy which does not enforce the password expiration and add your administrative user ID to it. Then your batch job can run as many times as you need it to, and the administrative ID's password will never expire.

In summary, accessing Liferay remotely requires the successful passing of two security checks:

1.  The IP address must be pre-configured in the server's `portal-ext.properties` file.

2.  The user ID being used must have permission to access the resources it is attempting to access.

Next, we'll learn about Liferay's SOAP web services.

## SOAP Web Services

Service Builder generates classes to use your services via *Simple Object Access Protocol* (*SOAP*) over HTTP. SOAP is the *packaging* protocol and HTTP is the *transport* protocol. For our example, we'll look at the SOAP web service classes for Liferay's `Company`, `User`, and `UserGroup` portal services to accomplish the following:

1. List each UserGroup to which user `test` belongs

2. Add a new UserGroup named `MyGroup`

3. Add user `test` to the UserGroup

Here are the SOAP related classes that we'll use:

    import com.liferay.portal.model.CompanySoap;
	import com.liferay.portal.model.UserGroupSoap;
	import com.liferay.portal.service.http.CompanyServiceSoap;
	import com.liferay.portal.service.http.CompanyServiceSoapServiceLocator;
	import com.liferay.portal.service.http.UserGroupServiceSoap;
	import com.liferay.portal.service.http.UserGroupServiceSoapServiceLocator;
	import com.liferay.portal.service.http.UserServiceSoap;
	import com.liferay.portal.service.http.UserServiceSoapServiceLocator;

You can see in the listing a naming convention involving classes with suffixes `-ServiceSoapServiceLocater`, `-ServiceSoap`, and `-Soap`. The `-ServiceSoapServiceLocater` class *finds* the `-ServiceSoap` by means of the service's URL you provide. The `-ServiceSoap` class implements the Java Remote Method Invocation (RMI) interface bound to the the services specified in the *Web Services Definition Language* (*WSDL*) file for each service. Lastly, the `-SOAP` classes are the serializeable implementations of the models.  Let's look at how to determine the URLs for these services.

Service Builder was used to publish these services as web services.  You can view a listing of the services deployed on your portal by opening your browser to the URL of the format `http://[host]:[port]/api/secure/axis` for your *secure* services (services requiring user authentication) and the URL of the format `http://[host]:[port]/api/axis` for your services that do not require user authentication. For demonstration, we're using *secure* services. Here are the web services for `UserGroup`:

![Figure 8.3: UserGroup Web Service listing](../../images/wsdl-summary-listing.png)

Each web service is listed with its name, operations, and a link to its WSDL file. The WSDL is written in XML and provides a model for describing and locating the web service.

![Figure 8.4: WSDL Excerpt for the addUserGroup operation of UserGroup](../../images/wsdl-for-user-group-service.png)

As you'll see in the example in the next section, you pass in the WSDL URL along with your login credentials to the SOAP service locator for your service.

Next, let's invoke the web service!

### Enabling SOAP Web Services

First of all, the host must be allowed to access the remote services by configuring the portal-ext.properties file. By default, the portal.properties has the following settings to enable the services be accessed remotely:

axis.servlet.hosts.allowed=127.0.0.1,SERVER_IP
axis.servlet.https.required=false

The code above shows the IPs whick can access the services. You can input a list of comma delimited IPs that can access this servlet. You also can input a blank list to allow any IP to access this servlet. SERVER_IP will be replaced with the IP of the host server. This is the reason that you can access web services only locally if you don't change this list.

Besides, the user must have permission to access the portal resources.
To authenticate an user remotely we have to change the authentication method to screenName or userId:

company.security.auth.type=screenName

Type http://localhost:8080/api/axis in your browser and see a list of SOAP services.
To access the wsdl, click on the wsdl for any of the service.

PS: In old Liferay versions you could access that services by http://localhost:8080/tunnel-web/axis, however in Liferay 6.1 this path has changed and when you type it, you are redirected to the new one.

### SOAP Java Client

A Java web service client can easily be setup using the Eclipse IDE. Here is how you can do it:

Add a new *Web Service Client* to your Project for each service you plan to consume in your client code. For the purposes of the client we're going to build, we'll want to add a *Web Service Client* for the portal's Company, User, and UserGroup services.

![Figure 8.5:  New Web Service Client](../../images/api-new-web-svc-client.png)

When creating each client, you will need to enter the service definition (WSDL) for the desired service.

For example:
	http://localhost:8080/api/axis/Portal_UserService?wsdl

![Figure 8.6:  Service Definition](../../images/api-web-svc-wsdl.png)

With the WSDL specified, Eclipse automatically adds the auxiliary files and libraries required to consume that web service!

Next, let's look at the code we'll use to locate and invoke operations to add a new UserGroup named `MyUserGroup` and assign to it a User with screen name `test`:

	import java.net.URL;

	import com.liferay.portal.model.CompanySoap;
	import com.liferay.portal.model.UserGroupSoap;
	import com.liferay.portal.service.http.CompanyServiceSoap;
	import com.liferay.portal.service.http.CompanyServiceSoapServiceLocator;
	import com.liferay.portal.service.http.UserGroupServiceSoap;
	import com.liferay.portal.service.http.UserGroupServiceSoapServiceLocator;
	import com.liferay.portal.service.http.UserServiceSoap;
	import com.liferay.portal.service.http.UserServiceSoapServiceLocator;

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

				System.out.println("User groups for userId " + userId +	" ...");
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

				System.out.println("User groups for userId " + userId +	" ...");
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

As a result of running this client you should get output similar to the following:

	userId for user named test is 10196
	User groups for user 10196 ...
	java.rmi.RemoteException: No UserGroup exists with the key {companyId=10154, name=MyUserGroup}
	Added user group named
	Added user to user group named MyUserGroup
	User groups for user 10196 ...
		MyUserGroup

As you can see, the user had no groups but then was added to usergroup `MyUserGroup`.

No worries about the `java.rmi.RemoteException` as it is thrown when invoking  the UserGroup check with `usergroupsoap.getUserGroup(groupName)` because the UserGroup does not yet exist.

Some things to note about the URL:

-	It is *secure* (authenticated) URL for the service. Authentication is done using HTTP Basic Authentication, which of course is not appropriate for a production environment, since the password is unencrypted, but is used for convenience in this example.

-	The screen name and password are passed in as credentials. The authorization type specified for your portal's company dictates the authorization type you must use to access your web service. You can override the value for property `company.security.auth.type` found in `portal.properties` by specifying your desired authorization type value in your `portal-ext.properties`. Valid authorization type values are:

    - `screenName`
    - `userId`
    - `emailAddress`

- The name of the service (e.g. `Portal_UserGroupService`) is specified at the end of the URL. Remember that the service name can be found in the web service listing like the one we looked at previously.

The operations `getCompanyByVirtualHost()`,  `getUserIdByScreenName()`, `getUserUserGroups()`, `addUserGroup()` and `addUserGroupUsers()` are specified for the `-ServiceSOAP` classes `CompanyServiceSoap`, `UserServiceSoap` and  `UserGroupServiceSoap` in the WSDL files. And information on parameter types, parameter order, request type, response type, and return type are conveniently specified in the WSDL for each Liferay web service. It's all there for you!

Next, let's try accomplishing the same behavior by using a client implemented in PHP.

### SOAP PHP Client

Now, let's say you want write your client in a language other than Java ... no problem! You can use any language that supports web services invocation.

The following invokes the same operations using PHP and the PHP SOAP Client:

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

So, if you'd like to use a language, other than Java, that supports use of SOAP web services, go ahead and try it out on Liferay's SOAP web services!

Next, we'll explore Liferay's JSON Web Services.

## JSON Web Services

JSON Web Services provide convenient access to portal service methods by exposing them as JSON HTTP API. This makes services methods easily accessible using HTTP requests, not only from JavaScript within the portal, but also from any JSON-speaking client.

JSON Web Service functionality can be split in following topics: registration, configuration, invocation and results. We'll cover each topic here.

### Registering JSON Web Services

By default, all remote-enabled services (i.e. entities with `remote-service="true"` in `service.xml`) will be exposed as JSON Web Services. When Service Builder creates each a  `-Service.java` interface for a remote-enable service, it also adds the `@JSONWebService` annotation on the class level of that interface. Therefore, *all* of the public methods of that interface become registered and available as JSON Web Services.

A `-Service` interface is a generated source file which is not to be modified by by the user directly. Sometimes, however, you need more control over which methods to expose and/or hide. To do so, just simply configure the `-ServiceImpl` class of the service. When service implementation class (`-ServiceImpl`) is annotated with the `@JSONWebService` annotation, the service interface is ignored and only the service implementation class is used for configuration. In other words, `@JSONWebService` annotations in service implementation **override** any JSON Web Service configuration in service interface.

And that’s all! Upon start-up, Liferay Portal scans classes on classpath for annotations. The ccanning process is optimized so only portal and service JARs are scanned, as well as class raw bytecode content. Each class that uses the `@JSONWebService` annotation is loaded and further examined; its methods become exposed as JSON API. As explained previously, the `-ServiceImpl` configuration overrides the `-Service` interface configuration during registration.

For example, let’s look the `DLAppService`: 

	@JSONWebService
	public interface DLAppService {
	...

It contains the annotation that is found on portal startup. Notice the following lines in console output when debug log level is set:

	10:55:06,595 DEBUG [JSONWebServiceConfigurator:121] Configure JSON web service actions
	10:55:06,938 DEBUG [JSONWebServiceConfigurator:136] Configuring 820 actions in ... ms

At this point, scanning and registration is done and all service methods (those of `DLAppService` and of other services) are registered as JSON Web Services.

Custom portlets can be registered and scanned, too, and their services can become part of the JSON API. Since scanning of portlet services is not enabled by default, you must add the following servlet definition in the `web.xml` of your portlet:

	<web-app>

		...

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
		<servlet-mapping>
			<servlet-name>JSON Web Service Servlet</servlet-name>
			<url-pattern>/api/secure/jsonws/*</url-pattern>
		</servlet-mapping>

		...

	</web-app>

This enables the servlet to scan and register your portlet's JSON Web Services.

#### Mapping and naming conventions

Mapped URLs of exposed service methods are formed using following naming convention:

	http://[server]:[port]/api/jsonws/[service-class-name]/[service-method-name]

where:

+ **service-class-name** is the name generated from service class name, by removing the `Service` or `ServiceImpl` suffix and converting the camel-case prefix of class name to a lowercase separated-by-dash name.
+ **service-method-name** is generated from the service method name, using the same convention as for the class name.

For example, the following service method ...

	@JSONWebService
	public interface UserService {
		public com.liferay.portal.model.User getUserById(long userId) {...}

... is mapped to the following URL:

	http://localhost:8080/api/jsonws/user-service/get-user-by-id

Each service method is also bound to one HTTP method type. All methods having names starting with `get`, `is` or `has` are assumed to be read-only methods and are therefore mapped as GET HTTP methods, by default. All other methods are mapped as POST HTTP methods.

Non-public service methods require the user to be registered before invoking the method. For those calls, users must specify a URL of the following convention:

	http://[server]:[port]/api/secure/jsonws/[service-class-name]/[service-method-name]

Note the `secure` part of the URL.

#### Listing available JSON Web Services

To a service overview and verify which service methods are registered and available, you can get a service listing in your browser by opening the base address:

	http://localhost:8080/api/jsonws

The resulting page lists all registered and exposed service methods of the portal. You can see more details of each method, by clicking the method name. For example, you can see the full signature of the service method, list of all its arguments, exceptions that can be thrown and even read its javadoc! Moreover, you can even invoke the service method for testing purposes using simple form right from within your browser.

To list registered services on a portlet, don't forget to use portlet context path: 

	http://localhost:8080/[portlet-context]/api/jsonws

This will list the JSON Web Service API for the portlet.

#### More on registration

As said, you can control registration by using annotations in your `-ServiceImpl` class. This overrides any configuration defined in the interface. Moreover, you can fine-tune which *methods* are visible/hidden using annotations at the method level.

##### Ignoring a method

To ignore a method from being exposed as a service, just annotate the method with:

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)

Any methods annotated like this do not become part of the JSON Web Service API.

##### HTTP method name and URL

It is also possible to define custom HTTP method names and URL names, using a similar annotation at the method level.

	@JSONWebService(value = "add-file-wow", method = "PUT")
	public FileEntry addFileEntry(

In this example, the `DLApp` service method `addFileEntry` is mapped to URL method name `add-file-wow`. The complete URL is actually  `http://localhost:8080/api/jsonws/dlapp/add-file-wow` and can be accessed using the PUT HTTP method.

If the URL name starts with a slash character (`/`), only the method name is used to form the service URL; the class name is ignored.

	@JSONWebService("/add-something-very-specific")
	public FileEntry addFileEntry(

Similarly, you can change the class name part of the URL, by setting the value in class-level annotation:

	@JSONWebService("dla")
	public class DLAppServiceImpl extends DLAppServiceBaseImpl {

This maps all the service methods of the class to URL class name `dla` instead of the `dlapp` default.

##### Manual registration mode

Up to now, it is expected that most of the service methods are going to be exposed; that only specific methods are to be hidden (the *blacklist* approach). But sometimes you might need a different behavior: to explicitly specify only those methods that are to be exposed (*whitelist* approach). This is possible, too, using so-called *manual mode* on class-level annotation. Then, it is up to you annotate only those methods which are to be exposed.

Then you can annotate only methods that have to be exposed.

	@JSONWebService(mode = JSONWebServiceMode.MANUAL)
	public class DLAppServiceImpl extends DLAppServiceBaseImpl {
		...
		@JSONWebService
		public FileEntry addFileEntry(

Now only the `addFileEntry` method and any other method annotated with `@JSONWebService` are to be part of the JSON Web Service API; all other methods of this service are to be excluded from the API.

Next, let's take a look at portal configuration options that apply to JSON Web Services.

### Portal Configuration of JSON Web Services

JSON Web Services are enabled by default but can be easily disabled by setting the following property in your `portal-ext.properties`: 

	json.web.service.enabled=false

#### Strict HTTP methods

JSON Web Service services are, by default, mapped to either GET or POST HTTP methods. If service method has name that starts with `get`, `is` or `has`, the service is assumed to be read-only and is bound to the GET method; otherwise it is bound to POST.

By default, portal does not check HTTP methods when invoking a service call; that is, the portal works in "non-strict http method" mode as services may be invoked using any HTTP method. If you need the strict mode, you can set it with portal property: 

	jsonws.web.service.strict.http.method=true

When you use strict mode, you must use the correct HTTP methods in calling service methods.

#### Disabling HTTP methods

When strict HTTP method mode is enabled, you can even filter web service access based on HTTP methods used by the services. For example, you can set the portal JSON Web Services to work in read-only mode by disabling HTTP methods other than GET. For example:

	jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

Now all requests that use HTTP methods from above list are simply ignored.

Lastly, let's consider how to invoke JSON Web Services.

### Invoking JSON Web Services

JSON Web Services can be invoked in several ways depending on how their parameters (i.e. method arguments) are passed in. But before we dive into different ways of passing parameters, it's important to understand how your invocation is matched to a method.

#### Matching service methods

It is important to understand how calls to service methods are matched , especially when a service method is overloaded.

The general rule is that besides the method name, you must provide **all** parameters for that service method. Even if some parameter is to be `null`, you must still provide it.

Note, however, it is neither important how parameters are provided (as part of the URL line, as request parameters, etc.) nor the order the parameters.

An exception to the rule of *all* parameters being required, is when using numberic *hints* to match methods. Let's look at using hints next.

#### Using hints

It is possible to add numeric hints that specify how many method arguments a service has. Hints are added as numbers separated by a dot in method name. For example:

	/foo/get-bar.2/param1/123/-param2

Here, the `.2` is a hint, so only service methods with 2 arguments will be matched, others will be ignored for matching.

One important difference when hint is specified, is that now you do not have to specify all of the parameters. All missing arguments are treated as `null`. Therefore, the previous example may be called with ...

	/foo/get-bar.2/param1/123

... and `param2` will automatically be set to `null`.

#### Passing parameters as part of URL path

Parameters can be passed as part of the URL path. After the service URL, you can append methods parameters in name/value pairs. Parameter names must be formed from method argument names, by converting them from camel-case to lowercase separated-by-dash names. Example:

	http://localhost:8080/api/secure/jsonws/dlapp/get-file-entries/repository-id/10172/folder-id/0

Parameters may be given in **any** order; it’s not necessary to follow the order in which the arguments specified in the method signatures. 

When a method name is overloaded, the *best match* will be used: The method that contains the least number of undefined arguments is invoked.

#### Passing parameters as URL query

Parameters can be passed as request parameters, too. The difference is that parameter names are specified as is (e.g. camel-case) and are set equal to their argument values:

	http://localhost:8080/api/secure/jsonws/dlapp/get-file-entries?repositoryId=10172&folderId=0

As with passing parameters as part of a URL path, the parameter order is not important, the *best match* rule applies for overloaded methods, etc.

#### Mixed way of passing parameters

Parameters can be passed in a mixed way: some can be part of the URL path and some can be specified as request parameters.

#### Sending NULL values

To pass a `null` value for an argument, simply prefix that parameter name with a dash `-`. For example: 

	.../dlsync/get-d-l-sync-update/company-id/10151/repository-id/10195/-last-access-date

Here the `last-access-date` parameter is interpreted as `null`.

Null parameters, therefore, do not have specified values. Of course, null parameters do not have to be the last in the URL, as in this example. When a null parameter is passed as a request parameter, its value is ignored and `null` is used instead: 

	<input type="hidden" name="-last-access-date" value=""/>

When using JSON RPC (see below), null values may be sent explicitly, even without a prefix. For example:

	"last-access-date" : null

#### Difference between URL and query encoding

Although often forgotten, there is a difference between URL encoding and query (request parameters) encoding. An  illustrative example of this is the difference in how the space character is encoded. When the space character is part of the URL path, it is encoded as `%20`; when it is part of the query it is encoded as plus sign (`+`).

#### Sending files as arguments

Files can be uploaded using multipart forms and requests. Example:

	<form action="http://localhost:8080/api/secure/jsonws/dlapp/add-file-entry" method="POST" enctype="multipart/form-data">
	        <input type="hidden" name="repositoryId" value="10172"/>
	        <input type="hidden" name="folderId" value="0"/>
	        <input type="hidden" name="title" value="test.jpg"/>
	        <input type="hidden" name="description" value="File upload example"/>
	        <input type="hidden" name="changeLog" value="v1"/>
	        <input type="file" name="file"/>
	        <input type="submit" value="addFileEntry(file)"/>
	</form>

As you see, it's a common upload form that invokes the `addFileEntry` method of the `DLAppService` class.

#### JSON RPC

JSON Web Service may be invoked using [JSON RPC](http://json-rpc.org/). A good part of JSON RPC 2.0 specification is supported in Liferay JSON Web Services. One limitation is that parameters may be passed only as *named* parameters; positional parameters are not supported, as there are too many overloaded methods for convenient use of positional parameters.

Here is an example of invoking a JSON web service using JSON RPC:

	POST http://localhost:8080/api/secure/jsonws/dlapp
	{
		"method":"get-folders",
		"params":{"repositoryId":10172, "parentFolderId":0},
		"id":123,
		"jsonrpc":"2.0"
	}

#### Default parameters

When accessing *secure* JSON web services (user has to be authenticated), some parameters are made available to the web services by default. They need not to be specified explicitly, unless you want to change their values to something other than their defaults.

Default parameters are:

+ `userId` - id of authenticated user
+ `user` - full User object
+ `companyId` - users company
+ `serviceContext` - empty service context object 

#### Object parameters

Most services accept simple parameters: numbers, strings etc. However, sometimes you need to provide an object (a non-simple type) as a service parameter.

Similar specifying null parameters by using the `-` prefix, to create an instance of an object parameter, just prefix the parameter name with a plus sign, `+`,  without any parameter value at all. For example:

	/jsonws/foo/get-bar/zap-id/10172/start/0/end/1/+foo

or as a request parameter (note, the `+` sign must be encoded!):

	/jsonws/foo/get-bar?zapId=10172&start=0&end=1&%2Bfoo

or

	<input type="hidden" name="+foo" value=""/>

If a parameter is an abstract class or an interface, it can't be instantiated as such. Instead, a concrete implementation class must be specified to create the argument value. This can be done by specifying the `+` prefix before the parameter name followed by specifying the concrete implementation class. For example:

	/jsonws/foo/get-bar/zap-id/10172/start/0/end/1/+foo:com.liferay.impl.FooBean

or

	<input type="hidden" name="+foo:com.liferay.impl.FooBean" value=""/>

In the example, specifies that a `com.liferay.impl.FooBean` object, presumed to implement the class of the parameter named `foo`, is to be created.

A concrete implementation can be set as a value, too! For example:

	<input type="hidden" name="+foo" value="com.liferay.impl.FooBean"/>

or in JSON RPC:

	"+foo" : "com.liferay.impl.FooBean"

All these examples specifies a concrete implementation for `foo` service method parameter.

#### Map and List parameters

`Map` and `List` parameters are also supported, by sending JSON objects and arrays. If parameters `Map` or `List` contain generics information (e.g. `Map<Locale, String>`), Liferay Portal will generify the map/list entries before passing the value to the method.

#### Inner Parameters

In many cases, you'll need to populate objects that are passed as parameters. A good example is a default parameter `serviceContext` of type `ServiceContext` (see the *Service Context* section in this chapter). Sometimes, you need to set some of the inner properties (i.e. fields) of the `ServiceContext`, such as: `addGroupPermissions`, `scopeGroupId` etc., to make an appropriate call to a JSONWS.

To pass inner parameters, just specify them using a 'dot' notation. That is, specify the name of the parameter, followed by a dot `.`, followed by the name of the inner parameter. For example, with regards to the `ServiceContext` inner parameters you can provide: `serviceContext.addGroupPermissions`, `serviceContext.scopeGroupId`  parameters, together with other parameters of your web service call. They will be recognized as inner parameters (with a dot in the name) and their values will be *injected* into existing parameters, before the API service method is executed.

Inner parameters are not counted as *regular* parameters for matching methods and are ignored during matching.

---
![tip](../../images/tip-pen-paper.png)**Tip:** Use inner parameters with object parameters to set inner content of created parameter instances!
---

### Returned values

No matter how a JSON web service is invoked, it returns a JSON string that represents the service method result. Any returned objects are *loosely* serialized to a JSON string and returned to the caller.

Let's take a look a some returned values from calls to services. In fact, let's create a UserGroup as we did in our previous SOAP web service client examples. To make it easy, we'll use the test form provided with the JSON web service in our browser.

1.	Open your browser to the JSON web service method that adds a UserGroup:

		http://127.0.0.1:8080/api/jsonws?signature=/usergroup/add-user-group-2-name-description
		
	or navigate to it by starting at `http://127.0.0.1:8080/api/jsonws`, scrolling down to the section for *UserGroup* and clicking *add-user-group*.

2.	Fill in the *name* field to "MyUserGroup3" and the *description* to some arbitrary value string like "Created using JSON WS".

3.	Click *Inovke* to get a result similar to the following:

		{"addedByLDAPImport":false,"companyId":10154,"description":"Created using JSON WS","name":"MyUserGroup33","parentUserGroupId":0,"userGroupId":13162}

Notice that the JSON string returned represents the `UserGroup` object you just created. The object has been serialized into a JSON string. As a starting point for understanding JSON strings, go to [json.org](http://www.json.org/).

To find out how to serialize Java objects, maps and lists, check out article [JSON Serialization](http://www.liferay.com/community/wiki/-/wiki/Main/JSON+Serialization) by Igor Spasic. 

Well, you've just added some powerful tools to your toolbox by learning how to leverage JSON web services in Liferay. Good job!

Next, let's consider the `ServiceContext` class used by so many Liferay services and how it can be helpful to use in your services.

## Service Context

The `ServiceContext` class is a parameter class to be used in passing contextual information for a service. By using a parameter class it is possible to consolidate many different methods with different sets of optional parameters into a single, easier to use method. The class also aggregates information necessary for transversal features such as permissioning, tagging, categorization, etc.

This section covers:

-	The Service Context fields

-	Creating and populating a Service Context

-	Accessing  Service Context data

First, we'll take a look at the fields of the `ServiceContext` class.

### Service Context Fields

There are a good number of fields found in `ServiceContext`. The best descriptions of these fields are found in the Javadoc comments for of their corresponding *getter* methods found at [http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/portal/service/ServiceContext.html](http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/portal/service/ServiceContext.html). But what may also be helpful is the following categorical listing of the fields:

-	Actions:
	-	`_command`
	-	`_workflowAction`

-	Attributes:
	-	`_attributes`
	-	`_expandoBridgeAttributes`

-	Classification:	
	-	`_assetCategoryIds`
	-	`_assetTagNames`

-	IDs and Scope:
	-	`_companyId`
	-	`_portletPreferencesIds`
	-	`_plid`
	-	`_scopeGroupId`
	-	`_userId`
	-	`_uuid`

-	Language:
	-	`_languageId`

-	Miscellaneous:
	-	`_headers`
	-	`_signedIn`

-	Permissions:
	-	`_addGroupPermissions`
	-	`_addGuestPermissions`
	-	`_deriveDefaultPermissions`
	-	`_groupPermissions`
	-	`_guestPermissions`

-	Resources:
	-	`_assetEntryVisible`
	-	`_assetLinkEntryIds`
	-	`_createDate`
	-	`_indexingEnabled`
	-	`_modifiedDate`

-	URLs, paths and addresses:
	-	`_currentURL`
	-	`_layoutFullURL`
	-	`_layoutURL`
	-	`_pathMain`
	-	`_portalURL`
	-	`_remoteAddr`
	-	`_remoteHost`
	-	`_userDisplayURL`

In case you are wondering how the `ServiceContext` fields get populated we're going to look at that next.

### Creating and Populating a Service Context 

All of the fields of the `ServiceContext` class are optional, although your services that store any type of content will require you to specify at least the scope group ID. Here is a simple example of how to create a `ServiceContext` instance and pass it as a parameter to a service API using Java:

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setScopeGroupId(myGroupId);
		...
		BlogsEntryServiceUtil.addEntry(...., serviceContext);

If you are invoking the service from a servlet, a struts action or any other front-end class which has access to the `PortletRequest`, use one of the `ServiceContextFactory.getInstance(...)` methods. These methods create the `ServiceContext` object and fill it with all the necessary values automatically. In the case that your are invoking the service from a servlet, the above example could be rewritten as follows:

		ServiceContext serviceContext =
				ServiceContextFactory.getInstance(BlogsEntry.class.getName(),
				portletRequest);
		BlogsEntryServiceUtil.addEntry(..., serviceContext);

To see an example of how to populate a `ServiceContext` with information from a request object, check out the code of the `ServiceContextFactory.getInstance(...)` methods. Not only do the methods demonstrate setting parameters such as scope group ID, company ID, language ID, etc., but they also demonstrate accessing and populating more complex context parameters such as tags, categories, asset links, headers, and the attributes parameter. Note, by calling `ServiceContextFactory.getInstance(String className, PortletRequest portletRequest)`, you can assure that your expando bridge attributes are also set on the `ServiceContext`.

And, by all means, you are not limited to using only Java with ServiceContext; as you can use ServiceContext from other languages like JavaScript. In fact, since our API can be invoked from JavaScript, it is often required to pass the ServiceContext from JavaScript to the server, and this can be done in a very simple way -- by passing the ServiceContext as any other JavaScript object. There are plenty of examples of this in the JavaScript code of Liferay's portlets. Here is an example from `[liferay-portal]/portal-web/html/portlet/journal/js/main.js`that demonstrates using ServiceContext in calling the `updateStructure` method of the JournalStructure service:

	
	var instance = this;

	var defaultLocale = instance.getDefaultLocale();

	var serviceParameterTypes = [
		'long',
		'java.lang.String',
		'java.lang.String',
		'java.util.Map<java.util.Locale, java.lang.String>',
		'java.util.Map<java.util.Locale, java.lang.String>',
		'java.lang.String',
		'com.liferay.portal.service.ServiceContext'
	];

	Liferay.Service.Journal.JournalStructure.updateStructure(
		{
			groupId: groupId,
			structureId: structureId,
			parentStructureId: parentStructureId || '',
			nameMap: '{' + defaultLocale + ':' + name + '}',
			descriptionMap: '{' + defaultLocale + ':' + (description == '' ? null : description ) + '}',
			xsd: xsd,
			serviceContext: A.JSON.stringify(
				{
					scopeGroupId: groupId
				}
					),
			serviceParameterTypes: A.JSON.stringify(serviceParameterTypes)
		},
		function(message) {
			if (Lang.isFunction(callback)) {
				callback(message);
			}
		}
	);

Note, that the example above uses JSON to populate the ServiceContext.

On the front-end, you can use Alloy UI and Liferay UI tags in your forms to extract information and automatically insert the corresponding data into your request object. As an example, see `portal-web/docroot/html/portlet/blogs/edit_entry.jsp`. Next, let's take a look at an example of accessing information from a `ServiceContext`.

### Accessing Service Context data

This section provides code snippets from `BlogsEntryLocalServiceImpl.addEntry(..., ServiceContext)` that demonstrate a accessing information from a `ServiceContext` and provides comments on how the context information is being used.

As previously mentioned, your service will need a scope group ID from your `ServiceContext`. The same holds true for the blogs entry service because the scope group ID provides the scope of the blogs entry (the entity being persisted). In the case of adding a blogs entry, the scope group ID is used in the following manner:

-	Used as the groupId for the `BlogsEntry` entity
-	Used in generating a unique URL for the blog entry
-	Setting the scope for comments on the blog entry

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

The `ServiceContext` is also used to access the UUID and the time this blog entry was added.

		entry.setUuid(serviceContext.getUuid());
		...
		entry.setCreateDate(serviceContext.getCreateDate(now));

Can `ServiceContext` be used in setting permissions on resources? You bet! When adding a blog entry, new permissions can be added or existing permissions can be applied for the blog entry:

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

Categories, tag names, and the link entry IDs can be applied to the `AssetEntry` for the blogs entry, as demonstrated:

		// Asset

		updateAsset(
			userId, entry, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());

The `ServiceContext` also plays a part in starting a workflow instance for the blogs entry. As you can see, the scope group ID sets the scope for the workflow to be started for the blog entry.

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

The previous snippet also demonstrates using the `trackbacks` attribute which is a *standard* attribute for the blogs entry service. But there may be cases where you need to pass in *custom* attributes to your blogs entry service. To do so, use Expando attributes to carry these *custom* attributes along in your `ServiceContext`. Expando attributes are set on the added blogs entry like so:

		entry.setExpandoBridgeAttributes(serviceContext);

As we've demonstrated, the `ServiceContext` can be used to transfer lots of useful information for your services.

## Conclusion

Well, you've covered a lot of ground here in learning how to use the API locally and remotely, how to enable/disable remote services and access to them, and how to leverage ServiceContext objects in your use of Liferay services. Well done! Next, we'll take a look at some of the powerful frameworks of Liferay Portal, learn how they work and how you can leverage them.

