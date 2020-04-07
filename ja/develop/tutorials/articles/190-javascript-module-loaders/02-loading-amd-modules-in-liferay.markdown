---
header-id: loading-amd-modules-in-liferay
---

# LiferayでAMDモジュールをロードする

[TOC levels=1-4]

モジュール化されたJavaScriptコードは、非同期モジュール定義（AMD）と呼ばれるJavaScript言語の仕様です。 [Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader) は、AMDモジュールのロードに使用できるネイティブローダーです。 このチュートリアルでは、Liferay AMD Module Loaderの使用方法について説明します。

| **注：** AMDローダーは手動で構成できますが、以下をお勧めします。 |を使用します [liferay-npm-bundler](/docs/7-1/tutorials/-/knowledge_base/t/using-npm-in-your-portlets) |代わりに。

## ローダー用のAMDモジュールの構成

次の手順に従って、モジュールを準備します。

1.  AMDモジュールコードを、以下に示すような `Liferay.Loader.define（）` メソッドでラップします。
   
        Liferay.Loader.define('my-dialog', ['my-node', 'my-plugin-base'], 
        function(myNode, myPluginBase) {
           return {
               log: function(text) {
                   console.log('module my-dialog: ' + text);
               }
           };
        });

2.  別のモジュールがトリガーされたとき、または条件が満たされたときにモジュールをロードするように構成を変更できます。 以下の構成では、開発者が `my-test` モジュールを要求した場合にこのモジュールをロードするように指定しています。
   
        Liferay.Loader.define('my-dialog', ['my-node', 'my-plugin-base'], 
        function(myNode, myPluginBase) {
           return {
               log: function(text) {
                   console.log('module my-dialog: ' + text);
               }
           };
        }, {
           condition: {
               trigger: 'my-test',
               test: function() {
                   var el = document.createElement('input');
       
                   return ('placeholder' in el);
               }
           },
           path: 'my-dialog.js'
        });

    Liferay AMD Loaderは、定義と、リストされた依存関係、および指定された他の構成を使用して、 `config.json` ファイルを作成します。 この構成オブジェクトは、使用可能なモジュール、モジュールの場所、必要な依存関係をローダーに伝えます。 以下は、生成された `config.json` ファイルの例です。
   
        {
            "frontend-js-web@1.0.0/html/js/parser": {
                "dependencies": []
            },
            "frontend-js-web@1.0.0/html/js/list-display": {
                "dependencies": ["exports"]
            },
            "frontend-js-web@1.0.0/html/js/autocomplete": {
                "dependencies": ["exports", "./parser", "./list-display"]
            }
        }

3.  モジュールをスクリプトにロードします。 モジュール名を `Liferay.Loader.require` メソッドに渡します。 以下の例は、 `my-dialog`というモジュールをロードします。
   
        Liferay.Loader.require('my-dialog', function(myDialog) {
            // your code here
        }, function(error) {
            console.error(error);
        });

| **注：** デフォルトでは、AMDローダーは7秒でタイムアウトします。 Liferay以来| DXP Fix Pack 3およびLiferay Portal 7.1 CE GA 2では、この値を構成できます|システム設定から。 [コントロールパネル]を開き、[ *構成* 移動し* 。 → *システム設定* → *プラットフォーム* → *インフラ* *JavaScriptローダー*。 *モジュール定義タイムアウト* 構成を|に設定します。必要な時間に *[保存]*をクリックします。</p>

## 関連トピック

[AUIスクリプトを使用したモジュールのロード](/docs/7-1/tutorials/-/knowledge_base/t/loading-modules-with-aui-script)

[ポートレットでnpmを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-npm-in-your-portlets)
