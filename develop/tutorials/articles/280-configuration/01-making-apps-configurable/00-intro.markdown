# Making Applications Configurable [](id=making-applications-configurable)

You don't need much prior knowledge to use the configuration API, but
understanding a few key concepts is useful before diving into the code.

+$$$

**Note:** To see a working application configuration, deploy the
`configuration-action` 
[Blade sample](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/configuration-action)
and navigate to System Settings (*Control Panel* &rarr; *Configuration* &rarr;
*System Settings*). In the Other category, click the *Message display
configuration* entry.

Add the *Blade Message Portlet* to a page to test your configuration choices.

$$$

**Typed Configuration**
: The method described here uses *typed* configuration. The application
configuration isn't just a list of key-value pairs. Values can have types,
like `Integer`, a list of `Strings`, a URL, etc. It's even possible to use your
own custom types, although that's beyond the scope of this tutorial. Typed
configurations are easier to use than untyped configurations, and they prevent
many programmatic errors. Configuration options should be programmatically
explicit, so developers can use autocomplete in modern IDEs to find out all
configuration options of a given application or one of its components.

**Modularity** 
: Modern applications are *modular* and built as a collection of lightweight
[components](/develop/tutorials/-/knowledge_base/7-1/fundamentals#components).

**Configuration Scope** 
: If your application must support different configurations at different scopes,
the APIs described below handle most of the burden for you. You should still
understand the term *configuration scope* even if you don't plan to scope the
application's configuration. Here are the most common configuration scopes:

   - *System* configurations are unique for the complete installation of
     the application.

   - *Virtual Instance* configurations can vary per virtual instance.
 
   - *Site* configuration can vary per site.

   - *Portlet Instance* configurations apply to a single application placed on a
     page (i.e., portlets). Each placement (instance) of the application on the
     page can have a different configuration.

Enough with the conceptual stuff. You're ready to get started with some code. If
you already had a portlet or service that was configurable using the traditional
mechanisms of Liferay Portal 6.2 and before, refer to the
[Transitioning from Portlet Preferences to the Configuration API](/develop/tutorials/-/knowledge_base/7-1/transitioning-from-portlet-preferences-to-the-configuration-api) tutorial.

## Creating A Configuration Interface

First, you'll learn how to create a configuration at the system scope.

1.  Create a Java interface to represent the configuration and its default
    values. Using a Java interface allows for an advanced type system for each
    configuration option. Here is an example interface:

        @Meta.OCD(id = "com.liferay.docs.exampleconfig.ExampleConfiguration")
        public interface ExampleConfiguration {

            @Meta.AD(
                deflt = "blue",
                required = false
            )
            public String favoriteColor();

            @Meta.AD(
               deflt = "red|green|blue",
               required = false
            )
            public String[] validColors();

            @Meta.AD(required = false)
            public int itemsPerPage();

        }

    Here's what the two Java annotations in the above snippet do:

    1.  **Meta.OCD:** Registers this class as a configuration with a specific id. The
        ID must be the fully qualified configuration class name.

    2.  **Meta.AD:** Specifies 
        [optional metadata](http://bnd.bndtools.org/chapters/210-metatype.html) 
        about the field, such as whether it's a required field or if it has a default value.
        Note that if you set a field as required and don't specify a default value,
        the system administrator must specify a value in order for your application
        to work properly. Use the `deflt` property to specify a default value.

    The fully-qualified name of the `Meta` class above is
    `aQute.bnd.annotation.metatype.Meta`. For more information about this class and
    the `Meta.OCD` and `Meta.AD` annotations, please refer to the [bndtools
    documentation](http://bnd.bndtools.org/chapters/210-metatype.html).

2.  To use the `Meta.OCD` and `Meta.AD` annotations in your modules, you must
    [specify a dependency](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)
    on the bnd library. We recommend using bnd version 3. Here's an example of
    how to include this dependency in a Gradle project: 

        dependencies {
            compile group: "biz.aQute.bnd", name: "biz.aQute.bndlib", version: "3.1.0"
        }

    +$$$

    **Note:** The annotations `@Meta.OCD` and `@Meta.AD` are part of the bnd
    library, but as of OSGi standard version R6, they're included in the OSGi core
    under the names `@ObjectClassDefinition` and `@AttributeDefinition`. The OSGi
    annotations can be used for simple cases like the one described in this
    tutorial. However, a key difference between the two libraries is that the bnd
    annotations are available at runtime, while the OSGi annotations are not.
    Because runtime availability is necessary for some of the Liferay-specific features
    described below, we recommend defaulting to the bnd annotations.

    $$$

3.  Add the following line to your project's `bnd.bnd` file:

        -metatype: *

    This line lets bnd use your configuration interface to generate an XML
    configuration file. This provides a lot of information about your application's
    configuration options. Enough, in fact, to generate a 
    [System Settings](/discover/portal/-/knowledge_base/7-1/system-settings) user 
    interface automatically.

<!--[Figure 1: Navigate to the Control Panel and then click on *Configuration* &rarr; *System Settings*. Then click on *Platform* &rarr; *Third Party*, find the *Example configuration* link, and click on it.](../../images/example-configuration-system-settings.png)-->

## Categorizing the Configuration [](id=categorizing-the-configuration)

Because it's easy to make any application or service configurable, there are
already lots of configuration options in @product@ by default. If you've
deployed custom applications and services, there are even more. To make it
easier for portal administrators to find the right configuration options,
specify a category for the configuration in the auto-generated System Settings
UI.

<!-- Here's how the System Settings UI looks:

IMAGES NEED TO BE UPDATED WHEN UI STABILIZES ![Figure 3: Navigate to the Control Panel, click on *Configuration* and then
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
Platform &rarr; Third Party. You can place your configurations in an existing
category or create your own.

<!-- Add Figure of Platform Third Party section when UI stabilizes -->.

### Specifying a Configuration Category [](id=specifying-a-configuration-category)

To specify a different category, use the `@ExtendedObjectClassDefinition`
annotation as in the following example: 

    @ExtendedObjectClassDefinition(category = "blogs")
    @Meta.OCD(
        id = "com.liferay.blogs.configuration.BlogsConfiguration",
        localization = "content/Language", name = "blogs-configuration-name"
    )

The fully qualified class name of the `@ExtendedObjectClassDefinition` class is
`com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition`.

Note: The infrastructure used by System Settings assumes the `configurationPid`
is the same as the fully qualified class name of the interface. If they don't
match, it can't provide any information through
`ExtendedObjectClassConfiguration`.

The `@ExtendedObjectClassDefinition` annotation is distributed through the
`com.liferay.portal.configuration.metatype` module, which you can 
[configure as a dependency](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies). 

### Creating new Sections and Categories [](id=creating-new-sections-and-categories)

Configurations should be in the most intuitive location (section and category)
so administrators find them. If your configurations don't fit into the existing
categories or category sections, create your own by implementing the,
`ConfigurationCategory` interface.

Here's the code that creates the *Content Management* section and the *Assets*
category:

    @Component
    public class AssetsConfigurationCategory implements ConfigurationCategory {

        @Override
        public String getCategorySection() {
            return _CATEGORY_SET_KEY;
        }

        @Override
        public String getCategoryKey() {
            return _CATEGORY_KEY;
        }

        private static final String _CATEGORY_SET_KEY = "content-management";

        private static final String _CATEGORY_KEY = "assets";

    }

The `getCategorySection` method returns the String with the new section's key.
Similarly, `getCategoryKey` returns the key for the new category. Provide
localized values for these keys in your module's
`src/main/resources/content/Language.properties` file.

Next you'll specify the scope of your application's configuration.
