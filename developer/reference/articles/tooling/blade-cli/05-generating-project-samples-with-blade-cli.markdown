---
header-id: generating-project-samples-with-blade-cli
---

# Generating Project Samples with Blade CLI

[TOC levels=1-4]

Liferay provides many useful 
[sample projects](https://github.com/liferay/liferay-blade-samples) for those
interested in learning best practices for @product@ projects. You can learn more
about these samples by visiting 
[Sample Projects](/developer/reference/-/knowledge_base/7-1/sample-projects).

Rather than cloning the repository, you can generate these samples using Blade
CLI for convenience.

1.  List the available sample projects:

        blade samples

    Note the sample project you want to generate; you'll use it in the next
    step.

2.  Run the following command to generate a sample project:

        blade samples <NAME>

    For example, to generate the
    [portlet-ds](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/ds-portlet)
    sample, execute

        blade samples ds-portlet

    The sample is generated in the current folder.

| **Note:** Interested in generating legacy versions of Blade samples? Pass in the
| `-v` param followed by the @product@ version to target. For example,
| 
|     blade samples -v 7.0 ds-portlet

Awesome! You've successfully generated a Liferay sample project using Blade CLI!
