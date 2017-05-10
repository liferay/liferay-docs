# Creating Remote Services [](id=creating-remote-services)

Many default @product@ services are available as web services. @product@ exposes its
web services via JSON and SOAP web services. If you're running the portal locally
on port 8080, visit the following URL to browse @product@'s default JSON web
services:

    http://localhost:8080/api/jsonws/

To browse @product@'s default SOAP web services, visit this URL:

    http://localhost:8080/api/axis

These web services APIs can be accessed by many different kinds of clients,
including non-portlet and even non-Java clients. You can use Service Builder to
generate similar remote services for your projects' custom entities. When you
run Service Builder with the `remote-service` attribute set to `true` for an
entity, all the classes, interfaces, and files required to support both SOAP and
JSON web services are generated for that entity. Service Builder generates
methods that call existing services, but it's up to you to implement the methods
that are exposed remotely. In this tutorial, you'll learn how to generate remote
services for your application. When you're done, your application's remote
service methods can be called remotely via JSON and SOAP web services. 

## Using Service Builder to Generate Remote Services [](id=using-service-builder-to-generate-remote-services)

Remember that you should implement your application's local service methods in 
`*LocalServiceImpl`. You should implement your application's remote service 
methods in `*ServiceImpl`. 

+$$$

**Best Practice:** If your application needs both local and remote services,
determine the service methods that your application needs for working with your
entity model. Add these service methods to `*LocalServiceImpl`. Then create
corresponding remote services methods in `*ServiceImpl`. Add permission checks
to the remote service methods and make the remote service methods invoke the
local service methods. The remote service methods can have the same names as the
local service methods that they call. Within your application, only call the
remote services. This ensures that your service methods are secured and that you
don't have to duplicate permissions code.

$$$

