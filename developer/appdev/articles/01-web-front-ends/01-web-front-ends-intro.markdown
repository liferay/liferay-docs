---
header-id: web-front-ends
---

# Developing Web Front-Ends

[TOC levels=1-4]

Liferay's open development framework removes barriers so developers can write
applications faster. If you already have an application, you can deploy it on
@product@: 

- Java-based standards (CDI, JSF, Portlets, Spring)
- Front-end standards (Angular, React, Vue)

If you plan to write a new application and deploy it on @product@, you can use
the frameworks you know along with the build tools (Gradle, Maven) you know.
Liferay also offers its own development framework called MVC Portlet that it
uses to develop applications. When you want to integrate with 
[Liferay services](/docs/7-2/appdev/-/knowledge_base/a/services-development)
and frameworks such as permissions, assets, and indexers, you'll find that these
easily and seamlessly blend with your application to provide a great user
experience.

Regardless of your development strategy for applications, you'll find @product@
to be a flexible platform that supports anything you need to write. 

## Using Popular Frameworks

Liferay gives you a head start on developing and deploying apps that use these
popular Java and JavaScript-based technologies:

-   [Angular Widget](/docs/7-2/frameworks/-/knowledge_base/appdev/angular-widget)
-   [React Widget](/docs/7-2/frameworks/-/knowledge_base/appdev/react-widget) 
-   [Vue Widget](/docs/7-2/frameworks/-/knowledge_base/appdev/vue-widget)
-   [Bean Portlet](/docs/7-2/frameworks/-/knowledge_base/appdev/bean-portlet) 
-   [JSF Portlet](/docs/7-2/frameworks/-/knowledge_base/appdev/jsf-portlet)
-   [PortletMVC4Spring Portlet](/docs/7-2/frameworks/-/knowledge_base/appdev/portlet-mvc-for-spring-portletmvc4spring)
-   [Liferay MVC Portlet](/docs/7-2/frameworks/-/knowledge_base/appdev/liferay-mvc-portlet)

| **Note:** The
| [Reference](/docs/7-2/reference)
| section describes samples and templates for creating UIs using other 
| technologies. 

Angular, React, and Vue applications are written the same as you would outside of
@product@---using
[npm](https://www.npmjs.com/)
and the webpack dev server. The Liferay Bundle Generator creates a portlet
bundle (project) for developing and deploying each type of app. The bundle
project comes with npm commands for building, testing, and deploying the app. It
packages the app's dependencies (including JavaScript packages), deploys the
bundle as a JAR, and installs the bundle to @product@'s run time environment,
making your app available as a widget. 

You can also develop web front-ends using Java EE standards.
@product@ supports the
[JSR 362](https://jcp.org/en/jsr/detail?id=362)
Portlet 3.0 standard which is backwards-compatible with the
[JSR 286](http://jcp.org/en/jsr/detail?id=286)
Portlet 2.0 standard from the Java Community Process (JCP). Each portlet
framework has benefits you may wish to consider. 

Bean Portlet is the only framework containing all of the Portlet 3 features:

-   Contexts and Dependency Injection (CDI)
-   Extended method annotations
-   Explicit render state
-   Action, render, and resource parameters
-   Asynchronous support 

If you're a JavaServer Faces (JSF) developer, the
[Liferay Faces Bridge](/docs/7-1/reference/-/knowledge_base/reference/understanding-liferay-faces-bridge)
supports deploying JSF web apps as portlets without writing portlet-specific
Java code. It also contains innovative features that make it possible to
leverage the power of JSF 2.x inside a portlet application. 

If Spring is your thing, Spring Portlet MVC portlets are easy to configure and
deploy on @product@. You can continue using Spring features, including Spring
beans and Spring dependency injection. 

Last but not least, Liferay MVC Portlet continues to be a favorite with
experienced Liferay developers, and makes portlet development easy for Liferay
newcomers. It leverages OSGi Declarative Services (DS) for injecting
dependencies and defining configurable extension points. Since @product@ core
and Liferay-written apps use DS, gaining experience with DS helps you
develop @product@ extensions and customizations. Liferay MVC Portlet works
seamlessly with many Liferay frameworks, such as MVC commands, Service Builder,
and more. 

No matter which development framework you choose, you'll be able to get an app
up and running fast. 

## Getting Started

If you have an existing app that uses one the frameworks described above, your
first step is to deploy it to @product@. Most deployments involve configuration
steps that you can complete in an hour or less. 

You can also build apps from scratch using the tools you like or leveraging
Liferay's tool offering. Liferay provides templates for creating all kinds of
apps and samples that you can examine and modify to fit your needs. 

Once your app is functional, you can improve your app by integrating it with
Liferay frameworks: 

-   Localization
-   Permissions
-   Search and indexing
-   Asset publishing
-   Workflow
-   Staging
-   Data export and import

Liferay provides frameworks that integrate these features fast. As you develop
apps on @product@, you'll enjoy using what you know, discover frameworks and
tools that boost your productivity, and have fun creating rich, full-featured
applications. 

If you're experienced with developing one of the listed app types, feel free to
jump ahead to it. Otherwise, Angular Widgets is next. 
