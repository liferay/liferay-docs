# Modularizing an Existing Portlet [](id=modularizing-an-existing-portlet)

An application with properly modularized plugins offers several benefits. You
can release individually its plugins without releasing the entire application.
External clients can consume services from particular plugins, without having to
depend on an entire application. And by splitting up large amounts of code into
concise modules, teams can more easily focus on particular areas of the
application. These are just a few reasons to modularize application plugins. 

In this tutorial, you'll learn how to convert your traditional application into
modules. Before getting started, it's important to reiterate that the module
structure shown in this tutorial is just one of many ways for structuring your
application's modules. It's also important to remember that applications come in
all different shapes and sizes. There may be special actions that some
applications require. This tutorial provides the general process for converting
to modules using Liferay's module structure.

Here's what's involved:

- Converting portlet classes and the UI
- Converting Service Builder interfaces and implementations
- Building and deploying modules

The instructions covered in this tutorial apply to both the commercial and open 
source versions of Liferay.

The first thing you'll do is create your application's web (client) module.

## Converting Your Application's Portlet Classes and UI [](id=converting-your-applications-portlet-classes-and-ui)

The first thing you'll do is create your application's parent directory and the
directory structure for your application's *web* client module. This module
holds your application's portlet classes and is responsible for its UI. Before
you start creating a skeleton structure for your application's modules, you
should determine which modules will comprise this version of your
application. If your application provides service and API classes (which is the
case for all Liferay Service Builder applications), you should create separate
modules for your service implementation and service API classes. This tutorial
assumes the Maven project model, although any build tools or directory setup is
permissible.

+$$$

**Note:** It's recommended that you use the build plugin versions that support
the latest OSGi features. The following Gradle or Maven build plugin versions
should be used in their respective build frameworks:

**Gradle**
- biz.aQute.bnd:biz.aQute.bnd.gradle:3.1.0 **or**
- org.dm.gradle:gradle-bundle-plugin:0.8.1

**Maven**
- biz.aQute.bnd:bnd-maven-plugin:3.1.0 **or**
- org.apache.felix:maven-bundle-plugin:3.0.1

<!-- These versions support the R6 OSGi released features. -Cody -->

$$$

1.  Create the parent directory for your application. This parent directory is
    home for your application's independent modules and configuration files. For
    example, if your application's name is *Tasks*, then your parent directory
    could be *tasks*.

2.  Create the directory structure skeleton for your web client module. You can
    do this automatically by using Blade CLI. You can learn how to install
    Blade CLI by visiting the
    [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli)
    tutorial section and you can view examples of
    [Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)
    by visiting the respective tutorials.

    Navigate to your parent directory (e.g., `tasks`) and run the following
    Blade CLI command to generate a generic web client module structure:

        blade create -t mvc-portlet [APPLICATION_NAME]-web

    If your application uses Liferay Service Builder, you'll need to run the
    following Blade CLI command as well. This command generates the service
    implementation and service API modules:

        blade create -t service-builder -p [ROOT_PACKAGE] [APPLICATION_NAME]

    If you used the `blade create service-builder` command to generate
    implementation and API modules, ignore the `*-service` and `*-api` folders
    for now; you'll learn about them later in this tutorial.

3.  Replace the `/src/main/java/[APPLICATION_NAME]` folder with your root
    package. For instance, if your application's root package name is
    `com.liferay.tasks.web`, your class's directory should be
    `/src/main/java/com/liferay/tasks/web`. Also, remove the `init.jsp` and
    `view.jsp` files located in the `src/main/resources/META-INF/resources`
    folder. You'll insert your traditional application's Java code and JSPs, so
    the generated default code is not necessary.

4.  Verify that your current directory structure for your application's `*-web`
    module matches the structure listed below:

    - `tasks`
        - `tasks-web`
            - `src`
                - `main`
                    - `java`
                        - `[ROOT_PACKAGE]`
                    - `resources`
                        - `content`
                            - `Language.properties`
                        - `META-INF`
                            - `resources`
            - `bnd.bnd`
            - `build.gradle`

    The instructions in the rest of this sub-section only affect your
    application's web client module.

