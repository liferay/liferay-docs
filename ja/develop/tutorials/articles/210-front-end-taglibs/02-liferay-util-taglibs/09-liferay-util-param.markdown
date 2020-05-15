---
header-id: using-liferay-util-param
---

# Liferay Util Paramの使用

[TOC levels=1-4]

paramタグを使用すると、[含まれているJSPページ](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-include)にパラメータを設定できます 。
この構成には2つのJSPが必要です。アプリのメインビューであるJSP AにはJSP Bが含まれており、そのパラメーター値が設定されます。これにより、JSPを含めるときにコンテンツを動的に設定できます。

たとえば、`my-app.jsp`にメインの機能があり、`more-content.jsp`によって追加の機能が提供されるとします。以下に示す構成が考えられます。

`more-content.jsp`:

    <%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
    
    <%
    String answer = ParamUtil.getString(request, "answer");
    %>
    
    <div>
    <p>The answer to life, the universe and everything is <%= answer %>.</p>
    </div>

次に、`my-app.jsp`で、`more-content.jsp`を含め、`answer`パラメーターの値を設定します。

    <liferay-util:include page="/path/to/more-content.jsp" servletContext="<%= application %>">
    <liferay-util:param name="answer" value="42" />
    </liferay-util:include>


これにより、`my-app.jsp`で次の出力が得られます。

    The answer to life, the universe and everything is 42.

これで、`<liferay-util:param>`タグを使用して、含まれているJSPにパラメーターを設定する方法がわかりました。このアプローチを使用して、アプリに一般的なコードの再利用可能なピースを含めることができます。

## 関連トピック

[Using the Liferay Util Include Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-include)

[Using the Liferay Util Body Top Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-body-top)

[Using the Chart Taglib](/docs/7-1/tutorials/-/knowledge_base/t/using-the-chart-taglib-in-your-portlets)
