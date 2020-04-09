---
header-id: migrating-a-liferay-npm-bundler-project-from-1-x-to-2-x
---

# liferay-npm-bundlerプロジェクトの1.xから2.xへの移行

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

liferay-npm-bundlerの最新の2.xバージョンを使用する必要があります。 [はより安定性が高く、すぐに使用できる](/docs/7-1/reference/-/knowledge_base/r/changes-between-liferay-npm-bundler-1x-and-2x)機能がより多く含まれています。 すでに1.xバージョンを使用してプロジェクトを作成している場合、心配する必要はありません。 次の手順に従って、プロジェクトを2.xに移行します。

1.  `package.json` の `liferay-npm-bundler` 依存関係をバージョン2.xに更新します。
   
        {
          "devDependencies": {
            ...
            "liferay-npm-bundler": "^2.0.0",
            ...
          },
          ...
        }

2.  すべて削除 `のLiferay-NPM-バンドラ-PRESET- *` あなたからの依存関係を `package.json` のLiferay-NPM-バンドラ2.xは、デフォルトではこれらが含まれているため。

3.  `.npmbundlerrc` ファイルで設定したバンドラープリセットを削除します。 liferay-npm-bundler 2.xには、すべてのフレームワークを自動的に処理する1つのスマートプリセットが含まれています。

これらは、すべてのプロジェクトに共通する標準要件です。 残りの手順は、プロジェクトのフレームワークによって異なります。 対応するセクションの指示に従って、プロジェクトの移行を完了します。

## プレーンJavaScript、ビルボードJS、JQuery、Metal JS、React、またはVue JSプロジェクトの移行

最初に説明した手順を実行した後、これらの残りの手順に従って、以下に示すフレームワークプロジェクトを2.xに移行します。

  - プレーンJSプロジェクト
  - Billboard.jsプロジェクト
  - JQueryプロジェクト
  - Metal.jsプロジェクト
  - Reactプロジェクト
  - Vue.jsプロジェクト

ソースファイルを変換するにはBabelが必要ですが、Bundler 1.xが課したプロジェクトから変換に使用するBabelプリセットを削除する必要があります。 liferay-npm-bundler 2.xは、デフォルトでこれらの変換を処理します。

1.  プロジェクトの `.babelrc` ファイルから *liferay-project* プリセットを削除します。 残るのは、以下に示す `es2015` プリセットのみです。
   
        {
          "presets": ["es2015"]
        }

    プロジェクトでReactを使用している場合は、 `反応` プリセットもそのままにしてください。
   
        {
          "presets": ["es2015", "react"]
        }

2.  `package.json`から `babel-preset-liferay-project` 依存関係を削除します。

Angularプロジェクトを移行する場合は、次のセクションの手順に従ってください。

## Angularプロジェクトの移行

最初に説明した手順を実行した後、これらの残りの手順に従って、Angularプロジェクトを2.xに移行します。 liferay-npm-bundler 1.xはいくつかの変換手順を実行するためにBabelに依存していましたが、これらの変換はバージョン2.xで自動的に適用されるようになりました。したがって、プロジェクトからBabelを削除する必要があります。

1.  `tsconfig.json` ファイルを開き、 `"module"： "amd"` コンパイラオプションを以下に示す構成に置き換えて、CommonJSモジュールを生成します。
   
        {
          "compilerOptions": {
            ...
            "module": "commonjs",
            ...
          }
        }

2.  `.babelrc` ファイルを削除して、Babel構成を削除します。

3.  `package.json` ビルドプロセスからBabelを削除して、以下の設定と一致するようにします。
   
        {
          "scripts": {
            "build": "tsc && liferay-npm-bundler"
          },
          ...
        }

4.  `package.json` *devDependencies*から次のBabel依存関係を削除します。
   
        "babel-cli": "6.26.0",
        "babel-preset-liferay-amd": "1.2.2"

## 関連トピック

[AMD用のnpmモジュールのフォーマット](/docs/7-1/tutorials/-/knowledge_base/t/formatting-your-npm-modules-for-amd)

[ポートレットでNPMResolver APIを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-the-npmresolver-api-in-your-portlets)

[liferay-npm-bundler 1.xと2.xの間の変更点](/docs/7-1/reference/-/knowledge_base/r/changes-between-liferay-npm-bundler-1x-and-2x)
