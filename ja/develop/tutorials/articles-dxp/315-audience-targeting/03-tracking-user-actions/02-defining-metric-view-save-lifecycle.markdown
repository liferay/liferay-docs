---
header-id: defining-a-metrics-view-save-lifecycle
---

# メトリックのビューの定義/ライフサイクルの保存

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

このセクションでは、メトリックのビュー/保存ライフサイクルを定義します。ユーザーがレポートエディターを使用してレポートにメトリックを適用するとどうなるかを定義します。

| **注：** れているコンテンツターゲットクラスのJavadocを表示するにはこの記事、ダウンロード| [Javadoc JAR](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)。

ニュースレターメトリックのJavaクラスの定義を開始します。 これは、前の記事の手順に従って `NewsletterTrackingAction` クラスを作成し、 `com.liferay.content.targeting.api.model.BaseJSPTrackingAction`を拡張することを前提としています。 `content-targeting-tracking-action` Blade CLIテンプレートを使用した場合、プロジェクトはすでに `BaseJSPTrackingAction` を拡張しており、デフォルトの `view.jsp` ファイルがすでに作成されています。

1.  クラスにアクティブ化および非アクティブ化メソッドを追加します。

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

    これらのメソッドは、スーパークラス `com.liferay.content.targeting.api.model.BaseTrackingAction` を呼び出して、メトリックが開始および停止するときに必要なログと処理を実装します。 必ず、 [@Activate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Activate.html) および [@Deactivate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Deactivate.html) アノテーションを含めてください。これらは必須です。

2.  次のメソッドを追加します。

    ``` java
    @Override
    protected void populateContext(
        TrackingActionInstance trackingActionInstance,
        Map<String, Object> context, Map<String, String> values) {

        String alias = StringPool.BLANK;
        String elementId = StringPool.BLANK;
        String eventType = StringPool.BLANK;

        if (!values.isEmpty()) {
            alias = values.get("alias");
            elementId = values.get("elementId");
            eventType = values.get("eventType");
        }
        else if (trackingActionInstance != null) {
            alias = trackingActionInstance.getAlias();
            elementId = trackingActionInstance.getElementId();
            eventType = trackingActionInstance.getEventType();
        }

        context.put("alias", alias);
        context.put("elementId", elementId);
        context.put("eventType", eventType);
        context.put("eventTypes", getEventTypes());
    }
    ```

    この方法で何が行われるかを理解するには、メトリックの構成ライフサイクルを確認する必要があります。

    ![図1：オーディエンスターゲティング指標は、ユーザーが設定し、レポートの一部になる前に処理する必要があります。](../../../images-dxp/metric-lifecycle.png)

    ユーザーがレポートエディターを開くと、メトリックのレンダリングフェーズが開始されます。 `getFormHTML（...）` メソッドは、表示するHTMLを取得します。 このメソッドは、拡張している `BaseJSPTrackingAction` クラスにすでに実装されているため、このメソッドの実装について心配する必要はありません。 `getFormHTML` メソッドは、 `populateContext（...）` メソッドを呼び出します。

    `PopupContext` メソッドが `TrackingAction` インターフェースで使用できないことに気づくでしょう。 これは、すべてのケースで必要なわけではないためです。 これは、 `BaseJSPTrackingAction` クラスを拡張することで利用でき、ニュースレターメトリックのロジックをさらに追加する必要があります。

    `PopulateContext` メソッドは、JSPビューがメトリックのHTMLをレンダリングするために必要なすべてのパラメーターを含むマップを生成します。 このマップは `コンテキスト` 変数に格納されます。これは、ポートレットロジックの基本的な値が事前に入力されており、各メトリックが特定のパラメーターを提供します。 `populateContext` を移入上記方法 `エイリアス`、 `ELEMENTID`、 `のeventType`、及び `イベントタイプ` から隣接する値を有するコンテキスト変数 `の値` 、次にJSPに渡されるマップパラメータ。

    ニュースレターのメトリックでは、 `PopulateContext` メソッドが3つの使用例を説明します。

    a。 指標が追加されましたが、まだ設定値はありません。 この場合、開発者が定義したデフォルト値が挿入されます（例： `alias = ""`）。

    b。 メトリックが追加され、値が設定されましたが、リクエストが完了できませんでした（たとえば、エラーが原因で）。 この場合、 `PopupContext` メソッドの `値` パラメーターには、保存することを意図した値が含まれており、エラーメッセージとともにメトリックのビューに表示されるように、それらの値が挿入されます。

    c。 メトリックが追加され、値が正常に設定されました。 この場合、 `値` パラメーターは空であり、フォームが表示する値をストレージから取得して、それらをコンテキストに挿入して、メトリックのHTMLに表示する必要があります。 ニュースレターのメトリックは、メトリックのインスタンスに値を格納しますが、複雑なメトリックは、サービスを使用して値を格納できます。

    `populateContext` メソッドは、JSPとバックエンドコードの間の仲介者と考えることができます。 [メトリックのUIの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-metrics-ui) セクションにスキップすることにより、JSPを使用してニュースレターメトリックのUIを作成する方法を確認できます。 HTMLが正常に取得され、ユーザーがニュースレターの値を設定して *Save*をクリックすると、アクションフェーズが開始します。

