---
header-id: overriding-core-classes-with-ext-plugins
---

# Overriding Core Classes with Ext Plugins

[TOC levels=1-4]

A supported use case for using Ext plugins in @product@ is overriding its core
classes (e.g., `portal-impl`, `portal-kernel`, etc.). You can reference the
[Finding Core Liferay Portal Artifacts](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies#finding-core-liferay-portal-artifacts)
section for help distinguishing core classes. Make sure you've reviewed the
generalized
[Customization with Ext Plugins](/docs/7-2/reference/-/knowledge_base/r/customizing-core-functionality-with-ext)
section before creating an Ext plugin.

As an example, you'll create a sample Ext plugin that overwrites the
[PortalImpl](https://docs.liferay.com/ce/portal/7.2-latest/javadocs/portal-impl/com/liferay/portal/util/PortalImpl.html)
core class residing in the `portal-impl.jar`. You'll edit the
`PortalImpl.getComputerName()` method, which returns your server's node name.
The Ext plugin will override the entire `PortalImpl` class, adding the method
modifying the server's returned node name.

1.  Navigate to your Liferay Workspace's root folder and run the following
    command:

    ```bash
    blade create -t war-core-ext portal-impl-override
    ```

    Your Ext plugin is generated and now resides in the workspace's `/ext`
    folder with the name you assigned.

2.  Displaying the server node name in your @product@ installation is set to
    `false` by default. You'll need to enable this property. To do this,
    navigate into your Liferay bundle's root folder and create a
    `portal-ext.properties` file. In that file, insert the following property:

    ```properties
    web.server.display.node=true
    ```

    Now your server's node name will be displayed once your Liferay bundle is
    restarted.

3.  In the `/extImpl/java` folder, create the folder structure matching the
    class's folder structure you'd like to override (e.g.,
    `com/liferay/portal/util`). Then create the new Java class that will
    override the existing core class; your new class must have the same name as
    the original.

4.  Copy all of the original class's (e.g., `PortalImpl`) logic into your new
    class. Then modify the method you want to customize. For this example, you
    want to edit the `getComputerName()` method. Therefore, replace it with the
    method below:

    ```java
    @Override
    public String getComputerName() {
        return "sample_portalimpl_ext_installed_successfully_" + _computerName;
    }
    ```

    The method defined in the new class overrides the
    `PortalImpl.getComputerName()` method. The
    `sample_portalimpl_ext_installed_successfully_` String is now prefixed to
    your server's node name.

When your Ext plugin is deployed, your new Java class will override the core
`PortalImpl` class.

Awesome! You've created an Ext plugin that overrides a core class in @product@!
Follow the instructions in the
[Deploy the Plugin](/docs/7-2/reference/-/knowledge_base/r/deploying-an-ext-plugin)
article to deploy it to your server.
