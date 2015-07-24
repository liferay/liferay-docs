# Configuring liferay-look-and-feel.xml 

Making configurable theme settings involves a multi-step process. First, you'll
need to add the theme settings to `liferay-look-and-feel.xml`.

1. Open your `liferay-look-and-feel.xml` located in your `docroot/WEB-INF/` 
   directory.

2. Add the following code below `<template-extension>ftl</template-extension>`:

        <settings>
            <setting configurable="true" key="show-breadcrumbs" 
            options="true,false" type="select" value="false" />
            <setting configurable="true" key="portlet-setup-show-borders-default" 
            options="true,false" type="select" value="true" />
            <setting configurable="true" key="show-page-title" 
            options="true,false" type="select" value="true"/>
        </settings>
        
    The code you just added identifies the theme settings for the theme. Each 
    setting has a few attributes in common. The `configurable` attribute sets 
    whether the setting is configurable by the user. `key` refers to the 
    language key that identifies the theme setting. The `options` attribute sets 
    the options for the select menu. `type` refers to the type of UI to render 
    to control the theme setting. Finally, the `value` attribute sets the 
    default value for the theme setting.
    
    Although you can create custom theme setting using your own `key`, you can 
    also make existing theme settings user configurable. The 
    `portlet-setup-show-borders-default` is an existing theme setting that has
    been made user configurable for the Lunar Resort.

3. Save the file and wait for the changes to publish.

4. Login to the Portal as an Admin and navigate to *Admin*&rarr;
   *Site Administration*&rarr;*Pages*.

5. Select the *Settings* drop-down panel. The theme settings you just 
   configured are now available.
   
![Figure 1: The *Settings* panel allows you to set theme settings for the entire site.](../../images/settings-panel.png)

The steps you've completed so far have added the custom theme settings to the 
*Look and Feel* menu for the theme. In order to make the theme settings 
functional and not just cosmetic, you'll have to assign them in 
`init_custom.ftl`. You'll take care of this in the next section.