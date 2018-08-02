# Adding Buttons to AlloyEditor's Toolbars [](id=adding-buttons-to-alloyeditor-toolbars)

AlloyEditor's toolbars contains several useful functions out-of-the-box. You can 
format your text, add links and embedded videos, and much more. You may, 
however, require more features for your editor. Thanks to the 
[`EditorConfigContributor` interface](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html), 
you can easily modify the default AlloyEditor configuration. Whether you want to 
add a custom button you've created or an 
[existing CKEditor button that's bundled with @product@'s AlloyEditor](/develop/reference/-/knowledge_base/7-1/ckeditor-plugin-reference-guide), 
the `EditorConfigContributor` provides everything you need to add buttons to 
AlloyEditor's toolbars. 

The `com.liferay.docs.my.button` module is used as an example throughout this 
tutorial. If you want to use it as a starting point for your own custom 
configuration or follow along with this tutorial, you can download the module's 
zip file from the 
[Github repo](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/com.liferay.docs.my.button).

Get started by creating and configuring the OSGi module and 
`*EditorConfigContributor` class.

## Creating the OSGi Module and Configuring the EditorConfigContributor [](id=creating-the-osgi-module-and-configuring-the-editorconfigcontributor)

Follow these steps to add a button to the AlloyEditor:

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module), 
    using 
    [Blade's portlet template](/develop/reference/-/knowledge_base/7-1/using-the-portlet-template) 
    for example:

        blade create -t portlet -p com.liferay.docs.my.button -c 
        MyEditorConfigContributor my-new-button

2.  Open the portlet's `build.gradle` file and update the 
    `com.liferay.portal.kernel` `version` to `3.6.2`. This is the version 
    bundled with the @product@ release.

3.  Open the `*EditorConfigContributor` class and add the following imports:
    
        import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
        import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
        import com.liferay.portal.kernel.json.JSONArray;
        import com.liferay.portal.kernel.json.JSONFactoryUtil;
        import com.liferay.portal.kernel.json.JSONObject;
        import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
        import com.liferay.portal.kernel.theme.ThemeDisplay;

4.  Replace the `@Component` and properties with the properties below:

        @Component(
            immediate = true,
            property = {
              "editor.name=alloyeditor",
              "service.ranking:Integer=100"
            },
            service = EditorConfigContributor.class  
        )

    This targets AlloyEditor for the configuration and overrides the default 
    service by providing a higher 
    [service ranking](/develop/tutorials/-/knowledge_base/7-1/fundamentals#services). 
    If you would like to target a more specific configuration, you can find the 
    available properties in the 
    [`EditorConfigContributor` interface's javadoc](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html).

5.  Extend `BaseEditorConfigContributor` instead of `GenericPortlet`.

6.  Replace the `doView()` method and contents with the 
    `populateConfigJSONObject()` method shown below:
    
        @Override
        public void populateConfigJSONObject(
				JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
				ThemeDisplay themeDisplay,
				RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

        }

7.  Inside the `populateConfigJSONObject()` method, retrieve the AlloyEditor's 
    toolbars: 

        JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");

        if (toolbarsJSONObject == null) {
                toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
        }
        
8.  If you're adding a button for one of the 
    [CKEditor plugins bundled with the AlloyEditor](/develop/reference/-/knowledge_base/7-1/ckeditor-plugin-reference-guide), 
    add the code below to retrieve the extra plugins and add the plugin to the 
    AlloyEditor's configuration. The example below adds the `clipboard` CKEditor 
    plugin:
    
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
        
    @product@'s AlloyEditor also comes with several plugins to bridge the gap 
    between the CKEditor's UI and the AlloyEditor's UI. These are prefixed with 
    the `ae_` you see above. We recommend that you include them all to ensure 
    compatibility. 

The `*EditorConfigContributor` class is prepared. Now you must choose which 
toolbar you want to add the button to: The Add toolbar or one of the Styles 
toolbars. 

## Adding a Button to the Add Toolbar [](id=adding-a-button-to-the-add-toolbar)

The Add toolbar appears in the AlloyEditor upon clicking in the editor and 
clicking the Add button: 

![Figure 1: The Add toolbar lets you add content to the editor.](../../../images/alloyeditor-add-toolbar.png)

Follow these steps to add a button to the AlloyEditor's Add toolbar:
    
1.  Inside the `populateConfigJSONObject()` method, retrieve the Add toolbar:
    
        JSONObject addToolbar = toolbarsJSONObject.getJSONObject("add");
    
2.  Retrieve the existing Add toolbar buttons:
    
        JSONArray addToolbarButtons = addToolbar.getJSONArray("buttons");
        
3.  Add the button to the existing buttons. Note that the button's name is case 
    sensitive. The example below adds the `camera` button to the Add toolbar:
    
        addToolbarButtons.put("camera");

4.  Update the AlloyEditor's configuration with the changes you made:

        addToolbar.put("buttons", addToolbarButtons);

        toolbarsJSONObject.put("add", addToolbar);

        jsonObject.put("toolbars", toolbarsJSONObject);

5.  [Deploy your module](/develop/tutorials/-/knowledge_base/7-1/deploying-projects-with-blade-cli) 
    and create a new piece of content that uses the AlloyEditor---such as a blog 
    entry or web content article---to see your new configuration in action!

The `com.liferay.docs.my.button` module's updated Add toolbar is shown in the 
figure below:

![Figure 2: The Updated Add toolbar lets you add pictures from a camera directly to the editor.](../../../images/alloyeditor-updated-add-toolbar.png)

Next you can learn how to add buttons to the Styles toolbars. 

## Adding a Button to the Styles Toolbars [](id=adding-a-button-to-the-styles-toolbars)

The Styles toolbars appear when content is selected or highlighted in 
AlloyEditor. There are five Styles toolbars to choose from:

- `embedurl`: Appears when embedded content is selected 

![Figure 3: The embed URL Styles toolbar lets you format embedded content in the editor.](../../../images/alloyeditor-embedurl-toolbar.png)

- `image`: Appears when an image is selected

![Figure 4: The image Styles toolbar lets you format images in the editor.](../../../images/alloyeditor-image-toolbar.png)

- `link`: Appears when a hyperlink is selected

![Figure 5: The link Styles toolbar lets you format hyperlinks in the editor.](../../../images/alloyeditor-link-toolbar.png)

- `table`: Appears when a table is selected

![Figure 6: The table Styles toolbar lets you format tables in the editor.](../../../images/alloyeditor-table-toolbar.png)

- `text`: Appears when text is highlighted

![Figure 7: The text Styles toolbar lets you format highlighted text in the editor.](../../../images/alloyeditor-text-toolbar.png)

Follow these steps to add a button to one of the Styles toolbars:

1.  Inside the `populateConfigJSONObject()` method, retrieve the Styles toolbar:
    
        JSONObject stylesToolbar = toolbarsJSONObject.getJSONObject("styles");

        if (stylesToolbar == null) {
                stylesToolbar = JSONFactoryUtil.createJSONObject();
        }

2.  Retrieve the available selection toolbars:

        JSONArray selectionsJSONArray = stylesToolbar.getJSONArray(
        "selections");

3.  Iterate through the selection toolbars and select the one you want to add 
    the button(s) to (`embedurl`, `image`, `link`, `table`, or `text`), retrieve 
    the existing buttons, and add your button. The example below adds the 
    `clipboard` plugin's `Copy`, `Cut`, and `Paste` buttons to the `text` 
    selection toolbar. Note that buttons are case sensitive and may be aliased 
    or not match the name of the plugin. Search the plugin's 
    [`plugin.js` file](/develop/reference/-/knowledge_base/7-1/ckeditor-plugin-reference-guide) 
    for `editor.ui.addButton` to find the button's name:

        for (int i = 0; i < selectionsJSONArray.length(); i++) {
                JSONObject selection = selectionsJSONArray.getJSONObject(i);

                if (Objects.equals(selection.get("name"), "text")) {
                        JSONArray buttons = selection.getJSONArray("buttons");

                        buttons.put("Copy");
                        buttons.put("Cut");
                        buttons.put("Paste");
                }
        }

4.  Update the AlloyEditor's configuration with the changes you made:

        stylesToolbar.put("selections", selectionsJSONArray);

        toolbarsJSONObject.put("styles", stylesToolbar);

        jsonObject.put("toolbars", toolbarsJSONObject);

5.  [Deploy your module](/develop/tutorials/-/knowledge_base/7-1/deploying-projects-with-blade-cli) 
    and create a new piece of content that uses the AlloyEditor---such as a blog 
    entry or web content article---to see your new configuration in action!

The `com.liferay.docs.my.button` module's updated text styles toolbar is shown 
in the figure below:

![Figure 8: The Updated text styles toolbar lets you copy, cut, and paste text in the editor.](../../../images/alloyeditor-updated-styles-toolbar.png)

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor)

[CKEditor Plugin Reference Guide](/develop/reference/-/knowledge_base/7-1/ckeditor-plugin-reference-guide)