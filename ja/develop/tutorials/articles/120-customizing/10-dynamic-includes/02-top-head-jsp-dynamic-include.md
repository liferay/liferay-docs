---
header-id: top-head-jsp-dynamic-includes
---

# Top Head JSP動的インクルード

[TOC levels=1-4]

`top_head.jsp`動的インクルードは、テーマの先頭に追加のリンクを読み込みます。
次のキーが使用されます。

テーマの先頭の既存のリンクの前に追加のリンクを読み込みます。

    /html/common/themes/top_head.jsp#pre

または、テーマの先頭の既存のリンクの後に追加のリンクを読み込むこともできます。

    /html/common/themes/top_head.jsp#post

次の例では、`top_head.jsp`の上部にリンクを挿入します。

    @Component(immediate = true, service = DynamicInclude.class)
    public class CssTopHeadDynamicInclude extends BaseDynamicInclude {
    
    @Override
    public void include(
    HttpServletRequest request, HttpServletResponse response,
    String key)
    throws IOException {
    
    PrintWriter printWriter = response.getWriter();
    
    String content =
    "<link href=\"http://localhost:8080/o/my-custom-dynamic-include/css/mentions.css\"
    rel=\"stylesheet\"
    type = \"text/css\" />";
    
    printWriter.println(content);
    }
    
    @Override
    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
    dynamicIncludeRegistry.register("/html/common/themes/top_head.jsp#pre");
    }
    
    }

![図1：top_head preキーは、テーマの先頭に追加のCSSおよびHTMLリソースを読み込みます。](../../../images/dynamic-include-top-head-pre-mentions-css.png)

リンクの`href`属性の値`/o/my-custom-dynamic-include/`は、OSGiモジュールの`Web-ContextPath`（例では`/my-custom-dynamic-include`）によって提供されることに注意してください。

これで、`top_head.jsp`動的インクルードの使用方法がわかりました。

## 関連トピック

[Bottom JSP Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/bottom-jsp-dynamic-includes)

[Top JS Dynamic Include](/docs/7-1/tutorials/-/knowledge_base/t/top-js-dynamic-include)

[WYSIWYG Editor Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/wysiwyg-editor-dynamic-includes)
