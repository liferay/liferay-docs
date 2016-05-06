# Creating Remote Services [](id=creating-remote-services)

Many default Liferay services are available as web services. Liferay exposes its
web services via JSON and SOAP web services. If you're running Liferay locally
on port 8080, visit the following URL to browse Liferay's default JSON web
services:

    http://localhost:8080/api/jsonws/

To browse Liferay's default SOAP web services, visit this URL:

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

As an example, consider Liferay's Blogs app. Articles are represented by the 
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
deploy your project and check the Liferay JSON web services URL 
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

Liferay uses Apache Axis to make SOAP web services available. Since Axis 
requires a WSDD to make a plugin's remote services available via SOAP, you must 
build and deploy a WSDD for your plugin. To create your WSDD, Liferay provides 
the `buildWSDD` Gradle task as part of the WSDD Builder Gradle plugin. To use 
this plugin, you must add it as a dependency in your `*-service` project's 
`build.gradle` file, and then apply it to your project. For example, the 
following part of an example `*-service` project's `build.gradle` file includes 
Service Builder and the WSDD Builder plugin: 

    buildscript {
        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins.service.builder", version: "latest.release"
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins.wsdd.builder", version: "latest.release"
        }
    }

    apply plugin: "com.liferay.portal.tools.service.builder"
    apply plugin: "com.liferay.portal.tools.wsdd.builder"
    ...

Next, run `gradle buildWSDD` in your `*-service` project directory. In your 
`*-service` project's `build/libs` folder, the `buildWSDD` task generates a 
`*-service-wsdd-[version].jar` file that contains your WSDD. Deploy this JAR to 
your Liferay instance. Your SOAP web services are then available at a URL that 
uses the following pattern: 

    yourportaladdress/o/your.apps.service.module.context/api/axis

For example, if an app called Foo consists of the modules `foo-api`, 
`foo-service`, and `foo-web`, then the app's service module context is 
`foo-service`. If this app is deployed to a local Liferay instance running at 
`http://localhost:8080`, you could access its SOAP services at: 

    http://localhost:8080/o/foo-service/api/axis

If you don't know an app's `*-service` module context, you can find it by 
searching for the app in the App Manager. For example, the following screenshot 
shows the Foo app's modules. The name of the `*-service` module in the App 
Manager is also its context. Also note that the app's WSDD module is grayed out 
and listed as Resolved instead of Active. This is normal. WSDD modules are OSGi 
fragments and can't be activated. They still work as intended, though. 

![Figure 1: To find your app's modules, including its WSDD module, search for your app in the App Manager. The `*-service` module's name in the App Manager is also its context.](../../../images/app-manager-remote-services.png)

Next, you'll learn how to build the WSDD module for Liferay's built-in apps that 
don't include a WSDD by default. If you don't need to do this, you can move on 
to the tutorial 
[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-0/invoking-remote-services). 

## Building the WSDD for Built-in Liferay Apps [](id=building-the-wsdd-for-built-in-liferay-apps)

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
to your Liferay instance. If your Liferay instance is running locally on 
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
