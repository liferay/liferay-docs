---
header-id: create-and-use-a-connector-with-your-screenlet
---

# スクリーンレットでコネクタを作成して使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensでは、コネクタはローカルおよびリモートのデータソースとLiferayインスタンスと非同期に対話するクラスです。 コールバックは非同期サービス呼び出しも行うことを思い出してください。 では、なぜコネクタに悩まされるのでしょうか？ コネクタは、Interactorの外部でサービスを呼び出すことにより、抽象化の層を提供します。 たとえば、スクリーンレット作成チュートリアル</a> のインタラクター

はサーバーを呼び出し、その結果を `LRCallback`処理します。 代わりに、このスクリーンレットは別のコネクタクラスでサーバー呼び出しを行い、Interactorがコネクタをインスタンス化してその結果を受け取るようにすることができます。 コネクタを使用すると、スクリーンレットのデータを検証することもできます。 コネクタの詳細については、以下を参照してください [iOS用のLiferay画面のアーキテクチャ上のチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)。</p> 

このチュートリアルでは使用して、あなたのscreenletsでコネクタを作成して使用するために必要な手順を説明します [サンプルの高度なバージョンがブックマークScreenlet追加](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced) 例として。 このスクリーンレットには2つのアクションが含まれています。

1.  ブックマークの追加：@product@インストールのブックマークポートレットにブックマークを追加します。 このチュートリアルでは、このアクションのコネクタを作成して使用する方法を示します。

2.  タイトルを取得：ユーザーが入力したブックマークURLからタイトルを取得します。 このチュートリアルでは、このアクションで既存のコネクターを使用する方法を示します。

先に進む前に、必ず読んで作る [Screenlet作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)。 最初に、コネクターの作成方法を学びます。



## コネクタの作成

