---
header-id: using-liferay-util-whitespace-remover
---

# Using Liferay Util Whitespace Remover

[TOC levels=1-4]

The whitespace remover tag removes line breaks and tabs from code blocks 
included between the opening and closing of the tag. Below is an example 
configuration for the `<liferay-util:whitespace-remover>` tag:

with remover:

```html
<liferay-util:whitespace-remover>
	<p>Here is some text with        tabs.</p>
</liferay-util:whitespace-remover>
```

result:

```html
Here is some text withtabs.
```
Now you know how to use the `<liferay-util:whitespace-remover>` tag to ensure 
that your code formatting is consistent. 

## Related Topics

- [Using the Liferay Util Param Tag](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-param)
- [Using the Liferay Util Buffer Tag](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-buffer)
- [Using the AUI Taglib](/docs/7-2/reference/-/knowledge_base/r/using-aui-taglibs-in-your-portlets)
