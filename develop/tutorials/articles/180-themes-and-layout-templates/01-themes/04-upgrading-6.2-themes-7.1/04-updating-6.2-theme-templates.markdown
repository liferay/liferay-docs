# Updating 6.2 Theme Templates [](id=updating-6-2-theme-templates)

@product-ver@ theme templates are essentially the same as Liferay Portal 6.2 
theme templates. Here are the main changes:

-   Velocity templates were deprecated in Liferay Portal CE 7.0 and are now 
    removed in favor of FreeMarker templates in @product@. Below are the key 
    reasons for this move: 

    -   FreeMarker is developed and maintained regularly, while Velocity is no 
        longer actively being developed.

    -   FreeMarker is faster and supports more sophisticated macros.

    -   FreeMarker supports using taglibs directly rather than requiring a 
        method to represent them. You can pass body content to them, parameters, 
        etc.

-   The Dockbar has been replaced and reorganized into a set of three distinct 
    menus:

    -  *The Product Menu*: Manage Site and page navigation, content, settings 
       and pages for the current Site, and navigate to user account settings, 
       etc.

    -  *The Control Menu*: Configure and add content to the page and view the 
        page in a simulation window. 

    -  *The User Personal Bar*: Display notifications and the user's avatar and 
        name. 

    ![Figure 1: The Dockbar was removed in @product-ver@ and must be replaced with the new Control Menu.](../../../../images/upgrading-themes-dockbar.png) 

