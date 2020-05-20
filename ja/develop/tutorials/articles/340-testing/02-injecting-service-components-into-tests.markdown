---
header-id: injecting-service-components-into-tests
---

# 統合テストへのサービスコンポーネントの注入

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product@の `@Inject` アノテーションを使用して、 [`@Reference` アノテーションを使用してサービスコンポーネント](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services) をOSGiコンポーネントに挿入するように、統合テストにサービスコンポーネントを挿入できます。

| **注：** | [アルキリアン](http://arquillian.org/) |プラス| [JUnit](https://junit.org) |アノテーションは、統合テストを開発する1つの方法です。 Liferayを使用して|必要なテストフレームワークは何でも。

`@Inject` は、リフレクションを使用して、フィールドのインターフェイスに一致するサービスコンポーネントオブジェクトをフィールドに注入します。 [テストルール `LiferayIntegrationTestRule`](@platform-ref@/7.1-latest/javadocs/portal-test-integration/com/liferay/portal/test/rule/LiferayIntegrationTestRule.html) は注釈を提供します。 注釈は、 `フィルター` および `タイプ` パラメーターを受け入れます。これらのパラメーターは、個別にまたは一緒に使用できます。

特定の実装またはサブクラスオブジェクトでフィールドを埋めるには、 `タイプ` を設定します。

    @Inject(type = SubClass.class)

`SubClass` を、注入するサービスインターフェイスの名前に置き換えます。

`DDLServiceUpgrade` オブジェクトを `UpgradeStepRegistrator` インターフェイスフィールドに挿入するテストクラスの例を次に示します。

``` 
public class Test {

    @ClassRule
    @Rule
    public static final AggregateTestRule aggregateTestRule = 
        new LiferayIntegrationTestRule();

    @Test
    public void testSomething() {
        // your test code here
    }

    @Inject(
        filter = "(&(objectClass=com.liferay.dynamic.data.lists.internal.upgrade.DDLServiceUpgrade))"
    )
    private static UpgradeStepRegistrator _upgradeStepRegistrator;

} 
```

サービスコンポーネントをテストクラスに注入する方法は次のとおりです。

1.  テストクラスで、 [タイプ `ルールフィールドcom.liferay.portal.test.rule.LiferayIntegrationTestRule`](@platform-ref@/7.1-latest/javadocs/portal-test-integration/com/liferay/portal/test/rule/LiferayIntegrationTestRule.html)追加します。 例えば、
   
        @ClassRule
        @Rule
        public static final AggregateTestRule aggregateTestRule = 
            new LiferayIntegrationTestRule();

2.  サービスコンポーネントを保持するフィールドを追加します。 フィールドを静的にすると、コンテナがテストの実行前に静的フィールドを1回挿入し、すべてのテストの実行後にそれらを無効にするため、効率が向上します。 非静的フィールドは各テストの実行前に挿入されますが、すべてのテストが終了するまでメモリに残ります。

3.  フィールドに `@Inject` 注釈を付けます。 デフォルトでは、コンテナはフィールドのタイプに一致するサービスコンポーネントオブジェクトをフィールドに注入します。

4.  オプションで、 `フィルター` 文字列または `タイプ` パラメーターを追加して、注入するサービスコンポーネントオブジェクトをさらに指定します。

実行時に、 `@Inject` アノテーションは、一致するサービスコンポーネントが利用可能になるまでテストをブロックします。 ブロックにはタイムアウトがあり、テストで使用できない依存関係に関するメッセージが記録されます。

| **重要**：注入するサービスコンポーネントを公開している場合、|テストが実行されない可能性があります。 テストからサービスコンポーネントを公開する必要がある場合|クラス、使用| [サービストラッカー](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers) |サービスコンポーネントにアクセスします。

すばらしいです\！ これで、テストにサービスコンポーネントを注入できます。

## 関連記事

[サービストラッカー](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)
