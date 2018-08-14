# Adding Permissions to Resources [](id=adding-permissions-to-resources)

Public bulletin boards are great. Anyone can inform others of just about
anything. On the other hand, *anyone* can post just about *anything* on the
bulletin board. Some of this content might not be relevant to the community.
Other content might be inappropriate. Thus, you sometimes need a way to restrict
who can post or access content.

Fortunately, no matter what your portlet does, access to it and to its content
can be controlled with permissions. Read on to learn about Liferay's permissions
system and how add permissions to your application.

## Liferay's Permission System [](id=liferays-permission-system)

Liferay's permission system uses a flexible mechanism that defines the actions
that a given user can perform within the context of the portal or a specific
portlet. Portal and portlet developers break down the operations that can be
performed in the portal or portlet into distinct *actions*. The act of granting
the ability to perform an action to a specific role is the act of granting a
*permission*. In Liferay, permissions are not granted to directly to users.
Instead, permissions are granted to roles. Roles, in turn, can be assigned to
specific users, sites, organizations, or user groups.

Developers need to define the different types of operations that are required to
suit the business logic of their applications. They don't need to worry about
which users will receive which permissions. Once the actions have been
determined and configured, portal administrators can grant permissions to
perform those actions to users, sites, organizations, or user groups by
assigning roles. Administrators can use the portal's administration tools to
grant permissions to roles or they can use the permissions UIs of individual
portlets.

In this article, you'll learn how a Liferay application developer can use
Liferay's permissions system to provide the portal administrator the same level
of control over permissions that she has over the default Liferay applications. 

Before proceeding, make sure you understand these critical terms: 

**Action**: An operation that can be performed by a portal user. For example,
actions that be performed on the Guestbook portlet include `ADD_TO_PAGE`,
`CONFIGURATION`, and `VIEW`. Actions that can be performed on a Guestbook
entity include `ADD_ENTRY`, `DELETE`, `PERMISSIONS`, `UPDATE`, and `VIEW`. 

**Resource**: A generic representation of any portlet or entity in the portal
on which an action can be performed. Resources are used for permission
checking.  For example, resources within a Liferay portal instance could include
the RSS portlet with instance ID `hF5f`, a globally scoped Wiki page, a
Guestbook entry of the site X, and the Message Boards post with ID `5052`.

**Permission**: An action that can be performed on a resource. In Liferay's
database, resources and actions are saved in pairs. (Each entry in the
`ResourceAction` table contains both the name of a portlet or entity and the
name of an action.) For example, the `VIEW` action with respect to *viewing
the Guestbook portlet* is associated with the `guestbook_WAR_guestbookportlet`
portlet ID. The `VIEW` actions with respect to *viewing a Guestbook* or
*viewing a Guestbook entry* are associated with the
`com.liferay.docs.guestbook.model.Guestbook` and
`com.liferay.docs.guestbook.model.Entry` entities, respectively.

There are two kinds of resources in Liferay: *portlet resources* and *model
resources*. Portlet resources represent portlets. The names of portlet
resources are the portlet IDs from the portlets' `portlet.xml` files (or in the
case of core portlets, Liferay's `portlet-custom.xml`. Model resources
refer to entities within Liferay. The names of model resources are the fully
qualified class names of the entities they represent. In the XML displayed
below, permission implementations are first defined for the *portlet* resource
and then for the *model* resources. 

+$$$

**Note:** For each resource, there are four scopes to which the permissions can
be applied: company, group, group-template, or individual. See the Javadoc of
[ResourcePermissionImpl](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portal/model/impl/ResourcePermissionImpl.java)
for more information.

$$$

You can add permissions to your custom portlets using four easy steps (also
known as *DRAC*): 

1. <b>D</b>efine all resources and their permissions. This is the step presented 
   in this tutorial.

2. <b>R</b>egister all defined resources in the permissions system. This is also
   known as *adding resources*. This step is discussed in the tutorial 
   [Adding and Deleting Resources](/develop/tutorials/-/knowledge_base/6-2/adding-and-deleting-resources).

3. <b>A</b>ssociate the necessary permissions with resources. This step is 
   discussed in the tutorial [Exposing the Permission Interface to Users](/develop/tutorials/-/knowledge_base/6-2/exposing-the-permission-interface-to-users).

