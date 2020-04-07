---
header-id: upgrading-a-genericportlet
---

# GenericPortletのアップグレード

[TOC levels=1-4]

`javax.portlet.GenericPortlet`を拡張するポートレットを作成することは一般的です。 いずれにしても、`GenericPortlet`はデフォルトの`javax.portlet.Portlet`インターフェース実装を提供します。 `GenericPortlet`のアップグレードは、2つの手順のみで簡単に実行できます。

1.  Liferay Upgrade Plannerを使用して、ポートレットを@product-ver@のAPIに適合させます。

2.  依存関係を解決します。

このチュートリアルでは、*Sample DAO*（プロジェクト`sample-dao-portlet`）と呼ばれる、LiferayプラグインSDK 6.2のサンプルポートレットのアップグレードについて説明します。

![図1： <code>sample-dao-portlet</code>を使用すると、ユーザーは食品を管理できます。](../../../../images/upgrading-portlets-sample-dao-portlet.png)

サンプルポートレットを使用すると、ユーザーはリストから食品を表示、追加、編集、および削除できます。 参考として、[事前アップグレードされたポートレットコード](https://portal.liferay.dev/documents/113763090/114000186/sample-dao-portlet-pre-7-0-upgrade.zip)と[アップグレードされたコード](https://portal.liferay.dev/documents/113763090/114000653/sample-dao-portlet-post-7-1-upgrade.zip)をダウンロードできます。

サンプルポートレットには、次の特性があります。

  - `GenericPortlet`を拡張します。
  - JSPによって実装されたレイヤーを表示します。
  - データアクセスオブジェクト（DAO）デザインパターンを使用してモデルを永続化します。
  - プロパティファイルでデータベース接続情報を指定します。
  - Ant/Ivyを介して依存関係を管理します。
  - LiferayプラグインSDK 6.2で開発されました。

ポートレットは、従来のプラグインSDKポートレットのプロジェクトのフォルダ構造を使用します。

![図2：<code>sample-dao-portlet</code>プロジェクトは、標準的なプラグインSDKポートレットフォルダ構造を使用します。](../../../../images/upgrading-a-genericportlet-folder-structure.png)

ほとんどの`GenericPortlet`ポートレットのアップグレードには、次の手順が含まれます。

1.  [@product-ver@のAPIにコードを適合させる](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems)
2.  [依存関係を解決する](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

サンプルポートレットの依存関係は変更されていないため、アップグレードはコードを@product-ver@のAPIに適合させるだけで行えます。 [Liferay Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)は、コードのアップデートとコンパイルの事象の迅速な解決を促進します。

| **注**: 依存関係の変更に適応させる必要がある場合は、チュートリアル | [Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies) | を参照してください。

Portal 6.xにデプロイするのと同じ方法で、`GenericPortlet`を@product-ver@にデプロイします。プラグインWARファイルが`[Liferay_Home]/deploy`フォルダに到着すると、@product@のプラグイン互換レイヤーがWARをWebアプリケーションバンドル（WAB）に変換し、@product@のOSGiランタイムへのWABとしてポートレットをインストールします。

アップグレードされたポートレットをデプロイすると、サーバーは次のポートレット状態を示すメッセージを出力します。

  - WAR processing
  - WAB startup
  - Availability to users

サンプルポートレットをデプロイすると、次のようなメッセージが生成されます。

    2018-03-21 17:44:59.179 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:262] Processing sample-dao-portlet-7.1.0.1.war
    ...
    2018-03-21 17:45:09.959 INFO  [Refresh Thread: Equinox Container: 0012cbb0-7e2c-0018-146e-95a4d71cdf95][PortletHotDeployListener:298] 1 portlet for sample-dao-portlet is available for use 
    ...
    2018-03-21 17:45:10.151 INFO  [Refresh Thread: Equinox Container: 0012cbb0-7e2c-0018-146e-95a4d71cdf95][BundleStartStopLogger:35] STARTED sample-dao-portlet_7.1.0.1 [655]

これでポートレットが@product@で利用可能になりました。

`GenericPortlet`を拡張するポートレットをアップグレードおよびデプロイする方法を学習しました。 コードを適合し、依存関係を解決したら、通常通りポートレットをデプロイします。 このステップは簡単に行えます。

**関連トピック**

[プラグインSDKプロジェクトをWorkspaceとGradleに移行する](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)

[依存関係管理ツールの使用](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-a-dependency-management-tool)

[WABジェネレーターの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)

[データアップグレードプロセスの移行](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-app-upgrade-processes)
