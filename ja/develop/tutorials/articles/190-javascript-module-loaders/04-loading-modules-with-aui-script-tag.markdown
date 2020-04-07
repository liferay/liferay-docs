---
header-id: loading-modules-with-aui-script
---

# AUIスクリプトを使用したモジュールのロード

[TOC levels=1-4]

`aui：script` タグは、ページ上のスクリプトタグにJavaScriptをロードするJSPタグであり、特定のリソースが実行前に確実にロードされるようにします。

| **Note:** AUI is deprecated and no longer in active development in | @product-ver@, but all the tags will remain fully functional in @product-ver@. |最終的に、これらのタグは|に置き換えられます。 [粘土](https://claycss.com/) |対応するタグ。

## aui：scriptを使用する

`aui：script` タグは、次のオプションをサポートしています。

  - `require`： [Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader)ロードするにはAMDモジュールが必要です。
  - `use`：YUIローダーを介してロードされるAlloyUI / YUIモジュールを使用します。
  - `位置`：スクリプトタグがページに配置される位置。 可能なオプションは `インライン` または `auto`です。
  - `サンドボックス`：匿名関数でスクリプトタグをラップするかどうか。 `設定した場合、`に加えて、ラッピングに加えて、jQueryとアンダースコアに `$` と `_` が定義されます。

次に、ES2015および [Metal.js](https://metaljs.com/) モジュールをロードする方法を学習できます。

## ES2015およびMetal.jsモジュールのロード

`aui：script` を使用して、次のようにES2015およびMetal.jsモジュールをロードできます。

    <aui:script require="metal-clipboard/src/Clipboard">
        new metalClipboardSrcClipboard.default();
    </aui:script>

または、次の例に示すように、モジュール名の後にvariableName</code> として `を追加して、モジュールの変数を指定できます。</p>

<pre><code><aui:script require="metal-clipboard/src/Clipboard as myModule">
    new myModule.default();
</aui:script>
`</pre>

これにより、登録された `Clipboard.js` の依存関係が解決され、すべての依存関係が満たされ、要求されたモジュールが安全に実行できるようになるまで順番にロードされます。

ブラウザでは、 `aui：script` 以下に示す完全なHTMLに変換されます。

    <script type="text/javascript">
        Liferay.Loader.require("metal-clipboard/src/Clipboard", 
        function(metalClipboardSrcClipboard) {
            (function() {
                new metalClipboardSrcClipboard.default();
            })()
        }, function(error) {
            console.error(error)
        });
    </script>

次に、AlloyUIモジュールをロードする方法を学習できます。

## AlloyUIモジュールのロード

`使用` 属性を使用してAlloyUI / YUIモジュールをロードできます。

    <aui:script use="aui-base">
        A.one('#someNodeId').on(
            'click',
            function(event) {
                alert('Thank you for clicking.')
            }
        );
    </aui:script>

これにより、 `aui-base` AlloyUIコンポーネントがロードされ、 `aui：script`内のコードで使用できるようになります。

ブラウザでは、 `aui：script` 以下に示す完全なHTMLに変換されます。

    <script type="text/javascript">
        AUI().use("aui-base",
            function(A){
                A.one('#someNodeId').on(
                    'click',
                    function(event) {
                        alert('Thank you for clicking.')
                    }
                );
            }
        );
    </script>

次に、AlloyUIモジュールとES2015およびMetal.jsモジュールをロードする方法を学習できます。

## AlloyUIモジュールとES2015およびMetal.jsモジュールを一緒にロードする

`aui：script`ES2015モジュールまたはMetal.jsモジュールとともにAUIモジュールをロードすることもできます。 `aui：script` タグは、同じ構成で `が` と `が` 属性を使用することをサポートしていません。 でも心配しないで。 `aui：script`'s `require` 属性を使用してES2015およびMetal.jsモジュールをロードし、スクリプト内で `AUI（）。use（）` 関数を使用してAUIモジュールをロードできます。 以下に設定例を示します。

    <aui:script require="path-to/metal/module">
     AUI().use(
        'liferay-aui-module', 
        function(A) {
            let var = pathToMetalModule.default;
        }
    );
    </aui:script>

これで、 `aui：script` tag \を使用してモジュールをロードする方法がわかりました。

## 関連トピック

[外部JavaScriptライブラリの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-external-javascript-libraries)

[AMDモジュールの読み込み](/docs/7-1/tutorials/-/knowledge_base/t/loading-amd-modules-in-liferay)
