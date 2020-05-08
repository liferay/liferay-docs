---
header-id: using-liferay-util-html-bottom
---

# Liferay Util HTML Bottomの使用

[TOC levels=1-4]

HTML bottomタグは自己終了タグではありません。このタグの開始と終了の間に配置されたコンテンツは、`<html>`タグの下部に移動されます。このtaglibを使用して渡されたコンテンツがあると、[bottom.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/bottom.jsp#L53-L59)が マークアップに渡され、このJSPで出力されます。

このタグにはオプションの`outputKey` 属性もあります。ページ上の複数のポートレットにこのタグを持つ同じリソースが含まれている場合は、各タグに同じ`outputKey`値を指定して、リソースが一度だけロードされるようにすることができます。

以下の構成例では、`<liferay-util:html-bottom>`タグを使用して、ポートレットのバンドルによって提供されるJavaScript（一般的なユースケース）を含めています。

    <liferay-util:html-bottom outputKey="htmlbottom">
    
    <script src="/o/my-liferay-util-portlet/js/my_custom_javascript.js"
    type="text/javascript"></script>
    
    </liferay-util:html-bottom>

これで、`<liferay-util:html-bottom>`タグを使用してページのHTMLタグの下部に追加のリソースを含める方法がわかりました。

## 関連トピック

[Using the Liferay Util HTML Body Bottom Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-body-bottom)

[Using the Liferay Util HTML Top Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-html-top)

[Using the Liferay UI Taglib](/docs/7-1/tutorials/-/knowledge_base/t/using-the-liferay-ui-taglib-in-your-portlets)
