# Introduction to Liferay Development [](id=introduction-to-liferay-development)

How many times have you had to start over from scratch? Probably almost as many
times as you've started a new project, because each time you have to write not
only the code to build the project, but also the underlying code that supports
the project. It's never a good feeling to have to write the same kind of code
over and over again. But each new project that you do after a while can feel
like that: you're writing a new set of database tables, a new API, a new set of
CSS classes and HTML, a new set of JavaScript functions.

Wouldn't it be great if there were a platform that provided a baseline set of
features that gave you a head start on all that repetitive code? Something that
lets you get right to the features of your app or site, rather than making you
start over every time with the basic building blocks? There is such a thing, and
it's called @product@.

![With @product@, you never have to start from scratch.](../../images/dont-start-from-scratch.jpg)

## Leveraging a Suite of Products, Frameworks and Libraries [](id=leveraging-a-suite-of-products-frameworks-and-libraries)

@product@ offers developers a complete platform for building web apps, mobile
apps, and web services quickly, using features and frameworks designed for rapid
development, good performance, and ease of use. The base platform is already
there, and it's built as a robust container for applications that you can put
together in far less time than you would from scratch.

It also ships with a default set of common applications you can make use of
right away: web experience management, collaboration applications such as forums
and wikis, documents and media, blogs, and more. All of these applications are
designed to be customized, as is the system itself. You can also extend them to
include your own functionality, and this is no hack: because of Liferay's
extensible design, customization is by design.

![@product@ ships with suites of applications to get you started building your site quickly.](../../images/liferay-suites.png)

In short, Liferay was written by developers for developers, to help you get your
work done faster and more easily, to take the drudgery out of web and mobile app
development, so that writing code becomes enjoyable again.

## Build Websites, Intranets, Collaborative Environments, Mobile Apps, and More [](id=build-websites-intranets-collaborative-environments-mobile-apps-and-more)

One of the most often cited best characteristics of Liferay is how versatile it
is. It can be used to build websites of all sorts, from very large websites with
hundreds of thousands of articles, to smaller, highly dynamic and interactive
sites. This includes public sites, internal sites like intranets, or mixed
environments like collaboration platforms.

<!-- Collection of screenshots from Marketing --> 

Developers often choose Liferay for one of these cases and quickly find that
Liferay is a great fit for completely different projects.

## Creating Your Own Applications and Extending the Existing Ones [](id=creating-your-own-applications-and-extending-the-existing-ones)

@product@ is based on the Java platform and can be extended by adding new
applications, customizing existing applications, modifying its behavior, or
creating new themes. You can do this with any programming language supported by
the JVM, such as Java itself, Scala, jRuby, Jython, Groovy, and others.
@product@ is lightweight, can be deployed to a variety of Java EE containers and
app servers, and it supports a variety of databases. Because of its ability to
be customized, you can add support for more app servers or databases without
modifying its source code: just develop and deploy a module with the features
you need.

Speaking of code and deploying, here are some of the most common ways of
expanding or customizing @product@'s features:

1.  Developing a new full-blown web application. The most common way to develop
    web applications for @product@ is with portlets, because they integrate well
    with other existing applications. You are not, however, limited to portlets
    if you don't need to integrate your apps with others.
2.  Customizing an existing web application or feature. @product@ is designed to
    be extended. Many extension points can be leveraged to modify existing
    behavior, and most of these can be developed through a single Java class
    with some annotations (more details later).
3.  Creating a new web service for an external system, a mobile app, an IoT
    device, or anything else.
4.  Developing a mobile app that leverages Liferay as its back-end, which you can
    write in a fraction of the normal time thanks to Liferay Screens and Liferay
    Mobile SDK.
5.  Developing a custom theme that adapts the look and feel of the platform to
    the visual needs of your project.

![@product@ can be used by developers in many ways.](../../images/liferay-developer-roles-diagram.png)

The Liferay platform can be used as a headless platform to develop web or mobile
apps with any technology of your choice (Angular, React, Backbone, Cocoa,
Android's Material Design components, Apache Cordova, etc). It can also be used
as a web integration layer, leveraging technologies such as portlets to allow
several applications to coexist on the same web page.
