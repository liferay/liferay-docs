
# Liferay APIs

This chapter provides an overview of several of the
essential Liferay *application programming interfaces* (*APIs*) available to
developers. An API is a programing interface that can be invoked from your own
code, either directly through a Java invocation or through web services, to
perform an action or set of actions.

Liferay comes with a host of APIs that expose powerful portal and portlet
services. You can access Liferay's built-in *portal* services for such entities
as User, Organization, UserGroup, Company, Role, Permission, ResourcePermission
and Layout. In addition, Liferay's built-in *portlet* services for which APIs
are provided include the blogs, wikis, assets, social equity, and the
Document & Media Library services.

The APIs can be called via Java method or web service invocations giving you
flexibility to implement client code in Java or other languages (even a
scripting language such as PHP) that support web service invocations.  The APIs
can be called from within a portlet or non-portlet code. You can make calls to
the APIs *locally* from within the portal or *remotely* outside of the
*Java virtual machine* (*JVM*) in which the portal is running.  So, let's look
at in more detail at how we can access Liferay APIs.

## Local Access

Each service built with Service Builder provides a local interface to clients
running in the same JVM as the portal. Since Liferay's portal and portlet
services are built using the Service Builder, their public service methods can
be accessed through static methods provided by auto-generated
`-LocalServiceUtil` classes.

The following JSP code snippet demonstrates
how to get a list of the most recent bloggers from an organization. It invokes
static method `getOrganizationStatsUsers()` on `-LocalServiceUtil`
class `BlogsStatsUserLocalServiceUtil`:

    <%@ page import="com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil" %>
    <%@ page import="com.liferay.portlet.blogs.util.comparator.StatsUserLastPostDateComparator" %>
    ...
    <%@
    List statsUsers = BlogsStatsUserLocalServiceUtil.getOrganizationStatsUsers(
        organizationId, 0, max, new StatsUserLastPostDateComparator());
    %>

## Remote Access

Services are also made available to *remote* clients (clients running outside
of the portal JVM). Service Builder generates remote interfaces for
your entities if their `remote-service` attribute is set to `true` in your
portlet's `service.xml`. Here is an example of how remote services are turned
on for Liferay's `Country` entity:

    <entity name="Country" local-service="false" remote-service="true">
\

The generated services can be accessed using web services or Java RMI.
We'll explore how to user web services to invoke several remote services.

### SOAP Web Services

Service Builder generates classes to use your services via
*Simple Object Access Protocol* (*SOAP*) over HTTP. SOAP is the
*packaging* protocol and HTTP is the *transport* protocol. For our
example, we'll look at the SOAP web service classes for Liferay's `Company`,
`User`, and `UserGroup` portal services to accomplish the following:

1. List each `UserGroup` to which user `joebloggs` belongs
2. Add a new `UserGroup` named `MyGroup` 
3. Add user `joebloggs` to the `UserGroup` 
\

Here are the SOAP related classes that we'll use:

    com.liferay.client.soap.portal.model.CompanySoap
    com.liferay.client.soap.portal.service.http.CompanyServiceSoap
    com.liferay.client.soap.portal.service.http.CompanyServiceSoapServiceLocator

    com.liferay.client.soap.portal.model.UserGroupSoap
    com.liferay.client.soap.portal.service.http.UserServiceSoap
    com.liferay.client.soap.portal.service.http.UserServiceSoapServiceLocator

    com.liferay.client.soap.portal.service.http.UserGroupServiceSoap
    com.liferay.client.soap.portal.service.http.UserGroupServiceSoapServiceLocator
\
    
You can see in the listing a naming convention involving classes with
suffixes `-ServiceSoapServiceLocater`, `-ServiceSoap`, and `-Soap`. The
`-ServiceSoapServiceLocater` class finds the `-ServiceSoap` by means of the
service's URL you provide. The `-ServiceSoap` class implements the Java Remote
Method Invocation (RMI) interface bound to the the services specified in the
*Web Services Definition Language* (*WSDL*) file for each service. Lastly,
`-SOAP` classes are the serializeable implementations of the models.  Let's
look at how to determine the URLs for these services.
\
\