Start by converting your Velocity theme templates to FreeMarker. You can refer
to Apache's 
[FreeMarker documentation](https://freemarker.apache.org/docs/ref.html) 
for help. Common @product@ FreeMarker variables and macros can be found in 
[`FTL_liferay.ftl`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl)

If you used the 
[Gulp `upgrade` task](running-the-upgrade-task-for-6.2-themes), 
it reports the required theme template changes in the log. For example, here are 
the 6.2 to 7.0 upgrade log and 7.0 to 7.1 upgrade log for the Lunar Resort 
theme:

    ----------------------------------------------------------------
     Liferay Upgrade (6.2 to 7)
    ----------------------------------------------------------------

    File: portal_normal.ftl
        Warning: <@liferay.dockbar /> is deprecated, replace with 
        <@liferay.control_menu /> for new admin controls.
        Warning: not all admin controls will be visible without 
        <@liferay.control_menu />
        Warning: ${theme} variable is no longer available in Freemarker 
        templates, see https://goo.gl/9fXzYt for more information.
    [18:57:23] Finished 'upgrade:log-changes' after 5.61 ms
    [18:57:23] Finished 'upgrade' after 19 s

    ----------------------------------------------------------------
     Liferay Upgrade (7.0 to 7.1)
    ----------------------------------------------------------------
    
    Renamed aui.scss to clay.scss
    [19:16:54] Finished 'upgrade:log-changes' after 2.53 ms
    [19:16:54] Finished 'upgrade' after 16 min

The log warns about removed and deprecated code and suggests replacements when 
applicable. 

Next, you'll learn how to update various theme templates to @product-ver@. If 
you didn't modify any theme templates, you can skip these sections. 

## Updating Portal Normal FTL [](id=updating-portal-normal-ftl)

If you didn't customize `portal_normal.ftl`, you can skip this section. Follow 
these steps to update `portal_normal.ftl`:

1.  Open your modified `portal_normal.ftl` file and replace the following 6.2 
    directives with the updated syntax. This change is described in the 
    [7.0 Breaking Changes](/develop/reference/-/knowledge_base/7-0/breaking-changes#taglibs-are-no-longer-accessible-via-the-theme-variable-in-freemarker) 
    reference document:

      6.2                                |  &nbsp;Updated                                                                                                                     |
    ------------------------------------ |:------------------------------------------------------------------------------------------------------------------------------ |
    `${theme.include(top_head_include)}`                   | `<@liferay_util["include"] page=top_head_include />`                                                         |
    `${theme.include(body_top_include)}`                   | `<@liferay_util["include"] page=body_top_include />`                                                         |
    `${theme.include(content_include)}`                    | `<@liferay_util["include"] page=content_include />`                                                          |
    `${theme.wrapPortlet("portlet.ftl", content_include)}` | `<@liferay_theme["wrap-portlet"] page="portlet.ftl"> <@liferay_util["include"] page=content_include /> </@>` |
    `${theme.include(body_bottom_include)}`                | `<@liferay_util["include"] page=body_bottom_include />`                                                      |
    `${theme.include(bottom_include)}`                     | `<@liferay_util["include"] page=bottom_include />`                                                           |
    `${theme_settings["my-theme-setting"]}`                | `${themeDisplay.getThemeSetting("my-theme-setting")}`                                                                      |
    `${theme.runtime("56", "articleId=" + my_article_id)}` | `<@liferay_portlet["runtime"] portletName=`<br/>`"com_liferay_journal_content_web_portlet_JournalContentPortlet"` <br/>`queryString="articleId=" + my_article_id />`|

2.  Optionally remove the breadcrumbs and page title code:

        <nav id="breadcrumbs">		
            <@liferay.breadcrumbs />		
        </nav>
        ...
        <h2 class="page-title">
            <span>${the_title}</span>
        </h2>

3.  If you used the split Dockbar in your Liferay Portal 6.2 theme, remove 
    `dockbar-split` from the `body` element's `class` value so it matches the 
    markup below:
    
        <body class="${css_class}">

4.  Find the 
    `<a href="#main-content" id="skip-to-content"><@liferay.language key="skip-to-content" /></a>` 
    element and replace it with the updated Liferay UI quick access macro shown 
    below:
    
        <@liferay_ui["quick-access"] contentId="#main-content" />

5.  Replace the `<@liferay.dockbar />` macro with the updated Control menu 
    macro:

        <@liferay.control_menu />

6.  Add the `<#if...></#if>` wrappers to the `navigation.ftl` theme template 
    include:
    
        <#if has_navigation && is_setup_complete>
        	<#include "${full_templates_path}/navigation.ftl" />
        </#if>

7.  Replace the `content` `<div>` with an HTML 5 `section` element. The `section` 
    element is more accurate and provides better accessibility for screen 
    readers:

        <section id="content">

8.  Add the `<h1 class="hide-accessible">${the_title}</h1>` header element just 
    inside the `content` `<section>` to support accessibility. 

If you modified the navigation template for your theme, follow the steps in the 
next section. 

## Updating Navigation FTL [](id=updating-navigation-ftl)

Follow these steps to update your modified `navigation.ftl` file:

1.  Below the `<nav class="${nav_css_class}" id="navigation" role="navigation">` 
    element, add the following hidden heading for accessibility screen readers:

        <h1 class="hide-accessible">
            <@liferay.language key="navigation" />
        </h1>

2.  To access the layout, add the following variable declaration below the 
    `<#assign nav_item_css_class = "" />` variable declaration:

        <#assign nav_item_layout = nav_item.getLayout() />

3.  Replace the `${nav_item.icon()}`variable in the 
    `<a aria-labelledby="layout_${nav_item.getLayoutId()}"...</a>` anchor with 
    the following element: 

        <@liferay_theme["layout-icon"] layout=nav_item_layout />

The navigation template is updated. You can update `portlet.ftl` next. 

## Updating Portlet FTL [](id=updating-portlet-ftl)

Follow these steps to update your modified `portlet.ftl` file:

1.  Find the `<a class="icon-monospaced portlet-icon-back text-default" 
    href="${portlet_back_url}" title="<@liferay.language 
    key="return-to-full-page" />">` element and add the `list-unstyled` class 
    to it:

        <a 
          class="icon-monospaced list-unstyled portlet-icon-back text-default" 
          href="${portlet_back_url}" 
          title="<@liferay.language key="return-to-full-page" />"
        >

2.  Find the `<div class="autofit-float autofit-row">` element and add the 
    `portlet-header` class to it:
    
        <div class="autofit-float autofit-row portlet-header">

The portlet template is updated. You can update `init_custom.ftl` next.

## Updating Init Custom FTL [](id=updating-init-custom-ftl)

If your theme uses 
[configurable theme settings](/develop/tutorials/-/knowledge_base/7-1/making-configurable-theme-settings), 
update them to use the new syntax, following the patterns below.

Original syntax:

    <#assign theme_setting_variable = 
    getterUtil.getBoolean(theme_settings["theme-setting-key"])>

    <#assign theme_setting_variable = 
    getterUtil.getString(theme_settings["theme-setting-key"])>

Updated syntax:

    <#assign theme_setting_variable =
    getterUtil.getBoolean(themeDisplay.getThemeSetting("theme-setting-key"))/>

    <#assign theme_setting_variable = 
    themeDisplay.getThemeSetting("theme-setting-key")/>

For example, here are the Lunar Resort theme's updated theme settings:

    <#assign show_breadcrumbs = 
    getterUtil.getBoolean(themeDisplay.getThemeSetting("show-breadcrumbs"))/>

    <#assign show_page_title = 
    getterUtil.getBoolean(themeDisplay.getThemeSetting("show-page-title"))/>

That covers most, if not all, of the theme template changes. If you modified any 
other FreeMarker theme templates, compare them with templates in the 
[`_unstyled` theme](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates). 
If your theme uses the Liferay JS Theme Toolkit, refer to the suggested changes 
that the Gulp `upgrade` task reports. 

## Related Topics [](id=related-topics)

[Updating CSS Code](/develop/tutorials/-/knowledge_base/7-1/updating-6-2-css-code)

[Making Configurable Theme Settings](/develop/tutorials/-/knowledge_base/7-1/making-configurable-theme-settings)
