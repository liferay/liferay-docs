---
header-id: clay-progress-bars
---

# Clay進行状況バー

[TOC levels=1-4]

`clay:progressbar`タグを使用してアプリに進行状況バーを追加できます。これらはタスクの完了率を示し、3つのステータススタイルがあります：`default`（青色）、 `warning`（赤色）、`complete`（緑色でチェックマーク付き）。 最小値（`minValue`）と最大値（`maxValue`）を指定できます。

デフォルトの進行状況バー：

    <clay:progressbar
    maxValue="<%= 100 %>"
    minValue="<%= 0 %>"
    value="<%= 30 %>"
    />

![図1：進行状況バーをアプリに含めることができます。](../../../images/clay-taglib-progress-bar.png)

警告進行状況バー：

    <clay:progressbar
    maxValue="<%= 100 %>"
    minValue="<%= 0 %>"
    status="warning"
    value="<%= 70 %>"
    />

![図2：警告進行状況バーは、エラーのため進行状況が完了していないことを示しています。](../../../images/clay-taglib-progress-bar-warning.png)

完了進行状況バー：

    <clay:progressbar
    status="complete"
    />

![図3：完了進行状況バーは、進行状況が完了したことを示します。](../../../images/clay-taglib-progress-bar-complete.png)

Clay taglibを使用すると、アプリの進行状況を簡単に追跡できます。

## 関連トピック

[Clay Dropdown Menus and Action Menus](/docs/7-1/tutorials/-/knowledge_base/t/clay-dropdown-menus-and-action-menus)

[Clay Icons](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)

[Clay Navigation Bars](/docs/7-1/tutorials/-/knowledge_base/t/clay-navigation-bars)
