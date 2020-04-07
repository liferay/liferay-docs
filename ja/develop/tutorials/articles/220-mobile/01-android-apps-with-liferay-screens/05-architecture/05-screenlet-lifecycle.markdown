---
header-id: screenlet-lifecycle
---

# スクリーンレットのライフサイクル

[TOC levels=1-4]

Liferay Screensは、Android SDKメソッド `onSaveInstanceState` および `onRestoreInstanceState`を使用して、スクリーンレットの状態を自動的に保存および復元します。 各スクリーンレットは、一意に生成された識別子（`screenletId`）を使用して、アクションソースにアクション結果を割り当てます。

[標準Androidライフサイクル](http://developer.android.com/training/basics/activity-lifecycle/recreating.html)指定されているように、 `onCreate` および `onStart` メソッドの後に、スクリーンレットの状態が復元されます。 アクティビティの `onResume` メソッド内でScreenletメソッドを実行するのがベストプラクティスです。これにより、アクションとタスクが目的地を確実に見つけることができます。

## 関連トピック

[高レベルのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/high-level-architecture)

[コア層](/docs/7-1/tutorials/-/knowledge_base/t/core-layer)

[スクリーンレットレイヤー](/docs/7-1/tutorials/-/knowledge_base/t/screenlet-layer)

[レイヤーを表示](/docs/7-1/tutorials/-/knowledge_base/t/view-layer)
