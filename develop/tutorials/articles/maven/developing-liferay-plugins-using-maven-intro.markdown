# Developing Liferay Plugins Using Maven

"Once upon a midnight dreary, while I pondered weak and weary..."

Here's the scene--you're sitting in a luxurious armchair next to a dancing fire,
hot beverage in hand. Shadows dance on the tapestry-covered wall, and your cat
*Lenore II* is purring softly from her favorite perch atop the mantle.

"Ah, distinctly I remember it was in the bleak December..."

At least you're passing this cold December night in grand style (in front of
your computer customizing Liferay Portal, of course). 

"Eagerly I wished the morrow;--vainly I had sought to borrow 
From *Liferay* surcease of sorrow--sorrow for my last Lenore--" 

We're sorry to hear your previous cat, the original *Lenore*, has passed away.
Just take good care of Lenore II, would you? 

"And the silken sad uncertain rustling of each purple curtain
Thrilled me--filled me with *Ant*astic terrors never felt before;"

Okay now you're being melodramatic; nobody can disdain *Apache Ant* that
vehemently. What about customizing Liferay Portal using the Ant-based Plugins
SDK could make you feel sadness and terror? 

"Deep into that darkness peering, long I stood there wondering, fearing..."

We get it! You don't want to use our Ant-based Plugins SDK. Give us surcease
from the melodrama, okay? 

"Open here I flung the shutter, when, with many a flirt and flutter,
In there stepped a stately *Maven* of the saintly days of yore."

So, you'd rather use Apache Maven to develop your Liferay plugins? 

"But *Apache Maven* still beguiling all my sad soul into smiling..."

Edgar Allen Poe liked Maven too, so you're in good company. Trust us; we know.
But if your soul was made sad because you thought you had to use Liferay's
Ant-based Plugins SDK to develop your plugins, Apache Maven will make your sad
soul smile. And while you're at it, take care of Lenore II for all of us animal
lovers, would you? 

<!--
The following info on our Maven coverage seems useful but we need to decide
where it should go. Should it go in the first of a series of related tutorial
articles? (But tutorial articles aren't arranged sequentially.) - JR
-->

<!--
Quoth the Maven, "Let us proceed undaunted in exploration of these topics:" 

- Installing Maven 
- Using Maven Repositories
- Installing Required Liferay Artifacts 
- Using a Parent Plugin Project
- Creating Liferay Plugins with Maven
- Deploying Liferay Plugins with Maven
- Liferay Plugin Types to Develop with Maven
-->

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
We'll dive into all these topics in our Maven tutorials.

"Straight I wheeled a cushioned seat in front of computer desk once more;
Then, upon the velvet falling, I betook to Maven installing..."

## Next Steps

Next, let's learn how to install Maven and how to manage Maven artifacts with a
Maven repository manager.

[Managing Maven Repositories](http://www.liferay.com)
