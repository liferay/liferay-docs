---
header-id: installing-adaptive-media
---

# アダプティブメディアのインストール

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アダプティブメディアアプリはデフォルトで@product@にインストールされます。 次のセクションでは、Adaptive Mediaアプリのモジュールと、アニメーションGIFを処理するようにAdaptive Mediaを準備する方法について説明します。

| **注：** アダプティブメディアアプリはデフォルトでインストールされるため、|経由で更新されます。 Liferay DXPフィックスパックおよびLiferay Portal CE GAリリース。 |の使用 [Liferayマーケットプレイス](https://web.liferay.com/marketplace) | Liferay DXP 7.1でアプリを更新すると、エラーが発生します。

## アダプティブメディアのモジュール

アダプティブメディアアプリの一部のモジュールは必須であり、アダプティブメディアが機能するために有効にする必要がありますが、他のモジュールは無効にすることができます。 他のモジュールが使用するパッケージをエクスポートするAdaptive Media APIモジュールは必須です。 1つを無効にすると、それに依存する他のモジュールもすべて無効になります。 次に、Adaptive Media APIモジュールのリストを示します。

  - Liferay Adaptive Media API
  - Liferay Adaptive Media Content Transformer API
  - Liferay Adaptive Media Image API
  - Liferay適応型メディアイメージアイテムセレクターAPI

アダプティブメディアコアモジュールも必須であり、アダプティブメディアが期待どおりに機能することを保証するために有効にする必要があります。

  - Liferayアダプティブメディアドキュメントライブラリ
  - LiferayアダプティブメディアドキュメントライブラリのアイテムセレクターWeb
  - Liferay Adaptive Media Document Library Web
  - Liferayアダプティブメディア画像コンテンツトランスフォーマー
  - Liferayアダプティブメディアイメージの実装
  - Liferay適応型メディアイメージアイテムセレクターの実装
  - Liferay Adaptive Media Image JS Web
  - Liferayアダプティブメディアイメージサービス
  - Liferayアダプティブメディアイメージタグライブラリ
  - Liferay Adaptive Media Image Web
  - Liferayアダプティブメディアアイテムセレクターウェブのアップロード
  - Liferay Adaptive Media Web

アダプティブメディアブログモジュールは、ブログエントリにアップロードされた画像を確実に処理および調整できるようにするもので、オプションです。 これらのモジュールのリストは次のとおりです。

  - Liferay Adaptive Media Blogs Editorの構成
  - LiferayアダプティブメディアブログアイテムセレクターWeb
  - Liferay Adaptive MediaブログWeb
  - Liferay Adaptive MediaブログWebフラグメント

アダプティブメディアジャーナルモジュールはオプションです。 これらのモジュールは、Adaptive MediaをWebコンテンツ記事に適用します。

  - Liferay Adaptive Media Journal Editorの構成
  - Liferay Adaptive Media Journal Web

Adaptive Mediaには、さらに2つのオプションモジュールが含まれています。

  - **Liferayアダプティブメディアイメージコンテンツトランスフォーマーの下位互換性：** アダプティブメディアをインストールする前に作成されたコンテンツは、そのコンテンツを手動で編集しなくても、適応したイメージを使用できます。 起動時とユーザーがコンテンツを表示するときの両方で画像を変換します。これはパフォーマンスに悪影響を及ぼす可能性があります。 したがって、このモジュールを本番環境で使用する前に、いくつかのパフォーマンステストを実行することをお勧めします。 古いコンテンツがない場合、パフォーマンスの問題が発生している場合、または古いコンテンツに適応画像が必要ない場合は、このモジュールを無効にできます。

  - **Liferayアダプティブメディアドキュメントライブラリのサムネイル：** ドキュメントとメディアのサムネイルで適合画像を使用できます。 このために仕事に、あなたは最初にしなければならない [移行元サムネイルは、画像に適応するための](/docs/7-1/user/-/knowledge_base/u/migrating-documents-and-media-thumbnails-to-adaptive-media)。 このモジュールを有効にすることを強くお勧めしますが、必須ではありません。

すごい\！ これで、アダプティブメディアに付属している必須およびオプションのモジュールがわかりました。 次のセクションでは、アダプティブメディアでアニメーションGIFを使用するためのインストール要件について説明します。 GIFを使用する必要がない場合は、Adaptive Mediaへの画像解像度の追加に関する記事にスキップできます。

## アニメーションGIFの処理

アニメーションGIFを処理するために、Adaptive Mediaは [Gifsicle](https://www.lcdf.org/gifsicle)と呼ばれる外部ツールを使用します。 このツールは、GIFが異なる解像度に拡大縮小されたときにアニメーションが機能することを保証します。 サーバーにGifsicleを手動でインストールし、それが `PATH`にあることを確認する必要があります。 それがインストールされていますしたら、適応型メディアの中でそれを有効にする必要があります [高度な設定オプション](/docs/7-1/user/-/knowledge_base/u/advanced-configuration-options)。

Gifsicleがインストールされておらず、 `image / gif` がサポートされているMIMEタイプとして詳細構成オプションに含まれている場合、Adaptive MediaはGIFの単一フレームのみをスケーリングします。 これにより、アニメーションGIFの代わりに静止画像が生成されます。
