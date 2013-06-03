
# Liferay Frameworks 

A framework, in the context of an API, is a set of APIs and configuration
designed for a specific purpose. Liferay provides you several essential
frameworks you can use to enhance your applications, adding things like
permission systems, tags, categories, comments, and more. 

This chapter will evolve as we add information about the existing APIs and
frameworks and how they're used. So don't be a stranger, keep checking back. 

These are the frameworks this chapter's current iteration covers: 

- Service Builder 

- Security and Permissions 

- Asset Framework 

- File Storage Framework 

- Other Frameworks 

## Service Builder 

Wouldn't it be nice if Liferay gave you a tool that *automagically* generated
most of your common code for implementing *find*, *create*, *update*, and
*delete* operations on the database? If we did, we'd call it Service Builder!
In fact, we do! Service Builder is a model-driven code generation tool we built
by Liferay to automate the creation of interfaces and classes for database
persistence and a service layer. Service Builder lets you focus on the higher
level aspects of service design. 

Service Builder generates a service layer with an implementation class that
retrieves data classes, stores them, and adds the necessary business logic
around them. Your service layer can have two layers itself; a local service and
a remote service layer. The local service contains the business logic and
accesses the persistence layer. You can invoke it with client code running in
the same Java Virtual Machine. The remote service usually has code to check
security, and it's meant to be accessible both from over the Internet or on
your local network. Service Builder automatically generates the code to allow
access to the remote services using SOAP, JSON and Java RMI. 

### Define the Model 

When you start using Service Builder, your first order of business is to define
your model classes and their attributes in a `service.xml` file. For
convenience, we'll define the service within the *my-greeting* portlet,
although it should really be placed inside a new portlet. Create a file named
`service.xml` in `portlets/my-greeting-portlet/docroot/WEB-INF` in the Plugins
SDK and add the following content: 

    <?xml version="1.0"?> <!DOCTYPE service-builder PUBLIC "-//Liferay//DTD
    Service Builder 6.1.0//EN"
    "http://www.liferay.com/dtd/liferay-service-builder_6_1_0.dtd">
    <service-builder package-path="com.sample.portlet.library">
        <namespace>Library</namespace> <entity name="Book" local-service="true" remote-service="true">

            <!-- PK fields -->

            <column name="bookId" type="long" primary="true" />

            <!-- Group instance -->

            <column name="groupId" type="long" />

            <!-- Audit fields -->

            <column name="companyId" type="long" /> 
            <column name="userId" type="long" /> 
            <column name="userName" type="String" /> 
            <column name="createDate" type="Date" /> 
            <column name="modifiedDate" type="Date" />

            <!-- Other fields -->

            <column name="title" type="String" /> </entity> </service-builder>

Let's look a little more closely at the contents of our `service.xml` file: 

- `<service-builder package-path="com.sample.portlet.library">`: Here we
  specified the package path where the class will be generated. In this
example, classes will generate to `WEB-INF/src/com/sample/portlet/library/`. 

- `<namespace>Library</namespace>`: The `namespace` element must be a unique
  namespace for this component. Table names will be prepended with this value. 

- `<entity name="Book" local-service="true" remote-service="false">`: The
  `entity name` is the database table you want to create. 

- `<column name="title" type="String" />`: Columns specified in `service.xml`
  will be created in the database with an appropriate data type for the
specified Java type. Accessors in the model class will automatically be
generated for these attributes. 

---

![tip](../../images/tip-pen-paper.png)**Tip:** We recommend you always add two
`long` fields called *groupId* and *companyId* to your data models. They let
your portlet support the multi-tenancy features of Liferay so that each
organization (for each portal instance) can have its own data. 

---

Now that you have your `service.xml` file, let's generate the service.
 
### Generate the Service 

Now let's use our `service.xml` to build a service. You can use Liferay
Developer Studio or your terminal to build your service layer. First let's do it
using Developer Studio. 

#### Using Developer Studio to Generate a Service 

From the *Package Explorer*, open your `service.xml` file; it should be in your
`my-greeting-portlet/docroot/WEB-INF` folder. By default, the file opens up in
the *Service Builder Editor*. Make sure you're in *Overview* mode, then select
*Build Services*. 

![Figure 10.1: *Overview* mode in editor](../../images/10-frameworks-1.png)

You should see a BUILD SUCCESSFUL message in your console, along with a list of
generated files. Don't worry about these files for now; we'll talk about them
later in this section. 

You just used the *Overview* mode in Developer Studio's *Service Builder
Editor* to build services; it can also build web service deployment descriptors
(WSDDs). *Service Builder Editor* also has *Diagram* and *Source* modes. If you
select *Diagram*, you're given a graph structured background; the *Palette* on
the right hand side of the editor lets you add entities and relationships.
*Source* mode lets you edit the XML source directly. 