As an example, consider @product@'s Blogs app. Articles are represented by the 
`JournalArticle` entity. This entity is declared in the `journal-service` 
module's 
[`service.xml` file](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/journal/journal-service/service.xml) 
with the `remote-service` attribute set to `true`. Service Builder therefore 
generates 
[the remote service class `JournalArticleServiceImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/journal/journal-service/src/main/java/com/liferay/journal/service/impl/JournalArticleServiceImpl.java) 
to hold the remote service method implementations. If you were developing this 
app from scratch, this class would initially be empty; you must use it to 
implement the entity's remote service methods. Also, note that the remote 
service method implementations in `JournalArticleServiceImpl` follow best 
practice by checking permissions and calling the corresponding local service 
method. For example, each `addArticle` method in `JournalArticleServiceImpl` 
checks permissions via 
[the custom permissions class `JournalFolderPermission`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/journal/journal-service/src/main/java/com/liferay/journal/service/permission/JournalFolderPermission.java) 
and then calls the local service's matching `addArticle` method: 

    @Override
    public JournalArticle addArticle(...)
        throws PortalException {

        JournalFolderPermission.check(
            getPermissionChecker(), groupId, folderId, ActionKeys.ADD_ARTICLE);

        return journalArticleLocalService.addArticle(...);
    }

You'll also need to develop custom permissions classes for each entity you need 
to perform permissions checks on. Also note that the local service is called via 
the `journalArticleLocalService` field. This is a Spring bean of type 
`JournalArticleLocalServiceImpl` that's injected into 
`JournalArticleServiceImpl` by Service Builder. See 
[the class `JournalArticleServiceBaseImpl`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/journal/journal-service/src/main/java/com/liferay/journal/service/base/JournalArticleServiceBaseImpl.java) 
for a complete list of Spring beans available in `JournalArticleServiceImpl`. 

After you've finished adding remote service methods to your `*ServiceImpl` 
class, save it and run Service Builder again. After running Service Builder, 
deploy your project and check the @product@ JSON web services URL 
[http://localhost:8080/api/jsonws/](http://localhost:8080/api/jsonws/) 
to make sure that your remote services appear when you select your application's 
context path. 

Nice work! You've successfully used Service Builder to generate your app's 
remote services. To make these services available via SOAP, however, you must 
build and deploy your app's Web Service Deployment Descriptor (WSDD). The next 
section shows you how to do this. If you don't need to generate SOAP web 
services, you can move on to the tutorial
[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-0/invoking-remote-services). 

## Generating Your App's WSDD [](id=generating-your-apps-wsdd)

@product@ uses Apache Axis to make SOAP web services available. Since Axis 
requires a WSDD to make an app's remote services available via SOAP, you must 
build and deploy a WSDD for your app. To create your WSDD, you must install 
Liferay's WSDD Builder Gradle plugin in your app's project. How you do this, 
however, depends on what kind of project you have. For multi-module projects 
like a Service Builder project in a Liferay Workspace, you'll install the plugin 
via the workspace's `settings.gradle` file. This applies the WSDD Builder plugin 
to every module in the workspace that uses Service Builder (typically the 
`*-api` and `*-service` modules). If you have a standalone `*-service` module 
that uses Service Builder, however, you'll install the WSDD Builder plugin in 
the module's `build.gradle` file. 

The next section shows you how to install the WSDD builder in a multi-module 
project. If you have a standalone module project, skip ahead to the section 
*Installing the WSDD Builder Plugin in a Standalone Module Project*. 

### Installing the WSDD Builder Plugin in a Multi-module Project [](id=installing-the-wsdd-builder-plugin-in-a-multi-module-project)

To install the WSDD Builder plugin in a multi-module project like a Service 
Builder project in a Liferay Workspace, do the following in the workspace's 
`settings.gradle` file: 
 
1. Add the `ServiceBuilderPlugin` and `WSDDBuilderPlugin` imports to the top of 
   the file: 

        import com.liferay.gradle.plugins.service.builder.ServiceBuilderPlugin
        import com.liferay.gradle.plugins.wsdd.builder.WSDDBuilderPlugin

2. In the `repositories` block, add the Liferay CDN repository via Maven: 

        repositories {
            maven {
                url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
            }
        }

    This repository hosts the WSDD Builder library, its transitive dependencies, 
    and other Liferay libraries. Note that if you created your Service Builder 
    project with the `service-builder` template in Blade or Liferay IDE, then 
    your `settings.gradle` file should already contain this. 

3. Add this code to the end of the file: 

        gradle.beforeProject {
            project ->

            project.plugins.withType(ServiceBuilderPlugin) {
                project.apply plugin: WSDDBuilderPlugin
            }
        }

    This is the code that applies the WSDD Builder plugin in every module in the 
    Liferay Workspace that uses Service Builder. Your `settings.gradle` file 
    should now look something like this: 

        import com.liferay.gradle.plugins.service.builder.ServiceBuilderPlugin
        import com.liferay.gradle.plugins.wsdd.builder.WSDDBuilderPlugin

        buildscript {
            dependencies {
                classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "1.2.0"
            }

            repositories {
                maven {
                    url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
                }
            }
        }

        apply plugin: "com.liferay.workspace"

        gradle.beforeProject {
            project ->

            project.plugins.withType(ServiceBuilderPlugin) {
                project.apply plugin: WSDDBuilderPlugin
            }
        }

4. Refresh the Liferay Workspace's Gradle project. Close and restart Liferay IDE 
   if you're using it. 

Now that you've installed the WSDD Builder plugin, you're ready to build and 
deploy the WSDD. For instructions on this, proceed to the section *Building and 
Deploying the WSDD*. 

### Installing the WSDD Builder Plugin in a Standalone Module Project [](id=installing-the-wsdd-builder-plugin-in-a-standalone-module-project)

To install the WSDD Builder plugin in a standalone `*-service` module that uses 
Service Builder, do the following in the module's `build.gradle` file: 

1. Add the plugin as a dependency in your `buildscript`. 
2. Add the Liferay CDN repository via Maven. 
3. Apply the plugin to your project. 

For example, the following part of an example `build.gradle` file in a 
standalone `*-service` module includes the WSDD Builder plugin and applies it to 
the project: 

    buildscript {
        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins.wsdd.builder", version: "1.0.9"
        }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
	}

    apply plugin: "com.liferay.portal.tools.wsdd.builder"

Now you're ready to build and deploy the WSDD. The next section shows you how to 
do this. 

## Building and Deploying the WSDD [](id=building-and-deploying-the-wsdd)

To build the WSDD, you must run the `buildWSDD` Gradle task in your `*-service` 
module. Exactly how you do this depends on your development tools: 

- **Liferay IDE:** From the Liferay Workspace perspective's *Gradle Tasks* pane 
  (typically on the right), open your `*-service` module's *build* folder and 
  double-click *buildWSDD*. 
- **Command Line:** Navigate to your `*-service` module and run 
  `../../../gradlew buildWSDD`. Note that the exact location of the Gradle 
  wrapper (`gradlew`) may vary. For Liferay Workspace projects, it's typically 
  in the root workspace folder. 

So what should you do if `buildWSDD` fails? A common cause of `buildWSDD` 
failures is failing to satisfy the dependencies needed by the WSDD Builder for 
your `*-service` module. Note that these dependencies vary depending on your 
project's code--there's no standard set. That said, the following are often 
required for portlet development: 

    compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
	compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    compileOnly group: "com.liferay", name: "com.liferay.registry.api", version: "1.0.0"

[Click here](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies) 
for more information on finding and configuring dependencies for your apps. 

In your `*-service` project's `build/libs` folder, the `buildWSDD` task 
generated a `*-service-wsdd-[version].jar` file that contains your WSDD. Deploy 
this JAR to your @product@ instance. Your SOAP web services are then available 
at a URL that uses the following pattern: 

    yourportaladdress/o/your.apps.service.module.context/api/axis

For example, if an app called *Foo* consists of the modules `foo-api`, 
`foo-service`, and `foo-web`, then the app's service module context is 
`foo-service`. If this app is deployed to a local @product@ instance running at 
`http://localhost:8080`, you could access its SOAP services at: 

    http://localhost:8080/o/foo-service/api/axis

If you don't know an app's `*-service` module context, you can find it by 
searching for the app in the App Manager of the @product@ instance in which the 
app is running. For example, the following screenshot shows the Foo app's 
modules in the App Manager. The name of the `*-service` module in the App 
Manager, `foo-service`, is also its context. Also note that the app's WSDD 
module is grayed out and listed as Resolved instead of Active. This is normal. 
WSDD modules are OSGi fragments, which can't be activated. They still work as 
intended, though. 

![Figure 1: To find your app's modules, including its WSDD module, search for your app in the App Manager. The `*-service` module's name in the App Manager is also the module's context.](../../../images/app-manager-remote-services.png)

Next, you'll learn how to build the WSDD module for @product@'s built-in apps 
that don't include a WSDD by default. If you don't need to do this, you can move 
on to the tutorial 
[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-0/invoking-remote-services). 

## Building the WSDD for Built-in @product@ Apps [](id=building-the-wsdd-for-built-in-liferay-apps)

@product@ doesn't provide WSDD modules for built-in apps that exist outside 
of the portal context. This means that by default you can't access SOAP web 
services for apps like Bookmarks or Blogs. To make SOAP web services available 
for such an app, you must build and deploy its WSDD from the 
[`liferay-portal` GitHub repository](https://github.com/liferay/liferay-portal). 
The apps are in the `liferay-portal/modules/apps` folder. Note that to build 
WSDDs for these apps, you must first download the `liferay-portal` source code 
to your machine. You'll run the WSDD build from your local `liferay-portal` 
copy. 

When you build an app's WSDD, make sure to use `gradlew` in `liferay-portal` 
instead of the `gradle` on your machine. After building, you can find the WSDD 
JAR in the `tools/sdk/dist` folder of your local `liferay-portal` copy. 
Otherwise, building an app's WSDD is the same as in the preceding section. 

For example, to build the WSDD for the Bookmarks app, first navigate to the 
`liferay-portal/modules/apps/collaboration/bookmarks/bookmarks-service` folder 
in your terminal. Then run the following command:

    `../../../../../gradlew buildWSDD`

Next, deploy the 
`liferay-portal/tools/sdk/dist/com.liferay.bookmarks.service-wsdd-[version].jar` 
to your @product@ instance. If your instance is running locally on 
`localhost:8080`, you should then be able to view the Bookmarks app's SOAP 
services at 
[http://localhost:8080/o/com.liferay.bookmarks.service/api/axis](http://localhost:8080/o/com.liferay.bookmarks.service/api/axis). 

Fantastic! Once you've created remote web services, you'll want to know how to 
invoke them. To learn how, see the tutorial 
[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-0/invoking-remote-services). 

## Related Topics [](id=related-topics)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-0/invoking-remote-services)

[Invoking JSON Web Services](/develop/tutorials/-/knowledge_base/7-0/invoking-json-web-services)

[JSON Web Services Invoker](/develop/tutorials/-/knowledge_base/7-0/json-web-services-invoker)

[JSON Web Services Invocation Examples](/develop/tutorials/-/knowledge_base/7-0/json-web-services-invocation-examples)

[What is Service Builder?](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
