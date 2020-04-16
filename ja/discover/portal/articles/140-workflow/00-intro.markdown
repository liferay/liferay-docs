---
header-id: workflow
---

# ワークフロー

[TOC levels=1-4]

Liferayのワークフローエンジンの名前は *Kaleo*です。 ギリシャ語でカレオは「呼ばれるもの」を意味します。これは、ユーザーのために設計されたレビュープロセスに参加するようにユーザーを呼び出すワークフローエンジンに適しています。 Kaleoワークフローを使用すると、単純なものから複雑なものまで、いくつものビジネスプロセス/ワークフローを定義し、それらをデプロイして、ポータルインターフェイスを介して管理することができます。 プロセスには、ユーザー、グループ、および役割に関する知識があります。 これを実現するために1行のコードを記述する必要はありません。これはXMLドキュメントで定義できます。または、Liferayデジタルエクスペリエンスプラットフォーム（DXP）の顧客であれば、ワークフローの洗練されたビジュアルデザイナーにアクセスできます。 、カレオデザイナーと呼ばれます。

効果的なワークフローにはいくつかのステップがあります。

  - [XMLでのレビュープロセスの設計](/docs/7-1/tutorials/-/knowledge_base/t/crafting-xml-workflow-definitions)

  - （DXPのみ） [Kaleo Designerでのレビュープロセスの設計](https://customer.liferay.com/documentation/7.1/admin/-/official_documentation/portal/kaleo-designer)

  - [ワークフロー定義のアップロード](/docs/7-1/user/-/knowledge_base/u/managing-workflows#uploading-workflow-definitions)

  - [有効なアセットのワークフローをアクティブにする](/docs/7-1/user/-/knowledge_base/u/activating-workflow)

  - [ワークフロー定義の管理](/docs/7-1/user/-/knowledge_base/u/managing-workflows)

  - [レビューによるアセットの送信](/docs/7-1/user/-/knowledge_base/u/reviewing-assets)

結局それは、ワークフローの任意のための承認プロセス設定するLiferayのKALEOを使用に精通しているでしょう [ワークフロー対応コンテンツ](/docs/7-1/user/-/knowledge_base/u/activating-workflow)。

## ワークフローの新機能

ワークフロー機能には、注目に値するいくつかの機能強化があります。

  - ワークフローを複製します。

  - 以前のバージョンをプレビューして復元します。

  - ドラフトワークフローを保存し、未公開の状態で保持します（これはLiferay Portal CEワークフローユーザー向けの新機能です）。

  - コントロールパネルのすべてのワークフロー機能は、コントロールパネル→構成→ワークフローにある1つのエントリに統合されています。

  - [コントロールパネル]→[構成]→[ワークフロー]で、ワークフローを作成します（Liferay Portal CEユーザーの場合はテキストエディター、Liferay DXPの場合はワークフローデザイナーを使用）。 追加ボタンをクリックするだけです。

独自のワークフローを最初から作成するか、既存のワークフローを活用します。

## 埋め込みワークフロー

単一の承認者定義に加えて、@ product @に同梱されているが、事前にインストールされていないワークフロー定義がいくつかあります。これらは主にテストケースに含まれているためです。 これらは `/ modules / apps / portal-workflow / portal-workflow-kaleo-runtime-impl / src / main / resources / META-INF / definitions`のLiferayソースコード、またはLiferayインストール自体にあります。 Liferayインストールの `osgi / portal / com.liferay.portal.workflow.kaleo.runtime.impl.jar`を開き、 `com.liferay.workflow.kaleo.runtime.impl-[version].jar`見つけて開きます。 定義は `META-INF / definitions` フォルダーにあります（図 [参照](/docs/6-2/tutorials/-/knowledge_base/t/designing-a-kaleo-workflow-definition)）。


<!-- Do you need a real life example to convince you that workflow is
important? Grab a cup of coffee and settle in. Story about ancient Greek
philosophers (web sites) competing rhetorically for followers and financial
supporters (users, customers, advertisers). One of them runs his arguments by
another philosopher for review, and finds his way to fame (single approver
workflow). The other does not, and is forgotten to history. Names: Phlegmaticus
and Sanguineus -->
