---
header-id: upgrading-data-schemas-in-development
---

# 開発中のデータスキーマのアップグレード

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

モジュールを開発する際、いくつかのデータベーススキーマの変更を繰り返す必要がある場合があります。 スキーマの変更が完了した新しいモジュールバージョンをリリースする前に、正式な [データアップグレードプロセス](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)作成する必要があります。 それまでは、ビルド自動アップグレード機能を使用して、スキーマの変更をその場でテストできます。

| **注**：Liferay Portal 6.x Service Builderポートレットでは、| `build.auto.upgrade` `service.propertiesのプロパティ` Liferayサービスを適用|サービスの再構築およびポートレットの再デプロイ時にスキーマが変更されます。 @product-ver@では、このプロパティは非推奨です。 | | Build Auto Upgrade機能が、グローバルプロパティ | `schema.module.build.auto.upgrade` が、`[Liferay_Home]/portal-developer.properties`ファイルにに追加されました。

グローバルプロパティ `schema.module.build.auto.upgrade` を `true` は、サービスビルド番号が増加した再デプロイされたモジュールにモジュールスキーマの変更を適用します。 モジュールの `service.properties` ファイルの `build.number` プロパティは、サービスビルド番号を示します。 Build Auto Upgradeは、既存のデータをマッサージせずにスキーマの変更を実行します。 作成された列のデータを空のままにし、削除され名前が変更された列からデータを削除し、削除され名前が変更されたテーブルからデータを孤立させます。

Build Auto Upgradeはデータベースを迅速かつ自動的に更新しますが、適切なデータアップグレードを保証するものではありません [データアップグレードプロセス](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)を介して実装します。 ビルド自動アップグレードは、開発目的専用です。

| **警告**：実稼働環境ではビルド自動アップグレード機能を使用しないでください。 @product@ |実稼働環境でのビルド自動アップグレードはサポートしていません。 ビルドの自動アップグレードは開発目的のみ。 本番環境で有効にすると、データが失われたり、|不適切なデータアップグレード。 実稼働環境では、` portal-developer.properties`内の`schema.module.build.auto.upgrade `プロパティを`false`に設定したままにします。

デフォルトでは、 `schema.module.build.auto.upgrade` は `false`設定されています。 モジュールの最初のデプロイメントでは、 `schema.module.build.auto.upgrade` 値に関係なく、モジュールのテーブルが生成されます。

次の表は、スキーマの変更に対するBuild Auto Upgradeの処理をまとめたものです。

| スキーマ変更                                  | 結果                                       |
|:--------------------------------------- |:---------------------------------------- |
| 列を追加                                    | 新しい空の列を作成します。                            |
| 列の名前を変更                                 | 既存の列を削除し、そのデータをすべて削除します。 新しい空の列を作成します。   |
| 列を削除                                    | 既存の列を削除し、そのデータをすべて削除します。                 |
| @product@の組み込みデータソースでテーブルを作成または名前変更します。 | 既存のテーブルとそのすべてのデータを孤立させます。 新しいテーブルを作成します。 |

すばらしいです\！ これで、ビルド自動アップグレード開発者機能の使用方法がわかりました。

## 関連トピック

[モジュールのデータアップグレードプロセスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)
