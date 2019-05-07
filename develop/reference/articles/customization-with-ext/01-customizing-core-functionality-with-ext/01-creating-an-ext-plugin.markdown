# Creating an Ext Plugin [](id=creating-an-ext-plugin)

An Ext plugin is a powerful tool for extending @product@. Because it increases		
the complexity of your @product@ installation, you should only use an Ext plugin		
if you're sure you can't accomplish your goal in a different way. You can		
create Ext plugins using the pre-configured `war-core-ext` project
template/archetype. See the
[`war-core-ext`](/develop/reference/-/knowledge_base/7-1/war-core-ext-template)
project template article for information on how to create an Ext plugin, its
folder structure, and other important details.

It's recommended to create and develop your Ext plugin in a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace).
Workspace is preconfigured with an `ext` folder, which applies important
settings (via the `LiferayExtPlugin`) to your Ext plugin when it's deployed to
@product@. You'll learn more about this in the
[Set Up the Build Environment](/develop/reference/-/knowledge_base/7-1/developing-an-ext-plugin#set-up-the-build-environment)
section.

Next you'll learn the anatomy of an Ext plugin.
