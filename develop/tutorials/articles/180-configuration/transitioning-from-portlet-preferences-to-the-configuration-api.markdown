# Transitioning from Portlet Preferences to the Configuration API [](id=transitioning-from-portlet-preferences-to-the-configuration-api)

This tutorial describes how to take an existing portlet developed for Liferay
Portal 6.2 or prior, which uses portlet preferences to allow administrators to
configure the portlet, and convert it to use the new Configuration API.

For more information on the Configuration API and the recommended ways to
develop configurable applications for @product-ver@, please see the
[Making your applications configurable](/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable)
tutorial.

Before you start, it's important to understand the benefits of making this
change. That will allow you to decide whether to go ahead with the change or
not, since previous configuration mechanisms of Liferay Portal 6.2 and prior still work
in @product-ver@.

The main benefits are:

* Ability to modify the default portlet preferences through the new System
  Settings UI. Previously the default configuration of a portlet could only be
  set by modifying the portlet.xml file, which was not easy to extend.

* In the future Liferay will support modifying the default values for any
  portlet using this API for each company or site. If you use the new API you
  won't need to make any change to benefit from this.

* You can now use the PortletPreferences API to allow users to have their own
  personal preferences for the portlet, which was the original intention of this
  API.

* Have full programmatic control of the scope of the configuration (Portlet
  Instance or Group), instead of leaving it to the liferay-portlet.xml file.

* It becomes easier to read the configuration of the portlet from any class or
  JSP just by knowing its name. The configuration is also presented as a typed
  object which reduces the possibility of error reading a property name or type.

One final thing to note is that using the new Configuration API preserves all
existing configurations of the portlet, since it uses the same persistence
storage (the PortletPreferences table). That means that you can deploy a new
version of the portlet to a running system and the existing portlets that may
have been added to its pages and configured will not need to be reconfigured. 

If your existing (Liferay Portal 6.2 or prior) application uses portlet preferences and you
have decided to convert it to use the new Configuration API, follow these steps
to update your application:

1. Identify the existing preferences of the portlet configuration. 

    For example, you could check the `configuration.jsp` (where you will find
    DOM elements with the name `preferences--XXX--`, where `XXX` is the name of
    the preference).

2. Determine the scope of the portlet configuration. The traditional way of
   specifying it is through `liferay-portlet.xml`. Look at the elements
   `preferences-company-wide`, `preferences-unique-per-layout` and
   `preferences-owned-by-group` to determine the right scope. The following table
   maps out the scopes:

    | liferay-portlet.xml                                                     | Scope            |
    |-------------------------------------------------------------------------|------------------|
    | preferences-company-wide=true                                           | Company          |
    | preferences-owned-by-group=true AND preferences-unique-per-layout=false | Group            |
    | preferences-owned-by-group=true AND preferences-unique-per-layout=true  | Portlet Instance |

    Related to this, we make the following recommendations for the scope of the
    configuration of a portlet:

    * For any portlet that can be added to a page it should be Portlet Instance. 

    * For any portlet that is accessible through the product menu and is used to
    administer the site it should be Group.
 
3. Create a Java interface that will represent the configuration, with one
   method per existing preference.
 
    By Liferay's convention, the suggested names for these interfaces are
    `[Portlet Name]PortletInstanceConfiguration` for the portlet scoped ones or
    `[Portlet Name]GroupServiceConfiguration` for the group scoped ones. However
    you can choose different conventions. 
 
    This interface will use two annotations (`@Meta.OCD` and
    `@ExtendedObjectClassDefinition`) to declare that it represents a configuration
    and to specify the desired scope. The `id` specified in the `@Meta.OCD` annotation
    must be the fully qualified class name of the interface. 

    For example,

        @ExtendedObjectClassDefinition(
            category = "[category]",
            scope = ExtendedObjectClassDefinition.Scope.GROUP
        )
        @Meta.OCD(
            id = "[package].[PortletName]GroupServiceConfiguration",
        )
        public interface [PortletName]GroupServiceConfiguration {
            @Meta.AD(deflt = "", required = false)
            public String displayStyle();
	
            @Meta.AD(deflt = "0", required = false)
            public long displayStyleGroupId(long defaultDisplayStyleGroupId);
        }

4. In the code above replace [category] with a category name of your choice. Out
   of the box Liferay uses: wem, forms, collaboration and foundation. If your
   portlet fits in any of those go ahead and use them. If you pick any other
   name it will be added, but you will need to provide a translation as well.

5. If you want to specify the name of the configuration as it will appear in
   System Settings (and optionally translate it to several languages) add the
   attributes name and localization (to specify the location of a resource
   bundle file) to the @Meta.OCD annotation:
 
		@Meta.OCD(
     		localization = "content/Language",
 			name = "[PortletName].group.service.configuration.name"
			id = "[package].[PortletName]GroupServiceConfiguration",
		)

5. Create one class that implements the Configuration Bean Declaration
   interface to let the Configuration framework know about the Configuration
   class.

        @Component
        public class [PortletName]GroupServiceConfigurationBeanDeclaration
                implements ConfigurationBeanDeclaration {

            @Override
            public class getConfigurationBeanClass() {
                return [[PortletName]GroupServiceConfiguration.class;
            }

        }

6. Change the configuration JSP to retrieve the configuration using the
   Configuration API. If the scope is "Portlet Instance" the configuration can
   be retrieved from `portletDisplay`:

        [PortletName]PortletInstanceConfiguration portletInstanceConfiguration = 
			portletDisplay.getPortletInstanceConfiguration(
				[PortletName]PortletInstanceConfiguration.class);
	
    Once the configuration object is obtained, the individual preferences can now
    be changed from this:

        String displayStyle = portletPreferences.getValue("displayStyle", defaultValue);

    ... to this ...

        String displayStyle = v.displayStyle();

7. Finally it is usually necessary to read the configuration from Java classes
   or other JSPs. In cases where the `portletDisplay` is not available, or when
   the scope is "Group" or "Company", the `PortletProvider` class offers methods
   to obtain the configuration. The best way to access the `PortletProvider`
   depends on who is making the invocation: 
 
    Within an OSGi Component a reference to the `ConfigurationProvider` can be
    obtained and used as follows:
        	 
            @Component(service = Foo)
        		public class Foo {
        			
        			protected void methodWhichNeedsConfiguration() {
                		_configurationProvider.getGroupConfiguration(
                            [PortletName]GroupServiceConfiguration.class, groupId);
        			}
            		
                    @Reference
                    private ConfigurationProvider _configurationProvider;
        		}
        		
    Within a service created with Service Builder the code is very similar:
        
        		public class FooServiceImpl {
        			
        			protected void methodWhichNeedsConfiguration() {
                		_configurationProvider.getGroupConfiguration(
                            [PortletName]GroupServiceConfiguration.class, groupId);
        			}
            		
                    @Reference
                    private ConfigurationProvider _configurationProvider;
        		}

    For all other cases it is preferred to get the configuration injected or
    passed as a parameter. As a last resort it is possible to use
    `ConfigurationProviderUtil` to obtain the configuration, although this method
    might have issues in highly dynamic environments:
            
            	[PortletName]GroupServiceConfiguration groupConfiguration 
            			ConfigurationProviderUtil.getGroupConfiguration(
            				[PortletName]GroupServiceConfiguration.class, groupId);
