---
header-id: listing-your-themes-extensions
---

# テーマの拡張機能リスト

[TOC levels=1-4]

テーマがどの基本テーマ/テーマを拡張するかを知る必要がありますか？ そのためのgulp タスクがあります。 テーマの` package.jsonを手動で確認できますが`この 情報については、` gulpステータス`タスクはこの情報を表示します。 テーマのルートフォルダーに移動し、 `gulp status` を実行して、テーマの拡張機能を表示します。

![図1： <code>gulp status</code> タスクを実行して、テーマの現在の拡張機能を一覧表示します。](../../../../images/theme-dev-listing-theme-extensions.png)

| **注：** Gulpは、生成されたテーマのローカル依存関係として含まれているため、|インストールする必要はありません。 |を実行してアクセスできます。 `node_modules \ .bin \ gulp` 後に、生成されたテーマのGulpタスクが続きます|ルートフォルダ。

## 関連トピック

[基本テーマの変更](/docs/7-1/tutorials/-/knowledge_base/t/changing-your-base-theme)

[テーマのアプリサーバーの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-themes-app-server)

[テーマ用に再利用可能なコードを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)
