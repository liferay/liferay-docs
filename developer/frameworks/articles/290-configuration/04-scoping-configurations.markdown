# Scoping Configurations [](id=scoping-configurations)

Here's how to scope a configuration: 

1.  Set the scope in the configuration interface.

2.  Enable the configuration for scoped retrieval by creating a configuration
    bean declaration.

## Step 1: Setting the Configuration Scope [](id=step-1-setting-the-configuration-scope)

Use the `@ExtendedObjectClassDefinition` annotation to specify the
configuration's scope. The scope you choose must match how the configuration
object is retrieved through the [configuration
provider](/develop/tutorials/-/knowledge_base/7-1/reading-configuration-values-from-a-configuration-provider).
Pass one of these valid scope options to `@ExtendedObjectClassDefinition`:

`Scope.SYSTEM`: for system scope
`Scope.COMPANY`: for virtual instance scope
`Scope.GROUP`: for site scope
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

## Step 2: Enabling the Configuration for Scoped Retrieval [](id=enabling-the-configuration-for-scoped-retrieval)

To create a configuration bean declaration:

1.  Register the configuration class by implementing `ConfigurationBeanDeclaration`.

        @Component
        public class JournalGroupServiceConfigurationBeanDeclaration
            implements ConfigurationBeanDeclaration {

2.  This class has one method that returns the class of the configurationn
    interface you created. It enables the system to keep track of configuration
    changes as they happen, making requests for the configuration very fast.

        @Override
        public Class<?> getConfigurationBeanClass() {
            return JournalGroupServiceConfiguration.class;
        }

That's all there is to it. Now the configuration is scoped and supports scoped
retrieval.
