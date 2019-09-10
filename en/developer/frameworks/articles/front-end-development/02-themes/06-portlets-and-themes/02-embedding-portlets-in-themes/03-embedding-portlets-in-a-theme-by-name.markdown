---
header-id: embedding-a-portlet-by-portlet-name
---

# Embedding a Portlet by Portlet Name

[TOC levels=1-4]

If you'd like to embed a specific portlet in the theme, you can hard code it by 
providing its instance ID and name. Follow these steps:

1.  Open the FreeMarker theme template that you want to embed the portlet in. 
    (`portal_normal.ftl` is a good choice.

2.  Add the `liferay_portlet["runtime"]` macro to the template, as shown below. 
    The portlet name **must** be the same as `javax.portlet.name`'s value.

    ```markup
    <@liferay_portlet["runtime"]
        instanceId="INSTANCE_ID"
        portletName="PORTLET_NAME"
    />
    ```

| **Note:** If your portlet is instanceable, an instance ID must be provided;
| otherwise, you can remove this line. To set your portlet to non-instanceable,
| set the property `com.liferay.portlet.instanceable` in the component annotation
| of your portlet to `false`.
 
Here's an example of an embedded portlet declaration that uses the portlet name 
to embed a web content portlet:

```markup
<@liferay_portlet["runtime"]
    portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
/>
```

Great! Now you know how to embed a portlet in your theme's by their name and ID. 

## Related Topics

- [Embedding Portlets in Themes by Entity Type and Action](/docs/7-2/frameworks/-/knowledge_base/f/embedding-portlets-in-themes-by-entity-type-and-action)
- [Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)
- [Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
