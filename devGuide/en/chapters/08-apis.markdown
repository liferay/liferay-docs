# Liferay APIs

This chapter provides you with a brief overview of several of the
essential APIs by Liferay to developers. An API
is any programing interface that you can invoke from your own code
either directly through a Java invocation or through web services to
perform a certain action.

This chapter will keep evolving with more information about the existing
APIs and frameworks and how to use it. So look back for more information
often.

## Accessing Services (new)

### Local Access (new)

### Remote Access (new)

#### SOAP Web Services (new)

#### JSON Web Services (new)

### Client Examples (new)

## RESTful Interfaces (new)
TODO Include Atom and loose serialization with JSON 

## Security and Permissions

JSR-286 (and JSR-168) define simple security scheme using portlet roles
and their mapping to portal roles. On top of that Liferay implements a
fine-grained permissions system, which developers can use to implement
access security in their custom portlets. This section of the document
provides an overview of the JSR-286 (JSR-168) security system, Liferay's
permission system, and how to implement them in your own portlets.

### JSR Portlet Security

The JSR specification defines the means to specify the roles that will
be used by each portlet within its definition in portlet.xml. For
example, the Blogs portlet definition included in Liferay references 3
roles`:`{.western}

<portlet\>

<portlet-name\>33</portlet-name\>

<display-name\>Blogs</display-name\>

<portlet-class\>com.liferay.portlet.StrutsPortlet</portlet-class\>

<init-param\>

<name\>view-action</name\>

<value\>/blogs/view</value\>

</init-param\>

<expiration-cache\>0</expiration-cache\>

<supports\>

<mime-type\>text/html</mime-type\>

</supports\>

<resource-bundle\>com.liferay.portlet.StrutsResourceBundle</resource-bundle\>

<security-role-ref\>

<role-name\>guest</role-name\>

</security-role-ref\>

**<security-role-ref\>**

**<role-name\>power-user</role-name\>**

**</security-role-ref\>**

<security-role-ref\>

<role-name\>user</role-name\>

</security-role-ref\>

</portlet\>

These roles need to be mapped to specific roles within the portal. The
reason for this mapping is to allow the deployer of a portlet to solve
conflicts if two portlets from two different developers use the same
role name for different purposes.

![image](../../images/08-apis-and-frameworks_html_5c790363.png)**Tip:** Liferay
provides an additional behavior to the roles referenced in the
portlet.xml file using the security-role-ref element. Each of those
roles will be given the permission to "Add to a page" those portlets by
default.

In order to do the mapping it is necessary to use portal-specific
configuration files. In the case of Liferay you can define mapping in
liferay-portlet.xml. For example see definition of mapping inside
`liferay-portlet.xml`{.western} in
`portal-web/docroot/WEB-INF:`{.western}

<role-mapper\>

<role-name\>administrator</role-name\>

<role-link\>Administrator</role-link\>

</role-mapper\>

<role-mapper\>

<role-name\>guest</role-name\>

<role-link\>Guest</role-link\>

</role-mapper\>

**<role-mapper\>**

**<role-name\>power-user</role-name\>**

**<role-link\>Power User</role-link\>**

**</role-mapper\>**

<role-mapper\>

<role-name\>user</role-name\>

<role-link\>User</role-link\>

</role-mapper\>


This means that if a portlet definition references the role “power-user”
it will be mapped to the Liferay role in its database called “Power
User”.

In your portlet's code you can then use methods as defined in portlet
specification:

• getRemoteUser ()

• isUserInRole()

• getUserPrincipal ()

For example to check if the current user has the “Power User” role the
following code could be used:

if (renderRequest.isUserInRole(“power-user”)) {

// ….

}

Note that Liferay doesn't use the isUserInRole() method in any of the
portlets provided by default. Instead it uses Liferay's permission
System directly, to achieve more finegrained security. The next section
describes this system and how to use it in your portlets, so that you
can decide which option fits your needs better.

### Liferay's Permission System Overview

Adding permissions to custom portlets consists of four main steps (also
known as DRAC):

1.  Define all resources and their permissions.

2.  **R**egister all the resources defined in step 1 in the permissions
    system. This is also known as “adding resources.”

3.  **A**ssociate the necessary permissions with resources.

4.  **C**heck permission before returning resources.

