---
header-id: reading-scoped-configuration-values
---

# Reading Scoped Configuration Values

[TOC levels=1-4]

If your configuration is scoped to anything other than `SYSTEM`, you have two
options for reading configuration values.

-   Use `ConfigurationProvider`. This works for any kind of configuration,
    and is the only way to read configuration values at the `COMPANY` and
    `GROUP` scopes.

-   Use `PortletDisplay`. This is the recommended approach for configurations
    at the `PORTLET_INSTANCE` scope, but only works at that scope.

## Using the Configuration Provider

When using the Configuration Provider, instead of receiving the configuration
directly, the class that wants to access it must 

1.  Receive a `ConfigurationProvider` to obtain the configuration.

2.  Be registered with a `ConfigurationBeanDeclaration`.

The tutorial on 
[scoping configurations](/docs/7-2/frameworks/-/knowledge_base/f/scoping-configurations) 
demonstrates how to register the configuration with a `ConfigurationBeanDeclaration`.

After registering with a `ConfigurationBeanDeclaration`, you're ready to use a
`ConfigurationProvider` to retrieve the scoped configuration. Here's how you
obtain a reference to it:

1.  Here's the approach for components:

    ```java
    @Reference(unbind = "-")
    protected void setConfigurationProvider(ConfigurationProvider configurationProvider) {
        _configurationProvider = configurationProvider;
    }
    ```

2.  Here's the approach for Service Builder services:

    ```java
    @ServiceReference(type = ConfigurationProvider.class)
    protected ConfigurationProvider configurationProvider;
    ```

3.  For Spring beans, it is possible to use the same mechanism as for Service
    Builder services (`@ServiceReference`). 
    
    <!--Uncomment once article is available
    Check the documentation on 
    how to integrate Spring beans with OSGi services
    for more details.
    -->

4.  For anything else, call the same methods from the utility class,
    `ConfigurationProviderUtil`. Be sure you call the utility methods in
    contexts where the portal is guaranteed to be initialized prior to the
    method call. This class is useful in the 
    [scripting console](/docs/7-2/user/-/knowledge_base/u/running-scripts-from-the-script-console),
    for example. Here's an example method that uses the utility class. It comes
    from the export-import service, which is only called during the import and
    export of content from a running portal:

    ```java
  	protected boolean isValidateLayoutReferences() throws PortalException {
  		long companyId = CompanyThreadLocal.getCompanyId();

  		ExportImportServiceConfiguration exportImportServiceConfiguration =
  			ConfigurationProviderUtil.getCompanyConfiguration(
  				ExportImportServiceConfiguration.class, companyId);

  		return exportImportServiceConfiguration.validateLayoutReferences();
  	}
    ```

To retrieve the configuration, use one of the following methods of the provider:

`getCompanyConfiguration()`
: Used when you want to support different configurations per virtual instance.
In this case, the configuration is usually entered by an admin through *Control
Panel* &rarr; *Configuration* &rarr; *Instance Settings*. 

`getGroupConfiguration()`
: Used when you want to support different configurations per site (or, if
desired, per page scope). Usually this configuration is specified by an admin
through the Configuration menu option in an app accessing through the site
administration menu. That UI is developed as a portlet configuration view.

`getPortletInstanceConfiguration()`
: Used to obtain the configuration for a specific portlet instance. Most often
you should not be using this directly. Use the convenience method in
`PortletDisplay` instead as shown below.

`getSystemConfiguration`
: Used to obtain the configuration for the system scope. These settings are
specified by an admin via the System Settings application or with an OSGi
configuration file.

Here are a couple real world examples from Liferay's source code:

```java
JournalGroupServiceConfiguration configuration =
    configurationProvider.getGroupConfiguration(
        JournalGroupServiceConfiguration.class, groupId);

MentionsGroupServiceConfiguration configuration =
  _configurationProvider.getCompanyConfiguration(
     MentionsGroupServiceConfiguration.class, entry.getCompanyId());
```

Next, you'll learn a nifty way to to access a portlet instance configuration
from a JSP.

## Accessing the Portlet Instance Configuration Through the `PortletDisplay`

Often you must access portlet instance settings from a JSP or from a Java class
that isn't an OSGi component. To read the settings in these cases, a
method was added to `PortletDisplay`, which is available as a request object.
Here is an example of how to use it:

```java
RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
    portletDisplay.getPortletInstanceConfiguration(
        RSSPortletInstanceConfiguration.class);
```

As you can see, it knows how to find the values and returns a typed bean
containing them just by passing the configuration class.
