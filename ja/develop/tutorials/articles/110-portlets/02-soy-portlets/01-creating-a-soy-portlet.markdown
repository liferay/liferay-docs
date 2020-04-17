---
header-id: creating-a-soy-portlet
---

# 大豆ポートレットの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Soyポートレットを作成するには、次の主要コンポーネントが必要です。

  - 必要なプロパティを持つポートレットコンポーネントを公開するモジュール
  - 要求と応答を処理するコントローラーコード
  - ビューレイヤーを実装するための大豆テンプレート

## Webモジュールの構成

最初に、Soyポートレットの構造に慣れます。 SoyポートレットはMVCポートレットを拡張するため、これを認識することができます。

  - `my-soy-portlet`
      - `bnd.bnd`
      - `build.gradle`
      - `package.json`
      - `src/main/`
          - `java/path/to/portlet/`
              - `MySoyPortletRegister.java`
              - `action/`
                  - `*MVCRenderCommand.java`
          - `resources/META-INF/resources/`
              - `content/`
                  - `Language.properties`
              - `View.es.js` （MetalJSコンポーネント）
              - `View.soy` （Soyテンプレート）

Soyポートレットモジュールの基本構造がわかったので、構成できます。 必要に応じて、 [大豆ポートレットブレードテンプレート](/docs/7-1/reference/-/knowledge_base/r/soy-portlet-template) を使用して初期プロジェクトをビルドできます。 それ以外の場合は、このセクションの指示に従ってモジュールを手動で構成できます。

### OSGiメタデータの指定

OSGiメタデータをモジュールの `bnd.bnd` ファイルに追加します。 サンプルのBND設定を以下に示します。

Bundle-Name：Liferay Hello Soy Web Bundle-SymbolicName：com.liferay.hello.soy.web Bundle-Version：2.0.7 Provide-Capability： soy; type = "hello-soy"; version：Version = "1.0.10" Require-Capability： 大豆; filter：= "（type = metal）" Web-ContextPath：/ hello-soy-web

`Provide-Capability` ヘッダーは、このバンドルが大豆機能を提供することを指定しているため、テンプレートエンジンはバンドルを追跡できます。 `Require-Capability` ヘッダーは、バンドルが機能するために `type` of `metal` の機能 `soy` を提供するモジュールを必要とすることを指定します。 `Web-ContextPath` ヘッダーは、リソースを参照できるようにアプリケーションの相対パスを指定します。

### JavaScriptの依存関係の指定

`package.json`でJavaScriptモジュールの依存関係を指定します。 少なくとも、次の依存関係と構成パラメーターが必要です。 常に最新のコンポーネントバージョンを使用してください（以下に示すバージョンは最新ではない場合があります）。

    {
        "dependencies": {
            "metal-component": "^2.16.8",
            "metal-soy": "^2.16.8"
        },
        "scripts": {
            "build": "liferay-npm-scripts build",
            "checkFormat": "liferay-npm-scripts check",
        "format": "liferay-npm-scripts fix"
        },
        "name": "my-portlet-name",
        "version": "1.0.0"
    }

これにより、Soyに基づいてMetalコンポーネントを作成するために必要なすべてが提供されます。 `package.json` の `バージョン` は、 `bnd.bnd` ファイルの `Bundle-Version` と一致する必要があることに注意してください。

次に、モジュールのビルド依存関係を指定できます。

### ビルドの依存関係の指定

`build.gradle` ファイルに以下に示す依存関係を追加します。

    dependencies {
        compileOnly group: "com.liferay", name: "com.liferay.portal.portlet.bridge.soy.api", version: "1.0.0"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
        compileOnly group: "com.liferay.portal", name: "com.liferay.util.java", version: "3.0.0"
        compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }

