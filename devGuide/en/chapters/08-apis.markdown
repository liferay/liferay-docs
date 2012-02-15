
# Liferay APIs

This chapter provides an overview of several of the essential Liferay *application programming interfaces* (*APIs*) available to developers. An API is a programing interface that can be invoked from your own code, either directly through a Java invocation or through web services, to perform an action or set of actions.

Liferay comes with a host of APIs that expose powerful portal and portlet services. You can access Liferay's built-in *portal* services for such entities as User, Organization, UserGroup, Company, Role, Permission, ResourcePermission and Layout. In addition, Liferay's built-in *portlet* services for which APIs are provided include the blogs, wikis, assets, social equity, and the Document & Media Library services.

The APIs can be called via Java method or web service invocations giving you flexibility to implement client code in Java or other languages (even a scripting language such as PHP) that support web service invocations.  The APIs can be called from within a portlet or non-portlet code. You can make calls to the APIs *locally* from within the portal or *remotely* outside of the *Java virtual machine* (*JVM*) in which the portal is running.  So, let's look at in more detail at how we can access Liferay APIs.

## Local Access

Each service built with Service Builder provides a local interface to clients running in the same JVM as the portal. Since Liferay's portal and portlet services are built using the Service Builder, their public service methods can be accessed through static methods provided by auto-generated `-LocalServiceUtil` classes.

The following JSP code snippet demonstrates how to get a list of the most recent bloggers from an organization. It invokes static method `getOrganizationStatsUsers()` on `-LocalServiceUtil` class `BlogsStatsUserLocalServiceUtil`:

    <%@ page import="com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil" %>
    <%@ page import="com.liferay.portlet.blogs.util.comparator.StatsUserLastPostDateComparator" %>
    ...
    <%@
    List statsUsers = BlogsStatsUserLocalServiceUtil.getOrganizationStatsUsers(
        organizationId, 0, max, new StatsUserLastPostDateComparator());
    %>

## Remote Access

Services are also made available to *remote* clients (clients running outside of the portal JVM). Service Builder generates remote interfaces for your entities if their `remote-service` attribute is set to `true` in your portlet's `service.xml`. Here is an example of how remote services are turned on for Liferay's `Country` entity:

    <entity name="Country" local-service="false" remote-service="true">

The generated services can be accessed using web services or Java RMI. We'll explore how to user web services to invoke several remote services.

### SOAP Web Services

Service Builder generates classes to use your services via *Simple Object Access Protocol* (*SOAP*) over HTTP. SOAP is the *packaging* protocol and HTTP is the *transport* protocol. For our example, we'll look at the SOAP web service classes for Liferay's `Company`, `User`, and `UserGroup` portal services to accomplish the following:

1. List each `UserGroup` to which user `joebloggs` belongs

2. Add a new `UserGroup` named `MyGroup`

3. Add user `joebloggs` to the `UserGroup`

Here are the SOAP related classes that we'll use:

    com.liferay.client.soap.portal.model.CompanySoap
    com.liferay.client.soap.portal.service.http.CompanyServiceSoap
    com.liferay.client.soap.portal.service.http.CompanyServiceSoapServiceLocator

    com.liferay.client.soap.portal.model.UserGroupSoap
    com.liferay.client.soap.portal.service.http.UserServiceSoap
    com.liferay.client.soap.portal.service.http.UserServiceSoapServiceLocator

    com.liferay.client.soap.portal.service.http.UserGroupServiceSoap
    com.liferay.client.soap.portal.service.http.UserGroupServiceSoapServiceLocator

You can see in the listing a naming convention involving classes with suffixes `-ServiceSoapServiceLocater`, `-ServiceSoap`, and `-Soap`. The `-ServiceSoapServiceLocater` class finds the `-ServiceSoap` by means of the service's URL you provide. The `-ServiceSoap` class implements the Java Remote Method Invocation (RMI) interface bound to the the services specified in the *Web Services Definition Language* (*WSDL*) file for each service. Lastly, `-SOAP` classes are the serializeable implementations of the models.  Let's look at how to determine the URLs for these services.

Service Builder was used to publish these services as a web services.  You can view a listing of the services deployed on your portal by opening your browser to the URL of the format `http://host:port/tunnel-web/secure/axis` for your *secure* services (services requiring user authentication) and `http://host:port/tunnel-web/axis` for your services that do not require user authentication. We've used *secure* services such as the following for `UserGroup`:

![Figure 8.x: UserGroup Web Service listing](../../images/wsdl-summary-listing.png)

Each web service is listed with its name, operations, and a link to its WSDL file. The WSDL is written in XML and provides a model for describing and locating the web service.

![Figure 8.x: WSDL Excerpt for the addUserGroup operation of UserGroup](../../images/wsdl-for-user-group-service.png)

You pass in the WSDL URL along with your login credentials to the SOAP service locator for your service.

The following code demonstrates locating and invoking operations to add a new `UserGroup` named `MyUserGroup` and assign user with screen name `joebloggs` to that `UserGroup`:

    import java.net.URL;

    import com.liferay.client.soap.portal.model.CompanySoap;
    import com.liferay.client.soap.portal.model.UserGroupSoap;
    import com.liferay.client.soap.portal.service.http.CompanyServiceSoap;
    import com.liferay.client.soap.portal.service.http.CompanyServiceSoapServiceLocator;
    import com.liferay.client.soap.portal.service.http.UserGroupServiceSoap;
    import com.liferay.client.soap.portal.service.http.UserGroupServiceSoapServiceLocator;
    import com.liferay.client.soap.portal.service.http.UserServiceSoap;
    import com.liferay.client.soap.portal.service.http.UserServiceSoapServiceLocator;

    public class WSUserGroup {
	    public static void main(String[] args) {

		    try {
			    String remoteUser = "joebloggs";
			    String password = "test";
			    String virtualHost = "localhost";
			
			    String groupName = "MyUserGroup";

			    String serviceCompanyName = "Portal_CompanyService";
			    String serviceUserName = "Portal_UserService";
			    String serviceUserGroupName = "Portal_UserGroupService";

			    // Locate the Company
			    CompanyServiceSoapServiceLocator locatorCompany =
				    new CompanyServiceSoapServiceLocator();

			    CompanyServiceSoap soapCompany =
				    locatorCompany.getPortal_CompanyService(
					    _getURL(remoteUser, password, serviceCompanyName, true));

			    CompanySoap companySoap =
				    soapCompany.getCompanyByVirtualHost(virtualHost);

			    // Locate the User service
			    UserServiceSoapServiceLocator locatorUser =
				    new UserServiceSoapServiceLocator();
			    UserServiceSoap userSoap = locatorUser.getPortal_UserService(
				    _getURL(remoteUser, password, serviceUserName, true));

			    // Get the ID of the remote user
			    long userId = userSoap.getUserIdByScreenName(
				    companySoap.getCompanyId(), remoteUser);
			    System.out.println(remoteUser + " id is " + userId);

			    // Locate the UserGroup service
			    UserGroupServiceSoapServiceLocator locator =
				    new UserGroupServiceSoapServiceLocator();
			    UserGroupServiceSoap usergroupsoap =
				    locator.getPortal_UserGroupService(
					    _getURL(remoteUser, password, serviceUserGroupName, true));

			    // Get the user's user groups
			    UserGroupSoap[] usergroups = usergroupsoap.getUserUserGroups(userId);
			    System.out.println("User groups for user " + userId + " ...");
			    for (int i = 0; i < usergroups.length; i++) {
				    UserGroupSoap usergroup = usergroups[i];
				    System.out.println(
					    usergroup.getCompanyId() + "\t" +
					    usergroup.getUserGroupId() + "\t" +
					    usergroup.getName() + "\t" +
					    usergroup.getDescription());
			    }

			    // Adds the user group
			    String groupName = groupName;
			    String groupDesc = "My new user group";
			    UserGroupSoap group = usergroupsoap.addUserGroup(
				    groupName, groupDesc, 0, 0);

				// Add the user to the user group
			    long users[] = {userId};
			    userSoap.addUserGroupUsers(group.getUserGroupId(), users);
		    }
		    catch (Exception e) {
			    e.printStackTrace();
		    }
	    }

	    private static URL _getURL(String remoteUser, String password,
		    String serviceName, boolean authenicate)
	    throws Exception {
		    //Unathenticated url

		    String url = "http://localhost:8080/tunnel-web/axis/" + serviceName;
		    //Authenticated url
		    if (authenicate) {
			    url = "http://" + remoteUser + ":" + password +
				    "@localhost:8080/tunnel-web/secure/axis/" + serviceName;
		    }
		    return new URL(url);
	    }
    }

