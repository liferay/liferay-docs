# Defining Resources and Permissions

Your first step in implementing permissions is to define the resources and the
permissions that protect them. The first thing to know is that there are two
different kinds of resources: *portlet resources* and *model resources*. 

Portlet resources represent portlets. The names of portlet resources are the
portlet IDs from the portlets' `portlet.xml` files (or in the case of core
portlets, Liferay's `portlet-custom.xml`. Model resources refer to entities
within Liferay. The names of model resources are the fully qualified class names
of the entities they represent. In the XML displayed below, permission
implementations are first defined for the *portlet* resource and then for the
*model* resources.

Model resources represent entities, such as blog entries. Resources are named
using the fully qualified class names of the entities they represent. 

+$$$

**Note:** For each resource, there are four scopes to which the permissions can
be applied: company, group, group-template, or individual. See
[`ResourcePermissionImpl`](@platform-ref@/7.0-latest/javadocs/portal-impl/)'s
Javadoc for more information.

$$$

You define resources and their permissions using an XML file. By convention,
this file is called `default.xml` and exists in a module's
`src/main/resources/resource-actions` folder. 

Because of the two different types of resources, you'll have two of these files:
one in your portlet module to define the portlet resources and one in your
service module to define the model resources. 

## Defining Portlet Resource Permissions

Define the portlet resources first; here's an example using Liferay's Blogs
application. 

1.  Start with the DTD declaration: 

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

2.  The root tag contains all the resources to be declared: 

        <resource-action-mapping>

        </resource-action-mapping> 

3.  Inside these tags, define your resources. The Blogs application defines two
    portlet resources: 

        <portlet-resource>
            <portlet-name>com_liferay_blogs_web_portlet_BlogsAdminPortlet</portlet-name>
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
            <portlet-name>com_liferay_blogs_web_portlet_BlogsPortlet</portlet-name>
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

The Blogs application comprises two portlets: the Blogs portlet itself and
the Blogs Admin portlet that appears in the Site menu for administrators.
Define your portlets by their names, and then list the permissions for the
portlet. The Blogs portlet, for example, supports four permissions:
ADD_PORTLET_DISPLAY_TEMPLATE, ADD_TO_PAGE, CONFIGURATION, and VIEW. The Blogs
Admin portlet has an additional permission: ACCESS_IN_CONTROL_PANEL, which
defines who can see the entry in the Site menu. 

Once you've listed the permissions you're defining at the portlet level for your
application, you can define default permissions different types of users should
have. The DTD allows for site member and guest defaults. Since guests are users
that aren't logged in, there's also a `guest-unsupported` tag for defining
permissions guests can *never* have (in other words, the user must be logged in
and identifiable). 

That's all there is to it! Your next task is to define permissions for your
model resources. 

## Defining Model Resource Permissions

Defining permissions for models is a similar process. Create a `default.xml`
file in your service module's `src/main/resources/resource-actions` folder. In
this file, you must define top-level function permissions and individual entity
permissions using the same `<model-resource>` tag. 

This can be confusing, so some explanation is in order. Model permissions for
what Liferay calls the *root model* are defined separately from permissions on
stored entities, which Liferay calls the *model*. This makes sense when you
think about the functions you're allowing users to do: 

- Creating something new 
- Editing something that exists

Creating something new (like adding a new Blog entry) is different from having
access to something that exists. A Blog owner should be able to create a new
Blog entry or edit an existing one, but a User or guest should have read
permission for existing entries and no permission to create them. 

Permission to create something new that doesn't yet exist is a *root model*
permission, whether that functionality is exposed in a portlet or not.
Permission on an existing resource is a *model* permission. 

Now you're ready to define both your root model and model permissions. 

1.  First, create the skeleton for your file: 

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

        <resource-action-mapping>

        </resource-action-mapping> 

2.  Inside the `<resource-action-mapping>` tags, use a `<model-resource>` tag to
    define permissions for the root model:

        <model-resource>
            <model-name>com.liferay.blogs</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_blogs_web_portlet_BlogsAdminPortlet</portlet-name>
                <portlet-name>com_liferay_blogs_web_portlet_BlogsPortlet</portlet-name>
            </portlet-ref>
            <root>true</root>
            <weight>1</weight>
            <permissions>
                <supports>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>PERMISSIONS</action-key>
                    <action-key>SUBSCRIBE</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>SUBSCRIBE</action-key>
                </site-member-defaults>
                <guest-defaults />
                <guest-unsupported>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>PERMISSIONS</action-key>
                    <action-key>SUBSCRIBE</action-key>
                </guest-unsupported>
            </permissions>
        </model-resource>

    The model name (`com.liferay.blogs`) is just a package name. The
    `<root>true</root>` tag defines this as a root model. The `<weight>` tag
    defines the order these permissions are displayed in the GUI. The
    permissions defined are ADD_ENTRY (add a Blog entry), PERMISSIONS (set
    permissions on Blog entries), and SUBSCRIBE (receive notifications when Blog
    entries are created). These are all root model permissions, because no
    primary key in the database can be assigned to any of these functions. 

3.  Finally, define your model permissions: 

        <model-resource>
            <model-name>com.liferay.blogs.model.BlogsEntry</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_blogs_web_portlet_BlogsAdminPortlet</portlet-name>
                <portlet-name>com_liferay_blogs_web_portlet_BlogsPortlet</portlet-name>
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

    Note the lack of a `<root>` tag, the fully qualified class name for the
    model, and the permissions that operate on an entity with a primary key. 

## Pointing @product@ to Your Permissions Configuration

Your last step is to point @product@ at your permission definitions. Each module
that contains a `default.xml` permissions definition file must also have
a `portlet.properties` file with a property that defines where to find the
permissions definition file. For your service and your web modules, then create
a `portlet.properties` file in `src/main/resources` and make sure it has this
property: 

    resource.actions.config=resource-actions/default.xml

Once you've defined portlet permissions, root model permissions, and model
permissions, you've completed step 1 (the *D* in DRAC). Congratulations! You're
now ready to *register* the resources you've now defined in the permissions
system. 
