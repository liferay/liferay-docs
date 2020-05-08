---
header-id: using-the-liferay-ui-taglib-in-your-portlets
---

# Liferay UI Taglibの使用

[TOC levels=1-4]

Liferay UIタグライブラリは、一般的に使用されるUIコンポーネントを実装するタグを提供します。これらのタグにより、マークアップの一貫性、応答性、アクセス性が向上します。

使用可能なLiferay UI taglibのリストは、[Liferay UI taglibdocs](https://docs.liferay.com/portal/7.1-latest/taglibs/util-taglib/liferay-ui/tld-summary.html)で確認できます。
各taglibには、タグに渡すことができる属性のリストがあります。これらの一部は必須で、一部はオプションです。各タグの要件については、taglibdocsを参照してください。[Liferay Github Repo](https://github.com/liferay/liferay-portal/tree/7.1.x/portal-web/docroot/html/taglib/ui)フォルダーのJSPにタグによって生成された完全なマークアップがあります。

アプリでLiferay-UI taglibライブラリを使用するには、JSPに次の宣言を追加する必要があります。

    <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

Liferay-UI taglibは、FreeMarkerのテーマおよびWebコンテンツテンプレート用にマクロ経由でも利用できます。次の構文に従います。

    <@liferay_ui["tag-name"] attribute="string value" attribute=10 />

チュートリアルのこのセクションでは、Liferay UI taglibを使用してUIコンポーネントを作成する方法について説明します。各チュートリアルには、コードの例と、作成されたUIのスクリーンショットが含まれています。
