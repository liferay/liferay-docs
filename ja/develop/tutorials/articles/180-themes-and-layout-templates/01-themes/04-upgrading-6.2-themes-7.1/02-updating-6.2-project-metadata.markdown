---
header-id: updating-6-2-project-metadata
---

# 6.2プロジェクトメタデータの更新

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマが [Liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages)使用している場合、 [Gulp `アップグレード` タスク](/docs/7-1/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes) は、アップグレードプロセスの一部としてテーマのメタデータの一部を自動的に更新します。 以下の手順に従って、テーマのメタデータを手動で更新します。

1.  テーマの `liferay-plugin-package.properties` ファイルを開き、 `liferay-versions` プロパティ値を `7.1.0+`ます。
   
        liferay-versions=7.1.0+

2.  `liferay-look-and-feel.xml` ファイルを開き、互換バージョンとして `7.1.0+` を指定します。
   
        <look-and-feel>
       <compatibility> <version>7.1.0+</version> </compatibility> 
   
                ...
       
        </look-and-feel>

3.  `liferay-look-and-feel.xml`更新している間に、テーマで [Portlet Decorators](/docs/7-1/tutorials/-/knowledge_base/t/adding-portlet-decorators-to-a-theme)を使用できるようにします。

4.  テーマがLiferay JS Theme Toolkitを使用している場合、 [Gulp `アップグレード` タスク](/docs/7-1/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes) は `package.json` ファイルのLiferayバージョン参照を `7.1`に更新し、 `liferayTheme`の `templateLanguage` から `ftl` 更新します（Velocityテーマテンプレートはサポートされなくなったため）：
   
        "liferayTheme": {
            ...
            "templateLanguage": "ftl",
            "version": "7.1"
        },

テーマのLiferayバージョン参照が@product-ver@に更新されます。

## 関連トピック

[6.2 CSS コードのアップデート手順](/docs/7-1/tutorials/-/knowledge_base/t/updating-6-2-css-code)

[テーマの開発](/docs/7-1/tutorials/-/knowledge_base/t/developing-themes)
