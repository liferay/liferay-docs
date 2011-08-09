# Liferay APIs and Frameworks

This chapter provides you with a brief overview of several of the
essential APIs and frameworks provided by Liferay to developers. An API
is any programing interface that you can invoke from your own code
either directly through a Java invocation or through web services to
perform a certain action. A framework, in this context, is a set of APIs
and configuration that is designed for an specific purpose such as
enhancing your applications with a permission system, with tags, with
categories, comments, etc.

This chapter will keep evolving with more information about the existing
APIs and frameworks and how to use it. So look back for more information
often.

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

\
\

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

\
\

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

\
\

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

\

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

\
\

BlogsPermission.*check*(

getPermissionChecker(), serviceContext.getScopeGroupId(),

ActionKeys.*ADD\_ENTRY*);

\
\

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

## Asset Framework

The asset framework provides a set of functionalities that are common to
several different content types. It was initially created to be able to
add tags to blog entries, wiki pages, web content, etc without having to
reimplement this same functionality over and over. Since then, it has
grown to add more functionalities and it has been made possible to use
the framework for custom applications even if they are implemented
within a plugin.

The term *asset* is used as a generic way to refer to any type of
content regardless of whether it's purely text, an external file, a URL,
an image, an record in an online book library, etc. From now on,
whenever the word asset is used, think of it as a generic way to refer
to documents, blog entries, bookmarks, wiki pages, etc.

Here are the main functionalities that you will be able to reuse thanks
to the asset framework:

-   Associate tags to custom content types (new tags will be created
    automatically when the author assigns them to the content).

-   Associate categories to custom content types (authors will only be
    allowed to select from predefined categories within several
    predefined vocabularies)

-   Manage tags from the control panel (including merging tags)

-   Manage categories from the control panel (including creating complex
    hierachies).

-   Keep track of the number of visualizations of an asset.

-   Publish your content using the Asset Publisher portlet. Asset
    Publisher is able to publish dynamic lists of assets or manually
    selected lists of assets. It is also able to show a summary view of
    an asset and offer a link to the full view if desired. Because of
    this it will save you time since for many use cases it will make it
    unnecessary to develop custom portlets for your custom content
    types.

If these functionalities seem useful for your case, then you might be
wondering, what do I have to do to benefit from them?

The following subsections describe the steps involved in using the asset
framework. The first one is mandatory and consists on letting the
framework know whenever one of your custom content entries is added,
updated or deleted. The second part is optional but can save a lot of
time so most developers will probably make use of it. It consists on
using a set of taglibs to provide widgets that allow authors to enter
tags and categories as well as how to show the entered tags and
categories along with the content. The rest of the sections are also
optional but offer interesting functionalities such as how to allow your
custom assets to be published through the Asset Publisher.

#### Adding, updating and deleting assets

Whenever one of your custom content is created you need to let the asset
framework know. Don't worry, it is simple. You just need to invoke a
method of the asset framework. When invoking this method you will also
let the framework know about the tags and/or categories of the content
that was just authored.

All the methods that you will need to invoke are part of the
AssetEntryLocalService. In particular you should access these methods
using either the static methods of AssetLocalServiceUtil or by using an
instance of the AssetEntryLocalService injected by Spring. To make this
section simpler we will be using the former, since it doesn't require
any special setup in your application.

The method that you need to invoke when one of your custom content has
been added or updated is the same and is called updateAsset. Here is the
full signature:

