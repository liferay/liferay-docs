---
header-id: changing-your-base-theme
---

# 基本テーマの変更

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマが [ビルド](/docs/7-1/tutorials/-/knowledge_base/t/building-your-themes-files)になると、 `gulp extend` タスクを使用して、テーマのベーステーマを変更できます。

| **注：** Gulpは、生成されたテーマのローカル依存関係として含まれているため、|インストールする必要はありません。 |を実行してアクセスできます。 `node_modules \ .bin \ gulp` 後に、生成されたテーマのGulpタスクが続きます|ルートフォルダ。

基本テーマを変更するには、次の手順に従います。

1.  テーマのルートフォルダーに移動し、 `gulp extend` を実行し、オプション1を選択して、テーマが拡張するベーステーマを変更します。

    ![図1： <code>gulp extend</code> タスクを実行して、ベーステーマを変更するか、テーマレットをインストールします。](../../../../images/theme-dev-changing-base-themes-gulp-extend-base-theme.png)

2.  拡張する基本テーマを選択します。 デフォルトでは、 [Liferay Theme Generator](https://github.com/liferay/generator-liferay-theme) 作成されたテーマは、 [スタイルのテーマ](https://www.npmjs.com/package/liferay-theme-styled)基づいています。 スタイル付きまたはスタイルなしのベーステーマ、グローバルにインストールされたテーマ、npmレジストリで公開されたテーマを拡張するか、パッケージURLを指定できます。 選択するオプションの番号を入力します。

    | **注：** パッケージのURLを取得するには、|を実行します。 `npm show package-name dist.tarball`。

    ![図2：スタイル付きまたはスタイルなしのベーステーマ、グローバルにインストールされたテーマ、またはnpmレジストリに公開されたテーマを拡張できます。](../../../../images/theme-dev-changing-base-themes-gulp-extend-base-theme-choice.png)

    | **注：** クラシックテーマは、既存のベーステーマの実装です|したがって、拡張することは意図されていません。 Liferayのクラシックテーマの拡張|強くお勧めしません。

3.  テーマの `package.json` は、更新されたベーステーマ構成が含まれています。
   
        {
          ...
            "liferayTheme": {
                "baseTheme": "styled",
                "screenshot": "",
                "rubySass": false,
                "templateLanguage": "ftl",
                "version": "7.1"
            },
          ...
        }

いつ [ビルドあなたのテーマのファイル](/docs/7-1/tutorials/-/knowledge_base/t/building-your-themes-files) または [デプロイそれ](/docs/7-1/tutorials/-/knowledge_base/t/deploying-your-theme)、あなたのテーマが更新され、ベーステーマのファイルを継承します。

## 関連トピック

[テーマのアプリサーバーの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-themes-app-server)

[テーマの展開](/docs/7-1/tutorials/-/knowledge_base/t/deploying-your-theme)

[テーマの拡張機能リスト](/docs/7-1/tutorials/-/knowledge_base/t/listing-your-themes-extensions)
