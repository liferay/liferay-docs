---
header-id: using-liferay-util-dynamic-include
---

# Liferay Util Dynamic Includeの使用

[TOC levels=1-4]

dynamic includeタグを使用すると、開発者が`DynamicIncludeRegistry`を使用して追加のHTML、リソース、または機能を挿入できるJSPまたはテーマ内のポイントを指定できます。OSGiサービスレジストリの詳細については、[こちら](http://docs.spring.io/osgi/docs/current/reference/html/service-registry.html)をご覧ください。
`key`属性は、拡張ポイントを識別します。Dynamic Include拡張ポイントを使用して追加機能を挿入する構成例については、[Dynamic Include](/docs/7-1/tutorials/-/knowledge_base/t/dynamic-includes)チュートリアルのセクションを参照してください。

以下の構成例では、`<liferay-util:dynamic-include>`タグを使用して、プライマリコードの前と後にそれぞれ拡張ポイントを含めています。

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
    
    <liferay-util:dynamic-include key="/path/to/jsp#pre" />
    
    <div>
    <p>And here we have our content</p>
    </div>
    
    <liferay-util:dynamic-include key="/path/to/jsp#post" />

これで、`<liferay-util:dynamic-include>`タグを使用してアプリに拡張ポイントを追加する方法がわかりました。

## 関連トピック

[Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/dynamic-includes)

[Using the Liferay Util Body Top Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-body-top)

[Using the Chart Taglib](/docs/7-1/tutorials/-/knowledge_base/t/using-the-chart-taglib-in-your-portlets)
