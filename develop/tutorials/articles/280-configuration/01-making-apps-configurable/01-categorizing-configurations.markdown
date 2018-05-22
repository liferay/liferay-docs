# Categorizing the Configuration [](id=categorizing-the-configuration)

Because it's easy to make any application or service configurable, there are
already lots of configuration options in @product@ by default. If you've
deployed custom applications and services, there are even more. To make it
easier for portal administrators to find the right configuration options,
specify a category for the configuration in the auto-generated System Settings
UI.

<!-- Here's how the System Settings UI looks:

IMAGES NEED TO BE UPDATED WHEN UI STABILIZES ![Figure 1: Navigate to the Control Panel, click on *Configuration* and then
*System Settings*. You'll find five categories of configurations, including
Other. Click on any configuration to access a form through which the
configuration values can be updated.](../../images/system-settings-ui.png) -->

By default, the following System Settings sections are defined. All
available categories are nested beneath these sections:

1.  Content Management

2.  Social

3.  Platform

4.  Security 

5.  Commerce

6.  Other

+$$$

**Note:** Sections appear if they contain at least one configuration category.
Categories appear if they contain at least one configuration. The visible
sections and categories depend on the deployed modules.

$$$

If you don't specify a category, your application's configuration resides in
Platform &rarr; Third Party. Usually, you'll want to place your configurations
in an existing category or create your own.

<!-- Add Figure of Platform Third Party section when UI stabilizes -->.

## Specifying a Configuration Category [](id=specifying-a-configuration-category)

If you looked in the source code at the Liferay Forms configuration interface
(it's in the Forms & Workflow suite's Liferay Dynamic Data Mapping Form Web
module), you'll notice something was left out of the code snippet above. The
`@Meta.OCD` annotation is directly preceded by

        @ExtendedObjectClassDefinition(
            category = "dynamic-data-mapping",
            scope = ExtendedObjectClassDefinition.Scope.GROUP
        )

This annotation does two things:

1.  Specifies the `dynamic-data-mapping` category.

2.  Sets the 
    [scope](/discover/portal/-/knowledge_base/7-1/configuring-liferay#configuration-scope) 
    of the configuration.

The fully qualified class name of the `@ExtendedObjectClassDefinition` class is
`com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition`.

Note: The infrastructure used by System Settings assumes the `configurationPid`
is the same as the fully qualified class name of the interface. If they don't
match, it can't provide any information through
`ExtendedObjectClassConfiguration`.

The `@ExtendedObjectClassDefinition` annotation is distributed through the
`com.liferay.portal.configuration.metatype` module, which you can 
[configure as a dependency](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies). 

## Creating New Sections and Categories [](id=creating-new-sections-and-categories)

Configurations should be in the most intuitive location (section and category)
so administrators find them. If your configurations don't fit into the existing
categories or category sections, create your own by implementing the
`ConfigurationCategory` interface.

Here's code that creates the *Content Management* section and the *Dynamic
Data Mapping* category:

        @Component
        public class DynamicDataMappingConfigurationCategory
            implements ConfigurationCategory {

            @Override
            public String getCategoryIcon() {
                return "dynamic-data-list";
            }

            @Override
            public String getCategoryKey() {
                return _KEY;
            }

            @Override
            public String getCategorySection() {
                return _CATEGORY_SET_KEY;
            }

            private static final String _CATEGORY_SET_KEY = "content-management";

            private static final String _KEY = "dynamic-data-mapping";

        }

The `getCategorySection` method returns the String with the new section's key.
Similarly, `getCategoryKey` returns the key for the new category. Provide
localized values for these keys in your module's
`src/main/resources/content/Language.properties` file.

Next you'll specify the scope of your application's configuration.
