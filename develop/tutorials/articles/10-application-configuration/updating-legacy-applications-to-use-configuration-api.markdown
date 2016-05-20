# Updating Legacy Applications to Use the Configuration API [](id=updating-legacy-applications-to-use-the-configuration-api)

This tutorial describes how to take an existing (6.2 or prior) application or
service and convert it to use the new Configuration API instead of previous mechanisms.

For more information on the Configuration API and the recommended ways to
develop configurable applications for Liferay 7, please see the
[Making your applications configurable](/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable)
tutorial.

## Transitioning from Portlet Preferences to the Configuration API [](id=transitioning-from-portlet-preferences-to-the-configuration-api)

If your existing (6.2 or prior) application uses portlet preferences, follow
these steps to update your application to use Liferay' Configuration API:

1. Identify the preferences that are going to be replaced. In order to do so,
   first check the `configuration.jsp` file of the module. Here, you will find
   DOM elements with the name `preferences--XXX--`, where `XXX` is the name of
   the preference.

2. Change the name of the fields to `configuration--XXX--`. Technically, either
   name works but it's better to make this name change to avoid confusing the
   two.

3. Now that you have these preferences, create a Portlet Instance Configuration
   interface, which holds the default values for the configuration fields. By
   Liferay's convention, the suggested name for this interface is
   `[Portlet Name]PortletInstanceConfiguration`.

    For example,

        @Meta.OCD(
            id = "[package].[PortletName]PortletInstanceConfiguration"
        )
        public interface [PortletName]PortletInstanceConfiguration {
            @Meta.AD(deflt = "", required = false)
            public String displayStyle();

            @Meta.AD(deflt = "0", required = false)
            public long displayStyleGroupId(long defaultDisplayStyleGroupId);
        }

4. Create one class that implements the Configuration Bean Declaration
   interface to let the Configuration framework know about the Configuration
   class.

        @Component
        public class [PortletName]PortletInstanceConfigurationBeanDeclaration
                implements ConfigurationBeanDeclaration {

            @Override
            public class getConfigurationBeanClass() {
                return [PortletName]PortletInstanceConfiguration.class;
            }

        }

5. Create a class that implements the `ConfigurationPidMapping` interface that
   maps the `portletId` to the class. Note: This is only necessary in this case
   to avoid an upgrade process since the configuration has so far been
   associated with the `portletId`, while the Configuration classes each have a
   `pid` equal to their fully qualified class name.

        @Component
        public class [PortletName]PortletInstanceConfigurationPidMapping
                implements ConfigurationPidMapping {

            @Override
            public Class<?> getConfigurationBeanClass() {
                return [PortletName]PortletInstanceConfiguration.class;
            }

            @Override
            public String getConfigurationPid() {
                return [PortletName]PortletKeys.[Key]
            }

        }

6. The next step involves obtaining the settings and replacing the actual
   preferences in the JSP files. In the `init.jsp` file of the module, you can
   obtain the configuration for the module from `portletDisplay`:

        [PortletName]PortletInstanceConfiguration portletInstanceConfiguration = 
        portletDisplay.getPortletInstanceConfiguration([PortletName]PortletInstanceConfiguration.class);

    Replace the preferences for the settings in the JSP files.

        String displayStyle = portletPreferences.getValue("displayStyle", [DefaultValue]);

    will change to

        String displayStyle = [PortletName]PortletInstanceConfiguration.displayStyle();

## Final steps [](id=final-steps)

1. Check Liferay's
   [`BREAKING_CHANGES.markdown`](https://github.com/liferay/liferay-portal/blob/7.0.x/readme/7.0/BREAKING_CHANGES.markdown)
   document for a list of changes to Liferay's API. If you're using a Liferay
   application or service whose API has changed, take note and update your
   application accordingly. These API changes include migrations from using
   portlet preferences to using the Configuration API, as we discussed in this
   tutorial.

the list.

2. You can also check Liferay's
   [`VerifyProperties`](https://github.com/liferay/liferay-portal/blob/7.0.x/portal-impl/src/com/liferay/portal/verify/VerifyProperties.java)
   class. This class includes lists of portal properties that have been renamed
   or deprecated. In cases where configurations have been updated to use the
   new Configuration API, the configuration class name and relevant method
   names are also specified.
