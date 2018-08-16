# Adding a Button to a Styles Toolbar [](id=adding-a-button-to-a-styles-toolbar)

A Styles Toolbar appears when content is selected or highlighted in AlloyEditor. 
There are five Styles toolbars to choose from:

`embedurl`: Appears when embedded content is selected. 

![Figure 1: The embed URL Styles toolbar lets you format embedded content in the editor.](../../../../images/alloyeditor-embedurl-toolbar.png)

`image`: Appears when an image is selected.

![Figure 2: The image Styles toolbar lets you format images in the editor.](../../../../images/alloyeditor-image-toolbar.png)

`link`: Appears when a hyperlink is selected.

![Figure 3: The link Styles toolbar lets you format hyperlinks in the editor.](../../../../images/alloyeditor-link-toolbar.png)

`table`: Appears when a table is selected.

![Figure 4: The table Styles toolbar lets you format tables in the editor.](../../../../images/alloyeditor-table-toolbar.png)

`text`: Appears when text is highlighted.

![Figure 5: The text Styles toolbar lets you format highlighted text in the editor.](../../../../images/alloyeditor-text-toolbar.png)

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

    The example above adds one of the 
    [CKEditor plugins bundled with @product@'s AlloyEditor](/develop/reference/-/knowledge_base/7-1/ckeditor-plugin-reference-guide). 
    There are also several buttons available by default with the AlloyEditor,
    but they are not all enabled. The full list of existing buttons you can add
    to the Styles toolbars is shown in the table below, ordered by Toolbar:
    
    | text | table | image | link |
    | ---- | ----- | ----- | ---- |
    | bold | tableHeading | imageCenter | linkEdit |
    | code | tableRow | imageLeft | |
    | h1 | tableColumn | imageRight | |
    | h2 | tableCell | | |
    | indentBlock | tableRemove | | |
    | italic | | | |
    | link | | | |
    | ol | | | |
    | outdentBlock | | | |
    | paragraphLeft | | | |
    | paragraphRight | | | |
    | paragraphCenter | | | |
    | paragraphJustify | | | |
    | quote | | | |
    | removeFormat | | | |
    | strike | | | |
    | styles | | | |
    | subscript | | | |
    | superscript | | | |
    | twitter | | | |
    | ul | | | |
    | underline | | | |
    
    See 
    [https://alloyeditor.com/docs/features/](https://alloyeditor.com/docs/features/) 
    for an explanation of each button's features.

4.  Update the AlloyEditor's configuration with the changes you made:

        stylesToolbar.put("selections", selectionsJSONArray);

        toolbarsJSONObject.put("styles", stylesToolbar);

        jsonObject.put("toolbars", toolbarsJSONObject);

5.  [Deploy your module](/develop/tutorials/-/knowledge_base/7-1/deploying-projects-with-blade-cli) 
    and create a new piece of content that uses the AlloyEditor---such as a blog 
    entry or web content article---to see your new configuration in action!

The `com.liferay.docs.my.button` module's updated text styles toolbar is shown 
in the figure below:

![Figure 6: The Updated text styles toolbar lets you copy, cut, and paste text in the editor.](../../../../images/alloyeditor-updated-styles-toolbar.png)

## Related Topics [](id=related-topics)

[Adding a Button to the Add Toolbar](/develop/tutorials/-/knowledge_base/7-1/adding-a-button-to-the-add-toolbar)

[CKEditor Plugin Reference Guide](/develop/reference/-/knowledge_base/7-1/ckeditor-plugin-reference-guide)
