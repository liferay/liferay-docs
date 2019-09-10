---
header-id: top-js-dynamic-include
---

# Top JS Dynamic Include

[TOC levels=1-4]

The `top_js.jspf` dynamic include adds additional JavaScript files to the 
theme's head. For example, you can use this extension point to include a JS 
library that you need present in the theme's head:

    /html/common/themes/top_js.jspf#resources

The example below injects a JavaScript file into the top of the `top_js.jspf`:

`*DynamicInclude` Java Class:

```java
@Component(immediate = true, service = DynamicInclude.class)
public class JSTopHeadDynamicInclude extends BaseDynamicInclude {

  @Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

    PrintWriter printWriter = response.getWriter();

    String content = "<script charset=\"utf-8\" src=\"/o/my-custom-dynamic-include/my_example_javascript.js\" async />";

    printWriter.println(content);
	}

	@Override
	public void register(
		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {

		dynamicIncludeRegistry.register(
      "/html/common/themes/top_js.jspf#resources"
    );
	}
}
```

Page Source:

```html
<head>
  ...
  <script charset="utf-8" src="/o/my-custom-dynamic-include/my_example_javascript.js" async>...</script>
  ...
</head>
```

Note that the JavaScript `src` attribute's value 
`/o/my-custom-dynamic-include/...` is provided by the OSGi module's 
`Web-ContextPath` (`/my-custom-dynamic-include` in the example). 
 
Now you know how to use the `top_js.jspf` dynamic include. 

## Related Topics

- [Bottom JSP Dynamic Includes](/docs/7-2/customization/-/knowledge_base/c/bottom-jsp-dynamic-includes)
- [Top Head JSP Dynamic Includes](/docs/7-2/customization/-/knowledge_base/c/top-head-jsp-dynamic-includes)
- [WYSIWYG Editor Dynamic Includes](/docs/7-2/customization/-/knowledge_base/c/wysiwyg-editor-dynamic-includes)