With Developer Studio, you have lots of tools to help automate the creation of
interfaces and classes for your database persistence and service layers. 

Next let's generate a service from the terminal. 

#### Using the Terminal to Generate a Service 

To generate a service in your terminal window, navigate to your
`portlets/my-greeting-portlet` directory and enter this command: 

        ant build-service

A BUILD SUCCESSFUL message indicates that the service was generated
successfully, and you should see that a large number of files have been
generated. 

Next let's look at the files generated when we built the service.

#### Generated files 

Whenever you build a service, there are a number of files automatically
generated for you. Let's look at them briefly: 

- Persistence

    - `BookPersistence`: book persistence interface `@generated`

    - `BookPersistenceImpl`: book persistence `@generated`

    - `BookUtil`: book persistence util, instances BookPersistenceImpl
      `@generated`

- Local Service

    - *`BookLocalServiceImpl`*: local service implementation. This is the only
      class within the local service that you can modify manually. Your
business logic will be here. 

    - `BookLocalService`: local service interface `@generated`

    - `BookLocalServiceBaseImpl`: local service base `@generated` `@abstract`

    - `BookLocalServiceUtil`: local service util, instances
      `BookLocalServiceImpl` `@generated`

    - `BookLocalServiceWrapper`: local service wrapper, wraps
      `BookLocalServiceImpl` `@generated`

- Remote Service

    - *`BookServiceImpl`*: remove service implementation. This is where you
      should put the code that adds additional security checks and invokes the
local service. 

    - `BookService`: remote service interface `@generated`

    - `BookServiceBaseImpl`: remote service base `@generated` `@abstract`

    - `BookServiceUtil`: remote service util, instances `BookServiceImpl`
      `@generated`

    - `BookServiceWrapper`: remote service wrapper, wraps `BookServiceImpl`
      `@generated`

    - `BookServiceSoap`: soap remote service, proxies `BookServiceUtil`
      `@generated`

    - `BookSoap`: soap book model, similar to `BookModelImpl`, does not
      implement Book `@generated`

    - `BookServiceHttp`: http remote service, proxies `BookServiceUtil`
      `@generated`

- Model

    - `BookModel`: book base model interface `@generated`

    - `BookModelImpl`: book base model `@generated`

    - `Book`: book model interface `@generated`

    - *`BookImpl`*: book model implementation. You can use this class to add
      methods to your model other than the auto-generated field getters and
setters. 

    - `BookWrapper`: book wrapper, wraps `Book` `@generated`

From the above list only `BookLocalServiceImpl`, `BookServiceImpl` and
`BookImpl` can be manually modified. 

Now let's write the local service class. 

### Write the Local Service Class 

In the file overview above, you can see that `BookLocalService` is the
interface for the local service. It contains the signatures of every method in
`BookLocalServiceBaseImpl` and `BookLocalServiceImpl`. The
`BookLocalServiceBaseImpl` class contains a few automatically generated methods
providing common functionality. Don't modify this class; it's automatically
generated, so your changes would be overwritten the next time you ran Service
Builder. Instead, place all your custom code in `BookLocalServiceImpl`. 

To write the local service class, open the following file: 

    /docroot/WEB-INF/src/com/sample/portlet/library/service/impl/BookLocalServiceImpl.java

To add the database interaction methods to this service layer class, we'll add
the following method to the `BookLocalServiceImpl` class: 

    public Book addBook(long userId, String title) 
        throws PortalException, SystemException { 
        User user = UserUtil.findByPrimaryKey(userId); 
        Date now = new Date(); 
        long bookId = CounterLocalServiceUtil.increment(Book.class.getName());

        Book book = bookPersistence.create(bookId);

        book.setTitle(title); 
        book.setCompanyId(user.getCompanyId());
        book.setUserId(user.getUserId()); 
        book.setUserName(user.getFullName());
        book.setCreateDate(now); 
        book.setModifiedDate(now); 
        book.setTitle(title);

        return bookPersistence.update(book, false); 
    }

Before you can use this new method, you must add its signature to the
`BookLocalService` interface by building the service in Service Builder again.
Let's review the procedure: 

- *Using Developer Studio:* Open your `service.xml` file, make sure you're in
  *Overview* mode, then select *Build Services*. 

- *Using the terminal:* Navigate to the root directory of your portlet in the
  terminal and run `ant build-service`. 

Service Builder looks through `BookLocalServiceImpl` and copies the signatures
of each method into the interface. Now you can add a new book to the database
with the following call: 

    BookLocalServiceUtil.addBook(userId, "A new title");

### Overview of *service.properties* 

