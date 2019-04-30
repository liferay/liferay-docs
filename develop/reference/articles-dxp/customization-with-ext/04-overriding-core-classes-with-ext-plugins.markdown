# Overriding Core Classes with Ext Plugins [](id=overriding-core-classes-with-ext-plugins)

A supported use case for using Ext plugins in Liferay DXP is overriding Liferay
Digital Enterprise's core classes (e.g., `portal-impl`, `portal-kernel`, etc.).
You can reference the
[Finding Core Liferay Portal Artifacts](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies#finding-core-liferay-portal-artifacts)
section for help distinguishing core classes. Make sure you've reviewed the
generalized
[Customization with Ext Plugins](/documentation/7.0/develop/tutorials/-/official_documentation/tutorials/advanced-customization-with-ext-plugins)
tutorial before creating an Ext plugin.

As an example, you'll create a sample Ext plugin that overwrites the
[PortalImpl](https://docs.liferay.com/ce/portal/7.0-latest/javadocs/portal-impl/com/liferay/portal/util/PortalImpl.html)
core class residing in the `portal-impl.jar`. You'll edit the
`PortalImpl.getComputerName()` method, which returns your server's node name.
The Ext plugin will override the entire `PortalImpl` class, adding the method
modifying the server's returned node name.

1.  Navigate into your Plugins SDK's /ext folder and run the following command:

        create.[bat|sh]  portal-impl-override "PortalImpl Override"

    Your Ext plugin is generated and now resides in the Plugins SDK's `/ext`
    folder with the name you assigned followed by `-ext` (e.g.,
    `portal-impl-override-spring`).

2.  Displaying the server node name in your Liferay DXP installation is set to
    `false` by default. You'll need to enable this property. To do this,
    navigate into your Liferay bundle's root folder and create a
    `portal-ext.properties` file. In that file, insert the following property:

        web.server.display.node=true

    Now your server's node name will be displayed once your Liferay bundle is
    restarted.

3.  In the `/ext-impl/src` folder, create the folder structure matching the
class's folder structure you'd like to override (e.g., `com/liferay/portal/util`).
Then create the new Java class that will override the existing core class; your
new class must have the same name as the original.

4.  Copy all of the original class's (e.g., `PortalImpl`) logic into your new
class. Then modify the method you want to customize. For this example, you want
to edit the `getComputerName()` method. Therefore, replace it with the method
below:

        @Override
        public String getComputerName() {
            return â€œsample_portalimpl_ext_installed_successfully_â€? + _computerName;
        }

    The method defined in the new class overrides the
    `PortalImpl.getComputerName()` method. The
    `sample_portalimpl_ext_installed_successfully_` String is now prefixed to
    your server's node name.

When your Ext plugin is deployed, your new Java class will override the core
`PortalImpl` class.

Awesome! You've created an Ext plugin that overrides a core class in Liferay
Digital Enterprise! Follow the instructions in the
[Deploy the Plugin](/documentation/7.0/develop/tutorials/-/official_documentation/tutorials/advanced-customization-with-ext-plugins#deploy-the-plugin)
section to deploy it to your server.
