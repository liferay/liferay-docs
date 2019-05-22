---
header-id: using-liferay-util-dynamic-include
---

# Using Liferay Util Dynamic Include

[TOC levels=1-4]

The dynamic include tag lets you specify a point or points in a JSP or theme 
where a developer can inject additional HTML, resources, or functionality, using 
the `DynamicIncludeRegistry`. You can read more about the OSGi Service Registry 
[here](http://docs.spring.io/osgi/docs/current/reference/html/service-registry.html). 
The `key` attribute identifies the extension point. See 
[Dynamic Includes](/docs/7-2/frameworks/-/knowledge_base/f/dynamic-includes) 
for example configurations that use dynamic include extension points to inject 
additional functionality. 

The example configuration below uses the `<liferay-util:dynamic-include>` tag to 
include an extension point before the primary code and an extension point after 
the primary code:

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:dynamic-include key="/path/to/jsp#pre" />

<div>
        <p>And here we have our content</p>
</div>

<liferay-util:dynamic-include key="/path/to/jsp#post" />
```

Now you know how to use the `<liferay-util:dynamic-include>` tag to add 
extension points to your app. 
    
## Related Topics

- [Dynamic Includes](/docs/7-2/frameworks/-/knowledge_base/f/dynamic-includes)
- [Using the Liferay Util Body Top Tag](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-body-top)
- [Using the Chart Taglib](/docs/7-2/reference/-/knowledge_base/r/using-the-chart-taglib-in-your-portlets)
