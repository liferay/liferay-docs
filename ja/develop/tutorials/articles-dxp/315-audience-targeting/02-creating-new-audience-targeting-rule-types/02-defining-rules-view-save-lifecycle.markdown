---
header-id: defining-a-rules-view-save-lifecycle
---

# ルールの表示/保存ライフサイクルの定義

[TOC levels=1-4]

ビュー/保存ライフサイクルは、管理者がユーザーセグメントエディターを使用してユーザーセグメントにルールを適用する場合の舞台裏のプロセスを示します。 これを実装します。

ユーザーがユーザーセグメントエディターを開くと、ルール作成のためのレンダリングフェーズが始まります。 レンダリング段階では、フォームのHTMLが生成され、必要に応じて、フォームを作成するために必要なパラメーターを含む `コンテキスト` マップが生成されます。 HTMLが正常に取得され、ユーザーが値を設定して *Save*をクリックすると、アクションフェーズが開始されます。

アクションフェーズが始まると、 `processRule（...）` メソッドは、フォームによって提供された値を取得して永続化します。 ルール処理が終了すると、フォームがリロードされ、ライフサイクルが再開されます。 ルールで選択された値は保存され、ユーザーセグメントの評価が開始されるとアクセスできるようになります。

このセクションでは、気象ルールのJavaクラスの定義を開始します。 これは、前の [チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-custom-rule-type)の指示に従って、 `WeatherRule` クラスを作成し、 `com.liferay.content.targeting.api.model.BaseJSPRule`を拡張したことを前提としています。

| **注：** れているコンテンツターゲットクラスのJavadocを表示するにはこの記事、ダウンロード| [Javadoc JAR](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)。

`content-targeting-rule` Blade CLIテンプレートを使用した場合、プロジェクトはすでに `BaseJSPRule` を拡張しており、デフォルトの `view.jsp` ファイルがすでに作成されています。

1.  テンプレートを使用しなかった場合は、アクティブ化および非アクティブ化メソッドをクラスに追加します。

    ``` java
    @Activate
    @Override
    public void activate() {
        super.activate();
    }

    @Deactivate
    @Override
    public void deActivate() {
        super.deActivate();
    }
    ```

    これらのメソッドは、スーパークラス [`com.liferay.content.targeting.api.model.BaseRule`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc) を呼び出して、ルールの開始と停止に必要なログと処理を実装します。 必ず、 [@Activate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Activate.html) および [@Deactivate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Deactivate.html) アノテーションを含めてください。これらは必須です。

2.  ユーザーセグメントエディタに表示されたときに、ルールのカテゴリを定義します。 `getRuleCategoryKey（）` メソッドを見つけて、以下のコードに置き換えます。

    ``` java
    @Override
    public String getRuleCategoryKey() {
        return SessionAttributesRuleCategory.KEY;
    }
    ```

    このコードは、ウェザールールを[セッション属性]カテゴリに配置します。 ルールを適切なカテゴリに入れるには、 `getRuleCategoryKey` メソッドを使用して、カテゴリクラスのキーを返します。 利用可能なカテゴリクラスは、 [`com.liferay.content.targeting.rule.categories.BehaviourRuleCategory`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)、 [`com.liferay.content.targeting.rule.categories.SessionAttributesRuleCategory`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)、 [`com.liferay.content.targetingを。 rule.categories.SocialRuleCategory`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)および [`com.liferay.content.targeting.rule.categories.UserAttributesRoleCategory`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)。

    ![図1：この例の天気ルールは、セッション属性カテゴリに存在するように変更されました。](../../../images-dxp/new-category-rule.png)

