# Adding a WYSIWYG Editor to a Portlet [](id=adding-a-wysiwyg-editor-to-a-portlet)

It's easy to include WYSIWYG editors in your portlet, thanks to the 
`<liferay-ui:input-editor />` tag. Below is an example configuration:

    <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
    
    <div class="alloy-editor-container">
        <liferay-ui:input-editor
            contents="Default Content"
            cssClass="my-alloy-editor"
            editorName="alloyeditor"
            name="myAlloyEditor"
            placeholder="description"
            showSource="true" />
    </div>

It is also possible to pass JavaScript functions through the `onBlurMethod`, 
`onChangeMethod`, `onFocusMethod`, and `onInitMethod` attributes. Here is an 
example configuration that uses the `onInitMethod` attribute to pass
a JavaScript function called `OnDescriptionEditorInit`:

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
    <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
    
    <div class="alloy-editor-container">
        <liferay-ui:input-editor
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
                document.getElementById('<portlet:namespace />myAlloyEditor').setAttribute('contenteditable', false);
            </c:if>
        }
    </aui:script>

Below is an overview of the main attributes of the `<liferay-ui:input-editor />` 
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

See the [taglibdocs](https://docs.liferay.com/portal/7.0-latest/taglibs/util-taglib/liferay-ui/input-editor.html) 
for the complete list of supported attributes.

As you can see, it's easy to include WYSIWYG editors in your portlets! 

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-0/adding-new-behavior-to-an-editor)

[Modifying an Editor's Configuration](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration)

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-0/using-the-liferay-ui-taglib)
