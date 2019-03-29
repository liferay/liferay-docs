---
header-id: ui-development
---

# UI Development

[TOC levels=1-4]

Liferay celebrates and encourages you to develop UIs your way. Use the
programming languages and frameworks you like that the JVM supports. Liferay
provides mechanisms for building, testing, and deploying apps written using all
kinds of popular technologies. The point is, Liferay makes it easy to get apps
up and running on your @product@ site. 

Services (web services and local services) are a key ingredient to apps too.
Whether they're @product@'s services, third-party services, or
[services you develop](/docs/7-2/frameworks/-/knowledge_base/appdev/services-development),
you'll learn how to connect with them here. 

## Using Popular Frameworks

Liferay gives you a jumpstart on deploying and developing apps that use these
popular Java and JavaScript-based technologies:

-   [Angular Widget](/docs/7-2/frameworks/-/knowledge_base/appdev/angular-widget)
-   [React Widget](/docs/7-2/frameworks/-/knowledge_base/appdev/react-widget) 
-   [Vue Widget](/docs/7-2/frameworks/-/knowledge_base/appdev/vue-widget)
-   [Bean Portlet](/docs/7-2/frameworks/-/knowledge_base/appdev/bean-portlet) 
-   [JSF Portlet](/docs/7-2/frameworks/-/knowledge_base/appdev/jsf-portlet)
-   [Spring Portlet MVC](/docs/7-2/frameworks/-/knowledge_base/appdev/spring-portlet-mvc)
-   [Liferay MVC Portlet](/docs/7-2/frameworks/-/knowledge_base/appdev/liferay-mvc-portlet)

| **Note:** The
| [Reference](/docs/7-2/frameworks/-/knowledge_base/reference/reference)
| section describes samples and templates for creating UIs using other 
| technologies. 

Angular, React, and Vue widgets are developed the same as you would outside of
@product@---using
[npm](https://www.npmjs.com/)
and the webpack dev server. The Liferay Bundle Generator creates a portlet
bundle (project) for developing and deploying each type of app. The bundle
project comes with npm commands for building, testing, and deploying the app. It
packages the app's dependencies (including JavaScript packages), deploys the
bundle as a JAR, and installs the bundle to @product@'s run time environment,
making your app available as a widget. 

The other ways of developing UIs involve Java-based portlet development.
@product@'s supports the
[JSR 362](https://jcp.org/en/jsr/detail?id=362)
Portlet 3.0 standard and continues to support the
[JSR 286](http://jcp.org/en/jsr/detail?id=286)
Portlet 2.0 standard from the Java Community Process (JCP). If you already use
one of these frameworks, it's natural to gravitate to it. But it can be
worthwhile to consider the benefits each portlet framework offers. 

Bean Portlet lets you develop using POJOs and it is the only framework that lets you use all of the Portlet 3 features, including: 

-   Contexts and Dependency Injection (CDI)
-   Extended method annotations
-   Explicit render state
-   Action, render, and resource parameters
-   Asynchronous support 

If you're a Java EE fan and like JavaServer Faces (JSF), Liferay's got you
covered. The
[Liferay Faces Bridge](/develop/reference/-/knowledge_base/7-1/understanding-liferay-faces-bridge)
supports deploying JSF web apps as portlets without writing portlet-specific
Java code. It also contains innovative features that make it possible to
leverage the power of JSF 2.x inside a portlet application. 

If Spring is your thing, know that Spring Portlet MVC portlets are easy to
configure and deploy on @product@. You can continue using Spring features,
including Spring beans and Spring dependency injection. 

Last but not least, Liferay MVC Portlet continues to be a favorite with
developers experienced with Liferay and has attractive features for Liferay
newcomers. It leverages OSGi Declarative Services (DS) for injecting
dependencies and defining configurable extension points. Since @product@ core
and Liferay apps use DS, gaining experience with DS makes it easier to develop
@product@ extensions and customizations. Liferay MVC Portlet works seamlessly
with many Liferay frameworks, such as MVC commands, Service Builder, and more. 

No matter which development framework you choose, you'll be able to get an app
up and running fast. 

## Getting Started

If you have an existing app developed using one the frameworks described above,
your first step is to deploy it to @product@. Most deployments involve
configuration steps that you can complete in an hour. 

You can also build apps from scratch using the tools you like or leveraging
Liferay's tool offering. Liferay provides templates for creating all kinds of
apps and samples that you can examine and modify to fit your needs. 

Once your app is functional, you can improve your app with features such as
these: 

-   Localization
-   Permissions
-   Search and indexing
-   Workflow
-   Staging
-   Data export and import

@product@ integrates with standard frameworks that implement these features and
Liferay also provides frameworks that integrate these features fast. As you
develop apps on @product@, you'll enjoy using what you know, discover frameworks
and tools that boost your productivity, and have fun creating rich full-featured
applications. 

If you're already experienced with developing one of the listed app types, feel
free to jump ahead to it. Otherwise, Angular Widgets is next. 
