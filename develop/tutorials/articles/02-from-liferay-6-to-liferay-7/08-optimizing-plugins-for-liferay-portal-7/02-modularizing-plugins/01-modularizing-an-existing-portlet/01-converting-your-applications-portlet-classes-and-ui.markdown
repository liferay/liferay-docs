# Converting Your Application's Portlet Classes and UI [](id=converting-your-applications-portlet-classes-and-ui)

The first thing you'll do is create your application's root folder and the
folder structure for its *web* client module. This module holds portlet classes
and the web UI. Before you start creating a skeleton structure for the modules,
determine the modules that comprise this version of your application. If your
application provides service API and implementation classes (which is the case
for all Liferay Service Builder applications), you'll create separate modules
for them. This tutorial assumes the Maven project model, although any build
tools or folder structure is permissible.

+$$$

**Note:** You should use the build plugin versions that support the latest OSGi
features. The following Gradle or Maven build plugin versions should be used in
their respective build frameworks:

**Gradle**

- biz.aQute.bnd:biz.aQute.bnd.gradle:3.2.0 **or**
- org.dm.gradle:gradle-bundle-plugin:0.9.0

**Maven**

- biz.aQute.bnd:bnd-maven-plugin:3.2.0

$$$

Here are the steps for creating the folder structure: 

1.  Create the root folder. It is the new home for your application's
    independent modules and configuration files. For example, if your
    application's name is *Tasks*, then your root folder could be *tasks*.

2.  Create the folder structure for your web client module.
    [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli) and
    [Maven](/develop/reference/-/knowledge_base/7-1/maven)
    generate project folder structures based on
    [project templates](/develop/reference/-/knowledge_base/7-1/project-templates).

    For example, navigate to the root folder (e.g., `tasks`) and run the
    following Blade CLI command to generate a generic web client module
    structure:

        blade create -t mvc-portlet [APPLICATION_NAME]-web

    If you use Liferay Service Builder, you must create modules for it. This
    command generates the service API and service implementation modules:

        blade create -t service-builder -p [ROOT_PACKAGE] [APPLICATION_NAME]

    If you generated service API and service implementation modules, ignore the
    `*-service` and `*-api` folders for now; you'll learn about them in the next
    tutorial.

3.  In your `*-web` module, replace the `/src/main/java/[APPLICATION_NAME]`
    folder with your root Java package. For example, if your application's root
    package name is `com.liferay.tasks.web`, your class's folder should be
    `/src/main/java/com/liferay/tasks/web`. Also, remove the `init.jsp` and
    `view.jsp` files from the `src/main/resources/META-INF/resources` folder.
    You'll use your existing application's JSPs instead of these generated
    default JSPs.

4.  Verify that your `*-web` module folder resides in your application's root 
    folder (marked by `[APPLICATION_NAME]` below)'s and your `*-web` module's
    folder structure looks like this:

    - `[APPLICATION_NAME]`
        - `[APPLICATION_NAME]-web`
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

    The remaining steps affect the web client module (`*-web` module).

5.  The `bnd.bnd` file is used to generate your module's `MANIFEST.MF`
    file when you build your project. Open it and change it to fit your
    application. There's 
    [further documentation](http://bnd.bndtools.org/) 
    about configuring your module's `bnd.bnd`. For example, here's
    the Liferay `dictionary-web` module's `bnd.bnd`:

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

6.  Open the `build.gradle` file. 
    [Specify all your module's dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)
    here. The one generated for you is pre-populated with content and default
    dependencies. Add your module's dependencies in the `dependencies {...}`
    block. 

    [Finding Liferay API Modules](/develop/reference/-/knowledge_base/7-0/finding-liferay-api-modules)
    lists common Liferay API module's symbolic names. The Javadoc overviews for 
    [@product-ver@](@platform-ref@/7.1-latest/javadocs/) and
    [Liferay apps](@app-ref@/7.1-latest/javadocs)
    list each module's symbolic name and version. The
    [Configuring Dependencies tutorial](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)
    demonstrates finding artifact information and specifying dependencies. 
    [@product@ provides many Java packages and entire artifacts](/develop/reference/-/knowledge_base/7-0/third-party-packages-portal-exports)
    at runtime in the OSGi container. Your module is activated after
    installation once all its dependencies resolve. Unresolved dependencies
    appear in the log. Here's an example message:

        ! could not resolve the bundles: ... Unresolved requirement: Import-Package: ... Unresolved requirement: Require-Capability ...

7.  Copy your traditional application's JSP files into the
    `/src/main/resources/META-INF/resources` folder. In most cases, all of your
    application's JSP files belong in the web client module.

8.  Copy your portlet classes and supporting classes that aren't related to
    [Service Builder](/develop/tutorials/-/knowledge_base/7-0/running-service-builder)
    into their respective package folders in the web client module. Organizing
    classes into sub-packages can make them easier to manage. 

    For example, here's the `journal-web` module's Java source folder structure:

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

    **Note:** Many applications have API and implementation classes. These 
    classes belong in API and implementation modules. The next tutorial
    demonstrates copying those classes into modules. 

    $$$

9.  Now that the necessary classes are in your client module, you must make them
    comply with OSGi. If you're a beginner, we recommend using the 
    [Declarative Services](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html) 
    component framework because @product@ uses it. This tutorial assumes that
    you're using Declarative Services. You can, however, use any other OSGi
    component framework.

    Review your traditional application's XML files and migrate the
    configuration and metadata information to the portlet class as component
    properties.  You can do this by adding the `@Component` annotation to your
    portlet class and adding the necessary properties to that annotation.
    Examine the
    [mapping of the portlet descriptors to component properties](/develop/reference/-/knowledge_base/7-0/portlet-descriptor-to-osgi-service-property-map).
    The end result should look similar to the following example:

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
            ...
        }

10. Convert all references of the `portletId` (e.g., `58_INSTANCE_4gtH`) to the
    class name of the portlet, replacing all periods with underscores (e.g.,
    `com_liferay_web_proxy_portlet_WebProxyPortlet`).

11. Migrate your traditional application's resource actions (if it has any), 
    into your client module. Create the
    `/src/main/resources/resource-actions/default.xml` file, and copy your
    resource actions there. Make sure to create the `src/portlet.properties`
    file and add the following property:

        resource.actions.configs=resource-actions/default.xml

    As an example, see the Directory application's
    [`default.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/directory/directory-web/src/main/resources/resource-actions/default.xml).

    Note that 
    [the permissions API has changed in 7.1](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/defining-application-permissions);
    adapt your permissions helpers accordingly. 

12. Add your language keys to the
    `src/main/resources/content/Language.properties` file. Only include the
    language keys unique to your application. @product@'s language keys are
    available to all portlet applications. 

Awesome! You've created your application's web client module and completed some
of the most common tasks for modularizing your portlet classes and UI. To
convert other parts of your application this tutorial hasn't covered, examine
the
[@product@ developer tutorials](/develop/tutorials)
to see how those parts fit into application modules. The tutorials  are divided
into popular areas so you can easily find the topical information you need.

Lastly, the table below is a quick reference guide that maps files and Java
packages from a traditional portlet plugin to a module for a fictitious
application called `tasks-portlet`. 

| Traditional Plugin | Module |
|--------------------|--------|
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
