# Using Liferay Frameworks [](id=using-liferay-frameworks-liferay-portal-6-2-dev-guide-06-en)

Picture a hot, summer day. You're on vacation, and you're just coming back from
the beach after a day of frolicking on the sand and in the water. After all
that activity, you're hungry. Time to grill up some burgers and dogs. 

To grill hamburgers and hot dogs, you have to have a proper procedure and
apparatus for accomplishing the task. The *procedure* would be called an
algorithm in computer science terms. The *apparatus* is the framework. 

You first need a grill. That grill should be equipped with a heating mechanism;
in the case of most grills, that's either charcoal or propane gas. Obviously,
it also has a metal frame, or grill, placed near the heat. You also need tools,
such as a spatula, a plate to hold the meat, and if you're making chicken in
addition to those burgers, a brush and some barbecue sauce. 

All of these together form a *framework* for making grilled food on a hot
summer day. All the tools you need are at your disposal; you just need to pick
them up and get grilling! If the framework is already in place, it's obviously
a lot easier (and more timely) to cook food than it would be if the framework
weren't there. Just ask the cave men. 

Liferay contains several frameworks that give you all the tools you need to
perform various common tasks, such has handling permissions, letting users
enter comments, categories, and tags, and other common tasks that Liferay
doesn't make you have to write yourself. 

This chapter covers the following topics: 

- ServiceContext
- Security and Permissions
- Asset Framework
- Recycle Bin
- Message Bus
- Device Detection

<!-- - Social Activities (Add this above Device Detection) -->

<!-- - Other frameworks -->

Let's get cookin' with Liferay's `ServiceContext` class next.

## ServiceContext [](id=servicecontext-liferay-portal-6-2-dev-guide-06-en)

The `ServiceContext` class is a parameter class used for passing contextual
information for a service. Using a parameter class lets you consolidate many
different methods with different sets of optional parameters into a single,
easier-to-use method. The class also aggregates information necessary for
features used throughout Liferay's core portlets, such as permissioning,
tagging, categorization, and more. 

In this section we'll look at the Service Context fields, learn how to create
and populate a Service Context, and learn to access Service Context data.

First we'll look at the fields of the `ServiceContext` class. 

### Service Context Fields [](id=service-context-fields-liferay-portal-6-2-dev-guide-06-en)

The `ServiceContext` class has many fields. The best field descriptions are
found in the Javadoc: 

[http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/ServiceContext.html](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/ServiceContext.html).

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

### Creating and Populating a Service Context [](id=creating-and-populating-a-service-context-liferay-portal-6-2-dev-guide-en)

Although all the `ServiceContext` class fields are optional, services that
store any type of content need the scope group ID specified, at least. Here's a
simple example of creating a `ServiceContext` instance and passing it as a
parameter to a service API using Java: 

    ServiceContext serviceContext = new ServiceContext();
    serviceContext.setScopeGroupId(myGroupId);
    ...
    BlogsEntryServiceUtil.addEntry(...., serviceContext);

If you invoke the service from a servlet, a Struts action or any other front-end
end class which has access to the `PortletRequest`, use one of the
`ServiceContextFactory.getInstance(...)` methods. These methods create the
`ServiceContext` object and automatically fill it with all necessary values. The
above example looks different if you invoke the service from a servlet: 

    ServiceContext serviceContext =
        ServiceContextFactory.getInstance(BlogsEntry.class.getName(), portletRequest);
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

Next, let's see an example of accessing information from a `ServiceContext`. 

### Accessing Service Context Data [](id=accessing-service-context-data-liferay-portal-6-2-dev-guide-06-en)

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
Can you use `ServiceContext` to set the time the blog entry was added? You sure
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
blogs entry? Must you ask? 

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

Let's look at Liferay's permissions system next. 

## Security and Permissions [](id=security-and-permissions-liferay-portal-6-2-dev-guide-06-en)

The Java portlet standard defines a simple security scheme using portlet roles
and their mapping to portal roles. On top of this, Liferay provides a
fine-grained permissions system that you can use to implement access security in
your custom portlets. Here, we'll give an overview of the standard Java security
system, Liferay's permission system, and how to use them in your own portlets. 

### JSR Portlet Security [](id=jsr-portlet-security-liferay-portal-6-2-dev-guide-06-en)

The JSR specification defines a means to specify roles used by portlets in their
`portlet.xml` definitions. The role names themselves, however, are not
standardized, so when these portlets run in Liferay, you'll recognize familiar
role names. For example, the Liferay Blogs portlet definition references the
*guest*, *power-user*, and *user* roles: 

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
        <supported-public-render-parameter>
        categoryId
        </supported-public-render-parameter>
        <supported-public-render-parameter>
        tag
        </supported-public-render-parameter>
    </portlet>

Your `portlet.xml` roles need to be mapped to specific roles in the portal.
That way the portal can resolve conflicts between roles with the same name that
are from different portlets (e.g. portlets from different developers). 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** Each role named in a
 portlet's `<security-role-ref>` element is given permission to add the portlet
 to a page. 

---

To map the roles to the portal, you'll have to use a Liferay-specific
configuration file called `liferay-portlet.xml`. For an example, see the mapping
defined inside `liferay-portlet.xml` found in `portal-web/docroot/WEB-INF`: 

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

<!-- Better to use an example from plugins here, rather than from an internal
portlet. -Rich -->

If a portlet definition references the role `power-user`, that portlet is
mapped to the Liferay role called *Power User* that's already in its database. 

Once roles are mapped to the portal, you can use methods as defined in portlet
specification: 

- `getRemoteUser()`
- `isUserInRole()`
- `getUserPrincipal()`

For example, you can use the following code to check if the current user has
the `power-user` role:

    if (renderRequest.isUserInRole("power-user")) {
        // ...
    }

By default, Liferay doesn't use the `isUserInRole()` method in any built-in
portlets. Liferay uses its own permission system directly to achieve more
fine-grained security. Next we'll describe Liferay's permission system and
how to use it in your portlets. We recommend using Liferay's permission system,
because it offers a much more robust way of tailoring your application's
permissions. 

### Liferay's Permission System [](id=liferays-permission-system-liferay-portal-6-2-dev-guide-06-en)

You can add permissions to your custom portlets using four easy steps (also
known as DRAC): 

1. **D**efine all resources and their permissions. 

2. **R**egister all defined resources in the permissions system. This is also
   known as *adding resources*. 

3. **A**ssociate the necessary permissions with resources. 

4. **C**heck permission before returning resources. 

Before you start adding permissions to a portlet, make sure you understand
these two critical terms used throughout this section: 

- *Resource*: A generic term for any object represented in the portal.
  Examples of resources include portlets (e.g. Message Boards, Calendar, etc.),
Java classes (e.g. Message Board Topics, Calendar Events, etc.), and files
(e.g.  documents, images, etc.). 

- *Permission*: An action on a resource. For example, the *view* action with
respect to *viewing the calendar portlet* is defined as a permission in Liferay. 

It's important to know that permissions for *portlet* resources are implemented
a little differently than for other resources like Java classes and files.
Below, we'll describe permission implementation for the *portlet* resource
first, followed by the model resource. 

<!-- We definitely should not be using the Blogs portlet to illustrate this, as
the patterns are different from what plugin developers would do. For example, in
a plugin, the convention is to create default.xml in the resource-actions folder
of a plugin, and that's what developers reading this will do. We should find
another portlet to serve as our example portlet. -Rich -->

The first step is to define your resources and the actions that can be defined
on them. Let's use the Blogs portlet to demonstrate. Open the `blogs.xml` file in
`portal-impl/src/resource-actions` and you'll see the following mapping of
resources to actions: 

    <?xml version="1.0"?>
    <!DOCTYPE resource-action-mapping PUBLIC
    "-//Liferay//DTD Resource Action Mapping 6.2.0//EN"
    "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_2_0.dtd">

    <resource-action-mapping>
            <portlet-resource>
                    <portlet-name>33</portlet-name>
                    <permissions>
                            <supports>
                                    <action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
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
                                    <action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
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
                            <portlet-name>161</portlet-name>
                    </portlet-ref>
                    <weight>1</weight>
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
                            <portlet-name>161</portlet-name>
                    </portlet-ref>
                    <weight>2</weight>
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
            </model-resource>
    </resource-action-mapping>

Permissions in the blogs portlet are defined at two different levels: the
portlet level and the model level. The portlet level defines permissions on the
portlet as a whole. The model level defines permissions on the model layer of
the application, as defined by the entities in the application. Each level
coincides with a section of the `resource-actions` XML file (in this case,
`blogs.xml`). 

At the `<portlet-resource>` level, actions and default permissions
are defined on the portlet itself. Changes to portlet level permissions are
performed on a per-site basis, and define whether users can add the portlet to a
page, edit the portlet's configuration, or view the portlet.  All these actions
are defined in the `<supports>` tag for the portlet resource's permissions. The
default portlet-level permissions for members of the site are defined in the
`<site-member-defaults>` tag. In the case of the Blogs portlet, site members can
view any blog in the site. Similarly, default guest permissions are defined in
the `<guest-defaults>` tag. The `<guest-unsupported>` tag contains permissions
forbidden to guests. Here, guests can't be given permission to configure the
portlet. 

The `<model-resource>` section contains the next level of permissions, based on
the *scope* of an individual instance of the portlet. A scope in Liferay
refers to how widely the data from an instance of a portlet is shared. For
example, if you place a Blogs portlet on a page in the guest site and place
another Blogs portlet on another page in the same site, the two blogs share the
same set of posts. That happens because portlets are given a site level scope by
default. If you reconfigure one of the two Blogs and change its scope to be the
current page, that Blogs portlet instance no longer shares content with the
other instance (or any other Blogs instance in that site). A portlet instance's
scope-based permissions can thus span an entire site or be restricted to a
single page. If you set the scope to the page, it's possible to have multiple
distinct Blog instances within a site, each with different permissions for site
users. For example, a food site could have one blog open to posts from any site
member, but also have a separate informational blog about the site itself
restricted to posts from administrators. 

After defining the portlet and portlet instance as resources, we need to define
permissions on the models in the portlet. The model resource is surrounded by
the `<model-resource>` tag. Inside the tag, we first define the model name; the
`<model-name>` isn't the name of a Java class, but the fully qualified name of
the portlet's package (e.g. the blog portlet's package
`com.liferay.portlet.blogs`). This is the recommended convention for permissions
that refer to an instance of the portlet as a whole. Permissions like the
ability to *add* or *subscribe to* a blog entry are defined here, and affect the
portlet at the instance level. The `<portlet-ref>` element comes next and
contains a `<portlet-name>`. The value of `<portlet-name>` references the name
of the portlet to which the model resource belongs. Theoretically, a model
resource can belong to multiple portlets referenced with multiple
`<portlet-name>` elements, but this is uncommon. Like the portlet resource, the
model resource lets you define a list of supported actions that require
permission to perform. You must list all the performable actions that require a
permission check. For a blog entry, users must belong to appropriate roles for
permission to do the following: 

- *Add comments* to an entry 
- *Delete* an entry 
- *Change the permission* setting of an entry 
- *Update* an entry 
- *View* an entry 

As with a portlet resource, the `<site-member-defaults>` tag, `<guest-defaults>`
tag, and `<guest-unsupported>` tag define default permissions for site members
and guests, respectively, for *model resources*. 

After defining resource permissions for your custom portlet, you need to refer
Liferay to the `resource-actions` XML file that contains your definitions (e.g.
`blogs.xml` for the Blogs portlet). For Liferay core, the `resource-actions`
XML files are in the `portal/portal-impl/src/resource-actions` directory
and the file named `default.xml` file refers to each of these files. This
excerpt from `default.xml` references the resource permission definition files
for all built-in Liferay portlets (including the blogs portlet): 

    <?xml version="1.0"?>
    <!DOCTYPE resource-action-mapping PUBLIC
     "-//Liferay//DTD Resource Action Mapping 6.2.0//EN"
     "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_2_0.dtd">
    
    <resource-action-mapping>
        <resource file="resource-actions/portal.xml" />
        <resource file="resource-actions/announcements.xml" />
        <resource file="resource-actions/asset.xml" />
        <resource file="resource-actions/blogs.xml" />
        ...
    </resource-action-mapping>