[AssetEntry](http://cdn.docs.liferay.com/portal/6.0/javadocs/com/liferay/portlet/asset/model/AssetEntry.html)
[updateEntry](http://cdn.docs.liferay.com/portal/6.0/javadocs/src-html/com/liferay/portlet/asset/service/AssetEntryLocalService.html#line.343)(

long userId, long groupId,
[String](http://java.sun.com/javase/6/docs/api/java/lang/String.html?is-external=true)
className, long classPK,
[String](http://java.sun.com/javase/6/docs/api/java/lang/String.html?is-external=true)
classUuid, long[] categoryIds,

[String](http://java.sun.com/javase/6/docs/api/java/lang/String.html?is-external=true)[]
tagNames, boolean visible,
[Date](http://java.sun.com/javase/6/docs/api/java/util/Date.html?is-external=true)
startDate,
[Date](http://java.sun.com/javase/6/docs/api/java/util/Date.html?is-external=true)
endDate,

[Date](http://java.sun.com/javase/6/docs/api/java/util/Date.html?is-external=true)
publishDate,
[Date](http://java.sun.com/javase/6/docs/api/java/util/Date.html?is-external=true)
expirationDate,
[String](http://java.sun.com/javase/6/docs/api/java/lang/String.html?is-external=true)
mimeType,
[String](http://java.sun.com/javase/6/docs/api/java/lang/String.html?is-external=true)
title,

[String](http://java.sun.com/javase/6/docs/api/java/lang/String.html?is-external=true)
description,
[String](http://java.sun.com/javase/6/docs/api/java/lang/String.html?is-external=true)
summary,
[String](http://java.sun.com/javase/6/docs/api/java/lang/String.html?is-external=true)
url, int height, int width,

[Integer](http://java.sun.com/javase/6/docs/api/java/lang/Integer.html?is-external=true)
priority, boolean sync)

throws
[PortalException](http://cdn.docs.liferay.com/portal/6.0/javadocs/com/liferay/portal/kernel/exception/PortalException.html),
[SystemException](http://cdn.docs.liferay.com/portal/6.0/javadocs/com/liferay/portal/kernel/exception/SystemException.html)

Here is an example invocation to this method extracted out from the
blogs portlets that comes bundled with Liferay:

assetEntryLocalService.updateEntry(

userId, entry.getGroupId(), BlogsEntry.class.getName(),

entry.getEntryId(), entry.getUuid(), assetCategoryIds,

assetTagNames, visible, null, null, entry.getDisplayDate(), null,

ContentTypes.TEXT\_HTML, entry.getTitle(), null, summary, null, 0, 0,

null, false);

Here is a quick summary of the most important parameters of this method:

-   *userId*: is the identifier of the user who created the content

-   *groupId*: identifies the scope in which the content has been
    created. If your content does not support scopes, you can just pass
    0 as the value.

-   *className*: identifies the type of asset. By convention we
    recommend that it is the name of the Java class that represents your
    content type, but you can actually use any String you want as long
    as you are sure that it is unique.

-   *classPK*: identifies the specific content being created among any
    other of the same type. It is usually the primary key of the table
    where the custom content is stored. The *classUuid* parameter can
    optionally be used to specify a secondary identifier that is
    guaranteed to be unique universally. Having this type of identifier
    is specially useful if your contents will be exported and imported
    across separate portals.

-   *assetCategoryIds* and *assetTagNames*: represent the categories and
    tags that have been selected by the author of the content. The asset
    framework will sotre them for you.

-   *visible*: specifies whether this content should be shown at all by
    Asset Publisher.

-   *title,* *description* and *summary:* are descriptive fields that
    will be used by the Asset Publisher when displaying entries of your
    content type.

-   *publishDate* and *expirationDate:* can be optionally specified to
    let Asset Publisher know that it should not show the content before
    a given publication date of after a given expiration date.

-   All other fields are optional and might not make sense in all cases.
    The *sync* parameter should always be false unless you are doing
    something very advanced (look at the code if you are really
    curious).

\
\

When one of your custom content is deleted you should also let the Asset
Framework know, to clean up the information stored and also to make sure
that the Asset Publisher doesn't show any information for a content that
has been deleted. The signature of method to do this is:

void
[deleteEntry](http://cdn.docs.liferay.com/portal/6.0/javadocs/src-html/com/liferay/portlet/asset/service/AssetEntryLocalService.html#line.218)(

[String](http://java.sun.com/javase/6/docs/api/java/lang/String.html?is-external=true)
className, long classPK)

throws
[PortalException](http://cdn.docs.liferay.com/portal/6.0/javadocs/com/liferay/portal/kernel/exception/PortalException.html),
[SystemException](http://cdn.docs.liferay.com/portal/6.0/javadocs/com/liferay/portal/kernel/exception/SystemException.html)

Here is an example invocation extracted again from the blogs portlet:

assetEntryLocalService.deleteEntry(

BlogsEntry.class.getName(), entry.getEntryId());

#### Entering and displaying tags and categories

The previous section showed how you can let the asset framework know
about the tags and categories that have been associated with a given
asset, but how does a content author specify such tags and categories?

The answer is that you can choose any method that you prefer, but
Liferay provides a set of JSP tags that you can use to make this task
very easy. The following tags can be used within the form you have
created to create your type of content to allow entering tags or
selecting a predefined category:

<label\>Tags</label\>

<liferay-ui:asset-tags-selector

className="<%= entry.getClass().getName() %\>"

classPK="<%= entry.getPrimaryKey() %\>"

/\>\
\
\

<label\>Categories</label\>

<liferay-ui:asset-categories-selector

className="<%= entry.getClass().getName() %\>"

classPK="<%= entry.getPrimaryKey() %\>"

/\>

\
\

These two taglibs will create appropriate form controls that allow the
user to enter any tag (even if it doesn't exist) or search and select
one of the existing categories.

\

![image](../../images/08-apis-and-frameworks_html_5c790363.png)**Tip:**If you are
using Liferay's Allow Form taglibs, then creating a field to enter tags
or categories is even simpler. You just need to use <aui:input
name="tags" type="assetTags" /\> and <aui:input name="categories"
type="assetCategories" /\> respectively.

\
\

Once the tags and categories have been entered you will want to show
them somewhere along with the content of the asset, there are another
pair of tags that you can use to do so:

<label\>Tags</label\>

<liferay-ui:asset-tags-summary

className="<%= entry.getClass().getName() %\>"

classPK="<%= entry.getPrimaryKey() %\>"

/\>\
\
\

<label\>Categories</label\>

<liferay-ui:asset-categories-summary

className="<%= entry.getClass().getName() %\>"

classPK="<%= entry.getPrimaryKey() %\>"

/\>

In both tags you can also use an optional parameter called portletURL.
When specifying this parameter each of the tags will be a link built
with the provided URL and adding a “tag” parameter or a “categoryId”
parameter. This is very useful in order to provide support for tags
navigation and categories navigation within your portlet. But you will
need to take care of implementing this functionality yourself in the
code of the portlet by reading the values of those two parameters and
using the AssetEntryService to query the database for entries based on
the specified tag or category.

#### Publishing assets with Asset Publisher

One of the nice benefits of using the asset framework is the possibility
of using the Asset Publisher portlet, which is part of the Liferay
distribution, to publish lists of your custom asset types. These lists
can be dynamic (for example based on the tags or categories that the
asset has) or manually selected by an administrator.

In order to be able to display your assets the Asset Publisher needs to
know how to access some metadata of them and also needs you to provide
templates for the different type of views that it can display (abstract
and full view).

You can provide all this information to the Asset Publisher through a
pair of classes that implement the AssetRendererFactory interface and
the AssetRenderer interface:

-   [AssetRendererFactory](http://docs.liferay.com/portal/6.0/javadocs/com/liferay/portlet/asset/model/AssetRendererFactory.html):
    this is the class that knows how to retrieve specific assets from
    the persistent storage from the classPK (that is usually the primary
    key, but can be anything you have chosen when invoking the
    updateAsset method used to add or update the asset). This class
    should be able to grab the asset from a groupId (that identifies an
    scope of data) and a urlTitle (which is a title that can be used in
    friendly URLs to refer uniquele to the asset within a given scope).
    Finally, it can also provide a URL that the Asset Publisher can use
    when a user wants to add a new asset of your custom type. This URL
    should point to your own portlet. There are other less important
    methods, but you can avoid implementing them by extending from
    [BaseAssetRendererFactory](http://docs.liferay.com/portal/6.0/javadocs/com/liferay/portlet/asset/model/BaseAssetRendererFactory.html).
    Extending from this class, instead of implementing the interface
    directly will also make your code more robust if there are changes
    in the interface in future versions of Liferay, since the base
    implementation will provide custom implementations for them.

-   [AssetRenderer](http://docs.liferay.com/portal/6.0/javadocs/com/liferay/portlet/asset/model/AssetRenderer.html):
    this is the class that provides metadata information about one
    specific asset and is also able to check for permissions to edit or
    view it for the current user. It is also reponsible for rendering
    the asset for the different templates (abstract, and full content),
    by forwarding to an specific JSP. It is also recommended that
    instead of implementing the interface directly, you extend from the
    [BaseAssetRenderer](http://docs.liferay.com/portal/6.0/javadocs/com/liferay/portlet/asset/model/BaseAssetRenderer.html)
    class, that provides with nice defaults and more robustness for
    methods that could be added to the interface in the future.

Let's seen an example of these two classes. Again we will pick Liferay's
Blogs portlet. Lets start with the implementation for the
AssetRendererFactory:

public class BlogsEntryAssetRendererFactory extends
BaseAssetRendererFactory {

\
\

public static final String CLASS\_NAME = BlogsEntry.class.getName();

\
\

public static final String TYPE = "blog";

\
\

public AssetRenderer getAssetRenderer(long classPK, int type)

throws PortalException, SystemException {

\
\

BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(classPK);

\
\

return new BlogsEntryAssetRenderer(entry);

}

\
\

public AssetRenderer getAssetRenderer(long groupId, String urlTitle)

throws PortalException, SystemException {

\
\

BlogsEntry entry = BlogsEntryServiceUtil.getEntry(\
 groupId, urlTitle);

\
\

return new BlogsEntryAssetRenderer(entry);

}

\
\

public String getClassName() {

return CLASS\_NAME;

}

\
\

public String getType() {

return TYPE;

}

\
\

public PortletURL getURLAdd(

LiferayPortletRequest liferayPortletRequest,

LiferayPortletResponse liferayPortletResponse)

throws PortalException, SystemException {

\
\

HttpServletRequest request =

liferayPortletRequest.getHttpServletRequest();

\
\

ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(

WebKeys.THEME\_DISPLAY);

\
\

if (!BlogsPermission.contains(

themeDisplay.getPermissionChecker(),

themeDisplay.getScopeGroupId(), ActionKeys.ADD\_ENTRY)) {

\
\

return null;

}

\
\

PortletURL portletURL = PortletURLFactoryUtil.create(

request, PortletKeys.BLOGS, getControlPanelPlid(themeDisplay),

PortletRequest.RENDER\_PHASE);

\
\

portletURL.setParameter("struts\_action", "/blogs/edit\_entry");

\
\

return portletURL;

}

\
\

public boolean hasPermission(

PermissionChecker permissionChecker, long classPK, String actionId)

throws Exception {

\
\

return BlogsEntryPermission.contains(

permissionChecker, classPK, actionId);

}

\
\

protected String getIconPath(ThemeDisplay themeDisplay) {

return themeDisplay.getPathThemeImages() + "/blogs/blogs.png";

}

\
\

}

\
\

And here is the AssetRenderer implementation:

public class BlogsEntryAssetRenderer extends BaseAssetRenderer {

\
\

public BlogsEntryAssetRenderer(BlogsEntry entry) {

\_entry = entry;

}

\
\

public long getClassPK() {

return \_entry.getEntryId();

}

\
\

public String getDiscussionPath() {

if (PropsValues.BLOGS\_ENTRY\_COMMENTS\_ENABLED) {

return "edit\_entry\_discussion";

}

else {

return null;

}

}

\
\

public long getGroupId() {

return \_entry.getGroupId();

}

\
\

public String getSummary(Locale locale) {

return HtmlUtil.stripHtml(\_entry.getContent());

}

\
\

public String getTitle(Locale locale) {

return \_entry.getTitle();

}

\
\

public PortletURL getURLEdit(

LiferayPortletRequest liferayPortletRequest,

LiferayPortletResponse liferayPortletResponse) {

\
\

PortletURL portletURL = liferayPortletResponse.createRenderURL(

PortletKeys.BLOGS);

\
\

portletURL.setParameter("struts\_action", "/blogs/edit\_entry");

portletURL.setParameter(\
 "entryId", String.valueOf(\_entry.getEntryId()));

\
\

return portletURL;

}

\
\

public String getUrlTitle() {

return \_entry.getUrlTitle();

}

\
\

public String getURLViewInContext(

LiferayPortletRequest liferayPortletRequest,

LiferayPortletResponse liferayPortletResponse,

String noSuchEntryRedirect) {

\
\

ThemeDisplay themeDisplay =

(ThemeDisplay)liferayPortletRequest.getAttribute(

WebKeys.THEME\_DISPLAY);

\
\

return themeDisplay.getPortalURL() + themeDisplay.getPathMain() +

"/blogs/find\_entry?noSuchEntryRedirect=" +

HttpUtil.encodeURL(noSuchEntryRedirect) + "&entryId=" +

\_entry.getEntryId();

}

\
\

public long getUserId() {

return \_entry.getUserId();

}

\
\

public String getUuid() {

return \_entry.getUuid();

}

\
\

public boolean hasEditPermission(PermissionChecker permissionChecker) {

return BlogsEntryPermission.contains(

permissionChecker, \_entry, ActionKeys.UPDATE);

}

\
\

public boolean hasViewPermission(PermissionChecker permissionChecker) {

return BlogsEntryPermission.contains(

permissionChecker, \_entry, ActionKeys.VIEW);

}

\
\

public boolean isPrintable() {

return true;

}

\
\

public String render(

RenderRequest renderRequest, RenderResponse renderResponse,

String template)

throws Exception {

\
\

if (template.equals(TEMPLATE\_ABSTRACT) ||

template.equals(TEMPLATE\_FULL\_CONTENT)) {

\
\

renderRequest.setAttribute(WebKeys.BLOGS\_ENTRY, \_entry);

\
\

return "/html/portlet/blogs/asset/" + template + ".jsp";

}

else {

return null;

}

}

\
\

protected String getIconPath(ThemeDisplay themeDisplay) {

return themeDisplay.getPathThemeImages() + "/blogs/blogs.png";

}

\
\

private BlogsEntry \_entry;

\
\

}

Note that in the render method, there is a forward to a JSP in the case
of the abstract and the full content templates. The abstract is not
mandatory and if it is not provided, the Asset Publisher will show the
title and the summary obtained through the appropriate methods of the
renderer. The full content template should always be provided. Here is
how it looks like for blogs entries:

<%@ include file="/html/portlet/blogs/init.jsp" %\>

\
\

<%

BlogsEntry entry =
(BlogsEntry)request.getAttribute(WebKeys.BLOGS\_ENTRY);

%\>

\
\

<%= entry.getContent() %\>

\
\

<liferay-ui:custom-attributes-available className="<%=
BlogsEntry.class.getName() %\>"\>

<liferay-ui:custom-attribute-list

className="<%= BlogsEntry.class.getName() %\>"

classPK="<%= (entry != null) ? entry.getEntryId() : 0 %\>"

editable="<%= false %\>"

label="<%= true %\>"

/\>

</liferay-ui:custom-attributes-available\>

\
\

That's about it. It wasn't that hard, right? Now you can start enjoying
the benefits of the asset framework in your custom portlets.

## Service Builder

Service Builder is a model-driven code generation tool built by Liferay
to automate the creation of interfaces and classes for database
persistence and a service layer. Service Builder will generate most of
the common code needed to implement find, create, update, and delete
operations on the database, allowing you to focus on the higher level
aspects of service design.

The service layer generated by Service Builder, has an implementation
class that is responsible to handle retrieving and storing data classes
and adding the necessary business logic around them. This layer can
optionally be composed of two layers, the local service and the remote
service. The local service contains the business logic and accesses the
persistence layer. It can be invoked by client code running in the same
Java Virtual Machine. The remote service usually ads a code to check
security and is meant to be accessible from anywhere over the Internet
or your local network. Service Builder automatically generates the code
necessary to allow access to the remote services using SOAP, JSON and
Java RMI.

### Define the Model

The first step in using Service Builder is to define your model classes
and their attributes in a `service.xml`{.western} file. For convenience,
we will define the service within the **my-greeting** portlet, although
it should be placed inside a new portlet. Create a file named
`service.xml`{.western} in
`portlets/my-greeting-portlet/docroot/WEB-INF`{.western} inside the
Plugins SDK and add the following content:

<?xml version="1.0"?\>

<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder
6.0.0//EN"
"http://www.liferay.com/dtd/liferay-service-builder\_6\_0\_0.dtd"\>

<service-builder package-path="com.sample.portlet.library"\>

<namespace\>Library</namespace\>

<entity name="Book" local-service="true" remote-service="true"\>

\
\

<!-- PK fields --\>

\
\

<column name="bookId" type="long" primary="true" /\>

\
\

<!-- Group instance --\>

\
\

<column name="groupId" type="long" /\>

\
\

<!-- Audit fields --\>

\
\

<column name="companyId" type="long" /\>

<column name="userId" type="long" /\>

<column name="userName" type="String" /\>

<column name="createDate" type="Date" /\>

<column name="modifiedDate" type="Date" /\>

\
\

<!-- Other fields --\>

\
\

<column name="title" type="String" /\>

</entity\>

</service-builder\>

#### Overview of *service.xml*

<service-builder package-path="**com.sample.portlet.library**"\>

This specifies the package path that the class will generate to. In this
example, classes will generate to
`WEB-INF/src/com/sample/portlet/library/`{.western}

<namespace\>**Library**</namespace\>

The namespace element must be a unique namespace for this component.
Table names will be prepended with this namepace.

<entity name="**Book**" local-service="true" remote-service="false"\>

The entity name is the database table you want to create.

<column name="title" type="String" /\>

Columns specified in `service.xml`{.western} will be created in the
database with a data type appropriate to the Java type. Accessors will
be automatically generated for these attributes in the model class.

**Tip**: Always consider adding two long fields called *groupId* and
*companyId* to your data models. These two fields will allow your
portlet to support the multi-tenancy features of Liferay so that each
community or organization (for each portal instance) can have its own
independent data.

### Generate the Service

Open a terminal window in your `portlets/my-greeting-portlet`{.western}
directory and enter this command:

ant build-service

The service has been generated successfully when you see “BUILD
SUCCESSFUL.” In the terminal window, you should see that a large number
of files have been generated. An overview of these files is provided
below:

-   Persistence

    -   BookPersistence - book persistence interface @generated

    -   BookPersistenceImpl - book persistence @generated

    -   BookUtil - book persistence util, instances BookPersistenceImpl
        @generated

-   Local Service

    -   **BookLocalServiceImpl** - local service implementation. This is
        the only class within the local service that you will be able to
        modify manually. Your business logic will be here.

    -   BookLocalService - local service interface @generated

    -   BookLocalServiceBaseImpl - local service base @generated
        @abstract

    -   BookLocalServiceUtil - local service util, instances
        BookLocalServiceImpl @generated

    -   BookLocalServiceWrapper - local service wrapper, wraps
        BookLocalServiceImpl @generated

-   Remote Service

    -   **BookServiceImpl** - remove service implementation. Put here
        the code that adds additional security checks and invokes the
        local service.

    -   BookService - remote service interface @generated

    -   BookServiceBaseImpl - remote service base @generated @abstract

    -   BookServiceUtil - remote service util, instances BookServiceImpl
        @generated

    -   BookServiceWrapper - remote service wrapper, wraps
        BookServiceImpl @generated

    -   BookServiceSoap - soap remote service, proxies BookServiceUtil
        @generated

    -   BookSoap - soap book model, similar to BookModelImpl, does not
        implement Book @generated

    -   BookServiceHttp - http remote service, proxies BookServiceUtil
        @generated

    -   BookJSONSerializer - json serializer, converts Book to JSON
        array @generated

-   Model

    -   BookModel - book base model interface @generated

    -   BookModelImpl - book base model @generated

    -   Book - book model interface @generated

    -   **BookImpl** - book model implementation. You can use this class
        to add additional methods to your model other than the
        autogenerated field getters and setters.

    -   BookWrapper - book wrapper, wraps Book @generated

Out of all of these classes only three can be manually modified:
BookLocalServiceImpl, BookServiceImpl and BookImpl.

### Write the Local Service Class

In the file overview above, you will see that **BookLocalService** is
the interface for the local service. It contains the signatures of every
method in **BookLocalServiceBaseImpl** and **BookLocalServiceImpl**.
**BookLocalServiceBaseImpl** contains a few automatically generated
methods providing common functionality. Since this class is generated,
you should never modify it, or your changes will be overwritten the next
time you run Service Builder. Instead, all custom code should be placed
in **BookLocalServiceImpl**.

Open the following file:

`/docroot/WEB-INF/src/com/sample/portlet/library/service/impl/BookLocalServiceImpl.java`{.western}

We will be adding the database interaction methods to this service layer
class. Add the following method to the **BookLocalServiceImpl** class:

public Book addBook(long userId, String title)

throws PortalException, SystemException {

User user = UserUtil.findByPrimaryKey(userId);

Date now = new Date();

long bookId = CounterLocalServiceUtil.increment(Book.class.getName());

\
\

Book book = bookPersistence.create(bookId);

\
\

book.setTitle(title);

book.setCompanyId(user.getCompanyId());

book.setUserId(user.getUserId());

book.setUserName(user.getFullName());

book.setCreateDate(now);

book.setModifiedDate(now);

book.setTitle(title);

\
\

return bookPersistence.update(book);

}

Before you can use this new method, you must add its signature to the
**BookLocalService** interface by running service builder again.

Navigate to the root folder of your portlet in the terminal and run:

ant build-service

Service Builder looks through **BookLocalServiceImpl** and automatically
copies the signatures of each method into the interface. You can now add
a new book to the database by making the following call

BookLocalServiceUtil.addBook(userId, “A new title”);

### Built-In Liferay Services

In addition to the services you create using Service Builder, your
portlets may also access a variety of services built into Liferay. These
include `UserService`{.western}, `OrganizationService`{.western},
`GroupService`{.western}, `CompanyService`{.western},
`ImageService`{.western}, `LayoutService`{.western},
`OrganizationService`{.western}, `PermissionService`{.western},
`UserGroupService`{.western}, and `RoleService`{.western}. For more
information on these services, see *Liferay in Action* and Liferay's
Javadocs.

## Other frameworks

Liferay has a wide variety of frameworks that make it much easier to
develop complex functionalities for your own applications with little
effort. These frameworks have evolved from the applications bundled with
Liferay out of the box so they have been proven in the real world, even
in very high performance portals.

This chapter is a placeholder that provides a quick description to the
main frameworks provided with Liferay 6. Note that what follows is a
work in progress since more sections will be added over time and some of
the current sections will evolve into its own chapter as we add more
information and detailed instructions on how to use them over time.

-   File Storage Framework: Allows storing files using the backend of
    the Document Library. By using this framework you won't have to
    worry yourself about clustering or backups since that will already
    be taken care of for the Document Library itself. This framework is
    used, for example, by the wiki and the message boards of Liferay to
    store attached files in pages and posts respectively. You can check
    the sourcecode of these two portlets for great real-life examples of
    how to use the framework.

-   Workflow Framework: Allows adding Workflow functionality to your own
    portlets. One great benefit of using this framework is that you will
    be able to reuse all of the workflow management UIs provided by
    Liferay. Also you will be able to abstract your code from the
    specific workflow engine that will be used (JBPM, Liferay Kaleo, …).
    Many Liferay portlets use this framework. If you want a simple
    example to learn how to use it, the blogs portlet is a good start.

-   Comments Framework: Allows adding comments easily in any portlet
    without any database code. Many Liferay portlets use this
    functionality, for example the blogs portlet for the comments of
    each entry.

-   Custom fields: A portlet that uses custom fields will allow the end
    user to extend the fields of its data entries with custom ones
    defined by the end user. To see a list of data types in Liferay that
    support this functionality just go to the Control Panel \> Custom
    Fields.

-   Report abuse: Allow end users to report that some information
    published in a page should not be there.

-   Inline permissions Framework: Allows enhancing your SQL queries so
    that the database takes care of checking for view permissions. This
    is particularly useful when doing queries for data entries that
    could result in a large number of items (and thus checking of
    permissions afterwards would be very ineficient) or when you want to
    implement pagination (which would not work fine if permissions are
    checked afterwards and an item is removed). The Document Library or
    the Message Boards of Liferay are examples of portlets that use this
    functionality.

-   ServiceContext: The ServiceContext object contains a set of fields
    that are common to many different services. It is used, for example
    to carry tags, categories, permissions information, … It is not a
    framework in itself but rather a utility object that helps usage of
    the other frameworks.

Check in the near future for new editions of the Developer's Guide for
extended information on each of these frameworks.