3.  アクションフェーズが始まると、オーディエンスターゲティングは追跡アクション（メトリック）を処理します。 `processTrackingAction（...）` メソッドは、 [メトリックのUIフォーム](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-metrics-ui) から値を取得し、それらを `trackingActionInstance`対応するフィールドに格納します。 `BaseTrackingAction` クラスは `null`を返すこのメソッドのデフォルト実装を提供するため、 `NewsletterTrackingAction` クラスはそれを実装する必要はありません。

    メトリックのカスタムフィールドを処理する必要がある場合は、このメソッドをオーバーライドする必要があります。 カスタム値を `TrackingActionInstance`の `typeSettings` フィールドに格納する場合は、 `nullの代わりにそれらの値を返します`。

    | **注：** より複雑なケースでは、保存する独自のサービスを作成できます。データベースへのメトリックの情報。 サービスを呼び出す必要があります '| `processTrackingAction` メソッド内のロジックを更新します。 詳細については|サービスの作成については、| [サービスビルダー](/docs/7-1/tutorials/-/knowledge_base/t/service-builder) |チュートリアル。

    メトリック処理が終了すると、フォームが再ロードされ、ライフサイクルが再び再開されます。 メトリックで指定された値は保存され、レポートの生成が始まるとすぐにアクセスできます。 次に、ニュースレターの指標で評価する必要があるイベントタイプを設定する必要があります。

4.  次のメソッドとプライベートフィールドを追加します。

    ``` java
    @Override
    public List<String> getEventTypes() {
        return ListUtil.fromArray(_EVENT_TYPES);
    }

    private static final String[] _EVENT_TYPES = {"view"};
    ```

    これは、ニュースレターのメトリックがニュースレターを閲覧した人のみを追跡することを指定します。

5.  メトリックのローカライズされた概要を取得する方法を定義します。 多くの場合、これは、メトリックのリソースバンドル内のキーを、メトリック用に保存されている情報と組み合わせることで実現できます。 ニュースレターメトリックの場合、追跡されているニュースレターのIDに関する情報を提供できます。これは、 `trackingActionInstance` オブジェクトの `エイリアス` フィールドに格納されます。

    ``` java
    @Override
    public String getSummary(
        TrackingActionInstance trackingActionInstance, Locale locale) {

        return LanguageUtil.get(
            locale, trackingActionInstance.getTypeSettings());
    }
    ```

6.  メトリックのサーブレットコンテキストを設定します。

    ``` java
    @Override
    @Reference(
        target = "(osgi.web.symbolicname=newsletter)",
        unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }
    ```

    これは、 `BaseJSPTrackingAction` クラスを拡張するメトリックにのみ必要です。 メトリックが独自のJSPファイルをレンダリングするには、サーブレットコンテキストを設定する必要があります。 `setServletContext` メソッドは、メトリックモジュールがLiferayにインストールされて解決されるときに自動的に呼び出されます。 `@Reference` アノテーションの `target` プロパティの `osgi.web.symbolicname` が、モジュールの `bnd.bnd` ファイルで定義されている `Bundle-SymbolicName` と同じ値に設定されていることを確認します。

次に、メトリックが使用する追跡メカニズムを定義します。
