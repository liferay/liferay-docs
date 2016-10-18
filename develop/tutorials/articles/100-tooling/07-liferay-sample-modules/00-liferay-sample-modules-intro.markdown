# Liferay Sample Modules [](id=liferay-sample-modules)

Liferay provides working examples of sample modules that target different
integration points in @product@. These working examples can be copy/pasted into
your own independent project so you can take advantage of various Liferay
extension points. Most of the sample modules include a single class using
Declarative Services. Therefore, each sample is developed to demonstrate a
single extension point and includes its own build files. Liferay's sample
modules can be found in the
[liferay-blade-samples](https://github.com/liferay/liferay-blade-samples)
repository on GitHub.

If you'd like to browse the repository locally or copy sample projects into your
own project, [fork](https://help.github.com/articles/fork-a-repo/) and
[clone](https://help.github.com/articles/cloning-a-repository/) the
`liferay-blade-samples` repository.

At first glance, you'll notice that the repository is broken up into four
primary folders:

- `bndtools`
- `gradle`
- `liferay-gradle`
- `maven`

The provided sample modules are organized by their development toolchains to
cater to a variety of developers. Each folder offers the same set of sample
Liferay modules. Their only difference is that the build files are specific to
their toolchain. For example, the `gradle` folder contains projects using
standard OSS Gradle plugins that can be added to any Gradle composite build. The
same concept also applies to the `bndtools` and `maven` projects.

The `liferay-gradle` folder also contains projects built with Gradle; however,
it uses the Liferay Gradle plugin (e.g., `com.liferay.plugin`) which encompasses
additional functionality for various types of Liferay modules. The Liferay
Gradle plugin is recommended for Gradle users developing for Liferay. 

Some developers are unable to adopt the `liferay-gradle` plugin, because it
takes over the standard Gradle JAR plugin, and there may be cases where it is
incompatible with an existing Gradle build configuration. The `gradle` folder is
therefore available for those who are unable to adopt the `liferay-gradle`
plugin. 

The sample projects for each toolchain demonstrate how to use various frameworks
like Declarative Services (DS) and the OSGi API.

+$$$

**Note:** There are two additional projects in the `/bndtools` folder that
you'll need to build and publish modules:

- `/bndtools/cnf` - the Bndtools configuration project.
- `/bndtools/blade.run` - A Bndtools project which can push modules into an OSGi
container and run them. The `biz.aQute.remote.agent-X.X.X.jar` must be deployed
before this project can work properly.

$$$

For a list of sample template projects available, visit the
[Liferay extension points](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-and-template-projects)
sub-section in the Liferay Blade Samples repository. This list is not
comprehensive. A subset of missing extension point samples can be found in the
[Liferay extension points without template projects](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-without-template-projects)
sub-section. Visit the repo's
[Contribution](https://github.com/liferay/liferay-blade-samples#contribution)
section for details on contributing to this repository.
