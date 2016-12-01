# Adding a WYSIWYG Editor to a Custom Portlet [](id=adding-a-wysiwyg-editor-to-a-custom-portlet)

Using a wysiwyg editor in your portlet can be easily achieved using the `<liferay-ui:input-editor />` tag. Here is an example:

```html
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
```

It is also possible to pass in some javascript functions through `onBlurMethod`, `onChangeMethod`, `onFocusMethod`, and `onInitMethod`. Here is an example of using one of thse methods:

```html
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
```

As you can see Liferay has made it easy to make use of the new AlloyEditor. Below is an overview of main attributes of the `<liferay-ui:input-editor />` tag:

| Attribute | Type | Description |
| --- | --- | --- |
| autoCreate | `java.lang.String` | A string boolean to determine whether or not to show HTML edit view of editor  initially |
| contents | `java.lang.String` | Initial contents of the editor |
| contentsLanguageId | `java.lang.String` | The ID of a language for the input editor's text. |
| cssClass | `java.lang.String` | A CSS class for styling the component. |
| data | `java.util.Map` | data that can be used as the editorConfig |
| editorName | `java.lang.String` | The editor you want to use (alloyeditor, ckeditor, tinymce, simple) |
| name | `java.lang.String` | A name for the input editor. The default value is `editor`. |
| onBlurMethod | `java.lang.String` | A function to be called when the input editor loses focus. |
| onChangeMethod | `java.lang.String` | A function to be called on a change in the input editor. |
| onFocusMethod | `java.lang.String` | A function to be called when the input editor gets focus. |
| onInitMethod | `java.lang.String` | A function to be called when the input editor initializes. |
| placeholder | `java.lang.String` | Placeholder text to display in the input editor. |
| showSource | `java.lang.String` | Whether to enable editing the HTML source code of the content. The default value  is `true`. |

See the [tag reference docs](https://docs.liferay.com/portal/7.0-latest/taglibs/util-taglib/liferay-ui/input-editor.html) for the complete list of supported attributes.