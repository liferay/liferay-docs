---
header-id: invoking-services-from-service-builder-code
---

# Service Builderコードからのサービスの呼び出し

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Service Builderアプリケーション内で作成されたすべてのサービスは、内部のSpringアプリケーションコンテキストを使用して配線されます。 これは、AOPプロキシを使用して、トランザクション、インデックス作成、およびセキュリティにサービスを適合させます。 モジュールの中で `モジュールspring.xml` スプリングアプリケーション・コンテキスト・ファイル、サービスビルダは、各エンティティの定義 `* LocalServiceImpl`、 `* ServiceImpl`、及び `* PersistenceImpl` のSpring Beanとしてクラスを。 たとえば、Service Builderは、 [Liferay Blade Service Builder `basic-service` sampleモジュールの](/docs/7-1/reference/-/knowledge_base/r/service-builder-samples) `src / main / resources / META-INF / spring / module-spring.xml` ファイルの `Foo` エンティティにSpring Beanを定義します。

    <?xml version="1.0"?>
    
    <beans
        default-destroy-method="destroy"
        default-init-method="afterPropertiesSet"
        xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
    >
        <bean class="com.liferay.blade.samples.servicebuilder.service.impl.FooLocalServiceImpl" id="com.liferay.blade.samples.servicebuilder.service.FooLocalService" />
        <bean class="com.liferay.blade.samples.servicebuilder.service.impl.FooServiceImpl" id="com.liferay.blade.samples.servicebuilder.service.FooService" />
        <bean class="com.liferay.blade.samples.servicebuilder.service.persistence.impl.FooPersistenceImpl" id="com.liferay.blade.samples.servicebuilder.service.persistence.FooPersistence" parent="basePersistence" />
    </beans>

サンプルコンテキストが定義するBeanの概要は次のとおりです。

| **インターフェースID**                                                                | **実装クラス**                                                                              |
|:----------------------------------------------------------------------------- |:-------------------------------------------------------------------------------------- |
| `com.liferay.blade.samples.servicebuilder.service.FooLocalService`            | `com.liferay.blade.samples.servicebuilder.service.impl.FooLocalServiceImpl`            |
| `com.liferay.blade.samples.servicebuilder.service.FooService`                 | `com.liferay.blade.samples.servicebuilder.service.impl.FooServiceImpl`                 |
| `com.liferay.blade.samples.servicebuilder.service.persistence.FooPersistence` | `com.liferay.blade.samples.servicebuilder.service.persistence.impl.FooPersistenceImpl` |

