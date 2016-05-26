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
be assets they develop or assets that ship with the system.  Assets are shared
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

## Modules and Components [](id=modules-and-components)

<!-- Separate Modules and Components. -->

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

Note: In the OSGi nomenclature, modules are sometimes called OSGi bundles, but
we will avoid that terminology to avoid confusing it with the concept of a
Liferay bundle, which is a distribution of Liferay bundled with an app server. 

$$$

Modules may have one or more *components*. Creating a component can be as easy
as writing a Java class with the `@Component` annotation. In software
engineering terms, a component is the smallest building block of a larger
application, and that application is itself made up of many small components.
This makes it easier to develop an application because you only have to deal
with small, well-defined, bite-sized chunks of code at a time. 

<!-- Why should I have components? What do I need them for? 
Components can also be extension points. A dynamic extension points module. -->

Components are managed by a component container, which keeps track of the
components that are installed and active. Components offer services, and
it's the container's job to manage the available services by providing
components the services they need. It does this through a robust dependency
management system that's handled automatically at runtime. 

<!-- Mention dependency injection and the fact that it's better than Spring
because it's dynamic. -->

As you can see, many components running inside the system form something of an
ecosystem of complementary services. @product@ ships with many components that are
ready to use out of the box, and of course you can develop your own. You can
write components to provide new services or to override existing services with
your own implementation, and the container manages it all. Liferay is an
exciting platform that empowers developers to be more productive. 

<!-- Flesh the above out. Much of Liferay's functionality is in components;
developers are in the same ecosystem with the same extension points. -->
