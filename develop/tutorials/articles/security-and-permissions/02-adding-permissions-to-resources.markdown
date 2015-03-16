# Adding Permissions to Resources [](id=adding-permissions-to-resources)

Public bulletin boards are great. Anyone can inform others of just about
anything. On the other hand, *anyone* can post just about *anything* on the
bulletin board. Some of this content might not be relevant to the community.
Other content might be inappropriate. Thus, you sometimes need a way to restrict
who can post or access content.

Fortunately, no matter what your portlet does, access to it and to its content
can be controlled with permissions. Read on to learn about Liferay's permissions
system and how add permissions to your application.

## Liferay's Permission System 

Liferay's permission system uses a very flexible mechanism that defines the
actions that a given user can perform within the general context of the portal
or within the context of a specific portlet. Portal and portlet developers break
down the operations which can be performed within the portal or portlet into
distinct *actions*. The act of granting the ability to perform a certain action
to a specific role is the act of granting a *permission*. In Liferay,
permissions are not granted to directly to users. Instead, permissions are
granted to roles. Roles, in turn, can be assigned to specific users, sites,
organizations, or user groups.

Developers need to define the different types of operations that are required to
suit the business logic of their applications. They don't need to worry about
which users will receive which permissions. Once the actions have been
determined and configured, portal administrators can grant permissions to
perform those actions to users, sites, organizations, or user groups by
assigning roles. Administrators can either use the portal's administration tools
to grant permissions to roles or they can use the permissions UIs of individual
portlets.

In this article, you'll learn how a Liferay application developer can use
Liferay's permissions system to provide the portal administrator the same level
of control over permissions that she has over the default Liferay applications. 

Before proceeding, make sure you understand these critical terms: 

- *Action*: An operation that can be performed by a portal user. E.g., actions
  that be performed on the Calendar portlet include `ADD_TO_PAGE`,
  `CONFIGURATION`, and `VIEW`. Actions that can be performed on a Calendar
  entity include `DELETE`, `UPDATE`, and `VIEW`. 

- *Resource*: A generic representation of any portlet or entity in the portal on
  which an action can be performed. Resources are used for permission checking.
  E.g., resources within a Liferay portal instance could include the RSS portlet
  with instance ID `hF5f`, a globally scoped Wiki page, a Calendar event of the
  site X, and the message board post with ID `5052`.

- *Permission*: An action that can be performed on a resource. In Liferay's
  database, resources and actions are saved in pairs. (Each entry in the
  `ResourceAction` table contains both the name of a portlet or entity and the
  name of an action.) For example, the `VIEW` action with respect to *viewing
  the Calendar portlet* is associated with the `1_WAR_calendarportlet` portlet
  ID. The `VIEW` actions with respect to *viewing a Calendar* or *viewing a
  Calendar event* are associated with the `com.liferay.calendar.model.Calendar`
  and `com.liferay.calendar.model.Calendar` entities, respectively.

There are two kinds of resources in Liferay: *portlet resources* and *model
resources*. Portlet resources represents portlets. The names of portlet
resources are the numeric IDs of the portlets they represent. Model resources
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

1. **D**efine all resources and their permissions. This is the step presented 
   in this tutorial.

2. **R**egister all defined resources in the permissions system. This is also
   known as *adding resources*. This step is discussed in the tutorial 
   [Adding and Deleting Resources](/tutorials/-/knowledge_base/adding-and-deleting-resources).

3. **A**ssociate the necessary permissions with resources. This step is 
   discussed in the tutorial [Exposing the Permission Interface to Users](/tutorials/-/knowledge_base/exposing-the-permission-interface-to-users).

4. **C**heck permission before returning resources. This step is described in 
   the tutorial [Checking Permissions](/tutorials/-/knowledge_base/checking-permissions).
   
## Define All Resources and Permissions

