# Maven [](id=maven-lp-6-2-develop-tutorial)

As an alternative to developing plugins using the Plugins SDK, you can leverage
the Apache Maven build management framework. Here's a list of some exciting
Maven features: 

- Offers a simple build process. 
- Features a project object model. 
- Has a defined project life cycle. 
- Provides a dependency management system. 

Maven's core installation is lightweight; there are core plugins for compiling
source code and creating distributions, and there is an abundance of non-core
plugins, letting you extend Maven easily for your customizations. 

Many developers are switching from Ant to Maven because it offers a common
interface for project builds. Maven's universal directory structure makes it
easier for you to understand another developer's project more quickly. With
Maven, there's a simple process to build, install, and deploy project artifacts. 

Maven uses a *project object model (POM)* to describe a software project. The
POM is specified as XML in a file named `pom.xml`. Think of `pom.xml` as a
blueprint for your entire project; it describes your project's directories,
required plugins, build sequence, and dependencies. The POM is your project's
sole descriptive declaration. Once you create the `pom.xml` file and invoke the
build process, Maven does the rest, downloading your project's inferred
dependencies and building your project artifacts. If you're not already familiar
with how Maven works, you can get familiar with Maven's project object model by
reading Sonatype's documentation for it at
[http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html](http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html). 

Maven provides a clear definition of a project's structure and manages a project
from a single piece of information--its POM. Understanding a Maven project can
be much easier than understanding an Ant-based project's various build files.
Maven forces projects to conform to a standard build process, whereas Ant
projects can be built differently from project to project. Also, Maven provides
an easy way to share artifacts (e.g., JARs, WARs, etc.) across projects via
public repositories.

There are disadvantages to using Maven. You might find the Maven project
structure too restrictive, or decide that reorganizing your projects to work
with Maven is too cumbersome. Maven is intended primarily for Java-based
projects, so it can be difficult to manage your project's non-Java source code.
Consider Maven's advantages and disadvantages, then decide how you want to
manage your projects. After you're finished reading about Maven here, you can
read an in-depth book about Maven at *Maven: The Complete Reference* by
Sonatype, Inc. at
[http://www.sonatype.com/books/mvnref-book/reference/](http://www.sonatype.com/books/mvnref-book/reference/). 

Liferay provides Maven archetypes to help you build plugins of various types,
including Liferay portlets, themes, hooks, layout templates, web plugins, and
more. You can also install and deploy Liferay artifacts to your repositories.
You'll dive into these topics in the Maven tutorials.

## Next Steps

If you haven't already installed Maven or if you want to learn how to manage
Maven artifacts with a Maven repository manager, check out this tutorial:

[Setting Up Maven](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/setting-up-maven-lp-6-2-develop-tutorial)

Otherwise, you can get your hands on Liferay's Maven artifacts by following this
tutorial: 

[Installing and Managing Liferay Maven Artifacts](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/managing-liferay-maven-artifacts-lp-6-2-develop-tutorial)

