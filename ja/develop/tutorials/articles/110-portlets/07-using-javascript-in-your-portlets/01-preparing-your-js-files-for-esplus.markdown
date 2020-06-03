---
header-id: preparing-your-javascript-files-for-esplus
---

# ES2015 +用のJavaScriptファイルの準備

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

JavaScriptファイルでES2015 +構文を使用するには、拡張子 `.es` を名前に追加します。 たとえば、ファイル `filename.js` を `filename.es.js`名前変更します。 この拡張機能は、ES2015 +構文を使用することを示しているため、展開する前に [Babel](https://babeljs.io/) 変換する必要があります。

`polyfill-babel` モジュールから `polyfillBabel` クラスをインポートすると、 [ジェネレーター](https://babeljs.io/docs/learn-es2015/#generators)などのES2015 +の高度な機能を使用できます。

    import polyfillBabel from 'polyfill-babel'

[Babel Polyfill](http://babeljs.io/docs/usage/polyfill/) は、完全なES6環境をエミュレートします。 大量のコードをロードするため、独自の判断で使用してください。 <https://github.com/zloirock/core-js#core-js> を調べて、ポリフィルされているものを確認できます。

モジュールの作成が完了したら、バンドルの名前とバージョンを指定する `package.json` ファイルを作成してモジュールを公開できます。 これをモジュールのルートフォルダーに作成してください。 以下に、 `js-logger` モジュールの `package.json` ファイルの例を示します。

    {
        "name": "js-logger",
        "version": "1.0.0"
    }

Module Config Generatorは、この情報に基づいてモジュールを作成します。 そこにあります！ ほんの数ステップで、最新のJavaScript標準機能を活用して公開するモジュールを準備できます。

## 関連トピック

[ポートレットでES2015 +モジュールを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-esplus-modules-in-your-portlet)
