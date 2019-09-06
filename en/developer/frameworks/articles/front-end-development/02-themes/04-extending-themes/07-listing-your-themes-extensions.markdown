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
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme).

| **Note:** Gulp is included as a local dependency in generated themes, so you 
| are not required to install it. It can be accessed by running 
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's 
| root folder.

1.  Navigate to your theme's root folder.

2.  Run `gulp status` to print your theme's current extensions to the command 
    line.

Your theme's current extensions are also found under the `baseTheme` and 
`themeletDependencies` headings in your theme's `package.json`.

![Figure 1: Run the `gulp status` task to list your theme's current extensions.](../../../../images/theme-ext-listing-theme-extensions.png)

## Related Topics

- [Changing Your Base Theme](/docs/7-2/frameworks/-/knowledge_base/f/changing-your-base-theme)
- [Configuring Your Theme's App Server](/docs/7-2/frameworks/-/knowledge_base/f/updating-your-themes-app-server)
- [Generating Themelets with the Theme Generator](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator)