Service Builder generates the properties file `service.properties` in your
service's `src` directory. Liferay Portal uses these properties to alter your
service's database schema and load Spring configuration files that support
deployment of your service. You shouldn't modify `service.properties`; make any
necessary overrides in a `service-ext.properties` file in the `src` folder. 

The property you're most likely to override from `service.properties` is
`build.auto.upgrade`. Setting `build.auto.upgrade=false` in your
`service-ext.properties` prevents Liferay from automatically applying changes
to the database model when a new version of the plugin is deployed. This is
needed in projects where you want to manually manage changes to the database or
the SQL schema has been modified manually after generation by Service Builder. 

### Built-In Liferay Services 

In addition to the services you create using Service Builder, your portlets can 
access a variety of services built into Liferay. These include the
following: 

- `UserService`

- `OrganizationService`

- `GroupService`

- `CompanyService`

- `ImageService`

- `LayoutService`

- `PermissionService`

- `UserGroupService`

- `RoleService`

For more information on these services, see Liferay's Javadocs at
[http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/).

Next we'll show you Liferay's security and permissions systems. 

## Security and Permissions 

JSR-286 (and JSR-168) defines a simple security scheme using portlet roles and
their mapping to portal roles. On top of that, Liferay provides a fine grained
permissions system you can use to implement access security in your custom
portlets. Here we'll give an overview of the JSR-286 (JSR-168) security system,
Liferay's permission system, and how to use them in your own portlets. 

### JSR Portlet Security 

The JSR specification defines the means to specify the roles used by each
portlet in its `portlet.xml` definition. For example, the Liferay *Blogs*
portlet definition references the *guest*, *power-user*, and *user* roles: 

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

Your `portlet.xml` roles need to be mapped to specific roles in the portal.
That way the deployer of a portlet can resolve conflicts between roles with the
same name that are from different portlets (e.g. portlets from different
developers). 

---

![note](../../images/tip-pen-paper.png)**Note:** Each role named in a portlet's
`<security-role-ref>` element is given permission to add the portlet to a page. 

---

To map the roles to the portal, you'll have to use *portal-specific*
configuration files. With Liferay, you can define the mapping in
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
*fine-grained* security. Next we'll describe Liferay's Permission System and
how to use it in your portlets, and you can decide which option fits your needs
best. 

### Liferay's Permission System 

You can add permissions to your custom portlets using four easy steps (also
known as DRAC): 

1. **D**efine all resources and their permissions. 

2. **R**egister all defined resources in the permissions system.  This is also
   known as *adding resources*. 

3. **A**ssociate the necessary permissions with resources. 

4. **C**heck permission before returning resources. 

### Implementing Permissions 

Before you start adding permissions to a portlet, make sure you understand
these two critical terms used throughout this section: 

- *Resource*: A generic term for any object represented in the portal.
  Examples of resources include portlets (e.g. Message Boards, Calendar, etc.),
Java classes (e.g. Message Board Topics, Calendar Events, etc.), and files
(e.g.  documents, images, etc.). 

- *Permission*: An action on a resource. For example, the *view* action with
respect to *viewing the calendar portlet* is defined as a permission in Liferay. 

It's important to know that permissions for *portlet* resources are implemented
a little differently than for other resources like Java classes and files. In
each subsection below, we'll describe permission implementation for the
*portlet* resource first, followed by the model (and file) resource. 

The first step is to define your resources and permissions (or *actions*).
Let' use the *blogs* portlet to demonstrate. Open the `blogs.xml` file in
`portal-impl/src/resource-actions` and you'll see the following mapping of
resources to actions: 

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

Permissions in the blogs portlet are defined at several different levels; each
level coincides with a section of the `resource-actions` XML file (in this
case, `blogs.xml`). At the `<portlet-resource>` level, actions and default
permissions are defined on the portlet itself. Changes to portlet level
permissions are performed on a per-site basis, and change whether users can add
the portlet to a page, edit the portlet's configuration, or view the portlet.
All these actions are defined inside the `<supports>` tag for the permissions
of the portlet resource. The default portlet-level permissions for members of
the site are defined inside the `<site-member-defaults>` tag. In the case of
this blogs portlet, site members can view any blog in the site.  Similarly,
default guest permissions are defined in the `<guest-defaults>` tag.  The
`<guest-unsupported>` tag contains permissions forbidden to guests. Here,
guests can't be given permission to configure the portlet, even by a user with
an administrator role. 

The `<model-resource>` section contains the next level of permissions, based on
the *scope* of an individual instance of the portlet. A *scope* in Liferay
refers to how widely the data from an instance of a portlet is shared . For
example, if you place a blogs portlet on a page in the guest site and place
another blogs portlet on another page in the *same* site, the two blogs will
share the same set of posts.  That happens because *blogs* portlets are given a
*site level* scope by default. If you reconfigure one of the two blogs,
changing its scope to be the current page, that blogs portlet instance will no
longer share content with the other instance (or any other blogs instance in
that site). A portlet instance's scope-based permissions can either span an
entire site or be restricted to a single page. 

