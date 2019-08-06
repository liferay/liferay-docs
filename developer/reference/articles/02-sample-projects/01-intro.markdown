---
header-id: sample-projects
---

# Sample Projects

[TOC levels=1-4]

| **Note:** This section of articles does not provide documentation for *all*
| sample projects residing in the `liferay-blade-samples` repo. The documentation
| for these samples is in progress and will grow over time.

Liferay provides sample projects that target different integration points in
@product@. These projects reside in the
[liferay-blade-samples](https://github.com/liferay/liferay-blade-samples)
Github repository and can be easily copy/pasted to your local environment. You
can also generated them using
[Blade CLI](/docs/7-2/reference/-/knowledge_base/r/generating-project-samples-with-blade-cli).

The sample projects are grouped into three different parent folders based on the
build tools used to generate them:

- `gradle`
- `liferay-workspace`
- `maven`

The provided sample projects are organized by their development toolchains to
cater to a variety of developers. Each folder offers the same set of sample
Liferay projects. Their only difference is that the build files are specific to
their toolchain. For example, the `gradle` folder contains projects using
standard OSS Gradle plugins that can be added to any Gradle composite build. The
same concept also applies to the `liferay-workspace` and `maven` projects.

| **Note:** The Liferay Workspace folder stores WAR-type samples in a separate
| folder named
| [wars](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/wars).
| The Gradle and Maven tool folders mix WAR samples with the other sample types
| (apps, extensions, etc.).

The `gradle` folder also uses the Liferay Gradle plugin (e.g.,
`com.liferay.plugin`) which encompasses additional functionality for various
types of Liferay projects. The Liferay Gradle plugin is recommended for Gradle
users developing for @product@. 

Some samples also come configured with logging to help you fully understand
what the sample is accomplishing behind the scenes. For example, OSGi module
logging is implemented for several samples (e.g.,
[action-command-portlet](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/action-command-portlet),
[document-action](/docs/7-2/reference/-/knowledge_base/r/document-action),
[service-builder/jdbc](/docs/7-2/reference/-/knowledge_base/r/service-builder-application-using-external-database-via-jdbc),
etc.), which lets OSGi modules supply their own logging configuration defaults
without external configuration. See the
[Adjusting Module Logging](/docs/7-2/appdev/-/knowledge_base/a/adjusting-module-logging)
article for more information.

For a list of sample template projects available, visit the
[Liferay extension points](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-and-template-projects)
sub-section in the Liferay Blade Samples repository. This list is not
comprehensive. A subset of missing extension point samples can be found in the
[Liferay extension points without template projects](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-without-template-projects)
sub-section. Visit the repo's
[Contribution Guidelines](https://github.com/liferay/liferay-blade-samples#contribution-guidelines)
section for details on contributing to this repository.