Some things to note about the URL:

- It is *secure* (authenticated) URL for the service. Authentication is done using HTTP Basic Authentication, which of course is not appropriate for a production environment, since the password is unencrypted.

- The screen name and password are passed in as credentials.  The authorization type specified for your portal's company dictates the authorization type you must use to access your web service. You can override the `company.security.auth.type` found in `portal.properties` by specifying your desired authorization type in your `portal-ext.properties`. Valid authorization type values are:

    - `screenName`
    - `userId`
    - `emailAddress`

- The name of the service (e.g., `Portal_UserGroupService`) is specified at the end of the URL. Remember that the service name can be found in the web service listing like the one we looked at previously.

The operations `getCompanyByVirtualHost()`,  `getUserIdByScreenName()`, `getUserUserGroups()`, `addUserGroup()` and `addUserGroupUsers()` are specified for the `-ServiceSOAP` classes `CompanyServiceSoap`, `UserServiceSoap` and  `UserGroupServiceSoap` in the WSDL files. Information on parameter types, parameter order, request type, response type, and return type are conveniently specified in the WSDL for each Liferay web service!

Now, let's say you want write your client in a language other than Java ... no problem! You can use any language that supports web services invocation.

The following invokes the same operations using PHP and the PHP SOAP Client:

    <?php
        $groupName = "MyGroup";
        $userName = "joebloggs";
        $clientOptions = array(
	        'login' => $userName,
	        'password' => 'test');

        // Add user group

        $userGroupClient = new SoapClient(
	        "http://localhost:8080/tunnel-web/secure/axis/Portal_UserGroupService?wsdl",
	        $clientOptions);
        $group = $userGroupClient->addUserGroup($groupName, "This is my group", 0, 0);
        print "group id for " . $groupName . " is " . $group->userGroupId . "\n";

        // add user to user group

        $companyClient = new SoapClient(
	        "http://localhost:8080/tunnel-web/secure/axis/Portal_CompanyService?wsdl",
	        $clientOptions);
        $company = $companyClient->getCompanyByVirtualHost("localhost");
        $userClient = new SoapClient(
	        "http://localhost:8080/tunnel-web/secure/axis/Portal_UserService?wsdl",
	        $clientOptions);
        $userId = $userClient->getUserIdByScreenName($company->companyId, $userName);
        print "user id for " . $userName . " is " . $userId . "\n";

        $users = array($userId);
        $userClient->addUserGroupUsers($group->userGroupId, $users);

        // get and print user groups to which user belongs

        $userGroups = $userGroupClient->getUserUserGroups($userId);
        print "user groups for user " . $userId . " ...\n";
        foreach($userGroups as $k=>$v)
            print ($v->name) . " " . $v->userGroupId . "\n";
    ?>

### JSON Web Services

JSON Web Services provide convenient access to portal service methods by exposing them as JSON HTTP API. This makes services methods easily accessible using HTTP requests, not only from portals javascript, but from any JSON-speaking client.

JSON Web Services functionality can be split in following topics: registration, invocation and results. We'll cover each topic here.

#### Registering JSON Web Services

By default, all remote-enabled services (i.e. with `remote-service="true"` on entity in `service.xml`) will be exposed as JSON Web Services. When Service Builder creates some `Service.java` interface, it will also add `@JSONWebService` annotation on class level in that service. Therefore, when service interface is annotated with `@JSONWebService` annotation, **all** its service methods becomes registered and available as JSON Web Services.

Service interface is generated source file and it is recommended not to be modified by user directly. Sometimes, however, you need more control over which methods to expose and/or hide. Then just simply configure the `ServiceImpl` class of the service. When service implementation class (`ServiceImpl`) is annotated with `@JSONWebService` annotation, service interface is ignored and only service implementation class will be used for configuration.

In other words, `@JSONWebService` annotations in service implementation **overrides** configuration from service interface.

And that’s all! Upon start, portal scans classes on classpath for annotation usage. Scanning process is optimized, so only portal and service jars are scanned, as well as class raw bytecode content. Each class that uses the annotation is loaded and further examined; its methods become exposed as JSON API. The registration follows explained rule: `ServiceImpl` configuration overrides configuration from `Service` interfaces.

