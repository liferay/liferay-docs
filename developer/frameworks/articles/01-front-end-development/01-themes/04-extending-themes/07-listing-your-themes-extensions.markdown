---
header-id: listing-your-themes-extensions
---

# Listing Your Theme's Extensions

[TOC levels=1-4]

Do you need to know what base theme/themelet(s) your theme extends? Follow these 
steps to list your theme's extensions with the Status task. Note that this task 
only works for themes that use the 
[liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the 
[Liferay Theme Generator](/developer/reference/-/knowledge_base/7-2/installing-the-theme-generator-and-creating-a-theme).

1.  Navigate to your theme's root folder.

2.  Run `gulp status` to print your theme's current extensions to the command 
    line.

Your theme's current extensions are also found under the `baseTheme` and 
`themeletDependencies` headings in your theme's `package.json`.

![Figure 1: Run the `gulp status` task to list your theme's current extensions.](../../../../images/theme-ext-listing-theme-extensions.png)

## Related Topics

- [Changing Your Base Theme](/developer/frameworks/-/knowledge_base/7-2/changing-your-base-theme)
- [Configuring Your Theme's App Server](/developer/frameworks/-/knowledge_base/7-2/configuring-your-themes-app-server)
- [Generating Themelets](/developer/frameworks/-/knowledge_base/7-2/creating-themelets-with-the-themes-generator)
