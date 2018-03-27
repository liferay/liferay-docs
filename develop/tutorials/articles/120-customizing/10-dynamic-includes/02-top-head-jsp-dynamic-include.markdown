# Top Head JSP Dynamic Includes

The `top_head.jsp` dynamic includes let you load additional links in the theme's 
head. The following keys are available:

Load additional links in the theme's head, before the existing ones:

    /html/common/themes/top_head.jsp#pre

Alternatively, you can load additional links in the theme's head, after the 
existing ones:

    /html/common/themes/top_head.jsp#post

The example below injects a link into the top of the `top_head.jsp`:

    @Component(immediate = true, service = DynamicInclude.class)
    public class CssTopHeadDynamicInclude extends BaseDynamicInclude {

    	@Override
    	public void include(
    			HttpServletRequest request, HttpServletResponse response,
    			String key)
    		throws IOException {

    		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
    			WebKeys.THEME_DISPLAY);

    		PrintWriter printWriter = response.getWriter();

    		String content = "<link href="...mentions.css" />";

    		printWriter.println(content);
    	}

    	@Override
    	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
    		dynamicIncludeRegistry.register("/html/common/themes/top_head.jsp#pre");
    	}

    }
    
![Figure 1: The top_head pre key loads additional CSS and HTML resources in the head of the theme.](../../../images/dynamic-include-top-head-pre-mentions-css.png)    

Now you know how to use the `top_head.jsp` dynamic includes.

## Related Topics

[Bottom JSP Dynamic Includes](develop/tutorials/-/knowledge_base/7-1/bottom-jsp-dynamic-includes)

[Top JS Dynamic Includes](develop/tutorials/-/knowledge_base/7-1/top-js-dynamic-includes)

[WYSIWYG Editor Dynamic Includes](develop/tutorials/-/knowledge_base/7-1/wysiwyg-editor-dynamic-includes)
