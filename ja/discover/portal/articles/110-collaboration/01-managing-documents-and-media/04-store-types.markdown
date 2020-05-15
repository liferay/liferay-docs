---
header-id: store-types
---

# 店舗タイプ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Documents and Mediaライブラリがファイルの保存に使用するファイルシステム（ *ストア*）を変更できます。 これは、 `dl.store.impl =`プロパティーを設定することにより、 `portal-ext.properties` ファイルで構成されます。 店舗の設定はで覆われている [ドキュメントリポジトリの設定ガイド](/docs/7-1/deploy/-/knowledge_base/d/document-repository-configuration)。 ここでは、さまざまな店舗の影響を検討します。

  - **シンプルファイルシステムストア：** ファイルシステム（ローカルまたはマウントされた共有）を使用してファイルを格納します。 これがデフォルトのストアです。

  - **Advanced File System Store：** より高速なパフォーマンスとより多くのファイルを保存するために、バージョンごとにファイルをディレクトリにネストします。

  - **DBStore（データベースストレージ）**：@product@データベースにファイルを保存します。 ファイル（blobとして保存）のサイズ制限は1 GBです。 Simple File System StoreまたはAdvanced File System Storeを使用して、より大きなファイルを保存します。

  - **S3Store（Amazon Simple Storage）**：Amazonのクラウドベースのストレージソリューションを使用します。

ストア間でファイルを移動する必要がある場合は、 *コントロールパネル* → *構成* → *サーバー管理* → *データ移行*の移行ユーティリティを使用します。

## シンプルファイルシステムストア

Simple File System Storeがデフォルトのストアです。 ドキュメントとメディアファイルをサーバーのファイルシステム（ローカルまたはマウント済み）に保存します。 このストアは、@product@のデータベースに大きく拘束されています。 ストアのデフォルトのルートフォルダーは `[Liferayホーム] / data / document_library`です。 これは、 `portal-ext.properties` ファイルの `dl.store.file.system.root.dir =` プロパティ、またはコントロールパネルで変更できます。 この手順については、 [ドキュメントリポジトリ構成ガイド](/docs/7-1/deploy/-/knowledge_base/d/document-repository-configuration)参照してください。

Simple File System Storeは、ローカルフォルダーを使用してファイルを格納します。 クラスター化された構成にファイルシステムを使用できますが、ストアを指すフォルダーが同時要求やファイルロックなどを処理できることを確認する必要があります。 したがって、ストレージエリアネットワークまたはクラスター化されたファイルシステムを使用する必要があります。

Simple File System Storeは、@product@のデータベースの主キーに基づいてフォルダー構造を作成します。 たとえば、ファイル名 `workflow.odp` プレゼンテーションを `stuff`という名前のフォルダーにアップロードすると、ストアは次のようなフォルダー構造を作成します。

    /companyId/folderId/numericFileEntryName/versionNumber

  - `companyId`：サイトの会社ID。
  - `folderId`：ドキュメントを含むDocuments and MediaフォルダーのID。
  - `numericFileEntryName`：ドキュメントの数値ファイルエントリ名。
  - `versionNumber`：ドキュメントのバージョン番号。

![図1：Simple File System Storeは、@product@のデータベースの主キーに基づいてフォルダー構造を作成します。](../../../images/enterprise-file-system-store.png)

| **注：** |からのドキュメントの数値ファイルエントリ名を混同しないように注意してください。そのドキュメントID。 それぞれに独立したカウンターがあります。 数値のファイルエントリ名は|です。ドキュメントを格納するためのフォルダパスで使用されますが、ドキュメントIDは使用されません。 |数値のファイルエントリ名は、|の `name` 列にあります。 `@product@のデータベースのDLFileEntry` テーブル。ドキュメントIDは|にあります。同じテーブルの `fileEntryId` 列。

## Advanced File System Storeの使用

Advanced File System Storeは、Simple File System Storeと同様に、ファイルをローカルファイルシステムに保存します。 ただし、わずかに異なるフォルダー構造を使用し、プログラムで数百万のファイルに拡張できる構造を作成することにより、特定のフォルダーに格納されるファイル数に関するオペレーティングシステムの制限を克服できます。 フォルダ内のファイルをアルファベット順にネストします。 フォルダごとに保存されるファイルが少ないため、これによりパフォーマンスも向上します。

![図2：Advanced File System Storeは、Simple File System Storeよりもネストされたフォルダー構造を作成します。](../../../images/enterprise-adv-file-system-store.png)

Advanced File System Storeには、Simple File System Storeと同じルールが適用されます。 クラスタ化するには、すべてのノードがアクセスできるネットワークマウントされたファイルシステムをストアに指定する必要があります。 そのネットワーク化されたファイルシステムは、同時要求とファイルロックもサポートする必要があります。 そうしないと、2人のユーザーが2つの異なるノードから同時に同じファイルに書き込もうとすると、データ破損の問題が発生する可能性があります。

Advanced File System Storeの使用方法については、 [ドキュメントリポジトリ構成ガイド](/docs/7-1/deploy/-/knowledge_base/d/using-the-advanced-file-system-store) を参照してください。

## Amazon Simple Storage Serviceの使用

AmazonのSimple Storage Service（S3）は、@product@で使用できるクラウドベースのストレージソリューションです。 すべてのノードからシームレスにドキュメントをクラウドに保存できます。

サービスにサインアップすると、Amazonがアカウントにリンクする一意のキーを割り当てます。 Amazonのインターフェースでは、地域ごとに最適化されたデータの *バケット* を作成できます。 あなたの仕様にこれらを作成した後、続く [の手順](/docs/7-1/deploy/-/knowledge_base/d/using-amazon-simple-storage-service) @製品にリポジトリを接続するために、@。

詳細については、AmazonのS3ドキュメントを参照してください。
