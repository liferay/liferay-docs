---
header-id: preparing-an-upgrade-to-liferay-7-ja
---

# @product-ver@へのアップグレードの準備

[TOC levels=1-4]

アップグレード前のデータクリーニングとデータの正規化のプロセスが完了したので、これで@product-ver@にアップグレードをするための環境が準備できました。準備の手順の概要は、以下のとおりです。

**手順1**：マーケットプレイスアプリをアップグレードする

**手順2**：すべての変更をステージングサイトから本番サイトに公開する

**手順3**：重複したWebコンテンツの構造フィールド名を削除する

**手順4**：@product@の完全なバックアップを同期する

**手順5**：ポータルのプロパティを更新する

**手順6**：ドキュメントとメディアのファイルストアを設定する

**手順7**：@product-ver@と最新のフィックスパックをインストールする

**手順8**：アップグレードプロセス中のインデックス作成を無効にする

このチュートリアルでは、上記の手順について詳しく説明します。

## 手順1：マーケットプレイスアプリをアップグレードする

使用しているマーケットプレイスのアプリ（Kaleo、カレンダー、通知など）をそれぞれインストールのために最新バージョンにアップグレードします。アップグレードを実行する前に、これらのアプリに関する問題のトラブルシューティングしておいてください。

## 手順2：すべての変更をステージングサイトから本番サイトへ公開する

[ローカル/リモートステージングを有効](/discover/portal/-/knowledge_base/7-1/enabling-staging)にし、コンテンツやデータをステージングサイトに保存した場合、本番サイトに[公開](/discover/portal/-/knowledge_base/7-1/publishing-staged-content-efficiently)する必要があります。この手順を飛ばした場合、システムには最後の公開日以降に変更されたコンテンツがわからないため、アップグレード後に完全公開（または手動で変更を公開）する必要があります。

## 手順3：重複したWebコンテンツの構造フィールド名を削除する

