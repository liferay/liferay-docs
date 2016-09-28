# OSGi and Modularity for Liferay 6 Developers [](id=osgi-and-modularity-for-liferay-6-developers)

To create a powerful, reliable platform for developing modular applications,
Liferay sought best-of-breed standards-based frameworks and technologies.
It was imperative to not only meet demands for enterprise digital experiences
but to also offer developers, both experienced with Liferay and new to Liferay,
a clear and elegant way to create apps.

Here were some of the key goals:

- Allow breaking down a large system into smaller pieces of code, whose
boundaries and relationships could be clearly defined.

- Explicitly differentiate public APIs from private APIs.

- Facilitate extensibility of existing code.

- Modernize the development environment, leveraging more state-of-the-art tools
to provide a great developer experience.

It wasn't long before Liferay discovered that OSGi, and its supporting
tools/technologies, fit the bill!

In this tutorial, you'll learn how Liferay 7 uses OSGi to meet these objectives.
And equally important, you'll find out how easy and fun modular development can
be.

Here are the topics you'll dig into:

1. **Modules as an Improvement over Traditional Plugins**: Development and
customization of applications for Liferay has traditionally been done in plugins
(Portlet, Hook, Ext and Web). In Liferay 7, plugins are replaced with (and can
be automatically converted to) modules. This section compares and contrasts
plugins and modules, and explains the benefits of using modules.

2. **Leveraging Dependencies**: In Liferay 7, developers can declare
dependencies among modules and combine modules to create applications. Since
leveraging dependencies provides huge benefits, it warrants its own section.

3. **OSGi Services and Dependency Injection**: OSGi provides a very powerful
concept called OSGi Services (also known as microservices). Together with the
Declarative Services standard, it provides a clean way to inject dependencies
(similar to Spring DI) in a dynamic environment. It also offers an elegant
extensibility model that Liferay 7 leverages extensively.

<!-- Uncomment when these sections are added. Jim
4. **Dynamic Deployment**: Module deployment is managed by Liferay 7 (not the
application server). This section demonstrates how to use dynamic deployment to
allow for more dynamicity and speed.
-->

After investigating these topics, you'll get hands-on experience creating and
deploying an OSGi module. 

Let's start with learning how modules are better than traditional plugins.

## Modules as an Improvement over Traditional Plugins [](id=modules-as-an-improvement-over-traditional-plugins)

In Liferay 7, you can develop applications using OSGi modules or using
traditional Liferay plugins (WAR-style portlets, hooks, EXT, and web
applications). Liferay's Plugin Compatibility Layer (explained later) makes it
possible to deploy traditional plugins to the OSGi runtime framework. To gain
from all Liferay 7 and OSGi offer, however, you should use OSGi modules.

Here are some important things you gain from using modules:

- **Better Encapsulation** - The only classes a module exposes publicly are
those it exports explicitly. This lets the developer define internal public
classes transparent to external clients.

- **Dependencies by Package** - Dependencies are specified by Java package, not
by JAR file. In traditional plugins, developers had to add *all* of a JAR file's
classes to the classpath in order to use *any* of its classes. With OSGi,
developers need only import packages of the classes they need. Only the classes
in those packages are added to the module's class path.

- **Lightweight** - A module can be as small as the developer wants it to be. In
contrast to a traditional plugin, which may require several descriptor files, a
module requires only a single descriptor file--a standard JAR manifest. Also,
traditional plugins are typically larger than modules and are deployed on app
server startup, which can slow down that process considerably. Modules deploy
more quickly and require minimal overhead cost.

- **Easier to Reuse** - OSGi modules lend themselves well to developing small,
highly cohesive chunks of code. They can be combined to create applications that
are easier to test and maintain. Modules can be distributed publicly (e.g., on
Maven Central) or privately. And since modules are versioned, developers can
specify precisely the modules they want to use.

