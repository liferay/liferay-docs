---
header-id: creating-the-ios-list-screenlets-connector
---

# iOSリストスクリーンレットのコネクタの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

コネクタはサーバー呼び出しを行うことを思い出してください。 ページネーションをサポートするには、List ScreenletのConnectorクラスが [`PaginationLiferayConnector` クラス](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/PaginationLiferayConnector.swift)拡張する必要があります。 Connectorクラスには、サーバー呼び出しを行うために必要なすべてのプロパティと、それらを設定する初期化子も含まれている必要があります。 ページネーションをサポートするには、イニシャライザに次の引数も含める必要があります。これらの引数は、スーパークラスのイニシャライザに渡します。

  - `startRow`：ページの最初の行を表す番号。
  - `endRow`：ページの最後の行を表す番号。
  - `computeRowCount`：コネクターの `doAddRowCountServiceCall` メソッドを呼び出すかどうか（このメソッドについては後ほど説明します）。

たとえば、ブックマークリストスクリーンレットは、特定のサイトのブックマークポートレットフォルダからブックマークを取得する必要があります。 したがって、ScreenletのConnectorクラスには、 `groupId` （サイトID）および `folderId` （ブックマークフォルダーID）のプロパティと、それらを設定する初期化子が必要です。 イニシャライザも有するスーパーイニシャライザ呼び出し `startRow属性`、 `とendRow`、及び `computeRowCount` 引数：

    import UIKit
    import LiferayScreens
    
    public class BookmarkListPageLiferayConnector: PaginationLiferayConnector {
    
        public let groupId: Int64
        public let folderId: Int64
    
        //MARK: Initializer
    
        public init(startRow: Int, endRow: Int, computeRowCount: Bool, groupId: Int64, 
            folderId: Int64) {
    
                self.groupId = groupId
                self.folderId = folderId
    
                super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
        }
        ...

次に、あなたがオーバーライドし、あなたのScreenletのプロパティのいずれかを検証したい場合は `validateData` に記載の方法を [コネクタの作成に関するチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/create-and-use-a-connector-with-your-screenlet)。 ブックマークリストスクリーンレットは、 `folderId`のみを検証する必要があることに注意してください。

    override public func validateData() -> ValidationError? {
        let error = super.validateData()
    
        if error == nil {
            if folderId <= 0 {
                return ValidationError("Undefined folderId")
            }
        }
    
        return error
    }

最後に、Connectorクラスの次の2つのメソッドをオーバーライドする必要があります。

  - `doAddPageRowsServiceCall`：エンティティのページを取得するLiferay Mobile SDKサービスメソッドを呼び出します。 `doAddPageRowsServiceCall` メソッドの `startRow` および `endRow` 引数は、それぞれページの最初と最後のエンティティを指定します。 スクリーンレットの場合と同様に、サービスを呼び出します。 たとえば、 `BookmarkListPageLiferayConnector` の `doAddPageRowsServiceCall` メソッドは、サービスの `getEntriesWithGroupId` メソッドを呼び出して、 `folderId`指定されたフォルダーからブックマークのページを取得します。

    ``` 
      public override func doAddPageRowsServiceCall(session session: LRBatchSession, 
          startRow: Int, endRow: Int, obc: LRJSONObjectWrapper?) {
              let service = LRBookmarksEntryService_v7(session: session)

          do {
              try service.getEntriesWithGroupId(groupId,
                                                folderId: folderId,
                                                start: Int32(startRow),
                                                end: Int32(endRow))
          }
          catch  {
              // ignore error: the service method returns nil because
              // the request is sent later, in batch
          }
      }
    ```

    要求はバッチで後で送信されるため、 `catch` ステートメントでは何もする必要がないことに注意してください。 `セッション` タイプ `LRBatchSession` がこれを処理します。 リクエストが完了すると、リクエストの結果を別の場所で受け取ります。

  - `doAddRowCountServiceCall`：エンティティの総数を取得するLiferay Mobile SDKサービスメソッドを呼び出します。 これはページネーションをサポートします。 スクリーンレットの場合と同様に、サービスを呼び出します。 たとえば、 `BookmarkListPageLiferayConnector` の `doAddRowCountServiceCall` は、サービスの `getEntriesCountWithGroupId` メソッドを呼び出して、 `folderId`指定されたフォルダー内のブックマークの総数を取得します。

    ``` 
      override public func doAddRowCountServiceCall(session session: LRBatchSession) {
          let service = LRBookmarksEntryService_v7(session: session)

          do {
              try service.getEntriesCountWithGroupId(groupId, folderId: folderId)
          }
          catch  {
              // ignore error: the service method returns nil because
              // the request is sent later, in batch
          }
      }
    ```

Connectorクラスができたので、Interactorを作成する準備ができました。
