# Updating 6.2 Portal Normal Theme Template

<div class="learn-path-step">
    <p>Updating 6.2 Theme Templates<br>Step 1 of 4</p>
</div>

Follow these steps to update `portal_normal.ftl`:

1.  Open your modified `portal_normal.ftl` file and replace the following 6.2 
    directives with the updated syntax. This change is described in the 
    [7.0 Breaking Changes](/docs/7-0/reference/-/knowledge_base/r/breaking-changes#taglibs-are-no-longer-accessible-via-the-theme-variable-in-freemarker) 
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

```html
<nav id="breadcrumbs">		
    <@liferay.breadcrumbs />		
</nav>
...
<h2 class="page-title">
    <span>${the_title}</span>
</h2>
```

3.  Remove `dockbar-split` from the `body` element's `class` value so it matches 
    the markup below:

```html    
<body class="${css_class}">
```

4.  Find the 
    `<a href="#main-content" id="skip-to-content"><@liferay.language key="skip-to-content" /></a>` 
    element and replace it with the updated Liferay UI quick access macro shown 
    below:

```markup    
<@liferay_ui["quick-access"] contentId="#main-content" />
```

5.  Replace the `<@liferay.dockbar />` macro with the updated Control menu 
    macro:

```markup
<@liferay.control_menu />
```

6.  Add the `<#if...></#if>` wrappers to the `navigation.ftl` theme template 
    include:

```markup    
<#if has_navigation && is_setup_complete>
	<#include "${full_templates_path}/navigation.ftl" />
</#if>
```

7.  Replace the `content` `<div>` with an HTML 5 `section` element. The `section` 
    element is more accurate and provides better accessibility for screen 
    readers:

```html
<section id="content">
```

8.  Add the `<h1 class="hide-accessible">${the_title}</h1>` header element just 
    inside the `content` `<section>` to support accessibility. 

`portal_normal.ftl` is updated! Next you can update the navigation template. 