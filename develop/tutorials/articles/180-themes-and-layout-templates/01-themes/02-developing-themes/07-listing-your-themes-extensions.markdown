---
header-id: listing-your-themes-extensions
---

# Listing Your Theme's Extensions

[TOC levels=1-4]

Do you need to know what base theme/themelets your theme extends? There's a gulp 
task for that. While you can manually check your theme's `package.json` for this 
information, the `gulp status` task displays this information for you. Navigate 
to your theme's root folder and run `gulp status` to display your theme's 
extensions.

![Figure 1: Run the `gulp status` task to list your theme's current extensions.](../../../../images/theme-dev-listing-theme-extensions.png)

| **Note:** Gulp is included as a local dependency in generated themes, so you
| are not required to install it. It can be accessed by running
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's
| root folder.

## Related Topics

[Changing Your Base Theme](/docs/7-1/tutorials/-/knowledge_base/t/changing-your-base-theme)

[Configuring Your Theme's App Server](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-themes-app-server)

[Creating Reusable Pieces of Code for Your Themes](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)
