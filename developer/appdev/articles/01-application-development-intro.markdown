# Application Development 

Writing applications on Liferay's standards-based platform makes your life
easier. Whether you create headless services for clients to access, full-blown
web applications with beautiful UIs, or anything in between, @product@
streamlines the process to help you get your job done faster. 

Liferay's framework embraces your existing tools and build environments like
[Maven](https://maven.apache.org) and [Gradle](https://gradle.org). You can work
with the standard technologies you know and leverage Liferay's APIs for
Documents, Permissions, Search, or Content when you need them. Here's a high
level view of what you can do: 

-   **Deployment of existing standards-based apps:** If you have an existing app 
    built outside of @product@, you can deploy it on @product@. The Liferay
    Bundler Generator and Liferay npm Bundler provide the project scaffolding
    and packaging to deploy [Angular](https://angular.io/), [React](https://reactjs.org/), and [Vue](https://vuejs.org/) web front-ends as Widgets.
    Spring Portlet MVC app conversion to [PortletMVC4Spring](https://github.com/liferay/portletmvc4spring) requires only a few
    steps. JSF applications work almost as-is. Portlet 3.0 or 2.0 compliant
    portlets deploy on @product@. 

-   **Back-end Java services, web services, and REST services:** Service Builder
    is an object-relational mapper where you describe your data model in
    a single `xml` file. From this, you can generate the tables, a Java API for
    accessing your data model, and web services. On top of these, REST Builder
    generates OpenAPI-based REST services your client applications can call. 

-   **Authentication and single-sign on (SSO):** OAuth 2.0, OpenID Connect, and
    SAML are built-in and ready to go. 

-   **Front-end web development using Java EE and/or JavaScript:** Use Java EE
    standard Portlet technology (JSR 168, JSR 286, JSR 362) with CDI and/or JSF.
    Prefer Spring? [PortletMVC4Spring](https://github.com/liferay/portletmvc4spring) brings the Spring MVC Framework to Liferay. 
    Rather have a client-side app? Write it in [Angular](https://angular.io/),
    [React](https://reactjs.org/), or [Vue](https://vuejs.org/). Been using
    @product@ for a while? Liferay MVC Portlet is better than ever. 

-   **Frameworks and APIs for every need:** Be more productive by using
    Liferay's built-in and well-tested APIs that cover often-used features like
    file management(upload/download), permissions, comments, out-of-process
    messaging, or UI elements such as data tables and item selectors. @product@
    offers many APIs for every need, from an entire workflow framework to
    a streamlined way of getting request parameters.

-   **Tool freedom:** Liferay provides Maven archetypes, [Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace),
    [Gradle](/docs/7-2/reference/-/knowledge_base/r/gradle-plugins) and [Maven](/docs/7-2/reference/-/knowledge_base/r/maven-plugins) plugins, a [Yeoman](http://yeoman.io/)-based [theme generator](/docs/7-2/reference/-/knowledge_base/r/theme-generator), and [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli)
    to integrate with any development workflow. On top of that, you can use our
    [IntelliJ plugin](/docs/7-2/reference/-/knowledge_base/r/intellij) or the
    Eclipse-based [Liferay Developer Studio](/docs/7-2/reference/-/knowledge_base/r/liferay-dev-studio) if you need 
    a full-blown development environment. 

-   **Developer community:** The
    [@product@ community](https://liferay.dev)
    is helpful and active. 

## Getting Started with Liferay Development

Want to see what it's like to develop an app on @product@? Here's a quick tour. 

### Create Your Object Model and Database in One Shot

You don't need a database to work with Liferay, but if your app uses one, you
can design it and your object model at the same time with Liferay's
object-relational mapper, [Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder). 
You define your object model in a single `xml` file: 

```xml
<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.2.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_0_0.dtd">
<service-builder auto-namespace-tables="true" package-path="com.liferay.docs.guestbook">
    <author>liferay</author>
    <namespace>GB</namespace>
    <entity name="Guestbook" local-service="true" remote-service="true" uuid="true">

        <column name="guestbookId" primary="true" type="long" />
        <column name="name" type="String" />

        <finder name="Name" return-type="Collection"/>
            <finder-column name="name" />
        </finder>

    </entity>

	<entity name="Entry" local-service="true" remote-service="true" uuid="true">
	
	    <column name="entryId" primary="true" type="long" />
        <column name="name" type="String" />
        <column name="email" type="String" />
        <column name="message" type="String" />
        <column name="guestbookId" type="long" />

        <finder name="Email" return-type="Collection" />
            <finder-column name="email" />
        </finder>

    </entity>

</service-builder>
```

Service Builder, generates your object model, database SOAP, and JSON web
services automatically. Java classes are ready for you to implement your
business logic around generated CRUD operations. The web services are mapped to
your business logic. If you want a REST interface, you can create one. 

### Create a REST Interface

[REST Builder](/docs/7-2/appdev/-/knowledge_base/a/rest-builder) helps you define
REST interfaces for your APIs, using [OpenAPI/Swagger](https://swagger.io/docs/specification/about/). 
Create your [YAML definition](https://swagger.io/docs/specification/basic-structure/) 
file for your REST interface along with a configuration file defining where Java
classes, a client, and tests should be generated, and you have REST endpoints
ready to call your API. 

Next, you need a client. You can use @product@ in headless mode and write your
web and mobile clients any way you want. Or you can create your web clients on
Liferay's platform and take advantage of its many tools and APIs that speed up
development. 

### Create a Web Client

@product@ is an ideal platform upon which to build a web client. Its Java
EE-based technology means you can pick from the best it has to offer: Spring MVC
using [PortletMVC4Spring](https://github.com/liferay/portletmvc4spring), the new
backwards-compatible Portlet 3, JSF using [Liferay Faces](https://liferayfaces.org), 
or the venerable OSGi-based [Liferay MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet). 
If you're a front-end developer, deploy your Angular, React, or Vue-based
front-end applications to run as widgets next to the rest of @product@'s
installed applications. 

### Use Liferay's Frameworks

Your apps need features. Liferay has implemented tons of common functionality
you can use in your applications. The [Liferay-UI](@platform-ref@/7.2-latest/taglibs/util-taglib/liferay-ui/tld-summary.html) tag library 
has tons of web components like Search Container (a sortable data table),
panels, buttons, and more. Liferay's [Asset Framework](/docs/7-2/frameworks/-/knowledge_base/f/asset-framework)
can publish data from your application in context wherever users need it---as
a notification, a related asset, as tagged or categorized data, or as relevant
data based on a [user segment](docs/7-2/user/-/knowledge_base/u/creating-user-segments). Need to provide file upload/download? Use the 
[Documents API](/docs/7-2/frameworks/-/knowledge_base/f/documents-and-media-api). Need a robust permissions system? Use [Liferay permissions](/docs/7-2/frameworks/-/knowledge_base/f/defining-application-permissions).
Want users to submit comments? Use Liferay's [comments](/docs/7-2/frameworks/-/knowledge_base/f/adding-comments-to-your-app).
Need to process data outside the request/response? Use the [Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/message-bus). 
Should users select items from a list? Use the [Item Selector](/docs/7-2/frameworks/-/knowledge_base/f/item-selector).

## Next Steps

So what's next? [Download](/download) @product@ and [create your first project](/docs/7-2/reference/-/knowledge_base/r/creating-a-project)! 
Have a look at our [back-end](/docs/7-2/appdev/-/knowledge_base/a/service-builder), [REST Builder](/docs/7-2/appdev/-/knowledge_base/a/rest-builder), and [front-end](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends) docs, 
examine what Liferay's [frameworks](/docs/7-2/frameworks) have to offer, and
then go create the beautiful things that only you can make. 
