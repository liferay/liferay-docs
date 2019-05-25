---
header-id: upgrading-6-2-layout-templates-to-7-1
---

# Upgrading 6.2 Layout Templates to 7.1

[TOC levels=1-4]

Upgrading your @product@ 6.2 layout template to @product-ver@ a few updates:

1.  Open your layout template's `liferay-plugin-package.properties` file and 
    update the `liferay-versions` property to `7.1.0+`:

```properties
liferay-versions=7.1.0+
```

2.  Update the Bootstrap `span[number]` classes to use the newer 
    `col-[size]-[number]` classes. See [Creating Layout Templates Manually](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-manually) 
    for more information on Bootstrap's grid system. 

3.  Save the changes.

| **Note:** Velocity layout templates are supported, but deprecated as of
| @product-ver@. We recommend that you convert your Velocity layout templates to
| FreeMarker at your earliest convenience. See
| [Creating Layout Templates Manually](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-manually#anatomy)
| for an example of the updated syntax.

## Related Topics

[Layout Templates with the Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-with-the-themes-generator)

[Creating Layout Templates Manually](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-manually)

[Including Layout Templates with a Theme](/docs/7-1/tutorials/-/knowledge_base/t/including-layout-templates-with-a-theme)
