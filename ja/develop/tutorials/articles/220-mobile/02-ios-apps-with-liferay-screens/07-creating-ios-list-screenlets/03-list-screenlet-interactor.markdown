---
header-id: creating-the-ios-list-screenlets-interactor
---

# iOSリストスクリーンレットのインタラクターの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

インタラクターがスクリーンレットのアクションを実装することを思い出してください。 リストスクリーンレットでは、ユーザーが実行できるアクションは通常、エンティティの読み込みのみです。 流fluentなページネーションを実装するリストScreenletのInteractorクラスは、 [`BaseListPageLoadInteractor` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/BaseListPageLoadInteractor.swift)拡張する必要があります。 Interactorクラスには、Screenletが必要とするプロパティ、およびそれらを設定する初期化子も含まれている必要があります。 この初期化子には、スーパークラス初期化子に渡す次のプロパティの引数も必要です。

  - `スクリーンレット`： `BaseListScreenlet` リファレンス。 これにより、Interactorは常にスクリーンレット参照を保持します。
  - `ページ`：取得するページ番号。
  - `computeRowCount`：コネクターの `doAddRowCountServiceCall` メソッドを呼び出すかどうか。

たとえば、ブックマークリストスクリーンレットのInteractorクラスには、コネクタと同じ `groupId` および `folderId` プロパティと、それらを設定する初期化子が含まれています。 この初期化はまた、通過する `screenlet`、 `ページ`、及び `computeRowCount` スーパー初期の引数：

    public class BookmarkListPageLoadInteractor : BaseListPageLoadInteractor {
    
        private let groupId: Int64
        private let folderId: Int64
    
        init(screenlet: BaseListScreenlet,
            page: Int,
            computeRowCount: Bool,
            groupId: Int64,
            folderId: Int64) {
    
                self.groupId = (groupId != 0) ? groupId : LiferayServerContext.groupId
                self.folderId = folderId
    
                super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
        }
        ...

Interactorクラスは、Connectorをインスタンス化してサーバーリクエストを開始し、結果をモデルオブジェクトに変換する必要もあります。 `createListPageConnector` メソッドをオーバーライドして、コネクターのインスタンスを作成して返します。 このメソッドは、最初に `スクリーンレット` プロパティを介してスクリーンレットへの参照を取得する必要があります。 コネクターの初期化子を呼び出すときは、 `screenlet.firstRowForPage` を使用して、ページ番号（`ページ`）をページの開始および終了インデックスに変換します。 `groupId`など、必要な他のプロパティも初期化子に渡す必要があります。 たとえば、 `BookmarkListPageLoadInteractor`の `createListPageConnector` メソッドは、これを実行して `BookmarkListPageLiferayConnector` インスタンスを作成します。

    public override func createListPageConnector() -> PaginationLiferayConnector {
        let screenlet = self.screenlet as! BaseListScreenlet
    
        return BookmarkListPageLiferayConnector(
            startRow: screenlet.firstRowForPage(self.page),
            endRow: screenlet.firstRowForPage(self.page + 1),
            computeRowCount: self.computeRowCount,
            groupId: groupId,
            folderId: folderId)
    }

次に、 `convertResult` メソッドをオーバーライドして、各 `[String：AnyObject]` 結果をモデルオブジェクトに変換します。 スクリーンレットは、サーバーから取得したエンティティごとにこのメソッドを1回呼び出します。 たとえば、 `BookmarkListPageLoadInteractor`の `convertResult` メソッドは、 `[String：AnyObject]` 結果を `Bookmark` オブジェクトに変換します。

    override public func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {
        return Bookmark(attributes: serverResult)
    }

Interactorで [オフラインモード](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-offline-mode-in-liferay-screens) をサポートすることもできます。 これを行うには、Interactorが `cacheKey` メソッドをオーバーライドして、スクリーンレットに固有のキャッシュキーを返す必要があります。 たとえば、 `BookmarkListPageLoadInteractor`の `cacheKey` メソッドは、スクリーンレットの `groupId` および `folderId` 設定を含むキャッシュキーを返します。

    override public func cacheKey(_ op: PaginationLiferayConnector) -> String {
        return "\(groupId)-\(folderId)"
    }

すばらしいです\！ 次に、スクリーンレットのデリゲートを作成します。
