---
header-id: defining-the-rules-ui
---

# ルールのUIの定義

[TOC levels=1-4]

ここまでに追加したJavaコードは、評価プロセス中に比較するために事前設定された気象値が利用可能であると想定しています。 管理者がその値を設定できるようにするには、UIを定義して、ビューのライフサイクル中にルールを構成できるようにする必要があります。 ルールのモジュール（たとえば、 `/src/main/resources/META-INF/resources/view.jsp`）に `view.jsp` ファイルを作成し、次のロジックを追加します。

``` markup
<%
Map<String, Object> context = (Map<String, Object>)request.getAttribute("context");

String weather = (String)context.get("weather");
%>

<aui:fieldset>
    <aui:select name="weather" value="<%= weather %>">
        <aui:option label="sunny" value="sunny" />
        <aui:option label="clouds" value="clouds" />
        <aui:option label="snow" value="snow" />
        <aui:option label="rain" value="rain" />
    </aui:select>
</aui:fieldset>
```

`コンテキスト` マップの `天気` 変数は、天気ルールに設定する必要があります。 ユーザーがオプションを選択すると、それはビューテンプレートから `PopulateContext` メソッドに渡されます。

![図1：気象ルールは <code>select</code> ドロップダウンボックスを使用して、気象値を設定します。](../../../images-dxp/select-box-rule.png)

| **注：** 気象ルールはJSPテンプレートを使用して、ルールのビューを表示します。 |ただし、オーディエンスターゲティングは、どのUIテクノロジーとも互換性があります。 |をご覧ください [UIテクノロジーの選択](/develop/tutorial/-/knowledge_base/7-1/best-practices-for-audience-targeting#selecting-a-ui-technology) | FreeMarkerなどの他のUIテクノロジーの使用方法の詳細については、セクションを参照してください。

これで気象ルールが作成され、気象条件に基づいてユーザーをターゲティングできます。 [ZIPファイル](https://portal.liferay.dev/documents/113763090/114000653/weather-7-1.zip/c1865f26-c4df-f4fa-a012-459e23d3b3c8?t=1565967914487)ダウンロードして、天気ルールの完成版を表示できます。

これで、完全に機能するルールを作成して検討し、独自のルールを作成するための知識を身に付けました。
