# Liferay Sample Modules [](id=liferay-sample-modules)

Liferay provides working examples of sample modules that target different
integration points in @product@. These working examples can be copy/pasted into
your own independent project so you can take advantage of various Liferay
extension points. Most of the sample modules include a single class using
Declarative Services. Therefore, each sample is developed to demonstrate a
single extension point and includes its own build files. Liferay's sample
modules can be found in the
[liferay-blade-samples](https://github.com/liferay/liferay-blade-samples)
repository on GitHub. You can find documentation for Liferay's sample modules in
the
[Sample Modules](/develop/reference/-/knowledge_base/7-0/sample-modules)
reference section.

If you'd like to browse the repository locally or copy sample projects into your
own project, [fork](https://help.github.com/articles/fork-a-repo/) and
[clone](https://help.github.com/articles/cloning-a-repository/) the
`liferay-blade-samples` repository.

At first glance, you'll notice that the repository is broken up into three
primary folders:

- `gradle`
- `liferay-workspace`
- `maven`

The provided sample modules are organized by their development toolchains to
cater to a variety of developers. Each folder offers the same set of sample
Liferay modules. Their only difference is that the build files are specific to
their toolchain. For example, the `gradle` folder contains projects using
standard OSS Gradle plugins that can be added to any Gradle composite build. The
same concept also applies to the `maven` and `liferay-workspace` projects.

The `gradle` folder also uses the Liferay Gradle plugin (e.g.,
`com.liferay.plugin`) which encompasses additional functionality for various
types of Liferay modules. The Liferay Gradle plugin is recommended for Gradle
users developing for Liferay. 

The sample projects for each toolchain demonstrate how to use various frameworks
like Declarative Services (DS) and the OSGi API.

For a list of sample template projects available, visit the
[Liferay extension points](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-and-template-projects)
sub-section in the Liferay Blade Samples repository. This list is not
comprehensive. A subset of missing extension point samples can be found in the
[Liferay extension points without template projects](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-without-template-projects)
sub-section. Visit the repo's
[Contribution](https://github.com/liferay/liferay-blade-samples#contribution)
section for details on contributing to this repository.
