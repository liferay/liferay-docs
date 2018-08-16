# Adding a Button to the Add Toolbar [](id=adding-a-button-to-the-add-toolbar)

The Add Toolbar appears in the AlloyEditor upon clicking in the editor and 
clicking the Add button: 

![Figure 1: The Add toolbar lets you add content to the editor.](../../../../images/alloyeditor-add-toolbar.png)

Follow these steps to add a button to the AlloyEditor's Add Toolbar:
    
1.  Inside the `populateConfigJSONObject()` method, retrieve the Add Toolbar:
    
        JSONObject addToolbar = toolbarsJSONObject.getJSONObject("add");
    
2.  Retrieve the existing Add Toolbar buttons:
    
        JSONArray addToolbarButtons = addToolbar.getJSONArray("buttons");
        
3.  Add the button to the existing buttons. Note that the button's name is case 
    sensitive. The example below adds the `camera` button to the Add Toolbar:
    
        addToolbarButtons.put("camera");
        
    The camera button is just one of the buttons available by default with
    AlloyEditor, however, they are not all enabled. Here's the full list of
    available buttons you can add to the Add Toolbar: 
    
    - camera
    - embed
    - hline
    - image
    - table
    
    See 
    [https://alloyeditor.com/docs/features/](https://alloyeditor.com/docs/features/) 
    for an explanation of each button's features.

4.  Update the AlloyEditor's configuration with the changes you made:

        addToolbar.put("buttons", addToolbarButtons);

        toolbarsJSONObject.put("add", addToolbar);

        jsonObject.put("toolbars", toolbarsJSONObject);

5.  [Deploy your module](/develop/tutorials/-/knowledge_base/7-1/deploying-projects-with-blade-cli) 
    and create new content that uses the AlloyEditor---like a blog entry or
    web content article---to see your new configuration in action!

The `com.liferay.docs.my.button` module's updated Add Toolbar is shown in the 
figure below:

![Figure 2: The Updated Add toolbar lets you add pictures from a camera directly to the editor.](../../../../images/alloyeditor-updated-add-toolbar.png)

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor)

[Adding a Button to a Styles Toolbar](/develop/reference/-/knowledge_base/7-1//develop/tutorials/-/knowledge_base/7-1/adding-a-button-to-a-styles-toolbar)
