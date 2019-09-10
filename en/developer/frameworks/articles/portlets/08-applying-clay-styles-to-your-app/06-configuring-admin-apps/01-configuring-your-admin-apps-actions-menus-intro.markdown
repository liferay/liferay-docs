---
header-id: configuring-your-admin-apps-actions-menu
---

# Configuring Your Admin App's Actions Menu

[TOC levels=1-4]

Rather then have a series of buttons or menus with actions in the different 
views of the app, you can move all of these actions to the upper right menu of 
the administrative portlet, leaving the primary action (often an 
["Add" operation](/docs/7-2/frameworks/-/knowledge_base/f/applying-the-add-button-pattern)) 
visible in the add menu. For example, the web content application has the 
actions menu shown below:

![Figure 1: The upper right ellipsis menu contains most of the actions for the app.](../../../../images/actions-menu.png)

Follow these steps to configure the actions menu in your admin app:

1.  Create a `*ConfigurationIcon` Component class for the action that extends 
    the [`BasePortletConfigurationIcon` class](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/configuration/icon/BasePortletConfigurationIcon.html) 
    and implements the `PortletConfigurationIcon` service:
    
    ```java
    @Component(
    	immediate = true,
    	service = PortletConfigurationIcon.class
    )
    public class MyConfigurationIcon extends BasePortletConfigurationIcon {
      ...
    }
    ```

2.  Override the `getMessage()` method to specify the action's label:

    ```java
    @Override
    public String getMessage(PortletRequest portletRequest) {
      ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
        WebKeys.THEME_DISPLAY);

      ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
        themeDisplay.getLocale(), ExportAllConfigurationIcon.class);

      return LanguageUtil.get(resourceBundle, "export-all-settings");
    }
    ```

3.  Override the `get()` method to specify whether the action is invoked with 
    the `GET` or `POST` method:
    
    ```java
    @Override
    public String getMethod() {
    	return "GET";
    }
    ```

4.  Override the `getURL()` method to specify the URL (or `onClick` 
    JavaScript method) to invoke when the action is clicked:

    ```java
    @Override
    public String getURL(
      PortletRequest portletRequest, PortletResponse portletResponse) {

      LiferayPortletURL liferayPortletURL =
        (LiferayPortletURL)_portal.getControlPanelPortletURL(
          portletRequest, ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
          PortletRequest.RESOURCE_PHASE);

      liferayPortletURL.setResourceID("export");

      return liferayPortletURL.toString();
    }
    ```

5.  Override the `getWeight()` method to specify the order that the action 
    should appear in the list:
    
    ```java
    @Override
    public double getWeight() {
      return 1;
    }
    ```

6.  Override the `isShow()` method to specify the context in which the action 
    should be displayed:

    ```java
    @Override
    public boolean isShow(PortletRequest portletRequest) {
    	return true;
    }
    ```

7.  Define the view where you want to display the configuration options. By 
    default, if the portlet uses `mvcPath`, the global actions (such as 
    configuration, export/import, maximized, etc.) are displayed for the JSP 
    indicated in the initialization parameter of the portlet 
    `javax.portlet.init-param.view-template=/view.jsp`. The value indicates the 
    JSP where the global actions should be displayed. However, if the portlet 
    uses MVC Command, the views for the global actions must be indicated with 
    the initialization parameter 
    `javax.portlet.init-param.mvc-command-names-default-views=/wiki_admin/view` 
    and the value must contain the `mvcRenderCommandName` where the global 
    actions should be displayed. 

8.  If the portlet can be added to a page and you want to always include the 
    configuration options, add this initialization parameter to the portlet's 
    properties:

    ```java
    javax.portlet.init-param.always-display-default-configuration-icons=true
    ```

    In this example, the action appears in the System Settings portlet. To make 
    it appear in a secondary screen, you can use the `path` property as shown 
    below. The value of the `path` property depends on the MVC framework used to 
    develop the app. For the MVCPortlet framework, provide the path 
    (often a JSP) from the `mvcPath` parameter. For MVCPortlet with MVC 
    Commands, the path should contain the `mvcRenderCommandName` where the 
    actions should be displayed (such as `/document_library/edit_folder` for 
    example):

    ```java
    @Component(
    	immediate = true,
    	property = {
    		"javax.portlet.name=" + ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
    		"path=/view_factory_instances"
    	},
    	service = PortletConfigurationIcon.class
    )
    public class ExportFactoryInstancesIcon extends BasePortletConfigurationIcon {
    
    	@Override
    	public String getMessage(PortletRequest portletRequest) {
    		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
    			WebKeys.THEME_DISPLAY);
    
    		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
    			themeDisplay.getLocale(), ExportFactoryInstancesIcon.class);
    
    		return LanguageUtil.get(resourceBundle, "export-entries");
    	}
    
    	@Override
    	public String getMethod() {
    		return "GET";
    	}
    
    	@Override
    	public String getURL(
    		PortletRequest portletRequest, PortletResponse portletResponse) {
    
    		LiferayPortletURL liferayPortletURL =
    			(LiferayPortletURL)_portal.getControlPanelPortletURL(
    				portletRequest, ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
    				PortletRequest.RESOURCE_PHASE);
    
    		ConfigurationModel factoryConfigurationModel =
    			(ConfigurationModel)portletRequest.getAttribute(
    				ConfigurationAdminWebKeys.FACTORY_CONFIGURATION_MODEL);
    
    		liferayPortletURL.setParameter(
    			"factoryPid", factoryConfigurationModel.getFactoryPid());
    
    		liferayPortletURL.setResourceID("export");
    
    		return liferayPortletURL.toString();
    	}
    
    	@Override
    	public double getWeight() {
    		return 1;
    	}
    
    	@Override
    	public boolean isShow(PortletRequest portletRequest) {
    		ConfigurationModelIterator configurationModelIterator =
    			(ConfigurationModelIterator)portletRequest.getAttribute(
    				ConfigurationAdminWebKeys.CONFIGURATION_MODEL_ITERATOR);
    
    		if (configurationModelIterator.getTotal() > 0) {
    			return true;
    		}
    
    		return false;
    	}
    
    	@Reference
    	private Portal _portal;
    
    }
    ```

This covers some of the available methods. See the [Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/configuration/icon/BasePortletConfigurationIcon.html) 
for a complete list of the available methods. 

Great! Now you know how to configure your admin app's actions. 

## Related Topics

- [Applying Clay Patterns to Your Navigation Bar](/docs/7-2/frameworks/-/knowledge_base/f/applying-clay-patterns-to-navigation)
- [Configuring Your Application's Title and Back Link](/docs/7-2/frameworks/-/knowledge_base/f/configuring-your-applications-title-and-back-link)
