# Macros [](id=freemarker-macros)

Macros let you assign theme template fragments to a variable. This keeps your 
theme templates from becoming cluttered and makes them easier to read. 

Note that **Velocity templates are supported, but deprecated as of Liferay 
Portal CE 7.0 and Liferay DXP**. We recommend that you convert your Velocity 
theme templates to FreeMarker at your earliest convenience to avoid future
issues. This tutorial covers both FreeMarker and Velocity template macros to
help with your conversion process.

The syntax for a macro is straightforward. A macro directive is defined 
containing the template fragment. For example, below is the macro directive for 
@product@'s Control Menu:

FreeMarker:

    <#macro control_menu>
            <#if themeDisplay.isImpersonated() || (is_setup_complete && is_signed_in)>
                    <@liferay_product_navigation["control-menu"] />
            </#if>
    </#macro>

Velocity:

    #macro (control_menu)
            #if ($themeDisplay.isImpersonated() || ($is_setup_complete && $is_signed_in))
                $theme.runtime(
                    "com.liferay.admin.kernel.util.PortalProductNavigationControlMenuApplicationType$ProductNavigationControlMenu",  
                    $portletProviderAction.VIEW
                )
            #end
    #end

+$$$

**Note:** @product@'s default FreeMarker macro calls are namespaced with 
`liferay` (for example, `<@liferay.macro_variable_name />`). If you create 
custom macros, they can be called with the explicit variable name.

$$$

To include the template fragment in your theme templates, call the macro using
the variable name:

FreeMarker:

    <@liferay.control_menu />
 
Velocity:

    #control_menu()

The macro is replaced with the template fragment when the page is rendered.
That's all there is to a basic macro.

Macros can also be passed arguments. For example @product@'s `language` macro 
has a language key parameter:

FreeMarker:

    <#macro language
            key
    >
    ${languageUtil.get(locale, key)}
    </#macro>

Velocity:

    #macro (language $lang_key)
    $languageUtil.get($locale, $lang_key)
    #end    

You can pass an argument in the macro call like this:

FreeMarker:

    <@liferay.language key="powered-by" />
 
Velocity:

    #language ("powered-by")

You can read more about FreeMarker macros and Velocity macros at 
[freemarker.org](http://freemarker.org/docs/ref_directive_macro.html) and 
[velocity.apache.org](http://velocity.apache.org/engine/1.7/user-guide.html#velocimacros).
 
@product@ provides several macros that you can use in your theme templates. 
These are covered next.

## @product@ Macros

There are several default macros defined in the 
[`FTL_Liferay.ftl` template](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl)
that you can use in your FreeMarker theme templates. Likewise, 
[VM_liferay.vm](https://raw.githubusercontent.com/liferay/liferay-portal/master/modules/apps/foundation/portal-template/portal-template-velocity/src/main/resources/VM_liferay.vm) 
defines the default macros for Velocity. The table below lists the available 
macros and parameters:

| Macro | Parameters | Description | 
| --- | --- | --- |
| breadcrumbs | default preferences | Adds the Breadcrumbs portlet with optional preferences |
| control_menu | N/A | Adds the Control Menu portlet |
| css | filename | Adds an external stylesheet with the specified file name location |
| date | format | Prints the date in the current locale with the given format |
| js | filename | Adds an external JavaScript file with the specified file name source |
| language | key | Prints the specified language key in the current locale |
| language_format | arguments<br/>key | Formats the given language key with the specified arguments. For example, passing `go-to-x` as the key and `Mars` as the arguments prints *Go to Mars*. |
| languages | default preferences | Adds the Languages portlet with optional preferences |
| navigation_menu | default preferences<br/>instance ID | Adds the Navigation Menu portlet with optional preferences and instance ID. `${freeMarkerPortletPreferences}` or `$velocityPortletPreferences.toString()` is a common value for default preferences. |
| search | default preferences | Adds the Search portlet with optional preferences |
| user_personal_bar | N/A | Adds the User Personal Bar portlet |

Now you know how to use @product@'s macros in your theme templates!

## Related Topics [](id=related-topics)

[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)

[Theme Reference Guide](/develop/reference/-/knowledge_base/7-0/theme-reference-guide)

