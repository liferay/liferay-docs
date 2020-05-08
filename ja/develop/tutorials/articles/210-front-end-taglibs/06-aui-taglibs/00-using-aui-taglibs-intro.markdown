---
header-id: using-aui-taglibs-in-your-portlets
---

# AUI Taglibの使用

[TOC levels=1-4]

AUIタグライブラリは、一般的に使用されるUIコンポーネントを実装するタグを提供します。
これらのタグにより、マークアップの一貫性、応答性、アクセス性が向上します。

使用可能な`<aui>` taglibの リストは、[AUI taglibdocs](https://docs.liferay.com/portal/7.1-latest/taglibs/util-taglib/aui/tld-summary.html)で確認できます。
各taglibには、タグに渡すことができる属性のリストがあります。これらの一部は必須で、一部はオプションです。各タグの要件については、taglibdocsを参照してください。[Liferay Github Repo](https://github.com/liferay/liferay-portal/tree/7.1.x/portal-web/docroot/html/taglib/aui)フォルダーのJSPにタグによって生成された完全なマークアップがあります。

アプリでAUI taglibライブラリを使用するには、JSPに次の宣言を追加する必要があります。

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

AUI taglibは、FreeMarkerのテーマテンプレートおよびWebコンテンツテンプレート用にマクロ経由でも利用できます。次の構文に従います。

    <@liferay_aui["tag-name"] attribute="string value" attribute=10 />

チュートリアルのこのセクションでは、AUI taglibを使用してUIコンポーネントを作成する方法について説明します。各チュートリアルには、コードの例と、作成されたUIのスクリーンショットが含まれています。
