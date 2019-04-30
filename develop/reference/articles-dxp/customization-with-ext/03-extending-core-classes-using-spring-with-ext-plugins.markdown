# Extending Core Classes Using Spring with Ext Plugins [](id=extending-core-classes-using-spring-with-ext-plugins)

A supported use case for using Ext plugins in Liferay DXP is extending Liferay
Digital Enterprise's core classes (e.g., `portal-impl`, `portal-kernel`, etc.)
using Spring. You can reference the
[Finding Core Liferay Portal Artifacts](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies#finding-core-liferay-portal-artifacts)
section for help distinguishing core classes. Make sure you've reviewed the
generalized
[Customization with Ext Plugins](/documentation/7.0/develop/tutorials/-/official_documentation/tutorials/advanced-customization-with-ext-plugins)
tutorial before creating an Ext plugin.

As an example, you'll create a sample Ext plugin that extends the
[PortalImpl](https://docs.liferay.com/ce/portal/7.0-latest/javadocs/portal-impl/com/liferay/portal/util/PortalImpl.html)
core class residing in the `portal-impl.jar`. You'll override the
`PortalImpl.getComputerName()` method via Spring bean, which returns your
server's node name. The Ext plugin will override this method and modify the
server's returned node name.

1.  Navigate into your Plugins SDK's `/ext` folder and run the following
    command:

        create.[bat|sh]  portal-impl-extend-spring "PortalImpl Extend Spring"

    Your Ext plugin is generated and now resides in the Plugins SDK's `/ext`
    folder with the name you assigned followed by `-ext` (e.g.,
    `portal-impl-extend-spring-ext`).

2.  Displaying the server node name in your Liferay DXP installation is set to
    `false` by default. You'll need to enable this property. To do this,
    navigate into your Liferay bundle's root folder and create a
    `portal-ext.properties` file. In that file, insert the following property:

        web.server.display.node=true

    Now your server's node name will be displayed once your Liferay bundle is
    restarted.

3.  In the `/ext-impl/src` folder, create the folder structure representing the
    package name you want your new class to reside in (e.g.,
    `com/liferay/portal/util`). Then create your new Java class:

        package com.liferay.portal.util;

        public class SamplePortalImpl extends PortalImpl {

            @Override
            public String getComputerName() {
                return "SAMPLE_EXT_INSTALLED_" + super.getComputerName();
            }

        }

The method defined in the extension class overrides the
`PortalImpl.getComputerName()` method. The `"SAMPLE_EXT_INSTALLED_"` String is
now prefixed to your server's node name.

4.  In your Ext plugin's `/ext-impl/src` folder, create a
    `META-INF/ext-spring.xml` file. In this file, insert the following code:

        <?xml version="1.0"?>

        <beans
            default-destroy-method="destroy"
            default-init-method="afterPropertiesSet"
            xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
        >

            <bean class="com.liferay.portal.util.SamplePortalImpl" id="com.liferay.portal.util.PortalImpl" />
        </beans>

Since you plan on modifying a core service class, you can inject its extension
class via a Spring bean. This will ensure your new class is recognized. Assign
your extension class's fully defined class name (e.g.,
`com.liferay.portal.util.SamplePortalImpl`) to the bean tag's `class` attribute and
the fully defined original class name (e.g., `com.liferay.portal.util.PortalImpl`)
to the bean tag's `id` attribute.

When your Ext plugin is deployed, your new service (e.g., `SamplePortalImpl`)
will extend the core `PortalImpl` class.

Awesome! You've created an Ext plugin that extends a core class in Liferay
Digital Enterprise! Follow the instructions in the
[Deploy the Plugin](/documentation/7.0/develop/tutorials/-/official_documentation/tutorials/advanced-customization-with-ext-plugins#deploy-the-plugin)
section to deploy it to your server.
