---
header-id: extending-blade-cli
---

# Extending Blade CLI

[TOC levels=1-4]

There are several ways you can extend Blade CLI. In simple cases, you can add
custom commands and templates. These are *extensions* of Blade CLI. When
creating extensions, you can specify the extension name and help text, and
implement it to meet your requirements. For more information on creating and
installing custom extensions for Blade CLI, see the following topics:

- [Creating Custom Commands for Blade CLI](/docs/7-2/reference/-/knowledge_base/r/creating-custom-commands-for-blade-cli)
- [Creating Custom Project Templates for Blade CLI](/docs/7-2/reference/-/knowledge_base/r/creating-custom-project-templates-for-blade-cli)
- [Installing New Extensions for Blade CLI](/docs/7-2/reference/-/knowledge_base/r/installing-new-extensions-for-blade-cli)

There are a few use cases to consider when extending Blade CLI. For example, if
you only want to add a new command that adds to Blade CLI's standard set of
commands and also applies globally to all types of workspaces, you can create
and install a new custom command as explained in the links above.

Alternatively, you may want a set of custom commands that only apply to a
specific workspace environment. If you want to create a fully-customized Liferay
Workspace workflow tailored for your requirements in Blade CLI, you can create
extensions that are all associated with a Blade *profile*. Whenever that profile
is applied to a workspace, the custom extensions it provides are available.

For example, suppose you want to build some custom commands that only work when
the Liferay Workspace is configured to use Docker or OpenShift. For this, you
should create a Blade profile that supports the development workflow in that
environment. For more information, see the
[Creating a Blade Profile](/docs/7-2/reference/-/knowledge_base/r/creating-a-blade-profile)
article.

Continue on to learn more!
