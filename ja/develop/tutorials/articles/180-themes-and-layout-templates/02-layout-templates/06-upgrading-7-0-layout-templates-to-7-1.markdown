---
header-id: upgrading-7-0-layout-templates-to-7-1
---

# 7.0レイアウトテンプレートの7.1へのアップグレード

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