5.  Open the `bnd.bnd` file. This is used to generate your module's
    `MANIFEST.MF` file that is generated when you build your project. Edit your
    module's `bnd.bnd` file to fit your application. For more information about
    configuring your module's `bnd.bnd`, visit
    [http://bnd.bndtools.org/](http://bnd.bndtools.org/). You can view the
    `dictionary-web` module's `bnd.bnd` for a simple example below:

        Bundle-Name: Liferay Dictionary Web
        Bundle-SymbolicName: com.liferay.dictionary.web
        Bundle-Version: 1.0.6

    For a more advanced example, examine the `journal-web` module's `bnd.bnd`:

        Bundle-Name: Liferay Journal Web
        Bundle-SymbolicName: com.liferay.journal.web
        Bundle-Version: 2.0.0
        Export-Package:\
            com.liferay.journal.web.asset,\
            com.liferay.journal.web.dynamic.data.mapping.util,\
            com.liferay.journal.web.social,\
            com.liferay.journal.web.util
        Liferay-JS-Config: /META-INF/resources/js/config.js
        Liferay-Releng-Module-Group-Description:
        Liferay-Releng-Module-Group-Title: Web Content
        Web-ContextPath: /journal-web

6.  Open the `build.gradle` file. This is used to [specify all your module's
    dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies).
    The `build.gradle` file that was generated for you is pre-populated with
    content and default dependencies related to OSGi and @product@. In the
    `dependencies {...}` block, you need to add the web client module's
    dependencies. To learn how to find and specify dependencies on Liferay API
    modules, refer to the reference document 
    [Finding Liferay API Modules](/develop/reference/-/knowledge_base/7-0/finding-liferay-api-modules). 
    When deploying your module into the OSGi container, OSGi checks if the
    dependencies are available in the container. If the dependencies are not
    available in the container, your module will be unavailable. Therefore, your
    dependencies are not bundled with your module. Instead, they're available
    from Liferay's OSGi container.

7.  Copy your traditional application's JSP files into the
    `/src/main/resources/META-INF/resources` directory. In most cases, all of
    your application's JSP files should reside in the web client module.

8.  Your next task is to add your portlet classes, non-service classes, and
    non-implementation classes into your client module. Copy your portlet
    classes into their respective directories and ensure their package names
    within the class are specified correctly. Your client module can hold one
    class or many classes, depending on how large your application is. It's a
    good practice to organize your classes into sub-packages of the main
    package, to more easily manage them. You'll examine the `journal-web` module
    for an example of a client module holding many different Java classes:

    - `journal-web`
        - ...
        - `src/main/java/com/liferay/journal/web/`
            - `asset`
                - [classes]
            - `configuration`
                - [classes]
            - `dynamic/data/mapping/util`
                - [classes]
            - `internal`
                - `application/list`
                    - [classes]
                - `custom/attributes`
                    - [classes]
                - `dao/search`
                    - [classes]
                - ...
            - `social`
                - [classes]
            - `util`
                - [classes]
        - ...

    +$$$

    **Note:** Many applications have service and API classes. These classes
    need to live in separate implementation and API modules. You'll learn more
    about creating these later in this tutorial.

    $$$

9.  Now that you have the necessary classes in your client module, you need to
    edit these classes to be compliant with OSGi. First, you need to choose a
    component framework to work with. Using a component framework lets you
    easily harness the power of OSGi. @product@ uses the
    [Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
    component framework and recommends that Liferay developers use it too. This
    tutorial assumes that you're using Declarative Services. You can, however,
    use any other OSGi component framework in @product@.

    Review your traditional application's XML files and migrate the
    configuration and metadata information to the portlet class as properties.
    You can do this by adding the `@Component` annotation to your portlet class
    and adding the necessary properties to that annotation. The end result
    should look similar to the following example:

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

10. Convert all references of the `portletId` (e.g., `58_INSTANCE_4gtH`) to the
    class name of the portlet, replacing all periods with underscores (e.g.,
    `com_liferay_web_proxy_portlet_WebProxyPortlet`).

11. If your traditional application has resource actions, you'll need to migrate
    those into your client module. Create the
    `/src/main/resources/resource-actions/default.xml` file, and copy your
    resource actions there. Make sure to create the `src/portlet.properties`
    file and add the following property:

        resource.actions.configs=resource-actions/default.xml

    As an example, you can view the Directory application's
    [`default.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/directory/directory-web/src/main/resources/resource-actions/default.xml)
    file.

12. Add any language keys that your application uses to the
    `src/main/resources/content/Language.properties` file. You should only
    include the language keys that are unique to your application. Your
    application will use the default language keys in Liferay when it is
    deployed.

Awesome! You've created your application's web client module and navigated
through some of the most common tasks necessary to modularize your portlet
classes and UI. There are certain parts of your application that may not be
covered in this tutorial that you must account for. Liferay's Developer Network
provides developer tutorials divided into popular areas so you can easily find
the correct way to transform your legacy code to use @product@'s updated best
practices.

The table below serves as a quick reference guide. It summarizes the migration
process for many of your application's directories, packages, and files.
This is a sample table for a fictitious *tasks* applications.

| Plugin Package | Module Package |
|----------------|----------------|
| `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.asset` | `tasks-web/src/main/java/com.liferay.tasks.asset` |
| `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.portlet` | `tasks-web/src/main/java/com.liferay.tasks.portlet` |
| `tasks-portlet/docroot/WEB-INF/src/content` | `tasks-web/src/main/resources/content` |
| `tasks-portlet/docroot/WEB-INF/src/resource-actions` | `tasks-web/src/main/resources/resource-actions` |
| `tasks-portlet/docroot/WEB-INF/src/portlet.properties` | `tasks-web/src/main/resources/portlet.properties` |
| `tasks-portlet/docroot/init.jsp` | `tasks-web/src/main/resources/META-INF/resources/init.jsp` |
| `tasks-portlet/docroot/tasks` | `tasks-web/src/main/resources/META-INF/resources/tasks` |
| `tasks-portlet/docroot/upcoming_tasks` | `tasks-web/src/main/resources/META-INF/resources/upcoming_tasks` |

Many applications only have a web client module. Larger, more complex
applications, such as Liferay Service Builder applications, require additional
modules to hold their service API and service implementation logic. You'll
learn how to create these modules next.

## Converting Your Application's Service Builder API and Implementation [](id=converting-your-applications-service-builder-api-and-implementation)

In this section, you'll learn about converting a Liferay Portal 6 Service Builder
application to a @product-ver@ style application. In the previous section, you
learned how to generate your implementation and API modules. If you haven't yet
run the `service-builder` Blade CLI command outlined in step 2 of the previous
section, run it now. The API module holds your application's Service Builder
generated API and the implementation module holds your application's Service
Builder implementation.

Before you begin editing the API and implementation modules, you'll need to
configure your root project (e.g., `tasks`) to recognize the multiple modules
residing there. A multi-module Gradle project must have a `settings.gradle`
file in the root project for building purposes. Luckily, when you generated
your Service Builder project's modules using Blade CLI, the `settings.gradle`
file was inserted and pre-configured for the `api` and `service` modules. You
should add your `web` module into the Service Builder project's generated parent
folder and define it in the `settings.gradle` file too. You'll configure your
`web` module via Gradle settings later, but for now, go ahead and copy the
module into the project generated by the `service-builder` template. For
example, an example `tasks` project's root folder would look like this:

- `tasks`
    - `gradle`
    - `tasks-api`
    - `tasks-service`
    - `tasks-web`
    - `build.gradle`
    - `gradlew`
    - `settings.gradle`

Your root project directory should now be in good shape. Next, you'll learn how
to use Service Builder to generate your application's service API and service
implementation code.

1.  Copy your traditional application's `service.xml` file and paste it into the
    implementation module's root directory (e.g., `tasks/tasks-service`).

2.  Blade CLI generated a `bnd.bnd` file for your service implementation module.
    Make sure to edit this `bnd.bnd` file to fit your application. For an
    example of a service implementation module's BND file, examine the
    `export-import-service` module's BND below:

        Bundle-Name: Liferay Export Import Service
        Bundle-SymbolicName: com.liferay.exportimport.service
        Bundle-Version: 4.0.0
        Export-Package:\
            com.liferay.exportimport.content.processor.base,\
            com.liferay.exportimport.controller,\
            com.liferay.exportimport.data.handler.base,\
            com.liferay.exportimport.lar,\
            com.liferay.exportimport.lifecycle,\
            com.liferay.exportimport.messaging,\
            com.liferay.exportimport.portlet.preferences.processor.base,\
            com.liferay.exportimport.portlet.preferences.processor.capability,\
            com.liferay.exportimport.search,\
            com.liferay.exportimport.staged.model.repository.base,\
            com.liferay.exportimport.staging,\
            com.liferay.exportimport.xstream
        Liferay-Releng-Module-Group-Description:
        Liferay-Releng-Module-Group-Title: Data Management
        Liferay-Require-SchemaVersion: 1.0.0
        -includeresource: content=../../staging/staging-lang/src/main/resources/content

3.  Blade CLI also generated your service implementation module's
    `build.gradle` file. In this file, Service Builder is already configured to
    generate code both in this module and in your service API module. When you
    run Service Builder, Java classes, interfaces, and related files are
    generated in your `*api` and `*service` modules. Open your service
    implementation module's `build.gradle` file to view the default
    configuration.

    As you've learned already, you don't have to accept the generated build
    files' defaults. Blade CLI simply generated some standard OSGi and
    Liferay configurations.

    For example, Service Builder is already available for you by default. Blade
    CLI applies the Service Builder plugin automatically when a project contains
    the `service.xml` file. With the Service Builder plugin already available,
    you don't have to worry about configuring it in your project.

4.  Another important part of your service implementation module's
    `build.gradle` file is the `buildService{...}` block. This block configures
    how Service Builder runs for your project. The current configuration will
    generate your API module successfully, but extra configuration might be
    necessary in certain cases.

5.  Open a terminal and navigate to your root project folder. Then run `gradlew
    buildService`.

    Your `service.xml` file's configuration is used to generate your
    application's service API and service implementation classes in their
    respective modules. You've also generated other custom files (related to
    SQL, Hibernate, Spring, etc.), depending on your `buildService {...)`
    block's configuration. For more information on configuration options for the
    Service Builder plugin, see the
    [Service Builder Gradle Plugin](/develop/reference/-/knowledge_base/7-0/service-builder-gradle-plugin)
    reference article.

6.  Now that you've run Service Builder, continue copying custom classes into
    your implementation module. The table below highlights popular Liferay
    Portal 6 classes and packages and where they should be placed in your
    application. This table is intended to aid in the organization of your
    classes and configuration files; however, remember to follow the
    organizational methodologies that make the most sense for your application.
    One size does not fit all with your modules' directory schemes.

    | Plugin Package | Module Package |
    |----------------|----------------|
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.model.impl` | `tasks-service/src/main/java/com.liferay.tasks.model.impl` | 
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.impl` | `tasks-service/src/main/java/com.liferay.tasks.service.impl` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.permission` | `tasks-service/src/main/java/com.liferay.tasks.service.permission` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.persistence.impl` | `tasks-service/src/main/java/com.liferay.tasks.service.persistence.impl` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.social` | `tasks-service/src/main/java/com.liferay.tasks.social` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.util` | `tasks-service/src/main/java/com.liferay.tasks.util` |
    | `tasks-portlet/docroot/WEB-INF/src/custom-sql` | `tasks-service/src/main/resources/META-INF/custom-sql` |

7.  Once you've copied all of your custom classes over, run `gradlew
    buildService` again to generate the remaining services.

Now that your services are generated, you'll need to wire up your modules so
they can reference each other when deployed to Liferay's OSGi container. Blade
CLI has already partially completed this task. For example, it assumes that the
service implementation module depends on the service API module.

You still need to associate the client module with the `api` and `service`
modules, since they were generated separately. To do this, follow the steps
below:

1.  In your project's `settings.gradle` file, you must add the `web` module with
    the `api` and `service` modules so it's included in the Gradle build
    lifecycle:

        include "tasks-api", "tasks-service", "tasks-web"

2.  Add the `api` and `service` modules as dependencies in you client module:

        dependencies {
            compileOnly  project(':tasks-api')
            compileOnly  project(':tasks-service')
        }

Excellent! You've successfully generated your application's services using
Service Builder. They now reside in modules, and can be deployed to
@product-ver@.

## Building Your Module JARs for Deployment [](id=building-your-module-jars-for-deployment)

Now it's time to build your modules and deploy them to your @product@ instance.
To build your project, run `gradlew build` from your project's root directory.

Once your project successfully builds, check all of your modules' `/build/libs`
folders. There should be a newly generated JAR file in each, which is the file
you'll need to deploy to @product@. You can deploy each JAR by running `blade
deploy` from each module's root directory.

+$$$

**Note:** If you deploy your modules out of order, you might receive error
messages. For instance, if you try deploying your web client module first,
you'll receive errors if it relies on the service implementation and service
API modules. Once each module's dependencies are met, they will successfully be
deployed for use in Liferay. For more information on checking each module's
dependencies, see the
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
article.

$$$

Once you've successfully deployed your modules, you can list them from the Gogo
shell as shown below.

![Figure 1: Once you've connected to your Liferay instance in your Gogo shell prompt, run *lb* to list your new converted modules.](../../../../images/deploy-converted-modules.png)

This tutorial explained how to convert your traditional application into the
modular format of a @product-ver@ style applicaton. Specifically, you learned
how to

- Create a web client (`*-web`) module that holds your application's portlet
  classes and UI.
- Create a service implementation module (`*-service`) and a service API module
  (`*-api`).
- Run Service Builder to generate code for your application's service and API
  modules.
- Wire your modules together by declaring their dependencies on each another.
- Build your modules and deploy them to your @product@ installation.

Great job!

## Related Topics [](id=related-topics)

[Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

[Service Builder](/develop/tutorials/-/knowledge_base/7-0/service-builder)
