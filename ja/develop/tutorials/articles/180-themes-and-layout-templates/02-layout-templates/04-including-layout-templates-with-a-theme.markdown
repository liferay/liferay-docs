---
header-id: including-layout-templates-with-a-theme
---

# テーマにレイアウトテンプレートを含める

[TOC levels=1-4]

レイアウトテンプレートは単独でデプロイできますが、テーマにバンドルすることもできます。 テーマにレイアウトテンプレートを含めるには、次の手順を実行します。

1.  テーマの `liferay-look-and-feel.xml` ファイルを開き、 `<theme>...</theme>` タグの間に `<layout-templates>` タグをネストして、以下の構成に一致するようにします。

    ``` 
    <theme id="my-theme-name" name="My Theme Name">
        ...
          <layout-templates>
            <custom>
                //layout template code goes here
            </custom>
          </layout-templates>
        ...
    </theme>  
    ```

2.  `<layout-template>` タグを使用して、 `<custom>...</custom>` タグの間にレイアウトテンプレートを配置します。 `<layout-template>` タグの `id` 属性 **は、レイアウトテンプレートのファイル名** 一致する必要があります。 以下に設定例を示します。
   
        <layout-template id="my_liferay_layout_template" 
        name="My Liferay Layout Template">

3.  以下に示すように、 `<template-path>` タグでレイアウトテンプレートのパスを指定します。
   
        <template-path>
            /layoutttpl/custom/my_liferay_layout_template.ftl
        </template-path>

4.  以下に示すように、 `<thumbnail-path>` タグで [レイアウトテンプレートサムネイルの](/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-layout-template-thumbnail-previews) パスを指定します。
   
        <thumbnail-path>
            /layoutttpl/custom/my_liferay_layout_template.png
        </thumbnail-path>

5.  完成したレイアウトをテーマの `src / layouttpl` フォルダーに配置します。 以下は、 `liferay-look-and-feel` 構成の例です。<theme id="my-theme-name" name="My Theme Name"> ... <layout-templates> <custom> <layout-template id="my_liferay_layout_template" name="My Liferay Layout Template"> <template-path> /layoutttpl/custom/my_liferay_layout_template.ftl </template-path> <thumbnail-path> /layoutttpl/custom/my_liferay_layout_template.png </thumbnail-path> </layout-template> </custom> </layout-templates> ... </theme>

これで、レイアウトテンプレートを@product@テーマに含める方法がわかりました！

## 関連トピック

[カスタムレイアウトテンプレートのサムネイルプレビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-layout-template-thumbnail-previews)

[Liferayテーマジェネレーターを使用したレイアウトテンプレート](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-with-the-themes-generator)

[レイアウトテンプレートを手動で作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-manually)
