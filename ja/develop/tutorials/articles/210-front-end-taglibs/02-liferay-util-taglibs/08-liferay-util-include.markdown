---
header-id: using-liferay-util-include
---

# Liferay Util Includeの使用

[TOC levels=1-4]

includeタグを使用すると、ポートレットのJSP、テーマ、またはWebコンテンツに他のJSPファイルを含めることができます。これにより、読みやすくなるだけでなく、JSPファイルに対する懸念事項を分離できます。

`page`属性は必須であり、含めるJSPまたはJSPFへのパスを指定します。`servletContext`は、含まれているJSPが使用する必要のあるリクエストコンテキストを参照します。`<%= application %>`をこの属性に渡すことで、含まれているJSPは、以前のJSPで設定されていた可能性がある他のオブジェクトと同じ`request`オブジェクトを使用できます。

以下は、`<liferay-util:include>`タグの構成例です。

    <liferay-util:include
    page="/relative/path/to/file.jsp"
    servletContext="<%= application %>"
    />

これで、`<liferay-util:include>`タグを使用して、ポートレット、テーマ、およびWebコンテンツに他のJSPを含める方法がわかりました。

## 関連トピック

[Using the Liferay Util Param Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-param)

[Using the Liferay Util Dynamic Include Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-dynamic-include)

[Using the Liferay Front-End Taglibs](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-frontend-taglibs-in-your-portlet)