- **In-Context Descriptors** - Where plugins use descriptor files (e.g.,
`web.xml`, `portlet.xml`, etc.) to describe classes, module classes uses OSGi
annotations to describe themselves. For example, a module portlet class can use
[OSGi Service annotation
properties](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/portlet-descriptor-to-osgi-service-property-map)
to specify its name, display name, resource bundle, public render parameters,
and much more. Instead of specifying that information in descriptor files
separate from the code, they're specified in context in the code.

These are just a few ways modules outshine traditional plugins. Note, however,
developers experienced with Liferay plugins have the best of both worlds.
Liferay 7 supports traditional plugins *and* modules. Existing Liferay
developers can find comfort in the simplicity of modules and their similarities
with plugins.
Here are some fundamental characterics modules share with plugins:

- Developers use them to create applications (portlets for Liferay)

- They're zipped up packages of classes and resources

- They're packaged as a standard Java JARs

Now that we've compared and contrasted modules with plugins, it's time to take a
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

Additional headers can be used to specify more characteristics, such as how the
module was built, development tools used, etc. 

As an example, here are some headers from the Liferay Journal Web module manifest:

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
abbreviated (`[...]`) and some have been removed.

As for a module's Java code and resources, you can organize them and build them
however you like. You're free to use any directory structure conventions, such
as those used in Maven or by your development team. And you can use any build
tool, such as Gradle or Maven, to manage dependencies.

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
is an environment for managing module projects (and theme projects). It provides
Gradle build scripts for developing on Liferay. It can be used from the command
line or from within [Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
and Developer Studio. Note also, these IDE/Studio provide plugins for Gradle,
Maven, and BndTools. Tooling details are covered later in this series.

Now that you're familiar with the module structure and manifest, it's time to
explore how to build modules.

### Building Modules with Bnd [](id=building-modules-with-bnd)

The most common way to build modules is with a little tool called [Bnd](http://bnd.bndtools.org/).
It's an engine that, among other things, simplifies generating manifest
metadata. Instead of manually creating a `MANIFEST.MF` file, developers use Bnd
to generate it. Bnd can be used on its own or along with other build tools, such
as Gradle or Maven. Liferay Workspace uses Gradle and Bnd together.

One of Bnd's best features is that it automatically transverses a module's code,
to identify external classes the module uses and add them to the manifest's list
of packages to import. Bnd also provides several OSGi-specific operations that
simplify module development.

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
Liferay IDE / Developer Studio.

Now that you're familiar with Bnd and the `Export-Package` and `Import-Package`
manifest headers, let's explore how leverage dependencies using them.

## Leveraging Dependencies [](id=leveraging-dependencies)

Using an OSGi manifest, a module declares the Java packages it consumes and
shares. The manifest's `Import-Package` and `Export-Package` settings expose
this information. As developers determine whether to use a particular module,
they know up-front what it offers and what it depends on. As an improvement over
JavaEE, OSGi takes away dependency guesswork.

This part of the tutorial explains:

- **How dependencies work**

- **How to develop modular apps using dependencies**

Let's start by learning how dependencies operate in Liferay 7.

### How Dependencies Work [](id=how-dependencies-work)

Since all of Liferay 7 leverages dependencies, it also demonstrates how to use
them. As mentioned previously, all of what was in Liferay 6 and its apps has
been refactored into OSGi modules. The `portal-service` API (the main API in
Liferay 6) has been replaced by the `portal-kernel` module (@product@'s kernel
API) and many small, highly-cohesive modules that provide frameworks, utilities,
apps, and more.

Not only do @product@ modules depend on third-party modules but they also depend
on each other. You can likewise leverage dependencies in your projects. Whether
you're developing new OSGi modules or continuing to develop traditional apps,
you need only set dependencies on modules whose packages you need.

Each module's manifest lists the packages the module depends on. Using a build
environment, such as Gradle, Maven, or Ant/Ivy, the developer can set
dependencies on each package's module. At build time, the dependency framework
verifies the entire dependency chain, downloading all newly specified modules.
The same thing happens at runtime (and this is the key difference), where the
OSGi runtime knows exactly which modules depend on which other modules (failing
fast if any dependency is unmet). Dependency management is explicit and enforced
automatically upfront.

Versioning is independent for each Liferay module and its exported packages. You
can use a specific package version by depending on the version of the module
that exports it. And you're free to use a mix of Liferay modules in the versions
you want (but remember, "With great power comes great responsibility"; so unless
you really know what you are doing, use the same version of each module you
depend on).

For all its modules, @product@ uses [Semantic Versioning](http://semver.org).
It's a standard that enables API authors to automatically communicate
programmatic compatibility of a package or module as it relates to dependent
consumers and API implementations. If a package is programmatically (i.e.,
semantically) incompatible with a project, Bnd (used in Liferay Workspace) fails
that project's build immediately. Developers not using Bnd, can manually check
package versions in each dependency module's manifest.

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

You're now familiar with the power of semantic versioning and how dependencies
work. Next let's consider when to modularize existing apps and when to combine
modules to create apps.

### Dependencies Facilitate Modular Development [](id=dependencies-facilitate-modular-development)

Liferay 7's support of dependencies and semantic versioning facilitates modular
development. The dependency frameworks enable you to use modules and link them
together. You can use these modules throughout your organization and distribute
them to others. Liferay 7's integration with dependency management frees you to
modularize existing apps and develop apps that combine modules. It's a powerful
and fun way to develop apps on @product@.

Here are some general steps to consider when modularizing an existing app:

1. **Start by putting the entire app in a single module**: This is a minimal
first step that acquaints you with Liferay 7's module framework. You'll gain
confidence as you build, deploy, and test your app in an environment of your
choice, such as a Liferay Workspace or Maven project.

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

- `blogs-api` - Encapsulates the core implementation

- `blogs-item-selector-api` - Encapsulates the item-selector implementation

**Back-end**:

- `blogs-service` - Implements `blogs-api`

**Front-end**:

- `blogs-web` - Provides the app's UI

**Non-essential features and extensions**:

- `blogs-editor-configuration` - Extends the `portal-kernel` module for
extending editors.

- `blogs-recent-bloggers-web` - Provides the Recent Bloggers app

- `blogs-item-selector-api` - Encapsulates the item-selector implementation

- `blogs-item-selector-web` - Renders the Blogs app's item-selector

- `blogs-layout-prototype` - Creates a Page Template showcasing blog entries

The Blogs app, like many modular apps, separates concerns into modules. In this
way, front-end developers concentrate on front-end code, back-end developers
concentrates on that code, and so on. These logical boundaries free developers
to design, implement, and test the modules independently.

As you develop app-centered modules, you can consider bundling them with your
app (e.g., as part of a Liferay Marketplace app). Including them as part of the
app is a convenience for the consumer. By bundling a module with an app,
however, you're committing to the app's release schedule. In other words, you
can't directly deploy a new version of a module for the app--you must release it
as part of the app's next release.

In this section, you've learned how dependencies and Semantic Versioning work.
You've considered guidelines for modularizing existing apps and creating
modular-based apps. Uncomment this transition when next section is ready. Now,
to add to the momentum around OSGi and modularity, you'll explore OSGi Services
and dependency injection using OSGi Declarative Services.

## OSGi Services and Dependency Injection with Declarative Services [](id=osgi-services-and-dependency-injection-with-declarative-services)

In Liferay 7, the OSGi framework registers objects as *services*. Each service
offers functionality and can leverage functionality other services provide. The
OSGi Services model supports a collaborative environment for objects.

Declarative Services (DS) provides a service component model on top of the OSGi
Services model. A *Service Component* is a class that implements or extends a
service class. Other OSGi Service classes can refer to the service class to use
the Service Component. And the Service Component Runtime (SCR) handles
registration, lookup, and binding of Service Components to classes that refer to
them.

Here's how the "magic" happens:

1.  **Service component registration** - On installing a module that contains a
    Service Component, the SCR creates a component configuration that associates
    the Service Component with its specified service type and stores it in a
    service registry.

2.  **Service reference handling** - On installing a module that contains a class
    that references a service type, the SCR searches the registry for a
    component configuration that matches the service type. On finding a matching
    component configuration, the SCR creates an instance of the Service
    Component class and binds it to the referring service.

It's publish, find, and bind at its best!

How does a developer use DS to register and bind service components? Does it
involve creating XML files? No, it's much easier than that. The developer uses
two annotations: `@Component` and `@Reference`.

-  `@Component` - defines the class as a Service Component--a provider of a
    particular service class.

-  `@Reference` - enables the referring class to be injected with a particular
    service class.

The `@Component` annotation makes the class an OSGi component. Defining a
`service` property in the annotation allows other components to reference it by
type.

For example, the following class is a Service Component of type `SomeApi.class`.

    @Component(
        service = SomeApi.class
    )
    public class Service1 implements SomeApi {

       ...
    }

On deploying this class's module, the SCR creates a Component Configuration that
associates the class with the service type `SomeApi`.

Specifying a service reference is easy too. The `@Reference` annotation can be
applied to a field of the desired service class type. 

    @Reference
    SomeApi _someApi;

On deploying this class's module, the SCR finds a Service Component of the class
type `SomeApi` and binds the service component to the consumer.

The SCR stands ready to pair Service Components with any service classes that
reference them. For each referencing service class, the SCR creates an instance
of the Service Component and binds it to the referencing service.

As an improvement over dependency injection with Spring, OSGi Declarative
Services supports dynamic dependency injection. Developers can create and
publish service components for other classes to use. Developers can update the
components and even publish alternative component implementations for a service.
Dynamicity is a powerful part of Liferay 7.

## Example: Building an OSGi Module [](id=example-building-an-osgi-module)

The previous sections explained some of the most important concepts for Liferay
6 developers to understand about OSGi and modularity. Now, it's time to put this
knowledge to practice by creating and deploying a module.

The module will include a Java class that implements an OSGi service using
Declarative Services. The project will use Gradle and Bnd, and can be built and
deployed from within a [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace).

Here's the module project's anatomy:

- `bnd.bnd`

- `build.gradle`

- `src/main/java/com/liferay/docs/service/MyService.java`

On building the module JAR, Bnd will generate the module manifest automatically. 

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
following properties accompany specify its details:

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

Bnd will generate the module's OSGi manifest to the file `META-INF/MANIFEST.MF`
in the module's JAR. In this project, the JAR will be generated to the
`build/libs/` folder.

The last file to examine is the Gradle build file `build.gradle`:

    dependencies {
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }

Since the `MyService` class uses the `@Component` annotation, the project
depends on the OSGi service component annotations module--Liferay Workspace
module projects leverage its Gradle build infrastructure.

Although this module project was created in a Liferay Workspace, it can easily
be modified to use in other build environments. For simplicity, however, we'll
use it in Liferay Workspace.

Place the project files in a folder under the `modules/` folder (e.g.,
`[Liferay_Workspace]/modules/my.service.project/`).

To build the module JAR and deploy it to @product@, execute the `deploy` Gradle
task:

    ../../gradlew deploy

+$$$

**Note**: If Blade is installed (recommended), Gradle can be executed by
entering `blade gw` followed by a task name (e.g., `blade gw deploy`). For
details on Blade commands, see [Blade CLI](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/blade-cli).

$$$

On deploying the module, the following message is printed to the server console:

`Activating MyService`

Congratulations! You've successfully built and deployed an OSGi module to
@product@. 

## Learning More about OSGi

[Introduction to Liferay Development](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/introduction)

[OSGi enRoute](http://enroute.osgi.org/)

Liferay 7 leverages the following services extensively. They're specified in
[*The OSGi Alliance OSGi Compendium: Release 6*](https://www.osgi.org/developer/specifications/).

- *Declarative Services Specification* 

- *Configuration Admin Service Specification* - For modifying deployed bundles.
Since Configuration Admin services are already integrated with Declarative
Services, developers need not use the low-level API.

- *Metatype Service Specification* - For describing attribute types as metadata. 
