---
header-id: adding-custom-interactors-to-ios-screenlets
---

# iOSスクリーンレットへのカスタムインタラクターの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

インタラクターは、特定のユースケースのサーバー通信を実装するスクリーンレットコンポーネントです。 たとえば、ログインスクリーンレットのインタラクターは、ポータルに対してユーザーを認証するLiferay Mobile SDKサービスを呼び出します。 同様に、ブックマークの追加スクリーンレット</a>

のインタラクターは、ブックマークポートレットにブックマークを追加するLiferay Mobile SDKサービスを呼び出します。</p> 

それで十分ですが、Screenletのサーバー呼び出しをカスタマイズしたい場合はどうでしょうか？ スクリーンレットで別のバックエンドを使用する場合はどうなりますか？ 問題ない\！ スクリーンレット用のカスタムインタラクターを実装できます。 カスタムロジックまたはネットワークコードを使用して、サーバー呼び出しを行う別のInteractorをプラグインできます。 これを行うには、現在のInteractorのインターフェイスを実装し、オーバーライドするスクリーンレットに渡す必要があります。 これはアプリのコード内で行う必要があります。

このチュートリアルでは、Login Screenletをオーバーライドして、パスワードなしで常に同じユーザーでログインするInteractorの例を見ることができます。



## カスタムインタラクターの実装

1.  カスタムInteractorを実装します。 次に示すように、 `ServerConnectorInteractor`継承する必要があります。 
   
   

    ``` 
     class LoginCustomInteractor: ServerConnectorInteractor {

         override func createConnector() -> ServerConnector? {

             ...

             return connector
         }

     }
    ```


2.  `customInteractorForAction`を受け取るオプションのプロトコルを実装し、独自のInteractorを返します。 
   
   

    ``` 
     func screenlet(screenlet: BaseScreenlet, 
             customInteractorForAction: String, 
             withSender: AnyObject?) -> Interactor? {

         return LoginCustomInteractor()
     }
    ```


すばらしいです\！ これで、iOSスクリーンレット用のカスタムインタラクターを実装する方法がわかりました。



## 関連トピック

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)
