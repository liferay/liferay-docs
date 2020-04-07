---
header-id: building-your-themes-files
---

# テーマのファイルを作成する

[TOC levels=1-4]

`gulp build` タスクは、ベーステーマファイルを生成し、SassをCSSにコンパイルし、すべてのテーマファイルをサーバーにデプロイできるWARファイルに圧縮します。

| **注：** Gulpは、生成されたテーマのローカル依存関係として含まれているため、|インストールする必要はありません。 |を実行してアクセスできます。 `node_modules \ .bin \ gulp` 後に、生成されたテーマのGulpタスクが続きます|ルートフォルダ。

次の手順に従って、テーマのファイルを作成します。

1.  テーマのルートフォルダーに移動し、 `gulp build`を実行します。

    ![図1： <code>gulp build</code> タスクを実行して、テーマのファイルをビルドします。](../../../../images/theme-dev-building-themes-gulp-build.png)

2.  すべてのテーマのファイルを含む新しい `ビルド` フォルダーが作成されます。 これらのファイルとフォルダーをテーマの `src` フォルダーにコピーして、テーマを変更できます。

    ![図2：ビルドフォルダーには、テーマのすべてのファイルが含まれます。](../../../../images/theme-dev-building-themes-build-folder.png)

3.  テーマのファイルは、新しい `dist` フォルダーの `war` ファイルに圧縮されます。 `war` ファイルをアプリサーバーにデプロイして、利用可能にします。

![図3：distフォルダーにはテーマのWARファイルが含まれています。](../../../../images/theme-dev-building-themes-dist-folder.png)

## 関連トピック

[テーマの変更の自動展開](/docs/7-1/tutorials/-/knowledge_base/t/automatically-deploying-theme-changes)

[既存のテーマのファイルをコピーする](/docs/7-1/tutorials/-/knowledge_base/t/copying-an-existing-themes-files)

[テーマの展開](/docs/7-1/tutorials/-/knowledge_base/t/deploying-your-theme)
