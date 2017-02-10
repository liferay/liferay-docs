# Adding Permissions to Resources [](id=adding-permissions-to-resources)

Public bulletin boards are great. Anyone can inform others of just about
anything. On the other hand, *anyone* can post just about *anything* on the
bulletin board. Some of this content might not be relevant to the community.
Other content might be inappropriate. Thus, you sometimes need a way to
restrict who can post or access content.

Fortunately, no matter what your portlet does, access to it and to its content
can be controlled with permissions. Read on to learn about Liferay's
permissions system and how add permissions to your application.

## Liferay's Permission System [](id=liferays-permission-system)

Liferay's permission system uses a flexible mechanism that defines the actions
that a given user can perform within the context of Liferay or a specific
application. Liferay developers break down the operations that can be performed
in Liferay or in a certain application into distinct *actions*. The act of
granting the ability to perform an action to a specific role is the act of
granting a *permission*. In Liferay, permissions are not granted to directly to
users. Instead, permissions are granted to roles. Roles, in turn, can be
assigned to specific users, sites, organizations, or user groups.

Developers need to define the different types of operations that are required
to suit the business logic of their applications. They don't need to worry
about which users will receive which permissions. Once the actions have been
determined and configured, portal administrators can grant permissions to
perform those actions to users, sites, organizations, or user groups by
assigning roles. Administrators can use the portal's administration tools to
grant permissions to roles or they can use the permissions UIs of individual
portlets.

In this tutorial, you'll learn how to use Liferay's permissions system to
provide Liferay administrators the same level of control over permissions that
they they over the out-of-the-box Liferay applications.

Before proceeding, make sure you understand these critical terms:

**Action**: An operation that can be performed by a Liferay user. For example,
actions that be performed on the Bookmarks application include `ADD_TO_PAGE`,
`CONFIGURATION`, and `VIEW`. Actions that can be performed with respect to
Bookmarks entry entity include `ADD_ENTRY`, `DELETE`, `PERMISSIONS`, `UPDATE`,
and `VIEW`. 

**Resource**: A generic representation of any application or entity in the
portal on which an action can be performed. Resources are used for permission
checking. For example, resources within a Liferay instance could include the
RSS application with instance ID `hF5f`, a globally scoped Wiki page, a
Bookmarks entry of the site X, and a Message Boards post with the ID `5052`.

**Permission**: An action that can be performed on a resource. In Liferay's
database, resources and actions are saved in pairs. (Each entry in the
`ResourceAction` table contains both the name of a portlet or entity and the
name of an action.) For example, the `VIEW` action with respect to *viewing the
Bookmarks application* is associated with the
`com_liferay_bookmarks_web_portlet_BookmarksPortlet` portlet ID. The `VIEW`
actions with respect to *viewing a Bookmarks Folder* or *viewing a Bookmarks
entry* are associated with the `com.liferay.bookmarks.model.BookmarksFolder`
and `com.liferay.bookmarks.model.BookmarksEntry` entities, respectively.

There are two kinds of resources in Liferay: *portlet resources* and *model
resources*. Portlet resources represent portlet applications. The names of
portlet resources typically correspond to the IDs of the portlets themselves.
For example, the fully qualified name of the Bookmarks portlet class is
`com.liferay.bookmarks.web.portlet.BookmarksPortlet`. Its ID is defined in the `BookmarksPortletKeys` class like this:

    public static final String BOOKMARKS =
        "com_liferay_bookmarks_web_portlet_BookmarksPortlet";

This `BOOKMARKS` string is used when declaring portlet resources in
`default.xml` files, as discussed below.

There are two kinds of resources in Liferay: *portlet resources* and *model
resources*. Portlet resources represent portlets. The names of portlet
resources are the portlet IDs from the portlets' `portlet.xml` files (or in the
case of core portlets, Liferay's `portlet-custom.xml`. Model resources
refer to entities within Liferay. The names of model resources are the fully
qualified class names of the entities they represent. In the XML displayed
below, permission implementations are first defined for the *portlet* resource
and then for the *model* resources.

