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
specific workspace environment. Normally, Liferay developers who use Blade CLI
run a series of Blade commands that all make sense in the *default* Liferay
Workspace. What if the workspace, however, should support a containerized
environment (e.g., Docker) or some other specialized environment? The commands
used in the development workflow must complete the workflow differently.

To customize Blade CLI's development workflow, you must create a Blade
*profile*. Blade profiles let you *override* existing Blade commands or add
*new* commands in a preserved environment that can be applied to any Liferay
Workspace. For example, `blade init` for a profile `myprofile` would override
the default `init` command to do something before/after the normal `init`
command. For more information, see the
[Creating a Blade Profile](/docs/7-2/reference/-/knowledge_base/r/creating-a-blade-profile)
article.

| **Note:** Blade CLI leverages the profile system internally for Maven support.
| The Maven specific code is stored in an extension JAR and embedded inside the
| default Blade JAR.

Continue on to learn more!
