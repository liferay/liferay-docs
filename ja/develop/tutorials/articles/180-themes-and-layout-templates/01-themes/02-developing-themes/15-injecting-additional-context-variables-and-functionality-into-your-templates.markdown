---
header-id: injecting-additional-context-variables-into-your-templates
---

# 追加のコンテキスト変数と機能をテンプレートに挿入する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

JSP templates are the predominant templating framework in @product@. テーマ、アプリケーション表示テンプレート（ADT）、DDMテンプレートなどは、JSPをテンプレートエンジンとして利用します。 JSPs, however, are not the only templating language supported by @product@. 多くの開発者は他のテンプレートフレームワーク（FreeMarkerなど）を好むため、@product@では、Context Contributorsフレームワークを提供することにより、他のテンプレート形式を使用できます。

JSPはJava EEに「ネイティブ」であるため、リクエストやセッションなど、プラットフォームに固有のすべてのコンテキストオブジェクトにアクセスできます。 これらのオブジェクトを通じて、開発者は通常、 `themeDisplay` または `serviceContext`などのコンテナーオブジェクトにアクセスすることで、@product@固有のコンテキスト情報を取得できます。

FreeMarkerのようなテンプレート形式はJava EEにネイティブではないため、これらのオブジェクトにアクセスできません。 テンプレートに現在のユーザー、ページなどのコンテキスト情報が必要な場合、Java EEはJSPの場合のようにテンプレートで利用できません。自分でテンプレートに挿入する必要があります。 @製品@しかし、注入することによって、あなたの頭のスタートを与える `contextObjects` 共通変数のマップを（例えば、 `themeDisplay`、 `ロケール`、 `、ユーザ`など）FreeMarkerのテンプレート（例えば、テーマ）にデフォルトで。 このマップは、通常、テンプレートの *コンテキスト* と呼ばれます。 @product@がデフォルトで提供しない他のコンテキストオブジェクトにアクセスする必要がある場合は、テンプレートのコンテキストを変更または追加する必要があります。 そのためには、コンテキストコントリビュータを作成します。

コンテキストコントリビューターは、テンプレートフレームワークで使用可能な変数と機能を注入することにより、テンプレートのコンテキストを変更します。 This lets you use non-JSP templating languages for themes, ADTs, and any other templates used in @product@. たとえば、ユーザーの組織に基づいてテーマの色を変更するとします。 コンテキストコントリビュータを作成して、ユーザーの組織をテーマのコンテキストに注入し、その情報に基づいてテーマの色を決定できます。

コンテキストコントリビュータは、デフォルトで@product@ですでに使用されています。 @product@の製品メニューの表示は、コンテキストコントリビューターによって注入された変数によって決定されます。 これについては後で詳しく説明します。

最初に、独自のコンテキストコントリビュータを作成する方法を学習し、次に@product@がコンテキストコントリビュータを使用する方法の1つの例を調べます。

1.  お気に入りのサードパーティツールを使用して汎用OSGiモジュールを作成するか、 [Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)使用します。

2.  モジュールの `src` ディレクトリに一意のパッケージ名を作成し、そのパッケージに新しいJavaクラスを作成します。 命名規則に従うには、コンテキスト固有の変数を注入するエンティティでクラス名を開始し、その後に *TemplateContextContributor* （たとえば、 `ProductMenuTemplateContextContributor`）を続けます。

3.  クラスの宣言のすぐ上に、次の注釈を挿入します。
   
        @Component(
            immediate = true,
            property = {"type=" + TemplateContextContributor.[Type of Contributor]},
            service = TemplateContextContributor.class
        )

    The `immediate` element instructs the module to start immediately once deployed to @product@. `Type` プロパティは、 [TemplateContextContributor](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateContextContributor.html) インターフェースで定義された

