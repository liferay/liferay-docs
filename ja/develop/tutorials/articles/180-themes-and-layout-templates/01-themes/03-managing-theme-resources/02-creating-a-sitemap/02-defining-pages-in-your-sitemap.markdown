---
header-id: defining-pages-in-a-sitemap
---

# サイトマップ内のページの定義

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

サイトマップは、サイトまたはサイトテンプレートが使用するレイアウト（ページ）を定義します。 このチュートリアルでは、サイトマップ内のページで使用可能な構成オプションについて説明します。

| **注：** ページはデフォルトでサイトテンプレートにインポートされます。 サイトテンプレート|パブリックページセットまたはプライベートページセットのインポートのみをサポートします。両方。 | |例に示すように、パブリックページセットとプライベートページセットの両方をインポートする場合 `sitemap.json` 以下、必要です| [リソースをサイト](/docs/7-1/tutorials/-/knowledge_base/t/specifying-where-to-import-your-themes-resources#importing-resources-into-existing-site-templates-and-sites)インポートします。

ページの名前、タイトル、わかりやすいURL、非表示かどうかなどを指定できます。 次の例では、 [デフォルトレイアウトテンプレート](/docs/7-1/tutorials/-/knowledge_base/t/defining-layout-templates-in-a-sitemap) と、サイトにインポートするパブリックページセットとプライベートページセットの両方を定義しています。

    {
      "layoutTemplateId": "2_columns_ii",
      "privatePages": [
          {
            "friendlyURL": "/private-page",
                "name": "Private Page",
                "title": "Private Page"
          }
      ],
      "publicPages": [
          {
            "friendlyURL": "/welcome-page",
            "nameMap": {
                "en_US": "Welcome",
                "fr_FR": "Bienvenue"
            },
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

ページ構成用に `layouts` 要素を構成することにより、子ページを作成できます。

    {      
    "friendlyURL": "/parent-page",
    "layouts": [
        {
            "friendlyURL": "/child-page-1",
            "name": "Child Page 1",
            "title": "Child Page 1"
        },
        {
            "friendlyURL": "/child-page-2",
            "name": "Child Page 2",
            "title": "Child Page 2"
        }
    ],
    "name": "Parent Page",
    "title": "Parent Page"
    }

これらの例では、使用可能なページ構成属性の一部を使用しています。 完全なリストを以下に示します。

**colorSchemeId：** ページに使用するデフォルトの配色とは異なる配色（IDによる）を指定します。

**列：** ページの列の内容を指定します。

**friendlyURL：** ページの分かりやすいURLを設定します。

**hidden：** ページを非表示にするかどうかを設定します。

**layoutCss：** テーマの後に読み込むページのカスタムCSSを設定します。

**layoutPrototypeLinkEnabled：** ページがページテンプレートに加えられた変更を継承するかどうかを設定します（ページに変更がある場合）。

**layoutPrototypeName：** ページに使用するページテンプレートを（名前で）指定します。 これが定義されている場合、ページテンプレートのUUIDは名前を使用して取得され、 `layoutPrototypeUuid` は必要ありません。

**layoutPrototypeUuid：** ページに使用するページテンプレートを（UUIDで）指定します。 `layoutPrototypeName` が定義されている場合、これは必要ありません。

**レイアウト：** ページセットの子ページを指定します。

**name：** ページの名前。

**nameMap：** 複数の名前キー/値のペアを持つ名前オブジェクトを渡します。 上記の例に示すように、これを使用してページのタイトルの翻訳を渡すことができます。

**privatePages：** プライベートページを指定します。

**publicPages：** 公開ページを指定します。

**themeId：** ページに使用する `sitemap.json` にバンドルされているデフォルトのテーマとは異なるテーマを（IDで）指定します。

**title：** ページのタイトル。

**type：** ページタイプを設定します。 デフォルト値は `ポートレット` （空のページ）です。 可能な値は `コピー` （このサイトのページのコピー）、 `埋め込ま`、 `full_page_application`、 `link_to_layout`、 `、ノード` （ページセット）、 `、パネル`、 `ポートレット`、及び `のURL` （リンクURLへ）。

**typeSettings：** ページ `タイプ`設定（キー/値ペアを使用）を指定します。

## 関連トピック

[Resources Importer用のWebコンテンツの準備と整理](/docs/7-1/tutorials/-/knowledge_base/t/preparing-and-organizing-web-content-for-the-resources-importer)

[サイトマップでレイアウトテンプレートを定義する](/docs/7-1/tutorials/-/knowledge_base/t/defining-layout-templates-in-a-sitemap)

[テーマのリソースをインポートする場所を指定する](/docs/7-1/tutorials/-/knowledge_base/t/specifying-where-to-import-your-themes-resources)