### Implementing Permissions

Before you can add permissions to a portlet, two critical terms must be
defined.

**Resource** - A generic term for any object represented in the portal.
Examples of resources include portlets (e.g., Message Boards, Calendar,
etc.), Java classes (e.g., Message Board Topics, Calendar Events, etc.),
and files (e.g., documents, images, etc.)

**Permission** - An action acting on a resource. For example, the *view*
in “viewing the calendar portlet” is defined as a permission in Liferay.

Keep in mind that permissions for a portlet resource are implemented a
little differently from other resources such as Java classes and files.
In each of the subsections below, the permission implementation for the
portlet resource is explained first, then the model (and file) resource.

The first step in implementing permissions is to define your resources
and permissions. You can see examples of how this is accomplished for
the built-in portlets by checking out a copy of the Liferay source code
and looking in the `portal-impl/src/resource-actions`{.western}
directory. For an example of how permissions work in the context of a
portlet plugin, checkout `plugins/trunk`{.western} from the Liferay
public Subversion repository, and look in the portlet
*sample-permissions-portlet*.

Let’s take a look at `blogs.xml`{.western} in
`portal-impl/src/resource-actions`{.western} and see how the blogs
portlet defines these resources and actions.

<?xml version="1.0"?\>

<resource-action-mapping\>

<portlet-resource\>

<portlet-name\>33</portlet-name\>

<permissions\>

<supports\>

<action-key\>ACCESS\_IN\_CONTROL\_PANEL</action-key\>

<action-key\>ADD\_TO\_PAGE</action-key\>

<action-key\>CONFIGURATION</action-key\>

<action-key\>VIEW</action-key\>

</supports\>

<community-defaults\>

<action-key\>VIEW</action-key\>

</community-defaults\>

<guest-defaults\>

<action-key\>VIEW</action-key\>

</guest-defaults\>

<guest-unsupported\>

<action-key\>ACCESS\_IN\_CONTROL\_PANEL</action-key\>

<action-key\>CONFIGURATION</action-key\>

</guest-unsupported\>

</permissions\>

</portlet-resource\>

<model-resource\>

<model-name\>com.liferay.portlet.blogs</model-name\>

<portlet-ref\>

<portlet-name\>33</portlet-name\>

</portlet-ref\>

<permissions\>

<supports\>

<action-key\>ADD\_ENTRY</action-key\>

<action-key\>PERMISSIONS</action-key\>

<action-key\>SUBSCRIBE</action-key\>

</supports\>

<community-defaults /\>

<guest-defaults /\>

<guest-unsupported\>

<action-key\>ADD\_ENTRY</action-key\>

<action-key\>PERMISSIONS</action-key\>

<action-key\>SUBSCRIBE</action-key\>

</guest-unsupported\>

</permissions\>

</model-resource\>

<model-resource\>

<model-name\>com.liferay.portlet.blogs.model.BlogsEntry</model-name\>

<portlet-ref\>

<portlet-name\>33</portlet-name\>

</portlet-ref\>

<permissions\>

<supports\>

<action-key\>ADD\_DISCUSSION</action-key\>

<action-key\>DELETE</action-key\>

<action-key\>DELETE\_DISCUSSION</action-key\>

<action-key\>PERMISSIONS</action-key\>

<action-key\>UPDATE</action-key\>

<action-key\>UPDATE\_DISCUSSION</action-key\>

<action-key\>VIEW</action-key\>

</supports\>

<community-defaults\>

<action-key\>ADD\_DISCUSSION</action-key\>

<action-key\>VIEW</action-key\>

</community-defaults\>

<guest-defaults\>

<action-key\>VIEW</action-key\>

</guest-defaults\>

<guest-unsupported\>

<action-key\>ADD\_DISCUSSION</action-key\>

<action-key\>DELETE</action-key\>

<action-key\>DELETE\_DISCUSSION</action-key\>

<action-key\>PERMISSIONS</action-key\>

<action-key\>UPDATE</action-key\>

<action-key\>UPDATE\_DISCUSSION</action-key\>

</guest-unsupported\>

</permissions\>

...

</model-resource\>

</resource-action-mapping\>

