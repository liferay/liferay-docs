---
header-id: formatting-your-npm-modules-for-amd
---

# AMD用のnpmモジュールのフォーマット

[TOC levels=1-4]

@product@がnpmモジュールを認識するためには、Liferay AMD Loader用にフォーマットする必要があります。 幸い、liferay-npm-bundlerがこれを処理します。適切な構成を提供し、ビルドスクリプトに追加するだけです。 このチュートリアルでは、liferay-npm-bundlerを使用してnpmベースのポートレットプロジェクトをセットアップする方法を示します。

| **注：** このチュートリアルで取り上げる例と手順では、|の古いモードを使用します。 `ビルド` 追加のビルドステップを含むliferay-npm-bundler |バンドラーを実行する前のスクリプト。 webpackのようなバンドラーの新しいモード|ローダーと一連のルールを使用します。 参照| [新しいモードを使用するためのプロジェクトの移行](/docs/7-1/tutorials/-/knowledge_base/t/migrating-your-project-to-use-the-new-mode) |新しいモードを使用するようにプロジェクトを移行する方法を学習します。

以下の構造例は、このチュートリアル全体を通して参照されます。 フォローしたい場合は、ここから [をダウンロードできます](https://github.com/izaera/liferay-npm-bundler-2-example)。

  - `npm-angular5-portlet-say-hello/`
      - `META-INF/`
          - `resources/`
              - `package.json`
                  - 名前：npm-angular5-portlet-say-hello
                  - バージョン：1.0.0
                  - main：js / angular.pre.loader.js
                  - スクリプト：
                      - ビルド：tsc && liferay-npm-bundler
              - `tsconfig.json`
                  - ターゲット：es5
                  - moduleResolution：node
              - `.npmbundlerrc`
                  - 除外する：
                      - \ *：true
                  - 構成：
                      - 輸入：
                          - npm-angular5-provider：
                              - @ angular / animations：^ 5.0.0
                              - @ angular / cdk：^ 5.0.0
                              - @ angular / common：^ 5.0.0
                              - @ angular / compiler：^ 5.0.0
                          - 「」：
                              - npm-angular5-provider：^ 1.0.0
              - `js/`
                  - `indigo-pink.css`
                  - `angular.pre.loader.ts` //ブートストラップシムとプロバイダー
                  - npm-angular5-providerをインポートします。
  - `npm-angular5-provider`
      - `package.json`
          - 名前：npm-angular5-provider
          - バージョン：1.0.0
          - メイン：bootstrap.js
          - スクリプト：
              - ビルド：liferay-npm-bundler
          - 依存関係：
              - @ angular / animations：^ 5.0.0
              - @ angular / cdk：^ 5.0.0
              - @ angular / common：^ 5.0.0
              - @angular/compiler:^5.0.0
              - ...
      - `src/main/resources/META-INF/resources/`
          - `bootstrap.js` //このファイルには、Angularに必要なポリフィルが含まれており、アプリの前にロードする必要があります
              - require（ 'core-js/es6/reflect'）;
              - require（'core-js/es7/reflect'）;
              - require（'zone-js/dist/zone'）;

liferay-npm-bundlerを使用するようにプロジェクトを構成するには、次の手順に従います。

1.  NodeJSがインストールされていない場合は、\>= [v6.11.0](http://nodejs.org/dist/v6.11.0/) インストールします。

2.  ポートレットのプロジェクトフォルダーに移動し、 `package.json` ファイルがまだ存在しない場合は初期化します。

    まだポートレットがない場合は、空のMVCポートレットプロジェクトを作成します。 便宜上、 [Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/installing-blade-cli) を使用して、 [mvcポートレットブレードテンプレート](/docs/7-1/reference/-/knowledge_base/r/using-the-mvc-portlet-template)空のポートレットを作成できます。

    `package.json` ファイルがない場合は、 `npm init -y` を実行して、プロジェクトディレクトリの名前に基づいて空のファイルを作成できます。

3.  次のコマンドを実行して、liferay-npm-bundlerをインストールします。
   
        npm install --save-dev liferay-npm-bundler

    | **注：** ポートレットプロジェクトのルートフォルダ内から使用NPM（| `package.json` ファイルの生活）、あなたは通常、一般的なWebプロジェクトにそうであるように。

4.  `liferay-npm-bundler` を `package.json`のビルドスクリプトに追加して、必要なnpmパッケージをパックし、AMDに変換します。
   
        "scripts": {
       "build": "[... && ] liferay-npm-bundler"
        }

    `[...&&]` は、実行する必要のある以前の手順を示します（たとえば、ソースをBabelでトランスコンパイルする、SOYテンプレートをコンパイルする、Typescriptをトランスコンパイルするなど）。 この例では、AngularがコードをES5にトランスコンパイルするためにTypescriptコンパイラー（`tsc`）をビルドスクリプトに必要とします。

    ``` 
    "build": "tsc && liferay-npm-bundler" 
    ```

    | **注：** 可能な限り、任意の言語を使用できます。 ECMAscript 5以降にトランスコンパイルされます。 唯一の要件は次のとおりです。 | -BabelはそれらをASTに変換して処理できるようにする| -ブラウザが実行できること。 | -モジュールは `require（）` 呼び出しを使用してロードされます（この要件は、カスタマイズされたプラグインを使用することで緩和できますが、デフォルトの構成では必須です）。 | | Gradleを使用してポートレットをデプロイすると、ビルドスクリプトは|として呼び出されます。プロセスの一部。

5.  `.npmbundlerrc` ファイルを使用して、バンドラー用にプロジェクトを構成します（このファイルが存在しない場合は、プロジェクトのルートフォルダーに作成します）。 出力JARから除外するパッケージ、共有依存関係のインポートなどを指定できます。 参照してください [構成のLiferay-NPM-バンドラ参照](/docs/7-1/reference/-/knowledge_base/r/configuring-liferay-npm-bundler) 利用可能なオプションの詳細については、を。

    この例では、 `npm-angular5-portlet-say-hello` ウィジェットのすべての依存関係（ワイルドカード（`*`）シンボルを使用）を除外して、AngularがJARに表示されないようにして、ビルドプロセスを高速化し、展開を最適化します。 そのノート `NPM-angular5プロバイダー` もない名前空間（でインポートされる`「」`、そのモジュールの一つは、ブートストラップ角シムに起動されようとしているので）参照 `angular.pre.loader.ts` ファイルを、ここで `npm-angular5-provider` がインポートされます。 次に、そのインポートは、 `npm-angular5-provider`のメインファイル（`bootstrap.js`）をロードします`</p>

<pre><code> {
     ...
     "exclude": {
         "*": true
     },
     "config": {
         "imports": {
             "npm-angular5-provider": {
                 "@angular/animations": "^5.0.0",
                 "@angular/cdk": "^5.0.0",
                 "@angular/common": "^5.0.0",
                 "@angular/compiler": "^5.0.0",
                 ...
             },
             "": {
                 "npm-angular5-provider": "^1.0.0"
             }
         }
     }
 }
`</pre></li>

6

`npm install` を実行して、必要な依存関係をインストールします。

7

ビルドスクリプトを実行して、liferay-npm-bundlerに依存関係をバンドルします。
  
       npm run-script build
      </ol>

バンドラコピープロジェクトと `node_modules`"JSが出力するファイルの内部にそれらをラップ `Liferay.Loader.define（）` LiferayのAMDローダーがそれらを処理する方法を知っているように、コール。 また、 `require（）` 呼び出しおよび `Liferay.Loader.define（）` 内のモジュール名の名前空間をプロジェクトの名前プレフィックス（例では`npm-angular5-provider $` ）で呼び出して、 [依存関係の分離を実現します](/docs/7-1/reference/-/knowledge_base/r/how-liferay-portal-publishes-npm-packages#isolated-package-dependencies)。 バンドラーは、 `npm-angular5-provider` に関連する `package.json` 依存関係を注入して、実行時にそれらを利用できるようにします。 サンプルウィジェットの結果のビルドを以下に示します。

  - `npm-angular5-portlet-say-hello/`
      - `build/`
          - `resources/main/META-INF/resources`
              - `package.json`
                  - 依存関係：
                      - @ npm-angular5-provider$angular/ animations：^ 5.0.0
                      - @ npm-angular5-provider$angular/ cdk：^ 5.0.0
                      - @ npm-angular5-provider$angular/ common：^ 5.0.0
                      - @ npm-angular5-provider$angular/ compiler：^ 5.0.0
              - `js/`
                  - `angular.loader.js`
                      - Liferay.Loader.define（ "npm-angular5-portlet-say-hello@1.0.0/js/angular.loader"
                      - \ ['module'、 'exports'、 'require'、 '@ npm-angular5-provider$angular/ platform-browser-dynamic'、... \]
  - `npm-angular5-provider`
      - `package.json`
          - 名前：npm-angular5-provider
          - バージョン：1.0.0
          - メイン：bootstrap.js
          - 依存関係：
              - @ npm-angular5-provider$angular/ animations：^ 5.0.0
              - @ npm-angular5-provider$angular/ cdk：^ 5.0.0
              - @ npm-angular5-provider$angular/ common：^ 5.0.0
              - @ npm-angular5-provider$angular/ compiler：^ 5.0.0
              - ...
      - `bootstrap.js`
          - Liferay.Loader.define（'npm-angular5-provider@1.0.0/bootstrap '
          - \ ['module'、 'exports'、 'require'、 'npm-angular5-provider$core-js / es6 / reflect'、 'npm-angular5-provider$core-js / es7 / reflect'、 'npm-angular5-provider$zone.js / dist / zone '\]
      - `src/main/resources/META-INF/resources/`
          - `bootstrap.js` //このファイルには、Angularに必要なポリフィルが含まれており、アプリの前にロードする必要があります
              - require（ 'core-js / es6 / reflect'）;
              - require（ 'core-js / es7 / reflect'）;
              - require（ 'zone-js / dist / zone'）;

| **注：** デフォルトでは、AMDローダーは7秒でタイムアウトします。 Liferay以来| DXP Fix Pack 3およびLiferay Portal 7.1 CE GA 2では、この値を構成できます|システム設定から。 [コントロールパネル]を開き、[ *構成* 移動し* 。 → *システム設定* → *プラットフォーム* → *インフラ* *JavaScriptローダー*。 *モジュール定義タイムアウト* 構成を|に設定します。必要な時間に *[保存]*をクリックします。</p>

これで、liferay-npm-bundlerを使用して、Liferay AMD Loader \のnpmベースのポートレットをバンドルする方法がわかりました。

## 関連トピック

[ES2015 +用のJavaScriptファイルの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-your-javascript-files-for-esplus)
