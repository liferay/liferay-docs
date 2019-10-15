---
header-id: updating-a-cluster
---

# クラスターの更新

[TOC levels=1-4]

[クラスター](/docs/7-1/deploy/-/knowledge_base/d/liferay-clustering)を維持することは重要です。これには、新規および更新されたプラグインとモジュールのデプロイ、[フィックスパック](/discover/deployment/-/knowledge_base/7-1/maintaining-liferay)の適用 、設定の変更などが含まれています。変更を適用するときは、サーバーの稼働時間を最大化し、リスクを最小化することが優先されます。@product@は、標準的なクラスターメンテナンス技術の使用をサポートしています。

- [ローリング再起動](/docs/7-1/deploy/-/knowledge_base/d/using-rolling-restarts)：ノードは一度に1つずつ、シャットダウンと更新が行われます。
- [ブルーグリーンデプロイ](/docs/7-1/deploy/-/knowledge_base/d/other-cluster-update-techniques)：ブルーグリーンは、現在の環境（*青い*環境）の複製、複製（*緑*の環境）の更新、およびユーザーを更新された環境（緑）に切り替えることが含まれています。

方法を以下で比較します。

**クラスターの更新方法**

| アップデート | &nbsp;Rolling Restart | &nbsp;Blue-green |
 |
	--------- | :-------------- | :---------- | -------------- |
| Plugin/module installation | Supported | Supported |
| Plugin/module update (backward-compatible data/schema changes) | Supported | Supported |
| Plugin/module update (non-backward-compatible data/schema changes) [1](#one) | Not supported | Supported |
| Fix pack installation and removal (revertable fix pack) | Supported | Supported |
| Fix pack installation (non-revertible fix pack) | Not supported | Supported |
| Cluster code changes [2](#two) | Not supported | Supported |
| Portal property changes | Supported | Supported |
| System Setting changes via configuration admin files | Supported | Supported |
| Application server updates | Supported | Supported |
| JVM setting changes | Supported | Supported |
| New Java version (minor) | Supported | Supported |
| New Java version (major) | Not supported | Supported |

[<a name="one">1</a>]下位互換性がないデータおよびデータスキーマの変更には、次のものが含まれますが、これらに限定されません。

- 既存の列のデータを変更する
- 列を削除する
- 列タイプを変更する
- 列で使用されるデータ形式の変更（XMLからJSONへの変更など）
- Service Builderサービスモジュールのデータスキーマをモジュールの[必須データスキーマの範囲](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app#specifying-the-schema-version)外のバージョンに更新する。
モジュールの`Liferay-Require-SchemaVersion`（`bnd.bnd`で指定）は、`Release_`テーブル内のモジュールのスキーマバージョン値と一致する必要があります。新しいスキーマバージョンでモジュールをインストールすると、そのスキーマバージョンで`Release_`テーブルが更新され、データアップグレードプロセスが実行されます。このようなモジュールを1つのノードにインストールすると、`Release_`テーブル内のスキーマバージョンは他のノードのモジュールの`Liferay-Require-SchemaVersion`と一致しなくなります。そして、モジュールが他のノードにインストールされるまで、モジュールのService Builderサービスが使用できなくなります。このような変更を元に戻すことはできないので、データベースはバックアップから復元する必要があります。これらのスキーマバージョンの変更は、すべてのノードがシャットダウンされている間に適用する必要があります。

[<a name="two">2</a>]クラスター通信はそのままにしておく必要があります。したがって、クラスターコードをローリング再起動時に更新しないでください。カスタマーポータルは、そのような変更を含むDXPフィックスパックを元に戻せないと識別します。ローリング再起動時に変更してはいけないパッケージは次のとおりです。

- `com.liferay.portal.kernel.cluster`
- `com.liferay.portal.kernel.cluster.*`
- `com.liferay.portal.kernel.exception.NoSuchClusterGroupException`
- `com.liferay.portal.kernel.scheduler.multiple`
- `com.liferay.portal.kernel.scheduler.multiple.*`
- `com.liferay.portal.cache.multiple`
- `com.liferay.portal.cache.multiple.*`
- `com.liferay.portal.scheduler.multiple`
- `com.liferay.portal.scheduler.multiple.*`

対象となる変更はローリング再起動で行う必要があるため、最初に説明します。
