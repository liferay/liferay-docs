---
header-id: liferay-frontend-info-bar
---

# Liferayフロントエンド情報バー

[TOC levels=1-4]

情報バーには、追加のサイドバー情報の表示を切り替えるボタンがあります。これは、ファイルサイズ、タイプ、URLなど、検索結果のより詳細なメタデータを提供するのに最適です。

![図1：情報バータグは、追加情報を表示するサイドバーパネルトグラーを作成します。](../../../images/liferay-frontend-taglib-info-bar-article.png)

構成には、情報バー（およびボタン）とサイドバーパネルの2つの主要な部分があります。

情報バー：

    <liferay-frontend:info-bar>
    <liferay-frontend:info-bar-buttons>
    <liferay-frontend:info-bar-sidenav-toggler-button
    icon="info-circle"
    label="my info"
    />
    </liferay-frontend:info-bar-buttons>
    </liferay-frontend:info-bar>

`<liferay-frontend:info-bar-sidenav-toggler-button>`タグは、 `icon`属性に[Clayアイコン](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)を使用します。

サイドバーパネル：

    <div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
    <liferay-frontend:sidebar-panel>
    <div>
    <h2>sidebar content</h2>
    <p>Here is some content</p>
    </div>
    </liferay-frontend:sidebar-panel>
    </div>

サイドバーパネルのラッパー`<div>`にはクラス`closed`と`sidenav-right`があることに注意してください。情報ボタンは、クラス`open`と`closed`を切り替えて、サイドバーパネルを表示および非表示にします。`sidenav-right`クラスは、パネルが右側に開くよう指定します。

![図2：情報バータグは、追加情報を表示するサイドバーパネルトグラーを作成します。](../../../images/liferay-frontend-taglib-info-bar.png)

上記の例では、使用可能な属性の一部を使用しています。タグに使用可能な属性の全リストについては、[info bar](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/info-bar.html)、[info bar buttons](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/info-bar-buttons.html)、[info bar sidenav toggler button](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/info-bar-sidenav-toggler-button.html)、および[sidebar panel](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/sidebar-panel.html)taglibdocsを参照してください。

## 関連トピック

[Liferay Front-end Add Menu](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-add-menu)

[Liferay Front-end Cards](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-cards)

[Liferay Front-end Management Bar](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-management-bar)

