---
header-id: obtaining-dependency-npm-package-descriptors
---

# OSGiバンドルの依存関係npmパッケージ記述子の取得

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

npmポートレットの作成中に、依存関係パッケージまたはそのモジュールを参照する必要がある場合があります。 たとえば、npm依存関係パッケージモジュールのCSSファイルを取得して、それをポートレットで使用できます。 [`NPMResolver` OSGiコンポーネント](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html) は、OSGiバンドルの依存関係npmパッケージ記述子を取得するための2つのメソッドを提供します： [`getDependencyJSPackage（）`](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html#getDependencyJSPackage) 依存関係npmパッケージを取得する [`resolveModuleName（）`](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html#resolveModuleName) このチュートリアルでは、以下の `package.json` 参照して、これらの方法を説明します。

    {
        "dependencies": {
            "react": "15.6.2",
            "react-dom": "15.6.2"
        },
        .
        .
        .
    }

OSGiバンドルのnpm依存パッケージを取得するには、パッケージの名前を `getDependencyJSPackage（）` メソッドの引数として渡します。 以下の例は、 `反応` 依存パッケージを解決します。

    String reactResolvedId = npmResolver.getDependencyJSPackage("react");

`reactResolvedId`の結果値は `react@15.6.2`です。

`resolveModuleName（）` メソッドを使用して、npm依存パッケージのモジュールを取得できます。 これを行うには、モジュールの相対パスを `resolveModuleName（）` メソッドの引数として渡します。 次の例では、 `react` 依存パッケージの `react-with-addons` というモジュールを解決します。

    String resolvedModule = 
    npmResolver.resolveModuleName("react/dist/react-with-addons");

`resolveModule` 変数は、 `react@15.6.2/dist/react-with-addons`評価されます。 以下の例に示すように、これを使用して、npmパッケージ内の静的リソース（CSSまたはイメージファイルなど）を参照することもできます。

``` 
String cssPath = npmResolver.resolveModuleName(
      "react/lib/css/main.css"); 
```

これで、OSGiバンドルの依存関係のnpmパッケージ記述子を取得する方法がわかりました！

## 関連トピック

[npmモジュールのパッケージを参照する](/docs/7-1/tutorials/-/knowledge_base/t/referencing-an-npm-modules-package)

[npmパッケージを含むOSGiバンドルの構造](/docs/7-1/reference/-/knowledge_base/r/the-structure-of-osgi-bundles-containing-npm-packages)

[@product@がnpmパッケージを公開する方法](/docs/7-1/reference/-/knowledge_base/r/how-liferay-portal-publishes-npm-packages)