3.  `populateContext（）` メソッドを見つけて、以下のコードに置き換えます。

    ``` java
    @Override
    protected void populateContext(
        RuleInstance ruleInstance, Map<String, Object> context,
        Map<String, String> values) {

        String weather = "";

        if (!values.isEmpty()) {
            weather = GetterUtil.getString(values.get("weather"));
        }
        else if (ruleInstance != null) {
            weather = ruleInstance.getTypeSettings();
        }

        context.put("weather", weather);
    }
    ```

    この方法で達成できることを理解するには、ルールの構成ライフサイクルを調べる必要があります。

    ![図2：オーディエンスターゲティングルールは、ユーザーが構成し、ユーザーセグメントの一部になる前に処理する必要があります。](../../../images-dxp/rule-lifecycle.png)

    ユーザーがユーザーセグメントエディターを開くと、ルールのレンダリングフェーズが開始されます。 `getFormHTML（...）` メソッドは、表示するHTMLを取得します。 このメソッドは、拡張している `BaseJSPRule` クラスに既に実装されているため、このメソッドの実装について心配する必要はありません。 `getFormHTML` メソッドは、 `populateContext（...）` メソッドを呼び出します。

    `populateContext` メソッドが [`com.liferay.content.targeting.api.model.Rule`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc) インターフェースで使用できないことに気づくでしょう。 これは、すべてのケースで必要なわけではないためです。 これは、 `BaseJSPRule` クラスを拡張することで利用可能になり、天気ルールにはより多くのロジックが必要になります。

    `PopulateContext` メソッドは、JSPビューがルールのHTMLをレンダリングするために必要なすべてのパラメーターを含むマップを生成します。 このマップは、 `コンテキスト` 変数に格納されます。 この変数は、オーディエンスターゲティングルールのフォーム評価コンテキストを定義するマップです。 各ルールは、特定のパラメーターを提供します。 上記の `PopulateContext` メソッドは、 `Weather` コンテキスト変数に、 `値` マップパラメーターから `天気` 値を入力し、JSPに渡されます。

    気象ルールの場合、 `populateContext` メソッドは3つの使用例を説明します。

    a。 ルールが追加されましたが、まだ設定値がありません。 この場合、開発者が定義したデフォルト値が挿入されます（例： `weather = ""`）。

    b。 ルールが追加され、値が設定されましたが、リクエストが完了できませんでした（たとえば、エラーが原因で）。 この場合、 `PopupContext` メソッドの `値` パラメータには、保存することを意図した値が含まれており、エラーメッセージとともにルールのビューに表示されるように、これらの値が挿入されます。

    c。 ルールが追加され、値が正常に設定されました。 この場合、 `値` パラメータは空であり、フォームが表示する値をストレージから取得し、それらをコンテキストに挿入して、ルールのHTMLに表示する必要があります。 気象ルールはルールインスタンスの `typeSettings` フィールドを使用しますが、複雑なルールはサービスを使用して値を格納できます。

    `PopupContext` メソッドは、JSPとバックエンドコードの間の仲介者と考えることができます。 JSPを使用して気象規則のUIを作成する方法については、 [規則のUIの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-rules-ui)ます。 HTMLが正常に取得され、ユーザーが天気の値を設定して[ *保存*をクリックすると、アクションフェーズが開始されます。

4.  `processRule（）` メソッドを次のコードに置き換えます。

    ``` java
    @Override
    public String processRule(
        PortletRequest portletRequest, PortletResponse portletResponse,
        String id, Map<String, String> values) {

        return values.get("weather");
    }
    ```

    アクションフェーズが開始されると、 `processRule（...）` メソッドが呼び出されます。 `値` パラメーターには、ユーザーがフォームに追加した値のみが含まれます。 `processRule` メソッドに追加できるロジックの概要を以下に示します。

    a。 `値` パラメータから値を取得します。

    b。 （オプション）データの整合性と考えられるエラーを検証します。 何かが間違っている場合は、 [`com.liferay.content.targeting.exception.InvalidRuleException`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc) をスローし、値の保存を禁止します。 気象ルールのシナリオでは、フォームで例外がスローされた後にルールが再ロードされると、前のステップのケース3bが発生します。

    c。 ルールインスタンスの `typeSettings` フィールドに格納される値を返します。 `typeSettings` フィールドは、ルールインスタンステーブルのフレームワークによって管理されます。 ルールに独自のストレージメカニズムがある場合は、 `processRule` メソッドでサービスを呼び出す必要があります。

    ルール処理が終了すると、フォームがリロードされ、ライフサイクルが再開されます。 ルールで選択された値は保存され、ユーザーセグメントの評価が開始されるとアクセスできるようになります。 ルールの評価を定義する前に、 `WeatherRule` クラスにさらに2つのメソッドを追加する必要があります。

5.  ルールのローカライズされた概要を取得する方法を定義します。 多くの場合、これは、ルールのリソースバンドル内のキーを、ルール用に保存されている情報と組み合わせることで実現できます。 気象ルールの場合、選択した気象条件を含むルールのタイプ設定を返すことができます。 生成された `getSummary（）` メソッドを次のメソッドで置き換えます。

    ``` java
    @Override
    public String getSummary(RuleInstance ruleInstance, Locale locale) {
        return ruleInstance.getTypeSettings();
    }
    ```

6.  ルールのサーブレットコンテキストを設定します。 このメソッドは生成され、そのままにしておくことができます。

    ``` java
    @Override
    @Reference(
        target = "(osgi.web.symbolicname=weather)",
        unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }
    ```

    サーブレットコンテキストの設定は、 `BaseJSPRule` クラスを拡張するルールでのみ必要です。 ルールが独自のJSPファイルをレンダリングするには、サーブレットコンテキストを設定する必要があります。 ルールモジュールがLiferayにインストールされて解決されると、 `setServletContext` メソッドが自動的に呼び出されます。 `@Reference` アノテーションの `target` プロパティの `osgi.web.symbolicname` が、モジュールの `bnd.bnd` ファイルで定義されている `Bundle-SymbolicName` と同じ値に設定されていることを確認します。

次に、構成されてユーザーセグメントに保存されたルールを評価する方法を学習します。
