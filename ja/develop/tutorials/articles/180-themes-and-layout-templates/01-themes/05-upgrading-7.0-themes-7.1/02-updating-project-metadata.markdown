---
header-id: updating-project-metadata
---

# プロジェクトメタデータの更新

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマが [Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)使用する場合、 [gulpアップグレードタスク](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-themes-created-with-the-theme-generator) は、アップグレードプロセスの一部としてテーマのメタデータを自動的に更新します。 テーマジェネレーター以外の環境でテーマを開発している場合は、次の手順に従ってテーマのメタデータを手動で更新します。

1.  テーマの `liferay-plugin-package.properties` ファイルを開き、 `liferay-versions` プロパティ値を `7.1.0+`ます。
   
        liferay-versions=7.1.0+

2.  `liferay-look-and-feel.xml` ファイルを開き、互換バージョンとして `7.1.0+` を指定します。
   
        <look-and-feel>
       <compatibility> <version>7.1.0+</version> </compatibility> 
   
                ...
       
        </look-and-feel>

3.  テーマでLiferay Theme Generatorを使用している場合は、 `package.json` ファイルを開き、ファイルのLiferayバージョン参照を `7.1`更新します。 `liferayTheme`の `templateLanguage` から `ftl` （Velocityテーマテンプレートはサポートされなくなったため）を更新し、 `バージョン` から `7.1`更新します。
   
        "liferayTheme": {
            ...
            "templateLanguage": "ftl",
            "version": "7.1"
        },

4.  `liferay-theme-deps-7-0` 依存関係を以下のバージョンの `liferay-theme-deps-7.1` 更新し、以下の構成例に示すように `liferay-theme-tasks` 依存関係を追加します。
   
        "devDependencies": {
            "gulp": "3.9.1",
            "liferay-theme-tasks": "8.0.0-alpha.6",
            "liferay-theme-deps-7.1": "8.0.0-alpha.6"
        },

テーマのLiferayバージョン参照が@product-ver@に更新されます。

## 関連トピック

[CSSコードの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-css-code)

[テーマの開発](/docs/7-1/tutorials/-/knowledge_base/t/developing-themes)