Service Builder was used to publish these services as a web services.  You
can view a listing of the services deployed on your portal by opening your
browser to the URL of the format `http://host:port/tunnel-web/secure/axis`
for your *secure* services (services requiring user authentication) and
`http://host:port/tunnel-web/axis` for your services that do not require
user authentication. We've used *secure* services such as the following for
`UserGroup`:

![Figure 8.x: UserGroup Web Service listing](../../images/wsdl-summary-listing.png)

Each web service is listed with its name, operations, and a link to its WSDL
file. The WSDL is written in XML and provides a model for describing and
locating the web service.

![Figure 8.x: WSDL Excerpt for the addUserGroup operation of UserGroup](../../images/wsdl-for-user-group-service.png)

You pass in the WSDL URL along with your login credentials to the SOAP service
locator for your service.

The following code demonstrates locating and invoking operations to add a new
`UserGroup` named `MyUserGroup` and assign user with screen name `joebloggs`
to that `UserGroup`:

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
\

Some things to note about the URL:

- It is *secure* (authenticated) URL for the service. Authentication is done
using HTTP Basic Authentication, which of course is not appropriate for a
production environment, since the password is unencrypted.
- The screen name and password are passed in as credentials.  The
authorization type specified for your portal's company dictates the
authorization type you must use to access your web service. You can override
the `company.security.auth.type` found in `portal.properties` by specifying
your desired authorization type in your `portal-ext.properties`. Valid
authorization type values are:
    - `screenName`
    - `userId`
    - `emailAddress`
- The name of the service (e.g., `Portal_UserGroupService`) is specified at the
end of the URL. Remember that the service name can be found in the web
service listing like the one we looked at previously.
\

The operations `getCompanyByVirtualHost()`,  `getUserIdByScreenName()`,
`getUserUserGroups()`, `addUserGroup()` and `addUserGroupUsers()` are specified
for the `-ServiceSOAP` classes `CompanyServiceSoap`, `UserServiceSoap` and 
`UserGroupServiceSoap` in the WSDL files. Information on parameter types,
parameter order, request type, response type, and return type
are conveniently specified in the WSDL for each Liferay web service!
\

Now, let's say you want write your client in a language other than Java ...
no problem! You can use any language that supports web services invocation.

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
\  

### JSON Web Services (new TODO)

TODO consider calling a method that uses service context (like an update method)

## RESTful Interfaces (new TODO)

TODO Include Atom and loose serialization with JSON 

## Security and Permissions

JSR-286 (and JSR-168) defines a simple security scheme using portlet roles
and their mapping to portal roles. On top of that, Liferay provides a
fine-grained permissions system, which developers can use to implement
access security in their custom portlets. This section of the document
gives an overview of the JSR-286 (JSR-168) security system, Liferay's
permission system, and how to use them in your own portlets.

### JSR Portlet Security

The JSR specification defines the means to specify the roles that will
be used by each portlet within its definition in `portlet.xml`. For
example, the Liferay's Blogs portlet definition references 3
roles - *guest*, *power-user*, and *user*:

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

These roles need to be mapped to specific roles within the portal. The
reason for this mapping is to provide a means for the deployer of a portlet to
resolve conflicts between roles that have the same name but are from different
portlets (e.g., portlets from different developers).

![tip](../../images/tip-pen-paper.png)**Tip:** Each role named in a
portlet's `<security-role-ref>` element is given permission to add the portlet
to a page.

In order to do the mapping, it is necessary to use *portal-specific*
configuration files. In the case of Liferay, you can define the mapping in
`liferay-portlet.xml`. For an example, see the mapping defined inside
`liferay-portlet.xml` found in `portal-web/docroot/WEB-INF`:

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

This means that if a portlet definition references the role `power-user`,
that portlet will be mapped to the Liferay role in its database called *Power
User*.

In your portlet's code, you can then use methods as defined in portlet
specification:

- `getRemoteUser()`
    
- `isUserInRole()`
    
- `getUserPrincipal()`

For example, the following code could be used to check if the current user has
the `power-user` role:

    if (renderRequest.isUserInRole("power-user")) {
        // ...
    }

Note by default, Liferay doesn't use the `isUserInRole()` method in any of the
built-in portlets. Instead Liferay uses its own permission system directly to
achieve more *fine-grained* security. The next section describes Liferay's
Permission System and how to use it in your portlets, so that you can decide
which option fits your needs best.

### Liferay's Permission System Overview

