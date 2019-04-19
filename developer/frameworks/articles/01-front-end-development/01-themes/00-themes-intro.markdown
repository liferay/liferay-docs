---
header-id: themes-introduction
---

# Themes

[TOC levels=1-4]

Themes customize the default look and feel of your site. You can inject your own
flavor and personality and represent the visual identity of your brand or
company. 

You'll learn these things:

- [Developing Themes](/docs/7-2/frameworks/-/knowledge_base/frameworks/developing-themes): 
  Learn how to use @product@'s tools and features to develop your theme.
- [Extending Themes](/docs/7-2/frameworks/-/knowledge_base/frameworks/extending-themes): 
  Learn how to use @product@'s theme extension mechanisms and features to add to 
  your theme.

Themes use the standard components (CSS, JS, and HTML) along with FreeMarker
templates for rendering. There are several 
[default FreeMarker templates](/docs/7-2/frameworks/-/knowledge_base/frameworks/theme-components-and-workflow#theme-templates)
that each handle a key piece of functionality for the page. There are also 
[theme template utilities](/docs/7-2/frameworks/-/knowledge_base/frameworks/theme-components-and-workflow#theme-template-utilities)
that let you use portlets, taglibs, theme objects, and more in your theme
templates. 
[CSS extensions and patterns](/docs/7-2/frameworks/-/knowledge_base/frameworks/theme-components-and-workflow#css-frameworks-and-extensions)
come out-of-the-box, and support SASS, and multiple JavaScript frameworks.
Several mechanisms are available for customizing, developing, and extending
themes. 

## Theme Workflow

Themes are built on top of one of the following base themes: 

- **Unstyled:** provides basic markup, functions, and images 
- **Styled:** inherits from the Unstyled base theme and adds some styling on top

Themes can be built with your choice of tooling. Liferay also offers its own set
of tools to get your themes up and running quickly. 

The following Liferay tools help you build themes:

- [Theme Builder Gradle Plugin](/docs/7-2/reference/-/knowledge_base/reference/theme-builder-gradle-plugin)
- [Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/reference/installing-the-theme-generator-and-creating-a-theme)
- [Dev Studio](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-themes-with-liferay-ide)
- [Blade CLI](/docs/7-2/frameworks/-/knowledge_base/frameworks/blade-cli)'s 
  [Theme Template](/docs/7-2/reference/-/knowledge_base/reference/theme-template). 

Depending on the tool you choose 
(
  [Theme Generator](/docs/7-2/reference/-/knowledge_base/reference/theme-generator), 
  [Gradle](/docs/7-2/reference/-/knowledge_base/reference/theme-builder-gradle-plugin), 
  [Blade CLI](/docs/7-2/reference/-/knowledge_base/reference/theme-template), 
  [Maven](/docs/7-2/reference/-/knowledge_base/reference/theme-template), 
  or 
  [Dev Studio](/docs/7-2/reference/-/knowledge_base/reference/theme-template)
), 
the theme anatomy can be different. The overall development process is the 
same: 

1.  Mirror the structure of the files you want to modify. Most of the time,
    you'll modify these files:

    - `portal_normal.ftl`: main theme markup
    - `_custom.scss`: custom CSS styling
    - `main.js`: the theme's JavaScript

2.  Build and deploy the theme.

3.  Apply the theme 
    [through the Look and Feel menu](/docs/7-2/user/-/knowledge_base/user/page-set-look-and-feel) 
    by selecting your 
    [theme's thumbnail](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-a-thumbnail-preview-for-your-theme). 

The finished theme is bundled as a WAR (Web application ARchive) file. 

| **Note:** While developing your theme, you should enable
| [Developer Mode](/docs/7-2/frameworks/-/knowledge_base/frameworks/using-developer-mode-with-themes).
| This disables the JavaScript minifier and caching for CSS and FreeMarker
| template files, which makes debugging easier.

If you've built your theme with the Liferay Theme Generator, you can use some
helpful Gulp tasks to streamline the process: 

- **build:** builds your theme's files based on the specified base theme. 
  See the 
  [gulp build tutorial](/docs/7-2/frameworks/-/knowledge_base/frameworks/building-your-themes-files) 
  for more information.
- **extend:** sets the base theme or themelet to extend. See the 
  [gulp extend tutorial](/docs/7-2/frameworks/-/knowledge_base/frameworks/changing-your-base-theme) 
  for more information.
- **init:** specifies the app server to deploy your theme to (automatically run
  during the initial creation of the theme). See the 
  [gulp init tutorial](/docs/7-2/frameworks/-/knowledge_base/frameworks/configuring-your-themes-app-server)
  for more information. 
- **kickstart:** copies files from an existing theme into your theme to help 
  kickstart it. See the 
  [gulp kickstart tutorial](/docs/7-2/frameworks/-/knowledge_base/frameworks/copying-an-existing-themes-files) 
  for more information.
- **status:** lists the base theme/themelets that your theme extends. See the 
  [gulp status tutorial](/docs/7-2/frameworks/-/knowledge_base/frameworks/listing-your-themes-extensions) 
  for more information.
- **watch:** watches for changes to your theme's files and automatically deploys 
  them to the server when a change is made. See the 
  [gulp watch tutorial](/docs/7-2/frameworks/-/knowledge_base/frameworks/automatically-deploying-theme-changes) 
  for more information.

See 
[Theme Components](/docs/7-2/frameworks/-/knowledge_base/frameworks/theme-components) 
and 
[Understanding the Page Layout](/docs/7-2/frameworks/-/knowledge_base/frameworks/understanding-the-page-layout) 
to get a top-level overview of how themes work. 
