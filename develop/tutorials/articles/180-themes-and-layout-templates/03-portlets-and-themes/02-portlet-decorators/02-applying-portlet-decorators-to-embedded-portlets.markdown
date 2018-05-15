# Applying Portlet Decorators to Embedded Portlets [](id=applying-portlet-decorators-to-embedded-portlets)

Once you have installed a theme that contains Portlet Decorators, site
administrators can apply them to a portlet instance by selecting the
Application Decorator in the Look and Feel Configuration dialog. If your theme
contains embedded portlets, it's also possible to apply an Application
Decorator other than the default one by setting its preferences.

This tutorial demonstrates how to apply Portlet Decorators to Embedded Portlets
in your theme.

## Setting Application Decorator Preferences [](id=setting-portlet-decorator-preferences)

To define a default Application Decorator for your theme's embedded portlets,
you must set a default decorator in the portlet preferences.

For example, the 
[Classic theme](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-theme/frontend-theme-classic/src/templates/portal_normal.ftl)
declares an Application Decorator with Id `barebone` and applies it to the
embedded Search portlet in its `portal_normal.ftl`:

    <div class="navbar navbar-top navigation-bar-secondary">
      <div class="container user-personal-bar">
        <#assign preferences = 
        freeMarkerPortletPreferences.getPreferences(
          "portletSetupPortletDecoratorId", "barebone"
        ) />

        <#if show_header_search>
          <div class="ml-auto mr-4 navbar-form" role="search">
            <@liferay.search default_preferences="${preferences}" />
          </div>
        </#if>

        <@liferay.user_personal_bar />
      </div>
    </div>

To set the default decorator for your embedded portlets, follow these steps:

1.  Set the value for the `portletSetupPortletDecoratorId` to the Id of the
    Application Decorator you want to use. The example below assigns the 
    `barebone` decorator to the `preferences` variable:

        <#assign preferences = freeMarkerPortletPreferences.getPreferences(
          "portletSetupPortletDecoratorId", "barebone"
        ) />

2.  Next, set the `default_preferences` attribute of the portlet's tag to
    the variable you just defined (`preferences` in the last step):

        <@liferay.search default_preferences= "${preferences}" />

Your embedded portlets now have a custom default Application Decorator!

## Related Topics [](id=related-topics)

[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-1/embedding-portlets-in-themes-and-layout-templates)

[Providing Portlets to Manage Requests](/develop/tutorials/-/knowledge_base/7-1/providing-portlets-to-manage-requests)