Permissions in the blogs portlet are defined at several different
levels, coinciding to the different sections of the XML file. First, in
the `<portlet-resource>`{.western} section, actions and default
permissions are defined on the portlet itself. Changes to portlet level
permissions are performed on a per-community basis. The settings here
affect whether users can add the portlet to a page, edit its
configuration, or view the portlet at all, regardless of content. All
these actions are defined inside the `<supports>`{.western} tag. The
default portlet-level permissions for members of the community are
defined inside the `<community-defaults>`{.western} tag. In this case,
members of a community should be able to view any blogs in that
community. Likewise, default guest permissions are defined in
`<guest-defaults>`{.western}. `<guest-unsupported>`{.western} contains
permissions that a guest may never be granted, even by an administrator.
For the blogs portlet, guests can never be given permission to configure
the portlet or access it in the control panel.

The next level of permissions is based on the scope of an individual
instance of the portlet. These permissions are defined in the first
`<model-resource>`{.western} section. Notice that the
`<model-name>`{.western} is not the name of an actual Java class, but
simply of the blogs package. This is the recommended convention for
permissions that refer to an instance of the portlet as a whole.

![image](../../images/08-apis-and-frameworks_html_5c790363.png)**Tip:**A “scope” in
Liferay is simply a way of specifying how widely the data from an
instance of a portlet is shared. For instance, if I place a blogs
portlet on a page in the guest community, and then place another blogs
portlet on another page in the same community, the two blogs will share
the same set of posts. This is the default or “community-level” scope.
If I then configure one of the two blogs and change its scope to the
current page, it will no longer share content with any of the other
blogs in that community. Thus, with respect to permissions, an
“instance” of a blogs portlet could exist on only one page, or span an
entire community.

The difference between the portlet instance permissions defined in this
section, and the portlet permissions in the
`<portlet-resource>`{.western} block is subtle, but critical. You will
notice that permissions such as adding an entry or subscribing are
defined at the portlet instance level. This makes it possible to have
multiple distinct blogs within a community, each with different
permissions. For instance, a food community could have one blog that
every community member could post recipes to, but also have a separate
blog containing updates and information about the site itself that only
administrators can post to.

After defining the portlet and portlet instance as resources, we move on
to define models within the portlet that also require permissions. The
model resource is surrounded by the `<model-resource>`{.western} tag.
Within this tag, we first define the model name. This must be the fully
qualified Java class name of the model. Next we define the portlet name
that this model belongs to under the `portlet-ref`{.western} tag. Though
unlikely, a model can belong to multiple portlets, which you may use
multiple `<portlet-name>`{.western} tags to define. Similar to the
portlet resource element, the model resource element also allows you to
define a supported list of actions that require permission to perform.
You must list out all the performable actions that require a permission
check. As you can see for a blog entry, a user must have permission in
order to add comments to an entry, delete an entry, change the
permission setting of an entry, update an entry, or simply to view an
entry. The `<community-defaults>`{.western} tag, the
`<guest-defaults>`{.western} tag, and the
`<guest-unsupported>`{.western} tag are all similar in meaning to what’s
explained above for a portlet resource.

After defining your permission scheme for your custom portlet, you then
need to tell Liferay the location of this file. For Liferay core, the
XML file would normally reside in
`portal/portal-impl/classes/resource-actions`{.western} and a reference
to the file would appear in the `default.xml`{.western} file. For a
plugin, you should put the file in a directory that is in the class path
for the project. Then create a properties file for your portlet (the one
in the Sample Permissions Portlet is simply called
`portlet.properties`{.western}) and create a property called
`resource.actions.configs`{.western} with a value that points to the the
XML file. Below is an example from the Sample Permissions Portlet:

resource.actions.configs=resource-actions/default.xml

### Permission Algorithms

There are 6 permission algorithms that Liferay has used during time for
checking permissions. Liferay 5 introduced algorithm 5 that is based on
RBAC system. Liferay 6 optimized algorithm 5 into version 6, which
included important performance improvements by using a reduced set of
database tables.

It's important to note that once a permission algorithm is configured
and resources are created it cannot be changed, or the existing
permissions will be lost (and some system features may fail).

For all new deployments it is strongly recommended to use algorithm 6.
For deployments that were using other algorithms it's recommended to use
the migration tools provided from the Control Panel \> Server
Administration \> Migration.

