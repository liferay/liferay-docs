---
header-id: using-vue-in-your-portlets
---

# ポートレットでVueを使用する

[TOC levels=1-4]

Vueプロジェクトを手動で作成するか、 [npm Vueポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/npm-vue-js-portlet-template) を使用して、必要な構成の多くを自動化できます。 便宜上、すべての手動ステップを以下にリストします。 このチュートリアルでは、既存のVueプロジェクトを移行する場合でも、新規プロジェクトを構築する場合でも、ポートレットでVue JSを使用する方法を示します。 ポートレットの構造の詳細については、 [npm Vueポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/npm-vue-js-portlet-template) リファレンスドキュメントを、今すぐテストおよびデプロイできるVueポートレットの例については、 [npm Vueポートレットサンプル](/docs/7-1/reference/-/knowledge_base/r/vue-js-npm-portlet) を参照してください。 OSGiモジュールを作成し、そのメタデータを構成することから始めます。

## メタデータの構成

次の手順に従って、モジュールを作成し、Vueのメタデータを構成します。

1.  [OSGiモジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)。 たとえば、 [npm Vueポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/npm-vue-js-portlet-template)使用します。

2.  プロジェクトの `bnd.bnd` ファイルに `Web-ContextPath` BND Headerを指定します。 以下は、npm Vueポートレットテンプレートのデフォルト構成です。
   
        Web-ContextPath: /my-npm-vue-portlet

3.  [`.babelrc` ファイル](/docs/7-1/reference/-/knowledge_base/r/configuring-liferay-npm-bundler) を作成し、次のプリセットを追加します。
   
        {
            "presets": ["env"]
        }

4.  オプションで、プロジェクトのルートフォルダーに [`.npmbundlerrc` ファイル](/docs/7-1/reference/-/knowledge_base/r/configuring-liferay-npm-bundler) を追加します。 このファイルは必要ありません。 ただし、このファイルを構成して、ファイルを無視するなど、ニーズに合わせてliferay-npm-bundlerをカスタマイズできます。

5.  `build.gradle` ファイルに次の依存関係を含めます。
   
        compileOnly group: "com.liferay", 
        name: "com.liferay.frontend.js.loader.modules.extender.api", 
        version: "2.0.2"

6.  プロジェクトに `package.json` が存在しない場合は作成し、以下に示す構成を追加します。 `"main"` JSパスを更新して、アプリのメインJSファイルを指すようにします。 `liferay-npm-bundler` がビルドスクリプトの最後に追加されることに注意してください。 これより前に、プロジェクトで必要な追加のビルドプロセスをリストします。
   
        {
            "dependencies": {
                "vue": "2.4.4"
            },
          "description": "Vue.js Portlet",
            "devDependencies": {
                "babel-cli": "^6.26.0",
                "babel-preset-env": "^1.7.0",
                "liferay-npm-bundler": "^2.0.0"
            },
            "main": "js/index.js",
            "name": "my-npm-vuejs-portlet",
            "scripts": {
                "build": "babel --source-maps -d 
            build/resources/main/META-INF/resources 
            src/main/resources/META-INF/resources && liferay-npm-bundler"
            },
            "version": "1.0.0"
        }

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
                "javax.portlet.name=" + MyNpmVuejsPortletKeys.MyNpmVuejs,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
        )
        public class MyNpmVuejsPortlet extends MVCPortlet {
          ...
        }

2.  VueプロジェクトにCSSスタイルが含まれている場合は、次の追加プロパティを追加して、メインCSSファイルの場所を指定します。
   
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
                MyNpmVuejsWebKeys.BOOTSTRAP_REQUIRE,
                jsPackage.getResolvedId() + " as bootstrapRequire");
       
            super.doView(renderRequest, renderResponse);
        }
       
        @Reference
        private NPMResolver _npmResolver;

4.  `init.jsp`内に、次のJavaスクリプトレットを追加して、ポートレットの `view.jsp`の `bootstrapRequire` 変数にアクセスします。
   
        <%
        String bootstrapRequire = (String)renderRequest.getAttribute(
          MyNpmVuejsWebKeys.BOOTSTRAP_REQUIRE
        );
        %>

次に、アプリのコンポーネントをレンダリングする方法を学びます。

## コンポーネントのレンダリング

アプリコンポーネントをレンダリングするには、次の手順に従います。

1.  アプリのメインJSファイル（たとえば、`index.js` ）内で、以下の関数を使用してコンポーネントをレンダリングします。
   
        import Vue from 'vue/dist/vue.common';
       
        export default function(portletNamespace) {
            // Application 1
            new Vue({
                el: `#${portletNamespace}-root`,
                data: {
                    message: 'Hello from Vue.js!',
                },
                methods: {
                    reverseMessage: function() {
                        this.message = this.message
                            .split('')
                            .reverse()
                            .join('');
                    },
                },
            });
        }

2.  `view.jsp` を開き、コンポーネントを収容する要素コンテナを追加します。 次に、 `<aui:script>` を追加し、 `が` 属性値を必要とするため、エイリアスモジュール名を渡します。 最後に、前の手順でエクスポートしたモジュールの `デフォルト` 関数を呼び出し、ポートレット名前空間を渡します。 `<portlet:namespace />` を `<div>`の `id` 追加すると、ポートレットに固有であり、ページ上の既存の要素と衝突しません。
   
        <%@ include file="/init.jsp" %>
       

<div id="<portlet:namespace / mark=">
  -root ">
</div>

        <aui:script require="<%= bootstrapRequire %>">
            bootstrapRequire.default('<portlet:namespace />');
        </aui:script>

これで、プロジェクトでVueを使用する方法がわかりました！

## 関連トピック

[ポートレットでのAngularの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-angular-js-in-your-portlets)

[ポートレットでReactを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-react-in-your-portlets)

[ポートレットでnpmを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-npm-in-your-portlets)
