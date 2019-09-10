---
header-id: embedding-portlets-in-themes-by-entity-type-and-action
---

# Embedding Portlets in Themes by Entity Type and Action

[TOC levels=1-4]

In this article, you'll learn how to declare an entity type and action in a 
custom theme, and you'll create a module that finds the correct portlet to use 
based on those given parameters. Follow these steps:

1.  Insert a declaration where you want the portlet embedded. This declaration 
    expects two parameters: the type of action and the class name of the entity 
    type the portlet should handle. An example configuration is shown below:

    ```markup
    <@liferay_portlet["runtime"]
        portletProviderAction=portletProviderAction.VIEW
        portletProviderClassName="com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry"
    />
    ```
    This example declares that the theme is requesting to view language entries. 
    @product@ determines which deployed portlet to use in this case by providing 
    the portlet with the highest service ranking. 

    | **Note:** In some cases, a default portlet is already provided to fulfill
    | certain requests. You can override the default portlet with your custom
    | portlet by specifying a higher service rank. To do this, set the following
    | property in your class' `@Component` declaration:
    | 
    |     property= {"service.ranking:Integer=20"}
    | 
    | Make sure you set the service ranking higher than the default portlet being
    | used.

2.  The Portal is not yet configured to handle this request. You must create a 
    module that can find the portlet that fits the theme's request. 
    [Create a module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project). 

3.  Create a unique package name in the module's `src` directory, and create a 
    new Java class in that package. To follow naming conventions, name the class 
    based on the entity type and action type, followed by *PortletProvider* 
    (e.g., `SiteNavigationLanguageEntryViewPortletProvider`). The class should 
    extend the 
    [`BasePortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html) 
    class and implement the appropriate portlet provider interface based on the 
    action you chose in your theme (e.g., 
    [`ViewPortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ViewPortletProvider.html), 
    [`BrowsePortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BrowsePortletProvider.html), 
    etc.). 

4.  Directly above the class's declaration, insert the following annotation:

    ```java
    @Component(
        immediate = true,
        property = {"model.class.name=CLASS_NAME"},
        service = INTERFACE.class
    )
    ```

    The `property` element must match the entity type you specified in your 
    theme declaration (e.g., 
    `com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry`). Also, your 
    `service` element should match the interface you're implementing (e.g., 
    `ViewPortletProvider.class`).

5.  Specify the methods you want to implement. Make sure to retrieve the portlet 
    ID and page ID that should be provided when this service is called by your 
    theme. 

    A common use case is to implement the `getPortletId()` and 
    `getPlid(ThemeDisplay)` methods. Below is an example configuration:

    ```java    
    @Override
    public String getPortletName() {
      return SiteNavigationLanguagePortletKeys.SITE_NAVIGATION_LANGUAGE;
    }

    @Override
    public PortletURL getPortletURL(
        HttpServletRequest httpServletRequest, Group group)
      throws PortalException {

      return PortletURLFactoryUtil.create(
        httpServletRequest, getPortletName(), PortletRequest.RENDER_PHASE);
    }

    /**
     * @deprecated As of Judson (7.1.x)
     */
    @Deprecated
    @Override
    protected long getPlid(ThemeDisplay themeDisplay) throws PortalException {
      return themeDisplay.getPlid();
    }
    ```

    This returns the portlet ID and the PLID, which is the ID that uniquely 
    identifies a page used by your theme. By retrieving these, your theme will 
    know which portlet to use, and which page to use it on. 

6.  Generate the module's JAR file and copy it to your app server's 
    `osgi/modules/` folder. Once the module is installed and activated in your 
    Portal's service registry, your embedded portlet is available for use 
    wherever your theme is used. 

Awesome! You successfully requested a portlet based on the entity and action 
types required, and created and deployed a module that retrieves the portlet and 
embeds it in your theme. 

## Related Topics

- [Embedding Portlets in Themes](/docs/7-2/frameworks/-/knowledge_base/f/embedding-portlets-in-themes)
- [Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)
- [Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
