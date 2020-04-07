---
header-id: services-in-jsf
---

# JSFのサービス

[TOC levels=1-4]

Creating services works the same in a JSF portlet as it would in any other standard WAR-style MVC portlet; generate custom services as separate API and Impl JARs and deploy them as individual modules to @<product@>. Service Builderを使用して、JSFポートレットのカスタムサービスを生成できます。 @product@でのService Builderの動作の詳細については、 [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder) チュートリアルをご覧ください。

JSF WARは、 *が* 依存関係としてAPIモジュールに依存できます。 この方法でサービスをパッケージ化する主な利点は、複数のWARがすべてのWARの `WEB-INF / lib` フォルダーにパッケージ化せずに同じカスタムサービスAPIを利用できるようにすることです。 この方法はまた、関心の分離、又は強制 *モジュール*のシステムのUI層とサービス層との間を、。

JSFポートレットからOSGiベースのService Builderサービスを呼び出すには、宣言サービスを使用してOSGiランタイムに公開されたサービスを検索できないため、OSGiサービスレジストリにアクセスできるメカニズムが必要です。 代わりに、OSGiサービスレジストリにあるサービスを呼び出す場合は、 [ServiceTracker](https://osgi.org/javadoc/r6/core/org/osgi/util/tracker/ServiceTracker.html) 開く必要があります。

JSFポートレットにサービストラッカーを実装するには、 `org.osgi.util.tracker.ServiceTracker`を拡張するタイプセーフラッパークラスを追加できます。 たとえば、これはデモJSFポートレットで次のように行われます

    public class UserLocalServiceTracker extends ServiceTracker<UserLocalService, UserLocalService> {
    
        public UserLocalServiceTracker(BundleContext bundleContext) {
            super(bundleContext, UserLocalService.class, null);
        }
    }

`ServiceTracker`拡張した後、コンストラクターを呼び出すだけで、サービストラッカーがマネージドBeanで使用できるようになります。

マネージドBeanでは、サービスを呼び出す必要があるときはいつでも、サービストラッカーを開きます。 たとえば、これは同じデモJSFポートレットで行われ、サービストラッカーを開き、 [`@PostContruct`](http://docs.oracle.com/javaee/7/api/javax/annotation/PostConstruct.html) アノテーションを使用し

 。</p> 

    @PostConstruct
    public void postConstruct() {
        Bundle bundle = FrameworkUtil.getBundle(this.getClass());
        BundleContext bundleContext = bundle.getBundleContext();
        userLocalServiceTracker = new UserLocalServiceTracker(bundleContext);
        userLocalServiceTracker.open();
    }
    

その後、サービスを呼び出すことができます。

    UserLocalService userLocalService = userLocalServiceTracker.getService();
    ...
    
    userLocalService.updateUser(user);
    

マネージドBeanがスコープ外に出るとき、 [`@PreDestroy`](http://docs.oracle.com/javaee/7/api/javax/annotation/PreDestroy.html) アノテーションを使用してサービストラッカーを閉じる必要があります。

    @PreDestroy
    public void preDestroy() {
        userLocalServiceTracker.close();
    }
    

サービストラッカーとWARスタイルポートレットでそれらを使用する方法の詳細については、 [Service Trackers](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers) チュートリアルを参照してください。



## 関連トピック

[基礎](/docs/7-1/tutorials/-/knowledge_base/t/fundamentals)

[国際化](/docs/7-1/tutorials/-/knowledge_base/t/internationalization)

[構成可能なアプリケーション](/docs/7-1/tutorials/-/knowledge_base/t/configurable-applications)
