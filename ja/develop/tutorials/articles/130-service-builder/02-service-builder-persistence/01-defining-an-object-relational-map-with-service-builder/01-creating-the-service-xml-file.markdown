---
header-id: creating-the-service-xml-file
---

# service.xmlファイルの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ポートレットプロジェクトのサービスを定義するには、 `service.xml` ファイルを作成する必要があります。 DTD（Document Type Declaration）ファイル [liferay-service-builder \ _7 \ _1 \ _0.dtd](https://docs.liferay.com/ce/portal/7.1-latest/definitions/liferay-service-builder_7_1_0.dtd.html) は、使用するXMLの形式と要件を指定します。 `service.xml` ファイルを手動で作成するか、Liferay @ \<ide@\>使用できます。 @ide@を使用すると、 `service.xml` ファイルを</code>つずつ作成し、DTDに準拠したXMLを作成する際の当て推量を排除できます。

Bladeまたは@ide@テンプレートからプロジェクトを作成した場合、 `* -service` モジュールのルートフォルダーには、 *Foo*という名前の `エンティティ` 要素を持つ `service.xml` ファイルがあります。 `<entity name="Foo" ...> 全体を削除します... </entity>` 要素：これは単なる例です。 実用的ではありません。

`service.xml` ファイルがまだない場合は、 `* -service` モジュールのルートフォルダーにファイルを作成し、ファイルを開きます。 Liferay @ide@には、ダイアグラムモードとソースモードが用意されており、 `service.xml` ファイルのサービス情報をさまざまな観点から見ることができます。

  - **図モード** 、サービスエンティティ間の関係の作成と視覚化を促進します。
  - **ソースモード** は、エディターで `service.xml` ファイルの未加工XMLコンテンツを表示します。

これらのモードを切り替えることができます。

次に、サービスのグローバル情報を指定します。
