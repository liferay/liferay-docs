---
header-id: product-freemarker-macros
---

# @product@ FreeMarker Macros

[TOC levels=1-4]

@product@ defines several 
[macros](https://freemarker.apache.org/docs/ref_directive_macro.html) in 
[`FTL_Liferay.ftl` template](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl) 
that you can use in your theme templates to include theme resources, standard 
portlets, and more. @product@ also exposes its taglibs as FreeMarker 
macros. See each 
[taglib's documentation](/docs/7-2/frameworks/-/knowledge_base/frameworks/front-end-taglibs) 
for more information on using the taglib in your FreeMarker templates. This 
reference guide lists the available FreeMarker macros that @product@ offers. 

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

A few reference examples are shown below.

## Reference Examples

The example below includes a language key with the `language` macro directive 
along with its language `key` parameter:

    <@liferay.language key="powered-by" />

This example includes the Search portlet with its 
[Portlet Decorator](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-configurable-styles-for-portlet-wrappers) 
portlet preference set to barebone:

    <@liferay.search default_preferences=
      freeMarkerPortletPreferences.getPreferences(
        "portletSetupPortletDecoratorId", "barebone"
      ) 
    />

You can also pass multiple portlet preferences in an object, as shown in the 
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

| **Note:** Portlet preferences are unique to each portlet, so first you 
| must determine which preferences you want to configure. There are two ways 
| to determine the proper key/value pair for a portlet preference. The first 
| is to set the portlet preference manually, and then check the values in 
| the `portletPreferences.preferences` column of the database as a hint for 
| what to configure. 
|
|  Another approach is to search each app in your bundle for the keyword 
|  `preferences--`. This returns app JSPs that have the portlet preferences 
|  defined for the portlet. 
