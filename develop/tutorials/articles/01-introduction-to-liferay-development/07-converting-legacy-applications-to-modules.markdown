# Converting Legacy Applications to Modules

You've probably heard the term *modularity* discussed quite frequently in
relation to Liferay 7.0. With Liferay 7.0 transforming into a modular platform,
components that Liferay uses will be mostly in the form of modules. For example,
many of the components that you're used to seeing in Liferay are now
[modules](https://github.com/liferay/liferay-portal/tree/master/modules/apps).

A module doesn't use the traditional Liferay application structure from previous
versions. Liferay is following a different structure for modules that you'll
learn about in this tutorial. You, however, are not required to follow
Liferay's standard application structure; you have the freedom to structure your
application's modules however you wish.

The options you have for upgrading your application to Liferay 7.0 is discussed
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
whether converting your legacy application to modules is the right decision.

**When to convert?**

- If you have a very large application with many lines of code. For example, if
there are many developers that are collaborating on an application concurrently
and making changes frequently, separating the code into modules will increase
productivity and the agility to release more frequently.
- If your plugin has reusable parts that you'd like to consume from elsewhere.
For instance, suppose you have business logic that you're reusing in multiple
different projects. As opposed to copying that code into several different WARs
and deploying that to different customers, you can make those code parts into
modules and consume those services from one single module.

**When to not convert?**

- You have a portlet that's using JSR-168/286 compatibility and you still want
to be able to deploy that into another portlet container. If you want to retain
that compatibility, it is recommended to stay with the traditional WAR model.
- You're using a complex legacy web framework that is really tied to the Java EE
programming model, and the amount of work necessary to make that work with OSGi
is more than you feel is necessary or warranted.
- If your plugin interacts with the app server. By distancing applications from
the app server, it means that they are much more portable because you can remove
a lot of contingent code that does different behaviors based on the app server.
This also unifies the classloading model because different app servers have
different classloaders models, so it's very difficult to manage plugins and
decide how their classloading is going to work.
- If your legacy application's original intent was to have limited-lifetime,
there may not be a good reason to convert.

Your decision to convert to modules utimately comes down to benefits vs. costs.
Obviously, the time to convert your legacy application is a cost. Likewise,
there are many benefits to managing your legacy application as modules. Large
applications can be split into many smaller, easy to manage modules, which
allows for a much better release process. You can fix bugs in sub-modules
without affecting the entire application. Other benefits include being able to
have an incremental release cycle and the ability to update modules
independently.

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
perimissible.

1. Create the parent directory for your application. This parent directory is
home for your application's independent modules and configuration files. This
tutorial will initially set up one module in this parent directory, but,
depending on the size of your application, you could have multiple modules
within the parent directory. For example, if your application's name is *Tasks*,
then your parent directory could be *tasks*.

2. Create the directory structure skeleton for your client module. You can do
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

    The current directory structure for you application's *-web module is listed
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

3. Open the `bnd.bnd` file. This is used to generate your module's `MANIFEST.MF`
file that is generated when you build your project. Edit your module's `bnd.bnd`
file to fit your application. For more information about `bnd.bnd`, visit
[http://www.aqute.biz/Bnd/Bnd](http://www.aqute.biz/Bnd/Bnd). You can view the
`dictionary-web`'s
[`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dictionary/dictionary-web/bnd.bnd)
for a simple example, or the `journal-web`'s
[`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/journal/journal-web/bnd.bnd)
for a more advanced example.

4. Open the `build.gradle` file. This is used to specify all your module's
dependencies. The `build.gradle` file that was generated for you is
pre-populated with content and default dependencies related to OSGi and Liferay
Portal. In the `dependencies {...}` block, you'll need to add the client
module's dependencies. When deploying your module into the OSGi container, OSGi
checks if the dependencies are available in the container. If the dependencies
are not available in the container, your module will be unavailable. Therefore,
your dependencies are not bundled with your module, but instead, they'll be
available from the OSGi container.

<!-- Check step 4 explanation for accuracy. -Cody -->

5. Copy your legacy application's JSP files into the
`/src/main/resources/META-INF/resources` directory. In most cases. all of your
JSP files should reside in the client module.

6. Your next task is to copy your portlet classes, non-service classes, and
non-implementation classes into your client module. Before you do this, create
your directory package structure inside the `/src/main/java` directory. For
instance, if you want your class' package name to be `com.liferay.tasks.web`,
your class' directory would be `/src/main/java/com/liferay/tasks/web`. Copy your
portlet classes into their respective directories and ensure their package names
within the class are specified correctly. Your client module can hold one class
or many classes, depending on how large your application is. It's a good
practice to organize your classes into sub-folders of the main package, to
easily manage your Java classes. You can view the
[journal-web](https://github.com/liferay/liferay-portal/tree/master/modules/apps/journal/journal-web/src/main/java/com/liferay/journal/web)
module for an example of a client module holding many different Java classes.

    +$$$

    **Note:** Many applications have service and API classes. These classes will
    need to reside in different modules, such as the service or implementation
    module. You'll learn more about creating these later in this tutorial.

    $$$

7. Now that you have the necessary classes in your client module, you'll need to
edit these classes to be compliant with OSGi. You'll need to choose a component
framework to work with, which is the key to harnessing the power of OSGi.
Liferay uses Declarative Services, so this tutorial will assume the use of this
framework. You can, however, choose the component framework you are most
comfortable with in Liferay.

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

8. Convert all references of the `portletId` (e.g., `58_INSTANCE_4gtH`) to the
class name of the portlet, replacing all periods with underscores
(e.g., `com_liferay_web_proxy_portlet_WebProxyPortlet`).

9. If your legacy application has resource actions, you'll need to migrate those
into your client module. Create the
`/src/main/resources/resource-actions/default.xml` file, and copy your resource
actions there. Make sure to create the `src/portlet.properties` file and add the
following property:

        resource.actions.configs=resource-actions/default.xml

    As an example, you can view the Directory application's
    [`default.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/directory/directory-web/src/main/resources/resource-actions/default.xml)
    file.

10. Add any language keys that your application uses to the
`src/main/resources/content/Language.properties` file. You should only include
the language keys that are unique to your application. Your application will
use the default language keys in Liferay when it is deployed.

Awesome! You've successfully created your application's client module! If you'd
like to learn more about the client module, and how to create one from scratch,
visit the
[Creating a Client Module](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components#creating-a-client-module)
section. Many applications only have the client module. Larger, more complex
modules, however, require additional modules to hold their service and
implementation logic. You'll learn about how to create these modules next.

## Converting Your Legacy Application's API and Service Builder Implementation










## Related Topics [](id=related-topics)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)