Model resources represent entities within Liferay, such as bookmarks folders or
bookmarks entries. The names of model resources are the fully qualified class
names of the entities they represent. In the `default.xml` files displayed
below, permission implementations are first defined for the *portlet* resource
and then for the *model* resources. 

+$$$

**Note:** For each resource, there are four scopes to which the permissions can
be applied: company, group, group-template, or individual. See the Javadoc of
[ResourcePermissionImpl](@platform-ref@/7.0-latest/javadocs/portal-impl/)
for more information.

$$$

You can add permissions to your custom portlets using four easy steps (also
known as *DRAC*): 

1. <b>D</b>efine all resources and their permissions. This is the step presented 
   in this tutorial.

2. <b>R</b>egister all defined resources in the permissions system. This is also
   known as *adding resources*. This step is discussed in the tutorial 
   [Adding and Deleting Resources (not yet written)]().

3. <b>A</b>ssociate the necessary permissions with resources. This step is 
   discussed in the tutorial [Exposing the Permission Interface to Users (not yet written)]().

4. <b>C</b>heck permission before returning resources. This step is described in 
   the tutorial [Checking Permissions (not yet written)]().

## Define All Resources and Permissions [](id=define-all-resources-and-permissions)

The first step is to define your resources and the actions that can be defined
on them. The Bookmarks application is used here to demonstrate how to define
portlet resources and model resources. Open the `default.xml` file in
`bookmarks-web` module of the Bookmarks application:
[default.xml](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/bookmarks/bookmarks-web/src/main/resources/resource-actions/default.xml).
There, you'll see the following mapping of resources to actions: 

    <?xml version="1.0"?>
    <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

    <resource-action-mapping>
        <portlet-resource>
            <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksAdminPortlet</portlet-name>
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
        <portlet-resource>
            <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksPortlet</portlet-name>
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
    </resource-action-mapping>

This `default.xml` defines portlet resources. The bookmarks service module also
contains a
[default.xml](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/resources/META-INF/resource-actions/default.xml)
file:

    <?xml version="1.0"?>
    <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

    <resource-action-mapping>
        <model-resource>
            <model-name>com.liferay.bookmarks</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksPortlet</portlet-name>
                <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksAdminPortlet</portlet-name>
            </portlet-ref>
            <root>true</root>
            <weight>1</weight>
            <permissions>
                <supports>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>ADD_FOLDER</action-key>
                    <action-key>PERMISSIONS</action-key>
                    <action-key>SUBSCRIBE</action-key>
                    <action-key>VIEW</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>SUBSCRIBE</action-key>
                    <action-key>VIEW</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>VIEW</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>ADD_FOLDER</action-key>
                    <action-key>PERMISSIONS</action-key>
                    <action-key>SUBSCRIBE</action-key>
                </guest-unsupported>
            </permissions>
        </model-resource>
        <model-resource>
            <model-name>com.liferay.bookmarks.model.BookmarksFolder</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksPortlet</portlet-name>
                <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksAdminPortlet</portlet-name>
            </portlet-ref>
            <weight>2</weight>
            <permissions>
                <supports>
                    <action-key>ACCESS</action-key>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>ADD_SUBFOLDER</action-key>
                    <action-key>DELETE</action-key>
                    <action-key>PERMISSIONS</action-key>
                    <action-key>SUBSCRIBE</action-key>
                    <action-key>UPDATE</action-key>
                    <action-key>VIEW</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>SUBSCRIBE</action-key>
                    <action-key>VIEW</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>VIEW</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>ADD_SUBFOLDER</action-key>
                    <action-key>UPDATE</action-key>
                </guest-unsupported>
            </permissions>
        </model-resource>
        <model-resource>
            <model-name>com.liferay.bookmarks.model.BookmarksEntry</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksPortlet</portlet-name>
                <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksAdminPortlet</portlet-name>
            </portlet-ref>
            <weight>3</weight>
            <permissions>
                <supports>
                    <action-key>DELETE</action-key>
                    <action-key>PERMISSIONS</action-key>
                    <action-key>SUBSCRIBE</action-key>
                    <action-key>UPDATE</action-key>
                    <action-key>VIEW</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>SUBSCRIBE</action-key>
                    <action-key>VIEW</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>VIEW</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>SUBSCRIBE</action-key>
                    <action-key>UPDATE</action-key>
                </guest-unsupported>
            </permissions>
        </model-resource>
    </resource-action-mapping>

