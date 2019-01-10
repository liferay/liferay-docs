# Adding a WYSIWYG Editor to a Portlet [](id=adding-a-wysiwyg-editor-to-a-portlet)

It's easy to include WYSIWYG editors in your portlet, thanks to the 
`<liferay-editor:editor />` tag. While you can alternatively use the 
`<liferay-ui:input-editor />` tag, it is deprecated as of @product-ver@ in favor 
of the `<liferay-editor:editor />` tag, so we recommend that you use the 
`<liferay-editor:editor />` tag to avoid future issues. 

Below is an example configuration:

    <%@ taglib uri="http://liferay.com/tld/editor" prefix="liferay-editor" %>
    
    <div class="alloy-editor-container">
        <liferay-editor:editor
        		contents="Default Content"
        		cssClass="my-alloy-editor"
        		editorName="alloyeditor"
        		name="myAlloyEditor"
        		placeholder="description"
        		showSource="true" 
        /> 
    </div>

It is also possible to pass JavaScript functions through the `onBlurMethod`, 
`onChangeMethod`, `onFocusMethod`, and `onInitMethod` attributes. Here is an 
example configuration that uses the `onInitMethod` attribute to pass
a JavaScript function called `OnDescriptionEditorInit`:

    <%@ taglib uri="http://liferay.com/tld/editor" prefix="liferay-editor" %>
    
    <div class="alloy-editor-container">
        <liferay-editor:editor
            contents="Default Content"
            cssClass="my-alloy-editor"
            editorName="alloyeditor"
            name="myAlloyEditor"
            onInitMethod="OnDescriptionEditorInit"
            placeholder="description"
            showSource="true" />
    </div>
 
    <aui:script>
        function <portlet:namespace />OnDescriptionEditorInit() {
            <c:if test="<%= !customAbstract %>">
                document.getElementById(
                  '<portlet:namespace />myAlloyEditor'
                ).setAttribute('contenteditable', false);
            </c:if>
        }
    </aui:script>

Below is an overview of the main attributes of the `<liferay-editor:editor />` 
tag:

| Attribute | Type | Description |
| --- | --- | --- |
| autoCreate | `java.lang.String` | Whether to show the HTML edit view of the editor initially |
| contents | `java.lang.String` | Sets the initial contents of the editor |
| contentsLanguageId | `java.lang.String` | Sets the language ID for the input editor's text |
| cssClass | `java.lang.String` | A CSS class for styling the component. |
| data | `java.util.Map` | Data that can be used as the editorConfig |
| editorName | `java.lang.String` | The editor you want to use (alloyeditor, ckeditor, tinymce, simple) |
| name | `java.lang.String` | A name for the input editor. The default value is `editor`. |
| onBlurMethod | `java.lang.String` | A function to be called when the input editor loses focus. |
| onChangeMethod | `java.lang.String` | A function to be called on a change in the input editor. |
| onFocusMethod | `java.lang.String` | A function to be called when the input editor gets focus. |
| onInitMethod | `java.lang.String` | A function to be called when the input editor initializes. |
| placeholder | `java.lang.String` | Placeholder text to display in the input editor. |
| showSource | `java.lang.String` | Whether to enable editing the HTML source code of the content. The default value  is `true`. |

See the 
[taglibdocs](@app-ref@/frontend-editor/latest/taglibdocs/liferay-editor/editor.html) 
for the complete list of supported attributes. 

As you can see, it's easy to include WYSIWYG editors in your portlets! 

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor)

[Modifying an Editor's Configuration](/develop/tutorials/-/knowledge_base/7-1/modifying-an-editors-configuration)

[Modifying the AlloyEditor](/develop/tutorials/-/knowledge_base/7-1/alloyeditor)