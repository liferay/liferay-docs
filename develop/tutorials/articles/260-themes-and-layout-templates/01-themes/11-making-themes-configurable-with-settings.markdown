# Making Themes Configurable with Settings [](id=making-themes-configurable-with-settings)

Theme settings let site administrators control the look and feel of certain 
aspects of a theme. For example, you can create a theme setting to control the 
visibility of theme elements, such as only showing a site banner when the user 
is logged in. You can also create a theme setting to configure an element, such 
as a title or a background image. The Settings API is built flexibly to meet 
your needs. The sky's the limit. 

This tutorial covers how to create theme settings for a theme. 

Making configurable theme settings involves a multi-step process:

- Add the settings to `liferay-look-and-feel.xml`
- Assign the settings to variables in `init_custom.ftl`
- Use the settings variables in your theme templates
<!-- Create a language key for the setting(s) (optional)-->

Follow these steps to create theme settings:

1. Open `liferay-look-and-feel.xml` from the theme's `WEB-INF` folder. Settings
   placed here appear in the *Look and Feel* menu of @product@'s *Site
   Administration*. If your project doesn't have this file, create it in the
   `WEB-INF` folder and add the following XML content (make sure to replace the
   theme `id` and `name` with your theme's):

        <?xml version="1.0"?>
        <!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 7.0.0//EN" 
        "http://www.liferay.com/dtd/liferay-look-and-feel_7_0_0.dtd">

        <look-and-feel>
        	<compatibility>
        		<version>7.0.0+</version>
        	</compatibility>
        	<theme id="your-theme-name" name="Your Theme Name">
        		<template-extension>ftl</template-extension>
            <portlet-decorator id="barebone" name="Barebone">
        			<portlet-decorator-css-class>portlet-barebone</portlet-decorator-css-class>
        		</portlet-decorator>
        		<portlet-decorator id="borderless" name="Borderless">
        			<portlet-decorator-css-class>portlet-borderless</portlet-decorator-css-class>
        		</portlet-decorator>
        		<portlet-decorator id="decorate" name="Decorate">
        			<default-portlet-decorator>true</default-portlet-decorator>
        			<portlet-decorator-css-class>portlet-decorate</portlet-decorator-css-class>
        		</portlet-decorator>
        	</theme>
        </look-and-feel>

2.  Add `<settings></settings>` tags after the closing `</portlet-decorator>` 
    tag.

3.  Add a `<setting/>` element between the `<settings></settings>` tags for each 
    setting the theme requires. Below is an example pattern along with the 
    available attributes:

        <setting configurable="true" key="my-setting-language-key" 
        options="true,false" type="select" value="false" />

    The following attributes are available for theme settings:

    **configurable:** whether the setting is configurable or static. 
    **key:** the language key that identifies the theme setting. 
    **options:** sets the options for the select menu if the `type` is `select`. 
    **type:** the type of UI to render to control the theme setting. Possible 
    values are `checkbox`, `select`, `text`, or `textarea`.
    **value:** sets the default value for the theme setting.
    
    You can find more information about setting attributes and all other 
    configurations for the `liferay-look-and-feel.xml` in its 
    [DTD docs](@platform-ref@/7.0-latest/definitions/liferay-look-and-feel_7_0_0.dtd.html#settings).
 
4.  Open `init_custom.ftl` and assign the settings to variables using the 
    patterns below.

    Use the following pattern for settings that return a `Boolean` (for example 
    a select box with the options `true` and `false` or a toggle-switch checkbox 
    with values `yes` and `no`):
    
        <#assign my_variable_name =
        getterUtil.getBoolean(theme_settings["theme-setting-key"])>
        
    Use the following pattern for settings that return a `String` (for example, 
    a text input or textarea input):
    
        <#assign footer_text = 
        getterUtil.getString(theme_settings["theme-setting-key"])/>
 
5.  Use the theme setting variable in the theme template. For example, you can
    use the variable to check a condition, print a value, or even display a
    theme template. Examples of each case are shown below.
 
    This configuration is used in `portal_normal.ftl` to show the navigation
    breadcrumbs element if the `show_breadcrumbs` theme setting is `true`:

   `liferay-look-and-feel.xml`:
   
        <setting configurable="true" key="show-breadcrumbs" type="checkbox" 
        value="false" />
 
   `init_custom.ftl`:
   
       <#assign show_breadcrumbs =
       getterUtil.getBoolean(theme_settings["show-breadcrumbs"])>
 
   `portal_normal.ftl`:
   
        <#if show_breadcrumbs>
          <nav id="breadcrumbs">
            <@liferay.breadcrumbs />
          </nav>
        </#if>

    This example configuration prints a text input's value in a `<p>` element, 
    or defaults to *Hello Text* if no value is given:

    `liferay-look-and-feel.xml`:
    
        <setting configurable="true" key="custom-text" type="text" 
        value="Hello Text"/>
    
    `init_custom.ftl`:
    
        <#assign custom_text = 
        getterUtil.getString(theme_settings["custom-text"])/>
    
    `portal_normal.ftl`:
    
        <p>${custom_text}</p>

    This example renders the brief header template or detailed header 
    template based on the theme setting:

    `liferay-look-and-feel.xml`:

        <setting configurable="true" key="header-type" type="select" 
        options="brief,detailed" value="brief"/>

    `init_custom.ftl`:

        <#assign header_type = 
        getterUtil.getString(theme_settings["header-type"])/>

    `header_brief.ftl`: brief header template
    
    `header_detailed.ftl`: detailed header template

    `portal_normal.ftl`:

        <#if header_type == "brief">
          <#include "${full_templates_path}/header_brief.ftl" />
        <#elseif header_type == "detailed">
          <#include "${full_templates_path}/header_detailed.ftl" />
        </#if>

6.  Make sure the theme is installed. Open the *Control Menu* &rarr; *Site
    Administration* &rarr; *Navigation* &rarr; *Public Pages* and select the
    *Configure* option. Configure the theme settings from the *Look and Feel*
    section to see your changes. You can set the theme settings for an
    individual page by selecting the *Configure Page* option from the page's
    Actions menu and selecting the *Define a Specific look and feel for this
    page* option under the *Look and Feel* section.

![Figure 1: Here are examples of configurable settings for the site Admin.](../../../images/theme-settings-look-and-feel.png)

Now you know how to make configurable theme settings for your themes!

## Related Topics [](id=related-topics)

[Macros](/develop/tutorials/-/knowledge_base/7-0/freemarker-macros)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)

[Theme Contributors](/develop/tutorials/-/knowledge_base/7-0/theme-contributors)
