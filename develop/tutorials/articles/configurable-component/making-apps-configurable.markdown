# Making Your Applications Configurable

This tutorial shows how to make applications configurable. It starts with basic
configuration and then covers some advanced use cases.

Note that the methods described here are not mandatory. You can make your
applications configurable using any other mechanism that you know.  We have
found, however, that the method described below provides the largest benefit
with the smallest amount of effort.

## Fundamentals 

While you don't need to know much to make your applications configurable,
understanding a few concepts helps you achieve a higher degree of
configurability with little effort.

The first important concept is that the method described here uses *typed*
configuration. That means that the application configuration isn't just a list
of key-value pairs. The values can be types, like `Integer`, a list of
`Strings`, a URL, etc. This can prevent many programmatic errors and is easier
to use. Related to this, the configuration options should be programmatically
explicit, so that developers can use autocomplete in modern IDEs to find out all
the configuration options of a given application or one of its components.

A second concept that you should know about is modularity. With Liferay 7,
applications are modular and built as a collection of lightweight components. A
component is just a class that has the `@Component` annotation, often along with a
set of properties to provide metadata. The configuration mechanisms described
here leverage the concept of components.

A third important concept is the ability to have different configurations for
the same application at different scopes. Not all applications need this, but if
you do, the APIs described below handle most of the burden for you. It’s still
good, however, for you to understand the term *configuration scope*. Here are the
most common configuration scopes that Liferay applications can have:

1.  **System:** configuration that is unique for the complete installation of the
    application. 
2.  **Portal Instance:** configuration that can vary per portal
    instance.
3.  **Site:** configuration that can vary per Liferay site.
4.  **Portlet Instance:** applicable for applications that are placed on a
    page (i.e., portlets). Each placement (instance) of the application on the
    page can have a different configuration.

