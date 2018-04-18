# Scoping Configurations [](id=scoping-configurations)

Applications can have different configurations depending on the scope: per
virtual instance (a.k.a. `Company`), site (a.k.a. `Group`), or portlet instance.
The Configuration Provider API (based on the standard OSGi Configuration Admin
API shown in the previous section) handles this for you.

Scoping the configuration is specifying the scope where the configuration values
are set or overridden. Anything set at a less granular scope is just a default
for the configuration. It can always be overridden at the configuration's
current scope. For example, a site scoped configuration can have its defaults
set at the system scope (via System Settings). However, once the configuration
is changed at the site scope, it ignores the higher level scope forever. It can
also be configured in other places at the same scope. From the database level,
this means there could be multiple configuration values for the application, all
scoped to the site level, because the values set in one site don't matter if the
context in which you need the value is a different site. This is covered in more
detail
[here](/discover/portal/-/knowledge_base/7-1/configuring-liferay#configuration-scope).

Here's how to scope a configuration: 

1.  Set the scope in the configuration interface.

2.  Enable the configuration for scoped retrieval by creating a configuration
    bean declaration.

3.  Retrieve scoped configurations with a configuration provider.

The third step is covered in the
[configuration provider](/develop/tutorials/-/knowledge_base/7-1/reading-configuration-values-from-a-configuration-provider)
tutorial. This article covers the first two steps. Start by setting the scope in
the configuration interface.

## Step 1: Setting the Configuration Scope [](id=step-1-setting-the-configuration-scope)

Use the `@ExtendedObjectClassDefinition` annotation to specify the
configuration's scope. The scope you choose must match how the configuration
object is retrieved through the 
[configuration provider](/develop/tutorials/-/knowledge_base/7-1/reading-configuration-values-from-a-configuration-provider)
configuration provider. Pass one of these valid scope options to
`@ExtendedObjectClassDefinition`:

`Scope.GROUP`: for site scope
`Scope.COMPANY`: for virtual instance scope
`Scope.SYSTEM`: for system scope
`Scope.PORTLET_INSTANCE`: for the portlet instance scope

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

The scope property makes it appear in System Settings so an administrator
can change its value. In future releases it may serve additional purposes.

## Step 2: Enabling the Configuration for Scoped Retrieval [](id=enabling-the-configuration-for-scoped-retrieval)

If you set the configuration scope, you must retrieve the configuration values
from the same scope. To retrieve a scoped configuration, use a 
[Configuration Provider](/develop/tutorials/-/knowledge_base/7-1/reading-configuration-values-from-a-configuration-provider):

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
installed.

$$$

To create a configuration bean declaration:

1.  Register the configuration class by implementing `ConfigurationBeanDeclaration`.

        @Component
        public class JournalGroupServiceConfigurationBeanDeclaration
            implements ConfigurationBeanDeclaration {

2.  This class has one method that returns the class of the interface you
    created in the previous section. It enables the system to keep track of
    configuration changes as they happen, making requests for the configuration
    very fast.

        @Override
        public Class<?> getConfigurationBeanClass() {
            return JournalGroupServiceConfiguration.class;
        }

## Step 3: Retrieving Scoped Configurations [](id=step-3-retrieving-scoped-configurations)

If you set the configuration scope, then you must retrieve the configuration
values from the same scope. To retrieve a scoped configuration, use a 
[Configuration Provider](/develop/tutorials/-/knowledge_base/7-1/reading-configuration-values-from-a-configuration-provider):

    JournalGroupServiceConfiguration configuration =
        configurationProvider.getGroupConfiguration(
            JournalGroupServiceConfiguration.class, groupId);

This is an example from the Journal module that gets a site-scoped configuration
from the configuration provider. The `groupId` variable is important since it
identifies which site the configuration value should be read from.

That's all there is to it. Now the configuration is scoped and supports scoped
retrieval.
