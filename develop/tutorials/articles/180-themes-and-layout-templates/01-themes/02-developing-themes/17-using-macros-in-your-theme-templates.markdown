# Using @product@'s Macros in Your Theme [](id=using-liferays-macros-in-your-theme)

[Macros](https://freemarker.apache.org/docs/ref_directive_macro.html) 
let you assign theme template fragments to a variable. This keeps your theme 
templates from becoming cluttered and makes them easier to read. @product@ 
defines several macros in 
[`FTL_Liferay.ftl` template](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl) 
that you can use in your FreeMarker theme templates to include theme resources, 
standard portlets, and more. This tutorial shows how to use @product@'s macros 
in your themes. 

Follow these steps:
    
1.  Select one of the macros shown in the table below:

    | Macro | Parameters | Description | Example |
    | --- | --- | --- | --- |
    | breadcrumbs | default_preferences | Adds the Breadcrumbs portlet with optional preferences | `<@liferay.breadcrumbs />` |
    | control_menu | N/A | Adds the Control Menu portlet | `<@liferay.control_menu />` |
    | css | file_name | Adds an external stylesheet with the specified file name location | `<@liferay.css file_name="${css_folder}/mycss.css"/>` |
    | date | format | Prints the date in the current locale with the given format | `<@liferay.date format="/yyyy/MM/dd/HH/" />` |
    | js | file_name | Adds an external JavaScript file with the specified file name source | `<@liferay.js file_name="${javascript_folder}/myJs.js"/>` |
    | language | key | Prints the specified language key in the current locale | `<@liferay.language key="last-modified" />` |
    | language_format | arguments<br/>key | Formats the given language key with the specified arguments. For example, passing `go-to-x` as the key and `Mars` as the arguments prints *Go to Mars*. | `<@liferay.language_format arguments="${site_name}" key="go-to-x" />` |
    | languages | default_preferences | Adds the Languages portlet with optional preferences | `<@liferay.languages />` |
    | navigation_menu | default_preferences<br/>instance_id | Adds the Navigation Menu portlet with optional preferences and instance ID. | `<@liferay.navigation_menu />` |
    | search | default_preferences | Adds the Search portlet with optional preferences | `<@liferay.search />` |
    | search_bar | default_preferences | Adds the Search Bar portlet with optional preferences | `<@liferay.search_bar />` |
    | user_personal_bar | N/A | Adds the User Personal Bar portlet | `<@liferay.user_personal_bar />` |

2.  Call the macro in your theme template. @product@'s default FreeMarker macro 
    calls are namespaced with `liferay`. For example, to include the Search Bar 
    portlet, you would add the macro call shown below:
    
        <@liferay.search_bar>
        
3.  Include any required or optional arguments, such as 
    [portlet preferences](/develop/tutorials/-/knowledge_base/7-1/embedding-portlets-in-themes-and-layout-templates#setting-default-preferences-for-an-embedded-portlet), 
    in the macro call. For example, @product@'s `language` macro directive 
    includes a language key parameter:

        <#macro language
          key
        >
          ${languageUtil.get(locale, key)}
        </#macro>

    You can pass an argument in the macro call like this:

        <@liferay.language key="powered-by" />

    The example below sets the Search portlet's 
    [Portlet Decorator](/develop/tutorials/-/knowledge_base/7-1/creating-configurable-styles-for-portlet-wrappers) 
    to barebone:

        <@liferay.search default_preferences=
          freeMarkerPortletPreferences.getPreferences(
            "portletSetupPortletDecoratorId", "barebone"
          ) 
        />
        
    You can also pass multiple portlet preferences in an Object, as shown in the 
    example below for the Navigation Menu portlet:

        <#assign secondaryNavigationPreferencesMap = 
          {
            "displayStyle": "ddmTemplate_NAVBAR-BLANK-JUSTIFIED-FTL", 
            "portletSetupPortletDecoratorId": "barebone", 
            "rootLayoutType": "relative", 
            "siteNavigationMenuId": "0", 
            "siteNavigationMenuType": "1"
          } 
        />

        <@liferay.navigation_menu
          default_preferences=
          freeMarkerPortletPreferences.getPreferences(secondaryNavigationPreferencesMap)
          instance_id="main_navigation_menu"
        />

+$$$

**Note:** Portlet preferences are unique to each portlet, so first you must 
determine which preferences you want to configure. There are two ways to 
determine the proper key/value pair for a portlet preference. The first is to 
set the portlet preference manually, and then check the values in the 
`portletPreferences.preferences` column of the database as a hint for what to 
configure. 

Another approach is to search each app in your bundle for the keyword 
`preferences--`. This returns some of the app's JSPs that have the portlet 
preferences defined for the portlet. 

$$$

Now you know how to use @product@'s macros in your theme templates!

## Related Topics [](id=related-topics)

[Creating Themes](/develop/tutorials/-/knowledge_base/7-1/creating-themes)

[Creating Reusable Pieces of Code for Your Themes](/develop/tutorials/-/knowledge_base/7-1/creating-reusable-pieces-of-code-for-your-themes)

[Theme Reference Guide](/develop/reference/-/knowledge_base/7-1/theme-reference-guide)
