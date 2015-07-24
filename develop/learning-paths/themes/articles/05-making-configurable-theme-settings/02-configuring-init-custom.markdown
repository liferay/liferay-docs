# Configuring init_custom.ftl 

So far you've added the theme settings to the *Look and Feel* menu in Liferay.
In this section you'll add the logic to the theme settings. 

1. Open `init_custom.ftl` and insert the following code:

        <#assign show_breadcrumbs = 
        getterUtil.getBoolean(theme_settings["show-breadcrumbs"])>

        <#assign show_page_title = 
        getterUtil.getBoolean(theme_settings["show-page-title"])>

    The code you just added assigns Freemarker variables to the theme settings 
    using the `#assign` declaration. The theme settings are accessed by their 
    `key`, that was assigned in `liferay-look-and-feel.xml`, using the following 
    code: `getterUtil.getBoolean(theme_settings[key]!)`. Note that 
    `portlet-setup-show-borders-default` is not assigned a variable because it 
    is a default theme setting that already exist. Next you'll need to set the 
    condition to display the theme elements in `portal_normal.ftl`.

2. Open `portal_normal.ftl` and replace:
   
        <h2 class="page-title">
            <span>${the_title}</span>
        </h2>
        
    with the following code:
    
        <#if show_page_title>
        <h2 class="page-title">
            <span>${the_title}</span>
        </h2>
        </#if>
        
    Now if `show_page_title` is set to `true` the `<h2>` *page-title* element
    will be rendered, and if it is set to `false` the element will not be
    rendered. 

3. Find `<nav id="breadcrumbs"><@liferay.breadcrumbs /></nav>` and add the 
   condition to it:
        
        <#if show_breadcrumbs>
            <nav id="breadcrumbs"><@liferay.breadcrumbs /></nav>
        </#if>
   
    If `show_breadcrumbs` is set to `true` the breadcrumbs will be included on 
    the page, and if it's set to `false` the breadcrumbs won't be included on 
    the page. 
    
4. Save the file and wait for the changes to publish.

Now if you go to the *Settings* menu in the *Site Administration* you can 
configure the settings for the theme and see your changes. So far you've learned 
how to set theme settings site-wide. In the next section you'll learn how to set
theme settings for an individual page.