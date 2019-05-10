---
header-id: configurable-applications
---

# Configurable Applications [](id=configurable-applications)

[TOC levels=1-4]

Many applications must be configurable, whether by end users or administrators.
A configuration solution must support use cases ranging from setting a location
for a weather display to more complex cases like settings for a mail or time
sheet application. 

The Portlet standard's portlet preferences API can be used for portlet
configuration, but it's intended for storing user preferences. This limits its
usefulness for enabling administrator configuration; plus it can only be used
with portlets. Instead, application developers tend to create ad hoc
configuration methods. But this isn't necessary. 

@product@'s configuration API is easy to use and is not limited to portlets.
When you define configuration options in a Java interface, Liferay's
configuration framework auto-generates a UI, sparing you the trouble of
developing an interface for your users to select configuration options.

|**Note:** To see a working application configuration, deploy the
|`configuration-action` 
|[Blade sample](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/configuration-action)
|and navigate to System Settings (*Control Panel* &rarr; *Configuration* &rarr;
|*System Settings*). In the Other category, click the *Message display
|configuration* entry.
|
|Add the *Blade Message Portlet* to a page to test your configuration choices.

Complete these three high level tasks to integrate your application with the
configuration framework: 

1.  Provide a way to set configurations in the UI.

2.  Set the scope where the application is configured.

3.  Read configuration values in your business logic.

## Using a Configuration Interface

You can take care of the first two steps by 
[Creating A Configuration Interface](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-configuration-interface).
This Java interface does a number of things:

-   Just by existing, it gives you a UI in *System Settings*, so you don't
    have to write one yourself. Score!

-   It defines the configuration options that will appear in the UI.

-   It defines the type {`int`, `String`, etc.) of values each configuration
    takes.

-   It defines the scope of your configuration. Bonus in @product-ver@: if your
    configuration is scoped to anything other than `SYSTEM`, you get an
    additional UI generated for you in *Instance Settings*. More on scope in
    a minute.

-   It categorizes your configuration screen so that it can be easily found in
    *System Settings* and *Instance Settings*. If you skip this the screen will
    be put in a default location.

A few things you need to know:

**Typed Configuration**
: The method described here uses *typed* configuration. The application
configuration isn't just a list of key-value pairs. Values can have types, like
`Integer`, a list of `Strings`, a URL, etc. You can even use your own types,
although that's beyond the scope of this tutorial. Typed configurations are
easier to use than untyped configurations, and they prevent many programmatic
errors. Configuration options should be programmatically explicit, so developers
can use autocomplete in modern IDEs to find out all configuration options of
a given application or one of its components.

**Configuration Scope** 
: Scope defines where a configuration value applies. Here are the most common
configuration scopes:

-   `SYSTEM`: Configuration values apply throughout the system.

-   `COMPANY`: One set of configuration values is stored for each
    virtual instance, so each instance can be configured individually.

-   `GROUP`: Each group can be configured individually.

-   `PORTLET_INSTANCE`: this refers to apps that can be placed on a page as
    a widget. Each widget can be configured individually.

**Configuration UIs** : When you create a configuration interface of any sort,
a UI is generated for you in *System Settings*. If your configuration is scoped
to `COMPANY`, `GROUP`, or `PORTLET_INSTANCE`, an additional UI is generated in
*Instance Settings*.

|Note: An Instance Settings UI is not currently generated for factory
|configurations. You can track the progress of this issue
|[here](https://issues.liferay.com/browse/LPS-94490).

**Default Configurations**
: Default values for any scoped configuration can be set at any wider scope.
For example, if your configuration is scoped to the `GROUP`, you can set
a system-wide default in *System Settings, an instance-wide default in
*Instance Settings*, or both. Any configuration at a narrower scope will always
override a configuration at a wider scope.

Read more about configuration scope
[here](/docs/7-2/user/-/knowledge_base/u/system-settings#configuration-scope).

When you complete your configuration interface, you're done with steps 1 and
2 above.

## Reading Configuration Values

The final step is to make your app read the configuration values that users
enter. There are a number of ways to do that:

If your configuration is scoped to `COMPANY` or `GROUP` you must use
[`ConfigurationProvider`](/docs/7-2/frameworks/-/knowledge_base/f/reading-configuration-values-from-a-configuration-provider)
This allows your app to read different configuration values from each site,
virtual instance, or whatever the configuration is scoped to.

If your configuration is scoped to `PORTLET_INTSANCE`, you can still use
`ConfigurationProvider`, but using `PortletDisplay` is simpler and more
convenient. See 
[`PortletDisplay`](/docs/7-2/frameworks/-/knowledge_base/f/reading-configuration-values-from-a-configuration-provider#accessing-the-portlet-instance-configuration-through-the-portletdisplay). 

If you only want your app to be configurable at the `SYSTEM` scope, you have
a few options. `ConfigurationProvider` will work fine, but there are
alternatives that---since they don't need to query multiple sources---can yield
modest performance benefits. Which one you use depends on what kind of class
you're using to read configuration values. Here are your options:

-   Read with an [MVC portlet's JSP](/docs/7-2/frameworks/-/knowledge_base/f/reading-configuration-values-from-a-mvc-portlet#accessing-the-configuration-from-a-jsp)

-   With an [MVC Portlet's Portlet Class](/docs/7-2/frameworks/-/knowledge_base/7-2/reading-configuration-values-from-a-mvc-portlet#accessing-configuration-object-in-the-portlet-class)

-   With any other [Component Class](/docs/7-2/frameworks/-/knowledge_base/f/reading-configuration-values-from-a-component)


## Further Customization

At this point you may be asking, "But what if I don't *like* the auto-generated
UI?" Relax. There are a number of ways you can customize it, or even suppress it
entirely so you can put your own UI in its place.

-   Implement the `ConfigurationFormRenderer` 
    [interface](/docs/7-2/frameworks/-/knowledge_base/f/configuration-form-renderer)
    to customize the auto-generated UI in system settings.

-   If you need more flexibility---perhaps your app needs multiple configuration
    screens, or maybe you've already written a configuration UI and just want to
    insert it without bothering to write a configuration interface---implement
    the `ConfigurationScreen` interface to implement your own.

-   If you're using a configuration interface but you don't want a UI to be
    generated---maybe you're using a `ConfigurationScreen` implementation
    instead, or maybe you just want configuration to be handled programatically
    or by [.config file](/docs/7-2/user/-/knowledge_base/u/understanding-system-configuration-files)
    ---you can [just leave it out](/docs/frameworks/-/knowledge_base/7-2/customizing-the-system-settings-user-interface#excluding-a-configuration-ui-from-system-settings).

Enough conceptual stuff. You're ready to get started with some code. If you
already have an app that was configurable under an earlier version of
@product@, see 
[Upgrading a Legacy App](/docs/7-2/frameworks/-/knowledge_base/f/upgrading-a-legacy-app).