The differences between the `<model-resource>` section's portlet instance
permissions and the `<portlet-resource>` section's portlet permissions are
subtle.  Permissions, like the ability to *add* or *subscribe to* a blog entry,
are defined at the portlet instance level. It's possible to have multiple
distinct blog instances within a site, each with different permissions for site
users.  For example, a food site could have one blog open to posts from any
site member, but also have a separate informational blog about the site itself
restricted to posts from administrators. 

After defining the portlet and portlet instance as resources, we need to define
models in the portlet; they'll also require permissions. The model resource is
surrounded by the `<model-resource>` tag. Inside the tag, we first define the
model name; the `<model-name>` isn't the name of a Java class, but the fully
qualified name of the portlet's package (e.g. the blog portlet's package
`com.liferay.portlet.blogs`). This is the recommended convention for
permissions that refer to an instance of the portlet as a whole. The
`<portlet-ref>` element comes next and contains a `<portlet-name>`. The value
of `<portlet-name>` references the name of the portlet to which the model
resource belongs; a model resource can belong to multiple portlets referenced
with multiple `<portlet-name>` elements, but this is uncommon. Like the portlet
resource, the model resource lets you define a list of supported actions that
require permission to perform. You must list all the performable actions that
require a permission check.  For a blog entry, users must belong to appropriate
roles for permission to do the following: 

- *Add comments* to an entry 

- *Delete* an entry 

- *Change the permission* setting of an entry 

- *Update* an entry 

- *View* an entry 

As with a *portlet resource*, the `<site-member-defaults>` tag,
`<guest-defaults>` tag, and `<guest-unsupported>` tag define default
permissions for site members and guests, respectively, for *model resources*. 

After defining resource permissions for your custom portlet, you need to refer
Liferay to the `resource-actions` XML file that contains your definitions (e.g.
`blogs.xml` for the blogs portlet). For Liferay core, the `resource-actions`
XML files are in the `portal/portal-impl/classes/resource-actions` directory
and the file named `default.xml` file refers to each of these files. This
excerpt from `default.xml` references the resource permission definition files
for all built-in Liferay portlets (including the blogs portlet): 

    <?xml version="1.0"?>
    <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_1_0.dtd">
	
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
references the the file that specifies your `<resource-action-mapping>` element
(e.g. `default.xml`). Within this portlet properties file, create a property
named `resource.actions.configs` with the relative path to your portlet's
resource-action mapping file (e.g.  `default.xml`) as its value. Here's what
this property specification might look like: 

    resource.actions.configs=resource-actions/default.xml

Check out a copy of the Liferay source code from the Liferay public
*Subversion* repository to see an example of a portlet that defines its
resources and permissions as we just described; start by looking at the
definition files found in the `portal-impl/src/resource-actions` directory. For
an example of defining permissions in the context of a portlet plugin, check
out `plugins/trunk` and look at the portlet `sample-permissions-portlet`. 

Next, let's talk about the permission algorithm for this version of Liferay
Portal. 

### Permission Algorithms 

Liferay has used six permission-checking algorithms over the years. *Liferay 5*
introduced the fifth algorithm, which was based on *RBAC* system. The sixth
algorithm was introduced with  *Liferay 6*; it was an optimized version of the
fifth algorithm that improved performance by using a reduced set of database
tables. 

Importantly, once a permission algorithm is configured and resources are
created, the algorithm can't be changed, or the existing permissions will be
lost, and some system features may fail. 

For all new deployments, we strongly recommended you use the sixth algorithm.
For deployments using other algorithms, it's recommended you migrate to the
sixth algorithm using the *Control Panel* migration tools: 

        Control Panel &rarr; Server Administration &rarr; Data Migration

For more information, see options for `permissions.user.check.algorithm` in the
`portal.properties` file. 

Next we'll show you how to add resources. 

### Adding a Resource 

After defining resources and actions, it's time to add resources into the
permissions system. A lot of the logic to add resources is encapsulated in the
`ResourceLocalServiceImpl` class. Adding resources is as easy as calling the
`addResources(...)` method of the `ResourceLocalServiceUtil` class. Here's the
signature of that method: 

    public void addResources(
            long companyId, long groupId, long userId, String name,
            String primKey, boolean portletActions,
            boolean addGroupPermissions, boolean addGuestPermissions)

Each Java object that requires access permission must be added as a resource
every time a new object is created. For example, every time a user adds a new
entry to her blog, the `addResources(...)` method should be called to add the
new entry object to the resource system. Here's an example of the call from the
`BlogsEntryLocalServiceImpl` class: 

    resourceLocalService.addResources(
            entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
            BlogsEntry.class.getName(), entry.getEntryId(), false,
            addGroupPermissions, addGuestPermissions);

