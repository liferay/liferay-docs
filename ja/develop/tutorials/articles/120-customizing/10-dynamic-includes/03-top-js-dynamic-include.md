---
header-id: top-js-dynamic-include
---

# Top JS動的インクルード

[TOC levels=1-4]

`top_js.jspf`動的インクルードは、テーマの先頭に追加のJavaScriptファイルを追加します。たとえば、次の拡張ポイントを使用して、テーマの先頭に必要なJSライブラリを含めることができます。

    /html/common/themes/top_js.jspf#resources

次の例では、`top_js.jspf`の上部にJavaScriptファイルを挿入します。

`*DynamicInclude` Javaクラス：

    @Component(immediate = true, service = DynamicInclude.class)
    public class JSTopHeadDynamicInclude extends BaseDynamicInclude {
    
    @Override
    public void include(
    HttpServletRequest request, HttpServletResponse response,
    String key)
    throws IOException {
    
    PrintWriter printWriter = response.getWriter();
    
    String content = "<script charset=\"utf-8\" src=\"/o/my-custom-dynamic-include/my_example_javascript.js\" async />";
    
    printWriter.println(content);
    }
    
    @Override
    public void register(
    DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {
    
    dynamicIncludeRegistry.register(
    "/html/common/themes/top_js.jspf#resources"
    );
    }
    }

![図1：Top JS動的インクルードを使用して、テーマの先頭に追加のスクリプトを読み込むことができます。](../../../images/dynamic-include-top-js-example.png)

JavaScript `src`属性の値`/o/my-custom-dynamic-include/...`は、OSGiモジュールの`Web-ContextPath`（例では`/my-custom-dynamic-include`）によって提供されることに注意してください。

これで、`top_js.jspf`動的インクルードの使用方法がわかりました。

## 関連トピック

[Bottom JSP Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/bottom-jsp-dynamic-includes)

[Top Head JSP Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/top-head-jsp-dynamic-includes)

[WYSIWYG Editor Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/wysiwyg-editor-dynamic-includes)
