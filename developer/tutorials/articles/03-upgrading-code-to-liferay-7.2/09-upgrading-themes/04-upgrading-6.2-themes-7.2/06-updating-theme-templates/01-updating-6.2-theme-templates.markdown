---
header-id: updating-6-2-theme-templates
---

# Updating 6.2 Theme Templates

[TOC levels=1-4]

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
[`FTL_liferay.ftl`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl)

The [Gulp `upgrade` task](running-the-upgrade-task-for-6.2-themes) reports the 
required theme template changes in the log. For example, here are the 6.2 to 7.0 
upgrade log and 7.0 to 7.2 upgrade log for the Lunar Resort theme:

```bash
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
```

The log warns about removed and deprecated code and suggests replacements when 
applicable. 

In this section you'll learn how to update various theme templates to 
@product-ver@. 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-2/updating-6-2-portal-normal-theme-template">Let's Go<span class="icon-circle-arrow-right"></span></a>