In the `addResources(...)` method, the parameters `companyId`, `groupId`, and
`userId` are self explanatory. Let's look more closely at the remaining
parameters: 

- The `name` parameter is the fully qualified Java class name for the resource
  object being added. 

- The `primKey` parameter is the primary key of the resource object. 

- The `portletActions` parameter should be set to `true` if you're adding
  portlet action permissions. In our example, it's `false` because we're adding
a model resource, which should be associated with permissions related to the
model action defined in `blogs.xml`. 

- The `addGroupPermissions` and the `addGuestPermissions` parameters are inputs
  from the user. If set to `true`, `ResourceLocalService` will then add the
default permissions to the current group and the guest group for this resource,
respectively. 

You can let your users choose whether to add the default site permission and/or
the guest permission for your custom portlet resources; Liferay has a custom
JSP tag `<liferay-ui:input-permissions />` that you can use to quickly add that
functionality. You just insert the tag into the appropriate JSP and the
checkboxes will show up on that JSP. Make sure that the tag is within the
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

### Adding Permission 

On the portlet level, no code needs to be written in order to have the
permission system work for your custom portlet. Your custom portlet will
automatically have permission features. If you've defined any custom
permissions (supported actions) in your `portlet-resource` tag, they're
automatically added to a list of permissions users can choose from. However,
what good are permissions if you don't to show or hide certain functionality in
your portlet based on permissions? You can do that by checking the permission
*before* performing the intended action on the resource. 

To let a user set permissions on model resources, you'll need to
expose the permission interface to the user. Just add these two
Liferay UI tags to your JSP:

1.  `<liferay-security:permissionsURL>`: Returns a URL that takes the
    user to the page to configure the permission settings. 

2. `<liferay-ui:icon>`: Shows a permission icon to the user. 

This example demonstrates the use of both tags; it comes from the
`view_entry_content.jspf`file. 

    <liferay-security:permissionsURL
    modelResource="<%= BlogsEntry.class.getName() %>"
    modelResourceDescription="<%= entry.getTitle() %>"
    resourcePrimKey="<%= entry.getPrimaryKey().toString() %>"
    var="entryURL"
    />

    <liferay-ui:icon image="permissions" url="<%= entryURL %>" />

For the first tag, you need to specify the following attributes: 
- `modelResource`: The fully qualified Java object class name.  This class name
  gets translated into its more readable name as specified in
`Language.properties`. 

- `modelResourceDescription`: You can pass in anything that best describes this
  model instance. In this example, the blogs title was passed in. 

- `resourcePrimKey`: The primary key of your model instance. 

- `var`: Specifies the name of the variable to be assigned the resulting URL
  String. The variable is then passed to the `<liferay-ui:icon>` tag so the
permission icon will have the proper URL link. 

There's an optional attribute redirect that's available if you want to override
the default behavior of the upper right arrow link. That's it; now your users
can configure the permission settings for model resources. 

Next we'll show you how to implement permissions checking. 

### Checking Permissions 

The last major step toward implementing permissions for your custom portlet is
to ensure the configured permissions are enforced.  You'll do this by adding
checks in a couple of places. For example, your business layer can check for
permission before deleting a resource, or your user interface can hide a button
that adds a model (e.g. a calendar event) if the user doesn't have permission. 

As with the preceding steps, the default permissions for the portlet resources
are automatically checked for you. You don't need to implement anything for
your portlet to discriminate whether a user is allowed to view or configure
the portlet itself. However, you do need to implement checking of any custom
permissions you defined in your `resource-actions` XML file. In the blogs
portlet, one supported custom action is `ADD_ENTRY`. There are two places in
the source code to check for this permission; in your JSP files and in the
business logic.  The presence of the add entry button is contingent on whether
the user has permission to add entry. Here's the `ADD_ENTRY` action in a JSP
file: 

    <%
    if (permissionChecker.hasPermission(
    scopeGroupId, "com.liferay.portlet.blogs.model",
    scopeGroupId, "ADD_ENTRY") {
    // Show add entry button
    }
    %>

The second place to check for the add entry permission is in the business
logic. If the check fails, a `PrincipalException` is thrown `and the add entry
request is aborted:`

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

- `groupId`: Represents the scope where the permission check is performed. In
  Liferay, many scopes are available, including a specific site, organization,
personal site of a user, and more. This is important because a user may be
allowed to add blog entries in one site, but not in another. For resources that
don't belong to a scope, set the value of this parameter to `0`. There
are several ways you can obtain the `groupId` of the current scope: 

    - JSP that uses the `<theme:defineObjects/>` tag: there's an implicit
    variable called `scopeGroupId`.

    - Business logic class: If you're using the ServiceContext pattern, you can
      obtain the `groupId` by using `serviceContext.getScopeGroupId()`. If
you're not using the ServiceContext pattern, your can obtain `groupId` from the
theme display request object: 

            ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                WebKeys.THEME_DISPLAY);
            long scopeGroupId = themeDisplay.getScopeGroupId();

