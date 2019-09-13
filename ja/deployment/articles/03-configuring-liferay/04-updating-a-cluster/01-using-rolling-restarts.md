# ローリング再始動[](id=using-rolling-restarts)

ローリング再起動のクラスターのメンテナンスプロセスでは、ノードがすべて更新されるまで、ノードを一度に1つずつシャットダウンして更新します（他のノードの実行中に）。これにより、クラスターを更新している間の稼働時間が最大化されます。ローリング再起動は、コンテナおよびイメージベースの環境で使用できます。

+$$$

**注：**ローリング再起動には、ブルーグリーン（別々だけど同一の環境）のアーキテクチャーの概念は含まれていません。これらの概念は、特にマルチクラスタスタイルの開発に対応しています。

$$$

ローリング再起動の手順は次のとおりです。

1. 1つのクラスタノード（JVMインスタンス）をシャットダウンします。

2. そのノードのデプロイメントを更新または変更します（以下のメンテナンスシナリオを参照）。

3. ノードを起動します。

4. 他のすべてのクラスタノードに対してこれらの手順を繰り返します。

メンテナンスシナリオは、ローリング再起動時の動作の仕方によって異なります。たとえば、プラグインの更新におけるUIの変更は、更新されたノードにのみ表示されます。更新されていないノード上のユーザーには、UIの変更は表示されません。メンテナンスシナリオには、ローリング再始動では実行できない特定のケースがある場合があります。シナリオ記述では、これらのケースについて説明しています。

ローリング再起動に適したメンテナンスシナリオを次に説明します。

## 新しいモジュールとプラグイン[](id=new-plugins-and-modules)

新しいプラグインまたはモジュール（クラスター内にまだ存在していないもの）をローリング再起動に適格にするには、データを変更したり、既存のプラグインまたはモジュールとの互換性を損なうような方法でデータベース列を削除または名前変更しないでください。

## 既存のモジュールとプラグインを更新する[](id=updating-existing-plugins-and-modules)

新しいプラグインまたはモジュール（まだクラスタに存在しないもの）をローリング再起動の対象とするには、データを変更したり、既存のプラグインまたはモジュールとの互換性を損なうような方法でデータベースの列を削除、または名前変更をしないでください。

## フィックスパックの適用（DXPのみ）[](id=applying-fix-packs-dxp-only)

カスタマーポータル は、元に戻せないため、ローリング再起動の対象にならない[フィックスパック](/discover/deployment/-/knowledge_base/7-1/maintaining-liferay)を識別します。その他のフィックスパックは全て対象となります。

## フィックスパックのリバート（DXPのみ）[](id=reverting-fix-packs-dxp-only)

リバートが可能なフィックスパックは、ローリング再起動で削除できます。

## `portal-ext.properties`[](id=portal-properties-controlled-by-portal-ext-properties)によって制御されているポータルプロパティ

[ポータルプロパティ](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)ファイルの変更は、ローリング再起動で適用できます。

## 設定管理ファイルによって制御されているシステム設定[](id=system-settings-controlled-by-configuration-admin-files)

[システム設定](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)ファイルは、ローリング再起動で適用できます。

## アプリケーションサーバーまたはJVM設定の変更[](id=application-server-or-jvm-setting-modifications)

アプリケーションサーバーとJVMの設定への変更は、ローリング再起動で行うことができます。

## Javaのバージョン更新[](id=java-version-updates)

Javaのマイナーバージョンアップデートは、ローリング再起動で適用できます。メジャーバージョンアップデートは、ローリング再起動ではサポートされていないため、すべてのクラスタノードがシャットダウンされてから実行する必要があります。

ローリング再起動が対象となる全てのアップデートは、先述のローリング再起動のステップを使用して適用できます。次に説明するように、他の更新は異なる方法で行う必要があります。

## 関連トピック[](id=related-topics)

[@product@ Clustering](/discover/deployment/-/knowledge_base/7-1/liferay-clustering)

[Maintaining @product@](/discover/deployment/-/knowledge_base/7-1/maintaining-liferay)

[Implementing Data Upgrades](/develop/tutorials/-/knowledge_base/7-1/data-upgrades)
