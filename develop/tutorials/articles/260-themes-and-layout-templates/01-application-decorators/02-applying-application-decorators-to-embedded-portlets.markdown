# Applying Application Decorators to Embedded Portlets [](id=applying-application-decorators-to-embedded-portlets)

Once you have installed a theme that contains Application Decorators, site 
administrators can apply them to an application instance by selecting the 
Application Decorator in the Look and Feel Configuration dialog.

If your theme contains embedded applications, it’s also possible to apply an 
Application Decorator other than the default one by setting its preferences.

This tutorial demonstrates how to:

- Apply Application Decorators to Embedded Portlets in your theme.

Read on to find out how you can apply default decorators to your embedded 
applications.

## Setting Application Decorator Preferences [](id=setting-application-decorator-preferences)

To define a default Application Decorator for the embedded portlets of your
theme, you have to set a default decorator in the portlet preferences.

For example, the [Classic theme](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/frontend-theme/frontend-theme-classic-web/src/main/resources/META-INF/resources/classic/_diffs/templates/navigation.ftl) 
declares an Application Decorator with Id `barebone` and applies it to the 
embedded Navigation Menu application and Search application in its 
`navigation.ftl`:

    <#assign VOID = 
    freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", 
    "barebone")>

    <div aria-expanded="false" class="collapse navbar-collapse" 
    id="navigationCollapse">
	    <#if has_navigation && is_setup_complete>
		    <nav class="${nav_css_class} site-navigation" 
		    id="navigation" role="navigation">
			    <div class="navbar-form navbar-right" role="search">
				    <@liferay.search default_preferences=
				    "${freeMarkerPortletPreferences}" />
			    </div>

			    <div class="navbar-right">
				    <@liferay.navigation_menu default_preferences=
				    "${freeMarkerPortletPreferences}" />
			    </div>
		    </nav>
	    </#if>
    </div>

    <#assign VOID = freeMarkerPortletPreferences.reset()>

To set the default decorator for your embedded applications, follow these steps:

1.  Set the value for the `portletSetupPortletDecoratorId` to the Id of the 
    Application Decorator you want to use:

        <#assign VOID = 
        freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", 
        "barebone")> 

2.  Next, set the `default_preferences` attribute of the application's tag to
    the `freeMarkerPortletPreferences` variable you just defined:
    
        <@liferay.search default_preferences= "${freeMarkerPortletPreferences}" />

Your embedded portlets now have a custom default Application Decorator!

## Related Topics [](id=related-topics)

[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/embedding-portlets-in-themes-and-layout-templates)

[Providing Portlets to Manage Requests](/develop/tutorials/-/knowledge_base/7-0/providing-portlets-to-manage-requests)
