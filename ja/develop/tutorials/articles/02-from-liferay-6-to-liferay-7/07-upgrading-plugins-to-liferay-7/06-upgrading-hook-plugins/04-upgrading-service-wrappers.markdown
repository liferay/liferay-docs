---
header-id: upgrading-service-wrappers
---

# サービスラッパーのアップグレード

[TOC levels=1-4]

従来の[サービスラッパーフックプラグイン](/docs/6-2/tutorials/-/knowledge_base/t/overriding-a-portal-service-using-a-hook)を@product-ver@にアップグレードすることは簡単です。

1.  Liferay Upgrade Plannerを使用して、コードを@product-ver@のAPIに適合させます。 プランナーの*アップグレードの問題の修復*ステップを実行した時、既存の事象の多くが自動修正またはフラグ付けされました。 残りのエラーについては、記事[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)を参照してください。

2.  プラグインをデプロイします。

@product@のプラグイン互換レイヤーは、プラグインWARをWebアプリケーションバンドル（WAB）に変換し、LiferayのOSGiランタイムにインストールします。

## 関連記事

[Liferayサービス（サービスラッパー）のオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/customizing-liferay-services-service-wrappers)

[プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Liferay Mavenビルドのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
