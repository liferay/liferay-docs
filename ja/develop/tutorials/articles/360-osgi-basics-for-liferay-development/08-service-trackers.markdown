---
header-id: service-trackers
---

# サービストラッカー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

OSGiランタイムエコシステムでは、モジュールが他のモジュールのサービスに依存して機能する方法を考慮する必要があります。 サービスの実装をスワップアウトまたは完全に削除することは可能です。また、この環境でモジュールが生き残るだけでなく繁栄する必要があります。

`@Component` クラスから [サービス](/docs/7-1/tutorials/-/knowledge_base/t/invoking-local-services) を呼び出す場合、簡単です。別の [宣言サービス（DS）](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html) アノテーション `@Reference`を使用して、サービス参照を取得します。 参照されたサービスが利用可能になると、コンポーネントがアクティブになります。

| **注：** `@Reference` 注釈は、次のクラスでのみ使用できます。 `@Component`注釈が付けられています。 つまり、宣言型サービスコンポーネントのみができます。 `@Reference` を使用して、OSGiサービスにバインドします。

DSを使用して、 `@Component` および `@Reference` アノテーションを活用できる場合は、そうする必要があります。 DSは、サービスダイナミズムの処理の複雑さの多くを透過的に処理します。

DSを使用してコンポーネントを作成できない場合は、サービストラッカーを実装してサービスレジストリ内のサービスを検索する方法を学習してください。

![図1：OSGiサービスレジストリに登録されているサービス実装は、Service Trackerを使用してアクセスできます。](../../images/service-registry.png)

| **注：** WARスタイルのプロジェクトでService Trackerを使用する場合、以下を行う必要があります。必要な `org.osgi.core` 依存関係をビルドファイルで慎重に構成します| （例えば、 `build.gradle`、 `のpom.xml`、等）のエラーを回避します。 に含まれているので| @product@はデフォルトで `が`設定されている必要があります。 を参照してください| [サードパーティパッケージポータルのエクスポート](/docs/7-1/reference/-/knowledge_base/r/third-party-packages-portal-exports) |詳細についてはチュートリアル。

Service Trackerを使用する必要があるシナリオは何ですか？ 我々はDSのシナリオに焦点を当てている点に注意してください *できません* 使用すること。 これには通常、非ネイティブ（OSGiへの）依存性注入フレームワークが含まれます。

  - [Spring MVCポートレットからOSGiサービスを呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/spring-mvc)
  - OSGiサービスの呼び出し [WARパッケージ化ポートレット](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-plugins-to-liferay-7) 製品-VER @ @上で実行するようにアップグレードされていますが、ない [完全にモジュール](/docs/7-1/tutorials/-/knowledge_base/t/modularizing-an-existing-portlet) とOSGiのモジュール化しました


<!-- TODO: readd JSF part, when available. -Cody

-   Calling OSGi services from a
    [JSF portlet](develop/tutorials/-/knowledge_base/7-1/jsf-portlets-with-liferay-faces)
-->

| **注：** 静的ユーティリティクラス（例： `UserLocalServiceUtil`） Liferay Portal 6.2（およびそれ以前）で有用であり、互換性のために存在しますが、可能であれば、呼び出さないでください。 静的ユーティリティクラスはOSGiを説明できません。ランタイムの動的環境。 静的クラスを使用する場合、|停止したサービスまたはデプロイまたは開始されていないサービスを呼び出します。 これ|回復不能な実行時エラーを引き起こす可能性があります。 ただし、Service Trackersは役立ちます| OSGiに優しいサービス呼び出しを行います。

OSGi以外のアプリケーションは、独自の [Service Builderサービス](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder) やLiferayのモジュールで公開されているサービス（人気のある `UserLocalService`）を含め、Service Trackerを使用してOSGiランタイムに登録されたサービスにアクセスできます。

## Service Trackerの実装

Service Trackerでは、DSを使用してサービスの依存関係を管理することはできませんが、サービスレジストリからサービスを呼び出すことができます。

サービストラッカーは、2つの方法で実装できます。1）必要なコード内、または2） `を拡張するクラス内org.osgi.util.tracker.ServiceTracker`。

直接作成するには、次を実行します。

    import org.osgi.framework.Bundle;
    import org.osgi.framework.FrameworkUtil;
    import org.osgi.util.tracker.ServiceTracker;
    
    Bundle bundle = FrameworkUtil.getBundle(this.getClass());
    BundleContext bundleContext = bundle.getBundleContext();
    ServiceTracker<SomeService, SomeService> serviceTracker =
        new ServiceTracker(bundleContext, SomeService.class, null);
    serviceTracker.open();
    SomeService someService = serviceTracker.waitForService(500);

より良い方法は、 `org.osgi.util.tracker.ServiceTracker`を拡張するクラスを作成することです。これにより、コードが簡単になります。

