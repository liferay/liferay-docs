---
header-id: using-esplus-modules-in-your-portlet
---

# ポートレットでES2015モジュールを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

`package.json` ファイルを介してモジュール</a> を

公開したら、それらをポートレットで使用できます。 `aui：script` タグの `require` 属性により、簡単になります。</p> 

このチュートリアルでは、ポートレットで公開されているモジュールにアクセスする方法について説明します。 以下の手順に従って、公開されたモジュールをポートレットで使用します。

1.  ビューJSPで `aui` taglibを宣言します。
   
        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
       
   
   **注：** Bladeを使用してポートレットを作成した場合、 `init.jsp``aui` taglibがすでに提供されています。

2.  `aui：script` タグをJSPに追加し、 `require` 属性をモジュールの相対パスに設定します。
   
   `require` 属性により、公開されたモジュールをJSPに含めることができます。 AMDローダーは、指定されたモジュールとその依存関係を取得します。 以下に示すfaux Console Loggerポートレットの `view.jsp` の例には、モジュール `logger.es`含まれています。
   
        <aui:script require="js-logger/logger.es">
            var Logger = jsLoggerLoggerEs.default;
       
            var loggerOne = new Logger('*** -> ');
            loggerOne.log('Hello');
       
            var loggerDefault = new Logger();
            loggerDefault.log('World');
        </aui:script>
       
   
   スクリプトタグ内のモジュールへの参照は、キャメルケースで `が` 値を必要とするため、すべての無効な文字が削除された名前が付けられます。 `logger.es` モジュールの参照 `jsLoggerLoggerEs` は、モジュールの相対パス値 `js-logger / logger.es`から派生します。 値からダッシュ文字とスラッシュ文字が取り除かれ、キャメルケースに変換されます。

`aui：script` タグとその `require` 属性のおかげで、ポートレットでモジュールを使用することは簡単です！



## 関連トピック

[JSPのカスタマイズ](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps)

[ウェブサービス](/docs/7-1/tutorials/-/knowledge_base/t/web-services)
