---
header-id: using-liferay-util-html-bottom
---

# Using Liferay Util HTML Bottom

[TOC levels=1-4]

The HTML bottom tag is not a self-closing tag. Content placed between the 
opening and closing of this tag is moved to the bottom of the `<html>` tag. When 
something is passed using this taglib, the 
[bottom.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/bottom.jsp#L53-L59) 
is passed markup and outputs in this JSP. 

This tag also has an optional `outputKey` attribute. If several portlets 
on the page include the same resource with this tag, you can specify the same 
`outputKey` value for each tag so the resource is only loaded once. 

The example configuration below uses the `<liferay-util:html-bottom>` tag to 
include JavaScript (a common use case) provided by the portlet's bundle:

```html
<liferay-util:html-bottom outputKey="htmlbottom">

    <script src="/o/my-liferay-util-portlet/js/my_custom_javascript.js" 
    type="text/javascript"></script>

</liferay-util:html-bottom>
```

Now you know how to use the `<liferay-util:html-bottom>` tag to include 
additional resources in the bottom of the page's HTML tag. 

## Related Topics

- [Using the Liferay Util HTML Body Bottom Tag](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-body-bottom)
- [Using the Liferay Util HTML Top Tag](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-html-top)
- [Using the Liferay UI Taglib](/docs/7-2/reference/-/knowledge_base/r/using-the-liferay-ui-taglib-in-your-portlets)
