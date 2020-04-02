---
header-id: using-the-clay-taglib-in-your-portlets
---

# ポートレットでClay Taglibを使用する

[TOC levels=1-4]

Liferay Clayタグライブラリは、 アプリで[Clay](https://claycss.com/docs/clay/) UIコンポーネントを作成するための一連のタグを提供します。

| **注：** AUI taglibは、@product-ver@では推奨されていません。将来の互換性の問題を回避するために、Clay taglibを使用することを推奨しています。

アプリでClay taglibを使用するには、JSPに次の宣言を追加します。

    <%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>

Liferay Clay taglibは、FreeMarkerのテーマテンプレートおよびWebコンテンツテンプレート用にマクロ経由でも利用できます。次の構文に従います。

    <@clay["tag-name"] attribute="string value" attribute=10 />

Clay taglibsは、アプリに次のUIコンポーネントを提供します。

- [アラート](/docs/7-1/tutorials/-/knowledge_base/t/clay-alerts)
- [バッジ](/docs/7-1/tutorials/-/knowledge_base/t/clay-badges)
- [ボタン](/docs/7-1/tutorials/-/knowledge_base/t/clay-buttons)
- [カード](/docs/7-1/tutorials/-/knowledge_base/t/clay-cards)
- [ドロップダウンメニューとアクションメニュー](/docs/7-1/tutorials/-/knowledge_base/t/clay-dropdown-menus-and-action-menus)
- [フォーム要素](/docs/7-1/tutorials/-/knowledge_base/t/clay-form-elements)
- [アイコン](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)
- [ラベルとリンク](/docs/7-1/tutorials/-/knowledge_base/t/clay-labels-and-links)
- [管理ツールバー](/docs/7-1/tutorials/-/knowledge_base/t/clay-management-toolbar)
- [ナビゲーションバー](/docs/7-1/tutorials/-/knowledge_base/t/clay-navigation-bars)
- [進行状況バー](/docs/7-1/tutorials/-/knowledge_base/t/clay-progress-bars)
- [ステッカー](/docs/7-1/tutorials/-/knowledge_base/t/clay-stickers)

このセクションのチュートリアルでは、Clay taglibを使用してこれらのコンポーネントを作成する方法について説明します。各チュートリアルには、clayコンポーネントの事例集と、作成されたUIのスクリーンショットが含まれています。