The first step is to define your resources and the actions that can be defined
on them. The Calendar portlet is used here to demonstrate how to define portlet
resources and model resources. Open the `default.xml` file in the Calendar
portlet's `docroot/WEB-INF/src/resource-actions` directory and you'll see the
following mapping of resources to actions: 

    <?xml version="1.0"?>
    <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.2.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_2_0.dtd">

    <resource-action-mapping>
        <portlet-resource>
            <portlet-name>1</portlet-name>
            <permissions>
                <supports>
                    <action-key>ACCESS_IN_CONTROL_PANEL</action-key>
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
                    <action-key>ACCESS_IN_CONTROL_PANEL</action-key>
                    <action-key>ADD_TO_PAGE</action-key>
                    <action-key>CONFIGURATION</action-key>
                </guest-unsupported>
            </permissions>
        </portlet-resource>
        <model-resource>
            <model-name>com.liferay.calendar</model-name>
            <portlet-ref>
                <portlet-name>1</portlet-name>
            </portlet-ref>
            <permissions>
                <supports>
                    <action-key>ADD_RESOURCE</action-key>
                    <action-key>PERMISSIONS</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>ADD_RESOURCE</action-key>
                </site-member-defaults>
                <guest-defaults />
                <guest-unsupported>
                    <action-key>ADD_RESOURCE</action-key>
                    <action-key>PERMISSIONS</action-key>
                </guest-unsupported>
            </permissions>
        </model-resource>
        <model-resource>
            <model-name>com.liferay.calendar.model.Calendar</model-name>
            <portlet-ref>
                <portlet-name>1</portlet-name>
            </portlet-ref>
            <permissions>
                <supports>
                    <action-key>DELETE</action-key>
                    <action-key>MANAGE_BOOKINGS</action-key>
                    <action-key>PERMISSIONS</action-key>
                    <action-key>UPDATE</action-key>
                    <action-key>VIEW</action-key>
                    <action-key>VIEW_BOOKING_DETAILS</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>VIEW</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>VIEW</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>DELETE</action-key>
                    <action-key>MANAGE_BOOKINGS</action-key>
                    <action-key>PERMISSIONS</action-key>
                    <action-key>UPDATE</action-key>
                </guest-unsupported>
            </permissions>
        </model-resource>
        <model-resource>
            <model-name>com.liferay.calendar.model.CalendarBooking</model-name>
            <portlet-ref>
                <portlet-name>1</portlet-name>
            </portlet-ref>
            <permissions>
                <supports>
                    <action-key>ADD_DISCUSSION</action-key>
                    <action-key>DELETE_DISCUSSION</action-key>
                    <action-key>PERMISSIONS</action-key>
                    <action-key>UPDATE_DISCUSSION</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>ADD_DISCUSSION</action-key>
                </site-member-defaults>
                <guest-defaults />
                <guest-unsupported>
                    <action-key>DELETE_DISCUSSION</action-key>
                    <action-key>UPDATE_DISCUSSION</action-key>
                </guest-unsupported>
            </permissions>
        </model-resource>
        <model-resource>
            <model-name>com.liferay.calendar.model.CalendarResource</model-name>
            <portlet-ref>
                <portlet-name>1</portlet-name>
            </portlet-ref>
            <permissions>
                <supports>
                    <action-key>ADD_CALENDAR</action-key>
                    <action-key>DELETE</action-key>
                    <action-key>UPDATE</action-key>
                    <action-key>VIEW</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>UPDATE</action-key>
                    <action-key>VIEW</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>VIEW</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>ADD_CALENDAR</action-key>
                    <action-key>DELETE</action-key>
                    <action-key>UPDATE</action-key>
                </guest-unsupported>
            </permissions>
        </model-resource>
    </resource-action-mapping>

The `<portlet-resource>` tag is used to define actions that can be taken with
respect to the Calendar portlet window. Such actions include the following
ones:

- *Access* the portlet in the Control Panel
- *Add* the portlet to a page
- *View* the portlet
- *Access* the portlet's Configuration window

All of the supported actions are defined in the `<supports>` tag for the portlet
resource's permissions. The default portlet-level permissions for members of the
site are defined in the `<site-member-defaults>` tag. In the case of the
Calendar portlet, site members can view any calendar in the site. Similarly,
default guest permissions are defined in the `<guest-defaults>` tag. Guests can
also view any Calendar in the site. The `<guest-unsupported>` tag specifies
permissions forbidden to guests. For the Calendar portlet, guests can't be given
permission to access the portlet in the Control Panel, add the portlet to a
page, or configure the portlet. 

The `<model-resource>` tag is used to define actions that can be performed with
respect to models, a.k.a. entities. There are two kinds of such actions in
Liferay: *top-level actions* and *resource actions*. Top-level actions are not
applied to a particular resource. For example, the action of adding a new entity
is not applied to a particular resource so it's considered a top-level action.
The first `<model-resource>` tag (the one that has the
`<model-name>com.liferay.calendar</model-name>` inner tag) defines adding a
Calendar resource as a top-level action. (Note: A Calendar resource represents
something that can be used when booking Calendar events. E.g., a Calendar
resource could be a room or a projector. A Calendar resource is a completely
separate concept from a permissions resource.) The second through fourth
`<model-resource>` tags define resource actions that can be applied to the
`Calendar`, `CalendarBooking`, and `CalendarResource` entities. E.g., the
`DELETE`, `UPDATE`, and `VIEW` permissions are defined with respect to the
resource associated with the `Calendar` entity. The `ADD_DISCUSSION`,
`DELETE_DISCUSSION`, and `UPDATE_DISCUSSION` permissions are defined with
respect to the resource associated with the `CalendarBooking` entity. And the
`DELETE`, `UPDATE`, and `VIEW` permissions are defined with respect to the
resource associated with the `CalendarResource` entity.

Inside of each `<model-resource>` tag, notice that the model name must be
defined. The `<model-name>` must be either the fully-qualified name of a package
or of an entity class. E.g. `com.liferay.calendar` is the name of a package and
`com.liferay.calendar.model.Calendar` is the name of an entity class. Using a
package is the recommended convention for permissions that refer to top-level
actions. The `ADD_RESOURCE` permission is defined this way since it's a
top-level action. The `<portlet-ref>` element comes next and contains a
`<portlet-name>`. The value of `<portlet-name>` references the name of the
portlet to which the model resource belongs. It's possible for a model resource
to belong to multiple portlets referenced with multiple `<portlet-name>`
elements but this is uncommon. Like a portlet resource, a model resource lets
you define a list of supported actions that require permission to perform. You
must list all the performable actions that require a permission check. For a
calendar, users must belong to appropriate roles in order to have permission to
do the following: 

- *Delete* a calendar
- *Manage the bookings* of a calendar 
- *Manage the permissions* of a calendar
- *Update* a calendar 
- *View* a calendar 
- *View the bookings (events)* of a calendar

For *model resources*, as for portlet resources, the `<site-member-defaults>`
tag and the `<guest-defaults>` tags define default permissions for site members
and guests, respectively. And the `<guest-unsupported>` tag specifies
permissions forbidden to guests.

After defining resource permissions for your custom portlet, you need to point
Liferay to the `resource-actions` XML file that contains your definitions. This
is `docroot/WEB-INF/src/resource-actions/default.xml` for the Calendar portlet.
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

## Related Topics

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Plugin Security and PACL](/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/6-2/liferay-ide)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)
