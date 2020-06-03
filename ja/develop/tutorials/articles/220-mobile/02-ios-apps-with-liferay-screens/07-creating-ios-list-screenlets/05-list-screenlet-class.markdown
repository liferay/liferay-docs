---
header-id: creating-the-ios-list-screenlets-class
---

# iOSリストスクリーンレットのクラスの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

リストScreenletの他のコンポーネントが存在するので、Screenletクラスを作成できます。 リストScreenletのScreenletクラスは、 [`BaseListScreenlet` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/BaseListScreenlet.swift) を拡張し、Screenletが必要とする構成可能なプロパティを定義する必要があります。 これらを `IBInspectable` プロパティとして定義する必要があります。 オフラインモードをサポートする場合は、 `offlinePolicy` プロパティも追加する必要があります。

例えば、 [ブックマークリストScreenletのScreenletクラス](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/BookmarkListScreenlet.swift) 含ま `IBInspectable` プロパティ `のgroupId`、 `フォルダID`、および `offlinePolicy`：

    public class BookmarkListScreenlet: BaseListScreenlet {
    
        @IBInspectable public var groupId: Int64 = 0
        @IBInspectable public var folderId: Int64 = 0
        @IBInspectable public var offlinePolicy: String? = CacheStrategyType.RemoteFirst.rawValue
    
        ...

次に、 `createPageLoadInteractor` メソッドをオーバーライドして、Interactorを作成して返します。 Screenletがオフラインモードをサポートしている場合、 `offlinePolicy` を使用して、 `CacheStrategyType` オブジェクトをInteractorに渡す必要もあります。 たとえば、 `BookmarkListScreenlet` の `createPageLoadInteractor` メソッドは、 `BookmarkListPageLoadInteractor` インスタンスを作成して返します。 このメソッドは、Interactorの `cacheStrategy` プロパティを、 `offlinePolicy`作成された `CacheStrategyType` オブジェクトに設定します。

    override public func createPageLoadInteractor(
        page page: Int, 
        computeRowCount: Bool) -> BaseListPageLoadInteractor {
    
        let interactor = BookmarkListPageLoadInteractor(screenlet: self,
                                                        page: page,
                                                        computeRowCount: computeRowCount,
                                                        groupId: self.groupId,
                                                        folderId: self.folderId)
    
        interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .RemoteFirst
    
        return interactor
    }

次に、デリゲートへの参照を取得します。 `BaseListScreenlet` が拡張する `BaseScreenlet` クラスは、デリゲートオブジェクトの `デリゲート` プロパティを既に定義しています。 したがって、すべてのリストScreenletにはこのプロパティがあり、Screenletを使用するアプリ開発者は誰でもアクセスできます。 このプロパティを使用するたびにデリゲートにキャストしないようにするには、計算するプロパティをScreenletクラスに追加します。 たとえば、 `BookmarkListScreenlet` の次の `bookmarkListDelegate` プロパティは、 `デリゲート` プロパティを `BookmarkListScreenletDelegate`キャストします。

    public var bookmarkListDelegate: BookmarkListScreenletDelegate? {
        return delegate as? BookmarkListScreenletDelegate
    }

次に、Screenletのイベントを処理する `BaseListScreenlet` メソッドをオーバーライドします。 これらのイベントはデリゲートメソッドが処理するイベントに対応するため、これらの `BaseListScreenlet` メソッドでデリゲートメソッドを呼び出します。

  - `onLoadPageResult`：スクリーンレットがページを正常にロードすると呼び出されます。 このメソッドをオーバーライドして、デリゲートの `screenlet（_：onBookmarkListResponse :)` メソッドを呼び出します。 たとえば、 `BookmarkListScreenlet`の `onLoadPageResult` メソッドは次のとおりです。

    ``` 
      override public func onLoadPageResult(page page: Int, rows: [AnyObject], rowCount: Int) {
          super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

          bookmarkListDelegate?.screenlet?(screenlet: self, onBookmarkListResponse: rows as! [Bookmark])
      }
    ```

  - `onLoadPageError`：スクリーンレットがページのロードに失敗すると呼び出されます。 このメソッドをオーバーライドして、デリゲートの `screenlet（_：onBookmarkListError :)` メソッドを呼び出します。 たとえば、 `BookmarkListScreenlet`の `onLoadPageError` メソッドは次のとおりです。

    ``` 
      override public func onLoadPageError(page page: Int, error: NSError) {
          super.onLoadPageError(page: page, error: error)

          bookmarkListDelegate?.screenlet?(screenlet: self, onBookmarkListError: error)
      }
    ```

  - `onSelectedRow`：リストでアイテムが選択されると呼び出されます。 このメソッドをオーバーライドして、デリゲートの `screenlet（_：onBookmarkSelected :)` メソッドを呼び出します。 たとえば、 `BookmarkListScreenlet`の `onSelectedRow` メソッドは次のとおりです。

    ``` 
      override public func onSelectedRow(_ row: AnyObject) {
          bookmarkListDelegate?.screenlet?(screenlet: self, onBookmarkSelected: row as! Bookmark)
      }
    ```
