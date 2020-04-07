---
header-id: creating-the-ios-list-screenlets-delegate
---

# iOSリストスクリーンレットのデリゲートの作成

[TOC levels=1-4]

Screenletのアクションに他のクラスが応答するようにするには、デリゲートが必要であることを思い出してください。 最初のステップ以下でデリゲートを作成します [Screenletのデリゲートの追加のチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/add-a-screenlet-delegate)。 リストスクリーンレットのデリゲートは、リストアイテムの選択に応答するためのメソッドも定義する必要があります。 たとえば、ブックマークリストスクリーンレットのデリゲートには次のメソッドが必要です。

  - `screenlet（_：onBookmarkListResponse :)`：サーバー呼び出しが成功した場合、 `ブックマーク` 結果を返します。
  - `screenlet（_：onBookmarkListError :)`：サーバー呼び出しが失敗したときに `NSError` オブジェクトを返します。
  - `screenlet（_：onBookmarkSelected :)`：ユーザーがリストで選択すると、 `ブックマーク` 返します。

`BookmarkListScreenletDelegate` プロトコルはこれらのメソッドを定義します：

    @objc public protocol BookmarkListScreenletDelegate : BaseScreenletDelegate {
    
        optional func screenlet(screenlet: BookmarkListScreenlet,
                                onBookmarkListResponse bookmarks: [Bookmark])
    
        optional func screenlet(screenlet: BookmarkListScreenlet,
                                onBookmarkListError error: NSError)
    
        optional func screenlet(screenlet: BookmarkListScreenlet,
                                onBookmarkSelected bookmark: Bookmark)
    
    }

よくやった\！ 次に、Screenletクラスを作成します。
