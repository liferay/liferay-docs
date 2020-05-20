---
header-id: using-angular-js-in-your-portlets
---

# ポートレットでのAngularの使用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[npm Angularポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/npm-angular-portlet-template) を使用して、必要な構成の多くを自動化するか、モジュールを手動で作成できます。 便宜上、すべての手動ステップを以下にリストします。 このチュートリアルでは、既存のAngularプロジェクトを移行する場合でも、新しいプロジェクトを構築する場合でも、ポートレットでAngularを使用する方法を示します。 ポートレットの構造の詳細については、 [npm Angularポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/npm-angular-portlet-template) リファレンスドキュメントを参照してください。 OSGiモジュールを作成し、そのメタデータを構成することから始めます。

## メタデータの構成

以下の手順に従って、モジュールを作成し、Angularのメタデータを構成します。

1.  [OSGiモジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)。 たとえば、 [npm Angularポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/npm-angular-portlet-template)使用します。

2.  プロジェクトの `bnd.bnd` ファイルに `Web-ContextPath` BND Headerを指定します。 以下は、npm Angularポートレットテンプレートのデフォルト構成です。
   
        Web-ContextPath: /my-npm-angular-portlet

3.  `.tsconfig.json` ファイルを作成し、次の構成を追加します。
   
        {
            "compilerOptions": {
                "emitDecoratorMetadata": true,
                "experimentalDecorators": true,
                "lib": ["es2015", "dom"],
                "moduleResolution": "node",
                "outDir": "build/resources/main/META-INF/resources/lib",
                "sourceMap": true,
                "suppressImplicitAnyIndexErrors": true,
                "target": "es5",
                "typeRoots": ["./node_modules/@types/"],
       
                "module": "commonjs",
                "strict": true,
                "noFallthroughCasesInSwitch": true,
                "inlineSources": true,
                "declaration": false,
                "skipLibCheck": true,
                "types": ["jasmine", "node"]
            },
            "include": ["src/main/resources/META-INF/resources/**/*.ts"]
        }

4.  オプションで、プロジェクトのルートフォルダーに [`.npmbundlerrc` ファイル](/docs/7-1/reference/-/knowledge_base/r/configuring-liferay-npm-bundler) を追加します。 このファイルは必要ありません。 ただし、このファイルを構成して、ファイルを無視するなど、ニーズに合わせてliferay-npm-bundlerをカスタマイズできます。

5.  `build.gradle` ファイルに次の依存関係を含めます。
   
        compileOnly group: "com.liferay", 
        name: "com.liferay.frontend.js.loader.modules.extender.api", 
        version: "2.0.2"

6.  プロジェクトに `package.json` が存在しない場合は作成し、以下に示す構成を追加します。 `"main"` JSパスを更新して、アプリのメインJSファイルを指すようにします。 `liferay-npm-bundler` がビルドスクリプトの最後に追加されることに注意してください。 以下に示す `tsc` （Typescript）プロセスなど、プロジェクトに必要な追加のビルドプロセスをリストします。
   
        {
            "dependencies": {
                "@angular/animations": "^5.0.0",
                "@angular/common": "^5.0.0",
                "@angular/compiler": "^5.0.0",
                "@angular/core": "^5.0.0",
                "@angular/forms": "^5.0.0",
                "@angular/http": "^5.0.0",
                "@angular/platform-browser": "^5.0.0",
                "@angular/platform-browser-dynamic": "^5.0.0",
                "@angular/platform-server": "^5.0.0",
                "@angular/router": "^5.0.0",
                "@ngx-translate/core": "^9.1.1",
                "core-js": "^2.5.1",
                "rxjs": "^5.5.2",
                "zone.js": "0.8.12"
            },
            "description": "Angular Portlet",
            "devDependencies": {
                "@angular/cli": "^1.6.7",
                "@angular/compiler-cli": "^5.0.0",
                "@compodoc/compodoc": "1.0.0-beta.10",
                "@types/bootstrap": "^3.3.33",
                "@types/bootstrap-datepicker": "0.0.6",
                "@types/jasmine": "2.5.48",
                "@types/jquery": "^2.0.46",
                "@types/moment": "^2.13.0",
                "@types/node": "~6.0.60",
                "@types/toastr": "^2.1.34",
                "chalk": "1.1.3",
                "codelyzer": "3.1.2",
                "del": "^3.0.0",
                "gulp": "^3.9.1",
                "gulp-flatten": "^0.3.1",
                "gulp-sass": "^3.1.0",
                "inline-ng2-resources": "^1.1.0",
                "jasmine-core": "~2.6.2",
                "jasmine-spec-reporter": "~3.2.0",
                "karma": "1.7.0",
                "karma-chrome-launcher": "~2.1.1",
                "karma-cli": "~1.0.1",
                "karma-coverage-istanbul-reporter": "^1.1.0",
                "karma-jasmine": "~1.1.0",
                "karma-jasmine-html-reporter": "^0.2.2",
                "karma-junit-reporter": "1.2.0",
                "karma-remap-istanbul": "^0.2.1",
                "karma-spec-reporter": "0.0.31",
                "liferay-npm-bundler": "^2.0.0",
                "protractor": "~5.1.0",
                "rollup": "0.41.6",
                "rollup-plugin-commonjs": "^8.0.2",
                "rollup-plugin-node-resolve": "3.0.0",
                "shelljs": "0.7.7",
                "sorcery": "0.10.0",
                "ts-node": "~2.0.0",
                "tslint": "5.4.0",
                "typescript": "2.4.2",
                "webpack": "2.6.1",
                "yargs": "8.0.1"
            },
            "main": "js/angular-loader.js",
            "name": "my-npm-angular-portlet",
            "scripts": {
                "build": "tsc && babel --source-maps -d build/resources/main/META-INF/resources src/main/resources/META-INF/resources && liferay-npm-bundler"
            },
            "version": "1.0.0"
        }

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
                "javax.portlet.name=" + MyNpmAngularPortletKeys.MyNpmAngular,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
        )
        public class MyNpmAngularPortlet extends MVCPortlet {
          ...
        }

