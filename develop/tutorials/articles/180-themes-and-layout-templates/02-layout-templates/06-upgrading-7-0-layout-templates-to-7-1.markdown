# Upgrading 7.0 Layout Templates to 7.1 [](id=upgrading-7-0-layout-templates-to-7-1)

If you're upgrading your @product@ 7.0 layout template to @product-ver@, you 
just have to upgrade your layout template version to 7.1. Follow these steps:

1.  Open your layout template's `liferay-plugin-package.properties` file.

2.  Update the `liferay-versions` property to `7.1.0+`:

```properties
liferay-versions=7.1.0+
```

3.  Save the changes.

| **Note:** Velocity layout templates are supported, but deprecated as of 
| @product-ver@. We recommend that you convert your Velocity layout templates to 
| FreeMarker at your earliest convenience. See 
| [Creating Layout Templates Manually](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-manually#anatomy) 
| for an example of the updated syntax.

## Related Topics [](id=related-topics)

[Layout Templates with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-with-the-themes-generator)

[Creating Layout Templates Manually](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-manually)

[Including Layout Templates with a Theme](/develop/tutorials/-/knowledge_base/7-1/including-layout-templates-with-a-theme)