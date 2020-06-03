---
header-id: deploying-your-theme
---

# テーマを展開する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマをアプリサーバーにデプロイするには、 `gulp deploy` タスクを実行します。 `gulp deploy` タスク [は、テーマのファイル](/docs/7-1/tutorials/-/knowledge_base/t/building-your-themes-files)構築し、テーマ [作成したときに](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes) 構成した [アプリサーバー](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-themes-app-server)に生成されたWARファイルをデプロイします</a>。

| **注：** |を実行している場合 [Felix Gogoシェル](/docs/7-0/reference/-/knowledge_base/r/using-the-felix-gogo-shell)、| `gulp deploy：gogo` コマンドを使用してテーマをデプロイすることもできます。

| **注：** Gulpは、生成されたテーマのローカル依存関係として含まれているため、|インストールする必要はありません。 |を実行してアクセスできます。 `node_modules \ .bin \ gulp` 後に、生成されたテーマのGulpタスクが続きます|ルートフォルダ。

テーマを展開するには、次の手順に従います。

1.  テーマのルートフォルダーに移動し、 `gulp deploy`を実行します。

    ![図1： <code>gulp deploy</code> タスクを実行してテーマのファイルをビルドし、アプリサーバーに展開します。](../../../../images/theme-dev-deploying-themes-gulp-deploy.png)

2.  サーバーのログには、OSGiバンドルが開始されたことが表示されます。

    ![図2：テーマのバンドルが開始されると、サーバーのログが通知します。](../../../../images/theme-dev-deploying-themes-server-log.png)

3.  コントロールメニューの *ナビゲーション* → *サイトページ* メニューからテーマを適用できます。 サイトページの[ *構成* ]オプションを選択し、[ *現在のテーマの変更* ]ボタンをクリックしてテーマを適用します。

    ![図3： <code>gulp deploy</code> タスクを実行してテーマのファイルをビルドし、アプリサーバーにデプロイします。](../../../../images/theme-dev-deploying-themes-install-theme.png)

## 関連トピック

[テーマの変更の自動展開](/docs/7-1/tutorials/-/knowledge_base/t/automatically-deploying-theme-changes)

[既存のテーマのファイルをコピーする](/docs/7-1/tutorials/-/knowledge_base/t/copying-an-existing-themes-files)

[テーマ用に再利用可能なコードを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)
