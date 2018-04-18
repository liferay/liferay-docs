# Reading Configuration Values from a Configuration Provider [](id=reading-configuration-values-from-a-configuration-provider)

When an application is deployed, it's common to need different configurations
[depending on the scope](/develop/tutorials/-/knowledge_base/7-1/making-applications-configurable/scoping-configurations).
That means having different
configurations for a given application per virtual instance (a.k.a. Company),
site (a.k.a. Group), or portlet instance. Achieve this with little effort using
the Configuration Provider API that is based on the standard OSGi Configuration
Admin API.

## Using the Configuration Provider [](id=using-the-configuration-provider)

When using the Configuration Provider, instead of receiving the configuration
directly, the class that wants to access it must 

1.  Receive a `ConfigurationProvider` to obtain the configuration

2.  Be registered with a `ConfigurationBeanDeclaration`.

The tutorial on 
[scoping configurations](/develop/tutorials/-/knowledge_base/7-1/making-applications-configurable/scoping-configurations) 
demonstrates how to register the configuration with a `ConfigurationBeanDeclaration`.

After registering with a `ConfigurationBeanDeclaration`, you're ready to use a
`ConfigurationProvider` to retrieve the scoped configuration. Here's how you
obtain a reference to it:

1.  Here's the approach for components:

        @Reference
        protected void setConfigurationProvider(ConfigurationProvider configurationProvider) {
            _configurationProvider = configurationProvider;
        }

2.  Here's the approach for Service Builder services:

        @ServiceReference(type = ConfigurationProvider.class)
        protected ConfigurationProvider configurationProvider;

3.  For Spring beans, it is possible to use the same mechanism as for Service
    Builder services (`@ServiceReference`). Check the documentation on 
    [how to integrate Spring beans with OSGi services](/develop/tutorials/-/knowledge_base/7-1/finding-and-invoking-liferay-services#referencing-osgi-services)
    for more details.

4.  For anything else, call the same methods from the utility class,
    `ConfigurationProviderUtil`. Be sure you call the utility methods in
    contexts where the portal is guaranteed to be initialized prior to the
    method call. This class is useful in the 
    [scripting console](/discover/portal/-/knowledge_base/7-0/running-scripts-from-the-script-console),
    for example. Here's an example method that uses the utility class. It comes
    from the export-import service, which is only called during the import and
    export of content from a running portal:

	protected boolean isValidateLayoutReferences() throws PortalException {
		long companyId = CompanyThreadLocal.getCompanyId();

		ExportImportServiceConfiguration exportImportServiceConfiguration =
			ConfigurationProviderUtil.getCompanyConfiguration(
				ExportImportServiceConfiguration.class, companyId);

		return exportImportServiceConfiguration.validateLayoutReferences();
	}

To retrieve the configuration, use one of the following methods of the provider:

`getCompanyConfiguration()`
: Used when you want to support different configurations per virtual instance.
In this case, the configuration is usually entered by an admin through Control
Panel &rarr; Configuration &rarr; Instance Settings. Since this UI is not
automatically generated (yet) you must extend the UI with your own form.

`getGroupConfiguration()`
: Used when you want to support different configurations per site (or, if
desired, per page scope). Usually this configuration is specified by an admin
through the Configuration menu option in an app accessing through the site
administration menu. That UI is developed as a portlet configuration view.

`getPortletInstanceConfiguration()`
: Used to obtain the configuration for a specific portlet instance. Most often
you should not be using this directly and use the convenience method in
`PortletDisplay` instead as shown below.

`getSystemConfiguration`
: Used to obtain the configuration for the system scope. These settings are
specified by an admin via the System Settings application or with an OSGi
configuration file.

Here are a couple real world examples from Liferay's source code:

    JournalGroupServiceConfiguration configuration =
        configurationProvider.getGroupConfiguration(
            JournalGroupServiceConfiguration.class, groupId);

    MentionsGroupServiceConfiguration configuration =
      _configurationProvider.getCompanyConfiguration(
         MentionsGroupServiceConfiguration.class, entry.getCompanyId());

Next, you'll learn a nifty way to to access a portlet instance configuration
from a JSP.

## Accessing the Portlet Instance Configuration Through the PortletDisplay [](id=accessing-the-portlet-instance-configuration-through-the-portletdisplay)

Often you must access portlet instance settings from a JSP or from a Java class
that isn't an OSGi component. To read the settings in these cases, a
method was added to `PortletDisplay`, which is available as a request object.
Here is an example of how to use it:

    RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
        portletDisplay.getPortletInstanceConfiguration(
            RSSPortletInstanceConfiguration.class);

As you can see, it knows how to find the values and returns a typed bean
containing them just by passing the configuration class.
