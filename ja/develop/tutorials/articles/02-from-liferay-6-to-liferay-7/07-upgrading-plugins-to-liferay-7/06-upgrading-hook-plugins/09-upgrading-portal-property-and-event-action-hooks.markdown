---
header-id: upgrading-portal-property-and-event-action-hooks
---

# ポータルプロパティとイベント操作フックのアップグレード

[TOC levels=1-4]

@product-ver@でも使用されるLiferay Portal 6.2のすべてのポータルプロパティをオーバーライドできます。 これらのプロパティを使用するポータルプロパティおよびポータルイベント操作フックは、次の手順に従ってアップグレードできます。

1.  Liferayアップグレードプランナーを使用して、コードを@product-ver@のAPIに適合させます。 プランナーの*アップグレードの問題の修復*ステップを実行した時、既存の事象の多くが自動修正またはフラグ付けされました。 残りのエラーについては、記事

Resolving a Plugin's Dependencies/a>を参照してください。</p></li> 
   
   2  フックプラグインをデプロイします。</ol> 

@product@のプラグイン互換レイヤーは、プラグインWARをWebアプリケーションバンドル（WAB）に変換し、LiferayのOSGiランタイムにインストールします。

これでカスタムプロパティの値と操作が本番環境になりました。



## 関連トピック

[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)

[プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[依存関係の設定方法](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[Liferay Mavenビルドのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