Adding permissions to custom portlets consists of four main steps (also
known as DRAC):

1.  **D**efine all resources and their permissions.

2.  **R**egister all the resources defined in step 1 in the permissions
    system. This is also known as *adding resources.*

3.  **A**ssociate the necessary permissions with resources.

4.  **C**heck permission before returning resources.

### Implementing Permissions

Before you can add permissions to a portlet, let's get an understanding of the
following two critical terms used throughout this section:

- **Resource** - A generic term for any object represented in the portal.
Examples of resources include portlets (e.g., Message Boards, Calendar,
etc.), Java classes (e.g., Message Board Topics, Calendar Events, etc.),
and files (e.g., documents, images, etc.)

- **Permission** - An action on a resource. For example, the *view* action
with respect to *viewing the calendar portlet* is defined as a permission in
Liferay.

Keep in mind that permissions for a *portlet* resource are implemented a
little differently from other resources such as Java classes and files.
In each of the subsections below, the permission implementation for the
*portlet* resource is explained first, then the model (and file) resource.

The first step is to define your resources and permissions (or *actions*). So
let's consider how this is done for the blogs portlet.  Look at resource-actions XML
file `blogs.xml` in `portal-impl/src/resource-actions` to see the following mapping
of resources to actions:

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

Permissions in the blogs portlet are defined at several different levels,
coinciding with the different sections of the resource-actions XML file. In the
`<portlet-resource>` level, actions and default permissions are defined on the
portlet itself. Changes to portlet level permissions are performed on a
per-site basis. These settings affect whether users can add the portlet to a
page, edit the portlet's configuration, or view the portlet at all, regardless
of portlet's content. All these actions are defined inside the `<supports>`
tag for the permissions of the portlet resource. The default portlet-level
permissions for members of the site are defined inside the
`<site-member-defaults>` tag. In the case of this blogs portlet, members of the
site should be able to view any blogs in the site. Likewise, default guest
permissions are defined in the `<guest-defaults>` tag. Lastly, the
`<guest-unsupported>` tag contains permissions forbidden to guests.
So in the case of this portlet, guests cannot be given permission to configure
the portlet, even by a user with the administrator role.

The next level of permissions is defined in the `<model-resource>` section.
This level of permissions is based on the *scope* of an individual instance of
the portlet. A *scope* in Liferay is simply a way of specifying how widely the
data from an instance of a portlet is shared. For example, if you place a blogs
portlet on a page in the guest site and then place another blogs portlet on
another page in the *same* site, the two blogs will share the same set of
posts. This demonstrates the default or *site-level* scope. However, if you
reconfigure one of the two blogs changing its scope to the current page, that
blogs portlet instance will no longer share content with the other instance (or
any of the other blogs instances in that site). Thus, with respect to
permissions, a portlet *instance* can either span an entire site or be
restricted  to a single page.

The differences between the portlet instance permissions defined in the
`<model-resource>` section and the portlet permissions defined in the
`<portlet-resource>` section are subtle, but critical. Notice that
permissions, such as the ability to *add* or *subscribe to* a blog entry, are
defined at the portlet instance level. This makes it possible to have multiple
distinct blog instances within a site, each with different permissions for site
users. For example, a food site could have one blog open to posts from any site
member, but also have a separate informational blog about the site itself
restricted to posts from administrators.