You should put your plugin's `resource-actions` XML files (e.g.  `default.xml`
and `blogs.xml`) in a directory in your project's classpath. Then create a
properties file (typically named `portlet.properties`) for your portlet that
references the file that specifies your `<resource-action-mapping>` element
(e.g. `default.xml`). In this portlet properties file, create a property named
`resource.actions.configs` with the relative path to your portlet's
resource-action mapping file (e.g.  `default.xml`) as its value. Here's what
this property specification might look like: 

    resource.actions.configs=resource-actions/default.xml

Check out a copy of the Liferay source code from the Liferay Github
[repository](https://github.com/liferay/liferay-portal) to see an example of a
portlet that defines its resources and permissions as we just described; start
by looking at the definition files found in the
`portal-impl/src/resource-actions` directory. For an example of defining
permissions in the context of a portlet plugin, check out `plugins/trunk` and
look at the portlet `sample-permissions-portlet`. 

Next, we'll show you how to add resources. 

<!-- I didn't see any point to the permission algorithms section. It didn't seem
to lead anywhere, and developers who have been approaching Liferay have been met
with a default Algorithm 6 for two releases now. Algorithm 6 is now required for
the proper operation of Liferay, so the only people who need to worry about the
older algorithms are those who are upgrading, and we cover that in the upgrading
section of the User Guide. The API described below works the same regardless of
which permissions algorithm is used. For these reasons, I removed the whole
section. It seems to improve the flow: we just left off talking about portal
resources in the XML file, and now it goes right into the code. -Rich -->

### Adding a Resource [](id=adding-a-resource-liferay-portal-6-2-dev-guide-06-en)

After defining resources and actions, it's time to add resources into the
permissions system. Resources are added at the same time entities are added to
the database. Managing resources follows the same pattern you've seen throughout
Liferay: there's a service to use. Adding resources is as easy as calling the
`addResources(...)` method of the `ResourceLocalServiceUtil` class. Here's the
signature of that method: 

    public void addResources(
        long companyId, long groupId, long userId, String name,
        String primKey, boolean portletActions,
        boolean addGroupPermissions, boolean addGuestPermissions)

Each entity that requires access permission must be added as a resource
every time it is stored. For example, every time a user adds a new
entry to her blog, the `addResources(...)` method must be called to add the
new entry object to the resource system. Here's an example of the call from the
`BlogsEntryLocalServiceImpl` class: 

    resourceLocalService.addResources(
        entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
        BlogsEntry.class.getName(), entry.getEntryId(), false,
        addGroupPermissions, addGuestPermissions);

In the `addResources(...)` method, the parameters `companyId`, `groupId`, and
`userId` correspond to the portal instance, the site in which the entity is
being saved, and the user who is saving it. Let's look more closely at the
remaining parameters: 

- The `name` parameter is the fully qualified Java class name for the 
  entity being added. 
- The `primKey` parameter is the primary key of the entity. 
- The `portletActions` parameter should be set to `true` if you're adding
  portlet action permissions. In our example, it's `false` because we're adding
  a model resource, which should be associated with permissions related to the
  model action defined in `blogs.xml`. 
- The `addGroupPermissions` and the `addGuestPermissions` parameters are inputs
  from the user. If set to `true`, `ResourceLocalService` adds the default
  permissions to the current group and the guest group for this resource,
  respectively. 

You can let your users choose whether to add the default site permission and/or
the guest permission for your custom portlet resources: Liferay has a custom
JSP tag `<liferay-ui:input-permissions />` that you can use to quickly add that
functionality. You just insert the tag into the appropriate JSP and the
checkboxes appear on that page. Make sure that the tag is inside the
appropriate `<form>` tags, and that's all there is to it. 

When you remove entities from the database, it's good practice to remove
permissions mapped directly to the entity. To prevent dead resources from
taking up space in the `Resource_` database table, remember to remove them from
the `Resource_` table when the resource no longer applies. Do this by calling
the `deleteResource(...)` method of `ResourceLocalServiceService`. Here's an
example of a blogs entry being removed: 

    resourceLocalService.deleteResource(
        entry.getCompanyId(), BlogsEntry.class.getName(),
        ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

Great! Now that you know how to work with resource permissions, we'll show you
how to provide a user interface for managing resource permissions. 

### Adding Permissions [](id=adding-permissions-liferay-portal-6-2-dev-guide-06-en)

On the portlet level, no code needs to be written in order to have the
permission system work for your custom portlet. If you've defined any custom
permissions (supported actions) in your configuration file's `portlet-resource`
tag, they're automatically added to a list of permissions in Liferay's
permissions UI. What good, however, are permissions that are available but can't
be set by users? 

To let a user set permissions on model resources, you must expose the permission
interface to the user. Just add these two Liferay UI tags to your JSP:

1.  `<liferay-security:permissionsURL>`: Returns a URL to the permission
    settings configuration page. 

2. `<liferay-ui:icon>`: Shows an icon to the user. These are defined in the
    theme, and one of them (see below) is used for permissions. 

This example demonstrates the use of both tags; it comes from the
`view_entry_content.jspf` file. 

    <liferay-security:permissionsURL
        modelResource="<%= BlogsEntry.class.getName() %>"
        modelResourceDescription="<%= entry.getTitle() %>"
        resourcePrimKey="<%= entry.getPrimaryKey().toString() %>"
        var="entryURL"
    />

    <liferay-ui:icon image="permissions" url="<%= entryURL %>" />

For the first tag, specify the following attributes: 

-   `modelResource`: The fully qualified Java object class name. This class name
    gets translated into its more readable name as specified in
    `Language.properties`. 
-   `modelResourceDescription`: You can pass in anything that best describes
    this model instance. In this example, the blogs title was passed in. 
-   `resourcePrimKey`: The primary key of your model instance. 
-   `var`: Specifies the name of the variable to which the resulting URL string
    will be assigned. The variable is then passed to the `<liferay-ui:icon>` tag
    so the permission icon has the proper URL link.

There's an optional attribute called `redirect` that's available if you want to
override the default behavior of the upper right arrow link. That's it; now your
users can configure the permission settings for model resources. 

Next, we'll show you how to implement permissions checking. 

### Checking Permissions [](id=checking-permissions-liferay-portal-6-2-dev-guide-06-en)

The last major step toward implementing permissions for your custom portlet is
to ensure the configured permissions are enforced. You'll do this by adding
permission checks to your application. For example, your business layer can
check for permission before deleting a resource, or your user interface can hide
a button that adds an entity (e.g. a calendar event) if the user doesn't have
permission.

You need to implement checking of any custom permissions you defined in your
`resource-actions` XML file. In the Blogs portlet, one supported custom action
is `ADD_ENTRY`. There are two places in the source code to check for this
permission: in your JSP files and in the business logic. For the JSP files, you
want to wrap certain elements in permission checks, so they only appear for
users with the permission to perform those functions. For example, the presence
of the Add Entry button is contingent on whether the user has permission to add
a blog entry.  Here's the `ADD_ENTRY` action in a JSP file: 

    <%
    if (permissionChecker.hasPermission(
        scopeGroupId, "com.liferay.portlet.blogs.model",
        scopeGroupId, "ADD_ENTRY") {
        // Show add entry button
    }
    %>

The second place to check for the add entry permission is in the business logic.
If the check fails, a `PrincipalException` is thrown and the add entry request
is aborted:

    if (!permissionChecker.hasPermission(
        scopeGroupId, "com.liferay.portlet.blogs.model",
        scopeGroupId, "ADD_ENTRY")) {
            throw new PrincipalException();
    }

    blogsEntryLocalService.addEntry(...);

The `PermissionChecker` class has a method `hasPermission(...)` that checks
whether a user making a resource request has the necessary access permission.
If the user isn't signed in (guest user), it checks for guest permissions.
Otherwise, it checks for user permissions. Let's quickly review the parameters
of this method: 

-   `groupId`: Represents the scope where the permission check is performed. In
    Liferay, many scopes are available, including a specific site, organization,
    personal site of a user, or a page in a site. This is important because a
    user may be allowed to add blog entries in one site, but not in another. For
    resources that don't belong to a scope (extremely rare and unlikely), set
    the value of this parameter to `0`. There are several ways you can obtain
    the `groupId` of the current scope: 
    -   JSP that uses the `<theme:defineObjects/>` tag: there's an implicit
        variable called `scopeGroupId`. 
    -   Business logic class: If you're using the ServiceContext pattern, you
        can obtain the `groupId` by using `serviceContext.getScopeGroupId()`. If
        you're not using the `ServiceContext` pattern, your can obtain `groupId`
        from the theme display request object: 

            ThemeDisplay themeDisplay = (ThemeDisplay)
                request.getAttribute(WebKeys.THEME_DISPLAY);
            long scopeGroupId = themeDisplay.getScopeGroupId();

-   `name`: The name of the resource as specified in the XML file of the
    previous sections. 
-   `primKey`: The primary key of the resource. In this example the resource
    doesn't exist as an entry in the database, so we use the `groupId` again. If
    we were checking for a permission on a given blog entry, we'd use the
    primary key of that blog entry instead. 
-   `actionId`: The name of the action as it appears in the XML file. To
    simplify searching for usages, consider creating a helper class that has
    constants for all the actions defined. 

In the examples above, we're assuming there's a variable called
`permissionChecker` already available. Liferay automatically creates a
`PermissionChecker` instance that has the necessary information from the user
for every request. Liferay also caches the security checks to ensure good
performance. There are several ways to obtain a permission checker: 

-   In a JSP that uses the `<theme:defineObjects/>` tag, there's an implicit
    variable called `permissionChecker`. 
-   With Service Builder, every service implementation class can access the
    `PermissionChecker` instance by using the method `getPermissionChecker()`. 
-   If you're not using Service Builder, `PermissionChecker` can be obtained
    from the theme display request object: 

        ThemeDisplay themeDisplay = (ThemeDisplay)
            request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker permissionChecker =
            themeDisplay.getPermissionChecker();

Next, you'll optimize permission checking by creating helper classes to do most
of the heavy lifting. 

### Creating Helper Classes for Permission Checking [](id=creating-permission-helper-classes-liferay-portal-6-2-dev-guide-06-en)

Helper classes streamline your code. They encapsulate the use of
`permissionChecker` and the names of the resources for a specific portlet. This
is especially useful when there are complex parent-child relationships, or if
your permission logic calls for checking multiple action types.
`BlogsPermission` is an example of a permission helper class. Here's how
`BlogsPermission` is used in a JSP: 

    <%
    if (BlogsPermission.contains(permissionChecker, scopeGroupId,
        ActionKeys.ADD_ENTRY)) {
        // show add entry button
        }
    %>

Now let's see how a `ServiceImpl` class, `BlogsEntryServiceImpl`, uses the
`BlogsPermission` helper class. In the method
`BlogsEntryServiceImpl.addEntry(...)`, a call is made to check whether the
incoming request has permission to add an entry. The check is done using the
helper class `BlogsPermission`. If the check fails, it throws a
`PrincipalException` and the add entry request aborts. 

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

Check out the parameters passed into the `check(...)` method. Again, the
`getPermissionChecker()` method is readily available in all `ServiceImpl`
classes. The blogs entry ID is available in the `serviceContext`, indicating
that the permission check is against the Blogs portlet. `ActionKeys.ADD_ENTRY`
is a static string used to indicate the action requiring the permission check.
Likewise, you're encouraged to use custom portlet action keys. 

<!-- We're not ready for review yet. We haven't covered how to create the helper
class, which is what the reader is expecting out of this section. Certainly I
was. We've only seen how to use a helper class that already exists. -Rich --> 

Let's review what we've just covered. Implementing permissions into your custom
portlet consists of four main steps:
 
1.  Define any custom resources and actions.

2.  Implement code to register (i.e., add) any newly created resources, such as
    a `BlogsEntry` object.

3.  Provide an interface for the user to configure permissions.

4.  Implement code to check permissions before returning resources or showing
    custom features.

The two major resources are portlets and entities. Liferay does most of the work
for you by providing a configuration file and a system of resources that let you
check permissions wherever you need to in your application. 

You're now equipped to implement security in your custom Liferay portlets! 

Next, let's learn how to use Liferay's asset framework. 

## Asset Framework [](id=asset-framework-liferay-portal-6-2-dev-guide-06-en)

Liferay's asset framework is a system that allow you to add common functionality
to your application. For example, you might build an event management
application that shows a list of upcoming events. It might be nice to be able to
tag or categorize those events to provide users with metadata describing more
about them. Or you might want to let users comment on events. 

This common functionality is what Liferay's asset framework gives you. Using the
power of Liferay's built-in message boards, tags, and categories, Liferay lets
you infuse your application with these features in no time. 
 
The term *asset* is a generic term that refers to any type of content, including
text, an external file, a URL, an image, or a record in an online book library.
Consequently, when we use the term *asset* here, we're referring to some type of
Liferay content, like documents, blog entries, bookmarks, wiki pages, or
anything you create in your applications. 

Here are the features you can reuse thanks to the asset framework: 

-   Associate tags to custom content types. New tags are created automatically
    when the author assigns them to the content. 
-   Associate categories to custom content types. Authors are only allowed to
    select from predefined categories within several predefined vocabularies. 
-   Manage tags from the Control Panel, including merging tags. 
-   Manage categories from the Control Panel, including creating complex
    hierarchies. 
-   Associate comments with assets. 
-   Rate assets using a five star rating system. 
-   Assign social bookmarks to assets, including via tweet, Facebook like, or +1
    (Google Plus). 
-   Add custom fields to assets. 
-   Relate assets to one another. 
-   Flag asset content as inappropriate. 
-   Keep track of the number of visualizations of an asset. 
-   Integrate workflow with assets. 
-   Publish your content using the Asset Publisher portlet. Asset Publisher can
    publish dynamic asset lists or manually selected asset lists. It can also
    show an asset summary view with a link to the full view. This saves you
    time, since it likely won't be necessary to develop custom portlets for your
    custom content types. 

At this point you might be saying, "Liferay's asset framework sounds great; but
how do I leverage all these awesome functions?" Excellent question, young
padawan, and perfect timing; we couldn't have said it better ourselves. 

We'll briefly describe the first two steps here before we dive in head first: 

-   The first step is mandatory. You must let the framework know whenever one of
    your custom content entries is added, updated, or deleted. 
-   The second step enables the asset framework in the UI. You can use a set of
    taglibs to provide widgets that allow authors to enter comments, tags and
    categories and that show the entered tags and categories along with the
    content. 

Let's dive head first into the first step: we need to inform the asset framework
when we're adding, updating, or deleting assets.

### Adding, Updating, and Deleting Assets [](id=adding-updating-and-deleting-assets-liferay-portal-6-2-dev-guide-06-en)

Whenever you create a new entity, you need to let the asset framework know. In
this sense, it's similar to permission resources. It's a simple procedure: you
invoke a method of the asset framework that adds an `AssetEntry` so that Liferay
can keep track of the asset. 

Specifically, you should access these methods using either the static methods
of `AssetLocalServiceUtil` or an instance of the `AssetEntryLocalService`
injected by Spring. To simplify this section, we'll be using the static methods
of `AssetLocalServiceUtil`, since it doesn't require any special setup in your
application. 

<!-- This is wrong. We should show them the right way to do it, rather than the
way that's easier to document. Remember that developers will be taking direction
from our text and writing actual code based on it. The right way is to add a
<reference> tag to the service.xml and inject the service with Spring. In fact,
the example below does just that (since it's Liferay's best practice), so the
example code here doesn't even match what we just stated. We need to fix this.
-Rich--> 

The method to invoke when one of your custom content entries is added or
updated is the same, and is called `updateEntry`. Here's the full signature: 

    AssetEntry updateEntry(
            long userId, long groupId, String className, long classPK,
            String classUuid, long classTypeId, long[] categoryIds,
            String[] tagNames, boolean visible, Date startDate, Date endDate,
            Date publishDate, Date expirationDate, String mimeType,
            String title, String description, String summary, String url,
            String layoutUuid, int height, int width, Integer priority,
            boolean sync)
        throws PortalException, SystemException

Here's an example of this method's invocation extracted from the built in
blogs portlet: 

    assetEntryLocalService.updateEntry(
            userId, entry.getGroupId(), BlogsEntry.class.getName(),
            entry.getEntryId(), entry.getUuid(), 0, assetCategoryIds,
            assetTagNames, visible, null, null, entry.getDisplayDate(), null,
            ContentTypes.TEXT_HTML, entry.getTitle(), null, summary, null, null,
            0, 0, null, false);

Here's a quick summary of the most important parameters of this method: 

-   `userId` is the identifier of the user who created the content. 
-   `groupId` identifies the scope of the created content. If your content
    doesn't support scopes (extremely rare), just pass `0` as the value. 
-   `className` identifies the type of asset. The recommended convention is to
    use the name of the Java class that represents your content type, but you
    can actually use any String you want as long as you are sure that it is
    unique.  
-   `classPK` identifies the specific content being created among others of the
    same type. It's usually the primary key of the table where the custom
    content is stored. If you want, you can use the *classUuid* parameter to
    specify a secondary identifier; it's guaranteed to be universally unique.
    It's especially useful if your content will be exported and imported across
    separate portals.  
-   `assetCategoryIds` and `assetTagNames` represent the categories and tags
    selected by the author of the content. The asset framework stores them for
    you.
-   `visible` specifies whether the content should be shown at all by Asset
    Publisher. 
-   `title,` `description` and `summary` are descriptive fields used by the
    Asset Publisher when displaying entries of your content type. 
-   `publishDate` and `expirationDate`, when specified, tell Asset Publisher it
    shouldn't show the content before a given publication date or after a given
    expiration date, respectively. 
-   All other fields are optional; it won't always make sense to include them.
    The `sync` parameter should always be *false* unless you're doing something
    very advanced (feel free to look at the code if you're really curious). 

<!-- We should fully describe the sync parameter, as well as any others.
Otherwise, it looks like we're holding back information for no apparent reason.
-Rich -->

When one of your custom content entries is deleted, you should once again let
the asset framework know. This way, it can clean up stored information and make
sure that the Asset Publisher doesn't show any information for the content that
has been deleted. The signature of method to delete an asset entry is: 

    void deleteEntry(String className, long classPK)

Here's an example invocation extracted again from the blogs portlet: 

    assetEntryLocalService.deleteEntry(
        BlogsEntry.class.getName(), entry.getEntryId());

Now that you can create, modify, and delete assets, let's learn how to
categorize them. 

### Entering and Displaying Tags and Categories [](id=entering-and-displaying-tags-and-categories-liferay-portal-6-2-dev-guide-en)

In the last section, we let the asset framework know about the tags and
categories that we associated with a given asset; but how does a content author
specify the tags and categories? 

Liferay provides a set of JSP tags you can use to make this task very easy. You
can put the following Liferay UI tags in your forms to create content that can
be associated with new or existing tags or predefined categories:

    <label>Tags</label>
    <liferay-ui:asset-tags-selector
        className="<%= entry.getClass().getName() %>"
        classPK="<%= entry.getPrimaryKey() %>"
    />

    <label>Categories</label>
        <liferay-ui:asset-categories-selector
            className="<%= entry.getClass().getName() %>"
            classPK="<%= entry.getPrimaryKey() %>"
    />

These two taglibs create appropriate form controls that allow the user to
search for a tag or create a new one or select an existing category. 

---

 ![Tip](../../images/tip-pen-paper.png) **Tip:** If you're using Liferay's
 AlloyUI Form taglibs, creating fields to enter tags and categories is even
 simpler. You just use `<aui:input name="tags" type="assetTags" />` and
 `<aui:input name="categories" type="assetCategories" />`, respectively. 

---

Once the tags and categories have been entered, you'll want to show them along
with the content of the asset. Here's how to display the tags and categories: 

    <label>Tags</label>
    <liferay-ui:asset-tags-summary
        className="<%= entry.getClass().getName() %>"
        classPK="<%= entry.getPrimaryKey() %>"
    />

    <label>Categories</label>
    <liferay-ui:asset-categories-summary
        className="<%= entry.getClass().getName() %>"
        classPK="<%= entry.getPrimaryKey() %>"
    />

In both JSP tags, you can also specify a `portletURL` parameter; each tag that
uses it will be a link containing the `portletURL` *and* `tag` or `categoryId`
parameter value, respectively. This supports tags navigation and categories
navigation within your portlet. You'll need to implement the look-up
functionality in your portlet code; do this by reading the values of those two
parameters and using the `AssetEntryService` to query the database for entries
based on the specified tag or category. 

<!-- An example of this would be really nice. -Rich -->

Great job! You'll have no problem associating tags and categories with your
assets. Before we go further with our example, let's take a look at more JSP
tags you can use to leverage the asset framework's features. 

### More JSP Tags for Assets [](id=more-jsp-tags-for-assets-liferay-portal-6-2-dev-guide-06-en)

In addition to tags and categories, there are more features that the asset
framework provides. These features allow users to do the following with your
assets: 

-   Add comments
-   Rate comments of other users
-   Rate assets
-   Apply social bookmarks (e.g. via tweet, Facebook like, or +1 (Google Plus))
-   Relate assets to one another
-   Flag content as inappropriate and notify the portal administrator

There are JSP tags, called *Liferay UI* tags, associated with each feature. You
can find these tags used in the JSPs for Liferay's built-in portlets (e.g. the
`edit_entry.jsp` of the Blogs portlet).  Here are some examples of the JSP tags
from the Blogs portlet: 

-   *Comments and comment ratings:* 

        <portlet:actionURL var="discussionURL">
            <portlet:param
               name="struts_action"
               value="/blogs/edit_entry_discussion"
            />
        </portlet:actionURL>

        <liferay-ui:discussion
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            formAction="<%= discussionURL %>"
            formName="fm2"
            ratingsEnabled="<%= enableCommentRatings %>"
            redirect="<%= currentURL %>"
            subject="<%= entry.getTitle() %>"
            userId="<%= entry.getUserId() %>"
        />

-   *Rate assets:* 

        <liferay-ui:ratings
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
        />

-   *Social Bookmarks:* 

        <liferay-ui:social-bookmarks
            displayStyle="<%= socialBookmarksDisplayStyle %>"
            target="_blank"
            title="<%= entry.getTitle() %>"
            url="<%= PortalUtil.getCanonicalURL(bookmarkURL.toString(),
                themeDisplay) %>"
        />

-   *Related assets:* 

        <liferay-ui:input-asset-links
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entryId %>"
        />

-   *Flag as inappropriate:* 

        <liferay-ui:flags
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            contentTitle="<%= entry.getTitle() %>"
            reportedUserId="<%= entry.getUserId() %>"
        />

<!-- The above features need more explanation and sections of their own. We
don't need to do this now, but we should do it later, for the 6.2 release. -Rich
-->

With Liferay's taglib tags, you can easily apply these features to your assets.
No problemo, right? So let's get the assets published in your portal. 

### Publishing Assets with Asset Publisher [](id=publishing-assets-with-asset-publisher-liferay-portal-6-2-dev-guide-06-en)

A huge benefit of using the asset framework is that you can leverage the Asset
Publisher portlet to publish lists of your custom asset types. You can choose
to have users specify lists dynamically (e.g., based on the asset tags or
categories) or have administrators do it statically. 

To display your assets, the Asset Publisher needs to know how to access their
metadata. You also need to provide the Asset Publisher templates for the types
of views (e.g. *full* view and *abstract* view) available to display your
assets. You can provide all this to the Asset Publisher by implementing these
two interfaces: 

-   `AssetRendererFactory`: A class that knows how to retrieve specific assets
    from persistent storage using the `classPK`. The `classPK` is typically the
    asset's primary key, but can be anything you specified to the `updateAsset`
    method, which you use to add or update the asset. Your factory
    implementation can grab the asset from a `groupId` (identifies a scope of
    data) and a `urlTitle` (a title that can be used in friendly URLs to refer
    uniquely to the asset within a given scope). Finally, the asset renderer
    factory can provide a URL for the Asset Publisher to use when a user wants
    to add a new asset of your custom type. This URL should point to your own
    portlet.  There are other less important methods of the interface, but you
    can avoid implementing them by extending `BaseAssetRendererFactory`. By
    extending this base class instead of implementing the interface directly,
    your code will be more robust to possible interface changes in future
    versions of Liferay, since the base implementation will be updated to
    accommodate the interface changes.  
-   `AssetRenderer`: This is an interface that provides metadata information
    about one specific asset. It checks whether the current user has permission
    to edit or view the asset and renders the asset for the different templates
    (e.g. abstract and full content view) by forwarding to a specific JSP. We
    recommend that you extend the `BaseAssetRenderer` class rather than directly
    implementing the interface. The base class provides helpful defaults and 
    contains methods that get added to the interface in the future. 

Let's look at an example of these two classes. We'll use Liferay's Blogs
portlet again, and we'll start by implementing `AssetRendererFactory`: 

    public class BlogsEntryAssetRendererFactory extends BaseAssetRendererFactory {

        public static final String CLASS_NAME = BlogsEntry.class.getName();

        public static final String TYPE = "blog";

        public AssetRenderer getAssetRenderer(long classPK, int type)
            throws PortalException, SystemException {

            BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(classPK);

            return new BlogsEntryAssetRenderer(entry);
        }

        @Override
        public AssetRenderer getAssetRenderer(long groupId, String urlTitle)
            throws PortalException, SystemException {

            BlogsEntry entry = BlogsEntryServiceUtil.getEntry(groupId, urlTitle);

            return new BlogsEntryAssetRenderer(entry);
        }

        public String getClassName() {
            return CLASS_NAME;
        }

        public String getType() {
            return TYPE;
        }

        @Override
        public PortletURL getURLAdd(
                LiferayPortletRequest liferayPortletRequest,
                LiferayPortletResponse liferayPortletResponse)
            throws PortalException, SystemException {

            HttpServletRequest request =
                liferayPortletRequest.getHttpServletRequest();

            ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                WebKeys.THEME_DISPLAY);

            if (!BlogsPermission.contains(
                themeDisplay.getPermissionChecker(),
                themeDisplay.getScopeGroupId(), ActionKeys.ADD_ENTRY)) {

                return null;
            }

        PortletURL portletURL = PortletURLFactoryUtil.create(
            request, PortletKeys.BLOGS, getControlPanelPlid(themeDisplay),
            PortletRequest.RENDER_PHASE);

            portletURL.setParameter("struts_action", "/blogs/edit_entry");

            return portletURL;
        }

        @Override
        public boolean hasPermission(
                PermissionChecker permissionChecker, long classPK, String actionId)
            throws Exception {

            return BlogsEntryPermission.contains(
                permissionChecker, classPK, actionId);
        }

        @Override
        public boolean isLinkable() {
            return _LINKABLE;
        }

        @Override
        protected String getIconPath(ThemeDisplay themeDisplay) {
            return themeDisplay.getPathThemeImages() + "/blogs/blogs.png";
        }

        private static final boolean _LINKABLE = true;
    }

Here's the `AssetRenderer` implementation: 

    public class BlogsEntryAssetRenderer extends BaseAssetRenderer {

        public BlogsEntryAssetRenderer(BlogsEntry entry) {
            _entry = entry;
        }

        public long getClassPK() {
            return _entry.getEntryId();
        }

        @Override
        public String getDiscussionPath() {
            if (PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED) {
                return "edit_entry_discussion";
            }
            else {
                return null;
            }
        }

        public long getGroupId() {
            return _entry.getGroupId();
        }

        public String getSummary(Locale locale) {
            return HtmlUtil.stripHtml(_entry.getDescription());
        }

        public String getTitle(Locale locale) {
            return _entry.getTitle();
        }

        @Override
        public PortletURL getURLEdit(
                LiferayPortletRequest liferayPortletRequest,
                LiferayPortletResponse liferayPortletResponse)
            throws Exception {

            PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
                getControlPanelPlid(liferayPortletRequest), PortletKeys.BLOGS,
                PortletRequest.RENDER_PHASE);

            portletURL.setParameter("struts_action", "/blogs/edit_entry");
            portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));

            return portletURL;
        }

        @Override
        public String getUrlTitle() {
            return _entry.getUrlTitle();
        }

        @Override
        public PortletURL getURLView(
                LiferayPortletResponse liferayPortletResponse,
                WindowState windowState)
            throws Exception {

            PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
                PortletKeys.BLOGS, PortletRequest.RENDER_PHASE);

            portletURL.setWindowState(windowState);

            portletURL.setParameter("struts_action", "/blogs/view_entry");
            portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));

            return portletURL;
        }

        @Override
        public String getURLViewInContext(
            LiferayPortletRequest liferayPortletRequest,
            LiferayPortletResponse liferayPortletResponse,
            String noSuchEntryRedirect) {

            ThemeDisplay themeDisplay =
                (ThemeDisplay)liferayPortletRequest.getAttribute(
                    WebKeys.THEME_DISPLAY);

            return themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
                "/blogs/find_entry?noSuchEntryRedirect=" +
                    HttpUtil.encodeURL(noSuchEntryRedirect) + "&entryId=" +
                        _entry.getEntryId();
        }

        public long getUserId() {
            return _entry.getUserId();
        }

        public String getUuid() {
            return _entry.getUuid();
        }

        @Override
        public boolean hasEditPermission(PermissionChecker permissionChecker) {
            return BlogsEntryPermission.contains(
                permissionChecker, _entry, ActionKeys.UPDATE);
        }

        @Override
        public boolean hasViewPermission(PermissionChecker permissionChecker) {
            return BlogsEntryPermission.contains(
                permissionChecker, _entry, ActionKeys.VIEW);
        }

        @Override
        public boolean isPrintable() {
            return true;
        }

        public String render(
                RenderRequest renderRequest, RenderResponse renderResponse,
                String template)
            throws Exception {

            if (template.equals(TEMPLATE_ABSTRACT) ||
                template.equals(TEMPLATE_FULL_CONTENT)) {

                renderRequest.setAttribute(WebKeys.BLOGS_ENTRY, _entry);

                return "/html/portlet/blogs/asset/" + template + ".jsp";
            }
            else {
                return null;
            }
        }

        @Override
        protected String getIconPath(ThemeDisplay themeDisplay) {
            return themeDisplay.getPathThemeImages() + "/blogs/blogs.png";
        }

        private BlogsEntry _entry;

    }

