# Updating 6.2 Init Custom Theme Template

<div class="learn-path-step">
    <p>Updating 6.2 Theme Templates<br>Step 4 of 4</p>
</div>

The Lunar Resort theme has a couple theme settings defined in `init_custom.ftl`. 
The syntax has changed slightly in @product-ver@. Follow these steps to update 
the theme setting syntax:

1.  

Original syntax:

    <#assign theme_setting_variable = 
    getterUtil.getBoolean(theme_settings["theme-setting-key"])>

    <#assign theme_setting_variable = 
    getterUtil.getString(theme_settings["theme-setting-key"])>

Updated syntax:

    <#assign theme_setting_variable =
    getterUtil.getBoolean(themeDisplay.getThemeSetting("theme-setting-key"))/>

    <#assign theme_setting_variable = 
    themeDisplay.getThemeSetting("theme-setting-key")/>

For example, here are the Lunar Resort theme's updated theme settings:

    <#assign show_breadcrumbs = 
    getterUtil.getBoolean(themeDisplay.getThemeSetting("show-breadcrumbs"))/>

    <#assign show_page_title = 
    getterUtil.getBoolean(themeDisplay.getThemeSetting("show-page-title"))/>



That covers most, if not all, of the theme template changes. If you modified any 
other FreeMarker theme templates, compare them with templates in the 
[`_unstyled` theme](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates). 
If your theme uses the Liferay JS Theme Toolkit, refer to the suggested changes 
that the Gulp `upgrade` task reports. 