4. <b>C</b>heck permission before returning resources. This step is described in 
   the tutorial [Checking Permissions](/develop/tutorials/-/knowledge_base/6-2/checking-permissions).
 
## Define All Resources and Permissions [](id=define-all-resources-and-permissions)

The first step is to define your resources and the actions that can be defined
on them. The Guestbook portlet is used here to demonstrate how to define portlet
resources and model resources. Open the `default.xml` file in the Guestbook
portlet's `docroot/WEB-INF/src/resource-actions` directory and you'll see the
following mapping of resources to actions: 

    <?xml version="1.0"?>
    <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.2.0//EN"
    "http://www.liferay.com/dtd/liferay-resource-action- mapping_6_2_0.dtd">

    <resource-action-mapping>
        <portlet-resource>
            <portlet-name>guestbook</portlet-name>
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
                <guest-unsupported />
            </permissions>
        </portlet-resource>

        <model-resource>
            <model-name>com.liferay.docs.guestbook.model</model-name>
            <portlet-ref>
                <portlet-name>guestbook</portlet-name>
            </portlet-ref>
            <permissions>
            <supports>
                <action-key>ADD_GUESTBOOK</action-key>
                <action-key>ADD_ENTRY</action-key>
            </supports>
            <site-member-defaults>
                <action-key>ADD_ENTRY</action-key>
            </site-member-defaults>
            <guest-defaults />
            <guest-unsupported>
                <action-key>ADD_GUESTBOOK</action-key>
                <action-key>ADD_ENTRY</action-key>
            </guest-unsupported>
            </permissions>
        </model-resource>

        <model-resource>
            <model-name>com.liferay.docs.guestbook.model.Guestbook</model-name>
            <portlet-ref>
                <portlet-name>guestbook</portlet-name>
            </portlet-ref>
            <permissions>
            <supports>
                <action-key>ADD_ENTRY</action-key>
                <action-key>DELETE</action-key>
                <action-key>PERMISSIONS</action-key>
                <action-key>UPDATE</action-key>
                <action-key>VIEW</action-key>
            </supports>
            <site-member-defaults>
                <action-key>ADD_ENTRY</action-key>
                <action-key>VIEW</action-key>
            </site-member-defaults>
            <guest-defaults>
                <action-key>VIEW</action-key>
            </guest-defaults>
            <guest-unsupported>
                <action-key>UPDATE</action-key>
            </guest-unsupported>
            </permissions>
        </model-resource>

        <model-resource>
            <model-name>com.liferay.docs.guestbook.model.Entry</model-name>
            <portlet-ref>
                <portlet-name>guestbook</portlet-name>
            </portlet-ref>
            <permissions>
            <supports>
                <action-key>DELETE</action-key>
                <action-key>PERMISSIONS</action-key>
                <action-key>UPDATE</action-key>
                <action-key>VIEW</action-key>
            </supports>
            <site-member-defaults>
                <action-key>VIEW</action-key>
            </site-member-defaults>
            <guest-defaults>
                <action-key>VIEW</action-key>
            </guest-defaults>
            <guest-unsupported>
                <action-key>UPDATE</action-key>
            </guest-unsupported>
            </permissions>
        </model-resource>
    </resource-action-mapping>

The `<portlet-resource>` tag is used to define actions that can be taken with
respect to the portlet window. For the Guestbook portlet, such actions include
these: 

- `ADD_TO_PAGE`: *Add* the portlet to a page
- `CONFIGURATION`: *Access* the portlet's Configuration window
- `VIEW`: *View* the portlet

