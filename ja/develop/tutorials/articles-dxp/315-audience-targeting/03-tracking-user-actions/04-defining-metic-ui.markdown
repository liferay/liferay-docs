---
header-id: defining-the-metrics-ui
---

# メトリックのUIの定義

[TOC levels=1-4]

ここまでに追加したJavaコードは、ニュースレターのメトリックに3つの構成可能なフィールドがあると想定しています。

  - *名前：* は、メトリックがトリガーされた回数をカウントするレポートで使用されます。 これは、ニュースレターのエイリアスとも呼ばれます。
  - *ニュースレターID：* はニュースレターを区別するために使用されます。
  - *イベントタイプ：* は、ニュースレターを開く、リンクをクリックするなど、同じニュースレターのいくつかのアクションを区別するために使用されます。

管理者がこれらの値を設定できるようにするには、UIを定義して、ビュー/保存のライフサイクル中にメトリックを構成できるようにする必要があります。 生成された透明画像のURLを表示するためのフィールドも定義する必要があることに注意してください。 メトリックのモジュール（たとえば、 `/src/main/resources/META-INF/resources/view.jsp`）に `view.jsp` ファイルを作成し、次のロジックを追加します。

``` markup
<%
Map<String, Object> context = (Map<String, Object>)request.getAttribute("context");

String alias = (String)context.get("alias");
String elementId = (String)context.get("elementId");
String eventType = (String)context.get("eventType");
List<String> eventTypes = (List<String>)context.get("eventTypes");
String trackImageHTML = (String)context.get("trackImageHTML");
%>

<aui:input helpMessage="name-help" label="name" name='<%= ContentTargetingUtil.GUID_REPLACEMENT + "alias" %>' type="text" value="<%= alias %>">
    <aui:validator name="required" />
</aui:input>

<aui:input helpMessage="enter-the-id-of-the-newsletter-to-be-tracked" label="newsletter-id" name='<%= ContentTargetingUtil.GUID_REPLACEMENT + "elementId" %>' type="text" value="<%= elementId %>">
    <aui:validator name="required" />
</aui:input>

<c:if test="<%= ListUtil.isNotEmpty(eventTypes) %>">
    <aui:select label="event-type" name='<%= ContentTargetingUtil.GUID_REPLACEMENT + "eventType" %>'>

        <%
        for (String curEventType : eventTypes) {
        %>

            <aui:option label="<%= curEventType %>" selected="<%= curEventType.equals(eventType) %>" value="<%= curEventType %>" />

        <%
        }
        %>

    </aui:select>
</c:if>

<c:if test="<%= !Validator.isBlank(trackImageHTML) %>">
    <span class="h5">
        <liferay-ui:message key="paste-this-code-at-the-beginning-of-your-newsletter" />
    </span>
    <label for='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' key="paste-this-code-at-the-beginning-of-your-newsletter" /></label>

    <liferay-ui:input-resource id='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' url="<%= trackImageHTML %>" />
</c:if>
```

まず、Javaクラスの `PopulateContext` メソッドで構成した `コンテキスト` 変数とその属性をインスタンス化します。 次に、適切なフィールドの名前、ニュースレターID、およびイベントタイプを指定します。 最後に、生成された透明な画像のURLを提示します。

JSPの入力フィールド名の前に `ContentTargetingUtil.GUID_REPLACEMENT`が付いていることに注意してください。 このプレフィックスは複数インスタンス化可能なメトリックに必要です。これは、 `-TrackingAction` クラスの `isInstantiable` メソッドで `true` を返すメトリックであり、メトリックフォームに複数回追加できます。

![図1：メトリックを保存したら、生成された透過画像のURLをニュースレターのHTMLにコピーして、誰がそれを表示したかを追跡できます。](../../../images-dxp/metric-generated-url.png)

おめでとう\！ ニュースレターのメトリックを作成しました。これで、ユーザーがニュースレターを表示したかどうかを追跡できます。 生成された追跡画像HTMLをメールのHTMLエディターにコピーして送信し、実際のニュースレターのようにそれを開くことで、メトリックが機能しているかどうかをテストできます。 次に、ニュースレターのメトリックを含むカスタムレポートを開き、[ *Update Report*を選択します。 ニュースレターの閲覧数を示すグラフと表が表示されます。

ニュースレターメトリックの完成版は、 [ZIPファイル](https://portal.liferay.dev/documents/113763090/114000653/newsletter-7-1.zip/47e5b490-dd9d-9201-8abd-09b534ff7507?t=1565967901027)ダウンロードして表示できます。

これで、完全に機能するメトリックを作成して検証し、独自のメトリックを作成するための知識を得ました。