- `name`: The name of the resource as specified in the XML file of the previous
sections. 

- `primKey`: The primary key of the resource. In this example the resource
  doesn't exist as an entry in the database, so we use the `groupId` again. If
we were checking for a permission on a given blog entry, we'd use the primary
key of that blog entry instead. 

- `actionId`: The name of the action as it appears in the XML file. To simplify
  searching for usages, consider creating a helper class that has constants for
all the actions defined. 

In the examples above, we're assuming there's a variable called
`permissionChecker` already available. Liferay automatically creates a
`PermissionChecker` instance for every request that has the necessary
information from the user. Liferay also caches the security checks to ensure
good performance. There are several ways to obtain this instance: 

- In a JSP that uses the `<theme:defineObjects/>` tag, there's an implicit
variable called `permissionChecker`. 

- With Service Builder, every service implementation class can access the
  `PermissionChecker` instance by using the method `getPermissionChecker()`. 

- If you're not using Service Builder, `PermissionChecker` can be obtained from
  the theme display request object: 

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker permissionChecker =
            themeDisplay.getPermissionChecker();

Next you'll learn about creating helper classes for permission checking. 

### Creating Helper Classes for Permission Checking 

It's helpful to create helper classes that encapsulate the use of
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

Now let's see how a `ServiceImpl` class `BlogsEntryServiceImpl` uses the
`BlogsPermission` helper class. In method
`BlogsEntryServiceImpl.addEntry(...)`, a call is made to check whether the
incoming request has permission to add entry. The check is done using helper
class `BlogsPermission`. If the check fails, it throws a `PrincipalException`
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

Check out the parameters passed into the `check(...)` method. Again, the
`getPermissionChecker()` method is readily available in all `ServiceImpl`
classes. The blogs entry ID is available in the `serviceContext`, indicating
that the permission check is against the blogs portlet. `ActionKeys.ADD_ENTRY`
is a static string used to indicate the action requiring the permission check.
Likewise, you're encouraged to use custom portlet action keys. 

Let's review what we've just covered. Implementing permission into your custom
portlet consists of four main steps: 
 
1. Define any custom resources and actions. 

2. Implement code to register (or add) any newly created resources, such as a
   `BlogsEntry` object. 

3. Provide an interface for the user to configure permission. 

4. Implement code to check permission before returning resources or showing
   custom features.  

The two major resources are *portlets* and *Java objects*; there's not a lot to
be done for the portlet resource to implement the permission system, since
Liferay Portal does most of the work for you. You can focus your efforts on any
custom Java objects you've built. 

You're now equipped to implement security in your custom Liferay portlets! 

Next, let's learn how to use the *Asset Framework*. 

## Asset Framework 

The asset framework provides a set of functionalities common to several content
types, including blog entries, wiki pages, web content, and more. It was
initially created to add tags to content without having to repeatedly
re-implement the same functionality. Since then, the Asset Framework has grown
to include more functionalities, and you can now use the framework for custom
applications even if they're implemented inside a plugin. 

The term *asset* is a generic term referring to *any* type of content,
including text, an external file, a URL, an image, or a record in an online
book library. Consequently, when we use the term *asset* here, we're referring
to some type of Liferay content, like documents, blog entries, bookmarks, wiki
pages, and more. 

Here are the main functionalities you can reuse thanks to the asset framework: 

- Associate tags to custom content types. New tags are created automatically
  when the author assigns them to the content. 

- Associate categories to custom content types. Authors are only allowed to
  select from predefined categories within several predefined vocabularies. 

- Manage tags from the control panel, including merging tags. 

- Manage categories from the control panel, including creating complex
  hierarchies. 

- Associate comments with assets. 

- Rate assets using a five star rating system. 

- Assign social bookmarks to asssets, inlcuding via tweet, Facebook like, or +1
  (Google Plus). 

- Add custom fields to assets. 

- Relate assets to one another. 

- Flag asset content as inappropriate. 

- Keep track of the number of visualizations of an asset. 

- Integrate workflow with assets. 

- Publish your content using the Asset Publisher portlet. Asset Publisher can
  publish dynamic asset lists or manually selected asset lists. It can also
show an asset summary view with a link to the full view. This saves you time,
since it likely won't be necessary to develop custom portlets for your custom
content types. 

