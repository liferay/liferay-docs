# Adding Permissions to Resources [](id=adding-permissions-to-resources)

Public bulletin boards are great. Anyone can inform others of just about 
anything. On the other hand, *anyone* can post just about *anything* on the 
bulletin board. Some of this content may not be relevant to the community, while 
other content may even be lewd or inappropriate. Thus, you sometimes need a way 
to restrict who can post or access content.

Fortunately, no matter what your portlet does, access to its content can be 
controlled with permissions. This tutorial begins by describing Liferay's 
permissions system. Then, the first step of adding permissions to your portlet 
is described. Go ahead and get started learning about permissions!

## Liferay's Permission System 

Before you start adding permissions to a portlet, make sure you understand
these two critical terms used throughout this tutorial: 

- *Resource*: A generic term for any object represented in the portal.
  Examples of resources include portlets (e.g. Message Boards, Calendar, etc.),
Java classes (e.g. Message Board Topics, Calendar Events, etc.), and files
(e.g.  documents, images, etc.). 

- *Permission*: An action on a resource. For example, the *view* action with
respect to *viewing the calendar portlet* is defined as a permission in Liferay. 

It's important to know that permissions for *portlet* resources are implemented
a little differently than for other resources like Java classes and files.
In the next section below, permission implementation is described for the 
*portlet* resource first, followed by the model resource. 

You can add permissions to your custom portlets using four easy steps (also
known as DRAC): 

1. **D**efine all resources and their permissions. This is the step presented 
   in this tutorial.

2. **R**egister all defined resources in the permissions system. This is also
   known as *adding resources*. This step is discussed in the tutorial 
   [Adding and Deleting Resources](/tutorials/-/knowledge_base/adding-and-deleting-resources-lp-6-2-develop-tutorial).

3. **A**ssociate the necessary permissions with resources. This step is 
   discussed in the tutorial [Exposing the Permission Interface to Users](/develop/tutorials/-/knowledge_base/exposing-the-permission-interface-lp-6-2-develop-tutorial).

4. **C**heck permission before returning resources. This step is described in 
   the tutorial [Checking Permissions](/develop/tutorials/-/knowledge_base/checking-permissions-lp-6-2-develop-tutorial).

<!-- Links need changed once header id's are generated for those tutorials -->
   
<!-- We definitely should not be using the Blogs portlet to illustrate this, as
the patterns are different from what plugin developers would do. For example, in
a plugin, the convention is to create default.xml in the resource-actions folder
of a plugin, and that's what developers reading this will do. We should find
another portlet to serve as our example portlet. -Rich -->

## Define All Resources and Permissions

The first step is to define your resources and the actions that can be defined
on them. The Blogs portlet is used here to demonstrate. Open the `blogs.xml` 
file in `portal-impl/src/resource-actions` and you'll see the following mapping 
of resources to actions: 

	<?xml version="1.0"?>
	<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.2.0//EN" 
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
			<root>true</root>
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

After defining the portlet and portlet instance as resources, you need to define
permissions on the models in the portlet. The model resource is surrounded by
the `<model-resource>` tag. Inside the tag, first define the model name; the
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
portlet that defines its resources and permissions as just described; start
by looking at the definition files found in the
`portal-impl/src/resource-actions` directory. For an example of defining
permissions in the context of a portlet plugin, check out `plugins/trunk` and
look at the portlet `sample-permissions-portlet`.

## Related Topics

[Customizing Liferay Portal](/tutorials/-/knowledge_base/customizing-liferay-portal)

[Plugin Security and PACL](/tutorials/-/knowledge_base/plugin-security-and-pacl)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/liferay-ide)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/alloyui)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/liferay-ui-taglibs)
