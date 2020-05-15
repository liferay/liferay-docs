---
header-id: using-liferay-util-body-top
---

# Liferay Util Body Topの使用

[TOC levels=1-4]

body topタグは自己終了タグではありません。このタグの開始と終了の間に配置されたコンテンツは、`body`タグの上部に移動されます。このtaglibを使用して渡されたコンテンツがあると、[body_top.jsp](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-web/docroot/html/common/themes/body_top.jsp#L25-L31)が マークアップに渡され、このJSPで出力されます。

このタグにはオプションの`outputKey` 属性もあります。ページ上の複数のポートレットにこのタグを持つ同じリソースが含まれている場合は、各タグに同じ`outputKey`値を指定して、リソースが一度だけロードされるようにすることができます 。

以下の構成例では、 `<liferay-util:body-top>`タグを使用して、ポートレットのバンドルによって提供されるJavaScriptを含めています。

    <liferay-util:body-top outputKey="bodytop" >
    <script
    src="/o/my-liferay-util-portlet/js/my_custom_javascript_body_top.js"
    type="text/javascript"></script>
    </liferay-util:body-top>

これで、`<liferay-util:body-top>`タグを使用してページの本文の上部に追加のリソースを含める方法がわかりました。

## 関連トピック

[Using the Liferay Util HTML Body Bottom Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-body-bottom)

[Using the Liferay Util HTML Bottom Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-html-bottom)

[Using the Clay Taglib](/docs/7-1/tutorials/-/knowledge_base/t/using-the-clay-taglib-in-your-portlets)
