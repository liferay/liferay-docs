---
header-id: configuring-your-themes-app-server
---

# テーマのアプリサーバーの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマが最初に [Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)で作成されたとき、アプリサーバーの場所を指定する必要がありました。 これは、 `gulp init` タスクで実行されました。 テーマはこの情報を使用して適切なサーバーにデプロイします。 開発中にアプリサーバーまたはサイトが変更された場合、 `gulp init` タスクを手動で実行することにより、構成情報を更新できます。

| **注：** Gulpは、生成されたテーマのローカル依存関係として含まれているため、|インストールする必要はありません。 |を実行してアクセスできます。 `node_modules \ .bin \ gulp` 後に、生成されたテーマのGulpタスクが続きます|ルートフォルダ。

次の手順を実行します：

1.  テーマのルートフォルダーに移動し、 `gulp init`を実行します。

    ![図1： <code>gulp init</code> タスクを実行して、アプリサーバーの構成を更新します。](../../../../images/theme-dev-server-configuration-gulp-init.png)

2.  アプリサーバーとサイトへのパスを入力します。

    ![図2： <code>gulp init</code> タスクを実行して、サイトのURLを更新することもできます。](../../../../images/theme-dev-server-configuration-gulp-init-config.png)

3.  テーマの `liferay-theme.json` ファイルには、更新されたサーバー構成情報が含まれています。
   
        {
          "LiferayTheme": {
            "appServerPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\tomcat-8.0.32",
            "deployPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\deploy",
            "url": "http://localhost:8080",
            "appServerPathPlugin": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\tomcat-8.0.32\\webapps\\my-liferay-theme",
            "deployed": false,
            "pluginName": "my-liferay-theme"
          }
        }

## 関連トピック

[テーマの変更の自動展開](/docs/7-1/tutorials/-/knowledge_base/t/automatically-deploying-theme-changes)

[基本テーマの変更](/docs/7-1/tutorials/-/knowledge_base/t/changing-your-base-theme)

[テーマの拡張機能リスト](/docs/7-1/tutorials/-/knowledge_base/t/listing-your-themes-extensions)