<!--Some long code blocks in here. Not sure if they're too long? -->

<!-- These are too long. Can we abstract out just the functionality we need?
-Rich -->

In the render method, there's a forward to a JSP in the case of the abstract
and the full content templates. The abstract isn't mandatory and if it isn't
provided, the Asset Publisher shows the title and the summary from the
appropriate methods of the renderer. The full content template should always be
provided. Here's how it looks for blogs entries: 

    <%@ include file="/html/portlet/blogs/init.jsp" %>

    <%
    BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);
    %>

    <%= entry.getContent() %>

    <liferay-ui:custom-attributes-available
        className="<%= BlogsEntry.class.getName() %>"
    >

        <liferay-ui:custom-attribute-list
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= (entry != null) ? entry.getEntryId() : 0 %>"
            editable="<%= false %>"
            label="<%= true %>"
        />

    </liferay-ui:custom-attributes-available>

That's about it. It wasn't that hard, right? Now it's time to get really fancy;
put on your dancing shoes. If you need to extend the capabilities of the
`AssetRendererFactory` for one of Liferay's core portlets, check out the
article [Extending an
AssetRendererFactory](http://www.liferay.com/web/juan.fernandez/blog/-/blogs/extending-an-assetrendererfactory)
by Juan Fern&agrave;ndez; he talks about doing just that. 

<!-- Let's not do this. Blog entries get out of date quickly. Instead, we should
take his blog entry and massage the text to include it right here in the
documentation. -Rich -->

Now get out there and start enjoying the benefits of the asset framework in
your custom portlets!

Next, we'll explore the Recycle Bin framework and its many benefits for your
portal.

## Implementing the Recycle Bin in Your App [](id=implementing-the-recycle-bin-in-your-app-liferay-portal-6-2-dev-guide-07-en)

How many times have you deleted something, only to realize it was a mistake and
you need to restore it? Does the app you're working on have a delete function?
If so, you can add the ability to move items into a recycle bin instead of
deleting them immediately. Have you been looking for an easy way to implement
this capability in your app? Fortunately, you can, with the help of Liferay's
Recycle Bin.

By leveraging the Recycle Bin in your app, your users can have deleted content
temporarily stored in the Recycle Bin until it has reached a desired expiration
date or until it is explicitly deleted. And while the content is in
the Recycle Bin, it can be safely restored back to its original state. The
[Recycling Assets with the Recycle Bin](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/recycling-assets-with-the-recycle-bin-liferay-portal-6-2-user-guide-04-en)
section of *Using Liferay Portal* provides complete details on the Recycle Bin's
capabilities.

Interestingly, the Recycle Bin is not a single feature, but rather one of
Liferay Portal's frameworks. This means that all applications in the portal can
use the Recycle Bin if developers implement the feature. Also, because the
Recycle Bin is completely integrated with the platform, Liferay Portal can
distinguish between recycled and non-recycled assets. For example, when
searching for assets in the search bar, only non-trashed assets are returned.

In this section, we'll discuss how to implement the Recycle Bin framework for a
Liferay application. There are five Recycle Bin framework capabilities that you
can leverage in your app. 

- Moving Entries to the Recycle Bin
- Restoring Entries from the Recycle Bin
- Implementing the Undo Action
- Moving/Restoring Parent Entities
- Resolving Conflicts

While discussing these capabilities, we'll refer to code snippets taken from
Liferay's [Jukebox Portlet](https://github.com/liferay-labs/jukebox-portlet)
plugin. The plugin actually bundles three separate portlets: Songs, Albums,
and Artists, which are integrated together.

The Jukebox portlet allows you to upload songs that you can play from your
portal. You can categorize the songs by album and artist, keeping your song
collection organized. The Jukebox portlet's song and album assets can be moved
to and from the Recycle Bin.

Also, the Jukebox portlet offers indexed (searchable) entities and a workflow.
These are not mandatory to implement the Recycle Bin, but are present in this
example portlet.

For each section, we'll walk through how the Jukebox portlet implements the
given capability of the Recycle Bin. By following the steps taken to implement
the Recycle Bin in the Jukebox portlet, you can similarly implement the Recycle
Bin in your app. Let's begin implementing the Recycle Bin framework!

### Moving Entries to the Recycle Bin [](id=moving-entries-to-the-recycle-bin-liferay-portal-6-2-dev-guide-07-en)

The first framework component to implement is the ability to move entries to the
Recycle Bin. Once you implement this component, users can move entries to the
Recycle Bin with the click of a button. 

![Figure 7.1: You can easily create a way to move your app's entities to the Recycle Bin.](../../images/move-entities-rb.png)

We'll guide you through the following steps for providing the means to move
entries to the Recycle Bin: 

1. Enable Trash for Service Entities
2. Implement a Trash Handler for Each Trash-Enabled Entity
3. Create a Service Method to Move Entries to the Recycle Bin
4. Create a Portlet Action to Initiate Moving Entries to Recycle Bin
5. Implement a Trash Renderer for Each Trash-Enabled Entity

Let's begin implementing this component by configuring the app's service. 

#### *Moving Entries Step 1*: Enable Trash for Service Entities [](id=moving-entries-step-1-enable-trash-for-s-liferay-portal-6-2-dev-guide-07-en)

You must enable the trash feature for each entity in your app that you want to
use with the Recycle Bin. In your app's `service.xml` file, insert the
`trash-enabled="true"` attribute within the `<entity>` tag for each of those
entities. Here's a code snippet from the Jukebox portlet's
[service.xml](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/service.xml):

    <entity name="Song" local-service="true" remote-service="true" uuid="true" trash-enabled="true">

Run
[Service Builder](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/generate-services-liferay-portal-6-2-dev-guide-04-en)
to generate back-end trash related classes for the trash-enabled entities. 

Next, you'll need to implement trash handlers for those entities.

#### *Moving Entries Step 2*: Implement a Trash Handler for Each Trash-Enabled Entity [](id=moving-entries-step-2-implement-a-trash--liferay-portal-6-2-dev-guide-07-en)

As with many other Liferay frameworks--such as those for workflow, assets, and
indexing--you must implement handler classes for that framework. The Recycle
Bin's handlers are required to manage basic trash operations. You must create an
implementation of the
[TrashHandler](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/TrashHandler.html)
interface for each trash-enabled entity. Recycle Bin trash handlers manage the
entries as they are moved into the Recycle Bin, viewed in the Recycle Bin,
restored, or permanently deleted. As a convenience, Liferay provides the
abstract class
[BaseTrashHandler](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/BaseTrashHandler.html)
that your trash handlers can extend.

Consider the following
[TrashHandler](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/trash/TrashHandler.html)
methods as a minimal set of methods to implement/override in your trash
handlers: 

- `deleteTrashEntry()`
- `getClassName()`
- `getRestoreContainedModelLink()`
- `getRestoreMessage()`
- `hasTrashPermission()`
- `isInTrash()`
- `restoreTrashEntry()`

The Jukebox portlet implements a class named
[JukeBoxBaseTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/JukeBoxBaseTrashHandler.java)
that serves as a base implementation. It's leveraged by trash handlers for the
app's song and album entities. As an example trash handler implementation, you
can view the
[SongTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/SongTrashHandler.java)
and its parent base class
[JukeBoxBaseTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/JukeBoxBaseTrashHandler.java).

After you've implemented trash handlers for your trash-enabled entities, you'll
need to specify those handlers in your app's `liferay-portlet.xml` file. For
example, the Jukebox's `SongTrashHandler` class is specified in a
`<trash-handler>` element for the Songs portlet as follows:

    <trash-handler>org.liferay.jukebox.trash.SongTrashHandler</trash-handler>

You can refer to the Jukebox portlet's
[liferay-portlet.xml](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/liferay-portlet.xml)
file for examples of defining trash handlers. 

---

![note](../../images/tip-pen-paper.png) **Note:** Notice that the album trash
handler is also specified within the Songs portlet. This was done for
organizational purposes. A trash handler refers to an entity, not a portlet.
Thus, a trash handler can be declared in any of a plugin's portlets. For
neatness, however, they are usually declared in the principal portlet of the
suite.

Also, the Jukebox portlet does not send artist assets to the Recycle Bin.
Therefore, there is no trash handler specified for artists.

---

Great! So we have trash handlers ready to manage our trash entries, but we still
need a way to get the entries into the Recycle Bin. Let's create a service
method to move entries there. 

#### *Moving Entries Step 3*: Create a Service Method to Move Entries to the Recycle Bin [](id=moving-entries-step-3-create-a-service-m-liferay-portal-6-2-dev-guide-07-en)

We'll implement a local service method that actually moves entries to the
Recycle Bin. This service method must implement the trash service for the
entity. 

For example, the Jukebox portlet's
[SongLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)
class implements the following method to move song entities to the Recycle Bin: 

    @Indexable(type = IndexableType.REINDEX)
    public Song moveSongToTrash(long userId, Song song)
        throws PortalException, SystemException {

        ServiceContext serviceContext = new ServiceContext();

        // Entry

        User user = userPersistence.findByPrimaryKey(userId);
        Date now = new Date();

        int oldStatus = song.getStatus();

        song.setModifiedDate(serviceContext.getModifiedDate(now));
        song.setStatus(WorkflowConstants.STATUS_IN_TRASH);
        song.setStatusByUserId(user.getUserId());
        song.setStatusByUserName(user.getFullName());
        song.setStatusDate(serviceContext.getModifiedDate(now));

        // Asset

        assetEntryLocalService.updateVisible(
            Song.class.getName(), song.getSongId(), false);

        // Trash

        UnicodeProperties typeSettingsProperties = new UnicodeProperties();

        typeSettingsProperties.put("title", song.getName());

        TrashEntry trashEntry = trashEntryLocalService.addTrashEntry(
            userId, song.getGroupId(), Song.class.getName(), song.getSongId(),
            song.getUuid(), null, oldStatus, null, typeSettingsProperties);

        song.setName(TrashUtil.getTrashTitle(trashEntry.getEntryId()));

        songPersistence.update(song);

        return song;
    }

Notice that this method is annotated as `@Indexable`. That means that every time
a song entry is moved to the Recycle Bin, Liferay reindexes the song entities
and their corresponding trash entries. This makes trashed entries searchable
from within the Recycle Bin, but not searchable outside of it. Trashed entries
are not included in search results outside of the Recycle Bin.

Another important aspect of this method is its call to
`song.setStatus(WorkflowConstants.STATUS_IN_TRASH)`, which sets the song's
status so that workflows know the song is in the trash.

Next, the asset's visibility is updated so a user can no longer view it outside
the Recycle Bin. Its visibility is deactivated by the following call:

    assetEntryLocalService.updateVisible(Song.class.getName(), song.getSongId(), false);

On first thought, this may seem a bit odd. Why are we making the entry invisible
in its original location? I thought we were moving it to the Recycle Bin?
Importantly, entries that are moved to the Recycle Bin are actually left in
their original location, but with their visibility turned off.

---

![note](../../images/tip-pen-paper.png) **Note:** If you're not using assets
with your entity, you'll need to filter the elements in your UI by status, so
only approved entities are shown. Otherwise, your app will display approved
entities and trashed entities together. Only assets can use the
`updateVisible()` method to update their status.

---

Next, notice that the service method adds a new trash entry in the Recycle Bin:

    TrashEntry trashEntry = trashEntryLocalService.addTrashEntry(
            userId, song.getGroupId(), Song.class.getName(), song.getSongId(),
            song.getUuid(), null, oldStatus, null, typeSettingsProperties);

Lastly, the `moveSongToTrash()` service method invokes the
[`TrashUtil.getTrashTitle`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/trash/util/TrashUtil.html#getTrashTitle(long))
method to set the entry's *trash title*, which is an alternative reference to
the entry. The trash title prevents duplicate entry name conflicts, which we'll
discuss in more detail in the later section on resolving conflicts. 

#### *Moving Entries Step 4*: Create a Portlet Action to Initiate Moving Entries to Recycle Bin [](id=moving-entries-step-4-create-a-portlet-a-liferay-portal-6-2-dev-guide-07-en)

Great! Now you must provide the means of invoking the service method from your
portlet. We'll implement this using a portlet action that can be triggered from
a JSP.

The
[JukeboxPortlet](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/portlet/JukeboxPortlet.java)
class, which extends
[MVCPortlet](https://github.com/liferay/liferay-portal/blob/master/util-bridges/src/com/liferay/util/bridges/mvc/MVCPortlet.java),
provides the `deleteSong()` portlet action method to invoke the
[SongLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)'s
`moveSongToTrash()` service method:

    public void deleteSong(ActionRequest request, ActionResponse response)
        throws Exception {

        long songId = ParamUtil.getLong(request, "songId");

        boolean moveToTrash = ParamUtil.getBoolean(request, "moveToTrash");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
            Song.class.getName(), request);

        try {
            if (moveToTrash) {
                Song song = SongServiceUtil.moveSongToTrash(songId);
                ...
                SessionMessages.add(request, PortalUtil.getPortletId(request) +
                    SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA, data);
                ...
            }
            else {
                SongServiceUtil.deleteSong(songId, serviceContext);

                SessionMessages.add(request, "songDeleted");
            }
            ...
        }
        ...
    }

Note that the `try` block contains logic for handling whether the entry is to be
moved to the Recycle Bin or permanently deleted.

You can use a JSP to invoke your portlet action. Our Jukebox portlet's
[view_song.jsp](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/html/songs/view_song.jsp)
file provides buttons named *Move to the Recycle Bin* and *Delete* to trigger
trashing or permanently deleting a song: 

    <aui:nav>
        <portlet:actionURL name="deleteSong" var="deleteSongURL">
            <portlet:param name="songId" value="<%= String.valueOf(song.getSongId()) %>" />
            <portlet:param name="moveToTrash" value="<%= String.valueOf(trashEnabled) %>" />
            <portlet:param name="redirect" value="<%= redirect %>" />
        </portlet:actionURL>

        <c:choose>
            <c:when test="<%= trashEnabled %>">
                <aui:nav-item href="<%= deleteSongURL %>" iconCssClass="icon-trash" label="move-to-the-recycle-bin" />
            </c:when>
            <c:otherwise>
                <aui:nav-item href="<%= deleteSongURL %>" iconCssClass="icon-key" label="delete" useDialog="<%= true %>" />
            </c:otherwise>
        </c:choose>
    </aui:nav>

Notice this JSP code specifies the `JukeboxPortlet.java`'s `deleteSong` action
method. Also, the button appropriate for recycling or deleting is displayed
depending on the value of the `trashEnabled` boolean variable.

Let's wrap up our objective of moving entries to the Recycle Bin by providing
the means to render the trashed entries in the Recycle Bin. For this, we must
implement a trash renderer for the trash-enabled entities. 

#### *Moving Entries Step 5*: Implement a Trash Renderer for Each Trash-Enabled Entity [](id=moving-entries-step-5-implement-a-trash--liferay-portal-6-2-dev-guide-07-en)

Now that we have the necessary classes and methods to accomplish moving entries
to the Recycle Bin, let's implement the appropriate renderer so we can see our
trashed entries. Similar to the trash handler, you'll need to create a class
that renders trash entries in the Recycle Bin. If you're already using an asset
renderer, you can reuse it, as long as it implements the `TrashRenderer`
interface too.

As an example of a combined asset renderer and trash renderer implementation,
consider the Jukebox portlet's 
[SongAssetRenderer](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/asset/SongAssetRenderer.java).

If you don't have an asset renderer, you'll need to create a trash renderer and
implement a `getTrashRenderer()` method to instantiate and return a trash
renderer based on a trash entry's primary key. For an example of accessing the
trash renderer from a trash handler, consider the following method from the
Document Library class
[DLFileShortcutTrashHandler](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portlet/documentlibrary/trash/DLFileShortcutTrashHandler.java):

    @Override
    public TrashRenderer getTrashRenderer(long classPK)
        throws PortalException, SystemException {

        DLFileShortcut fileShortcut = getDLFileShortcut(classPK);

        return new DLFileShortcutTrashRenderer(fileShortcut);
    }

It creates a new trash renderer class,
[DLFileShortcutTrashRenderer](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portlet/documentlibrary/trash/DLFileShortcutTrashRenderer.java),
based on the file shortcut instance. 

Congratulations! You now know how to implement moving your app's entries to the
Recycle Bin. 

You can test the Recycle Bin feature in the example Jukebox portlet by moving
songs to the Recycle Bin.

![Figure 7.2: To recycle songs in the Jukebox portlet, first click the *i* button, and then select *Move to the Recycle Bin*.](../../images/recycle-songs-steps.png)

Now that you've provided the means for putting entries into the Recycle Bin, how
about providing a way to restore them? Don't worry. Restoring entries from the
Recycle Bin to their original state is straightforward. Let's learn how to do
this next. 

### Restoring Entries from the Recycle Bin [](id=restoring-entries-from-the-recycle-bin-liferay-portal-6-2-dev-guide-07-en)

Now that you're able to move entries *to* the Recycle Bin, we'll make sure your
app can restore entries *from* the Recycle Bin. Besides, what's the point of
having a Recycle Bin if you can't restore its entries?

![Figure 7.3: By implementing the *Restore* functionality, you'll be able to move entries out of the Recycle Bin and make them visible again in their original locations.](../../images/restore-entry-rb.png)

Entries are restored by returning their visibility in their original location
and removing them from the Recycle Bin. As we briefly discussed in the last
section, entries are never removed from their original location; their
visibility is simply turned off and a reference to the original entry is placed
in the Recycle Bin. We call this reference a *trash entry*. Therefore, the
restoration process is very similar to the moving process: you'll just need to
return the entry's visibility in its original location and delete its trash
entry.

We'll guide you through the following steps for implementing the restoration
capability: 

1. Create a Service Method to Restore Entries from the Recycle Bin
2. Invoke the Service Method from the Trash Handler

We'll begin this process by creating a service method for the restoration
process.

#### *Restoring Entries Step 1*: Create a Service Method to Restore Entries from the Recycle Bin [](id=restoring-entries-step-1-create-a-servic-liferay-portal-6-2-dev-guide-07-en)

You'll need to create a service method that removes the trash entry from the
Recycle Bin and makes the asset entry visible again in its original location. 

As an example, the `restoreSongFromTrash()` service method from the Jukebox
portlet's
[SongLocalServiceImpl](https://github.com/codyhoag/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)
restores songs from the Recycle Bin:

    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Song restoreSongFromTrash(long userId, long songId)
        throws PortalException, SystemException {

        ServiceContext serviceContext = new ServiceContext();

        // Entry

        User user = userPersistence.findByPrimaryKey(userId);
        Date now = new Date();

        TrashEntry trashEntry = trashEntryLocalService.getEntry(
            Song.class.getName(), songId);

        Song song = songPersistence.findByPrimaryKey(songId);

        song.setName(TrashUtil.getOriginalTitle(song.getName()));
        song.setModifiedDate(serviceContext.getModifiedDate(now));
        song.setStatus(trashEntry.getStatus());
        song.setStatusByUserId(user.getUserId());
        song.setStatusByUserName(user.getFullName());
        song.setStatusDate(serviceContext.getModifiedDate(now));

        songPersistence.update(song);

        assetEntryLocalService.updateVisible(
            Song.class.getName(), song.getSongId(), true);

        trashEntryLocalService.deleteEntry(Song.class.getName(), songId);

        return song;
    }

First, we restore the item's original name with the help of
[TrashUtil](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/trash/util/TrashUtil.html)'s
`getOriginalTitle()` method. Then we set the entry's modified date to the
current date.

    song.setName(TrashUtil.getOriginalTitle(song.getName()));
    song.setModifiedDate(serviceContext.getModifiedDate(now));

Next, we update the entry's status by setting it to the original status from
before the entry was trashed. For instance, if the entry was originally a draft
(`STATUS_DRAFT`), it's restored back to a draft. The status is updated by the
following call:

    song.setStatus(trashEntry.getStatus());

There are also several other statuses we update, which include the status by
user ID, by user name, and the status date. These indicate the user that
restored the trash entry and set the date the status was modified.

    song.setStatusByUserId(user.getUserId());
    song.setStatusByUserName(user.getFullName());
    song.setStatusDate(serviceContext.getModifiedDate(now));

Then we make the asset entry visible in its original location by calling:

    assetEntryLocalService.updateVisible(Song.class.getName(), song.getSongId(), true);

Lastly, we delete the trash entry from the Recycle Bin by calling:

    trashEntryLocalService.deleteEntry(Song.class.getName(), songId);

The entry is restored and no longer resides in the Recycle Bin.

Importantly, after writing your service method, make sure to run 
[Service Builder](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/generate-services-liferay-portal-6-2-dev-guide-04-en)
to generate the corresponding service interface and utility methods. 

To finish implementing the entry restoration process, let's invoke the service
method from the entity's trash handler. 

#### *Restoring Entries Step 2*: Invoke the Service Method from the Trash Handler [](id=restoring-entries-step-2-invoke-the-serv-liferay-portal-6-2-dev-guide-07-en)

Now that our service provides a method for restoring the entry, we must invoke
it from our trash handler's `restoreTrashEntry()` method. The Recycle Bin
framework calls this method when a user clicks the trash entry's *Restore*
button. 

The following `restoreTrashEntry()` method implementation is from the Jukebox
portlet's
[SongTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/SongTrashHandler.java)
class. 

    @Override
    public void restoreTrashEntry(long userId, long classPK)
        throws PortalException, SystemException {

        SongLocalServiceUtil.restoreSongFromTrash(userId, classPK);
    }

When Jukebox users want to restore a song from the Recycle Bin, they simply
click the song's *Restore* button. 

---

![note](../../images/tip-pen-paper.png) **Note:** Sometimes, conflicts can occur
when restoring entries. For instance, suppose you create a file with the same
name of a file that you've trashed. Although the file is in the Recycle Bin,
it's still present in its original location, but with its status changed and
visibility turned off. The resolution framework avoids these two files
conflicting. We'll talk more about the resolution framework in the *Resolving
Conflicts* section.

---

You now know how to provide the means for users to restore entries from the
Recycle Bin! Next, let's implement the convenient *Undo* button so users don't
have to visit the Recycle Bin to restore an item they just trashed. 

### Implementing the Undo Functionality [](id=implementing-the-undo-functionality-liferay-portal-6-2-dev-guide-07-en)

Sometimes, you may accidentally send the wrong entry to the Recycle Bin. It
seems kind of grueling to navigate away from your page to the Recycle Bin to
restore the item, just to go back to where you originally started, right?
For this reason, the Recycle Bin framework supports an *Undo* button so you can
conveniently undo the action of sending an entry to the Recycle Bin without
leaving the page. In addition, the undo functionality provides links to the
trashed entry and the Recycle Bin.

![Figure 7.4: Implementing the Undo button enables users to conveniently reverse sending entries to the Recycle Bin.](../../images/undo-rb.png)

We'll guide you through the following steps in implementing the Undo
functionality:

1. Add the Undo Tag
2. Call the Action for Restoration
3. Display the Taglib

Let's implement the Undo button and its related links!

#### *Implementing the Undo Functionality Step 1*: Add the Undo Tag [](id=implementing-the-undo-functionality-step-liferay-portal-6-2-dev-guide-07-en)

The first thing you'll need to do is add the `<liferay-ui:trash-undo>` taglib in
our JSP. Then, you need to set a portlet action URL and pass it to the
`<liferay-ui:trash-undo>` taglib. This maps the taglib's *Undo* button to the
portlet action that we'll implement in the next step. 

Here's an example of how the Jukebox portlet's
[view.jsp](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/html/songs/view.jsp)
implements this:

    <portlet:actionURL name="restoreSong" var="undoTrashURL" />

    <liferay-ui:trash-undo portletURL="<%= undoTrashURL %>" />

Now that we've added the taglib and action URL, let's implement the portlet
action to restore the entry.

#### *Implementing the Undo Functionality Step 2*: Create a Portlet Action to Initiate Restoration [](id=implementing-the-undo-functionality-st-liferay-portal-6-2-dev-guide-07-en-0)

You must create a portlet action method that invokes your service method to
restore the entry. 

The following portlet action method from the
[JukeboxPortlet](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/portlet/JukeboxPortlet.java)
class restores the song from the Recycle Bin:

    public void restoreSong(ActionRequest request, ActionResponse response)
        throws Exception {

        long[] restoreEntryIds = StringUtil.split(
            ParamUtil.getString(request, "restoreEntryIds"), 0L);

        for (long restoreEntryId : restoreEntryIds) {
            SongServiceUtil.restoreSongFromTrash(restoreEntryId);
        }
    }

This method implements the `restoreSong` action that we named in the `view.jsp`.
The action URL maps the `<liferay-ui:trash-undo>` taglib to this method.

Note how it parses entry IDs from the request object. It restores all of these
entries by calling the `restore*` service method we defined in the previous
*Restoring Entries from the Recycle Bin* section.

Are you wondering how this portlet action gets the ID of the entries to restore?
We'll show you how to pass this data to the session next. 

#### *Implementing the Undo Functionality Step 3*: Providing Trash Entry Data for the Taglib [](id=implementing-the-undo-functionality-st-liferay-portal-6-2-dev-guide-07-en-1)

The final step for implementing the Undo button is to provide the trashed
entry's information to the `<liferay-ui:trash-undo>` taglib. In order for the
taglib to display properly, we must provide some information for the session
messages, so that the session knows which entries were just deleted. Once the
session knows which elements were just deleted, our method can use that
information to restore the entries. 

The following `if` statement from the `deleteSong()` method of the
[JukeboxPortlet](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/portlet/JukeboxPortlet.java)
class populates the session with the entries that were just deleted:

    if (moveToTrash) {
        Song song = SongServiceUtil.moveSongToTrash(songId);

        Map<String, String[]> data = new HashMap<String, String[]>();

        data.put("deleteEntryClassName",
            new String[] {Song.class.getName()});
        data.put("deleteEntryTitle",
            new String[] {TrashUtil.getOriginalTitle(song.getName())});
        data.put("restoreEntryIds",
            new String[] {String.valueOf(songId)});

        SessionMessages.add(request, PortalUtil.getPortletId(request) +
            SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA, data);

        SessionMessages.add(request, PortalUtil.getPortletId(request) +
            SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
    }

It gathers the elements needed to distinguish each entry instance to restore.
For the Jukebox song element's we include the song's class name, title, and IDs.

    data.put("deleteEntryClassName",
        new String[] {Song.class.getName()});
    data.put("deleteEntryTitle",
        new String[] {TrashUtil.getOriginalTitle(song.getName())});
    data.put("restoreEntryIds",
        new String[] {String.valueOf(songId)});

Then it adds these elements to the session messages, so the session knows which
elements were deleted. 

    SessionMessages.add(request, PortalUtil.getPortletId(request) +
        SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA, data);

    SessionMessages.add(request, PortalUtil.getPortletId(request) +
        SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);

In your portlet's delete action, you can similarly populate the session with the
entry information of the entries being deleted. It's simple really. Now you know
how to implement the Undo functionality for your app's trash-enabled entities. 

Next, let's learn how to move and restore container entities to and from the
Recycle Bin.

### Moving/Restoring Parent Entities [](id=moving-restoring-parent-entities-liferay-portal-6-2-dev-guide-07-en)

To this point, we've only discussed how to move/restore single entities to/from
the Recycle Bin. What happens if we need to trash a parent entity, such as a
parent wiki page, or trash an entity like a web content folder that aggregates
web content articles? The fundamental logic for handling them is similar to
handling single entities, but there are a few different aspects that must be
considered. 

We'll look at the Jukebox portlet's `Album` entity to understand handling
container entities, since each album is a collection of song entities. Let's
learn how the Jukebox portlet is able to move/restore albums to/from the Recycle
Bin so you can translate that knowledge to container entities in your personal
app. 

#### *Moving/Restoring Parent Entities Step 1*: Mark Container Model Service Entities [](id=moving-restoring-parent-entities-step-1--liferay-portal-6-2-dev-guide-07-en)

The first thing we'll need to do is define each container model as such in our
service. To do this, open your app's `service.xml` file and add the
`container-model="true"` attribute in the container entity's primary key
`<column>` tag.

For example, the Jukebox portlet's
[service.xml](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/service.xml)
file marks the album entity as a container model in its `albumId` column: 

    <column name="albumId" type="long" primary="true" container-model="true" />

On running Service Builder for the portlet's service, with its newly designated
container model entities, Service Builder generates several methods that can be
used to obtain the child entities of each parent, or in the case of Jukebox,
songs of each album.

Service Builder generates `*Model.java`, `*ModelClp.java`, and `*ModelImpl.java`
classes to implement the
[ContainerModel](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/ContainerModel.html)
interface. These implementations enable the Recycle Bin framework to identify
and use these models as container models. 

Next, we'll handle the child entities of the parent entity.

#### *Moving/Restoring Parent Entities Step 2*: Manage Children Entities [](id=moving-restoring-parent-entities-step-2--liferay-portal-6-2-dev-guide-07-en)

Implementing the moving/restoring parent entities to/from the Recycle Bin is
very similar to what we've already learned for single entities. However, because
the parents hold child entities, the child entities must be accounted for. The
service method for moving a parent entry to the Recycle Bin must also move the
parent's child entries to the Recycle Bin. Likewise, the service method for
restoring a parent entry from the Recycle Bin must also restore the parent's
child entries. 

On moving a parent entity to the Recycle Bin, the following things must be done
with each of its dependent entities: 

1. Update its status
2. Add a trash version for it
3. Turn off the visibility of its asset
4. Reindex it

Let's look at how Jukebox portlet's
[AlbumLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/AlbumLocalServiceImpl.java)
class handles moving an album's songs to the Recycle Bin by calling a local
convenience method `moveDependentsToTrash()`. Here's the source code for the
`moveDependentsToTrash()` method: 

	protected void moveDependentsToTrash(List<Song> songs, long trashEntryId)
		throws PortalException, SystemException {

		for (Song song : songs) {

			// Entry

			if (song.isInTrash()) {
				continue;
			}

			int oldStatus = song.getStatus();

			song.setStatus(WorkflowConstants.STATUS_IN_TRASH);

			songPersistence.update(song);

			// Trash

			int status = oldStatus;

			if (oldStatus == WorkflowConstants.STATUS_PENDING) {
				status = WorkflowConstants.STATUS_DRAFT;
			}

			if (oldStatus != WorkflowConstants.STATUS_APPROVED) {
				trashVersionLocalService.addTrashVersion(
					trashEntryId, Song.class.getName(), song.getSongId(),
					status, null);
			}

			// Asset

			assetEntryLocalService.updateVisible(
				Song.class.getName(), song.getSongId(), false);

			// Indexer

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				Song.class);

			indexer.reindex(song);
		}
	}

For each of the album's songs, let's consider how this method updates its
status, adds a trash version for it, turns off the visibility of its asset, and
reindexes it for search purposes.

Here's the breakdown of how the this method implements this for the song entity: 

1. *Update its status*: Similar to updating the status for a single entry, the
   status of each album's song must reflect that the song has been moved to the
   Recycle Bin. The following code updates the song's status: 

        song.setStatus(WorkflowConstants.STATUS_IN_TRASH);

        songPersistence.update(song);

2. *Add a trash version for it*: When moving content with versions to the
   Recycle Bin, the trash version entity stores the status of those versions, so
   those statuses can be set back to their original values when the entity is
   restored. When a parent entity with content is sent to the Recycle Bin, each 
   element in that parent also generates a trash version. You can view trash
   versions by navigating inside a trash entry. Adding the trash version for the
   song is done with the following code: 

        trashVersionLocalService.addTrashVersion(trashEntryId,
             Song.class.getName(), song.getSongId(), status, null);

3. *Turn off the visibility of its asset*: The song's visibility is turned off
   in the following code:

        assetEntryLocalService.updateVisible(Song.class.getName(), song.getSongId(), false);

4. *Reindex it*: Since we've modified the visibility of the song entity, we
   must reindex the album's songs so they're searchable. Here's the code that
   accomplishes this: 

        Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Song.class);

        indexer.reindex(song);

You'll be comforted to know that restoring a parent's child entities from the
Recycle Bin involves similar steps. The `restoreDependentsFromTrash()`
convenience method in the Jukebox portlet's
[AlbumLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/AlbumLocalServiceImpl.java)
class demonstrates these steps. As a summary, here's what you do for each child
entity: 

1. Update its status
2. Remove the trash version of it
3. Turn on the visibility of its asset
4. Reindex it

It's really very straightforward, making it easy for you to do in the service
classes for your app's parent entities. 

You can deploy the Jukebox in your portal and test trashing/restoring albums and
songs. As a Jukebox user, you can click inside a trashed album to view its
trashed songs. You also have the option of restoring individual songs or
deleting them permanently. Note that the Jukebox developers didn't create this
UI; it was all generated by the portal framework. As a developer, you only need
to tell the portal that the parent entity has children and how to obtain them.
The Recycle Bin UI automatically accounts for the parent's children for you. 

Terrific! You've learned how to designate parent models as container model
entities in your service definition, you've provided a means to trash/restore a
parent's child entities when trashing/restoring that parent, and you've learned
how the powerful Recycle Bin UI lets you work with a parent's child entities.

Have you wondered what happens when there are conflicts moving and restoring
entries to and from the Recycle Bin? Let's learn how to deal with conflicts
next.

### Resolving Conflicts [](id=resolving-conflicts-liferay-portal-6-2-dev-guide-07-en)

The Conflict Resolution framework helps Liferay users identify and solve
conflicts in the Recycle Bin. The most common conflict for the Recycle Bin is
duplicate naming. For instance, you have file 1 and file 2, both with the same
name. Suppose you move file 1 to the Recycle Bin from a folder. Then, in that
folder, you create file 2. You would get a naming conflict because, although
file 1 is in the Recycle Bin, it is also still located in the original folder,
but with its status changed and visibility turned off.

Let's learn how to implement the Conflict Resolution framework so we can avoid
Recycle Bin conflicts.

#### *Resolving Conflicts Step 1*: Rename Entities Sent to the Recycle Bin [](id=resolving-conflicts-step-1-rename-entiti-liferay-portal-6-2-dev-guide-07-en)

When an entry is sent to the Recycle Bin, it is essentially replicated. We keep
the entity in its original location and create a similar entry in the Recycle
Bin. When viewing the entry from the Recycle Bin UI, the name appears the same
as the original entry. But the portal needs a way to distinguish between the two
entries. This requires adding logic to your entity's service and leveraging
trash utilities to generate new names for the Recycle Bin entries. 

When an entry is sent to the Recycle Bin, it's still located in its original
location, but with a different status. Users may create an entity with a name,
move it to the Recycle Bin, and then create another entity with the same name.
Since both entities are residing in the same location, a naming conflict could
occur. Some applications only allow one entity to exist with a particular field;
for example, names for documents, titles for songs in an album, friendly URLs
for pages, etc. On moving an entity to the Recycle Bin, we need to rename its
fields that could generate conflicts to something unique. 

    UnicodeProperties typeSettingsProperties = new UnicodeProperties();

    typeSettingsProperties.put("title", song.getName());

    TrashEntry trashEntry = trashEntryLocalService.addTrashEntry(
        userId, song.getGroupId(), Song.class.getName(), song.getSongId(),
        song.getUuid(), null, oldStatus, null, typeSettingsProperties);

    song.setName(TrashUtil.getTrashTitle(trashEntry.getEntryId()));

We create a `UnicodeProperties` instance to hold a mapping of each song's title.
The mapping is stored with the trash entry. We invoke
`TrashUtil.getTrashTitle(trashEntry.getEntryId())` from
[SongLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)'s
`moveSongToTrash` method to set the name of the original song to a unique value
that we can use to look up the trash entry associated with the song. Invoking
`TrashUtil.getTrashTitle(trashEntry.getEntryId())` resets the name of the
original song to a unique value--a slash followed by the ID of the song's trash
entry. Since the song is now in the Recycle Bin, it is hidden from viewing in
its original location; so you don't have to worry about this lookup value being
visible to users. As you'll see shortly, the unique names generated by
`TrashUtil.getTrashTitle(...)` are used for looking up the names of the original
entities, on restoring those entities from the Recycle Bin. 

![Figure 7.5: The Recycle Bin allows you to manage trash entries, even if they share the same name.](../../images/trash-entries-with-same-name.png)

Next, we'll consider how to restore the original name of each trashed entity
when it's restored from the Recycle Bin. 

#### *Resolving Conflicts Step 2*: Restore the Entity's Original Name When Restoring From Recycle Bin [](id=resolving-conflicts-step-2-restore-the-e-liferay-portal-6-2-dev-guide-07-en)

Since your entity is renamed, you'll need a way to retrieve its old name, in the
case that the entity gets restored. The code snippet below, found in the
`restoreSongFromTrash(long userId, long songId)` method of the Jukebox portlet's
[SongLocalServiceImpl](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/service/impl/SongLocalServiceImpl.java)
portrays how this is done:

    Song song = songPersistence.findByPrimaryKey(songId);

    song.setName(TrashUtil.getOriginalTitle(song.getName()));

The original song is retrieved by its ID. Then the
`TrashUtil.getOriginalTitle(...)` method is called to look up the song's
original name. Remember that the song's current name is based on the trash
entry's ID. `TrashUtil`'s `getTrashTitle()` method looks up the trash entry and
returns the *title* value (the song's original name) previously mapped in the
entry's type settings properties. We set the original song name back to the
song entity. 

Lastly, whether an entity is in the Recycle Bin or not, it's always nice to
render the entity with its original name. As an example of rendering a song's
original title based on a locale, the Jukebox portlet's
[SongAssetRenderer](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/asset/SongAssetRenderer.java)
provides the following implementation:

 
    @Override
    public String getTitle(Locale locale) {
        if (!_song.isInTrash()) {
            return _song.getName();
        }

        return TrashUtil.getOriginalTitle(_song.getName());
    }

If the song isn't in the Recycle Bin, the song's current name is returned.
Otherwise, the method invokes `TrashUtil.getOriginalTitle(_song.getName())` to
return the song's original name. 

Lastly, we need to implement some required methods to finalize the Conflicts
Resolution framework.

#### *Resolving Conflicts Step 3*: Implement Conflict Resolution Trash Handler Methods [](id=resolving-conflicts-step-3-implement-con-liferay-portal-6-2-dev-guide-07-en)

Your app can now uniquely rename entries on removal and reinstate their original
names when restored. What happens when the original entry is restored to its
original location where a new entry with the same already exists? This causes a
naming conflict that needs to be resolved by the user.

The Recycle Bin framework provides a UI for users to decide whether to overwrite
the existing entry or to keep both entries by updating the title of the entry
they're restoring. The figure below exemplifies a conflict resolution pop-up on
trying to restore a song for which an identically named song is already present
in the Songs portlet. 

![Figure 7.6: The Recycle Bin enables you to handle conflicts by notifying the user with a pop-up message and options for solving the problem.](../../images/resolved-conflict-rb.png)

Two methods need to be implemented in the trash handler to
allow for the necessary checks and updates. The first method should check for
duplicate trash entries. If an entry with the same name is detected in a
directory, an exception must be thrown. You can reference the
`checkDuplicateTrashEntry()` method from
[SongTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/SongTrashHandler.java)
to see how this is done.

<!-- We should fix this. We don't ever want to tell people to go look at some
code to learn how to do something. We should document it instead. -Rich -->

Lastly, implement a method that updates the entry title name. For instance, the
Jukebox portlet updates a song title by calling the `updateTitle()` method,
which can also be found in the
[SongTrashHandler](https://github.com/liferay-labs/jukebox-portlet/blob/master/docroot/WEB-INF/src/org/liferay/jukebox/trash/SongTrashHandler.java)
class. This method is called when the entry you're restoring needs its title
updated, so it no longer conflicts with the preexisting entry that has the same
name. After implementing these methods, you should always be able to resolve
naming conflicts between your trashed entries.

Fantastic! By leveraging the Conflicts Resolution framework in your app, you're
able to provide a smarter Recycle Bin that handles potential conflicts with
ease.

Congratulations! You've finished your journey through the Recycle Bin framework.
You accomplished this feat by developing your app to move entries to the Recycle
Bin, restore entries from the Recycle Bin, use the Undo action, move and restore
parent entities, and use the Recycle Bin's Resolution Conflicts framework. Your
*dirty* work is complete and you now know how to implement the Recycle Bin
framework in your app.

Next, let's learn about Liferay's Message Bus. 

## Using Message Bus [](id=using-message-bus-liferay-portal-6-2-dev-guide-06-en)

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

### The Message Bus System [](id=the-message-bus-system-liferay-portal-6-2-dev-guide-06-en)

The Message Bus system contains the following components: 

- **Message Bus**: Manages transfer of messages from message *senders* to
  message *listeners*. 
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

![Figure 7.7: Example, Message Bus system](../../images/msg-bus-system.png)

The Message Bus supports *synchronous* and *asynchronous* messaging: 

- **Synchronous messaging**: After it sends a message, the sender blocks waiting
  for a response from a recipient. 
- **Asynchronous messaging**: After it sends a message, the sender is free to
  continue processing. The sender can be configured to receive a call-back or
  can simply send and forget. We'll show you how to implement both synchronous
  and asynchronous messaging in this section. 
    - **Call-back**: The sender can include a call-back destination key as the
      response destination for the message. The recipient (listener) can then
      send a response message back to the sender via this response
      destination. 
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

### Example Use Case--Procurement Process [](id=message-bus-example-procurement-process-liferay-portal-6-2-dev-guide-06-en)

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
forgot to copy the Sales department in their email to Procurement.  Tempers
flew, feelings were hurt, and everybody avoided hanging out in the company
breakroom for the next couple of weeks. 

<!-- Now that I see that this example is not a functional or even a potential
real-world example, I think we need to scrap the whole thing and come up with
another example, one that is integrated into an actual application. We could
conceivablly salvage it by making it into a purchase request application:
Departments have to enter purchase requests, and the message bus automatically
emails certain departments when a purchase request is entered. -Rich -->

Jungle Gyms R-Us could use Liferay's [Workflow with
Kaleo](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/kaleo-forms-defining-business-processes-liferay-portal-6-2-user-guide-12-en)
to resolve the communication breakdown, but we'll resolve the Jungle Gym's
communication woes using Message Bus, to show you how it works. Here are the
inter-department message exchanges we'll accommodate:

 Message | Sender | Listener | Response | Response Listeners |
-------- | ------ | -------- | -------- | ------------------ |
 Request permission to proceed with purchase | Procurement | Finance | required | Procurement |
 Request permission to proceed with purchase | Procurement | Legal | required | Procurement |
 Notify and solicit feedback on new purchase | Procurement | Warehouse | optional | Procurement, Sales |
 Notify and solicit feedback on new purchase | Procurement | Sales | optional | Procurement, Warehouse |
 Broadcast equipment news | Procurement | Employees | none | none |

Let's implement Procurement's request to Finance first. 

### Synchronous Messaging [](id=synchronous-messaging-liferay-portal-6-2-dev-guide-06-en)

In our example, equipment purchases can't proceed without approval from Finance
and Legal departments. Since special offers from the manufacturers often only
last for a couple hours, Procurement makes it their top priority to get approval
as soon as possible. Implementing their exchange using *synchronous* messaging
makes the most sense. 

![Figure 7.8: Synchronous messaging](../../images/msg-bus-sync-msg.png)

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
   response destination name from the `message` variable and sets the destination
   of the response message. 

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
        xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
    >

        <!-- Listeners -->

        <bean
            id="messageListener.finance_listener"
            class="com.liferay.training.parts.messaging.impl.FinanceMessagingImpl"
        /> 
        <bean
            id="messageListener.legal_listener"
            class="com.liferay.training.parts.messaging.impl.LegalMessagingImpl"
        />
        <bean
            id="messageListener.procurement_listener"
            class="com.liferay.training.parts.messaging.impl.ProcurementMessagingImpl"
        />

        <!-- Destinations -->

        <bean
            id="destination.finance.purchase"
            class="com.liferay.portal.kernel.messaging.SynchronousDestination"
        >
            <property name="name" value="jungle/finance/purchase" />
        </bean>

        <bean
            id="destination.finance.purchase.response"
            class="com.liferay.portal.kernel.messaging.SynchronousDestination"
        >
            <property name="name" value="jungle/finance/purchase/response" />
        </bean>

        <bean
            id="destination.legal.purchase"
            class="com.liferay.portal.kernel.messaging.SynchronousDestination"
        >
            <property name="name" value="jungle/legal/purchase" />
        </bean>

        <bean
            id="destination.legal.purchase.response"
            class="com.liferay.portal.kernel.messaging.SynchronousDestination"
        >
            <property name="name" value="jungle/legal/purchase/response" />
        </bean>

        <!-- Configurator -->

        <bean
            id="messagingConfigurator"
            class=
                "com.liferay.portal.kernel.messaging.config.PluginMessagingConfigurator"
        >
            <property name="messageListeners">
                <map key-type="java.lang.String" value-type="java.util.List">
                    <entry key="jungle/finance/purchase">
                        <list
                            value-type=
                                "com.liferay.portal.kernel.messaging.MessageListener"
                        >
                            <ref bean="messageListener.finance_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/finance/purchase/response">
                        <list
                            value-type=
                                "com.liferay.portal.kernel.messaging.MessageListener"
                        >
                            <ref bean="messageListener.procurement_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/legal/purchase">
                        <list
                            value-type=
                                "com.liferay.portal.kernel.messaging.MessageListener"
                        >
                            <ref bean="messageListener.legal_listener" />
                        </list>
                    </entry>
                    <entry key="jungle/legal/purchase/response">
                        <list
                            value-type=
                                "com.liferay.portal.kernel.messaging.MessageListener"
                        >
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

### Asynchronous Messaging with Callbacks [](id=asynchronous-messaging-with-callbacks-liferay-portal-6-2-dev-guide-06-en)

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

![Figure 7.9: Asynchronous messaging with *serial* dispatching](../../images/msg-bus-async-serial-msg.png)

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
            if (message.getDestinationName().equals("jungle/purchase"))
            {
                doReceive(message);
            }
            else if (
                message.getDestinationName().equals("jungle/purchase/response"))
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

    <bean
        id="messageListener.warehouse_listener"
        class="com.liferay.training.parts.messaging.impl.WarehouseMessagingImpl"
    />
    <bean
        id="messageListener.sales_listener"
        class="com.liferay.training.parts.messaging.impl.SalesMessagingImpl"
    />

*Destination beans*: The purchase notifications will be sent to a *serial*
  destination and the responses will be sent to a *synchronous* destination. 

    <bean
        id="destination.purchase"
        class="com.liferay.portal.kernel.messaging.SerialDestination"
    >
        <property name="name" value="jungle/purchase" />
    </bean>

    <bean
        id="destination.purchase.response"
        class="com.liferay.portal.kernel.messaging.SynchronousDestination"
    >
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

Next, let's explore the asynchronous *send and forget* model.

### Asynchronous "Send and Forget" [](id=asynchronous-send-and-forget-liferay-portal-6-2-dev-guide-06-en)

In the *send and forget* model, the sender sends messages and continues
processing. We'll apply this behavior to Jungle Gym's company-wide new product
notification.

Procurement isn't expecting response messages from individual employees, so
there's no need for the company-wide listener to package up responses. We do,
however, want everyone to get product news at the *same time*, so instead of
dispatching news to employees *serially* we'll dispatch *in parallel*.

![Figure 7.10: Asynchronous messaging with *parallel* dispatching](../../images/msg-bus-async-parallel-msg.png)

We'll specify a parallel destination type in our `messaging-spring.xml`:

*Destination bean*:

    <bean
        id="destination.employee.news"
        class="com.liferay.portal.kernel.messaging.ParallelDestination"
    >
            <property name="name" value="jungle/employee/news" />
    </bean>

*Listener bean*:

    <bean
        id="messageListener.employee_listener"
        class="com.liferay.training.parts.messaging.impl.EmployeeMessagingImpl"
    />

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

<!--

## Social Activities [](id=social-activities-liferay-portal-6-2-dev-guide-06-en)

Needs Content
 
 -->

Next we'll show you the Device Detection API and its capabilities. 

## Device Detection [](id=device-detection-liferay-portal-6-2-dev-guide-06-en)

As you know, Internet traffic has risen exponentially over the past decade and
shows no sign of stopping. With the latest and greatest devices, mobile Internet
access has become the norm and is predicted to pass PC based Internet access
soon. Because of the mobile boom, new obstacles and challenges are presented for
content management. How will content adapt to all devices with different
capabilities? How can your grandma's gnarly tablet and cousin's awesome new
mobile phone request the same information from your portal?

The Device Detection API detects the capabilities of any device making a request
to your portal. It can also determine what mobile device or operating system was
used to make a request, and then follows rules to make Liferay render pages
based on the device. To use this feature, you first need to install the *Device
Recognition Provider* app from Liferay Marketplace. Find more information on
[Device Recognition Provider CE
app](http://www.liferay.com/marketplace/-/mp/application/15193341) and [Device
Recognition Provider
EE](http://www.liferay.com/marketplace/-/mp/application/15186132) at Liferay
Marketplace. 

The Device Recognition plugin comes bundled inside the Device Recognition
Provider app; it uses a device database called *WURFL* to determine the
capabilities of your device. Visit the WURFL website for more information at
[http://wurfl.sourceforge.net](http://wurfl.sourceforge.net).

You can create your own plugin to use your device's database. Let's look at
some simple uses of the Device Detection API and talk about its
capabilities. 

### Using the Device API [](id=using-the-device-api-liferay-portal-6-2-dev-guide-06-en)

Let's look at a couple of code snippets to get you started. You can obtain the
object `Device` from the `themeDisplay` object like this:

    Device device = themeDisplay.getDevice();

You can view the `Device` API at
[http://docs.liferay.com/portal/6.2/javadocs](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/mobile/device/Device.html).
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

### Device Capabilities [](id=device-capabilities-liferay-portal-6-2-dev-guide-06-en)

Most of the capabilities of a device can be detected, depending on the device
detection implementation you're using. The Device Recognition plugin's device
database (WURFL) has a list of capabilities, described at
[http://www.scientiamobile.com/wurflCapability/tree](http://www.scientiamobile.com/wurflCapability/tree).
For example, you can obtain the capability of a brand name with this code:

    String brand = device.getCapability("brand_name");

You can grab many other device capabilities, including `model_name`,
`marketing_name`, and `release_date`. You can also get boolean values like
`is_wireless_device`, `is_tablet`, etc. 

With the Device Detection API, you can detect the capabilities of a device
making request to your portal and render content accordingly; so your grandma's
gnarly tablet and your cousin's awesome new mobile phone can make requests to
your portal and receive identical content. This will make everyone happy! 

<!-- ## Other frameworks -->

<!-- Liferay has a variety of frameworks that simplify development of complex
functionalities for your own applications. Liferay's frameworks have evolved
from its built in applications, so they're proven to work in the real world,
even in high performance portals. 

This section is a placeholder that provides a brief description of the main
frameworks provided with Liferay 6.2. The following list is a work in progress,
since we'll add more sections  to this chapter over time, and some of the
current sections might evolve into their own chapters as we add more
information and detailed instructions on how to use them. 

- *Workflow Framework*: Lets you add Workflow functionality to your own
  portlets. One benefit of using this framework is that you can reuse all the
workflow management UIs Liferay provides. You can also abstract your code from
the specific workflow engine that will be used (e.g., JBPM or Liferay Kaleo).
Many Liferay portlets use this framework; to see a simple example and learn how
you can use it, the blogs portlet is a good start. 

- *Custom Fields*: A portlet that uses custom fields lets the end user extend
  the fields of its data entries with custom fields defined by the end user.
To see a list of data types that support this functionality go to Liferay's
Control Panel &rarr; Custom Fields. 

- *Inline Permissions Framework*: Lets you enhance your SQL queries so the
  database checks for view permissions. This is particularly useful when doing
queries for data entries that might result in many of items, making the
checking of permissions afterward inefficient. It's also useful when you want
to implement pagination (which wouldn't work if permissions are checked
afterward and an item is removed). Liferay's *Document Library* and *Message
Boards* portlets both use this functionality. 

- *Faceted Search*: A new API that simplifies the creation new facet
  configurations and searches. It uses a JSON based configuration to define the
details of facets used for the search. To find out more, see the [Faceted
Search](http://www.liferay.com/community/wiki/-/wiki/1071674/Faceted+Search)
wiki by Ray Aug&#233;.

Check back in the near future; new editions of the Developer's Guide will have
extended information on each of these frameworks.  -->

<!-- The above section should also be removed. All of these are implemented and
documented in *Liferay in Action*. -Rich -->

## Summary [](id=summary-liferay-portal-6-2-dev-guide-06-en)

You've learned how to leverage `ServiceContext` objects in your use of Liferay
services and how Liferay's permissions and JSR portal security work.

With Liferay's frameworks, implementing complex functionality in your custom
portlets becomes easy. We discussed how to use Liferay's ServiceContext object,
permissions framework, asset framework, message bus API, and device detection
API. Check back regularly to find more detailed descriptions of current
frameworks. You might also discover brand new frameworks that'll knock your
socks off, or at least simplify your custom portlet development. 

Not only does Liferay Portal boast of fabulous frameworks but also of its
unwavering support of workflow development. Naturally, privileged portal users
can create workflows right in portal. But as workflows incorporate API calls and
complex user interaction, you the developer, are called on to deliver robust
workflow solutions. As you implement your advanced workflows, it only makes
sense that we give you an optimal development environment. Enter Liferay
Developer Studio and the Kaleo Designer for Java plugin! With this plugin, you
not only get the drag-and-drop functionality you've come to know and love, but
you also get access to an optimal development environment to work with Java APIs
and FreeMarker templates. You're going to love building your workflows in Kaleo
Designer for Java. Get ready to drop in and make waves with your workflows.
Cowabunga! 
