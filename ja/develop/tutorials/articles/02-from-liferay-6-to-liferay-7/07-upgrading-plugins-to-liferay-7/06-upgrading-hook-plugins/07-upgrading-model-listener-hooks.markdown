---
header-id: upgrading-model-listener-hooks
---

# モデルリスナーフックのアップグレード

[TOC levels=1-4]

開発者は、いくつかのLiferay Portalバージョン向けのモデルリスナーを作成しています。 以前のバージョンのポータルからモデルリスナーフックをアップグレードすることは、これまでになく簡単になりました。

1.  Liferay Upgrade Plannerを使用して、コードを@product-ver@のAPIに適合させます。 プランナーの*アップグレードの問題の修復*ステップを実行した時、既存の事象の多くが自動修正またはフラグ付けされました。 残りのエラーについては、記事[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)を参照してください。

2.  プラグインをデプロイします。

@product@のプラグイン互換レイヤーは、プラグインWARをWebアプリケーションバンドル（WAB）に変換し、LiferayのOSGiランタイムにインストールします。

これでモデルリスナーフックは動作する準備が整いました。

## 関連トピック

[モデルリスナーの作成](/docs/7-1/tutorials/-/knowledge_base/t/model-listeners)

[プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[依存関係の設定方法](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[Liferay Mavenビルドのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