At this point you might be saying. "Asset Framework sounds great; but how do I
leverage all these awesome functions?" Excellent question, young padawan, and
perfect timing; we couldn't have said it better ourselves. 

The subsections below describe the steps involved in using Asset Framework.
We'll describe the first two briefly here before we dive in head first: 

- The first step is mandatory; you must let the framework know whenever one of
  your custom content entries is added, updated or deleted. 

- The second step is optional, but it can save you a lot of time; you can use a
  set of taglibs to provide widgets that allow authors to enter tags and
categories, as well as how to show the entered tags and categories along with
the content. 

<!--I don't understand the second half of the second step, after the comma.
-Russ -->

The remaining steps are optional but offer interesting functionalities, like
allowing your custom assets to be published through the Asset Publisher. 

Next let's dive head first into the first step; informing the Asset Framework
when you add, update, or delete assets. 

#### Adding, updating and deleting assets 

Whenever one of your custom content entries is created, you need to let the
Asset Framework know. It's simple; you just invoke a method of the Asset
Framework. When invoking this method, the framework becomes aware of the tags
and categories of the created content. 

All the methods you need to invoke are part of `AssetEntryLocalService`.
Specifically, you should access these methods using either the static methods
of `AssetLocalServiceUtil` or an instance of the `AssetEntryLocalService`
injected by Spring. To simplify this section, we'll be using the static methods
of `AssetLocalServiceUtil`, since it doesn't require any special setup in your
application. 

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

- `userId` is the identifier of the user who created the content. 

- `groupId` identifies the scope of the created content. If your content
  doesn't support scopes, just pass `0` as the value. 

- `className` identifies the type of asset. The recommended convention is to
  use the name of the Java class that represents your content type, but you can
actually use any String you want as long as you are sure that it is unique. 

- `classPK` identifies the specific content being created among others of the
  same type. It's usually the primary key of the table where the custom content
is stored. If you want, you can use the *classUuid* parameter to specify a
secondary identifier; it's guaranteed to be universally unique. It's especially
useful if your contents will be exported and imported across separate portals. 

- `assetCategoryIds` and *assetTagNames* represent the categories and tags
  selected by the author of the content. The Asset Framework will store them
for you. 

- `visible` specifies whether the content should be shown at all by Asset
  Publisher. 

- `title,` `description` and `summary` are descriptive fields used by the Asset
  Publisher when displaying entries of your content type. 

- `publishDate` and `expirationDate`, when specified, tell Asset Publisher it
  shouldn't show the content before a given publication date or after a given
expiration date, respectively. 

- All other fields are optional; it won't always make sense to include them.
  The `sync` parameter should always be *false* unless you're doing something
very advanced (feel free to look at the code if you're really curious). 

When one of your custom content entries is deleted, you should once again let
Asset Framework know. That way it can clean up stored information and make sure
that the Asset Publisher doesn't show any information for the content that has
been deleted. The signature of method to delete an asset entry is: 

    void deleteEntry(String className, long classPK)

Here's an example invocation extracted again from the blogs portlet: 

    assetEntryLocalService.deleteEntry(
        BlogsEntry.class.getName(), entry.getEntryId());

Now that you can create and modify assets, consider tagging and categorizing
them. Let's tackle that next. 

#### Entering and displaying tags and categories 

In the last section we let Asset Framework know about the tags and categories
that we associated with a given asset; but how does a content author specify
the tags and categories? 

Liferay provides a set of JSP tags you can use to make this task very easy, but
you can use a different method if you like. You can put the following Liferay
UI tags in your forms to create content that can be associated with new or
existing tags, or predefined categories: 

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
search for a tag or create a new one, and select an existing category. 

---

![tip](../../images/tip-pen-paper.png)**Tip:** If you're using Liferay's Alloy
Form taglibs, creating fields to enter tags and categories is even simpler. You
just use <aui:input name="tags" type="assetTags" /> and <aui:input
name="categories" type="assetCategories" />, respectively. 

---

Once the tags and categories have been entered you'll want to show them along
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

Inside both tags, you can also specify a `portletURL` parameter; each tag that
uses it will be a link containing the `portletURL` *and* `tag` or `categoryId`
parameter value, respectively. This supports tags navigation and categories
navigation within your portlet. You'll need to implement the look-up
functionality in your portlet code; do this by reading the values of those two
parameters and using the `AssetEntryService` to query the database for entries
based on the specified tag or category. 

Great job! You'll have no problem associating tags and categories with your
assets. Before we go further with our example, let's take a look at more JSP
tags you can use to leverage Asset Framework's features. 

#### More JSP tags for assets 

In addition tags and categories, there are more features that Asset Framework
provides you. These features allow users to do the following with your assets: 

- Add comments

- Rate comments of other users

- Rate assets

- Apply social bookmarks (e.g. via tweet, Facebook like, or +1 (Google Plus))

- Relate assets to one another

- Flag content as inappropriate and notify the portal administrator

There are JSP tags, called *Liferay UI* tags, associated with each feature. You
can find these tags used in the JSPs for Liferay's built-in portlets (e.g. the
`edit_entry.jsp` of the Blogs portlet).  Here are some examples of the JSP tags
from the Blogs portlet: 

