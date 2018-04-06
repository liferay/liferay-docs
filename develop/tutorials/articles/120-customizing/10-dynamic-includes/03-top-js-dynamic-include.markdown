# Top JS Dynamic Include [](id=top-js-dynamic-include)

The `top_js.jspf` dynamic include adds additional JavaScript files to the
theme's head. For example, you can use this extension point to include a JS
library that you need present in the theme's head: 

    /html/common/themes/top_js.jspf#resources

The example below injects a JavaScript file into the top of the `top_js.jspf`:

`*DynamicInclude` Java Class:

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

![Figure 1: The Top JS dynamic include lets you load additional scripts in the theme's head.](../../../images/dynamic-include-top-js-example.png)

Note that the JavaScript `src` attribute's value 
`/o/my-custom-dynamic-include/...` is provided by the OSGi module's 
`Web-ContextPath` (`/my-custom-dynamic-include` in the example).
 
Now you know how to use the `top_js.jspf` dynamic include.

## Related Topics [](id=related-topics)

[Bottom JSP Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/bottom-jsp-dynamic-includes)

[Top Head JSP Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/top-head-jsp-dynamic-includes)

[WYSIWYG Editor Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/wysiwyg-editor-dynamic-includes)