For more info see permissions.user.check.algorithm option in
portal.properties file.

### Adding a Resource

After defining resources and actions, the next task is to write code
that adds resources into the permissions system. A lot of the logic to
add resources is encapsulated in the
`ResourceLocalServiceImpl`{.western} class. So adding resources is as
easy as calling the add resource method in ResourceLocalServiceUtil
class.

public void addResources(

String companyId, String groupId, String userId, String name,

String primKey, boolean portletActions,

boolean addCommunityPermissions, boolean addGuestPermissions);

For all the Java objects that require access permission, you need to
make sure that they are added as resources every time a new one is
created. For example, every time a user adds a new entry to her blog,
the addResources(…) method is called to add the new entry to the
resource system. Here’s an example of the call from the
`BlogsEntryLocalServiceImpl`{.western} class.

ResourceLocalServiceUtil.addResources(

entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),

BlogsEntry.class.getName(), entry.getEntryId(),

false, addCommunityPermissions, addGuestPermissions);

The parameters `companyId`{.western}, `groupId`{.western}, and
`userId`{.western} should be self explanatory. The name parameter is the
fully qualified Java class name for the resource object being added. The
`primKey`{.western} parameter is the primary key of the resource object.
As for the `portletActions`{.western} parameter, set this to true if
you’re adding portlet action permissions. In our example, we set it to
false because we’re adding a model resource, which should be associated
with permissions related to the model action defined in
`blogs.xml`{.western}. The `addCommunityPermissions`{.western} and the
`addGuestPermissions`{.western} parameters are inputs from the user. If
set to true, `ResourceLocalService`{.western} will then add the default
permissions to the current community group and the guest group for this
resource respectively.

If you would like to provide your user the ability to choose whether to
add the default community permission and the guest permission for the
resources within your custom portlet, Liferay has a custom JSP tag you
may use to quickly add that functionality. Simply insert the
`<liferay-ui:input-permissions />`{.western} tag into the appropriate
JSP and the checkboxes will show up on your JSP. Of course, make sure
the tag is within the appropriate `<form>`{.western} tags.

When removing entities from database it is also good to remove
permissions mapped directly to the entity. To prevent having a lot of
dead resources taking up space in the Resource\_ database table, you
must remember to remove them from the Resource\_ table when the resource
is no longer applicable. To perform this operation call the
`deleteResource(…)`{.western} method in
`ResourceLocalServiceUtil`{.western}. Here’s an example of a blogs entry
being removed:

ResourceLocalServiceUtil.deleteResource(

entry.getCompanyId(), BlogsEntry.class.getName(),

Resource.SCOPE\_INDIVIDUAL, entry.getEntryId);


