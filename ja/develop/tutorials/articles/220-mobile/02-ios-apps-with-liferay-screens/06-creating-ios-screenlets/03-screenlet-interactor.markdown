---
header-id: creating-the-ios-screenlets-interactor
---

# iOSスクリーンレットのインタラクターを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

スクリーンレットのアクションごとにInteractorクラスを作成します。 [`Interactor` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/Interactor.swift)では、ScreensはすべてのInteractorクラスに必要なデフォルトの機能を提供します。 したがって、Interactorクラスは、 `Interactor` を拡張して、スクリーンレットに固有の機能を提供する必要があります。

| **注：** |の代わりにコネクターでサーバー呼び出しを行うことができます。インタラクター。 これにより、サーバーに追加の抽象化レイヤーが提供されます。呼び出し、Interactorを残してConnectorをインスタンス化し、その|結果。 この手順については、チュートリアルを参照してください| [スクリーンレットでコネクタを作成して使用する](/docs/7-1/tutorials/-/knowledge_base/t/create-and-use-a-connector-with-your-screenlet)。

インタラクターは同期的に機能しますが、コールバック（デリゲート）またはコネクターを使用して、バックグラウンドで操作を実行できます。 たとえば、Liferay Mobile SDKは、この目的で [`LRCallback` プロトコル](https://github.com/liferay/liferay-mobile-sdk/blob/master/ios/Source/Core/LRCallback.h) を提供します。 これについては、Liferayサービスの非同期呼び出しに関するMobile SDKチュートリアル [で説明されています](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-asynchronously-from-your-ios-app)。 Screensはこのプロトコルをブリッジして、Swiftで使用できるようにします。 Interactorクラスは、このプロトコルに準拠して、サーバー呼び出しを非同期的に行うことができます。 Interactorクラスを実装するには：

  - 初期化子は、必要なすべてのプロパティとスクリーンレットへの参照を受け取る必要があります。
  - Screenletが必要とするサーバー操作を実行するために、 `Interactor`の `start` メソッドをオーバーライドします（例えば、Liferay Mobile SDKサービスを介してLiferay操作を呼び出します）。
  - 必要に応じて、アクセス可能なプロパティにサーバーの応答を保存します。 たとえば、サーバー呼び出しがLiferayインスタンスからオブジェクトを返す場合、これらのオブジェクトをアクセス可能なプロパティに保存する必要があります。 このようにして、スクリーンレットはそれらの結果をユーザーに表示できます。
  - メソッド `callOnSuccess` および `callOnFailure` を呼び出して、それぞれクロージャー `onSuccess` および `onFailure`を実行する必要があります。

たとえば、サンプルAdd Bookmark ScreenletのInteractorクラス [`AddBookmarkInteractor`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/Interactor/AddBookmarkInteractor.swift) は、Liferayインスタンスにブックマークを追加するサーバー呼び出しを行います。 このクラスは、 `Interactor` クラスを拡張し、 `LRCallback` プロトコルに準拠しています。 後者は、Interactorのサーバー呼び出しが非同期で実行されることを保証します。

    public class AddBookmarkInteractor: Interactor, LRCallback {...

サーバー呼び出しの結果を保存するために、 `AddBookmarkInteractor` はパブリック変数 `resultBookmarkInfo`定義します。 このクラスは、ブックマークのフォルダーID、タイトル、およびURLのパブリック定数も定義します。 初期化子は、これらの変数を設定し、ベースScreenletクラス（`BaseScreenlet`）への参照を使用して `Interactor`のコンストラクターを呼び出します`</p>

<pre><code>public var resultBookmarkInfo: [String:AnyObject]?
public let folderId: Int64
public let title: String
public let url: String

public init(screenlet: BaseScreenlet, folderId: Int64, title: String, url: String) {
    self.folderId = folderId
    self.title = title
    self.url = url
    super.init(screenlet: screenlet)
}
`</pre>

`AddBookmarkInteractor` クラスの `start` メソッドは、サーバー呼び出しを行います。 そのためには、最初に `Session`取得する必要があります。 Login Screenletはログインが成功すると自動的にセッションを作成するため、 `start` メソッドは `SessionContext.createSessionFromCurrentSession（）`このセッションを取得します。 サーバーを非同期的に呼び出すには、 `start` メソッドがこのセッションにコールバックを設定する必要があります。 `AddBookmarkInteractor` は `LRCallback` プロトコルに準拠しているため、セッションのコールバックとして `self` を設定するとこれが実現します。 `start` メソッドは、 `LRBookmarksEntryService_v7` インスタンスを作成し、このインスタンスの `addEntryWithGroupId` メソッドを呼び出す必要があります。 後者のメソッドは、Liferayインスタンスの `add-entry` サービスを `BookmarksEntry`に対して呼び出します。 `スタート` の方法は、したがって、提供 `のgroupId`、 `フォルダID`、 `名`、 `URL`、 `説明`、および `ServiceContextの` 引数 `addEntryWithGroupId`。 この例では、 `説明`ハードコードされた文字列が提供されています。 また、Mobile SDKが `ServiceContext` オブジェクトを処理するため、 `serviceContext` は `nil` です。

    override public func start() -> Bool {
        let session = SessionContext.createSessionFromCurrentSession()
        session?.callback = self
    
        let service = LRBookmarksEntryService_v7(session: session)
    
        do {
            try service.addEntryWithGroupId(LiferayServerContext.groupId,
                            folderId: folderId,
                            name: title,
                            url: url,
                            description: "Added from Liferay Screens",
                            serviceContext: nil)
    
            return true
        }
        catch {
            return false
        }
    }

最後に、 `AddBookmarkInteractor` クラスは、 `onFailure` および `onSuccess` メソッドを実装することにより、 `LRCallback` プロトコルに準拠する必要があります。 `onFailure` メソッドは、失敗したサーバー呼び出しの結果である `NSError` オブジェクトを通信します。 これは、ベース `Interactor` クラスの `callOnFailure` メソッドをエラーで呼び出すことにより行います。 サーバー呼び出しが成功すると、 `onSuccess` メソッドはサーバー呼び出しの結果（ `結果` 引数）を `resultBookmarkInfo` 変数に設定します。 `onSuccess` メソッドは、ベース `Interactor` クラスの `callOnSuccess` メソッドを呼び出して、スクリーンレット全体で成功ステータスを伝えることで終了します。

    public func onFailure(error: NSError!) {
        self.callOnFailure(error)
    }
    
    public func onSuccess(result: AnyObject!) {
        //Save result bookmark info
        resultBookmarkInfo = (result as! [String:AnyObject])
    
        self.callOnSuccess()
    }
