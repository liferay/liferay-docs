---
header-id: using-liferay-util-body-bottom
---

# Liferay Util Body Bottomの使用

[TOC levels=1-4]

body bottomタグは自己終了タグではありません。このタグを使用することで、`body`タグの下部に追加のHTMLまたはスクリプトを追加できます。このタグの開始と終了の間に配置されたコンテンツは[body_bottom.jsp](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-web/docroot/html/common/themes/body_bottom.jsp#L26-L31)に渡され、 このJSPで出力されます。

このタグにはオプションの`outputKey` 属性もあります。ページ上の複数のポートレットにこのタグを持つ同じリソースが含まれている場合は、各タグに同じ`outputKey`値を指定して、リソースが一度だけロードされるようにすることができます。

以下の構成例では、 `<liferay-util:body-bottom>`タグを使用して、ポートレットのバンドルによって提供されるJavaScriptを含めています。

    <liferay-util:body-bottom outputKey="bodybottom" >
    <script
    src="/o/my-liferay-util-portlet/js/my_custom_javascript_body_bottom.js"
    type="text/javascript"></script>
    </liferay-util:body-bottom>

これで、`<liferay-util:body-bottom>`タグを使用してページの本文の下部に追加のリソースを含める方法がわかりました。

## 関連トピック

[Using the Liferay Util HTML Body Top Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-body-top)

[Using the Liferay Util HTML Top Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-html-top)

[Using the Liferay UI Taglib](/docs/7-1/tutorials/-/knowledge_base/t/using-the-liferay-ui-taglib-in-your-portlets)
