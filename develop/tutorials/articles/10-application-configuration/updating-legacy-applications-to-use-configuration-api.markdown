# Updating Legacy Applications to Use the Configuration API [](id=updating-legacy-applications-to-use-the-configuration-api)

This tutorial describes how to take an existing (6.2 or prior) application or
service and convert it to use the new Configuration API instead of previous mechanisms.

For more information on the Configuration API and the recommended ways to
develop configurable applications for Liferay 7, please see the
[Making your applications configurable](/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable)
tutorial.

## Transitioning from Portlet Preferences to the Configuration API [](id=transitioning-from-portlet-preferences-to-the-configuration-api)

Before you start it's important to understand the benefits of making this change. That will allow you to decide whether you want to do it or not, since the traditional way of Liferay 6.2 and prior still works in 7.0.

The main benefits are:

* Ability to modify the default portlet preferences through the new System Settings UI. Previously the default configuration of a portlet could only be set by modifying the portlet.xml file, which was not easy to extend.
* In the future Liferay will support modifying the default values for any portlet using this API for each company or site. If you use the new API you won't need to make any change to benefit from this.
* You can now use the PortletPreferences API to allow users to have their own personal preferences for the portlet, which was the original intention of this API.
* Have full programmatic control of the scope of the configuration (Portlet Instance or Group), instead of leaving it to the portlet.xml file. Tip: for any portlet that can be added to a page it should be Portlet Instance. For any portlet that is accessible through the product menu and is used to administer the site it should be Group.
* It becomes easier to read the configuration of the portlet from any class or JSP just by knowing its name. The configuration is also presented as a typed object which reduces the possibility of error reading a property name or type.

If your existing (6.2 or prior) application uses portlet preferences, follow
these steps to update your application to use Liferay' Configuration API:

1. Identify the preferences that are going to be replaced, and select the right scope for them, depending in its use. 

	For example, you could check the `configuration.jsp` (where you will find DOM elements with the name `preferences--XXX--`, where `XXX` is the name of the preference) or the `portlet.properties` file of the module.

2. Now that you have these preferences, create Configuration interfaces as needed, which holds the default values for the configuration fields.
   
   By Liferay's convention, some of the suggested names for this interfaces are `[Portlet Name]PortletInstanceConfiguration` for the portlet scoped ones or `[Portlet Name]GroupServiceConfiguration` for the group scoped ones.

    For example,

        
    	@ExtendedObjectClassDefinition(
			category = "[category]",
			scope = ExtendedObjectClassDefinition.Scope.GROUP
		)
		@Meta.OCD(
			id = "[package].[PortletName]GroupServiceConfiguration",
			localization = "content/Language",
			name = "[PortletName].group.service.configuration.name"
		)
    	public interface [PortletName]GroupServiceConfiguration {
	  		@Meta.AD(deflt = "", required = false)
	    	public String displayStyle();
	
	    	@Meta.AD(deflt = "0", required = false)
	    	public long displayStyleGroupId(long defaultDisplayStyleGroupId);
    	}

3. In the code above replace [category] with a category name of your choice. Out of the box Liferay uses: wem, forms, collaboration and foundation. If your portlet fits in any of those go ahead and use them. If you pick any other name it will be added, but you will need to provide a translation as well.
4. Create one class that implements the Configuration Bean Declaration
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

5. The next step involves obtaining the settings and replacing the actual
   preferences in the JSP files. If you are going to use the `[PortletName]PortletInstanceConfiguration` (scoped at instance level) you can obtain the configuration for the module from `portletDisplay`:

        [PortletName]PortletInstanceConfiguration portletInstanceConfiguration = 
			portletDisplay.getPortletInstanceConfiguration(
				[PortletName]PortletInstanceConfiguration.class);
        
    If you need the `[PortletName]GroupServiceConfiguration` (scoped at group level), in a static context (a `.jsp`or a `*Util.java` you could use:
    
    	[PortletName]GroupServiceConfiguration 
    		[PortletName]GroupServiceConfiguration = 
    			ConfigurationProviderUtil.getConfiguration(
    				[PortletName]GroupServiceConfiguration.class, 
    				new GroupServiceSettingsLocator(themeDisplay.getScopeGroupId(),
    				 [PortletName]Constants.SERVICE_NAME)
    			 );
	
	 Or your could inject it in your components!!!
	 
		@Component(service = Foo)
		public class Foo {
			
			@Activate
			@Modified
			protected void activate(Map<String, Object> properties) {
        		_[PortletName]GroupServiceConfiguration = 
        			Configurable.createConfigurable(
        				[PortletName]GroupServiceConfiguration.class, properties);
			}
    		
		    private [PortletName]GroupServiceConfiguration 
		   		_[PortletName]GroupServiceConfiguration;

		}

    Replace the preferences for the settings, so ...

        String displayStyle = 
        	portletPreferences.getValue("displayStyle", [DefaultValue]);

    ... will change to ...

        String displayStyle = 
        	[PortletName]GroupServiceConfiguration.displayStyle();

## Final steps [](id=final-steps)

1. Check Liferay's
   [`BREAKING_CHANGES.markdown`](https://github.com/liferay/liferay-portal/blob/7.0.x/readme/7.0/BREAKING_CHANGES.markdown)
   document for a list of changes to Liferay's API. If you're using a Liferay
   application or service whose API has changed, take note and update your
   application accordingly. These API changes include migrations from using
   portlet preferences to using the Configuration API, as we discussed in this
   tutorial.

2. You can also check Liferay's
   [`VerifyProperties`](https://github.com/liferay/liferay-portal/blob/7.0.x/portal-impl/src/com/liferay/portal/verify/VerifyProperties.java)
   class. This class includes lists of portal properties that have been renamed
   or deprecated. In cases where configurations have been updated to use the
   new Configuration API, the configuration class name and relevant method
   names are also specified.
