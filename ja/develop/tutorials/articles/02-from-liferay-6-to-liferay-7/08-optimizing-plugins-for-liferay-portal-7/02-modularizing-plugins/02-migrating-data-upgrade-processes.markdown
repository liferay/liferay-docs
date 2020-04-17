---
header-id: optimizing-app-upgrade-processes
---

# モジュール向けの新しいフレームワークへのデータアップグレードプロセスの移行

[TOC levels=1-4]

アプリケーションへデータベースの変更を行う場合、*データアップグレードプロセス*を使用して、ユーザーの既存データを新しいデータベーススキーマに移行する必要があります。 古いフレームワークでは複数のクラスが必要でしたが、新しいフレームワークでは単一のクラスからアップグレード手順を調整できます。 1つのクラスのステップを管理すると、アップグレードプロセスの開発が容易になります。 使用するデータアップグレードフレームワークは、開発フレームワークによって異なります。 このチュートリアルでは、新しいフレームワークに移行する方法を示します。

  - [アップグレードされたプラグイン](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-plugins-to-liferay-7)が従来のWARである場合、特別なことをする必要はありません。[@product-ver@のAPIに適合](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)した既存のアップグレードプロセスはそのまま機能します。 新しいデータアップグレードフレームワークはモジュール専用です。

  - アップグレードしたプラグインをモジュールに変換した場合、またはアップグレードしたモジュールがある場合、引き続き使用するアップグレードプロセスを新しいデータアップグレードフレームワークに移行する必要があります。

（最新のものから始まる）古いアップグレードプロセスをいくつでも新しいフレームワークに移行できます。 たとえば、モジュールにバージョン1.0、1.1、1.2、および1.3があり、バージョン1.2以降の顧客のみがアップグレードすることを予期している場合、バージョン1.2および1.3のみのアップグレードプロセスを移行できます。

開始する前に新しいフレームワークを使用してアップグレードプロセスを作成する[方法](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)を参照してください。

| **注:** Liferay Portal 6プラグインには検証プロセスが含まれる場合もあります。 | 検証プロセスを変更せずに@product-ver@に移行できますが、代わりにアップグレードプロセスで検証を実行することを | お勧めします。

まず最初に、Liferay Portal 6のアップグレードプロセスの仕組みを確認します。

## Liferay Portal 6のアップグレードプロセスについて

