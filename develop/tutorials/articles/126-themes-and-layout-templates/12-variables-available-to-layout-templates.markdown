# Variables Available to Layout Templates [](id=variables-available-to-layout-templates)

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

Now you have a better understanding of the variables available to you
for your layout templates! 

**Related Topics**

[Making Themes Configurable with Settings](/develop/tutorials/-/knowledge_base/6-2/making-themes-configurable-with-settings)

[Creating a Layout Template Project in the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/creating-a-layout-template-project-in-the-plugins-sdk)