For example, let’s look the `DLAppService`: 

	@JSONWebService
	public interface DLAppService {
	...

It contains the annotation that is found on portal startup. Notice the following lines in console output:

	10:55:06,595 DEBUG [JSONWebServiceConfigurator:121] Configure JSON web service actions
	10:55:06,938 DEBUG [JSONWebServiceConfigurator:136] Configuring 820 actions in ... ms

At this point, scanning and registration is done and all service methods (of `DLAppService` and of other services as well) are registered as JSON Web Services.

##### Portlets

Custom portlets can be scanned, too, and their service becomes part of the JSON API. As this is not enabled by default, you must add the following servlet definition in `web.xml` of your portlet:

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

This enables the servlet that scans and registers JSON Web Services.

#### Mapping and naming conventions

Mapped URLs of exposed service methods are formed using following naming convention:

	http://<server:port>/api/jsonws/<service-class-name>/<service-method-name>

where:

+ **service-class-name** is the name generated from service class name, by removing `Service` or `ServiceImpl` suffix and converting the camelcase class name to lowercase separated-by-dash name.
+ **service-method-name** is generated from the service method name, using the same convention as for the class name.

For example, the following service method:

	@JSONWebService
	public interface UserService {
		public com.liferay.portal.model.User getUserById(long userId) {...}

will be mapped to following URL:

	http://localhost:8080/api/jsonws/user-service/get-user-by-id

Each service method is also bind to one HTTP method. By default, all read-only methods (which names start with `get', `is` and `has`) are mapped as GET HTTP methods. All other methods are mapped as POST methods.

##### Security

Non-public service methods require user to be registered before invoking the method. For those calls users should use the following URL:

	http://<server:port>/api/secure/jsonws/<service-class-name>/<service-method-name>

Note the `secure` part of the URL.

#### List available JSON Web Services

To overview and verify which service methods are registered and available, you can list them all in browser by opening the base address:

	http://localhost:8080/api/jsonws

Resulting page contains a list of all registered and exposed service methods of portal. You can see more details of each method, by clicking the method name. For example, you can see the full signature of the service method, list of all arguments, exceptions that can be thrown and even read the javadoc! Moreover, you can even invoke the service method for testing purposes using simple form.

To list registered services on portlet, don't forget to use portlet context path: 

	http://localhost:8080/<portlet-context>/api/jsonws

This will list only portlets API.

#### More on registration

As said, you can control registration by using annotations in the `ServiceImpl` class. This overrides configuration defined in the interface. Moreover, you can fine-tune which methods are visible/hidden using annotations on method level.

##### Ignoring a method

To ignore a method from being exposed as a service, just annotated method with:

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)

All methods annotated like this will not be part of the JSON Web Service API.

##### HTTP method name and URL

It is also possible to define custom HTTP method name and URL names, using the same annotation on method level.

	@JSONWebService(value = "add-file-wow", method = "PUT")
	public FileEntry addFileEntry(

In this example, method `addFileEntry` will be mapped to `/dlapp/add-file-wow` (actually to the: `/api/jsonws/dlapp/add-file-wow`) and can be accessed using HTTP method PUT.

If the URL name starts with a slash character (`/`), only method name is used to form the service URL; class name is ignored

	@JSONWebService("/add-something-very-specific")
	public FileEntry addFileEntry(

Similarly, you can change the class name part of the URL, by setting the value in class-level annotation:

	@JSONWebService("dla")
	public class DLAppServiceImpl extends DLAppServiceBaseImpl {

Now all service methods will have `dla` in the mapped URL instead of default `dlapp`.

##### Manual registration mode

Up to now, it is expected that most of the service methods are going to be exposed; we allowed user to ignore few (the *blacklist* approach). But sometimes you might need a different behavior: to explicitly specify only methods that are going to be exposed (*whitelist* approach). This is possible, too, using so-called *manual mode* on class-level annotation. Then you can annotate only methods that have to be exposed.

	@JSONWebService(mode = JSONWebServiceMode.MANUAL)
	public class DLAppServiceImpl extends DLAppServiceBaseImpl {
		...
		@JSONWebService
		public FileEntry addFileEntry(

Now only the `addFileEntry` method is going to be part of the JSON Web Service API, all other methods will be ignored.

#### Configuration

JSON Web Services can be easily turned off in `portal.properties` by setting: 

	json.web.service.enabled=false

##### Strict HTTP methods

JSON Web Service services are, by default, mapped to either GET or POST HTTP methods. If service method is a read-only method, i.e. its name starts with `get`, `is` or `has`, service is bind to GET method; otherwise it is bind to POST.

By default, portal is not checking HTTP method when invoking a service call, i.e. the portal works in "non-strict http method" mode, when services may be invoked using any HTTP method. If you need the strict mode, you can set it with: 

	jsonws.web.service.strict.http.method=true

Now you have to use correct HTTP method when calling service method.

##### Disabled HTTP methods

When strict HTTP method is enabled, you can even filter the access based on HTTP methods. For example, you can set the portal JSON Web Services in read-only mode, by disabling POST (and other) HTTP methods. For example: 

	jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

Now all requests that use HTTP methods from above list will be simply ignored.

#### JSON Web Service Invocation

All services are invoked through its `Util` classes, as you would use it from the java code.

JSON Web Services can be invoked in several ways depending on how parameters (i.e. method arguments) are passed. 

##### Passing parameters as part of URL path

Parameters can be passed as part of the URL path. After the service URL, you can append methods parameters like name/value pairs. Parameter names must be formed form method argument names, by converting camel-case to lowercased separated-by-dash name. Example: 

	http://localhost:8080/api/secure/jsonws/dlapp/get-file-entries/repository-id/10172/folder-id/0

Parameters may be given in **any** order; it’s not necessary to follow arguments order defined by methods. 

When method name is overloaded, the best match will be used: method that contains the least of arguments undefined.

##### Passing parameters as URL query

Parameters can be passed as request parameters, too. The difference is that now parameter names are equals to service method argument names: 

	http://localhost:8080/api/secure/jsonws/dlapp/get-file-entries?repositoryId=10172&folderId=0

Everything else remains the same: the parameter order is not important, etc.

##### Mixed way of passing parameters

Parameters can be passed in a mixed way: some can be part of the URL path and some can be specified as request parameters.

##### Sending NULL values

To pass `null` value for some argument, simply prefix that parameter name with a dash `-`. For example: 

	.../dlsync/get-d-l-sync-update/company-id/10151/repository-id/10195/-last-access-date

Here the `last-access-date` parameter will be `null`.

Null parameters, therefore, do not have specified value. Of course, null parameters do not have to be the last in the URL, as in this example. When passed as request parameter, its value will be ignored and `null` will be used instead: 

	<input type="hidden" name="-last-access-date" value=""/>

When using JSON RPC (see below), null values may be sent even without a prefix. For example: 

	"last-access-date" : null

##### Difference between URL and query encoding #

This is something often forgotten: there is a difference between URL and query (request parameters) encoding. Most illustrative difference is how space character is encoded. When space character is part of the URL path, it is encoded as `%20`; when it is part of the query it is encoded as plus sign (`+`).

##### Sending files as arguments

Files can be uploaded using multipart forms and request. Example:

	<form action="http://localhost:8080/api/secure/jsonws/dlapp/add-file-entry" method="POST" enctype="multipart/form-data">
	        <input type="hidden" name="repositoryId" value="10172"/>
	        <input type="hidden" name="folderId" value="0"/>
	        <input type="hidden" name="title" value="test.jpg"/>
	        <input type="hidden" name="description" value="File upload example"/>
	        <input type="hidden" name="changeLog" value="v1"/>
	        <input type="file" name="file"/>
	        <input type="submit" value="addFileEntry(file)"/>
	</form>

As you see, it's a common upload form.

##### JSON RPC

JSON Web Service may be invoked using [JSON RPC](http://json-rpc.org/). Good part of JSON RPC 2.0 specification is supported. Parameters may be passed only as *named* parameters, since positional are not supported - there are too many overloaded methods for convenient use of positional parameters. Example:

	POST http://localhost:8080/api/secure/jsonws/dlapp
	{
		"method":"get-folders",
		"params":{"repositoryId":10172, "parentFolderId":0},
		"id":123,
		"jsonrpc":"2.0"
	}

##### Default parameters

When accessing *secure* JSON web services (user has to be authenticated), some parameters will be available by default. They do not need to be explicitly specified, only if you want to change their default value.

Default parameters are:

+ `userId` - id of authenticated user
+ `user` - full User object
+ `companyId` - users company
+ `serviceContext` - empty service context object 

##### Object parameters

Most services accept simple parameters: numbers, strings etc. However, sometimes we need to provide an object of non-simple type as a service parameter.

Similarly to null parameters and the '-' prefix, to create an instance of some parameter just prefix its name with a plus sign, `+`,  without any parameter value at all. For example:

	/jsonws/foo/get-bar/zap-id/10172/start/0/end/1/+foo

or as request parameter (`+` sign has to be encoded!):

	/jsonws/foo/get-bar?zapId=10172&start=0&end=1&%2Bfoo

or

	<input type="hidden" name="+foo" value=""/>

If parameter is of abstract type or interface, it can't be created. We must specify the concrete implementation. This can be done by adding suffix to the parameter, for example:

	/jsonws/foo/get-bar/zap-id/10172/start/0/end/1/+foo:com.liferay.impl.FooBean

or

	<input type="hidden" name="+foo:com.liferay.impl.FooBean" value=""/>

Concrete implementation can be set as a value, too! For example:

	<input type="hidden" name="+foo" value="com.liferay.impl.FooBean"/>

or in JSON RPC:

	"+foo" : "com.liferay.impl.FooBean"

All these examples specifies a concrete implementation for `foo` service method parameter.

##### Map and List parameters

`Map` and `List` parameters are also supported, by sending JSON objects and arrays. If parameters `Map` or `List` contains generics information (e.g. `Map<Locale, String>`), portal will generify the map/list entries before passing value to the method.

##### Inner Parameters

In some cases it is needed to populate objects that are passed as parameters. A good example is a default parameter `serviceContext`. Sometimes, you need to set some of its inner properties, such as: `addGroupPermissions`, `scopeGroupId` etc. to make a successful JSONWS call.

To pass inner parameters, just provide them as usual and by using a 'dot' notation. For this example it means that you can provide: `serviceContext.addGroupPermissions`, `serviceContext.scopeGroupId`  parameters, together with others. They will be recognized as inner parameters (with a dot in the name) and their values will be *injected* into existing parameters, before API service method is executed.

Inner parameters are not count as regular parameters for matching methods.

Note: use inner parameters with object parameters to set inner content of created parameter instances! 

#### Matching service methods

It is important to understand how service methods are matched from the URL path, especially when some method is overloaded.

General rule is that besides the method name, you must provide **all** parameters as well. It is not important how parameters are provided (as part of the URL line or as request parameters...), neither the order is important. Even if some parameter will be `null`, you must provide it.

Therefore, the global rule is that methods are matched by method and parameter names.

Note that inner parameters are ignored during matching.

##### Using hints

It is also possible to add numeric hints that specify how many method arguments a service has. Hints are added as numbers separated by a dot in method name. For example:

	/foo/method.2/param1/123/-param2

Here, the `.2` is a hint, so only service methods with 2 arguments will be matched, others will be ignored for matching.

One important difference when hint is specified is that now you do not have to specify all parameters. All missing arguments are treated as `null`. Therefore, previous example may be called as:

	/foo/method.2/param1/123

and `param2` will be set to `null`. 

#### Returned values

No matter which way invoked, JSON Web Services return JSON string that represents service method result. Returned object is loosely serialized to JSON string and returned to calling client.

## RESTful Interfaces (new TODO)

TODO Include Atom and loose serialization with JSON

## Liferay Services Oriented Architecture

Liferay includes a utility called the *Service Builder* which is used to generate all of the low level code for accessing resources from the portal database. This utility is further explained in *Liferay in Action*, but it is mentioned here because of its feature which generates interfaces not only for Java code, but also for web services and JavaScript. This means that the method calls for storing and retrieving portal objects are all the same, and are generated in the same step.

![Figure 8.x:  Liferay SOA's first layer of security](../../images/portal-admin-ch6_html_m1232b4ba.png)

Because the actual method calls for retrieving data are the same regardless of how one gets access to those methods (i.e., locally or through web services), Liferay provides a consistent interface for accessing portal data that few other products can match. The actual interfaces for the various services are covered in *Liferay in Action*, but before they can be used there are steps that need to be taken to enable users to access those services remotely.

In the default `portal.properties` file, there is a section called **Main Servlet**. This section defines the security settings for all of the remote services provided by Liferay. Copy this section and paste it into your custom `portal-ext.properties` file, and you can configure security settings for the Axis Servlet, the Liferay Tunnel Servlet, the Spring Remoting Servlet, the JSON Tunnel Servlet, and the WebDAV servlet.

By default, a user connecting from the same machine Liferay is running on can access remote services so long as that user has the permission to use those services in Liferay's permissions system. Of course, you are not really “remote�? unless you are accessing services from a different machine. Liferay has two layers of security when it comes to accessing its services remotely. Without explicit rights to both layers, a remote exception will be thrown and access to those services will not be granted.

The first layer of security that a user needs to get through in order to call a method from the service layer is servlet security. The *Main Servlet* section of the `portal-ext.properties` file is used to enable or disable access to Liferay's remote services. In that section of the properties file, there are properties for each of Liferay's remote services.

You can set each service individually with the security settings that you require. For example, you may have a batch job which runs on another machine in your network. This job looks in a particular shared folder on your network and uploads documents to your community's document library portlet on a regular basis, using Liferay's web services. To enable this batch job to get through the first layer of security, you would modify the `portal-ext.properties` file and put the IP address of the machine on which the batch job is running in the list for that particular service. For example, if the batch job uses the Axis web services to upload the documents, you would enter the IP address of the machine on which the batch job is running to the `axis.servlet.hosts.allowed` property. A typical entry might look like this:

axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, SERVER\_IP

If the machine on which the batch job is running has the IP address `192.168.100.100`, this configuration will allow that machine to connect to Liferay's web services and pass in user credentials to be used to upload the documents.

![Figure 8.x: Liferay SOA's second layer of security](../../images/portal-admin-ch6_html_57e67878.png)

The second layer of security is Liferay's security model that it uses for every object in the portal. The user ID that accesses the services remotely must have the proper permission to operate on the objects it will be accessing. Otherwise, a remote exception will be thrown. The Portal Administrator will need to make use of Liferay's usual means of granting access to these resources to the user ID that will be operating on them remotely. For example, say that a Document Library folder called *Documents* has been set up in a community. A role has been created called *Document Uploaders* which has the rights to add documents to this folder. Your batch job will be accessing Liferay's web services in order to upload documents into this folder. In order for this to work, you will have to call the web service using a user ID that is a member of this group (or that has individual rights to add documents to this folder). Otherwise, you will be prevented from using the Web Service.

To call the web service using credentials, you would use the following URL syntax:

http://" + userIdAsString + ":" + password + "@<server.com\>:<port\>/tunnel-web/secure/axis/" + serviceName

The user ID is the user's ID from the Liferay database. This may be obtained by logging in as the user and clicking *My Account* from the Dock. In the top left corner of the portlet that appears is the user ID.

For example, to get Organization data using a user that has the ID of *2* with a password of *test*, you would use the following URL:

http://2:test@localhost:8080/tunnel-web/secure/axis/Portal\_OrganizationService

It is important to note here how *Password Policies* (covered in Chapter 3) can be used in combination with this feature. If you are enforcing password policies on your users (requiring them to change their passwords on a periodic basis, etc.), any administrative ID which accesses Liferay's web services in a batch job will have its password expire too.

To prevent this from happening, you can add a new password policy which does not enforce the password expiration and add your administrative user ID to it. Then your batch job can run as many times as you need it to, and the administrative ID's password will never expire.

In summary, accessing Liferay remotely requires the successful passing of two security checks:

1.  The IP address must be pre-configured in the server's     `portal-ext.properties` file.

2.  The user ID being used must have permission to access the resources     it is attempting to access.

### Accessing Liferay's WSDL

After configuring the security settings properly, your first step in obtaining access to remote web services is to access the WSDL. If you are on a browser on the same machine Liferay is running on, you can do this by accessing the following URL:

http://localhost:<port number\>/tunnel-web/axis

If, for example, you are running on Tomcat on port 8080, you would specify this URL:

http://localhost:8080/tunnel-web/axis

If you are accessing a web service that was created as part of a portlet plugin, the URL is similar, but uses the context of your application rather than the tunnel-web servlet. You can get a list of your Service Builder-generated WSDL documents by using the URL pattern below:

http://localhost:8080/your-portlet/axis

If you are on a different machine from the Liferay server, you will need to pass in your user credentials on the URL to access the WSDL:

http://<user ID\>:<password\>@<server name\>:<port number\>/tunnel-web/axis

In any case, once you successfully browse to this URL, you will see the list of web services.

WSDL for each service is available by clicking on the *WSDL* link next to the name of the service. There are many services; one for each of the services available from the Liferay API.

Once you click on one of the *WSDL* links, the Web Service Definition Language document will be displayed. This document can be used to generate client code in any language that supports it. You can either save the document to your local machine and then generate the client code that way, or use your tool to trigger Liferay to generate the document dynamically by using one of the URLs above.

## Service Context

The Service Context is an object that contains context information about a given API call. By using this pattern it is possible to consolidate many different methods with different sets of optional parameters into a single, easier to use method. It also aggregates information necessary for transversal features such as permissioning, tagging, categorization, etc.

First, we'll take a look at some of the most commonly used fields of the Service Context. Then we'll consider how the to go about using the Service Context.

### Fields

Here are some the most significant fields of the ServiceContext object:

-	**_addGroupPermissions:** Whether to apply group permissions from the request

-	**_addGuestPermissions:** Whether to apply guest permissions from the request

-	**_assetCategoryIds:** IDs of categories to be applied to an asset entry

-	**_assetLinkEntryIds:** Primary keys of the asset entries linked to an asset entry

-	**_assetTagNames:** Tag names to be applied to an asset entry

-	**_attributes:** Serializable parameters to be persisted in the entity model

-	**_companyId:** Company ID of the portal instance

-	**_expandoBridgeAttributes:** Additional parameters to be persisted along with the entity

-	**_languageId:** Language ID of the locale of this service context's current user

-	**_layoutURL:** Relative URL of the current page

-	**_pathMain:** Main context path of the portal

-	**_plid:** Portal layout ID of the current page

-	**_portalURL:** URL of this service context's portal

-	**_portletPreferencesIds:** Portlet preferences IDs of the current portlet

-	**_scopeGroupId:** ID of the group corresponding to the current data scope

-	**_userDisplayURL:** Complete URL of this service context's current user's profile page

-	**_userId:** ID of the current user

For descriptions of all methods available in `ServiceContext`, see its API found in the Liferay Portal Javadocs [http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/).

But, let's dig into some Liferay code to find out more and learn how to create and populate a `ServiceContext`.

### Usage

All of the fields of the `ServiceContext` class are optional, although your services that store any type of content will require you to specify at least the scope group ID. Here is a simple example of how to create a `ServiceContext` instance and pass it to a service API:

	ServiceContext serviceContext = new ServiceContext();
	serviceContext.setScopeGroupId(myGroupId);
	...
	BlogsEntryServiceUtil.addEntry(...., serviceContext);

If you are invoking the service from a servlet, a struts action or any other frontend class which has access to the `PortletRequest`, use the  utility method that will create the ServiceContext object and fill it with all the necessary values automatically. In that case the above example could be rewritten as follows:

	ServiceContext serviceContext = ServiceContextFactory.getInstance(BlogsEntry.class.getName(), portletRequest);
	
	BlogsEntryServiceUtil.addEntry(..., serviceContext);

For an example of how to populate a `ServiceContext` with information from a request object, check out the code in any of the `ServiceContextFactory.getInstance(...)` methods. Not only do the methods demonstrate populating context parameters such as scope group ID, company ID, language ID, etc. But they also demonstrate how to access and populate more complex context parameters such as tags, categories, asset links, headers, and the attributes parameter.

And have you taken a look at the Liferay-UI and Alloy UI form tags that that you can use in your front-end interface? These tags allow you to easily extract information from your forms for putting into your request object. As an example, look at how blog entries are created in  `portal-web/docroot/html/portlet/blogs/edit_entry.jsp`.

Notice that the following Liferay-UI panel and Alloy-UI input tags facilitate presenting a panel that gathers the category and tag values of your blog entry:
                                                      
		<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="blogsEntryCategorizationPanel" persistState="<%= true %>" title="categorization">
			<aui:fieldset>
				<aui:input name="categories" type="assetCategories" />

				<aui:input name="tags" type="assetTags" />
			</aui:fieldset>
		</liferay-ui:panel>

And look at how easy it is to create a panel for your user to link other assets to a blog entry! It is a breeze when you use powerful tags like  `<liferay-ui:input-asset-links />` as demonstrated here:

	<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="blogsEntryAssetLinksPanel" persistState="<%= true %>" title="related-assets">
		<aui:fieldset>
			<liferay-ui:input-asset-links
				className="<%= BlogsEntry.class.getName() %>"
				classPK="<%= entryId %>"
			/>
		</aui:fieldset>
	</liferay-ui:panel>
	
The `ServiceContext` class is a great way to pass information along in your service. We've looked at how information can be extracted from a front-end form, carried along by a request object, and placed into a `ServiceContext` for use in the business logic of a service.

Next, let's take a look at the security of your Liferay Portal and consider how to implement permissions.

## Security and Permissions

JSR-286 (and JSR-168) defines a simple security scheme using portlet roles and their mapping to portal roles. On top of that, Liferay provides a fine-grained permissions system, which developers can use to implement access security in their custom portlets. This section of the document gives an overview of the JSR-286 (JSR-168) security system, Liferay's permission system, and how to use them in your own portlets.

### JSR Portlet Security

The JSR specification defines the means to specify the roles that will be used by each portlet within its definition in `portlet.xml`. For example, the Liferay's Blogs portlet definition references 3 roles - *guest*, *power-user*, and *user*:

	<portlet>
		<portlet-name>33</portlet-name>
		<display-name>Blogs</display-name>
		<portlet-class>com.liferay.portlet.StrutsPortlet</portlet-class>
		<init-param>
			<name>view-action</name>
			<value>/blogs/view</value>
		</init-param>
		<init-param>
			<name>config-jsp</name>
			<value>/html/portlet/blogs/configuration.jsp</value>
		</init-param>
		<expiration-cache>0</expiration-cache>
		<supports>
			<mime-type>text/html</mime-type>
		</supports>
		<resource-bundle>com.liferay.portlet.StrutsResourceBundle</resource-bundle>
		<security-role-ref>
			<role-name>guest</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>power-user</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>user</role-name>
		</security-role-ref>
		<supported-public-render-parameter>categoryId</supported-public-render-parameter>
		<supported-public-render-parameter>tag</supported-public-render-parameter>
	</portlet>

These roles need to be mapped to specific roles within the portal. The reason for this mapping is to provide a means for the deployer of a portlet to resolve conflicts between roles that have the same name but are from different portlets (e.g., portlets from different developers).

---
![tip](../../images/tip-pen-paper.png) Each role named in a portlet's `<security-role-ref>` element is given permission to add the portlet to a page.
---

In order to do the mapping, it is necessary to use *portal-specific* configuration files. In the case of Liferay, you can define the mapping in `liferay-portlet.xml`. For an example, see the mapping defined inside `liferay-portlet.xml` found in `portal-web/docroot/WEB-INF`:

	<role-mapper>
		<role-name>administrator</role-name>
		<role-link>Administrator</role-link>
    </role-mapper>
    <role-mapper>
		<role-name>guest</role-name>
		<role-link>Guest</role-link>
    </role-mapper>
    <role-mapper>
		<role-name>power-user</role-name>
		<role-link>Power User</role-link>
    </role-mapper>
    <role-mapper>
		<role-name>user</role-name>
		<role-link>User</role-link>
	</role-mapper>

This means that if a portlet definition references the role `power-user`, that portlet will be mapped to the Liferay role in its database called *Power User*.

In your portlet's code, you can then use methods as defined in portlet specification:

- `getRemoteUser()`

- `isUserInRole()`

- `getUserPrincipal()`

For example, the following code could be used to check if the current user has the `power-user` role:

    if (renderRequest.isUserInRole("power-user")) {
        // ...
    }

Note by default, Liferay doesn't use the `isUserInRole()` method in any of the built-in portlets. Instead Liferay uses its own permission system directly to achieve more *fine-grained* security. The next section describes Liferay's Permission System and how to use it in your portlets, so that you can decide which option fits your needs best.

### Liferay's Permission System Overview

Adding permissions to custom portlets consists of four main steps (also known as DRAC):

1.  **D**efine all resources and their permissions.

2.  **R**egister all the resources defined in step 1 in the permissions system. This is also known as *adding resources.*

3.  **A**ssociate the necessary permissions with resources.

4.  **C**heck permission before returning resources.

### Implementing Permissions

Before you can add permissions to a portlet, let's get an understanding of the following two critical terms used throughout this section:

-	**Resource** - A generic term for any object represented in the portal. Examples of resources include portlets (e.g., Message Boards, Calendar, etc.), Java classes (e.g., Message Board Topics, Calendar Events, etc.), and files (e.g., documents, images, etc.)

-	**Permission** - An action on a resource. For example, the *view* action with respect to *viewing the calendar portlet* is defined as a permission in Liferay.

Keep in mind that permissions for a *portlet* resource are implemented a little differently from other resources such as Java classes and files. In each of the subsections below, the permission implementation for the *portlet* resource is explained first, then the model (and file) resource.

The first step is to define your resources and permissions (or *actions*). So let's consider how this is done for the blogs portlet.  Look at resource-actions XML file `blogs.xml` in `portal-impl/src/resource-actions` to see the following mapping of resources to actions:

	<?xml version="1.0"?>
	<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_1_0.dtd">

	<resource-action-mapping>
		<portlet-resource>
			<portlet-name>33</portlet-name>
			<permissions>
				<supports>
					<action-key>ADD_TO_PAGE</action-key>
					<action-key>CONFIGURATION</action-key>
					<action-key>VIEW</action-key>
				</supports>
				<site-member-defaults>
					<action-key>VIEW</action-key>
				</site-member-defaults>
				<guest-defaults>
					<action-key>VIEW</action-key>
				</guest-defaults>
				<guest-unsupported>
					<action-key>CONFIGURATION</action-key>
				</guest-unsupported>
			</permissions>
		</portlet-resource>
		<portlet-resource>
			<portlet-name>161</portlet-name>
			<permissions>
				<supports>
					<action-key>ACCESS_IN_CONTROL_PANEL</action-key>
					<action-key>CONFIGURATION</action-key>
					<action-key>VIEW</action-key>
				</supports>
				<site-member-defaults>
					<action-key>VIEW</action-key>
				</site-member-defaults>
				<guest-defaults>
					<action-key>VIEW</action-key>
				</guest-defaults>
				<guest-unsupported>
					<action-key>ACCESS_IN_CONTROL_PANEL</action-key>
					<action-key>CONFIGURATION</action-key>
				</guest-unsupported>
			</permissions>
		</portlet-resource>
		<model-resource>
			<model-name>com.liferay.portlet.blogs</model-name>
			<portlet-ref>
				<portlet-name>33</portlet-name>
			</portlet-ref>
			<permissions>
				<supports>
					<action-key>ADD_ENTRY</action-key>
					<action-key>PERMISSIONS</action-key>
					<action-key>SUBSCRIBE</action-key>
				</supports>
				<site-member-defaults />
				<guest-defaults />
				<guest-unsupported>
					<action-key>ADD_ENTRY</action-key>
					<action-key>PERMISSIONS</action-key>
					<action-key>SUBSCRIBE</action-key>
				</guest-unsupported>
			</permissions>
		</model-resource>
		<model-resource>
			<model-name>com.liferay.portlet.blogs.model.BlogsEntry</model-name>
			<portlet-ref>
				<portlet-name>33</portlet-name>
			</portlet-ref>
			<permissions>
				<supports>
					<action-key>ADD_DISCUSSION</action-key>
					<action-key>DELETE</action-key>
					<action-key>DELETE_DISCUSSION</action-key>
					<action-key>PERMISSIONS</action-key>
					<action-key>UPDATE</action-key>
					<action-key>UPDATE_DISCUSSION</action-key>
					<action-key>VIEW</action-key>
				</supports>
				<site-member-defaults>
					<action-key>ADD_DISCUSSION</action-key>
					<action-key>VIEW</action-key>
				</site-member-defaults>
				<guest-defaults>
					<action-key>ADD_DISCUSSION</action-key>
					<action-key>VIEW</action-key>
				</guest-defaults>
				<guest-unsupported>
					<action-key>DELETE</action-key>
					<action-key>DELETE_DISCUSSION</action-key>
					<action-key>PERMISSIONS</action-key>
					<action-key>UPDATE</action-key>
					<action-key>UPDATE_DISCUSSION</action-key>
				</guest-unsupported>
			</permissions>
			...
		</model-resource>
	</resource-action-mapping>

Permissions in the blogs portlet are defined at several different levels, coinciding with the different sections of the resource-actions XML file. In the `<portlet-resource>` level, actions and default permissions are defined on the portlet itself. Changes to portlet level permissions are performed on a per-site basis. These settings affect whether users can add the portlet to a page, edit the portlet's configuration, or view the portlet at all, regardless of portlet's content. All these actions are defined inside the `<supports>` tag for the permissions of the portlet resource. The default portlet-level permissions for members of the site are defined inside the `<site-member-defaults>` tag. In the case of this blogs portlet, members of the site should be able to view any blogs in the site. Likewise, default guest permissions are defined in the `<guest-defaults>` tag. Lastly, the `<guest-unsupported>` tag contains permissions forbidden to guests. So in the case of this portlet, guests cannot be given permission to configure the portlet, even by a user with the administrator role.

The next level of permissions is defined in the `<model-resource>` section. This level of permissions is based on the *scope* of an individual instance of the portlet. A *scope* in Liferay is simply a way of specifying how widely the data from an instance of a portlet is shared. For example, if you place a blogs portlet on a page in the guest site and then place another blogs portlet on another page in the *same* site, the two blogs will share the same set of posts. This demonstrates the default or *site-level* scope. However, if you reconfigure one of the two blogs changing its scope to the current page, that blogs portlet instance will no longer share content with the other instance (or any of the other blogs instances in that site). Thus, with respect to permissions, a portlet *instance* can either span an entire site or be restricted  to a single page.

The differences between the portlet instance permissions defined in the `<model-resource>` section and the portlet permissions defined in the `<portlet-resource>` section are subtle, but critical. Notice that permissions, such as the ability to *add* or *subscribe to* a blog entry, are defined at the portlet instance level. This makes it possible to have multiple distinct blog instances within a site, each with different permissions for site users. For example, a food site could have one blog open to posts from any site member, but also have a separate informational blog about the site itself restricted to posts from administrators.

After defining the portlet and portlet instance as resources, we move on to defining models within the portlet that also require permissions.  The model resource is surrounded by the `<model-resource>` tag. Within this tag, we first define the model name. Notice that the `<model-name>` is not the name of an actual Java class, but simply the fully qualified name of the portlet's package (e.g., the blog portlet's package `com.liferay.portlet.blogs`). This is the recommended convention for permissions that refer to an instance of the portlet as a whole. Next is the `<portlet-ref>` element which contains a `<portlet-name>`. The value of the `<portlet-name>` references the name of the portlet to which the model resource belongs; though unlikely, a model resource can belong to multiple portlets referenced with multiple `<portlet-name>` elements. Similar to the portlet resource, the model resource also allows you to define a list of supported actions that require permission to perform. You must list out all the performable actions that require a permission check. As you can see for a blog entry, users must belong to appropriate roles for permission to *add comments* to an entry, *delete* an entry, *change the permission* setting of an entry, *update* an entry, or simply to *view* an entry. The `<site-member-defaults>` tag, `<guest-defaults>` tag, and `<guest-unsupported>` tag have similar meaning for a *model resource* as they do for a *portlet resource*.

After defining resource permissions for your custom portlet, you then need to refer Liferay to the resource-actions XML file that provides these definitions (e.g., `blogs.xml` for the blogs portlet). For Liferay core, the resource-actions XML files would normally reside in `portal/portal-impl/classes/resource-actions` and a a file named `default.xml` file would refer to each of these files. Here is an excerpt from the `default.xml` that references the resource permission definition files for all of the Liferay built-in portlets (including the blogs portlet):

	<?xml version="1.0"?>
	<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_1_0.dtd">
	
	<resource-action-mapping>
		<resource file="resource-actions/portal.xml" />
		<resource file="resource-actions/announcements.xml" />
		<resource file="resource-actions/asset.xml" />
		<resource file="resource-actions/blogs.xml" />
		...
	</resource-action-mapping>

For your plugin, you should put your resource-actions XML file(s) (e.g. `default.xml` and `blogs.xml`) in a directory that is in your project's classpath. Then create a properties file (typically named `portlet.properties`) for your portlet that references the the file that specifies your `<resource-action-mapping>` element (e.g. `default.xml`). Within this portlet properties file, create a property named `resource.actions.configs` with the relative path to your portlet's resource-action mapping file (e.g., `default.xml`) as its value. For example, you could specify the property in this manner:

    resource.actions.configs=resource-actions/default.xml


To see an example of a portlet that defines its resources and permissions in the manner we just described, check out a copy of the Liferay source code from the Liferay  public Subversion repository and start by looking at the definition files found in the `portal-impl/src/resource-actions` directory. For an example of how permissions are defined in the context of a portlet plugin, checkout `plugins/trunk` , and look at the portlet `sample-permissions-portlet`.

Next, let's consider the permission algorithm for this version of Liferay Portal.

### Permission Algorithms

There are 6 permission algorithms that Liferay has used over the years for checking permissions. Liferay 5 introduced algorithm 5 that was based on RBAC system. Liferay 6 used algorithm 6 which was an optimized version of algorithm 5 that improved performance by using a reduced set of database tables.

It's important to note that once a permission algorithm is configured and resources are created, the algorithm cannot be changed; otherwise the existing permissions will be lost (and some system features may fail).

For all new deployments it is strongly recommended you use algorithm 6. For deployments that are using other algorithms it's recommended you migrate to algorithm 6 using the migration tools provided in the Control Panel (see Control Panel -\> Server Administration -\> Data Migration.)

For more information see options for `permissions.user.check.algorithm` in the `portal.properties` file. But next, we'll cover how to add resources.

### Adding a Resource

After defining resources and actions, the next task is to writing code that adds resources into the permissions system. A lot of the logic to add resources is encapsulated in the `ResourceLocalServiceImpl` class. So adding resources is as easy as calling the `addResources(...)` method of the `ResourceLocalServiceUtil` class. Here is the signature of that method:

	public void addResources(
			long companyId, long groupId, long userId, String name,
			String primKey, boolean portletActions,
			boolean addGroupPermissions, boolean addGuestPermissions)

For all the Java objects that require access permission, you need to make sure that they are added as resources every time a new object is created. For example, every time a user adds a new entry to her blog, the `addResources(...)` method should be called to add the new entry object to the resource system. Here's an example of the call from the `BlogsEntryLocalServiceImpl` class:

    resourceLocalService.addResources(
			entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
			BlogsEntry.class.getName(), entry.getEntryId(), false,
			addGroupPermissions, addGuestPermissions);

In the `addResources(...)` method, the parameters `companyId`, `groupId`, and `userId` should be self explanatory. The `name` parameter is the fully qualified Java class name for the resource object being added. The `primKey` parameter is the primary key of the resource object. As for the `portletActions` parameter, set this to `true` if you're adding portlet action permissions. In our example, we set it to `false` because we're adding a model resource, which should be associated with permissions related to the model action defined in `blogs.xml`. The `addGroupPermissions` and the  `addGuestPermissions` parameters are inputs from the user. If set to `true`, `ResourceLocalService` will then add the default permissions to the current group and the guest group for this resource, respectively.

To give your user the ability to choose whether to add the default site permission and/or the guest permission for the your custom portlet resources, Liferay has a custom JSP tag `<liferay-ui:input-permissions />` that you can use to quickly add that functionality. Simply insert the tag into the appropriate JSP and the checkboxes will show up on that JSP. Make sure, of course, that the tag is within the appropriate `<form>` tags.

When removing entities from the database it is also good to remove permissions mapped directly to the entity. To prevent having a lot of dead resources taking up space in the `Resource_` database table, you must remember to remove them from the `Resource_` table when the resource is no longer applicable. Perform this operation by calling the `deleteResource(...)` method of `ResourceLocalServiceService`. Here's an example of a blogs entry being removed:

	resourceLocalService.deleteResource(
		entry.getCompanyId(), BlogsEntry.class.getName(),
		ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

Great! Now that you know how to work with resource permissions, let's consider how to provide a user interface for managing resource permissions.

### Adding Permission

On the portlet level, no code needs to be written in order to have the permission system work for your custom portlet. Your custom portlet will automatically have all the permission features. If you've defined any custom permissions (supported actions) in your `portlet-resource` tag, they are automatically added to a list of permissions that users can readily choose. Of course, for your custom permissions to have any value, you'll need to show or hide certain functionality in your portlet. You can do that by checking the permission *before* performing the intended action on the resource.

In order to allow a user to set permissions on model resources, you will need to expose the permission interface to the user. This can be done by adding two Liferay UI tags to your JSP. The first one is the `<liferay-security:permissionsURL>` tag which returns a URL that takes the user to the page to configure the permission settings. The second tag is the `<liferay-ui:icon>` tag that shows a permission icon to the user. The example below demonstrates using these two tags and is from the file `view_entry_content.jspf`.

    <liferay-security:permissionsURL
	modelResource="<%= BlogsEntry.class.getName() %>"
	modelResourceDescription="<%= entry.getTitle() %>"
	resourcePrimKey="<%= entry.getPrimaryKey().toString() %>"
	var="entryURL"
	/>

    <liferay-ui:icon image="permissions" url="<%= entryURL %>" />

The attributes you need to specify for the first tag are `modelResource`, `modelResourceDescription`, `resourcePrimKey`, and `var`. The `modelResource` attribute is the fully qualified Java object class name. This class name gets translated into its more readable name as specified in file `Language.properties`.

As for the `modelResourceDescription` attribute, you can pass in anything that best describes this model instance. In this example, the blogs title was passed in. The `resourcePrimKey` attribute is simply the primary key of your model instance. The `var` attribute specifies the name of the variable to be assigned the resulting URL String. This variable is then passed to the `<liferay-ui:icon>` tag so the permission icon will have the proper URL link. There's also an optional attribute redirect that's available if you want to override the default behavior of the upper right arrow link. That is all you need to do to allow users to configure the permission settings for model resources.

Next, we'll show you how to implement permissions checking.

### Checking Permissions

The last major step to implementing permissions for your custom portlet is to add some checks that guarantee that the configured permissions are enforced. This may be done in a couple of places. For example, your business layer should check for permission before deleting a resource, or your user interface should hide a button that adds a model (e.g., a calendar event) if the user does not have permission to do so.

Similar to the other steps, the default permissions for the portlet resources are automatically checked for you. You do not need to implement anything for your portlet to discriminate whether a user is allowed to view or to configure the portlet itself. However, you do need to implement checking of any custom permission you have defined in your resource-actions XML file. In the blogs portlet, one custom supported action is `ADD_ENTRY`. There are two places in the source code to check for this permission. The first place to check for the add entry permission is in your JSP files. The presence of the add entry button is contingent on whether the user has permission to add entry.

    <%
	if (permissionChecker.hasPermission(
	scopeGroupId, "com.liferay.portlet.blogs.model",
	scopeGroupId, "ADD_ENTRY") {
	// Show add entry button
	}
    %>

The second place to check for the add entry permission is in the business logic. If the check fails, a `PrincipalException` is thrown `and the add entry request is aborted.`

    if (!permissionChecker.hasPermission(
	scopeGroupId, "com.liferay.portlet.blogs.model",
	scopeGroupId, "ADD_ENTRY")) {
	    throw new PrincipalException();
    }

    blogsEntryLocalService.addEntry(...);

The `PermissionChecker` class has a method called `hasPermission(...)` that checks whether a user making a resource request has the necessary access permission. If the user is not signed in (guest user), it checks for guest permissions. Otherwise, it checks for user permissions.  Let's do a quick review of the parameters of this method:

-   `groupId`:  represents the scope in which the permission check is being performed.  In Liferay, the scopes can be a specific site, an organization, a personal site of a user, etc.  This is important because a user may be allowed to add blog entries in a given site but not in another. For resources that do not belong to an scope like those mentioned, the value of this parameter should be `0`. There are several ways to obtain the `groupId` of the current scope:

    -   JSP that uses the `<theme:defineObjects/>` tag: there is an implicit variable called `scopeGroupId`.

    -   Business logic class: When using the ServiceContext pattern, it can be obtained using `serviceContext.getScopeGroupId()`.          -   Other cases: it can be obtained from the theme display request object:

            ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
            	WebKeys.THEME_DISPLAY);
            long scopeGroupId = themeDisplay.getScopeGroupId();

-   `name`:  The name of the resource as specified in the XML file of the previous sections.

-   `primKey`:  The primary key of the resource. In this example, since the resource doesn't exist as an entry in the database we use the groupId again. If we were checking for a permission on a given blog entry, we would use the primary key of that blog entry instead.

-   `actionId`:  The name of the action as entered in the XML file.  It is a common practice to create a helper class with constants for all the actions defined, so that it's easier to search usages.

In the examples above, we are assuming that there is a variable called `permissionChecker` already available.  Liferay automatically creates a `PermissionChecker` instance for every request that has all the necessary information from the user and caches the security checks to ensure good performance. There are several ways to obtain this instance:

-   In a JSP that uses the `<theme:defineObjects/>` tag: there is an implicit variable called `permissionChecker`.

-   When using ServiceBuilder, every service implementation class can access the `PermissionChecker` instance by using the method `getPermissionChecker()`.

-   Other cases: it can be obtained from the theme display request object:

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker permissionChecker =
        	themeDisplay.getPermissionChecker();

### Creating Helper Classes for Permission Checking

Often, it is a good practice to create helper classes that encapsulate the use of permissionChecker and the names of the resources for a specific portlet. This is especially useful when there are complex parent/child relationships or if your permission logic calls for checking multiple action types. `BlogsPermission` is an example of a permission helper class. See how `BlogsPermission` may be used in a JSP.

    <%
	if (BlogsPermission.contains(permissionChecker, scopeGroupId,
	    ActionKeys.ADD_ENTRY)) {
		// show add entry button
	    }
    %>

Now, let's take a look at how a `ServiceImpl` class `BlogsEntryServiceImpl` uses the `BlogsPermission` helper class. In method `BlogsEntryServiceImpl.addEntry(...)`, a call is made to check whether the incoming request has permission to add entry.  The check is done using helper class `BlogsPermission`.  If the check fails, it throws a `PrincipalException` and the add entry request aborts.

	public BlogsEntry addEntry(
			String title, String description, String content,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, boolean allowPingbacks,
			boolean allowTrackbacks, String[] trackbacks, boolean smallImage,
			String smallImageURL, String smallImageFileName,
			InputStream smallImageInputStream, ServiceContext serviceContext)
		throws PortalException, SystemException {

		BlogsPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ENTRY);

		return blogsEntryLocalService.addEntry(
			getUserId(), title, description, content, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			allowPingbacks, allowTrackbacks, trackbacks, smallImage,
			smallImageURL, smallImageFileName, smallImageInputStream,
			serviceContext);
	}

Note the parameters passed into the `check(...)` method. Again, the `getPermissionChecker()` method is readily available in all `ServiceImpl` classes. The blogs entry ID is available in the serviceContext indicating that the permission check is against the blogs portlet. `ActionKeys.ADD_ENTRY` is a static String to indicate the action requiring the permission check. Likewise, you are encouraged to use custom portlet action keys.

Let's review what we've just covered. Implementing permission into your custom portlet consists of four main steps. First step is to define any custom resources and actions. Next step is to implement code to register (or add) any newly created resources such as a `BlogsEntry` object. The third step is to provide an interface for the user to configure permission. Lastly, implement code to check permission before returning resources or showing custom features. Two major resources are portlets and Java objects. There is not a lot that needs to be done for the portlet resource to implement the permission system since Liferay Portal has a lot of that work done for you. You mainly focus your efforts on any custom Java objects you've built. You're now well equipped to implement security in your custom Liferay portlets!

## Search Engine API (new TODO)

### Faceted Search (new TODO)

### Using Multiple Search Engines (new TODO)
