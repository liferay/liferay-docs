---
header-id: messaging-destinations
---

# メッセージング宛先

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

メッセージバスでは、 *宛先*メッセージを送信します。 宛先は、名前付きの論理的な（物理的ではない）場所です。 送信側クラスはメッセージを宛先に送信し、リスナークラスは宛先でメッセージの受信を待機します。 このように、送信者と受信者はお互いを知る必要はありません---疎結合です。 このチュートリアルで扱うメッセージング宛先のトピックは次のとおりです。

  - [宛先構成](#destination-configuration)
  - [宛先を作成する](#creating-a-destination)
  - [メッセージングイベントリスナー](#messaging-event-listeners)

宛先を構成します。

## 宛先設定

各宛先には名前とタイプがあり、他のいくつかの属性を持つことができます。 宛先タイプは、メッセージキューがあるかどうか、宛先に関係するスレッドの種類、および宛先で予期されるメッセージ配信動作を決定します。

主な宛先タイプは次のとおりです。

  - **並列宛先**

      - ここに送信されたメッセージはキューに入れられます。

      - スレッドプールの複数のワーカースレッドは、登録されたメッセージリスナーに各メッセージを配信します。 メッセージリスナごとに、メッセージごとに1つのワーカースレッドがあります。

  - **シリアル宛先**

      - ここに送信されたメッセージはキューに入れられます。

      - スレッドプールのワーカースレッドは、登録された各メッセージリスナーにメッセージを配信します（メッセージごとに1つのワーカースレッド）。

  - **同期宛先**

      - ここで送信されるメッセージは、メッセージリスナーに直接配信されます。

      - ここでメッセージを送信するスレッドは、すべてのメッセージリスナーにもメッセージを配信します。

Liferayは、さまざまな目的のために宛先を事前に構成しています。 [`DestinationNames` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationNames.html) は、それぞれに `文字列` 定数を定義します。 たとえば、 `DestinationNames.HOT_DEPLOY` （値は `"liferay / hot_deploy"`）は、展開イベントメッセージ用です。 宛先は特定の目的のために調整されているため、変更しないでください。

宛先は、 [`DestinationConfiguration` インスタンス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html)基づいています。 構成では、宛先タイプ、名前、およびこれらの宛先関連属性を指定します。

**最大キューサイズ**：宛先のキューメッセージの数を制限します。

**拒否された実行ハンドラ**：A [`com.liferay.portal.kernel.concurrent.RejectedExecutionHandler` インスタンス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/concurrent/RejectedExecutionHandler.html) は、宛先キューがいっぱいになった場合に拒否されたメッセージに関するアクション（ログの警告など）を実行できます。

**ワーカーコアサイズ**：メッセージを処理するためのワーカースレッドの初期数。

**ワーカーの最大サイズ**：メッセージを処理するワーカースレッドの数を制限します。

`DestinationConfiguration` クラスは、さまざまなタイプの構成を作成するためのこれらの静的メソッドを提供します。

  - `createParallelDestinationConfiguration(String destinationName)`

  - `createSerialDestinationConfiguration(String destinationName)`

  - `createSynchronousDestinationConfiguration(String destinationName)`

[`DestinationConfiguration` コンストラクター](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html#DestinationConfiguration-java.lang.String-java.lang.String-) を使用して、独自の宛先タイプでも構成を作成できます。

## 宛先の作成

メッセージバスの宛先は、宛先構成に基づいており、OSGiサービスとして登録されます。 メッセージバスは、宛先サービスを検出し、関連する宛先を管理します。

宛先を作成するための一般的な手順は次のとおりです。 次のコンフィギュレータクラスの例は、これらの手順を示しています。

1.  `DestinationConfiguration`のstatic `create *` メソッドまたはそのコンストラクターのいずれかを使用して、宛先構成を作成します。 作成する宛先に適用される属性を設定します。

2.  [`DestinationFactory`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationFactory.html) メソッド `createDestination（DestinationConfiguration）`を呼び出して、前の手順で作成した宛先構成を渡して宛先を作成します。

3.  [`BundleContext` メソッド `registerService`](https://osgi.org/javadoc/r4v43/core/org/osgi/framework/BundleContext.html)を呼び出し、次のパラメーターを渡して、OSGiサービスとして宛先を登録します。

      - 宛先クラス `Destination.class`
      - `Destination` オブジェクト
      - `destination.name`を含む、宛先を定義するプロパティの `ディクショナリ`

4.  `Map<String, ServiceRegistration<Destination>>`などのコレクションを使用して、宛先オブジェクトとサービス登録リソースを管理します。 これらのリソースへの参照を保持しておくと、登録を解除して破棄する準備ができたときに役立ちます。 以下の例の `無効化` メソッドはこれを示しています。

以下は、並列宛先を作成および登録し、そのリソースを管理するメッセージング設定コンポーネントの例です。

    @Component (
        immediate = true,
        service = MyMessagingConfigurator .class
    )
    public class MyMessagingConfigurator {
    
        @Activate
        protected void activate(BundleContext bundleContext) {
    
            _bundleContext = bundleContext;
    
            // Create a DestinationConfiguration for parallel destinations.
    
            DestinationConfiguration destinationConfiguration =
                new DestinationConfiguration(
                    DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
                        "myDestinationName");
    
            // Set the DestinationConfiguration's max queue size and
            // rejected execution handler.
    
            destinationConfiguration.setMaximumQueueSize(_MAXIMUM_QUEUE_SIZE);
    
            RejectedExecutionHandler rejectedExecutionHandler =
                new CallerRunsPolicy() {
    
                    @Override
                    public void rejectedExecution(
                        Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
    
                        if (_log.isWarnEnabled()) {
                            _log.warn(
                                "The current thread will handle the request " +
                                    "because the graph walker's task queue is at " +
                                        "its maximum capacity");
                        }
    
                        super.rejectedExecution(runnable, threadPoolExecutor);
                    }
    
                };
    
           destinationConfiguration.setRejectedExecutionHandler(
                rejectedExecutionHandler);
    
           // Create the destination
    
           Destination destination = _destinationFactory.createDestination(
                destinationConfiguration);
    
            // Add the destination to the OSGi service registry
    
            Dictionary<String, Object> properties = new HashMapDictionary<>();
    
            properties.put("destination.name", destination.getName());
    
            ServiceRegistration<Destination> serviceRegistration =
                _bundleContext.registerService(
                    Destination.class, destination, properties);
    
            // Track references to the destination service registrations 
    
            _serviceRegistrations.put(destination.getName(),    
                serviceRegistration);
        }
    
        @Deactivate
        protected void deactivate() {
    
            // Unregister and destroy destinations this component unregistered
    
            for (ServiceRegistration<Destination> serviceRegistration : 
            _serviceRegistrations.values()) {
    
                Destination destination = _bundleContext.getService(
                    serviceRegistration.getReference());
    
                serviceRegistration.unregister();
    
                destination.destroy();
    
            }
    
            _serviceRegistrations.clear();
    
         }
    
        @Reference
        private DestinationFactory _destinationFactory;
    
        private final Map<String, ServiceRegistration<Destination>>
            _serviceRegistrations = new HashMap<>();
    }

アクティベーション時に、上記のコンフィグレータの例は次のことを行います。

1.  並列宛先に対して `DestinationConfiguration` を作成します。

2.  `DestinationConfiguration`の最大キューサイズと拒否された実行ハンドラーを設定します。

3.  `DestinationFactory` （ `_destinationFactory` フィールドにバインドされたもの）を使用して、宛先を作成します。

4.  宛先をOSGiサービスレジストリに追加します

5.  それらを管理するために、宛先サービス登録をマップに追加します。

宛先が登録されると、Message Busはそのサービスを検出し、宛先を管理します。 サンプルコンフィギュレータを非アクティブ化すると、その `deactivate` メソッドは宛先サービスの登録を解除し、宛先を破棄します。

宛先を作成するための追加のボーナスとして、新しい宛先と新しいメッセージリスナーをリッスンするクラスを作成できます。 このようなリスナーを作成して、新しいメッセージバスエンドポイントの展開を記録することができます。

## メッセージングイベントリスナー

新しい宛先とメッセージリスナをリッスンできるメッセージバスフレームワークインターフェイスがあります。

### 新しい目的地を聞く

宛先が追加および削除されると、メッセージバスはメッセージバスイベントリスナーに通知します。 これらのリスナーを登録するには、 [`MessageBusEventListener` インスタンス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBusEventListener.html) をOSGiサービスレジストリに公開します（たとえば、 `@Component` アノテーションを使用して）。

    @Component(
        immediate = true,
        service = MessageBusEventListener.class
    )
    public class MyMessageBusEventListener implements MessageBusEventListener {
    
        void destinationAdded(Destination destination) {
            ...
        }
    
        void destinationDestroyed(Destination destination) {
            ...
        }
    }

新しいメッセージリスナのリスニングも簡単です。

### 新しいメッセージリスナのリッスン

メッセージバスは、メッセージリスナーが宛先に登録または登録解除されると、 [`DestinationEventListener` インスタンス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationEventListener.html) に通知します。 リスナーを宛先に登録するには、 [`DestinationEventListener` サービス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationEventListener.html) をOSGiサービスレジストリに公開し、宛先の `destination.name` プロパティを必ず指定します。

    @Component(
        immediate = true,
        property = {"destination.name=myCustom/Destination"},
        service = DestinationEventListener.class
    )
    public class MyDestinationEventListener implements DestinationEventListener {
    
        void messageListenerRegistered(String destinationName,
                                       MessageListener messageListener) {
            ...
        }
    
        void messageListenerUnregistered(String destinationName,
                                       MessageListener messageListener) {
            ...
        }
    }

そして、それが新しい宛先とメッセージリスナーをリッスンする方法です。

これで、さまざまな宛先タイプ、宛先の作成および登録方法、宛先リソースの管理方法を理解できました。 宛先をデプロイすると、登録されたメッセージリスナーは、宛先に送信されたメッセージを受信します。

## 関連トピック

[メッセージリスナー](/docs/7-1/tutorials/-/knowledge_base/t/message-listeners)

[メッセージを送信する](/docs/7-1/tutorials/-/knowledge_base/t/sending-messages)
