---
header-id: including-layout-templates-with-a-theme
---

# テーマにレイアウトテンプレートを含める

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

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
