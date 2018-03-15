# Scoping Configurations [](id=scoping-configurations)

When an application is deployed, it's common to need different configurations
depending on the scope. That means having different configurations for a given
application per virtual instance (a.k.a. Company), site (a.k.a. Group), or
portlet instance. Achieve this with the Configuration Provider API that is based
on the standard OSGi Configuration Admin API shown in the previous section.

The `ExtendedObjectClassDefinition` annotation allows you to specify the scope
of the configuration. This must match how the configuration object is
retrieved through the provider (your choice). The valid options are:

- `Scope.GROUP`: for site scope
- `Scope.COMPANY`: for virtual instance scope
- `Scope.SYSTEM`: for system scope
- `Scope.PORTLET_INSTANCE`: for the portlet instance scope

Here is an example:

    @ExtendedObjectClassDefinition(
        category = "dynamic-data-mapping",
        scope = ExtendedObjectClassDefinition.Scope.GROUP
    )
    @Meta.OCD(
        id = "com.liferay.dynamic.data.mapping.form.web.configuration.
            DDMFormWebConfiguration",
        localization = "content/Language", 
        name = "ddm-form-web-configuration-name"
    )

    public interface DDMFormWebConfiguration {

The scope property isn't currently used for anything other than making it appear
in System Settings so that an administrator can change its value. In future
releases it may serve additional purposes.

## Enabling the Configuration for Scoped Retrieval [](id=enabling-the-configuration-for-scoped-retrieval)

To retrieve a scoped configuration, use a [Configuration
Provider](/develop/tutorials/-/knowledge_base/7-1/reading-configuration-values-from-a-configuration-provider).
The method call looks like this:

    JournalGroupServiceConfiguration configuration =
        configurationProvider.getGroupConfiguration(
            JournalGroupServiceConfiguration.class, groupId);

This is an example from the Journal module that gets a site-scoped configuration
from the configuration provider. To enable scoped retrieval of a configuration,
the application's configuration must be registered with a
`ConfigurationBeanDeclaration`.

+$$$

**Note:** `ConfigurationProvider` is part of Liferay's kernel API so you don't
need a new dependency to use it. However, its implementation is distributed as a
module called `portal-configuration-module-configuration`, so make sure it is
installed in order to use it.

$$$

Register the configuration class by writing a class that implements
`ConfigurationBeanDeclaration`. This class has one method that returns the class
of the interface you created in the previous section. It enables the system to
keep track of configuration changes as they happen, making requests for the
configuration very fast.

Declare the configuration interface by creating a `ConfigurationBeanDeclaration`
class:

    @Component
    public class RSSPortletInstanceConfigurationBeanDeclaration
        implements ConfigurationBeanDeclaration {

        @Override
        public Class getConfigurationBeanClass() {
            return RSSPortletInstanceConfiguration.class;
        }

    }

That's all there is to it. Now the configuration is scoped and supports scoped
retrieval.