All the supported actions are defined in the `<supports>` tag, a sub-tag of
the `<permissions>` tag (which is itself a sub-tag of the `<portlet-resource>`
tag:

    <supports>
        <action-key>ADD_TO_PAGE</action-key>
        <action-key>CONFIGURATION</action-key>
        <action-key>VIEW</action-key>
    </supports>

The default permissions for site members are defined in the
`<site-member-defaults>` tag. In the case of the Guestbook portlet, site members
can view any Guestbook portlet in the site:

    <site-member-defaults>
        <action-key>VIEW</action-key>
    </site-member-defaults>

Similarly, the default permissions for guests are defined in the
`<guest-defaults>` tag. Guests can also view any Guestbook portlet in the site:

    <guest-defaults>
        <action-key>VIEW</action-key>
    </guest-defaults>

The `<guest-unsupported>` tag specifies permissions forbidden to guests. There
aren't any forbidden Guestbook portlet for guests:

    <guest-unsupported />

However, guests are forbidden from adding guestbooks and guestbook entries, for
example, see the following entry in the first `<model-resource>` tag:

    <guest-unsupported>
        <action-key>ADD_GUESTBOOK</action-key>
        <action-key>ADD_ENTRY</action-key>
    </guest-unsupported>

The `<model-resource>` tag is used to define actions that can be performed with
respect to models, also known as entities. There are two kinds of actions in
Liferay: *top-level actions* and *resource actions*. Top-level actions are not
applied to a particular resource. For example, the action of adding a new entity
is not applied to a particular resource, so it's considered a top-level action.
The first `<model-resource>` tag defines adding guestbook and guestbook entry
resources as top-level actions:

    <supports>
        <action-key>ADD_GUESTBOOK</action-key>
        <action-key>ADD_ENTRY</action-key>
    </supports>

The second and third `<model-resource>` tags define resource actions that can be
applied to the `Guestbook`, and `GuestbookEntry` entities, respectively. For
example, the permissions for the following actions are defined with respect to the
resource associated with the `Guestbook` entity:

    <supports>
        <action-key>ADD_ENTRY</action-key>
        <action-key>DELETE</action-key>
        <action-key>PERMISSIONS</action-key>
        <action-key>UPDATE</action-key>
        <action-key>VIEW</action-key>
    </supports>

Similarly, the permissions for the following actions are defined with respect to
the resource associated with the `GuestbookEntry` entity:

    <supports>
        <action-key>DELETE</action-key>
        <action-key>PERMISSIONS</action-key>
        <action-key>UPDATE</action-key>
        <action-key>VIEW</action-key>
    </supports>

In each `<model-resource>` tag, notice that the model name must be defined. The
`<model-name>` must be either the fully-qualified name of a package or of an
entity class. For example, `com.liferay.docs.guestbook.model` is the name of a
package and `com.liferay.docs.guestbook.model.Guestbook` is the name of an
entity class.  Using a package is the recommended convention for permissions
that refer to top-level actions:

    <model-name>com.liferay.docs.guestbook.model</model-name>

The `ADD_GUESTBOOK` and `ADD_ENTRY` permissions are defined this way since
they're top-level actions. For resource actions, the entity class is specified:

    <model-name>com.liferay.docs.guestbook.model.Guestbook</model-name>

The `<portlet-ref>` element comes next and contains a `<portlet-name>` sub-tag.

    <portlet-ref>
        <portlet-name>guestbook</portlet-name>
    </portlet-ref>

The value of `<portlet-name>` references the name of the portlet to which the
model resource belongs. It's possible for a model resource to belong to multiple
portlets referenced with multiple `<portlet-name>` elements, but this is
uncommon.

The `<supports>`, `<site-member-defaults>`, `<guest-defaults>`, and
`<guest-unsupported>` tags work the same way in the `<model-resource>` tag as
they do in the `<portlet-resource>` tag. The `<supports>` tag lets you specify a
list of supported actions that require permission to perform. The
`<site-member-defaults>` tag and the `<guest-defaults>` tags define default
permissions for site members and guests, respectively. And the
`<guest-unsupported>` tag specifies permissions forbidden to guests.

After defining resource permissions for your portlet, you need to point Liferay
to the `resource-actions` XML file that contains your definitions. This is
`docroot/WEB-INF/src/resource-actions/default.xml` for the Guestbook project.
In Liferay's core, there are multiple permissions XML definition files for
various core Liferay portlets in the `portal/portal-impl/src/resource-actions`
directory. The `default.xml` file contains pointers to the definition files of
the various applications. This excerpt from `default.xml` references the
resource permission definition files for all built-in Liferay portlets: 

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

Your plugin's permissions XML file should be named `default.xml` and should be
placed in a directory in your project's classpath.
`docroot/WEB-INF/src/resource-actions` is the standard location. Once your
project's `default.xml` file has been created, you should create a properties
file named `portlet.properties` that contains a reference to your permissions
XML file. In your `portlet.properties` file, create a property named
`resource.actions.configs` with the relative path to your portlet's
resource-action mapping file (e.g. `default.xml`) as its value. Here's what this
property specification might look like: 

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

## Related Topics [](id=related-topics)

[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Plugin Security and PACL](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)
