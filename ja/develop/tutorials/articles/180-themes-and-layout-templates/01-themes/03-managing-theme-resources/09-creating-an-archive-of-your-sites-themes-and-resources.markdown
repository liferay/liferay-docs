---
header-id: archiving-your-sites-resources
---

# サイトリソースのアーカイブ

[TOC levels=1-4]

テーマにリソースを含めるには、 `sitemap.json` が推奨されますが、サイトのデータをLAR（Liferay Archive）ファイルにエクスポートすることもできます。 LARファイルはバージョン固有です。エクスポート元以外の@product@のどのバージョンでも動作しません。 ただし、この方法では、サイトマップやその他のファイルが必要ないため、設定が少なくて済みます。 そのため、同じバージョンの@product@でエクスポートされたリソースを使用していて、Liferay Marketplaceのテーマ用ではない場合、LARファイルを好む場合があります。

`archive.lar`を作成するには、サイトスコープを使用してサイトのコンテンツをエクスポートします。 次に、 `archive.lar` ファイルをテーマの `[theme-name]/ src / WEB-INF / src / resources-importer` フォルダーに配置します。 LARアーカイブには、 `sitemap.json` ファイルまたは `[theme-name]/ src / WEB-INF / src / resources-importer` フォルダー内の他のファイルは必要ありません。

## 関連トピック
