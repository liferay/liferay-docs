---
header-id: overwriting-and-extending-liferay-theme-tasks
---

# Liferayテーマタスクの上書きと拡張

[TOC levels=1-4]

Liferay Theme Generatorで作成されたテーマは、テーマの開発とビルド（ビルド、デプロイ、監視など）に必要な標準機能を提供するいくつかのデフォルトのgulpテーマタスクにアクセスできます。 ただし、テーマをサーバーにデプロイする前に、テーマのファイルで追加のプロセス（JavaScriptファイルの縮小など）を実行することもできます。 LiferayテーマジェネレーターのAPIは、 `hookFn` プロパティを公開します。これにより、デフォルトのgulpテーマタスクにフックして、独自のロジックを注入できます。

デフォルトのLiferayテーマタスクにフックするには、次の手順に従います。

1.  フックまたは上書きするgulpタスクまたはサブタスクを特定します。 タスクとそのサブタスクは、 [`liferay-theme-tasks`](https://github.com/liferay/liferay-themes-sdk/tree/master/packages/liferay-theme-tasks) パッケージの [`タスク` フォルダー](https://github.com/liferay/liferay-themes-sdk/tree/master/packages/liferay-theme-tasks/tasks) の `[task-name].js` ファイルにリストされています。 たとえば、gulp `ビルド` タスクとサブタスクは、 [`build.js` ファイル](https://github.com/liferay/liferay-themes-sdk/blob/master/packages/liferay-theme-tasks/tasks/build.js#L25-L39)定義されています。
   
        'build:clean',
        'build:base',
        'build:src',
        'build:web-inf',
        'build:liferay-look-and-feel',
        'build:hook',
        'build:themelets',
        'build:rename-css-dir',
        'build:prep-css',
        'build:compile-css',
        'build:fix-url-functions',
        'build:move-compiled-css',
        'build:remove-old-css-dir',
        'build:fix-at-directives',
        'build:r2',
        'build:war',

2.  テーマの `gulpfile.js` ファイルを開き、 `liferayThemeTasks.registerTasks（）` メソッドを見つけます。 このメソッドは、デフォルトのgulpテーマタスクを登録します。 `hookFn` プロパティを `registerTasks（）` メソッドの構成オブジェクトに追加し、 `gulp` インスタンスを必ず渡すようにします。
   
        liferayThemeTasks.registerTasks({
          gulp: gulp,
          hookFn: function(gulp) {
       
          }
        });

3.  `hookFn（）` 関数内で、 `gulp.hook（）` メソッドを使用して、フックするテーマタスクまたはサブタスクを指定します。 `before：` または `after：` キーワードをプレフィックスとして付けることにより、タスクの前後にコードを挿入できます。 または、 `gulp.task（）` メソッドを使用して、gulpタスクを上書きできます。 どちらのメソッドにも2つのパラメーターがあります。フックするタスクまたはサブタスクと、 `done` を呼び出すか、注入したいロジックを含むストリームを返すコールバック関数です。 以下に、構成パターンの例をいくつか示します。

    ``` 
    liferayThemeTasks.registerTasks({
      gulp: gulp,
      hookFn: function(gulp) {
        gulp.hook('before:build:src', function(done) {
          // Fires before build:src task
        });

        gulp.hook('after:build', function(done) {
          // Fires after build task
        });

        gulp.task('build:base', function(done) {
          // Overwrites build:base task
        });
      }
    });   
    ```

以下の例は、 `build：war` サブタスクの前に起動し、テーマの `build` フォルダーのJavaScriptファイルを読み取り、 `gulp-uglify` モジュールでそれらを縮小し、 `./build/js` 戻します。フォルダー、 `done`呼び出し、最後にJavaScriptが縮小されたことをログに記録します。 従うには、テーマの `gulpfile.js` を以下に示す内容に置き換え、 [gulp-uglify](https://www.npmjs.com/package/gulp-uglify) モジュールと [fancy-log](https://www.npmjs.com/package/fancy-log) モジュールをインストールし、 `gulp deploy`を実行します。

    'use strict';
    
    var gulp = require('gulp');
    var log = require('fancy-log');
    var uglify = require('gulp-uglify');
    var liferayThemeTasks = require('liferay-theme-tasks');
    
    liferayThemeTasks.registerTasks({
       gulp: gulp,
       hookFn: function(gulp) {
         gulp.hook('before:build:war', function(done) {
          // Fires before build `war` task
          gulp.src('./build/js/*.js')
          .pipe(uglify())
          .pipe(gulp.dest('./build/js'))
          .on('end', done);
          log('Your JS is now minified...');
          });
       }
    });

次のような出力が表示されます。

    [15:58:07] Finished 'build:r2' after 198 ms
    [15:58:07] Starting 'build:war'...
    [15:58:07] Your JS is now minified...
    [15:58:07] Starting 'plugin:version'...
    [15:58:07] Finished 'plugin:version' after 2.52 ms

| **注：** `フック` コールバック関数は、 `done` 引数を呼び出すか、|を返す必要があります。ストリーム。

これで、デフォルトのLiferayテーマタスクにフックして上書きする方法がわかりました！

## 関連トピック

[テーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[テーマ用に再利用可能なコードを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)

[テーマで開発者モードを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-developer-mode-with-themes)
