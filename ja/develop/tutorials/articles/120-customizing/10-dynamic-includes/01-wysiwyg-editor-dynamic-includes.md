---
header-id: wysiwyg-editor-dynamic-includes
---

# WYSIWYGエディターの動的インクルード

[TOC levels=1-4]

すべてのWYSIWYGエディターは、次の操作について同じ動的インクルードの拡張ポイントを共有します。

- エディターへのリソース、プラグインなどの追加：

   com.liferay.frontend.editor.`editorType`.web#`editorName`#additionalResources

- エディターインスタンスへのアクセスによるイベントのリッスン、設定など：

   com.liferay.frontend.editor.`editorType`.web#`editorName`#onEditorCreate

以下の表に、各エディターの`editorType`、変数、および`editorName`を示します。

| editorType | 変数 | editorName |
:---------: | :--------------: | :---------: |
| alloyeditor | alloyEditor | alloyeditor |
| &nbsp; | &nbsp; | alloyeditor_bbcode |
| &nbsp; | &nbsp; | alloyeditor_creole |
| ckeditor | ckEditor | ckeditor |
| &nbsp; | &nbsp; | ckeditor_bbcode |
| &nbsp; | &nbsp; | ckeditor_creole |
| tinymce | tinyMCEEditor | tinymce |
| &nbsp; | &nbsp; | tinymce_simple |

次の例では、ユーザがコンテンツをCKEditorに貼り付けると警告が表示されます。

`*DynamicInclude` Javaクラス：

    @Component(immediate = true, service = DynamicInclude.class)
    public class CKEditorOnEditorCreateDynamicInclude implements DynamicInclude {
    
    @Override
    public void include(
    HttpServletRequest request, HttpServletResponse response,
    String key)
    throws IOException {
    
    Bundle bundle = _bundleContext.getBundle();
    
    URL entryURL = bundle.getEntry(
    "/META-INF/resources/ckeditor/extension/ckeditor_alert.js");
    
    StreamUtil.transfer(
    entryURL.openStream(), response.getOutputStream(), false);
    }
    
    @Override
    public void register(
    DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {
    
    dynamicIncludeRegistry.register(
    "com.liferay.frontend.editor.ckeditor.web#ckeditor#onEditorCreate");
    }
    
    @Activate
    protected void activate(BundleContext bundleContext) {
    _bundleContext = bundleContext;
    }
    
    private BundleContext _bundleContext;
    
    }


JavaScriptの例：

    // ckEditor variable is already available in the execution context
    ckEditor.on(
    'paste',
    function(event) {
    event.stop();
    
    alert('Please, do not paste code here!');
    }
    );

これで、WYSIWYGエディターの動的インクルードの使用方法がわかりました。

## 関連トピック

[Adding New Behavior to an Editor](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

[Bottom JSP Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/bottom-jsp-dynamic-includes)

[Top Head JSP Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/top-head-jsp-dynamic-includes)

[Top JS Dynamic Include](/docs/7-1/tutorials/-/knowledge_base/t/top-js-dynamic-include)
