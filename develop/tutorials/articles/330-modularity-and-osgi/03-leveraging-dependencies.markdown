# Leveraging Dependencies [](id=leveraging-dependencies)

Using an OSGi manifest, a module declares the Java packages it consumes and
shares. The manifest's `Import-Package` and `Export-Package` settings expose
this information. As you determine whether to use a particular module, you know
up-front what it offers and what it depends on. As an improvement over Java EE,
OSGi takes away dependency guesswork.

This part of the tutorial explains:

- [How dependencies work](#how-dependencies-work)

- [How dependencies facilitate modular development](#dependencies-facilitate-modular-development)

Let's start by learning how dependencies operate in @product-ver@.

## How Dependencies Work [](id=how-dependencies-work)

Each module's manifest lists the packages the module depends on. Using a build
environment such as Gradle, Maven, or Ant/Ivy, you can set dependencies on each
package's module. At build time, the dependency framework verifies the entire
dependency chain, downloading all newly specified modules. The same thing
happens at runtime: the OSGi runtime knows exactly which modules depend on which
other modules (failing fast if any dependency is unmet). Dependency management
is explicit and enforced automatically upfront.

+$$$

**Note**: Since Liferay 7.0, all of what was in Liferay Portal 6 and its apps
has been refactored into OSGi modules. The `portal-service` API (the main API in
Liferay Portal 6) has been replaced by the `portal-kernel` module
(@product-ver@'s kernel API) and many small, highly-cohesive modules that
provide frameworks, utilities, apps, and more. Not only do @product@ modules
depend on third-party modules but they also depend on each other. You can
likewise leverage dependencies in your projects. Whether you're developing new
OSGi modules or continuing to develop traditional apps, you need only set
dependencies on modules whose packages you need.

$$$

Versioning is independent for each module and its exported packages. You can use
a specific package version by depending on the version of the module that
exports it. And you're free to use a mix of modules in the versions you want
(but remember, "With great power comes great responsibility," so unless you
really know what you're doing, use the same version of each module you depend
on).

For all its modules, @product@ uses [Semantic Versioning](http://semver.org).
It's a standard that enables API authors to communicate programmatic
compatibility of a package or module automatically as it relates to dependent
consumers and API implementations. If a package is programmatically (i.e.,
semantically) incompatible with a project, bnd (used in Liferay Workspace and
projects created from [Liferay project templates](/develop/reference/-/knowledge_base/7-1/project-templates)) fails
that project's build immediately. Developers not using bnd can check package
versions manually in each dependency module's manifest.

Semantic Versioning also gives you flexibility to specify a version range of
packages and modules to depend on. In other words, if several versions of a
package work for an app, you can configure the app to use any of them. What's
more, bnd automatically determines the semantically compatible range of each
package a module depends on and records the range to the module's manifest.

On testing your project, you might find a new version of a dependency package
has bugs or behaves differently than you'd like. No problem. You can adjust the
package version range to include versions up to, but not including, the one you
don't want.

Next you want to consider when to modularize existing apps and when to combine
modules to create apps.

## Dependencies Facilitate Modular Development [](id=dependencies-facilitate-modular-development)

@product@'s support of dependencies and semantic versioning facilitates modular
development. The dependency frameworks enable you to use modules and link them
together. You can use these modules throughout your organization and distribute
them to others. Liferay's integration with dependency management frees you to
modularize existing apps and develop apps that combine modules. It's a powerful
and fun way to develop apps on Liferay.

Here are some general steps to consider when modularizing an existing app:

1. **Start by putting the entire app in a single module**: This is a minimal
first step that acquaints you with Liferay's module framework. You'll gain
confidence as you build, deploy, and test your app in an environment of your
choice, such as a [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace),
Gradle, or Maven [project](/develop/reference/-/knowledge_base/7-1/project-templates).

2. **Split the front-end from the back-end**: Modularizing front-end portlets
and servlets and back-end implementations (e.g., [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)
or OSGi component) is a logical next step. This enables each code area to evolve
separately and allows for varying implementations.

3. **Extract non-essential features to modules**: You may have functionality or
API extensions that need not be tied to an app's core codebase. They can be
refactored as independent modules that implement APIs you provide. Examples
might be connectors to third-party systems or support for various data
export/import formats.

The principles listed above also apply to [developing new modular-based apps](/develop/tutorials/-/knowledge_base/7-1/developing-a-web-application). 
As you design an app, consider possible implementation variations with respect
to its features, front-end, and back-end. Encapsulate the variations using APIs.
Then develop the APIs and implementations as separate modules. You can wire them
together using dependencies.

**Liferay's Blogs application** exemplifies modularization in the manner we've
described:

**API**:

-   `blogs-api` - Encapsulates the core implementation

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
app is convenient for the consumer. By bundling a module with an app, however,
you're committing to the app's release schedule. In other words, you can't
directly deploy a new version of a module for the app--you must release it as
part of the app's next release.

So far, you've learned how dependencies and Semantic Versioning work. You've
considered guidelines for modularizing existing apps and creating new modular
apps. Now, to add to the momentum around OSGi and modularity, explore
[OSGi Services and dependency injection using OSGi Declarative Services](osgi-services-and-dependency-injection-with-declarative-services).

+$$$

If you visited this tutorial as a part of the Learning Path
[From Liferay Portal 6 to 7.1](/develop/tutorials/-/knowledge_base/7-1/from-liferay-6-to-liferay-7),
you can continue with the next topic:
[OSGi Services and dependency injection using OSGi Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services).

$$$

## Related Topics [](id=related-topics)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies)

[Importing Packages](/develop/tutorials/-/knowledge_base/7-1/importing-packages)

[Exporting Packages](/develop/tutorials/-/knowledge_base/7-1/exporting-packages)
