# Layout Template Project Files and Variables

One or more layout template plugins can reside in a layout template project.
Take a look and see what each template file does: 

- `[project-name].tpl`: Generates the HTML structure of the template. 
- `[project-name].wap.tpl`: Variant template for mobile devices. WAP stands for
  wireless application protocol. 
- `[project-name].png`: Thumbnail representation of the template that you see in
  Liferay Portal from the Page Layout screen. You'll have to create a custom
  thumbnail image to draw attention to your layout, but you can use the default
  thumbnail PNG file as a starting point.

![Figure 1: This is an example of a default layout template thumbnail.](../../images/blank_columns.png)

You'll move on to Liferay configuration files next.

## Liferay Configuration Files

In addition to the three template-specific files, a layout template project has
two Liferay configuration files:

- `liferay-layout-templates.xml`: Specifies the name of the layout templates and
  the location of their TPL and PNG files. 
- `liferay-plugin-package.properties`: Describes the plugin project to Liferay's
  hot deployer. 

Now that you're familiar with the layout template's files and directory
structure, you can dive right in and learn about the variables available to
layout templates. 

## Variables Available to Layout a Template

A number of variables are available for you to use in your custom TPL files. For
your convenience, they're all listed in the table below. 

| Variable | Type | Description |
| -------- | ---- | ----------- |
 $processor | com.liferay.portal.layoutconfiguration.util.velocity.TemplateProcessor | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs-all/com/liferay/portal/layoutconfiguration/util/velocity/TemplateProcessor.html) |
 $request | javax.servlet.http.HttpServletRequest| |
 $themeDisplay | com.liferay.portal.theme.ThemeDisplay | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/theme/ThemeDisplay.html) |
 $company | com.liferay.portal.model.Company | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/Company.html) |
 $user | com.liferay.portal.model.User | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/User.html) |
 $realUser | com.liferay.portal.model.User | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/User.html) |
 $layout | com.liferay.portal.model.Layout | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/Layout.html) |
 $layouts | java.util.List<com.liferay.portal.model.Layout> ||
 $plid | java.lang.Long ||
 $layoutTypePortlet | com.liferay.portal.model.LayoutTypePortlet | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/LayoutTypePortlet.html) |
 $portletGroupId | java.lang.Long ||
 $locale | java.util.Locale ||
 $timeZone| java.util.TimeZone ||
 $theme | com.liferay.taglib.util.VelocityTaglib | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs-all/com/liferay/taglib/util/VelocityTaglib.html) |
 $colorScheme | com.liferay.portal.model.ColorScheme | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/ColorScheme.html) |
 $portletDisplay | com.liferay.portal.theme.PortletDisplay | [Javadoc](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/theme/PortletDisplay.html) |
---

Now you have a better understanding of the files and variables available to you
for layout templates! 

