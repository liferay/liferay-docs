---
header-id: using-liferay-frontend-taglibs-in-your-portlet
---

# ポートレットでLiferayフロントエンドTaglibを使用する

[TOC levels=1-4]

Liferayフロントエンドタグライブラリは、アプリで一般的なフロントエンドUIコンポーネントを作成するための一連のタグを提供します。

アプリでフロントエンドtaglibを使用するには、JSPに次の宣言を追加します。

    <%@ taglib prefix="liferay-frontend" uri="http://liferay.com/tld/frontend" %>

Liferayフロントエンドtaglibは、FreeMarkerのテーマテンプレートおよびWebコンテンツテンプレート用にマクロ経由でも利用できます。次の構文に従います。

    <@liferay_frontend["tag-name"] attribute="string value" attribute=10 />

このチュートリアルのセクションでは、次のフロントエンドUIコンポーネントについて説明します。

- [メニューの追加](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-add-menu)
- [カード](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-cards)
- [情報バー](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-info-bar)
- [管理バー](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-management-bar)

このセクションのチュートリアルでは、フロントエンドtaglibを使用してこれらのコンポーネントを作成する方法について説明します。各チュートリアルには、事例集と、作成されたUIのスクリーンショットが含まれています。
