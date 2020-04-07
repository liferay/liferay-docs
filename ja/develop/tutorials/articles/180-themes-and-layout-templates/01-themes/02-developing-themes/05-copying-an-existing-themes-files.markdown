---
header-id: copying-an-existing-themes-files
---

# 既存のテーマのファイルをコピーする

[TOC levels=1-4]

テーマの開発をすぐに開始したい場合は、既存のテーマのファイルをコピーして、その上にビルドできます。 `gulp kickstart` タスクは、このプロセスを自動化します。 別のテーマのcss、画像、js、およびテンプレートを独自の `src` ディレクトリにコピーします。 これは、テーマをベーステーマまたはテーマレットで拡張するのに似ていますが、別のテーマからのキックスタートは1回の継承であり、別のテーマからの拡張は、すべてのベーステーマの上に `src` ファイルを適用する動的な継承ですビルドします。

| **注：** gulpキックスタートタスクは、既存のテーマのファイルを独自のファイルにコピーします|同じ名前のファイルを上書きする可能性があります。 注意して進めてください。

| **注：** Gulpは、生成されたテーマのローカル依存関係として含まれているため、|インストールする必要はありません。 |を実行してアクセスできます。 `node_modules \ .bin \ gulp` 後に、生成されたテーマのGulpタスクが続きます|ルートフォルダ。

テーマをキックスタートするには、次の手順を実行します。

1.  テーマのルートフォルダーに移動し、 `gulp kickstart`を実行します。

    ![図1： <code>gulp kickstart</code> タスクを実行して、テーマのファイルを独自のテーマにコピーします。](../../../../images/theme-dev-kickstarting-themes-gulp-kickstart.png)

2.  テーマの場所を選択します。 グローバルにインストールされたテーマまたはnpmレジストリに公開されたテーマからファイルをコピーできます。

    | **注：** テーマをグローバルにインストールするには、|から `npm link` コマンドを実行します。テーマのルートフォルダ。

    ![図2：グローバルにインストールされたテーマからファイルをコピーできます。](../../../../images/theme-dev-kickstarting-themes-global-theme.png)

3.  テーマのファイルが独自のテーマにコピーされ、開発が始まります。

    ![図3：キックスタートタスクは別のテーマのファイルを独自のファイルにコピーし、ファイルを上書きする可能性があります。](../../../../images/theme-dev-kickstarting-themes-copied-files.png)

## 関連トピック

[テーマのファイルを構築する](/docs/7-1/tutorials/-/knowledge_base/t/building-your-themes-files)

[テーマ用に再利用可能なコードを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)

[テーマの展開](/docs/7-1/tutorials/-/knowledge_base/t/deploying-your-theme)