つのフィールドのいずれかに設定する必要があります： `TYPE_GLOBAL` または `TYPE_THEME`。 テーマにコンテキスト固有の変数のみを挿入する場合は、 `TYPE_THEME` フィールドを設定する必要があります。そうでない場合、 `TYPE_GLOBAL` フィールドを設定すると、テーマ、ADT、DDMテンプレートなど、@product@のすべてのコンテキスト実行に影響します。 最後に、 `サービス` 要素を `TemplateContextContributor.class`設定する必要があります。</p> 
   
   `ProductMenuTemplateContextContributor` クラスの `@Component` アノテーションは、同様のレイアウトに従います。
   
        @Component(
            immediate = true,
            property = {"type=" + TemplateContextContributor.TYPE_THEME},
            service = TemplateContextContributor.class
        )
       </li> 
   
   4  `-TemplateContextContributor` クラスに [TemplateContextContributor](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateContextContributor.html) インターフェイスを実装します。 これには、 `prepare（Map<String,Object>、HttpServletRequest）` メソッドの実装のみが必要です。
  
  `prepare` メソッドは、パラメータとして `contextObjects` マップを受け取ります。 これは、前に説明したテンプレートのコンテキストです。 このメソッドは、新規または変更された変数を `contextObjects` マップに注入して、コンテキストを編集するために使用する必要があります。</ol> 

`TemplateContextContributor` インターフェースを実装してテンプレートのコンテキストに変数を注入する方法の簡単な例については、デフォルトで@product@によって使用される `ProductMenuTemplateContextContributor` クラスを調べてください。 このクラスは、変数を@product@のFreeMarkerテーマに注入し、現在のテーマに製品メニューを表示するかどうかを決定します。

`ProductMenuTemplateContextContributor` クラスは、 `prepare（...）` メソッドを実装します。このメソッドは、変更された変数（`bodyCssClass`）と新しい変数（`liferay_product_menu_state`）をテーマコンテキストに注入します。

    @Override
    public void prepare(
        Map<String, Object> contextObjects, HttpServletRequest request) {
    
        if (!isShowProductMenu(request)) {
            return;
        }
    
        String cssClass = GetterUtil.getString(
            contextObjects.get("bodyCssClass"));
        String productMenuState = SessionClicks.get(
            request,
            ProductNavigationProductMenuWebKeys.
                PRODUCT_NAVIGATION_PRODUCT_MENU_STATE,
            "closed");
    
        contextObjects.put(
            "bodyCssClass", cssClass + StringPool.SPACE + productMenuState);
    
        contextObjects.put("liferay_product_menu_state", productMenuState);
    }
    

このメソッドは、コンテキストコントリビューターを準備して、変数をテーマに挿入して、製品メニューで使用します。 この例では、 `cssClass` および `productMenuState` 変数が定義されてから、 `contextObjects` マップに配置されます。 これにより、これらの変数はテーマコンテキストに挿入され、テーマからアクセスできるようになりました。 具体的には、 `cssClass` 変数は製品メニューのスタイルを提供し、 `productMenuState` 変数は表示されている製品メニューを開くか閉じるかを決定します。

上記の `prepare` メソッドは、次のifステートメントで製品メニューを表示するかどうかも決定します。

    if (!isShowProductMenu(request)) {
        return;
    }
    

`isShowProductMenu（...）` メソッドは、製品メニューを表示/非表示するオプションを提供することにより、テーマのコンテキストに機能を注入します。 このメソッドは、 `ProductMenuTemplateContextContributor` クラスにも含まれてい` 。</p>

<pre><code>protected boolean isShowProductMenu(HttpServletRequest request) {
    ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
        WebKeys.THEME_DISPLAY);

    if (themeDisplay.isImpersonated()) {
        return true;
    }

    if (!themeDisplay.isSignedIn()) {
        return false;
    }

    User user = themeDisplay.getUser();

    if (!user.isSetupComplete()) {
        return false;
    }

    return true;
}
`</pre> 

`ProductMenuTemplateContextContributor` は、製品メニューに直接関連する@product@のテーマに変数を注入する簡単な方法を提供します。 カスタムコンテキストコントリビュータでも同じことができます。 @product@の任意のコンテキストに追加の変数を挿入する機能により、選択したテンプレート言語の機能を完全に活用できます。



## 関連トピック

[製品メニューのカスタマイズ](/docs/7-1/tutorials/-/knowledge_base/t/customizing-the-product-menu)

[テーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[テーマ貢献者](/docs/7-1/tutorials/-/knowledge_base/t/packaging-independent-ui-resources-for-your-site)
