---
header-id: defining-layout-templates-in-a-sitemap
---

# サイトマップでレイアウトテンプレートを定義する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

`sitemap.json` ファイルで最初に宣言する必要があるのは、デフォルトのレイアウトテンプレートIDです。そのため、ターゲットサイトまたはサイトテンプレートは、ページに使用するレイアウトテンプレートを参照できます。 ページの範囲外で定義された場合、 `layoutTemplateId` はテーマのページのデフォルトのレイアウトテンプレートを設定します。

    {
      "layoutTemplateId": "2_columns_ii",
      "publicPages": [
        {
          "friendlyURL": "/my-page",
          "name": "My Page",
          "title": "My Page"
        }
      ]  
    }

ページ構成内に配置されると、 `layoutTemplateId` は個々のページのレイアウトテンプレートを設定します。 次の例では、非表示ページとウェルカムページの両方でデフォルトの `2_columns_ii` レイアウトテンプレートが使用されますが、カスタムレイアウトページではデフォルトのレイアウトテンプレートがオーバーライドされ、代わりに `2_columns_i` レイアウトテンプレートが使用されます。

    {
      "layoutTemplateId":"2_columns_ii",
      "publicPages": [
          {
            "friendlyURL": "/welcome-page",
            "name": "Welcome",
            "title": "Welcome"
          },
          {
            "friendlyURL": "/custom-layout-page",
            "name": "Custom Layout Page",
            "title": "Custom Layout Page",
            "layoutTemplateId": "2_columns_i"
          },
          {
            "friendlyURL": "/hidden-page",
            "name": "Hidden Page",
            "title": "Hidden Page",
            "hidden": "true"
          }
      ]
    }

## 関連トピック

[Resources Importer用のWebコンテンツの準備と整理](/docs/7-1/tutorials/-/knowledge_base/t/preparing-and-organizing-web-content-for-the-resources-importer)

[サイトマップでのポートレットの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-portlets-in-a-sitemap)

[テーマのリソースをインポートする場所を指定する](/docs/7-1/tutorials/-/knowledge_base/t/specifying-where-to-import-your-themes-resources)
