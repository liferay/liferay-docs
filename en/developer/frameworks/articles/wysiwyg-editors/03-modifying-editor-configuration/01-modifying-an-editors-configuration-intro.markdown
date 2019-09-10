---
header-id: modifying-an-editors-configuration
---

# Modifying an Editor's Configuration

[TOC levels=1-4]

You can use many different kinds of WYSIWYG editors to edit content in 
portlets. Depending on the content you're editing, you may want to modify the 
editor to provide a customized configuration for your needs. In this article, 
you'll learn how to modify the default configuration for @product@'s supported 
WYSIWYG editors to meet your requirements. 

To modify the editor's configuration, create a module with a component that 
implements the 
[`EditorConfigContributor`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html) 
interface. Follow these steps to modify one of @product@'s WYSIWYG editors:

1.  [Create an OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

2.  Open the portlet's `build.gradle` file and update the 
    `com.liferay.portal.kernel` `version` to `4.13.1`. This is the version 
    bundled with the @product@ release.

3.  Create a unique package name in the module's `src` directory, and create a 
    new Java class in that package that extends the 
    [`BaseEditorConfigContributor`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/BaseEditorConfigContributor.html) 
    class: 

4.  Create a component class that implements the `EditorConfigContributor` 
    service:

    ```java
    @Component(
        property = {

        },

        service = EditorConfigContributor.class
    )
    ```

5.  Add the following imports:

    ```java
    import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
    import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
    import com.liferay.portal.kernel.json.JSONArray;
    import com.liferay.portal.kernel.json.JSONFactoryUtil;
    import com.liferay.portal.kernel.json.JSONObject;
    import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
    import com.liferay.portal.kernel.theme.ThemeDisplay;
    ```

6.  Specify the editor's name, editor's configuration key, and/or the portlet 
    name(s) where the editor resides. These three properties can be specified 
    independently, or together, in any order. See the 
    [`EditorConfigContributor`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html) interface's Javadoc for more information about 
    the available properties and how to use them. The example configuration 
    below modifies the AlloyEditor's Content Editor, identified by the 
    `contentEditor` configuration key and `alloyeditor` name key. 
    
    | **Note:** If you're targeting all editors for a portlet, the
    | `editor.config.key` is not required. For example, if you just want to target
    | the Web Content portlet's editors, you can provide the configuration below:
    | 
    | ```java
    | @Component(
    | property = {"editor.name=ckeditor",
    | "javax.portlet.name=com_liferay_journal_web_portlet_JournalPortlet",
    | "service.ranking:Integer=100"
    | }
    | ```
    
    Two portlet names are declared (Blogs and Blogs Admin), specifying that the 
    service applies to the content editors in those portlets. Lastly, the 
    configuration overrides the default one by providing a higher 
    [service ranking](/docs/7-2/customization/-/knowledge_base/c/creating-a-custom-osgi-service):

    ```java
    @Component(
        property = {
            "editor.config.key=contentEditor", "editor.name=alloyeditor",
            "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsPortlet",
            "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsAdminPortlet", 
            "service.ranking:Integer=100"
        },

        service = EditorConfigContributor.class
    )
    ```

    | **NOTE:** If you want to create a global configuration that applies to an
    | editor everywhere it's used, you must create two separate configurations:
    | one configuration that targets just the editor and a second configuration
    | that targets the Blogs and Blogs Admin portlets. For example, the two
    | separate configurations below apply the updates to AlloyEditor everywhere
    | it's used:
    | 
    | Configuration one:
    | ```java
    | @Component(
    |     immediate = true,
    |     property = {
    |         "editor.name=alloyeditor",
    |         "service.ranking:Integer=100"
    |     },
    | 
    |     service = EditorConfigContributor.class
    | )
    | ```
    | 
    | Configuration two:
    | ```java
    | @Component(
    |     immediate = true,
    |     property = {
    |         "editor.name=alloyeditor",
    |         "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsPortlet",
    |         "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsAdminPortlet",
    |         "service.ranking:Integer=100"
    |     },
    | 
    |     service = EditorConfigContributor.class
    | )
    | ```

7.  Override the `populateConfigJSONObject()` method to provide the custom 
    configuration for the editor. This method updates the original configuration 
    JSON object. It can also Update or delete existing configurations, or any 
    other configuration introduced by another `*EditorConfigContributor`. 

    ```java
    @Override
    public void populateConfigJSONObject(
        JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
        ThemeDisplay themeDisplay,
        RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

    }
    ```

8.  In the `populateConfigJSONObject` method, you must instantiate a 
    [`JSONObject`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/json/JSONObject.html) 
    to hold the current configuration of the editor. For instance, you could 
    use the code snippet below to retrieve the available toolbars for the 
    editor:

    ```java
    JSONObject toolbars = jsonObject.getJSONObject("toolbars");
    ```

    | **Note:** This toolbar configuration is only applicable for the AlloyEditor.
    | If you choose a configuration that is supported by multiple editors, you
    | could apply it to them all. To do this, you could specify all the editors
    | (e.g., `"editor.name=alloyeditor"`, `"editor.name=ckeditor"`,
    | `ckeditor_bbcode` etc.) in the `@Component` annotation  of your
    | `EditorConfigContributor` implementation, as you did in step six. Use the
    | links the bottom of this article to view each editor's configuration
    | options and requirements.

9.  Now that you've retrieved the toolbar, you can modify it. The example below 
    adds a camera button to the AlloyEditor's Add toolbar. It extracts the *Add* 
    buttons out of the toolbar configuration object as 
    a 
    [`JSONArray`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/json/JSONArray.html), 
    and then adds the button to that `JSONArray`:

    ```java
    if (toolbars != null) {
        JSONObject toolbarAdd = toolbars.getJSONObject("add");

        if (toolbarAdd != null) {
            JSONArray addButtons = toolbarAdd.getJSONArray("buttons");

            addButtons.put("camera");
        }
    }
    ```

    The configuration JSON object is passed to the editor with the modifications 
    you've implemented in the `populateConfigJSONObject` method.

10.  Finally, generate the module's JAR file and copy it to your `deploy`
     folder. Once the module is installed and activated in the service
     registry, your new editor configuration is available for use. 

@product@ supports several different types of WYSIWYG editors, which include 
(among others):

- [AlloyEditor](https://alloyeditor.com/api/1.5.0/Core.html)
- [CKEditor](http://docs.ckeditor.com/#!/api/CKEDITOR.config)
- [TinyMCE](http://www.tinymce.com/wiki.php/Configuration)

Make sure to visit each editor's configuration API to learn what each editor 
offers for configuration settings. 

## Related Topics

- [Adding New Behavior to an Editor](/docs/7-2/frameworks/-/knowledge_base/f/adding-new-behavior-to-an-editor)
- [Modifying the AlloyEditor](/docs/7-2/frameworks/-/knowledge_base/f/alloyeditor)
- [Adding a WYSIWYG Editor to a Portlet](/docs/7-2/frameworks/-/knowledge_base/f/adding-a-wysiwyg-editor-to-a-portlet)
