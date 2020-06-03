---
header-id: best-practices-for-audience-targeting
---

# オーディエンスターゲティングのベストプラクティス

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ルールを作成したので、追加のルールを作成するときに留意すべきいくつかのベストプラクティスを次に示します。 いくつかのベストプラクティスを実行する前に、ルールに指定できる3つのコンポーネントを理解する必要があります。

  - *ルールの動作*
  - *設定用UI（オプション）*
  - *言語キー（オプション）*

ルールの動作とそのUI構成については、言語キーとその作成方法、使用方法、および生成方法の詳細について説明し、 [国際化](/docs/7-1/tutorials/-/knowledge_base/t/internationalization) チュートリアルにアクセスしてください。

## UIテクノロジーの選択

オーディエンスターゲティングでは、任意のフロントエンドテクノロジーを選択できますが、オーディエンスターゲティングの拡張ビューにはJSPが推奨されるテクノロジーです。 ただし、FreeMarkerビューは、それぞれの基本クラス（たとえば、 `BaseFreemarkerRule` または `BaseFreemarkerTrackingAction`）を通じて引き続きサポートされます。 JSPまたはFreeMarker以外のテクノロジーを使用してUIを実装することに興味がある場合は、メソッド `getFormHTML` を `-Rule` または `-TrackingAction` クラスに追加できます。 `getFormHTML` メソッドの実装例を次に示します。

``` java
@Override
public String getFormHTML(
    RuleInstance ruleInstance, Map<String, Object> context,
    Map<String, String> values) {

    String content = "";

    try {
        populateContext(ruleInstance, context, values);

        content = ContentTargetingContextUtil.parseTemplate(
            getClass(), getFormTemplatePath(), context);
    }
    catch (Exception e) {
        _log.error(
            "Error while processing template " + getFormTemplatePath(), e);
    }

    return content;
}
```

`getFormHTML` は、選択したテクノロジーによって作成されたHTMLを取得し、ルールのフォームから表示可能な文字列として返すために使用されます。 したがって、JSPまたはFreeMarkerの代替を使用する場合は、 `-Rule` または `-TrackingAction` クラスでこのメソッドを作成および変更して、このメソッドをオーバーライドする必要があります。

## その他のベストプラクティス

オーディエンスターゲティングルールを実装して導入する際に考慮すべき点は次のとおりです。

  - ルールインスタンステーブルのフレームワークによって管理される `typeSettings` フィールドに複雑な情報を格納する代わりに、ルールプラグインでサポートされている [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)を使用してデータベースに永続化することを検討できます。

  - ルールを本番環境にデプロイする場合、すべてのリクエストで同じ値を取得することは非常に非効率的であり、ポータルの速度が低下する可能性があるため、値をキャッシュに追加することを検討することをお勧めします（たとえば、異なる場所の天気）。 たとえば、 `evaluate` メソッドが呼び出されると、現在のユーザーID、現在のユーザーの天気予報、およびユーザーが最初にページにアクセスした時刻を取得できます。 次に、キャッシュされた時間が3時間以上経過した場合にのみルールを評価できます。 これにより、ユーザーがページにアクセスするたびにルールが評価されなくなります。 これは、サービスを使用して行うのが最適です。

  - `-Rule`の `BaseJSPRule.deleteData` メソッドをオーバーライドして、現在削除されているルールに関連付けられているデータを削除することができます。

  - ルールがステージング可能なデータまたはデータへの参照（ページまたはWebコンテンツ記事への参照など）を処理する場合、コンテンツを管理するために `BaseRule.exportData` および `BaseRule.importData` メソッドをオーバーライドする必要がある場合があります正しく。</p></li>
</ul>
