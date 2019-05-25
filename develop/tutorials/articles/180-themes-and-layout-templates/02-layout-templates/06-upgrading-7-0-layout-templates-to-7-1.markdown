---
header-id: upgrading-7-0-layout-templates-to-7-1
---

# Upgrading 7.0 Layout Templates to 7.1

[TOC levels=1-4]

If you're upgrading your @product@ 7.0 layout template to @product-ver@, you 
must upgrade your layout template version to 7.1: 

1.  Open your layout template's `liferay-plugin-package.properties` file.

2.  Update the `liferay-versions` property to `7.1.0+`:

```properties
liferay-versions=7.1.0+
```

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
