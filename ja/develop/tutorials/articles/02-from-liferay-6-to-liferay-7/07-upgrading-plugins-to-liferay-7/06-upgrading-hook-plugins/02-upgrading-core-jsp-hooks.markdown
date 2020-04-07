---
header-id: upgrading-core-jsp-hooks
---

# コアJSPフックのアップグレード

[TOC levels=1-4]

@product-ver@で実行されるコアJSPフックを取得するには、2つのステップが必要です。

1.  Liferay Upgrade Plannerを使用して、コードを@product-ver@のAPIに適合させます。 プランナーの*アップグレードの問題の修復*ステップを実行した時、既存の事象の多くが自動修正またはフラグ付けされました。 残りのエラーについては、記事[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)を参照してください。

2.  フックプラグインをデプロイします。

@product@のプラグイン互換レイヤーは、プラグインWARをWebアプリケーションバンドル（WAB）に変換し、LiferayのOSGiランタイムにインストールします。

このステップは簡単に行えます。

## 関連トピック

[JSPのカスタマイズ](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps)

[アプリケーションJSPフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-app-jsp-hook-plugins)

[プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Liferay Mavenビルドのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
