# Maven [](id=maven)

[Maven](https://maven.apache.org/) is a popular project management tool that is
frequently used for Java-based projects. Because Liferay is tool-agnostic, Maven
is a viable option for managing Liferay projects. Here's a list of some exciting
Maven features:

- Offers a simple build process. 
- Features a project object model. 
- Has a defined project life cycle. 
- Provides a dependency management system. 

Maven's core installation is lightweight; there are core plugins for compiling
source code and creating distributions, and there is an abundance of non-core
plugins, letting you extend Maven easily for your customizations. 

Many developers are switching to Maven because it offers a common interface for
project builds. Maven's universal directory structure makes it easier for you to
understand another developer's project more quickly. With Maven, there's a
simple process to build, install, and deploy project artifacts. 

Maven uses a *project object model (POM)* to describe a software project. The
POM is specified as XML in a file named `pom.xml`. Think of `pom.xml` as a
blueprint for your entire project; it describes your project's directories,
required plugins, build sequence, and dependencies. The POM is your project's
sole descriptive declaration. Once you create the `pom.xml` file and invoke the
build process, Maven does the rest, downloading your project's inferred
dependencies and building your project artifacts. If you're not already familiar
with how Maven works, you can get familiar with Maven's project object model by
reading Sonatype's documentation for it at
[http://books.sonatype.com/mvnref-book/reference/pom-relationships-sect-pom.html](http://books.sonatype.com/mvnref-book/reference/pom-relationships-sect-pom.html). 

Maven provides a clear definition of a project's structure and manages a project
from a single piece of information--its POM. Understanding a Maven project can
be much easier than understanding other build tools' various build files. Maven
forces projects to conform to a standard build process, whereas other build
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
[http://books.sonatype.com/mvnref-book/reference/index.html](http://books.sonatype.com/mvnref-book/reference/index.html). 

Liferay provides Maven archetypes to help you build plugins of various types,
including Liferay portlets, themes, and more. You can also install and deploy
Liferay artifacts to your repositories. You'll dive into these topics and many
more in the Maven tutorials.
