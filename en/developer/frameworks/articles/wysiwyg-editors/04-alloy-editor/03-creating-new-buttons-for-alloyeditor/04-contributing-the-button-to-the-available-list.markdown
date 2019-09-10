---
header-id: contributing-the-button-to-alloyeditor
---

# Contributing the Button to AlloyEditor

[TOC levels=1-4]

Once you've created your button, you can add it to the list of available 
buttons. This can be achieved thanks to some smartly placed 
`<liferay-util:dynamic-include />` tags in the editor's infrastructure. To make 
your button available in the AlloyEditor, you must extend the 
[`BaseDynamicInclude` class](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/BaseDynamicInclude.html). 
Below is an example configuration that extends this class:

1.  Create a Component class that implements the `DynamicInclude.class` service 
    and extends `BaseDynamicInclude`:

    ```java
    @Component(immediate = true, service = DynamicInclude.class)
    public class MyButtonDynamicInclude extends BaseDynamicInclude {
    ```

2.  Override the `include()` method to include a script with your transpiled JSX 
    file. You can use the `StringBundler` to concatenate the script. Note the 
    `sb.append("/js/buttons.js")` line below. This is the `bundleFileName` you 
    defined in your 
    [bundle's `build.gradle` `transpileJS` task](/docs/7-2/frameworks/-/knowledge_base/f/creating-the-alloyeditor-buttons-osgi-bundle):

    ```java
    @Override
    public void include(
                    HttpServletRequest request, HttpServletResponse response,
                    String key)
            throws IOException {

            ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                    WebKeys.THEME_DISPLAY);

            PrintWriter printWriter = response.getWriter();

            StringBundler sb = new StringBundler(7);

            sb.append("<script src=\"");
            sb.append(themeDisplay.getPortalURL());
            sb.append(PortalUtil.getPathProxy());
            sb.append(_servletContext.getContextPath());
            sb.append("/js/buttons.js");
            sb.append("\" ");
            sb.append("type=\"text/javascript\"></script>");

            printWriter.println(sb.toString());
    }
    ```

3.  Override the `register()` method to use the `additionalResources` dynamic 
    include to add your script. Note the `@Reference` annotation's `target` 
    value is your bundle's symbolic name defined in its `bnd.bnd` file:

    ```java
    @Override
    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
            dynamicIncludeRegistry.register(
                    "com.liferay.frontend.editor.alloyeditor.web#alloyeditor#" +
                            "additionalResources");
    }

    @Reference(
            target = "(osgi.web.symbolicname=com.liferay.frontend.editor.alloyeditor.my.button.web)"
    )
    private ServletContext _servletContext;
    }
    ```

Now that your button is included, you can follow the steps covered in 
[Adding Buttons to the AlloyEditor's Toolbars](/docs/7-2/frameworks/-/knowledge_base/f/adding-buttons-to-alloyeditor-toolbars) 
tutorials to add the button to the editor's toolbars. 

## Related Topics

- [Adding New Behavior to an Editor](/docs/7-2/frameworks/-/knowledge_base/f/adding-new-behavior-to-an-editor)
- [Creating the Button's JSX File](/docs/7-2/frameworks/-/knowledge_base/f/creating-the-alloyeditor-buttons-jsx-file)
- [WYSIWYG Editor Dynamic Includes](/docs/7-2/customization/-/knowledge_base/c/wysiwyg-editor-dynamic-includes)