2.  AngularプロジェクトにCSSスタイリングが含まれている場合は、次の追加のプロパティを追加して、メインCSSファイルの場所を指定します。
   
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
                MyNpmAngularWebKeys.BOOTSTRAP_REQUIRE,
                jsPackage.getResolvedId() + " as bootstrapRequire");
       
            super.doView(renderRequest, renderResponse);
        }
       
        @Reference
        private NPMResolver _npmResolver;

4.  `init.jsp`内に、次のJavaスクリプトレットを追加して、ポートレットの `view.jsp`の `bootstrapRequire` 変数にアクセスします。
   
        <%
        String bootstrapRequire = (String)renderRequest.getAttribute(
          MyNpmAngularWebKeys.BOOTSTRAP_REQUIRE
        );
        %>

次に、アプリのコンポーネントをレンダリングする方法を学びます。

## コンポーネントのレンダリング

アプリコンポーネントをレンダリングするには、次の手順に従います。

1.  アプリのメインTSファイル（たとえば、`main.ts` ）内で、以下の関数を使用してコンポーネントをレンダリングします。
   
        import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
       
        import { AppComponent } from './app/app.component';
        import { AppModule } from './app/app.module';
        import { DynamicLoader } from './app/dynamic.loader';
       
        export default function(rootId: any) {
            platformBrowserDynamic()
                .bootstrapModule(AppModule)
                .then((injector: any) => {
       
                    // Load the bootstrap component dynamically so that we can attach it
                    // to the portlet's DOM, which is different for each portlet
                    // instance and, thus, cannot be determined until the page is
                    // rendered (during runtime).
       
                    // The rootId argument is passed from view.jsp where we can obtain
                    // the portlet's namespace by using JSP tags.
       
                    const dynamicLoader = new DynamicLoader(injector);
       
                    dynamicLoader.loadComponent(AppComponent, rootId);
                });
        }

2.  `angular-loader.ts`などの別のファイルで、次の構成を追加できます。
   
        // Import needed polyfills before application is launched
       
        import 'reflect-metadata';
        import 'zone.js';
       
        // Declare Liferay AMD loader
       
        declare var Liferay: any;
       
        // Launch application
       
        export default function(rootId: any) {
            Liferay.Loader.require('my-npm-angular-portlet@1.0.0/js/main', 
          (main: any) => {
                main.default(rootId);
            });
        }

3.  `view.jsp` を開き、コンポーネントを収容する要素コンテナを追加します。 次に、 `<aui:script>` を追加し、 `が` 属性値を必要とするため、エイリアスモジュール名を渡します。 最後に、前の手順でエクスポートしたモジュールの `デフォルト` 関数を呼び出し、ルートIDとしてコンテナ要素を渡します。 `<portlet:namespace />` を `<div>`の `id` 追加すると、ポートレットに固有であり、ページ上の既存の要素と衝突しません。
   
        <%@ include file="/init.jsp" %>
       

<div id="<portlet:namespace / mark=">
  -root ">
</div>

        <aui:script require="<%= bootstrapRequire %>">
            bootstrapRequire.default('#<portlet:namespace />-root');
        </aui:script>

これでプロジェクトでAngularを使用する方法がわかりました！

## 関連トピック

[ポートレットでReactを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-react-in-your-portlets)

[ポートレットでVueを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-vue-in-your-portlets)

[ポートレットでnpmを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-npm-in-your-portlets)
