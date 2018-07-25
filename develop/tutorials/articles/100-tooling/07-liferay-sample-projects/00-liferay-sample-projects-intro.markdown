# Liferay Sample Projects [](id=liferay-sample-modules)

Liferay provides working examples of sample projects that target different
integration points in @product@. These working examples can be copy/pasted into
your own independent project so you can take advantage of various Liferay
extension points. Each sample is a standalone project and includes its own build
files. Liferay's sample projects can be found in the
[liferay-blade-samples](https://github.com/liferay/liferay-blade-samples)
repository on GitHub. You can find documentation for Liferay's sample projects
in the
[Sample Projects](/develop/reference/-/knowledge_base/7-0/sample-modules)
reference section.

If you'd like to browse the repository locally or copy sample projects into your
own project, [fork](https://help.github.com/articles/fork-a-repo/) and
[clone](https://help.github.com/articles/cloning-a-repository/) the
`liferay-blade-samples` repository.

<!-- You can also use Blade to create samples (`blade samples [SAMPLE_NAME]`),
but that functionality is currently broken. Update this article with those
instructions when it's fixed. -Cody -->

At first glance, you'll notice that the repository is broken up into three
primary folders:

- `gradle`
- `liferay-workspace`
- `maven`

The provided sample projects are organized by their development toolchains to
cater to a variety of developers. Each folder offers the same set of sample
Liferay projects. Their only difference is that the build files are specific to
their toolchain. For example, the `gradle` folder contains projects using
standard OSS Gradle plugins that can be added to any Gradle composite build. The
same concept also applies to the `liferay-workspace` and `maven` projects.

The `gradle` folder also uses the Liferay Gradle plugin (e.g.,
`com.liferay.plugin`) which encompasses additional functionality for various
types of Liferay projects. The Liferay Gradle plugin is recommended for Gradle
users developing for Liferay. 

Some samples also come configured with logging to help you fully understand
what the sample is accomplishing behind the scenes. For example, OSGi module
logging is implemented for several samples (e.g.,
[action-command-portlet](https://github.com/liferay/liferay-blade-samples/tree/7.0/gradle/apps/action-command-portlet),
[document-action](/develop/reference/-/knowledge_base/7-0/document-action),
[service-builder/jdbc](/develop/reference/-/knowledge_base/7-0/service-builder-application-using-external-database-via-jdbc),
etc.), which lets OSGi modules supply their own logging configuration defaults
without external configuration. See the
[Adjusting Module Logging](/develop/tutorials/-/knowledge_base/7-0/adjusting-module-logging)
tutorial for more information.

For a list of sample template projects available, visit the
[Liferay extension points](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-and-template-projects)
sub-section in the Liferay Blade Samples repository. This list is not
comprehensive. A subset of missing extension point samples can be found in the
[Liferay extension points without template projects](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-without-template-projects)
sub-section. Visit the repo's
[Contribution Guidelines](https://github.com/liferay/liferay-blade-samples#contribution-guidelines)
section for details on contributing to this repository.
