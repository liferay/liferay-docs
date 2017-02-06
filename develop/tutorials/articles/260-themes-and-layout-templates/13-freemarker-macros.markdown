# FreeMarker Macros [](id=freemarker-macros)

Macros let you assign theme template fragments to a variable. This keeps your 
theme templates from becoming cluttered and makes them easier to read. The 
syntax for a FreeMarker macro is straightforward. A macro directive is defined 
containing the template fragment. For example, below is the macro directive for 
@product@'s Control Menu:

    <#macro control_menu>
            <#if themeDisplay.isImpersonated() || (is_setup_complete && is_signed_in)>
                    <@liferay_product_navigation["control-menu"] />
            </#if>
    </#macro>
 
+$$$

**Note:** @product@'s default macro calls are namespaced with `liferay` (for 
example, `<@liferay.macro_variable_name />`). If you create custom macros, they
can be called with the explicit variable name.

$$$

To include the template fragment in your theme templates, call the macro using
the variable name:

    <@liferay.control_menu />

The macro is replaced with the template fragment when the page is rendered.
That's all there is to a basic macro.

Macros can also be passed arguments. For example @product@'s `language` macro 
has the parameter `key`:

    <#macro language
            key
    >
    ${languageUtil.get(locale, key)}
    </#macro>
 
You can pass an argument in the macro call like this:

    <@liferay.language key="powered-by" />

You can read more about FreeMarker macros at 
[freemarker.org](http://freemarker.org/docs/ref_directive_macro.html).
 
@product@ provides several macros that you can use in your FreeMarker theme
templates. These are covered next.

## @product@ FreeMarker Macros

There are several default macros defined in the 
[`FTL_Liferay.ftl` template](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl)
that you can use in your FreeMarker theme templates. The table below lists the
available macros and parameters:

| Macro | Parameters | Description | 
| --- | --- | --- |
| breadcrumbs | default_preferences = "" | Adds the Breadcrumbs portlet with optional preferences |
| control_menu | N/A | Adds the Control Menu portlet |
| css | file_name | Adds an external stylesheet with the specified file name location |
| date | format | Prints the date in the current locale with the given format |
| js | file_name | Adds an external JavaScript file with the specified file name source |
| language | key | Prints the specified language key in the current locale |
| language_format | arguments<br/>key | Formats the given language key with the specified arguments. For example, passing `go-to-x` as the `key` and `Mars` as the `arguments` prints "Go to Mars." |
| languages | default_preferences = "" | Adds the Languages portlet with optional preferences. |
| navigation_menu | default_preferences = ""<br/>instance_id = "" | Add the Navigation Menu portlet with optional preferences and an optional instance ID. `${freeMarkerPortletPreferences}` is usually passed as the value of `default_preferences`. |
| search | default_preferences = "" | Adds the Search portlet with optional preferences. |
| user_personal_bar | N/A | Adds the User Personal Bar portlet |

Now you know how to use @product@'s FreeMarker macros in your theme templates!

## Related Topics [](id=related-topics)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)

[Theme Reference Guide](/develop/reference/-/knowledge_base/7-0/theme-reference-guide)

