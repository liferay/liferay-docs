---
header-id: archiving-your-sites-resources
---

# サイトリソースのアーカイブ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマにリソースを含めるには、 `sitemap.json` が推奨されますが、サイトのデータをLAR（Liferay Archive）ファイルにエクスポートすることもできます。 LARファイルはバージョン固有です。エクスポート元以外の@product@のどのバージョンでも動作しません。 ただし、この方法では、サイトマップやその他のファイルが必要ないため、設定が少なくて済みます。 そのため、同じバージョンの@product@でエクスポートされたリソースを使用していて、Liferay Marketplaceのテーマ用ではない場合、LARファイルを好む場合があります。

`archive.lar`を作成するには、サイトスコープを使用してサイトのコンテンツをエクスポートします。 次に、 `archive.lar` ファイルをテーマの `[theme-name]/ src / WEB-INF / src / resources-importer` フォルダーに配置します。 LARアーカイブには、 `sitemap.json` ファイルまたは `[theme-name]/ src / WEB-INF / src / resources-importer` フォルダー内の他のファイルは必要ありません。

## 関連トピック