コネクタクラスを作成するときは、ベストプラクティスチュートリアルで指定されている [命名規則](/docs/7-1/tutorials/-/knowledge_base/t/ios-best-practices#naming-conventions) に従ってください。

次の手順を使用して、Connectorクラスを実装します。

1.  [`ServerConnector` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerConnector.swift)拡張して、Connectorクラスを作成します。 たとえば、Add Bookmark Screenletのコネクタクラス `AddBookmarkLiferayConnector`のクラス宣言は次のとおりです。
   
        public class AddBookmarkLiferayConnector: ServerConnector {
            ...
        }
       

2.  Mobile SDKサービスの呼び出しに必要なプロパティを追加し、それらのプロパティを設定する初期化子を作成します。 たとえば、 `AddBookmarkLiferayConnector` は、ブックマークのフォルダーID、タイトル、およびURLのプロパティが必要です。 これらのプロパティを設定するには、初期化子も必要です。
   
        public let folderId: Int64
        public let title: String
        public let url: String
       
        public init(folderId: Int64, title: String, url: String) {
            self.folderId = folderId
            self.title = title
            self.url = url
            super.init()
        }
       

3.  Screenletのプロパティのいずれかを検証する場合は、 `validateData` メソッドをオーバーライドして、それらのプロパティの検証を実装します。 [`ValidationError` クラス](https://github.com/liferay/liferay-screens/blob/develop/ios/Framework/Core/Extensions/NSError%2BScreens.swift) を使用して、エラーをカプセル化できます。 たとえば、次の `AddBookmarkLiferayConnector` `validateData` 実装により、 `folderId` が `0`超え、 `title` および `url` に値が含まれることが保証されます。 このメソッドは、エラーを表すために `ValidationError` も使用します。
   
        override public func validateData() -> ValidationError? {
            let error = super.validateData()
       
            if error == nil {
       
                if folderId <= 0 {
                    return ValidationError("Undefined folderId")
                }
       
                if title.isEmpty {
                    return ValidationError("Title cannot be empty")
                }
       
                if url.isEmpty {
                    return ValidationError("URL cannot be empty")
                }
            }
       
            return error
        }
       

4.  `doRun` メソッドをオーバーライドして、呼び出す必要があるMobile SDKサービスを呼び出します。 このメソッドは、サービスから結果を取得し、パブリックプロパティに保存する必要があります。 また、エラーと空の結果を処理するようにしてください。 たとえば、次のコードは、 `doRun` メソッドで取得したサービスの結果を保存するための `resultBookmarkInfo` プロパティを定義します。 このメソッドのサービス呼び出しは、 [スクリーンレット作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)の `AddBookmarkInteractor` クラスの `start` メソッドの呼び出しと同じであることに注意してください。 ただし、 `doRun` メソッドは、結果を `resultBookmarkInfo` プロパティに保存する追加のステップを実行します。 また、この `doRun` メソッドは、エラーを `NSError` オブジェクトとして処理することに注意してください。
   
        public var resultBookmarkInfo: [String:AnyObject]?
       
        override public func doRun(session session: LRSession) {
       
            let service = LRBookmarksEntryService_v7(session: session)
       
            do {
                let result = try service.addEntryWithGroupId(LiferayServerContext.groupId,
                                                             folderId: folderId,
                                                             name: title,
                                                             url: url,
                                                             description: "Added from Liferay Screens",
                                                             serviceContext: nil)
       
                if let result = result as? [String: AnyObject] {
                    resultBookmarkInfo = result
                    lastError = nil
                }
                else {
                    lastError = NSError.errorWithCause(.InvalidServerResponse)
                    resultBookmarkInfo = nil
                }
            }
            catch let error as NSError {
                lastError = error
                resultBookmarkInfo = nil
            }
       
        }
       

よくやった\！ これで、Connectorクラスを作成する方法がわかりました。 完成した例を見るには `AddBookmarkLiferayConnector` クラス、 [ここをクリック](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Connector/AddBookmarkLiferayConnector.swift)。



## コネクタを使用する

コネクターを使用するには、Interactorクラスが [`ServerConnectorInteractor` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerConnectorInteractor.swift) または以下のサブクラスのいずれかを拡張する必要があります。

  - [`ServerReadConnectorInteractor`：](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerReadConnectorInteractor.swift) サーバーまたはデータソースから情報を取得するアクションを実装する場合、Interactorクラスはこのクラスを拡張する必要があります。

  - [`ServerWriteConnectorInteractor`：](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerWriteConnectorInteractor.swift) サーバーまたはデータソースに情報を書き込むアクションを実装する場合、Interactorクラスはこのクラスを拡張する必要があります。

`ServerConnectorInteractor` またはそのサブクラスの1つを拡張する場合、Interactorクラスは `createConnector` および `completedConnector` メソッドをオーバーライドするだけで済みます。 これらのメソッドは、それぞれコネクタインスタンスを作成し、コネクタの結果を回復します。

Interactorでコネクタを使用するには、次の手順に従います。

1.  Interactorクラスのスーパークラスを `ServerConnectorInteractor` またはそのサブクラスの1つに設定します。 また、コールバックプロトコルが存在する場合は、コールバックプロトコルに準拠するコードも削除する必要があります。 たとえば、ブックマークの追加スクリーンレットのInteractorクラス（`AddBookmarkInteractor`）は、@product@インストールにデータを書き込むため、 `ServerWriteConnectorInteractor` 拡張します。 この時点で、Interactorに必要なプロパティとイニシャライザーのみを含める必要があります。 
   
   

    ``` 
     public class AddBookmarkInteractor: ServerWriteConnectorInteractor {

         public let folderId: Int64
         public let title: String
         public let url: String

         public var resultBookmark: Bookmark?

         //MARK: Initializer

         public init(screenlet: BaseScreenlet, folderId: Int64, title: String, url: String) {
             self.folderId = folderId
             self.title = title
             self.url = url
             super.init(screenlet: screenlet)
         }
     }
    ```


2.  `createConnector` メソッドをオーバーライドして、コネクターのインスタンスを返します。 例えば、 `createConnector` における方法 `AddBookmarkInteractor` 戻り `AddBookmarkLiferayConnector` で作成されたインスタンス `フォルダID`、 `タイトル`、及び `URL` の特性： 
   
   

    ``` 
     public override func createConnector() -> ServerConnector? {
         return AddBookmarkLiferayConnector(folderId: folderId, title: title, url: url)
     }
    ```


3.  `completedConnector` メソッドをオーバーライドして、Connectorから結果を取得し、適切なプロパティに保存します。 たとえば、 `AddBookmarkInteractor` の `completedConnector` メソッドは、最初に `ServerConnector` 引数を `AddBookmarkLiferayConnector`キャストします。 次に、Connectorの `resultBookmarkInfo` プロパティを取得し、Interactorの `resultBookmark` プロパティに設定します。 
   
   

    ``` 
     override public func completedConnector(c: ServerConnector) {
         if let addCon = (c as? AddBookmarkLiferayConnector),
             bookmarkInfo = addCon.resultBookmarkInfo {
             self.resultBookmark = bookmarkInfo
         }
     }
    ```


それでおしまい\！ 完全な例を参照するには `AddBookmarkInteractor`、 [こちらをクリックしてください](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Interactor/AddBookmarkInteractor.swift)。

スクリーンレットが複数のインタラクターを使用している場合は、同じ手順に従ってコネクターを使用します。 また、画面が提供する [すぐに使用できる `HTTPConnectorの`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/HttpConnector.swift) 非LiferayのURLのと対話するため。 このコネクターを使用するには、Interactorを `HttpConnector`を使用するように設定します。 たとえば、URLのタイトルを取得するブックマークスクリーンレットの追加アクションは、@product@インストールと対話しません。 URLから直接タイトルを取得します。 このアクションのInteractorクラス（`GetWebTitleInteractor`）はデータを取得するため、 `ServerReadConnectorInteractor`拡張します。 また、 `createConnector` および `completedConnector` メソッドをオーバーライドして、 `HttpConnector`を使用します。 完全な `GetWebTitleInteractor`次のとおりです。

    import UIKit
    import LiferayScreens
    
    public class GetWebTitleInteractor: ServerReadConnectorInteractor {
    
        public let url: String?
    
        // title from the webpage
        public var resultTitle: String?
    
        //MARK: Initializer
    
        public init(screenlet: BaseScreenlet, url: String) {
            self.url = url
            super.init(screenlet: screenlet)
        }
    
        //MARK: ServerConnectorInteractor
    
        public override func createConnector() -> ServerConnector? {
            if let url = url, URL = NSURL(string: url) {
                return HttpConnector(url: URL)
            }
    
            return nil
        }
    
        override public func completedConnector(c: ServerConnector) {
            if let httpCon = (c as? HttpConnector), data = httpCon.resultData,
                html = NSString(data: data, encoding: NSUTF8StringEncoding) {
                self.resultTitle = parseTitle(html)
            }
        }
    
        //MARK: Private methods
    
        // Parse the title from the webpage's HTML
        private func parseTitle(html: NSString) -> String {
            let range1 = html.rangeOfString("<title>")
            let range2 = html.rangeOfString("</title>")
    
            let start = range1.location + range1.length
    
            return html.substringWithRange(NSMakeRange(start, range2.location - start))
        }
    
    }
    

驚くばかり\！ これで、スクリーンレットでコネクタを作成および使用する方法がわかりました。



## 関連トピック

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)

[スクリーンレットアクションの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-screenlet-actions)

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)
