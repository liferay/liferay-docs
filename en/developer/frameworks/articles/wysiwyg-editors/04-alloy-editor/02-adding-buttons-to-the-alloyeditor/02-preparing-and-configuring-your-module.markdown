---
header-id: creating-the-osgi-module-and-configuring-the-editorconfigcontributor
---

# Creating the OSGi Module and Configuring the EditorConfigContributor Class

[TOC levels=1-4]

To add a button to the AlloyEditor's toolbars, you must first create an OSGi 
component class of service type `EditorConfigContributor.class`. Follow these 
steps to create and configure the OSGi module:

1.  [Create an OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project), 
    using 
    [Blade's portlet template](/docs/7-2/reference/-/knowledge_base/r/using-the-mvc-portlet-template):

    ```bash
    blade create -t portlet -p com.liferay.docs.my.button -c 
    MyEditorConfigContributor my-new-button
    ```

2.  Open the portlet's `build.gradle` file and update the 
    `com.liferay.portal.kernel` `version` to `4.13.1`. This is the version 
    bundled with the @product@ release.

3.  Open the portlet class you created in step one (`MyEditorConfigContributor`) 
    and add the following imports:

    ```java
    import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
    import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
    import com.liferay.portal.kernel.json.JSONArray;
    import com.liferay.portal.kernel.json.JSONFactoryUtil;
    import com.liferay.portal.kernel.json.JSONObject;
    import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
    import com.liferay.portal.kernel.theme.ThemeDisplay;
    ```

4.  Replace the `@Component` and properties with the properties below:

    ```java
    @Component(
        immediate = true,
        property = {
          "editor.name=alloyeditor",
          "service.ranking:Integer=100"
        },
        service = EditorConfigContributor.class  
    )
    ```

    This targets AlloyEditor for the configuration and overrides the default 
    service by providing a higher 
    [service ranking](/docs/7-2/customization/-/knowledge_base/c/creating-a-custom-osgi-service). 
    If you want to target a more specific configuration, you can find the 
    available properties in the 
    [`EditorConfigContributor` interface's Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html).

5.  Extend `BaseEditorConfigContributor` instead of `GenericPortlet`.

6.  Replace the `doView()` method and contents with the 
    `populateConfigJSONObject()` method shown below:

    ```java
    @Override
    public void populateConfigJSONObject(
    JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
    ThemeDisplay themeDisplay,
    RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

    }
    ```

7.  Inside the `populateConfigJSONObject()` method, retrieve the AlloyEditor's 
    toolbars: 

    ```java
    JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");

    if (toolbarsJSONObject == null) {
            toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
    }
    ```

8.  If you're adding a button for one of the 
    [CKEditor plugins bundled with the AlloyEditor](/docs/7-2/reference/-/knowledge_base/r/ckeditor-plugin-reference-guide), 
    add the code below to retrieve the extra plugins and add the plugin to the 
    AlloyEditor's configuration. The example below adds the `clipboard` CKEditor 
    plugin:

    ```java
    String extraPlugins = jsonObject.getString("extraPlugins");

    if (Validator.isNotNull(extraPlugins)) {
      extraPlugins = extraPlugins + ",ae_uibridge,ae_autolink,
      ae_buttonbridge,ae_menubridge,ae_panelmenubuttonbridge,ae_placeholder,
      ae_richcombobridge,clipboard";
    }
    else {
      extraPlugins = "ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,
      ae_panelmenubuttonbridge,ae_placeholder,ae_richcombobridge,clipboard";
    }

    jsonObject.put("extraPlugins", extraPlugins);
    ```

    AlloyEditor also comes with several plugins to bridge the gap between the 
    CKEditor's UI and the AlloyEditor's UI. These are prefixed with the `ae_` 
    you see above. We recommend that you include them all to ensure 
    compatibility. 

The `*EditorConfigContributor` class is prepared. Now you must choose which 
toolbar you want to add the button(s) to: the 
[Add Toolbar](/docs/7-2/frameworks/-/knowledge_base/f/adding-a-button-to-the-add-toolbar) 
or one of the 
[Styles Toolbars](/docs/7-2/frameworks/-/knowledge_base/f/adding-a-button-to-a-styles-toolbar). 

## Related Topics

- [Adding New Behavior to an Editor](/docs/7-2/frameworks/-/knowledge_base/f/adding-new-behavior-to-an-editor)
- [CKEditor Plugin Reference Guide](/docs/7-2/reference/-/knowledge_base/r/ckeditor-plugin-reference-guide)
