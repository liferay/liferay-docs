---
header-id: creating-custom-loaders-for-the-liferay-npm-bundler
---

# liferay-npm-bundler用のカスタムローダーの作成

[TOC levels=1-4]

webpackはJSバンドルを作成し、liferay-npm-bundlerはAMDローダーを対象とするため、webpackのローダーはliferay-npm-bundlerと互換性がありません。 そのため、デフォルトでは [使用できないローダーを使用する場合は](/docs/7-1/reference/-/knowledge_base/r/default-liferay-npm-bundler-loaders)、カスタムローダーを作成する必要があります。

liferay-npm-bundlerの観点から見ると、ローダーは、次のシグネチャを持つデフォルトの機能をエクスポートするメインモジュールを持つnpmパッケージとして定義されます。

``` js
function(context, options){
}
```

引数は次のように定義されます。

  - `コンテキスト`：これらのフィールドを含むオブジェクト

      - `content`：処理されたファイルのコンテンツを含む文字列（ローダーのメイン入力）
      - `filepath`：ローダーで処理するファイルへのプロジェクト相対パス
      - `extraArtifacts`：プロジェクト相対パスをキーとして、プロパティの値として文字列を使用して、処理中のファイルとともに追加のファイルを出力できるオブジェクト（たとえば、ソースマップの生成に使用できます）。
      - `log`：実行情報をバンドラのレポートファイルに書き込むロガー（その構造とAPIについては、 [PluginLoggerクラス](https://github.com/liferay/liferay-js-toolkit/blob/master/packages/liferay-npm-build-tools-common/src/plugin-logger.js) を参照）。

  - `オプション`：ローダーの設定の `オプション` フィールドから取得されたオブジェクト（詳細については、 [liferay-npm-bundlerのローダーとルールの理解](/docs/7-1/reference/-/knowledge_base/r/understanding-liferay-npm-bundlers-loaders) を参照）。

| **注：** 関数は何も返さないか、変更されたコンテンツを返す場合があります。 何かある場合|返されると、 `context.content` フィールドansの先頭にコピーされ、フィードに使用されます|次のローダーまたは出力ファイルを書き込みます。 これは|と同等です。 `context.content` =「何か」。 ローダーがファイルを返さないが、|代わりに、ファイルが生成されないようにファイルをフィルタリングするだけです。 `context.content = 'undefined'`明示的に設定する必要があります。

以下の手順に従って、新しいローダーを作成します。 これらの手順では、例としてbabelローダーを使用します。

1.  ローダーにbabelなどの構成が必要な場合は、ローダーのオプションを指定できるように、以下に示すようなルール構成を定義できます。

    ``` json
    {
      "rules": [
        {
          "test": "\\.js$",
          "exclude": "node_modules",
          "use": [
            {
              "loader": "babel-loader",
              "options": {
                "presets": ["env", "react"]
              }
            }
          ]
        }
      ]
    }
    ```

2.  `index.js` ファイルを作成し、入力コンテンツを取得してローダーに渡す関数を作成し、結果とソースマップファイルを出力フォルダーに書き込みます。 以下のローダー関数は、渡されたコンテンツ（JSファイル）を取得してbabelで実行し、結果とソースマップをデフォルトの `/ build /` 出力フォルダーに書き込みます。

    ``` js
    export default function(context, options) {
// Get input parameters
const { content, filePath, log, sourceMap } = context;

// Run babel on content
const result = babel.transform(content, options);

// Create an extra .map file with source map next to source .js file
context.extraArtifacts[`${filePath}.map`] = JSON.stringify(result.map);

// Tell the user what we have done
log.info("babel-loader", "Transpiled file");

// Return the modified content
return result.code;
}
    ```

3.  `index.js` ファイルをnpmパッケージに入れて公開します。

4.  プロジェクトの `package.json`devDependencyとして作成したnpmパッケージを含めます：

    ``` json
    "devDependencies": {
      "liferay-npm-bundler": "2.12.0",
      "liferay-npm-build-support": "2.12.0",
      "liferay-npm-bundler-loader-babel-loader": "2.12.0",
      ...
    }
    ```

5.  プロジェクトの `.npmbundlerrc` ファイルの `ルール` セクションでローダーの名前を構成します。

    ``` json
    {
      "sources": ["src"],
      ...
      "rules": [
        {
          "test": "\\.js$",
          "exclude": "node_modules",
          "use": [
            {
              "loader": "babel-loader",
              "options": {
                "presets": ["env", "react"]
              }
            }
          ]
        }
      ],
      ...
    }
    ```

## 関連トピック

  - [デフォルトのliferay-npm-bundlerローダー](/docs/7-1/reference/-/knowledge_base/r/default-liferay-npm-bundler-loaders)
  - [liferay-npm-bundlerのローダーを理解する](/docs/7-1/reference/-/knowledge_base/r/understanding-liferay-npm-bundlers-loaders)
