## Creating A Configuration Interface [](id=creating-a-configuration-interface)

First, you'll learn how to create a configuration at the system scope.

1.  Create a Java interface to represent the configuration and its default
    values. Using a Java interface allows for an advanced type system for each
    configuration option. Here is the configuration interface for the Liferay
    Forms application:

        @Meta.OCD(
            id = "com.liferay.dynamic.data.mapping.form.web.configuration.DDMFormWebConfiguration",
            localization = "content/Language", name = "ddm-form-web-configuration-name"
        )
        public interface DDMFormWebConfiguration {

            @Meta.AD(
                deflt = "1", description = "autosave-interval-description",
                name = "autosave-interval-name", required = false
            )
            public int autosaveInterval();

            @Meta.AD(
                deflt = "descriptive", name = "default-display-view",
                optionLabels = {"Descriptive", "List"},
                optionValues = {"descriptive", "list"}, required = false
            )
            public String defaultDisplayView();


        }

    It defines two configuration options, the autosave interval (with a default
    of one minute) and the default display view, which can be descriptive or
    list, but defaults to descriptive. Here's what the two Java annotations in
    the above snippet do:

    1.  **Meta.OCD:** Registers this class as a configuration with a specific
        id. The ID must be the fully qualified configuration class name.

    2.  **Meta.AD:** Specifies [optional
        metadata](http://bnd.bndtools.org/chapters/210-metatype.html) about the
        field, such as whether it's a required field or if it has a default
        value.  Note that if you set a field as required and don't specify a
        default value, the system administrator must specify a value in order
        for your application to work properly. Use the `deflt` property to
        specify a default value.

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
    configuration file. This provides the information about your application's
    configuration options needed to generate a 
    [System Settings](/discover/portal/-/knowledge_base/7-1/system-settings) user 
    interface automatically.

<!--[Figure 1: Navigate to the Control Panel and then click on *Configuration* &rarr; *System Settings*. Then click on *Platform* &rarr; *Third Party*, find the *Example configuration* link, and click on it.](../../images/example-configuration-system-settings.png)-->

Just by registering a configuration interface, you get a fully capable UI form
auto-generated in the System Settings application. By default, configurations
are placed in Platform &rarr; Third Party. Make it easier to find for your
application's users by categorizing the configuration somewhere logical. The
next tutorial show you how to do that.