- *Comments and comment ratings:* 

        <portlet:actionURL var="discussionURL">
            <portlet:param name="struts_action" value="/blogs/edit_entry_discussion" />
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

- *Rate assets:* 

        <liferay-ui:ratings
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
        />

- *Social Bookmarks:* 

        <liferay-ui:social-bookmarks
            displayStyle="<%= socialBookmarksDisplayStyle %>"
            target="_blank"
            title="<%= entry.getTitle() %>"
            url="<%= PortalUtil.getCanonicalURL(bookmarkURL.toString(), themeDisplay) %>"
        />

- *Related assets:* 

        <liferay-ui:input-asset-links
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entryId %>"
        />

- *Flag as inappropriate:* 

        <liferay-ui:flags
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            contentTitle="<%= entry.getTitle() %>"
            reportedUserId="<%= entry.getUserId() %>"
        />

With Liferay's taglib tags, you can easily apply these features to your assets.
No problemo, right? So let's get the assets published in your portal. 

#### Publishing assets with Asset Publisher 

A huge benefit of using Asset Framework is that you can leverage the Asset
Publisher portlet to publish lists of your custom asset types. You can choose
to have users specify lists dynamically (e.g., based on the asset tags or
categories) or have administrators do it statically. The Asset Publisher
portlet is part of the Liferay distribution. 

To display your assets, the Asset Publisher needs to know how to access their
metadata. You also need to provide the Asset Publisher templates for the types
of views (e.g. *full* view and *abstract* view) available to display your
assets. You can provide all this to the Asset Publisher by implementing these
two interfaces: 

- `AssetRendererFactory`: A class that knows how to retrieve specific assets
  from the persistent storage using the class `classPK`. The `classPK` class is
typically the asset's primary key, but can be anything you specified to the
`updateAsset` method, which you use to add or update the asset. Your factory
implementation can grab the asset from a `groupId` (identifies a scope of data)
and a `urlTitle` (a title that can be used in friendly URLs to refer uniquely
to the asset within a given scope). Finally, the asset renderer factory can
provide a URL for the Asset Publisher to use when a user wants to add a new
asset of your custom type. This URL should point to your own portlet.  There
are other less important methods of the interface, but you can avoid
implementing them by extending `BaseAssetRendererFactory`.  By extending this
*base* class instead of implementing the interface directly, your code will be
more robust to possible interface changes in future versions of Liferay, since
the base implementation will be updated to accommodate the interface changes. 

- `AssetRenderer`: The class that provides metadata information about one
  specific asset. It checks whether the current user has permission to edit or
view the asset and renders the asset for the different templates (e.g. abstract
and full content view) by forwarding to a specific and appropriate JSP. We
recommend that you extend the `BaseAssetRenderer` class rather than directly
implementing the interface. The *base* class provides helpful defaults and is
robust robust for methods that get added to the interface in the future. 

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

    <liferay-ui:custom-attributes-available className="<%= BlogsEntry.class.getName() %>">
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

Now get out there and start enjoying the benefits of the Asset Framework in
your custom portlets. 

Let's learn how to leverage Liferay Portal's File Storage Framework. 

## File Storage Framework 

The File Storage Framework lets you store files using the back end of the
Document Library. If you use this framework you won't have to worry about
clustering or backups, since they'll already be taken care of for the Document
Library itself. To see examples of this framework in action, check out the
source code for the built in *wiki* and *message boards* portlets of Liferay;
the File Storage Framework stores their attached files in pages and posts,
respectively. 

## Other frameworks 

Liferay has a variety of frameworks that simplify development of complex
functionalities for your own applications. Liferay's frameworks have evolved
from its built in applications, so they're proven to work in the real world,
even in high performance portals. 

This section is a placeholder that provides a brief description of the main
frameworks provided with Liferay 6.1. The following list is a work in progress,
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
extended information on each of these frameworks. 

Next, we'll explore the seemingly mysterious world of plugin security
management.

## Summary 

With Liferay's frameworks, implementing complex functionality in your custom
portlets becomes easy. We covered Service Builder, security and permissions,
Asset Framework, the file storage framework, and some other interesting
frameworks that aren't yet fully documented. Make sure  you check back
regularly to find more detailed descriptions of current frameworks and discover
brand new frameworks that'll knock your socks off, or at least simplify your
custom portlet development. 
