---
header-id: including-layout-templates-with-a-theme
---

# Including Layout Templates with a Theme

[TOC levels=1-4]

Although you can deploy a layout template by itself, you can also bundle it with 
a theme. If you generated a layout template with the [Layouts sub-generator](/docs/7-2/reference/-/knowledge_base/r/creating-layout-templates-with-the-themes-generator) 
from inside a generated theme project, the layout template is bundled with the 
theme automatically. If, however, you generated a layout template and want to 
bundle it with a theme afterwards, follow these steps to include the layout 
template with a theme:

1.  Copy the layout template's `liferay-layout-templates.xml` file to the 
    theme's `src/WEB-INF/` folder.

2.  Create a `layouttpl/custom/my-layouttpl/` folder inside the theme's `src/` 
    folder.

3.  Copy the layout template's FreeMarker (.ftl) file, and [thumbnail preview](/docs/7-2/frameworks/-/knowledge_base/f/creating-custom-layout-template-thumbnail-previews) 
    (.png) if it exist, over to the `layouttpl/custom/my-layouttpl/` folder.

4.  Copy the theme's `liferay-theme.json` file into the 
    `src/layouttpl/custom/my-layouttpl/` folder and rename it 
    `liferay-plugin.json`.

5.  Open `liferay-plugin.json`, rename the `LiferayTheme` entry `LiferayPlugin`, 
    and replace the `pluginName` entry's value with the name of the layout 
    template. Below is an example configuration:

```json    
{
  "LiferayPlugin": {
    "deploymentStrategy": "LocalAppServer",
    "appServerPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\liferay-ce-portal-tomcat-7.2.0\\tomcat-9.0.10",
    "deployPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\liferay-ce-portal-tomcat-7.2.0\\tomcat-9.0.10\\deploy",
    "url": "http://localhost:8080",
    "appServerPathPlugin": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\liferay-ce-portal-tomcat-7.2.0\\tomcat-9.0.10\\webapps\\my-layouttpl",
    "deployed": false,
    "pluginName": "my-layouttpl"
  }
}
```

Now you know how to include layout templates with your @product@ themes! 

## Related topics

- [Layout Templates with the Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/creating-layout-templates-with-the-themes-generator)
- [Creating Custom Layout Template Thumbnail Previews](/docs/7-2/frameworks/-/knowledge_base/f/creating-custom-layout-template-thumbnail-previews)
- [Themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
