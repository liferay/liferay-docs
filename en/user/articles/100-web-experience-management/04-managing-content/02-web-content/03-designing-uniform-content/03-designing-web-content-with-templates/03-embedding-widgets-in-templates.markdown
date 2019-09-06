---
header-id: embedding-widgets-in-templates
---

# Embedding Widgets in Templates

[TOC levels=1-4]

You can also embed widgets in web content templates. Core apps and custom apps, 
instanceable or non-instanceable can be embedded in web content templates. Below
is an example of embedding a Language widget in FreeMarker:

```markup
<@liferay_portlet_ext["runtime"] portletName="com_liferay_portal_kernel_servlet_taglib_ui_LanguageEntry" />
```

| **Warning:** The `theme` variable is no longer injected into the FreeMarker
| context. For more information about why the theme variable was removed for
| @product@ 7.0 and suggestions for updating your code, visit the
| [Taglibs Are No Longer Accessible via the theme Variable in FreeMarker](/docs/7-0/reference/-/knowledge_base/r/breaking-changes#taglibs-are-no-longer-accessible-via-the-theme-variable-in-freemarker)
| breaking change entry.

In addition to embedding widgets in templates, you can embed a template within
another template. This allows for reusable code, JavaScript library imports,
scripts, or macros.

Below is an example of embedding a template in FreeMarker:

```markup
<#include "${templatesPath}/[template-key]" />
```   

The *Template Key* can be found when editing a previously published template.

![Figure 1: You can find the Template Key when view the Edit page for a template..](../../../../../../images/adt-template-key.png)

