# Themes [](id=themes-introduction)

Themes let you customize the default look and feel of your site. They let you 
inject your own flavor and personality and represent the visual identity of your 
brand or company. 

You'll learn the following:

- [Creating Themes](/develop/tutorials/-/knowledge_base/7-2/creating-themes) - 
  Learn how to create themes using various tools.
- [Developing Themes](/develop/tutorials/-/knowledge_base/7-2/developing-themes) - 
  Learn how to use @product@'s tools and features to develop your theme.
- [Extending Themes](/develop/tutorials/-/knowledge_base/7-2/extending-themes) - 
  Learn how to use @product@'s theme extension mechanisms and features to add to 
  your theme.

@product@ themes use the standard components (CSS, JS, and HTML) along with 
FreeMarker templates for rendering. @product@ provides several 
[default FreeMarker templates](/develop/tutorials/-/knowledge_base/7-2/theme-components-and-workflow#theme-templates) 
that each handle a key piece of functionality for the page. @product@ also 
provides several 
[theme template utilities](/develop/tutorials/-/knowledge_base/7-2/theme-components-and-workflow#theme-template-utilities) 
that let you use @product@'s portlets, taglibs, theme objects, and more in your 
theme templates. @product@ provides 
[CSS extensions and patterns out-of-the-box](/develop/tutorials/-/knowledge_base/7-2/theme-components-and-workflow#css-frameworks-and-extensions) 
and supports SASS, as well as multiple JavaScript frameworks. Several mechanisms 
are available for customizing, developing, and extending themes. 

## Theme Workflow [](id=theme-workflow)

Themes are built on top of one of the following base themes: 

- **Unstyled:** provides basic markup, functions, and images for Portal
- **Styled:** inherits from the Unstyled base theme and adds some styling on top

Themes can be built with your choice of tooling. @product@ offers its own set of 
tools as well that you can use to quickly get your themes up and running.

The following Liferay tools help you build themes:

- [Theme Builder Gradle Plugin](/develop/reference/-/knowledge_base/7-2/theme-builder-gradle-plugin)
- [Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-2/creating-themes)
- [Dev Studio](/develop/tutorials/-/knowledge_base/7-2/creating-themes-with-liferay-ide)
- [Blade CLI](/develop/tutorials/-/knowledge_base/7-2/blade-cli)'s 
  [Theme Template](https://dev.liferay.com/develop/reference/-/knowledge_base/7-2/theme-template). 

Depending on the tool you choose 
(
  [Theme Generator](/develop/reference/-/knowledge_base/7-2/theme-reference-guide), 
  [Gradle](/develop/reference/-/knowledge_base/7-2/theme-builder-gradle-plugin), 
  [Blade CLI](/develop/reference/-/knowledge_base/7-2/theme-template), 
  [Maven](/develop/reference/-/knowledge_base/7-2/theme-template), 
  or 
  [Dev Studio](/develop/reference/-/knowledge_base/7-2/theme-template)
), 
the theme anatomy is a bit different. The overall development process is the 
same though: 

1.  Mirror the structure of the files you want to modify. The main modifications 
    are placed in the following files:

    - `portal_normal.ftl`: main theme markup
    - `_custom.scss`: custom CSS styling
    - `main.js`: the theme's JavaScript

2.  Build and deploy the theme to your @product@ server.

3.  Apply the theme 
    [through the Look and Feel menu](/discover/portal/-/knowledge_base/7-2/page-set-look-and-feel) 
    by selecting your 
    [theme's thumbnail](/develop/tutorials/-/knowledge_base/7-2/creating-a-thumbnail-preview-for-your-theme). 

The finished theme is bundled as a WAR (Web application ARchive) file. 

+$$$

**Note:** While developing your theme, we recommend that you enable 
[Developer Mode](/develop/tutorials/-/knowledge_base/7-2/using-developer-mode-with-themes).
This un-minifies JS files and disables caching for CSS and FreeMarker template
files, making the debugging process much easier.

$$$

During theme development, if you've built your theme with the Liferay Theme 
Generator, you can use some helpful Gulp tasks to make the process easier:

- **build:** builds your theme's files based on the specified base theme. 
  See the 
  [gulp build tutorial](/develop/tutorials/-/knowledge_base/7-2/building-your-themes-files) 
  for more information.
- **extend:** sets the base theme or themelet to extend. See the 
  [gulp extend tutorial](/develop/tutorials/-/knowledge_base/7-2/changing-your-base-theme) 
  for more information.
- **init:** specifies the app server to deploy your theme to (automatically run
  during the initial creation of the theme). See the 
  [gulp init tutorial](/develop/tutorials/-/knowledge_base/7-2/configuring-your-themes-app-server)
  for more information. 
- **kickstart:** copies files from an existing theme into your theme to help 
  kickstart it. See the 
  [gulp kickstart tutorial](/develop/tutorials/-/knowledge_base/7-2/copying-an-existing-themes-files) 
  for more information.
- **status:** lists the base theme/themelets that your theme extends. See the 
  [gulp status tutorial](/develop/tutorials/-/knowledge_base/7-2/listing-your-themes-extensions) 
  for more information.
- **watch:** watches for changes to your theme's files and automatically deploys 
  them to the server when a change is made. See the 
  [gulp watch tutorial](/develop/tutorials/-/knowledge_base/7-2/automatically-deploying-theme-changes) 
  for more information.

See 
[Theme Components](/develop/reference/-/knowledge_base/7-2/theme-components) 
and 
[Understanding the Page Layout](/develop/reference/-/knowledge_base/7-2/understanding-the-page-layout) 
to get a top-level overview of how themes work. 
