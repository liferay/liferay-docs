---
header-id: why-arent-my-modules-javascript-and-css-changes-showing
---

# モジュールのJavaScriptとCSSの変更が表示されないのはなぜですか？

[TOC levels=1-4]

モジュールへのJavaScriptおよびCSSの更新がブラウザに反映されない理由を判断するには、次のチェックを実行します。

1. ポートレットモジュールを開発している場合は、ポートレットクラスの`@Component`アノテーションに正しいプロパティが指定されていることを確認します。

   - ポートレットクラスの`@Component`アノテーションのプロパティによって参照されるリソースが、モジュールプロジェクトの正しい場所に存在することを確認します。
   - ポートレットCSSラッパークラスを使用して、ページ上の他のアプリケーションとの潜在的なCSS IDおよびクラス名の競合が回避されていることを確認します。
   たとえば、次のサンプルのポートレットクラスについて考えます。

       @Component(
       immediate = true,
       property = {
       "com.liferay.portlet.css-class-wrapper=example-portlet",
       "com.liferay.portlet.display-category=category.sample",
       "com.liferay.portlet.instanceable=true",
       "com.liferay.portlet.header-portlet-css=/css/main.css",
       "com.liferay.portlet.header-portlet-js=/css/main.js",
       "javax.portlet.display-name=Example Portlet",
       "javax.portlet.init-param.template-path=/",
       "javax.portlet.init-param.view-template=/view.jsp",
       "javax.portlet.name=" + ExamplePortletKeys.TicTacToe,
       "javax.portlet.resource-bundle=content.Language",
       "javax.portlet.security-role-ref=power-user,user"
       },
       service = Portlet.class
       )
       public class ExamplePortlet extends MVCPortlet {
       
       }
   
   上記の最初の項目で説明したように、ポートレットのCSSファイルはプロパティ`com.liferay.portlet.header-portlet-css`によって指定されます。このプロパティの値として指定されたパスは、モジュールの`src/main/resources/META-INF/resources`フォルダに対応しています。したがって、`css/main.css`の値を指定した場合、モジュール内のCSSファイルへの実際のパスは`src/main/resources/META-INF/resources/css/main.css`となります。ポートレットのJavaScriptファイルへのパスは、プロパティ`com.liferay.portlet.header-portlet-js`によって指定されます。このプロパティの値は、CSSプロパティの値と同じように機能します。

   プロパティ`com.liferay.portlet.css-class-wrapper`がCSSクラスラッパー`example-portlet`を指定していることにも注意してください。したがって、ポートレットの実際のCSSファイルで`example-portlet`のサブクラスを使用する必要があります。たとえば、`main.css`で、以下を実行して背景を緑に変更します。

       .example-portlet {
       .greenBackground {
       background-color: green;
       }
       
       ... (further properties)
       
       }
   
   つまり、CSSクラスとID名の競合を回避するには、指定するすべてのCSSプロパティが`com.liferay.portlet.css-class-wrapper`プロパティで指定されたクラスのサブクラスである必要があります。@product@は、ポートレットのHTMLコンテンツを`<div>`でラップします。`com.liferay.portlet.css-class-wrapper`（この例では`example-portlet`）で指定されたクラスは、この`<div>`に適用されています。

2. キャッシュにより、モジュールへのJSおよびCSSの更新がブラウザに表示されなくなっていないことを確認します。

   - ブラウザのキャッシュをクリアします。
   - 開発中に、開発者モードを有効にして、@product@のリソースキャッシングをオフにします。
@product@の開発者モードを有効にする方法については、[こちらをクリック](/docs/7-1/tutorials/-/knowledge_base/t/using-developer-mode-with-themes)してください。

## 関連トピック

[Using Developer Mode with Themes](/docs/7-1/tutorials/-/knowledge_base/t/using-developer-mode-with-themes)
