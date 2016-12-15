# OSGi and Modularity for Liferay Portal 6 Developers [](id=osgi-and-modularity-for-liferay-6-developers)

To create a powerful, reliable platform for developing modular applications,
Liferay sought best-of-breed standards-based frameworks and technologies.
It was imperative not only to meet demands for enterprise digital experiences
but also to offer developers, both experienced with Liferay and new to Liferay,
a clear and elegant way to create apps.

Here were some of the key goals:

- Allow breaking down a large system into smaller pieces of code, whose
boundaries and relationships could be clearly defined.

- Explicitly differentiate public APIs from private APIs.

- Facilitate extensibility of existing code.

- Modernize the development environment, leveraging more state-of-the-art tools
to provide a great developer experience.

It wasn't long before Liferay discovered that OSGi and its supporting
tools/technologies fit the bill!

In this tutorial, you'll learn how @product-ver@ uses OSGi to meet these objectives.
And equally important, you'll find out how easy and fun modular development can
be.

Here are the topics you'll dig into:

1.  [Modules as an Improvement over Traditional Plugins](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers#modules-as-an-improvement-over-traditional-plugins):
    Development and customization of applications for Liferay has been done
    traditionally in plugins (Portlet, Hook, Ext and Web). In @product-ver@,
    plugins are replaced with (and can be automatically converted to) modules.
    You'll see the similarities and differences of plugins and modules, and
    you'll learn the benefits of using modules.

2.  [Leveraging Dependencies](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers#leveraging-dependencies):
    In @product-ver@, developers can both declare dependencies among modules and
    combine modules to create applications. Since leveraging dependencies
    provides huge benefits, it's important to devote a lot of space to it.

3.  [OSGi Services and Dependency Injection](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers#osgi-services-and-dependency-injection-with-declarative-services):
    OSGi provides a powerful concept called OSGi Services (also known as
    microservices). OSGi's Declarative Services standard provides a clean way to
    inject dependencies in a dynamic environment. This is similar to Spring DI,
    except the changes happen while the system is running. It also offers an
    elegant extensibility model that @product-ver@ leverages extensively.

4.  [Dynamic Deployment](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers#dynamic-deployment):
    Module deployment is managed by @product-ver@ (not the application server).
    This section demonstrates how to use dynamic deployment for better control
    and efficiency.

After investigating these topics, you'll get hands-on experience creating and
deploying an OSGi module. 

Let's start with learning how modules are better than traditional plugins.

## Modules as an Improvement over Traditional Plugins [](id=modules-as-an-improvement-over-traditional-plugins)

In @product-ver@, you can develop applications using OSGi modules or using
traditional Liferay plugins (WAR-style portlets, hooks, EXT, and web
applications). Liferay's Plugin Compatibility Layer (explained later) makes it
possible to deploy traditional plugins to the OSGi runtime framework. To benefit
from all @product-ver@ and OSGi offer, however, you should use OSGi modules.

Modules offer these benefits:

- **Better Encapsulation** - The only classes a module exposes publicly are
those it exports explicitly. This lets the developer define internal public
classes transparent to external clients.

- **Dependencies by Package** - Dependencies are specified by Java package, not
by JAR file. In traditional plugins, developers had to add *all* of a JAR file's
classes to the classpath to use *any* of its classes. With OSGi, developers need
only import packages containing the classes they need. Only the classes in those
packages are added to the module's classpath.

- **Lightweight** - A module can be as small as the developer wants it to be. In
contrast to a traditional plugin, which may require several descriptor files, a
module requires only a single descriptor file--a standard JAR manifest. Also,
traditional plugins are typically larger than modules and deployed on app server
startup, which can slow down that process considerably. Modules deploy more
quickly and require minimal overhead cost.

- **Easy Reuse** - Modules lend themselves well to developing small, highly
cohesive chunks of code. They can be combined to create applications that are
easier to test and maintain. Modules can be distributed publicly (e.g., on Maven
Central) or privately. And since modules are versioned, developers can specify
precisely the modules they want to use.

- **In-Context Descriptors** - Where plugins use descriptor files (e.g.,
`web.xml`, `portlet.xml`, etc.) to describe classes, module classes use OSGi
annotations to describe themselves. For example, a module portlet class can use
[OSGi Service annotation properties](/develop/reference/-/knowledge_base/7-0/portlet-descriptor-to-osgi-service-property-map)
to specify its name, display name, resource bundle, public render parameters,
and much more. Instead of specifying that information in descriptor files
separate from the code, they're specified in context in the code.

These are just a few ways modules outshine traditional plugins. Note, however,
that developers experienced with Liferay plugins have the best of both worlds.
@product-ver@ supports traditional plugins *and* modules. Existing Liferay
developers can find comfort in the simplicity of modules and their similarities
with plugins.

Here are some fundamental characteristics modules share with plugins:

- Developers use them to create applications (portlets for Liferay)

- They're zipped up packages of classes and resources

- They're packaged as a standard Java JARs

Now that you've compared and contrasted modules with plugins, it's time to take a
tour of the module structure. 

### Module Structure: A JAR File with a Manifest [](id=module-structure-a-jar-file-with-a-manifest)

A module's structure is extremely simple. It has one mandatory file:
`META-INF/MANIFEST.MF`. Developers add code and resources to the module and
organize them as desired. 

Here's the essential structure of a module JAR file:

    |
    |- [Module's files]
    |_ META-INF
        |_ MANIFEST.MF

The `MANIFEST.MF` file describes the module to the system. The manifest's [OSGi headers](https://www.osgi.org/bundle-headers-reference/)
identify the module and its relationship to other modules. 

Here are some of the most commonly used headers:

- `Bundle-Name`: User friendly name of the module.

- `Bundle-SymbolicName`: Globally unique identifier for the module. Java package
conventions (e.g., `com.liferay.journal.api`) are commonly used.

- `Bundle-Version`: Version of the module.

- `Export-Package`: Packages from this module to make accessible to other
modules.

- `Import-Package`: Packages this module requires that other modules provide.

Other headers can be used to specify more characteristics, such as how the
module was built, development tools used, etc. 

For example, here are some headers from the Liferay Journal Web module manifest:

    Manifest-Version: 1.0
    Bundle-ManifestVersion: 2
    Bundle-Name: Liferay Journal Web
    Bundle-SymbolicName: com.liferay.journal.web
    Bundle-Vendor: Liferay, Inc.
    Bundle-Version: 1.1.2
    Export-Package:\
        com.liferay.journal.web.asset,\
        com.liferay.dynamic.data.mapping.util,\
        com.liferay.journal.model,
        com.liferay.journal.service,com.liferay.journal.util, [..]
    Import-Package:\
        aQute.bnd.annotation.metatype,\
        com.liferay.announcements.kernel.model,
        com.liferay.application.list,\
        com.liferay.asset.kernel,\
        com.liferay.asset.kernel.exception, [..]

Note: to remove unnecessary "noise" from this example, some headers have been
abbreviated (`[..]`) and some have been removed.

You can organize and build a module's Java code and resources however you like.
You're free to use any directory structure conventions, such as those used in
Maven or by your development team. And you can use any build tool, such as
Gradle or Maven, to manage dependencies.

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
is an environment for managing module projects (and theme projects). It provides
Gradle build scripts for developing on Liferay. It can be used from the command
line or from within [Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide).
Note also that Liferay @ide@ provides plugins for Gradle, Maven, and BndTools.
Tooling details are covered later in this series.

Now that you're familiar with the module structure and manifest, it's time to
explore how to build modules.

### Building Modules with Bnd [](id=building-modules-with-bnd)

The most common way to build modules is with a little tool called [Bnd](http://bnd.bndtools.org/).
It's an engine that, among other things, simplifies generating manifest
metadata. Instead of manually creating a `MANIFEST.MF` file, developers use Bnd
to generate it. Bnd can be used on its own or along with other build tools, such
as Gradle or Maven. Liferay Workspace uses Gradle and Bnd together.

One of Bnd's best features is that it automatically transverses a module's code
to identify external classes the module uses and adds them to the manifest's
list of packages to import. Bnd also provides several OSGi-specific operations
that simplify module development.

Bnd generates the manifest based on a file called `bnd.bnd` in the project root.
This file's header list is similar to (but shorter than) that of the
`MANIFEST.MF`. Compare the Liferay Journal Web module's `bnd.bnd` file content
(simplified a bit) below to its `MANIFEST.MF` file content that was listed
earlier:

    Bundle-Name: Liferay Journal Web
    Bundle-SymbolicName: com.liferay.journal.web
    Bundle-Version: 1.1.2
    Export-Package:\
        com.liferay.journal.web.asset,\
        com.liferay.journal.web.dynamic.data.mapping.util,\
        com.liferay.journal.web.social,\
        com.liferay.journal.web.util

The main difference is that the `bnd.bnd` file doesn't specify an
`Import-Package` header. It's unnecessary because Bnd generates it in the
`MANIFEST.MF` file automatically. It's metadata made easy!

Bnd plugins are available to use with Gradle and Maven. And since Liferay
Workspace includes Bnd, developers can use Bnd from the command line and from
Liferay @ide@.

Now that you're familiar with Bnd and the `Export-Package` and `Import-Package`
manifest headers, let's explore how to use them to leverage dependencies.

## Leveraging Dependencies [](id=leveraging-dependencies)

Using an OSGi manifest, a module declares the Java packages it consumes and
shares. The manifest's `Import-Package` and `Export-Package` settings expose
this information. As developers determine whether to use a particular module,
they know up-front what it offers and what it depends on. As an improvement over
Java EE, OSGi takes away dependency guesswork.

This part of the tutorial explains:

- **How dependencies work**

- **How to develop modular apps using dependencies**

Let's start by learning how dependencies operate in @product-ver@.

### How Dependencies Work [](id=how-dependencies-work)

Since all of @product-ver@ leverages dependencies, it also demonstrates how to
use them. As mentioned previously, all of what was in Liferay Portal 6 and its
apps has been refactored into OSGi modules. The `portal-service` API (the main
API in Liferay Portal 6) has been replaced by the `portal-kernel` module
(@product-ver@'s kernel API) and many small, highly-cohesive modules that
provide frameworks, utilities, apps, and more.

Not only do @product@ modules depend on third-party modules but they also depend
on each other. You can likewise leverage dependencies in your projects. Whether
you're developing new OSGi modules or continuing to develop traditional apps,
you need only set dependencies on modules whose packages you need.

Each module's manifest lists the packages the module depends on. Using a build
environment such as Gradle, Maven, or Ant/Ivy, the developer can set
dependencies on each package's module. At build time, the dependency framework
verifies the entire dependency chain, downloading all newly specified modules.
The same thing happens at runtime: the OSGi runtime knows exactly which modules
depend on which other modules (failing fast if any dependency is unmet).
Dependency management is explicit and enforced automatically upfront.

Versioning is independent for each Liferay module and its exported packages. You
can use a specific package version by depending on the version of the module
that exports it. And you're free to use a mix of Liferay modules in the versions
you want (but remember, "With great power comes great responsibility," so unless
you really know what you're doing, use the same version of each module you
depend on).

For all its modules, @product@ uses [Semantic Versioning](http://semver.org).
It's a standard that enables API authors to communicate programmatic
compatibility of a package or module automatically as it relates to dependent
consumers and API implementations. If a package is programmatically (i.e.,
semantically) incompatible with a project, Bnd (used in Liferay Workspace) fails
that project's build immediately. Developers not using Bnd can check package
versions manually in each dependency module's manifest.

Semantic Versioning also gives module developers flexibility to specify a
version range of packages and modules to depend on. In other words, if several
versions of a package work for an app, the developer can configure the app to
use any of them. What's more, Bnd automatically determines the semantically
compatible range of each package a module depends on and records the range to
the module's manifest.

On testing your project, you might find a new version of a dependency package
has bugs or behaves differently than you'd like. No problem. You can adjust the
package version range to include versions up to, but not including, the one you
don't want.

Next you want to consider when to modularize existing apps and when to combine
modules to create apps.

### Dependencies Facilitate Modular Development [](id=dependencies-facilitate-modular-development)

@product-ver@'s support of dependencies and semantic versioning facilitates modular
development. The dependency frameworks enable you to use modules and link them
together. You can use these modules throughout your organization and distribute
them to others. @product-ver@'s integration with dependency management frees you to
modularize existing apps and develop apps that combine modules. It's a powerful
and fun way to develop apps on @product@.

Here are some general steps to consider when modularizing an existing app:

1. **Start by putting the entire app in a single module**: This is a minimal
first step that acquaints you with @product-ver@'s module framework. You'll gain
confidence as you build, deploy, and test your app in an environment of your
choice, such as a Liferay Workspace, Gradle, or Maven project.

2. **Split the front-end from the back-end**: Modularizing front-end portlets
and servlets and back-end implementations (e.g., Service Builder or OSGi
component) is a logical next step. This enables each code area to evolve
separately and allows for varying implementations.

3. **Extract non-essential features to modules**: You may have functionality or
API extensions that need not be tied to an app's core codebase. They can be
refactored as independent modules that implement APIs you provide. Examples
might be connectors to 3rd party systems or support for various data
export/import formats.

The principles listed above also apply to developing new modular-based apps. As
you design an app, consider possible implementation variations with respect to
its features, front-end, and back-end. Encapsulate the variations using APIs.
Then develop the APIs and implementations as separate modules. You can wire them
together using dependencies.

**Liferay's Blogs application** exemplifies modularization in the manner we've
described:

**API**:

-   `blogs-api` - Encapsulates the core implementation

-   `blogs-item-selector-api` - Encapsulates the item-selector implementation

**Back-end**:

-   `blogs-service` - Implements `blogs-api`

**Front-end**:

-   `blogs-web` - Provides the app's UI

**Non-essential features and extensions**:

-   `blogs-editor-configuration` - Extends the `portal-kernel` module for
    extending editors

-   `blogs-recent-bloggers-web` - Provides the Recent Bloggers app

-   `blogs-item-selector-api` - Encapsulates the item-selector implementation

-   `blogs-item-selector-web` - Renders the Blogs app's item-selector

-   `blogs-layout-prototype` - Creates a Page Template showcasing blog entries

The Blogs app, like many modular apps, separates concerns into modules. In this
way, front-end developers concentrate on front-end code, back-end developers
concentrate on that code, and so on. These logical boundaries free developers
to design, implement, and test the modules independently.

As you develop app-centered modules, you can consider bundling them with your
app (e.g., as part of a Liferay Marketplace app). Including them as part of the
app is a convenience for the consumer. By bundling a module with an app,
however, you're committing to the app's release schedule. In other words, you
can't directly deploy a new version of a module for the app--you must release it
as part of the app's next release.

So far, you've learned how dependencies and Semantic Versioning work. You've
considered guidelines for modularizing existing apps and creating modular-based
apps. Now, to add to the momentum around OSGi and modularity, you'll explore
OSGi Services and dependency injection using OSGi Declarative Services.

## OSGi Services and Dependency Injection with Declarative Services [](id=osgi-services-and-dependency-injection-with-declarative-services)

In @product-ver@, the OSGi framework registers objects as *services*. Each service
offers functionality and can leverage functionality other services provide. The
OSGi Services model supports a collaborative environment for objects.

Declarative Services (DS) provides a service component model on top of OSGi
Services. DS service components are marked with the `@Component` annotation and
implement or extend a service class. Service component can refer to and use each
other's services. The Service Component Runtime (SCR) registers component
services and handles binding them to other components that reference them.

Here's how the "magic" happens:

1.  **Service registration:** On installing a module that contains a
    service component, the SCR creates a component configuration that associates
    the component with its specified service type and stores it in a service
    registry.

2.  **Service reference handling:** On installing a module whose service
    component references another service type, the SCR searches the registry for
    a component configuration that matches the service type and on finding a
    match binds an instance of that service to the referring component.

It's publish, find, and bind at its best!

How does a developer use DS to register and bind services? Does it involve
creating XML files? No, it's much easier than that. The developer uses two
annotations: `@Component` and `@Reference`.

-  `@Component`: Add this annotation to a class definition to make the class a
    component--a service provider. 

-  `@Reference`: Add this annotation to a field to inject it with a service that
    matches the field's type. 

The `@Component` annotation makes the class an OSGi component. Setting a
`service` property to a particular service type in the annotation, allows other
components to reference the service component by the specified service type.

For example, the following class is a service component of type `SomeApi.class`.

    @Component(
        service = SomeApi.class
    )
    public class Service1 implements SomeApi {

       ...
    }

On deploying this class's module, the SCR creates a component configuration that
associates the class with the service type `SomeApi`.

Specifying a service reference is easy too. Applying the `@Reference` annotation
to a field marks it to be injected with a service matching the field's type.

    @Reference
    SomeApi _someApi;

On deploying this class's module, the SCR finds a component configuration of the
class type `SomeApi` and binds the service to this referencing component class.

At build time, Bnd creates a *component description* file for each module's
components automatically. The file specifies the component's services,
dependencies, and activation characteristics. On module deployment, the OSGi
framework reads the component description to create the component and manage its
dependency on other components.

The SCR stands ready to pair service components with each other. For each
referencing component, the SCR binds an instance of the targeted service to it.

As an improvement over dependency injection with Spring, OSGi Declarative
Services supports dynamic dependency injection. Developers can create and
publish service components for other classes to use. Developers can update the
components and even publish alternative component implementations for a service.
This kind of dynamism is a powerful part of @product-ver@.

## Dynamic Deployment [](id=dynamic-deployment)

In OSGi, all components, Java classes, resources, and descriptors are deployed
via modules. The `MANIFEST.MF` file describes the module's physical
characteristics, such as the packages it exports and imports. The module's
component description files specify its functional characteristics (i.e., the
services its components offer and consume). Also modules and their components
have their own lifecycles and administrative APIs. Declarative Services and shell
tools give developers fine-grained control over module and component deployment.

Since a module's contents depend on its activation, consider the activation
steps: 

1.  *Installation*: Copying the module JAR into @product@'s `deploy` folder
    installs the module to the OSGi framework, marking the module `INSTALLED`.

2.  *Resolution*: Once all the module's requirements are met (e.g., all packages
    it imports are available), the framework publishes the module's exported
    packages and marks it `RESOLVED`.

3.  *Activation*: Modules are activated *eagerly* by default. That is, they're
    started in the framework and marked `ACTIVE` on resolution. An active
    module's components are enabled. If a module specifies a `lazy` activation
    policy, as shown in the manifest header below, it's activated only after
    another module requests one of its classes.

        Bundle-ActivationPolicy: lazy

The figure below illustrates the module lifecycle.

![Figure 1: This state diagram illustrates the module lifecycle.](../../../images/module-state-diagram.png) 

The [Apache Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
lets developers manage the module lifecycle. They can install/uninstall modules
and start/stop them. Developers can update a module and notify dependent modules
to use the update. Liferay's tools, including Liferay IDE/Developer Studio,
Liferay Workspace, and Blade CLI offer similar shell commands that use the
OSGi Admin API. 

On activating a module, its components are enabled. But only *activated*
components can be used. Component activation requires all its referenced
services be satisfied. That is, all services it references must be registered.
The highest ranked service that matches a reference is bound to the component.
When the container find and binds all the services the component references, it
registers the component. It's now ready for activation. 

Components can use *delayed* (default) or *immediate* activation policies. To
specify immediate activation, the developer adds the attribute `immediate=true`
to the `@Component` annotation. 

    @Component(
        immediate = true,
        ...)

Unless immediate activation is specified, the component's activation is delayed.
That is, the component's object is created and its classes are loaded once the
component is requested. In this way delayed activation can improve startup times
and conserve resources. 

Gogo Shell's [Service Component Runtime commands](http://felix.apache.org/documentation/subprojects/apache-felix-service-component-runtime.html#shell-command)
let developers manage components:

-  `src:list [bundleID]`: Lists the module's (bundle's) components.

-  `src:info [componentID]`: Describes the component, including its status and
    the services it provides.

-  `src:enable [componentID]`: Enables the component.

-  `src:disable [componentID]`: Disables the component.

Service references are static by default. That is, an injected service remains
bound to the referencing component until the service is disabled.
Alternatively, developers can specify *greedy* service policies for references.
Every time a higher ranked matching service is registered, the framework unbinds
the lower ranked service from the component and binds the new service in its
place automatically. Here's a `@Reference` annotation that uses a greedy policy:

    @Reference(policyOption = ReferencePolicyOption.GREEDY)

Declarative Services annotations let you specify component activation and
service policies. Gogo Shell commands let you control modules and components.
Next, you'll create and deploy a module and component to @product@. 

## Example: Building an OSGi Module [](id=example-building-an-osgi-module)

The previous sections explained some of the most important concepts for Liferay
Portal 6 developers to understand about OSGi and modularity. Now it's time to
put this knowledge to practice by creating and deploying a module.

The module includes a Java class that implements an OSGi service using
Declarative Services. The project uses Gradle and Bnd, and can be built and
deployed from within a [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace).

Here's the module project's anatomy:

- `bnd.bnd`

- `build.gradle`

- `src/main/java/com/liferay/docs/service/MyService.java`

On building the module JAR, Bnd generates the module manifest automatically. 

Here's the Java class:

    package com.liferay.docs.service;

    import org.osgi.service.component.annotations.Activate;
    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true,
        service = MyService.class
    )
    public class MyService {

        @Activate
        void activate() throws Exception {

            System.out.println("Activating " + this.getDescription());
        }

        public String getDescription() {

            return this.getClass().getSimpleName();
        }

    }

It contains these methods:

-  `getDescription` - returns the class's name

-  `activate` - prints the console message *Activating MyService*. The
    `@Activate` annotation signals the OSGi runtime environment to invoke this
    method on component activation.

The `@Component` annotation defines the class as an OSGi service component. The
following properties specify its details:

-  `service=MyService.class` - designates the component to be a service
    component for registering under the type `MyService`. In this example, the
    class implements a service of itself. Note, service components typically
    implement services for interface classes.

-  `immediate=true` - signals the Service Component Runtime to activate the
    component immediately after the component's dependencies are resolved.

The `bnd.bnd` file is next:

    Bundle-SymbolicName: my.service.project
    Bundle-Version: 1.0.0

The `Bundle-SymbolicName` is the arbitrary name for the module. The module's
version value `1.0.0` is appropriate.

Bnd generates the module's OSGi manifest to the file `META-INF/MANIFEST.MF`
in the module's JAR. In this project, the JAR is created in the `build/libs/`
folder.

The last file to examine is the Gradle build file `build.gradle`:

    dependencies {
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }

Since the `MyService` class uses the `@Component` annotation, the project
depends on the OSGi service component annotations module. The build script is so
simple because Liferay Workspace module projects leverage its Gradle build
infrastructure.

Although this module project was created in a Liferay Workspace, it can easily
be modified to use in other build environments. To keep the focus on what's
most important, it was created in a Liferay Workspace.

Place the project files in a folder under the `modules/` folder (e.g.,
`[Liferay_Workspace]/modules/my.service.project/`).

To build the module JAR and deploy it to @product@, execute the `deploy` Gradle
task:

    ../../gradlew deploy

+$$$

**Note**: If Blade is installed (recommended), Gradle can be executed by
entering `blade gw` followed by a task name (e.g., `blade gw deploy`). For
details on Blade commands, see [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

$$$

On deploying the module, the following message is printed to the server console:

`Activating MyService`

Congratulations! You've successfully built and deployed an OSGi module to
@product@. 

## Learning More about OSGi [](id=learning-more-about-osgi)

There is much more to learn about developing apps using OSGi. Several resources
are listed below and many more abound. To make the best of your time, however,
avoid OSGi service articles that explain techniques that are older and more
complicated than Declarative Services.

Developers new to OSGi should check out these resources:

-   [Introduction to Liferay Development](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-development): 
    For using OSGi to develop on @product@.

-   [OSGi enRoute](http://enroute.osgi.org/) is a site the OSGi Alliance
    provides to the OSGi community. These sections are recommended.

    -   [Documentation](http://enroute.osgi.org/book/210-doc.html): For details
        on modularity and the OSGi architecture.

    -   [Tutorials](http://enroute.osgi.org/book/150-tutorials.html): For
        hands-on experience with OSGi modules and Declarative Services.

Developers ready to dive deep into OSGi should read the OSGi specifications.
They're well-written and provide comprehensive details on all that OSGi offers.
[*The OSGi Alliance OSGi Compendium: Release 6*](https://osgi.org/download/r6/osgi.cmpn-6.0.0.pdf)
specifies the following services that @product-ver@ leverages extensively.

-   *Declarative Services Specification*

-   *Configuration Admin Service Specification*: For modifying deployed
    bundles. Since Configuration Admin services are already integrated with
    Declarative Services, however, Liferay developers need not use the low-level
    API.

-   *Metatype Service Specification*: For describing attribute types as metadata.
