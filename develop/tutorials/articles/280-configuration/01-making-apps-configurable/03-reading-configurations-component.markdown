# Reading Configuration Values from a Component [](id=reading-configuration-values-from-a-component)

Once you have the 
[application configured](/develop/tutorials/-/knowledge_base/7-1/making-applications-configurable)
so that administrators can configure it in System Settings, you might be
wondering how to read the configuration from your application's Java code.

The answer is that there are several ways. Which one you use depends on the
context from which the configuration must be read:

1. From any Component class

2. From an MVC portlet's JSP

3. From an MVC portlet's Portlet class

4. From a non-Component class

This tutorial shows the first usage, reading the configuration from a Component
class.

## Reading Configurations from a Component Class [](id=reading-configurations-from-a-component-class)

1.  First set the `configurationPid` Component property as the fully qualified
    class name of the configuration class:

        @Component(configurationPid = "com.liferay.dynamic.data.mapping.form.web.configuration.DDMFormWebConfiguration")

2.  Then provide an `activate` method, annotated with `@Activate` to ensure the
    method is invoked as soon as the Component is started, and `@Modified` so
    it's invoked whenever the configuration is modified.

        @Activate
        @Modified
        protected void activate(Map<String, Object> properties) {
            _formWebConfiguration = ConfigurableUtil.createConfigurable(
                DDMFormWebConfiguration.class, properties);
        }

        private volatile DDMFormWebConfiguration _formWebConfiguration;

    The `activate()` method calls the method
    `ConfigurableUtil.createConfigurable()` to convert a map of the
    configuration's properties to a typed class, which is easier to handle. The
    configuration is stored in a `volatile` field. Don't forget to make it
    `volatile` to prevent thread safety problems.

3.  Once the activate method is set up, retrieve particular properties from the
    configuration wherever they're needed:

        public void orderCar(String model) {
            order("car", model, _configuration.favoriteColor);
        }

    This is dummy code: don't try to find it in the Liferay source code. The
    String configuration value of `favoriteColor` is passed to the `order`
    method call, presumably so that whatever model car is ordered will be
    ordered in the configured favorite color.

+$$$

**Note:** The bnd library also provides a class called
`aQute.bnd.annotation.metatype.Configurable` with a `createConfigurable()`
method. You can use that instead of Liferay's
`com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil` without
any problems. Liferay's developers created the `ConfigurableUtil` class to
improve the performance of bnd's implementation, and it's used in internal code.
Feel free to use whichever method you prefer. 

$$$

With very few lines of code, you have a configurable application that
dynamically changes its configuration, has an auto-generated UI, and uses a
simple API to access the configuration.
