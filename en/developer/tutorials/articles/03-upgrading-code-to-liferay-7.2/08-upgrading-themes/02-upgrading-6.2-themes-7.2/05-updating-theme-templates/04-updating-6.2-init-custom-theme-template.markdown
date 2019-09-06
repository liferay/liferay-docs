---
header-id: updating-6-2-init-custom-theme-template
---

# Updating 6.2 Init Custom Theme Template

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Updating 6.2 Theme Templates</p><p>Step 3 of 3</p>
</div>

The Lunar Resort theme has a couple theme settings defined in `init_custom.ftl`. 
The syntax has changed slightly in @product-ver@. Follow these steps to update 
the theme setting syntax:

1.  Replace the `getterUtil.getBoolean(theme_settings` method with 
    `getterUtil.getBoolean(themeDisplay.getThemeSetting`:
      
    Original:

    ```markup
    <#assign show_breadcrumbs = 
    getterUtil.getBoolean(theme_settings["show-breadcrumbs"])/>

    <#assign show_page_title = 
    getterUtil.getBoolean(theme_settings["show-page-title"])/>
    ```

    Updated:

    ```markup
    <#assign show_breadcrumbs = 
    getterUtil.getBoolean(themeDisplay.getThemeSetting("show-breadcrumbs"))/>

    <#assign show_page_title = 
    getterUtil.getBoolean(themeDisplay.getThemeSetting("show-page-title"))/>
    ```

2.  Although the Lunar Resort theme doesn't have any String variables, you would 
    replace the `getterUtil.getString(theme_settings` method with 
    `themeDisplay.getThemeSetting`:

    Original:

    ```markup
    <#assign string_setting = 
    getterUtil.getString(theme_settings["my-string-key"])/>
    ```

    Updated:

    ```markup
    <#assign string_setting = 
    themeDisplay.getThemeSetting("my-string-key")/>
    ```

Awesome! The theme templates are updated. You can always compare theme templates 
with the updated ones found in the 
[`_unstyled` theme](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates),
if you're unsure if  something has changed. Refer to the suggested changes that
the Gulp `upgrade`  task reports for the theme. 
