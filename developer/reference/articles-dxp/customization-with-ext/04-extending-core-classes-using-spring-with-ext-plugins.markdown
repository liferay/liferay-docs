# Extending Core Classes Using Spring with Ext Plugins [](id=extending-core-classes-using-spring-with-ext-plugins)

A supported use case for using Ext plugins in @product@ is extending its core
classes (e.g., `portal-impl`, `portal-kernel`, etc.) using Spring. You can
reference the
[Finding Core Liferay Portal Artifacts](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies#finding-core-liferay-portal-artifacts)
section for help distinguishing core classes. Make sure you've reviewed the
generalized
[Customization with Ext Plugins](/docs/7-2/reference/-/knowledge_base/r/customizing-core-functionality-with-ext)
section before creating an Ext plugin.

As an example, you'll create a sample Ext plugin that extends the
[PortalImpl](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-impl/com/liferay/portal/util/PortalImpl.html)
core class residing in the `portal-impl.jar`. You'll override the
`PortalImpl.getComputerName()` method via Spring bean, which returns your
server's node name. The Ext plugin will override this method and modify the
server's returned node name.

1.  Navigate to your Liferay Workspace's root folder and run the following
    command:

        blade create -t war-core-ext portal-impl-extend-spring-ext

    Your Ext plugin is generated and now resides in the workspace's `/ext`
    folder with the name you assigned.

2.  Displaying the server node name in your @product@ installation is set to
    `false` by default. You'll need to enable this property. To do this,
    navigate into your Liferay bundle's root folder and create a
    `portal-ext.properties` file. In that file, insert the following property:

        web.server.display.node=true

    Now your server's node name will be displayed once your Liferay bundle is
    restarted.

3.  In the `/extImpl/java` folder, create the folder structure representing the
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

4.  In your Ext plugin's `/extImpl/resources` folder, create a
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
`com.liferay.portal.util.SamplePortalImpl`) to the bean tag's `class` attribute
and the fully defined original class name (e.g.,
`com.liferay.portal.util.PortalImpl`) to the bean tag's `id` attribute.

When your Ext plugin is deployed, your new service (e.g., `SamplePortalImpl`)
will extend the core `PortalImpl` class.

Awesome! You've created an Ext plugin that extends a core class in @product@!
Follow the instructions in the
[Deploy the Plugin](/docs/7-2/reference/-/knowledge_base/r/deploying-an-ext-plugin)
article to deploy it to your server.
