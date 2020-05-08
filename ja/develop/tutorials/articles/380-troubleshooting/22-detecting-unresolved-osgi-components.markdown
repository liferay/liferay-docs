---
header-id: detecting-unresolved-osgi-components
---

# 未解決のOSGiコンポーネントの検出

[TOC levels=1-4]

@product@には、未解決のOSGiコンポーネントに起因する問題を診断するときに役立つ[Gogoシェル](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)コマンドが含まれています。使用する特定のツールは、未解決のコンポーネントのコンポーネントフレームワークによって異なります。ほとんどの@product@コンポーネントは、SCR（Service Component Runtime）とも呼ばれるDeclarative Services（DS）を使用して開発されます。
例外は、Dependency Manager（DM）のコンポーネントである@product@のService Builderサービスです。[Declarative Services](http://felix.apache.org/documentation/subprojects/apache-felix-service-component-runtime.html)と[Dependency Manager](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager.html)はどちらもApache Felixプロジェクトです。

未解決のコンポーネントのトラブルシューティング手順は、次のセクションに分かれています。

- [Declarative Servicesコンポーネント](#declarative-services-components)
   - [Declarative Services Unsatisfied Component Scanner](#declarative-services-unsatisfied-component-scanner)
   - [ds:unsatisfiedコマンド](#dsunsatisfied-command)
- [Service Builderコンポーネント](#service-builder-components)
   - [Unavailable Component Scanner](#unavailable-component-scanner)
   - [dm naコマンド](#dm-na-command)
   - [`ServiceProxyFactory`](#serviceproxyfactory)

## Declarative Servicesコンポーネント

Service Builderコンポーネントを除くほとんどの@product@コンポーネントはDSコンポーネントなので、DSから始めます。バンドルのコンポーネントの1つに、不満足なサービス参照があるとします。どのように検出できるでしょうか。2通りあります。

- [Declarative Services Unsatisfied Component Scanner](#declarative-services-unsatisfied-component-scanner)を有効にして、不満足な参照を自動的に報告する。

- [Gogoシェルコマンド`ds:unsatisfied`](#dsunsatisfied-command)を使用して、手動で確認する。

### Declarative Services Unsatisfied Component Scanner

Unsatisfied Component Scannerを有効にする方法は次のとおりです。

1. ファイル`com.liferay.portal.osgi.debug.declarative.service.internal.configuration.UnsatisfiedComponentScannerConfiguration.cfg`を作成します。

2. 以下のコンテンツを追加します。

   `unsatisfiedComponentScanningInterval=5`

3. ファイルを`[LIFERAY_HOME]/osgi/configs`にコピーします。

スキャナーが、不満足なサービスコンポーネント参照を検出してログに記録します。 ログメッセージには、バンドル、参照しているDSコンポーネントクラス、および参照されているコンポーネントが記載されます。

スキャナーメッセージの例を次に示します。

    11:18:28,881 WARN  [Declarative Service Unsatisfied Component Scanner][UnsatisfiedComponentScanner:91]
    Bundle {id: 631, name: com.liferay.blogs.web, version: 2.0.0}
    Declarative Service {id: 3333, name: com.liferay.blogs.web.internal.portlet.action.EditEntryMVCRenderCommand, unsatisfied references:
    {name: ItemSelectorHelper, target: null}
    }

上記のメッセージは、`com.liferay.blogs.web`バンドルのDSコンポーネント`com.liferay.blogs.web.internal.portlet.action.EditEntryMVCRenderCommand`に、タイプ`ItemSelectorHelper`のコンポーネントへの不満足な参照があることを警告しています。参照しているコンポーネントのID（SCR ID）は`3333`で、バンドルIDは`631`です。

### ds:unsatisfiedコマンド

不満足なコンポーネント参照を検出するもう1つの方法は、Gogoシェルコマンド`ds:unsatisfied`を呼び出すことです。

- `ds:unsatisfied`は、不満足なすべてのDSコンポーネントを表示します
- `ds:unsatisfied [BUNDLE_ID]`は、バンドルの不満足なDSコンポーネントを表示します

不満足なDSコンポーネントに関する詳細情報を表示するには、コンポーネントのIDをコマンド`scr:info [component ID]`に渡します。たとえば、ID `1701`のコンポーネントに対するコマンドを以下に示します。

    g!scr:info 1701
    *** Bundle: org.foo.bar.command (507)
    Component Description:
    Name: org.foo.bar.command
    Implementation Class: org.foo.bar.command.FooBarCommand
    Default State: enabled
    Activation: delayed
    Configuration Policy: optional
    Activate Method: activate
    Deactivate Method: deactivate
    Modified Method: -
    Configuration Pid: [org.foo.bar.command]
    Services:
    org.foo.bar.command.DuckQuackCommand
    Service Scope: singleton
    Reference: Duck
    Interface Name: org.foo.bar.api.Foo
    Cardinality: 1..1
    Policy: static
    Policy option: reluctant
    Reference Scope: bundle
    Component Description Properties:
    osgi.command.function = foo
    osgi.command.scope = bar
    Component Configuration:
    ComponentId: 1701
    State: unsatisfied reference
    UnsatisfiedReference: Foo
    Target: null
    (no target services)
    Component Configuration Properties:
    component.id = 1701
    component.name = org.foo.bar.command
    osgi.command.function = foo
    osgi.command.scope = bar

`Component Configuration`セクションで、`UnsatisfiedReference` に不満足な参照のタイプがリストされます。このバンドルのコンポーネントは、`Foo`サービスが欠落しているため機能していません。これで、`Foo`が利用できない原因に焦点を当てることができます。このソリューションは、`Foo`サービスを提供するバンドルを開始またはデプロイするのと同じくらい簡単です。

## Service Builderコンポーネント

Service Builderモジュールは、Springを使用して実装されます。@product@は、[Apache Felix Dependency Manager](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager.html)を使用して、[Portal Spring Extender](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.spring.extender/)モジュールを介してService BuilderモジュールのOSGiコンポーネントを管理します。

Liferay Service Builderのアプリケーションを開発する際に、未解決のSpring関連のOSGiコンポーネントが存在する場合があります。これは、アプリケーションのデータベーススキーマを更新したが、アップグレードのトリガーを忘れた場合に発生する可能性があります（@product@アプリケーションのデータベースアップグレードプロセスの作成については、チュートリアル[Creating an Upgrade Process for Your App](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)を参照してください）。

これらの機能は、未解決のService Builder関連のコンポーネントを検出します。

- [Unavailable Component Scanner](#unavailable-component-scanner)
- [dm naコマンド](#dm-na-command)
- [ServiceProxyFactory](#serviceproxyfactory)

### Unavailable Component Scanner

[OSGi Debug Spring Extender](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.osgi.debug.spring.extender/)モジュールのUnavailable Component Scannerは、Service Builderを使用するモジュール内の欠落しているコンポーネントを報告します。スキャナーを有効にする方法は次のとおりです。

1. 構成ファイル`com.liferay.portal.osgi.debug.spring.extender.internal.configuration.UnavailableComponentScannerConfiguration.cfg`を作成します。

2. 構成ファイルで、スキャン間の時間間隔（秒単位）を設定します。

   `unavailableComponentScanningInterval=5`

3. ファイルを`[LIFERAY_HOME]/osgi/configs`にコピーします。

スキャナーは、設定された間隔でSpring Extender Dependency Managerのコンポーネントステータスを報告します。すべてのコンポーネントが登録されている場合、スキャナーは確認メッセージを送信します。

    11:10:53,817 INFO  [Spring Extender Unavailable Component Scanner][UnavailableComponentScanner:166] All Spring extender dependency manager components are registered

利用できないコンポーネントがある場合、次のような警告が表示されます。

    11:13:08,851 WARN  [Spring Extender Unavailable Component Scanner][UnavailableComponentScanner:173] Found unavailable component in bundle com.liferay.screens.service_1.0.28 [516].
    Component ComponentImpl[null com.liferay.portal.spring.extender.internal.context.ModuleApplicationContextRegistrator@1541eee] is unavailable due to missing required dependencies: ServiceDependency[interface com.liferay.blogs.service.BlogsEntryService null].

DSコンポーネントとService Builderコンポーネントが同じモジュールで公開され使用されていると、上記で報告されているようなコンポーネントの利用不可が発生する場合があります。DSコンポーネントとService Builderコンポーネントを公開する際は別々のモジュールを使用してください。

### dm naコマンド

Dependency Managerの[Gogoシェルコマンド`dm`](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager/tutorials/leveraging-the-shell.html)は、すべてのService Builderコンポーネント、それらの必要なサービス、および必要な各サービスが利用可能かどうかをリストします。

未解決のコンポーネントをリストするには、次のGogoシェルコマンドのみを実行します。

`dm na`

`na`オプションは「利用不可」の略です。

### ServiceProxyFactory

@product@のログは、未解決のService Builderコンポーネントも報告します。たとえば、サービスコンポーネントが未解決であるため、Service Proxy FactoryがService Builderベースのエンティティの新しいインスタンスを作成できない場合、@product@はエラーを記録します。

次のコードは、[`ServiceProxyFactory` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ServiceProxyFactory.html)を使用して新しいエンティティインスタンスを作成する方法を示しています。

    private static volatile MessageBus _messageBus =
    ServiceProxyFactory.newServiceTrackedInstance(
    MessageBus.class, MessageBusUtil.class, "_messageBus", true);

次のメッセージは、利用できないサービスについて警告しています。

    11:07:35,139 ERROR [localhost-startStop-1][ServiceProxyFactory:265] Service "com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactory" is unavailable in 60000 milliseconds while setting field "_singleDestinationMessageSenderFactory" for class "com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactoryUtil", will retry...

上記のメッセージに基づいて、サービス`com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactory`を提供しているバンドルはありません。

未解決のコンポーネントの検出、DSおよびDMコンポーネントの検出、スキャナー使用による自動検出、Gogoシェルコマンド使用による手動検出、`ServiceProxyFactory`を使用したプログラムによる検出を行うことができるようになりました。

## 関連トピック

[System Check](/docs/7-1/tutorials/-/knowledge_base/t/system-check)
