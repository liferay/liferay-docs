# Configuring Your Admin App's Actions Menu [](id=configuring-your-admin-apps-actions-menu)

In previous versions of Liferay it was common to have a series of buttons or 
menus with actions in the different views of the app. In @product-ver@ the 
proposed pattern is to move all of these actions to the upper right menu of the 
administrative portlet, leaving the primary action(often an "Add" operation) 
visible in the add menu, [using the Add Button pattern](/develop/tutorials/-/knowledge_base/7-0/applying-the-add-button-pattern).
For example, the web content application has the actions menu shown below:

![Figure 1: The upper right ellipsis menu contains most of the actions for the app.](../../../images/actions-menu.png)

The changes covered in this tutorial do not refer to actions menus associated 
with entities. Follow the instructions found in the 
[Applying Lexicon Patterns to Your Forms, Navigation, and Search](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-patterns-to-forms-navigation-and-search#applying-lexicon-patterns-to-forms-navigation-and-search)
tutorial to learn how to apply Lexicon to entity actions menus.

This tutorial shows how to configure the actions menu in your admin app. Get 
started by creating the `PortletConfigurationIconFactory` class next.

## Creating the Icon Factory Class

To add an action to the upper right menu of the Admin portlet you must first 
create a [`PortletConfigurationIcon` Component class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/configuration/icon/PortletConfigurationIcon.html).
This class specifies the portlet where the action will be added, the screen in 
which it will be shown, and the order (by specifying a weight).

In this example, the action is shown in the home page of the System Settings 
portlet. To make it appear in a secondary screen you can use the `path` property 
as shown below:

    @Component(
      immediate = true,
      property = {
         "javax.portlet.name=" +
            ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
         "path=/view_factory_instances"
      },
      service = PortletConfigurationIconFactory.class
    )
    public class ExportFactoryInstancesIconFactory
      extends BasePortletConfigurationIconFactory {

      @Override
      public PortletConfigurationIcon create(PortletRequest portletRequest) {
         return new ExportFactoryInstancesIcon(portletRequest);
      }

      @Override
      public double getWeight() {
         return 1;
      }

    }

The value of the `path` property depends on the MVC framework being used to 
develop the app.

For the MVCPortlet framework you should provide the path(often a JSP) that is 
used in the mvcPath parameter.

For MVCPortlet with MVCCommands the path should contain the mvcRenderCommandName 
where the actions should be displayed 
(such as `/document_library/edit_folder` for example).

Now that your `PortletConfigurationIconFactory` class is written, you can write 
the `PortletConfigurationIcon` class next.

## Writing the Configuration Icon Class

The second class that you must write is a class that extends the 
[`BasePortletConfigurationIcon` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/configuration/icon/BasePortletConfigurationIcon.html).
This class specifies the label of the action, whether it should be invoked with 
a GET or POST method, and the URL (or onClick JavaScript method) that should be 
invoked when the action is clicked. It can also implement some custom code to 
determine whether the action should be shown for the current request. For 
example the class below creates a `export-all-settings` label and specifies the 
`GET` method for the action:

    public class ExportAllConfigurationIcon extends BasePortletConfigurationIcon {

      public ExportAllConfigurationIcon(PortletRequest portletRequest) {
         super(portletRequest);
      }

      @Override
      public String getMessage() {
         return "export-all-settings";
      }

      @Override
      public String getMethod() {
         return "GET";
      }

      @Override
      public String getURL() {
         LiferayPortletURL liferayPortletURL =
            (LiferayPortletURL)PortalUtil.getControlPanelPortletURL(
               portletRequest, ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
               PortletRequest.RESOURCE_PHASE);

         liferayPortletURL.setResourceID("export");

         return liferayPortletURL.toString();
      }

      @Override
      public boolean isShow() {
         return true;
      }

    }

By default, if the portlet uses mvcPath, the global actions 
(such as configuration, export/import, maximized, etc) are displayed for the 
JSP indicated in the init param of the portlet 
`javax.portlet.init-param.view-template=/view.jsp`. The value indicates the JSP 
where the global actions should be displayed.

However, if the portlet uses MVCCommand, the views for the global actions need 
to be indicated with the init-parameter 
`javax.portlet.init-param.mvc-command-names-default-views=/wiki_admin/view` and 
the value must contain the mvcRenderCommandName where the global actions should 
be displayed.

For portlets that can be added to a page, if the desired behavior is to always 
include the configuration options, the following init-parameter should be added 
to the portlet:

    javax.portlet.init-param.always-display-default-configuration-icons=true

Now you know how to configure your admin app's actions!

## Related Topics

[Applying Lexicon Patterns to your Forms, Navigation, and Search](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-patterns-to-forms-navigation-and-search)

[Configuring Your Application's Title and Back Link](/develop/tutorials/-/knowledge_base/7-0/configuring-your-applications-title-and-back-link)