Webコンテンツ管理システムを広範囲に使用したことがある場合は、フィールド名が固有ではない構造があるかもしれません。 アップグレードする前に、[重複するフィールド名を見つけて削除する](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#find-and-remove-duplicate-field-names)必要があり ます。以前にLiferay Portal 6.2にアップグレードしてこれをスキップしていた場合に、このエラーが発生します。

    19:29:35,298 ERROR [main][VerifyProcessTrackerOSGiCommands:221] com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once
    com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once

この場合、Liferay 6.2の以前のバックアップにロールバックして、[重複するフィールド名を 見つけて削除します](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#find-and-remove-duplicate-field-names)。

## 手順4：@product@の完全なバックアップを同期する

[@product@のデータベース、インストール先、およびドキュメントライブラリストアをバックアップします。](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation-ja)

## 手順5：ポータルのプロパティを更新する

要件に合わせてインストールをカスタマイズするために、ポータルプロパティが上書きされている可能性があります。そうであれば、@product-ver@と互換性を持つために、プロパティファイル（`portal-setup-wizard.properties`や`portal-ext.propertiesファイル`など）を更新する必要があります。これを行う際には、現在のバージョンから@product-ver@までのすべてのバージョンの@product@のプロパティの変更を考慮する必要があります。

Liferay Portal 6.2より前のバージョンから移行する場合は、以下のプロパティに関連した更新から始めます。

- Liferay Portal 6.1 を使用している場合は、[プロパティをLiferay Portal 6.2で導入された新しいデフォルトに適合させます](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#review-the-liferay-6)。

- Liferay 6.0.12を使っている場合は 、[Image Galleryを移行します](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay#migrate-your-image-gallery-images)。

- シャード環境がある場合は、[シャーディング用のアップグレードを設定します](/discover/deployment/-/knowledge_base/7-0/upgrading-sharded-environment)。

新しいバージョンの@product@がリリースされた際に、多くの場合はデフォルト設定が変更されていますが、今回のリリースでも同じことが言えます。古いバージョンのデフォルトを使用する場合は、変更内容を確認してから、古いバージョンのデフォルトをそのまま使用するか、新しいバージョンのデフォルトを使用するかを決定してください。

7.1で変更された6.2のプロパティのリストは次のとおりです。

    users.image.check.token=false
    organizations.types=regular-organization,location
    organizations.rootable[regular-organization]=true
    organizations.children.types[regular-organization]=regular-organization,location
    organizations.country.enabled[regular-organization]=false
    organizations.country.required[regular-organization]=false
    organizations.rootable[location]=false
    #organizations.children.types[location]=
    organizations.country.enabled[location]=true
    organizations.country.required[location]=true
    layout.set.prototype.propagate.logo=true
    editor.wysiwyg.portal-web.docroot.html.taglib.ui.discussion.jsp=simple
    web.server.servlet.check.image.gallery=true
    blogs.trackback.enabled=true
    discussion.comments.format=bbcode
    discussion.max.comments=0
    dl.file.entry.thumbnail.max.height=128
    dl.file.entry.thumbnail.max.width=128

モジュール化された機能のプロパティは変更されたため、[OSGi設定ファイル](/discover/portal/-/knowledge_base/7-1/system-settings#exporting-and-importing-configurations)に個別にデプロイする必要があり ます。 
[7.1のポータルプロパティリファレンスドキュメント](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)に、プロパティの詳細と例があります。

## 手順6：ドキュメントとメディアのファイルストアを設定する

次に、ドキュメントとメディアの設定を確認します。アップグレードプロセスを実行する前に、[ドキュメントライブラリーのドキュメンテーション](/discover/deployment/-/knowledge_base/7-1/document-repository-configuration-ja)を見て、@product-ver@のすべてのオプションを確認しておいてください。
ただし、変更された設定には重要な部分が2つあります。

- ファイルストアの場所は、ポータルプロパティファイルでは指定されなくなりました。（例：`portal-ext.properties`）。
- ストア実装パッケージの名前が`com.liferay.portlet.documentlibrary.store`から`com.liferay.portal.store.*`に変更されたため、6.2 `portal-ext.properties`から名前をコピーするのではなく、適切な更新済みの実装名を設定する必要があります。 

デフォルトのストアを使用していても、ファイルをデフォルトの場所（`[Liferay Home]/data/document_library`）に保存したくない場合は、`[Liferay Home]/osgi/configs`フォルダーに`com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.config`というファイルを作成し、下記の内容を追加します。

    rootDir="{document_library_path}"

`{document_library_path}`をドキュメントライブラリへのパスに置き換えます。

Advanced File System Storeメソッドを使用してドキュメントライブラリファイルを永続化する場合は、そのファイル名を`com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config`にします。

設定ファイル名は、設定を実装するクラスの名前と一致していなければなりません。

詳細については、[ドキュメントリポジトリ設定のドキュメンテーション](/discover/deployment/-/knowledge_base/7-1/document-repository-configuration-ja)を参照してください。

## 手順7：@product-ver@をインストールする

次に、[@product@をアプリケーションサーバーにインストールする](/discover/deployment/-/knowledge_base/7-1/deploying-product-ja)か 、[選択したアプリケーションサーバーにバンドルされている@product@を使用します](/discover/deployment/-/knowledge_base/7-1/installing-liferay)。

それから 、最新のフィックスパックをインストールします。

**重要**：@product-ver@をインストールしたら、**起動しないでください**。Liferay Portal 6.2以前では、アップグレードのためにシステムを準備した後は、新しいバージョンを初めて起動したときにアップグレードプロセスが実行されていました。サーバーの起動を効率化するために、@product@にはデータベースのアップグレードに使用する必要があるアップグレードツール（次の記事で説明）が付属しています。

| 注：アップグレードツールを実行する前に@product-ver@を起動しようとすると、@product@は次の例外をスローします。
| 
|     [MainServlet:237] java.lang.RuntimeException: まず、@product@7000にアップグレードしてください

前の手順で更新したカスタムポータルプロパティファイル（`portal-ext.properties`など）とドキュメントとメディアのファイルストアを新しいインストールにコピーします。

## 手順8：アップグレードプロセス中のインデックス作成を無効にする

新しいインストールでアップグレードプロセスを開始する前に、インデクサーがコンテンツのインデックスを再作成しようとしたときに生じるアップグレードプロセスでのパフォーマンスの問題を防ぐために、インデックスの作成を無効にする必要があります。

インデックス作成を無効にするには、`[Liferay Home]/osgi/configs`フォルダに`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`というファイルを作成し、次の内容を追加します。

    indexReadOnly="true"

アップグレードを完了したら（次の記事で説明します）、`.config`ファイルを削除するか、`indexReadOnly="false"`を設定して、インデックス作成を再度有効にします。

 次の記事では、アップグレードの方法について説明します。
