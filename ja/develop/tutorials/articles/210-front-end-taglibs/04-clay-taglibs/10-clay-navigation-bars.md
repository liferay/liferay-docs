---
header-id: clay-navigation-bars
---

# Clayナビゲーションバー

[TOC levels=1-4]

ドロップダウンメニューと同様に、ナビゲーションバーにはナビゲーション項目のリストが表示されます。
主な違いは、ナビゲーションバーは水平バーに表示され、すべてのナビゲーション項目が常に表示されることです。また、ナビゲーションバーには、アクティブなナビゲーション項目が下線で示されます。ナビゲーションバーには2つのスタイルがあります：白い背景に濃い灰色のテキスト（デフォルト）と、濃い灰色の背景に白いテキスト（反転）です。

デフォルトのナビゲーションバー：

    <clay:navigation-bar
    navigationItems="<%= navigationBarsDisplayContext.getNavigationItems() %>"
    />

![図1：ナビゲーションバーをアプリに含めることができます。](../../../images/clay-taglib-nav-bars.png)

反転ナビゲーションバー（`inverted`属性を`true`に設定）：

    <clay:navigation-bar
    inverted="<%= true %>"
    navigationItems="<%= navigationBarsDisplayContext.getNavigationItems() %>"
    />

![図2：必要に応じて、ナビゲーションバーを反転できます。](../../../images/clay-taglib-nav-bars-inverted.png)

## 関連トピック

[Clay Dropdown Menus and Action Menus](/docs/7-1/tutorials/-/knowledge_base/t/clay-dropdown-menus-and-action-menus)

[Clay Form Elements](/docs/7-1/tutorials/-/knowledge_base/t/clay-form-elements)

[Clay Progress Bars](/docs/7-1/tutorials/-/knowledge_base/t/clay-progress-bars)
