---
header-id: using-liferay-util-get-url
---

# Liferay Util Get URLの使用

[TOC levels=1-4]

get URLタグは、`url`属性によって提供されるURLを取得します。`var`属性に値が指定されている場合、画面スクレイプのコンテンツはその変数にスコープされます。それ以外の場合は、taglibが使用されている場所に取得されたコンテンツが表示されます。

`<liferay-util:get-url>`タグの基本構成を以下に示します。

    <liferay-util:get-url url="https://portal.liferay.dev/" />

`var`属性を使用する例を次に示します。

    <liferay-util:get-url url="https://portal.liferay.dev/" var="ldn" />
    
    <div>
    <h2>We stole <a href="https://portal.liferay.dev/">Liferay Portal</a>, here it is.</h2>
    
    <div class="ldn">
    <%= ldn %>
    </div>
    </div>

![図1：Liferay Util Get URLタグを使用してURLを取得できます。](../../../images/liferay-util-get-url-ldn.png)

これで、`<liferay-util:get-url>`タグを使用してURLを取得する方法がわかりました。

## 関連トピック

[Using the Liferay Util Param Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-param)

[Using the Liferay Util Include Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-include)

[Using the AUI Taglib](/docs/7-1/tutorials/-/knowledge_base/t/using-aui-taglibs-in-your-portlets)
