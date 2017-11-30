# Using CKEditor Plugins in AlloyEditor [](id=using-ckeditor-plugins-in-alloyeditor)

You can customize an editor's configuration to include several modifications, 
such as 
[adding new buttons](/develop/tutorials/-/knowledge_base/7-0/creating-and-contributing-new-buttons-to-alloyeditor) 
and 
[adding new behaviors](/develop/tutorials/-/knowledge_base/7-0/adding-new-behavior-to-an-editor).
You can also use existing CKEditor plugins in @product@'s AlloyEditor. Several 
of the CKEditor plugins are packaged with @product@'s AlloyEditor, so you can 
use them with just a few configuration adjustments. This tutorial shows how to 
use the CKEditor plugins that are bundled with @product@'s AlloyEditor.

Follow these steps:

1.  Create a module to 
    [modify the AlloyEditor's configuration](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration). 
    The example boilerplate below modifies the AlloyEditor's configuration for 
    the Blogs and Blogs Admin portlets:

        @Component(
          property = {
            "editor.config.key=contentEditor", "editor.name=alloyeditor",
            "editor.name=ckeditor", "javax.portlet.name=" + "com_liferay_blogs_web_portlet_BlogsPortlet",
            "javax.portlet.name=" + "com_liferay_blogs_web_portlet_BlogsAdminPortlet",
            "service.ranking:Integer=101"
          },
          service = EditorConfigContributor.class
        )
        public class MyBlogsEditorConfigContributor
          extends BaseEditorConfigContributor {

          @Override
          public void populateConfigJSONObject(
            JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
            ThemeDisplay themeDisplay,
            RequestBackedPortletURLFactory requestBackedPortletURLFactory) {
              
            }
          }

2.  Add additional plugins to the AlloyEditor via the `extraPlugins` JSON 
    object. To add a CKEditor plugin, first extract the current list of 
    `extraPlugins` from your editor configuration object as a `String`:

        String extraPlugins = jsonObject.getString("extraPlugins");

3.  Choose the plugin(s) you want to use from the  
    [default CKEditor plugins bundled with @product@'s AlloyEditor](/develop/reference/-/knowledge_base/7-0/ckeditor-plugin-reference-guide).

4.  Add the CKEditor plugin(s) you want to use to the `extraPlugins` 
    configuration. @product@'s AlloyEditor also comes with several plugins to 
    bridge the gap between the CKEditor's UI and the AlloyEditor's UI. These are 
    prefixed with `ae_`. We recommend that you include them all to ensure 
    compatibility. The example below checks for existing `extraPlugins` and adds 
    the [`font` CKEditor plugin](https://ckeditor.com/cke4/addon/font)
    along with it's required 
    [Rich Combo plugin](https://ckeditor.com/cke4/addon/richcombo) 
    dependency and the remaining UI bridge plugins:

        if (Validator.isNotNull(extraPlugins)) {
          extraPlugins = extraPlugins + ",ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,
          ae_menubuttonbridge,ae_panelmenubuttonbridge,ae_placeholder,
          ae_richcombobridge,font";
        }
        else {
          extraPlugins = "ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,
          ae_menubuttonbridge,ae_panelmenubuttonbridge,ae_placeholder,
          ae_richcombobridge,font";
        }
        
        jsonObject.put("extraPlugins", extraPlugins);

    +$$$

    **Note:** Make sure the `ae_uibridge` plugin is listed first, followed by 
    the remaining UI bridge plugins, and finally the CKEditor plugin(s).

    $$$

5.  If the plugin includes buttons, add them to the toolbar you wish to display 
    them in. The configuration below retrieves the text selection toolbar's 
    buttons and adds the `font` plugin's `Font` and `FontSize` buttons to it: 

        JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");

        if (toolbarsJSONObject == null) {
         toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
        }

        JSONObject stylesJSONObject = toolbarsJSONObject.getJSONObject(
         "styles");

        if (stylesJSONObject == null) {
         stylesJSONObject = JSONFactoryUtil.createJSONObject();
        }

        JSONArray selectionsJSONArray = stylesJSONObject.getJSONArray(
         "selections");

        for (int i = 0; i < selectionsJSONArray.length(); i++) {
         JSONObject selection = selectionsJSONArray.getJSONObject(i);

        if (Objects.equals(selection.get("name"), "text")) {
         JSONArray buttons = selection.getJSONArray("buttons");

         buttons.put("Font");
         buttons.put("FontSize");
        }
        
+$$$

**Note:** A plugin's buttons may not have the same name as the plugin. You can 
find the button names for a plugin by 
[searching its `plugin.js` file](/develop/reference/-/knowledge_base/7-0/ckeditor-plugin-reference-guide) 
for `editor.ui.addButton`. Note that button names are case sensitive and may be 
aliased in the `addButton()` method, such as the [`clipboard` plugin's](https://github.com/ckeditor/ckeditor-dev/blob/release/4.0.x/plugins/clipboard/plugin.js#L341-L350) `Cut`,`Copy`, and `Paste` buttons.

$$$

Below is the full example `*EditorConfigContributor` class that adds the `font` 
plugin to the AlloyEditor for the Blogs and Blogs Admin portlets:

    @Component(
    	property = {
    		"editor.config.key=contentEditor", "editor.name=alloyeditor",
    		"editor.name=ckeditor", "javax.portlet.name=" + "com_liferay_blogs_web_portlet_BlogsPortlet",
    		"javax.portlet.name=" + "com_liferay_blogs_web_portlet_BlogsAdminPortlet",
    		"service.ranking:Integer=101"
    	},
    	service = EditorConfigContributor.class
    )
    public class MyBlogsEditorConfigContributor
    	extends BaseEditorConfigContributor {

    	@Override
    	public void populateConfigJSONObject(
    		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
    		ThemeDisplay themeDisplay,
    		RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

    		String extraPlugins = jsonObject.getString("extraPlugins");

        if (Validator.isNotNull(extraPlugins)) {
          extraPlugins = extraPlugins + ",ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,
          ae_menubuttonbridge,ae_panelmenubuttonbridge,ae_placeholder,
          ae_richcombobridge,font";
        }
        else {
          extraPlugins = "ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,
          ae_menubuttonbridge,ae_panelmenubuttonbridge,ae_placeholder,
          ae_richcombobridge,font";
        }

    		jsonObject.put("extraPlugins", extraPlugins);

    		JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");

    		if (toolbarsJSONObject == null) {
    			toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
    		}

    		JSONObject stylesJSONObject = toolbarsJSONObject.getJSONObject(
    			"styles");

    		if (stylesJSONObject == null) {
    			stylesJSONObject = JSONFactoryUtil.createJSONObject();
    		}

    		JSONArray selectionsJSONArray = stylesJSONObject.getJSONArray(
    			"selections");

    		for (int i = 0; i < selectionsJSONArray.length(); i++) {
    			JSONObject selection = selectionsJSONArray.getJSONObject(i);

    			if (Objects.equals(selection.get("name"), "text")) {
    				JSONArray buttons = selection.getJSONArray("buttons");

    				buttons.put("Font");
    				buttons.put("FontSize");
    			}
    		}

    		stylesJSONObject.put("selections", selectionsJSONArray);

    		toolbarsJSONObject.put("styles", stylesJSONObject);

    		jsonObject.put("toolbars", toolbarsJSONObject);
    	}

    }

Now you know how to use @product@'s bundled CKEditor plugins in its AlloyEditor!

## Related Topics [](id=related-topics)

[Modifying an Editor's Configuration](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration)

[Creating and Contributing New Buttons to AlloyEditor](/develop/tutorials/-/knowledge_base/7-0/creating-and-contributing-new-buttons-to-alloyeditor)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-0/adding-new-behavior-to-an-editor)
