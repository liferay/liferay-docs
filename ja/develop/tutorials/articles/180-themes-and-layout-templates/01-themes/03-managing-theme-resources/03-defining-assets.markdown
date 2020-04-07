---
header-id: defining-assets-for-the-resources-importer
---

# リソースインポーターのアセットの定義

[TOC levels=1-4]

`sitemap.json` ファイルは、インポートするサイトまたはサイトテンプレートのページと、これらのページのレイアウトテンプレート、ポートレット、およびポートレットプリファレンスを定義します。 テーマに含めるアセットに関する詳細を提供することもできます。 `asset.json` ファイルを使用すると、この情報を提供できます。 テーマの `[theme-name]/ src / WEB-INF / src / resources-importer` フォルダーに `asset.json` 作成します。

タグはどのアセットにも適用できます。 要約と小さな画像をWebコンテンツの記事に適用できます。 たとえば、次の `asset.json` ファイルは、 `company_logo.png` イメージの`つのタグ、 <code>カスタムTitle.xml` Webコンテンツ記事の1タグ、 `子Webコンテンツの要約と小さいイメージを指定します。 1. json` 記事の構造：

    {
        "assets": [
            {
                "name": "company_logo.png",
                "tags": [
                    "logo",
                    "company"
                ]
            },
            {
                "name": "Custom Title.xml",
                "tags": [
                    "web content"
                ]
            },
            {
                "abstractSummary": "This is an abstract summary.",
                "name": "Child Web Content 1.json",
                "smallImage": "company_logo.png"
            }
        ]
    }

これで、Webコンテンツのアセットを構成する方法がわかりました。

## 関連トピック

[Resources Importer用のWebコンテンツの準備と整理](/docs/7-1/tutorials/-/knowledge_base/t/preparing-and-organizing-web-content-for-the-resources-importer)

[Resources Importerのサイトマップを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-sitemap-for-the-resources-importer)

[テーマのリソースをインポートする場所を指定する](/docs/7-1/tutorials/-/knowledge_base/t/specifying-where-to-import-your-themes-resources)
