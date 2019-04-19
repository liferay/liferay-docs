---
header-id: making-configurable-theme-settings
---

# Making Configurable Theme Settings

[TOC levels=1-4]

If you have an aspect of a theme that you want an Administrator to configure 
without having to manually update and redeploy the theme, you can create a 
*theme setting* for it. Theme settings are very versatile and can be customized 
to meet your needs. 

![Figure 1: Here are examples of configurable settings for the site Admin.](../../../../images/theme-dev-configurable-theme-settings.png)

Follow the steps below to create theme settings:

1.  Open your theme's `WEB-INF/liferay-look-and-feel.xml` file, and follow the 
    pattern below to nest a `<setting/>` element inside the parent `<settings>` 
    element for each setting you want to add:

    ```xml
    <look-and-feel>
    	<compatibility>
    		<version>7.2.0+</version>
    	</compatibility>
    	<theme id="your-theme-name" name="Your Theme Name">
    		<template-extension>ftl</template-extension>
        <settings>
          <setting configurable="true" key="theme-setting-key"
          options="true,false" type="select" value="true" />
          <setting configurable="true" key="theme-setting-key"
          type="text" value="My placeholder text" />
        </settings>
        <portlet-decorator>
          portlet decorators...
    		</portlet-decorator>
    	</theme>
    </look-and-feel>
    ```

    The example below adds a text input setting for a custom hex code:
    
    ```xml
    <settings>
      <setting configurable="true" key="my-hex-code" type="text" value="blue" />
    </settings>
    ```

    See the `liferay-look-and-feel.xml`'s 
    [DTD docs](@platform-ref@/7.2-latest/definitions/liferay-look-and-feel_7_2_0.dtd.html#settings) 
    for an explanation of the setting's configuration options. 

    | **Note:** You can modify theme settings with JavaScript to provide a more 
    | custom experience. The example below modifies the theme setting, changing 
    | its `type` to `color`, to provide a color picker for the user:  
    |
    | ```xml
    | <setting configurable="true" key="user-color"
    | type="text" value="#993300"  
    | >
    | <![CDATA[  
    |      AUI().ready('node',function(A) {
    |           A.one("#[@NAMESPACE@]user-color").setAttribute("type", "color");    
    |           A.one("#[@NAMESPACE@]user-color").setAttribute("style", "height: 35px; width: 200px");    
    |       });
    | ]]>
    | </setting>
    | ```

2.  Create a file called `init_custom.ftl` in your theme's `templates` folder if 
    it doesn't already exist, and follow the patterns in the table below to 
    define your theme setting variables in it:

    | Return Type | Description | Pattern |
    | --- | --- | --- |
    | Boolean | a select box with the options `true` and `false` or a checkbox with values `yes` and `no` | `<#assign my_variable_name = getterUtil.getBoolean(themeDisplay.getThemeSetting("theme-setting-key"))/>` |
    | String | a text input or text area input | `<#assign my_variable_name = getterUtil.getString(themeDisplay.getThemeSetting("theme-setting-key"))/>` |
 
    The example below adds a custom hex code setting:

        <#assign my_hex_code = 
        getterUtil.getString(themeDisplay.getThemeSetting("my-hex-code"))/>

3.  Add your theme setting variables to the theme template. The example below 
    prints `my_hex-code`'s value as the value of the header's `style` attribute:

    `portal_normal.ftl`:

    ```html
    <header style="background-color:${my_hex_code}">
    ```

4.  [Deploy the theme](/docs/7-2/frameworks/-/knowledge_base/frameworks/deploying-and-applying-themes) 
    to apply the changes. To set the theme setting for a Public or Private page 
    set, click the *Gear icon* next to the page set you want to configure and 
    update the setting under the *Look and Feel* tab. Alternatively, you can set 
    the theme setting for an individual page by opening the *Actions menu* next 
    to the page and selecting *Configure* and choosing the 
    *Define a Specific look and feel for this page* option. 

Great! You've created configurable settings for your theme. 

## Related Topics

- [Creating Reusable Pieces of Code for Your Themes](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-reusable-pieces-of-code-for-your-themes)
- [Listing Your Theme's Extensions](/docs/7-2/frameworks/-/knowledge_base/frameworks/listing-your-themes-extensions)
- [Importing Resources with a Theme](/docs/7-2/frameworks/-/knowledge_base/frameworks/importing-resources-with-a-theme)
