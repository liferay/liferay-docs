---
header-id: message-listeners
---

# メッセージリスナー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

宛先に送信されるメッセージに関心がある場合は、それらを「聞く」必要があります。 つまり、宛先のメッセージリスナーを作成して登録する必要があります。

メッセージリスナーを作成するには、 [`MessageListener` インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageListener.html) を実装し、その `receive（Message）` メソッドをオーバーライドして、メッセージを処理します。

    public void receive(Message message) {
        // Process messages your way
    }

リスナーをメッセージバスに登録する方法は次のとおりです。

  - **コンポーネント**としての自動登録：宛先を指定する [宣言型サービスコンポーネント](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services) として、OSGiレジストリにリスナーを公開します。 メッセージバスは、リスナーを宛先に自動的に接続します。

  - **MessageBusを介した登録**：メッセージバスへの参照を取得し、それを直接使用して、リスナーを宛先に登録します。

  - **宛先への直接登録**：特定の宛先への参照を取得し、それを直接使用してリスナーをその宛先に登録します。

| **注**：| [`DestinationNames` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationNames.html) | @product@の事前構成された宛先に `ストリング` 定数を定義します。

宣言型サービスコンポーネントモジュールは、メッセージリスナーを登録する最も簡単な方法を提供します。

## コンポーネントとしての自動登録

Declarative Services（DS） `@Component` アノテーションでメッセージリスナーを指定できます。

    @Component (
        immediate = true,
        property = {"destination.name=myCustom/Destination"},
        service = MessageListener.class
    )
    public class MyMessageListener implements MessageListener {
       ...
    
       public void receive(Message message) {
           // Handle the message
       }
    }

メッセージバスは、このような `MessageListener` サービスコンポーネントをリッスンして、OSGiサービスレジストリに自身を公開します。 属性 `immediate = true` は、依存関係が解決するとすぐにコンポーネントをアクティブ化するようOSGiフレームワークに指示します。 メッセージバスは、登録された各リスナーを、 `destination.name` プロパティが指定する宛先に配線します。 宛先がまだ登録されていない場合、Message Busは宛先が登録されるまでリスナーをキューに入れます。

コンポーネントとしての登録は、メッセージリスナを宛先に登録するための推奨される方法です。

## MessageBusを介した登録

[`MessageBus` インスタンス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBus.html) 直接使用して、メッセージリスナーを宛先に登録できます。 たとえば、特別なプロキシラッパーを作成する場合に、これを行うことができます。 このようにリスナーを登録することを示す登録者は次のとおりです。

    @Component (
        immediate = true,
        service = MyMessageListenerRegistrator.class
    )
    public class MyMessageListenerRegistrator {
        ...
    
        @Activate
        protected void activate() {
    
            _messageListener = new MessageListener() {
    
                public void receive(Message message) {
                    // Handle the message
                }
            };
    
            _messageBus.registerMessageListener("myDestinationName",  
                _messageListener);
        }
    
        @Deactivate
        protected void deactivate() {
            _messageBus.unregisterMessageListener("myDestinationName",  
                _messageListener);
        }
    
        @Reference
        private MessageBus _messageBus;
    
        private MessageListener _messageListener;
    }

`_messageBus` フィールドの `@Reference` 注釈は、 `MessageBus` インスタンスにバインドします。 `activate` メソッドはリスナーを作成し、メッセージバスを使用して `"myDestination"`という名前の宛先にリスナーを登録します。 この登録コンポーネントが破棄されると、 `deactivate` メソッドはリスナーを登録解除します。

## 宛先に直接登録する

`Destination` インスタンスを使用して、その宛先にリスナーを登録できます。 たとえば、特別なプロキシラッパーを作成する場合に、これを行うことができます。 このようにリスナーを登録することを示す登録者は次のとおりです。

    @Component (
        immediate = true,
        service = MyMessageListenerRegistrator.class
    )
    public class MyMessageListenerRegistrator {
       ...
    
        @Activate
        protected void activate() {
    
            _messageListener = new MessageListener() {
    
                public void receive(Message message) {
                    // Handle the message
                }
            };
    
            _destination.register(_messageListener);
        }
    
        @Deactivate
        protected void deactivate() {
    
            _destination.unregister(_messageListener);
        }
    
        @Reference(target = "(destination.name=someDestination)")
        private Destination _destination;
    
        private MessageListener _messageListener;
    }

`_destination` フィールドの `@Reference` アノテーションは、 `"someDestination"`という名前の宛先にバインドします。 `activate` メソッドは、リスナーを作成し、それを宛先に登録します。 この登録コンポーネントが破棄されると、 `deactivate` メソッドはリスナーを登録解除します。

これで、宛先に送信されたメッセージを受信するためのメッセージリスナーを作成および登録する方法がわかりました。

## 関連トピック

[メッセージング宛先](/docs/7-1/tutorials/-/knowledge_base/t/messaging-destinations)

[メッセージを送信する](/docs/7-1/tutorials/-/knowledge_base/t/sending-messages)