これらのクラスはSpring Beansであり、OSGi Declarative Servicesコンポーネントではないため、 `@Reference` Declarative Servicesアノテーション以外のアノテーションを使用して、Spring BeansおよびOSGiサービスを注入します。 Service Builder Spring Beanで使用できる推奨Liferayアノテーションを以下に示します。

  - [`@BeanReference` を使用して、アプリケーションコンテキストにあるSpring Beanを参照します。](#referencing-a-spring-bean-that-is-in-the-application-context)
  - [`@ServiceReference` を使用して、OSGiサービスを参照します。](#referencing-osgi-services)

| **重要：** MVCCが有効になっているサービスのサービスエンティティアップデート（例： `fooService.update（object）`）を呼び出すときは、必ず|トランザクションでそうします。 拒否されたトランザクションをUIに伝播して、ユーザーが|ハンドル。 詳細については、を参照してください| [マルチバージョン同時実行制御（MVCC）](/docs/7-1/tutorials/-/knowledge_base/t/defining-global-service-information#multiversion-concurrency-control-mvcc)

最初に `@BeanReference` アノテーションについて説明します。

## アプリケーションコンテキストにあるSpring Beanを参照する

`* LocalServiceImpl` クラスなどのService Builder Spring Beanクラスは、Liferayの `@BeanReference` アノテーションを使用して、モジュールのSpring Application Contextが定義する他のSpring Beanにアクセスする必要があります。

たとえば、サービスモジュールの `service.xml` ファイルが `Foo` および `Bar`という名前のエンティティのローカルサービスを定義する場合、Service Builderは両方のエンティティのローカルサービスSpring Beanを定義する `module-spring.xml` ファイルを生成します。 たとえば、 `BarLocalService` Spring Beanを `FooLocalServiceImpl` クラスに注入するには、 `FooLocalServiceImpl` クラスが `BarLocalService` フィールドを宣言し、 `@BeanReference` アノテーションを適用します。

    @BeanReference
    private BarLocalService _barLocalService;

`@BeanReference` 使用すると、LiferayのAOPは、必要に応じて、トランザクション、検索インデックス、またはセキュリティで使用するためのBean参照を処理できます。 参照クラスは、Spring Beanクラスのメソッドを呼び出すことができます。

Service Builderがアプリケーションで使用可能にするサービスに加えて、Service Builder Spring Beanクラスは、OSGiレジストリで公開されているすべてのサービスにもアクセスできます。 これは、次のサービスが利用できることを意味します。

  - Liferayのコアで定義されたBean
  - 他のモジュールアプリコンテキストで作成されたBean
  - OSGi宣言サービスを使用して宣言されたサービス
  - OSGi低レベルAPIを使用して登録されたサービス

これらはすべてOSGiサービスです。 次のセクションでは、OSGiサービスを参照するService Builder Spring Beanを示します。

## OSGiサービスの参照

多くの場合、Service Builderコード（Spring Bean）は外部サービスを使用する必要があります。 Liferayの `@ServiceReference` アノテーションにより、Liferay Spring BeansはOSGiサービスを参照できます。

あなたはその中で、単純な実体を持つアプリケーション、サービス・モジュールの定義を構築していると仮定 `service.xmlに` ファイル。 アプリケーションは、新しいエンティティが作成されるたびにSMSを送信する必要があり、システムにインストールされたモジュールによって `SMSService` が提供されます。

`* LocalServiceImpl` （Spring Bean）は、 `@ServiceReference` アノテーションを使用して、 *外部* サービスを参照できます。

    @ServiceReference
    private SMSService _smsService;

この注釈は、OSGiサービスへの参照を取得し、いくつかの素晴らしい利点を提供します。 `SMSService` サービスが使用可能になるまで、Springコンテキストは作成されません。 同様に、 `SMSService` 突然消えると、Spring Application Context全体が破棄されます。 これにより、Liferay Springアプリの堅牢性と汎用性が大幅に向上します。

幸いなことに、Service Builderは、 `service.xml` ファイルが参照するすべてのエンティティに対してこの種のコードを生成します。 たとえば、 [Liferay Blade Service Builderサンプルプロジェクト](/docs/7-1/reference/-/knowledge_base/r/service-builder-samples) `basic-service` モジュールの `service.xml` ファイルは、 `AssetEntry` エンティティを参照する `Foo` エンティティを定義します。

    <reference entity="AssetEntry" package-path="com.liferay.portlet.asset" />

Service Builderは `FooLocalServiceBaseImpl` クラス（基本クラスは `FooLocalServiceImpl` クラスの階層の一部です）を生成し、 `@ServiceReference`アノテーションが付けられたフィールドを使用して `AssetEntry` エンティティのローカルサービス `AssetEntryLocalService` を参照します。

    @ServiceReference(type = com.liferay.asset.kernel.service.AssetEntryLocalService.class)
    protected com.liferay.asset.kernel.service.AssetEntryLocalService assetEntryLocalService;

すばらしいです\！ これで、OSGiサービスへの参照をService Builder Spring Beanに追加する方法がわかりました。 また、Service Builder Spring Beanのアプリケーションコンテキストで他のSpring Beanへの参照を追加する方法も知っています。

## 関連トピック

[ローカルサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-local-services)

[リモートサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-remote-services)

[JSON Web Services Invoker](/docs/7-1/tutorials/-/knowledge_base/t/json-web-services-invoker)

[サービストラッカー](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)