1.  `ServiceTracker`を拡張するこのようなクラスを作成します。
   
        public class SomeServiceTracker
            extends ServiceTracker<SomeService, SomeService> {
       
            public SomeServiceTracker(Object host) {
                super(
                    FrameworkUtil.getBundle(host.getClass()).getBundleContext(),
                    SomeService.class, null);
            }
        }

2.  サービスを使用するロジックの初期化部分から、サービストラッカーコンストラクターを呼び出します。 `Object host` パラメーターは、独自のバンドルコンテキストを取得します。正確な結果を得るには、独自のバンドルのオブジェクトである必要があります。
   
        ServiceTracker<SomeService, SomeService> someServiceTracker =
            new SomeServiceTracker(this);

3.  Service Trackerを使用する場合は、通常はできるだけ早く開きます。
   
        someServiceTracker.open();

4.  サービスを使用する前に、Service Trackerを使用してサービスの状態を調べます。 たとえば、プログラムロジックで、使用する前にサービスが `null` かどうかを確認します。
   
        SomeService someService = someServiceTracker.getService();
       
        if (someService == null) {
            _log.warn("The required service 'SomeService' is not available.");
        }
        else {
            someService.doSomethingCool();
        }

    Service Trackerには、追跡されたサービスを内省するための他のユーティリティ機能がいくつかあります。

5.  後でアプリケーションが破棄またはアンデプロイされるときに、サービストラッカーを閉じます。
   
        someServiceTracker.close();

Service Trackerを使用すると、OSGiランタイムの外部からOSGiサービスを呼び出すことができます。

## サービスのコールバックハンドラーの実装

サービスが利用できない可能性が高い場合、または複数のサービスを追跡する必要がある場合、Service Tracker APIはサービス *イベント*で動作するコールバックメカニズムを提供します。 これを使用するには、 `ServiceTracker`の `addService` および `removedService` メソッドをオーバーライドします。 それらの `ServiceReference` パラメーターは、アクティブなサービスオブジェクトを参照します。

以下は、 [OSGi AllianceのOSGi Core Release 7仕様](https://osgi.org/specification/osgi.core/7.0.0/util.tracker.html#d0e51991)からの `ServiceTracker` 実装の例です。

    new ServiceTracker<HttpService, MyServlet>(context, HttpService.class, null) {
    
        public MyServlet addingService(ServiceReference<HttpService> reference) {
            HttpService httpService = context.getService(reference);
            MyServlet myServlet = new MyServlet(httpService);
            return myServlet;
        }
    
        public void removedService(
            ServiceReference<HttpService> reference, MyServlet myServlet) {
            myServlet.close();
            context.ungetService(reference);
        }
    }

`HttpService` がOSGiレジストリに追加されると、この `ServiceTracker` は、新しく追加されたサービスを使用する新しいラッパークラス `MyServlet`作成します。 サービスがレジストリから削除されると、 `removedService` メソッドが関連リソースをクリーンアップします。

`ServiceTracker` メソッドを直接オーバーロードする代わりに、 `org.osgi.util.tracker.ServiceTrackerCustomizer`作成します。

    class MyServiceTrackerCustomizer 
        implements ServiceTrackerCustomizer<SomeService, MyWrapper> {
    
        private final BundleContext bundleContext;
    
        MyServiceTrackerCustomizer(BundleContext bundleContext) {
            this.bundleContext = bundleContext;
        }
    
        @Override
        public MyWrapper addedService(
            ServiceReference<SomeService> serviceReference) {
    
            // Determine if the service is one that's interesting to you.
            // The return type of this method is the `tracked` type. Its type 
            // is what is returned from `getService*` methods; useful for wrapping 
            // the service with your own type (e.g., MyWrapper).
            if (isInteresting(serviceReference)) {
                MyWrapper myWrapper = new MyWrapper(
                    serviceReference, bundleContext.getService());
    
                // trigger the logic that requires the available service(s)
                triggerServiceAddedLogic(myWrapper);
    
                return myWrapper;
            }
    
            // If the return is null, the tracker is effectively ignoring any further
            // events for the service reference
            return null;
        }
    
        @Override
        public void modifiedService(
            ServiceReference<SomeService> serviceReference, MyWrapper myWrapper) {
            // handle the modified service
        }
    
        @Override
        public void removedService(
            ServiceReference<SomeService> serviceReference, MyWrapper myWrapper) {
    
            // finally, trigger logic when the service is going away
            triggerServiceRemovedLogic(myWrapper);
        }
    
    }

`ServiceTracker` コンストラクターの3番目のパラメーターとして渡して、 `ServiceTrackerCustomizer` 登録します。

    ServiceTrackerCustomizer<SomeService, MyWrapper> serviceTrackerCustomizer =
        new MyServiceTrackerCustomizer();
    
    ServiceTracker<SomeService, MyWrapper> serviceTracker = 
        new ServiceTracker<>(
            bundleContext, SomeService.class, serviceTrackerCustomizer);

作成する必要がある小さな定型コードがありますが、プラグインが宣言型サービスコンポーネントモデルを利用できない場合でも、サービスレジストリでサービスを検索できるようになりました。
