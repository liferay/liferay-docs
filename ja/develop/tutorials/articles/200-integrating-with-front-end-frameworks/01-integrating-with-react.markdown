---
header-id: using-react-in-your-portlets
---

# ポートレットでReactを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[npm Reactポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/npm-react-portlet-template) を使用して、必要な構成の多くを自動化するか、モジュールを手動で作成できます。 便宜上、すべての手動ステップを以下にリストします。 このチュートリアルでは、既存のReactプロジェクトを移行する場合でも、新しいプロジェクトを構築する場合でも、ポートレットでReactを使用する方法を示します。 ポートレットの構造の詳細については、 [npm Reactポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/npm-react-portlet-template) リファレンスドキュメントを、今すぐテストおよびデプロイできるReactポートレットの例については、 [react npmポートレットサンプル](/docs/7-1/reference/-/knowledge_base/r/react-npm-portlet) を参照してください。 OSGiモジュールを作成し、そのメタデータを構成することから始めます。

## メタデータの構成

次の手順に従って、モジュールを作成し、Reactのメタデータを構成します。

1.  [OSGiモジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)。 たとえば、 [npm Reactポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/npm-react-portlet-template)使用します。

2.  プロジェクトの `bnd.bnd` ファイルに `Web-ContextPath` BND Headerを指定します。 以下は、npm Reactポートレットテンプレートのデフォルト構成です。
   
        Web-ContextPath: /my-npm-react-portlet

3.  [`.babelrc` ファイル](/docs/7-1/reference/-/knowledge_base/r/configuring-liferay-npm-bundler) を作成し、次のプリセットを追加します。
   
        {
            "presets": ["env", "react"]
        }

4.  オプションで、プロジェクトのルートフォルダーに [`.npmbundlerrc` ファイル](/docs/7-1/reference/-/knowledge_base/r/configuring-liferay-npm-bundler) を追加します。 このファイルは必要ありません。 ただし、このファイルを設定して、ファイルを無視するなど、ニーズに合わせて `liferay-npm-bundler` をカスタマイズできます。

5.  `build.gradle` ファイルに次の依存関係を含めます。
   
        compileOnly group: "com.liferay", 
        name: "com.liferay.frontend.js.loader.modules.extender.api", 
        version: "2.0.2"

6.  以下に示す構成で存在しない場合は、プロジェクトに `package.json` 作成します。 `"main"` JSパスを更新して、アプリのメインJSファイルを指すようにします。 `liferay-npm-bundler` がビルドスクリプトの最後に追加されることに注意してください。 これより前に、プロジェクトで必要な追加のビルドプロセスをリストします。

    ``` 
    {
        "dependencies": {
            "react": "15.6.2",
            "react-dom": "15.6.2"
        },
      "description": "React Portlet",
        "devDependencies": {
            "babel-cli": "^6.26.0",
            "babel-preset-env": "^1.7.0",
            "babel-preset-react": "6.24.1",
            "liferay-npm-bundler": "^2.0.0"
        },
        "main": "js/index.js",
        "name": "my-npm-react-portlet",
        "scripts": {
            "build": "babel --source-maps -d 
        build/resources/main/META-INF/resources 
        src/main/resources/META-INF/resources && liferay-npm-bundler"
        },
        "version": "1.0.0"
    }    
    ```

ポートレットでES2015 +構文を使用するには、ブラウザー用にトランスパイルする必要があります。 ビルドスクリプトに含まれるBabelがこれを処理します。

次ポートレットを構成できます。

## ポートレットの構成

次の手順に従って、ポートレットを構成します。

1.  `Portlet.class` サービスを実装するコンポーネントクラスを作成します。
   
        @Component(
            immediate = true,
            property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + MyNpmReactPortletKeys.MyNpmReact,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
        )
        public class MyNpmReactPortlet extends MVCPortlet {
          ...
        }

2.  ReactプロジェクトにCSSスタイリングも含まれている場合は、次の追加プロパティを追加して、メインCSSファイルの場所を指定します。
   
        "com.liferay.portlet.header-portlet-css=/css/main.css"

    このパスはリソースパスに関連していることに注意してください。 Sassを使用している場合は、このプロパティに `.scss` 拡張子をドロップし、代わりに `.css` を使用します。 たとえば、メインCSSファイルが `src / main / resources / META-INF / resources / css / app.scss`にある場合、次の構成になります。
   
        "com.liferay.portlet.header-portlet-css=/css/app.css"

3.  コードの保守性を向上させるために、 [あなたのモジュールのパッケージ名のエイリアスに使用NPMResolverのAPI](/docs/7-1/tutorials/-/knowledge_base/t/referencing-an-npm-modules-package)。 次の例では、モジュールの名前を `bootstrapRequire`として公開しています。
   
        @Override
        public void doView(
                RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
       
            JSPackage jsPackage = _npmResolver.getJSPackage();
       
            renderRequest.setAttribute(
                MyNpmReactWebKeys.BOOTSTRAP_REQUIRE,
                jsPackage.getResolvedId() + " as bootstrapRequire");
       
            super.doView(renderRequest, renderResponse);
        }
       
        @Reference
        private NPMResolver _npmResolver;

4.  `init.jsp`内に、次のJavaスクリプトレットを追加して、ポートレットの `view.jsp`の `bootstrapRequire` 変数にアクセスします。
   
        <%
        String bootstrapRequire = (String)renderRequest.getAttribute(
          MyNpmReactWebKeys.BOOTSTRAP_REQUIRE
        );
        %>

次に、アプリのコンポーネントをレンダリングする方法を学びます。

## コンポーネントのレンダリング

アプリコンポーネントをレンダリングするには、次の手順に従います。

1.  アプリのメインJSファイル（たとえば、`index.js` ）内で、以下の関数を使用してコンポーネントをレンダリングします。

    ``` 
    import React from 'react';
    import ReactDOM from 'react-dom';
    import AppComponent from './components/App' //parent component

    export default function(elementId) {
      ReactDOM.render(<AppComponent />, document.getElementById(elementId));
    }  
    ```

2.  `view.jsp` を開き、コンポーネントを収容する要素コンテナを追加します。 次に、 `<aui:script>` タグを追加し、 `は` 属性の値を必要とするため、エイリアスモジュール名を渡します。 最後に、前の手順でエクスポートしたモジュールの `デフォルト` 関数を呼び出し、コンテナIDを要素IDとして渡します。 `<portlet:namespace />` を `<div>`の `id` 追加すると、ポートレットに固有であり、ページ上の既存の要素と衝突しません。
   
        <%@ include file="/init.jsp" %>
       

<div id="<portlet:namespace / mark=">
  -root ">
</div>

        <aui:script require="<%= bootstrapRequire %>">
            bootstrapRequire.default('<portlet:namespace />-root');
        </aui:script>

これで、プロジェクトでReactを使用する方法がわかりました！

## 関連トピック

[ポートレットでのAngularの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-angular-js-in-your-portlets)

[ポートレットでVueを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-vue-in-your-portlets)

[ポートレットでnpmを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-npm-in-your-portlets)
