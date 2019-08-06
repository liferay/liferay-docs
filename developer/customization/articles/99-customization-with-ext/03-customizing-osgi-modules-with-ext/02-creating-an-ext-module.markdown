---
header-id: creating-an-ext-module
---

# Creating an Ext Module

[TOC levels=1-4]

You can create an Ext module based off the pre-configured `modules-ext` project
template/archetype. See the
[`modules-ext`](/docs/7-2/reference/-/knowledge_base/r/modules-ext-template)
project template article for information on how to create an Ext module, its
folder structure, and other important details.

It's recommended to create and develop your Ext module in a
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace).
Workspace is preconfigured with an `ext` folder, which applies important
settings (via the `LiferayOSGiExtPlugin`) to your Ext module when it's deployed
to @product@. You'll learn more about this in the next section.

You can also create an Ext module leveraging the `modules-ext` project template
in Dev Studio or IntelliJ. 

## Dev Studio

To create an Ext module in Dev Studio,

1.  Right click your Liferay Workspace and select *New* &rarr; *Liferay Module
    Ext Project*.

2.  Define the project name and the original module name (i.e., BSN form, like
    `com.liferay.login.web`) you intend to override.

    ![Figure 1: You must define your Ext module's name and the module you intend to override.](../../../images/ext-module-dev-studio.png)

    Note, you must have the `target.platform.index.sources` property enabled in
    your workspace's `gradle.properties` file to browse for the original module
    name.

3.  Click *Finish* to create the Module Ext project.

Awesome! You've created an Ext module project in Dev Studio!

## IntelliJ

To create an Ext module in IntelliJ,

1.  Go to *File* &rarr; *New* &rarr; *Liferay Module*.

2.  Select the *Liferay Modules Ext* option from the left menu.

3.  Define the original module name (i.e., BSN form, like
    `com.liferay.login.web`) you intend to override. Then click *Next*.

    ![Figure 2: You must define the module you intend to override.](../../../images/ext-module-intellij.png)

    Note, you must have the `target.platform.index.sources` property enabled in
    your workspace's `gradle.properties` file to browse for the original module
    name.

4.  Define the module name.

5.  Click *Finish*.

Great! You know how to create an Ext module and are familiar with its folder
structure and most significant files. Next, you'll learn how to develop your Ext
module to customize @product@. 
