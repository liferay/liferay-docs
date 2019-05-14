---
header-id: creating-an-ext-plugin
---

# Creating an Ext Plugin

[TOC levels=1-4]

An Ext plugin is a powerful tool for extending @product@. Because it increases		
the complexity of your @product@ installation, you should only use an Ext plugin		
if you're sure you can't accomplish your goal in a different way. You can		
create Ext plugins using the pre-configured `war-core-ext` project
template/archetype. See the
[`war-core-ext`](/docs/7-2/reference/-/knowledge_base/r/war-core-ext-template)
project template article for information on how to create an Ext plugin, its
folder structure, and other important details.

It's recommended to create and develop your Ext plugin in a
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace).
Workspace is preconfigured with an `ext` folder, which applies important
settings (via the `LiferayExtPlugin`) to your Ext plugin when it's deployed to
@product@. You'll learn more about this in the
[Set Up the Build Environment](/docs/7-2/reference/-/knowledge_base/r/developing-an-ext-plugin#set-up-the-build-environment)
section.

Next you'll learn the anatomy of an Ext plugin.