始める前に、Liferay Portal 6のアップグレードプロセスがどのように構成されているかを理解することが重要です。 例として、[Liferay Portal 6.2のアップグレードプロセス](https://github.com/liferay/liferay-plugins/tree/6.2.x/portlets/knowledge-base-portlet/docroot/WEB-INF/src/com/liferay/knowledgebase/hook/upgrade)をナレッジベースポートレットのために使用します。

Liferay Portal 6のアップグレードプロセスでは、各スキーマバージョンのアップグレードステップクラスは、スキーマバージョンに基づいて名前が付けられたフォルダの中にあります。 たとえば、ナレッジベースポートレットのアップグレードステップクラスは、`v1_0_0`、`v1_1_0`、`v1_2_0`などのフォルダ名の中にあります。 各アップグレードステップクラスは、`UpgradeProcess`を拡張し、`doUpgrade` メソッドをオーバーライドします。 `doUpgrade`のコードがアップグレードを実行します。 たとえば、ナレッジベースポートレットの[`v1_0_0/UpgradeRatingsEntry`アップグレードステップ](https://github.com/liferay/liferay-plugins/blob/6.2.x/portlets/knowledge-base-portlet/docroot/WEB-INF/src/com/liferay/knowledgebase/hook/upgrade/v1_0_0/UpgradeRatingsEntry.java)は`UpgradeProcess`を拡張し、その`doUpgrade`メソッドでの`updateRatingsEntries()`呼び出しを介してアップグレードを実行します。

    public class UpgradeRatingsEntry extends UpgradeProcess {
    
        @Override
        protected void doUpgrade() throws Exception {
            updateRatingsEntries();
        }
    
        ...
    
        protected void updateRatingsEntries() throws Exception {
            // Upgrade code
        }
    
        ...
    
    }

アップグレードプロセスクラスは、アップグレード手順を含むフォルダと同じレベルにあり、スキーマバージョンに基づいて名前が付けられます。 たとえば、ナレッジベースポートレットのアップグレードプロセスクラスの名前は、`UpgradeProcess_1_0_0`、`UpgradeProcess_1_1_0`、`UpgradeProcess_1_2_0`などとなります。 各アップグレードプロセスクラスは`UpgradeProcess`を拡張し、`doUpgrade`メソッドでアップグレードプロセスを実行します。 適切なアップグレードプロセスを`upgrade`メソッドに渡すことにより、アップグレードプロセスを実行します。 たとえば、ナレッジベースポートレットの[`UpgradeProcess_1_0_0`クラス](https://github.com/liferay/liferay-plugins/blob/6.2.x/portlets/knowledge-base-portlet/docroot/WEB-INF/src/com/liferay/knowledgebase/hook/upgrade/UpgradeProcess_1_0_0.java)にある`doUpgrade`メソッドは、`upgrade`メソッドを介して、アップグレード手順`UpgradeRatingsEntry`および`UpgradeRatingsStats`を実行します。

    @Override
    protected void doUpgrade() throws Exception {
        upgrade(UpgradeRatingsEntry.class);
        upgrade(UpgradeRatingsStats.class);
    }

ここまで、Liferay Portal 6のアップグレードプロセスの定義方法について学びました。次に、@product-ver@でそれらを新しいアップグレードプロセスフレームワークに変換する方法について学びます。

## Liferay Portal 6アップグレードプロセスを@product-ver@に変換する

Liferay Portal 6のアップグレードプロセスは、新しいアップグレードプロセスフレームワークを使用するプロセスと、どのように比較できるでしょうか。 まず、アップグレード手順のクラスは同じであるため、変更せずにそのままにしておくことができます。 新しいアップグレードプロセスの大きな変更点は次のとおりです。

  - [単一の登録者クラスがアップグレードプロセスクラスを置き換えます。](#create-a-registrator-class)
  - [Service Buildeサービスには、Bundle Activatorが必要です。](#create-a-bundle-activator)

登録クラスを作成して、変換を開始します。

### 登録者クラスを作成する

新しいデータアップグレードフレームワークでは、アップグレードプロセスクラスの代わりに登録クラスを使用する必要があります。 また、アップグレードプロセスクラスの機能を単一の登録クラスに結合する必要があります。 アップグレードプロセスフレームワークが実行するアップグレードプロセスを、登録者が定義することの詳細については、[the data upgrade process tutorial](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app#writing-the-upgrade-step-registrator)を参照してください。 登録者の`registry.register`呼び出しごとに、スキーマバージョンごとに適切なアップグレード手順が登録されます。 したがって、古いアップグレードプロセスクラスの`doUpgrade`メソッドの機能を、登録者の`registry.register`呼び出しに転送する必要があります。

例として、GitHubのナレッジベースポートレットの新しい@product-ver@アップグレードプロセスを参照するには、[ここをクリック](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/knowledge-base/knowledge-base-service/src/main/java/com/liferay/knowledge/base/internal/upgrade)してください。

@product-ver@用のポートレットに加えられた変更を処理するいくつかの追加のアップグレードステップクラス以外で、このアップグレードプロセスの唯一の違いは、複数のアップグレードプロセスクラスの代わりに単一の登録クラス`KnowledgeBaseServiceUpgrade`を含むことです。 [`KnowledgeBaseServiceUpgrade`クラス](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/knowledge-base/knowledge-base-service/src/main/java/com/liferay/knowledge/base/internal/upgrade/KnowledgeBaseServiceUpgrade.java)はすべての登録者と同様に、その `registry.register`呼び出しで、各スキーマバージョンに向けた適切なアップグレード手順を呼び出します。 たとえば、最初の`registry.register`呼び出しは、`1.0.0`スキーマバージョンのアップグレードプロセスを登録します。

    registry.register(
            "com.liferay.knowledge.base.service", "0.0.1", "1.0.0",
            new com.liferay.knowledge.base.internal.upgrade.v1_0_0.
                UpgradeRatingsEntry(),
            new com.liferay.knowledge.base.internal.upgrade.v1_0_0.
                UpgradeRatingsStats());

これを上記の[Liferay Portal 6対応アップグレードプロセスクラス`UpgradeProcess_1_0_0`](https://github.com/liferay/liferay-plugins/blob/6.2.x/portlets/knowledge-base-portlet/docroot/WEB-INF/src/com/liferay/knowledgebase/hook/upgrade/UpgradeProcess_1_0_0.java)からの`doUpgrade`メソッドと比較します。 どちらも同じアップグレード手順を呼び出します。

次に、モジュール化されたプラグインがService Builderを使用する場合、Bundle Activatorを作成します。

### Bundle Activatorを作成する

モジュールがService Builderサービスを実装する場合、リリーステーブルのレコードを初期化するためにBundle Activatorが必要です。 [Bundle Activator](/docs/7-1/tutorials/-/knowledge_base/t/upgrade-processes-for-former-service-builder-plugins)の作成は簡単です。

これで完了です。\ 登録者の作成の完全な手順を含む、@product-ver@の新しいアップグレードプロセスの作成手順については、[ここをクリック](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)してください。

## 関連トピック

[モジュールのデータアップグレードプロセスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)

[プラグインを@product-ver@にアップグレードする](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-plugins-to-liferay-7)

[Liferay Portal 6から7へ](/docs/7-1/tutorials/-/knowledge_base/t/from-liferay-6-to-liferay-7)
