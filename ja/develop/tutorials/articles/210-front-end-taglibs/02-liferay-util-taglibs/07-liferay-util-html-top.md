---
header-id: using-liferay-util-html-top
---

# Liferay Util HTML Topの使用

[TOC levels=1-4]

HTML topタグは自己終了タグではありません。このタグの開始と終了の間に配置されたコンテンツは、`<head>`タグに移動されます。このtaglibを使用して渡されたコンテンツがあると、[top_head.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/top_head.jsp#L147-L153)が マークアップに渡され、このJSPで出力されます。

このタグにはオプションの`outputKey` 属性もあります。ページ上の複数のポートレットにこのタグを持つ同じリソースが含まれている場合は、各タグに同じ`outputKey`値を指定して、リソースが一度だけロードされるようにすることができます。

以下の構成例では、`<liferay-util:html-top>`タグを使用して、ポートレットのバンドルによって提供される追加のCSSスタイルを含めています。

    <liferay-util:html-top outputKey="htmltop">
    <link data-senna-track="permanent"
    href="/o/my-liferay-util-portlet/css/my-custom-styles.css"
    rel="stylesheet" type="text/css" />
    </liferay-util:html-top>

これで、`<liferay-util:html-top>`タグを使用してページのHTMLタグの上部に追加のリソースを含める方法がわかりました。

## 関連トピック

[Using the Liferay Util HTML Bottom Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-html-bottom)

[Using the Liferay Util Body Top Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-body-top)

[Using the Clay Taglib](/docs/7-1/tutorials/-/knowledge_base/t/using-the-clay-taglib-in-your-portlets)
