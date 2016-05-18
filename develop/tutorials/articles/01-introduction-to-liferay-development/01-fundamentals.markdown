# Fundamentals [](id=fundamentals)

@product@ is based on the Java platform and can be extended by adding new
applications, customizing existing applications, modifying its behavior, or
creating new themes. You can do this with any programming language supported by
the JVM, such as Java itself, Scala, jRuby, Jython, Groovy, and others. @product@
is lightweight, can be deployed to a variety of Java EE containers and app
servers, and it supports a variety of databases. Because of its ability to be
customized, you can add support for more app servers or databases without
modifying its source code: just develop and deploy a module with the features
you need. 

Speaking of code and deploying, here are some of the most common ways of
expanding or customizing @product@s features: 

1. Developing a new full-blown web application. The most common way to develop
   web applications for @product@ is with a portlet, because it integrates well
   with other existing applications, but you are not limited to portlets if you
   don't need to integrate your apps with others. 

2. Customizing an existing web application or feature. @product@ is designed to be
   extended. Many extension points can be leveraged to modify existing behavior,
   and most of these can be developed through a single Java class with some
   annotations (more details later). 

3. Creating a new web service for an external system, a mobile app, an IoT
   device, or anything else. 

4. A mobile app that leverages Liferay as a platform, which you can write in a
   fraction of the normal time thanks to Liferay Screens and Liferay Mobile SDK. 

5. Developing a custom theme that adapts the look and feel of the platform to
   the visual needs of your project. 

Liferay provides a convenient and easy-to-use framework called `MVCPortlet` to
make writing portlets easy, but developers are free to use any other framework,
such as Spring MVC, to create portlets. 

Liferay includes a utility called *Service Builder* that makes it easy to create
back-end database tables, an object-relational map in Java for accessing
them, and a place to put your business logic. It can also generate JSON or SOAP
web services, giving developers a full stack for storing and retrieving data
using web or mobile clients.

Applications can be created by leveraging Liferay's many building blocks that
are commonly needed by today's applications. For example, a commenting system
allows developers to attach comments to any asset that they define, whether they
be assets they develop or assets that ship with the system. Assets are shared
by the system and are used to represent many common elements, such as Users,
Organizations, Sites, User Groups, blog entries, and even folders and files. 

Liferay also includes many frameworks for operating on assets. A workflow system
makes it easy to create applications that require an approval process for users
to follow. The recycle bin stores deleted assets for a specified period of time,
making it easy for users to restore data without the intervention of an
administrator. A file storage API with multiple available back-ends makes
storing and sharing files trivial. Search is built into the system as well, and
it is designed for developers to integrate it with their applications. Almost
any framework you might think of using or developing is already there; you just
need to take advantage of it: a Social Networking API, user-generated forms with
data lists, a message bus, an audit system, and much more. 

The same is true for customization. Almost anything in Liferay can be
customized. Application interfaces can be redesigned, the whole user interface
can be themed, and menu items can be added or removed. 

## Modules 


All applications, extensions, and customizations built on Liferay are
distributed and deployed as *modules*. A module is just a typical JAR file,
familiar to Java developers as a ZIP file containing compiled code, templates,
resources, and some meta information. 

In the spirit of following existing standards, Liferay has not invented its own
format for modules. Instead, it follows a set of very powerful standards known
as OSGi. OSGi defines, among other things, how modules can depend on each other,
communicate, replace their implementations, and be deployed in real time without
taking the server down. 

+$$$

Note: In the OSGi nomenclature, modules are sometimes called OSGi bundles. An
attempt has been made to be consistent and only use the word *modules* to refer
to modules, but if a *bundle* has slipped in here and there, just know they're
the same thing. 

$$$

## Components

Modules may contain one or more *components*. A component is a POJO whose
lifecycle is managed by the container. In Liferay, a component is generally part
of the Declarative Services framework. Creating a component can be as easy as
writing a Java class with the `@Component` annotation. In software engineering
terms, a component is the smallest building block of a larger application, and
that application is itself made up of many small components.  This makes it
easier to develop an application because you only have to deal with small,
well-defined, bite-sized chunks of code at a time. 

Components are managed by a component container, which keeps track of the
components that are installed and active. In this way, components have these
benefits: 

- The container can start and stop components. 
- A component *may* publish itself as an OSGi service, but not all components
    are services. 
- A component may use or consume an OSGi service. 
- The framework can manage the binding of the services a component consumes. 

It's the container's job to manage the available services by providing
components the services they need. It does this through a robust dependency
management system that's handled automatically at runtime. The benefit to you as
a developer is a framework defining a contract allowing simple POJOs to interact
with each other through OSGi services, with all the necessary
infrastructure--such as dependency injection and service ranking--that comes
with it. And because you have a container that manages it all, it's
dynamic--meaning restarts are not necessary for starting, stopping, adding, or
removing components and services. 

The next question then becomes, so what? Why is this a big deal? Why should I
have components, and what do I need them for? 

Picture this: you have a system that generates a report in PDF format from data
in a database. The data is captured from a web application running in Liferay.
You come in to work in the morning and something's happened (it doesn't matter
what it is; it could be corrupt data, the company has been bought, or a national
emergency). You need to change that report as fast as possible, either to insert
a new title page, add a warning to the existing title page, or whatever. 

In the old, monolithic model, you'd have to modify the application to change the
report and then you'd have to redeploy it. If this was a temporary change and
eventually you'd have to restore the application to its previous functionality,
you'd again have to modify the application and redeploy it. 

With a component-based application, you'd write a simple, small
component--probably one Java class--that extends the existing component and
provides the functionality you need. You'd then deploy your component to the
server with a higher priority than the existing component. The container would
then handle instantiating your new component in place of the old one, whenever
that functionality is needed. If you need to roll back that change in the
future, you'd simply undeploy your component, and the container will then supply
the original component to the application automatically. At no time would you
ever have to redeploy the whole application or take the server down. 

As you can see, many components running inside Liferay's OSGi container form
something of an ecosystem of complementary services. Much of Liferay's
functionality is in components, and your code is now in the same ecosystem as
Liferay's, with the same extension points. You can write components to provide
new services or to override existing services with your own implementation, and
the container manages it all. Liferay is an exciting platform that empowers
developers to be more productive. 

