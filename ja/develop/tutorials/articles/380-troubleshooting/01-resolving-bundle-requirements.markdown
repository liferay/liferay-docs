---
header-id: resolving-bundle-requirements
---

# バンドル要件の解決

[TOC levels=1-4]

バンドルの1つで、Liferay OSGiランタイム時に他のバンドルによってエクスポートされていないパッケージが必要な場合、バンドル例外が発生します。例外のサンプルを以下に示します。

    !could not resolve the bundles: [com.liferay.messaging.client.command-1.0.0.201707261701 org.osgi.framework.BundleException: Could not resolve module: com.liferay.messaging.client.command [1]
    Unresolved requirement: Import-Package: com.liferay.messaging.client.api; version="[1.0.0,2.0.0)"
    -> Export-Package: com.liferay.messaging.client.api; bundle-symbolic-name="com.liferay.messaging.client.provider"; bundle-version="1.0.0.201707261701"; version="1.0.0"; uses:="org.osgi.framework"
    com.liferay.messaging.client.provider [2]
    Unresolved requirement: Import-Package: com.liferay.messaging; version="[1.0.0,2.0.0)"
    -> Export-Package: com.liferay.messaging; bundle-symbolic-name="com.liferay.messaging.api"; bundle-version="1.0.0"; version="1.0.0"; uses:="com.liferay.petra.concurrent"
    com.liferay.messaging.api [12]
    Unresolved requirement: Import-Package: com.liferay.petra.io; version="[1.0.0,2.0.0)"
    -> Export-Package: com.liferay.petra.io; bundle-symbolic-name="com.liferay.petra.io"; bundle-version="1.0.0"; version="1.0.0"
    com.liferay.petra.io [16]
    Unresolved requirement: Require-Capability osgi.extender; filter:="(osgi.extender=osgi.serviceloader.processor)"

最初の行の状態は、*バンドルを解決できませんでした*。ここに示すのは、LiferayのOSGiランタイムが解決できなかった一連の要件です。

バンドル例外メッセージは、次の一般的なパターンに従います。

- モジュールAには未解決の要件（パッケージまたは機能）`aaa.bbb`があります。
- モジュールBは`aaa.bbb`を提供しますが、未解決の要件`ccc.ddd`があります。
- モジュールCは`ccc.ddd`を提供しますが、未解決の要件`eee.fff`があります。
- 等々。
- モジュールZは`www.xxx`を提供しますが、未解決の要件`yyy.zzz`があります。

パターンは、最後の不満足な要件で停止します。最後のモジュールの依存関係は、バンドル例外を解決するための鍵です。 2つの原因が考えられます。

1. 最終要件を満たす依存関係がビルドファイルにない可能性があります。

2. 最終要件を満たす依存関係がデプロイされていない可能性があります。

どちらの場合も、不足している要件を提供するバンドルをデプロイする必要があります。

このバンドル例外の例では、モジュール`com.liferay.petra.io`に`osgi.extender;
filter:="(osgi.extender=osgi.serviceloader.processor)"`機能が必要であると結論付けています。要件を解決するには、`com.liferay.petra.io`のすべての依存関係がデプロイされていることを確認してください。

`com.liferay.petra.io`モジュールの`build.gradle`ファイルには、依存関係がリストされています。

    dependencies {
    provided group: "com.liferay", name: "com.liferay.petra.concurrent", version: "1.0.0"
    provided group: "com.liferay", name: "com.liferay.petra.memory", version: "1.0.0"
    provided group: "org.apache.aries.spifly", name: "org.apache.aries.spifly.dynamic.bundle", version: "1.0.8"
    provided group: "org.slf4j", name: "slf4j-api", version: "1.7.2"
    testCompile group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "default"
    }

次に、[Felix Gogoシェルの`lbコマンド`](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)を使用して、依存関係がLiferayのOSGiランタイムにあることを確認します。

    lb
    START LEVEL 1
    ID|State      |Level|Name
    0|Active     |    0|OSGi System Bundle (3.10.100.v20150529-1857)|3.10.100.v20150529-1857
    1|Active     |    1|com.liferay.messaging.client.command (1.0.0.201707261923)|1.0.0.201707261923
    2|Active     |    1|com.liferay.messaging.client.provider (1.0.0.201707261927)|1.0.0.201707261927
    3|Active     |    1|Apache Felix Configuration Admin Service (1.8.8)|1.8.8
    4|Active     |    1|Apache Felix Log Service (1.0.1)|1.0.1
    5|Active     |    1|Apache Felix Declarative Services (2.0.2)|2.0.2
    6|Active     |    1|Meta Type (1.4.100.v20150408-1437)|1.4.100.v20150408-1437
    7|Active     |    1|org.osgi:org.osgi.service.metatype (1.3.0.201505202024)|1.3.0.201505202024
    8|Active     |    1|Apache Felix Gogo Command (0.16.0)|0.16.0
    9|Active     |    1|Apache Felix Gogo Runtime (0.16.2)|0.16.2
    10|Active     |    1|Apache Felix Gogo Runtime (1.0.0)|1.0.0
    ...

依存関係モジュール`org.apache.aries.spifly.dynamic.bundle`がランタイムバンドルリストにありません。`org.apache.aries.spifly.dynamic.bundle`モジュールの`MANIFEST.MF`ファイルは、`osgi.extender; filter:="(osgi.extender=osgi.serviceloader.processor)"`要件機能を提供していることを示しています。

    Provide-Capability: osgi.extender;osgi.extender="osgi.serviceloader.regi
    strar";version:Version="1.0",osgi.extender;osgi.extender="osgi.servicel
    oader.processor";version:Version="1.0"

この機能`osgi.extender; filter:="(osgi.extender=osgi.serviceloader.processor)"`は、以前に確認した未解決の要件です。この欠落しているバンドル`org.apache.aries.spifly.dynamic.bundle`をデプロイすると、サンプルモジュールの要件が満たされ、モジュールが解決されインストールできるようになります。

これらと同様の手順に従って、バンドルの例外を解決できます。

注：Bndtoolsの*[Resolve]*ボタンを使用すると、バンドルの依存関係を自動的に解決できます。
アプリケーションに必要なバンドルを指定すると、Bndtoolsは設定されたアーティファクトリポジトリから推移的な依存関係を追加します。

## 関連トピック

[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[Adding Third Party Libraries to a Module](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)

[Felix Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)

[Resolving a Plugins's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)
