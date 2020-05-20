---
header-id: using-external-javascript-libraries
---

# 外部JavaScriptライブラリの使用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ポートレットで外部JavaScriptライブラリを使用できます（つまり、デフォルトで含まれているMetal.js、jQuery、またはLodash以外のもの）。 外部ライブラリを利用可能にするために使用できる方法がいくつかあります。 選択する方法は、使用する予定の外部ライブラリとそれらの使用方法によって異なります。

このチュートリアルでは、JavaScriptローダーに外部ライブラリを適応させる方法について説明します。

## UMDをサポートするためのライブラリの構成

ライブラリの所有者である場合、 [UMD](https://github.com/umdjs/umd) （ユニバーサルモジュール定義）をサポートしていることを確認する必要があります。 以下に示すテンプレートを使用して、UMDをサポートするようにコードを構成できます。

    // Assuming your "module" will be exported as "mylibrary"
    (function (root, factory) {
        if (typeof Liferay.Loader.define === 'function' && Liferay.Loader.define.amd) {
            // AMD. Register as a "named" module.
            Liferay.Loader.define('mylibrary', [], factory);
        } else if (typeof module === 'object' && module.exports) {
            // Node. Does not work with strict CommonJS, but
            // only CommonJS-like environments that support module.exports,
            // like Node.
            module.exports = factory();
        } else {
            // Browser globals (root is window)
            root.mylibrary = factory();
      }
    }(this, function () {
    
        // Your library code goes here
        return {};
    }));

次に、ホストするライブラリの使用方法を学習できます。

## ホストするライブラリを使用する

ライブラリをホストしている（CDNからロードしていない）場合、Liferay AMD Loaderを非表示にしてライブラリを使用する必要があります。 次の手順を実行します：

1.  コントロールパネルを開き、 *構成* → *システム設定*ます。

2.  *Platform* → *Infrastructure*下の *JavaScript Loader* クリックします。

3.  `公開グローバル` オプションをオフにします。

| **注：** このオプションのチェックを外すと、|を使用できなくなります。 `Liferay.Loader.define` または `Liferay.Loader.require` アプリ内の関数。また、| AMD互換のサードパーティライブラリを使用している場合、停止する可能性があります。彼らは通常グローバル関数を使用するため、このオプションをオフにした後に動作します| `require（）` または `define（）`。

これで、LiferayのJavaScriptローダーに外部ライブラリを適応させる方法がわかりました。

## 関連トピック

[Liferay AMDモジュールローダー](/docs/7-1/tutorials/-/knowledge_base/t/loading-amd-modules-in-liferay)

[ポートレットでES2015 +モジュールを使用する](/docs/7-1/tutorials/-/knowledge_base/t/preparing-your-javascript-files-for-esplus)
