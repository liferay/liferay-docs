# Liferay Sample Modules

Liferay provides working examples of sample modules that target different
integration points in @product@. These working examples can be copy/pasted into
your own independent project so you can take advantage of various Liferay
extension points. Most of the sample modules include a single class using
Declarative Services. Therefore, each sample is developed to demonstrate a
single extension point and includes its own build files. Liferay's sample
modules can be found in the
[liferay-blade-samples](https://github.com/liferay/liferay-blade-samples)
repository on Github.

If you'd like to browse the repo locally or copy and sample projects into your
own project, [fork](https://help.github.com/articles/fork-a-repo/) and
[clone](https://help.github.com/articles/cloning-a-repository/) the
`liferay-blade-samples` repository.

At first glance, you'll notice that the repository is broken up into four
primary folders:

- `bndtools`
- `gradle`
- `liferay-gradle`
- `maven`

The provided sample modules are organized into these four main build chains, to
cater to a multitude of different developers. Each folder offers a set of
Liferay projects that can be bootstrapped to that specific development
environment. The `liferay-gradle` folder provides projects that are bootstrapped
on to the Liferay Gradle plugin (e.g., `com.liferay.plugin`). The Liferay Gradle
plugin is really a set of Gradle plugins that makes developing Liferay
applications quick and easy.

+$$$

**Note:** There are two additional projects in the `/bndtools` folder that
you'll need to build and publish the modules:

- `/bndtools/cnf` - the Bndtools configuration project.
- `/bndtools/blade.run` - A Bndtools project which can push modules into an OSGi
container and run them. The `biz.aQute.remote.agent-X.X.X.jar` must be deployed
before this project can work properly.

$$$

The sample projects also demonstrate how to use various frameworks like:

- Blueprint
- Declarative Services (DS)
- OSGi API

+$$$

**Note:** Liferay does not provide a Blueprint implementation out-of-the-box. To
use the Blueprint modules provided in this repository, you must deploy a
Blueprint implementation (e.g.,
[Apache Aries - Blueprint](http://aries.apache.org/modules/blueprint.html)).
Three modules are required:

- [Apache Aries Blueprint Bundle](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint/1.1.0)
- [Apache Aries Blueprint Annotation API](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint.annotation.api/1.0.1)
- [Apache Aries Proxy Bundle](http://mvnrepository.com/artifact/org.apache.aries.proxy/org.apache.aries.proxy/1.0.1)

$$$

For a list of sample template projects available, visit the
[Liferay extension points](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-and-template-projects)
heading in the Liferay Blade Samples repository. This list is not comprehensive.
A subset of missing extension point samples can be found in the
[Liferay extension points without template projects](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-without-template-projects)
sub-section. Visit the repo's
[Contribution](https://github.com/liferay/liferay-blade-samples#contribution)
section for details on contributing to this repository.