| **注：** これらはこの記事の執筆時点で最新のものですが、変更される可能性があります。 してください|を確認する| [Nexusリポジトリ](https://repository.liferay.com) | @product@インスタンスの適切なバージョン。

モジュールビルドが構成されたので、Soyポートレットコンポーネントの作成方法を学習できます。

## Soy Portlet Registerコンポーネントの作成

`SoyPortletRegister` クラスを拡張するSoy Portletコンポーネントを作成します。 この 実行するには、 `javax.portlet.Portlet` サービスの実装が必要です。 ポートレットクラスで `@Component` アノテーションを使用してこれを宣言します。

    @Component(
        immediate = true,
        service = SoyPortletRegister.class
    )
    public class MySoyPortletRegister extends SoyPortletRegister {
    
    }

@product@の [`SoyPortletRegister` クラス](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/portal-portlet-bridge/portal-portlet-bridge-soy-api/src/main/java/com/liferay/portal/portlet/bridge/soy/SoyPortletRegister.java) は [`SoyPortlet` クラス](https://github.com/liferay/com-liferay-portal-portlet-bridge/blob/7.1.x/portal-portlet-bridge-soy/src/main/java/com/liferay/portal/portlet/bridge/soy/SoyPortlet.java) インポートされ、 [`MVCPortlet` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)拡張します。これは `javax.portlet.Portlet`拡張自体であるため、正しい実装。

コンポーネントにはいくつかのプロパティも必要です。 サンプル構成を以下に示します。

    @Component(
            immediate = true,
            property = {
              "com.liferay.portlet.add-default-resource=true",
                "com.liferay.portlet.application-type=full-page-application",
                "com.liferay.portlet.application-type=widget",
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.layout-cacheable=true",
                "com.liferay.portlet.preferences-owned-by-group=true",
                "com.liferay.portlet.private-request-attributes=false",
                "com.liferay.portlet.private-session-attributes=false",
                "com.liferay.portlet.render-weight=50",
                "com.liferay.portlet.scopeable=true",
                "com.liferay.portlet.single-page-application=false",
                "com.liferay.portlet.use-default-template=true",
                "javax.portlet.display-name=Hello Soy Portlet",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.init-param.copy-request-parameters=true",
                "javax.portlet.init-param.template-path=/META-INF/resources/",
                "javax.portlet.init-param.view-template=View",
                "javax.portlet.name=hello_soy_portlet",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=guest,power-user,user",
                "javax.portlet.supports.mime-type=text/html"
            },
            service = SoyPortletRegister.class
    )

これらのプロパティの一部は、MVCポートレットの開発に使用されるものと同じであるため、見慣れているかもしれません。 利用可能なLiferay固有のポートレットコンポーネントプロパティの完全なリストは、 [`liferay-portlet-app_7_1_0.dtd`](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html)ます。

`javax.portlet ...` プロパティは [portlet.xml記述子の要素です](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd)

LiferayのDTDファイルは、ここで [ます](@platform-ref@/7.1-latest/definitions/)

Soyポートレットコンポーネントの基盤を設定したので、コントローラーコードを記述できます。

## コントローラーコードの記述

大豆ポートレットはMVCポートレットを拡張するため、同じModel-View-Controllerフレームワークを使用して動作します。 コントローラは、フロントエンドからリクエストを受信し、バックエンドからデータを受信します。 ユーザーに表示できるようにそのデータを右フロントエンドビューに送信し、ユーザーがフロントエンドに入力したデータを取得して右バックエンドサービスに渡す責任があります。 このため、フロントエンドからのリクエストを処理し、それらに適切に応答する方法が必要であり、ユーザーにデータを渡すための適切なフロントエンドビューを決定する方法が必要です。

### レンダリングロジック

レンダリングロジックは、すべての魔法が発生する場所です。 結局のところ、表示できない場合のポートレットの使用は何ですか？ コンポーネントクラスで設定した `init-param` プロパティに注意してください。

    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=View",

これにより、デフォルトのレンダリングがView（`View.soy`）に向けられます。 `template-path` プロパティは、Soyテンプレートの場所を指定します。 `/` 大豆ファイルは、プロジェクトのルートに位置していることを意味し、上記 `リソース` フォルダ。 そのため、上記で概説した標準のフォルダー構造に従うことが重要です。 仮想Webモジュールのリソースフォルダーのパスは次のとおりです。

    docs.liferaysoy.web/src/main/resources/META-INF/resources

この場合、 `View.soy` ファイルは次の場所にあります。

    docs.liferaysoy.web/src/main/resources/META-INF/resources/View.soy

これがアプリケーションのデフォルトビューです。 `init` メソッドが呼び出されると、指定した初期化パラメーターが読み取られ、デフォルトのテンプレートにレンダリングを指示するために使用されます。 このフレームワーク全体で、 `javax.portlet.PortletURL` の `mvcRenderCommandName` パラメーターをレンダリングするSoyテンプレートに設定することにより、異なるビュー（Soyテンプレート）をレンダリングできます。 次の例では、 `navigationURL` というポートレットURLを使用して、ビュー `ビュー`をレンダリングします。

    navigationURL.setParameter("mvcRenderCommandName", "View");

デフォルトのテンプレートビューを除く各ビューの **は、 [`MVCRenderCommand` クラス](/docs/7-1/tutorials/-/knowledge_base/t/mvc-render-command)実装が** 必要です。 `* MVCRenderCommand` 実装は、 `MVCRenderCommand` サービスを持つコンポーネントとして自身を宣言する必要があり、 `javax.portlet.name` および `mvc.command.name` プロパティをそれぞれ使用して、ポートレットの名前とMVCコマンド名を指定する必要があります。 以下は、 `Navigation` Soyテンプレートの `MVCRenderCommand` 実装の例です。

    @Component(
            immediate = true,
            property = {
                    "javax.portlet.name=hello_soy_portlet",
                    "mvc.command.name=Navigation"
            },
            service = MVCRenderCommand.class
    )
    public class HelloSoyNavigationExampleMVCRenderCommand
            implements MVCRenderCommand {
    
            @Override
            public String render(
                    RenderRequest renderRequest, RenderResponse renderResponse) {
    
                    Template template = (Template)renderRequest.getAttribute(
                            WebKeys.TEMPLATE);
    
                    PortletURL navigationURL = renderResponse.createRenderURL();
    
                    navigationURL.setParameter("mvcRenderCommandName", "View");
    
                    template.put("navigationURL", navigationURL.toString());
    
                    return "Navigation";
            }
    
    }

レンダリングロジックは、ユーザーにデータを適切に表示するための情報をビューレイヤーに提供します。 上記の例の説明は次のとおりです。

  - MVCコマンド名は `Navigation` （名前空間 `Navigation`Soyテンプレート）です。 これは、このロジックが `ナビゲーション` ビュー用であることを意味します。
  - `PortletURL` （`navigationURL`）が定義され、その `mvcRenderCommandName` が `View`（名前空間 `View`Soyテンプレート）に設定されます。
  - `navigationURL` は `文字列` 変換され、変数 `navigationURL` として `template.put（）` メソッドで `Navigation` Soyテンプレートに渡されます。

Soyポートレットパラメーターは、記述されているポートレットクラスにスコープされていることに注意してください。 たとえば、それぞれ異なる値を持つ2つの異なるクラスに `navigationURL` パラメーターを設定できます。 以下は、 `NavigationURL` パラメーターも定義する `HelloSoyViewMVCRenderCommand` クラスの例です。

    public class HelloSoyViewMVCRenderCommand implements MVCRenderCommand {
    
    @Override
    public String render(
            RenderRequest renderRequest, RenderResponse renderResponse) {
    
            Template template = (Template)renderRequest.getAttribute(
                WebKeys.TEMPLATE);
    
            ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
                WebKeys.THEME_DISPLAY);
    
            template.put("layouts", themeDisplay.getLayouts());
    
            PortletURL navigationURL = renderResponse.createRenderURL();
    
            navigationURL.setParameter("mvcRenderCommandName", "Navigation");
    
            template.put("navigationURL", navigationURL.toString());
    
            template.put("releaseInfo", ReleaseInfo.getReleaseInfo());
    
            return "View";
        }
    
    }

上記の例の説明は次のとおりです。

  - 今回、 `navigationURL` は `Navigation` Soyテンプレートを指します。
  - `navigationURL` および `releaseInfo` パラメーターは、 `View` Soyテンプレートに渡されます。
  - このロジックはデフォルトの `render` メソッドの前に実行する必要があるため、メソッドは `super.render`呼び出して終了します。

レンダリングロジックを理解したので、ビューレイヤーの仕組みを学習できます。

## ビューレイヤーの構成

ポートレットにはビューレイヤーも必要です。そのためには、Soyポートレットを開発するためのポイントであるSoyテンプレートを使用します。 このセクションでは、他のSoyテンプレートを含めることから、ビューをレンダリングするためのMetalJSコンポーネントを作成するまで、ビューレイヤーを機能させる方法について簡単に説明します。

大豆テンプレートは、拡張子を持つファイルで定義されている `.soy`。 ファイル名は任意です。 Soyテンプレートの名前は、 `名前空間` 宣言を使用してテンプレートの上部に指定されます。 たとえば、次の宣言は、 `ビュー` テンプレート用です。

    {namespace View}

以下に示すように、名前空間で `render` メソッドを呼び出すことにより、別のSoyテンプレートでアクセスできます。 `data = 'all'` 属性は、テンプレートにすべてのパラメーターも含めることを指定します。

    {call View.render data="all"}{/call}

| **注：** テンプレートの名前空間は一意でなければなりません。

以下に、 `ヘッダー` と `フッター` 大豆テンプレートを含む `ビュー` 大豆テンプレートの例を示します。

    {namespace View}
    
    /**
     * Prints the portlet main view.
     */
    {template .render}
      <div id="{$id}">
        {call Header.render data="all"}{/call}
    
        <p>{msg desc=""}here-is-a-message{/msg}</p>
    
        {call Footer.render data="all"}{/call}
      </div>
    {/template}

各ビューには、対応する `* es.js` ファイル（通常は同じ名前）があり、ビューに必要なSoyテンプレートをインポートし、ビューをMetalJSコンポーネントとして登録します。 このファイルは、ビューに追加されるJavaScriptロジックにも使用されます。 たとえば、 `View.soy` テンプレート用の `View.es.js` コンポーネントは次のとおりです。

    import Component from 'metal-component/src/Component';
    import Footer from './Footer.es';
    import Header from './Header.es';
    import Soy from 'metal-soy/src/Soy';
    import templates from './View.soy';
    
    /**
     * View Component
     */
    class View extends Component {}
    
    // Register component
    Soy.register(View, templates);
    
    export default View;

Soyテンプレートビューを構成する方法を理解したので、次に、Soyテンプレートでポートレットパラメーターを使用する方法を学習できます。

### Soyテンプレートでのポートレットテンプレートパラメーターの使用

上記のように、 `template.put（）` メソッドは、ポートレットパラメータをSoyテンプレートに公開します。 パラメータが公開されると、先頭に `@param name` 宣言を定義して、Soyテンプレートでアクセスできます。 たとえば、 `hello-soy-web` ポートレットの `View` Soyテンプレートは、 `navigationURL` パラメーターを以下のコードで定義します。

    @param navigationURL

次に、ポートレットビュー間を移動するために使用されます。

    <a href="{$navigationURL}">{msg desc=""}
        click-here-to-navigate-to-another-view
    {/msg}</a>

一部のJavaテーマオブジェクト変数も使用できます。 たとえば、Soyテンプレートの [`ThemeDisplay` オブジェクト](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html) にアクセスするには、次の構文を使用します。

    {$themeDisplay}

`{$locale}`を使用して `Locale` オブジェクトにアクセスすることもできます。 次に、 `com.liferay.hello.soy.web` ポートレット用の完全な `View.soy` テンプレートを示します。これは、このセクションで説明する機能を示しています。

    {namespace View}
    
    /**
     * Prints the Hello Soy portlet main view.
     * 
     * @param id
     * @param layouts
     * @param navigationURL
     */
    {template .render}
        <div id="{$id}">
            {call Header.render data="all"}{/call}
    
            <p>
          {msg desc=""}here-you-will-find-how-easy-it-is-to-do-things-like{/msg}
        </p>
    
            <h3>{msg desc=""}listing-pages{/msg}</h3>
    
            <div class="list-group">
                <div class="list-group-heading">{msg desc=""}navigate-to{/msg}</div>
    
                {foreach $layout in $layouts}
                    <a class="list-group-item" href="{$layout.friendlyURL}">
              {$layout.nameCurrentValue}
            </a>
                {/foreach}
            </div>
    
            <h3>{msg desc=""}navigating-between-views{/msg}</h3>
    
            <a href="{$navigationURL}">
          {msg desc=""}click-here-to-navigate-to-another-view{/msg}
        </a>
    
            {call Footer.render data="all"}{/call}
        </div>
    {/template}

これで、Soy Portlet \の作成方法がわかりました！

## 関連トピック

[Liferay MVCポートレット](/docs/7-1/tutorials/-/knowledge_base/t/liferay-mvc-portlet)
