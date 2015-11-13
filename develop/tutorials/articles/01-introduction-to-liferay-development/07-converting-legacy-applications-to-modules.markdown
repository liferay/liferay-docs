# Converting Legacy Applications to Modules

You've probably heard the term *modularity* discussed quite frequently in
relation to Liferay 7.0. With Liferay 7.0 transforming into a modular platform,
components that Liferay uses will be mostly in the form of
[modules](https://github.com/liferay/liferay-portal/tree/master/modules/apps).

A module doesn't use the traditional Liferay application structure from previous
versions. Liferay is following a different structure for modules that you'll
learn about in this tutorial. You, however, are not required to follow
Liferay's standard application structure; you have the freedom to structure your
application's modules however you wish.

A module in Liferay is very similar to a standard Java application with some
additional metadata and annotations. The annotations replace the need for XML
file descriptors in the application and are easier to manage since everything is
in one place.

The options you have for upgrading your application to Liferay 7.0 are discussed
briefly in the
[Migrating Legacy Applications to New Plugins SDK](/develop/tutorials/-/knowledge_base/7-0/migrating-legacy-applications-to-new-plugins-sdk)
tutorial. Because Liferay 7.0 maintains backwards compatibility, you have the
option to keep your legacy application as a WAR-style application or convert
your application into modules. Before you begin following the steps in this
tutorial to convert your application into modules, you'll consider expert
recommendations for if and when to convert your legacy application.

Before you begin converting your legacy application into modules, it is
recommended that you first migrate your legacy plugin to a Liferay 7.0
compatible WAR file. It is much easier to convert a legacy application to
modules when the API and breaking changes are updated. If you jump from a legacy
6.2 application to 7.0 modules, it will be much more difficult debugging and
figuring out what issues are related to which migration process. This is,
however, not required.

Converting your legacy application to modules is not always the right choice. In
some scenarios, it could make sense to stick with your traditional WAR model.
Below, you'll learn some important tips to help you make your decision on
whether to convert your legacy application to modules.

**When to convert?**

- If you have a very large application with many lines of code. For example, if
there are many developers that are collaborating on an application concurrently
and making changes frequently, separating the code into modules will increase
productivity and the agility to release more frequently.
- If your plugin has reusable parts that you'd like to consume from elsewhere.
For instance, suppose you have business logic that you're reusing in multiple
different projects. As opposed to copying that code into several different WARs
and deploying that to different customers, you can convert it to modules and
consume those services from one single module.

**When to not convert?**

- You have a portlet that's using JSR-168/286 compatibility and you still want
to be able to deploy that into another portlet container. If you want to retain
that compatibility, it is recommended to stay with the traditional WAR model.
- You're using a complex legacy web framework that is heavily tied to the Java
EE programming model, and the amount of work necessary to make that work with
OSGi is more than you feel is necessary or warranted.
- If your plugin interacts with the app server. Module-based applications are
not as portable when they directly interact with the app server.
- If your legacy application's original intent was to have a limited-lifetime.

Your decision to convert to modules ultimately comes down to benefits vs. costs.
Obviously, the time to convert your legacy application is a cost. Likewise,
there are many benefits to managing your legacy application as modules.

A large application can be split into many small independent, easy to manage
components. These small components also allow for incremental release cycles. In
multi-module projects, this also means a certain component can be updated
independently. For instance if a JSP is changed due to a security issue, the web
(client) module can be updated but the persistence modules can remain unchanged.
For Liferay. this could mean applications that used to have to wait for new
Liferay releases could see independent releases between Liferay versions.

Module dependencies are explicitly listed within the module and will refuse to
run unless all dependencies are met, thus eliminating obscure run time errors.
Another common deployment issue is multiple versions of the same library in an
environment. It is possible for the class loader to merge classes from multiple
versions of a library, leading to very hard to troubleshoot and obscure
problems. Module versions can be stated explicitly in the dependency,
eliminating these types of issues.

Now that you have some ammunition to make an informed decision on what's best
for your particular scenario, you'll learn how to convert your legacy WAR
application into modules. Before getting started, it's important to reiterate
that the module structure shown in this tutorial is just one of many ways for
structuring your application's modules. It's also important to remember that
applications come in all different shapes and sizes. There may be special
actions that some applications require. This tutorial will provide the general
process for converting to modules using Liferay's module structure. The first
thing you'll do is create your application's client module.

## Converting Your Legacy Application's Portlet Classes and UI

The first thing you'll do is create your application's parent directory and
directory structure for your application's client *web* module. This module
holds your application's portlet classes and is responsible for its UI. This
tutorial will assume the Maven model, although any directory set up is
permissible.

1.  Create the parent directory for your application. This parent directory is
    home for your application's independent modules and configuration files.
    This tutorial will initially set up one module in this parent directory,
    but, depending on the size of your application, you could have multiple
    modules within the parent directory. For example, if your application's name
    is *Tasks*, then your parent directory could be *tasks*.

2.  Create the directory structure skeleton for your client module. You can do
    this automatically by using Blade Tools. You can learn how to install Blade
    Tools by visiting the
    [Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/introduction-to-blade-tools)
    and you can view examples of
    [Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)
    and
    [Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)
    by visiting their respective tutorials.

    Navigate to your parent directory (e.g., `tasks`) and run the following
    blade command to generate a generic module structure:

        blade create jspportlet [APPLICATION_NAME]-web

    Delete the `/src/main/java/[APPLICATION_NAME]-web` folder and the `init.jsp`
    and `view.jsp` files located in the `src/main/resources/META-INF/resources`
    folder. You'll insert your legacy application's Java code and JSPs, so the
    generated default code is not necessary.

    The current directory structure for you application's \*-web module is listed
    below:

    - `tasks`
        - `tasks-web`
            - `src`
                - `main`
                    - `java`
                    - `resources`
                        - `content`
                            - `Language.properties`
                        - `META-INF`
                            - `resources`
            - `bnd.bnd`
            - `build.gradle`

3.  Open the `bnd.bnd` file. This is used to generate your module's `MANIFEST.MF`
    file that is generated when you build your project. Edit your module's
    `bnd.bnd` file to fit your application. For more information about
    `bnd.bnd`, visit
    [http://www.aqute.biz/Bnd/Bnd](http://www.aqute.biz/Bnd/Bnd). You can view
    the `dictionary-web`'s
    [`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dictionary/dictionary-web/bnd.bnd)
    for a simple example, or the `journal-web`'s
    [`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/journal/journal-web/bnd.bnd)
    for a more advanced example.

4.  Open the `build.gradle` file. This is used to specify all your module's
    dependencies. The `build.gradle` file that was generated for you is
    pre-populated with content and default dependencies related to OSGi and
    Liferay Portal. In the `dependencies {...}` block, you'll need to add the
    client module's dependencies. When deploying your module into the OSGi
    container, OSGi checks if the dependencies are available in the container.
    If the dependencies are not available in the container, your module will be
    unavailable. Therefore, your dependencies are not bundled with your module,
    but instead, they'll be available from the OSGi container.

    <!-- Check step 4 explanation for accuracy. -Cody -->

5.  Copy your legacy application's JSP files into the
    `/src/main/resources/META-INF/resources` directory. In most cases. all of
    your JSP files should reside in the client module.

6.  Your next task is to copy your portlet classes, non-service classes, and
    non-implementation classes into your client module. Before you do this,
    create your directory package structure inside the `/src/main/java`
    directory. For instance, if you want your class' package name to be
    `com.liferay.tasks.web`, your class' directory would be
    `/src/main/java/com/liferay/tasks/web`. Copy your portlet classes into their
    respective directories and ensure their package names within the class are
    specified correctly. Your client module can hold one class or many classes,
    depending on how large your application is. It's a good practice to organize
    your classes into sub-folders of the main package, to easily manage your
    Java classes. You can view the
    [journal-web](https://github.com/liferay/liferay-portal/tree/master/modules/apps/journal/journal-web/src/main/java/com/liferay/journal/web)
    module for an example of a client module holding many different Java
    classes.

    +$$$

    **Note:** Many applications have service and API classes. These classes will
    need to reside in different modules, such as the service or implementation
    module. You'll learn more about creating these later in this tutorial.

    $$$

7.  Now that you have the necessary classes in your client module, you'll need to
    edit these classes to be compliant with OSGi. You'll need to choose a
    component framework to work with, which is the key to harnessing the power
    of OSGi. Liferay uses Declarative Services, so this tutorial will assume the
    use of this framework. You can, however, choose the component framework you
    are most comfortable with in Liferay.

    Review your legacy application's XML files and migrate the configuration and
    metadata information to the portlet class as properties. You can do this by
    adding the `@Component` annotation to your portlet class and adding the
    necessary properties to that annotation. The end result will look similar to
    the following example:

        @Component(
            immediate = true,
            property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.icon=/icon.png",
                "javax.portlet.name=1",
                "javax.portlet.display-name=Tasks Portlet",
                "javax.portlet.security-role-ref=administrator,guest,power-user",
                "javax.portlet.init-param.clear-request-parameters=true",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.supports.mime-type=text/html",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.info.title=Tasks Portlet",
                "javax.portlet.info.short-title=Tasks",
                "javax.portlet.info.keywords=Tasks",
            },
            service = Portlet.class
        )
        public class TasksPortlet extends MVCPortlet {

8.  Convert all references of the `portletId` (e.g., `58_INSTANCE_4gtH`) to the
    class name of the portlet, replacing all periods with underscores (e.g.,
    `com_liferay_web_proxy_portlet_WebProxyPortlet`).

9.  If your legacy application has resource actions, you'll need to migrate those
    into your client module. Create the
    `/src/main/resources/resource-actions/default.xml` file, and copy your
    resource actions there. Make sure to create the `src/portlet.properties`
    file and add the following property:

        resource.actions.configs=resource-actions/default.xml

    As an example, you can view the Directory application's
    [`default.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/directory/directory-web/src/main/resources/resource-actions/default.xml)
    file.

10.  Add any language keys that your application uses to the
    `src/main/resources/content/Language.properties` file. You should only
    include the language keys that are unique to your application. Your
    application will use the default language keys in Liferay when it is
    deployed.

Awesome! You've successfully created your application's client module! If you'd
like to learn more about the client module, and how to create one from scratch,
visit the
[Creating a Client Module](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components#creating-a-client-module)
section.

For a quick reference guide, you can view the table below, which summarizes the
migration process of many of your legacy application's directories, packages,
and files. This is a sample table for a fictitious *tasks* applications.

    | Legacy Package | Module Package |
    |----------------|----------------|
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.asset` | `tasks.web/src/main/java/com.liferay.tasks.asset` | 
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.portlet` | `tasks.web/src/main/java/com.liferay.tasks.portlet` |
    | `tasks-portlet/docroot/WEB-INF/src/content` | `tasks.web/src/main/resources/content` |
    | `tasks-portlet/docroot/WEB-INF/src/resource-actions` | `tasks.web/src/main/resources/resource-actions` |
    | `tasks-portlet/docroot/WEB-INF/src/portlet.properties` | `tasks.web/src/main/resources/portlet.properties` |
    | `tasks-portlet/docroot/init.jsp` | `tasks.web/src/main/resources/META-INF/resources/init.jsp` |
    | `tasks-portlet/docroot/tasks` | `tasks.web/src/main/resources/META-INF/resources/tasks` |
    | `tasks-portlet/docroot/upcoming_tasks` | `tasks.web/src/main/resources/META-INF/resources/upcoming_tasks` |

Many applications only have the client module. Larger, more complex
modules, however, require additional modules to hold their service and
implementation logic. You'll learn about how to create these modules next.

## Converting Your Legacy Application's API and Service Builder Implementation

If you've continued to this section, you're converting a legacy application that
offers its own API and uses Service Builder. In this section, you'll create the
API module (\*-api) and implementation module (\*-service). The API module holds
your application's Service Builder generated API and the implementation module
holds your application's Service Builder implementation. Before you begin
creating the API and implementation modules, you'll need to configure your
root project (e.g., `tasks`).

In your root project directory, create a `build.gradle` file. By creating this
file, you allow yourself to build and generate your entire project, instead of
focusing on individual modules. To generate your APIs using Service Builder,
you'll need to have this build file. You can copy/paste the
`build.gradle` file that you generated for your client module. Obviously, the
sky is the limit for how you want to configure your project, but this tutorial
will keep it simple. The only modification your build file requires is the
specification of what configurations are designated for your modules. To specify
this, open your root project's `build.gradle` file and wrap the content after
the `buildscript {...}` block with the `subprojects {}` block.

The last configuration file required in your root project directory is the
`settings.gradle` file. Create this file and add the following:

    include '[APPLICATION_NAME]-api','[APPLICATION_NAME]-service','[APPLICATION_NAME]-web'

Of course, replace the `[APPLICATION_NAME]` syntax with your own application
name. This logic makes your root project directory aware of the modules listed.

Now that your root project directory is configured, you'll create the API and
implementation modules.

1.  Repeat step 2 from the previous section, except name the module
    `[APPLICATION_NAME]-service`. In addition to the directory deletions covered
    in step 2, you should also delete the `/src/main/resources` directory. This
    directory will be regenerated by Service Builder. Your implementation
    module's directory structure should resemble the following:

        - `tasks`
            - `tasks-service`
                - `src`
                    - `main`
                        - `java`
                - `bnd.bnd`
                - `build.gradle`

2.  Copy your legacy application's `service.xml` file and paste it into the
    implementation module's root directory (e.g., `tasks/tasks-service`). 

3.  Edit the `bnd.bnd` file to fit your implementation module. For examples of
    implementation module BND files, see the `export-import-service`'s
    [`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/export-import/export-import-service/bnd.bnd)
    and the `wiki-service`'s
    [`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/wiki/wiki-service/bnd.bnd).

4.  Open your implementation module's `build.gradle` file. You'll need to
    configure this file to use Service Builder. As you learned about the root
    folder's `build.gradle` file, the sky is the limit for how you'd like your
    module's build file configured. This tutorial will only configure the build
    file to use Service Builder.

    First, add the classpath declaration for Service Builder as a dependency
    inside the `buildscript{...}` block:

        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins.service.builder", version: "1.0.5"
        }

    Next, apply the Service Builder plugin directly after the `buildscript{...}`
    block:

        apply plugin: "com.liferay.portal.tools.service.builder"

5.  Now that the Service Builder plugin is configured, create a
   `buildService{...}` block. This configures how Service Builder runs for your
   project. You can view an example below for a `tasks` project:

        buildService {
            apiDirName = "../tasks-api/src/main/java"
            beanLocatorUtil = "com.liferay.util.bean.PortletBeanLocatorUtil"
            hbmFileName = "src/main/resources/META-INF/module-hbm.xml"
            implDirName = "src/main/java"
            inputFileName = "service.xml"
            modelHintsFileName = "src/main/resources/META-INF/portlet-model-hints.xml"
            osgiModule = true
            propsUtil = "com.liferay.tasks.service.util.PropsUtil"
            remotingFileName = null
            resourcesDirName = "src/main/resources"
            springFileName = "src/main/resources/META-INF/spring/module-spring.xml"
            springNamespaces = ["beans","osgi"]
            sqlDirName = "src/main/resources/META-INF/sql"
            sqlFileName = "tables.sql"
        }

    You'll notice that the example code above specifies the
    [APPLICATION_NAME]-api module. When you run Service Builder, your generated
    APIs will reside in the API module.

6.  You're now able to use Service Builder to generate your APIs. Open a
    terminal and navigate to your root project folder. Then run `gradle
    buildService`.

    Your `service.xml` file's configuration is used to generate your
    application's API and implementation classes in their respective modules.
    You've also generated other custom files (e.g., SQL, Spring, etc.),
    depending on your `buildService {...)` block's configuration.

7.  Now that you've run Service Builder, continue copying custom classes into
    your implementation module. There is a table listed below highlighting
    popular legacy classes and packages and where they should be placed in your
    module. This table is intended to aid in the organization of your classes
    and configuration files; however, remember to follow the organizational
    methodologies that make the most sense for your application. One size does
    not fit all with your modules' directory scheme.

    | Legacy Package | Module Package |
    |----------------|----------------|
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.model.impl` | `tasks.service/src/main/java/com.liferay.tasks.model.impl` | 
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.impl` | `tasks.service/src/main/java/com.liferay.tasks.service.impl` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.permission` | `tasks.service/src/main/java/com.liferay.tasks.service.permission` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.persistence.impl` | `tasks.service/src/main/java/com.liferay.tasks.service.persistence.impl` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.social` | `tasks.service/src/main/java/com.liferay.tasks.social` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.util` | `tasks.service/src/main/java/com.liferay.tasks.util` |
    | `tasks-portlet/docroot/WEB-INF/src/custom-sql` | `tasks.service/src/main/resources/META-INF/custom-sql` |

8.  Once you've copied all of your custom classes over, run `gradle
    buildService` again to generate the remaining services.

Now that your services are generated, you'll need to wire up your modules so
they can reference each other when deployed to the OSGi container. This tutorial
will assume the client module requires the implementation and API module and the
implementation module requires the API module. Open your client module's
`build.gradle` file and add the following dependencies:

    dependencies {
        compile  project(':tasks.api')
        compile  project(':tasks.service')
    }

To declare your implementation module's dependency, open its `build.gradle` file
and add the following:

    dependencies {
        compile  project(':tasks.api')
    }

Lastly, add the BND file to your new API module, so it can be recognized by the
OSGi container as a module. For an example BND file used in an API module, see
the export-import module's
[`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/export-import/export-import-api/bnd.bnd).
For consistency, add a `build.gradle` file too. You can add the version
information for now, and edit it later for your own application, if necessary:
`version='1.0.0'`.

Excellent! You've successfully generated your application's services using
Service Builder. They now reside in modules, and can be deployed to Liferay 7.0.
If you'd like to learn more information about creating implementation and API
modules from scratch, visit the
[Creating an Implementation Module](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components#creating-an-implementation-module)
and
[Creating an API Module](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components#creating-an-api-module),
respectively.

Now it's time to build your modules and deploy them to your Liferay 7.0
instance. To build your project, run `gradle build` from your project's root
directory. Once your project successfully builds, check any of your modules'
`/build/libs` directory. There should be a newly generated JAR file, which is
the file you'll need to deploy to Liferay. You can deploy each JAR by running:

    blade deploy path/to/JAR/file

+$$$

**Note:** If you deploy your modules out of order, you may receive error
messages. For instance, if you try deploying your client module first, you'll
receive errors because it relies on the implementation and API modules. Once
each module's dependencies are met, they will successfully be deployed for use
in Liferay. For more information on checking each module's dependencies, visit
the
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
article.

$$$

Once you've successfully deployed your modules, your Gogo shell prompt should
look like the figure below.

![Figure 1: Once you've connected to your Liferay instance in your Gogo shell prompt, run *lb* to list your new converted modules.](../../images/deploy-converted-modules.png)

This tutorial guided you with converting your legacy application to modules for
Liferay 7.0. You first created a client (\*-web) module that holds your
application's portlet classes and UI. Then you created your implementation
module and generated your application's services and the API module using
Service Builder. Next, you wired your modules together by declaring their
dependencies on one another. Lastly, you built your modules and deployed them to
your Liferay instance. Great job!

## Related Topics [](id=related-topics)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)
