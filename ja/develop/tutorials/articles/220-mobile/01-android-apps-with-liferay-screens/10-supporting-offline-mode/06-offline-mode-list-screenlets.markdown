---
header-id: supporting-offline-mode-in-list-screenlets
---

# リストスクリーンレットでのオフラインモードのサポート

[TOC levels=1-4]

リストスクリーンレットは、リスト内のエンティティを表示する読み取りスクリーンレットの特別なタイプです。 リスト [スクリーンレット作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-list-screenlets) を思い出してください

リストスクリーンレットには、サーバーから取得したエンティティをカプセル化するモデルクラスがあります。 オフラインモードをサポートするには、リストScreenletのイベントクラスは、モデルクラスを型引数として `ListEvent` を拡張する必要があります。 このイベントクラスには、次の3つのことも必要です。</p> 

1.  デフォルトのコンストラクター
2.  エンティティを格納する一意のIDを返す `getListKey` メソッド
3.  モデルインスタンスを返す `getModel` メソッド

リストスクリーンレット作成チュートリアルには、ブックマークリストスクリーンレットのオフラインモードをサポートするモデルとイベントクラスの例が含まれています。 次のリンクをクリックして、これらのクラスの作成方法を示すチュートリアルのセクションを参照してください。

  - [モデルクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-model-class)
  - [イベントを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor-0#creating-the-screenlets-event)



## 関連トピック

[オフラインモードのサポート](/docs/7-1/tutorials/-/knowledge_base/t/supporting-offline-mode)

[Androidリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-list-screenlets)
