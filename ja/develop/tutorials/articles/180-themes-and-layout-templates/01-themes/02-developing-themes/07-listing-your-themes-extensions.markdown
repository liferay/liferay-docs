---
header-id: listing-your-themes-extensions
---

# テーマの拡張機能リスト

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマがどの基本テーマ/テーマを拡張するかを知る必要がありますか？ そのためのgulp タスクがあります。 テーマの` package.jsonを手動で確認できますが`この 情報については、` gulpステータス`タスクはこの情報を表示します。 テーマのルートフォルダーに移動し、 `gulp status` を実行して、テーマの拡張機能を表示します。

![図1： <code>gulp status</code> タスクを実行して、テーマの現在の拡張機能を一覧表示します。](../../../../images/theme-dev-listing-theme-extensions.png)

| **注：** Gulpは、生成されたテーマのローカル依存関係として含まれているため、|インストールする必要はありません。 |を実行してアクセスできます。 `node_modules \ .bin \ gulp` 後に、生成されたテーマのGulpタスクが続きます|ルートフォルダ。

## 関連トピック

[基本テーマの変更](/docs/7-1/tutorials/-/knowledge_base/t/changing-your-base-theme)

[テーマのアプリサーバーの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-themes-app-server)

[テーマ用に再利用可能なコードを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)
