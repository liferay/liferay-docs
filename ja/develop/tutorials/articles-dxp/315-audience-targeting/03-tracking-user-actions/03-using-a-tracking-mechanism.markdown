---
header-id: using-a-tracking-mechanism
---

# 追跡メカニズムの使用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

管理者がカスタム指標を設定してレポートに保存しました。 それで？ メトリックは、定義されたニュースレターの `ビュー` イベントタイプを追跡するという目的を果たす必要があります。 これを行うには、追跡メカニズムを定義する必要があります。 ニュースレターでは、追跡メカニズムとして透明な画像を使用します。これには、 *View* 追跡イベント機能があります。 画像が表示されるたびに、ニュースレターのメトリックが情報を計算して保存します。

ニュースレターの指標には、オーディエンスターゲティングアプリが提供する追跡メカニズムを使用します。

1.  Content Targeting APIがイベントを追跡するために提供する分析プロセッサを設定します。 次のメソッドとプライベートフィールドを追加します。

    ``` java
    @Reference
    protected void setAnalyticsProcessor(AnalyticsProcessor analyticsProcessor) {
        _analyticsProcessor = analyticsProcessor;
    }

    private AnalyticsProcessor _analyticsProcessor;
    ```

    分析プロセッサには、Liferayページからの分析（ビュー、クリックなど）を追跡するサーブレットと、この追跡メカニズムを活用するAPIが含まれています。 `setAnalyticsProcesoor（...）` メソッドでは、現在のアナリティクスプロセッサの参照を取得して、透明な画像の生成に使用されるURLを構築しています。 必要なのは、生成されたURLをニュースレターのHTMLに挿入し、透明な画像がそれを読んだ人を追跡することだけです。 すべてはデフォルトのAudience Targeting Analyticsシステムによって自動的に処理されます。

    アナリティクスプロセッサのリファレンスを取得したので、適切なトラッキングURLを生成するためのロジックを追加する必要があります。

2.  `populateContext` メソッドを更新されたメソッドに置き換えます。

    ``` java
    @Override
    protected void populateContext(
        TrackingActionInstance trackingActionInstance,
        Map<String, Object> context, Map<String, String> values) {

        String alias = StringPool.BLANK;
        String elementId = StringPool.BLANK;
        String eventType = StringPool.BLANK;
        String trackImageHTML = StringPool.BLANK;

        if (!values.isEmpty()) {
            alias = values.get("alias");
            elementId = values.get("elementId");
            eventType = values.get("eventType");
        }
        else if (trackingActionInstance != null) {
            alias = trackingActionInstance.getAlias();
            elementId = trackingActionInstance.getElementId();
            eventType = trackingActionInstance.getEventType();

            String trackImageURL = _analyticsProcessor.getTrackingURL(
                trackingActionInstance.getCompanyId(), 0, 0, "", 0,
                Campaign.class.getName(),
                new long[] {trackingActionInstance.getCampaignId()},
                trackingActionInstance.getElementId(), "view", "");

            trackImageHTML = "<img alt=\"\" src=\"" + trackImageURL + "\" />";
        }

        context.put("alias", alias);
        context.put("elementId", elementId);
        context.put("eventType", eventType);
        context.put("eventTypes", getEventTypes());
        context.put("trackImageHTML", trackImageHTML);
    }
    ```

    この更新されたメソッドは、 `trackImageHTML`という名前の新しい変数を作成し、アナリティクスプロセッサを使用してトラッキングURLを取得してから、 `trackImageHTML` コンテキスト変数を設定します。 新しいメトリックを作成するとき、透明な画像のURLフィールドはメトリックのフォームに存在しません。 ただし、メトリックが最初に保存されるとき、URLは分析プロセッサを使用して生成され、コピーに使用できます。

優秀な\！ アナリティクスプロセッサを入手し、透明な画像追跡メカニズムを作成できます。 ニュースレターのメトリックの動作が定義され、完全です。 あなたがする必要がある最後のことは、JSPテンプレートを作成することです。