After defining the portlet and portlet instance as resources, we move on to
defining models within the portlet that also require permissions. 
The model resource is surrounded by the `<model-resource>` tag. Within this tag, we
first define the model name. Notice that the `<model-name>` is not the name of
an actual Java class, but simply the fully qualified name of the portlet's
package (e.g., the blog portlet's package `com.liferay.portlet.blogs`). This is
the recommended convention for permissions that refer to an instance of the
portlet as a whole. Next is the `<portlet-ref>` element which contains a
`<portlet-name>`. The value of the `<portlet-name>` references the name of the
portlet to which the model resource belongs; though unlikely, a model
resource can belong to multiple portlets referenced with multiple
`<portlet-name>` elements. Similar to the portlet resource, the model resource
also allows you to define a list of supported actions that require permission
to perform. You must list out all the performable actions that require a
permission check. As you can see for a blog entry, users must belong to
appropriate roles for permission to *add comments* to an entry, *delete* an
entry, *change the permission* setting of an entry, *update* an entry, or
simply to *view* an entry. The `<site-member-defaults>` tag, `<guest-defaults>`
tag, and `<guest-unsupported>` tag have similar meaning for a *model resource*
as they do for a *portlet resource*.

After defining resource permissions for your custom portlet, you then
need to refer Liferay to the resource-actions XML file that provides these
definitions (e.g., `blogs.xml` for the blogs portlet). For Liferay core, the
resource-actions XML files would normally reside in
`portal/portal-impl/classes/resource-actions` and a a file named `default.xml`
file would refer to each of these files. Here is an excerpt from the
`default.xml` that references the resource permission definition files for all
of the Liferay built-in portlets (including the blogs portlet):

	<?xml version="1.0"?>
	<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_1_0.dtd">
	
	<resource-action-mapping>
		<resource file="resource-actions/portal.xml" />
		<resource file="resource-actions/announcements.xml" />
		<resource file="resource-actions/asset.xml" />
		<resource file="resource-actions/blogs.xml" />
		...
	</resource-action-mapping>

For your plugin, you should put your resource-actions XML file(s) (e.g.
`default.xml` and `blogs.xml`) in a directory that is in your project's
classpath. Then create a properties file (typically named `portlet.properties`)
for your portlet that references the the file that specifies your
`<resource-action-mapping>` element (e.g. `default.xml`). Within this portlet
properties file, create a property named `resource.actions.configs` with the
relative path to your portlet's resource-action mapping file (e.g.,
`default.xml`) as its value. For example, you could specify the property in
this manner:

    resource.actions.configs=resource-actions/default.xml


To see an example of a portlet that defines its resources and permissions in
the manner we just described, check out a copy of the Liferay source code
from the Liferay  public Subversion repository and start by looking at the
definition files found in the `portal-impl/src/resource-actions` directory. For
an example of how permissions are defined in the context of a portlet plugin,
checkout `plugins/trunk` , and look at the portlet
`sample-permissions-portlet`.

### Permission Algorithms

There are 6 permission algorithms that Liferay has used over the years for
checking permissions. Liferay 5 introduced algorithm 5 that was based on
RBAC system. Liferay 6 used algorithm 6 which was an optimized version of
algorithm 5 that improved performance by using a reduced set of database
tables.

It's important to note that once a permission algorithm is configured
and resources are created, the algorithm cannot be changed; otherwise the
existing permissions will be lost (and some system features may fail).

For all new deployments it is strongly recommended you use algorithm 6.
For deployments that are using other algorithms it's recommended you
migrate to algorithm 6 using the migration tools provided in the Control Panel
(see Control Panel -\> Server Administration -\> Data Migration.)

For more information see options for `permissions.user.check.algorithm` in the
`portal.properties` file.

### Adding a Resource

After defining resources and actions, the next task is to writing code
that adds resources into the permissions system. A lot of the logic to
add resources is encapsulated in the
`ResourceLocalServiceImpl` class. So adding resources is as
easy as calling the `addResources(...)` method of the
`ResourceLocalServiceUtil` class. Here is the signature of that method:

	public void addResources(
			long companyId, long groupId, long userId, String name,
			String primKey, boolean portletActions,
			boolean addGroupPermissions, boolean addGuestPermissions)

For all the Java objects that require access permission, you need to
make sure that they are added as resources every time a new object is
created. For example, every time a user adds a new entry to her blog,
the `addResources(...)` method should be called to add the new entry object to
the resource system. Here's an example of the call from the
`BlogsEntryLocalServiceImpl` class:

    resourceLocalService.addResources(
			entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
			BlogsEntry.class.getName(), entry.getEntryId(), false,
			addGroupPermissions, addGuestPermissions);

In the `addResources(...)` method, the parameters `companyId`, `groupId`, and
`userId` should be self explanatory. The `name` parameter is the
fully qualified Java class name for the resource object being added. The
`primKey` parameter is the primary key of the resource object.
As for the `portletActions` parameter, set this to `true` if
you're adding portlet action permissions. In our example, we set it to
`false` because we're adding a model resource, which should be associated
with permissions related to the model action defined in
`blogs.xml`. The `addGroupPermissions` and the 
`addGuestPermissions` parameters are inputs from the user. If
set to `true`, `ResourceLocalService` will then add the default
permissions to the current group and the guest group for this
resource, respectively.

