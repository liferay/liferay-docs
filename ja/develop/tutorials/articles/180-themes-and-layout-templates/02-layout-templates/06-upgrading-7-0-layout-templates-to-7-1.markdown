---
header-id: upgrading-7-0-layout-templates-to-7-1
---

# 7.0レイアウトテンプレートの7.1へのアップグレード

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product@ 7.0レイアウトテンプレートを@product-ver@にアップグレードする場合、レイアウトテンプレートバージョンを7.1にアップグレードする必要があります。

1.  レイアウトテンプレートの `liferay-plugin-package.properties` ファイルを開きます。

2.  `liferay-versions` プロパティを `7.1.0+`更新します。

``` properties
liferay-versions=7.1.0+
```

3.  変更を保存します。

| **Note:** Velocity layout templates are supported, but deprecated as of | @product-ver@. Velocityレイアウトテンプレートを|に変換することをお勧めします。できるだけ早くFreeMarker。 参照| [レイアウトテンプレートの手動作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-manually#understanding-the-anatomy) |更新された構文の例については。

## 関連トピック

[Liferayテーマジェネレーターを使用したレイアウトテンプレート](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-with-the-themes-generator)

[レイアウトテンプレートを手動で作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-manually)

[テーマにレイアウトテンプレートを含める](/docs/7-1/tutorials/-/knowledge_base/t/including-layout-templates-with-a-theme)