Liferay Community Edition 6.0.5 has a known bug
([http://issues.liferay.com/browse/LPS-14135](http://issues.liferay.com/browse/LPS-14135))
that causes this method to leave some data behind in the database. This
error does nos affect the latest Enterprise Edition (6.0.11 and later)
and it has also been fixed in the latest release of the Community
Edition (6.0.6 at the time of this writing)

### Adding Permission

On the portlet level, no code needs to be written in order to have the
permission system work for your custom portlet. Your custom portlet will
automatically have all the permission features. If you’ve defined any
custom permissions (supported actions) in your
`portlet-resource`{.western} tag, those are automatically added to a
list of permissions and users can readily choose them. Of course, for
your custom permissions to have any value, you’ll need to show or hide
certain functionality in your portlet. You can do that by checking the
permission first before performing the intended functionality.

In order to allow a user to set permissions on the model resources, you
will need to expose the permission interface to the user. This can be
done by adding two Liferay UI tags to your JSP. The first one is the
`<liferay-security:permissionsURL>`{.western} tag which returns a URL
that takes the user to the page to configure the permission settings.
The second tag is the `<liferay-ui:icon>`{.western} tag that shows a
permission icon to the user. Below is an example found in the file
`view_entry_content.jspf`{.western}.

<liferay-security:permissionsURL

modelResource="<%= BlogsEntry.class.getName() %\>"

modelResourceDescription="<%= entry.getTitle() %\>"

resourcePrimKey="<%= entry.getPrimaryKey().toString() %\>"

var="entryURL"

/\>


<liferay-ui:icon image="permissions" url="<%= entryURL %\>" /\>

The attributes you need to provide to the first tag are
`modelResource`{.western}, `modelResourceDescription`{.western},
`resourcePrimKey`{.western}, and `var`{.western}. The
`modelResource`{.western} attribute is the fully qualified Java object
class name. It then gets translated in `Language.properties`{.western}
to a more readable name.

As for the `modelResourceDescription`{.western} attribute, you can pass
in anything that best describes this model instance. In the example, the
blogs title was passed in. The `resourcePrimKey`{.western} attribute is
simply the primary key of your model instance. The `var`{.western}
attribute is the variable name this URL String will get assigned to.
This variable is then passed to the `<liferay-ui:icon>`{.western} tag so
the permission icon will have the proper URL link. There’s also an
optional attribute redirect that’s available if you want to override the
default behavior of the upper right arrow link. That is all you need to
do to enable users to configure the permission settings for model
resources.

### Checking Permissions

The last major step to implementing permissions to a custom portlet is
to add some checks that guarantee that the configured permissions are
met. This may be done in a couple of places. For example, your business
layer should check for permission before deleting a resource, or your
user interface should hide a button that adds a model (e.g., a calendar
event) if the user does not have permission to do so.

Similar to the other steps, the default permissions for the portlet
resources are automatically checked for you. You do not need to
implement anything for your portlet to discriminate whether a user is
allowed to view or to configure the portlet itself. However, you do need
to implement any custom permission you have defined in your
resource-actions XML file. In the blogs portlet, one custom supported
action is ADD\_ENTRY. There are two places in the source code to check
for this permission. The first place to check for the add permission is
in your JSP files. The presence of the add entry button is contingent on
whether the user has permission to add entry.

<%

if (permissionChecker.hasPermission(

scopeGroupId, “com.liferay.portlet.blogs.model”,

scopeGroupId, ”ADD\_ENTRY”) {

// Show add entry button

}

%\>

The second place to check for the add entry permission is in the
business logic. If the check fails, a
`PrincipalException`{.western}` is thrown `{.western}`and the add entry request is aborted.`{.western}

if (!permissionChecker.hasPermission(

scopeGroupId, “com.liferay.portlet.blogs.model”,

scopeGroupId, ”*ADD\_ENTRY”*)) {

throw new PrincipalException();

}

blogsEntryLocalService.addEntry(...);


`The`{.western}`PermissionChecker`{.western}` class has a method called `{.western}`hasPermission(…)`{.western}` that checks whether a user making a resource request has the necessary access permission. If the user is not signed in (guest user), it checks for guest permissions. Otherwise, it checks for user permissions.  Let's do a quick review of the parameters of this method:`{.western}

-   `groupId`{.western}`:     represents the scope in which the permission check is being     performed.  In Liferay, the scopes can be a specific community, an     organization, a personal site of a user, `{.western}`etc.`{.western}`      This is important because a user may be allowed to add blog entries     in a given community but not in another. For resources that do not     belong to an scope like those mentioned, the value of this parameter     should be `{.western}`0`{.western}`.     There are several ways to obtain the `{.western}`groupId`{.western}`     of the current scope:`{.western}

    -   `JSP         that uses the `{.western}`<theme:defineObjects/>`{.western}`         tag: there is an implicit variable called `{.western}`scopeGroupId`{.western}`.`{.western}

    -   `Business         logic class: When using the ServiceContext pattern, it can be         obtained using `{.western}`serviceContext.getScopeGroupId()`{.western}`.`{.western}

    -   `Other         cases: it can be obtained from the theme display request object:  `{.western}

`       `{.western}`ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(`{.western}

`           WebKeys.THEME_DISPLAY);`{.western}

`        `{.western}`long scopeGroupId = themeDisplay.getScopeGroupId()`{.western}

-   `name`{.western}`:     The name of the resource as specified in the XML file of the     previous sections.`{.western}

-   `primKey`{.western}`:     The primary key of the resource. In this example, since the resource     doesn't exist as an entry in the database we use the groupId again.     If we were checking for a permission on a given blog entry, we would     use the primary key of that blog entry instead.`{.western}

-   `actionId`{.western}`:     The name of the action as entered in the XML file.  It is a common     practice to create a helper class with constants for all the actions     defined, so that it's easier to search usages.`{.western}

`In the examples above, we are assuming that there is a variable called`{.western}`permissionChecker`{.western}` already available.  Liferay automatically creates a `{.western}`PermissionChecker`{.western}` instance for every request that has all the necessary information from the user and caches the security checks to ensure good performance. There are several ways to obtain this instance:`{.western}

-   `In     a JSP that uses the `{.western}`<theme:defineObjects/>`{.western}`     tag: there is an implicit variable called `{.western}`permissionChecker`{.western}`.`{.western}

-   `When     using ServiceBuilder, every service implementation class can access     the `{.western}`PermissionChecker`{.western}`     instance by using the method `{.western}`getPermissionChecker()`{.western}`.`{.western}

-   `Other     cases: it can be obtained from the theme display request object:  `{.western}

`        `{.western}`ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(`{.western}`            WebKeys.THEME_DISPLAY);`{.western}

`        `{.western}`PermissionChecker permissionChecker = `{.western}

`           themeDisplay.getPermissionChecker()`{.western}

### `Creating Helper Classes for Permission Checking`{.western}

`Often, it is a good practice to create helper classes that encapsulate the use of permissionChecker and the names of the resources for an specific portlet. This is specially useful when there are complex parent/child relationships or if your permission logic calls for checking multiple action types.  `{.western}`BlogsPermission`{.western}` is an example of a permission helper class.  See how `{.western}`BlogsPermission`{.western}` may be used in a JSP.`{.western}

<%

if (BlogsPermission.contains(permissionChecker, scopeGroupId,
ActionKeys.ADD\_ENTRY)) {

// show add entry button

}

%\>

`Now, let's take a look at how a `{.western}`ServiceImpl`{.western}` class  `{.western}`BlogsEntryServiceImpl`{.western}` uses the `{.western}`BlogsPermission`{.western}` helper class.  `{.western}`In method `{.western}`BlogsEntryServiceImpl.`{.western}`addEntry(...)`{.western}`, a call is made to check whether the incoming request has permission to add entry.  T`{.western}`he check is done using `{.western}`helper`{.western}` class `{.western}`BlogsPermission`{.western}`.  If the check fails, it throws a `{.western}`PrincipalException`{.western}` and the add entry request aborts.`{.western}

public BlogsEntry addEntry(

String title, String description, String content,

int displayDateMonth, int displayDateDay,

int displayDateYear, int displayDateHour,

int displayDateMinute, boolean allowPingbacks,

boolean allowTrackbacks, String[] trackbacks,

boolean smallImage, String smallImageURL, File smallFile,

ServiceContext serviceContext)

throws PortalException, SystemException {


BlogsPermission.*check*(

getPermissionChecker(), serviceContext.getScopeGroupId(),

ActionKeys.*ADD\_ENTRY*);


return blogsEntryLocalService.addEntry(

getUserId(), title, description, content, displayDateMonth,

displayDateDay, displayDateYear, displayDateHour,

displayDateMinute, allowPingbacks, allowTrackbacks, trackbacks,

smallImage, smallImageURL, smallFile, serviceContext);

}

Note the parameters passed into the check(...) method. Again, the
`getPermissionChecker()`{.western} method is readily available in all
`ServiceImpl`{.western} classes. The blogs entry ID is available in the
serviceContext indicating that the permission check is against the blogs
portlet. `ActionKeys.ADD_ENTRY`{.western} is a static String to indicate
the action requiring the permission check. Likewise, you are encouraged
to use custom portlet action keys.

Let’s review what we’ve just covered. Implementing permission into your
custom portlet consists of four main steps. First step is to define any
custom resources and actions. Next step is to implement code to register
(or add) any newly created resources such as a `BlogsEntry`{.western}
object. The third step is to provide an interface for the user to
configure permission. Lastly, implement code to check permission before
returning resources or showing custom features. Two major resources are
portlets and Java objects. There is not a lot that needs to be done for
the portlet resource to implement the permission system since Liferay
Portal has a lot of that work done for you. You mainly focus your
efforts on any custom Java objects you’ve built. You’re now well
equipped to implement security in your custom Liferay portlets!

## Search Engine API (new)

### Faceted Search (new)

### Using Multiple Search Engines (new)

