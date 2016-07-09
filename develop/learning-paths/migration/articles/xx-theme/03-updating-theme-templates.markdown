# Updating Theme Templates

In the last section of the Learning Path you updated the lunar resort's CSS
files per the suggestions in the gulp upgrade log. In this section you'll review
the breaking changes listed in the log for the theme templates and make the 
required updates.

If you take a look at the upgrade output, you'll see these lines:

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
            
This points out some of the changes to themes that have taken place in Liferay 
7 for theme templates. Follow these steps to make the theme template updates:

1.  Open `portal_normal.ftl` in your `src/templates` directory and replace the
    `<@liferay.dockbar />` directive with `<@liferay.control_menu />`.

    The Dockbar has been removed in Liferay 7, and has been replaced with the
    Control Menu. Due to the deprecation of the dockbar, the `dockbar-split`
    class is no longer needed.
    
2.  Find the `<body class="${css_class} dockbar-split">` element and remove
    `dockbar-split`.
    
    As you can see from the log, the `${theme}` variable is no longer available
    in FreeMarker templates. You can view the breaking change in more detail
    here: [https://goo.gl/9fXzYt](https://goo.gl/9fXzYt). You'll need to update
    the templates to use the new syntax.
    
3.  Find the `${theme.include(top_head_include)}` directive and replace it with
    the `<@liferay_util["include"] page=top_head_include />` directive.

    Since the `${theme}` variable is no longer available to access the utlities 
    and tags in FreeMarker templates, this accesses the utility directly and
    defines what page to include.
    
4.  Find the `${theme.include(body_top_include)}` directive and replace it with
    `<@liferay_util["include"] page=body_top_include />`.

5.  Find `${theme.include(content_include)}` and replace it with 
    `<@liferay_util["include"] page=content_include />`.    

6.  Find the `${theme.wrapPortlet("portlet.ftl", content_include)}` directive in
    the `<div id="content">` div and replace it with the following one:
    
        <@liferay_theme["wrap-portlet"] page="portlet.ftl">
            <@liferay_util["include"] page=content_include />
        </@>

7. Find the `body_bottom_include` and `bottom_include` directives at the
   bottom of the file and replace theme with the following ones:
   
        <@liferay_util["include"] page=body_bottom_include />

        <@liferay_util["include"] page=bottom_include />

    That takes care of all the `${theme}` variable updates
        
8.  Find the link `<a href="#main-content" id="skip-to-content">
    <@liferay.language key="skip-to-content" /></a>` and replace it with 
    `<@liferay_ui["quick-access"] contentId="#main-content" />` to use the new
    syntax.
    
9.  Find the following line 
    `<#include "${full_templates_path}/navigation.ftl" />` and update it to
    match the pattern below:
    
        <#if has_navigation && is_setup_complete>
            <#include "${full_templates_path}/navigation.ftl" />
        </#if>
        
    The last update for portal normal is to update the content `<div>` to use 
    the HTML5 `<section>` element.

10. Find the `<div id="content">` element and update it and the matching closing
    `<div>` to use the `<section>` element instead:
    
        <section id="content">
            <h1 class="hide-accessible">${the_title}</h1>
            ...
        </section>
        
    A `<h1>` element was added as well to match the updated 
    [_unstyled] (https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates/portal_normal.ftl)
    base theme.
    
That's all the updates you need to make to the theme templates for the lunar
resort theme. In the next section of the Learning Path you'll update the
resources importer for Liferay 7.
