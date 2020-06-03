---
header-id: creating-a-thumbnail-preview-for-your-theme
---

# テーマのサムネイルプレビューを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマをサイトページに適用する場合、サイトセレクターで利用可能なテーマのリストから選択する必要があります。 各テーマの唯一の識別は、テーマの名前と、テーマの短い印象を与える小さなサムネイルプレビュー画像です。 デフォルトでは、テーマはサムネイル画像を提供しないため、作成する必要があります。 テーマの配色を開発する場合、これはさらに重要です。 カラースキームの名前は表示されないため、それらを識別するにはサムネイルプレビューが必要です。

![図1：テーマのサムネイルが残りの利用可能なテーマと共に表示されます。](../../../../images/theme-dev-theme-thumbnail-default.png)

次の手順に従って、テーマのサムネイルプレビューを作成します。

1.  テーマをサイトに適用し、オプションの配色を選択して、それを最もよく表すスクリーンショットを撮ります。

2.  不要な領域を切り取り、スクリーンショットのサイズを高さ150ピクセル、幅120ピクセルに変更します。 適切に表示するには、サムネイル *がこれらの正確な寸法* なければなりません。

3.  画像を `thumbnail.png` という名前の `.png` ファイルとして保存し、テーマの `src / images` フォルダーに配置します（このフォルダーが存在しない場合は作成します）。 再デプロイすると、 `thumbnail.png` ファイルが自動的にテーマのサムネイルになります。

| **注：** | [テーマビルダーGradleプラグイン](/docs/7-1/reference/-/knowledge_base/r/theme-builder-gradle-plugin) | `thumbnail.png` ファイルを認識しません。 このプラグインを使用してビルドする場合|代わりにテーマを作成するには、テーマの|に `screenshot.png` ファイルを作成する必要があります。高さ1080ピクセル、幅864ピクセルの `画像` フォルダー。 サムネイルは|テーマの作成時にスクリーンショットから自動的に生成されます。

これで、テーマを適用すると、そのサムネイルがサイトで利用可能な他のテーマとともに表示されます。

![図2：テーマのサムネイルが残りの利用可能なテーマと共に表示されます。](../../../../images/theme-dev-theme-thumbnail-custom.png)

## 関連トピック

[テーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[テーマ用に再利用可能なコードを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)

[テーマの配色の作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-color-schemes-for-your-theme)
