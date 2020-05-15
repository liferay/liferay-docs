---
header-id: overriding-liferays-default-yui-and-aui-modules
---

# @product@のデフォルトのYUIおよびAUIモジュールのオーバーライド

[TOC levels=1-4]

@product@には、いくつかのデフォルトのYUI/AUIモジュールが含まれています。場合によって、これらのモジュールのスクリプトによって提供される機能をオーバーライドする必要があります。これを行うには、次の3つを含むカスタムAUIモジュールを作成する必要があります。

- 変更内容を含む元のモジュールのJavaScriptファイルのコピー
- 変更されたJavaScriptファイルのパスとオーバーライドするモジュールを指定する`config.js`ファイル
- OSGiコンテナにオリジナルをオーバーライドするように指示する`bnd.bnd`ファイル

次の手順を実行します。

1. [OSGiモジュールを作成](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)して、元のモジュールをオーバーライドします。たとえば、`session-js-override-web`という名前のモジュールを作成して、@product@の`session.js`ファイルをオーバーライドできます。

2. ジュールに`src/main/resources/META-INF/resources/js`フォルダを作成し、元のJavaScriptファイルをその中にコピーして、名前を変更します。たとえば、`session.js`モジュールのコピーを作成し、`session-override.js`という名前に変更します。

3. 変更内容を適用し、ファイルを保存します。

4. 次に、[モジュールの構成ファイル（`config.js`）を記述](/docs/7-0/tutorials/-/knowledge_base/t/configuring-modules-for-products-loaders#writing-the-configuration-file)して、オーバーライドを適用します。 `config.js`ファイルをモジュールの`src/main/resources/META-INF/resources/js`フォルダに追加します。以下の`config.js`ファイルの例では、[YUI/AUIローダー](/docs/7-0/tutorials/-/knowledge_base/t/configuring-modules-for-products-loaders#writing-the-configuration-file)が`trigger`モジュール（`liferay-session`）の`代わりに（instead）`（`when`プロパティによる指示）、カスタムAUIモジュール（`liferay-session-override`）を読み込む`条件（condition）`を指定します。同じパターンに従って、モジュールの`config.js`ファイルを作成できます。

       ;(function() {
       
       var base = MODULE_PATH + '/js/';
       
       AUI().applyConfig(
       {
       groups: {
       mymodulesoverride: { //mymodulesoverride
       base: base,
       combine: Liferay.AUI.getCombine(),
       filter: Liferay.AUI.getFilterConfig(),
       modules: {
       'liferay-session-override': { //my-module-override
       path: 'session-override.js', //my-module.js
       condition: {
       name: 'liferay-session-override', //my-module-override
       trigger: 'liferay-session', //original module
       when: 'instead'
       }
       }
       },
       root: base
       }
       }
       }
       );
       })();
   
5. 最後に、`bnd.bnd`ファイルを構成する必要があります。システムが変更内容を適用するには、`Liferay-JS-Config` BNDヘッダーで`config.js`の場所を指定する必要があります。前述の例の`liferay-session-override`モジュールでは、`bnd.bnd`ファイルに次の構成が含まれています。

       Bundle-Name: session-js-override
       Bundle-SymbolicName: session.js.override.web
       Bundle-Version: 1.0.0
       Liferay-JS-Config:/META-INF/resources/js/config.js
       Web-ContextPath: /liferay-session-override-web
   
これで、@product@のデフォルトのYUI/AUIモジュールをオーバーライドする方法がわかりました。

## 関連トピック

[Customizing JSPs](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps)
