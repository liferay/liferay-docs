# Updating 6.2 Init Custom Theme Template

<div class="learn-path-step">
    <p>Updating 6.2 Theme Templates<br>Step 4 of 4</p>
</div>

The Lunar Resort theme has a couple theme settings defined in `init_custom.ftl`. 
The syntax has changed slightly in @product-ver@. Follow these steps to update 
the theme setting syntax:

1.  Replace the `getterUtil.getBoolean(theme_settings` method with 
    `getterUtil.getBoolean(themeDisplay.getThemeSetting`:
      
Orginal:

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
    
<!--
Replace this with an actual example from another theme, perhaps one on 
marketplace like Westeros Bank Theme
-->

Orginal:

```markup
<#assign string_setting = 
getterUtil.getString(theme_settings["my-string-key"])/>
```

Updated:

```markup
<#assign string_setting = 
themeDisplay.getThemeSetting("my-string-key")/>
```

You can compare the updated theme templates with those found in the 
[`_unstyled` theme](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates). 

Refer to the suggested changes that the Gulp `upgrade` task reports. 

<!--
Need to demonstrate any other theme template files that have changed, even if 
they're not included in the Lunar Resort theme
-->

