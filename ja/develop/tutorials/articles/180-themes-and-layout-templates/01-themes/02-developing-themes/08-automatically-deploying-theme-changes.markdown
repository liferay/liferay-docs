---
header-id: automatically-deploying-theme-changes
---

# テーマの変更の自動展開

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

変更を行うたびにテーマを手動でデプロイする必要があることに気づいたかもしれません。 これは、開発プロセス中に退屈になる可能性があります。 `gulp watch` タスクを使用すると、完全に再デプロイすることなく、テーマの変更をプレビューできます。

| **注：** Gulpは、生成されたテーマのローカル依存関係として含まれているため、|インストールする必要はありません。 |を実行してアクセスできます。 `node_modules \ .bin \ gulp` 後に、生成されたテーマのGulpタスクが続きます|ルートフォルダ。

テーマへの変更を自動的にプレビューするには、次の手順に従います。

1.  サーバーで [開発者モード](/docs/7-1/tutorials/-/knowledge_base/t/using-developer-mode-with-themes) 有効にします。 これを有効にしないと、gulp監視タスク **は機能しません**。

2.  テーマのルートフォルダーに移動し、 `gulp watch`を実行します。 これにより、アプリケーションサーバーのプロキシ（`http：// localhost：9080`）がセットアップされ、ブラウザーの新しいウィンドウで開かれます。 また、ローカルネットワークに接続されているすべてのデバイスのアプリサーバーを表示するためのIPアドレスも提供します。 ブラウザは、指定されたIPアドレスを使用するすべてのデバイス間で同期されます。

    | **注：** ライブ変更は、ポート `のみ表示可能です9080` | （`http：// localhost：9080`）。 ライブ変更 **はアプリで表示できません** サーバー（例： `http：// localhost：8080`）。

    ![図1： <code>gulp watch</code> タスクを実行して、テーマへの変更を自動的にデプロイします。](../../../../images/theme-dev-watching-themes-gulp-watch-startup.png)

    テーマの `liferay-theme.json` ファイルに `webBundleDir` プロパティが存在することを確認することで、監視タスクが実行されていることを確認できます。 </code>監視する値 `が必要です。</p>

<pre><code> {
   "LiferayTheme": {
     "appServerPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\tomcat-8.0.32",
     "deployPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\deploy",
     "url": "http://localhost:8080",
     "appServerPathPlugin": "C:\\Users\\liferay\\Desktop\\projects\\themes\\7-1-themes\\my-liferay-theme\\.web_bundle_build",
     "deployed": true,
     "pluginName": "my-liferay-theme",
     "webBundleDir": "watching"
   }
 }
`</pre></li>

3

テーマを変更して、ファイルを保存します。 更新されたファイルはビルドされ、コンパイルされ、ポート `9080`直接コピーされます。 CSSの変更はライブで展開されるため、ページをリロードする必要はありません。 JSとテンプレートの変更について、 **あなたがしなければならない** 変更を確認するには、ブラウザをリロードしてください。

    ![図2：監視タスクは、変更が展開されたことを通知します。](../../../../images/theme-dev-watching-themes-gulp-watch-auto-deploy.png)

4

プレビューした変更に満足したら、テーマをアプリサーバーに展開して変更を適用します。</ol>

## 関連トピック

[テーマのアプリサーバーの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-themes-app-server)

[既存のテーマのファイルをコピーする](/docs/7-1/tutorials/-/knowledge_base/t/copying-an-existing-themes-files)

[テーマの展開](/docs/7-1/tutorials/-/knowledge_base/t/deploying-your-theme)