This `default.xml` file defines model resources.

The `<portlet-resource>` tag is used to define actions that can be taken with
respect to the portlet window. For the Bookmarks application, such actions
include these:

- `ADD_TO_PAGE`: *Add* the application to a page
- `CONFIGURATION`: *Access* the application's Configuration window
- `VIEW`: *View* the application

All the supported actions are defined in the `<supports>` tag, a sub-tag of
the `<permissions>` tag (which is itself a sub-tag of the `<portlet-resource>`
tag:

    <supports>
        <action-key>ADD_TO_PAGE</action-key>
        <action-key>CONFIGURATION</action-key>
        <action-key>VIEW</action-key>
    </supports>

The default permissions for site members are defined in the
`<site-member-defaults>` tag. In the case of the Bookmarks application, site
members can view any Bookmarks application in the site:

    <site-member-defaults>
        <action-key>VIEW</action-key>
    </site-member-defaults>

Similarly, the default permissions for guests are defined in the
`<guest-defaults>` tag. Guests can also view any Bookmarks application in the
site:

    <guest-defaults>
        <action-key>VIEW</action-key>
    </guest-defaults>

The `<guest-unsupported>` tag specifies permissions forbidden to guests. By
default, guests cannot access the Bookmarks application in the Control Panel
nor can they access the Bookmarks's configuration window:

    <guest-unsupported>
        <action-key>ACCESS_IN_CONTROL_PANEL</action-key>
        <action-key>CONFIGURATION</action-key>
    </guest-unsupported>

With respect to model resources, guests are forbidden from adding bookmarks
entries and bookmarks folders. Guests are also not permitted to edit
permissions or subscribe. Remember, these are just default permissions that can
be changed by administrators. See the following entry in the first
`<model-resource>` tag:

    <guest-unsupported>
        <action-key>ADD_ENTRY</action-key>
        <action-key>ADD_FOLDER</action-key>
        <action-key>PERMISSIONS</action-key>
        <action-key>SUBSCRIBE</action-key>
    </guest-unsupported>

The `<model-resource>` tag is used to define actions that can be performed with
respect to models, also known as entities. There are two kinds of actions in
Liferay: *top-level actions* and *resource actions*. Top-level actions are not
applied to a particular resource. For example, the action of adding a new
entity is not applied to a particular resource, so it's considered a top-level
action. The first `<model-resource>` tag defines adding bookmark entry and
bookmark folder resources as top-level actions:

    <supports>
        <action-key>ADD_ENTRY</action-key>
        <action-key>ADD_FOLDER</action-key>
    </supports>

The second and third `<model-resource>` tags define resource actions that can
be applied to the `BookmarksFolder` and `BookmarksEntry` entities,
respectively. For example, the permissions for the following actions are
defined with respect to the resource associated with the `BookmarksEntry` entity:

    <supports>
        <action-key>DELETE</action-key>
        <action-key>PERMISSIONS</action-key>
        <action-key>SUBSCRIBE</action-key>
        <action-key>UPDATE</action-key>
        <action-key>VIEW</action-key>
    </supports>

Similarly, the permissions for the following actions are defined with respect to
the resource associated with the `BookmarksFolder` entity:

    <supports>
        <action-key>ACCESS</action-key>
        <action-key>ADD_ENTRY</action-key>
        <action-key>ADD_SUBFOLDER</action-key>
        <action-key>DELETE</action-key>
        <action-key>PERMISSIONS</action-key>
        <action-key>SUBSCRIBE</action-key>
        <action-key>UPDATE</action-key>
        <action-key>VIEW</action-key>
    </supports>

In each `<model-resource>` tag, notice that the model name must be defined. The
`<model-name>` must be either the fully-qualified name of a package or of an
entity class. For example, `com.liferay.bookmarks` is the name of a package and
`com.liferay.bookmarks.model.BookmarksEntry` is the name of an entity class.
Using a package is the recommended convention for permissions that refer to
top-level actions:

    <model-name>com.liferay.bookmarks</model-name>

The `ADD_ENTRY` and `ADD_FOLDER` permissions are defined this way since they're
top-level actions. For resource actions, the entity class is specified:

    <model-name>com.liferay.bookmarks.model.BookmarksEntry</model-name>

The `<portlet-ref>` element comes next and contains a `<portlet-name>` sub-tag.

    <portlet-ref>
        <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksPortlet</portlet-name>
        <portlet-name>com_liferay_bookmarks_web_portlet_BookmarksAdminPortlet</portlet-name>
    </portlet-ref>

The value of `<portlet-name>` references the name of the portlet to which the
model resource belongs. It's possible for a model resource to belong to
multiple portlets referenced with multiple `<portlet-name>` elements. This is
the case here since both the Bookmarks application and the Bookmarks Admin
application can be used to perform actions on bookmark entries.

The `<supports>`, `<site-member-defaults>`, `<guest-defaults>`, and
`<guest-unsupported>` tags work the same way in the `<model-resource>` tag as
they do in the `<portlet-resource>` tag. The `<supports>` tag lets you specify a
list of supported actions that require permission to perform. The
`<site-member-defaults>` tag and the `<guest-defaults>` tags define default
permissions for site members and guests, respectively. And the
`<guest-unsupported>` tag specifies permissions forbidden to guests.

After defining resource permissions for your portlet, you need to point Liferay
to the `default.xml` file that contains your definitions. In Liferay's core,
there are multiple permissions XML definition files for various core Liferay
portlets in the `portal/portal-impl/src/resource-actions` directory. The
`default.xml` file in that folder contains pointers to the definition files of
the various applications. This excerpt from Liferay's `default.xml` references
the resource permission definition files for all built-in Liferay portlets: 

    <?xml version="1.0"?>
    <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

    <resource-action-mapping>
        <resource file="resource-actions/portal.xml" />
        <resource file="resource-actions/announcements.xml" />
        <resource file="resource-actions/asset.xml" />
        <resource file="resource-actions/blogs.xml" />
        ...
    </resource-action-mapping>

Your application's permissions XML file should be named `default.xml` and
should be placed in a directory in your module's classpath.
`src/resource-actions` is the standard location. Once your project's
`default.xml` file has been created, you should create a properties file named
`portlet.properties` that contains a reference to your permissions XML file. In
your `portlet.properties` file, create a property named
`resource.actions.configs` with the relative path to your portlet's
resource-action mapping file (e.g. `default.xml`) as its value. Here's what
this property specification might look like: 

    resource.actions.configs=resource-actions/default.xml

Your permissions XML file must contain a root `resource-action-mapping` element.
Check out a copy of the Liferay source code from the
[Liferay Portal repository](https://github.com/liferay/liferay-portal)
to see how resources and permissions are defined for core Liferay portlets.
Start by looking at the definition files found in the
`portal-impl/src/resource-actions` directory. For a simple example of defining
permissions in the context of a portlet plugin, check out the
[Liferay Plugins repository](https://github.com/liferay/liferay-plugins)
and examine the portlet `sample-permissions-portlet`.
