---
header-id: crafting-xml-workflow-definitions
---

# XMLワークフロー定義の作成

[TOC levels=1-4]

ワークフローを構築するのに凝ったビジュアルデザイナーは必要ありません。Kaleo Designerはグラフィカルなインターフェイスを備えているため、より高速なワークフロー設計が可能です。多数のワークフロープロセスを構築する場合、Digital Enterpriseをサブスクリプションすることで、Kaleo Designerにアクセスできます。しかし、既存のワークフローから少しコピーアンドペーストし手作業で作成したXMLを少し使用するだけで、どんなワークフローでも作成することができ、その過程でワークフローウィザードの機能を得ることができます。この一連のチュートリアルに従って、定義に含めることができる要素について学習します。

## 既存のワークフロー定義

デフォルトでは、1つのワークフロー定義、単一承認者のみがインストールされています。ただし、@product@インストールのソースコードには、さらに複数埋め込まれています。
LPKGファイルに埋め込まれたJARファイルからXMLファイルを抽出したい場合は、次の手順に従ってワークフロー定義を取得してください。ファイルをより便利に入手するには、[こちら](https://portal.liferay.dev/documents/113763090/114000653/Workflow+Definitions+Zip.zip)からZIPファイルをダウンロードしてください。

自分用の定義を抽出するには、次に移動します。

    [Liferay Home]/osgi/marketplace

そして（アーカイブマネージャーを使用して）`Liferay CE Forms and Workflow.lpkg`を開きます。以下の名前のJARファイルを開きます。

    com.liferay.portal.workflow.kaleo.runtime.impl-[version].jar

JARファイルで、次に移動します。

    META-INF/definitions/

そして、4つのXMLワークフロー定義ファイルを抽出します。これらの定義は、これらの記事で説明されているワークフローの機能と要素の多くの優れた参考資料を提供します。実際、ここに表示されるXMLスニペットのほとんどは、これらの定義から直接引用されています。

## スキーマ

ワークフロー定義のXML構造は、XSDファイルで定義されます。

    liferay-worklow-definition-7_0_0.xsd

ワークフロー定義ファイルの上部でスキーマを宣言します。

    <?xml version="1.0"?>
    <workflow-definition
    xmlns="urn:liferay.com:liferay-workflow_7.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="urn:liferay.com:liferay-workflow_7.0.0
    http://www.liferay.com/dtd/liferay-workflow-definition_7_0_0.xsd">

より多くのXMLを記述する方法を定義した、きれいに書式が整えられた464行のXMLを読み込むには、[ここ](https://www.liferay.com/dtd/liferay-workflow-definition_7_0_0.xsd)からXSDをチェックしてください。
それ以外の場合は、定義のメタデータの入力に進みます。

## メタデータ

定義に名前、説明、およびバージョンを指定します。

    <name>Category Specific Approval</name>
    <description>A single approver can approve a workflow content.</description>
    <version>1</version>

これらのタグはすべてオプションです。定義を初めて保存するときに`<name>`タグが存在する場合、そのタグは定義の一意の識別子として機能します。指定しない場合（または最初の保存後に追加された場合）は、ランダムな一意の名前が生成され、ワークフローの識別に使用されます。

スキーマとメタデータを配置したら、次にフロー（ワークフロー）について見ていきましょう。次の記事ではワークフローノードについて学習します。
