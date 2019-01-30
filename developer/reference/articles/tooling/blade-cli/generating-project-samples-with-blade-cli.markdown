# Generating Project Samples with Blade CLI

Liferay provides many
[sample projects](https://github.com/liferay/liferay-blade-samples) that are
useful for those interested in learning best practices on structuring their
@product@ projects to accomplish specific tasks. You can learn more about these
samples by visiting the
[Sample Projects](/developer/reference/-/knowledge_base/7-1/sample-projects)
reference section.

You can generate these samples using Blade CLI for convenience, instead of
cloning the repository and manually copy/pasting them to your environment.

1.  Print the available sample projects by executing this:

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

+$$$

**Note:** Interested in generating legacy versions of Blade samples? Pass in the
`-v` param followed by the @product@ version to target. For example,

    blade samples -v 7.0 ds-portlet

$$$

Awesome! You've successfully generated a Liferay sample project using Blade CLI!
