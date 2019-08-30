---
header-id: top-head-jsp-dynamic-includes
---

# Top Head JSP Dynamic Includes

[TOC levels=1-4]

The `top_head.jsp` dynamic includes load additional links in the theme's head. 
It uses the following keys: 

Load additional links in the theme's head before the existing ones:

    /html/common/themes/top_head.jsp#pre

Alternatively, you can load additional links in the theme's head, after the 
existing ones:

    /html/common/themes/top_head.jsp#post

The example below injects a link into the top of the `top_head.jsp`:

```java
@Component(immediate = true, service = DynamicInclude.class)
public class CssTopHeadDynamicInclude extends BaseDynamicInclude {

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

		PrintWriter printWriter = response.getWriter();

		String content = 
    "<link href=\"http://localhost:8080/o/my-custom-dynamic-include/css/mentions.css\" 
    rel=\"stylesheet\" 
    type = \"text/css\" />";
    
		printWriter.println(content);
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register("/html/common/themes/top_head.jsp#pre");
	}
  
}
```

Page Source:

```html
<head>
  ...
  <link href="http://localhost:8080/o/my-custom-dynamic-include/css/mentions.css" rel="stylesheet" type="text/css">
  ...
</head>
```

Note that the link's `href` attribute's value 
`/o/my-custom-dynamic-include/` is provided by the OSGi module's 
`Web-ContextPath` (`/my-custom-dynamic-include` in the example). 

Now you know how to use the `top_head.jsp` dynamic includes. 

## Related Topics

- [Bottom JSP Dynamic Includes](/docs/7-2/customization/-/knowledge_base/c/bottom-jsp-dynamic-includes)
- [Top JS Dynamic Include](/docs/7-2/customization/-/knowledge_base/c/top-js-dynamic-include)
- [WYSIWYG Editor Dynamic Includes](/docs/7-2/customization/-/knowledge_base/c/wysiwyg-editor-dynamic-includes)
