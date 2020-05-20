---
header-id: configuring-service-properties
---

# service.propertiesの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

このチュートリアルでは、 `service.properties` ファイルを使用および編集する方法について説明します。 また、プロパティと、ニーズに合わせてプロパティを設定する方法についても説明します。

Service Builderは、 `* -service` モジュールの `src / main / resources` フォルダーに `service.properties` ファイルを生成します。 @product@は、このファイルのプロパティを使用して、サービスのデータベーススキーマを変更します。 このファイルを直接変更するのではなく、同じフォルダー内の `service-ext.properties` ファイルで必要なプロパティをオーバーライドする必要があります。

`service.properties` ファイルに含まれるプロパティの一部を次に示します。

  - `build.namespace`：これは [、あなたの中で定義された名前空間 `service.xmlに`](/docs/7-1/tutorials/-/knowledge_base/t/defining-an-object-relational-map-with-service-builder)。 Liferayは、名前空間を使用して異なるモジュールを互いに区別します。
  - `build.number`：Liferayは、モジュールのさまざまなService Builderビルドを区別します。 別個のService BuilderビルドをLiferayにデプロイするたびに、Liferayはこの数を増やします。
  - `build.date`：これは、モジュールの最新のService Builderビルドの時間です。
  - `include-and-override`：このプロパティのデフォルト値は、 `service-ext.properties` を `service.properties`オーバーライドファイルとして定義します。

| **注**：Liferay Portal 6.x Service Builderポートレットでは、| `build.auto.upgrade` `service.propertiesのプロパティ` Liferayサービスを適用|サービスの再構築およびポートレットの再デプロイ時にスキーマが変更されます。 @product-ver@では、このプロパティは非推奨です。 | |ビルドの自動アップグレード機能が異なり、グローバルプロパティで設定されます| `schema.module.build.auto.upgrade` ファイル内| `[Liferay_Home]/portal-developer.properties`。 詳細については、チュートリアルを参照してください| [開発中のデータスキーマのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-data-schemas-in-development)。

驚くばかり\！ これで、独自の `service-ext.properties` ファイルをセットアップするために必要なすべてのツールが手に入りました。

## 関連トピック

[サービスビルダーとは](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder)

[ローカルサービスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-local-services)