That’s it for now. You are ready to get started with some code. If you already
had a portlet or service that was configurable using the traditional mechanisms
of Liferay 6.2 and before, you might also want to read
[How to change your portlets and services to use the new Configuration API](https://docs.google.com/a/liferay.com/document/d/1o6U0fqsUv9WSJTLQmSytMMcW2exUwWclZPQ5A3vUKwc/edit#)[](https://docs.google.com/a/liferay.com/document/d/1o6U0fqsUv9WSJTLQmSytMMcW2exUwWclZPQ5A3vUKwc/edit#). 

## Making your application configurable 

Now you'll see the minimum amount of code you need to write to make your
application configurable the Liferay 7 way. The System scope is
covered first (read the previous section if you don’t know what that means).

The first step is to create a Java interface to represent the configuration and
its default values. The reason for using a Java interface is that it allows for
an advanced type system for each of the configuration options. Here is an
example of such an interface:

    @Meta.OCD(id = "com.foo.bar.MyAppConfiguration")
    public interface MyAppConfiguration {
        
        @Meta.AD(deflt = es)
        public String defaultLanguage();

        @Meta.AD(
           deflt = "en|es|pt",
           required = true
        )
        public String[] validLanguages();

        @Meta.AD(required = false)
        public int itemsPerPage();

    }

As you can see, you are using two Java annotations to provide some metadata about
the configuration. Here is what they do:

1.  **Meta.OCD:** Registers this class as a configuration with a specific id. You
    can choose any string you want, but make sure it's unique. A common
    pattern is to use the fully qualified class name. 
2.  **Meta.AD:** Specifies the default value of a configuration field as
    well as whether it's required or not. Note that if you set a field as required
    and don’t specify a default value, the system administrator must specify a
    value for your application to work properly.

<!-- 
This brings up lots of questions in my mind: 
What do OCD and AD stand for? 
What are the possible parameters for these annotations? 
What is deflt? 

I could go on. The description here doesn't help me understand what's going on.
Is there some reference for these annotations? If so, we should link to it. 

-Rich --> 

With this information, the system already knows a lot about your application's
configuration options. In fact it knows enough to generate a user interface
automatically. Cool, isn’t it? 

<!-- It's cool, but at this point, it's "magic." --Rich --> 

[Show screenshot here]

At this point you might be wondering, while this is pretty cool, how do
I read the configuration from my application code? It’s actually quite
easy. Here's an example:

    @Component(configurationPid = "com.foo.bar.MyAppConfiguration")
    public class MyAppManager {

      public String getDefaultLanguageLabel(Map labels) {
           return labels.get(_configuration.defaultLanguage());
      }

      @Activate
      @Modified
      protected void activate(Map<String, Object> properties) {
         _configuration = Configurable.createConfigurable(
            MyAppConfiguration.class, properties);
      }

      private volatile MyAppConfiguration _configuration;

    }

Here are the most relevant aspects of this example one by one:

1.  This class is a component, specified with the `@Component` annotation. 
2.  This component uses the configuration with id
    `com.foo.bar.MyAppConfiguration`. As a result, this method is
    invoked when the application starts (due to the `@Activate` annotation) and
    whenever the configuration is modified (due to the `@Modified` annotation). 
3.  The activate method uses the method `Configurable.createConfigurable()` to
    convert a map of properties with the configuration to our typed class, which is
    easier to handle. 
4.  The configuration is stored in a “volatile” field. Don’t forget to make it
    volatile or you’ll run into weird problems. 

That’s it. As you can see with very few lines of code, you have a configurable
application that dynamically changes its configuration, has an auto-generated
UI, and uses a simple API to access the configuration.

## Supporting different configurations per scope 

When an application is deployed to Liferay, it's common to need different
configurations depending on the scope. That means having different
configurations for a given service per portal instance or site. It's also very
common to need different configurations for each portlet instance. Liferay 7
provides an easy way to achieve this with little effort through a new framework
called the Settings API.

In order to use the Settings API you need to

1.  Declare the configuration interface by creating a `ConfigurationBeanDeclaration` class:

        @Component
        public class RSSPortletInstanceConfigurationBeanDeclaration
          implements ConfigurationBeanDeclaration {

          @Override
          public Class getConfigurationBeanClass() {
             return RSSPortletInstanceConfiguration.class;
          }

        }

2.  Obtain the configuration using the following code: 

        RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
          _settingsFactory.getSettings(
             RSSPortletInstanceConfiguration.class,
             new PortletInstanceSettingsLocator(
                themeDisplay.getLayout(), portletDisplay.getId()));

        RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
          _settingsFactory.getPortletInstanceSettings(
             RSSPortletInstanceConfiguration.class,
             themeDisplay.getLayout(), portletDisplay.getId()));

3.  In order to get the `SettingsFactory`, your class must have a method: 

        @Reference
        protected void setSettingsFactory(SettingsFactory settingsFactory) {
          _settingsFactory = settingsFactory;
        }

### Accessing the Portlet Instance Settings through the PortletDisplay 

Often it’s necessary to access a portlet’s settings from its JSPs or from Java
classes that are not OSGi components. To make it easier to read the settings in
these cases, a new method has been added to `PortletDisplay` (available as a
request object). Here is an example of how to use it:

    RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
        portletDisplay.getPortletInstanceConfiguration(

            RSSPortletInstanceConfiguration.class);

As you can see, it knows how to find the values and returns a typed bean
containing them just by passing the configuration class.

### Reusing the same configuration class for different purposes 

The settings service also allows reusing a single class for different
configuration needs. In order to support this, you must create a class that
maps the different scenarios to the class that will be used. Here is an example:

    @Component
    public class RSSPortletInstanceSettingsIdMapping implements SettingsIdMapping {

      @Override
      public Class<?> getConfigurationBeanClass() {
         return RSSPortletInstanceConfiguration.class;
      }

      @Override
      public String getSettingsId() {
         return RSSPortletKeys.RSS;
      }

    }

<!-- One rule is never to end a section with code. This section needs to be tied
up. -Rich --> 

## Summary 

<!-- Please summarize what the reader has learned. -Rich -->

