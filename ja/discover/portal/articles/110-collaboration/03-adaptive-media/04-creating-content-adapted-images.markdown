---
header-id: creating-content-with-adapted-images
---

# 適応した画像を使用したコンテンツの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アダプティブメディアは、ブログやウェブコンテンツの作成者にはほとんど見えません。 コンテンツに画像が追加されると、アプリは舞台裏で動作し、使用中のデバイスに適した画像を配信します。 コンテンツ作成者は、画像をコンテンツに追加するときに画像を選択します-改造された画像を選択する必要はありません（選択することもできません）。 アダプティブメディアは、ユーザーがコンテンツを表示したときに最新の適応画像で置き換えられる `data-fileentryid` 属性を使用して、コンテンツのHTMLの各適応画像を識別します。 これにより、Adaptive Mediaは、コンテンツがそれらの画像の前に存在していたとしても、最新の適応画像をコンテンツに配信できます。

| **注：** アダプティブメディアがアンインストールされると、元の画像は|に表示されます。ブログのエントリとWebコンテンツの記事。

## コンテンツに適応画像を含める

アダプティブメディアは適応された画像を背後で配信するため、コンテンツ作成者は通常どおり [ブログエントリ](/docs/7-1/user/-/knowledge_base/u/publishing-blogs) および [Webコンテンツ](/docs/7-1/user/-/knowledge_base/u/creating-web-content) 画像を追加する必要があります。エディターの画像ボタンをクリックし、ファイルセレクターで画像を選択します。

ただし、いくつかの重要な注意事項があります。 ファイルセレクターを使用してブログエントリの画像を含める場合、アダプティブメディアは、[ *Blog Images*、[ *Documents and Media*、および[ *Upload* タブから追加された画像でのみ機能します。 さらに、改作された画像はブログエントリのコンテンツにのみ適用できます。カバー画像は除外されます。 アダプティブメディアは、ドラッグアンドドロップでブログエントリに追加された画像に対して機能します。これは、画像がブログ画像リポジトリに自動的にアップロードされ、調整されて、ブログエントリのコンテンツに含まれるためです。 これを確認するには、HTMLを調べ、画像に `<img>` タグと `data-fileentryid` 属性が含まれていることを確認します。

Webコンテンツの記事の場合、Adaptive Mediaは、ファイルセレクターの[ *Documents and Media* ]タブから追加された画像でのみ機能します。 ブログとは異なり、Adaptive MediaはドラッグアンドドロップでWebコンテンツの記事に追加された画像に適合した画像を配信しません。

ブログエントリとメディアコンテンツ記事の両方で、Adaptive Mediaはファイルセレクターの[ *URL* ]タブから追加された画像では機能しません。 これは、画像がURLから直接リンクされているため、Adaptive Mediaがコピーする画像ファイルを提供しないためです。

ブログエントリまたはWebコンテンツの記事を書いているときに、HTMLで `<img>` タグと `data-fileentryid` 属性を確認できることに注意してください。 コンテンツが表示されると、HTMLは自動的に置き換えられ、次のようになります。

    <picture>
    
        <source media="(max-width:850px)" 
        srcset="/o/adaptive-media/image/44147/med/photo.jpeg">
    
        <source media="(max-width:1200px) and (min-width:850px)" 
        srcset="/o/adaptive-media/image/44147/hd/photo.jpeg">
    
        <source media="(max-width:2000px) and (min-width:1200px)" 
        srcset="/o/adaptive-media/image/44147/ultra-hd/photo.jpeg">
    
        <img src="/documents/20140/0/photo.jpeg/1992-9143-85d2-f72ec1ff77a0">
    
    </picture>

この例では、それぞれ解像度が異なる3つの異なる画像を使用しています。 `source` タグはこれらの各画像を定義します。 また、元の画像（`img`）は、適応された画像が利用できない場合のフォールバックとして使用されることに注意してください。

## 構造化Webコンテンツでの適応画像の使用

適応した画像を使用するには [構造化ウェブコンテンツ](/docs/7-1/user/-/knowledge_base/u/designing-uniform-content)、コンテンツ制作者は、手動でWebコンテンツの構造の画像フィールドを含める必要があります。 次に、エディターの左側で選択することにより、一致するテンプレートのその画像フィールドを参照できます。 テンプレートに含まれる `Imagecrrf` という名前の画像フィールドのサンプルスニペットを次に示します。

    <#if Imagecrrf.getData()?? && Imagecrrf.getData() !="">
      <img data-fileentryid="${Imagecrrf.getAttribute("fileEntryId")}" 
      alt="${Imagecrrf.getAttribute("alt")}" src="${Imagecrrf.getData()}" />
    </#if>

このスニペットには、 `data-fileentryid` 属性が含まれており、Adaptive Mediaが画像を適切な画像に確実に置き換えます。 結果のWebコンテンツのHTMLをエディターのコードビューで検査すると、次のようなタグが表示されます。

    <img data-fileentryid="37308" 
    src="/documents/20143/0/photo.jpeg/85140258-1c9d-89b8-4e45-d79d5e262318?t=1518425" />

`data-fileentryid` 属性を持つ `<img>` タグに注意してください。 アダプティブメディアは、ファイルエントリIDを使用して、 `<img>` 要素を自動的に `<picture>` 要素に置き換えます。34 </code>要素には、解像度ごとに利用可能な適応画像が含まれます（上記の `<picture>` 例を参照）。

## 適応した画像のステージング

Adaptive Mediaは、@product@の [コンテンツステージング](/docs/7-1/user/-/knowledge_base/u/staging-content-for-publication) および [エクスポート/インポート](/docs/7-1/user/-/knowledge_base/u/exporting-importing-widget-data) 機能と完全に統合されています。 アダプティブメディアは、公開時に段階的なコンテンツに適応された画像を含み、それらの画像を更新して新しい解像度に一致させることができます。

同様に、適応された画像を含むコンテンツがエクスポートされると、Adaptive Mediaはそれらの画像をLARファイルにエクスポートします。 その後、そのLARファイルをインポートして、そのコンテンツをその適応されたイメージとともに復元または転送できます。

Adaptive Mediaは、エクスポート/インポートまたはステージングされたコンテンツの公開中に、適応された画像を再生成しません。 パフォーマンスを向上させるために、代わりにアダプティブメディアは既存の適応画像を再利用します。

驚くばかり\！ これで、適応した画像を含むコンテンツを作成する方法がわかりました。 また、アダプティブメディアが適応された画像をコンテンツのHTMLに含める方法も知っています。
