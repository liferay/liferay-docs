---
header-id: using-other-build-systems-and-ides
---

# Using Other Build Systems and IDEs

[TOC levels=1-4]

@product@ is tool agnostic--you can use whatever tools you like to develop on
it. You can use any IDE and even use Gradle, Bnd, or BndTools if you don't want
to use Workspace. The drawback is you lose the Liferay-specific project
templates that you get with Blade and Workspace.

Blade lets you create modules to develop anywhere, not only in Liferay
Workspace.

Here are some new Gradle features Liferay provides that are independent of
Workspace: 

- Liferay's [Gradle plugins](/docs/7-1/reference/-/knowledge_base/r/gradle)
- Buildship plugins in Liferay @ide@
- Liferay @ide@'s
  [new Gradle views](/docs/7-1/tutorials/-/knowledge_base/t/using-gradle-in-liferay-ide)
  for developing modules and working with Gradle tasks

Liferay has worked hard to make @product@ IDE-agnostic. There are Liferay module
developers who use IntelliJ and some enjoy using NetBeans.

Finally, you can copy and modify 
[Liferay sample projects](/docs/7-1/tutorials/-/knowledge_base/t/liferay-sample-projects) 
to serve as templates in place of the Blade templates. They're available for
these build systems:

- Gradle
- Liferay Workspace
- Maven

Liferay's approach to tooling has vastly improved for @product-ver@. Our tools
help you upgrade to @product-ver@, continue developing traditional plugins the
way you have been, and migrate to optimal development environments. Liferay
Workspace and the improved Maven support facilitate module development. And
developing on @product@ using other tools is easier than ever. Your tool options
are wide open.