To give your user the ability to choose whether to add the default site
permission and/or the guest permission for the your custom portlet resources,
Liferay has a custom JSP tag `<liferay-ui:input-permissions />` that you can
use to quickly add that functionality. Simply insert the tag into the
appropriate JSP and the checkboxes will show up on that JSP. Make sure, of
course, that the tag is within the appropriate `<form>` tags.

When removing entities from the database it is also good to remove
permissions mapped directly to the entity. To prevent having a lot of
dead resources taking up space in the `Resource_` database table, you
must remember to remove them from the `Resource_` table when the resource
is no longer applicable. Perform this operation by calling the
`deleteResource(...)` method of `ResourceLocalServiceService`. Here's an
example of a blogs entry being removed:

	resourceLocalService.deleteResource(
		entry.getCompanyId(), BlogsEntry.class.getName(),
		ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

### Adding Permission

On the portlet level, no code needs to be written in order to have the
permission system work for your custom portlet. Your custom portlet will
automatically have all the permission features. If you've defined any
custom permissions (supported actions) in your
`portlet-resource` tag, they are automatically added to a
list of permissions that users can readily choose. Of course, for
your custom permissions to have any value, you'll need to show or hide
certain functionality in your portlet. You can do that by checking the
permission *before* performing the intended action on the resource.

In order to allow a user to set permissions on model resources, you
will need to expose the permission interface to the user. This can be
done by adding two Liferay UI tags to your JSP. The first one is the
`<liferay-security:permissionsURL>` tag which returns a URL
that takes the user to the page to configure the permission settings.
The second tag is the `<liferay-ui:icon>` tag that shows a
permission icon to the user. The example below demonstrates using these two
tags and is from the file `view_entry_content.jspf`.

    <liferay-security:permissionsURL
	modelResource="<%= BlogsEntry.class.getName() %>"
	modelResourceDescription="<%= entry.getTitle() %>"
	resourcePrimKey="<%= entry.getPrimaryKey().toString() %>"
	var="entryURL"
	/>
    
    <liferay-ui:icon image="permissions" url="<%= entryURL %>" />

The attributes you need to specify for the first tag are `modelResource`,
`modelResourceDescription`, `resourcePrimKey`, and `var`. The `modelResource`
attribute is the fully qualified Java object class name. This class name gets
translated into its more readable name as specified in file
`Language.properties`.

As for the `modelResourceDescription` attribute, you can pass
in anything that best describes this model instance. In this example, the
blogs title was passed in. The `resourcePrimKey` attribute is
simply the primary key of your model instance. The `var`
attribute specifies the name of the variable to be assigned the resulting URL
String. This variable is then passed to the `<liferay-ui:icon>` tag so
the permission icon will have the proper URL link. There's also an
optional attribute redirect that's available if you want to override the
default behavior of the upper right arrow link. That is all you need to
do to allow users to configure the permission settings for model
resources.

### Checking Permissions

The last major step to implementing permissions for your custom portlet is
to add some checks that guarantee that the configured permissions are
enforced. This may be done in a couple of places. For example, your business
layer should check for permission before deleting a resource, or your
user interface should hide a button that adds a model (e.g., a calendar
event) if the user does not have permission to do so.

Similar to the other steps, the default permissions for the portlet
resources are automatically checked for you. You do not need to
implement anything for your portlet to discriminate whether a user is
allowed to view or to configure the portlet itself. However, you do need
to implement checking of any custom permission you have defined in your
resource-actions XML file. In the blogs portlet, one custom supported
action is `ADD_ENTRY`. There are two places in the source code to check
for this permission. The first place to check for the add entry permission is
in your JSP files. The presence of the add entry button is contingent on
whether the user has permission to add entry.

    <%
	if (permissionChecker.hasPermission(
	scopeGroupId, "com.liferay.portlet.blogs.model",
	scopeGroupId, "ADD_ENTRY") {
	// Show add entry button
	}
    %>

The second place to check for the add entry permission is in the
business logic. If the check fails, a
`PrincipalException` is thrown `and the add entry request is aborted.`

    if (!permissionChecker.hasPermission(
	scopeGroupId, "com.liferay.portlet.blogs.model",
	scopeGroupId, "ADD_ENTRY")) {
	    throw new PrincipalException();
    }

    blogsEntryLocalService.addEntry(...);

The `PermissionChecker` class has a method called `hasPermission(...)` that
checks whether a user making a resource request has the necessary access
permission. If the user is not signed in (guest user), it checks for guest
permissions. Otherwise, it checks for user permissions.  Let's do a quick
review of the parameters of this method:

-   `groupId`:  represents the scope in which the permission check is being
performed.  In Liferay, the scopes can be a specific site, an
organization, a personal site of a user, etc.  This is important because a user
may be allowed to add blog entries in a given site but not in another. For
resources that do not belong to an scope like those mentioned, the value of
this parameter should be `0`. There are several ways to obtain the `groupId` of
the current scope:

    -   JSP that uses the `<theme:defineObjects/>` tag: there is an implicit
    variable called `scopeGroupId`.

    -   Business logic class: When using the ServiceContext pattern, it can be
    obtained using `serviceContext.getScopeGroupId()`.
    
    -   Other cases: it can be obtained from the theme display request object:

            ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
            	WebKeys.THEME_DISPLAY);
            long scopeGroupId = themeDisplay.getScopeGroupId();

-   `name`:  The name of the resource as specified in the XML file of the
previous sections.

-   `primKey`:  The primary key of the resource. In this example, since the
resource doesn't exist as an entry in the database we use the groupId again.
If we were checking for a permission on a given blog entry, we would use the
primary key of that blog entry instead.

-   `actionId`:  The name of the action as entered in the XML file.  It is a
common practice to create a helper class with constants for all the actions
defined, so that it's easier to search usages.

In the examples above, we are assuming that there is a variable called
`permissionChecker` already available.  Liferay automatically creates a
`PermissionChecker` instance for every request that has all the necessary
information from the user and caches the security checks to ensure good
performance. There are several ways to obtain this instance:

-   In a JSP that uses the `<theme:defineObjects/>` tag: there is an implicit
variable called `permissionChecker`.

-   When using ServiceBuilder, every service implementation class can access
the `PermissionChecker` instance by using the method `getPermissionChecker()`.

-   Other cases: it can be obtained from the theme display request object:

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker permissionChecker = 
	    	themeDisplay.getPermissionChecker();

### Creating Helper Classes for Permission Checking

Often, it is a good practice to create helper classes that encapsulate the use
of permissionChecker and the names of the resources for a specific portlet.
This is especially useful when there are complex parent/child relationships or
if your permission logic calls for checking multiple action
types. `BlogsPermission` is an example of a permission helper class. See how
`BlogsPermission` may be used in a JSP.

    <%
	if (BlogsPermission.contains(permissionChecker, scopeGroupId,
	    ActionKeys.ADD_ENTRY)) {
		// show add entry button
	    }
    %>

Now, let's take a look at how a `ServiceImpl` class `BlogsEntryServiceImpl`
uses the `BlogsPermission` helper class. In method
`BlogsEntryServiceImpl.addEntry(...)`, a call is made to check whether the
incoming request has permission to add entry.  The check is done using helper
class `BlogsPermission`.  If the check fails, it throws a `PrincipalException`
and the add entry request aborts.

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

Note the parameters passed into the `check(...)` method. Again, the
`getPermissionChecker()` method is readily available in all
`ServiceImpl` classes. The blogs entry ID is available in the
serviceContext indicating that the permission check is against the blogs
portlet. `ActionKeys.ADD_ENTRY` is a static String to indicate
the action requiring the permission check. Likewise, you are encouraged
to use custom portlet action keys.

Let's review what we've just covered. Implementing permission into your
custom portlet consists of four main steps. First step is to define any
custom resources and actions. Next step is to implement code to register
(or add) any newly created resources such as a `BlogsEntry`
object. The third step is to provide an interface for the user to
configure permission. Lastly, implement code to check permission before
returning resources or showing custom features. Two major resources are
portlets and Java objects. There is not a lot that needs to be done for
the portlet resource to implement the permission system since Liferay
Portal has a lot of that work done for you. You mainly focus your
efforts on any custom Java objects you've built. You're now well
equipped to implement security in your custom Liferay portlets!

## Search Engine API (new TODO)

### Faceted Search (new TODO)

### Using Multiple Search Engines (new TODO)

