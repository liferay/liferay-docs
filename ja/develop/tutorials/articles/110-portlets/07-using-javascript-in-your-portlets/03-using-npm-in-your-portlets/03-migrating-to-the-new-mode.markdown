---
header-id: migrating-your-project-to-use-the-new-mode
---

# liferay-npm-bundlerの新しいモードを使用するためのプロジェクトの移行

[TOC levels=1-4]

liferay-npm-bundlerの以前のバージョンでは、バンドラーが実行される前に、ビルドがいくつかの前処理を行い、次にバンドラーが前処理されたファイルからの出力を変更しました。以下のビルドスクリプトの例を示します。

``` json
{
  "scripts":{
    "build": "babel --source-maps -d build src && liferay-npm-bundler"
  }
}
```

新しいモードでは、liferay-npm-bundlerがwebpackなどのプロセス全体を担当し、一連のルールを介して構成されます。 以下に示すように、ビルドスクリプトは凝縮されています。

``` json
{
  "scripts":{
    "build": "liferay-npm-bundler"
  }
}
```

以下の手順に従って、新しい構成モードを使用するようにプロジェクトを移行します。

1.  プロジェクトの `package.json` ファイルを開き、lifebuild-npm-bundlerのみを使用するように `build` スクリプトを更新します。

    ``` json
    {
      "scripts":{
        "build": "liferay-npm-bundler"
      }
    }
    ```

2.  プロジェクトの `.npmbundlerrc` ファイルでバンドラーが使用するルールを定義します（たとえば、ファイルをトランスパイルするためにbabelを実行する）。 以下の設定例では、 `babel-loader` を使用してJavaScriptファイルをトランスパイルするためのルールを定義しています。 デフォルトローダーの完全なリストについては、 [デフォルトローダーリファレンス](/docs/7-1/reference/-/knowledge_base/r/default-liferay-npm-bundler-loaders) を参照してください。 [Bundler](/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-loaders-for-the-liferay-npm-bundler) 用のカスタムローダーの作成の手順に従って、カスタムローダーを作成します。 liferay-npm-bundlerはbabelで `/ src /` の `* .js` ファイルを処理し、デフォルトの `/ build /` フォルダーに結果を書き込みます。

    ``` json
    {
      "sources": ["src"],
      "rules": [
        {
          "test": "\\.js$",
          "exclude": "node_modules",
          "use": [
            {
              "loader": "babel-loader",
              "options": {
                "presets": ["env"]
              }
            }
          ]
        }
      ]
    }
    ```

    |**注：** liferay-npm-bundlerの新しいモードは非常に機能します。 webpackに似ていますが、webpackは単一のJSバンドルファイルを作成し、| liferay-npm-bundlerはAMDローダーを対象としていますが、互換性はありません。

## 関連トピック

  - [デフォルトのliferay-npm-bundlerローダー](/docs/7-1/reference/-/knowledge_base/r/default-liferay-npm-bundler-loaders)
  - [liferay-npm-bundlerのローダーを理解する](/docs/7-1/reference/-/knowledge_base/r/understanding-liferay-npm-bundlers-loaders)
