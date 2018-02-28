# Fundamentals [](id=fundamentals)

What are the fundamentals that every Liferay developer should know?

1.  It's Open Source and puts a strong emphasis on following standards, instead
    of reinventing the wheel.
2.  It's based on Java EE and heavily leverages OSGi and several other popular
    technologies for the Java Platform.
3.  It is based on a modular architecture and facilitates following a modular
    development paradigm for your own projects.
4.  You can build your own web applications, portlets, or mobile apps on top of
    it.
5.  It provides mature development tools, while staying agnostic so you
    can use tools you prefer.
6.  It's all about reusing, providing reusable frameworks and libraries and
    allowing you to create your own.

Interested? More details below. 

## Open Source and based on Standards [](id=open-source-and-based-on-standards)

@product@ is both Open Source and built in the open, following a collaborative
development model. That means that you can follow new development as it's
happening, make comments on it, and contribute! Here are some tools that you can
use to do all this:

1.  Our ticketing system. All product changes, including all bug fixes,
    improvements, and new features start with a ticket created in JIRA. We have
    several projects there, but the main one for tracking @product@ work or for
    reporting bugs you find (with as many details as you can and steps to
    reproduce, of course) is [LPS](https://issues.liferay.com/browse/LPS).

2.  [GitHub](http://github.com/liferay): The home of our source code. You can
    use it to see the code changes as they happen and also to send pull requests
    for improvements. There are also many repos, but the main one is 
    [liferay-portal](http://github.com/liferay/liferay-portal).

3.  [Forums](http://forums.liferay.com): It's where our community gets together
    to share ideas, discuss, and collaborate. Go ahead and ask your questions
    and help others ask theirs.

4.  [Blogs](http://blogs.liferay.com): Read the latest news, advice, and best
    practices from key core developers and our most active community members.

5.  [Participate](http://dev.liferay.com/participate): Learn how to get started
    participating. There are options for all levels of expertise and time
    availability.

In addition to being Open Source, Liferay is also heavily based on standards.
This is great news for your project, since it significantly reduces the lock-in
on Liferay. That also encourages us to improve constantly. 

Here are some key standards @product@ supports:

-   [Portlets 1.0](https://jcp.org/en/jsr/detail?id%3D168) (JSR-168) 
    and [Portlets 2.0](https://jcp.org/en/jsr/detail?id%3D286) (JSR-286):
    @product@ can run any portlets that follow these two versions of the
    specification. Liferay is also heavily involved in the upcoming Portlets 3.0
    specification.
-   [JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) (JSR-127,
    JSR-314, JSR-344): The Java standard for building component based web
    applications. Liferay is an active contributor to the standard and lead of
    the JSF-Portlet Bridge specification.
-   [EcmaScript 2015](http://www.ecma-international.org/ecma-262/6.0/):
    The latest incarnation of the JavaScript standard. Liferay's tooling
    provides the ability to use it in all modern browsers thanks to the
    integration of Babel JS.
-   [Content Management Interoperability Services](https://www.oasis-open.org/committees/cmis) (CMIS):
    Liferay's
    [Documents and Media](/discover/portal/-/knowledge_base/7-1/managing-documents-and-media)
    can behave as an interface for any external
    Documents Repository that supports this widely adopted standard.
-   [Java Content Repository](https://jcp.org/en/jsr/detail?id%3D170) (JSR-170):
    Files stored in the internal repository of Liferay's
    [Documents and Media can be configured](/discover/deployment/-/knowledge_base/7-1/document-repository-configuration)
    to be stored in a JSR-170 compatible repository if desired.
-   [WebDAV](http://www.webdav.org):
    Any Documents &amp; Media folder can be mounted anywhere WebDAV is
    supported, such as Windows explorer or WebDAV-specific clients.
-   [SAML](http://saml.xml.org) and
    [OAuth 1.1](http://oauth.net/core/1.1):
    These are the most widely adopted security protocols for SSO and application
    sign in, supported through specific Apps that can be installed from
    [Liferay's Marketplace](http://marketplace.liferay.com).
-   [JAX-WS and JAX-RS](/develop/tutorials/-/knowledge_base/7-1/jax-ws-and-jax-rs):
    Incorporated since Liferay 7 as the preferred tooling to create web
    services.
<!--
    -   [WSRP](https://www.oasis-open.org/committees/wsrp) 1
    and 2: Allows execution of portlets running in a remote container. TODO uncomment once the WSRP app is released on Marketplace.
-->
-   [OSGi r6](https://www.osgi.org):
    Liferay supports a wide range of the OSGi family of standards through its
    own implementations and also integrates the high quality implementations of
    the Apache Felix and Eclipse Equinox projects (which we also collaborate).
    Here are some of the most relevant supported standards:

    -   OSGi runtime: Allowing any OSGi module to run in
        @product@
    -   Declarative Services: Supports a dynamic component model for
        Liferay development.
    -   Configuration Admin: Lets you create highly configurable
        applications that can be reconfigured on the fly. Liferay provides
        an auto-generated UI to change the configuration of any component
        that leverages this standard.

## Technologies [](id=technologies)

Like any open source application, Liferay is built on the shoulders of giants.
When we choose the technology on which to build our platform, it must have the
following characteristics: 

-   It must balance being modern and being mature enough for
    demanding and critical enterprise environments.
-   It should be widely adopted and have a mature community.
-   It should be as easy as possible to contribute back, since we
    love to contribute to the Open Source projects we use. 
-   It should be possible to use only the piece of the project we
    need if we don't need the whole thing. That way, it's easier to
    replace that piece in the future if we find something that
    works better. 

The goal, of course, is to give our developers and users the most up to date,
easy-to-use, and stable platform to build services on.

![Liferay is based on popular, well known, and well supported technologies.](../../images/liferay-technologies.png)

At its base, Liferay is a JavaEE application that also includes an OSGi
container. This offers the best of both worlds: access to the world's most
robust and fully featured enterprise platform, along with the benefits of the
world's most fully featured and stable modular container. Now you can develop
and deploy enterprise-ready, scalable web and mobile-based applications in a
dynamic, component-based environment. 

With Java EE and OSGi at the bottom of the stack, we build the rest of our core
on well known or widely used products: 

-   Spring for transactions (and Dependency Injection in
    the core)
-   Hibernate for database access (along with direct JDBC access
    for optimized queries)
-   Elasticsearch for indexing and searching
-   Ehcache for caching. 

In the application layer, developers have access to many of the libraries
they're familiar with and have been using for years: 

-   Xalan 
-   Xerces 
-   Apache Commons 
-   Tika 
-   dom4j

If you're approaching @product@ with the intention of customizing it, you can
know that most if not all of the tools you're familiar with are there. If you're
writing applications on Liferay, the sky's the limit: you can use any
web framework you like, and you can write both servlet and portlet-based
applications. If you're looking for a recommendation, though, we're happy to
point you to either our
[MVCPortlet](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet)
or our JSF-based
[LiferayFaces](/develop/tutorials/-/knowledge_base/7-1/jsf-portlets-with-liferay-faces)
frameworks. 

On the front-end, Liferay has kept pace with the most recent progressions in
that space. If you've used Liferay in the past, you can of course continue to
use Liferay's venerable Alloy UI, but you are also free to use the front-end
technologies you love the most:

-   Bootstrap 
-   SaSS
-   [EcmaScript 2015](http://www.ecma-international.org/ecma-262/6.0/) (using Babel.js) 

You can also use any JavaScript library, including 

-   [Metal.js](http://metaljs.com/) (developed by Liferay)
-   [jQuery](https://jquery.com/) (included) 
-   Lodash (included)
-   Angular 1 or 2
-   React
-   Your library of choice

@product@ follows a design language created by our designers at Liferay called 
[Lexicon Experience Language](https://lexicondesign.io/), which has been 
implemented for use of the web as [Lexicon](https://liferay.github.io/clay/).

Lexicon is automatically made available to you through a set of CSS classes and
markup, although it's even easier to use our tag library. 

For templating, Java EE's JSP is there as expected as well as FreeMarker, but the
modularity of the platform allows you to use Google's Soy (aka Closure
Templates) or whatever else you like. 

Liferay has also chosen build tools that give you freedom to use any development
environment. Gradle along with bnd powers the product's build, but project
layouts are dynamic, which means you can use anything from Maven to Ant/Ivy to
build applications for Liferay. 

In short, Liferay has done a lot to make sure its users and developers have
access to the most widely used, robust tools possible--as well as the freedom to
use the tools they like the most. Know that Liferay has your back and will do
everything we can to provide you with the most flexible technology platform
possible, so that you have the freedom to go and build great things on
it--things we never could have expected or imagined. 

## Architecture [](id=architecture)

Liferay's design goals have from the beginning been to give you all the tools to
create exactly the web presence you have in mind. To achieve this, the product
must do these things: 

-   Provide a usable default configuration and interface
-   Ship with best-of-breed apps that you can use to build sites quickly
-   Make the UI customizable at any level of detail from small
    tweaks to a complete replacement
-   Make the apps customizable at any level of detail 
-   Provide a robust development platform upon which you can build and share new
    best-of-breed apps

![Liferay provides services for many different kinds of clients.](../../images/liferay-architecture.png)
These goals are now achieved to the furthest extent ever in Liferay's history,
and it's all because of our new modular architecture.

Imagine an environment where every piece of functionality is an independent
module. The modules declare three important things: 

-   The functionality they implement or define
-   Their dependency on other modules
-   Their priority relative to their functionality

Using this information, the container can start all the modules that fulfill
their definitions, implementations, dependencies, and priorities. 

Anything a developer wants to do is implemented as one or more modules. If it's 
a new application, that application can depend on existing modules and 
[define a dependency](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies) 
on them. This enables you to use functionality that's already there without 
rewriting it yourself for your app. If it's a customization, in many cases it's 
just a simple matter of defining your customization with a higher priority than 
the existing functionality. 

This is the power of a modular architecture. 

### Modules [](id=modules)

All new applications, extensions, and customizations built on Liferay are
built in a
[modular way](/develop/tutorials/-/knowledge_base/7-1/the-benefits-of-modularity).
A module is the single unit of distribution and deployment in a modular
architecture.

In the spirit of following existing standards, Liferay has leveraged a set of 
very powerful standards known as
[OSGi](https://www.osgi.org/developer/).
OSGi defines, among other things, how modules can depend on each other and
communicate. It also defines the packaging format for modules: OSGi bundles. An
OSGi module is just a typical JAR file, familiar to Java developers as a ZIP
file containing compiled code, templates, resources, and some meta information.

### Services [](id=services)

One aspect of modern software architecture is the notion of services. These are
independently running pieces of code that provide specific functionality when
called. They operate just like services in the real world do. For example, you
might call a service to come mow your lawn. You know how to call the service and
to give it what it needs (money) in order to receive the service (a mown lawn).
Software-based services work the same way.

Liferay's services are standard services as defined by the
[OSGi Alliance](https://www.osgi.org/about-us/).
Writing anything, whether it be an application, an interface to a database, or
even a "service" as you define it, is easy to implement as an OSGi service,
because they're both incredibly powerful and easy to develop. If you understand
Java interfaces and how they are implemented--which is introductory Java
material--you already understand more than 90% of what you need to know. First,
you define the interface, or contract for the service: what it returns, and what
it needs to return what it returns. Next, you define an implementation class
that implements the contract. 

In the services model, a class requests the service that provides the
functionality it needs. This functionality is provided (often injected) with the
right implementation automatically. It's similar to Spring or EJBs with one
important addition: implementations can be changed at runtime, without
restarting the system. This is achieved because when a service is deployed, it
becomes part of a service registry maintained by Liferay's OSGi container. The
container dynamically manages the lifecycle of the service and can start and
stop services when appropriate. 

The real power of services shines when they are extended. You can replace
existing implementations or in advanced use cases have several implementations
of a service. The developer can then choose to invoke all implementations or
just the one with the highest priority (specified with what is called the
service ranking). This means that if Liferay has a service that does something,
you can customize or override that service by implementing its interface
yourself and then deploying it with a higher ranking than the original service.
The container then instantiates your implementation when the service is called
by existing code. This simple, clean method is how most customizations are made
to Liferay 7. 

### Components [](id=components)

In OSGi, possibly the best and certainly the easiest way to create services is
through Declarative Services. In Declarative Services (aka DS), you create
Components. A Component is a Java class (marked with an `@Component` annotation)
that provides an implementation of a Service (as described above) and whose
instantiation is handled automatically by DS. This is similar to what you might
be used to if you have used Spring Beans or EJBs. DS also provides dependency
injection using annotations (`@Reference`). This is convenient because the
"wiring" of components is done by the container but can be changed while the
server is running (unlike Spring). 

Modules may contain as many service declarations and as many components as
desired (or zero, of course). 

In software engineering terms, a component is the smallest building block of a
larger application, and that application is itself made up of many small
components. This makes it easier to develop an application because you only have
to deal with small, well-defined, bite-sized chunks of code at a time.

### Real Life Benefits of Modular Development [](id=real-life-benefits-of-modular-development)

The next question then becomes, so what? Why is this a big deal? Why should I
have components, and what do I need them for?

It helps to examine two common development scenarios: a customization task and a
full-blown application. Picture this: you have a system that generates a report
in PDF format from data in a database. The data is captured from a web
application running in Liferay. You come in to work in the morning and
something's happened (it doesn't matter what it is; it could be corrupt data,
the company has been bought, or a national emergency). You need to change that
report as fast as possible, either to insert a new title page, add a warning to
the existing title page, or whatever.

In the monolithic model you'd have to modify the application to change the
report and then you'd have to redeploy the complete application. If this was a
temporary change, to restore the application to its original state you'd again
have to modify the application and redeploy it.

With a modular and component-based application, you'd fix a simple, small
component--probably one Java class--that provides the functionality you need.
You'd then deploy its module to the server. If you need to roll back that change
in the future, you'd just do the same thing in reverse. In each case, you're
only changing and redeploying the small piece of functionality that needs to
change, not the whole application. At no time would you ever have to redeploy
the whole application or take the server down.

For a full-blown application, the benefits are even greater. Modular
development helps developers be more efficient in three important ways: 

-   An application made up of components can be written in
    parallel by multiple developers working on different components.
-   An existing application can be extended by writing new
    components to implement features in different ways. 
-   Components can be enabled and disabled, allowing
    administrators to choose which features to enable in production.

For example, Liferay's Documents and Media library is a file repository that
supports many back-ends. Each back-end is a component that can be maintained by
different developers. They can be added and removed on the fly while the server
is running. 

Similarly, the services provided by the application are independent of the
front-end technology. In fact, there can be multiple front-ends, from the
web-based front-end Liferay provides out of the box, to a new front-end you
might develop for either the web or mobile.

As you can see, many components running inside Liferay's OSGi container form
something of an ecosystem of complementary services. Much of Liferay's
functionality is in components, and when you deploy your code, it sits in the
same ecosystem as Liferay's, with the same extension points. You can write
components to provide new services or to override existing services with your
own implementation, and the container manages it all. Liferay is an exciting
platform that empowers developers to be more productive.
